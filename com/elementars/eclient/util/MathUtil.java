package com.elementars.eclient.util;

import net.minecraft.client.*;
import net.minecraft.util.math.*;
import java.math.*;
import net.minecraft.entity.*;

public final class MathUtil
{
    public static Vec3d direction(final float lllllllllllllllllllIIlIIlIlIllII) {
        return new Vec3d(Math.cos(degToRad(lllllllllllllllllllIIlIIlIlIllII + 90.0f)), 0.0, Math.sin(degToRad(lllllllllllllllllllIIlIIlIlIllII + 90.0f)));
    }
    
    public static double radToDeg(final double lllllllllllllllllllIIlIIlIllIIIl) {
        return lllllllllllllllllllIIlIIlIllIIIl * 57.295780181884766;
    }
    
    public static double degToRad(final double lllllllllllllllllllIIlIIlIlIllll) {
        return lllllllllllllllllllIIlIIlIlIllll * 0.01745329238474369;
    }
    
    public static Vec3d div(final Vec3d lllllllllllllllllllIIlIIIllIllIl, final Vec3d lllllllllllllllllllIIlIIIllIllII) {
        return new Vec3d(lllllllllllllllllllIIlIIIllIllIl.x / lllllllllllllllllllIIlIIIllIllII.x, lllllllllllllllllllIIlIIIllIllIl.y / lllllllllllllllllllIIlIIIllIllII.y, lllllllllllllllllllIIlIIIllIllIl.z / lllllllllllllllllllIIlIIIllIllII.z);
    }
    
    public static double[] directionSpeed(final double lllllllllllllllllllIIlIIlIIIllll) {
        final Minecraft lllllllllllllllllllIIlIIlIIIlllI = Minecraft.getMinecraft();
        float lllllllllllllllllllIIlIIlIIIllIl = lllllllllllllllllllIIlIIlIIIlllI.player.movementInput.moveForward;
        float lllllllllllllllllllIIlIIlIIIllII = lllllllllllllllllllIIlIIlIIIlllI.player.movementInput.moveStrafe;
        float lllllllllllllllllllIIlIIlIIIlIll = lllllllllllllllllllIIlIIlIIIlllI.player.prevRotationYaw + (lllllllllllllllllllIIlIIlIIIlllI.player.rotationYaw - lllllllllllllllllllIIlIIlIIIlllI.player.prevRotationYaw) * lllllllllllllllllllIIlIIlIIIlllI.getRenderPartialTicks();
        if (lllllllllllllllllllIIlIIlIIIllIl != 0.0f) {
            if (lllllllllllllllllllIIlIIlIIIllII > 0.0f) {
                lllllllllllllllllllIIlIIlIIIlIll += ((lllllllllllllllllllIIlIIlIIIllIl > 0.0f) ? -45 : 45);
            }
            else if (lllllllllllllllllllIIlIIlIIIllII < 0.0f) {
                lllllllllllllllllllIIlIIlIIIlIll += ((lllllllllllllllllllIIlIIlIIIllIl > 0.0f) ? 45 : -45);
            }
            lllllllllllllllllllIIlIIlIIIllII = 0.0f;
            if (lllllllllllllllllllIIlIIlIIIllIl > 0.0f) {
                lllllllllllllllllllIIlIIlIIIllIl = 1.0f;
            }
            else if (lllllllllllllllllllIIlIIlIIIllIl < 0.0f) {
                lllllllllllllllllllIIlIIlIIIllIl = -1.0f;
            }
        }
        final double lllllllllllllllllllIIlIIlIIIlIlI = Math.sin(Math.toRadians(lllllllllllllllllllIIlIIlIIIlIll + 90.0f));
        final double lllllllllllllllllllIIlIIlIIIlIIl = Math.cos(Math.toRadians(lllllllllllllllllllIIlIIlIIIlIll + 90.0f));
        final double lllllllllllllllllllIIlIIlIIIlIII = lllllllllllllllllllIIlIIlIIIllIl * lllllllllllllllllllIIlIIlIIIllll * lllllllllllllllllllIIlIIlIIIlIIl + lllllllllllllllllllIIlIIlIIIllII * lllllllllllllllllllIIlIIlIIIllll * lllllllllllllllllllIIlIIlIIIlIlI;
        final double lllllllllllllllllllIIlIIlIIIIlll = lllllllllllllllllllIIlIIlIIIllIl * lllllllllllllllllllIIlIIlIIIllll * lllllllllllllllllllIIlIIlIIIlIlI - lllllllllllllllllllIIlIIlIIIllII * lllllllllllllllllllIIlIIlIIIllll * lllllllllllllllllllIIlIIlIIIlIIl;
        return new double[] { lllllllllllllllllllIIlIIlIIIlIII, lllllllllllllllllllIIlIIlIIIIlll };
    }
    
