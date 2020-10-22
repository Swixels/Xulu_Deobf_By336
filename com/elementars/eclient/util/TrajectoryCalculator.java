package com.elementars.eclient.util;

import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.block.material.*;
import java.util.*;

public class TrajectoryCalculator
{
    public static Vec3d mult(final Vec3d llllllllllllllllIlIlllIllllIlIIl, final float llllllllllllllllIlIlllIllllIIllI) {
        return new Vec3d(llllllllllllllllIlIlllIllllIlIIl.x * llllllllllllllllIlIlllIllllIIllI, llllllllllllllllIlIlllIllllIlIIl.y * llllllllllllllllIlIlllIllllIIllI, llllllllllllllllIlIlllIllllIlIIl.z * llllllllllllllllIlIlllIllllIIllI);
    }
    
    public static double interpolate(final double llllllllllllllllIlIlllIllllIllll, final double llllllllllllllllIlIlllIllllIllII) {
        return llllllllllllllllIlIlllIllllIllII + (llllllllllllllllIlIlllIllllIllll - llllllllllllllllIlIlllIllllIllII) * Wrapper.getMinecraft().getRenderPartialTicks();
    }
    
    public static double[] interpolate(final Entity llllllllllllllllIlIlllIlllllIlIl) {
        final double llllllllllllllllIlIlllIllllllIII = interpolate(llllllllllllllllIlIlllIlllllIlIl.posX, llllllllllllllllIlIlllIlllllIlIl.lastTickPosX) - Wrapper.getMinecraft().renderManager.renderPosX;
        final double llllllllllllllllIlIlllIlllllIlll = interpolate(llllllllllllllllIlIlllIlllllIlIl.posY, llllllllllllllllIlIlllIlllllIlIl.lastTickPosY) - Wrapper.getMinecraft().renderManager.renderPosY;
        final double llllllllllllllllIlIlllIlllllIllI = interpolate(llllllllllllllllIlIlllIlllllIlIl.posZ, llllllllllllllllIlIlllIlllllIlIl.lastTickPosZ) - Wrapper.getMinecraft().renderManager.renderPosZ;
        return new double[] { llllllllllllllllIlIlllIllllllIII, llllllllllllllllIlIlllIlllllIlll, llllllllllllllllIlIlllIlllllIllI };
    }
    
    public static Vec3d div(final Vec3d llllllllllllllllIlIlllIllllIIIIl, final float llllllllllllllllIlIlllIllllIIIlI) {
        return new Vec3d(llllllllllllllllIlIlllIllllIIIIl.x / llllllllllllllllIlIlllIllllIIIlI, llllllllllllllllIlIlllIllllIIIIl.y / llllllllllllllllIlIlllIllllIIIlI, llllllllllllllllIlIlllIllllIIIIl.z / llllllllllllllllIlIlllIllllIIIlI);
    }
    
    public static ThrowingType getThrowType(final EntityLivingBase llllllllllllllllIlIllllIIIIIIIII) {
        if (llllllllllllllllIlIllllIIIIIIIII.getHeldItem(EnumHand.MAIN_HAND).isEmpty()) {
            return ThrowingType.NONE;
        }
        final ItemStack llllllllllllllllIlIllllIIIIIIIlI = llllllllllllllllIlIllllIIIIIIIII.getHeldItem(EnumHand.MAIN_HAND);
        final Item llllllllllllllllIlIllllIIIIIIIIl = llllllllllllllllIlIllllIIIIIIIlI.getItem();
        if (llllllllllllllllIlIllllIIIIIIIIl instanceof ItemPotion) {
            if (llllllllllllllllIlIllllIIIIIIIlI.getItem() instanceof ItemSplashPotion) {
                return ThrowingType.POTION;
            }
        }
        else {
            if (llllllllllllllllIlIllllIIIIIIIIl instanceof ItemBow && llllllllllllllllIlIllllIIIIIIIII.isHandActive()) {
                return ThrowingType.BOW;
            }
            if (llllllllllllllllIlIllllIIIIIIIIl instanceof ItemExpBottle) {
                return ThrowingType.EXPERIENCE;
            }
            if (llllllllllllllllIlIllllIIIIIIIIl instanceof ItemSnowball || llllllllllllllllIlIllllIIIIIIIIl instanceof ItemEgg || llllllllllllllllIlIllllIIIIIIIIl instanceof ItemEnderPearl) {
                return ThrowingType.NORMAL;
            }
        }
        return ThrowingType.NONE;
    }
    
