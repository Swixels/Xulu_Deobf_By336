package org.newdawn.slick;

import org.newdawn.slick.opengl.*;
import org.newdawn.slick.opengl.renderer.*;

public class ScalableGame implements Game
{
    private /* synthetic */ int targetHeight;
    private /* synthetic */ int targetWidth;
    private /* synthetic */ GameContainer container;
    private /* synthetic */ float normalHeight;
    private /* synthetic */ boolean maintainAspect;
    private static /* synthetic */ SGL GL;
    private /* synthetic */ Game held;
    private /* synthetic */ float normalWidth;
    
    public void recalculateScale() throws SlickException {
        this.targetWidth = this.container.getWidth();
        this.targetHeight = this.container.getHeight();
        if (this.maintainAspect) {
            final boolean lllllllllllllllllIlllIIIIllIIlII = this.normalWidth / this.normalHeight > 1.6;
            final boolean lllllllllllllllllIlllIIIIllIIIll = this.targetWidth / (float)this.targetHeight > 1.6;
            final float lllllllllllllllllIlllIIIIllIIIlI = this.targetWidth / this.normalWidth;
            final float lllllllllllllllllIlllIIIIllIIIIl = this.targetHeight / this.normalHeight;
            if (lllllllllllllllllIlllIIIIllIIlII & lllllllllllllllllIlllIIIIllIIIll) {
                final float lllllllllllllllllIlllIIIIllIIllI = (lllllllllllllllllIlllIIIIllIIIlI < lllllllllllllllllIlllIIIIllIIIIl) ? lllllllllllllllllIlllIIIIllIIIlI : lllllllllllllllllIlllIIIIllIIIIl;
                this.targetWidth = (int)(this.normalWidth * lllllllllllllllllIlllIIIIllIIllI);
                this.targetHeight = (int)(this.normalHeight * lllllllllllllllllIlllIIIIllIIllI);
            }
            else if (lllllllllllllllllIlllIIIIllIIlII & !lllllllllllllllllIlllIIIIllIIIll) {
                this.targetWidth = (int)(this.normalWidth * lllllllllllllllllIlllIIIIllIIIlI);
                this.targetHeight = (int)(this.normalHeight * lllllllllllllllllIlllIIIIllIIIlI);
            }
            else if (!lllllllllllllllllIlllIIIIllIIlII & lllllllllllllllllIlllIIIIllIIIll) {
                this.targetWidth = (int)(this.normalWidth * lllllllllllllllllIlllIIIIllIIIIl);
                this.targetHeight = (int)(this.normalHeight * lllllllllllllllllIlllIIIIllIIIIl);
            }
            else {
                final float lllllllllllllllllIlllIIIIllIIlIl = (lllllllllllllllllIlllIIIIllIIIlI < lllllllllllllllllIlllIIIIllIIIIl) ? lllllllllllllllllIlllIIIIllIIIlI : lllllllllllllllllIlllIIIIllIIIIl;
                this.targetWidth = (int)(this.normalWidth * lllllllllllllllllIlllIIIIllIIlIl);
                this.targetHeight = (int)(this.normalHeight * lllllllllllllllllIlllIIIIllIIlIl);
            }
        }
        if (this.held instanceof InputListener) {
            this.container.getInput().addListener((InputListener)this.held);
        }
        this.container.getInput().setScale(this.normalWidth / this.targetWidth, this.normalHeight / this.targetHeight);
        int lllllllllllllllllIlllIIIIlIlllll = 0;
        int lllllllllllllllllIlllIIIIlIllllI = 0;
        if (this.targetHeight < this.container.getHeight()) {
            lllllllllllllllllIlllIIIIlIlllll = (this.container.getHeight() - this.targetHeight) / 2;
        }
        if (this.targetWidth < this.container.getWidth()) {
            lllllllllllllllllIlllIIIIlIllllI = (this.container.getWidth() - this.targetWidth) / 2;
        }
        this.container.getInput().setOffset(-lllllllllllllllllIlllIIIIlIllllI / (this.targetWidth / this.normalWidth), -lllllllllllllllllIlllIIIIlIlllll / (this.targetHeight / this.normalHeight));
    }
    
