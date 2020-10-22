package com.elementars.eclient.util;

import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;

public class EntityUtil
{
    public static boolean isHostileMob(final Entity llIIlIlIlIlIIl) {
        return llIIlIlIlIlIIl.isCreatureType(EnumCreatureType.MONSTER, false) && !isNeutralMob(llIIlIlIlIlIIl);
    }
    
    public static Vec3d getInterpolatedEyePos(final Entity llIIlIlIIlIlll, final float llIIlIlIIllIII) {
        return getInterpolatedPos(llIIlIlIIlIlll, llIIlIlIIllIII).add(0.0, (double)llIIlIlIIlIlll.getEyeHeight(), 0.0);
    }
    
    public static boolean isLiving(final Entity llIIlIllIIllll) {
        return llIIlIllIIllll instanceof EntityLivingBase;
    }
    
    public static Vec3d getInterpolatedPos(final Entity llIIlIlIlIIlIl, final float llIIlIlIlIIIlI) {
        return new Vec3d(llIIlIlIlIIlIl.lastTickPosX, llIIlIlIlIIlIl.lastTickPosY, llIIlIlIlIIlIl.lastTickPosZ).add(getInterpolatedAmount(llIIlIlIlIIlIl, llIIlIlIlIIIlI));
    }
    
    public static Vec3d getInterpolatedAmount(final Entity llIIlIlIllllIl, final Vec3d llIIlIlIlllIlI) {
        return getInterpolatedAmount(llIIlIlIllllIl, llIIlIlIlllIlI.x, llIIlIlIlllIlI.y, llIIlIlIlllIlI.z);
    }
    
    public static double[] calculateLookAt(final double llIIlIIllIIlII, final double llIIlIIlIllIII, final double llIIlIIlIlIlll, final EntityPlayer llIIlIIlIlIllI) {
        double llIIlIIllIIIII = llIIlIIlIlIllI.posX - llIIlIIllIIlII;
        double llIIlIIlIlllll = llIIlIIlIlIllI.posY - llIIlIIlIllIII;
        double llIIlIIlIllllI = llIIlIIlIlIllI.posZ - llIIlIIlIlIlll;
        final double llIIlIIlIlllIl = Math.sqrt(llIIlIIllIIIII * llIIlIIllIIIII + llIIlIIlIlllll * llIIlIIlIlllll + llIIlIIlIllllI * llIIlIIlIllllI);
        llIIlIIllIIIII /= llIIlIIlIlllIl;
        llIIlIIlIlllll /= llIIlIIlIlllIl;
        llIIlIIlIllllI /= llIIlIIlIlllIl;
        double llIIlIIlIlllII = Math.asin(llIIlIIlIlllll);
        double llIIlIIlIllIll = Math.atan2(llIIlIIlIllllI, llIIlIIllIIIII);
        llIIlIIlIlllII = llIIlIIlIlllII * 180.0 / 3.141592653589793;
        llIIlIIlIllIll = llIIlIIlIllIll * 180.0 / 3.141592653589793;
        llIIlIIlIllIll += 90.0;
        return new double[] { llIIlIIlIllIll, llIIlIIlIlllII };
    }
    
    public static Vec3d getInterpolatedAmount(final Entity llIIlIlIllIlIl, final double llIIlIlIllIlII) {
        return getInterpolatedAmount(llIIlIlIllIlIl, llIIlIlIllIlII, llIIlIlIllIlII, llIIlIlIllIlII);
    }
    
    public static double getRelativeZ(final float llIIlIIlIIlIIl) {
        return MathHelper.cos(llIIlIIlIIlIIl * 0.017453292f);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity llIIlIllIIIlll, final double llIIlIllIIIIlI, final double llIIlIllIIIIIl, final double llIIlIllIIIlII) {
        return new Vec3d((llIIlIllIIIlll.posX - llIIlIllIIIlll.lastTickPosX) * llIIlIllIIIIlI, (llIIlIllIIIlll.posY - llIIlIllIIIlll.lastTickPosY) * llIIlIllIIIIIl, (llIIlIllIIIlll.posZ - llIIlIllIIIlll.lastTickPosZ) * llIIlIllIIIlII);
    }
    
    public static boolean isMobAggressive(final Entity llIIlIlIllIIlI) {
        if (llIIlIlIllIIlI instanceof EntityPigZombie) {
            if (((EntityPigZombie)llIIlIlIllIIlI).isArmsRaised() || ((EntityPigZombie)llIIlIlIllIIlI).isAngry()) {
                return true;
            }
        }
        else {
            if (llIIlIlIllIIlI instanceof EntityWolf) {
                return ((EntityWolf)llIIlIlIllIIlI).isAngry() && !Wrapper.getPlayer().equals((Object)((EntityWolf)llIIlIlIllIIlI).getOwner());
            }
            if (llIIlIlIllIIlI instanceof EntityEnderman) {
                return ((EntityEnderman)llIIlIlIllIIlI).isScreaming();
            }
        }
        return isHostileMob(llIIlIlIllIIlI);
    }
    
