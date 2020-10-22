package com.elementars.eclient.module.movement;

import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.block.*;
import net.minecraft.entity.item.*;
import com.elementars.eclient.module.*;

public class Jesus extends Module
{
    private static final /* synthetic */ AxisAlignedBB WATER_WALK_AA;
    
    private static boolean isAboveBlock(final Entity lllllllllllllllllIlIIIIIllIIIlIl, final BlockPos lllllllllllllllllIlIIIIIllIIIllI) {
        return lllllllllllllllllIlIIIIIllIIIlIl.posY >= lllllllllllllllllIlIIIIIllIIIllI.getY();
    }
    
    private static boolean isAboveLand(final Entity lllllllllllllllllIlIIIIIllIIlllI) {
        if (lllllllllllllllllIlIIIIIllIIlllI == null) {
            return false;
        }
        final double lllllllllllllllllIlIIIIIllIIllll = lllllllllllllllllIlIIIIIllIIlllI.posY - 0.01;
        for (int lllllllllllllllllIlIIIIIllIlIIIl = MathHelper.floor(lllllllllllllllllIlIIIIIllIIlllI.posX); lllllllllllllllllIlIIIIIllIlIIIl < MathHelper.ceil(lllllllllllllllllIlIIIIIllIIlllI.posX); ++lllllllllllllllllIlIIIIIllIlIIIl) {
            for (int lllllllllllllllllIlIIIIIllIlIIlI = MathHelper.floor(lllllllllllllllllIlIIIIIllIIlllI.posZ); lllllllllllllllllIlIIIIIllIlIIlI < MathHelper.ceil(lllllllllllllllllIlIIIIIllIIlllI.posZ); ++lllllllllllllllllIlIIIIIllIlIIlI) {
                final BlockPos lllllllllllllllllIlIIIIIllIlIIll = new BlockPos(lllllllllllllllllIlIIIIIllIlIIIl, MathHelper.floor(lllllllllllllllllIlIIIIIllIIllll), lllllllllllllllllIlIIIIIllIlIIlI);
                if (Wrapper.getWorld().getBlockState(lllllllllllllllllIlIIIIIllIlIIll).getBlock().isFullBlock(Wrapper.getWorld().getBlockState(lllllllllllllllllIlIIIIIllIlIIll))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Jesus() {
        super("Jesus", "Walk on water", 0, Category.MOVEMENT, true);
    }
    
    @EventTarget
    public void onPacket(final EventSendPacket lllllllllllllllllIlIIIIIllIllIll) {
        if (lllllllllllllllllIlIIIIIllIllIll.getPacket() instanceof CPacketPlayer && EntityUtil.isAboveWater((Entity)Jesus.mc.player, true) && !EntityUtil.isInWater((Entity)Jesus.mc.player) && !isAboveLand((Entity)Jesus.mc.player)) {
            final int lllllllllllllllllIlIIIIIllIlllIl = Jesus.mc.player.ticksExisted % 2;
            if (lllllllllllllllllIlIIIIIllIlllIl == 0) {
                final CPacketPlayer cPacketPlayer = (CPacketPlayer)lllllllllllllllllIlIIIIIllIllIll.getPacket();
                cPacketPlayer.y += 0.02;
            }
        }
    }
    
    @EventTarget
    public void onCollision(final AddCollisionBoxToListEvent lllllllllllllllllIlIIIIIlllIIIlI) {
        if (Jesus.mc.player != null && lllllllllllllllllIlIIIIIlllIIIlI.getBlock() instanceof BlockLiquid && (EntityUtil.isDrivenByPlayer(lllllllllllllllllIlIIIIIlllIIIlI.getEntity()) || lllllllllllllllllIlIIIIIlllIIIlI.getEntity() == Jesus.mc.player) && !(lllllllllllllllllIlIIIIIlllIIIlI.getEntity() instanceof EntityBoat) && !Jesus.mc.player.isSneaking() && Jesus.mc.player.fallDistance < 3.0f && !EntityUtil.isInWater((Entity)Jesus.mc.player) && (EntityUtil.isAboveWater((Entity)Jesus.mc.player, false) || EntityUtil.isAboveWater(Jesus.mc.player.getRidingEntity(), false)) && isAboveBlock((Entity)Jesus.mc.player, lllllllllllllllllIlIIIIIlllIIIlI.getPos())) {
            final AxisAlignedBB lllllllllllllllllIlIIIIIlllIIlII = Jesus.WATER_WALK_AA.offset(lllllllllllllllllIlIIIIIlllIIIlI.getPos());
            if (lllllllllllllllllIlIIIIIlllIIIlI.getEntityBox().intersects(lllllllllllllllllIlIIIIIlllIIlII)) {
                lllllllllllllllllIlIIIIIlllIIIlI.getCollidingBoxes().add(lllllllllllllllllIlIIIIIlllIIlII);
            }
            lllllllllllllllllIlIIIIIlllIIIlI.setCancelled(true);
        }
    }
    
    @Override
    public void onUpdate() {
        if (!ModuleManager.isModuleEnabled("Freecam") && EntityUtil.isInWater((Entity)Jesus.mc.player) && !Jesus.mc.player.isSneaking()) {
            Jesus.mc.player.motionY = 0.1;
            if (Jesus.mc.player.getRidingEntity() != null && !(Jesus.mc.player.getRidingEntity() instanceof EntityBoat)) {
                Jesus.mc.player.getRidingEntity().motionY = 0.3;
            }
        }
    }
    
    static {
        WATER_WALK_AA = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.99, 1.0);
    }
}
