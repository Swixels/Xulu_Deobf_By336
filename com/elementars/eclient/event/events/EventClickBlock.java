package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;

public class EventClickBlock extends Event
{
    /* synthetic */ BlockPos pos;
    /* synthetic */ EnumFacing facing;
    
    public EventClickBlock(final BlockPos lllllllIlIlIIII, final EnumFacing lllllllIlIIllll) {
        this.pos = lllllllIlIlIIII;
        this.facing = lllllllIlIIllll;
    }
    
    public BlockPos getPos() {
        return this.pos;
    }
    
    public EnumFacing getFacing() {
        return this.facing;
    }
}
