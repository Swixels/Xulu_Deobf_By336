package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.world.chunk.*;

public class UnloadChunkEvent extends Event
{
    private /* synthetic */ Chunk chunk;
    
    public Chunk getChunk() {
        return this.chunk;
    }
    
    public UnloadChunkEvent(final Chunk lIlIlllIllIIlll) {
        this.chunk = lIlIlllIllIIlll;
    }
}
