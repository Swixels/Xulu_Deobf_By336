package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;

public class EventCollide extends Event
{
    private /* synthetic */ AxisAlignedBB boundingBox;
    private /* synthetic */ double posY;
    private /* synthetic */ Block block;
    private /* synthetic */ double posZ;
    private /* synthetic */ double posX;
    private /* synthetic */ Entity entity;
    
    public EventCollide(final Entity llllIIlIIlllIl, final double llllIIlIlIIIll, final double llllIIlIlIIIlI, final double llllIIlIlIIIIl, final AxisAlignedBB llllIIlIlIIIII, final Block llllIIlIIlllll) {
        this.entity = llllIIlIIlllIl;
        this.posX = llllIIlIlIIIll;
        this.posY = llllIIlIlIIIlI;
        this.posZ = llllIIlIlIIIIl;
        this.boundingBox = llllIIlIlIIIII;
        this.block = llllIIlIIlllll;
    }
    
    public Block getBlock() {
        return this.block;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public double getPosX() {
        return this.posX;
    }
    
    public double getPosY() {
        return this.posY;
    }
    
    public double getPosZ() {
        return this.posZ;
    }
    
    public void setBoundingBox(final AxisAlignedBB llllIIlIIIIlII) {
        this.boundingBox = llllIIlIIIIlII;
    }
    
    public AxisAlignedBB getBoundingBox() {
        return this.boundingBox;
    }
}
