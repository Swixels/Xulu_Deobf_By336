package org.newdawn.slick.particles;

import org.newdawn.slick.*;

public interface ParticleEmitter
{
    boolean completed();
    
    void update(final ParticleSystem p0, final int p1);
    
    void updateParticle(final Particle p0, final int p1);
    
    boolean isOriented();
    
    void wrapUp();
    
    void resetState();
    
    boolean usePoints(final ParticleSystem p0);
    
    Image getImage();
    
    boolean isEnabled();
    
    boolean useAdditive();
    
    void setEnabled(final boolean p0);
}
