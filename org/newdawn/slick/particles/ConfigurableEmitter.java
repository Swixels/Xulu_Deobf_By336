package org.newdawn.slick.particles;

import java.util.*;
import org.newdawn.slick.geom.*;
import java.io.*;
import org.newdawn.slick.*;
import org.newdawn.slick.util.*;

public class ConfigurableEmitter implements ParticleEmitter
{
    public /* synthetic */ Range spawnCount;
    private /* synthetic */ ParticleSystem engine;
    public /* synthetic */ SimpleValue startAlpha;
    public /* synthetic */ LinearInterpolator scaleY;
    public /* synthetic */ RandomValue spread;
    private /* synthetic */ boolean enabled;
    public /* synthetic */ Range emitCount;
    public /* synthetic */ String name;
    private /* synthetic */ Image image;
    public /* synthetic */ SimpleValue windFactor;
    public /* synthetic */ String imageName;
    public /* synthetic */ ArrayList colors;
    private /* synthetic */ int nextSpawn;
    public /* synthetic */ boolean useAdditive;
    public /* synthetic */ Range speed;
    private /* synthetic */ float y;
    public /* synthetic */ SimpleValue gravityFactor;
    private /* synthetic */ boolean updateImage;
    private /* synthetic */ int timeout;
    public /* synthetic */ SimpleValue growthFactor;
    public /* synthetic */ Range initialDistance;
    public /* synthetic */ Range xOffset;
    public /* synthetic */ LinearInterpolator velocity;
    public /* synthetic */ int usePoints;
    protected /* synthetic */ float adjusty;
    protected /* synthetic */ boolean wrapUp;
    public /* synthetic */ Range initialLife;
    protected /* synthetic */ float adjustx;
    public /* synthetic */ boolean useOriented;
    private static /* synthetic */ String relativePath;
    private /* synthetic */ int leftToEmit;
    private /* synthetic */ int particleCount;
    protected /* synthetic */ boolean completed;
    private /* synthetic */ float x;
    protected /* synthetic */ boolean adjust;
    public /* synthetic */ SimpleValue endAlpha;
    public /* synthetic */ SimpleValue angularOffset;
    public /* synthetic */ LinearInterpolator alpha;
    public /* synthetic */ Range length;
    public /* synthetic */ Range spawnInterval;
    public /* synthetic */ Range yOffset;
    public /* synthetic */ Range initialSize;
    public /* synthetic */ LinearInterpolator size;
    
    @Override
    public void resetState() {
        this.wrapUp = false;
        this.replay();
    }
    
    @Override
    public boolean completed() {
        if (this.engine == null) {
            return false;
        }
        if (this.length.isEnabled()) {
            return this.timeout <= 0 && this.completed;
        }
        if (this.emitCount.isEnabled()) {
            return this.leftToEmit <= 0 && this.completed;
        }
        return this.wrapUp && this.completed;
    }
    
    @Override
    public void setEnabled(final boolean llllllllllllllllIlllIIIllIlllIll) {
        this.enabled = llllllllllllllllIlllIIIllIlllIll;
    }
    
