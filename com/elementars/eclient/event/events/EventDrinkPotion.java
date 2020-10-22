package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;

public class EventDrinkPotion extends Event
{
    /* synthetic */ EntityLivingBase entityLivingBase;
    /* synthetic */ ItemStack stack;
    
    public ItemStack getStack() {
        return this.stack;
    }
    
    public EventDrinkPotion(final EntityLivingBase lIIIIlllIIlIII, final ItemStack lIIIIlllIIIlll) {
        this.entityLivingBase = lIIIIlllIIlIII;
        this.stack = lIIIIlllIIIlll;
    }
    
    public EntityLivingBase getEntityLivingBase() {
        return this.entityLivingBase;
    }
}
