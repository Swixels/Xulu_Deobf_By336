package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.entity.player.*;

public class EventTotemPop extends Event
{
    /* synthetic */ EntityPlayer player;
    
    public EntityPlayer getPlayer() {
        return this.player;
    }
    
    public EventTotemPop(final EntityPlayer lIIIIllIllllIl) {
        this.player = lIIIIllIllllIl;
    }
}
