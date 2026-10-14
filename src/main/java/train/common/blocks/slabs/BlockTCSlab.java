package train.common.blocks.slabs;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

/**
 * 1.12 slab boilerplate shared by the Traincraft slabs (one variant each, single and double block).
 * The 1.7.10 classes took {@code isDouble} as a constructor argument; 1.12 needs it while the block state
 * is being built (inside the super constructor), so it is handed over through {@link #PENDING}.
 */
public abstract class BlockTCSlab extends BlockSlab {

    public enum Variant implements IStringSerializable {
        DEFAULT;

        @Override
        public String getName() {
            return "default";
        }
    }

    public static final PropertyEnum<Variant> VARIANT = PropertyEnum.create("variant", Variant.class);

    private static final ThreadLocal<Boolean> PENDING = new ThreadLocal<>();

    private Boolean doubleSlab;

    private static Material prepare(Material material, boolean isDouble) {
        PENDING.set(isDouble);
        return material;
    }

    public BlockTCSlab(boolean isDouble, Material material) {
        super(prepare(material, isDouble));
        this.doubleSlab = isDouble;
        PENDING.remove();
        IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);
        if (!isDouble) {
            state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);
        }
        setDefaultState(state);
    }

    @Override
    public boolean isDouble() {
        if (doubleSlab != null) {
            return doubleSlab;
        }
        Boolean pending = PENDING.get();
        return pending != null && pending;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return isDouble() ? new BlockStateContainer(this, VARIANT) : new BlockStateContainer(this, HALF, VARIANT);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = getDefaultState();
        if (!isDouble()) {
            state = state.withProperty(HALF, (meta & 8) != 0 ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
        }
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return !isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP ? 8 : 0;
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return Variant.DEFAULT;
    }

    @Override
    public String getTranslationKey(int meta) {
        return super.getTranslationKey();
    }
}
