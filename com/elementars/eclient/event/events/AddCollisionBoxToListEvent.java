package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.block.state.*;
import java.util.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;

public class AddCollisionBoxToListEvent extends Event
{
    private final /* synthetic */ IBlockState state;
    private final /* synthetic */ List<AxisAlignedBB> collidingBoxes;
    private final /* synthetic */ boolean bool;
    private final /* synthetic */ Block block;
    private final /* synthetic */ AxisAlignedBB entityBox;
    private final /* synthetic */ World world;
    private final /* synthetic */ BlockPos pos;
    private final /* synthetic */ Entity entity;
    
    public Block getBlock() {
        return this.block;
    }
    
    public List<AxisAlignedBB> getCollidingBoxes() {
        return this.collidingBoxes;
    }
    
    public AxisAlignedBB getEntityBox() {
        return this.entityBox;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public AddCollisionBoxToListEvent(final Block llllllllllllllllllIIIIIIIlIllIII, final IBlockState llllllllllllllllllIIIIIIIlIIlllI, final World llllllllllllllllllIIIIIIIlIlIllI, final BlockPos llllllllllllllllllIIIIIIIlIIllII, final AxisAlignedBB llllllllllllllllllIIIIIIIlIIlIll, final List<AxisAlignedBB> llllllllllllllllllIIIIIIIlIIlIlI, final Entity llllllllllllllllllIIIIIIIlIIlIIl, final boolean llllllllllllllllllIIIIIIIlIlIIIl) {
        this.block = llllllllllllllllllIIIIIIIlIllIII;
        this.state = llllllllllllllllllIIIIIIIlIIlllI;
        this.world = llllllllllllllllllIIIIIIIlIlIllI;
        this.pos = llllllllllllllllllIIIIIIIlIIllII;
        this.entityBox = llllllllllllllllllIIIIIIIlIIlIll;
        this.collidingBoxes = llllllllllllllllllIIIIIIIlIIlIlI;
        this.entity = llllllllllllllllllIIIIIIIlIIlIIl;
        this.bool = llllllllllllllllllIIIIIIIlIlIIIl;
    }
    
    public IBlockState getState() {
        return this.state;
    }
    
    public World getWorld() {
        return this.world;
    }
    
    public boolean isBool() {
        return this.bool;
    }
    
    public BlockPos getPos() {
        return this.pos;
    }
}