    @Override
    public void updateParticle(final Particle llllllllllllllllIlllIIIlIlllIllI, final int llllllllllllllllIlllIIIlIllIllII) {
        ++this.particleCount;
        llllllllllllllllIlllIIIlIlllIllI.x += this.adjustx;
        llllllllllllllllIlllIIIlIlllIllI.y += this.adjusty;
        llllllllllllllllIlllIIIlIlllIllI.adjustVelocity(this.windFactor.getValue(0.0f) * 5.0E-5f * llllllllllllllllIlllIIIlIllIllII, this.gravityFactor.getValue(0.0f) * 5.0E-5f * llllllllllllllllIlllIIIlIllIllII);
        final float llllllllllllllllIlllIIIlIlllIlII = llllllllllllllllIlllIIIlIlllIllI.getLife() / llllllllllllllllIlllIIIlIlllIllI.getOriginalLife();
        final float llllllllllllllllIlllIIIlIlllIIll = 1.0f - llllllllllllllllIlllIIIlIlllIlII;
        float llllllllllllllllIlllIIIlIlllIIlI = 0.0f;
        float llllllllllllllllIlllIIIlIlllIIIl = 1.0f;
        Color llllllllllllllllIlllIIIlIlllIIII = null;
        Color llllllllllllllllIlllIIIlIllIllll = null;
        for (int llllllllllllllllIlllIIIlIllllllI = 0; llllllllllllllllIlllIIIlIllllllI < this.colors.size() - 1; ++llllllllllllllllIlllIIIlIllllllI) {
            final ColorRecord llllllllllllllllIlllIIIllIIIIIII = this.colors.get(llllllllllllllllIlllIIIlIllllllI);
            final ColorRecord llllllllllllllllIlllIIIlIlllllll = this.colors.get(llllllllllllllllIlllIIIlIllllllI + 1);
            if (llllllllllllllllIlllIIIlIlllIIll >= llllllllllllllllIlllIIIllIIIIIII.pos && llllllllllllllllIlllIIIlIlllIIll <= llllllllllllllllIlllIIIlIlllllll.pos) {
                llllllllllllllllIlllIIIlIlllIIII = llllllllllllllllIlllIIIllIIIIIII.col;
                llllllllllllllllIlllIIIlIllIllll = llllllllllllllllIlllIIIlIlllllll.col;
                final float llllllllllllllllIlllIIIllIIIIIIl = llllllllllllllllIlllIIIlIlllllll.pos - llllllllllllllllIlllIIIllIIIIIII.pos;
                llllllllllllllllIlllIIIlIlllIIlI = llllllllllllllllIlllIIIlIlllIIll - llllllllllllllllIlllIIIllIIIIIII.pos;
                llllllllllllllllIlllIIIlIlllIIlI /= llllllllllllllllIlllIIIllIIIIIIl;
                llllllllllllllllIlllIIIlIlllIIlI = 1.0f - llllllllllllllllIlllIIIlIlllIIlI;
                llllllllllllllllIlllIIIlIlllIIIl = 1.0f - llllllllllllllllIlllIIIlIlllIIlI;
            }
        }
        if (llllllllllllllllIlllIIIlIlllIIII != null) {
            final float llllllllllllllllIlllIIIlIlllllII = llllllllllllllllIlllIIIlIlllIIII.r * llllllllllllllllIlllIIIlIlllIIlI + llllllllllllllllIlllIIIlIllIllll.r * llllllllllllllllIlllIIIlIlllIIIl;
            final float llllllllllllllllIlllIIIlIllllIll = llllllllllllllllIlllIIIlIlllIIII.g * llllllllllllllllIlllIIIlIlllIIlI + llllllllllllllllIlllIIIlIllIllll.g * llllllllllllllllIlllIIIlIlllIIIl;
            final float llllllllllllllllIlllIIIlIllllIlI = llllllllllllllllIlllIIIlIlllIIII.b * llllllllllllllllIlllIIIlIlllIIlI + llllllllllllllllIlllIIIlIllIllll.b * llllllllllllllllIlllIIIlIlllIIIl;
            float llllllllllllllllIlllIIIlIllllIIl = 0.0f;
            if (this.alpha.isActive()) {
                final float llllllllllllllllIlllIIIlIlllllIl = this.alpha.getValue(llllllllllllllllIlllIIIlIlllIIll) / 255.0f;
            }
            else {
                llllllllllllllllIlllIIIlIllllIIl = this.startAlpha.getValue(0.0f) / 255.0f * llllllllllllllllIlllIIIlIlllIlII + this.endAlpha.getValue(0.0f) / 255.0f * llllllllllllllllIlllIIIlIlllIIll;
            }
            llllllllllllllllIlllIIIlIlllIllI.setColor(llllllllllllllllIlllIIIlIlllllII, llllllllllllllllIlllIIIlIllllIll, llllllllllllllllIlllIIIlIllllIlI, llllllllllllllllIlllIIIlIllllIIl);
        }
        if (this.size.isActive()) {
            final float llllllllllllllllIlllIIIlIllllIII = this.size.getValue(llllllllllllllllIlllIIIlIlllIIll);
            llllllllllllllllIlllIIIlIlllIllI.setSize(llllllllllllllllIlllIIIlIllllIII);
        }
        else {
            llllllllllllllllIlllIIIlIlllIllI.adjustSize(llllllllllllllllIlllIIIlIllIllII * this.growthFactor.getValue(0.0f) * 0.001f);
        }
        if (this.velocity.isActive()) {
            llllllllllllllllIlllIIIlIlllIllI.setSpeed(this.velocity.getValue(llllllllllllllllIlllIIIlIlllIIll));
        }
        if (this.scaleY.isActive()) {
            llllllllllllllllIlllIIIlIlllIllI.setScaleY(this.scaleY.getValue(llllllllllllllllIlllIIIlIlllIIll));
        }
    }
    
