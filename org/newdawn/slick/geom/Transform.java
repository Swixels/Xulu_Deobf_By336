package org.newdawn.slick.geom;

import org.newdawn.slick.util.*;

public class Transform
{
    private /* synthetic */ float[] matrixPosition;
    
    @Override
    public String toString() {
        final String lllllllllllllllllllllIllIllllIlI = String.valueOf(new StringBuilder().append("Transform[[").append(this.matrixPosition[0]).append(",").append(this.matrixPosition[1]).append(",").append(this.matrixPosition[2]).append("][").append(this.matrixPosition[3]).append(",").append(this.matrixPosition[4]).append(",").append(this.matrixPosition[5]).append("][").append(this.matrixPosition[6]).append(",").append(this.matrixPosition[7]).append(",").append(this.matrixPosition[8]).append("]]"));
        return lllllllllllllllllllllIllIllllIlI.toString();
    }
    
    public static Transform createRotateTransform(final float lllllllllllllllllllllIllIlllIIlI) {
        return new Transform((float)FastTrig.cos(lllllllllllllllllllllIllIlllIIlI), -(float)FastTrig.sin(lllllllllllllllllllllIllIlllIIlI), 0.0f, (float)FastTrig.sin(lllllllllllllllllllllIllIlllIIlI), (float)FastTrig.cos(lllllllllllllllllllllIllIlllIIlI), 0.0f);
    }
    
