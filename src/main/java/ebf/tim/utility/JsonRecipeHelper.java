package ebf.tim.utility;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.*;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * This class will handle parsing and adding recipes to the TiM table.
 * Recipes should be written in json and added to the resources section as if it was a 1.12 or newer mod. Json recipes
 * should use the same format as 1.12 and newer recipes. See the forge documentation for more information:
 * @see <a href="https://mcforge.readthedocs.io/en/1.12.x/utilities/recipes/">Recipes documentation</a>
 *
 * @note For forge 1.12 and greater, make a simple wrapper for this class. There were too many minor differences and I
 *       didn't want to leave landmines of commented out code.
 *
 * @note Recipe json may have to change in future due to changes in naming, ie. dyes changed
 *
 * @author ComputerButter
 */
public class JsonRecipeHelper {

    //I used this logger because it makes debugging easier and will show up nicer in logs both for users and us.
    private static Logger LOGGER = LogManager.getLogger("trainsinmotion");

    /**
     * This function handles parsing and adding a 3x3 (or smaller) json recipe to the TiM table.
     *
     * @param json the json of the recipe we need to parse
     */
    public static void parseAndAddRecipe(JsonObject json) {
        if (json == null || json.isJsonNull()) {
            //make sure not empty, just in case
            return;
        }

        if (!getString(json, "type").equals("trainsinmotion:table")) {
            LOGGER.log(Level.WARN, "TiM table given recipe to parse that is not for it.");
            return; //don't parse non-TiM table recipes.
        }

        //get result first
        ItemStack craftingResult = deserializeItem(json.getAsJsonObject("result"), true);

        //parse the crafting grid
        // first parse the keys ("X": { ... }, ...)
        Map<String, ItemStack[]> keyMap = deserializeKey(json.getAsJsonObject("key"));

        //get the pattern of the crafting recipe
        String[] patternStrings = patternFromJson(json.getAsJsonArray("pattern"));

        int craftingWidth = patternStrings[0].length();
        int craftingHeight = patternStrings.length;

        //use keymap with grid to generate resulting recipe input.
        List<List<ItemStack>> ingredients = deserializeIngredients(patternStrings, keyMap);

        //create and add recipe to workbench.
        RecipeManager.registerRecipe(new SizedRecipe(craftingResult, ingredients, craftingWidth, craftingHeight));
    }

    /**
     * Takes an jsonObject that usually looks like:
     * {
     * "item": "minecraft:rail"
     * }
     * and turns it into an ItemStack.
     *
     * @param json     jsonObject to parse
     * @param useCount whether or not to include number of items included with
     * @return An ItemStack representing the given jsonObject, null if not found.
     */
    @Nullable
    private static ItemStack deserializeItem(JsonObject json, boolean useCount) {
        String itemString = getString(json, "item");

        //NOTE: IN 1.12, this was in Traincraft/Traincraft, but also might only be for vanilla items:
        //Item item = Item.REGISTRY.getObject(new ResourceLocation(itemString));
        Item item = GameData.getItemRegistry().getObject(itemString);

        if (item == null) {
            throw new JsonSyntaxException("Unknown item '" + itemString + "'");
        } else if (item.getHasSubtypes() && !json.has("data")) {
            throw new JsonParseException("Missing data for item '" + itemString + "'");
        } else {
            int i = getInt(json, "data", 0);
            int j = useCount ? getInt(json, "count", 1) : 1;
            return new ItemStack(item, j, i);
        }
    }

    /**
     * Turns the "key" json object into a map of recipe ingredient keys (ie. #, X, etc.) and the corresponding ingredient.
     *
     * @param json The object containing the array of keys
     * @return A map of the keys to a group of ItemStacks that it represents (can be multiple because of ore dict)
     */
    private static Map<String, ItemStack[]> deserializeKey(JsonObject json) {
        Map<String, ItemStack[]> map = Maps.newHashMap();

        //iterate through keys
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            if (((String) entry.getKey()).length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + (String) entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }

            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }

