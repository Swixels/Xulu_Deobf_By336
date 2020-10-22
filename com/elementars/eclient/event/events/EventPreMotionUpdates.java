package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import com.elementars.eclient.util.*;

public class EventPreMotionUpdates extends Event
{
    private /* synthetic */ boolean cancel;
    public /* synthetic */ double y;
    public /* synthetic */ float yaw;
    public /* synthetic */ float pitch;
    
    public EventPreMotionUpdates(final float lllllllllllllllllllIIIllIIIlllIl, final float lllllllllllllllllllIIIllIIIllIII, final double lllllllllllllllllllIIIllIIIllIll) {
        this.yaw = lllllllllllllllllllIIIllIIIlllIl;
        this.pitch = lllllllllllllllllllIIIllIIIllIII;
        this.y = lllllllllllllllllllIIIllIIIllIll;
    }
    
    public void setYaw(final float lllllllllllllllllllIIIlIlllllIII) {
        this.yaw = lllllllllllllllllllIIIlIlllllIII;
    }
    
    public double getY() {
        return this.y;
    }
    
    @Override
    public void setCancelled(final boolean lllllllllllllllllllIIIlIlllIllII) {
        this.cancel = lllllllllllllllllllIIIlIlllIllII;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setMotion(final double lllllllllllllllllllIIIllIIIlIIlI, final double lllllllllllllllllllIIIllIIIlIIIl, final double lllllllllllllllllllIIIllIIIIllIl) {
        Wrapper.getMinecraft().player.motionX = lllllllllllllllllllIIIllIIIlIIlI;
        Wrapper.getMinecraft().player.motionY = lllllllllllllllllllIIIllIIIlIIIl;
        Wrapper.getMinecraft().player.motionZ = lllllllllllllllllllIIIllIIIIllIl;
    }
    
    public void setPitch(final float lllllllllllllllllllIIIllIIIIIIII) {
        this.pitch = lllllllllllllllllllIIIllIIIIIIII;
    }
    
    public void setY(final double lllllllllllllllllllIIIlIllllIlII) {
        this.y = lllllllllllllllllllIIIlIllllIlII;
    }
}
