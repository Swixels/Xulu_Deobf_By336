package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class AntiAliasTest extends BasicGame
{
    public static void main(final String[] llIllIIIlllII) {
        try {
            final AppGameContainer llIllIIIllllI = new AppGameContainer(new AntiAliasTest());
            llIllIIIllllI.setDisplayMode(800, 600, false);
            llIllIIIllllI.start();
        }
        catch (SlickException llIllIIIlllIl) {
            llIllIIIlllIl.printStackTrace();
        }
    }
    
    public AntiAliasTest() {
        super("AntiAlias Test");
    }
    
    @Override
    public void update(final GameContainer llIllIIlIIllI, final int llIllIIlIIlIl) throws SlickException {
    }
    
    @Override
    public void render(final GameContainer llIllIIlIIIlI, final Graphics llIllIIlIIIIl) throws SlickException {
        llIllIIlIIIIl.setAntiAlias(true);
        llIllIIlIIIIl.setColor(Color.red);
        llIllIIlIIIIl.drawOval(100.0f, 100.0f, 100.0f, 100.0f);
        llIllIIlIIIIl.fillOval(300.0f, 100.0f, 100.0f, 100.0f);
        llIllIIlIIIIl.setAntiAlias(false);
        llIllIIlIIIIl.setColor(Color.red);
        llIllIIlIIIIl.drawOval(100.0f, 300.0f, 100.0f, 100.0f);
        llIllIIlIIIIl.fillOval(300.0f, 300.0f, 100.0f, 100.0f);
    }
    
    @Override
    public void init(final GameContainer llIllIIlIlIIl) throws SlickException {
        llIllIIlIlIIl.getGraphics().setBackground(Color.green);
    }
}
