package dev.xulu.newgui.elements.menu;

import net.minecraft.util.*;
import java.awt.*;
import com.elementars.eclient.util.*;
import net.minecraft.client.gui.*;
import com.elementars.eclient.module.render.*;
import com.elementars.eclient.*;
import dev.xulu.newgui.util.*;
import dev.xulu.newgui.elements.*;
import dev.xulu.settings.*;

public class ElementTextBox extends Element
{
    private /* synthetic */ Timer timer;
    private /* synthetic */ boolean listening;
    private /* synthetic */ boolean showCursor;
    private /* synthetic */ CurrentString currentString;
    
    @Override
    public boolean mouseClicked(final int llIIlIIlIIIII, final int llIIlIIIllIll, final int llIIlIIIllllI) {
        if (this.isButtonHovered(llIIlIIlIIIII, llIIlIIIllIll)) {
            if (llIIlIIIllllI == 0) {
                this.currentString = new CurrentString(this.set.getValue().getText());
                this.listening = true;
            }
            return true;
        }
        return false;
    }
    
    public void setString(final String llIIlIIIIIlII) {
        this.currentString = new CurrentString(llIIlIIIIIlII);
    }
    
    @Override
    public boolean keyTyped(final char llIIlIIIlIIlI, final int llIIlIIIlIIIl) {
        if (!this.listening) {
            return false;
        }
        switch (llIIlIIIlIIIl) {
            case 1: {
                return true;
            }
            case 28: {
                this.enterString();
                return true;
            }
            case 14: {
                this.setString(removeLastChar(this.currentString.getString()));
                return true;
            }
            default: {
                if (ChatAllowedCharacters.isAllowedCharacter(llIIlIIIlIIlI)) {
                    this.setString(String.valueOf(new StringBuilder().append(this.currentString.getString()).append(llIIlIIIlIIlI)));
                    return true;
                }
                return false;
            }
        }
    }
    
    public boolean isButtonHovered(final int llIIIlllllIlI, final int llIIIllllllII) {
        return llIIIlllllIlI >= this.x && llIIIlllllIlI <= this.x + this.width && llIIIllllllII >= this.y && llIIIllllllII <= this.y + this.height;
    }
    
    @Override
    public void drawScreen(final int llIIlIIlIllll, final int llIIlIIlIlllI, final float llIIlIIlIllIl) {
        final Color llIIlIIlIllII = ColorUtil.getClickGUIColor();
        final int llIIlIIlIlIll = new Color(llIIlIIlIllII.getRed(), llIIlIIlIllII.getGreen(), llIIlIIlIllII.getBlue(), 150).getRGB();
        Gui.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 60));
        Gui.drawRect((int)this.x, (int)(this.y + this.height - 1.0), (int)(this.x + this.width), (int)(this.y + this.height), ColorUtils.changeAlpha(1996488704, 30));
        final String llIIlIIlIlIlI = this.currentString.getString();
        if (this.listening) {
            if (this.timer.hasReached(500L)) {
                this.showCursor = !this.showCursor;
                this.timer.reset();
            }
            if (this.showCursor) {
                Gui.drawRect((int)this.x + (NewGui.customfont.getValue() ? Xulu.cFontRenderer.getStringWidth(llIIlIIlIlIlI) : FontUtil.getStringWidth(llIIlIIlIlIlI)) + 2, (int)this.y, (int)this.x + (NewGui.customfont.getValue() ? Xulu.cFontRenderer.getStringWidth(llIIlIIlIlIlI) : FontUtil.getStringWidth(llIIlIIlIlIlI)) + 3, (int)this.y + FontUtil.getFontHeight() + 2, -1);
            }
        }
        else {
            if (!llIIlIIlIlIlI.equals(this.set.getValue().getText())) {
                this.currentString = new CurrentString(this.set.getValue().getText());
            }
            this.showCursor = false;
        }
        if (NewGui.customfont.getValue()) {
            Xulu.cFontRenderer.drawStringWithShadow(llIIlIIlIlIlI, (float)(this.x + 2.0), (float)this.y + 2.0f, -1);
        }
        else {
            FontUtil.drawStringWithShadow(llIIlIIlIlIlI, this.x + 2.0, this.y + 2.0, -1);
        }
    }
    
    private void enterString() {
        this.set.setValue(new TextBox(this.currentString.getString()));
        this.setString(this.set.getValue().getText());
        this.listening = false;
    }
    
    public ElementTextBox(final ModuleButton llIIlIIlllIIl, final Value llIIlIIllIlIl) {
        this.timer = new Timer();
        this.currentString = new CurrentString("");
        this.parent = llIIlIIlllIIl;
        this.set = llIIlIIllIlIl;
        this.currentString = new CurrentString(llIIlIIllIlIl.getValue().getText());
        super.setup();
    }
    
    public static String removeLastChar(final String llIIlIIIIllII) {
        String llIIlIIIIllIl = "";
        if (llIIlIIIIllII != null && llIIlIIIIllII.length() > 0) {
            llIIlIIIIllIl = llIIlIIIIllII.substring(0, llIIlIIIIllII.length() - 1);
        }
        return llIIlIIIIllIl;
    }
    
    public static class CurrentString
    {
        private /* synthetic */ String string;
        
        public String getString() {
            return this.string;
        }
        
        public CurrentString(final String lllllllllllllllllIlIllIlIlllIlll) {
            this.string = lllllllllllllllllIlIllIlIlllIlll;
        }
    }
}
