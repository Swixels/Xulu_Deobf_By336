package dev.xulu.newgui.elements;

import java.util.*;
import dev.xulu.newgui.*;
import java.io.*;
import java.awt.*;
import com.elementars.eclient.module.render.*;
import com.elementars.eclient.util.*;
import net.minecraft.client.gui.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.*;
import dev.xulu.newgui.util.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.client.*;
import dev.xulu.settings.*;
import dev.xulu.newgui.elements.menu.*;

public class ModuleButton
{
    public /* synthetic */ double y;
    public /* synthetic */ ArrayList<Element> menuelements;
    public /* synthetic */ Module mod;
    public /* synthetic */ double x;
    public /* synthetic */ boolean extended;
    public /* synthetic */ double height2;
    public /* synthetic */ Panel parent;
    public /* synthetic */ double width;
    public /* synthetic */ double height;
    public /* synthetic */ boolean listening;
    
    public boolean keyTyped(final char lllllllllllllllllIlllIIIllllIIIl, final int lllllllllllllllllIlllIIIllllIIII) throws IOException {
        for (final Element lllllllllllllllllIlllIIIllllIllI : this.menuelements) {
            if (!lllllllllllllllllIlllIIIllllIllI.set.isVisible()) {
                continue;
            }
            if (lllllllllllllllllIlllIIIllllIllI.keyTyped(lllllllllllllllllIlllIIIllllIIIl, lllllllllllllllllIlllIIIllllIIII)) {
                return true;
            }
        }
        return false;
    }
    
