package org.newdawn.slick.state.transition;

import org.newdawn.slick.state.*;
import org.newdawn.slick.opengl.renderer.*;
import org.newdawn.slick.*;

public class HorizontalSplitTransition implements Transition
{
    private /* synthetic */ boolean finish;
    protected static /* synthetic */ SGL GL;
    private /* synthetic */ float offset;
    private /* synthetic */ GameState prev;
    private /* synthetic */ Color background;
    
    @Override
    public void update(final StateBasedGame lIlIlIIIIIllIll, final GameContainer lIlIlIIIIIlIlll, final int lIlIlIIIIIlIllI) throws SlickException {
        this.offset += lIlIlIIIIIlIllI * 1.0f;
        if (this.offset > lIlIlIIIIIlIlll.getWidth() / 2) {
            this.finish = true;
        }
    }
    
    static {
        HorizontalSplitTransition.GL = Renderer.get();
    }
    
    @Override
    public boolean isComplete() {
        return this.finish;
    }
    
    @Override
    public void postRender(final StateBasedGame lIlIlIIIIlIIlll, final GameContainer lIlIlIIIIlIIllI, final Graphics lIlIlIIIIlIlIIl) throws SlickException {
        lIlIlIIIIlIlIIl.translate(-this.offset, 0.0f);
        lIlIlIIIIlIlIIl.setClip((int)(-this.offset), 0, lIlIlIIIIlIIllI.getWidth() / 2, lIlIlIIIIlIIllI.getHeight());
        if (this.background != null) {
            final Color lIlIlIIIIlIlllI = lIlIlIIIIlIlIIl.getColor();
            lIlIlIIIIlIlIIl.setColor(this.background);
            lIlIlIIIIlIlIIl.fillRect(0.0f, 0.0f, (float)lIlIlIIIIlIIllI.getWidth(), (float)lIlIlIIIIlIIllI.getHeight());
            lIlIlIIIIlIlIIl.setColor(lIlIlIIIIlIlllI);
        }
        HorizontalSplitTransition.GL.glPushMatrix();
        this.prev.render(lIlIlIIIIlIIllI, lIlIlIIIIlIIlll, lIlIlIIIIlIlIIl);
        HorizontalSplitTransition.GL.glPopMatrix();
        lIlIlIIIIlIlIIl.clearClip();
        lIlIlIIIIlIlIIl.translate(this.offset * 2.0f, 0.0f);
        lIlIlIIIIlIlIIl.setClip((int)(lIlIlIIIIlIIllI.getWidth() / 2 + this.offset), 0, lIlIlIIIIlIIllI.getWidth() / 2, lIlIlIIIIlIIllI.getHeight());
        if (this.background != null) {
            final Color lIlIlIIIIlIllIl = lIlIlIIIIlIlIIl.getColor();
            lIlIlIIIIlIlIIl.setColor(this.background);
            lIlIlIIIIlIlIIl.fillRect(0.0f, 0.0f, (float)lIlIlIIIIlIIllI.getWidth(), (float)lIlIlIIIIlIIllI.getHeight());
            lIlIlIIIIlIlIIl.setColor(lIlIlIIIIlIllIl);
        }
        HorizontalSplitTransition.GL.glPushMatrix();
        this.prev.render(lIlIlIIIIlIIllI, lIlIlIIIIlIIlll, lIlIlIIIIlIlIIl);
        HorizontalSplitTransition.GL.glPopMatrix();
        lIlIlIIIIlIlIIl.clearClip();
        lIlIlIIIIlIlIIl.translate(-this.offset, 0.0f);
    }
    
    public HorizontalSplitTransition() {
    }
    
    public HorizontalSplitTransition(final Color lIlIlIIIlIIIIII) {
        this.background = lIlIlIIIlIIIIII;
    }
    
    @Override
    public void init(final GameState lIlIlIIIIlllIlI, final GameState lIlIlIIIIlllIIl) {
        this.prev = lIlIlIIIIlllIIl;
    }
    
    @Override
    public void preRender(final StateBasedGame lIlIlIIIIlIIIlI, final GameContainer lIlIlIIIIlIIIIl, final Graphics lIlIlIIIIlIIIII) throws SlickException {
    }
}
