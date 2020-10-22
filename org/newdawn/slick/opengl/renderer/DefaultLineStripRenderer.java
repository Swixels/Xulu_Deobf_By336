package org.newdawn.slick.opengl.renderer;

public class DefaultLineStripRenderer implements LineStripRenderer
{
    private /* synthetic */ SGL GL;
    
    @Override
    public boolean applyGLLineFixes() {
        return true;
    }
    
    @Override
    public void start() {
        this.GL.glBegin(3);
    }
    
    @Override
    public void setLineCaps(final boolean lIlllIIIlIlIIII) {
    }
    
    @Override
    public void color(final float lIlllIIIlIllIlI, final float lIlllIIIlIllIIl, final float lIlllIIIlIllIII, final float lIlllIIIlIlIlll) {
        this.GL.glColor4f(lIlllIIIlIllIlI, lIlllIIIlIllIIl, lIlllIIIlIllIII, lIlllIIIlIlIlll);
    }
    
    @Override
    public void vertex(final float lIlllIIIllIIIlI, final float lIlllIIIllIIIIl) {
        this.GL.glVertex2f(lIlllIIIllIIIlI, lIlllIIIllIIIIl);
    }
    
    @Override
    public void setAntiAlias(final boolean lIlllIIIlllIlIl) {
        if (lIlllIIIlllIlIl) {
            this.GL.glEnable(2848);
        }
        else {
            this.GL.glDisable(2848);
        }
    }
    
    public DefaultLineStripRenderer() {
        this.GL = Renderer.get();
    }
    
    @Override
    public void setWidth(final float lIlllIIIllIllll) {
        this.GL.glLineWidth(lIlllIIIllIllll);
    }
    
    @Override
    public void end() {
        this.GL.glEnd();
    }
}
