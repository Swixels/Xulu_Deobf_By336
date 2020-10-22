package com.elementars.eclient.util;

import net.minecraft.util.math.*;

public class AngleHelper
{
    public static Angle getAngleFacingInRadians(final Vec3d llllllllllllllllllllIIIIlllIlIll) {
        double llllllllllllllllllllIIIIlllIlIIl = 0.0;
        double llllllllllllllllllllIIIIlllIlIlI = 0.0;
        if (llllllllllllllllllllIIIIlllIlIll.x == 0.0 && llllllllllllllllllllIIIIlllIlIll.z == 0.0) {
            final double llllllllllllllllllllIIIIlllIllIl = 0.0;
            final double llllllllllllllllllllIIIIlllIlllI = 1.5707963267948966;
        }
        else {
            llllllllllllllllllllIIIIlllIlIIl = Math.atan2(llllllllllllllllllllIIIIlllIlIll.z, llllllllllllllllllllIIIIlllIlIll.x) - 1.5707963267948966;
            final double llllllllllllllllllllIIIIlllIllII = Math.sqrt(llllllllllllllllllllIIIIlllIlIll.x * llllllllllllllllllllIIIIlllIlIll.x + llllllllllllllllllllIIIIlllIlIll.z * llllllllllllllllllllIIIIlllIlIll.z);
            llllllllllllllllllllIIIIlllIlIlI = -Math.atan2(llllllllllllllllllllIIIIlllIlIll.y, llllllllllllllllllllIIIIlllIllII);
        }
        return Angle.radians((float)llllllllllllllllllllIIIIlllIlIlI, (float)llllllllllllllllllllIIIIlllIlIIl);
    }
    
    public static double roundAngle(final double llllllllllllllllllllIIIlIIIllIll) {
        return roundAngle(llllllllllllllllllllIIIlIIIllIll, 1000000000L);
    }
    
    public static float normalizeInDegrees(final float llllllllllllllllllllIIIIllllIlII) {
        return MathHelper.wrapDegrees(llllllllllllllllllllIIIIllllIlII);
    }
    
    public static double normalizeInRadians(double llllllllllllllllllllIIIIllllllII) {
        while (llllllllllllllllllllIIIIllllllII > 3.141592653589793) {
            llllllllllllllllllllIIIIllllllII -= 6.283185307179586;
        }
        while (llllllllllllllllllllIIIIllllllII < -3.141592653589793) {
            llllllllllllllllllllIIIIllllllII += 6.283185307179586;
        }
        return llllllllllllllllllllIIIIllllllII;
    }
    
    public static boolean isAngleEqual(final double llllllllllllllllllllIIIlIIIIllII, final double llllllllllllllllllllIIIlIIIIlIll) {
        return isAngleEqual(llllllllllllllllllllIIIlIIIIllII, llllllllllllllllllllIIIlIIIIlIll, 1.0E-4);
    }
    
    public static float normalizeInRadians(float llllllllllllllllllllIIIIlllllIIl) {
        while (llllllllllllllllllllIIIIlllllIIl > 3.141592653589793) {
            llllllllllllllllllllIIIIlllllIIl -= (float)6.283185307179586;
        }
        while (llllllllllllllllllllIIIIlllllIIl < -3.141592653589793) {
            llllllllllllllllllllIIIIlllllIIl += (float)6.283185307179586;
        }
        return llllllllllllllllllllIIIIlllllIIl;
    }
    
    public static Angle getAngleFacingInDegrees(final Vec3d llllllllllllllllllllIIIIlllIIIlI) {
        return getAngleFacingInRadians(llllllllllllllllllllIIIIlllIIIlI).inDegrees();
    }
    
    public static boolean isEqual(final Angle llllllllllllllllllllIIIlIIIIIllI, final Angle llllllllllllllllllllIIIlIIIIIIIl) {
        final Angle llllllllllllllllllllIIIlIIIIIlII = llllllllllllllllllllIIIlIIIIIllI.normalize();
        final Angle llllllllllllllllllllIIIlIIIIIIll = llllllllllllllllllllIIIlIIIIIIIl.same(llllllllllllllllllllIIIlIIIIIlII).normalize();
        return isAngleEqual(llllllllllllllllllllIIIlIIIIIlII.getPitch(), llllllllllllllllllllIIIlIIIIIIll.getPitch()) && isAngleEqual(llllllllllllllllllllIIIlIIIIIlII.getYaw(), llllllllllllllllllllIIIlIIIIIIll.getYaw()) && isAngleEqual(llllllllllllllllllllIIIlIIIIIlII.getRoll(), llllllllllllllllllllIIIlIIIIIIll.getRoll());
    }
    
    public static boolean isAngleEqual(final double llllllllllllllllllllIIIlIIIlIllI, final double llllllllllllllllllllIIIlIIIlIIlI, final double llllllllllllllllllllIIIlIIIlIlII) {
        return Double.compare(llllllllllllllllllllIIIlIIIlIllI, llllllllllllllllllllIIIlIIIlIIlI) == 0 || Math.abs(llllllllllllllllllllIIIlIIIlIllI - llllllllllllllllllllIIIlIIIlIIlI) < llllllllllllllllllllIIIlIIIlIlII;
    }
    
    public static double roundAngle(final double llllllllllllllllllllIIIlIIlIIIII, final long llllllllllllllllllllIIIlIIIlllIl) {
        return Math.round(llllllllllllllllllllIIIlIIlIIIII * llllllllllllllllllllIIIlIIIlllIl) / (double)llllllllllllllllllllIIIlIIIlllIl;
    }
    
    public static double normalizeInDegrees(final double llllllllllllllllllllIIIIllllIllI) {
        return MathHelper.wrapDegrees(llllllllllllllllllllIIIIllllIllI);
    }
}
