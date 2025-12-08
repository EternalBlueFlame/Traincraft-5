package train.client.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

public class GuiLockMenuTextField extends GuiTextField {
    private final GuiLockMenuAbstract guiClass;
    private final int textFieldID;
    public GuiLockMenuTextField(int textFieldID, FontRenderer p_i1032_1_, int p_i1032_2_, int p_i1032_3_, int p_i1032_4_, int p_i1032_5_, GuiLockMenuAbstract guiClass) {
        super(p_i1032_1_, p_i1032_2_, p_i1032_3_, p_i1032_4_, p_i1032_5_);
        this.textFieldID = textFieldID;
        this.guiClass = guiClass;
    }

    @Override
    public void setFocused(boolean focused) {
        if (super.isFocused() && !focused) { // If losing focus...
            super.setFocused(false);
            // Update the trustee list whenever the text box is deselected.
            guiClass.updateTrustee(textFieldID + guiClass.getCurrentPage() * guiClass.MAX_TRUSTEES_ON_PAGE);
            guiClass.updateButtons();
        }
        super.setFocused(focused);
    }
}
