package org.newdawn.slick.state.transition;

import java.util.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class CombinedTransition implements Transition
{
    private /* synthetic */ ArrayList transitions;
    
    @Override
    public void update(final StateBasedGame lllllllllllllllllIIlIIlIIIIIllII, final GameContainer lllllllllllllllllIIlIIlIIIIIlIll, final int lllllllllllllllllIIlIIlIIIIIlIlI) throws SlickException {
        for (int lllllllllllllllllIIlIIlIIIIlIIlI = 0; lllllllllllllllllIIlIIlIIIIlIIlI < this.transitions.size(); ++lllllllllllllllllIIlIIlIIIIlIIlI) {
            final Transition lllllllllllllllllIIlIIlIIIIlIIll = this.transitions.get(lllllllllllllllllIIlIIlIIIIlIIlI);
            if (!lllllllllllllllllIIlIIlIIIIlIIll.isComplete()) {
                lllllllllllllllllIIlIIlIIIIlIIll.update(lllllllllllllllllIIlIIlIIIIIllII, lllllllllllllllllIIlIIlIIIIIlIll, lllllllllllllllllIIlIIlIIIIIlIlI);
            }
        }
    }
    
    @Override
    public boolean isComplete() {
        for (int lllllllllllllllllIIlIIlIIIlllIll = 0; lllllllllllllllllIIlIIlIIIlllIll < this.transitions.size(); ++lllllllllllllllllIIlIIlIIIlllIll) {
            if (!this.transitions.get(lllllllllllllllllIIlIIlIIIlllIll).isComplete()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void postRender(final StateBasedGame lllllllllllllllllIIlIIlIIIllIIII, final GameContainer lllllllllllllllllIIlIIlIIIlIlIll, final Graphics lllllllllllllllllIIlIIlIIIlIlIlI) throws SlickException {
        for (int lllllllllllllllllIIlIIlIIIllIIlI = this.transitions.size() - 1; lllllllllllllllllIIlIIlIIIllIIlI >= 0; --lllllllllllllllllIIlIIlIIIllIIlI) {
            this.transitions.get(lllllllllllllllllIIlIIlIIIllIIlI).postRender(lllllllllllllllllIIlIIlIIIllIIII, lllllllllllllllllIIlIIlIIIlIlIll, lllllllllllllllllIIlIIlIIIlIlIlI);
        }
    }
    
    public CombinedTransition() {
        this.transitions = new ArrayList();
    }
    
    @Override
    public void preRender(final StateBasedGame lllllllllllllllllIIlIIlIIIIlllIl, final GameContainer lllllllllllllllllIIlIIlIIIIlllII, final Graphics lllllllllllllllllIIlIIlIIIIlllll) throws SlickException {
        for (int lllllllllllllllllIIlIIlIIIlIIIll = 0; lllllllllllllllllIIlIIlIIIlIIIll < this.transitions.size(); ++lllllllllllllllllIIlIIlIIIlIIIll) {
            this.transitions.get(lllllllllllllllllIIlIIlIIIlIIIll).postRender(lllllllllllllllllIIlIIlIIIIlllIl, lllllllllllllllllIIlIIlIIIIlllII, lllllllllllllllllIIlIIlIIIIlllll);
        }
    }
    
    public void addTransition(final Transition lllllllllllllllllIIlIIlIIIlllllI) {
        this.transitions.add(lllllllllllllllllIIlIIlIIIlllllI);
    }
    
    @Override
    public void init(final GameState lllllllllllllllllIIlIIIllllllllI, final GameState lllllllllllllllllIIlIIlIIIIIIIII) {
        for (int lllllllllllllllllIIlIIlIIIIIIIll = this.transitions.size() - 1; lllllllllllllllllIIlIIlIIIIIIIll >= 0; --lllllllllllllllllIIlIIlIIIIIIIll) {
            this.transitions.get(lllllllllllllllllIIlIIlIIIIIIIll).init(lllllllllllllllllIIlIIIllllllllI, lllllllllllllllllIIlIIlIIIIIIIII);
        }
    }
}
