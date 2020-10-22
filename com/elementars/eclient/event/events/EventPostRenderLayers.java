package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.*;

public class EventPostRenderLayers extends Event
{
    public /* synthetic */ float limbSwingAmount;
    public /* synthetic */ ModelBase modelBase;
    public /* synthetic */ RenderLivingBase renderer;
    public /* synthetic */ float headPitch;
    public /* synthetic */ float netHeadYaw;
    public /* synthetic */ float limbSwing;
    public /* synthetic */ float partialTicks;
    public /* synthetic */ EntityLivingBase entity;
    public /* synthetic */ float scaleIn;
    public /* synthetic */ float ageInTicks;
    
    public RenderLivingBase getRenderer() {
        return this.renderer;
    }
    
    public float getLimbSwingAmount() {
        return this.limbSwingAmount;
    }
    
    public float getHeadPitch() {
        return this.headPitch;
    }
    
    public float getNetHeadYaw() {
        return this.netHeadYaw;
    }
    
    @Override
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public ModelBase getModelBase() {
        return this.modelBase;
    }
    
    public float getAgeInTicks() {
        return this.ageInTicks;
    }
    
    public float getScaleIn() {
        return this.scaleIn;
    }
    
    public float getLimbSwing() {
        return this.limbSwing;
    }
    
    public EventPostRenderLayers(final RenderLivingBase lllllllllllllllllllIIIIllIIIllII, final ModelBase lllllllllllllllllllIIIIllIIIIIII, final EntityLivingBase lllllllllllllllllllIIIIlIlllllll, final float lllllllllllllllllllIIIIllIIIlIIl, final float lllllllllllllllllllIIIIlIlllllIl, final float lllllllllllllllllllIIIIlIlllllII, final float lllllllllllllllllllIIIIlIllllIll, final float lllllllllllllllllllIIIIllIIIIlIl, final float lllllllllllllllllllIIIIlIllllIIl, final float lllllllllllllllllllIIIIllIIIIIll) {
        this.renderer = lllllllllllllllllllIIIIllIIIllII;
        this.modelBase = lllllllllllllllllllIIIIllIIIIIII;
        this.entity = lllllllllllllllllllIIIIlIlllllll;
        this.limbSwing = lllllllllllllllllllIIIIllIIIlIIl;
        this.limbSwingAmount = lllllllllllllllllllIIIIlIlllllIl;
        this.partialTicks = lllllllllllllllllllIIIIlIlllllII;
        this.ageInTicks = lllllllllllllllllllIIIIlIllllIll;
        this.netHeadYaw = lllllllllllllllllllIIIIllIIIIlIl;
        this.headPitch = lllllllllllllllllllIIIIlIllllIIl;
        this.scaleIn = lllllllllllllllllllIIIIllIIIIIll;
    }
    
    public EntityLivingBase getEntitylivingbaseIn() {
        return this.entity;
    }
}
