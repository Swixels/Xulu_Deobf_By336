package org.newdawn.slick;

public interface Font
{
    int getLineHeight();
    
    void drawString(final float p0, final float p1, final String p2);
    
    int getWidth(final String p0);
    
    int getHeight(final String p0);
    
    void drawString(final float p0, final float p1, final String p2, final Color p3);
    
    void drawString(final float p0, final float p1, final String p2, final Color p3, final int p4, final int p5);
}
