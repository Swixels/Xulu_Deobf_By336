package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class KeyRepeatTest extends BasicGame
{
    private /* synthetic */ Input input;
    private /* synthetic */ int count;
    
    public KeyRepeatTest() {
        super("KeyRepeatTest");
    }
    
    @Override
    public void render(final GameContainer llIIIIlIlIllIII, final Graphics llIIIIlIlIlIlll) {
        llIIIIlIlIlIlll.drawString(String.valueOf(new StringBuilder().append("Key Press Count: ").append(this.count)), 100.0f, 100.0f);
        llIIIIlIlIlIlll.drawString("Press Space to Toggle Key Repeat", 100.0f, 150.0f);
        llIIIIlIlIlIlll.drawString(String.valueOf(new StringBuilder().append("Key Repeat Enabled: ").append(this.input.isKeyRepeatEnabled())), 100.0f, 200.0f);
    }
    
    @Override
    public void init(final GameContainer llIIIIlIlIllllI) throws SlickException {
        this.input = llIIIIlIlIllllI.getInput();
        this.input.enableKeyRepeat(300, 100);
    }
    
    @Override
    public void keyPressed(final int llIIIIlIlIIIllI, final char llIIIIlIlIIlIII) {
        ++this.count;
        if (llIIIIlIlIIIllI == 57) {
            if (this.input.isKeyRepeatEnabled()) {
                this.input.disableKeyRepeat();
            }
            else {
                this.input.enableKeyRepeat(300, 100);
            }
        }
    }
    
    public static void main(final String[] llIIIIlIlIIlllI) {
        try {
            final AppGameContainer llIIIIlIlIlIIII = new AppGameContainer(new KeyRepeatTest());
            llIIIIlIlIlIIII.setDisplayMode(800, 600, false);
            llIIIIlIlIlIIII.start();
        }
        catch (SlickException llIIIIlIlIIllll) {
            llIIIIlIlIIllll.printStackTrace();
        }
    }
    
    @Override
    public void update(final GameContainer llIIIIlIlIlIIll, final int llIIIIlIlIlIIlI) {
    }
}
