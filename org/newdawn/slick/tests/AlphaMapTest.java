package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class AlphaMapTest extends BasicGame
{
    private /* synthetic */ Image textureMap;
    private /* synthetic */ Image alphaMap;
    
    public static void main(final String[] lllllllllllllllllllllllllllIllll) {
        try {
            final AppGameContainer llllllllllllllllllllllllllllIIll = new AppGameContainer(new AlphaMapTest());
            llllllllllllllllllllllllllllIIll.setDisplayMode(800, 600, false);
            llllllllllllllllllllllllllllIIll.start();
        }
        catch (SlickException llllllllllllllllllllllllllllIIIl) {
            llllllllllllllllllllllllllllIIIl.printStackTrace();
        }
    }
    
    public AlphaMapTest() {
        super("AlphaMap Test");
    }
    
    @Override
    public void init(final GameContainer llIIl) throws SlickException {
        this.alphaMap = new Image("testdata/alphamap.png");
        this.textureMap = new Image("testdata/grass.png");
        llIIl.getGraphics().setBackground(Color.black);
    }
    
    @Override
    public void render(final GameContainer I, final Graphics llllllllllllllllllllllllllllllll) throws SlickException {
        llllllllllllllllllllllllllllllll.clearAlphaMap();
        llllllllllllllllllllllllllllllll.setDrawMode(Graphics.MODE_NORMAL);
        this.textureMap.draw(10.0f, 50.0f);
        llllllllllllllllllllllllllllllll.setColor(Color.red);
        llllllllllllllllllllllllllllllll.fillRect(290.0f, 40.0f, 200.0f, 200.0f);
        llllllllllllllllllllllllllllllll.setColor(Color.white);
        llllllllllllllllllllllllllllllll.setDrawMode(Graphics.MODE_ALPHA_MAP);
        this.alphaMap.draw(300.0f, 50.0f);
        llllllllllllllllllllllllllllllll.setDrawMode(Graphics.MODE_ALPHA_BLEND);
        this.textureMap.draw(300.0f, 50.0f);
        llllllllllllllllllllllllllllllll.setDrawMode(Graphics.MODE_NORMAL);
    }
    
    @Override
    public void update(final GameContainer lIlll, final int lIllI) throws SlickException {
    }
    
    @Override
    public void keyPressed(final int lllllllllllllllllllllllllllllIll, final char lllllllllllllllllllllllllllllIlI) {
    }
}
