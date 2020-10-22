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

public class EnumButton extends Button
{
    @Override
    public void drawScreen(final int lllllllllllllllllIIIIIlIIIIIIIll, final int lllllllllllllllllIIIIIlIIIIIIIlI, final float lllllllllllllllllIIIIIlIIIIIIIIl) {
        XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height, this.getState() ? ColorUtils.changeAlpha(ColorUtil.getClickGUIColor().getRGB(), 200) : 290805077, -1);
        if (this.isHovering(lllllllllllllllllIIIIIlIIIIIIIll, lllllllllllllllllIIIIIlIIIIIIIlI)) {
            if (this.getState()) {
                XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width, this.y + this.height, ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 25), -1);
            }
            else {
                XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width, this.y + this.height, ColorUtils.changeAlpha(ColorUtils.Colors.WHITE, 25), -1);
            }
        }
        if (ExeterGui.getCF()) {
            Xulu.cFontRenderer.drawStringWithShadow(String.format("%s§7 %s", this.getLabel(), Xulu.getTitle(this.property.getValue().toString())), this.x + 2.3f, this.y + 3.0f, this.getState() ? -1 : -5592406);
        }
        else {
            EnumButton.fontRenderer.drawStringWithShadow(String.format("%s§7 %s", this.getLabel(), Xulu.getTitle(this.property.getValue().toString())), this.x + 2.3f, this.y + 4.0f, this.getState() ? -1 : -5592406);
        }
    }
    
    @Override
    public void mouseClicked(final int lllllllllllllllllIIIIIIllllIllll, final int lllllllllllllllllIIIIIIllllIlllI, final int lllllllllllllllllIIIIIIllllIllIl) {
        super.mouseClicked(lllllllllllllllllIIIIIIllllIllll, lllllllllllllllllIIIIIIllllIlllI, lllllllllllllllllIIIIIIllllIllIl);
        if (this.isHovering(lllllllllllllllllIIIIIIllllIllll, lllllllllllllllllIIIIIIllllIlllI)) {
            if (ExeterGui.getSound()) {
                EnumButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            }
            final String lllllllllllllllllIIIIIIlllllIlIl = (this.property.getValue() instanceof String) ? this.property.getValue() : this.property.getValue().toString();
            if (lllllllllllllllllIIIIIIllllIllIl == 0) {
                try {
                    if (!this.property.getCorrectOption(lllllllllllllllllIIIIIIlllllIlIl).toString().equalsIgnoreCase(this.property.getOptions().get(this.property.getOptions().size() - 1).toString())) {
                        this.property.setEnumValue(this.property.getOptions().get(this.property.getOptions().indexOf(this.property.getCorrectOption(lllllllllllllllllIIIIIIlllllIlIl)) + 1).toString());
                    }
                    else {
                        this.property.setEnumValue(this.property.getOptions().get(0).toString());
                    }
                }
                catch (Exception lllllllllllllllllIIIIIIlllllIlll) {
                    System.err.println("Error with invalid combo");
                    lllllllllllllllllIIIIIIlllllIlll.printStackTrace();
                    this.property.setEnumValue(this.property.getOptions().get(0).toString());
                }
            }
            else if (lllllllllllllllllIIIIIIllllIllIl == 1) {
                try {
                    if (this.property.getOptions().listIterator(this.property.getOptions().indexOf(this.property.getCorrectOption(lllllllllllllllllIIIIIIlllllIlIl))).hasPrevious()) {
                        this.property.setEnumValue(this.property.getOptions().listIterator(this.property.getOptions().indexOf(this.property.getCorrectOption(lllllllllllllllllIIIIIIlllllIlIl))).previous().toString());
                    }
                    else {
                        this.property.setEnumValue(this.property.getOptions().get(this.property.getOptions().size() - 1).toString());
                    }
                }
                catch (Exception lllllllllllllllllIIIIIIlllllIllI) {
                    System.err.println("Error with invalid combo");
                    lllllllllllllllllIIIIIIlllllIllI.printStackTrace();
                    this.property.setEnumValue(this.property.getOptions().get(0).toString());
                }
            }
        }
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
    
    @Override
    public boolean getState() {
        return true;
    }
    
    @Override
    public void toggle() {
    }
    
    public EnumButton(final Value lllllllllllllllllIIIIIlIIIIIlIlI) {
        super(lllllllllllllllllIIIIIlIIIIIlIlI.getName(), null);
        this.setValue(lllllllllllllllllIIIIIlIIIIIlIlI);
    }
}
