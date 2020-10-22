package dev.xulu.clickgui.item.properties;

import dev.xulu.clickgui.item.*;
import dev.xulu.newgui.util.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.module.render.*;
import com.elementars.eclient.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import dev.xulu.settings.*;
import dev.xulu.clickgui.*;

public class ModeButton extends Button
{
    @Override
    public void drawScreen(final int lllllllllllllllllIllIIIIIlllIIlI, final int lllllllllllllllllIllIIIIIlllIlIl, final float lllllllllllllllllIllIIIIIlllIlII) {
        XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height, this.getState() ? ColorUtils.changeAlpha(ColorUtil.getClickGUIColor().getRGB(), 200) : 290805077, -1);
        if (this.isHovering(lllllllllllllllllIllIIIIIlllIIlI, lllllllllllllllllIllIIIIIlllIlIl)) {
            if (this.getState()) {
                XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width, this.y + this.height, ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 25), -1);
            }
            else {
                XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width, this.y + this.height, ColorUtils.changeAlpha(ColorUtils.Colors.WHITE, 25), -1);
            }
        }
        if (ExeterGui.getCF()) {
            Xulu.cFontRenderer.drawStringWithShadow(String.format("%s§7 %s", this.getLabel(), this.property.getValue()), this.x + 2.3f, this.y + 3.0f, this.getState() ? -1 : -5592406);
        }
        else {
            ModeButton.fontRenderer.drawStringWithShadow(String.format("%s§7 %s", this.getLabel(), this.property.getValue()), this.x + 2.3f, this.y + 4.0f, this.getState() ? -1 : -5592406);
        }
    }
    
    @Override
    public void mouseClicked(final int lllllllllllllllllIllIIIIIllIIIlI, final int lllllllllllllllllIllIIIIIllIIIIl, final int lllllllllllllllllIllIIIIIllIIIII) {
        super.mouseClicked(lllllllllllllllllIllIIIIIllIIIlI, lllllllllllllllllIllIIIIIllIIIIl, lllllllllllllllllIllIIIIIllIIIII);
        if (this.isHovering(lllllllllllllllllIllIIIIIllIIIlI, lllllllllllllllllIllIIIIIllIIIIl)) {
            if (ExeterGui.getSound()) {
                ModeButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            }
            final String lllllllllllllllllIllIIIIIllIlIII = (this.property.getValue() instanceof String) ? this.property.getValue() : this.property.getValue().toString();
            if (lllllllllllllllllIllIIIIIllIIIII == 0) {
                try {
                    if (!this.property.getCorrectString(lllllllllllllllllIllIIIIIllIlIII).equalsIgnoreCase(this.property.getOptions().get(this.property.getOptions().size() - 1).toString())) {
                        this.property.setValue(this.property.getOptions().get(this.property.getOptions().indexOf(this.property.getCorrectString(lllllllllllllllllIllIIIIIllIlIII)) + 1));
                    }
                    else {
                        this.property.setValue(this.property.getOptions().get(0));
                    }
                }
                catch (Exception lllllllllllllllllIllIIIIIllIlIlI) {
                    System.err.println("Error with invalid combo");
                    lllllllllllllllllIllIIIIIllIlIlI.printStackTrace();
                    this.property.setValue(this.property.getOptions().get(0));
                }
            }
            else if (lllllllllllllllllIllIIIIIllIIIII == 1) {
                try {
                    if (this.property.getOptions().listIterator(this.property.getOptions().indexOf(this.property.getCorrectString(lllllllllllllllllIllIIIIIllIlIII))).hasPrevious()) {
                        this.property.setValue(this.property.getOptions().listIterator(this.property.getOptions().indexOf(this.property.getCorrectString(lllllllllllllllllIllIIIIIllIlIII))).previous());
                    }
                    else {
                        this.property.setValue(this.property.getOptions().get(this.property.getOptions().size() - 1));
                    }
                }
                catch (Exception lllllllllllllllllIllIIIIIllIlIIl) {
                    System.err.println("Error with invalid combo");
                    lllllllllllllllllIllIIIIIllIlIIl.printStackTrace();
                    this.property.setValue(this.property.getOptions().get(0));
                }
            }
        }
    }
    
    @Override
    public boolean getState() {
        return true;
    }
    
    public ModeButton(final Value lllllllllllllllllIllIIIIIlllllIl) {
        super(lllllllllllllllllIllIIIIIlllllIl.getName(), null);
        this.setValue(lllllllllllllllllIllIIIIIlllllIl);
    }
    
    @Override
    public void toggle() {
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
}
