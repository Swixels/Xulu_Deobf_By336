package org.newdawn.slick.state.transition;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class FadeInTransition implements Transition
{
    private /* synthetic */ Color color;
    private /* synthetic */ int fadeTime;
    
    @Override
    public void postRender(final StateBasedGame llIllIIlllllIll, final GameContainer llIllIIllllIllI, final Graphics llIllIIlllllIIl) {
        final Color llIllIIlllllIII = llIllIIlllllIIl.getColor();
        llIllIIlllllIIl.setColor(this.color);
        llIllIIlllllIIl.fillRect(0.0f, 0.0f, (float)(llIllIIllllIllI.getWidth() * 2), (float)(llIllIIllllIllI.getHeight() * 2));
        llIllIIlllllIIl.setColor(llIllIIlllllIII);
    }
    
    @Override
    public void init(final GameState llIllIIlllIIllI, final GameState llIllIIlllIIlIl) {
    }
    
    @Override
    public void preRender(final StateBasedGame llIllIIlllIlIlI, final GameContainer llIllIIlllIlIIl, final Graphics llIllIIlllIlIII) {
    }
    
    @Override
    public boolean isComplete() {
        return this.color.a <= 0.0f;
    }
    
    public FadeInTransition(final Color llIllIlIIIIllIl) {
        this(llIllIlIIIIllIl, 500);
    }
    
    public FadeInTransition() {
        this(Color.black, 500);
    }
    
    public FadeInTransition(final Color llIllIlIIIIlIII, final int llIllIlIIIIIlll) {
        this.fadeTime = 500;
        this.color = new Color(llIllIlIIIIlIII);
        this.color.a = 1.0f;
        this.fadeTime = llIllIlIIIIIlll;
    }
    
    @Override
    public void update(final StateBasedGame llIllIIllllIIII, final GameContainer llIllIIlllIllll, final int llIllIIlllIlllI) {
        final Color color = this.color;
        color.a -= llIllIIlllIlllI * (1.0f / this.fadeTime);
        if (this.color.a < 0.0f) {
            this.color.a = 0.0f;
        }
    }
}
