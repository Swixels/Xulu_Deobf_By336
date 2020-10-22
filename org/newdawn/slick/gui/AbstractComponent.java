package org.newdawn.slick.gui;

import org.newdawn.slick.util.*;
import org.newdawn.slick.geom.*;
import java.util.*;
import org.newdawn.slick.*;

public abstract class AbstractComponent extends InputAdapter
{
    protected /* synthetic */ Set listeners;
    protected /* synthetic */ GUIContext container;
    private static /* synthetic */ AbstractComponent currentFocus;
    protected /* synthetic */ Input input;
    private /* synthetic */ boolean focus;
    
    public abstract int getY();
    
    public boolean hasFocus() {
        return this.focus;
    }
    
    protected void consumeEvent() {
        this.input.consumeEvent();
    }
    
    public void setFocus(final boolean lIIllIIlIIIIllI) {
        if (lIIllIIlIIIIllI) {
            if (AbstractComponent.currentFocus != null) {
                AbstractComponent.currentFocus.setFocus(false);
            }
            AbstractComponent.currentFocus = this;
        }
        else if (AbstractComponent.currentFocus == this) {
            AbstractComponent.currentFocus = null;
        }
        this.focus = lIIllIIlIIIIllI;
    }
    
    static {
        AbstractComponent.currentFocus = null;
    }
    
    public abstract int getHeight();
    
    public void removeListener(final ComponentListener lIIllIIlIIlIIlI) {
        this.listeners.remove(lIIllIIlIIlIIlI);
    }
    
    protected void notifyListeners() {
        final Iterator lIIllIIlIIIllII = this.listeners.iterator();
        while (lIIllIIlIIIllII.hasNext()) {
            lIIllIIlIIIllII.next().componentActivated(this);
        }
    }
    
    public abstract int getX();
    
    public abstract void render(final GUIContext p0, final Graphics p1) throws SlickException;
    
    public abstract void setLocation(final int p0, final int p1);
    
    @Override
    public void mouseReleased(final int lIIllIIIllllIIl, final int lIIllIIIllllIII, final int lIIllIIIlllIlll) {
        this.setFocus(Rectangle.contains((float)lIIllIIIllllIII, (float)lIIllIIIlllIlll, (float)this.getX(), (float)this.getY(), (float)this.getWidth(), (float)this.getHeight()));
    }
    
    public AbstractComponent(final GUIContext lIIllIIlIIllllI) {
        this.focus = false;
        this.container = lIIllIIlIIllllI;
        this.listeners = new HashSet();
        this.input = lIIllIIlIIllllI.getInput();
        this.input.addPrimaryListener(this);
        this.setLocation(0, 0);
    }
    
    public abstract int getWidth();
    
    public void addListener(final ComponentListener lIIllIIlIIllIII) {
        this.listeners.add(lIIllIIlIIllIII);
    }
}
