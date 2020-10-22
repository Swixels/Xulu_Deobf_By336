package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class ClipTest extends BasicGame
{
    private /* synthetic */ boolean world;
    private /* synthetic */ boolean clip;
    private /* synthetic */ float ang;
    
    @Override
    public void keyPressed(final int lllllllllllllllllllllIlIIllIlIll, final char lllllllllllllllllllllIlIIllIlIlI) {
        if (lllllllllllllllllllllIlIIllIlIll == 2) {
            this.world = false;
            this.clip = false;
        }
        if (lllllllllllllllllllllIlIIllIlIll == 3) {
            this.world = false;
            this.clip = true;
        }
        if (lllllllllllllllllllllIlIIllIlIll == 4) {
            this.world = true;
            this.clip = false;
        }
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllllllIlIIllllIIl, final int lllllllllllllllllllllIlIIlllIllI) throws SlickException {
        this.ang += lllllllllllllllllllllIlIIlllIllI * 0.01f;
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllllllIlIIlllIIlI, final Graphics lllllllllllllllllllllIlIIlllIIIl) throws SlickException {
        lllllllllllllllllllllIlIIlllIIIl.setColor(Color.white);
        lllllllllllllllllllllIlIIlllIIIl.drawString("1 - No Clipping", 100.0f, 10.0f);
        lllllllllllllllllllllIlIIlllIIIl.drawString("2 - Screen Clipping", 100.0f, 30.0f);
        lllllllllllllllllllllIlIIlllIIIl.drawString("3 - World Clipping", 100.0f, 50.0f);
        if (this.world) {
            lllllllllllllllllllllIlIIlllIIIl.drawString("WORLD CLIPPING ENABLED", 200.0f, 80.0f);
        }
        if (this.clip) {
            lllllllllllllllllllllIlIIlllIIIl.drawString("SCREEN CLIPPING ENABLED", 200.0f, 80.0f);
        }
        lllllllllllllllllllllIlIIlllIIIl.rotate(400.0f, 400.0f, this.ang);
        if (this.world) {
            lllllllllllllllllllllIlIIlllIIIl.setWorldClip(350.0f, 302.0f, 100.0f, 196.0f);
        }
        if (this.clip) {
            lllllllllllllllllllllIlIIlllIIIl.setClip(350, 302, 100, 196);
        }
        lllllllllllllllllllllIlIIlllIIIl.setColor(Color.red);
        lllllllllllllllllllllIlIIlllIIIl.fillOval(300.0f, 300.0f, 200.0f, 200.0f);
        lllllllllllllllllllllIlIIlllIIIl.setColor(Color.blue);
        lllllllllllllllllllllIlIIlllIIIl.fillRect(390.0f, 200.0f, 20.0f, 400.0f);
        lllllllllllllllllllllIlIIlllIIIl.clearClip();
        lllllllllllllllllllllIlIIlllIIIl.clearWorldClip();
    }
    
    public static void main(final String[] lllllllllllllllllllllIlIIllIIlII) {
        try {
            final AppGameContainer lllllllllllllllllllllIlIIllIIllI = new AppGameContainer(new ClipTest());
            lllllllllllllllllllllIlIIllIIllI.setDisplayMode(800, 600, false);
            lllllllllllllllllllllIlIIllIIllI.start();
        }
        catch (SlickException lllllllllllllllllllllIlIIllIIlIl) {
            lllllllllllllllllllllIlIIllIIlIl.printStackTrace();
        }
    }
    
    public ClipTest() {
        super("Clip Test");
        this.ang = 0.0f;
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllllllIlIIlllllIl) throws SlickException {
    }
}