    public void setImageName(String llllllllllllllllIlllIIIllllIIlIl) {
        if (llllllllllllllllIlllIIIllllIIlIl.length() == 0) {
            llllllllllllllllIlllIIIllllIIlIl = null;
        }
        if ((this.imageName = llllllllllllllllIlllIIIllllIIlIl) == null) {
            this.image = null;
        }
        else {
            this.updateImage = true;
        }
    }
    
    public String getImageName() {
        return this.imageName;
    }
    
    public void setPosition(final float llllllllllllllllIlllIIIlllIllIlI, final float llllllllllllllllIlllIIIlllIllIIl) {
        this.setPosition(llllllllllllllllIlllIIIlllIllIlI, llllllllllllllllIlllIIIlllIllIIl, true);
    }
    
    public static void setRelativePath(String llllllllllllllllIlllIIIlllllIlII) {
        if (!((String)llllllllllllllllIlllIIIlllllIlII).endsWith("/")) {
            llllllllllllllllIlllIIIlllllIlII = String.valueOf(new StringBuilder().append((String)llllllllllllllllIlllIIIlllllIlII).append("/"));
        }
        ConfigurableEmitter.relativePath = (String)llllllllllllllllIlllIIIlllllIlII;
    }
    
    public void replay() {
        this.reset();
        this.nextSpawn = 0;
        this.leftToEmit = (int)this.emitCount.random();
        this.timeout = (int)this.length.random();
    }
    
