package fexcraft.tmt.slim;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_HEIGHT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WIDTH;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glGetTexLevelParameteri;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import ebf.tim.utility.ClientUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import ebf.tim.TrainsInMotion;
import ebf.tim.utility.ClientProxy;
import ebf.tim.utility.DebugUtil;
import ebf.tim.utility.RecipeManager;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class TextureManager {


    //public static ByteBuffer renderPixels = ByteBuffer.allocateDirect((4096*4096)*4);
    private static float skyLight;
    private static Set<?> MCResourcePacks;
    public static Map<String, Integer> tmtBoundTextures = new HashMap<>();
    private static Integer currentKey;

    public static Map<ItemStack,int[]> ingotColors = new HashMap<>();


    private static Map<ResourceLocation, Integer> tmtMap = new HashMap<>();

    private static ITextureObject object;

    /**
     * custom texture binding method, generally same as vanilla, but possible to improve performance later.
     * @param textureURI
     */
    public static int bindTexture(ResourceLocation textureURI) {
        if (textureURI == null){
            textureURI= new ResourceLocation(TrainsInMotion.MODID,"nullTrain");
        }
        //clean out the texture bind map when texturepacks are reloaded.
        if(MCResourcePacks!= Minecraft.getMinecraft().getResourceManager().getResourceDomains()){
            MCResourcePacks= Minecraft.getMinecraft().getResourceManager().getResourceDomains();
            tmtMap=new HashMap<>();
            tmtBoundTextures = new HashMap<>();
        }
        if(!ClientProxy.ForceTextureBinding) {
            object = Minecraft.getMinecraft().getTextureManager().getTexture(textureURI);
            if (object == null) {
                object = new SimpleTexture(textureURI);
                Minecraft.getMinecraft().getTextureManager().loadTexture(textureURI, object);
            }
            GL11.glBindTexture(GL_TEXTURE_2D, object.getGlTextureId());
            return object.getGlTextureId();
        } else {
            Integer id;
            if (!tmtMap.containsKey(textureURI)){
                object = Minecraft.getMinecraft().getTextureManager().getTexture(textureURI);
                if (object == null) {
                    object = new SimpleTexture(textureURI);
                    Minecraft.getMinecraft().getTextureManager().loadTexture(textureURI, object);
                }
                id=object.getGlTextureId();
                tmtMap.put(textureURI, id);
            } else {
                id= tmtMap.get(textureURI);
            }
            if(GL11.glGetInteger(GL_TEXTURE_2D) !=id) {
                GL11.glBindTexture(GL_TEXTURE_2D, id);
            }
        }
        return -1;
    }

    //most compilers should process this type of function faster than a normal typecast.
    public static byte b(int i){return (byte) i;}

    public static boolean colorInRange(int r, int g, int b, int oldR, int oldG, int oldB){
        return oldR-r>-17 && oldR-r <17 &&
                oldG-g>-17 && oldG-g <17 &&
                oldB-b>-17 && oldB-b <17;
    }


    /**Lighting fix*/
    public static void adjustLightFixture(World world, int x, int y, int z) {
        skyLight = world.getCombinedLight(new BlockPos(x,y,z), 0);
        float j = skyLight % 65536;
        float k = skyLight / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);
        GL11.glColor4f(1, 1, 1, 1);//fixes alpha layering bugs with other mods that don't clear their GL cache
    }

    private static FloatBuffer colorBuffer = GLAllocation.createDirectFloatBuffer(16);

    private static FloatBuffer setColorBuffer(float p_74521_0_, float p_74521_1_, float p_74521_2_, float p_74521_3_)
    {
        colorBuffer.clear();
        colorBuffer.put(p_74521_0_).put(p_74521_1_).put(p_74521_2_).put(p_74521_3_);
        colorBuffer.flip();
        /** Float buffer used to set OpenGL material colors */
        return colorBuffer;
    }

    public static void fixEntityLighting(){

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_LIGHT0);
        GL11.glEnable(GL11.GL_LIGHT1);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glColorMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE);
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, setColorBuffer(0.16169041989141428f, 0.8084520874101966f, -0.5659164515496377f, 0.0f));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, setColorBuffer(0.6F,0.6F,0.6F, 1.0F));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, setColorBuffer(0.0F,0.0F,0.0F, 1.0F));
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, setColorBuffer(-0.16169041989141428f, 0.8084520874101966f,0.5659164515496377f, 0.0f));
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, setColorBuffer(0.6F,0.6F,0.6F, 1.0F));
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_AMBIENT, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_SPECULAR, setColorBuffer(0.0F,0.0F,0.0F, 1.0F));
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, setColorBuffer(0.4F,0.4F,0.4F, 1.0F));

    }


    /**
     * Ingot color textures
     */
    public static void collectIngotColors(){
        List<ItemStack> Ores= RecipeManager.getAcceptedRailItems();

        int red,green,blue,divisor;
        int[]rgb, colorBuff;
        ResourceLocation texture;
        for (ItemStack s : Ores){

            texture=null;
            red =0;green=0;blue=0;divisor=0;
            //todo: this may not work? this is how it's supposed to work, buildcraft does it.
            // but i know better than to blindly trust this game.
            //Item item = s.getItem();
            String textureName = TextureMap.LOCATION_BLOCKS_TEXTURE.toString();// item.getIcon(s,0)!=null?item.getIcon(s,0).getIconName():null;
            if(textureName != null){
                if(textureName.split(":").length == 1){
                    textureName = "minecraft:" + textureName;
                }
                texture = ClientUtil.TEXTURE_MAP_ICONS;//in theory this should fix it since we're only looking for item textures.
                        //new ResourceLocation(textureName.split(":")[0], "textures/items/" + textureName.split(":")[1]);
            }

            if(texture != null){
                try {
                    colorBuff = TextureUtil.readImageData(Minecraft.getMinecraft().getResourceManager(), texture);
                    for(int c : colorBuff){
                        rgb=hexTorgba(c);
                        if(rgb[3]>128) {
                            if(rgb[0]+rgb[1]+rgb[2]>20) {
                                red+=25+rgb[2];
                                blue+=25+rgb[1];
                                green+=25+rgb[0];
                                divisor++;
                            }
                        }
                    }
                    ingotColors.put(s, new int[]{red/divisor,blue/divisor,green/divisor});
                } catch (IOException e) {
                    DebugUtil.println("Caught exception while parsing texture to get color: ");
                    e.printStackTrace();
                }

            }

        }
    }


    public static TextureAtlasSprite bindBlockTextureFromSide(int side, ItemStack b){
        //override texture should be pre-determined on init in 1.8+
        //if (RenderBlocks.getInstance().hasOverrideBlockTexture()) {
        //    return (TextureAtlasSprite)RenderBlocks.getInstance().overrideBlockTexture;
        //}
        IBlockState state = Block.getBlockFromItem(b.getItem()).getStateForPlacement(Minecraft.getMinecraft().world,
                new BlockPos(Minecraft.getMinecraft().player.getPosition()),EnumFacing.UP,0,0,0,
                b.getMetadata(),Minecraft.getMinecraft().player, EnumHand.MAIN_HAND);
        List<BakedQuad> pothead = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(state).getQuads(state, EnumFacing.byIndex(side),0l);
        if(pothead.isEmpty()){
            return Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(state).getParticleTexture();
        } else {
            return pothead.get(0).getSprite();
        }

        /* backup plan
        TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
        TextureAtlasSprite textureatlassprite = texturemap.getAtlasSprite("minecraft:blocks/fire_layer_0");
        TextureAtlasSprite textureatlassprite1 = texturemap.getAtlasSprite("minecraft:blocks/fire_layer_1");

         */
    }

    public static int[] hexTorgba(int hex){
        return new int[]{hex&0xFF, (hex>>8)&0xFF, (hex>>16)&0xFF, (hex>>24)&0xFF};
    }

    public static int[] hexTorgb(int hex){
        return new int[]{hex&0xFF, (hex>>8)&0xFF, (hex>>16)&0xFF};
    }



    public  static int[] postProcessColor(int newColor, int r, int g, int b){
        int[] ret =hexTorgb(newColor);

        ret[0] += ret[0]-b;
        ret[1] += ret[1]-g;
        ret[2] += ret[2]-r;
        return ret;
    }








    public static void bindTexture(ResourceLocation textureURI, int[] skinColorsFrom, int[] skinColorsTo, List<Integer> colorsFrom, List<Integer> colorsTo){
            //clean out the texture bind map when texturepacks are reloaded.
            if (MCResourcePacks != Minecraft.getMinecraft().getResourceManager().getResourceDomains()) {
                MCResourcePacks = Minecraft.getMinecraft().getResourceManager().getResourceDomains();
                tmtMap = new HashMap<>();
                tmtBoundTextures = new HashMap<>();
            }

            GL11.glEnable(GL_TEXTURE_2D);
            if (!tmtBoundTextures.containsKey(getID(textureURI, skinColorsFrom, skinColorsTo, colorsFrom, colorsTo, false))) {
                if (createAWT(textureURI, skinColorsFrom, skinColorsTo, colorsFrom, colorsTo) &&
                        new File(getID(textureURI,skinColorsFrom, skinColorsTo, colorsFrom,colorsTo,true)).exists()) {
                    try {
                        BufferedImage image = ImageIO.read(new File(getID(textureURI, skinColorsFrom, skinColorsTo, colorsFrom, colorsTo, true)));

                        currentKey = tmtBoundTextures.put(getID(textureURI, skinColorsFrom, skinColorsTo, colorsFrom, colorsTo, false),
                                Minecraft.getMinecraft().getTextureManager().getTexture(
                                        Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation(
                                                getID(textureURI, skinColorsFrom, skinColorsTo, colorsFrom, colorsTo, true),
                                                new DynamicTexture(image))).getGlTextureId());
                    } catch (IOException exception) {
                        DebugUtil.println("AWT FAILED");
                        exception.printStackTrace();
                    }
                }
            } else {
                currentKey = tmtBoundTextures.get(getID(textureURI, skinColorsFrom, skinColorsTo, colorsFrom, colorsTo, false));
            }

            //if for some reason the texture couldn't be written to I/O, which should never be an issue.
            if (currentKey == null) {
                bindTexture(textureURI);
            } else {
                if (GL11.glGetInteger(GL_TEXTURE_2D) != currentKey) {
                    GL11.glBindTexture(GL_TEXTURE_2D, currentKey);
                }
            }

    }



    public static boolean createAWT(ResourceLocation textureURI, int[] skinColorsFrom, int[] skinColorsTo, List<Integer> colorsFrom, List<Integer> colorsTo){
        int GLtexture = bindTexture(textureURI);
        if(GLtexture==-1){
            return true;
        }

        //get image data from the currently bound image
        int width =glGetTexLevelParameteri(GL_TEXTURE_2D, 0, GL_TEXTURE_WIDTH);
        int height =glGetTexLevelParameteri(GL_TEXTURE_2D, 0, GL_TEXTURE_HEIGHT);
        if(width*height<4){
            return false;
        }

        ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4);

        GL11.glGetTexImage(GL_TEXTURE_2D, 0, GL11.GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        //create a buffered image and push the data to it
        BufferedImage skin = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        int i,r,g,b,a, y,ii;
        int[] col;

        for (int x = 0; x < width; x++) {
            for (y = 0; y < height; y++) {

                i = (x + (width * y)) * 4;
                r = buffer.get(i) & 0xff;
                g = buffer.get(i + 1) & 0xff;
                b = buffer.get(i + 2) & 0xff;
                a = buffer.get(i + 3) & 0xff;
                if(a<10){
                    skin.setRGB(x,y,0x00000000);
                    continue;
                }

                //recolor from skin
                if(skinColorsFrom!=null&&skinColorsFrom.length>0){
                    for (ii=0;ii<skinColorsFrom.length;ii++){
                        col = hexTorgb(skinColorsFrom[ii]);
                        if(colorInRange(r,g,b,col[2],col[1],col[1])){
                            col=postProcessColor(skinColorsTo[ii],r,g,b);
                            r=col[0];
                            g=col[1];
                            b=col[2];
                        }
                    }
                }
                //recolor from player settings. ORDER IS IMPORTANT
                if(colorsFrom!=null && colorsFrom.size()>0) {
                    for (ii=0;ii<colorsFrom.size();ii++){
                        col = hexTorgba(colorsFrom.get(ii));
                        if(colorInRange(r,g,b,col[2],col[1],col[0])){
                            col=postProcessColor(skinColorsTo[ii],r,g,b);
                            r=col[0];
                            g=col[1];
                            b=col[2];
                        }
                    }
                }

                skin.setRGB(x, y, (a << 24) | (r << 16) | (g << 8) | b);
            }
        }

        try {
            if(!new File(ClientProxy.configDirectory+"/TrainsInMotion/TextureCache/"+
                    resourceLocation(textureURI)).exists()){
                new File(ClientProxy.configDirectory+"/TrainsInMotion/TextureCache/"+
                        resourceLocation(textureURI)).mkdirs();
            }
            ImageIO.write(skin, "PNG", new File(getID(textureURI,skinColorsFrom, skinColorsTo, colorsFrom,colorsTo,true)));
            buffer.clear();


            if(GL11.glIsTexture(GLtexture) && !ClientProxy.ForceTextureBinding){
                GL11.glDeleteTextures(GLtexture);
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            buffer.clear();
            return false;
        }
    }

    private static String getID(ResourceLocation textureURI, int[] skinColorsFrom, int[] skinColorsTo, List<Integer> colorsFrom, List<Integer> colorsTo, boolean isFile){
        StringBuilder filePath = new StringBuilder();
        if(isFile) {
            filePath.append(ClientProxy.configDirectory);
            filePath.append("/TrainsInMotion/TextureCache/");
            filePath.append(resourceLocation(textureURI));
            filePath.append("/");
            if(skinColorsFrom!=null && skinColorsTo!=null && skinColorsFrom.length>0 && skinColorsTo.length>0) {
                for (Integer i : skinColorsFrom) {
                    filePath.append(Integer.toHexString(i));
                }
                filePath.append("_");
                for (Integer i : skinColorsTo) {
                    filePath.append(Integer.toHexString(i));
                }
                filePath.append("+");
            }
            if(colorsFrom==null || colorsTo==null || colorsFrom.size()+colorsTo.size()==0){
                filePath.append("000_000");
            } else {
                for (Integer i : colorsFrom) {
                    filePath.append(Integer.toHexString(i));
                }
                filePath.append("_");
                for (Integer i : colorsTo) {
                    filePath.append(Integer.toHexString(i));
                }
            }
            filePath.append(".png");
        } else {
            filePath.append(resourceLocation(textureURI));
            filePath.append(".");
            if(skinColorsFrom!=null && skinColorsTo!=null && skinColorsFrom.length>0 && skinColorsTo.length>0) {
                for (Integer i : skinColorsFrom) {
                    filePath.append(Integer.toHexString(i));
                }
                filePath.append("_");
                for (Integer i : skinColorsTo) {
                    filePath.append(Integer.toHexString(i));
                }
                if(colorsFrom!=null && colorsTo!=null && colorsFrom.size()+colorsTo.size()>1) {
                    filePath.append("+");
                }
            }
            if(colorsFrom!=null && colorsTo!=null && colorsFrom.size()+colorsTo.size()>1){
                for (Integer i : colorsFrom) {
                    filePath.append(Integer.toHexString(i));
                }
                for (Integer i : colorsTo) {
                    filePath.append(Integer.toHexString(i));
                }
            }
        }
        return filePath.toString();
    }

    private static String resourceLocation(ResourceLocation res){
        return (res.getNamespace() + "/"
                +res.getPath().
                substring(0, res.getPath().lastIndexOf(".")));
    }

}
