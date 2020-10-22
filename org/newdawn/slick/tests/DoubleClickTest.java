package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class DoubleClickTest extends BasicGame
{
    private /* synthetic */ String message;
    
    @Override
    public void update(final GameContainer lllllllllllllllllIIIIlIIIIllIIll, final int lllllllllllllllllIIIIlIIIIllIIlI) throws SlickException {
    }
    
    public DoubleClickTest() {
        super("Double Click Test");
        this.message = "Click or Double Click";
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIIIIlIIIIllIlIl) throws SlickException {
    }
    
    public static void main(final String[] lllllllllllllllllIIIIlIIIIlIIlll) {
        try {
            final AppGameContainer lllllllllllllllllIIIIlIIIIlIlIIl = new AppGameContainer(new DoubleClickTest());
            lllllllllllllllllIIIIlIIIIlIlIIl.setDisplayMode(800, 600, false);
            lllllllllllllllllIIIIlIIIIlIlIIl.start();
        }
        catch (SlickException lllllllllllllllllIIIIlIIIIlIlIII) {
            lllllllllllllllllIIIIlIIIIlIlIII.printStackTrace();
        }
    }
    
    @Override
    public void mouseClicked(final int lllllllllllllllllIIIIlIIIIIlllll, final int lllllllllllllllllIIIIlIIIIIllIIl, final int lllllllllllllllllIIIIlIIIIIllIII, final int lllllllllllllllllIIIIlIIIIIlllII) {
        if (lllllllllllllllllIIIIlIIIIIlllII == 1) {
            this.message = String.valueOf(new StringBuilder().append("Single Click: ").append(lllllllllllllllllIIIIlIIIIIlllll).append(" ").append(lllllllllllllllllIIIIlIIIIIllIIl).append(",").append(lllllllllllllllllIIIIlIIIIIllIII));
        }
        if (lllllllllllllllllIIIIlIIIIIlllII == 2) {
            this.message = String.valueOf(new StringBuilder().append("Double Click: ").append(lllllllllllllllllIIIIlIIIIIlllll).append(" ").append(lllllllllllllllllIIIIlIIIIIllIIl).append(",").append(lllllllllllllllllIIIIlIIIIIllIII));
        }
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIIIIlIIIIlIlllI, final Graphics lllllllllllllllllIIIIlIIIIlIllIl) throws SlickException {
        lllllllllllllllllIIIIlIIIIlIllIl.drawString(this.message, 100.0f, 100.0f);
    }
}
