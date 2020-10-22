package org.newdawn.slick.tests;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tests.states.*;

public class StateBasedTest extends StateBasedGame
{
    public static void main(final String[] llllllllllllllllIlIIlllIllIIIIIl) {
        try {
            final AppGameContainer llllllllllllllllIlIIlllIllIIIIll = new AppGameContainer(new StateBasedTest());
            llllllllllllllllIlIIlllIllIIIIll.setDisplayMode(800, 600, false);
            llllllllllllllllIlIIlllIllIIIIll.start();
        }
        catch (SlickException llllllllllllllllIlIIlllIllIIIIlI) {
            llllllllllllllllIlIIlllIllIIIIlI.printStackTrace();
        }
    }
    
    public StateBasedTest() {
        super("State Based Test");
    }
    
    @Override
    public void initStatesList(final GameContainer llllllllllllllllIlIIlllIllIIIllI) {
        this.addState(new TestState1());
        this.addState(new TestState2());
        this.addState(new TestState3());
    }
}
