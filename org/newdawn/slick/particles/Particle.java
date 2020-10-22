package org.newdawn.slick.particles;

import org.newdawn.slick.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.opengl.renderer.*;

public class Particle
{
    protected /* synthetic */ float vely;
    private /* synthetic */ ParticleSystem engine;
    protected /* synthetic */ float x;
    protected /* synthetic */ int type;
    protected /* synthetic */ float originalLife;
    private /* synthetic */ ParticleEmitter emitter;
    protected /* synthetic */ boolean oriented;
    protected static /* synthetic */ SGL GL;
    protected /* synthetic */ Image image;
    protected /* synthetic */ float y;
    protected /* synthetic */ float scaleY;
    protected /* synthetic */ float life;
    protected /* synthetic */ Color color;
    protected /* synthetic */ float velx;
    protected /* synthetic */ int usePoints;
    protected /* synthetic */ float size;
    
    public void setVelocity(final float lllllIlIlIIIll, final float lllllIlIIllllI, final float lllllIlIIlllIl) {
        this.velx = lllllIlIlIIIll * lllllIlIIlllIl;
        this.vely = lllllIlIIllllI * lllllIlIIlllIl;
    }
    
    public int getType() {
        return this.type;
    }
    
    public ParticleEmitter getEmitter() {
        return this.emitter;
    }
    
    public Particle(final ParticleSystem llllllIIlIIlII) {
        this.size = 10.0f;
        this.color = Color.white;
        this.usePoints = 1;
        this.oriented = false;
        this.scaleY = 1.0f;
        this.engine = llllllIIlIIlII;
    }
    
    public void setSpeed(final float lllllIlIIlIlIl) {
        final float lllllIlIIlIlll = (float)Math.sqrt(this.velx * this.velx + this.vely * this.vely);
        this.velx *= lllllIlIIlIlIl;
        this.vely *= lllllIlIIlIlIl;
        this.velx /= lllllIlIIlIlll;
        this.vely /= lllllIlIIlIlll;
    }
    
    public void setImage(final Image llllllIIIIlIIl) {
        this.image = llllllIIIIlIIl;
    }
    
    public float getSize() {
        return this.size;
    }
    
    public void setScaleY(final float lllllIIlIIIIll) {
        this.scaleY = lllllIIlIIIIll;
    }
    
    public void setPosition(final float lllllIlIlIllIl, final float lllllIlIlIllII) {
        this.x = lllllIlIlIllIl;
        this.y = lllllIlIlIllII;
    }
    
    public void setLife(final float lllllIllIIlIlI) {
        this.life = lllllIllIIlIlI;
    }
    
    public void adjustVelocity(final float lllllIIlIlllll, final float lllllIIlIllIll) {
        this.velx += lllllIIlIlllll;
        this.vely += lllllIIlIllIll;
    }
    
    public void update(final int lllllIllllIllI) {
        this.emitter.updateParticle(this, lllllIllllIllI);
        this.life -= lllllIllllIllI;
        if (this.life > 0.0f) {
            this.x += lllllIllllIllI * this.velx;
            this.y += lllllIllllIllI * this.vely;
        }
        else {
            this.engine.release(this);
        }
    }
    
    public void setSize(final float lllllIllIllIII) {
        this.size = lllllIllIllIII;
    }
    
    public void adjustLife(final float lllllIllIIIlII) {
        this.life += lllllIllIIIlII;
    }
    
    public void setUsePoint(final int lllllIlllIIIIl) {
        this.usePoints = lllllIlllIIIIl;
    }
    
    public void setVelocity(final float lllllIlIIIllII, final float lllllIlIIIlIll) {
        this.setVelocity(lllllIlIIIllII, lllllIlIIIlIll, 1.0f);
    }
    
    public void adjustColor(final float lllllIIlllIllI, final float lllllIIlllIlIl, final float lllllIIlllIlII, final float lllllIIllllIII) {
        if (this.color == Color.white) {
            this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        }
        final Color color = this.color;
        color.r += lllllIIlllIllI;
        final Color color2 = this.color;
        color2.g += lllllIIlllIlIl;
        final Color color3 = this.color;
        color3.b += lllllIIlllIlII;
        final Color color4 = this.color;
        color4.a += lllllIIllllIII;
    }
    
    public float getLife() {
        return this.life;
    }
    
    public void adjustSize(final float lllllIllIlIIlI) {
        this.size += lllllIllIlIIlI;
        this.size = Math.max(0.0f, this.size);
    }
    
