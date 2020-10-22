package com.elementars.eclient.util;

import net.minecraft.util.math.*;
import net.minecraft.block.*;

public class Location
{
    private /* synthetic */ double y;
    private /* synthetic */ double x;
    private /* synthetic */ boolean ground;
    private /* synthetic */ double z;
    
    public Location(final double lIIIIII, final double lIIIIll, final double lllllI) {
        this.x = lIIIIII;
        this.y = lIIIIll;
        this.z = lllllI;
        this.ground = true;
    }
    
    public BlockPos toBlockPos() {
        return new BlockPos(this.getX(), this.getY(), this.getZ());
    }
    
    public Block getBlock() {
        return Wrapper.getMinecraft().world.getBlockState(this.toBlockPos()).getBlock();
    }
    
    public Location setOnGround(final boolean llllllllllllllllllllllllllIlIIII) {
        this.ground = llllllllllllllllllllllllllIlIIII;
        return this;
    }
    
    public double getY() {
        return this.y;
    }
    
    public Location add(final double lIIlI, final double lIlIl, final double lIIII) {
        this.x += lIIlI;
        this.y += lIlIl;
        this.z += lIIII;
        return this;
    }
    
    public Location setY(final double lllllllllllllllllllllllllIllIlIl) {
        this.y = lllllllllllllllllllllllllIllIlIl;
        return this;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public static Location fromBlockPos(final BlockPos lllllllllllllllllllllllllIlIIIlI) {
        return new Location(lllllllllllllllllllllllllIlIIIlI.getX(), lllllllllllllllllllllllllIlIIIlI.getY(), lllllllllllllllllllllllllIlIIIlI.getZ());
    }
    
    public Location(final int llIlII, final int llIlll, final int llIllI) {
        this.x = llIlII;
        this.y = llIlll;
        this.z = llIllI;
        this.ground = true;
    }
    
    public Location add(final int lIlIII, final int lIIlll, final int lIlIlI) {
        this.x += lIlIII;
        this.y += lIIlll;
        this.z += lIlIlI;
        return this;
    }
    
    public Location subtract(final int lIIl, final int lIII, final int ll) {
        this.x -= lIIl;
        this.y -= lIII;
        this.z -= ll;
        return this;
    }
    
    public Location setZ(final double lllllllllllllllllllllllllIlIIlII) {
        this.z = lllllllllllllllllllllllllIlIIlII;
        return this;
    }
    
    public Location subtract(final double llllllllllllllllllllllllllllIlIl, final double lllllllllllllllllllllllllllIlllI, final double llllllllllllllllllllllllllllIIll) {
        this.x -= llllllllllllllllllllllllllllIlIl;
        this.y -= lllllllllllllllllllllllllllIlllI;
        this.z -= llllllllllllllllllllllllllllIIll;
        return this;
    }
    
    public Location(final double lIIllIl, final double lIIllII, final double lIIlIll, final boolean lIIllll) {
        this.x = lIIllIl;
        this.y = lIIllII;
        this.z = lIIlIll;
        this.ground = lIIllll;
    }
    
    public boolean isOnGround() {
        return this.ground;
    }
    
    public Location setX(final double llllllllllllllllllllllllllIIIIII) {
        this.x = llllllllllllllllllllllllllIIIIII;
        return this;
    }
    
    public double getX() {
        return this.x;
    }
}