    @Override
    public final void render(final GameContainer lllllllllllllllllIlllIIIIlIIlIII, final Graphics lllllllllllllllllIlllIIIIlIIIlll) throws SlickException {
        int lllllllllllllllllIlllIIIIlIIIllI = 0;
        int lllllllllllllllllIlllIIIIlIIIlIl = 0;
        if (this.targetHeight < lllllllllllllllllIlllIIIIlIIlIII.getHeight()) {
            lllllllllllllllllIlllIIIIlIIIllI = (lllllllllllllllllIlllIIIIlIIlIII.getHeight() - this.targetHeight) / 2;
        }
        if (this.targetWidth < lllllllllllllllllIlllIIIIlIIlIII.getWidth()) {
            lllllllllllllllllIlllIIIIlIIIlIl = (lllllllllllllllllIlllIIIIlIIlIII.getWidth() - this.targetWidth) / 2;
        }
        SlickCallable.enterSafeBlock();
        lllllllllllllllllIlllIIIIlIIIlll.setClip(lllllllllllllllllIlllIIIIlIIIlIl, lllllllllllllllllIlllIIIIlIIIllI, this.targetWidth, this.targetHeight);
        ScalableGame.GL.glTranslatef((float)lllllllllllllllllIlllIIIIlIIIlIl, (float)lllllllllllllllllIlllIIIIlIIIllI, 0.0f);
        lllllllllllllllllIlllIIIIlIIIlll.scale(this.targetWidth / this.normalWidth, this.targetHeight / this.normalHeight);
        ScalableGame.GL.glPushMatrix();
        this.held.render(lllllllllllllllllIlllIIIIlIIlIII, lllllllllllllllllIlllIIIIlIIIlll);
        ScalableGame.GL.glPopMatrix();
        lllllllllllllllllIlllIIIIlIIIlll.clearClip();
        SlickCallable.leaveSafeBlock();
        this.renderOverlay(lllllllllllllllllIlllIIIIlIIlIII, lllllllllllllllllIlllIIIIlIIIlll);
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIlllIIIIllIllll) throws SlickException {
        this.container = lllllllllllllllllIlllIIIIllIllll;
        this.recalculateScale();
        this.held.init(lllllllllllllllllIlllIIIIllIllll);
    }
    
    public ScalableGame(final Game lllllllllllllllllIlllIIIIlllIllI, final int lllllllllllllllllIlllIIIIlllIlIl, final int lllllllllllllllllIlllIIIIllllIIl, final boolean lllllllllllllllllIlllIIIIllllIII) {
        this.held = lllllllllllllllllIlllIIIIlllIllI;
        this.normalWidth = (float)lllllllllllllllllIlllIIIIlllIlIl;
        this.normalHeight = (float)lllllllllllllllllIlllIIIIllllIIl;
        this.maintainAspect = lllllllllllllllllIlllIIIIllllIII;
    }
    
    @Override
    public String getTitle() {
        return this.held.getTitle();
    }
    
    public ScalableGame(final Game lllllllllllllllllIlllIIIlIIIIlII, final int lllllllllllllllllIlllIIIlIIIIIll, final int lllllllllllllllllIlllIIIlIIIIllI) {
        this(lllllllllllllllllIlllIIIlIIIIlII, lllllllllllllllllIlllIIIlIIIIIll, lllllllllllllllllIlllIIIlIIIIllI, false);
    }
    
    static {
        ScalableGame.GL = Renderer.get();
    }
    
    @Override
    public boolean closeRequested() {
        return this.held.closeRequested();
    }
    
    protected void renderOverlay(final GameContainer lllllllllllllllllIlllIIIIIlllllI, final Graphics lllllllllllllllllIlllIIIIIllllIl) {
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIlllIIIIlIlIIII, final int lllllllllllllllllIlllIIIIlIIllll) throws SlickException {
        if (this.targetHeight != lllllllllllllllllIlllIIIIlIlIIII.getHeight() || this.targetWidth != lllllllllllllllllIlllIIIIlIlIIII.getWidth()) {
            this.recalculateScale();
        }
        this.held.update(lllllllllllllllllIlllIIIIlIlIIII, lllllllllllllllllIlllIIIIlIIllll);
    }
}
