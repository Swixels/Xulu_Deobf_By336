package com.elementars.eclient.event.events;

import com.elementars.eclient.event.*;
import net.minecraft.util.math.*;
import net.minecraft.block.state.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.world.*;

public class EventRenderBlock extends Event
{
    private /* synthetic */ BlockPos blockPos;
    private /* synthetic */ boolean renderable;
    private /* synthetic */ boolean checkSides;
    private /* synthetic */ IBlockState blockState;
    private /* synthetic */ BufferBuilder bufferBuilder;
    private /* synthetic */ long rand;
    private /* synthetic */ IBakedModel bakedModel;
    private /* synthetic */ IBlockAccess blockAccess;
    
    public void setBufferBuilder(final BufferBuilder llllllllllllllllIllIlIIlIIllIlll) {
        this.bufferBuilder = llllllllllllllllIllIlIIlIIllIlll;
    }
    
    public boolean isCheckSides() {
        return this.checkSides;
    }
    
    public EventRenderBlock(final IBlockAccess llllllllllllllllIllIlIIlIlllIlIl, final IBakedModel llllllllllllllllIllIlIIlIlllllIl, final IBlockState llllllllllllllllIllIlIIlIllllIll, final BlockPos llllllllllllllllIllIlIIlIlllIIII, final BufferBuilder llllllllllllllllIllIlIIlIllllIIl, final boolean llllllllllllllllIllIlIIlIllllIII, final long llllllllllllllllIllIlIIlIlllIlll) {
        this.blockAccess = llllllllllllllllIllIlIIlIlllIlIl;
        this.bakedModel = llllllllllllllllIllIlIIlIlllllIl;
        this.blockState = llllllllllllllllIllIlIIlIllllIll;
        this.blockPos = llllllllllllllllIllIlIIlIlllIIII;
        this.bufferBuilder = llllllllllllllllIllIlIIlIllllIIl;
        this.checkSides = llllllllllllllllIllIlIIlIllllIII;
        this.rand = llllllllllllllllIllIlIIlIlllIlll;
    }
    
    public void setCheckSides(final boolean llllllllllllllllIllIlIIlIIlIlIll) {
        this.checkSides = llllllllllllllllIllIlIIlIIlIlIll;
    }
    
    public void setBlockState(final IBlockState llllllllllllllllIllIlIIlIlIlIIII) {
        this.blockState = llllllllllllllllIllIlIIlIlIlIIII;
    }
    
    public void setRenderable(final boolean llllllllllllllllIllIlIIlIIIIIIlI) {
        this.renderable = llllllllllllllllIllIlIIlIIIIIIlI;
    }
    
    public void setBlockPos(final BlockPos llllllllllllllllIllIlIIlIlIIIlll) {
        this.blockPos = llllllllllllllllIllIlIIlIlIIIlll;
    }
    
    public void setRand(final long llllllllllllllllIllIlIIlIIIllIIl) {
        this.rand = llllllllllllllllIllIlIIlIIIllIIl;
    }
    
    public BlockPos getBlockPos() {
        return this.blockPos;
    }
    
    public IBlockState getBlockState() {
        return this.blockState;
    }
    
    public void setBakedModel(final IBakedModel llllllllllllllllIllIlIIlIlIlllII) {
        this.bakedModel = llllllllllllllllIllIlIIlIlIlllII;
    }
    
    public IBlockAccess getBlockAccess() {
        return this.blockAccess;
    }
    
    public long getRand() {
        return this.rand;
    }
    
    public boolean isRenderable() {
        return this.renderable;
    }
    
    public BufferBuilder getBufferBuilder() {
        return this.bufferBuilder;
    }
    
    public void setBlockAccess(final IBlockAccess llllllllllllllllIllIlIIlIllIIlIl) {
        this.blockAccess = llllllllllllllllIllIlIIlIllIIlIl;
    }
    
    public IBakedModel getBakedModel() {
        return this.bakedModel;
    }
}