    public static float wrap(float lllllllllllllllllllIIlIIIlIIlIII) {
        lllllllllllllllllllIIlIIIlIIlIII %= (360.0f != 0.0f);
        if ((lllllllllllllllllllIIlIIIlIIlIII ? 1 : 0) >= 180.0f) {
            lllllllllllllllllllIIlIIIlIIlIII -= (360.0f != 0.0f);
        }
        if ((lllllllllllllllllllIIlIIIlIIlIII ? 1 : 0) < -180.0f) {
            lllllllllllllllllllIIlIIIlIIlIII += (360.0f != 0.0f);
        }
        return lllllllllllllllllllIIlIIIlIIlIII ? 1 : 0;
    }
    
    public static double getDistance(final Vec3d lllllllllllllllllllIIlIIIIIllIII, final double lllllllllllllllllllIIlIIIIIlIlll, final double lllllllllllllllllllIIlIIIIIlIllI, final double lllllllllllllllllllIIlIIIIIlIlIl) {
        final double lllllllllllllllllllIIlIIIIIllIll = lllllllllllllllllllIIlIIIIIllIII.x - lllllllllllllllllllIIlIIIIIlIlll;
        final double lllllllllllllllllllIIlIIIIIllIlI = lllllllllllllllllllIIlIIIIIllIII.y - lllllllllllllllllllIIlIIIIIlIllI;
        final double lllllllllllllllllllIIlIIIIIllIIl = lllllllllllllllllllIIlIIIIIllIII.z - lllllllllllllllllllIIlIIIIIlIlIl;
        return MathHelper.sqrt(lllllllllllllllllllIIlIIIIIllIll * lllllllllllllllllllIIlIIIIIllIll + lllllllllllllllllllIIlIIIIIllIlI * lllllllllllllllllllIIlIIIIIllIlI + lllllllllllllllllllIIlIIIIIllIIl * lllllllllllllllllllIIlIIIIIllIIl);
    }
    
    public static Vec3d mult(final Vec3d lllllllllllllllllllIIlIIIlllIIll, final float lllllllllllllllllllIIlIIIlllIIlI) {
        return new Vec3d(lllllllllllllllllllIIlIIIlllIIll.x * lllllllllllllllllllIIlIIIlllIIlI, lllllllllllllllllllIIlIIIlllIIll.y * lllllllllllllllllllIIlIIIlllIIlI, lllllllllllllllllllIIlIIIlllIIll.z * lllllllllllllllllllIIlIIIlllIIlI);
    }
    
    public static Vec3d mult(final Vec3d lllllllllllllllllllIIlIIIllllIIl, final Vec3d lllllllllllllllllllIIlIIIllllIlI) {
        return new Vec3d(lllllllllllllllllllIIlIIIllllIIl.x * lllllllllllllllllllIIlIIIllllIlI.x, lllllllllllllllllllIIlIIIllllIIl.y * lllllllllllllllllllIIlIIIllllIlI.y, lllllllllllllllllllIIlIIIllllIIl.z * lllllllllllllllllllIIlIIIllllIlI.z);
    }
    
    public static double map(double lllllllllllllllllllIIlIIIIllllIl, final double lllllllllllllllllllIIlIIIIllllII, final double lllllllllllllllllllIIlIIIlIIIIII, final double lllllllllllllllllllIIlIIIIllllll, final double lllllllllllllllllllIIlIIIIlllllI) {
        lllllllllllllllllllIIlIIIIllllIl = (lllllllllllllllllllIIlIIIIllllIl - lllllllllllllllllllIIlIIIIllllII) / (lllllllllllllllllllIIlIIIlIIIIII - lllllllllllllllllllIIlIIIIllllII);
        return lllllllllllllllllllIIlIIIIllllll + lllllllllllllllllllIIlIIIIllllIl * (lllllllllllllllllllIIlIIIIlllllI - lllllllllllllllllllIIlIIIIllllll);
    }
    
    public static int ensureRange(final int lllllllllllllllllllIIlIIIlIlIIlI, final int lllllllllllllllllllIIlIIIlIIllIl, final int lllllllllllllllllllIIlIIIlIIllII) {
        final int lllllllllllllllllllIIlIIIlIIllll = Math.min(Math.max(lllllllllllllllllllIIlIIIlIlIIlI, lllllllllllllllllllIIlIIIlIIllIl), lllllllllllllllllllIIlIIIlIIllII);
        return lllllllllllllllllllIIlIIIlIIllll;
    }
    
    public static Vec3d div(final Vec3d lllllllllllllllllllIIlIIIllIIlll, final float lllllllllllllllllllIIlIIIllIlIII) {
        return new Vec3d(lllllllllllllllllllIIlIIIllIIlll.x / lllllllllllllllllllIIlIIIllIlIII, lllllllllllllllllllIIlIIIllIIlll.y / lllllllllllllllllllIIlIIIllIlIII, lllllllllllllllllllIIlIIIllIIlll.z / lllllllllllllllllllIIlIIIllIlIII);
    }
    
