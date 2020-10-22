package com.elementars.eclient.util;

import net.minecraft.client.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.item.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import java.util.*;

public class BlockInteractionHelper
{
    private static final /* synthetic */ Minecraft mc;
    
    private static float[] getLegitRotations(final Vec3d lllllllllllllllllIIllIllIlllllIl) {
        final Vec3d lllllllllllllllllIIllIllIlllllII = getEyesPos();
        final double lllllllllllllllllIIllIllIllllIll = lllllllllllllllllIIllIllIlllllIl.x - lllllllllllllllllIIllIllIlllllII.x;
        final double lllllllllllllllllIIllIllIllllIlI = lllllllllllllllllIIllIllIlllllIl.y - lllllllllllllllllIIllIllIlllllII.y;
        final double lllllllllllllllllIIllIllIllllIIl = lllllllllllllllllIIllIllIlllllIl.z - lllllllllllllllllIIllIllIlllllII.z;
        final double lllllllllllllllllIIllIllIllllIII = Math.sqrt(lllllllllllllllllIIllIllIllllIll * lllllllllllllllllIIllIllIllllIll + lllllllllllllllllIIllIllIllllIIl * lllllllllllllllllIIllIllIllllIIl);
        final float lllllllllllllllllIIllIllIlllIlll = (float)Math.toDegrees(Math.atan2(lllllllllllllllllIIllIllIllllIIl, lllllllllllllllllIIllIllIllllIll)) - 90.0f;
        final float lllllllllllllllllIIllIllIlllIllI = (float)(-Math.toDegrees(Math.atan2(lllllllllllllllllIIllIllIllllIlI, lllllllllllllllllIIllIllIllllIII)));
        return new float[] { Wrapper.getPlayer().rotationYaw + MathHelper.wrapDegrees(lllllllllllllllllIIllIllIlllIlll - Wrapper.getPlayer().rotationYaw), Wrapper.getPlayer().rotationPitch + MathHelper.wrapDegrees(lllllllllllllllllIIllIllIlllIllI - Wrapper.getPlayer().rotationPitch) };
    }
    
    public static boolean canBeClicked(final BlockPos lllllllllllllllllIIllIllIlIlllII) {
        return getBlock(lllllllllllllllllIIllIllIlIlllII).canCollideCheck(getState(lllllllllllllllllIIllIllIlIlllII), false);
    }
    
    private static IBlockState getState(final BlockPos lllllllllllllllllIIllIllIlIlIlll) {
        return Wrapper.getWorld().getBlockState(lllllllllllllllllIIllIllIlIlIlll);
    }
    
    public static EnumFacing getPlaceableSide(final BlockPos lllllllllllllllllIIllIlIllIlllll) {
        final int lllllllllllllllllIIllIlIllIlllIl = (Object)EnumFacing.values();
        final byte lllllllllllllllllIIllIlIllIlllII = (byte)lllllllllllllllllIIllIlIllIlllIl.length;
        for (float lllllllllllllllllIIllIlIllIllIll = 0; lllllllllllllllllIIllIlIllIllIll < lllllllllllllllllIIllIlIllIlllII; ++lllllllllllllllllIIllIlIllIllIll) {
            final EnumFacing lllllllllllllllllIIllIlIlllIIIII = lllllllllllllllllIIllIlIllIlllIl[lllllllllllllllllIIllIlIllIllIll];
            final BlockPos lllllllllllllllllIIllIlIlllIIIIl = lllllllllllllllllIIllIlIllIlllll.offset(lllllllllllllllllIIllIlIlllIIIII);
            if (BlockInteractionHelper.mc.world.getBlockState(lllllllllllllllllIIllIlIlllIIIIl).getBlock().canCollideCheck(BlockInteractionHelper.mc.world.getBlockState(lllllllllllllllllIIllIlIlllIIIIl), false)) {
                final IBlockState lllllllllllllllllIIllIlIlllIIIlI = BlockInteractionHelper.mc.world.getBlockState(lllllllllllllllllIIllIlIlllIIIIl);
                if (!lllllllllllllllllIIllIlIlllIIIlI.getMaterial().isReplaceable()) {
                    return lllllllllllllllllIIllIlIlllIIIII;
                }
            }
        }
        return null;
    }
    
