package org.newdawn.slick.opengl.renderer;

public interface LineStripRenderer
{
    void end();
    
    void setAntiAlias(final boolean p0);
    
    void start();
    
    void vertex(final float p0, final float p1);
    
    void setWidth(final float p0);
    
    void color(final float p0, final float p1, final float p2, final float p3);
    
    boolean applyGLLineFixes();
    
    void setLineCaps(final boolean p0);
}
