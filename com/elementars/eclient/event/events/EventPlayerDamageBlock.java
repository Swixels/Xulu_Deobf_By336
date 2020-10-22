package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;

public class EventPlayerDamageBlock extends Event
{
    /* synthetic */ EnumFacing facing;
    /* synthetic */ BlockPos pos;
    
    public BlockPos getPos() {
        return this.pos;
    }
    
    public EnumFacing getFacing() {
        return this.facing;
    }
    
    public EventPlayerDamageBlock(final BlockPos lllIlIIIlIIIlIl, final EnumFacing lllIlIIIlIIIIIl) {
        this.pos = lllIlIIIlIIIlIl;
        this.facing = lllIlIIIlIIIIIl;
    }
}
