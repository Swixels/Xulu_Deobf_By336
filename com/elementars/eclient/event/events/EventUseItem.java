package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.entity.player.*;

public class EventUseItem extends Event
{
    /* synthetic */ EntityPlayer player;
    
    public EntityPlayer getPlayer() {
        return this.player;
    }
    
    public EventUseItem(final EntityPlayer lIIIIlIlI) {
        this.player = lIIIIlIlI;
    }
}
