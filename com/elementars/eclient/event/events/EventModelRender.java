package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;

public class EventModelRender extends Event
{
    public /* synthetic */ ModelBase modelBase;
    public /* synthetic */ float ageInTicks;
    public /* synthetic */ float limbSwing;
    public /* synthetic */ float scaleFactor;
    public /* synthetic */ float headPitch;
    public /* synthetic */ float netHeadYaw;
    public /* synthetic */ float limbSwingAmount;
    public /* synthetic */ Entity entity;
    
    public EventModelRender(final ModelBase lllllllllllllllllIlIIlIIIIIIlllI, final Entity lllllllllllllllllIlIIlIIIIIlIllI, final float lllllllllllllllllIlIIlIIIIIlIlIl, final float lllllllllllllllllIlIIlIIIIIlIlII, final float lllllllllllllllllIlIIlIIIIIIlIlI, final float lllllllllllllllllIlIIlIIIIIIlIIl, final float lllllllllllllllllIlIIlIIIIIlIIIl, final float lllllllllllllllllIlIIlIIIIIlIIII) {
        this.modelBase = lllllllllllllllllIlIIlIIIIIIlllI;
        this.entity = lllllllllllllllllIlIIlIIIIIlIllI;
        this.limbSwing = lllllllllllllllllIlIIlIIIIIlIlIl;
        this.limbSwingAmount = lllllllllllllllllIlIIlIIIIIlIlII;
        this.ageInTicks = lllllllllllllllllIlIIlIIIIIIlIlI;
        this.netHeadYaw = lllllllllllllllllIlIIlIIIIIIlIIl;
        this.headPitch = lllllllllllllllllIlIIlIIIIIlIIIl;
        this.scaleFactor = lllllllllllllllllIlIIlIIIIIlIIII;
    }
}
