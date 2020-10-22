package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.entity.*;

public class LocalPlayerUpdateEvent extends Event
{
    /* synthetic */ EntityLivingBase entityLivingBase;
    
    public LocalPlayerUpdateEvent(final EntityLivingBase lllllllllllllllllIlIlIlllIIlIllI) {
        this.entityLivingBase = lllllllllllllllllIlIlIlllIIlIllI;
    }
    
    public EntityLivingBase getEntityLivingBase() {
        return this.entityLivingBase;
    }
}