    public static float getYaw(final Entity lllllllllllllllllIIllIlIlIlIIlll) {
        final double lllllllllllllllllIIllIlIlIlIIllI = lllllllllllllllllIIllIlIlIlIIlll.posX - BlockInteractionHelper.mc.player.posX;
        final double lllllllllllllllllIIllIlIlIlIIlIl = lllllllllllllllllIIllIlIlIlIIlll.posZ - BlockInteractionHelper.mc.player.posZ;
        double lllllllllllllllllIIllIlIlIlIIlII = 0.0;
        if (lllllllllllllllllIIllIlIlIlIIlIl < 0.0 && lllllllllllllllllIIllIlIlIlIIllI < 0.0) {
            final double lllllllllllllllllIIllIlIlIlIlIIl = 90.0 + Math.toDegrees(Math.atan(lllllllllllllllllIIllIlIlIlIIlIl / lllllllllllllllllIIllIlIlIlIIllI));
        }
        else if (lllllllllllllllllIIllIlIlIlIIlIl < 0.0 && lllllllllllllllllIIllIlIlIlIIllI > 0.0) {
            final double lllllllllllllllllIIllIlIlIlIlIII = -90.0 + Math.toDegrees(Math.atan(lllllllllllllllllIIllIlIlIlIIlIl / lllllllllllllllllIIllIlIlIlIIllI));
        }
        else {
            lllllllllllllllllIIllIlIlIlIIlII = Math.toDegrees(-Math.atan(lllllllllllllllllIIllIlIlIlIIllI / lllllllllllllllllIIllIlIlIlIIlIl));
        }
        return MathHelper.wrapDegrees(-(BlockInteractionHelper.mc.player.rotationYaw - (float)lllllllllllllllllIIllIlIlIlIIlII));
    }
    
    public static void faceVectorPacketInstant(final Vec3d lllllllllllllllllIIllIllIllIlIIl) {
        final float[] lllllllllllllllllIIllIllIllIlIlI = getLegitRotations(lllllllllllllllllIIllIllIllIlIIl);
        Wrapper.getPlayer().connection.sendPacket((Packet)new CPacketPlayer.Rotation(lllllllllllllllllIIllIllIllIlIlI[0], lllllllllllllllllIIllIllIllIlIlI[1], Wrapper.getPlayer().onGround));
    }
    
    public static boolean hotbarSlotCheckEmpty(final ItemStack lllllllllllllllllIIllIllllIlIllI) {
        return lllllllllllllllllIIllIllllIlIllI != ItemStack.EMPTY;
    }
    
    public static double[] calculateLookAt(final double lllllllllllllllllIIllIlllIllIlII, final double lllllllllllllllllIIllIlllIllIIll, final double lllllllllllllllllIIllIlllIlIIlll, final EntityPlayer lllllllllllllllllIIllIlllIllIIIl) {
        double lllllllllllllllllIIllIlllIllIIII = lllllllllllllllllIIllIlllIllIIIl.posX - lllllllllllllllllIIllIlllIllIlII;
        double lllllllllllllllllIIllIlllIlIllll = lllllllllllllllllIIllIlllIllIIIl.posY - lllllllllllllllllIIllIlllIllIIll;
        double lllllllllllllllllIIllIlllIlIlllI = lllllllllllllllllIIllIlllIllIIIl.posZ - lllllllllllllllllIIllIlllIlIIlll;
        final double lllllllllllllllllIIllIlllIlIllIl = Math.sqrt(lllllllllllllllllIIllIlllIllIIII * lllllllllllllllllIIllIlllIllIIII + lllllllllllllllllIIllIlllIlIllll * lllllllllllllllllIIllIlllIlIllll + lllllllllllllllllIIllIlllIlIlllI * lllllllllllllllllIIllIlllIlIlllI);
        lllllllllllllllllIIllIlllIllIIII /= lllllllllllllllllIIllIlllIlIllIl;
        lllllllllllllllllIIllIlllIlIllll /= lllllllllllllllllIIllIlllIlIllIl;
        lllllllllllllllllIIllIlllIlIlllI /= lllllllllllllllllIIllIlllIlIllIl;
        double lllllllllllllllllIIllIlllIlIllII = Math.asin(lllllllllllllllllIIllIlllIlIllll);
        double lllllllllllllllllIIllIlllIlIlIll = Math.atan2(lllllllllllllllllIIllIlllIlIlllI, lllllllllllllllllIIllIlllIllIIII);
        lllllllllllllllllIIllIlllIlIllII = lllllllllllllllllIIllIlllIlIllII * 180.0 / 3.141592653589793;
        lllllllllllllllllIIllIlllIlIlIll = lllllllllllllllllIIllIlllIlIlIll * 180.0 / 3.141592653589793;
        lllllllllllllllllIIllIlllIlIlIll += 90.0;
        return new double[] { lllllllllllllllllIIllIlllIlIlIll, lllllllllllllllllIIllIlllIlIllII };
    }
    
