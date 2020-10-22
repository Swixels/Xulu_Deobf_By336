package org.newdawn.slick.tests;

import org.newdawn.slick.*;
import org.newdawn.slick.particles.*;
import java.io.*;

public class PedigreeTest extends BasicGame
{
    private /* synthetic */ ParticleSystem trail;
    private /* synthetic */ Image image;
    private /* synthetic */ float ry;
    private /* synthetic */ GameContainer container;
    private /* synthetic */ float rx;
    private /* synthetic */ ParticleSystem fire;
    
    private void spawnRocket() {
        this.ry = 700.0f;
        this.rx = (float)(Math.random() * 600.0 + 100.0);
    }
    
    public PedigreeTest() {
        super("Pedigree Test");
        this.ry = 900.0f;
    }
    
    @Override
    public void update(final GameContainer lIllIIIlllllII, final int lIllIIIllllIll) {
        this.fire.update(lIllIIIllllIll);
        this.trail.update(lIllIIIllllIll);
        this.ry -= lIllIIIllllIll * 0.25f;
        if (this.ry < -100.0f) {
            this.spawnRocket();
        }
    }
    
    @Override
    public void keyPressed(final int lIllIIIllIIIIl, final char lIllIIIllIIIII) {
        if (lIllIIIllIIIIl == 1) {
            this.container.exit();
        }
    }
    
    public static void main(final String[] lIllIIIllIIllI) {
        try {
            final AppGameContainer lIllIIIllIlIII = new AppGameContainer(new PedigreeTest());
            lIllIIIllIlIII.setDisplayMode(800, 600, false);
            lIllIIIllIlIII.start();
        }
        catch (SlickException lIllIIIllIIlll) {
            lIllIIIllIIlll.printStackTrace();
        }
    }
    
    @Override
    public void mousePressed(final int lIllIIIllIllIl, final int lIllIIIllIllII, final int lIllIIIllIllll) {
        super.mousePressed(lIllIIIllIllIl, lIllIIIllIllII, lIllIIIllIllll);
        for (int lIllIIIlllIIll = 0; lIllIIIlllIIll < this.fire.getEmitterCount(); ++lIllIIIlllIIll) {
            ((ConfigurableEmitter)this.fire.getEmitter(lIllIIIlllIIll)).setPosition((float)(lIllIIIllIllII - 400), (float)(lIllIIIllIllll - 300), true);
        }
    }
    
    @Override
    public void render(final GameContainer lIllIIlIIIIIll, final Graphics lIllIIlIIIIIII) {
        ((ConfigurableEmitter)this.trail.getEmitter(0)).setPosition(this.rx + 14.0f, this.ry + 35.0f);
        this.trail.render();
        this.image.draw((float)(int)this.rx, (float)(int)this.ry);
        lIllIIlIIIIIII.translate(400.0f, 300.0f);
        this.fire.render();
    }
    
    @Override
    public void init(final GameContainer lIllIIlIIIlIll) throws SlickException {
        this.container = lIllIIlIIIlIll;
        try {
            this.fire = ParticleIO.loadConfiguredSystem("testdata/system.xml");
            this.trail = ParticleIO.loadConfiguredSystem("testdata/smoketrail.xml");
        }
        catch (IOException lIllIIlIIIllll) {
            throw new SlickException("Failed to load particle systems", lIllIIlIIIllll);
        }
        this.image = new Image("testdata/rocket.png");
        this.spawnRocket();
    }
}
