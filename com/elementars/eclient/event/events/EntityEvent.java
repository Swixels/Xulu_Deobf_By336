package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.entity.*;

public class EntityEvent extends Event
{
    private /* synthetic */ Entity entity;
    
    public EntityEvent(final Entity llllllllllllllllIllllIIIllllIIlI) {
        this.entity = llllllllllllllllIllllIIIllllIIlI;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public static class EntityCollision extends EntityEvent
    {
        /* synthetic */ double y;
        /* synthetic */ double z;
        /* synthetic */ double x;
        
        public void setZ(final double lllllllllllllllllIIIIIlIlllIIllI) {
            this.z = lllllllllllllllllIIIIIlIlllIIllI;
        }
        
        public double getY() {
            return this.y;
        }
        
        public EntityCollision(final Entity lllllllllllllllllIIIIIllIIIIlIIl, final double lllllllllllllllllIIIIIllIIIIIIll, final double lllllllllllllllllIIIIIllIIIIIIlI, final double lllllllllllllllllIIIIIllIIIIIllI) {
            super(lllllllllllllllllIIIIIllIIIIlIIl);
            this.x = lllllllllllllllllIIIIIllIIIIIIll;
            this.y = lllllllllllllllllIIIIIllIIIIIIlI;
            this.z = lllllllllllllllllIIIIIllIIIIIllI;
        }
        
        public double getX() {
            return this.x;
        }
        
        public void setY(final double lllllllllllllllllIIIIIlIlllIllII) {
            this.y = lllllllllllllllllIIIIIlIlllIllII;
        }
        
        public void setX(final double lllllllllllllllllIIIIIlIllllIlII) {
            this.x = lllllllllllllllllIIIIIlIllllIlII;
        }
        
        public double getZ() {
            return this.z;
        }
    }
}
