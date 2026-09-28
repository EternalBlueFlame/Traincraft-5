package train.common;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.client.core.handlers.RecipeBookHandler;
import train.client.render.RenderEnum;
import train.common.api.*;
import train.common.api.crafting.ITierRecipe;
import train.common.core.managers.TierRecipe;
import train.common.core.managers.TierRecipeManager;
import train.common.library.EnumSounds;
import train.common.library.EnumTrains;
import train.common.library.Info;
import train.common.library.ItemIDs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class trainConverter {


    public static TrainRecord getTrain(AbstractTrains train) {
        for (TrainRecord trn : EnumTrains.trains()) {
            if (trn.getEntityClass() == train.getClass()) {
                return trn;
            }
        }
        return null;
    }

    public static RenderEnum getRender(AbstractTrains train) {
        for (RenderEnum trn : RenderEnum.values()) {
            if (trn.getEntityClass() == train.getClass()) {
                return trn;
            }
        }
        return null;
    }

    public static TrainSoundRecord getSound(AbstractTrains train) {
        for (EnumSounds trn : EnumSounds.values()) {
            if (trn.getEntityClass() == train.getClass()) {
                return trn;
            }
        }
        return null;
    }

    public static void write(TrainRecord[] trains) {
        System.out.println("enumlength " + EnumTrains.trains().length);
        for (TrainRecord t : trains) {
            EntityRollingStock rollingStock = (EntityRollingStock) t.getEntity((World) null, 0, 0, 0);
            if (rollingStock != null) {
                print(rollingStock);
            }

        }

        StringBuilder reg = new StringBuilder();
        for(String k: registerList.keySet()){
            reg.append("public static AbstractTrains[] list")
                    .append(k)
                    .append("() {\n")
                    .append("        return new AbstractTrains[]{")
            .append(registerList.get(k))
            .append("};\n}\n");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Traincraft.configDirectory.getAbsolutePath());
        sb.append("/traincraft/");
        if (!new File(sb.toString()).exists()) {
            new File(sb.toString()).mkdir();
        }
        sb.append("RegistrationLists.java");


        FileOutputStream fileoutputstream = null;
        try {
            fileoutputstream = new FileOutputStream(new File(sb.toString()));
            fileoutputstream.write(reg.toString().getBytes());
            fileoutputstream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<TierRecipe> recipeList = null;
    //public static ItemStack[] recipe = null;
    public static int tier = 1;
    public static Map<String,StringBuilder> registerList = new HashMap<>();
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
                        "import fexcraft.tmt.slim.ModelBase;\n"+
                        "import ebf.tim.api.SkinRegistry;\n"+
                        "import net.minecraft.init.Items;\n"+
                        "import net.minecraft.item.Item;\n"+
                        "import net.minecraft.item.ItemStack;\n"+
                        "import net.minecraft.init.Blocks;\n"+
                        "import net.minecraft.world.World;\n"+
                        "import train.common.Traincraft;\n"+
                        "import train.common.api.*;\n"+
                        "import train.common.items.ItemRollingStock;\n"+
                        "import train.common.library.Info;\n"+
                        "import train.common.library.ItemIDs;\n"+
                        "\n");

        builder.append("public class ");
        String classname = trn.getClass().getName().replace("train.common.entity.rollingStockOld.", "");
        classname=classname.replace("diesel.","").replace("electric.","").replace("steam.","")
        .replace("caboose.","").replace("freight.","").replace("passenger.","").replace("special.","").replace("tender.","");
        builder.append(classname);
        String outfolder="";
        if (trn instanceof Locomotive) {
            if (trn instanceof ElectricTrain) {
                outfolder+="electric/";
                builder.append(" extends ElectricTrain {\n\n");
            }
            if (trn instanceof DieselTrain) {
                outfolder+="diesel/";
                builder.append(" extends DieselTrain {\n\n");
            }
            if (trn instanceof SteamTrain) {
                outfolder+="steam/";
                builder.append(" extends SteamTrain {\n\n");
            }
        }
        else if (trn instanceof IPassenger) {
            outfolder+="passenger/";
            builder.append(" extends EntityRollingStock implements IPassenger {\n\n");
        }
        else if (trn instanceof Tender){
            outfolder+="tender/";
            builder.append(" extends Tender {\n\n");
        }
        else if (trn instanceof Freight){
            outfolder+="freight/";
            builder.append(" extends Freight {\n\n");
        }
        else if (trn instanceof LiquidTank){
            outfolder+="tanker/";
            builder.append(" extends LiquidTank {\n\n");
        }
        else if (trn instanceof AbstractWorkCart){
            outfolder+="work/";
            builder.append(" extends AbstractWorkCart {\n\n");
        } else {
            outfolder+="special/";
            builder.append(" extends EntityRollingStock {\n\n");
        }

        if(registerList.containsKey(outfolder.replace("/",""))){
            registerList.get(outfolder.replace("/","")).append(", new "+classname+"(null)");
        } else {
            registerList.put(outfolder.replace("/",""), new StringBuilder("new "+classname+"(null)"));
        }


        /**Item*/
        // builder.append("    public static final Item thisItem = new ItemRollingStock(this, new ");
        String itemName = trn.getItem().getUnlocalizedName().replace("item.tc:", "");
        for (ItemIDs items : ItemIDs.values()) {
            if (items.className.equals("ItemRollingStock")) {
                if (itemName.equals(items.name().toString())) {
                    itemName = items.iconName;
                }
            }
        }

        builder.append("    public static final Item thisItem = new ItemRollingStock(new "+classname+"(null), Info.modID+\":+" + itemName + "\", Traincraft.tcTab); \n");


        builder.append("    public ");
        builder.append(classname);
        builder.append("(World world, double x, double y, double z) {\n");
        builder.append("    super(world, x, y, z); }\n");

        builder.append("    public ");
        builder.append(classname);
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

        if(colours.size()>0) {
            for (int color = 0; color < colours.size(); color++) {
                transportSkin = getRender(trn).getTextureFile(colours.get(color)).toString();
                builder.append("        SkinRegistry.addSkin(this.getClass(), Info.modID,\"" + transportSkin.replace("tc:", "") + "\" , new String[]{} ,\"" + colours.get(color) + "\", \"\");\n");

            }
        } else {
            builder.append("        SkinRegistry.addSkin(this.getClass(), Info.modID,\"" + getRender(trn).getTextureFile("").toString().replace("tc:", "") + "\" , new String[]{} ,\"default\", \"\");\n");
        }
        builder.append("    }\n\n");
        builder.append("    @Override\n");
        builder.append("    public String getDefaultSkin(){return \"");
        if(colours.size()>0){
            builder.append(colours.get(0));
        } else {
            builder.append("default");
        }
        builder.append("\";}\n\n");

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
        builder.append("	return 0.65f;");
        builder.append("}\n\n");

        builder.append("	@Override\n");
        builder.append("	public float transportMetricHorsePower(){return ");
        builder.append(getTrain(trn).getMHP());
        builder.append(";}\n\n");

        if(getTrain(trn).getAdditionnalTooltip()!=null) {
            builder.append("	@Override\n");
            builder.append("	public String[] additionalItemText() { return new String[] {\"");
            builder.append(getTrain(trn).getAdditionnalTooltip());
            builder.append("\"};}\n\n");
        }

        builder.append("	@Override\n");
        builder.append("	public float weightKg(){ return ");
        builder.append(getTrain(trn).getMass()*2000);
        builder.append("f;}\n\n");

        builder.append("	@Override\n");
        builder.append("	public float[] rotationPoints(){ return new float[]{");
        builder.append(getTrain(trn).getEntity(null).rotationPoints()[0]);
        builder.append("f, ");
        builder.append(getTrain(trn).getEntity(null).rotationPoints()[1]);
        builder.append("f};}\n\n");

        builder.append("    @Override\n");
        builder.append("    public ItemStack[] getRecipe() {\n");


        recipeList = RecipeBookHandler.assemblyListCleaner(TierRecipeManager.getInstance().getRecipeList());

        //ITierRecipe r;
        boolean found = false;
        for (ITierRecipe recipe : recipeList){
            if(recipe.getOutput().getItem()==trn.getCartItem().getItem()){
                builder.append("        return new ItemStack[]{\n");
                tier = recipe.getTier();

                builder.append("                ");

                if(getItem(recipe.getInput().get(0))==null){
                    builder.append("null, \n");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(0)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(0).getCount());
                    builder.append("), \n");
                }

                if(getItem(recipe.getInput().get(1))==null){
                    builder.append("null,\n ");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(1)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(1).getCount());
                    builder.append("), \n");
                }

                if(getItem(recipe.getInput().get(2))==null){
                    builder.append("null, \n");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(2)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(2).getCount());
                    builder.append("), \n");
                }

                if(getItem(recipe.getInput().get(3))==null){
                    builder.append("null, \n");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(3)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(3).getCount());
                    builder.append("), \n");
                }

                if(getItem(recipe.getInput().get(4))==null){
                    builder.append("null,\n ");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(4)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(4).getCount());
                    builder.append("), \n");
                }

                if(getItem(recipe.getInput().get(5))==null){
                    builder.append("null, \n");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(5)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(5).getCount());
                    builder.append("), \n");
                }

                if(getItem(recipe.getInput().get(6))==null){
                    builder.append("null, \n");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(6)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(6).getCount());
                    builder.append("), \n");
                }

                if(getItem(recipe.getInput().get(7))==null){
                    builder.append("null, \n");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(7)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(7).getCount());
                    builder.append("), \n");
                }

                if(getItem(recipe.getInput().get(8))==null){
                    builder.append("null,\n");
                } else {
                    builder.append("new ItemStack(");
                    builder.append(getItem(recipe.getInput().get(8)));
                    builder.append(", ");
                    builder.append(recipe.getInput().get(8).getCount());
                    builder.append("),\n");
                }

                builder.append("new ItemStack(");
                builder.append("thisItem)");
                builder.append("\n        };\n");


                found=true;
                break;
            }
        }
        if(!found) {
            builder.append(" return null;\n");
        }
        builder.append("    }\n\n\n");
        builder.append("    @Override\n    public Item getItem(){return thisItem;}\n");

        builder.append("    @Override\n");
        builder.append("    public int getTier(){\n");
        builder.append("return ");
        builder.append(tier);
        builder.append(";\n");
        builder.append("}");

        builder.append("\n    //Model stuff\n");
        builder.append("    @Override\n");
        builder.append("    public ModelBase[] getModel(){return new ModelBase[]{new ");
        builder.append(getRender(trn).getModel().getClass().getName());
        builder.append("()};}\n");

        if(getRender(trn).getTrans()!=null) {
            builder.append("    @Override\n");
            builder.append("    public float[][] modelOffsets(){return new float[][]{{");
            builder.append(getRender(trn).getTrans()[0]);
            builder.append("f, ");
            builder.append(-getRender(trn).getTrans()[1]-0.5f);
            builder.append("f, ");
            builder.append(getRender(trn).getTrans()[2]);
            builder.append("f}};}\n");
        }

        if(getRender(trn).getRotate()!=null) {
            builder.append("    @Override\n");
            builder.append("    public float[][] modelRotations(){return new float[][]{{");
            builder.append(getRender(trn).getRotate()[0]);
            builder.append("f, ");
            builder.append(getRender(trn).getRotate()[1]);
            builder.append("f, ");
            builder.append(getRender(trn).getRotate()[2]);
            builder.append("f}};}\n");
        } else {
            builder.append("@Override\n");
            builder.append("    public float[][] modelRotations(){return new float[][]{{0f,180f,180f}};}\n");
        }

        if(getRender(trn).getScale()!=null){
            builder.append("    @Override\n");
            builder.append("    public float[][] getRenderScale(){return new float[][]{{");
            builder.append(getRender(trn).getScale()[0]);
            builder.append("f, ");
            builder.append(getRender(trn).getScale()[1]);
            builder.append("f, ");
            builder.append(getRender(trn).getScale()[2]);
            builder.append("f}};}\n");
        } else {
            builder.append("    @Override\n");
            builder.append("    public float[][] getRenderScale(){return new float[][]{null};}\n");
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


        builder.append("    public TrainParticle[] getEffects(){\n    return new TrainParticle[]{\n");
        if(getRender(trn).getSmokeFX()!=null) {
            for (double[] p : getRender(trn).getSmokeFX()) {
                builder.append("            new TrainParticle(\"");
                builder.append(getRender(trn).getSmokeType());
                builder.append("\", ");
                builder.append(getRender(trn).getSmokeIterations());
                builder.append(", new double[]{");
                builder.append(p[0]);
                builder.append(", ");
                builder.append(p[1]);
                builder.append(", ");
                builder.append(p[2]);
                builder.append("}),\n");
            }
        }
        if(getRender(trn).getExplosionFX()!=null) {
            for (double[] p : getRender(trn).getExplosionFX()) {
                builder.append("            new TrainParticle(\"");
                builder.append(getRender(trn).getExplosionType());
                builder.append("\", ");
                builder.append(getRender(trn).getExplosionFXIterations());
                builder.append(", new double[]{");
                builder.append(p[0]);
                builder.append(", ");
                builder.append(p[1]);
                builder.append(", ");
                builder.append(p[2]);
                builder.append("}),\n");
            }
        }
        builder.append("    };\n    }\n\n");


        if(trn instanceof Locomotive) {
            builder.append("    //Train specific stuff\n");
            builder.append("    @Override\n");
            builder.append("    public String transportFuelType(){return \"");
            builder.append(getTrain(trn).getTrainType());
            builder.append("\";}\n");

            builder.append("    @Override\n");
            builder.append("    public int getFuelConsumption(){return ");
            builder.append(getTrain(trn).getFuelConsumption());
            builder.append(";}\n\n");
            builder.append("    @Override\n");
            builder.append("    public int getWaterConsumption(){return ");
            builder.append(getTrain(trn).getWaterConsumption());
            builder.append(";}\n\n");


            builder.append("    @Override\n");
            builder.append("    public double getSpecAccel(){return ");
            builder.append(getTrain(trn).getAccelerationRate());
            builder.append(";}\n\n");
            builder.append("    @Override\n");
            builder.append("    public double getSpecBrake(){return ");
            builder.append(getTrain(trn).getBrakeRate());
            builder.append(";}\n\n");

            if(getSound(trn) !=null) {
                if(!getSound(trn).getHornString().isEmpty()) {
                    builder.append("    @Override\n");
                    builder.append("    public TrainSound getHorn(){return new TrainSound(\"");
                    builder.append(getSound(trn).getHornString());
                    builder.append("\", ");
                    builder.append(getSound(trn).getHornVolume());
                    builder.append("f,1f, 0);}\n\n");
                }

                builder.append("    @Override\n");
                builder.append("    public TrainSound getBell(){return new TrainSound(");
                builder.append("Info.resourceLocation + \":bell\",0.5f,1f, 0);}\n\n");

                if(!getSound(trn).getRunString().isEmpty()) {
                    builder.append("    @Override\n");
                    builder.append("    public TrainSound getRunningSound(){return new TrainSound(\"");
                    builder.append(getSound(trn).getRunString());
                    builder.append("\", ");
                    builder.append(getSound(trn).getRunVolume());
                    builder.append("f,0.4f, ");
                    builder.append(getSound(trn).getRunSoundLength());
                    builder.append(");}\n\n");
                }

                if(!getSound(trn).getIdleString().isEmpty()) {
                    builder.append("    @Override\n");
                    builder.append("    public TrainSound getIdleSound(){return new TrainSound(\"");
                    builder.append(getSound(trn).getIdleString());
                    builder.append("\", ");
                    builder.append(getSound(trn).getIdleVolume());
                    builder.append("f,0.001f, ");
                    builder.append(getSound(trn).getIdleSoundLength());
                    builder.append(");}\n\n");
                }
            }

            if (trn instanceof SteamTrain) {
                builder.append("    @Override\n");
                builder.append("    public int getOverheatTime(){return ");
                builder.append(getTrain(trn).getHeatingTime());
                builder.append(";}\n\n");
            }

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
                builder.append("    @Override\n");
                builder.append("    public int[] getTankCapacity(){return new int[]{2250};}\n");
            }
        } else {
            if (getTrain(trn).getTankCapacity()>0) {
                builder.append("    @Override\n");
                builder.append("    public int[] getTankCapacity(){return new int[]{");
                builder.append(getTrain(trn).getTankCapacity());
                builder.append("};}");
            }
        }

        builder.append("\n}\n\n\n    //these only change in very specific use cases.\n");

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

            sb.append(outfolder);
            if (!new File(sb.toString()).exists()) {
                new File(sb.toString()).mkdir();
            }

            sb.append(classname);
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
                    i.getItem().delegate.name().getPath();

        } else {
            if(i.getItem().delegate.name().split(":")[1].equals("ingot")){
                return "Items.IRON_INGOT";
            }

            if(i.getItem().delegate.name().split(":")[1].equals("potion")){
                return "Items.potionitem";
            }
            return "Items." +
                    i.getItem().delegate.name().getPath();
        }

    }
}
