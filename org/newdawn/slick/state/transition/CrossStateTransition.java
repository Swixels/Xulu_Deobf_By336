package org.newdawn.slick.state.transition;

import org.newdawn.slick.state.*;
import org.newdawn.slick.*;

public abstract class CrossStateTransition implements Transition
{
    private /* synthetic */ GameState secondState;
    
    public CrossStateTransition(final GameState lllllllllllllllllllllllIIIIIlIIl) {
        this.secondState = lllllllllllllllllllllllIIIIIlIIl;
    }
    
    public void preRenderSecondState(final StateBasedGame llllllllllllllllllllllIllllIIlll, final GameContainer llllllllllllllllllllllIllllIIllI, final Graphics llllllllllllllllllllllIllllIIlIl) throws SlickException {
    }
    
    @Override
    public abstract boolean isComplete();
    
    @Override
    public void update(final StateBasedGame llllllllllllllllllllllIllllIllll, final GameContainer llllllllllllllllllllllIllllIlllI, final int llllllllllllllllllllllIllllIllIl) throws SlickException {
    }
    
    @Override
    public void postRender(final StateBasedGame lllllllllllllllllllllllIIIIIIIll, final GameContainer llllllllllllllllllllllIllllllllI, final Graphics llllllllllllllllllllllIlllllllIl) throws SlickException {
        this.preRenderSecondState(lllllllllllllllllllllllIIIIIIIll, llllllllllllllllllllllIllllllllI, llllllllllllllllllllllIlllllllIl);
        this.secondState.render(llllllllllllllllllllllIllllllllI, lllllllllllllllllllllllIIIIIIIll, llllllllllllllllllllllIlllllllIl);
        this.postRenderSecondState(lllllllllllllllllllllllIIIIIIIll, llllllllllllllllllllllIllllllllI, llllllllllllllllllllllIlllllllIl);
    }
    
    public void postRenderSecondState(final StateBasedGame llllllllllllllllllllllIllllIIIll, final GameContainer llllllllllllllllllllllIllllIIIlI, final Graphics llllllllllllllllllllllIllllIIIIl) throws SlickException {
    }
    
    @Override
    public void preRender(final StateBasedGame llllllllllllllllllllllIlllllIlll, final GameContainer llllllllllllllllllllllIlllllIIlI, final Graphics llllllllllllllllllllllIlllllIIIl) throws SlickException {
        this.preRenderFirstState(llllllllllllllllllllllIlllllIlll, llllllllllllllllllllllIlllllIIlI, llllllllllllllllllllllIlllllIIIl);
    }
    
    public void preRenderFirstState(final StateBasedGame llllllllllllllllllllllIllllIlIll, final GameContainer llllllllllllllllllllllIllllIlIlI, final Graphics llllllllllllllllllllllIllllIlIIl) throws SlickException {
    }
}
