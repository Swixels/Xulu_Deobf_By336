package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import com.mojang.authlib.*;

public class EventPlayerJoin extends Event
{
    /* synthetic */ GameProfile gameProfile;
    
    public GameProfile getGameProfile() {
        return this.gameProfile;
    }
    
    public EventPlayerJoin(final GameProfile llllllllllllllllIlIllIlIlIIIllIl) {
        this.gameProfile = llllllllllllllllIlIllIlIlIIIllIl;
    }
}
