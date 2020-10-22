package com.elementars.eclient.module.combat;

import java.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.entity.*;
import net.minecraft.item.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import java.util.concurrent.*;
import com.elementars.eclient.friend.*;
import com.elementars.eclient.module.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class AutoWeb extends Module
{
    /* synthetic */ int delay;
    public static /* synthetic */ float pitch;
    public static /* synthetic */ List<EntityPlayer> targets;
    public static /* synthetic */ EntityPlayer target;
    public static /* synthetic */ float yaw;
    /* synthetic */ BlockPos feet;
    /* synthetic */ BlockPos head;
    
    public void loadTargets() {
        for (final EntityPlayer lllllllllllllllllIlIIlllllllIlll : AutoWeb.mc.world.playerEntities) {
            if (!(lllllllllllllllllIlIIlllllllIlll instanceof EntityPlayerSP)) {
                final EntityPlayer lllllllllllllllllIlIIllllllllIII = lllllllllllllllllIlIIlllllllIlll;
                if (this.isValid(lllllllllllllllllIlIIllllllllIII)) {
                    AutoWeb.targets.add(lllllllllllllllllIlIIllllllllIII);
                }
                else {
                    if (!AutoWeb.targets.contains(lllllllllllllllllIlIIllllllllIII)) {
                        continue;
                    }
                    AutoWeb.targets.remove(lllllllllllllllllIlIIllllllllIII);
                }
            }
        }
    }
    
    private boolean isStackObby(final ItemStack lllllllllllllllllIlIIllllllIlllI) {
        return lllllllllllllllllIlIIllllllIlllI != null && lllllllllllllllllIlIIllllllIlllI.getItem() == Item.getItemById(30);
    }
    
    public static IBlockState getState(final BlockPos lllllllllllllllllIlIIlllllIlllll) {
        return AutoWeb.mc.world.getBlockState(lllllllllllllllllIlIIlllllIlllll);
    }
    
    public BlockPos getBlockPos(final double lllllllllllllllllIlIIllllIIIIlll, final double lllllllllllllllllIlIIllllIIIIIll, final double lllllllllllllllllIlIIllllIIIIIlI) {
        return new BlockPos(lllllllllllllllllIlIIllllIIIIlll, lllllllllllllllllIlIIllllIIIIIll, lllllllllllllllllIlIIllllIIIIIlI);
    }
    
    private void trap(final EntityPlayer lllllllllllllllllIlIIllllIlIlIIl) {
        if (lllllllllllllllllIlIIllllIlIlIIl.moveForward == 0.0 && lllllllllllllllllIlIIllllIlIlIIl.moveStrafing == 0.0 && lllllllllllllllllIlIIllllIlIlIIl.moveVertical == 0.0) {
            ++this.delay;
        }
        if (lllllllllllllllllIlIIllllIlIlIIl.moveForward != 0.0 || lllllllllllllllllIlIIllllIlIlIIl.moveStrafing != 0.0 || lllllllllllllllllIlIIllllIlIlIIl.moveVertical != 0.0) {
            this.delay = 0;
        }
        if (!this.doesHotbarHaveObby()) {
            this.delay = 0;
        }
        if (this.delay == 2 && this.doesHotbarHaveObby()) {
            this.head = new BlockPos(lllllllllllllllllIlIIllllIlIlIIl.posX, lllllllllllllllllIlIIllllIlIlIIl.posY + 1.0, lllllllllllllllllIlIIllllIlIlIIl.posZ);
            this.feet = new BlockPos(lllllllllllllllllIlIIllllIlIlIIl.posX, lllllllllllllllllIlIIllllIlIlIIl.posY, lllllllllllllllllIlIIllllIlIlIIl.posZ);
            for (int lllllllllllllllllIlIIllllIlIlIll = 36; lllllllllllllllllIlIIllllIlIlIll < 45; ++lllllllllllllllllIlIIllllIlIlIll) {
                final ItemStack lllllllllllllllllIlIIllllIlIllII = AutoWeb.mc.player.inventoryContainer.getSlot(lllllllllllllllllIlIIllllIlIlIll).getStack();
                if (lllllllllllllllllIlIIllllIlIllII != null && this.isStackObby(lllllllllllllllllIlIIllllIlIllII)) {
                    final int lllllllllllllllllIlIIllllIlIllIl = AutoWeb.mc.player.inventory.currentItem;
                    if (AutoWeb.mc.world.getBlockState(this.head).getMaterial().isReplaceable() || AutoWeb.mc.world.getBlockState(this.feet).getMaterial().isReplaceable()) {
                        AutoWeb.mc.player.inventory.currentItem = lllllllllllllllllIlIIllllIlIlIll - 36;
                        if (AutoWeb.mc.world.getBlockState(this.head).getMaterial().isReplaceable()) {
                            placeBlockLegit(this.head);
                        }
                        if (AutoWeb.mc.world.getBlockState(this.feet).getMaterial().isReplaceable()) {
                            placeBlockLegit(this.feet);
                        }
                        AutoWeb.mc.player.inventory.currentItem = lllllllllllllllllIlIIllllIlIllIl;
                        this.delay = 0;
                        break;
                    }
                    this.delay = 0;
                }
                this.delay = 0;
            }
        }
    }
    
    @Override
    public void onUpdate() {
        if (AutoWeb.mc.player.isHandActive()) {
            return;
        }
        if (!this.isValid(AutoWeb.target) || AutoWeb.target == null) {
            this.updateTarget();
        }
        for (final EntityPlayer lllllllllllllllllIlIIllllIlllllI : AutoWeb.mc.world.playerEntities) {
            if (!(lllllllllllllllllIlIIllllIlllllI instanceof EntityPlayerSP)) {
                final EntityPlayer lllllllllllllllllIlIIllllIllllll = lllllllllllllllllIlIIllllIlllllI;
                if (this.isValid(lllllllllllllllllIlIIllllIllllll) && lllllllllllllllllIlIIllllIllllll.getDistance((Entity)AutoWeb.mc.player) < AutoWeb.target.getDistance((Entity)AutoWeb.mc.player)) {
                    AutoWeb.target = lllllllllllllllllIlIIllllIllllll;
                    return;
                }
                continue;
            }
        }
        if (this.isValid(AutoWeb.target) && AutoWeb.mc.player.getDistance((Entity)AutoWeb.target) < 4.0f) {
            this.trap(AutoWeb.target);
        }
        else {
            this.delay = 0;
        }
    }
    
    public static Block getBlock(final BlockPos lllllllllllllllllIlIIllllllIIIll) {
        return getState(lllllllllllllllllIlIIllllllIIIll).getBlock();
    }
    
    @Override
    public void onDisable() {
        this.delay = 0;
        AutoWeb.yaw = AutoWeb.mc.player.rotationYaw;
        AutoWeb.pitch = AutoWeb.mc.player.rotationPitch;
        AutoWeb.target = null;
    }
    
    public void updateTarget() {
        for (final EntityPlayer lllllllllllllllllIlIIllllIIllIll : AutoWeb.mc.world.playerEntities) {
            if (!(lllllllllllllllllIlIIllllIIllIll instanceof EntityPlayerSP)) {
                final EntityPlayer lllllllllllllllllIlIIllllIIlllII = lllllllllllllllllIlIIllllIIllIll;
                if (lllllllllllllllllIlIIllllIIlllII instanceof EntityPlayerSP) {
                    continue;
                }
                if (!this.isValid(lllllllllllllllllIlIIllllIIlllII)) {
                    continue;
                }
                AutoWeb.target = lllllllllllllllllIlIIllllIIlllII;
            }
        }
    }
    
    public boolean isInBlockRange(final Entity lllllllllllllllllIlIlIIIIIIlllll) {
        return lllllllllllllllllIlIlIIIIIIlllll.getDistance((Entity)AutoWeb.mc.player) <= 4.0f;
    }
    
    public static boolean placeBlockLegit(final BlockPos lllllllllllllllllIlIIlllllIlIIII) {
        final Vec3d lllllllllllllllllIlIIlllllIIllll = new Vec3d(AutoWeb.mc.player.posX, AutoWeb.mc.player.posY + AutoWeb.mc.player.getEyeHeight(), AutoWeb.mc.player.posZ);
        final Vec3d lllllllllllllllllIlIIlllllIIlllI = new Vec3d((Vec3i)lllllllllllllllllIlIIlllllIlIIII).add(0.5, 0.5, 0.5);
        final float lllllllllllllllllIlIIlllllIIlIlI = (Object)EnumFacing.values();
        for (Exception lllllllllllllllllIlIIlllllIIlIIl = (Exception)lllllllllllllllllIlIIlllllIIlIlI.length, lllllllllllllllllIlIIlllllIIlIII = (Exception)0; lllllllllllllllllIlIIlllllIIlIII < lllllllllllllllllIlIIlllllIIlIIl; ++lllllllllllllllllIlIIlllllIIlIII) {
            final EnumFacing lllllllllllllllllIlIIlllllIlIIIl = lllllllllllllllllIlIIlllllIIlIlI[lllllllllllllllllIlIIlllllIIlIII];
            final BlockPos lllllllllllllllllIlIIlllllIlIIlI = lllllllllllllllllIlIIlllllIlIIII.offset(lllllllllllllllllIlIIlllllIlIIIl);
            if (canBeClicked(lllllllllllllllllIlIIlllllIlIIlI)) {
                final Vec3d lllllllllllllllllIlIIlllllIlIIll = lllllllllllllllllIlIIlllllIIlllI.add(new Vec3d(lllllllllllllllllIlIIlllllIlIIIl.getDirectionVec()).scale(0.5));
                if (lllllllllllllllllIlIIlllllIIllll.squareDistanceTo(lllllllllllllllllIlIIlllllIlIIll) <= 36.0) {
                    AutoWeb.mc.playerController.processRightClickBlock(AutoWeb.mc.player, AutoWeb.mc.world, lllllllllllllllllIlIIlllllIlIIlI, lllllllllllllllllIlIIlllllIlIIIl.getOpposite(), lllllllllllllllllIlIIlllllIlIIll, EnumHand.MAIN_HAND);
                    AutoWeb.mc.player.swingArm(EnumHand.MAIN_HAND);
                    try {
                        TimeUnit.MILLISECONDS.sleep(10L);
                    }
                    catch (InterruptedException lllllllllllllllllIlIIlllllIlIlII) {
                        lllllllllllllllllIlIIlllllIlIlII.printStackTrace();
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public EnumFacing getEnumFacing(final float lllllllllllllllllIlIIllllIIIlllI, final float lllllllllllllllllIlIIllllIIIllIl, final float lllllllllllllllllIlIIllllIIIllll) {
        return EnumFacing.getFacingFromVector(lllllllllllllllllIlIIllllIIIlllI, lllllllllllllllllIlIIllllIIIllIl, lllllllllllllllllIlIIllllIIIllll);
    }
    
    @Override
    public void onEnable() {
        this.delay = 0;
    }
    
    public static double roundToHalf(final double lllllllllllllllllIlIIllllIllIlll) {
        return Math.round(lllllllllllllllllIlIIllllIllIlll * 2.0) / 2.0;
    }
    
    public boolean isValid(final EntityPlayer lllllllllllllllllIlIIllllllllllI) {
        if (lllllllllllllllllIlIIllllllllllI instanceof EntityPlayer) {
            final EntityPlayer lllllllllllllllllIlIlIIIIIIIIIlI = lllllllllllllllllIlIIllllllllllI;
            if (this.isInBlockRange((Entity)lllllllllllllllllIlIlIIIIIIIIIlI) && lllllllllllllllllIlIlIIIIIIIIIlI.getHealth() > 0.0f && !lllllllllllllllllIlIlIIIIIIIIIlI.isDead && !lllllllllllllllllIlIlIIIIIIIIIlI.getName().startsWith("Body #") && !Friends.isFriend(lllllllllllllllllIlIlIIIIIIIIIlI.getName())) {
                return true;
            }
        }
        return false;
    }
    
    private boolean doesHotbarHaveObby() {
        for (int lllllllllllllllllIlIIllllllIlIIl = 36; lllllllllllllllllIlIIllllllIlIIl < 45; ++lllllllllllllllllIlIIllllllIlIIl) {
            final ItemStack lllllllllllllllllIlIIllllllIlIlI = AutoWeb.mc.player.inventoryContainer.getSlot(lllllllllllllllllIlIIllllllIlIIl).getStack();
            if (lllllllllllllllllIlIIllllllIlIlI != null && this.isStackObby(lllllllllllllllllIlIIllllllIlIlI)) {
                return true;
            }
        }
        return false;
    }
    
    public AutoWeb() {
        super("AutoWeb", "AutoTrap with webs", 0, Category.COMBAT, true);
    }
    
    public static boolean canBeClicked(final BlockPos lllllllllllllllllIlIlIIIIIIlllII) {
        return AutoWeb.mc.world.getBlockState(lllllllllllllllllIlIlIIIIIIlllII).getBlock().canCollideCheck(AutoWeb.mc.world.getBlockState(lllllllllllllllllIlIlIIIIIIlllII), false);
    }
    
    private static void faceVectorPacket(final Vec3d lllllllllllllllllIlIlIIIIIIlIIll) {
        final double lllllllllllllllllIlIlIIIIIIlIIlI = lllllllllllllllllIlIlIIIIIIlIIll.x - AutoWeb.mc.player.posX;
        final double lllllllllllllllllIlIlIIIIIIlIIIl = lllllllllllllllllIlIlIIIIIIlIIll.y - AutoWeb.mc.player.posY + AutoWeb.mc.player.getEyeHeight();
        final double lllllllllllllllllIlIlIIIIIIlIIII = lllllllllllllllllIlIlIIIIIIlIIll.z - AutoWeb.mc.player.posZ;
        final double lllllllllllllllllIlIlIIIIIIIllll = MathHelper.sqrt(lllllllllllllllllIlIlIIIIIIlIIlI * lllllllllllllllllIlIlIIIIIIlIIlI + lllllllllllllllllIlIlIIIIIIlIIII * lllllllllllllllllIlIlIIIIIIlIIII);
        final float lllllllllllllllllIlIlIIIIIIIlllI = (float)Math.toDegrees(Math.atan2(lllllllllllllllllIlIlIIIIIIlIIII, lllllllllllllllllIlIlIIIIIIlIIlI)) - 90.0f;
        final float lllllllllllllllllIlIlIIIIIIIllIl = (float)(-Math.toDegrees(Math.atan2(lllllllllllllllllIlIlIIIIIIlIIIl, lllllllllllllllllIlIlIIIIIIIllll)));
        AutoWeb.mc.getConnection().sendPacket((Packet)new CPacketPlayer.Rotation(AutoWeb.mc.player.rotationYaw + MathHelper.wrapDegrees(lllllllllllllllllIlIlIIIIIIIlllI - AutoWeb.mc.player.rotationYaw), AutoWeb.mc.player.rotationPitch + MathHelper.wrapDegrees(lllllllllllllllllIlIlIIIIIIIllIl - AutoWeb.mc.player.rotationPitch), AutoWeb.mc.player.onGround));
    }
}
