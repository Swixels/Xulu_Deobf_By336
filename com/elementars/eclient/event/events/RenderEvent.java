package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.*;

public class RenderEvent extends Event
{
    private final /* synthetic */ Vec3d renderPos;
    private final /* synthetic */ Tessellator tessellator;
    
    public void resetTranslation() {
        this.setTranslation(this.renderPos);
    }
    
    public BufferBuilder getBuffer() {
        return this.tessellator.getBuffer();
    }
    
    public RenderEvent(final Tessellator llllIIIlIIIIlI, final Vec3d llllIIIIlllllI) {
        this.tessellator = llllIIIlIIIIlI;
        this.renderPos = llllIIIIlllllI;
    }
    
    public Vec3d getRenderPos() {
        return this.renderPos;
    }
    
    public Tessellator getTessellator() {
        return this.tessellator;
    }
    
    public void setTranslation(final Vec3d llllIIIIlIllll) {
        this.getBuffer().setTranslation(-llllIIIIlIllll.x, -llllIIIIlIllll.y, -llllIIIIlIllll.z);
    }
}
