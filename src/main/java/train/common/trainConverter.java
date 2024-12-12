package train.common;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.client.core.handlers.RecipeBookHandler;
import train.client.render.RenderEnum;
import train.common.api.*;
import train.common.api.crafting.ITierCraftingManager;
import train.common.api.crafting.ITierRecipe;
import train.common.core.managers.TierRecipe;
import train.common.core.managers.TierRecipeManager;
import train.common.core.util.TraincraftUtil;
import train.common.entity.rollingStock.EntityPassengerPassengerCar1;
import train.common.inventory.TrainCraftingManager;
import train.common.items.ItemRollingStock;
import train.common.items.TCItems;
import train.common.library.EnumTrains;
import train.common.library.Info;
import train.common.library.ItemIDs;
import train.common.recipes.AssemblyTableRecipes;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class trainConverter {


    public static TrainRecord getTrain(AbstractTrains train) {
        for (TrainRecord trn : EnumTrains.trains()) {
            if (trn.getEntityClass() == train.getClass()) {
                return trn;
            }
        }
        return null;
    }

    public static RenderEnum getRender(EntityRollingStock train) {
        for (RenderEnum trn : RenderEnum.values()) {
            if (trn.getEntityClass() == train.getClass()) {
                return trn;
            }
        }
        return null;
    }

    public static void write() {
        System.out.println("enumlength " + EnumTrains.trains().length);
        for (TrainRecord t : EnumTrains.trains()) {
            EntityRollingStock rollingStock = null;

            rollingStock = (EntityRollingStock) t.getEntity((World) null, 0, 0, 0);
            //if(t.getColors()!=null && !t.getColors().isEmpty()){
            //    if(rollingStock != null){
            //        rollingStock.setColor((t.getColors().get(0)));
            //    }
            // }
            if (rollingStock != null) {
                print(rollingStock);
            }

        }
    }

    public static List<TierRecipe> recipeList = null;
    //public static ItemStack[] recipe = null;
    public static int tier = 1;

    public static void print(EntityRollingStock trn) {
        StringBuilder builder;
        builder = new StringBuilder();

        if (trn instanceof Locomotive) {
            builder.append("package train.entity.trains;\n");
        } else {
            builder.append("package train.entity.rollingStock;\n");
        }

        builder.append(
                "\n" +
                        "import ebf.tim.TrainsInMotion;\n" +
                        "import ebf.tim.api.SkinRegistry;\n" +
                        "import ebf.tim.api.TransportSkin;\n" +
                        "import ebf.tim.entities.EntityTrainCore;\n" +
                        "import ebf.tim.entities.GenericRailTransport;\n" +
                        "import ebf.tim.items.ItemTransport;\n" +
                        "import ebf.tim.utility.ItemStackSlot;\n" +
                        "import fexcraft.tmt.slim.ModelBase;\n" +
                        "import net.minecraft.init.Items;\n" +
                        "import net.minecraft.item.Item;\n" +
                        "import net.minecraft.item.ItemStack;\n" +
                        "import net.minecraft.init.Blocks;\n" +
                        "import net.minecraft.world.World;\n" +
                        "import train.render.models.*;\n" +
                        "import train.Traincraft;\n" +
                        "import train.library.Info;\n" +
                        "import train.library.ItemIDs;\n" +
                        "\n" +
                        "import java.util.List;\n" +
                        "import java.util.UUID;\n" +
                        "\n");

        builder.append("public class ");
        builder.append(trn.getClass().getName().replace("train.common.entity.rollingStockOld.", ""));
        if (trn instanceof Locomotive) {
            if (trn instanceof ElectricTrain) {
                builder.append(" extends ElectricTrain {\n\n");
            }
            if (trn instanceof DieselTrain) {
                builder.append(" extends DieselTrain {\n\n");
            }
            if (trn instanceof SteamTrain) {
                builder.append(" extends SteamTrain {\n\n");
            }
        }
        else if (trn instanceof IPassenger) {
            builder.append(" extends EntityRollingStock implements IPassenger {\n\n");
        }
        else if (trn instanceof Freight){
            builder.append(" extends Freight {\n\n");
        }
        else if (trn instanceof Tender){
            builder.append(" extends Tender {\n\n");
        }
        else if (trn instanceof LiquidTank){
            builder.append(" extends LiquidTank {\n\n");
        }
        else if (trn instanceof AbstractWorkCart){
            builder.append(" extends AbstractWorkCart {\n\n");
        }



        /**Item*/
        // builder.append("    public static final Item thisItem = new ItemRollingStock(new ");
        String iconName = "";
        String itemName = trn.getItem().getUnlocalizedName().replace("item.tc:", "");
        for (ItemIDs items : ItemIDs.values()) {
            if (items.className.equals("ItemRollingStock")) {
                if (itemName.equals(items.name().toString())) {
                    iconName = items.iconName;
                }
            }
        }

        builder.append("    public static final Item thisItem = new ItemRollingStock(" + itemName + ", " + iconName + "," + Info.modID + "); \n");


        builder.append("    public ");
        builder.append(trn.getClass().getName().replace("train.common.entity.rollingStockOld.", ""));
        builder.append("(World world, double x, double y, double z,) {\n");
        builder.append("    super(world, x, y, z); }\n");

        builder.append("    public ");
        builder.append(trn.getClass().getName().replace("train.common.entity.rollingStockOld.", ""));
        builder.append("(World world) {\n");
        builder.append("    super (world); } \n");


        /**Stats*/
        builder.append("    //main stats\n");

        builder.append("    @Override\n");
        builder.append("    public String transportName(){return \"");
        builder.append(getTrain(trn).getInternalName());
        builder.append("\";}\n");

        builder.append("    @Override\n");
        builder.append("    public String transportcountry(){return \"");
        builder.append(getTrain(trn).getEntity(null).transportcountry());
        builder.append("\";}\n");

        builder.append("    @Override\n");
        builder.append("    public String transportYear(){return \"");
        builder.append(getTrain(trn).getEntity(null).transportYear());
        builder.append("\";}\n");

        builder.append("    @Override\n");
        builder.append("    public boolean isFictional(){return ");
        builder.append(getTrain(trn).getEntity(null).isFictional());
        builder.append(";}\n\n");

        builder.append("    @Override\n");
        builder.append("    public void registerSkins(){\n");

        String transportSkin;
        List<String> colours = getTrain(trn).getColors();

        for (int color = 0; color < colours.size(); color++) {
            transportSkin = getRender(trn).getTextureFile(colours.get(color)).toString();
            builder.append("        SkinRegistry.addSkin(this.getClass(), Info.modid,\"" + transportSkin.replace("tc:", "") + "\" , new String[]{} ,\"" + colours.get(color) + "\", \"\");\n");

        }
        builder.append("    }\n\n");

        builder.append("    @Override\n");
        builder.append("    public float transportTopSpeed(){return ");
        builder.append(getTrain(trn).getEntity(null).getSpec().getMaxSpeed());
        builder.append(";}\n\n");

        builder.append("    @Override\n");
        builder.append("    public int getInventoryRows(){return ");
        builder.append(getTrain(trn).getEntity(null).getSpec().getCargoCapacity() / 9);
        builder.append(";}\n\n");

        builder.append("	@Override\n");
        builder.append("	public float getPlayerScale(){ ");
        builder.append("	return 0.45f;");
        builder.append("}\n\n");

        builder.append("	@Override\n");
        builder.append("	public float transportMetricHorsePower(){return ");
        builder.append(getTrain(trn).getMHP());
        builder.append(";}\n\n");

        builder.append("	@Override\n");
        builder.append("	public String[] additionalItemText() { return new String[] {\"");
        builder.append(getTrain(trn).getAdditionnalTooltip());
        builder.append("\"}}\n\n");

        builder.append("	@Override\n");
        builder.append("	public float weightKg(){ return ");
        builder.append(getTrain(trn).getMass());
        builder.append(";}\n\n");

        builder.append("    @Override\n");
        builder.append("    public ItemStack[] getRecipie() {\n");
        builder.append("        return new ItemStack[]{\n");


        recipeList = RecipeBookHandler.assemblyListCleaner(TierRecipeManager.getInstance().getRecipeList());

        //ITierRecipe r;
        boolean found = false;
        for (ITierRecipe recipe : recipeList){
            if(recipe.getOutput().getItem()==trn.getCartItem().getItem()){
                tier = recipe.getTier();

                builder.append("                ");

                if(getItem(recipe.getInput().get(0))==null){
                    builder.append("null, \n");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(0)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(0).stackSize);
                    builder.append("), \n");
                }

                if(getItem(recipe.getInput().get(1))==null){
                    builder.append("null,\n ");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(1)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(1).stackSize);
                    builder.append("), \n");
                }

                if(getItem(recipe.getInput().get(2))==null){
                    builder.append("null, \n");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(2)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(2).stackSize);
                    builder.append("), \n");
                }

                if(getItem(recipe.getInput().get(3))==null){
                    builder.append("null, \n");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(3)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(3).stackSize);
                    builder.append("), \n");
                }

                if(getItem(recipe.getInput().get(4))==null){
                    builder.append("null,\n ");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(4)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(4).stackSize);
                    builder.append("), \n");
                }

                if(getItem(recipe.getInput().get(5))==null){
                    builder.append("null, \n");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(5)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(5).stackSize);
                    builder.append("), \n");
                }

                if(getItem(recipe.getInput().get(6))==null){
                    builder.append("null, \n");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(6)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(6).stackSize);
                    builder.append("), \n");
                }

                if(getItem(recipe.getInput().get(7))==null){
                    builder.append("null, \n");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(7)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(7).stackSize);
                    builder.append("), \n");
                }

                if(getItem(recipe.getInput().get(8))==null){
                    builder.append("null\n");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(8)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(8).stackSize);
                    builder.append(")\n");
                }

                builder.append("new ItemStack(");
                builder.append("thisItem)");


                found=true;
                break;
            }
        }
        if(!found) {
            builder.append("                new ItemStack(), new ItemStack(), new ItemStack(), \n");
            builder.append("                new ItemStack(), new ItemStack(), new ItemStack(), \n");
            builder.append("                new ItemStack(), new ItemStack(), new ItemStack() \n");
        }
        builder.append("        };\n");
        builder.append("    }\n\n\n");

        builder.append("@Override\n");
        builder.append("public int getTier(){\n");
        builder.append("return ");
        builder.append(tier);
        builder.append(";\n");
        builder.append("}");

        builder.append("\n    //Model stuff\n");
        builder.append("    @Override\n");
        builder.append("    public ModelBase[] getModel(){return new ModelBase[]{new ");
        builder.append(getRender(trn).getModel().getClass().getName().replace(".client",""));
        builder.append("()};}\n");

        if(getRender(trn).getTrans()!=null) {
            builder.append("    @Override\n");
            builder.append("    public float[][] modelOffsets(){return new float[][]{{");
            builder.append(getRender(trn).getTrans()[0]);
            builder.append("f, ");
            builder.append(-getRender(trn).getTrans()[1]);
            builder.append("f, ");
            builder.append(getRender(trn).getTrans()[2]);
            builder.append("f}};}\n");
        }

        if(getRender(trn).getRotate()!=null) {
            builder.append("    @Override\n");
            builder.append("    public float[][] modelRotations(){return new float[][]{{");
            builder.append(getRender(trn).getRotate()[0]);
            builder.append("f, ");
            builder.append(getRender(trn).getRotate()[1]-180);
            builder.append("f, ");
            builder.append(getRender(trn).getRotate()[2]-180);
            builder.append("f}};}\n");
        } else {
            builder.append("@Override\n");
            builder.append("    public float[][] modelRotations(){return new float[][]{{0f,180f,180f}};}\n");
        }

        builder.append("    //these are separated for being fiddly.\n");
        builder.append("    @Override\n");
        builder.append("    public float[][] getRiderOffsets(){return new float[][]{{");
        builder.append(0);
        builder.append(",1.2f, 0f}};}\n");
        builder.append("    @Override\n");
        builder.append("    public float[] getHitboxSize(){return new float[]{");
        builder.append(-getTrain(trn).getBogieLocoPosition()+(trn.getOptimalDistance(null)*2f));
        builder.append("f,2.1f,1.1f};}\n");


       /*
        builder.append("    @Override\n");
        builder.append("    public float[] bogieLengthFromCenter() {return new float[]{");
        builder.append((-getTrain(trn).getBogieLocoPosition()*0.5f)+(trn.getOptimalDistance(null)*0.8f));
        builder.append("f, ");
        builder.append(-((-getTrain(trn).getBogieLocoPosition()*0.5f)+(trn.getOptimalDistance(null)*0.8f)));
        builder.append("f};}\n");
*/

        if(trn instanceof Locomotive) {
            builder.append("    //Train specific stuff\n");
            builder.append("    @Override\n");
            builder.append("    public String transportFuelType(){return \"");
            builder.append(getTrain(trn).getTrainType());
            builder.append("\";}\n");

            builder.append("    @Override\n");
            builder.append("    public ItemStackSlot fuelSlot(){\n");
            builder.append("        return super.fuelSlot().setOverlay(Items.coal);\n");
            builder.append("    }\n");

            if(getTrain(trn).getTankCapacity()>0){
                builder.append("    @Override\n");
                builder.append("    public int[] getTankCapacity(){return new int[]{");
                builder.append((int)getTrain(trn).getTankCapacity());
                if(getTrain(trn).getTrainType().equals("steam")) {
                    builder.append(", ");
                    builder.append(Math.max(500, (int)(getTrain(trn).getTankCapacity() * 0.2)));
                }
                builder.append("};}\n");
            } else {
                builder.append("    public int[] getTankCapacity(){return new int[]{2250};}\n");
            }
        } else {
            if (getTrain(trn).getTankCapacity()>0) {
                builder.append("    @Override\n");
                builder.append("    public int[] getTankCapacity(){return new int[]{");
                builder.append(getTrain(trn).getTankCapacity());
                builder.append("};}\n");
            }
        }

        builder.append("\n\n\n    //these only change in very specific use cases.\n");

        System.out.print("Attempting to write files for train classes");

        try {

            StringBuilder sb = new StringBuilder();
            sb.append(Traincraft.configDirectory.getAbsolutePath());
            sb.append("/traincraft/");
            if (!new File(sb.toString()).exists()) {
                new File(sb.toString()).mkdir();
            }
            sb.append("/classes/");
            if (!new File(sb.toString()).exists()) {
                new File(sb.toString()).mkdir();
            }

            if (trn instanceof Locomotive) {
                sb.append("/trains/");
                if (!new File(sb.toString()).exists()) {
                    new File(sb.toString()).mkdir();
                }
            } else {
                sb.append("/stock/");
                if (!new File(sb.toString()).exists()) {
                    new File(sb.toString()).mkdir();
                }
            }
            sb.append(trn.getClass().getName().replace("train.common.entity.rollingStockOld.", ""));
            sb.append(".java");


            FileOutputStream fileoutputstream = new FileOutputStream(new File(sb.toString()));
            fileoutputstream.write(builder.toString().getBytes());
            fileoutputstream.close();
        } catch (Exception e) {

        }
    }


    public static String getItem(ItemStack i) {
        if (i == null) {
            return null;
        }
        for (ItemIDs id : ItemIDs.values()) {
            if (i.getItem() == id.item) {
                return "ItemIDs." + id.name() + ".item";
            }
        }

        if (i.getItem() instanceof ItemBlock) {
            return "Blocks." +
                    i.getItem().delegate.name().split(":")[1];

        } else {
            return "Items." +
                    i.getItem().delegate.name().split(":")[1];
        }

    }
}
