package org.newdawn.slick.tests.states;

import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.*;

public class TestState2 extends BasicGameState
{
    private /* synthetic */ StateBasedGame game;
    private /* synthetic */ Font font;
    private /* synthetic */ float ang;
    private /* synthetic */ Image image;
    
    @Override
    public void init(final GameContainer llllllllllllllllllIIlIIlIIIIIlll, final StateBasedGame llllllllllllllllllIIlIIlIIIIIlII) throws SlickException {
        this.game = llllllllllllllllllIIlIIlIIIIIlII;
        this.font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
        this.image = new Image("testdata/logo.tga");
    }
    
    @Override
    public void keyReleased(final int llllllllllllllllllIIlIIIlllIllIl, final char llllllllllllllllllIIlIIIlllIllll) {
        if (llllllllllllllllllIIlIIIlllIllIl == 2) {
            this.game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
        if (llllllllllllllllllIIlIIIlllIllIl == 4) {
            this.game.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
    }
    
    @Override
    public void render(final GameContainer llllllllllllllllllIIlIIlIIIIIIII, final StateBasedGame llllllllllllllllllIIlIIIllllllll, final Graphics llllllllllllllllllIIlIIIllllllII) {
        llllllllllllllllllIIlIIIllllllII.setFont(this.font);
        llllllllllllllllllIIlIIIllllllII.setColor(Color.green);
        llllllllllllllllllIIlIIIllllllII.drawString("This is State 2", 200.0f, 50.0f);
        llllllllllllllllllIIlIIIllllllII.rotate(400.0f, 300.0f, this.ang);
        llllllllllllllllllIIlIIIllllllII.drawImage(this.image, (float)(400 - this.image.getWidth() / 2), (float)(300 - this.image.getHeight() / 2));
    }
    
    @Override
    public void update(final GameContainer llllllllllllllllllIIlIIIlllllIII, final StateBasedGame llllllllllllllllllIIlIIIllllIlll, final int llllllllllllllllllIIlIIIllllIlII) {
        this.ang += llllllllllllllllllIIlIIIllllIlII * 0.1f;
    }
    
    @Override
    public int getID() {
        return 2;
    }
    
    static {
        ID = 2;
    }
}