    public static final class FlightPath
    {
        private /* synthetic */ Vec3d motion;
        private /* synthetic */ ThrowingType throwingType;
        private /* synthetic */ EntityLivingBase shooter;
        private /* synthetic */ float pitch;
        public /* synthetic */ Vec3d position;
        private /* synthetic */ float yaw;
        private /* synthetic */ boolean collided;
        private /* synthetic */ RayTraceResult target;
        private /* synthetic */ AxisAlignedBB boundingBox;
        
        public boolean isCollided() {
            return this.collided;
        }
        
        private void setThrowableHeading(final Vec3d lllIlIIlIllIlII, final float lllIlIIlIllIllI) {
            this.motion = TrajectoryCalculator.div(lllIlIIlIllIlII, (float)lllIlIIlIllIlII.length());
            this.motion = TrajectoryCalculator.mult(this.motion, lllIlIIlIllIllI);
        }
        
        private void setLocationAndAngles(final double lllIlIIllIIlIIl, final double lllIlIIllIIlllI, final double lllIlIIllIIIlll, final float lllIlIIllIIIllI, final float lllIlIIllIIlIll) {
            this.position = new Vec3d(lllIlIIllIIlIIl, lllIlIIllIIlllI, lllIlIIllIIIlll);
            this.yaw = lllIlIIllIIIllI;
            this.pitch = lllIlIIllIIlIll;
        }
        
        private float getGravityVelocity() {
            switch (this.throwingType) {
                case BOW:
                case POTION: {
                    return 0.05f;
                }
                case EXPERIENCE: {
                    return 0.07f;
                }
                case NORMAL: {
                    return 0.03f;
                }
                default: {
                    return 0.03f;
                }
            }
        }
        
        public FlightPath(final EntityLivingBase lllIlIlIIlIIlIl, final ThrowingType lllIlIlIIIlllIl) {
            this.shooter = lllIlIlIIlIIlIl;
            this.throwingType = lllIlIlIIIlllIl;
            final double[] lllIlIlIIlIIIIl = TrajectoryCalculator.interpolate((Entity)this.shooter);
            this.setLocationAndAngles(lllIlIlIIlIIIIl[0] + Wrapper.getMinecraft().getRenderManager().renderPosX, lllIlIlIIlIIIIl[1] + this.shooter.getEyeHeight() + Wrapper.getMinecraft().getRenderManager().renderPosY, lllIlIlIIlIIIIl[2] + Wrapper.getMinecraft().getRenderManager().renderPosZ, this.shooter.rotationYaw, this.shooter.rotationPitch);
            final Vec3d lllIlIlIIlIIIII = new Vec3d((double)(MathHelper.cos(this.yaw / 180.0f * 3.1415927f) * 0.16f), 0.1, (double)(MathHelper.sin(this.yaw / 180.0f * 3.1415927f) * 0.16f));
            this.position = this.position.subtract(lllIlIlIIlIIIII);
            this.setPosition(this.position);
            this.motion = new Vec3d((double)(-MathHelper.sin(this.yaw / 180.0f * 3.1415927f) * MathHelper.cos(this.pitch / 180.0f * 3.1415927f)), (double)(-MathHelper.sin(this.pitch / 180.0f * 3.1415927f)), (double)(MathHelper.cos(this.yaw / 180.0f * 3.1415927f) * MathHelper.cos(this.pitch / 180.0f * 3.1415927f)));
            this.setThrowableHeading(this.motion, this.getInitialVelocity());
        }
        
        private void setPosition(final Vec3d lllIlIIllIIIIII) {
            this.position = new Vec3d(lllIlIIllIIIIII.x, lllIlIIllIIIIII.y, lllIlIIllIIIIII.z);
            final double lllIlIIlIllllll = ((this.throwingType == ThrowingType.BOW) ? 0.5 : 0.25) / 2.0;
            this.boundingBox = new AxisAlignedBB(lllIlIIllIIIIII.x - lllIlIIlIllllll, lllIlIIllIIIIII.y - lllIlIIlIllllll, lllIlIIllIIIIII.z - lllIlIIlIllllll, lllIlIIllIIIIII.x + lllIlIIlIllllll, lllIlIIllIIIIII.y + lllIlIIlIllllll, lllIlIIllIIIIII.z + lllIlIIlIllllll);
        }
        
