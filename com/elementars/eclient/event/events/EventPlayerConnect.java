package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import java.util.*;

public class EventPlayerConnect extends Event
{
    /* synthetic */ UUID uuid;
    /* synthetic */ String name;
    
    public EventPlayerConnect(final UUID llllllllllllllllIlIllIllIIlIllll, final String llllllllllllllllIlIllIllIIlIlllI) {
        this.uuid = llllllllllllllllIlIllIllIIlIllll;
        this.name = llllllllllllllllIlIllIllIIlIlllI;
    }
    
    public UUID getUuid() {
        return this.uuid;
    }
    
    public String getName() {
        return this.name;
    }
    
    public static class Leave extends EventPlayerConnect
    {
        public Leave(final UUID llIllIllllIIlII, final String llIllIllllIIllI) {
            super(llIllIllllIIlII, llIllIllllIIllI);
        }
    }
    
    public static class Join extends EventPlayerConnect
    {
        public Join(final UUID llIIlIllIIIllIl, final String llIIlIllIIIllII) {
            super(llIIlIllIIIllIl, llIIlIllIIIllII);
        }
    }
}
