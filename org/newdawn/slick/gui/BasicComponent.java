package org.newdawn.slick.gui;

import org.newdawn.slick.*;

public abstract class BasicComponent extends AbstractComponent
{
    protected /* synthetic */ int width;
    protected /* synthetic */ int height;
    protected /* synthetic */ int x;
    protected /* synthetic */ int y;
    
    @Override
    public void render(final GUIContext llIIlIlIlIlllIl, final Graphics llIIlIlIlIlllII) throws SlickException {
        this.renderImpl(llIIlIlIlIlllIl, llIIlIlIlIlllII);
    }
    
    @Override
    public int getY() {
        return this.y;
    }
    
    public BasicComponent(final GUIContext llIIlIlIlllIIll) {
        super(llIIlIlIlllIIll);
    }
    
    @Override
    public int getWidth() {
        return this.width;
    }
    
    @Override
    public int getHeight() {
        return this.height;
    }
    
    @Override
    public void setLocation(final int llIIlIlIlIlIlll, final int llIIlIlIlIlIIll) {
        this.x = llIIlIlIlIlIlll;
        this.y = llIIlIlIlIlIIll;
    }
    
    public abstract void renderImpl(final GUIContext p0, final Graphics p1);
    
    @Override
    public int getX() {
        return this.x;
    }
}
