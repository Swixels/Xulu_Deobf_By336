package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.culling.*;

public class EventItemRender extends Event
{
    public /* synthetic */ Entity entity;
    public /* synthetic */ ICamera camera;
    public /* synthetic */ float n;
    
    public EventItemRender(final Entity llllllllllllllllIlllIIIIllIlIlIl, final ICamera llllllllllllllllIlllIIIIllIlIlII, final float llllllllllllllllIlllIIIIllIlIIll) {
        this.entity = llllllllllllllllIlllIIIIllIlIlIl;
        this.camera = llllllllllllllllIlllIIIIllIlIlII;
        this.n = llllllllllllllllIlllIIIIllIlIIll;
    }
}
