package org.newdawn.slick.tests;

import org.newdawn.slick.particles.*;
import java.io.*;
import org.newdawn.slick.*;

public class DuplicateEmitterTest extends BasicGame
{
    private /* synthetic */ GameContainer container;
    private /* synthetic */ ParticleSystem explosionSystem;
    private /* synthetic */ ConfigurableEmitter explosionEmitter;
    
    @Override
    public void keyPressed(final int lllllllllllllllllIIIIIllIlIlIIlI, final char lllllllllllllllllIIIIIllIlIlIIIl) {
        if (lllllllllllllllllIIIIIllIlIlIIlI == 1) {
            this.container.exit();
        }
        if (lllllllllllllllllIIIIIllIlIlIIlI == 37) {
            this.explosionEmitter.wrapUp();
        }
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIIIIIllIlIllIII, final Graphics lllllllllllllllllIIIIIllIlIlIlll) throws SlickException {
        this.explosionSystem.render();
    }
    
    public DuplicateEmitterTest() {
        super("DuplicateEmitterTest");
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIIIIIllIlIllllI, final int lllllllllllllllllIIIIIllIlIlllIl) throws SlickException {
        this.explosionSystem.update(lllllllllllllllllIIIIIllIlIlllIl);
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIIIIIllIllIIllI) throws SlickException {
        this.container = lllllllllllllllllIIIIIllIllIIllI;
        try {
            this.explosionSystem = ParticleIO.loadConfiguredSystem("testdata/endlessexplosion.xml");
            this.explosionEmitter = (ConfigurableEmitter)this.explosionSystem.getEmitter(0);
            this.explosionEmitter.setPosition(400.0f, 100.0f);
            for (int lllllllllllllllllIIIIIllIllIlIIl = 0; lllllllllllllllllIIIIIllIllIlIIl < 5; ++lllllllllllllllllIIIIIllIllIlIIl) {
                final ConfigurableEmitter lllllllllllllllllIIIIIllIllIlIlI = this.explosionEmitter.duplicate();
                if (lllllllllllllllllIIIIIllIllIlIlI == null) {
                    throw new SlickException("Failed to duplicate explosionEmitter");
                }
                lllllllllllllllllIIIIIllIllIlIlI.name = String.valueOf(new StringBuilder().append(lllllllllllllllllIIIIIllIllIlIlI.name).append("_").append(lllllllllllllllllIIIIIllIllIlIIl));
                lllllllllllllllllIIIIIllIllIlIlI.setPosition((float)((lllllllllllllllllIIIIIllIllIlIIl + 1) * 133), 400.0f);
                this.explosionSystem.addEmitter(lllllllllllllllllIIIIIllIllIlIlI);
            }
        }
        catch (IOException lllllllllllllllllIIIIIllIllIlIII) {
            throw new SlickException("Failed to load particle systems", lllllllllllllllllIIIIIllIllIlIII);
        }
    }
    
    public static void main(final String[] lllllllllllllllllIIIIIllIlIIlIll) {
        try {
            final AppGameContainer lllllllllllllllllIIIIIllIlIIllIl = new AppGameContainer(new DuplicateEmitterTest());
            lllllllllllllllllIIIIIllIlIIllIl.setDisplayMode(800, 600, false);
            lllllllllllllllllIIIIIllIlIIllIl.start();
        }
        catch (SlickException lllllllllllllllllIIIIIllIlIIllII) {
            lllllllllllllllllIIIIIllIlIIllII.printStackTrace();
        }
    }
}
