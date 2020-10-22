package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;

public class EventModelPlayerRender extends Event
{
    public /* synthetic */ float limbSwingAmount;
    public /* synthetic */ float scaleFactor;
    public /* synthetic */ ModelBase modelBase;
    public /* synthetic */ float limbSwing;
    public /* synthetic */ float netHeadYaw;
    public /* synthetic */ float ageInTicks;
    public /* synthetic */ float headPitch;
    public /* synthetic */ Entity entity;
    
    public EventModelPlayerRender(final ModelBase llllIIlllIllIlI, final Entity llllIIlllIlIIII, final float llllIIlllIllIII, final float llllIIlllIIlllI, final float llllIIlllIlIllI, final float llllIIlllIIllII, final float llllIIlllIlIlII, final float llllIIlllIlIIll) {
        this.modelBase = llllIIlllIllIlI;
        this.entity = llllIIlllIlIIII;
        this.limbSwing = llllIIlllIllIII;
        this.limbSwingAmount = llllIIlllIIlllI;
        this.ageInTicks = llllIIlllIlIllI;
        this.netHeadYaw = llllIIlllIIllII;
        this.headPitch = llllIIlllIlIlII;
        this.scaleFactor = llllIIlllIlIIll;
    }
}
