package org.newdawn.slick.state.transition;

import org.newdawn.slick.opengl.renderer.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.*;

public class SelectTransition implements Transition
{
    private /* synthetic */ float scale1;
    private /* synthetic */ float xp2;
    private /* synthetic */ float yp1;
    private /* synthetic */ float yp2;
    private /* synthetic */ boolean finish;
    protected static /* synthetic */ SGL GL;
    private /* synthetic */ boolean init;
    private /* synthetic */ int pause;
    private /* synthetic */ float xp1;
    private /* synthetic */ boolean moveBackDone;
    private /* synthetic */ Color background;
    private /* synthetic */ GameState prev;
    private /* synthetic */ float scale2;
    
    public SelectTransition() {
        this.scale1 = 1.0f;
        this.xp1 = 0.0f;
        this.yp1 = 0.0f;
        this.scale2 = 0.4f;
        this.xp2 = 0.0f;
        this.yp2 = 0.0f;
        this.init = false;
        this.moveBackDone = false;
        this.pause = 300;
    }
    
    static {
        SelectTransition.GL = Renderer.get();
    }
    
    @Override
    public void init(final GameState lllllllllllllllllllIlIlIIlIIIIll, final GameState lllllllllllllllllllIlIlIIlIIIIII) {
        this.prev = lllllllllllllllllllIlIlIIlIIIIII;
    }
    
    public SelectTransition(final Color lllllllllllllllllllIlIlIIlIIIlll) {
        this.scale1 = 1.0f;
        this.xp1 = 0.0f;
        this.yp1 = 0.0f;
        this.scale2 = 0.4f;
        this.xp2 = 0.0f;
        this.yp2 = 0.0f;
        this.init = false;
        this.moveBackDone = false;
        this.pause = 300;
        this.background = lllllllllllllllllllIlIlIIlIIIlll;
    }
    
    @Override
    public void update(final StateBasedGame lllllllllllllllllllIlIlIIIlIIIII, final GameContainer lllllllllllllllllllIlIlIIIIlllII, final int lllllllllllllllllllIlIlIIIIllllI) throws SlickException {
        if (!this.init) {
            this.init = true;
            this.xp2 = (float)(lllllllllllllllllllIlIlIIIIlllII.getWidth() / 2 + 50);
            this.yp2 = (float)(lllllllllllllllllllIlIlIIIIlllII.getHeight() / 4);
        }
        if (!this.moveBackDone) {
            if (this.scale1 > 0.4f) {
                this.scale1 -= lllllllllllllllllllIlIlIIIIllllI * 0.002f;
                if (this.scale1 <= 0.4f) {
                    this.scale1 = 0.4f;
                }
                this.xp1 += lllllllllllllllllllIlIlIIIIllllI * 0.3f;
                if (this.xp1 > 50.0f) {
                    this.xp1 = 50.0f;
                }
                this.yp1 += lllllllllllllllllllIlIlIIIIllllI * 0.5f;
                if (this.yp1 > lllllllllllllllllllIlIlIIIIlllII.getHeight() / 4) {
                    this.yp1 = (float)(lllllllllllllllllllIlIlIIIIlllII.getHeight() / 4);
                }
            }
            else {
                this.moveBackDone = true;
            }
        }
        else {
            this.pause -= lllllllllllllllllllIlIlIIIIllllI;
            if (this.pause > 0) {
                return;
            }
            if (this.scale2 < 1.0f) {
                this.scale2 += lllllllllllllllllllIlIlIIIIllllI * 0.002f;
                if (this.scale2 >= 1.0f) {
                    this.scale2 = 1.0f;
                }
                this.xp2 -= lllllllllllllllllllIlIlIIIIllllI * 1.5f;
                if (this.xp2 < 0.0f) {
                    this.xp2 = 0.0f;
                }
                this.yp2 -= lllllllllllllllllllIlIlIIIIllllI * 0.5f;
                if (this.yp2 < 0.0f) {
                    this.yp2 = 0.0f;
                }
            }
            else {
                this.finish = true;
            }
        }
    }
    
    @Override
    public void preRender(final StateBasedGame lllllllllllllllllllIlIlIIIlIIlll, final GameContainer lllllllllllllllllllIlIlIIIlIlIlI, final Graphics lllllllllllllllllllIlIlIIIlIlIIl) throws SlickException {
        if (this.moveBackDone) {
            lllllllllllllllllllIlIlIIIlIlIIl.translate(this.xp1, this.yp1);
            lllllllllllllllllllIlIlIIIlIlIIl.scale(this.scale1, this.scale1);
            lllllllllllllllllllIlIlIIIlIlIIl.setClip((int)this.xp1, (int)this.yp1, (int)(this.scale1 * lllllllllllllllllllIlIlIIIlIlIlI.getWidth()), (int)(this.scale1 * lllllllllllllllllllIlIlIIIlIlIlI.getHeight()));
            this.prev.render(lllllllllllllllllllIlIlIIIlIlIlI, lllllllllllllllllllIlIlIIIlIIlll, lllllllllllllllllllIlIlIIIlIlIIl);
            lllllllllllllllllllIlIlIIIlIlIIl.resetTransform();
            lllllllllllllllllllIlIlIIIlIlIIl.clearClip();
        }
        lllllllllllllllllllIlIlIIIlIlIIl.translate(this.xp2, this.yp2);
        lllllllllllllllllllIlIlIIIlIlIIl.scale(this.scale2, this.scale2);
        lllllllllllllllllllIlIlIIIlIlIIl.setClip((int)this.xp2, (int)this.yp2, (int)(this.scale2 * lllllllllllllllllllIlIlIIIlIlIlI.getWidth()), (int)(this.scale2 * lllllllllllllllllllIlIlIIIlIlIlI.getHeight()));
    }
    
    @Override
    public boolean isComplete() {
        return this.finish;
    }
    
    @Override
    public void postRender(final StateBasedGame lllllllllllllllllllIlIlIIIllIIll, final GameContainer lllllllllllllllllllIlIlIIIllIIlI, final Graphics lllllllllllllllllllIlIlIIIllIlIl) throws SlickException {
        lllllllllllllllllllIlIlIIIllIlIl.resetTransform();
        if (!this.moveBackDone) {
            lllllllllllllllllllIlIlIIIllIlIl.translate(this.xp1, this.yp1);
            lllllllllllllllllllIlIlIIIllIlIl.scale(this.scale1, this.scale1);
            lllllllllllllllllllIlIlIIIllIlIl.setClip((int)this.xp1, (int)this.yp1, (int)(this.scale1 * lllllllllllllllllllIlIlIIIllIIlI.getWidth()), (int)(this.scale1 * lllllllllllllllllllIlIlIIIllIIlI.getHeight()));
            this.prev.render(lllllllllllllllllllIlIlIIIllIIlI, lllllllllllllllllllIlIlIIIllIIll, lllllllllllllllllllIlIlIIIllIlIl);
            lllllllllllllllllllIlIlIIIllIlIl.resetTransform();
            lllllllllllllllllllIlIlIIIllIlIl.clearClip();
        }
    }
}