    public ConfigurableEmitter(final String llllllllllllllllIlllIIIllllIllII) {
        this.spawnInterval = new Range(100.0f, 100.0f);
        this.spawnCount = new Range(5.0f, 5.0f);
        this.initialLife = new Range(1000.0f, 1000.0f);
        this.initialSize = new Range(10.0f, 10.0f);
        this.xOffset = new Range(0.0f, 0.0f);
        this.yOffset = new Range(0.0f, 0.0f);
        this.spread = new RandomValue(360.0f);
        this.angularOffset = new SimpleValue(0.0f);
        this.initialDistance = new Range(0.0f, 0.0f);
        this.speed = new Range(50.0f, 50.0f);
        this.growthFactor = new SimpleValue(0.0f);
        this.gravityFactor = new SimpleValue(0.0f);
        this.windFactor = new SimpleValue(0.0f);
        this.length = new Range(1000.0f, 1000.0f);
        this.colors = new ArrayList();
        this.startAlpha = new SimpleValue(255.0f);
        this.endAlpha = new SimpleValue(0.0f);
        this.emitCount = new Range(1000.0f, 1000.0f);
        this.usePoints = 1;
        this.useOriented = false;
        this.useAdditive = false;
        this.imageName = "";
        this.enabled = true;
        this.nextSpawn = 0;
        this.wrapUp = false;
        this.completed = false;
        this.name = llllllllllllllllIlllIIIllllIllII;
        this.leftToEmit = (int)this.emitCount.random();
        this.timeout = (int)this.length.random();
        this.colors.add(new ColorRecord(0.0f, Color.white));
        this.colors.add(new ColorRecord(1.0f, Color.red));
        ArrayList llllllllllllllllIlllIIIllllIlllI = new ArrayList();
        llllllllllllllllIlllIIIllllIlllI.add(new Vector2f(0.0f, 0.0f));
        llllllllllllllllIlllIIIllllIlllI.add(new Vector2f(1.0f, 255.0f));
        this.alpha = new LinearInterpolator(llllllllllllllllIlllIIIllllIlllI, 0, 255);
        llllllllllllllllIlllIIIllllIlllI = new ArrayList();
        llllllllllllllllIlllIIIllllIlllI.add(new Vector2f(0.0f, 0.0f));
        llllllllllllllllIlllIIIllllIlllI.add(new Vector2f(1.0f, 255.0f));
        this.size = new LinearInterpolator(llllllllllllllllIlllIIIllllIlllI, 0, 255);
        llllllllllllllllIlllIIIllllIlllI = new ArrayList();
        llllllllllllllllIlllIIIllllIlllI.add(new Vector2f(0.0f, 0.0f));
        llllllllllllllllIlllIIIllllIlllI.add(new Vector2f(1.0f, 1.0f));
        this.velocity = new LinearInterpolator(llllllllllllllllIlllIIIllllIlllI, 0, 1);
        llllllllllllllllIlllIIIllllIlllI = new ArrayList();
        llllllllllllllllIlllIIIllllIlllI.add(new Vector2f(0.0f, 0.0f));
        llllllllllllllllIlllIIIllllIlllI.add(new Vector2f(1.0f, 1.0f));
        this.scaleY = new LinearInterpolator(llllllllllllllllIlllIIIllllIlllI, 0, 1);
    }
    
    public ConfigurableEmitter duplicate() {
        ConfigurableEmitter llllllllllllllllIlllIIIlIlIIllIl = null;
        try {
            final ByteArrayOutputStream llllllllllllllllIlllIIIlIlIlIIIl = new ByteArrayOutputStream();
            ParticleIO.saveEmitter(llllllllllllllllIlllIIIlIlIlIIIl, this);
            final ByteArrayInputStream llllllllllllllllIlllIIIlIlIlIIII = new ByteArrayInputStream(llllllllllllllllIlllIIIlIlIlIIIl.toByteArray());
            llllllllllllllllIlllIIIlIlIIllIl = ParticleIO.loadEmitter(llllllllllllllllIlllIIIlIlIlIIII);
        }
        catch (IOException llllllllllllllllIlllIIIlIlIIllll) {
            Log.error(String.valueOf(new StringBuilder().append("Slick: ConfigurableEmitter.duplicate(): caught exception ").append(llllllllllllllllIlllIIIlIlIIllll.toString())));
            return null;
        }
        return llllllllllllllllIlllIIIlIlIIllIl;
    }
    
    public void setPosition(final float llllllllllllllllIlllIIIlllIlIIII, final float llllllllllllllllIlllIIIlllIIlIll, final boolean llllllllllllllllIlllIIIlllIIlllI) {
        if (llllllllllllllllIlllIIIlllIIlllI) {
            this.adjust = true;
            this.adjustx -= this.x - llllllllllllllllIlllIIIlllIlIIII;
            this.adjusty -= this.y - llllllllllllllllIlllIIIlllIIlIll;
        }
        this.x = llllllllllllllllIlllIIIlllIlIIII;
        this.y = llllllllllllllllIlllIIIlllIIlIll;
    }
    
    public float getY() {
        return this.y;
    }
    
    static {
        ConfigurableEmitter.relativePath = "";
    }
    
    @Override
    public boolean usePoints(final ParticleSystem llllllllllllllllIlllIIIlIIllIllI) {
        return (this.usePoints == 1 && llllllllllllllllIlllIIIlIIllIllI.usePoints()) || this.usePoints == 2;
    }
    
