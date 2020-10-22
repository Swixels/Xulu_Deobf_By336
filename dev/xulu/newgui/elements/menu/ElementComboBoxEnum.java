package dev.xulu.newgui.elements.menu;

import dev.xulu.newgui.elements.*;
import dev.xulu.settings.*;
import java.awt.*;
import com.elementars.eclient.util.*;
import net.minecraft.client.gui.*;
import com.elementars.eclient.module.render.*;
import com.elementars.eclient.*;
import dev.xulu.newgui.util.*;

public class ElementComboBoxEnum extends Element
{
    public ElementComboBoxEnum(final ModuleButton lllllllllllllllllIlIllIIIllIIlII, final Value lllllllllllllllllIlIllIIIllIIIll) {
        this.parent = lllllllllllllllllIlIllIIIllIIlII;
        this.set = lllllllllllllllllIlIllIIIllIIIll;
        super.setup();
    }
    
    @Override
    public boolean mouseClicked(final int lllllllllllllllllIlIllIIIlIIIIIl, final int lllllllllllllllllIlIllIIIlIIIIII, final int lllllllllllllllllIlIllIIIIllllll) {
        final String lllllllllllllllllIlIllIIIIlllllI = (this.set.getValue() instanceof String) ? this.set.getValue() : this.set.getValue().toString();
        if (lllllllllllllllllIlIllIIIIllllll == 0 && this.isButtonHovered(lllllllllllllllllIlIllIIIlIIIIIl, lllllllllllllllllIlIllIIIlIIIIII)) {
            try {
                if (!this.set.getCorrectOption(lllllllllllllllllIlIllIIIIlllllI).toString().equalsIgnoreCase(this.set.getOptions().get(this.set.getOptions().size() - 1).toString())) {
                    this.set.setEnumValue(this.set.getOptions().get(this.set.getOptions().indexOf(this.set.getCorrectOption(lllllllllllllllllIlIllIIIIlllllI)) + 1).toString());
                }
                else {
                    this.set.setEnumValue(this.set.getOptions().get(0).toString());
                }
            }
            catch (Exception lllllllllllllllllIlIllIIIlIIIlII) {
                System.err.println("Error with invalid combo");
                lllllllllllllllllIlIllIIIlIIIlII.printStackTrace();
                this.set.setEnumValue(this.set.getOptions().get(0).toString());
            }
            return true;
        }
        if (lllllllllllllllllIlIllIIIIllllll == 1 && this.isButtonHovered(lllllllllllllllllIlIllIIIlIIIIIl, lllllllllllllllllIlIllIIIlIIIIII)) {
            try {
                if (this.set.getOptions().listIterator(this.set.getOptions().indexOf(this.set.getCorrectOption(lllllllllllllllllIlIllIIIIlllllI))).hasPrevious()) {
                    this.set.setEnumValue(this.set.getOptions().listIterator(this.set.getOptions().indexOf(this.set.getCorrectOption(lllllllllllllllllIlIllIIIIlllllI))).previous().toString());
                }
                else {
                    this.set.setEnumValue(this.set.getOptions().get(this.set.getOptions().size() - 1).toString());
                }
            }
            catch (Exception lllllllllllllllllIlIllIIIlIIIIll) {
                System.err.println("Error with invalid combo");
                lllllllllllllllllIlIllIIIlIIIIll.printStackTrace();
                this.set.setEnumValue(this.set.getOptions().get(0).toString());
            }
            return true;
        }
        return super.mouseClicked(lllllllllllllllllIlIllIIIlIIIIIl, lllllllllllllllllIlIllIIIlIIIIII, lllllllllllllllllIlIllIIIIllllll);
    }
    
    public boolean isButtonHovered(final int lllllllllllllllllIlIllIIIIllIIll, final int lllllllllllllllllIlIllIIIIllIIlI) {
        return lllllllllllllllllIlIllIIIIllIIll >= this.x && lllllllllllllllllIlIllIIIIllIIll <= this.x + this.width && lllllllllllllllllIlIllIIIIllIIlI >= this.y && lllllllllllllllllIlIllIIIIllIIlI <= this.y + this.height;
    }
    
    @Override
    public void drawScreen(final int lllllllllllllllllIlIllIIIlIllIII, final int lllllllllllllllllIlIllIIIlIlIlll, final float lllllllllllllllllIlIllIIIlIlIllI) {
        final Color lllllllllllllllllIlIllIIIlIlIlIl = ColorUtil.getClickGUIColor();
        final int lllllllllllllllllIlIllIIIlIlIlII = new Color(lllllllllllllllllIlIllIIIlIlIlIl.getRed(), lllllllllllllllllIlIllIIIlIlIlIl.getGreen(), lllllllllllllllllIlIllIIIlIlIlIl.getBlue(), 150).getRGB();
        Gui.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 60));
        if (NewGui.customfont.getValue()) {
            Xulu.cFontRenderer.drawStringWithShadow(this.set.getName(), (float)(this.x + 2.0), (float)this.y + 2.0f, -1);
        }
        else {
            FontUtil.drawStringWithShadow(this.set.getName(), this.x + 2.0, this.y + 2.0, -1);
        }
        final int lllllllllllllllllIlIllIIIlIlIIll = lllllllllllllllllIlIllIIIlIlIlII;
        final int lllllllllllllllllIlIllIIIlIlIIlI = lllllllllllllllllIlIllIIIlIlIlIl.getRGB();
        Gui.drawRect((int)this.x, (int)(this.y + 14.0), (int)(this.x + this.width), (int)(this.y + 15.0), ColorUtils.changeAlpha(1996488704, 30));
        final String lllllllllllllllllIlIllIIIlIlIIIl = Xulu.getTitle(this.set.getValue().toString());
        if (NewGui.customfont.getValue()) {
            Xulu.cFontRenderer.drawStringWithShadow(lllllllllllllllllIlIllIIIlIlIIIl, (float)(this.x + 8.0 + Xulu.cFontRenderer.getStringWidth(this.set.getName())), (float)this.y + 2.0f, new Color(-1).darker().darker().getRGB());
        }
        else {
            FontUtil.drawStringWithShadow(lllllllllllllllllIlIllIIIlIlIIIl, this.x + 8.0 + FontUtil.getStringWidth(this.set.getName()), this.y + 2.0, new Color(-1).darker().darker().getRGB());
        }
    }
}