    public void drawScreen(final int lllllllllllllllllIlllIIlIIIlIllI, final int lllllllllllllllllIlllIIlIIIlIlIl, final float lllllllllllllllllIlllIIlIIIlIlII) {
        final Color lllllllllllllllllIlllIIlIIIlIIll = ColorUtil.getClickGUIColor();
        int lllllllllllllllllIlllIIlIIIlIIlI = new Color(lllllllllllllllllIlllIIlIIIlIIll.getRed(), lllllllllllllllllIlllIIlIIIlIIll.getGreen(), lllllllllllllllllIlllIIlIIIlIIll.getBlue(), 200).getRGB();
        if (NewGui.rainbowgui.getValue()) {
            lllllllllllllllllIlllIIlIIIlIIlI = ColorUtils.changeAlpha(this.parent.rgb, 200);
        }
        int lllllllllllllllllIlllIIlIIIlIIIl = -5263441;
        if (this.mod.isToggled() && !NewGui.moduleSetting.getValue().equalsIgnoreCase("Text")) {
            if (NewGui.moduleSetting.getValue().equalsIgnoreCase("MiniButton")) {
                Gui.drawRect((int)this.x, (int)this.y + 1, (int)(this.x + this.width), (int)(this.y + this.height), ColorUtils.changeAlpha(lllllllllllllllllIlllIIlIIIlIIlI, 100));
                lllllllllllllllllIlllIIlIIIlIIIl = -1052689;
            }
            else {
                Gui.drawRect((int)this.x - 2, (int)this.y, (int)(this.x + this.width + 2.0), (int)(this.y + this.height + 1.0), lllllllllllllllllIlllIIlIIIlIIlI);
                lllllllllllllllllIlllIIlIIIlIIIl = -1052689;
            }
        }
        if (this.isHovered(lllllllllllllllllIlllIIlIIIlIllI, lllllllllllllllllIlllIIlIIIlIlIl) && !NewGui.moduleSetting.getValue().equalsIgnoreCase("Text")) {
            if (NewGui.moduleSetting.getValue().equalsIgnoreCase("MiniButton")) {
                Gui.drawRect((int)this.x, (int)this.y + 1, (int)(this.x + this.width), (int)(this.y + this.height), (this.mod.isToggled() && !this.mod.getCategory().equals(Category.HUD)) ? ColorUtils.changeAlpha(1427181841, 30) : ColorUtils.changeAlpha(ColorUtils.Colors.GRAY, 30));
            }
            else {
                Gui.drawRect((int)(this.x - 2.0), (int)this.y, (int)(this.x + this.width + 2.0), (int)(this.y + this.height + 1.0), (this.mod.isToggled() && !this.mod.getCategory().equals(Category.HUD)) ? ColorUtils.changeAlpha(1427181841, 30) : ColorUtils.changeAlpha(ColorUtils.Colors.GRAY, 30));
            }
        }
        if (NewGui.moduleSetting.getValue().equalsIgnoreCase("MiniButton")) {
            if (NewGui.customfont.getValue()) {
                Xulu.cFontRenderer.drawStringWithShadow(this.mod.getName(), (float)(this.x + 2.0), (float)(this.y + 1.0 + this.height2 / 2.0 - 4.0), lllllllllllllllllIlllIIlIIIlIIIl);
                if (Xulu.VALUE_MANAGER.getValuesByMod(this.mod) != null) {
                    Xulu.cFontRenderer.drawStringWithShadow(this.extended ? "." : "...", (float)(this.x + this.width - 10.0), (float)(this.y + 1.0 + this.height2 / 2.0 - 4.0), lllllllllllllllllIlllIIlIIIlIIIl);
                }
            }
            else {
                FontUtil.drawStringWithShadow(this.mod.getName(), this.x + 2.0, this.y + 1.0 + this.height / 2.0 - 4.0, lllllllllllllllllIlllIIlIIIlIIIl);
                if (Xulu.VALUE_MANAGER.getValuesByMod(this.mod) != null) {
                    FontUtil.drawStringWithShadow(this.extended ? "." : "...", this.x + this.width - 7.0, this.y + 1.0 + this.height / 2.0 - 4.0, lllllllllllllllllIlllIIlIIIlIIIl);
                }
            }
        }
        else if (NewGui.moduleSetting.getValue().equalsIgnoreCase("Text")) {
            if (NewGui.customfont.getValue()) {
                Xulu.cFontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(this.isHovered(lllllllllllllllllIlllIIlIIIlIllI, lllllllllllllllllIlllIIlIIIlIlIl) ? ChatFormatting.BOLD : "").append(this.mod.getName())), (float)(this.x + 2.0), (float)(this.y + 1.0 + this.height2 / 2.0 - 4.0), (this.mod.isToggled() && !this.mod.getCategory().equals(Category.HUD)) ? ColorUtil.getClickGUIColor().getRGB() : lllllllllllllllllIlllIIlIIIlIIIl);
                if (Xulu.VALUE_MANAGER.getValuesByMod(this.mod) != null) {
                    Xulu.cFontRenderer.drawStringWithShadow(this.extended ? ">" : "V", (float)(this.x + this.width - 6.0), (float)(this.y + 1.0 + this.height2 / 2.0 - 4.0), lllllllllllllllllIlllIIlIIIlIIIl);
                }
            }
            else {
                FontUtil.drawStringWithShadow(String.valueOf(new StringBuilder().append(this.isHovered(lllllllllllllllllIlllIIlIIIlIllI, lllllllllllllllllIlllIIlIIIlIlIl) ? ChatFormatting.BOLD : "").append(this.mod.getName())), this.x + 2.0, this.y + 1.0 + this.height / 2.0 - 4.0, (this.mod.isToggled() && !this.mod.getCategory().equals(Category.HUD)) ? ColorUtil.getClickGUIColor().getRGB() : lllllllllllllllllIlllIIlIIIlIIIl);
                if (Xulu.VALUE_MANAGER.getValuesByMod(this.mod) != null) {
                    FontUtil.drawStringWithShadow(this.extended ? ">" : "V", this.x + this.width - 5.0, this.y + 1.0 + this.height / 2.0 - 4.0, lllllllllllllllllIlllIIlIIIlIIIl);
                }
            }
        }
        else if (NewGui.customfont.getValue()) {
            Xulu.cFontRenderer.drawStringWithShadow(this.mod.getName(), (float)(this.x + 2.0), (float)(this.y + 1.0 + this.height2 / 2.0 - 4.0), lllllllllllllllllIlllIIlIIIlIIIl);
            if (Xulu.VALUE_MANAGER.getValuesByMod(this.mod) != null) {
                Xulu.cFontRenderer.drawStringWithShadow(this.extended ? ">" : "V", (float)(this.x + this.width - 6.0), (float)(this.y + 1.0 + this.height2 / 2.0 - 4.0), lllllllllllllllllIlllIIlIIIlIIIl);
            }
        }
        else {
            FontUtil.drawStringWithShadow(this.mod.getName(), this.x + 2.0, this.y + 1.0 + this.height / 2.0 - 4.0, lllllllllllllllllIlllIIlIIIlIIIl);
            if (Xulu.VALUE_MANAGER.getValuesByMod(this.mod) != null) {
                FontUtil.drawStringWithShadow(this.extended ? ">" : "V", this.x + this.width - 5.0, this.y + 1.0 + this.height / 2.0 - 4.0, lllllllllllllllllIlllIIlIIIlIIIl);
            }
        }
    }
    
    public boolean mouseClicked(final int lllllllllllllllllIlllIIIllllllll, final int lllllllllllllllllIlllIIIlllllllI, final int lllllllllllllllllIlllIIlIIIIIIIl) {
        if (!this.isHovered(lllllllllllllllllIlllIIIllllllll, lllllllllllllllllIlllIIIlllllllI)) {
            return false;
        }
        if (lllllllllllllllllIlllIIlIIIIIIIl == 0) {
            this.mod.toggle();
        }
        else if (lllllllllllllllllIlllIIlIIIIIIIl == 1 && this.menuelements != null && this.menuelements.size() > 0) {
            final boolean lllllllllllllllllIlllIIlIIIIIlIl = !this.extended;
            Xulu.newGUI.closeAllSettings();
            this.extended = lllllllllllllllllIlllIIlIIIIIlIl;
        }
        return true;
    }
    
    public ModuleButton(final Module lllllllllllllllllIlllIIlIIlIIIIl, final Panel lllllllllllllllllIlllIIlIIlIIIll) {
        this.extended = false;
        this.listening = false;
        this.mod = lllllllllllllllllIlllIIlIIlIIIIl;
        this.height = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 2;
        this.height2 = Xulu.cFontRenderer.getHeight() + 2.0f;
        this.parent = lllllllllllllllllIlllIIlIIlIIIll;
        this.menuelements = new ArrayList<Element>();
        if (Xulu.VALUE_MANAGER.getSettingsByMod(lllllllllllllllllIlllIIlIIlIIIIl) != null) {
            for (final Value lllllllllllllllllIlllIIlIIlIIllI : Xulu.VALUE_MANAGER.getSettingsByMod(lllllllllllllllllIlllIIlIIlIIIIl)) {
                if (lllllllllllllllllIlllIIlIIlIIllI.isToggle()) {
                    this.menuelements.add(new ElementCheckBox(this, lllllllllllllllllIlllIIlIIlIIllI));
                }
                else if (lllllllllllllllllIlllIIlIIlIIllI.isNumber()) {
                    this.menuelements.add(new ElementSlider(this, lllllllllllllllllIlllIIlIIlIIllI));
                }
                else if (lllllllllllllllllIlllIIlIIlIIllI.isMode()) {
                    this.menuelements.add(new ElementComboBox(this, lllllllllllllllllIlllIIlIIlIIllI));
                }
                else if (lllllllllllllllllIlllIIlIIlIIllI.isEnum()) {
                    this.menuelements.add(new ElementComboBoxEnum(this, lllllllllllllllllIlllIIlIIlIIllI));
                }
                else if (lllllllllllllllllIlllIIlIIlIIllI.isBind() && !(this.mod instanceof com.elementars.eclient.guirewrite.Element)) {
                    this.menuelements.add(new ElementKeyBind(this, lllllllllllllllllIlllIIlIIlIIllI));
                }
                else {
                    if (!lllllllllllllllllIlllIIlIIlIIllI.isText()) {
                        continue;
                    }
                    this.menuelements.add(new ElementTextBox(this, lllllllllllllllllIlllIIlIIlIIllI));
                }
            }
        }
    }
    
    public boolean isHovered(final int lllllllllllllllllIlllIIIlllIIllI, final int lllllllllllllllllIlllIIIlllIlIII) {
        return lllllllllllllllllIlllIIIlllIIllI >= this.x && lllllllllllllllllIlllIIIlllIIllI <= this.x + this.width && lllllllllllllllllIlllIIIlllIlIII >= this.y && lllllllllllllllllIlllIIIlllIlIII <= this.y + this.height;
    }
}
