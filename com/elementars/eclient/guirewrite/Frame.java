package com.elementars.eclient.guirewrite;

import com.elementars.eclient.module.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.*;
import java.awt.*;
import net.minecraft.client.gui.*;

public class Frame
{
    public /* synthetic */ double y;
    public /* synthetic */ String title;
    private /* synthetic */ double y2;
    public /* synthetic */ boolean dragging;
    public /* synthetic */ double height;
    private /* synthetic */ double x2;
    public /* synthetic */ double x;
    public /* synthetic */ boolean pinned;
    public /* synthetic */ HUD hud;
    public /* synthetic */ boolean visible;
    public /* synthetic */ double width;
    
    public void setup() {
    }
    
    public void drawScreen(final int lIIIlllIlIIIIIl, final int lIIIlllIlIIIIII, final float lIIIlllIlIIIIll) {
        this.visible = ModuleManager.isModuleEnabled(this.title);
        if (!this.visible) {
            return;
        }
        if (this.dragging) {
            this.x = this.x2 + lIIIlllIlIIIIIl;
            this.y = this.y2 + lIIIlllIlIIIIII;
            final ScaledResolution lIIIlllIlIIIlll = new ScaledResolution(Wrapper.getMinecraft());
            if (this.x < 0.0) {
                this.x = 0.0;
            }
            if (this.y < 0.0) {
                this.y = 0.0;
            }
            if (this.x > lIIIlllIlIIIlll.getScaledWidth() - this.width) {
                this.x = lIIIlllIlIIIlll.getScaledWidth() - this.width;
            }
            if (this.y > lIIIlllIlIIIlll.getScaledHeight() - this.height) {
                this.y = lIIIlllIlIIIlll.getScaledHeight() - this.height;
            }
        }
        if (Xulu.MODULE_MANAGER.getModuleByName(this.title) != null) {
            ((Element)Xulu.MODULE_MANAGER.getModuleByName(this.title)).x = this.x;
            ((Element)Xulu.MODULE_MANAGER.getModuleByName(this.title)).y = this.y;
        }
        if (this.dragging) {
            Gui.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), this.changeAlpha(Color.lightGray.getRGB(), 100));
            Gui.drawRect((int)(this.x + 4.0), (int)(this.y + 2.0), (int)(this.x + 4.3), (int)(this.y + this.height - 2.0), this.changeAlpha(Color.lightGray.getRGB(), 100));
        }
        else {
            Gui.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), this.changeAlpha(-15592942, 100));
            Gui.drawRect((int)(this.x + 4.0), (int)(this.y + 2.0), (int)(this.x + 4.3), (int)(this.y + this.height - 2.0), this.changeAlpha(-5592406, 100));
        }
        if (Xulu.MODULE_MANAGER.getModuleByName(this.title) != null) {
            Xulu.MODULE_MANAGER.getModuleByName(this.title).onRender();
        }
    }
    
    public boolean mouseClicked(final int lIIIlllIIllIlIl, final int lIIIlllIIlllIII, final int lIIIlllIIllIlll) {
        if (!this.visible) {
            return false;
        }
        if (lIIIlllIIllIlll == 0 && this.isHovered(lIIIlllIIllIlIl, lIIIlllIIlllIII)) {
            this.x2 = this.x - lIIIlllIIllIlIl;
            this.y2 = this.y - lIIIlllIIlllIII;
            this.dragging = true;
            return true;
        }
        if (lIIIlllIIllIlll == 2 && this.isHovered(lIIIlllIIllIlIl, lIIIlllIIlllIII)) {
            ((Element)Xulu.MODULE_MANAGER.getModuleByName(this.title)).onMiddleClick();
        }
        return false;
    }
    
    public boolean isHovered(final int lIIIlllIIlIIIll, final int lIIIlllIIlIIIlI) {
        return lIIIlllIIlIIIll >= this.x && lIIIlllIIlIIIll <= this.x + this.width && lIIIlllIIlIIIlI >= this.y && lIIIlllIIlIIIlI <= this.y + this.height;
    }
    
    int changeAlpha(int lIIIlllIlIIllll, final int lIIIlllIlIIllII) {
        lIIIlllIlIIllll &= 0xFFFFFF;
        return lIIIlllIlIIllII << 24 | lIIIlllIlIIllll;
    }
    
    public Frame(final String lIIIlllIlIllIlI, final double lIIIlllIllIIIIl, final double lIIIlllIllIIIII, final double lIIIlllIlIlIlll, final double lIIIlllIlIlIllI, final boolean lIIIlllIlIlllIl, final HUD lIIIlllIlIlIlII) {
        this.title = lIIIlllIlIllIlI;
        this.x = lIIIlllIllIIIIl;
        this.y = lIIIlllIllIIIII;
        this.width = lIIIlllIlIlIlll;
        this.height = lIIIlllIlIlIllI;
        this.pinned = lIIIlllIlIlllIl;
        this.dragging = false;
        this.visible = true;
        this.hud = lIIIlllIlIlIlII;
        this.setup();
    }
    
    public void mouseReleased(final int lIIIlllIIlIllll, final int lIIIlllIIlIlllI, final int lIIIlllIIlIlIll) {
        if (!this.visible) {
            return;
        }
        if (lIIIlllIIlIlIll == 0) {
            this.dragging = false;
        }
    }
}
