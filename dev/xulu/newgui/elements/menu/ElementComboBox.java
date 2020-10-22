package dev.xulu.newgui.elements.menu;

import dev.xulu.newgui.elements.*;
import dev.xulu.settings.*;
import java.awt.*;
import com.elementars.eclient.util.*;
import net.minecraft.client.gui.*;
import com.elementars.eclient.module.render.*;
import com.elementars.eclient.*;
import dev.xulu.newgui.util.*;

public class ElementComboBox extends Element
{
    public ElementComboBox(final ModuleButton llllllllllllllllIllIIlIIllIllIII, final Value llllllllllllllllIllIIlIIllIlIlll) {
        this.parent = llllllllllllllllIllIIlIIllIllIII;
        this.set = llllllllllllllllIllIIlIIllIlIlll;
        super.setup();
    }
    
    @Override
    public boolean mouseClicked(final int llllllllllllllllIllIIlIIlIllIIll, final int llllllllllllllllIllIIlIIlIllIIlI, final int llllllllllllllllIllIIlIIlIllIllI) {
        final String llllllllllllllllIllIIlIIlIllIlIl = (this.set.getValue() instanceof String) ? this.set.getValue() : this.set.getValue().toString();
        if (llllllllllllllllIllIIlIIlIllIllI == 0 && this.isButtonHovered(llllllllllllllllIllIIlIIlIllIIll, llllllllllllllllIllIIlIIlIllIIlI)) {
            try {
                if (!this.set.getCorrectString(llllllllllllllllIllIIlIIlIllIlIl).equalsIgnoreCase(this.set.getOptions().get(this.set.getOptions().size() - 1).toString())) {
                    this.set.setValue(this.set.getOptions().get(this.set.getOptions().indexOf(this.set.getCorrectString(llllllllllllllllIllIIlIIlIllIlIl)) + 1));
                }
                else {
                    this.set.setValue(this.set.getOptions().get(0));
                }
            }
            catch (Exception llllllllllllllllIllIIlIIlIlllIll) {
                System.err.println("Error with invalid combo");
                llllllllllllllllIllIIlIIlIlllIll.printStackTrace();
                this.set.setValue(this.set.getOptions().get(0));
            }
            return true;
        }
        if (llllllllllllllllIllIIlIIlIllIllI == 1 && this.isButtonHovered(llllllllllllllllIllIIlIIlIllIIll, llllllllllllllllIllIIlIIlIllIIlI)) {
            try {
                if (this.set.getOptions().listIterator(this.set.getOptions().indexOf(this.set.getCorrectString(llllllllllllllllIllIIlIIlIllIlIl))).hasPrevious()) {
                    this.set.setValue(this.set.getOptions().listIterator(this.set.getOptions().indexOf(this.set.getCorrectString(llllllllllllllllIllIIlIIlIllIlIl))).previous());
                }
                else {
                    this.set.setValue(this.set.getOptions().get(this.set.getOptions().size() - 1));
                }
            }
            catch (Exception llllllllllllllllIllIIlIIlIlllIlI) {
                System.err.println("Error with invalid combo");
                llllllllllllllllIllIIlIIlIlllIlI.printStackTrace();
                this.set.setValue(this.set.getOptions().get(0));
            }
            return true;
        }
        return super.mouseClicked(llllllllllllllllIllIIlIIlIllIIll, llllllllllllllllIllIIlIIlIllIIlI, llllllllllllllllIllIIlIIlIllIllI);
    }
    
    @Override
    public void drawScreen(final int llllllllllllllllIllIIlIIllIIllll, final int llllllllllllllllIllIIlIIllIIlllI, final float llllllllllllllllIllIIlIIllIIllIl) {
        final Color llllllllllllllllIllIIlIIllIIllII = ColorUtil.getClickGUIColor();
        final int llllllllllllllllIllIIlIIllIIlIll = new Color(llllllllllllllllIllIIlIIllIIllII.getRed(), llllllllllllllllIllIIlIIllIIllII.getGreen(), llllllllllllllllIllIIlIIllIIllII.getBlue(), 150).getRGB();
        Gui.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 60));
        if (NewGui.customfont.getValue()) {
            Xulu.cFontRenderer.drawStringWithShadow(this.setstrg, (float)(this.x + 2.0), (float)this.y + 2.0f, -1);
        }
        else {
            FontUtil.drawStringWithShadow(this.setstrg, this.x + 2.0, this.y + 2.0, -1);
        }
        final int llllllllllllllllIllIIlIIllIIlIlI = llllllllllllllllIllIIlIIllIIlIll;
        final int llllllllllllllllIllIIlIIllIIlIIl = llllllllllllllllIllIIlIIllIIllII.getRGB();
        Gui.drawRect((int)this.x, (int)(this.y + this.height - 1.0), (int)(this.x + this.width), (int)(this.y + this.height), ColorUtils.changeAlpha(1996488704, 30));
        final String llllllllllllllllIllIIlIIllIIlIII = (this.set.getValue() instanceof String) ? this.set.getValue() : this.set.getValue().toString();
        if (NewGui.customfont.getValue()) {
            Xulu.cFontRenderer.drawStringWithShadow(llllllllllllllllIllIIlIIllIIlIII, (float)(this.x + 8.0 + Xulu.cFontRenderer.getStringWidth(this.setstrg)), (float)this.y + 2.0f, new Color(-1).darker().darker().getRGB());
        }
        else {
            FontUtil.drawStringWithShadow(llllllllllllllllIllIIlIIllIIlIII, this.x + 8.0 + FontUtil.getStringWidth(this.setstrg), this.y + 2.0, new Color(-1).darker().darker().getRGB());
        }
    }
    
    public boolean isButtonHovered(final int llllllllllllllllIllIIlIIlIlIIlll, final int llllllllllllllllIllIIlIIlIlIlIIl) {
        return llllllllllllllllIllIIlIIlIlIIlll >= this.x && llllllllllllllllIllIIlIIlIlIIlll <= this.x + this.width && llllllllllllllllIllIIlIIlIlIlIIl >= this.y && llllllllllllllllIllIIlIIlIlIlIIl <= this.y + this.height;
    }
}