            map.put(entry.getKey(), deserializeIngredient(entry.getValue()));
        }

        //space will be handled as null elsewhere
        //map.put(" ", Ingredient.EMPTY);
        return map;
    }

    /**
     * Takes a jsonElement describing a Ingredient and turns it into the corresponding Ingredient. This is very similar
     * to deserializeItem, except for more support for things other than simply the "item:" tag
     *
     * @param jsonElement object/array of objects describing single ingredient.
     * @return the ingredient
     */
    private static ItemStack[] deserializeIngredient(@Nullable JsonElement jsonElement) {
        if (jsonElement != null && !jsonElement.isJsonNull()) {

            //first check if using ore dict, otherwise do normal stuff
            if (jsonElement.getAsJsonObject().has("type")) {
                if (jsonElement.getAsJsonObject().get("type").getAsString().equals("forge:ore_dict")) {
                    if (jsonElement.getAsJsonObject().has("ore")) {
                        //find all ItemStacks for ore
                        ArrayList<ItemStack> oresFromOreDict = OreDictionary.getOres(jsonElement.getAsJsonObject().get("ore").getAsString());
                        ItemStack[] itemStacks = new ItemStack[oresFromOreDict.size()];
                        for (int i = 0; i < oresFromOreDict.size(); ++i) {
                            itemStacks[i] = oresFromOreDict.get(i).copy();
                        }

                        int count = getInt(jsonElement.getAsJsonObject(), "count", 1);
                        for (ItemStack itemStack : itemStacks) {
                            itemStack.stackSize = count;
                        }
                        return itemStacks;

                    } else {
                        throw new JsonSyntaxException("Does not contain ore item.");
                    }
                } else {
                    throw new JsonSyntaxException("Does not support non forge:ore_dict types yet. You may have made typo.");
                }

            } else if (jsonElement.isJsonObject()) {
                return new ItemStack[]{deserializeItem(jsonElement.getAsJsonObject(), true)};
            } else if (!jsonElement.isJsonArray()) {
                throw new JsonSyntaxException("Expected item to be object or array of objects");
            } else {
                JsonArray jsonarray = jsonElement.getAsJsonArray();

                if (jsonarray.size() == 0) {
                    throw new JsonSyntaxException("Item array cannot be empty, at least one item must be defined");
                } else {
                    ItemStack[] aitemstack = new ItemStack[jsonarray.size()];

                    for (int i = 0; i < jsonarray.size(); ++i) {
                        aitemstack[i] = deserializeItem(getJsonObject(jsonarray.get(i), "item"), true);
                    }

                    return aitemstack;
                }
            }
        } else {
            throw new JsonSyntaxException("Item cannot be null");
        }
    }

    /**
     * This function takes the array of strings that make up the pattern and turn it into an array of Strings, which is
     * considerably easier to work with than raw json stuff.
     *
     * @param jsonArr the array of strings, looks like: ["XXX", "E E", "OO "] or similar. Can be not 3x3, must be square-shaped
     * @return An array of 1-3 strings.
     */
    private static String[] patternFromJson(JsonArray jsonArr) {
        String[] astring = new String[jsonArr.size()];

        if (astring.length > 3) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, 3 is maximum");
        } else if (astring.length == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        } else {
            for (int i = 0; i < astring.length; ++i) {
                String s = getString(jsonArr.get(i), "pattern[" + i + "]");

                if (s.length() > 3) {
                    throw new JsonSyntaxException("Invalid pattern: too many columns, 3 is maximum");
                }

                if (i > 0 && astring[0].length() != s.length()) {
                    throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
                }

                astring[i] = s;
            }

            //pad rows with empty spaces so becomes 3x3
//            for (int i = 0; i < astring.length; i++) {
//                while (astring[i].length() < 3) {
//                    astring[i] = astring[i] + " ";
//                }
//            }
//
//            if (astring.length < 3) {
//                //pad with empty rows so it becomes 3x3
//                String[] retString = new String[3];
//                for (int i = 0; i < 3; i++) {
//                    if (astring.length < i) {
//                        retString[i] = astring[i];
//                    } else {
//                        retString[i] = "   ";
//                    }
//                }
//                return retString;
//            }

            return astring;
        }
    }

    /**
     * Takes in a map of symbols to the ingredients and a array of strings corresponding to how each row looks in the table,
     * and converts it to a list of ingredients. length of list depends on craftingWidth and Height.
     *
     * @param pattern An array of strings that represent how pattern looks in crafting table
     * @param keys    A map relating characters in pattern with the Items they represent.
     * @return
     */
    private static List<List<ItemStack>> deserializeIngredients(String[] pattern, Map<String, ItemStack[]> keys /*, int craftingWidth, int craftingHeight*/) {
        List<List<ItemStack>> finalList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            finalList.add(null); //.add(new ArrayList<ItemStack>(0));g
        }
        Set<String> set = Sets.newHashSet(keys.keySet());
        //set.remove(" "); //not present anyways

        for (int i = 0; i < pattern.length; ++i) {
            for (int j = 0; j < pattern[i].length(); ++j) {
                String s = pattern[i].substring(j, j + 1);
                if (s.equals(" ")) {
                    continue;
                }
                ItemStack[] ingredient = keys.get(s);

                if (ingredient == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
                }

                set.remove(s);
                finalList.set(j + (pattern[0].length()) * i, Arrays.asList(ingredient));
            }
        }

        if (!set.isEmpty()) {
            LOGGER.log(Level.WARN, "Key defines symbols that aren't used in pattern: " + set);
        }

        return finalList;
    }

    /**
     * Removes empty rows and columns from the crafting recipe, good for being able to use (ie) one row recipe in any row.
     *
     * @param toShrink array of strings to shrink.
     * @return shrunken recipe
     */
    private static String[] shrink(String... toShrink) {
        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;

        for (int i1 = 0; i1 < toShrink.length; ++i1) {
            String s = toShrink[i1];
            i = Math.min(i, firstNonSpace(s));
            int j1 = lastNonSpace(s);
            j = Math.max(j, j1);

            if (j1 < 0) {
                if (k == i1) {
                    ++k;
                }

                ++l;
            } else {
                l = 0;
            }
        }

        if (toShrink.length == l) {
            return new String[0];
        } else {
            String[] astring = new String[toShrink.length - l - k];

            for (int k1 = 0; k1 < astring.length; ++k1) {
                astring[k1] = toShrink[k1 + k].substring(i, j + 1);
            }

            return astring;
        }
    }

    //ngl, never seen a for loop used in the way it is in the next two functions. v cool tho
    private static int firstNonSpace(String str) {
        int i;

        for (i = 0; i < str.length() && str.charAt(i) == ' '; ++i) {
            ;
        }

        return i;
    }

    private static int lastNonSpace(String str) {
        int i;

        for (i = str.length() - 1; i >= 0 && str.charAt(i) == ' '; --i) {
            ;
        }

        return i;
    }

    ///MARK: more small helper functions related to json. In future versions of minecraft, at least 1.12 and up, these are
    /// built into the JsonUtils class, a very helpful class I wish was here. I basically copied and merged functions
    /// so they work slightly faster for our use case at the expense of perfect error logging.

    private static int getInt(JsonElement json, String memberName) {
        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isNumber()) {
            return json.getAsInt();
        } else {
            throw new JsonSyntaxException("Expected " + memberName + " to be a Int.");
        }
    }

    //    private static int getInt(JsonObject json, String memberName) {
