package dev.xulu.newgui.elements.menu;

import dev.xulu.newgui.elements.*;
import java.awt.*;
import com.elementars.eclient.util.*;
import net.minecraft.client.gui.*;
import com.elementars.eclient.module.render.*;
import com.elementars.eclient.*;
import dev.xulu.newgui.util.*;
import dev.xulu.settings.*;
import org.lwjgl.input.*;
import java.io.*;

public class ElementKeyBind extends Element
{
    private /* synthetic */ boolean listening;
    
    public ElementKeyBind(final ModuleButton llllllllllllllllIllllIlllIlIlIlI, final Value llllllllllllllllIllllIlllIlIIllI) {
        this.parent = llllllllllllllllIllllIlllIlIlIlI;
        this.set = llllllllllllllllIllllIlllIlIIllI;
        super.setup();
    }
    
    @Override
    public void drawScreen(final int llllllllllllllllIllllIlllIIllllI, final int llllllllllllllllIllllIlllIIlllIl, final float llllllllllllllllIllllIlllIIlllII) {
        final Color llllllllllllllllIllllIlllIIllIll = ColorUtil.getClickGUIColor();
        final int llllllllllllllllIllllIlllIIllIlI = new Color(llllllllllllllllIllllIlllIIllIll.getRed(), llllllllllllllllIllllIlllIIllIll.getGreen(), llllllllllllllllIllllIlllIIllIll.getBlue(), 150).getRGB();
        Gui.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 60));
        if (NewGui.customfont.getValue()) {
            Xulu.cFontRenderer.drawStringWithShadow(this.setstrg, (float)(this.x + 2.0), (float)this.y + 2.0f, -1);
        }
        else {
            FontUtil.drawStringWithShadow(this.setstrg, this.x + 2.0, this.y + 2.0, -1);
        }
        final int llllllllllllllllIllllIlllIIllIIl = llllllllllllllllIllllIlllIIllIlI;
        final int llllllllllllllllIllllIlllIIllIII = llllllllllllllllIllllIlllIIllIll.getRGB();
        Gui.drawRect((int)this.x, (int)(this.y + this.height - 1.0), (int)(this.x + this.width), (int)(this.y + this.height), ColorUtils.changeAlpha(1996488704, 30));
        final String llllllllllllllllIllllIlllIIlIlll = this.listening ? "..." : Keyboard.getKeyName(this.set.getValue().getNum());
        if (NewGui.customfont.getValue()) {
            Xulu.cFontRenderer.drawStringWithShadow(llllllllllllllllIllllIlllIIlIlll, (float)(this.x + 8.0 + Xulu.cFontRenderer.getStringWidth(this.setstrg)), (float)this.y + 2.0f, new Color(-1).darker().darker().getRGB());
        }
        else {
            FontUtil.drawStringWithShadow(llllllllllllllllIllllIlllIIlIlll, this.x + 8.0 + FontUtil.getStringWidth(this.setstrg), this.y + 2.0, new Color(-1).darker().darker().getRGB());
        }
    }
    
    public boolean isButtonHovered(final int llllllllllllllllIllllIllIlllIIIl, final int llllllllllllllllIllllIllIlllIIll) {
        return llllllllllllllllIllllIllIlllIIIl >= this.x && llllllllllllllllIllllIllIlllIIIl <= this.x + this.width && llllllllllllllllIllllIllIlllIIll >= this.y && llllllllllllllllIllllIllIlllIIll <= this.y + this.height;
    }
    
    @Override
    public boolean keyTyped(final char llllllllllllllllIllllIllIlllllIl, final int llllllllllllllllIllllIllIlllllII) throws IOException {
        if (this.listening) {
            if (llllllllllllllllIllllIllIlllllII != 1) {
                this.parent.mod.setKey(llllllllllllllllIllllIllIlllllII);
            }
            else {
                this.parent.mod.setKey(0);
            }
            this.listening = false;
            return true;
        }
        return super.keyTyped(llllllllllllllllIllllIllIlllllIl, llllllllllllllllIllllIllIlllllII);
    }
    
    @Override
    public boolean mouseClicked(final int llllllllllllllllIllllIlllIIIIlll, final int llllllllllllllllIllllIlllIIIIllI, final int llllllllllllllllIllllIlllIIIIlIl) {
        if (this.isButtonHovered(llllllllllllllllIllllIlllIIIIlll, llllllllllllllllIllllIlllIIIIllI)) {
            if (llllllllllllllllIllllIlllIIIIlIl == 0) {
                this.listening = true;
            }
            return true;
        }
        return false;
    }
}
