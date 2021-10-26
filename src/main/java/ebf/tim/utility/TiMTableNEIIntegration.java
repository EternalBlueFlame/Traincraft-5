package ebf.tim.utility;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.API;
import codechicken.nei.recipe.TemplateRecipeHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic NEI integration for the traintable. This class will only work for the tier 0 of trains.
 * For other tiers of traintable, subclass it and override:
 *   {@link #loadCraftingRecipes(ItemStack)}
 *   {@link #loadUsageRecipes(ItemStack)}
 *   {@link #getGuiTexture()}
 *   {@link #setupNEIintegration()}
 *   See the functions for more detail.
 *
 *  Also subclass the CachedTiMTableRecipe and override the setIngredients(List) function, see it for more detail.
 *
 *  Contact for more info:
 * @author ComputerButter
 */
public class TiMTableNEIIntegration extends TemplateRecipeHandler {

    /**
     * Class that handles making recipes nei-understandable. Based heavily on the ShapedRecipeHandler.
     * https://github.com/Chicken-Bones/NotEnoughItems/blob/cb056a63d9184cbded7f98c3c079636ffd4ba343/src/codechicken/nei/recipe/ShapedRecipeHandler.java is sauce
     *
     * For other tiers of recipes, all you need to do is extend this and override the {@link #setIngredients(List)} function
     *   See this implementation for an example.
     * Also override the {@link #getOutputPosition()} function, as that tells nei where to put the output stack.
     */
    public class CachedTiMTableRecipe extends TemplateRecipeHandler.CachedRecipe {

        private PositionedStack result;
        private ArrayList<PositionedStack> ingredients;
        private Recipe recipe;

        public CachedTiMTableRecipe(Object _ingredient) {
            if (_ingredient instanceof ItemStack) {
                this.recipe = RecipeManager.getRecipe((ItemStack) _ingredient);

                if (recipe == null) { //Swift's guard statements would sure be nice here.
                    DebugUtil.println("[NEI INTEGRATION] Could not get recipe from ingredient.");
                } else if (recipe.getTier() != 0) {
                    result = new PositionedStack(_ingredient, getOutputPosition()[0], getOutputPosition()[1]);
                    result.setMaxSize(recipe.getresult().get(0).stackSize);
                    ingredients = setIngredients(recipe.getRecipeItems());
                } else {
                    result = new PositionedStack(_ingredient, getOutputPosition()[0], getOutputPosition()[1]);
                    result.setMaxSize(recipe.getresult().get(0).stackSize);
                    if (recipe instanceof SizedRecipe) { //sized recipe, have custom size.
                        this.ingredients = setIngredients(((SizedRecipe) recipe).getCraftWidth(), ((SizedRecipe) recipe).getCraftHeight(), recipe.getRecipeItems());
                    } else { //normal recipe class
                        this.ingredients = setIngredients(3,3, recipe.getRecipeItems());
                    }
                }
            }
        }

        @Override
        public PositionedStack getResult() {
            return result;
        }

        public Recipe getRecipe() {
            return recipe;
        }

        /**
         * Cycles through the ingredients rather than just display one, also copied from ShapedRecipe class
         * @return the items to show
         */
        @Override
        public List<PositionedStack> getIngredients() {
            return getCycledIngredients(cycleticks / 20, ingredients);
        }

        /**
         * Only method you need to override if you are implementing a different tier of recipes.
         * Takes the recipe's inputs and turns it into a positionedStack signifying where each ingredient should go.
         * You also have access to the Recipe class that this is representing.
         * @param items A list of recipe ingredients.
         * @return A list of positioned stacks with recipe items.
         */
        public ArrayList<PositionedStack> setIngredients(List<List<ItemStack>> items) {
            return setIngredients(3 ,3, items);
        }

        //copied and slightly modified from the ShapedRecipeHandler, maps ingredient list to the places where ingredient should go
        private ArrayList<PositionedStack> setIngredients(int width, int height, List<List<ItemStack>> items) {
            ArrayList<PositionedStack> tempIng = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (items.get(y * width + x) == null) {
                        continue;
                    } else if (items.get(y * width + x).size() == 0 || items.get(y * width + x).get(0) == null) {
                        continue;
                    }

                    //can make a positioned stack from either a single ItemStack, array or list of ItemStacks. Here we use the list of ItemStack
                    PositionedStack stack = new PositionedStack(items.get(y * width + x), 25 + x * 18, 6 + y * 18, true);

                    int ss = items.get(y * width + x).get(0).stackSize;
                    for (ItemStack perm : stack.items) {
                        perm.stackSize = ss;
                    }

                    tempIng.add(stack);
                }
            }
            return tempIng;
        }

        /**
         * Function that tells the constructor where to place the output stack in the gui.
         * Override this for other tiers of table where output position is not the vanilla crafting table position.
         * @return an array of two ints, representing x position and y position
         */
        public int[] getOutputPosition() {
            return new int[]{119, 24};
        }
        //end of CachedRecipe class
    }

    //loads recipes from output, basically redirects to the below function.
    @Override
    public void loadCraftingRecipes(String outputId, Object...results) {
        if ((outputId.equals("item") || outputId.equals("timtable")) && (results.length > 0)) {
            loadCraftingRecipes((ItemStack)results[0]);
        }
    }

    /**
     * Loads recipes based on the output of the recipe.
     * Override this function for other tiers and slightly modify it for your version of CachedRecipe
     * @param result The output of a recipe.
     */
    @Override
    public void loadCraftingRecipes(ItemStack result) {
        Recipe recipe = RecipeManager.getRecipe(result);

        if(recipe != null && recipe.getTier() == 0) {
            CachedRecipe cachedRecipe = new CachedTiMTableRecipe(result);
            arecipes.add(cachedRecipe);
        }
    }

    //don't have to touch this method, basically redirects to below func.
    @Override
    public void loadUsageRecipes(String inputId, Object... ingredients) {
        if ((inputId.equals("item") || inputId.equals("timtable")) && ingredients.length > 0) {
            this.loadUsageRecipes((ItemStack)ingredients[0]);
        }
    }

    /**
     * Loads recipes based on the ingredients in them. This function will list all the recipes that contain the itemStack
     * and all those will be shown.
     * Override this for other tiers, and slightly modify for your tier of table.
     * @param itemStack The item that is in the recipes' ingredients.
     */
    @Override
    public void loadUsageRecipes(ItemStack itemStack) {
        List<Recipe> matchingRecipes = RecipeManager.getRecipesContaining(itemStack, 0);
        for (Recipe r : matchingRecipes) {
            for (ItemStack crafted : r.getresult()) {
                CachedRecipe cr = new CachedTiMTableRecipe(crafted);
                arecipes.add(cr);
            }
        }
    }

    /**
     * This is the background for where the items are displayed.
     * Override this to provide your own crafting texture
     * @return String path to your crafting table texture.
     */
    @Override
    public String getGuiTexture() {
        return "textures/gui/container/crafting_table.png";
    }

    @Override
    public String getRecipeName() {
        return I18n.format("container.traincrafting");
    }

    @Override
    public String getOverlayIdentifier() {
        return "timtable";
    }

    /**
     * Helper function that gets called in setup to add this integration to NEI.
     * Override this function then call it in your mod's postInit function to register your handler.
     */
    public static void setupNEIintegration() {
        TemplateRecipeHandler recipeHandler = new TiMTableNEIIntegration();
        API.registerUsageHandler(recipeHandler);
        API.registerRecipeHandler(recipeHandler);
    }
}
