package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.network.play.server.*;
import net.minecraft.world.chunk.*;

public class ChunkEvent extends Event
{
    private /* synthetic */ SPacketChunkData packet;
    private /* synthetic */ Chunk chunk;
    
    public SPacketChunkData getPacket() {
        return this.packet;
    }
    
    public ChunkEvent(final Chunk lllIllll, final SPacketChunkData lllIlllI) {
        this.chunk = lllIllll;
        this.packet = lllIlllI;
    }
    
    public Chunk getChunk() {
        return this.chunk;
    }
}