    public static void rotate(final float lllllllllllllllllIIllIllllIIllIl, final float lllllllllllllllllIIllIllllIIllII) {
        Minecraft.getMinecraft().player.rotationYaw = lllllllllllllllllIIllIllllIIllIl;
        Minecraft.getMinecraft().player.rotationPitch = lllllllllllllllllIIllIllllIIllII;
    }
    
    private static PlayerControllerMP getPlayerController() {
        return Minecraft.getMinecraft().playerController;
    }
    
    private static Vec3d getEyesPos() {
        return new Vec3d(Wrapper.getPlayer().posX, Wrapper.getPlayer().posY + Wrapper.getPlayer().getEyeHeight(), Wrapper.getPlayer().posZ);
    }
    
    public static boolean blockCheckNonBlock(final ItemStack lllllllllllllllllIIllIllllIlIIIl) {
        return lllllllllllllllllIIllIllllIlIIIl.getItem() instanceof ItemBlock;
    }
    
    public static float[] getDirectionToBlock(final int lllllllllllllllllIIllIlIllIlIIlI, final int lllllllllllllllllIIllIlIllIIllII, final int lllllllllllllllllIIllIlIllIlIIII, final EnumFacing lllllllllllllllllIIllIlIllIIllll) {
        final EntityEgg lllllllllllllllllIIllIlIllIIlllI = new EntityEgg((World)BlockInteractionHelper.mc.world);
        lllllllllllllllllIIllIlIllIIlllI.posX = lllllllllllllllllIIllIlIllIlIIlI + 0.5;
        lllllllllllllllllIIllIlIllIIlllI.posY = lllllllllllllllllIIllIlIllIIllII + 0.5;
        lllllllllllllllllIIllIlIllIIlllI.posZ = lllllllllllllllllIIllIlIllIlIIII + 0.5;
        final EntityEgg entityEgg = lllllllllllllllllIIllIlIllIIlllI;
        entityEgg.posX += lllllllllllllllllIIllIlIllIIllll.getDirectionVec().getX() * 0.25;
        final EntityEgg entityEgg2 = lllllllllllllllllIIllIlIllIIlllI;
        entityEgg2.posY += lllllllllllllllllIIllIlIllIIllll.getDirectionVec().getY() * 0.25;
        final EntityEgg entityEgg3 = lllllllllllllllllIIllIlIllIIlllI;
        entityEgg3.posZ += lllllllllllllllllIIllIlIllIIllll.getDirectionVec().getZ() * 0.25;
        return getDirectionToEntity((Entity)lllllllllllllllllIIllIlIllIIlllI);
    }
    
