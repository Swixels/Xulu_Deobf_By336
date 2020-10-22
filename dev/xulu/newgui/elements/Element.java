package dev.xulu.newgui.elements;

import dev.xulu.settings.*;
import dev.xulu.newgui.*;
import dev.xulu.newgui.util.*;
import java.io.*;

public class Element
{
    public /* synthetic */ double y;
    public /* synthetic */ double x;
    public /* synthetic */ Value set;
    public /* synthetic */ NewGUI clickgui;
    public /* synthetic */ double offset;
    public /* synthetic */ String setstrg;
    public /* synthetic */ double height;
    public /* synthetic */ ModuleButton parent;
    public /* synthetic */ double width;
    
    public void mouseReleased(final int lllIllIIlIIlIlI, final int lllIllIIlIIlIIl, final int lllIllIIlIIlIII) {
    }
    
    public boolean mouseClicked(final int lllIllIIlIIllIl, final int lllIllIIlIlIIII, final int lllIllIIlIIllll) {
        return this.isHovered(lllIllIIlIIllIl, lllIllIIlIlIIII);
    }
    
    public void update() {
        this.x = this.parent.x - 2.0;
        this.y = this.parent.y + this.offset;
        this.width = this.parent.width + 4.0;
        this.height = this.parent.height + 1.0;
        final String lllIllIIlIllllI = this.set.getName();
        if (this.set.isToggle()) {
            this.setstrg = String.valueOf(new StringBuilder().append(lllIllIIlIllllI.substring(0, 1).toUpperCase()).append(lllIllIIlIllllI.substring(1, lllIllIIlIllllI.length())));
            final double lllIllIIllIIlll = this.x + this.width - FontUtil.getStringWidth(this.setstrg);
            if (lllIllIIllIIlll < this.x + 13.0) {
                this.width += this.x + 13.0 - lllIllIIllIIlll + 1.0;
            }
        }
        else if (this.set.isMode()) {
            this.setstrg = String.valueOf(new StringBuilder().append(lllIllIIlIllllI.substring(0, 1).toUpperCase()).append(lllIllIIlIllllI.substring(1, lllIllIIlIllllI.length())));
        }
        else if (this.set.isNumber()) {
            this.setstrg = String.valueOf(new StringBuilder().append(lllIllIIlIllllI.substring(0, 1).toUpperCase()).append(lllIllIIlIllllI.substring(1, lllIllIIlIllllI.length())));
            String lllIllIIllIIIIl = null;
            if (this.set.getValue() instanceof Integer) {
                final String lllIllIIllIIllI = String.valueOf(new StringBuilder().append("").append(Math.round(this.set.getMax() * 100.0) / 100.0));
            }
            else if (this.set.getValue() instanceof Short) {
                final String lllIllIIllIIlIl = String.valueOf(new StringBuilder().append("").append(Math.round(this.set.getMax() * 100.0) / 100.0));
            }
            else if (this.set.getValue() instanceof Long) {
                final String lllIllIIllIIlII = String.valueOf(new StringBuilder().append("").append(Math.round(this.set.getMax() * 100.0) / 100.0));
            }
            else if (this.set.getValue() instanceof Float) {
                final String lllIllIIllIIIll = String.valueOf(new StringBuilder().append("").append(Math.round(this.set.getMax() * 100.0) / 100.0));
            }
            else if (this.set.getValue() instanceof Double) {
                final String lllIllIIllIIIlI = String.valueOf(new StringBuilder().append("").append(Math.round(this.set.getMax() * 100.0) / 100.0));
            }
            else {
                lllIllIIllIIIIl = "";
            }
            final double lllIllIIllIIIII = this.x + this.width - FontUtil.getStringWidth(this.setstrg) - FontUtil.getStringWidth(lllIllIIllIIIIl) - 4.0;
            if (lllIllIIllIIIII < this.x) {
                this.width += this.x - lllIllIIllIIIII + 1.0;
            }
        }
        else if (this.set.isBind()) {
            this.setstrg = String.valueOf(new StringBuilder().append(lllIllIIlIllllI.substring(0, 1).toUpperCase()).append(lllIllIIlIllllI.substring(1, lllIllIIlIllllI.length())));
        }
    }
    
    public boolean keyTyped(final char lllIllIIlIIIllI, final int lllIllIIlIIIlIl) throws IOException {
        return false;
    }
    
    public void drawScreen(final int lllIllIIlIllIII, final int lllIllIIlIlIlll, final float lllIllIIlIlIllI) {
    }
    
    public boolean isHovered(final int lllIllIIIllllIl, final int lllIllIIIllllII) {
        return lllIllIIIllllIl >= this.x && lllIllIIIllllIl <= this.x + this.width && lllIllIIIllllII >= this.y && lllIllIIIllllII <= this.y + this.height;
    }
    
    public void setup() {
        this.clickgui = this.parent.parent.clickgui;
    }
}