    public static boolean isAboveWater(final Entity llIIlIlIIIIIIl) {
        return isAboveWater(llIIlIlIIIIIIl, false);
    }
    
    public static boolean isPassive(final Entity llIIlIllIlIIlI) {
        return (!(llIIlIllIlIIlI instanceof EntityWolf) || !((EntityWolf)llIIlIllIlIIlI).isAngry()) && (llIIlIllIlIIlI instanceof EntityAnimal || llIIlIllIlIIlI instanceof EntityAgeable || llIIlIllIlIIlI instanceof EntityTameable || llIIlIllIlIIlI instanceof EntityAmbientCreature || llIIlIllIlIIlI instanceof EntitySquid || (llIIlIllIlIIlI instanceof EntityIronGolem && ((EntityIronGolem)llIIlIllIlIIlI).getRevengeTarget() == null));
    }
    
    public static boolean isFriendlyMob(final Entity llIIlIlIlIlIll) {
        return (llIIlIlIlIlIll.isCreatureType(EnumCreatureType.CREATURE, false) && !isNeutralMob(llIIlIlIlIlIll)) || llIIlIlIlIlIll.isCreatureType(EnumCreatureType.AMBIENT, false) || llIIlIlIlIlIll instanceof EntityVillager || llIIlIlIlIlIll instanceof EntityIronGolem || (isNeutralMob(llIIlIlIlIlIll) && !isMobAggressive(llIIlIlIlIlIll));
    }
    
    public static Vec3d getInterpolatedRenderPos(final Entity llIIlIlIIlllll, final float llIIlIlIIlllII) {
        return getInterpolatedPos(llIIlIlIIlllll, llIIlIlIIlllII).subtract(Wrapper.getMinecraft().getRenderManager().renderPosX, Wrapper.getMinecraft().getRenderManager().renderPosY, Wrapper.getMinecraft().getRenderManager().renderPosZ);
    }
    
    public static double getRelativeX(final float llIIlIIlIIlIll) {
        return MathHelper.sin(-llIIlIIlIIlIll * 0.017453292f);
    }
    
    public static boolean isDrivenByPlayer(final Entity llIIlIlIIIIlIl) {
        return Wrapper.getPlayer() != null && llIIlIlIIIIlIl != null && llIIlIlIIIIlIl.equals((Object)Wrapper.getPlayer().getRidingEntity());
    }
    
    public static boolean isPlayer(final Entity llIIlIIlIIlllI) {
        return llIIlIIlIIlllI instanceof EntityPlayer;
    }
    
    public static boolean isNeutralMob(final Entity llIIlIlIlIllll) {
        return llIIlIlIlIllll instanceof EntityPigZombie || llIIlIlIlIllll instanceof EntityWolf || llIIlIlIlIllll instanceof EntityEnderman;
    }
    
    public static boolean isFakeLocalPlayer(final Entity llIIlIllIIllII) {
        return llIIlIllIIllII != null && llIIlIllIIllII.getEntityId() == -100 && Wrapper.getPlayer() != llIIlIllIIllII;
    }
    
    public static boolean isInWater(final Entity llIIlIlIIIlIll) {
        if (llIIlIlIIIlIll == null) {
            return false;
        }
        final double llIIlIlIIIllII = llIIlIlIIIlIll.posY + 0.01;
        for (int llIIlIlIIIlllI = MathHelper.floor(llIIlIlIIIlIll.posX); llIIlIlIIIlllI < MathHelper.ceil(llIIlIlIIIlIll.posX); ++llIIlIlIIIlllI) {
            for (int llIIlIlIIIllll = MathHelper.floor(llIIlIlIIIlIll.posZ); llIIlIlIIIllll < MathHelper.ceil(llIIlIlIIIlIll.posZ); ++llIIlIlIIIllll) {
                final BlockPos llIIlIlIIlIIII = new BlockPos(llIIlIlIIIlllI, (int)llIIlIlIIIllII, llIIlIlIIIllll);
                if (Wrapper.getWorld().getBlockState(llIIlIlIIlIIII).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isAboveWater(final Entity llIIlIIlllIlll, final boolean llIIlIIlllIIll) {
        if (llIIlIIlllIlll == null) {
            return false;
        }
        final double llIIlIIlllIlIl = llIIlIIlllIlll.posY - (llIIlIIlllIIll ? 0.03 : (isPlayer(llIIlIIlllIlll) ? 0.2 : 0.5));
        for (int llIIlIIllllIII = MathHelper.floor(llIIlIIlllIlll.posX); llIIlIIllllIII < MathHelper.ceil(llIIlIIlllIlll.posX); ++llIIlIIllllIII) {
            for (int llIIlIIllllIIl = MathHelper.floor(llIIlIIlllIlll.posZ); llIIlIIllllIIl < MathHelper.ceil(llIIlIIlllIlll.posZ); ++llIIlIIllllIIl) {
                final BlockPos llIIlIIllllIlI = new BlockPos(llIIlIIllllIII, MathHelper.floor(llIIlIIlllIlIl), llIIlIIllllIIl);
                if (Wrapper.getWorld().getBlockState(llIIlIIllllIlI).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
}
