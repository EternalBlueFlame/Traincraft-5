package ebf.tim.api;

import ebf.tim.utility.CommonUtil;
import net.minecraft.util.ResourceLocation;

public class TransportSkin {
    public String[] texture;
    public ResourceLocation[] bogieTextures, subBogieTextures;
    public int[] colorsFrom, colorsTo;
    public String name, modid, description;
    public int id;

    public TransportSkin(String modId, String[] texture, String name, String description){
        setTexture(texture);
        this.modid=modId;
        this.description=description;
        this.name=name;
    }

    public TransportSkin(String modId, String texture, String name, String description){
        this.texture=new String[]{texture.contains(".")?texture:texture+".png"};
        this.modid=modId;
        this.description=description;
        this.name=name;
    }


    public ResourceLocation getBogieSkin(int index){
        if(bogieTextures ==null || bogieTextures.length==0){
            return null;
        }
        if(bogieTextures.length>index){
            return bogieTextures[index];
        } else {
            return bogieTextures[0];
        }
    }

    public ResourceLocation getSubBogieSkin(int index){
        if(subBogieTextures ==null || subBogieTextures.length==0){
            return null;
        }
        if(subBogieTextures.length>index){
            return subBogieTextures[index];
        } else {
            return subBogieTextures[0];
        }
    }

    public String[] getDescription(){
        String translated=CommonUtil.translate(description.contains(":")?description:modid+":"+description);
        if(translated.startsWith(modid)){
            return description.split("\n");
        }
        return translated.split( "<br>");}

    public String getName(){
        String translated =CommonUtil.translate(name.contains(":")?name:modid+":"+name);
        if(translated.startsWith(modid)){
            return name;
        }
        return translated;
    }

    public ResourceLocation getTexture(int id){return new ResourceLocation(modid,id>=texture.length?texture[0]:texture[id]);}

    public TransportSkin setBogieTextures(String texture){
        bogieTextures=resourceList(modid,new String[]{texture.contains(".")?texture:texture+".png"});
        return this;
    }

    public TransportSkin setBogieTextures(String[] textures){
        bogieTextures=resourceList(modid,textures);
        return this;
    }
    public TransportSkin setBogieTextures(ResourceLocation... textures){
        bogieTextures=textures;
        if(bogieTextures!=null) {
            for (int i = 0; i < textures.length; i++) {
                if (!bogieTextures[i].getResourcePath().contains(".")) {
                    bogieTextures[i] = new ResourceLocation(bogieTextures[i].getResourceDomain(), bogieTextures[i].getResourcePath() + ".png");
                }
            }
        }
        return this;
    }
    public TransportSkin setSubBogieTextures(String... textures){
        subBogieTextures=resourceList(modid,textures);
        return this;
    }
    public TransportSkin setSubBogieTextures(ResourceLocation... textures){
        subBogieTextures=textures;
        if(subBogieTextures!=null) {
            for (int i = 0; i < textures.length; i++) {
                if (!subBogieTextures[i].getResourcePath().contains(".")) {
                    subBogieTextures[i] = new ResourceLocation(subBogieTextures[i].getResourceDomain(), subBogieTextures[i].getResourcePath() + ".png");
                }
            }
        }
        return this;
    }
    public TransportSkin setRecolorsTo(int... recolorsTo){
        colorsTo=recolorsTo;
        return this;
    }

    public TransportSkin setRecolorsFrom(int... recolorsFrom){
        colorsFrom=recolorsFrom;
        return this;
    }

    public TransportSkin setTexture(String[] textures){
        this.texture=textures;
        for(int i=0;i<texture.length;i++){
            if(!texture[i].contains(".")){
                texture[i]+=".png";
            }
        }
        return this;
    }


    public TransportSkin setTexture(String modId, String[] textures){
        this.texture=textures;
        for(int i=0;i<texture.length;i++){
            if(!texture[i].contains(".")){
                texture[i]+=".png";
            }
        }
        this.modid=modId;
        return this;
    }

    public TransportSkin setName(String name){
        this.name=name;
        return this;
    }

    public TransportSkin setDescription(String description){
        this.description = description;
        return this;
    }

    public void setId(int i){
        id=i;
    }


    private static ResourceLocation[] resourceList(String modid, String[] URIs){
        ResourceLocation[] value = new ResourceLocation[URIs.length];
        for (int i=0; i< URIs.length; i++){
            if(!URIs[i].contains(".")){
                URIs[i]+=".png";
            }

            if(URIs[i].contains(":")){
                value[i] = new ResourceLocation(URIs[i]);
            } else {
                value[i] = new ResourceLocation(modid, URIs[i]);
            }
        }
        return value;
    }
}