    public void addColorPoint(final float llllllllllllllllIlllIIIlIlIIIIIl, final Color llllllllllllllllIlllIIIlIlIIIIII) {
        this.colors.add(new ColorRecord(llllllllllllllllIlllIIIlIlIIIIIl, llllllllllllllllIlllIIIlIlIIIIII));
    }
    
    @Override
    public String toString() {
        return String.valueOf(new StringBuilder().append("[").append(this.name).append("]"));
    }
    
    @Override
    public void update(final ParticleSystem llllllllllllllllIlllIIIllIIllIll, final int llllllllllllllllIlllIIIllIIllIlI) {
        this.engine = llllllllllllllllIlllIIIllIIllIll;
        if (!this.adjust) {
            this.adjustx = 0.0f;
            this.adjusty = 0.0f;
        }
        else {
            this.adjust = false;
        }
        if (this.updateImage) {
            this.updateImage = false;
            try {
                this.image = new Image(String.valueOf(new StringBuilder().append(ConfigurableEmitter.relativePath).append(this.imageName)));
            }
            catch (SlickException llllllllllllllllIlllIIIllIlIllII) {
                this.image = null;
                Log.error(llllllllllllllllIlllIIIllIlIllII);
            }
        }
        if ((this.wrapUp || (this.length.isEnabled() && this.timeout < 0) || (this.emitCount.isEnabled() && this.leftToEmit <= 0)) && this.particleCount == 0) {
            this.completed = true;
        }
        this.particleCount = 0;
        if (this.wrapUp) {
            return;
        }
        if (this.length.isEnabled()) {
            if (this.timeout < 0) {
                return;
            }
            this.timeout -= llllllllllllllllIlllIIIllIIllIlI;
        }
        if (this.emitCount.isEnabled() && this.leftToEmit <= 0) {
            return;
        }
        this.nextSpawn -= llllllllllllllllIlllIIIllIIllIlI;
        if (this.nextSpawn < 0) {
            this.nextSpawn = (int)this.spawnInterval.random();
            for (int llllllllllllllllIlllIIIllIlIIIII = (int)this.spawnCount.random(), llllllllllllllllIlllIIIllIlIIIIl = 0; llllllllllllllllIlllIIIllIlIIIIl < llllllllllllllllIlllIIIllIlIIIII; ++llllllllllllllllIlllIIIllIlIIIIl) {
                final Particle llllllllllllllllIlllIIIllIlIIlIl = llllllllllllllllIlllIIIllIIllIll.getNewParticle(this, this.initialLife.random());
                llllllllllllllllIlllIIIllIlIIlIl.setSize(this.initialSize.random());
                llllllllllllllllIlllIIIllIlIIlIl.setPosition(this.x + this.xOffset.random(), this.y + this.yOffset.random());
                llllllllllllllllIlllIIIllIlIIlIl.setVelocity(0.0f, 0.0f, 0.0f);
                final float llllllllllllllllIlllIIIllIlIIlII = this.initialDistance.random();
                final float llllllllllllllllIlllIIIllIlIIIll = this.speed.random();
                if (llllllllllllllllIlllIIIllIlIIlII != 0.0f || llllllllllllllllIlllIIIllIlIIIll != 0.0f) {
                    final float llllllllllllllllIlllIIIllIlIlIll = this.spread.getValue(0.0f);
                    final float llllllllllllllllIlllIIIllIlIlIlI = llllllllllllllllIlllIIIllIlIlIll + this.angularOffset.getValue(0.0f) - this.spread.getValue() / 2.0f - 90.0f;
                    final float llllllllllllllllIlllIIIllIlIlIIl = (float)FastTrig.cos(Math.toRadians(llllllllllllllllIlllIIIllIlIlIlI)) * llllllllllllllllIlllIIIllIlIIlII;
                    final float llllllllllllllllIlllIIIllIlIlIII = (float)FastTrig.sin(Math.toRadians(llllllllllllllllIlllIIIllIlIlIlI)) * llllllllllllllllIlllIIIllIlIIlII;
                    llllllllllllllllIlllIIIllIlIIlIl.adjustPosition(llllllllllllllllIlllIIIllIlIlIIl, llllllllllllllllIlllIIIllIlIlIII);
                    final float llllllllllllllllIlllIIIllIlIIlll = (float)FastTrig.cos(Math.toRadians(llllllllllllllllIlllIIIllIlIlIlI));
                    final float llllllllllllllllIlllIIIllIlIIllI = (float)FastTrig.sin(Math.toRadians(llllllllllllllllIlllIIIllIlIlIlI));
                    llllllllllllllllIlllIIIllIlIIlIl.setVelocity(llllllllllllllllIlllIIIllIlIIlll, llllllllllllllllIlllIIIllIlIIllI, llllllllllllllllIlllIIIllIlIIIll * 0.001f);
                }
                if (this.image != null) {
                    llllllllllllllllIlllIIIllIlIIlIl.setImage(this.image);
                }
                final ColorRecord llllllllllllllllIlllIIIllIlIIIlI = this.colors.get(0);
                llllllllllllllllIlllIIIllIlIIlIl.setColor(llllllllllllllllIlllIIIllIlIIIlI.col.r, llllllllllllllllIlllIIIllIlIIIlI.col.g, llllllllllllllllIlllIIIllIlIIIlI.col.b, this.startAlpha.getValue(0.0f) / 255.0f);
                llllllllllllllllIlllIIIllIlIIlIl.setUsePoint(this.usePoints);
                llllllllllllllllIlllIIIllIlIIlIl.setOriented(this.useOriented);
                if (this.emitCount.isEnabled()) {
                    --this.leftToEmit;
                    if (this.leftToEmit <= 0) {
                        break;
                    }
                }
            }
        }
    }
    
