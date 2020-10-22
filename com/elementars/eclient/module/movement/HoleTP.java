package com.elementars.eclient.module.movement;

import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import java.util.concurrent.atomic.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.event.events.*;
import com.elementars.eclient.event.*;

public class HoleTP extends Module
{
    private /* synthetic */ boolean wasOnGround;
    private final /* synthetic */ BlockPos[] xd;
    
    private void unstep(final EntityPlayer llllllllllllllllIllllllIllIIIlII) {
        if (!this.shouldUnstep(new BlockPos(llllllllllllllllIllllllIllIIIlII.getPositionVector()))) {
            return;
        }
        final AxisAlignedBB llllllllllllllllIllllllIllIIlIII = llllllllllllllllIllllllIllIIIlII.getEntityBoundingBox().expand(0.0, -1.2000000476837158, 0.0).contract(0.0, (double)llllllllllllllllIllllllIllIIIlII.height, 0.0);
        if (!llllllllllllllllIllllllIllIIIlII.world.collidesWithAnyBlock(llllllllllllllllIllllllIllIIlIII)) {
            return;
        }
        final List<AxisAlignedBB> llllllllllllllllIllllllIllIIIlll = (List<AxisAlignedBB>)llllllllllllllllIllllllIllIIIlII.world.getCollisionBoxes((Entity)llllllllllllllllIllllllIllIIIlII, llllllllllllllllIllllllIllIIlIII);
        final AtomicReference<Double> llllllllllllllllIllllllIllIIIllI = new AtomicReference<Double>(0.0);
        final AtomicReference<Double> atomicReference;
        llllllllllllllllIllllllIllIIIlll.forEach(llllllllllllllllIllllllIlIlIlIlI -> atomicReference.set(Math.max(atomicReference.get(), llllllllllllllllIllllllIlIlIlIlI.maxY)));
        llllllllllllllllIllllllIllIIIlII.setPositionAndUpdate(llllllllllllllllIllllllIllIIIlII.posX, (double)llllllllllllllllIllllllIllIIIllI.get(), llllllllllllllllIllllllIllIIIlII.posZ);
    }
    
    private boolean shouldUnstep(final BlockPos llllllllllllllllIllllllIllIllIII) {
        boolean llllllllllllllllIllllllIllIlIlll = true;
        final char llllllllllllllllIllllllIllIlIIll = (Object)this.xd;
        final short llllllllllllllllIllllllIllIlIIlI = (short)llllllllllllllllIllllllIllIlIIll.length;
        for (float llllllllllllllllIllllllIllIlIIIl = 0; llllllllllllllllIllllllIllIlIIIl < llllllllllllllllIllllllIllIlIIlI; ++llllllllllllllllIllllllIllIlIIIl) {
            final BlockPos llllllllllllllllIllllllIllIllIlI = llllllllllllllllIllllllIllIlIIll[llllllllllllllllIllllllIllIlIIIl];
            if (HoleTP.mc.world.getBlockState(llllllllllllllllIllllllIllIllIII.add((Vec3i)llllllllllllllllIllllllIllIllIlI)).getBlock() != Blocks.BEDROCK && HoleTP.mc.world.getBlockState(llllllllllllllllIllllllIllIllIII.add((Vec3i)llllllllllllllllIllllllIllIllIlI)).getBlock() != Blocks.OBSIDIAN) {
                llllllllllllllllIllllllIllIlIlll = false;
            }
        }
        return llllllllllllllllIllllllIllIlIlll;
    }
    
    private void updateStepHeight(final EntityPlayer llllllllllllllllIllllllIlllIIIlI) {
        llllllllllllllllIllllllIlllIIIlI.stepHeight = (llllllllllllllllIllllllIlllIIIlI.onGround ? 1.2f : 0.6f);
    }
    
    private void updateUnstep(final EntityPlayer llllllllllllllllIllllllIlIlllIlI) {
        try {
            if (this.wasOnGround && !llllllllllllllllIllllllIlIlllIlI.onGround && llllllllllllllllIllllllIlIlllIlI.motionY <= 0.0) {
                this.unstep(llllllllllllllllIllllllIlIlllIlI);
            }
        }
        finally {
            this.wasOnGround = llllllllllllllllIllllllIlIlllIlI.onGround;
        }
    }
    
    public HoleTP() {
        super("HoleTP", "Reverse step for holes", 0, Category.MOVEMENT, true);
        this.xd = new BlockPos[] { new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1), new BlockPos(0, -1, 0) };
        this.wasOnGround = false;
    }
    
    @EventTarget
    public void onLocalPlayerUpdate(final LocalPlayerUpdateEvent llllllllllllllllIllllllIlIllIIIl) {
        final EntityPlayer llllllllllllllllIllllllIlIllIIll = (EntityPlayer)llllllllllllllllIllllllIlIllIIIl.getEntityLivingBase();
        if (llllllllllllllllIllllllIlIllIIll == null) {
            return;
        }
        this.updateUnstep(llllllllllllllllIllllllIlIllIIll);
    }
    
    @Override
    public void onDisable() {
        if (HoleTP.mc.player != null) {
            HoleTP.mc.player.stepHeight = 0.6f;
        }
        if (HoleTP.mc.player != null && HoleTP.mc.player.getRidingEntity() != null) {
            HoleTP.mc.player.getRidingEntity().stepHeight = 1.0f;
        }
    }
    
    @Override
    public void onEnable() {
        if (HoleTP.mc.player != null) {
            this.wasOnGround = HoleTP.mc.player.onGround;
        }
    }
}
