package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.*;

public class EventThrow extends Event
{
    private /* synthetic */ EntityThrowable entity;
    private /* synthetic */ float rotation;
    private /* synthetic */ Entity thrower;
    
    public float getRotation() {
        return this.rotation;
    }
    
    public EntityThrowable getEntity() {
        return this.entity;
    }
    
    public Entity getThrower() {
        return this.thrower;
    }
    
    public EventThrow(final Entity lllllllllllllllllIIIlllllIIlllIl, final EntityThrowable lllllllllllllllllIIIlllllIIlllII, final float lllllllllllllllllIIIlllllIIllIll) {
        this.thrower = lllllllllllllllllIIIlllllIIlllIl;
        this.entity = lllllllllllllllllIIIlllllIIlllII;
        this.rotation = lllllllllllllllllIIIlllllIIllIll;
    }
}
