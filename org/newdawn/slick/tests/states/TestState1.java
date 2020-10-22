package org.newdawn.slick.tests.states;

import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.*;

public class TestState1 extends BasicGameState
{
    private /* synthetic */ Font font;
    private /* synthetic */ StateBasedGame game;
    
    @Override
    public void update(final GameContainer lIllIIIl, final StateBasedGame lIllIIII, final int lIlIllll) {
    }
    
    @Override
    public void keyReleased(final int lIlIIIlI, final char lIlIIlII) {
        if (lIlIIIlI == 3) {
            final GameState lIlIlIIl = this.game.getState(2);
            final long lIlIlIII = System.currentTimeMillis();
            final CrossStateTransition lIlIIlll = new CrossStateTransition(lIlIlIIl) {
                @Override
                public void init(final GameState llllIlll, final GameState llllIllI) {
                }
                
                @Override
                public boolean isComplete() {
                    return System.currentTimeMillis() - lIlIlIII > 2000L;
                }
            };
            this.game.enterState(2, lIlIIlll, new EmptyTransition());
        }
        if (lIlIIIlI == 4) {
            this.game.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
    }
    
    @Override
    public int getID() {
        return 1;
    }
    
    @Override
    public void render(final GameContainer lIllIlll, final StateBasedGame lIllIllI, final Graphics lIllIlIl) {
        lIllIlIl.setFont(this.font);
        lIllIlIl.setColor(Color.white);
        lIllIlIl.drawString("State Based Game Test", 100.0f, 100.0f);
        lIllIlIl.drawString("Numbers 1-3 will switch between states.", 150.0f, 300.0f);
        lIllIlIl.setColor(Color.red);
        lIllIlIl.drawString("This is State 1", 200.0f, 50.0f);
    }
    
    @Override
    public void init(final GameContainer lIlllllI, final StateBasedGame lIlllIll) throws SlickException {
        this.game = lIlllIll;
        this.font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
    }
    
    static {
        ID = 1;
    }
}
