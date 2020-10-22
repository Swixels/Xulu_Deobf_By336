package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class ScalableTest extends BasicGame
{
    @Override
    public void update(final GameContainer lllllllllllllllllIlIlIlIIlIllIlI, final int lllllllllllllllllIlIlIlIIlIllIIl) throws SlickException {
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIlIlIlIIlIlllII) throws SlickException {
    }
    
    public static void main(final String[] lllllllllllllllllIlIlIlIIlIIllII) {
        try {
            final ScalableGame lllllllllllllllllIlIlIlIIlIIllll = new ScalableGame(new ScalableTest(), 1024, 568, true) {
                @Override
                protected void renderOverlay(final GameContainer lIIIIIllllIIlI, final Graphics lIIIIIlllIllll) {
                    lIIIIIlllIllll.setColor(Color.white);
                    lIIIIIlllIllll.drawString("Outside The Game", 350.0f, 10.0f);
                    lIIIIIlllIllll.drawString(String.valueOf(new StringBuilder().append(lIIIIIllllIIlI.getInput().getMouseX()).append(",").append(lIIIIIllllIIlI.getInput().getMouseY())), 400.0f, 20.0f);
                }
            };
            final AppGameContainer lllllllllllllllllIlIlIlIIlIIlllI = new AppGameContainer(lllllllllllllllllIlIlIlIIlIIllll);
            lllllllllllllllllIlIlIlIIlIIlllI.setDisplayMode(800, 600, false);
            lllllllllllllllllIlIlIlIIlIIlllI.start();
        }
        catch (SlickException lllllllllllllllllIlIlIlIIlIIllIl) {
            lllllllllllllllllIlIlIlIIlIIllIl.printStackTrace();
        }
    }
    
    public ScalableTest() {
        super("Scalable Test For Widescreen");
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIlIlIlIIlIlIlIl, final Graphics lllllllllllllllllIlIlIlIIlIlIlII) throws SlickException {
        lllllllllllllllllIlIlIlIIlIlIlII.setColor(new Color(0.4f, 0.6f, 0.8f));
        lllllllllllllllllIlIlIlIIlIlIlII.fillRect(0.0f, 0.0f, 1024.0f, 568.0f);
        lllllllllllllllllIlIlIlIIlIlIlII.setColor(Color.white);
        lllllllllllllllllIlIlIlIIlIlIlII.drawRect(5.0f, 5.0f, 1014.0f, 558.0f);
        lllllllllllllllllIlIlIlIIlIlIlII.setColor(Color.white);
        lllllllllllllllllIlIlIlIIlIlIlII.drawString(String.valueOf(new StringBuilder().append(lllllllllllllllllIlIlIlIIlIlIlIl.getInput().getMouseX()).append(",").append(lllllllllllllllllIlIlIlIIlIlIlIl.getInput().getMouseY())), 10.0f, 400.0f);
        lllllllllllllllllIlIlIlIIlIlIlII.setColor(Color.red);
        lllllllllllllllllIlIlIlIIlIlIlII.fillOval((float)(lllllllllllllllllIlIlIlIIlIlIlIl.getInput().getMouseX() - 10), (float)(lllllllllllllllllIlIlIlIIlIlIlIl.getInput().getMouseY() - 10), 20.0f, 20.0f);
    }
}
