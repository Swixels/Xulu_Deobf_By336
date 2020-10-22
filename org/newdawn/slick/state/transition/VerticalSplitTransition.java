package org.newdawn.slick.state.transition;

import org.newdawn.slick.state.*;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.renderer.*;

public class VerticalSplitTransition implements Transition
{
    private /* synthetic */ float offset;
    private /* synthetic */ GameState prev;
    private /* synthetic */ boolean finish;
    private /* synthetic */ Color background;
    protected static /* synthetic */ SGL GL;
    
    @Override
    public boolean isComplete() {
        return this.finish;
    }
    
    @Override
    public void postRender(final StateBasedGame llllllllllllllllIllIIIIllIlIIIlI, final GameContainer llllllllllllllllIllIIIIllIlIIlIl, final Graphics llllllllllllllllIllIIIIllIlIIIII) throws SlickException {
        llllllllllllllllIllIIIIllIlIIIII.translate(0.0f, -this.offset);
        llllllllllllllllIllIIIIllIlIIIII.setClip(0, (int)(-this.offset), llllllllllllllllIllIIIIllIlIIlIl.getWidth(), llllllllllllllllIllIIIIllIlIIlIl.getHeight() / 2);
        if (this.background != null) {
            final Color llllllllllllllllIllIIIIllIlIlIIl = llllllllllllllllIllIIIIllIlIIIII.getColor();
            llllllllllllllllIllIIIIllIlIIIII.setColor(this.background);
            llllllllllllllllIllIIIIllIlIIIII.fillRect(0.0f, 0.0f, (float)llllllllllllllllIllIIIIllIlIIlIl.getWidth(), (float)llllllllllllllllIllIIIIllIlIIlIl.getHeight());
            llllllllllllllllIllIIIIllIlIIIII.setColor(llllllllllllllllIllIIIIllIlIlIIl);
        }
        VerticalSplitTransition.GL.glPushMatrix();
        this.prev.render(llllllllllllllllIllIIIIllIlIIlIl, llllllllllllllllIllIIIIllIlIIIlI, llllllllllllllllIllIIIIllIlIIIII);
        VerticalSplitTransition.GL.glPopMatrix();
        llllllllllllllllIllIIIIllIlIIIII.clearClip();
        llllllllllllllllIllIIIIllIlIIIII.resetTransform();
        llllllllllllllllIllIIIIllIlIIIII.translate(0.0f, this.offset);
        llllllllllllllllIllIIIIllIlIIIII.setClip(0, (int)(llllllllllllllllIllIIIIllIlIIlIl.getHeight() / 2 + this.offset), llllllllllllllllIllIIIIllIlIIlIl.getWidth(), llllllllllllllllIllIIIIllIlIIlIl.getHeight() / 2);
        if (this.background != null) {
            final Color llllllllllllllllIllIIIIllIlIlIII = llllllllllllllllIllIIIIllIlIIIII.getColor();
            llllllllllllllllIllIIIIllIlIIIII.setColor(this.background);
            llllllllllllllllIllIIIIllIlIIIII.fillRect(0.0f, 0.0f, (float)llllllllllllllllIllIIIIllIlIIlIl.getWidth(), (float)llllllllllllllllIllIIIIllIlIIlIl.getHeight());
            llllllllllllllllIllIIIIllIlIIIII.setColor(llllllllllllllllIllIIIIllIlIlIII);
        }
        VerticalSplitTransition.GL.glPushMatrix();
        this.prev.render(llllllllllllllllIllIIIIllIlIIlIl, llllllllllllllllIllIIIIllIlIIIlI, llllllllllllllllIllIIIIllIlIIIII);
        VerticalSplitTransition.GL.glPopMatrix();
        llllllllllllllllIllIIIIllIlIIIII.clearClip();
        llllllllllllllllIllIIIIllIlIIIII.translate(0.0f, -this.offset);
    }
    
    @Override
    public void init(final GameState llllllllllllllllIllIIIIllIllIlIl, final GameState llllllllllllllllIllIIIIllIllIIlI) {
        this.prev = llllllllllllllllIllIIIIllIllIIlI;
    }
    
    @Override
    public void update(final StateBasedGame llllllllllllllllIllIIIIllIIlIllI, final GameContainer llllllllllllllllIllIIIIllIIlIlIl, final int llllllllllllllllIllIIIIllIIlIlII) throws SlickException {
        this.offset += llllllllllllllllIllIIIIllIIlIlII * 1.0f;
        if (this.offset > llllllllllllllllIllIIIIllIIlIlIl.getHeight() / 2) {
            this.finish = true;
        }
    }
    
    @Override
    public void preRender(final StateBasedGame llllllllllllllllIllIIIIllIIlllIl, final GameContainer llllllllllllllllIllIIIIllIIlllII, final Graphics llllllllllllllllIllIIIIllIIllIll) throws SlickException {
    }
    
    public VerticalSplitTransition(final Color llllllllllllllllIllIIIIllIlllIIl) {
        this.background = llllllllllllllllIllIIIIllIlllIIl;
    }
    
    public VerticalSplitTransition() {
    }
    
    static {
        VerticalSplitTransition.GL = Renderer.get();
    }
}
