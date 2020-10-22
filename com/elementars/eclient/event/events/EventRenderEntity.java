package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.*;
import net.minecraft.client.model.*;
import javax.annotation.*;

public class EventRenderEntity extends Event
{
    /* synthetic */ RenderLivingBase renderer;
    /* synthetic */ EntityLivingBase entity;
    /* synthetic */ double x;
    /* synthetic */ ModelBase model;
    /* synthetic */ double y;
    /* synthetic */ double z;
    /* synthetic */ float entityYaw;
    /* synthetic */ float partialTicks;
    
    @Override
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public ModelBase getModel() {
        return this.model;
    }
    
    public EntityLivingBase getEntity() {
        return this.entity;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public double getX() {
        return this.x;
    }
    
    public float getEntityYaw() {
        return this.entityYaw;
    }
    
    public RenderLivingBase getRenderer() {
        return this.renderer;
    }
    
    public double getY() {
        return this.y;
    }
    
    public EventRenderEntity(@Nullable final RenderLivingBase llIllIIllIIllll, final EntityLivingBase llIllIIllIIlllI, @Nullable final ModelBase llIllIIllIIllIl, final double llIllIIllIIIIll, final double llIllIIllIIlIll, final double llIllIIllIIlIlI, final float llIllIIllIIlIIl, final float llIllIIllIIlIII) {
        this.renderer = llIllIIllIIllll;
        this.entity = llIllIIllIIlllI;
        this.model = llIllIIllIIllIl;
        this.x = llIllIIllIIIIll;
        this.y = llIllIIllIIlIll;
        this.z = llIllIIllIIlIlI;
        this.entityYaw = llIllIIllIIlIIl;
        this.partialTicks = llIllIIllIIlIII;
    }
}