    public Transform() {
        this.matrixPosition = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f };
    }
    
    public void transform(final float[] lllllllllllllllllllllIlllIlIIlll, final int lllllllllllllllllllllIlllIlIIllI, final float[] lllllllllllllllllllllIlllIlIIlIl, final int lllllllllllllllllllllIlllIIlllIl, final int lllllllllllllllllllllIlllIIlllII) {
        final float[] lllllllllllllllllllllIlllIlIIIlI = (lllllllllllllllllllllIlllIlIIlll == lllllllllllllllllllllIlllIlIIlIl) ? new float[lllllllllllllllllllllIlllIIlllII * 2] : lllllllllllllllllllllIlllIlIIlIl;
        for (int lllllllllllllllllllllIlllIlIlIlI = 0; lllllllllllllllllllllIlllIlIlIlI < lllllllllllllllllllllIlllIIlllII * 2; lllllllllllllllllllllIlllIlIlIlI += 2) {
            for (int lllllllllllllllllllllIlllIlIlIll = 0; lllllllllllllllllllllIlllIlIlIll < 6; lllllllllllllllllllllIlllIlIlIll += 3) {
                lllllllllllllllllllllIlllIlIIIlI[lllllllllllllllllllllIlllIlIlIlI + lllllllllllllllllllllIlllIlIlIll / 3] = lllllllllllllllllllllIlllIlIIlll[lllllllllllllllllllllIlllIlIlIlI + lllllllllllllllllllllIlllIlIIllI] * this.matrixPosition[lllllllllllllllllllllIlllIlIlIll] + lllllllllllllllllllllIlllIlIIlll[lllllllllllllllllllllIlllIlIlIlI + lllllllllllllllllllllIlllIlIIllI + 1] * this.matrixPosition[lllllllllllllllllllllIlllIlIlIll + 1] + 1.0f * this.matrixPosition[lllllllllllllllllllllIlllIlIlIll + 2];
            }
        }
        if (lllllllllllllllllllllIlllIlIIlll == lllllllllllllllllllllIlllIlIIlIl) {
            for (int lllllllllllllllllllllIlllIlIlIIl = 0; lllllllllllllllllllllIlllIlIlIIl < lllllllllllllllllllllIlllIIlllII * 2; lllllllllllllllllllllIlllIlIlIIl += 2) {
                lllllllllllllllllllllIlllIlIIlIl[lllllllllllllllllllllIlllIlIlIIl + lllllllllllllllllllllIlllIIlllIl] = lllllllllllllllllllllIlllIlIIIlI[lllllllllllllllllllllIlllIlIlIIl];
                lllllllllllllllllllllIlllIlIIlIl[lllllllllllllllllllllIlllIlIlIIl + lllllllllllllllllllllIlllIIlllIl + 1] = lllllllllllllllllllllIlllIlIIIlI[lllllllllllllllllllllIlllIlIlIIl + 1];
            }
        }
    }
    
    public static Transform createTranslateTransform(final float lllllllllllllllllllllIllIlIlllIl, final float lllllllllllllllllllllIllIlIllIlI) {
        return new Transform(1.0f, 0.0f, lllllllllllllllllllllIllIlIlllIl, 0.0f, 1.0f, lllllllllllllllllllllIllIlIllIlI);
    }
    
    public float[] getMatrixPosition() {
        return this.matrixPosition;
    }
    
    public static Transform createScaleTransform(final float lllllllllllllllllllllIllIlIlIlIl, final float lllllllllllllllllllllIllIlIlIllI) {
        return new Transform(lllllllllllllllllllllIllIlIlIlIl, 0.0f, 0.0f, 0.0f, lllllllllllllllllllllIllIlIlIllI, 0.0f);
    }
    
    public Transform(final Transform lllllllllllllllllllllIllllIlllII) {
        this.matrixPosition = new float[9];
        for (int lllllllllllllllllllllIllllIllllI = 0; lllllllllllllllllllllIllllIllllI < 9; ++lllllllllllllllllllllIllllIllllI) {
            this.matrixPosition[lllllllllllllllllllllIllllIllllI] = lllllllllllllllllllllIllllIlllII.matrixPosition[lllllllllllllllllllllIllllIllllI];
        }
    }
    
    public static Transform createRotateTransform(final float lllllllllllllllllllllIllIllIlIll, final float lllllllllllllllllllllIllIllIlIlI, final float lllllllllllllllllllllIllIllIIIll) {
        final Transform lllllllllllllllllllllIllIllIlIII = createRotateTransform(lllllllllllllllllllllIllIllIlIll);
        final float lllllllllllllllllllllIllIllIIlll = lllllllllllllllllllllIllIllIlIII.matrixPosition[3];
        final float lllllllllllllllllllllIllIllIIllI = 1.0f - lllllllllllllllllllllIllIllIlIII.matrixPosition[4];
        lllllllllllllllllllllIllIllIlIII.matrixPosition[2] = lllllllllllllllllllllIllIllIlIlI * lllllllllllllllllllllIllIllIIllI + lllllllllllllllllllllIllIllIIIll * lllllllllllllllllllllIllIllIIlll;
        lllllllllllllllllllllIllIllIlIII.matrixPosition[5] = lllllllllllllllllllllIllIllIIIll * lllllllllllllllllllllIllIllIIllI - lllllllllllllllllllllIllIllIlIlI * lllllllllllllllllllllIllIllIIlll;
        return lllllllllllllllllllllIllIllIlIII;
    }
    
    public Transform concatenate(final Transform lllllllllllllllllllllIlllIIIIlIl) {
        final float[] lllllllllllllllllllllIlllIIIllIl = new float[9];
        final float lllllllllllllllllllllIlllIIIllII = this.matrixPosition[0] * lllllllllllllllllllllIlllIIIIlIl.matrixPosition[0] + this.matrixPosition[1] * lllllllllllllllllllllIlllIIIIlIl.matrixPosition[3];
        final float lllllllllllllllllllllIlllIIIlIll = this.matrixPosition[0] * lllllllllllllllllllllIlllIIIIlIl.matrixPosition[1] + this.matrixPosition[1] * lllllllllllllllllllllIlllIIIIlIl.matrixPosition[4];
        final float lllllllllllllllllllllIlllIIIlIlI = this.matrixPosition[0] * lllllllllllllllllllllIlllIIIIlIl.matrixPosition[2] + this.matrixPosition[1] * lllllllllllllllllllllIlllIIIIlIl.matrixPosition[5] + this.matrixPosition[2];
        final float lllllllllllllllllllllIlllIIIlIIl = this.matrixPosition[3] * lllllllllllllllllllllIlllIIIIlIl.matrixPosition[0] + this.matrixPosition[4] * lllllllllllllllllllllIlllIIIIlIl.matrixPosition[3];
        final float lllllllllllllllllllllIlllIIIlIII = this.matrixPosition[3] * lllllllllllllllllllllIlllIIIIlIl.matrixPosition[1] + this.matrixPosition[4] * lllllllllllllllllllllIlllIIIIlIl.matrixPosition[4];
        final float lllllllllllllllllllllIlllIIIIlll = this.matrixPosition[3] * lllllllllllllllllllllIlllIIIIlIl.matrixPosition[2] + this.matrixPosition[4] * lllllllllllllllllllllIlllIIIIlIl.matrixPosition[5] + this.matrixPosition[5];
        lllllllllllllllllllllIlllIIIllIl[0] = lllllllllllllllllllllIlllIIIllII;
        lllllllllllllllllllllIlllIIIllIl[1] = lllllllllllllllllllllIlllIIIlIll;
        lllllllllllllllllllllIlllIIIllIl[2] = lllllllllllllllllllllIlllIIIlIlI;
        lllllllllllllllllllllIlllIIIllIl[3] = lllllllllllllllllllllIlllIIIlIIl;
        lllllllllllllllllllllIlllIIIllIl[4] = lllllllllllllllllllllIlllIIIlIII;
        lllllllllllllllllllllIlllIIIllIl[5] = lllllllllllllllllllllIlllIIIIlll;
        this.matrixPosition = lllllllllllllllllllllIlllIIIllIl;
        return this;
    }
    
    public Vector2f transform(final Vector2f lllllllllllllllllllllIllIlIIlIlI) {
        final float[] lllllllllllllllllllllIllIlIIllIl = { lllllllllllllllllllllIllIlIIlIlI.x, lllllllllllllllllllllIllIlIIlIlI.y };
        final float[] lllllllllllllllllllllIllIlIIllII = new float[2];
        this.transform(lllllllllllllllllllllIllIlIIllIl, 0, lllllllllllllllllllllIllIlIIllII, 0, 1);
        return new Vector2f(lllllllllllllllllllllIllIlIIllII[0], lllllllllllllllllllllIllIlIIllII[1]);
    }
    
    public Transform(final float[] lllllllllllllllllllllIllllIIllII) {
        if (lllllllllllllllllllllIllllIIllII.length != 6) {
            throw new RuntimeException("The parameter must be a float array of length 6.");
        }
        this.matrixPosition = new float[] { lllllllllllllllllllllIllllIIllII[0], lllllllllllllllllllllIllllIIllII[1], lllllllllllllllllllllIllllIIllII[2], lllllllllllllllllllllIllllIIllII[3], lllllllllllllllllllllIllllIIllII[4], lllllllllllllllllllllIllllIIllII[5], 0.0f, 0.0f, 1.0f };
    }
    
    public Transform(final float lllllllllllllllllllllIlllIlllIlI, final float lllllllllllllllllllllIllllIIIIII, final float lllllllllllllllllllllIlllIlllIII, final float lllllllllllllllllllllIlllIlllllI, final float lllllllllllllllllllllIlllIllllIl, final float lllllllllllllllllllllIlllIllllII) {
        this.matrixPosition = new float[] { lllllllllllllllllllllIlllIlllIlI, lllllllllllllllllllllIllllIIIIII, lllllllllllllllllllllIlllIlllIII, lllllllllllllllllllllIlllIlllllI, lllllllllllllllllllllIlllIllllIl, lllllllllllllllllllllIlllIllllII, 0.0f, 0.0f, 1.0f };
    }
    
    public Transform(final Transform lllllllllllllllllllllIllllIlIIIl, final Transform lllllllllllllllllllllIllllIlIIll) {
        this(lllllllllllllllllllllIllllIlIIIl);
        this.concatenate(lllllllllllllllllllllIllllIlIIll);
    }
}
