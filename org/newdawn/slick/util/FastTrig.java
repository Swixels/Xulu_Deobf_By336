package org.newdawn.slick.util;

public class FastTrig
{
    private static double reduceSinAngle(double lIlIlIIIIlIIl) {
        final double lIlIlIIIIlIII = lIlIlIIIIlIIl;
        lIlIlIIIIlIIl %= 6.283185307179586;
        if (Math.abs(lIlIlIIIIlIIl) > 3.141592653589793) {
            lIlIlIIIIlIIl -= 6.283185307179586;
        }
        if (Math.abs(lIlIlIIIIlIIl) > 1.5707963267948966) {
            lIlIlIIIIlIIl = 3.141592653589793 - lIlIlIIIIlIIl;
        }
        return lIlIlIIIIlIIl;
    }
    
    public static double cos(final double lIlIlIIIIIIIl) {
        return sin(lIlIlIIIIIIIl + 1.5707963267948966);
    }
    
    public static double sin(double lIlIlIIIIIIll) {
        lIlIlIIIIIIll = reduceSinAngle(lIlIlIIIIIIll);
        if (Math.abs(lIlIlIIIIIIll) <= 0.7853981633974483) {
            return Math.sin(lIlIlIIIIIIll);
        }
        return Math.cos(1.5707963267948966 - lIlIlIIIIIIll);
    }
}