    @Override
    public void wrapUp() {
        this.wrapUp = true;
    }
    
    public void replayCheck() {
        if (this.completed() && this.engine != null && this.engine.getParticleCount() == 0) {
            this.replay();
        }
    }
    
    @Override
    public boolean useAdditive() {
        return this.useAdditive;
    }
    
    @Override
    public boolean isOriented() {
        return this.useOriented;
    }
    
    @Override
    public Image getImage() {
        return this.image;
    }
    
    public float getX() {
        return this.x;
    }
    
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void reset() {
        this.completed = false;
        if (this.engine != null) {
            this.engine.releaseAll(this);
        }
    }
    
    public class ColorRecord
    {
        public /* synthetic */ float pos;
        public /* synthetic */ Color col;
        
        public ColorRecord(final float llIIIlIlIIIIIII, final Color llIIIlIIllllIll) {
            this.pos = llIIIlIlIIIIIII;
            this.col = llIIIlIIllllIll;
        }
    }
    
    public class LinearInterpolator implements Value
    {
        private /* synthetic */ int max;
        private /* synthetic */ ArrayList curve;
        private /* synthetic */ int min;
        private /* synthetic */ boolean active;
        
        @Override
        public float getValue(final float lllIIIlIIIllllI) {
            Vector2f lllIIIlIIIlllIl = this.curve.get(0);
            for (int lllIIIlIIlIIIII = 1; lllIIIlIIlIIIII < this.curve.size(); ++lllIIIlIIlIIIII) {
                final Vector2f lllIIIlIIlIIIIl = this.curve.get(lllIIIlIIlIIIII);
                if (lllIIIlIIIllllI >= lllIIIlIIIlllIl.getX() && lllIIIlIIIllllI <= lllIIIlIIlIIIIl.getX()) {
                    final float lllIIIlIIlIIIll = (lllIIIlIIIllllI - lllIIIlIIIlllIl.getX()) / (lllIIIlIIlIIIIl.getX() - lllIIIlIIIlllIl.getX());
                    final float lllIIIlIIlIIIlI = lllIIIlIIIlllIl.getY() + lllIIIlIIlIIIll * (lllIIIlIIlIIIIl.getY() - lllIIIlIIIlllIl.getY());
                    return lllIIIlIIlIIIlI;
                }
                lllIIIlIIIlllIl = lllIIIlIIlIIIIl;
            }
            return 0.0f;
        }
        
