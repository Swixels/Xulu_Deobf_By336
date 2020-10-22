package org.newdawn.slick.state.transition;

import java.util.*;
import org.newdawn.slick.opengl.renderer.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.*;

public class BlobbyTransition implements Transition
{
    protected static /* synthetic */ SGL GL;
    private /* synthetic */ int blobCount;
    private /* synthetic */ boolean finish;
    private /* synthetic */ Color background;
    private /* synthetic */ GameState prev;
    private /* synthetic */ ArrayList blobs;
    private /* synthetic */ int timer;
    
    @Override
    public void init(final GameState lllllllllllllllllIlIIllIIllIIlII, final GameState lllllllllllllllllIlIIllIIllIIIIl) {
        this.prev = lllllllllllllllllIlIIllIIllIIIIl;
    }
    
    static {
        BlobbyTransition.GL = Renderer.get();
    }
    
    public BlobbyTransition(final Color lllllllllllllllllIlIIllIIllIlIlI) {
        this.blobs = new ArrayList();
        this.timer = 1000;
        this.blobCount = 10;
        this.background = lllllllllllllllllIlIIllIIllIlIlI;
    }
    
    @Override
    public boolean isComplete() {
        return this.finish;
    }
    
    @Override
    public void preRender(final StateBasedGame lllllllllllllllllIlIIllIIlIlIIIl, final GameContainer lllllllllllllllllIlIIllIIlIlIIII, final Graphics lllllllllllllllllIlIIllIIlIIllll) throws SlickException {
        this.prev.render(lllllllllllllllllIlIIllIIlIlIIII, lllllllllllllllllIlIIllIIlIlIIIl, lllllllllllllllllIlIIllIIlIIllll);
        MaskUtil.defineMask();
        for (int lllllllllllllllllIlIIllIIlIlIlII = 0; lllllllllllllllllIlIIllIIlIlIlII < this.blobs.size(); ++lllllllllllllllllIlIIllIIlIlIlII) {
            this.blobs.get(lllllllllllllllllIlIIllIIlIlIlII).render(lllllllllllllllllIlIIllIIlIIllll);
        }
        MaskUtil.finishDefineMask();
        MaskUtil.drawOnMask();
        if (this.background != null) {
            final Color lllllllllllllllllIlIIllIIlIlIIll = lllllllllllllllllIlIIllIIlIIllll.getColor();
            lllllllllllllllllIlIIllIIlIIllll.setColor(this.background);
            lllllllllllllllllIlIIllIIlIIllll.fillRect(0.0f, 0.0f, (float)lllllllllllllllllIlIIllIIlIlIIII.getWidth(), (float)lllllllllllllllllIlIIllIIlIlIIII.getHeight());
            lllllllllllllllllIlIIllIIlIIllll.setColor(lllllllllllllllllIlIIllIIlIlIIll);
        }
    }
    
    public BlobbyTransition() {
        this.blobs = new ArrayList();
        this.timer = 1000;
        this.blobCount = 10;
    }
    
    @Override
    public void postRender(final StateBasedGame lllllllllllllllllIlIIllIIlIlllII, final GameContainer lllllllllllllllllIlIIllIIlIllIll, final Graphics lllllllllllllllllIlIIllIIlIllIlI) throws SlickException {
        MaskUtil.resetMask();
    }
    
    @Override
    public void update(final StateBasedGame lllllllllllllllllIlIIllIIlIIIIlI, final GameContainer lllllllllllllllllIlIIllIIIlllllI, final int lllllllllllllllllIlIIllIIlIIIIII) throws SlickException {
        if (this.blobs.size() == 0) {
            for (int lllllllllllllllllIlIIllIIlIIIlIl = 0; lllllllllllllllllIlIIllIIlIIIlIl < this.blobCount; ++lllllllllllllllllIlIIllIIlIIIlIl) {
                this.blobs.add(new Blob(lllllllllllllllllIlIIllIIIlllllI));
            }
        }
        for (int lllllllllllllllllIlIIllIIlIIIlII = 0; lllllllllllllllllIlIIllIIlIIIlII < this.blobs.size(); ++lllllllllllllllllIlIIllIIlIIIlII) {
            this.blobs.get(lllllllllllllllllIlIIllIIlIIIlII).update(lllllllllllllllllIlIIllIIlIIIIII);
        }
        this.timer -= lllllllllllllllllIlIIllIIlIIIIII;
        if (this.timer < 0) {
            this.finish = true;
        }
    }
    
    private class Blob
    {
        private /* synthetic */ float y;
        private /* synthetic */ float rad;
        private /* synthetic */ float growSpeed;
        private /* synthetic */ float x;
        
        public void render(final Graphics lIIllIIlIlllIl) {
            lIIllIIlIlllIl.fillOval(this.x - this.rad, this.y - this.rad, this.rad * 2.0f, this.rad * 2.0f);
        }
        
        public Blob(final GameContainer lIIllIIllIIlll) {
            this.x = (float)(Math.random() * lIIllIIllIIlll.getWidth());
            this.y = (float)(Math.random() * lIIllIIllIIlll.getWidth());
            this.growSpeed = (float)(1.0 + Math.random() * 1.0);
        }
        
        public void update(final int lIIllIIllIIIll) {
            this.rad += this.growSpeed * lIIllIIllIIIll * 0.4f;
        }
    }
}
