package train.core.plugins;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.api.API;
import codechicken.nei.recipe.TemplateRecipeHandler;
import ebf.tim.utility.Recipe;
import ebf.tim.utility.RecipeManager;
import ebf.tim.utility.TiMTableNEIIntegration;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.blocks.TCBlocks;
import train.library.Info;

import java.util.ArrayList;
import java.util.List;

public class AssemblyTableNEIIntegration extends TiMTableNEIIntegration {
    private static final ResourceLocation[] asmTableGUIs = {
            new ResourceLocation(Info.modID, "textures/gui/gui_tierI_ironAge.png"),
            new ResourceLocation(Info.modID, "textures/gui/gui_tierII_steelAge.png"),
            new ResourceLocation(Info.modID, "textures/gui/gui_tierIII_advancedAge.png")
    };

    public class CachedAsmTableRecipe extends CachedTiMTableRecipe {

        public CachedAsmTableRecipe(Object _ingredient) {
            super(_ingredient);
        }

        @Override
        public ArrayList<PositionedStack> setIngredients(List<List<ItemStack>> items) {
            int[] inputXPos = {25, 79, 115, 145, 25, 79, 115, 43, 79, 145};
            int[] inputYPos = {27, 27, 27,  27,  61, 61, 61,  93, 93, 93};
            ArrayList<PositionedStack> tempStacks = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                if (items.size() <= i) break;
                if (items.get(i) != null && items.get(i).get(0) != null) {
                    PositionedStack ps = new PositionedStack(items.get(i), inputXPos[i], inputYPos[i], true);

                    int ss = items.get(i).get(0).stackSize;
                    for (ItemStack perm : ps.items) {
                        perm.stackSize = ss;
                    }

                    tempStacks.add(ps);
                }
            }
            return tempStacks;
        }

        @Override
        public int[] getOutputPosition() {
            return new int[]{92, 128};
        }
    }

    @Override
    public String getGuiTexture() {
        return Info.modID + ":textures/gui/gui_tierI_ironAge.png";
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        Recipe recipe = RecipeManager.getRecipe(result, TCBlocks.trainTableTier1);

        if(recipe != null) {
            CachedRecipe cachedRecipe = new CachedAsmTableRecipe(result);
            arecipes.add(cachedRecipe);
        }
        recipe = RecipeManager.getRecipe(result, TCBlocks.trainTableTier2);

        if(recipe != null) {
            CachedRecipe cachedRecipe = new CachedAsmTableRecipe(result);
            arecipes.add(cachedRecipe);
        }
        recipe = RecipeManager.getRecipe(result, TCBlocks.trainTableTier3);

        if(recipe != null) {
            CachedRecipe cachedRecipe = new CachedAsmTableRecipe(result);
            arecipes.add(cachedRecipe);
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack itemStack) {
        List<Recipe> matchingRecipes = RecipeManager.getRecipesContaining(itemStack, TCBlocks.trainTableTier1);
        matchingRecipes.addAll(RecipeManager.getRecipesContaining(itemStack, TCBlocks.trainTableTier2));
        matchingRecipes.addAll(RecipeManager.getRecipesContaining(itemStack, TCBlocks.trainTableTier3));
        for (Recipe r : matchingRecipes) {
            for (ItemStack crafted : r.getresult()) {
                CachedRecipe cr = new CachedAsmTableRecipe(crafted);
                arecipes.add(cr);
            }
        }
    }

    @Override
    public String getRecipeName() {
        return I18n.format("tile.block.asmtable.name");
    }

    //Following three functions do not have to be overridden but are necessary for this specific table

    @Override
    public void drawExtras(int recipe) { //draws text showing correct tier.
        CachedRecipe crecipe = arecipes.get(recipe);
        if (crecipe instanceof CachedAsmTableRecipe) {
            Block tier = ((CachedAsmTableRecipe) crecipe).getRecipeTable();
            FontRenderer frenderer = Minecraft.getMinecraft().fontRenderer;
            String tierS = "I";
            if(tier==TCBlocks.trainTableTier2){
                tierS = "II";
            } else if(tier==TCBlocks.trainTableTier3){
                tierS = "III";
            }
            frenderer.drawString(I18n.format("tile.block.asmtable.name") + " " + tierS, 10, 6, 12241200);
        }
    }

    //this is not a function that has to be overridden, but the assemblytable is different per tier and in sizing.
    @Override
    public void drawBackground(int recipe) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (arecipes.get(recipe) instanceof CachedAsmTableRecipe) {
            Block tier = ((CachedAsmTableRecipe) arecipes.get(recipe)).getRecipeTable();

            if(tier==TCBlocks.trainTableTier1){
                GuiDraw.changeTexture(asmTableGUIs[0]);
            } else if(tier==TCBlocks.trainTableTier2){
                GuiDraw.changeTexture(asmTableGUIs[1]);
            } else if(tier==TCBlocks.trainTableTier3){
                GuiDraw.changeTexture(asmTableGUIs[2]);
            }
            GuiDraw.drawTexturedModalRect(0, 0, 0, 0, 175, 170);
        } else {
            //default (slightly modified from TemplateRecipeHandler)
            GuiDraw.changeTexture(getGuiTexture());
            GuiDraw.drawTexturedModalRect(0, 0, 0, 0, 166, 165);
        }
    }

    @Override
    public int recipiesPerPage() {
        return 1;
    }

    public static void setupNEIIntegration() {
        TemplateRecipeHandler recipeHandler = new AssemblyTableNEIIntegration();
        API.registerUsageHandler(recipeHandler);
        API.registerRecipeHandler(recipeHandler);
    }
}
