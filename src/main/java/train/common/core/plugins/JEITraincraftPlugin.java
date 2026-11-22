package train.common.core.plugins;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import train.common.blocks.TCBlocks;
import train.common.core.handlers.ConfigHandler;
import train.common.core.managers.TierRecipe;
import train.common.core.managers.TierRecipeManager;
import train.common.inventory.TrainCraftingManager;
import train.common.library.Info;
import train.common.recipes.DistilRecipes;
import train.common.recipes.OpenHearthFurnaceRecipe;
import train.common.recipes.ShapedTrainRecipes;
import train.common.recipes.ShapelessTrainRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@JEIPlugin
public class JEITraincraftPlugin implements IModPlugin {

    public static final String WORKBENCH = Info.modID + ":train_workbench";
    public static final String ASSEMBLY = Info.modID + ":assembly_table";
    public static final String OPEN_HEarth = Info.modID + ":open_hearth_furnace";
    public static final String DISTILLATION = Info.modID + ":distillation_tower";

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IJeiHelpers helpers = registry.getJeiHelpers();
        registry.addRecipeCategories(new WorkbenchCategory(helpers));
        registry.addRecipeCategories(new AssemblyCategory(helpers));
        registry.addRecipeCategories(new OpenHearthCategory(helpers));
        registry.addRecipeCategories(new DistillationCategory(helpers));
    }

    @Override
    public void register(mezz.jei.api.IModRegistry registry) {
        if (ConfigHandler.DISABLE_JEI_RECIPES) {
            return;
        }

        registry.addRecipeCatalyst(new ItemStack(TCBlocks.trainWorkbench), WORKBENCH);
        registry.addRecipeCatalyst(new ItemStack(TCBlocks.assemblyTableI), ASSEMBLY);
        registry.addRecipeCatalyst(new ItemStack(TCBlocks.assemblyTableII), ASSEMBLY);
        registry.addRecipeCatalyst(new ItemStack(TCBlocks.assemblyTableIII), ASSEMBLY);
        registry.addRecipeCatalyst(new ItemStack(TCBlocks.openFurnaceIdle), OPEN_HEarth);
        registry.addRecipeCatalyst(new ItemStack(TCBlocks.distilIdle), DISTILLATION);

        List<WorkbenchWrapper> workbench = new ArrayList<>();
        for (Object recipe : TrainCraftingManager.getInstance().getRecipeList()) {
            if (recipe instanceof ShapedTrainRecipes) {
                workbench.add(new WorkbenchWrapper((ShapedTrainRecipes) recipe));
            } else if (recipe instanceof ShapelessTrainRecipe) {
                workbench.add(new WorkbenchWrapper((ShapelessTrainRecipe) recipe));
            }
        }
        registry.addRecipes(workbench, WORKBENCH);

        List<AssemblyWrapper> assembly = new ArrayList<>();
        for (Object recipe : TierRecipeManager.getInstance().getRecipeList()) {
            if (recipe instanceof TierRecipe) {
                assembly.add(new AssemblyWrapper((TierRecipe) recipe));
            }
        }
        registry.addRecipes(assembly, ASSEMBLY);

        List<OpenHearthWrapper> hearth = new ArrayList<>();
        for (OpenHearthFurnaceRecipe recipe : TrainCraftingManager.getInstance().getHearthFurnaceRecipeList()) {
            hearth.add(new OpenHearthWrapper(recipe));
        }
        registry.addRecipes(hearth, OPEN_HEarth);

        List<DistillationWrapper> distillation = new ArrayList<>();
        for (Map.Entry<?, ?> entry : DistilRecipes.smelting().getSmeltingList().entrySet()) {
            net.minecraft.item.Item item = (net.minecraft.item.Item) entry.getKey();
            ItemStack result = (ItemStack) entry.getValue();
            distillation.add(new DistillationWrapper(new ItemStack(item, 1), result,
                    DistilRecipes.smelting().getPlasticResult(item)));
        }
        registry.addRecipes(distillation, DISTILLATION);
    }

    private static abstract class BaseCategory<T extends IRecipeWrapper> implements IRecipeCategory<T> {
        protected final IDrawable background;

        protected BaseCategory(IJeiHelpers helpers, int width, int height) {
            background = helpers.getGuiHelper().createBlankDrawable(width, height);
        }

        @Override
        public IDrawable getBackground() {
            return background;
        }

        @Override
        public String getModName() {
            return Info.modName;
        }
    }

    public static class WorkbenchCategory extends BaseCategory<WorkbenchWrapper> {
        public WorkbenchCategory(IJeiHelpers helpers) { super(helpers, 160, 70); }
        @Override
        public String getUid() { return WORKBENCH; }
        @Override
        public String getTitle() { return "Train Workbench"; }
        @Override
        public void setRecipe(IRecipeLayout layout, WorkbenchWrapper wrapper, IIngredients ingredients) {
            IGuiItemStackGroup slots = layout.getItemStacks();
            for (int i = 0; i < 9; i++) {
                slots.init(i, true, 25 + (i % 3) * 18, 6 + (i / 3) * 18);
            }
            slots.init(9, false, 119, 24);
            slots.set(ingredients);
        }
    }

    public static class AssemblyCategory extends BaseCategory<AssemblyWrapper> {
        public AssemblyCategory(IJeiHelpers helpers) { super(helpers, 172, 133); }
        @Override
        public String getUid() { return ASSEMBLY; }
        @Override
        public String getTitle() { return "Assembly Table"; }
        @Override
        public void setRecipe(IRecipeLayout layout, AssemblyWrapper wrapper, IIngredients ingredients) {
            IGuiItemStackGroup slots = layout.getItemStacks();
            int[] x = {20,38,74,140,74,110,74,110,20,140};
            int[] y = {16,82,82,82,16,16,50,50,50,16};
            for (int i = 0; i < 10; i++) {
                slots.init(i, true, x[i], y[i]);
            }
            slots.init(10, false, 87, 118);
            slots.set(ingredients);
        }
    }

    public static class OpenHearthCategory extends BaseCategory<OpenHearthWrapper> {
        public OpenHearthCategory(IJeiHelpers helpers) { super(helpers, 144, 54); }
        @Override
        public String getUid() { return OPEN_HEarth; }
        @Override
        public String getTitle() { return "Open Hearth Furnace"; }
        @Override
        public void setRecipe(IRecipeLayout layout, OpenHearthWrapper wrapper, IIngredients ingredients) {
            IGuiItemStackGroup slots = layout.getItemStacks();
            slots.init(0, true, 30, 6);
            slots.init(1, true, 51, 6);
            slots.init(2, false, 111, 24);
            slots.set(ingredients);
        }
    }

    public static class DistillationCategory extends BaseCategory<DistillationWrapper> {
        public DistillationCategory(IJeiHelpers helpers) { super(helpers, 144, 70); }
        @Override
        public String getUid() { return DISTILLATION; }
        @Override
        public String getTitle() { return "Distillation Tower"; }
        @Override
        public void setRecipe(IRecipeLayout layout, DistillationWrapper wrapper, IIngredients ingredients) {
            IGuiItemStackGroup slots = layout.getItemStacks();
            slots.init(0, true, 51, 13);
            slots.init(1, false, 111, 29);
            slots.init(2, false, 111, 56);
            slots.set(ingredients);
        }
    }

    public static class WorkbenchWrapper implements IRecipeWrapper {
        private final ItemStack[] inputs;
        private final ItemStack output;

        public WorkbenchWrapper(ShapedTrainRecipes recipe) {
            inputs = recipe.recipeItems;
            output = recipe.getRecipeOutput();
        }

        public WorkbenchWrapper(ShapelessTrainRecipe recipe) {
            inputs = new ItemStack[recipe.recipeItems.size()];
            for (int i = 0; i < recipe.recipeItems.size(); i++) {
                inputs[i] = (ItemStack) recipe.recipeItems.get(i);
            }
            output = recipe.getRecipeOutput();
        }

        @Override
        public void getIngredients(IIngredients ingredients) {
            List<List<ItemStack>> inputLists = new ArrayList<>();
            for (ItemStack input : inputs) {
                inputLists.add(input == null ? Collections.<ItemStack>emptyList()
                        : Collections.singletonList(input.copy()));
            }
            while (inputLists.size() < 9) {
                inputLists.add(Collections.<ItemStack>emptyList());
            }
            ingredients.setInputLists(VanillaTypes.ITEM, inputLists);
            ingredients.setOutput(VanillaTypes.ITEM, output.copy());
        }
    }

    public static class AssemblyWrapper implements IRecipeWrapper {
        private final TierRecipe recipe;
        public AssemblyWrapper(TierRecipe recipe) { this.recipe = recipe; }

        @Override
        public void getIngredients(IIngredients ingredients) {
            List<List<ItemStack>> inputs = new ArrayList<>();
            for (ItemStack input : recipe.getInput()) {
                inputs.add(input == null ? Collections.<ItemStack>emptyList()
                        : Collections.singletonList(input.copy()));
            }
            ingredients.setInputLists(VanillaTypes.ITEM, inputs);
            ingredients.setOutput(VanillaTypes.ITEM, recipe.getOutput().copy());
        }
    }

    public static class OpenHearthWrapper implements IRecipeWrapper {
        private final OpenHearthFurnaceRecipe recipe;
        public OpenHearthWrapper(OpenHearthFurnaceRecipe recipe) { this.recipe = recipe; }

        @Override
        public void getIngredients(IIngredients ingredients) {
            ItemStack[] inputs = recipe.getRecipe();
            ingredients.setInputs(VanillaTypes.ITEM,
                    inputs[0] == null || inputs[1] == null
                            ? Collections.singletonList(inputs[0] == null ? inputs[1] : inputs[0])
                            : java.util.Arrays.asList(inputs[0].copy(), inputs[1].copy()));
            ingredients.setOutput(VanillaTypes.ITEM, recipe.getCraftingResult().copy());
        }
    }

    public static class DistillationWrapper implements IRecipeWrapper {
        private final ItemStack input;
        private final ItemStack output;
        private final ItemStack plastic;

        public DistillationWrapper(ItemStack input, ItemStack output, ItemStack plastic) {
            this.input = input;
            this.output = output;
            this.plastic = plastic;
        }

        @Override
        public void getIngredients(IIngredients ingredients) {
            ingredients.setInput(VanillaTypes.ITEM, input.copy());
            List<ItemStack> outputs = new ArrayList<>();
            if (output != null) outputs.add(output.copy());
            if (plastic != null) outputs.add(plastic.copy());
            ingredients.setOutputs(VanillaTypes.ITEM, outputs);
        }
    }
}