    public static List<BlockPos> getSphere(final BlockPos lllllllllllllllllIIllIllIIllIIlI, final float lllllllllllllllllIIllIllIIlIIlll, final int lllllllllllllllllIIllIllIIlIIllI, final boolean lllllllllllllllllIIllIllIIlIIlIl, final boolean lllllllllllllllllIIllIllIIlIIlII, final int lllllllllllllllllIIllIllIIlIllIl) {
        final List<BlockPos> lllllllllllllllllIIllIllIIlIllII = new ArrayList<BlockPos>();
        final int lllllllllllllllllIIllIllIIlIlIll = lllllllllllllllllIIllIllIIllIIlI.getX();
        final int lllllllllllllllllIIllIllIIlIlIlI = lllllllllllllllllIIllIllIIllIIlI.getY();
        final int lllllllllllllllllIIllIllIIlIlIIl = lllllllllllllllllIIllIllIIllIIlI.getZ();
        for (int lllllllllllllllllIIllIllIIllIIll = lllllllllllllllllIIllIllIIlIlIll - (int)lllllllllllllllllIIllIllIIlIIlll; lllllllllllllllllIIllIllIIllIIll <= lllllllllllllllllIIllIllIIlIlIll + lllllllllllllllllIIllIllIIlIIlll; ++lllllllllllllllllIIllIllIIllIIll) {
            for (int lllllllllllllllllIIllIllIIllIlII = lllllllllllllllllIIllIllIIlIlIIl - (int)lllllllllllllllllIIllIllIIlIIlll; lllllllllllllllllIIllIllIIllIlII <= lllllllllllllllllIIllIllIIlIlIIl + lllllllllllllllllIIllIllIIlIIlll; ++lllllllllllllllllIIllIllIIllIlII) {
                for (int lllllllllllllllllIIllIllIIllIlIl = lllllllllllllllllIIllIllIIlIIlII ? (lllllllllllllllllIIllIllIIlIlIlI - (int)lllllllllllllllllIIllIllIIlIIlll) : lllllllllllllllllIIllIllIIlIlIlI; lllllllllllllllllIIllIllIIllIlIl < (lllllllllllllllllIIllIllIIlIIlII ? (lllllllllllllllllIIllIllIIlIlIlI + lllllllllllllllllIIllIllIIlIIlll) : ((float)(lllllllllllllllllIIllIllIIlIlIlI + lllllllllllllllllIIllIllIIlIIllI))); ++lllllllllllllllllIIllIllIIllIlIl) {
                    final double lllllllllllllllllIIllIllIIllIllI = (lllllllllllllllllIIllIllIIlIlIll - lllllllllllllllllIIllIllIIllIIll) * (lllllllllllllllllIIllIllIIlIlIll - lllllllllllllllllIIllIllIIllIIll) + (lllllllllllllllllIIllIllIIlIlIIl - lllllllllllllllllIIllIllIIllIlII) * (lllllllllllllllllIIllIllIIlIlIIl - lllllllllllllllllIIllIllIIllIlII) + (lllllllllllllllllIIllIllIIlIIlII ? ((lllllllllllllllllIIllIllIIlIlIlI - lllllllllllllllllIIllIllIIllIlIl) * (lllllllllllllllllIIllIllIIlIlIlI - lllllllllllllllllIIllIllIIllIlIl)) : 0);
                    if (lllllllllllllllllIIllIllIIllIllI < lllllllllllllllllIIllIllIIlIIlll * lllllllllllllllllIIllIllIIlIIlll && (!lllllllllllllllllIIllIllIIlIIlIl || lllllllllllllllllIIllIllIIllIllI >= (lllllllllllllllllIIllIllIIlIIlll - 1.0f) * (lllllllllllllllllIIllIllIIlIIlll - 1.0f))) {
                        final BlockPos lllllllllllllllllIIllIllIIllIlll = new BlockPos(lllllllllllllllllIIllIllIIllIIll, lllllllllllllllllIIllIllIIllIlIl + lllllllllllllllllIIllIllIIlIllIl, lllllllllllllllllIIllIllIIllIlII);
                        lllllllllllllllllIIllIllIIlIllII.add(lllllllllllllllllIIllIllIIllIlll);
                    }
                }
            }
        }
        return lllllllllllllllllIIllIllIIlIllII;
    }
    
    private static void processRightClickBlock(final BlockPos lllllllllllllllllIIllIllIllIIlII, final EnumFacing lllllllllllllllllIIllIllIllIIIll, final Vec3d lllllllllllllllllIIllIllIlIlllll) {
        getPlayerController().processRightClickBlock(Wrapper.getPlayer(), BlockInteractionHelper.mc.world, lllllllllllllllllIIllIllIllIIlII, lllllllllllllllllIIllIllIllIIIll, lllllllllllllllllIIllIllIlIlllll, EnumHand.MAIN_HAND);
    }
    
    public static float[] getRotationNeededForBlock(final EntityPlayer lllllllllllllllllIIllIlIlIllllIl, final BlockPos lllllllllllllllllIIllIlIlIllIlII) {
        final double lllllllllllllllllIIllIlIlIlllIll = lllllllllllllllllIIllIlIlIllIlII.getX() - lllllllllllllllllIIllIlIlIllllIl.posX;
        final double lllllllllllllllllIIllIlIlIlllIlI = lllllllllllllllllIIllIlIlIllIlII.getY() + 0.5 - (lllllllllllllllllIIllIlIlIllllIl.posY + lllllllllllllllllIIllIlIlIllllIl.getEyeHeight());
        final double lllllllllllllllllIIllIlIlIlllIIl = lllllllllllllllllIIllIlIlIllIlII.getZ() - lllllllllllllllllIIllIlIlIllllIl.posZ;
        final double lllllllllllllllllIIllIlIlIlllIII = Math.sqrt(lllllllllllllllllIIllIlIlIlllIll * lllllllllllllllllIIllIlIlIlllIll + lllllllllllllllllIIllIlIlIlllIIl * lllllllllllllllllIIllIlIlIlllIIl);
        final float lllllllllllllllllIIllIlIlIllIlll = (float)(Math.atan2(lllllllllllllllllIIllIlIlIlllIIl, lllllllllllllllllIIllIlIlIlllIll) * 180.0 / 3.141592653589793) - 90.0f;
        final float lllllllllllllllllIIllIlIlIllIllI = (float)(-(Math.atan2(lllllllllllllllllIIllIlIlIlllIlI, lllllllllllllllllIIllIlIlIlllIII) * 180.0 / 3.141592653589793));
        return new float[] { lllllllllllllllllIIllIlIlIllIlll, lllllllllllllllllIIllIlIlIllIllI };
    }
    