        public void onUpdate() {
            Vec3d lllIlIlIIIlIIlI = this.position.add(this.motion);
            final RayTraceResult lllIlIlIIIlIIIl = this.shooter.getEntityWorld().rayTraceBlocks(this.position, lllIlIlIIIlIIlI, false, true, false);
            if (lllIlIlIIIlIIIl != null) {
                lllIlIlIIIlIIlI = lllIlIlIIIlIIIl.hitVec;
            }
            this.onCollideWithEntity(lllIlIlIIIlIIlI, lllIlIlIIIlIIIl);
            if (this.target != null) {
                this.collided = true;
                this.setPosition(this.target.hitVec);
                return;
            }
            if (this.position.y <= 0.0) {
                this.collided = true;
                return;
            }
            this.position = this.position.add(this.motion);
            float lllIlIlIIIlIIII = 0.99f;
            if (this.shooter.getEntityWorld().isMaterialInBB(this.boundingBox, Material.WATER)) {
                lllIlIlIIIlIIII = ((this.throwingType == ThrowingType.BOW) ? 0.6f : 0.8f);
            }
            this.motion = TrajectoryCalculator.mult(this.motion, lllIlIlIIIlIIII);
            this.motion = this.motion.subtract(0.0, (double)this.getGravityVelocity(), 0.0);
            this.setPosition(this.position);
        }
        
        public RayTraceResult getCollidingTarget() {
            return this.target;
        }
        
        private void onCollideWithEntity(final Vec3d lllIlIIllllIIll, final RayTraceResult lllIlIIllllIIlI) {
            Entity lllIlIIllllIlll = null;
            double lllIlIIllllIllI = 0.0;
            final List<Entity> lllIlIIllllIlIl = (List<Entity>)this.shooter.world.getEntitiesWithinAABBExcludingEntity((Entity)this.shooter, this.boundingBox.expand(this.motion.x, this.motion.y, this.motion.z).expand(1.0, 1.0, 1.0));
            for (final Entity lllIlIIlllllIll : lllIlIIllllIlIl) {
                if (!lllIlIIlllllIll.canBeCollidedWith() && lllIlIIlllllIll != this.shooter) {
                    continue;
                }
                final float lllIlIIlllllllI = lllIlIIlllllIll.getCollisionBorderSize();
                final AxisAlignedBB lllIlIIllllllIl = lllIlIIlllllIll.getEntityBoundingBox().expand((double)lllIlIIlllllllI, (double)lllIlIIlllllllI, (double)lllIlIIlllllllI);
                final RayTraceResult lllIlIIllllllII = lllIlIIllllllIl.calculateIntercept(this.position, lllIlIIllllIIll);
                if (lllIlIIllllllII == null) {
                    continue;
                }
                final double lllIlIIllllllll = this.position.distanceTo(lllIlIIllllllII.hitVec);
                if (lllIlIIllllllll >= lllIlIIllllIllI && lllIlIIllllIllI != 0.0) {
                    continue;
                }
                lllIlIIllllIlll = lllIlIIlllllIll;
                lllIlIIllllIllI = lllIlIIllllllll;
            }
            if (lllIlIIllllIlll != null) {
                this.target = new RayTraceResult(lllIlIIllllIlll);
            }
            else {
                this.target = lllIlIIllllIIlI;
            }
        }
        
        private float getInitialVelocity() {
            final Item lllIlIIllIlllll = this.shooter.getHeldItem(EnumHand.MAIN_HAND).getItem();
            switch (this.throwingType) {
                case BOW: {
                    final ItemBow lllIlIIlllIIIll = (ItemBow)lllIlIIllIlllll;
                    final int lllIlIIlllIIIlI = lllIlIIlllIIIll.getMaxItemUseDuration(this.shooter.getHeldItem(EnumHand.MAIN_HAND)) - this.shooter.getItemInUseCount();
                    float lllIlIIlllIIIIl = lllIlIIlllIIIlI / 20.0f;
                    lllIlIIlllIIIIl = (lllIlIIlllIIIIl * lllIlIIlllIIIIl + lllIlIIlllIIIIl * 2.0f) / 3.0f;
                    if (lllIlIIlllIIIIl > 1.0f) {
                        lllIlIIlllIIIIl = 1.0f;
                    }
                    return lllIlIIlllIIIIl * 2.0f * 1.5f;
                }
                case POTION: {
                    return 0.5f;
                }
                case EXPERIENCE: {
                    return 0.7f;
                }
                case NORMAL: {
                    return 1.5f;
                }
                default: {
                    return 1.5f;
                }
            }
        }
    }
    
    public enum ThrowingType
    {
        NORMAL, 
        EXPERIENCE, 
        POTION, 
        BOW, 
        NONE;
    }
}
