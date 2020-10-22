package org.newdawn.slick.particles;

import org.newdawn.slick.util.*;
import org.newdawn.slick.*;
import java.security.*;
import org.newdawn.slick.opengl.renderer.*;
import java.util.*;
import java.io.*;
import org.newdawn.slick.opengl.*;

public class ParticleSystem
{
    private /* synthetic */ boolean removeCompletedEmitters;
    private /* synthetic */ float y;
    private /* synthetic */ boolean visible;
    private /* synthetic */ ArrayList removeMe;
    private /* synthetic */ int blendingMode;
    protected /* synthetic */ HashMap particlesByEmitter;
    protected /* synthetic */ ArrayList emitters;
    private /* synthetic */ Image sprite;
    private /* synthetic */ boolean usePoints;
    private /* synthetic */ float x;
    protected /* synthetic */ SGL GL;
    private /* synthetic */ Color mask;
    private /* synthetic */ String defaultImageName;
    protected /* synthetic */ int maxParticlesPerEmitter;
    protected /* synthetic */ Particle dummy;
    private /* synthetic */ int pCount;
    
    static {
        BLEND_COMBINE = 2;
        DEFAULT_PARTICLES = 100;
        BLEND_ADDITIVE = 1;
    }
    
    public float getPositionX() {
        return this.x;
    }
    
    public ParticleSystem(final String llllllllllllllllIllIIIlllIIlIllI, final int llllllllllllllllIllIIIlllIIllIII) {
        this(llllllllllllllllIllIIIlllIIlIllI, llllllllllllllllIllIIIlllIIllIII, null);
    }
    
    public void setRemoveCompletedEmitters(final boolean llllllllllllllllIllIIIlllIlIlIlI) {
        this.removeCompletedEmitters = llllllllllllllllIllIIIlllIlIlIlI;
    }
    
    public boolean usePoints() {
        return this.usePoints;
    }
    
    public void moveAll(final ParticleEmitter llllllllllllllllIllIIIlIIllIlIlI, final float llllllllllllllllIllIIIlIIllIlIIl, final float llllllllllllllllIllIIIlIIllIIIll) {
        final ParticlePool llllllllllllllllIllIIIlIIllIIlll = this.particlesByEmitter.get(llllllllllllllllIllIIIlIIllIlIlI);
        for (int llllllllllllllllIllIIIlIIllIllII = 0; llllllllllllllllIllIIIlIIllIllII < llllllllllllllllIllIIIlIIllIIlll.particles.length; ++llllllllllllllllIllIIIlIIllIllII) {
            if (llllllllllllllllIllIIIlIIllIIlll.particles[llllllllllllllllIllIIIlIIllIllII].inUse()) {
                llllllllllllllllIllIIIlIIllIIlll.particles[llllllllllllllllIllIIIlIIllIllII].move(llllllllllllllllIllIIIlIIllIlIIl, llllllllllllllllIllIIIlIIllIIIll);
            }
        }
    }
    
    public void removeEmitter(final ParticleEmitter llllllllllllllllIllIIIllIIlIllll) {
        this.emitters.remove(llllllllllllllllIllIIIllIIlIllll);
        this.particlesByEmitter.remove(llllllllllllllllIllIIIllIIlIllll);
    }
    
    public void setBlendingMode(final int llllllllllllllllIllIIIllIlIIllIl) {
        this.blendingMode = llllllllllllllllIllIIIllIlIIllIl;
    }
    
    protected Particle createParticle(final ParticleSystem llllllllllllllllIllIIIllIlIlIllI) {
        return new Particle(llllllllllllllllIllIIIllIlIlIllI);
    }
    
    public void setPosition(final float llllllllllllllllIllIIIllIIIIlllI, final float llllllllllllllllIllIIIllIIIIllII) {
        this.x = llllllllllllllllIllIIIllIIIIlllI;
        this.y = llllllllllllllllIllIIIllIIIIllII;
    }
    