    private static Block getBlock(final BlockPos lllllllllllllllllIIllIllIlIllIIl) {
        return getState(lllllllllllllllllIIllIllIlIllIIl).getBlock();
    }
    
    public static void placeBlockScaffold(final BlockPos lllllllllllllllllIIllIlllIIIlllI) {
        final Vec3d lllllllllllllllllIIllIlllIIIllll = new Vec3d(Wrapper.getPlayer().posX, Wrapper.getPlayer().posY + Wrapper.getPlayer().getEyeHeight(), Wrapper.getPlayer().posZ);
        final int lllllllllllllllllIIllIlllIIIllII = (Object)EnumFacing.values();
        final long lllllllllllllllllIIllIlllIIIlIll = lllllllllllllllllIIllIlllIIIllII.length;
        for (char lllllllllllllllllIIllIlllIIIlIlI = '\0'; lllllllllllllllllIIllIlllIIIlIlI < lllllllllllllllllIIllIlllIIIlIll; ++lllllllllllllllllIIllIlllIIIlIlI) {
            final EnumFacing lllllllllllllllllIIllIlllIIlIIIl = lllllllllllllllllIIllIlllIIIllII[lllllllllllllllllIIllIlllIIIlIlI];
            final BlockPos lllllllllllllllllIIllIlllIIlIIll = lllllllllllllllllIIllIlllIIIlllI.offset(lllllllllllllllllIIllIlllIIlIIIl);
            final EnumFacing lllllllllllllllllIIllIlllIIlIIlI = lllllllllllllllllIIllIlllIIlIIIl.getOpposite();
            if (canBeClicked(lllllllllllllllllIIllIlllIIlIIll)) {
                final Vec3d lllllllllllllllllIIllIlllIIlIlII = new Vec3d((Vec3i)lllllllllllllllllIIllIlllIIlIIll).add(0.5, 0.5, 0.5).add(new Vec3d(lllllllllllllllllIIllIlllIIlIIlI.getDirectionVec()).rotatePitch(0.5f));
                if (lllllllllllllllllIIllIlllIIIllll.squareDistanceTo(lllllllllllllllllIIllIlllIIlIlII) <= 18.0625) {
                    faceVectorPacketInstant(lllllllllllllllllIIllIlllIIlIlII);
                    processRightClickBlock(lllllllllllllllllIIllIlllIIlIIll, lllllllllllllllllIIllIlllIIlIIlI, lllllllllllllllllIIllIlllIIlIlII);
                    Wrapper.getPlayer().swingArm(EnumHand.MAIN_HAND);
                    BlockInteractionHelper.mc.rightClickDelayTimer = 4;
                    return;
                }
            }
        }
    }
    
