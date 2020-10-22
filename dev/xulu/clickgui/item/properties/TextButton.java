package dev.xulu.clickgui.item.properties;

import dev.xulu.clickgui.item.*;
import dev.xulu.newgui.elements.menu.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.module.render.*;
import com.elementars.eclient.*;
import dev.xulu.newgui.util.*;
import net.minecraft.client.gui.*;
import dev.xulu.settings.*;
import dev.xulu.clickgui.*;
import net.minecraft.util.*;

public class TextButton extends Button
{
    private /* synthetic */ boolean showCursor;
    private /* synthetic */ boolean listening;
    private /* synthetic */ ElementTextBox.CurrentString currentString;
    private /* synthetic */ Timer timer;
    
    @Override
    public void mouseClicked(final int lllllllllllllllllIIIlIlllIllIlll, final int lllllllllllllllllIIIlIlllIllIllI, final int lllllllllllllllllIIIlIlllIllIIIl) {
        super.mouseClicked(lllllllllllllllllIIIlIlllIllIlll, lllllllllllllllllIIIlIlllIllIllI, lllllllllllllllllIIIlIlllIllIIIl);
        if (this.isHovering(lllllllllllllllllIIIlIlllIllIlll, lllllllllllllllllIIIlIlllIllIllI)) {
            if (ExeterGui.getSound()) {
                TextButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            }
            if (lllllllllllllllllIIIlIlllIllIIIl == 0) {
                this.currentString = new ElementTextBox.CurrentString(this.property.getValue().getText());
                this.listening = true;
            }
        }
    }
    
    public void setString(final String lllllllllllllllllIIIlIlllIIllIll) {
        this.currentString = new ElementTextBox.CurrentString(lllllllllllllllllIIIlIlllIIllIll);
    }
    
    @Override
    public void drawScreen(final int lllllllllllllllllIIIlIlllIllllll, final int lllllllllllllllllIIIlIlllIlllllI, final float lllllllllllllllllIIIlIllllIIIIlI) {
        XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height, this.getState() ? ColorUtils.changeAlpha(ColorUtil.getClickGUIColor().getRGB(), 200) : 290805077, -1);
        if (this.isHovering(lllllllllllllllllIIIlIlllIllllll, lllllllllllllllllIIIlIlllIlllllI)) {
            if (this.getState()) {
                XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width, this.y + this.height, ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 25), -1);
            }
            else {
                XuluTessellator.drawRectGradient(this.x, this.y, this.x + this.width, this.y + this.height, ColorUtils.changeAlpha(ColorUtils.Colors.WHITE, 25), -1);
            }
        }
        final String lllllllllllllllllIIIlIllllIIIIIl = this.currentString.getString();
        if (this.listening) {
            if (this.timer.hasReached(500L)) {
                this.showCursor = !this.showCursor;
                this.timer.reset();
            }
            if (this.showCursor) {
                Gui.drawRect((int)this.x + (NewGui.customfont.getValue() ? Xulu.cFontRenderer.getStringWidth(lllllllllllllllllIIIlIllllIIIIIl) : FontUtil.getStringWidth(lllllllllllllllllIIIlIllllIIIIIl)) + 3, (int)this.y + 1, (int)this.x + (NewGui.customfont.getValue() ? Xulu.cFontRenderer.getStringWidth(lllllllllllllllllIIIlIllllIIIIIl) : FontUtil.getStringWidth(lllllllllllllllllIIIlIllllIIIIIl)) + 4, (int)this.y + FontUtil.getFontHeight() + 5, -1);
            }
        }
        else {
            if (!lllllllllllllllllIIIlIllllIIIIIl.equals(this.property.getValue().getText())) {
                this.currentString = new ElementTextBox.CurrentString(this.property.getValue().getText());
            }
            this.showCursor = false;
        }
        if (ExeterGui.getCF()) {
            Xulu.cFontRenderer.drawStringWithShadow(lllllllllllllllllIIIlIllllIIIIIl, this.x + 2.3f, this.y + 3.0f, this.getState() ? -1 : -1);
        }
        else {
            TextButton.fontRenderer.drawStringWithShadow(lllllllllllllllllIIIlIllllIIIIIl, this.x + 2.3f, this.y + 4.0f, this.getState() ? -1 : -1);
        }
    }
    
    private void enterString() {
        this.property.setValue(new TextBox(this.currentString.getString()));
        this.setString(this.property.getValue().getText());
        this.listening = false;
    }
    
    public static String removeLastChar(final String lllllllllllllllllIIIlIlllIlIIIll) {
        String lllllllllllllllllIIIlIlllIlIIlII = "";
        if (lllllllllllllllllIIIlIlllIlIIIll != null && lllllllllllllllllIIIlIlllIlIIIll.length() > 0) {
            lllllllllllllllllIIIlIlllIlIIlII = lllllllllllllllllIIIlIlllIlIIIll.substring(0, lllllllllllllllllIIIlIlllIlIIIll.length() - 1);
        }
        return lllllllllllllllllIIIlIlllIlIIlII;
    }
    
    public TextButton(final Value lllllllllllllllllIIIlIllllIIllII) {
        super(lllllllllllllllllIIIlIllllIIllII.getName(), null);
        this.timer = new Timer();
        this.currentString = new ElementTextBox.CurrentString("");
        this.setValue(lllllllllllllllllIIIlIllllIIllII);
    }
    
    @Override
    public boolean keyTyped(final char lllllllllllllllllIIIlIlllIlIlIIl, final int lllllllllllllllllIIIlIlllIlIlIll) {
        if (!this.listening) {
            return false;
        }
        switch (lllllllllllllllllIIIlIlllIlIlIll) {
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
                if (ChatAllowedCharacters.isAllowedCharacter(lllllllllllllllllIIIlIlllIlIlIIl)) {
                    this.setString(String.valueOf(new StringBuilder().append(this.currentString.getString()).append(lllllllllllllllllIIIlIlllIlIlIIl)));
                    return true;
                }
                return false;
            }
        }
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
    
    @Override
    public boolean getState() {
        return false;
    }
    
    @Override
    public void toggle() {
    }
    
    public static class CurrentString
    {
        private /* synthetic */ String string;
        
        public String getString() {
            return this.string;
        }
        
        public CurrentString(final String lIIllIIlIlllIll) {
            this.string = lIIllIIlIlllIll;
        }
    }
}
