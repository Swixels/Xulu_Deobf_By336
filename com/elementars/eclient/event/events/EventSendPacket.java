package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.network.*;

public class EventSendPacket extends Event
{
    private /* synthetic */ Packet packet;
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public EventSendPacket(final Packet llllllllllllllllIlllIIlIIlIIlIlI) {
        this.packet = llllllllllllllllIlllIIlIIlIIlIlI;
    }
    
    public void setPacket(final Packet llllllllllllllllIlllIIlIIIllllll) {
        this.packet = llllllllllllllllIlllIIlIIIllllll;
    }
}
