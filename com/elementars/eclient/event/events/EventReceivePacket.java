package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.network.*;

public class EventReceivePacket extends Event
{
    private /* synthetic */ Packet packet;
    
    public void setPacket(final Packet llllIllIIIIlllI) {
        this.packet = llllIllIIIIlllI;
    }
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public EventReceivePacket(final Packet llllIllIIIlIlll) {
        this.packet = llllIllIIIlIlll;
    }
}
