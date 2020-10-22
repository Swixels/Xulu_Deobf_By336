package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.entity.*;

public class PlayerMoveEvent extends Event
{
    private /* synthetic */ MoverType type;
    private /* synthetic */ double x;
    private /* synthetic */ double z;
    private /* synthetic */ double y;
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public PlayerMoveEvent(final MoverType lllllllllllllllllllIIIIlIlIlIllI, final double lllllllllllllllllllIIIIlIlIlIlIl, final double lllllllllllllllllllIIIIlIlIIllll, final double lllllllllllllllllllIIIIlIlIlIIll) {
        this.type = lllllllllllllllllllIIIIlIlIlIllI;
        this.x = lllllllllllllllllllIIIIlIlIlIlIl;
        this.y = lllllllllllllllllllIIIIlIlIIllll;
        this.z = lllllllllllllllllllIIIIlIlIlIIll;
    }
    
    public void setX(final double lllllllllllllllllllIIIIlIIllIllI) {
        this.x = lllllllllllllllllllIIIIlIIllIllI;
    }
    
    public void setY(final double lllllllllllllllllllIIIIlIIllIIlI) {
        this.y = lllllllllllllllllllIIIIlIIllIIlI;
    }
    
    public MoverType getType() {
        return this.type;
    }
    
    public void setZ(final double lllllllllllllllllllIIIIlIIlIlIlI) {
        this.z = lllllllllllllllllllIIIIlIIlIlIlI;
    }
    
    public void setType(final MoverType lllllllllllllllllllIIIIlIlIIIlll) {
        this.type = lllllllllllllllllllIIIIlIlIIIlll;
    }
    
    public double getX() {
        return this.x;
    }
}
