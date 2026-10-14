package train.client.render.item;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import train.common.items.ILegacyIcon;
import train.common.library.Info;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Function;

/**
 * Item and block model wiring for 1.12, so the 1.7.10 items don't each need a hand written model json:
 * <ul>
 * <li>items implementing {@link ILegacyIcon} get a generated flat item model ({@code item/generated})
 * using their old icon texture: icon {@code tc:parts/steel} becomes the sprite {@code tc:items/parts/steel};</li>
 * <li>blocks drawn only by a TileEntitySpecialRenderer (render type ENTITYBLOCK_ANIMATED) get an empty
 * block model, and their items the shared {@link TCItemRenderRegistry#MODEL_LOCATION} built-in model.</li>
 * </ul>
 * Everything else still uses normal blockstate / item model jsons.
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Info.modID)
public final class TCModels {
    /** prefix of the generated item models: {@code tc:generated/<old icon path>#inventory} */
    private static final String GENERATED = "generated/";
    /** what custom loaders see: ModelLoaderRegistry prefixes plain model locations with "models/" */
    private static final String GENERATED_ITEM_MODEL = "models/item/" + GENERATED;
    public static final ModelResourceLocation EMPTY = new ModelResourceLocation(Info.modID + ":empty", "normal");

    /** used only for its property-string formatting ("half=bottom,variant=default") */
    private static final net.minecraft.client.renderer.block.statemap.DefaultStateMapper VARIANTS = new net.minecraft.client.renderer.block.statemap.DefaultStateMapper();

    private TCModels() {
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(Loader.INSTANCE);

        for (Item item : ForgeRegistries.ITEMS) {
            ResourceLocation name = item.getRegistryName();
            if (name == null || !Info.modID.equals(name.getNamespace())) {
                continue;
            }
            if (TCItemRenderRegistry.hasRenderer(item)) {
                ModelLoader.setCustomModelResourceLocation(item, 0, TCItemRenderRegistry.MODEL_LOCATION);
            } else if (item instanceof ILegacyIcon) {
                for (int meta : metadataValues(item)) {
                    String icon = ((ILegacyIcon) item).legacyIcon(meta);
                    if (icon != null && !icon.isEmpty()) {
                        ModelLoader.setCustomModelResourceLocation(item, meta, generatedModel(icon));
                    }
                }
            } else if (item instanceof ItemBlock && isSpecialRendered(((ItemBlock) item).getBlock())) {
                ModelLoader.setCustomModelResourceLocation(item, 0, TCItemRenderRegistry.MODEL_LOCATION);
            } else if (item instanceof ItemBlock && item.getHasSubtypes()) {
                // one item model per metadata: the matching blockstate variant (e.g. tc:oretc#type=2)
                Block block = ((ItemBlock) item).getBlock();
                for (int meta : metadataValues(item)) {
                    @SuppressWarnings("deprecation")
                    IBlockState state = block.getStateFromMeta(meta);
                    ModelLoader.setCustomModelResourceLocation(item, meta,
                            new ModelResourceLocation(block.getRegistryName(), variantOf(state)));
                }
            } else {
                // 1.12 items only get a model if one is assigned explicitly, otherwise they render as the
                // missing model (and Forge doesn't log anything). Use the item json when there is one,
                // else the block's "inventory" variant, else the block's default state variant.
                ModelLoader.setCustomModelResourceLocation(item, 0, defaultItemModel(item));
            }
        }

        if (Boolean.getBoolean("tc.dumpModels")) {
            dump();
        }

        StateMapperBase empty = new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return EMPTY;
            }
        };
        for (Block block : ForgeRegistries.BLOCKS) {
            ResourceLocation name = block.getRegistryName();
            if (name != null && Info.modID.equals(name.getNamespace()) && isSpecialRendered(block)) {
                ModelLoader.setCustomStateMapper(block, empty);
            } else if (name != null && Info.modID.equals(name.getNamespace()) && block instanceof net.minecraftforge.fluids.BlockFluidBase) {
                // fluid blockstates only have the "normal" variant (forge:fluid model)
                ModelLoader.setCustomStateMapper(block, new net.minecraft.client.renderer.block.statemap.StateMap.Builder()
                        .ignore(net.minecraftforge.fluids.BlockFluidBase.LEVEL).build());
            }
            else if (name != null && Info.modID.equals(name.getNamespace()) && block.getBlockState().getProperties().contains(train.common.api.blocks.LegacyMeta.META)) {
                // the legacy metadata only carries facing/variant for TESRs and logic, the json models don't know it
                ModelLoader.setCustomStateMapper(block, new net.minecraft.client.renderer.block.statemap.StateMap.Builder()
                        .ignore(train.common.api.blocks.LegacyMeta.META).build());
            }
        }
    }

    private static ModelResourceLocation defaultItemModel(Item item) {
        ResourceLocation name = item.getRegistryName();
        ModelResourceLocation inventory = new ModelResourceLocation(name, "inventory");
        if (resourceExists(new ResourceLocation(name.getNamespace(), "models/item/" + name.getPath() + ".json"))) {
            return inventory;
        }
        if (item instanceof ItemBlock) {
            Block block = ((ItemBlock) item).getBlock();
            ResourceLocation blockName = block.getRegistryName();
            String blockstate = readResource(new ResourceLocation(blockName.getNamespace(), "blockstates/" + blockName.getPath() + ".json"));
            if (blockstate != null && !blockstate.contains("\"inventory\"")) {
                return new ModelResourceLocation(blockName, variantOf(block.getDefaultState()));
            }
            return new ModelResourceLocation(blockName, "inventory");
        }
        return inventory;
    }

    /** blockstate variant name, without the legacy "meta" property (ignored by the state mapper) */
    private static String variantOf(IBlockState state) {
        java.util.Map<net.minecraft.block.properties.IProperty<?>, Comparable<?>> props = new java.util.LinkedHashMap<>(state.getProperties());
        props.remove(train.common.api.blocks.LegacyMeta.META);
        return VARIANTS.getPropertyString(props);
    }

    private static boolean resourceExists(ResourceLocation location) {
        try (net.minecraft.client.resources.IResource ignored = net.minecraft.client.Minecraft.getMinecraft().getResourceManager().getResource(location)) {
            return true;
        } catch (java.io.IOException e) {
            return false;
        }
    }

    @Nullable
    private static String readResource(ResourceLocation location) {
        try (net.minecraft.client.resources.IResource res = net.minecraft.client.Minecraft.getMinecraft().getResourceManager().getResource(location)) {
            return org.apache.commons.io.IOUtils.toString(res.getInputStream(), java.nio.charset.StandardCharsets.UTF_8);
        } catch (java.io.IOException e) {
            return null;
        }
    }

    /** True for blocks that are drawn by a TileEntitySpecialRenderer only (no baked block model). */
    public static boolean isSpecialRendered(Block block) {
        try {
            EnumBlockRenderType type = block.getDefaultState().getRenderType();
            return type == EnumBlockRenderType.ENTITYBLOCK_ANIMATED || type == EnumBlockRenderType.INVISIBLE;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /** {@code tc:parts/steel} -> {@code tc:generated/parts/steel#inventory} */
    private static ModelResourceLocation generatedModel(String icon) {
        ResourceLocation rl = new ResourceLocation(icon.contains(":") ? icon : Info.modID + ":" + icon);
        return new ModelResourceLocation(new ResourceLocation(rl.getNamespace(), GENERATED + rl.getPath()), "inventory");
    }

    private static Collection<Integer> metadataValues(Item item) {
        if (!item.getHasSubtypes()) {
            return Collections.singleton(0);
        }
        TreeSet<Integer> metas = new TreeSet<>();
        NonNullList<ItemStack> stacks = NonNullList.create();
        try {
            item.getSubItems(CreativeTabs.SEARCH, stacks);
        } catch (RuntimeException ignored) {
            // some items can't list sub items this early, fall back to meta 0
        }
        for (ItemStack stack : stacks) {
            if (stack.getItem() == item) {
                metas.add(stack.getMetadata());
            }
        }
        if (metas.isEmpty()) {
            metas.add(0);
        }
        return metas;
    }

    /** Serves the generated item models, the empty block model and a placeholder for the built-in item model. */
    private enum Loader implements ICustomModelLoader {
        INSTANCE;

        @Override
        public void onResourceManagerReload(IResourceManager resourceManager) {
        }

        @Override
        public boolean accepts(ResourceLocation location) {
            if (location instanceof ModelResourceLocation) {
                return location.getNamespace().equals(EMPTY.getNamespace()) && location.getPath().equals(EMPTY.getPath());
            }
            return location.getPath().startsWith(GENERATED_ITEM_MODEL)
                    || (location.getNamespace().equals(Info.modID) && location.getPath().equals("models/item/tc_item_renderer"));
        }

        @Override
        public IModel loadModel(ResourceLocation location) throws Exception {
            if (location.getPath().startsWith(GENERATED_ITEM_MODEL)) {
                String texture = location.getNamespace() + ":items/" + location.getPath().substring(GENERATED_ITEM_MODEL.length());
                return ModelLoaderRegistry.getModel(new ResourceLocation("minecraft", "item/generated"))
                        .retexture(ImmutableMap.of("layer0", texture));
            }
            return EmptyModel.INSTANCE;
        }
    }

    /** A model without any quads (for blocks/items rendered entirely by code). */
    private enum EmptyModel implements IModel {
        INSTANCE;

        @Override
        public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
            final TextureAtlasSprite particle = bakedTextureGetter.apply(new ResourceLocation("minecraft", "blocks/iron_block"));
            return new IBakedModel() {
                @Override
                public List<BakedQuad> getQuads(@Nullable IBlockState blockState, @Nullable EnumFacing side, long rand) {
                    return ImmutableList.of();
                }

                @Override
                public boolean isAmbientOcclusion() {
                    return false;
                }

                @Override
                public boolean isGui3d() {
                    return true;
                }

                @Override
                public boolean isBuiltInRenderer() {
                    return false;
                }

                @Override
                public TextureAtlasSprite getParticleTexture() {
                    return particle;
                }

                @Override
                public ItemCameraTransforms getItemCameraTransforms() {
                    return ItemCameraTransforms.DEFAULT;
                }

                @Override
                public ItemOverrideList getOverrides() {
                    return ItemOverrideList.NONE;
                }
            };
        }

        @Override
        public Collection<ResourceLocation> getTextures() {
            return ImmutableList.of(new ResourceLocation("minecraft", "blocks/iron_block"));
        }
    }

    /** Debug helper: old icon -> generated model location (handy when checking which textures are missing). */
    public static Map<String, ModelResourceLocation> describe() {
        Map<String, ModelResourceLocation> out = new LinkedHashMap<>();
        for (Item item : ForgeRegistries.ITEMS) {
            if (item instanceof ILegacyIcon && item.getRegistryName() != null) {
                String icon = ((ILegacyIcon) item).legacyIcon(0);
                if (icon != null) {
                    out.put(item.getRegistryName().toString(), generatedModel(icon));
                }
            }
        }
        return out;
    }

    /** -Dtc.dumpModels=true: writes which model source every Traincraft item/block uses to tc_models.txt. */
    private static void dump() {
        try (java.io.PrintWriter out = new java.io.PrintWriter("tc_models.txt", "UTF-8")) {
            for (Item item : ForgeRegistries.ITEMS) {
                ResourceLocation name = item.getRegistryName();
                if (name == null || !Info.modID.equals(name.getNamespace())) continue;
                String how;
                if (item instanceof ILegacyIcon) how = "icon " + ((ILegacyIcon) item).legacyIcon(0);
                else if (item instanceof ItemBlock && isSpecialRendered(((ItemBlock) item).getBlock())) how = "tesr";
                else if (item instanceof ItemBlock) how = "block";
                else how = "json";
                out.println("item " + name + " " + item.getClass().getSimpleName() + " " + how);
            }
            for (Block block : ForgeRegistries.BLOCKS) {
                ResourceLocation name = block.getRegistryName();
                if (name == null || !Info.modID.equals(name.getNamespace())) continue;
                out.println("block " + name + " " + block.getClass().getSimpleName() + " " + (isSpecialRendered(block) ? "tesr" : "json") + " " + block.getBlockState().getProperties());
            }
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
}
