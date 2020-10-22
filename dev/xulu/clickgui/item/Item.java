package dev.xulu.clickgui.item;

import dev.xulu.clickgui.*;
import dev.xulu.settings.*;
import java.io.*;

public class Item implements Labeled
{
    protected /* synthetic */ float y;
    protected /* synthetic */ float x;
    protected /* synthetic */ Value property;
    protected /* synthetic */ int height;
    protected /* synthetic */ int width;
    private final /* synthetic */ String label;
    
    public Item(final String lllllllllllllllllIIllIIlIlIIIIII) {
        this.label = lllllllllllllllllIIllIIlIlIIIIII;
    }
    
    public void setWidth(final int lllllllllllllllllIIllIIlIIIlIIIl) {
        this.width = lllllllllllllllllIIllIIlIIIlIIIl;
    }
    
    public void setLocation(final float lllllllllllllllllIIllIIlIIllIllI, final float lllllllllllllllllIIllIIlIIlllIII) {
        this.x = lllllllllllllllllIIllIIlIIllIllI;
        this.y = lllllllllllllllllIIllIIlIIlllIII;
    }
    
    public float getY() {
        return this.y;
    }
    
    public void mouseClicked(final int lllllllllllllllllIIllIIlIIlIllll, final int lllllllllllllllllIIllIIlIIlIlllI, final int lllllllllllllllllIIllIIlIIlIllIl) {
    }
    
    @Override
    public final String getLabel() {
        return this.label;
    }
    
    public void drawScreen(final int lllllllllllllllllIIllIIlIIllIIll, final int lllllllllllllllllIIllIIlIIllIIlI, final float lllllllllllllllllIIllIIlIIllIIIl) {
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void setHeight(final int lllllllllllllllllIIllIIlIIIIlIll) {
        this.height = lllllllllllllllllIIllIIlIIIIlIll;
    }
    
    public void mouseReleased(final int lllllllllllllllllIIllIIlIIlIlIll, final int lllllllllllllllllIIllIIlIIlIlIlI, final int lllllllllllllllllIIllIIlIIlIlIIl) {
    }
    
    public float getX() {
        return this.x;
    }
    
    public boolean keyTyped(final char lllllllllllllllllIIllIIlIIlIIlll, final int lllllllllllllllllIIllIIlIIlIIllI) throws IOException {
        return false;
    }
    
    public void setValue(final Value lllllllllllllllllIIllIIlIIIIIlll) {
        this.property = lllllllllllllllllIIllIIlIIIIIlll;
    }
}
