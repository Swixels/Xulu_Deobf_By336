package org.newdawn.slick.state.transition;

import org.newdawn.slick.state.*;
import org.newdawn.slick.*;

public class RotateTransition implements Transition
{
    private /* synthetic */ float scale;
    private /* synthetic */ Color background;
    private /* synthetic */ GameState prev;
    private /* synthetic */ boolean finish;
    private /* synthetic */ float ang;
    
    @Override
    public void postRender(final StateBasedGame llllllllllllllllIlIllIIlIIIIllIl, final GameContainer llllllllllllllllIlIllIIlIIIIlIll, final Graphics llllllllllllllllIlIllIIIlllllllI) throws SlickException {
        llllllllllllllllIlIllIIIlllllllI.translate((float)(llllllllllllllllIlIllIIlIIIIlIll.getWidth() / 2), (float)(llllllllllllllllIlIllIIlIIIIlIll.getHeight() / 2));
        llllllllllllllllIlIllIIIlllllllI.scale(this.scale, this.scale);
        llllllllllllllllIlIllIIIlllllllI.rotate(0.0f, 0.0f, this.ang);
        llllllllllllllllIlIllIIIlllllllI.translate((float)(-llllllllllllllllIlIllIIlIIIIlIll.getWidth() / 2), (float)(-llllllllllllllllIlIllIIlIIIIlIll.getHeight() / 2));
        if (this.background != null) {
            final Color llllllllllllllllIlIllIIlIIIlIIII = llllllllllllllllIlIllIIIlllllllI.getColor();
            llllllllllllllllIlIllIIIlllllllI.setColor(this.background);
            llllllllllllllllIlIllIIIlllllllI.fillRect(0.0f, 0.0f, (float)llllllllllllllllIlIllIIlIIIIlIll.getWidth(), (float)llllllllllllllllIlIllIIlIIIIlIll.getHeight());
            llllllllllllllllIlIllIIIlllllllI.setColor(llllllllllllllllIlIllIIlIIIlIIII);
        }
        this.prev.render(llllllllllllllllIlIllIIlIIIIlIll, llllllllllllllllIlIllIIlIIIIllIl, llllllllllllllllIlIllIIIlllllllI);
        llllllllllllllllIlIllIIIlllllllI.translate((float)(llllllllllllllllIlIllIIlIIIIlIll.getWidth() / 2), (float)(llllllllllllllllIlIllIIlIIIIlIll.getHeight() / 2));
        llllllllllllllllIlIllIIIlllllllI.rotate(0.0f, 0.0f, -this.ang);
        llllllllllllllllIlIllIIIlllllllI.scale(1.0f / this.scale, 1.0f / this.scale);
        llllllllllllllllIlIllIIIlllllllI.translate((float)(-llllllllllllllllIlIllIIlIIIIlIll.getWidth() / 2), (float)(-llllllllllllllllIlIllIIlIIIIlIll.getHeight() / 2));
    }
    
    @Override
    public boolean isComplete() {
        return this.finish;
    }
    
    public RotateTransition(final Color llllllllllllllllIlIllIIlIIllllll) {
        this.scale = 1.0f;
        this.background = llllllllllllllllIlIllIIlIIllllll;
    }
    
    @Override
    public void init(final GameState llllllllllllllllIlIllIIlIIlllIll, final GameState llllllllllllllllIlIllIIlIIlllIII) {
        this.prev = llllllllllllllllIlIllIIlIIlllIII;
    }
    
    @Override
    public void preRender(final StateBasedGame llllllllllllllllIlIllIIIllllIlIl, final GameContainer llllllllllllllllIlIllIIIllllIIll, final Graphics llllllllllllllllIlIllIIIllllIIlI) throws SlickException {
    }
    
    @Override
    public void update(final StateBasedGame llllllllllllllllIlIllIIIlllIIlIl, final GameContainer llllllllllllllllIlIllIIIlllIIlII, final int llllllllllllllllIlIllIIIlllIIIIl) throws SlickException {
        this.ang += llllllllllllllllIlIllIIIlllIIIIl * 0.5f;
        if (this.ang > 500.0f) {
            this.finish = true;
        }
        this.scale -= llllllllllllllllIlIllIIIlllIIIIl * 0.001f;
        if (this.scale < 0.0f) {
            this.scale = 0.0f;
        }
    }
    
    public RotateTransition() {
        this.scale = 1.0f;
    }
}
