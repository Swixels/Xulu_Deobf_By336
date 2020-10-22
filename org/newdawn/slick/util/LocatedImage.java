package org.newdawn.slick.util;

import org.newdawn.slick.*;

public class LocatedImage
{
    private /* synthetic */ float width;
    private /* synthetic */ Image image;
    private /* synthetic */ int y;
    private /* synthetic */ float height;
    private /* synthetic */ int x;
    private /* synthetic */ Color filter;
    
    public Color getColor() {
        return this.filter;
    }
    
    public void setWidth(final float llllllllllllllllllllIIIlIlIllIll) {
        this.width = llllllllllllllllllllIIIlIlIllIll;
    }
    
    public void setColor(final Color llllllllllllllllllllIIIlIlIlIlIl) {
        this.filter = llllllllllllllllllllIIIlIlIlIlIl;
    }
    
    public float getWidth() {
        return this.width;
    }
    
    public void setHeight(final float llllllllllllllllllllIIIlIllIIIIl) {
        this.height = llllllllllllllllllllIIIlIllIIIIl;
    }
    
    public void setX(final int llllllllllllllllllllIIIlIlIIlIlI) {
        this.x = llllllllllllllllllllIIIlIlIIlIlI;
    }
    
    public void setY(final int llllllllllllllllllllIIIlIlIIIllI) {
        this.y = llllllllllllllllllllIIIlIlIIIllI;
    }
    
    public LocatedImage(final Image llllllllllllllllllllIIIlIlllIIIl, final int llllllllllllllllllllIIIlIlllIIII, final int llllllllllllllllllllIIIlIllIllll) {
        this.filter = Color.white;
        this.image = llllllllllllllllllllIIIlIlllIIIl;
        this.x = llllllllllllllllllllIIIlIlllIIII;
        this.y = llllllllllllllllllllIIIlIllIllll;
        this.width = (float)llllllllllllllllllllIIIlIlllIIIl.getWidth();
        this.height = (float)llllllllllllllllllllIIIlIlllIIIl.getHeight();
    }
    
    public float getHeight() {
        return this.height;
    }
    
    public void draw() {
        this.image.draw((float)this.x, (float)this.y, this.width, this.height, this.filter);
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
}
