package com.elementars.eclient.util;

public class Rotation
{
    private /* synthetic */ float pitch;
    private /* synthetic */ boolean active;
    private /* synthetic */ float yaw;
    
    public Rotation(final float lllllllllllllllllIIIllIlIIllIIlI, final float lllllllllllllllllIIIllIlIIllIlII) {
        this.yaw = lllllllllllllllllIIIllIlIIllIIlI;
        this.pitch = lllllllllllllllllIIIllIlIIllIlII;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public Rotation subtract(final float lllllllllllllllllIIIllIlIIlIIIII, final float lllllllllllllllllIIIllIlIIlIIIlI) {
        this.yaw -= lllllllllllllllllIIIllIlIIlIIIII;
        this.pitch -= lllllllllllllllllIIIllIlIIlIIIlI;
        return this;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public Rotation setYaw(final float lllllllllllllllllIIIllIlIIIllIII) {
        this.active = true;
        this.yaw = lllllllllllllllllIIIllIlIIIllIII;
        return this;
    }
    
    public Rotation add(final float lllllllllllllllllIIIllIlIIlIllII, final float lllllllllllllllllIIIllIlIIlIlIII) {
        this.yaw += lllllllllllllllllIIIllIlIIlIllII;
        this.pitch += lllllllllllllllllIIIllIlIIlIlIII;
        return this;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public Rotation setPitch(final float lllllllllllllllllIIIllIlIIIIllIl) {
        this.active = true;
        this.pitch = lllllllllllllllllIIIllIlIIIIllIl;
        return this;
    }
}
