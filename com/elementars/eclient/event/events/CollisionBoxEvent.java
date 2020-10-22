package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraft.entity.*;
import javax.annotation.*;

public class CollisionBoxEvent extends Event
{
    private /* synthetic */ AxisAlignedBB aabb;
    private final /* synthetic */ Block block;
    private final /* synthetic */ BlockPos pos;
    private final /* synthetic */ List<AxisAlignedBB> collidingBoxes;
    private final /* synthetic */ Entity entity;
    
    public void setAABB(final AxisAlignedBB llllllllllllllllllllllIIlIIIIlIl) {
        this.aabb = llllllllllllllllllllllIIlIIIIlIl;
    }
    
    public final Block getBlock() {
        return this.block;
    }
    
    public final BlockPos getPos() {
        return this.pos;
    }
    
    public final List<AxisAlignedBB> getCollidingBoxes() {
        return this.collidingBoxes;
    }
    
    public CollisionBoxEvent(final Block llllllllllllllllllllllIIlIIIllll, final BlockPos llllllllllllllllllllllIIlIIIlllI, final AxisAlignedBB llllllllllllllllllllllIIlIIIllIl, final List<AxisAlignedBB> llllllllllllllllllllllIIlIIlIIlI, @Nullable final Entity llllllllllllllllllllllIIlIIlIIIl) {
        this.block = llllllllllllllllllllllIIlIIIllll;
        this.pos = llllllllllllllllllllllIIlIIIlllI;
        this.aabb = llllllllllllllllllllllIIlIIIllIl;
        this.collidingBoxes = llllllllllllllllllllllIIlIIlIIlI;
        this.entity = llllllllllllllllllllllIIlIIlIIIl;
    }
    
    public final AxisAlignedBB getBoundingBox() {
        return this.aabb;
    }
    
    public final Entity getEntity() {
        return this.entity;
    }
}
