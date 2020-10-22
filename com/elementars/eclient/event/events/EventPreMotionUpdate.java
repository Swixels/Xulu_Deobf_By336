package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;

public class EventPreMotionUpdate extends Event
{
    public /* synthetic */ double y;
    public /* synthetic */ double x;
    public /* synthetic */ double z;
    private /* synthetic */ float pitch;
    private /* synthetic */ float yaw;
    private /* synthetic */ boolean ground;
    
    public EventPreMotionUpdate(final float llllllllllllllllIlIllIIlIIlIIIll, final float llllllllllllllllIlIllIIlIIlIlIll, final boolean llllllllllllllllIlIllIIlIIIlllll, final double llllllllllllllllIlIllIIlIIlIlIIl, final double llllllllllllllllIlIllIIlIIlIlIII, final double llllllllllllllllIlIllIIlIIlIIlll) {
        this.yaw = llllllllllllllllIlIllIIlIIlIIIll;
        this.pitch = llllllllllllllllIlIllIIlIIlIlIll;
        this.ground = llllllllllllllllIlIllIIlIIIlllll;
        this.x = llllllllllllllllIlIllIIlIIlIlIIl;
        this.y = llllllllllllllllIlIllIIlIIlIlIII;
        this.z = llllllllllllllllIlIllIIlIIlIIlll;
    }
    
    public void setYaw(final float llllllllllllllllIlIllIIlIIIIIIlI) {
        this.yaw = llllllllllllllllIlIllIIlIIIIIIlI;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setPitch(final float llllllllllllllllIlIllIIIlllIllII) {
        this.pitch = llllllllllllllllIlIllIIIlllIllII;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void setGround(final boolean llllllllllllllllIlIllIIIllIlllIl) {
        this.ground = llllllllllllllllIlIllIIIllIlllIl;
    }
    
    public boolean onGround() {
        return this.ground;
    }
}