    private void loadSystemParticleImage() {
        AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
            @Override
            public Object run() {
                try {
                    if (ParticleSystem.this.mask != null) {
                        ParticleSystem.this.sprite = new Image(ParticleSystem.this.defaultImageName, ParticleSystem.this.mask);
                    }
                    else {
                        ParticleSystem.this.sprite = new Image(ParticleSystem.this.defaultImageName);
                    }
                }
                catch (SlickException llllllllllllllllllllIlIIIllllIII) {
                    Log.error(llllllllllllllllllllIlIIIllllIII);
                    ParticleSystem.this.defaultImageName = null;
                }
                return null;
            }
        });
    }
    
    public ParticleSystem(final Image llllllllllllllllIllIIIllllllIlII) {
        this(llllllllllllllllIllIIIllllllIlII, 100);
    }
    
    public void release(final Particle llllllllllllllllIllIIIlIlIIIlIlI) {
        if (llllllllllllllllIllIIIlIlIIIlIlI != this.dummy) {
            final ParticlePool llllllllllllllllIllIIIlIlIIIlllI = this.particlesByEmitter.get(llllllllllllllllIllIIIlIlIIIlIlI.getEmitter());
            llllllllllllllllIllIIIlIlIIIlllI.available.add(llllllllllllllllIllIIIlIlIIIlIlI);
        }
    }
    
    public void reset() {
        for (final ParticlePool llllllllllllllllIllIIIllllIlllII : this.particlesByEmitter.values()) {
            llllllllllllllllIllIIIllllIlllII.reset(this);
        }
        for (int llllllllllllllllIllIIIllllIllIIl = 0; llllllllllllllllIllIIIllllIllIIl < this.emitters.size(); ++llllllllllllllllIllIIIllllIllIIl) {
            final ParticleEmitter llllllllllllllllIllIIIllllIllIll = this.emitters.get(llllllllllllllllIllIIIllllIllIIl);
            llllllllllllllllIllIIIllllIllIll.resetState();
        }
    }
    
    public static void setRelativePath(final String llllllllllllllllIllIIIllllllllIl) {
        ConfigurableEmitter.setRelativePath(llllllllllllllllIllIIIllllllllIl);
    }
    
    public void setVisible(final boolean llllllllllllllllIllIIIlllIllIllI) {
        this.visible = llllllllllllllllIllIIIlllIllIllI;
    }
    
    public int getBlendingMode() {
        return this.blendingMode;
    }
    
    public void setDefaultImageName(final String llllllllllllllllIllIIIllIllIIIIl) {
        this.defaultImageName = llllllllllllllllIllIIIllIllIIIIl;
        this.sprite = null;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public ParticleSystem(final String llllllllllllllllIllIIIlllIIIIIlI, final int llllllllllllllllIllIIIlllIIIIIIl, final Color llllllllllllllllIllIIIlllIIIIIII) {
        this.GL = Renderer.get();
        this.removeMe = new ArrayList();
        this.particlesByEmitter = new HashMap();
        this.emitters = new ArrayList();
        this.blendingMode = 2;
        this.removeCompletedEmitters = true;
        this.visible = true;
        this.maxParticlesPerEmitter = llllllllllllllllIllIIIlllIIIIIIl;
        this.mask = llllllllllllllllIllIIIlllIIIIIII;
        this.setDefaultImageName(llllllllllllllllIllIIIlllIIIIIlI);
        this.dummy = this.createParticle(this);
    }
    
    public void setUsePoints(final boolean llllllllllllllllIllIIIlllIlIIIIl) {
        this.usePoints = llllllllllllllllIllIIIlllIlIIIIl;
    }
    
    public void update(final int llllllllllllllllIllIIIlIlIllllIl) {
        if (this.sprite == null && this.defaultImageName != null) {
            this.loadSystemParticleImage();
        }
        this.removeMe.clear();
        final ArrayList llllllllllllllllIllIIIlIlIllllll = new ArrayList(this.emitters);
        for (int llllllllllllllllIllIIIlIllIIlIlI = 0; llllllllllllllllIllIIIlIllIIlIlI < llllllllllllllllIllIIIlIlIllllll.size(); ++llllllllllllllllIllIIIlIllIIlIlI) {
            final ParticleEmitter llllllllllllllllIllIIIlIllIIlIll = llllllllllllllllIllIIIlIlIllllll.get(llllllllllllllllIllIIIlIllIIlIlI);
            if (llllllllllllllllIllIIIlIllIIlIll.isEnabled()) {
                llllllllllllllllIllIIIlIllIIlIll.update(this, llllllllllllllllIllIIIlIlIllllIl);
                if (this.removeCompletedEmitters && llllllllllllllllIllIIIlIllIIlIll.completed()) {
                    this.removeMe.add(llllllllllllllllIllIIIlIllIIlIll);
                    this.particlesByEmitter.remove(llllllllllllllllIllIIIlIllIIlIll);
                }
            }
        }
        this.emitters.removeAll(this.removeMe);
        this.pCount = 0;
        if (!this.particlesByEmitter.isEmpty()) {
            for (final ParticleEmitter llllllllllllllllIllIIIlIllIIIllI : this.particlesByEmitter.keySet()) {
                if (llllllllllllllllIllIIIlIllIIIllI.isEnabled()) {
                    final ParticlePool llllllllllllllllIllIIIlIllIIlIII = this.particlesByEmitter.get(llllllllllllllllIllIIIlIllIIIllI);
                    for (int llllllllllllllllIllIIIlIllIIlIIl = 0; llllllllllllllllIllIIIlIllIIlIIl < llllllllllllllllIllIIIlIllIIlIII.particles.length; ++llllllllllllllllIllIIIlIllIIlIIl) {
                        if (llllllllllllllllIllIIIlIllIIlIII.particles[llllllllllllllllIllIIIlIllIIlIIl].life > 0.0f) {
                            llllllllllllllllIllIIIlIllIIlIII.particles[llllllllllllllllIllIIIlIllIIlIIl].update(llllllllllllllllIllIIIlIlIllllIl);
                            ++this.pCount;
                        }
                    }
                }
            }
        }
    }
    
    public void releaseAll(final ParticleEmitter llllllllllllllllIllIIIlIIllllIll) {
        if (!this.particlesByEmitter.isEmpty()) {
            for (final ParticlePool llllllllllllllllIllIIIlIIllllllI : this.particlesByEmitter.values()) {
                for (int llllllllllllllllIllIIIlIIlllllll = 0; llllllllllllllllIllIIIlIIlllllll < llllllllllllllllIllIIIlIIllllllI.particles.length; ++llllllllllllllllIllIIIlIIlllllll) {
                    if (llllllllllllllllIllIIIlIIllllllI.particles[llllllllllllllllIllIIIlIIlllllll].inUse() && llllllllllllllllIllIIIlIIllllllI.particles[llllllllllllllllIllIIIlIIlllllll].getEmitter() == llllllllllllllllIllIIIlIIllllIll) {
                        llllllllllllllllIllIIIlIIllllllI.particles[llllllllllllllllIllIIIlIIlllllll].setLife(-1.0f);
                        this.release(llllllllllllllllIllIIIlIIllllllI.particles[llllllllllllllllIllIIIlIIlllllll]);
                    }
                }
            }
        }
    }
    
    public ParticleSystem(final Image llllllllllllllllIllIIIllIlllIIII, final int llllllllllllllllIllIIIllIllIlllI) {
        this.GL = Renderer.get();
        this.removeMe = new ArrayList();
        this.particlesByEmitter = new HashMap();
        this.emitters = new ArrayList();
        this.blendingMode = 2;
        this.removeCompletedEmitters = true;
        this.visible = true;
        this.maxParticlesPerEmitter = llllllllllllllllIllIIIllIllIlllI;
        this.sprite = llllllllllllllllIllIIIllIlllIIII;
        this.dummy = this.createParticle(this);
    }
    
    public int getEmitterCount() {
        return this.emitters.size();
    }
    
    public void addEmitter(final ParticleEmitter llllllllllllllllIllIIIllIIllIlII) {
        this.emitters.add(llllllllllllllllIllIIIllIIllIlII);
        final ParticlePool llllllllllllllllIllIIIllIIllIllI = new ParticlePool(this, this.maxParticlesPerEmitter);
        this.particlesByEmitter.put(llllllllllllllllIllIIIllIIllIlII, llllllllllllllllIllIIIllIIllIllI);
    }
    
    public void render() {
        this.render(this.x, this.y);
    }
    
    public ParticleSystem duplicate() throws SlickException {
        for (int llllllllllllllllIllIIIlIIlIlllII = 0; llllllllllllllllIllIIIlIIlIlllII < this.emitters.size(); ++llllllllllllllllIllIIIlIIlIlllII) {
            if (!(this.emitters.get(llllllllllllllllIllIIIlIIlIlllII) instanceof ConfigurableEmitter)) {
                throw new SlickException("Only systems contianing configurable emitters can be duplicated");
            }
        }
        ParticleSystem llllllllllllllllIllIIIlIIlIlIlll = null;
        try {
            final ByteArrayOutputStream llllllllllllllllIllIIIlIIlIllIll = new ByteArrayOutputStream();
            ParticleIO.saveConfiguredSystem(llllllllllllllllIllIIIlIIlIllIll, this);
            final ByteArrayInputStream llllllllllllllllIllIIIlIIlIllIlI = new ByteArrayInputStream(llllllllllllllllIllIIIlIIlIllIll.toByteArray());
            llllllllllllllllIllIIIlIIlIlIlll = ParticleIO.loadConfiguredSystem(llllllllllllllllIllIIIlIIlIllIlI);
        }
        catch (IOException llllllllllllllllIllIIIlIIlIllIIl) {
            Log.error("Failed to duplicate particle system");
            throw new SlickException("Unable to duplicated particle system", llllllllllllllllIllIIIlIIlIllIIl);
        }
        return llllllllllllllllIllIIIlIIlIlIlll;
    }
    
    public ParticleSystem(final String llllllllllllllllIllIIIlllllIlIII) {
        this(llllllllllllllllIllIIIlllllIlIII, 100);
    }
    
    public int getParticleCount() {
        return this.pCount;
    }
    
    public Particle getNewParticle(final ParticleEmitter llllllllllllllllIllIIIlIlIIllllI, final float llllllllllllllllIllIIIlIlIIlIlIl) {
        final ParticlePool llllllllllllllllIllIIIlIlIIllIlI = this.particlesByEmitter.get(llllllllllllllllIllIIIlIlIIllllI);
        final ArrayList llllllllllllllllIllIIIlIlIIllIII = llllllllllllllllIllIIIlIlIIllIlI.available;
        if (llllllllllllllllIllIIIlIlIIllIII.size() > 0) {
            final Particle llllllllllllllllIllIIIlIlIlIIIIl = llllllllllllllllIllIIIlIlIIllIII.remove(llllllllllllllllIllIIIlIlIIllIII.size() - 1);
            llllllllllllllllIllIIIlIlIlIIIIl.init(llllllllllllllllIllIIIlIlIIllllI, llllllllllllllllIllIIIlIlIIlIlIl);
            llllllllllllllllIllIIIlIlIlIIIIl.setImage(this.sprite);
            return llllllllllllllllIllIIIlIlIlIIIIl;
        }
        Log.warn("Ran out of particles (increase the limit)!");
        return this.dummy;
    }
    
    public ParticleEmitter getEmitter(final int llllllllllllllllIllIIIllIlIIIIlI) {
        return this.emitters.get(llllllllllllllllIllIIIllIlIIIIlI);
    }
    
    public void render(final float llllllllllllllllIllIIIlIlllIllll, final float llllllllllllllllIllIIIlIlllIllIl) {
        if (this.sprite == null && this.defaultImageName != null) {
            this.loadSystemParticleImage();
        }
        if (!this.visible) {
            return;
        }
        this.GL.glTranslatef(llllllllllllllllIllIIIlIlllIllll, llllllllllllllllIllIIIlIlllIllIl, 0.0f);
        if (this.blendingMode == 1) {
            this.GL.glBlendFunc(770, 1);
        }
        if (this.usePoints()) {
            this.GL.glEnable(2832);
            TextureImpl.bindNone();
        }
        for (int llllllllllllllllIllIIIlIlllllIIl = 0; llllllllllllllllIllIIIlIlllllIIl < this.emitters.size(); ++llllllllllllllllIllIIIlIlllllIIl) {
            final ParticleEmitter llllllllllllllllIllIIIlIllllllIl = this.emitters.get(llllllllllllllllIllIIIlIlllllIIl);
            if (llllllllllllllllIllIIIlIllllllIl.isEnabled()) {
                if (llllllllllllllllIllIIIlIllllllIl.useAdditive()) {
                    this.GL.glBlendFunc(770, 1);
                }
                final ParticlePool llllllllllllllllIllIIIlIlllllIll = this.particlesByEmitter.get(llllllllllllllllIllIIIlIllllllIl);
                Image llllllllllllllllIllIIIlIlllllIlI = llllllllllllllllIllIIIlIllllllIl.getImage();
                if (llllllllllllllllIllIIIlIlllllIlI == null) {
                    llllllllllllllllIllIIIlIlllllIlI = this.sprite;
                }
                if (!llllllllllllllllIllIIIlIllllllIl.isOriented() && !llllllllllllllllIllIIIlIllllllIl.usePoints(this)) {
                    llllllllllllllllIllIIIlIlllllIlI.startUse();
                }
                for (int llllllllllllllllIllIIIlIlllllllI = 0; llllllllllllllllIllIIIlIlllllllI < llllllllllllllllIllIIIlIlllllIll.particles.length; ++llllllllllllllllIllIIIlIlllllllI) {
                    if (llllllllllllllllIllIIIlIlllllIll.particles[llllllllllllllllIllIIIlIlllllllI].inUse()) {
                        llllllllllllllllIllIIIlIlllllIll.particles[llllllllllllllllIllIIIlIlllllllI].render();
                    }
                }
                if (!llllllllllllllllIllIIIlIllllllIl.isOriented() && !llllllllllllllllIllIIIlIllllllIl.usePoints(this)) {
                    llllllllllllllllIllIIIlIlllllIlI.endUse();
                }
                if (llllllllllllllllIllIIIlIllllllIl.useAdditive()) {
                    this.GL.glBlendFunc(770, 771);
                }
            }
        }
        if (this.usePoints()) {
            this.GL.glDisable(2832);
        }
        if (this.blendingMode == 1) {
            this.GL.glBlendFunc(770, 771);
        }
        Color.white.bind();
        this.GL.glTranslatef(-llllllllllllllllIllIIIlIlllIllll, -llllllllllllllllIllIIIlIlllIllIl, 0.0f);
    }
    
    public float getPositionY() {
        return this.y;
    }
    
    public void removeAllEmitters() {
        for (int llllllllllllllllIllIIIllIIlIIIIl = 0; llllllllllllllllIllIIIllIIlIIIIl < this.emitters.size(); --llllllllllllllllIllIIIllIIlIIIIl, ++llllllllllllllllIllIIIllIIlIIIIl) {
            this.removeEmitter(this.emitters.get(llllllllllllllllIllIIIllIIlIIIIl));
        }
    }
    
    private class ParticlePool
    {
        public /* synthetic */ Particle[] particles;
        public /* synthetic */ ArrayList available;
        
        public void reset(final ParticleSystem lllIllllIllIIl) {
            this.available.clear();
            for (int lllIllllIllIll = 0; lllIllllIllIll < this.particles.length; ++lllIllllIllIll) {
                this.available.add(this.particles[lllIllllIllIll]);
            }
        }
        
        public ParticlePool(final ParticleSystem lllIlllllIIIII, final int lllIllllIlllll) {
            this.particles = new Particle[lllIllllIlllll];
            this.available = new ArrayList();
            for (int lllIlllllIIllI = 0; lllIlllllIIllI < this.particles.length; ++lllIlllllIIllI) {
                this.particles[lllIlllllIIllI] = ParticleSystem.this.createParticle(lllIlllllIIIII);
            }
            this.reset(lllIlllllIIIII);
        }
    }
}
