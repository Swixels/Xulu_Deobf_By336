package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class CopyAreaAlphaTest extends BasicGame
{
    private /* synthetic */ Image copy;
    private /* synthetic */ Image textureMap;
    
    public static void main(final String[] llllIIllIllIIII) {
        try {
            final AppGameContainer llllIIllIllIIlI = new AppGameContainer(new CopyAreaAlphaTest());
            llllIIllIllIIlI.setDisplayMode(800, 600, false);
            llllIIllIllIIlI.start();
        }
        catch (SlickException llllIIllIllIIIl) {
            llllIIllIllIIIl.printStackTrace();
        }
    }
    
    @Override
    public void keyPressed(final int llllIIllIllIlIl, final char llllIIllIllIlII) {
    }
    
    @Override
    public void update(final GameContainer llllIIllIllllll, final int llllIIllIlllllI) throws SlickException {
    }
    
    public CopyAreaAlphaTest() {
        super("CopyArea Alpha Test");
    }
    
    @Override
    public void render(final GameContainer llllIIllIlllIlI, final Graphics llllIIllIllIlll) throws SlickException {
        llllIIllIllIlll.clearAlphaMap();
        llllIIllIllIlll.setDrawMode(Graphics.MODE_NORMAL);
        llllIIllIllIlll.setColor(Color.white);
        llllIIllIllIlll.fillOval(100.0f, 100.0f, 150.0f, 150.0f);
        this.textureMap.draw(10.0f, 50.0f);
        llllIIllIllIlll.copyArea(this.copy, 100, 100);
        llllIIllIllIlll.setColor(Color.red);
        llllIIllIllIlll.fillRect(300.0f, 100.0f, 200.0f, 200.0f);
        this.copy.draw(350.0f, 150.0f);
    }
    
    @Override
    public void init(final GameContainer llllIIlllIIIIIl) throws SlickException {
        this.textureMap = new Image("testdata/grass.png");
        llllIIlllIIIIIl.getGraphics().setBackground(Color.black);
        this.copy = new Image(100, 100);
    }
}
