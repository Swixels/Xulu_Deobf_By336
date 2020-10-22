package dev.xulu.newgui.elements.menu;

import dev.xulu.newgui.elements.*;
import dev.xulu.settings.*;
import com.elementars.eclient.module.render.*;
import com.elementars.eclient.*;
import java.awt.*;
import com.elementars.eclient.util.*;
import net.minecraft.client.gui.*;
import dev.xulu.newgui.util.*;

public class ElementCheckBox extends Element
{
    public ElementCheckBox(final ModuleButton lllIlIIIIllIllI, final Value lllIlIIIIllIIlI) {
        this.parent = lllIlIIIIllIllI;
        this.set = lllIlIIIIllIIlI;
        super.setup();
    }
    
    public boolean isCheckHovered(final int lllIlIIIIIIllll, final int lllIlIIIIIIlllI) {
        return lllIlIIIIIIllll >= this.x + 1.0 && lllIlIIIIIIllll <= this.x + 11.0 && lllIlIIIIIIlllI >= this.y + 1.0 && lllIlIIIIIIlllI <= this.y + 11.0;
    }
    
    @Override
    public void drawScreen(final int lllIlIIIIlIIIll, final int lllIlIIIIlIIIlI, final float lllIlIIIIlIIlIl) {
        if (NewGui.toggleSetting.getValue().equalsIgnoreCase("Checkbox")) {
            Color lllIlIIIIlIllII = ColorUtil.getClickGUIColor();
            if (NewGui.rainbowgui.getValue()) {
                lllIlIIIIlIllII = new Color(Xulu.rgb).darker();
            }
            final int lllIlIIIIlIlIll = new Color(lllIlIIIIlIllII.getRed(), lllIlIIIIlIllII.getGreen(), lllIlIIIIlIllII.getBlue(), 225).getRGB();
            Gui.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 60));
            if (NewGui.customfont.getValue()) {
                Xulu.cFontRenderer.drawStringWithShadow(this.setstrg, (float)(this.x + this.width - Xulu.cFontRenderer.getStringWidth(this.setstrg)), (float)(this.y + this.height / 2.0 - 1.5) - 3.0f, -1);
            }
            else {
                FontUtil.drawString(this.setstrg, this.x + this.width - FontUtil.getStringWidth(this.setstrg), this.y + FontUtil.getFontHeight() / 2 - 1.5, -1);
            }
            Gui.drawRect((int)(this.x + 1.0), (int)(this.y + 1.0), (int)(this.x + 11.0), (int)(this.y + 11.0), ((boolean)this.set.getValue()) ? lllIlIIIIlIlIll : ColorUtils.changeAlpha(-16777216, 150));
            if (this.isCheckHovered(lllIlIIIIlIIIll, lllIlIIIIlIIIlI)) {
                Gui.drawRect((int)(this.x + 1.0), (int)(this.y + 1.0), (int)(this.x + 11.0), (int)(this.y + 11.0), ColorUtils.changeAlpha(1427181841, 30));
            }
        }
        else if (NewGui.toggleSetting.getValue().equalsIgnoreCase("Full-box")) {
            Color lllIlIIIIlIlIlI = ColorUtil.getClickGUIColor().darker();
            if (NewGui.rainbowgui.getValue()) {
                lllIlIIIIlIlIlI = new Color(Xulu.rgb).darker();
            }
            final int lllIlIIIIlIlIIl = new Color(lllIlIIIIlIlIlI.getRed(), lllIlIIIIlIlIlI.getGreen(), lllIlIIIIlIlIlI.getBlue(), 200).getRGB();
            Gui.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), ((boolean)this.set.getValue()) ? ColorUtils.changeAlpha(lllIlIIIIlIlIIl, 225) : ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 60));
            if (NewGui.customfont.getValue()) {
                Xulu.cFontRenderer.drawStringWithShadow(this.setstrg, (float)(this.x + this.width / 2.0 - Xulu.cFontRenderer.getStringWidth(this.setstrg) / 2), (float)(this.y + Xulu.cFontRenderer.getHeight() / 2.0f - 1.5), -1);
            }
            else {
                FontUtil.drawStringWithShadow(this.setstrg, this.x + this.width / 2.0 - FontUtil.getStringWidth(this.setstrg) / 2, this.y + FontUtil.getFontHeight() / 2 - 1.5, -1);
            }
            Gui.drawRect((int)this.x, (int)(this.y + this.height - 1.0), (int)(this.x + this.width), (int)(this.y + this.height), ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 30));
        }
    }
    
    @Override
    public boolean mouseClicked(final int lllIlIIIIIllIlI, final int lllIlIIIIIlIlIl, final int lllIlIIIIIllIII) {
        if (lllIlIIIIIllIII == 0) {
            if (NewGui.toggleSetting.getValue().equalsIgnoreCase("Checkbox")) {
                if (!this.isCheckHovered(lllIlIIIIIllIlI, lllIlIIIIIlIlIl)) {
                    return super.mouseClicked(lllIlIIIIIllIlI, lllIlIIIIIlIlIl, lllIlIIIIIllIII);
                }
            }
            else if (!this.isHovered(lllIlIIIIIllIlI, lllIlIIIIIlIlIl)) {
                return super.mouseClicked(lllIlIIIIIllIlI, lllIlIIIIIlIlIl, lllIlIIIIIllIII);
            }
            this.set.setValue(!this.set.getValue());
            return true;
        }
        return super.mouseClicked(lllIlIIIIIllIlI, lllIlIIIIIlIlIl, lllIlIIIIIllIII);
    }
}
