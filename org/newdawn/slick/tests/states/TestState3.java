package org.newdawn.slick.tests.states;

import org.newdawn.slick.state.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.transition.*;

public class TestState3 extends BasicGameState
{
    private /* synthetic */ int selected;
    private /* synthetic */ Font font;
    private /* synthetic */ StateBasedGame game;
    private /* synthetic */ String[] options;
    
    @Override
    public void update(final GameContainer llIIIlIIllllIl, final StateBasedGame llIIIlIIllllII, final int llIIIlIIlllIll) {
    }
    
    @Override
    public void render(final GameContainer llIIIlIlIIIlII, final StateBasedGame llIIIlIlIIIIll, final Graphics llIIIlIlIIIIlI) {
        llIIIlIlIIIIlI.setFont(this.font);
        llIIIlIlIIIIlI.setColor(Color.blue);
        llIIIlIlIIIIlI.drawString("This is State 3", 200.0f, 50.0f);
        llIIIlIlIIIIlI.setColor(Color.white);
        for (int llIIIlIlIIIllI = 0; llIIIlIlIIIllI < this.options.length; ++llIIIlIlIIIllI) {
            llIIIlIlIIIIlI.drawString(this.options[llIIIlIlIIIllI], (float)(400 - this.font.getWidth(this.options[llIIIlIlIIIllI]) / 2), (float)(200 + llIIIlIlIIIllI * 50));
            if (this.selected == llIIIlIlIIIllI) {
                llIIIlIlIIIIlI.drawRect(200.0f, (float)(190 + llIIIlIlIIIllI * 50), 400.0f, 50.0f);
            }
        }
    }
    
    @Override
    public void init(final GameContainer llIIIlIlIIllIl, final StateBasedGame llIIIlIlIIlIlI) throws SlickException {
        this.font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
        this.game = llIIIlIlIIlIlI;
    }
    
    @Override
    public void keyReleased(final int llIIIlIIllIlll, final char llIIIlIIllIllI) {
        if (llIIIlIIllIlll == 208) {
            ++this.selected;
            if (this.selected >= this.options.length) {
                this.selected = 0;
            }
        }
        if (llIIIlIIllIlll == 200) {
            --this.selected;
            if (this.selected < 0) {
                this.selected = this.options.length - 1;
            }
        }
        if (llIIIlIIllIlll == 2) {
            this.game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
        if (llIIIlIIllIlll == 3) {
            this.game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
    }
    
    static {
        ID = 3;
    }
    
    @Override
    public int getID() {
        return 3;
    }
    
    public TestState3() {
        this.options = new String[] { "Start Game", "Credits", "Highscores", "Instructions", "Exit" };
    }
}
