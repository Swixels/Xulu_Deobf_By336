package com.elementars.eclient.guirewrite;

import com.elementars.eclient.module.*;

public class Element extends Module
{
    /* synthetic */ Frame frame;
    protected /* synthetic */ double width;
    protected /* synthetic */ double height;
    protected /* synthetic */ double y;
    protected /* synthetic */ double x;
    
    public Frame getFrame() {
        return this.frame;
    }
    
    public void registerFrame() {
        this.frame = HUD.getframeByName(this.getName());
        this.width = this.frame.width;
        this.height = this.frame.height;
    }
    
    public void setY(final double llIIlIIIllIIlII) {
        this.y = llIIlIIIllIIlII;
    }
    
    public void setX(final double llIIlIIIllIllIl) {
        this.x = llIIlIIIllIllIl;
    }
    
    public void onMiddleClick() {
    }
    
    public Element(final String llIIlIIlIlIlIll) {
        super(llIIlIIlIlIlIll, "NONE", 0, Category.HUD, false);
    }
    
    @Override
    public void onUpdate() {
        if (this.frame != null) {
            if (this.frame.width != this.width) {
                this.frame.width = this.width;
            }
            if (this.frame.height != this.height) {
                this.frame.height = this.height;
            }
        }
        super.onUpdate();
    }
    
    @Override
    public void onDisable() {
        if (this.frame != null && this.frame.pinned) {
            this.frame.pinned = false;
        }
        super.onDisable();
    }
    
    @Override
    public void onEnable() {
        if (this.frame != null && !this.frame.pinned) {
            this.frame.pinned = true;
        }
        super.onEnable();
    }
}
