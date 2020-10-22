package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;

public class Event3D extends Event
{
    private /* synthetic */ float partialTicks;
    
    @Override
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public Event3D(final float lIIlIIIIlIIlII) {
        this.partialTicks = lIIlIIIIlIIlII;
    }
}
