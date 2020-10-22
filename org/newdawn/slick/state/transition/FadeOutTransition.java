package org.newdawn.slick.state.transition;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class FadeOutTransition implements Transition
{
    private /* synthetic */ int fadeTime;
    private /* synthetic */ Color color;
    
    public FadeOutTransition(final Color lllllllllllllllllIllIllllllIlIll, final int lllllllllllllllllIllIllllllIlIlI) {
        this.color = new Color(lllllllllllllllllIllIllllllIlIll);
        this.color.a = 0.0f;
        this.fadeTime = lllllllllllllllllIllIllllllIlIlI;
    }
    
    @Override
    public void preRender(final StateBasedGame lllllllllllllllllIllIlllllIIllIl, final GameContainer lllllllllllllllllIllIlllllIIllII, final Graphics lllllllllllllllllIllIlllllIIlIll) {
    }
    
    public FadeOutTransition(final Color lllllllllllllllllIllIlllllllIIII) {
        this(lllllllllllllllllIllIlllllllIIII, 500);
    }
    
    @Override
    public void update(final StateBasedGame lllllllllllllllllIllIlllllIlIIll, final GameContainer lllllllllllllllllIllIlllllIlIIlI, final int lllllllllllllllllIllIlllllIIllll) {
        final Color color = this.color;
        color.a += lllllllllllllllllIllIlllllIIllll * (1.0f / this.fadeTime);
        if (this.color.a > 1.0f) {
            this.color.a = 1.0f;
        }
    }
    
    @Override
    public boolean isComplete() {
        return this.color.a >= 1.0f;
    }
    
    public FadeOutTransition() {
        this(Color.black, 500);
    }
    
    @Override
    public void init(final GameState lllllllllllllllllIllIlllllIIlIIl, final GameState lllllllllllllllllIllIlllllIIlIII) {
    }
    
    @Override
    public void postRender(final StateBasedGame lllllllllllllllllIllIlllllIllllI, final GameContainer lllllllllllllllllIllIlllllIlllIl, final Graphics lllllllllllllllllIllIlllllIlllII) {
        final Color lllllllllllllllllIllIlllllIllIll = lllllllllllllllllIllIlllllIlllII.getColor();
        lllllllllllllllllIllIlllllIlllII.setColor(this.color);
        lllllllllllllllllIllIlllllIlllII.fillRect(0.0f, 0.0f, (float)(lllllllllllllllllIllIlllllIlllIl.getWidth() * 2), (float)(lllllllllllllllllIllIlllllIlllIl.getHeight() * 2));
        lllllllllllllllllIllIlllllIlllII.setColor(lllllllllllllllllIllIlllllIllIll);
    }
}