        public LinearInterpolator(final ArrayList lllIIIlIIllIllI, final int lllIIIlIIlllIlI, final int lllIIIlIIllIlII) {
            this.curve = lllIIIlIIllIllI;
            this.min = lllIIIlIIlllIlI;
            this.max = lllIIIlIIllIlII;
            this.active = false;
        }
        
        public boolean isActive() {
            return this.active;
        }
        
        public int getMin() {
            return this.min;
        }
        
        public ArrayList getCurve() {
            return this.curve;
        }
        
        public int getMax() {
            return this.max;
        }
        
        public void setActive(final boolean lllIIIlIIIIllll) {
            this.active = lllIIIlIIIIllll;
        }
        
        public void setCurve(final ArrayList lllIIIlIIlIlllI) {
            this.curve = lllIIIlIIlIlllI;
        }
        
        public void setMax(final int lllIIIlIIIIIllI) {
            this.max = lllIIIlIIIIIllI;
        }
        
        public void setMin(final int lllIIIIlllllIll) {
            this.min = lllIIIIlllllIll;
        }
    }
    
    public interface Value
    {
        float getValue(final float p0);
    }
    
    public class Range
    {
        private /* synthetic */ boolean enabled;
        private /* synthetic */ float min;
        private /* synthetic */ float max;
        
        public void setMin(final float llllllllllllllllIllIIIIIIIIIlIII) {
            this.min = llllllllllllllllIllIIIIIIIIIlIII;
        }
        
        public float getMin() {
            return this.min;
        }
        
        public void setMax(final float llllllllllllllllIllIIIIIIIIIllll) {
            this.max = llllllllllllllllIllIIIIIIIIIllll;
        }
        
        public boolean isEnabled() {
            return this.enabled;
        }
        
        private Range(final float llllllllllllllllIllIIIIIIIlIIlIl, final float llllllllllllllllIllIIIIIIIlIlIII) {
            this.enabled = false;
            this.min = llllllllllllllllIllIIIIIIIlIIlIl;
            this.max = llllllllllllllllIllIIIIIIIlIlIII;
        }
        
        public float getMax() {
            return this.max;
        }
        
        public float random() {
            return (float)(this.min + Math.random() * (this.max - this.min));
        }
        
        public void setEnabled(final boolean llllllllllllllllIllIIIIIIIIllIII) {
            this.enabled = llllllllllllllllIllIIIIIIIIllIII;
        }
    }
    
    public class RandomValue implements Value
    {
        private /* synthetic */ float value;
        
        public void setValue(final float llllllllllllllllllIlIIlIIIllIlII) {
            this.value = llllllllllllllllllIlIIlIIIllIlII;
        }
        
        private RandomValue(final float llllllllllllllllllIlIIlIIIllllII) {
            this.value = llllllllllllllllllIlIIlIIIllllII;
        }
        
        @Override
        public float getValue(final float llllllllllllllllllIlIIlIIIlllIIl) {
            return (float)(Math.random() * this.value);
        }
        
        public float getValue() {
            return this.value;
        }
    }
    
    public class SimpleValue implements Value
    {
        private /* synthetic */ float value;
        
        public void setValue(final float lllllllllllllllllIlIlIlllIlIIllI) {
            this.value = lllllllllllllllllIlIlIlllIlIIllI;
        }
        
        private SimpleValue(final float lllllllllllllllllIlIlIlllIllIIll) {
            this.value = lllllllllllllllllIlIlIlllIllIIll;
        }
        
        @Override
        public float getValue(final float lllllllllllllllllIlIlIlllIlIllIl) {
            return this.value;
        }
    }
}
