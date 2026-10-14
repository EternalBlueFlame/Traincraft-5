package train.client.render.gl;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Wraps a Traincraft TESR so the GL state it leaves behind is restored (see {@link GLStateHelper}).
 */
@SideOnly(Side.CLIENT)
public class TCSafeTESR<T extends TileEntity> extends TileEntitySpecialRenderer<T> {
    private final TileEntitySpecialRenderer<T> delegate;

    private TCSafeTESR(TileEntitySpecialRenderer<T> delegate) {
        this.delegate = delegate;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T extends TileEntity> TileEntitySpecialRenderer<T> wrap(TileEntitySpecialRenderer tesr) {
        if (tesr == null || tesr instanceof TCSafeTESR) {
            return tesr;
        }
        return new TCSafeTESR<>((TileEntitySpecialRenderer<T>) tesr);
    }

    public TileEntitySpecialRenderer<T> getDelegate() {
        return this.delegate;
    }

    @Override
    public void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GLStateHelper.begin();
        try {
            this.delegate.render(te, x, y, z, partialTicks, destroyStage, alpha);
        } finally {
            GLStateHelper.end();
        }
    }

    @Override
    public void setRendererDispatcher(TileEntityRendererDispatcher dispatcher) {
        super.setRendererDispatcher(dispatcher);
        this.delegate.setRendererDispatcher(dispatcher);
    }

    @Override
    public boolean isGlobalRenderer(T te) {
        return this.delegate.isGlobalRenderer(te);
    }

    @Override
    public FontRenderer getFontRenderer() {
        return this.delegate.getFontRenderer();
    }
}