    public static double round(final double lllllllllllllllllllIIlIIIllIIIIl, final int lllllllllllllllllllIIlIIIllIIIII) {
        if (lllllllllllllllllllIIlIIIllIIIII < 0) {
            return lllllllllllllllllllIIlIIIllIIIIl;
        }
        return new BigDecimal(lllllllllllllllllllIIlIIIllIIIIl).setScale(lllllllllllllllllllIIlIIIllIIIII, RoundingMode.HALF_UP).doubleValue();
    }
    
    public static Vec3d interpolateEntity(final Entity lllllllllllllllllllIIlIIlIllIlIl, final float lllllllllllllllllllIIlIIlIllIllI) {
        return new Vec3d(lllllllllllllllllllIIlIIlIllIlIl.lastTickPosX + (lllllllllllllllllllIIlIIlIllIlIl.posX - lllllllllllllllllllIIlIIlIllIlIl.lastTickPosX) * lllllllllllllllllllIIlIIlIllIllI, lllllllllllllllllllIIlIIlIllIlIl.lastTickPosY + (lllllllllllllllllllIIlIIlIllIlIl.posY - lllllllllllllllllllIIlIIlIllIlIl.lastTickPosY) * lllllllllllllllllllIIlIIlIllIllI, lllllllllllllllllllIIlIIlIllIlIl.lastTickPosZ + (lllllllllllllllllllIIlIIlIllIlIl.posZ - lllllllllllllllllllIIlIIlIllIlIl.lastTickPosZ) * lllllllllllllllllllIIlIIlIllIllI);
    }
    
    public static double linear(final double lllllllllllllllllllIIlIIIIllIIlI, final double lllllllllllllllllllIIlIIIIllIlII, final double lllllllllllllllllllIIlIIIIllIIll) {
        return (lllllllllllllllllllIIlIIIIllIIlI < lllllllllllllllllllIIlIIIIllIlII - lllllllllllllllllllIIlIIIIllIIll) ? (lllllllllllllllllllIIlIIIIllIIlI + lllllllllllllllllllIIlIIIIllIIll) : ((lllllllllllllllllllIIlIIIIllIIlI > lllllllllllllllllllIIlIIIIllIlII + lllllllllllllllllllIIlIIIIllIIll) ? (lllllllllllllllllllIIlIIIIllIIlI - lllllllllllllllllllIIlIIIIllIIll) : lllllllllllllllllllIIlIIIIllIlII);
    }
    
    public static float clamp(float lllllllllllllllllllIIlIIIlIllIIl, final float lllllllllllllllllllIIlIIIlIllIII, final float lllllllllllllllllllIIlIIIlIlIlll) {
        if (lllllllllllllllllllIIlIIIlIllIIl <= lllllllllllllllllllIIlIIIlIllIII) {
            lllllllllllllllllllIIlIIIlIllIIl = lllllllllllllllllllIIlIIIlIllIII;
        }
        if (lllllllllllllllllllIIlIIIlIllIIl >= lllllllllllllllllllIIlIIIlIlIlll) {
            lllllllllllllllllllIIlIIIlIllIIl = lllllllllllllllllllIIlIIIlIlIlll;
        }
        return lllllllllllllllllllIIlIIIlIllIIl;
    }
    
    public static float[] calcAngle(final Vec3d lllllllllllllllllllIIlIIlIlIIlII, final Vec3d lllllllllllllllllllIIlIIlIlIIIll) {
        final double lllllllllllllllllllIIlIIlIlIIIlI = lllllllllllllllllllIIlIIlIlIIIll.x - lllllllllllllllllllIIlIIlIlIIlII.x;
        final double lllllllllllllllllllIIlIIlIlIIIIl = (lllllllllllllllllllIIlIIlIlIIIll.y - lllllllllllllllllllIIlIIlIlIIlII.y) * -1.0;
        final double lllllllllllllllllllIIlIIlIlIIIII = lllllllllllllllllllIIlIIlIlIIIll.z - lllllllllllllllllllIIlIIlIlIIlII.z;
        final double lllllllllllllllllllIIlIIlIIlllll = MathHelper.sqrt(lllllllllllllllllllIIlIIlIlIIIlI * lllllllllllllllllllIIlIIlIlIIIlI + lllllllllllllllllllIIlIIlIlIIIII * lllllllllllllllllllIIlIIlIlIIIII);
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(lllllllllllllllllllIIlIIlIlIIIII, lllllllllllllllllllIIlIIlIlIIIlI)) - 90.0), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(lllllllllllllllllllIIlIIlIlIIIIl, lllllllllllllllllllIIlIIlIIlllll))) };
    }
    
    public static double parabolic(final double lllllllllllllllllllIIlIIIIlIlIIl, final double lllllllllllllllllllIIlIIIIlIlIII, final double lllllllllllllllllllIIlIIIIlIIlll) {
        return lllllllllllllllllllIIlIIIIlIlIIl + (lllllllllllllllllllIIlIIIIlIlIII - lllllllllllllllllllIIlIIIIlIlIIl) / lllllllllllllllllllIIlIIIIlIIlll;
    }
}
