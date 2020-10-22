package org.newdawn.slick.particles.effects;

import org.newdawn.slick.particles.*;
import org.newdawn.slick.*;

public class FireEmitter implements ParticleEmitter
{
    private /* synthetic */ int y;
    private /* synthetic */ int x;
    private /* synthetic */ int interval;
    private /* synthetic */ int timer;
    private /* synthetic */ float size;
    
    @Override
    public void wrapUp() {
    }
    
    @Override
    public void updateParticle(final Particle lllllllllllllllllIIlllllllIIllIl, final int lllllllllllllllllIIlllllllIIlIII) {
        if (lllllllllllllllllIIlllllllIIllIl.getLife() > 600.0f) {
            lllllllllllllllllIIlllllllIIllIl.adjustSize(0.07f * lllllllllllllllllIIlllllllIIlIII);
        }
        else {
            lllllllllllllllllIIlllllllIIllIl.adjustSize(-0.04f * lllllllllllllllllIIlllllllIIlIII * (this.size / 40.0f));
        }
        final float lllllllllllllllllIIlllllllIIlIll = 0.002f * lllllllllllllllllIIlllllllIIlIII;
        lllllllllllllllllIIlllllllIIllIl.adjustColor(0.0f, -lllllllllllllllllIIlllllllIIlIll / 2.0f, -lllllllllllllllllIIlllllllIIlIll * 2.0f, -lllllllllllllllllIIlllllllIIlIll / 4.0f);
    }
    
    @Override
    public void setEnabled(final boolean lllllllllllllllllIIlllllllIIIlII) {
    }
    
    @Override
    public boolean isOriented() {
        return false;
    }
    
    public FireEmitter(final int lllllllllllllllllIIlllllllllIIlI, final int lllllllllllllllllIIlllllllllIIIl) {
        this.interval = 50;
        this.size = 40.0f;
        this.x = lllllllllllllllllIIlllllllllIIlI;
        this.y = lllllllllllllllllIIlllllllllIIIl;
    }
    
    @Override
    public boolean useAdditive() {
        return false;
    }
    
    @Override
    public void update(final ParticleSystem lllllllllllllllllIIlllllllIllIlI, final int lllllllllllllllllIIlllllllIlIllI) {
        this.timer -= lllllllllllllllllIIlllllllIlIllI;
        if (this.timer <= 0) {
            this.timer = this.interval;
            final Particle lllllllllllllllllIIlllllllIllllI = lllllllllllllllllIIlllllllIllIlI.getNewParticle(this, 1000.0f);
            lllllllllllllllllIIlllllllIllllI.setColor(1.0f, 1.0f, 1.0f, 0.5f);
            lllllllllllllllllIIlllllllIllllI.setPosition((float)this.x, (float)this.y);
            lllllllllllllllllIIlllllllIllllI.setSize(this.size);
            final float lllllllllllllllllIIlllllllIlllIl = (float)(-0.019999999552965164 + Math.random() * 0.03999999910593033);
            final float lllllllllllllllllIIlllllllIlllII = (float)(-(Math.random() * 0.15000000596046448));
            lllllllllllllllllIIlllllllIllllI.setVelocity(lllllllllllllllllIIlllllllIlllIl, lllllllllllllllllIIlllllllIlllII, 1.1f);
        }
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    @Override
    public boolean usePoints(final ParticleSystem lllllllllllllllllIIllllllIllllll) {
        return false;
    }
    
    @Override
    public boolean completed() {
        return false;
    }
    
    @Override
    public Image getImage() {
        return null;
    }
    
    public FireEmitter(final int lllllllllllllllllIIllllllllIlIll, final int lllllllllllllllllIIllllllllIlIlI, final float lllllllllllllllllIIllllllllIlIIl) {
        this.interval = 50;
        this.size = 40.0f;
        this.x = lllllllllllllllllIIllllllllIlIll;
        this.y = lllllllllllllllllIIllllllllIlIlI;
        this.size = lllllllllllllllllIIllllllllIlIIl;
    }
    
    public FireEmitter() {
        this.interval = 50;
        this.size = 40.0f;
    }
    
    @Override
    public void resetState() {
    }
}