//        if (json.has(memberName)) {
//            return getInt(json.get(memberName), memberName);
//        } else {
//            throw new JsonSyntaxException("Missing " + memberName + ", expected to find a Int");
//        }
//    }
    private static int getInt(JsonObject json, String memberName, int fallback) {
        return json.has(memberName) ? getInt(json.get(memberName), memberName) : fallback;
    }

    private static String getString(JsonElement json, String memberName) {
        if (json.isJsonPrimitive()) {
            return json.getAsString();
        } else {
            throw new JsonSyntaxException("Expected " + memberName + " to be a string.");
        }
    }

    private static String getString(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return getString(json.get(memberName), memberName);
        } else {
            throw new JsonSyntaxException("Missing " + memberName + ", expected to find a string");
        }
    }
//    private static String getString(JsonObject json, String memberName, String fallback) {
//        return json.has(memberName) ? getString(json.get(memberName), memberName) : fallback;
//    }

    private static JsonObject getJsonObject(JsonElement json, String memberName) {
        if (json.isJsonObject()) {
            return json.getAsJsonObject();
        } else {
            throw new JsonSyntaxException("Expected " + memberName + " to be a JsonObject.");
        }
    }
//    private static JsonObject getJsonObject(JsonObject json, String memberName) {
//        if (json.has(memberName)) {
//            return getJsonObject(json.get(memberName), memberName);
//        } else {
//            throw new JsonSyntaxException("Missing " + memberName + ", expected to find a JsonObject");
//        }
//    }
//    private static JsonObject getJsonObject(JsonObject json, String memberName, JsonObject fallback) {
//        return json.has(memberName) ? getJsonObject(json.get(memberName), memberName) : fallback;
//    }

    /**
     * Loads recipes from given folder, recursively searching through subdirectories.
     * *For addon writers*: the `recipeFolder` should be your modID + "/recipes"
     *
     * @return if the function completed successfully.
     */
    public static boolean loadRecipes(String modID, Class modClass) {
        //get the manifest and enumerate it.

        InputStream stream = modClass.getClassLoader().getResourceAsStream("assets/" + modID + "/recipes/_manifest");
        if (stream == null) {
            LOGGER.log(Level.FATAL, "Could not find manifest file.");
            return false;
        }
        InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader);

        String line;
        while (true) {
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                LOGGER.log(Level.FATAL, "Unable to get a new line of manifest.");
                e.printStackTrace();
                return false;
            }

            if (line == null) {
                break;
            }

            if (line.equals("") || line.startsWith("#")) {
                continue;
            }

            InputStream file = modClass.getResourceAsStream("/assets/" + modID + "/recipes/" + line);
            if (file == null) {
                LOGGER.log(Level.FATAL, "Could not load file referenced in manifest: " + line);
                continue;
            }
            InputStreamReader fileSR = new InputStreamReader(file, StandardCharsets.UTF_8);
            BufferedReader fileBR = new BufferedReader(fileSR);
            StringBuilder fileContents = new StringBuilder();
            String l;
            for (;;) {
                try {
                    l = fileBR.readLine();
                } catch (IOException e) {
                    break;
                }
                if (l == null)
                    break;
                fileContents.append(l);
            }
            parseAndAddRecipe(new Gson().fromJson(fileContents.toString(), JsonObject.class));
        }

        try { bufferedReader.close(); streamReader.close(); stream.close(); } catch (IOException ignored) {}

        return true;
    }

    public static boolean loadRecipesWithCURecursiveLoader(String recipeFolder) {
        Gson gson = new Gson();

        List<String> objects = CommonUtil.listFolders(recipeFolder);

        if (objects.size() == 0) {
            LOGGER.error("No recipes or recipe folders found.");
        }

        for (String item : objects) {
            LOGGER.info("Item found: " + item);
            if (item.endsWith(".json")) {
                if (item.contains("/_"))  {
                    continue; //don't parse json files starting with underscore.
                }
                //json not starting with underscore is a recipe
                try {
                    parseAndAddRecipe(gson.fromJson(CommonUtil.accessFile(item), JsonObject.class));
                } catch (JsonParseException jsonParseException) {
                    LOGGER.error("[TiM] problem handling json recipe: " + item);
                    jsonParseException.printStackTrace();
                }
            } else {
                //folder
                loadRecipesWithCURecursiveLoader(item);
            }
        }
        return true;
    }

    /**
     * Base of this function is simply yoinked from forge 1.12, vanilla. Then modified extensively to be compatible for 1.7.10
     *
     * @param modID modid of the mod to get recipes for.
     * @return Whether it worked or not
     */
    public static boolean oldloadRecipes(String modID, Class modClass) {
        FileSystem filesystem = null;

        try {
            URL url = modClass.getProtectionDomain().getCodeSource().getLocation(); //we need to get something to figure out if in jar or filesystem

            if (url != null) {
                LOGGER.info("URL of this mod's main class: " + url.getPath());

                URI uri = url.toURI();
                Path path;

                //following block figures out the correct path that the recipes are in.
                if ("file".equals(uri.getScheme())) { //if we are using a filesystem
                    path = Paths.get(CraftingManager.class.getResource("/assets/" + modID + "/recipes").toURI());
                } else { //make sure we are using a jar scheme then
                    if (!"jar".equals(uri.getScheme())) { //if not using a jar, well fuck
                        LOGGER.error("Unsupported scheme " + uri + " trying to list all recipes");
                        return false;
                    }
                    filesystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
                    path = filesystem.getPath("/assets/" + modID +"/recipes");
                }

                //recursively walks found path and parses files that don't start with underscore.
                // yoinkie yoinkie https://www.techiedelight.com/traverse-directory-print-files-java-7-8/
                try {
                    Path start = Paths.get(path.toUri()); //problem line
                    Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path filePath, BasicFileAttributes attributes) {
                            //do stuff with a recipe path:
                            try {
                                //continue if file name starts with underscore.
                                int lastSlash = filePath.toString().lastIndexOf("/"); //find slashes
                                if (lastSlash == -1) lastSlash = filePath.toString().lastIndexOf("\\"); //if no forward slashes, find backslashes
                                if (filePath.toString().substring(lastSlash + 1, lastSlash + 2).equals("_")) return FileVisitResult.CONTINUE;

                                //finally parse and add the recipe.
                                parseAndAddRecipe(getJsonFromPath(filePath));
                            } catch (Exception e) {
                                LOGGER.log(Level.ERROR, "Problem trying to get recipe: " + filePath.toString() + ": " + e);
                                if (!(e instanceof JsonSyntaxException)) {
                                    e.printStackTrace(); //only stack trace if it isn't a json error.
                                }
                            }
                            return FileVisitResult.CONTINUE;
                        }
                    });
                } catch (IOException e) {
                    LOGGER.error("[TiM] Problem walking recipe file tree: " + e);
                    e.printStackTrace();
                    return false;
                } catch (FileSystemNotFoundException e) {
                    //todo: fix this
                    LOGGER.error("[TiM] Could not create file system, SKIPPING loading recipes. error: " + e);
                    e.printStackTrace();
                    return false;
                }

                return true;
            }

            LOGGER.error("Couldn't find assets!?");
        } catch (IOException | URISyntaxException urisyntaxexception) {
            LOGGER.error("Couldn't get a list of all recipe files", urisyntaxexception);
            return false;
        } finally {
            IOUtils.closeQuietly(filesystem);
        }

        return false;
    }

    private static JsonObject getJsonFromPath(Path path) {
        String s = FilenameUtils.removeExtension(path.toString()).replaceAll("\\\\", "/");
        ResourceLocation resourcelocation = new ResourceLocation(s);
        BufferedReader bufferedreader = null;

        try {
            try {
                List<String> listOfLines = Files.readAllLines(path, Charset.forName("UTF-8"));
                StringBuilder sb = new StringBuilder();
                for(String str: listOfLines) {
                    sb.append(str);
                }
                return new Gson().fromJson(sb.toString(), JsonObject.class);
            } catch (JsonParseException jsonparseexception) {
                LOGGER.error("Parsing error loading recipe " + resourcelocation, jsonparseexception);
                return null;
            } catch (IOException ioexception) {
                LOGGER.error("Couldn't read recipe " + resourcelocation + " from " + path, ioexception);
                return null;
            }
        } finally {
            IOUtils.closeQuietly(bufferedreader);
        }
    }
}