    public void adjustColor(final int lllllIIllIIlll, final int lllllIIllIIllI, final int lllllIIllIlIlI, final int lllllIIllIlIIl) {
        if (this.color == Color.white) {
            this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        }
        final Color color = this.color;
        color.r += lllllIIllIIlll / 255.0f;
        final Color color2 = this.color;
        color2.g += lllllIIllIIllI / 255.0f;
        final Color color3 = this.color;
        color3.b += lllllIIllIlIlI / 255.0f;
        final Color color4 = this.color;
        color4.a += lllllIIllIlIIl / 255.0f;
    }
    
    public void render() {
        if ((this.engine.usePoints() && this.usePoints == 1) || this.usePoints == 2) {
            TextureImpl.bindNone();
            Particle.GL.glEnable(2832);
            Particle.GL.glPointSize(this.size / 2.0f);
            this.color.bind();
            Particle.GL.glBegin(0);
            Particle.GL.glVertex2f(this.x, this.y);
            Particle.GL.glEnd();
        }
        else if (this.oriented || this.scaleY != 1.0f) {
            Particle.GL.glPushMatrix();
            Particle.GL.glTranslatef(this.x, this.y, 0.0f);
            if (this.oriented) {
                final float lllllIllllllIl = (float)(Math.atan2(this.y, this.x) * 180.0 / 3.141592653589793);
                Particle.GL.glRotatef(lllllIllllllIl, 0.0f, 0.0f, 1.0f);
            }
            Particle.GL.glScalef(1.0f, this.scaleY, 1.0f);
            this.image.draw((float)(int)(-(this.size / 2.0f)), (float)(int)(-(this.size / 2.0f)), (float)(int)this.size, (float)(int)this.size, this.color);
            Particle.GL.glPopMatrix();
        }
        else {
            this.color.bind();
            this.image.drawEmbedded((float)(int)(this.x - this.size / 2.0f), (float)(int)(this.y - this.size / 2.0f), (float)(int)this.size, (float)(int)this.size);
        }
    }
    
    public float getX() {
        return this.x;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public boolean isOriented() {
        return this.oriented;
    }
    
    public float getScaleY() {
        return this.scaleY;
    }
    
    public float getY() {
        return this.y;
    }
    
    public void init(final ParticleEmitter lllllIlllIllll, final float lllllIlllIlIll) {
        this.x = 0.0f;
        this.emitter = lllllIlllIllll;
        this.y = 0.0f;
        this.velx = 0.0f;
        this.vely = 0.0f;
        this.size = 10.0f;
        this.type = 0;
        this.life = lllllIlllIlIll;
        this.originalLife = lllllIlllIlIll;
        this.oriented = false;
        this.scaleY = 1.0f;
    }
    
    public void adjustPosition(final float lllllIlIIIIllI, final float lllllIlIIIIlIl) {
        this.x += lllllIlIIIIllI;
        this.y += lllllIlIIIIlIl;
    }
    
    public void setOriented(final boolean lllllIIlIIlllI) {
        this.oriented = lllllIIlIIlllI;
    }
    
    public void setColor(final float lllllIlIlllIlI, final float lllllIlIlllIIl, final float lllllIlIlllIII, final float lllllIlIllIlll) {
        if (this.color == Color.white) {
            this.color = new Color(lllllIlIlllIlI, lllllIlIlllIIl, lllllIlIlllIII, lllllIlIllIlll);
        }
        else {
            this.color.r = lllllIlIlllIlI;
            this.color.g = lllllIlIlllIIl;
            this.color.b = lllllIlIlllIII;
            this.color.a = lllllIlIllIlll;
        }
    }
    
    public void kill() {
        this.life = 1.0f;
    }
    
    public float getOriginalLife() {
        return this.originalLife;
    }
    
    public boolean inUse() {
        return this.life > 0.0f;
    }
    
    public void move(final float llllllIIIlIllI, final float llllllIIIllIII) {
        this.x += llllllIIIlIllI;
        this.y += llllllIIIllIII;
    }
    
    @Override
    public String toString() {
        return String.valueOf(new StringBuilder().append(super.toString()).append(" : ").append(this.life));
    }
    
    public void setType(final int lllllIlllIIlIl) {
        this.type = lllllIlllIIlIl;
    }
    
    static {
        INHERIT_POINTS = 1;
        USE_POINTS = 2;
        USE_QUADS = 3;
        Particle.GL = Renderer.get();
    }
}
