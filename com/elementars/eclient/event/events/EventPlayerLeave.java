package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import com.mojang.authlib.*;

public class EventPlayerLeave extends Event
{
    /* synthetic */ GameProfile gameProfile;
    
    public EventPlayerLeave(final GameProfile llIlIllllIIlI) {
        this.gameProfile = llIlIllllIIlI;
    }
    
    public GameProfile getGameProfile() {
        return this.gameProfile;
    }
}