    public static boolean checkForNeighbours(final BlockPos lllllllllllllllllIIllIllIlIIllIl) {
        if (!hasNeighbour(lllllllllllllllllIIllIllIlIIllIl)) {
            final boolean lllllllllllllllllIIllIllIlIIlIll = (Object)EnumFacing.values();
            final char lllllllllllllllllIIllIllIlIIlIlI = (char)lllllllllllllllllIIllIllIlIIlIll.length;
            for (String lllllllllllllllllIIllIllIlIIlIIl = (String)0; lllllllllllllllllIIllIllIlIIlIIl < lllllllllllllllllIIllIllIlIIlIlI; ++lllllllllllllllllIIllIllIlIIlIIl) {
                final EnumFacing lllllllllllllllllIIllIllIlIIlllI = lllllllllllllllllIIllIllIlIIlIll[lllllllllllllllllIIllIllIlIIlIIl];
                final BlockPos lllllllllllllllllIIllIllIlIIllll = lllllllllllllllllIIllIllIlIIllIl.offset(lllllllllllllllllIIllIllIlIIlllI);
                if (hasNeighbour(lllllllllllllllllIIllIllIlIIllll)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    
    public static void rotate(final double[] lllllllllllllllllIIllIllllIIIIll) {
        Minecraft.getMinecraft().player.rotationYaw = (float)lllllllllllllllllIIllIllllIIIIll[0];
        Minecraft.getMinecraft().player.rotationPitch = (float)lllllllllllllllllIIllIllllIIIIll[1];
    }
    
    private static boolean hasNeighbour(final BlockPos lllllllllllllllllIIllIlIllllIIII) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_1        /* lllllllllllllllllIIllIlIlllIlllI */
        //     4: aload_1         /* lllllllllllllllllIIllIlIlllIlllI */
        //     5: arraylength    
        //     6: istore_2        /* lllllllllllllllllIIllIlIlllIllIl */
        //     7: iconst_0       
        //     8: istore_3        /* lllllllllllllllllIIllIlIlllIllII */
        //     9: iload_3         /* lllllllllllllllllIIllIlIlllIllII */
        //    10: iload_2         /* lllllllllllllllllIIllIlIlllIllIl */
        //    11: if_icmpge       54
        //    14: aload_1         /* lllllllllllllllllIIllIlIlllIlllI */
        //    15: iload_3         /* lllllllllllllllllIIllIlIlllIllII */
        //    16: aaload         
        //    17: astore          lllllllllllllllllIIllIlIllllIIIl
        //    19: aload_0         /* lllllllllllllllllIIllIlIlllIllll */
        //    20: aload           lllllllllllllllllIIllIlIllllIIIl
        //    22: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //    25: astore          lllllllllllllllllIIllIlIllllIIlI
        //    27: invokestatic    com/elementars/eclient/util/Wrapper.getWorld:()Lnet/minecraft/world/World;
        //    30: aload           lllllllllllllllllIIllIlIllllIIlI
        //    32: invokevirtual   net/minecraft/world/World.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    35: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //    40: invokevirtual   net/minecraft/block/material/Material.isReplaceable:()Z
        //    43: ifne            48
        //    46: iconst_1       
        //    47: ireturn        
        //    48: iinc            lllllllllllllllllIIllIlIlllIllII, 1
        //    51: goto            9
        //    54: iconst_0       
        //    55: ireturn        
        //    StackMapTable: 00 03 FE 00 09 07 00 82 01 01 FD 00 26 07 00 7C 07 00 84 F9 00 05
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.lang.Thread.run(Thread.java:748)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static float getPitch(final Entity lllllllllllllllllIIllIlIlIIlIIII) {
        final double lllllllllllllllllIIllIlIlIIlIlIl = lllllllllllllllllIIllIlIlIIlIIII.posX - BlockInteractionHelper.mc.player.posX;
        final double lllllllllllllllllIIllIlIlIIlIlII = lllllllllllllllllIIllIlIlIIlIIII.posZ - BlockInteractionHelper.mc.player.posZ;
        final double lllllllllllllllllIIllIlIlIIlIIll = lllllllllllllllllIIllIlIlIIlIIII.posY - 1.6 + lllllllllllllllllIIllIlIlIIlIIII.getEyeHeight() - BlockInteractionHelper.mc.player.posY;
        final double lllllllllllllllllIIllIlIlIIlIIlI = MathHelper.sqrt(lllllllllllllllllIIllIlIlIIlIlIl * lllllllllllllllllIIllIlIlIIlIlIl + lllllllllllllllllIIllIlIlIIlIlII * lllllllllllllllllIIllIlIlIIlIlII);
        final double lllllllllllllllllIIllIlIlIIlIIIl = -Math.toDegrees(Math.atan(lllllllllllllllllIIllIlIlIIlIIll / lllllllllllllllllIIllIlIlIIlIIlI));
        return -MathHelper.wrapDegrees(BlockInteractionHelper.mc.player.rotationPitch - (float)lllllllllllllllllIIllIlIlIIlIIIl);
    }
    
    private static float[] getDirectionToEntity(final Entity lllllllllllllllllIIllIlIllIIIllI) {
        return new float[] { getYaw(lllllllllllllllllIIllIlIllIIIllI) + BlockInteractionHelper.mc.player.rotationYaw, getPitch(lllllllllllllllllIIllIlIllIIIllI) + BlockInteractionHelper.mc.player.rotationPitch };
    }
    
    private static float wrapAngleTo180(float lllllllllllllllllIIllIlIlIIlllIl) {
        for (lllllllllllllllllIIllIlIlIIlllIl %= 360.0f; lllllllllllllllllIIllIlIlIIlllIl >= 180.0f; lllllllllllllllllIIllIlIlIIlllIl -= 360.0f) {}
        while (lllllllllllllllllIIllIlIlIIlllIl < -180.0f) {
            lllllllllllllllllIIllIlIlIIlllIl += 360.0f;
        }
        return lllllllllllllllllIIllIlIlIIlllIl;
    }
    
    public static void lookAtBlock(final BlockPos lllllllllllllllllIIllIlllIIlllll) {
        rotate(calculateLookAt(lllllllllllllllllIIllIlllIIlllll.getX(), lllllllllllllllllIIllIlllIIlllll.getY(), lllllllllllllllllIIllIlllIIlllll.getZ(), (EntityPlayer)Minecraft.getMinecraft().player));
    }
    
    public static List<BlockPos> getCircle(final BlockPos lllllllllllllllllIIllIllIIIIIIll, final int lllllllllllllllllIIllIllIIIIIIlI, final float lllllllllllllllllIIllIllIIIIIIIl, final boolean lllllllllllllllllIIllIllIIIIIIII) {
        final List<BlockPos> lllllllllllllllllIIllIllIIIIIllI = new ArrayList<BlockPos>();
        final int lllllllllllllllllIIllIllIIIIIlIl = lllllllllllllllllIIllIllIIIIIIll.getX();
        final int lllllllllllllllllIIllIllIIIIIlII = lllllllllllllllllIIllIllIIIIIIll.getZ();
        for (int lllllllllllllllllIIllIllIIIIlIll = lllllllllllllllllIIllIllIIIIIlIl - (int)lllllllllllllllllIIllIllIIIIIIIl; lllllllllllllllllIIllIllIIIIlIll <= lllllllllllllllllIIllIllIIIIIlIl + lllllllllllllllllIIllIllIIIIIIIl; ++lllllllllllllllllIIllIllIIIIlIll) {
            for (int lllllllllllllllllIIllIllIIIIllII = lllllllllllllllllIIllIllIIIIIlII - (int)lllllllllllllllllIIllIllIIIIIIIl; lllllllllllllllllIIllIllIIIIllII <= lllllllllllllllllIIllIllIIIIIlII + lllllllllllllllllIIllIllIIIIIIIl; ++lllllllllllllllllIIllIllIIIIllII) {
                final double lllllllllllllllllIIllIllIIIIllIl = (lllllllllllllllllIIllIllIIIIIlIl - lllllllllllllllllIIllIllIIIIlIll) * (lllllllllllllllllIIllIllIIIIIlIl - lllllllllllllllllIIllIllIIIIlIll) + (lllllllllllllllllIIllIllIIIIIlII - lllllllllllllllllIIllIllIIIIllII) * (lllllllllllllllllIIllIllIIIIIlII - lllllllllllllllllIIllIllIIIIllII);
                if (lllllllllllllllllIIllIllIIIIllIl < lllllllllllllllllIIllIllIIIIIIIl * lllllllllllllllllIIllIllIIIIIIIl && (!lllllllllllllllllIIllIllIIIIIIII || lllllllllllllllllIIllIllIIIIllIl >= (lllllllllllllllllIIllIllIIIIIIIl - 1.0f) * (lllllllllllllllllIIllIllIIIIIIIl - 1.0f))) {
                    final BlockPos lllllllllllllllllIIllIllIIIIlllI = new BlockPos(lllllllllllllllllIIllIllIIIIlIll, lllllllllllllllllIIllIllIIIIIIlI, lllllllllllllllllIIllIllIIIIllII);
                    lllllllllllllllllIIllIllIIIIIllI.add(lllllllllllllllllIIllIllIIIIlllI);
                }
            }
        }
        return lllllllllllllllllIIllIllIIIIIllI;
    }
    
    static {
        blackList = Arrays.asList(Blocks.ENDER_CHEST, (Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, (Block)Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER);
        shulkerList = Arrays.asList(Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX);
        mc = Minecraft.getMinecraft();
    }
}
