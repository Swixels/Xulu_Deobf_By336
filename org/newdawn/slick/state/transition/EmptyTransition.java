package org.newdawn.slick.state.transition;

import org.newdawn.slick.state.*;
import org.newdawn.slick.*;

public class EmptyTransition implements Transition
{
    @Override
    public void init(final GameState llIlllIIllIll, final GameState llIlllIIllIlI) {
    }
    
    @Override
    public void preRender(final StateBasedGame llIlllIlIIIll, final GameContainer llIlllIlIIIlI, final Graphics llIlllIlIIIIl) throws SlickException {
    }
    
    @Override
    public void postRender(final StateBasedGame lllIlIIIIlIII, final GameContainer lllIlIIIIIlll, final Graphics lllIlIIIIIllI) throws SlickException {
    }
    
    @Override
    public boolean isComplete() {
        return true;
    }
    
    @Override
    public void update(final StateBasedGame llIlllIIlllll, final GameContainer llIlllIIllllI, final int llIlllIIlllIl) throws SlickException {
    }
}
