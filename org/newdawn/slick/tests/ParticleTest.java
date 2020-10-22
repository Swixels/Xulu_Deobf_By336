package org.newdawn.slick.tests;

import org.newdawn.slick.particles.effects.*;
import org.newdawn.slick.particles.*;
import org.newdawn.slick.*;

public class ParticleTest extends BasicGame
{
    private /* synthetic */ ParticleSystem system;
    private /* synthetic */ int mode;
    
    @Override
    public void update(final GameContainer llllllllllllllllllIllIIIlIIllIIl, final int llllllllllllllllllIllIIIlIIllIII) {
        this.system.update(llllllllllllllllllIllIIIlIIllIII);
    }
    
    @Override
    public void init(final GameContainer llllllllllllllllllIllIIIlIlIlIlI) throws SlickException {
        final Image llllllllllllllllllIllIIIlIlIlIIl = new Image("testdata/particle.tga", true);
        this.system = new ParticleSystem(llllllllllllllllllIllIIIlIlIlIIl);
        this.system.addEmitter(new FireEmitter(400, 300, 45.0f));
        this.system.addEmitter(new FireEmitter(200, 300, 60.0f));
        this.system.addEmitter(new FireEmitter(600, 300, 30.0f));
    }
    
    @Override
    public void keyPressed(final int llllllllllllllllllIllIIIlIIIllll, final char llllllllllllllllllIllIIIlIIlIIIl) {
        if (llllllllllllllllllIllIIIlIIIllll == 1) {
            System.exit(0);
        }
        if (llllllllllllllllllIllIIIlIIIllll == 57) {
            this.mode = ((1 == this.mode) ? 2 : 1);
            this.system.setBlendingMode(this.mode);
        }
    }
    
    @Override
    public void render(final GameContainer llllllllllllllllllIllIIIlIlIIIIl, final Graphics llllllllllllllllllIllIIIlIIllllI) {
        for (int llllllllllllllllllIllIIIlIlIIIll = 0; llllllllllllllllllIllIIIlIlIIIll < 100; ++llllllllllllllllllIllIIIlIlIIIll) {
            llllllllllllllllllIllIIIlIIllllI.translate(1.0f, 1.0f);
            this.system.render();
        }
        llllllllllllllllllIllIIIlIIllllI.resetTransform();
        llllllllllllllllllIllIIIlIIllllI.drawString("Press space to toggle blending mode", 200.0f, 500.0f);
        llllllllllllllllllIllIIIlIIllllI.drawString(String.valueOf(new StringBuilder().append("Particle Count: ").append(this.system.getParticleCount() * 100)), 200.0f, 520.0f);
    }
    
    public ParticleTest() {
        super("Particle Test");
        this.mode = 2;
    }
    
    public static void main(final String[] llllllllllllllllllIllIIIlIIIlIll) {
        try {
            final AppGameContainer llllllllllllllllllIllIIIlIIIllIl = new AppGameContainer(new ParticleTest());
            llllllllllllllllllIllIIIlIIIllIl.setDisplayMode(800, 600, false);
            llllllllllllllllllIllIIIlIIIllIl.start();
        }
        catch (SlickException llllllllllllllllllIllIIIlIIIllII) {
            llllllllllllllllllIllIIIlIIIllII.printStackTrace();
        }
    }
}
