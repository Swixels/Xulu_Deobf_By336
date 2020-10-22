package org.newdawn.slick.geom;

public class OverTriangulator implements Triangulator
{
    private /* synthetic */ float[][] triangles;
    
    @Override
    public void startHole() {
    }
    
    @Override
    public boolean triangulate() {
        return true;
    }
    
    @Override
    public float[] getTrianglePoint(final int llllllllllllllllllIlIIIlllllIIIl, final int llllllllllllllllllIlIIIlllllIIII) {
        final float[] llllllllllllllllllIlIIIlllllIIll = this.triangles[llllllllllllllllllIlIIIlllllIIIl * 3 + llllllllllllllllllIlIIIlllllIIII];
        return new float[] { llllllllllllllllllIlIIIlllllIIll[0], llllllllllllllllllIlIIIlllllIIll[1] };
    }
    
    @Override
    public void addPolyPoint(final float llllllllllllllllllIlIIIlllllllll, final float llllllllllllllllllIlIIIllllllllI) {
    }
    
    public OverTriangulator(final Triangulator llllllllllllllllllIlIIlIIIIIllII) {
        this.triangles = new float[llllllllllllllllllIlIIlIIIIIllII.getTriangleCount() * 6 * 3][2];
        int llllllllllllllllllIlIIlIIIIIlIll = 0;
        for (int llllllllllllllllllIlIIlIIIIIlllI = 0; llllllllllllllllllIlIIlIIIIIlllI < llllllllllllllllllIlIIlIIIIIllII.getTriangleCount(); ++llllllllllllllllllIlIIlIIIIIlllI) {
            float llllllllllllllllllIlIIlIIIIlIIII = 0.0f;
            float llllllllllllllllllIlIIlIIIIIllll = 0.0f;
            for (int llllllllllllllllllIlIIlIIIIllIIl = 0; llllllllllllllllllIlIIlIIIIllIIl < 3; ++llllllllllllllllllIlIIlIIIIllIIl) {
                final float[] llllllllllllllllllIlIIlIIIIllIlI = llllllllllllllllllIlIIlIIIIIllII.getTrianglePoint(llllllllllllllllllIlIIlIIIIIlllI, llllllllllllllllllIlIIlIIIIllIIl);
                llllllllllllllllllIlIIlIIIIlIIII += llllllllllllllllllIlIIlIIIIllIlI[0];
                llllllllllllllllllIlIIlIIIIIllll += llllllllllllllllllIlIIlIIIIllIlI[1];
            }
            llllllllllllllllllIlIIlIIIIlIIII /= 3.0f;
            llllllllllllllllllIlIIlIIIIIllll /= 3.0f;
            for (int llllllllllllllllllIlIIlIIIIlIlIl = 0; llllllllllllllllllIlIIlIIIIlIlIl < 3; ++llllllllllllllllllIlIIlIIIIlIlIl) {
                int llllllllllllllllllIlIIlIIIIllIII = llllllllllllllllllIlIIlIIIIlIlIl + 1;
                if (llllllllllllllllllIlIIlIIIIllIII > 2) {
                    llllllllllllllllllIlIIlIIIIllIII = 0;
                }
                final float[] llllllllllllllllllIlIIlIIIIlIlll = llllllllllllllllllIlIIlIIIIIllII.getTrianglePoint(llllllllllllllllllIlIIlIIIIIlllI, llllllllllllllllllIlIIlIIIIlIlIl);
                final float[] llllllllllllllllllIlIIlIIIIlIllI = llllllllllllllllllIlIIlIIIIIllII.getTrianglePoint(llllllllllllllllllIlIIlIIIIIlllI, llllllllllllllllllIlIIlIIIIllIII);
                llllllllllllllllllIlIIlIIIIlIlll[0] = (llllllllllllllllllIlIIlIIIIlIlll[0] + llllllllllllllllllIlIIlIIIIlIllI[0]) / 2.0f;
                llllllllllllllllllIlIIlIIIIlIlll[1] = (llllllllllllllllllIlIIlIIIIlIlll[1] + llllllllllllllllllIlIIlIIIIlIllI[1]) / 2.0f;
                this.triangles[llllllllllllllllllIlIIlIIIIIlIll * 3 + 0][0] = llllllllllllllllllIlIIlIIIIlIIII;
                this.triangles[llllllllllllllllllIlIIlIIIIIlIll * 3 + 0][1] = llllllllllllllllllIlIIlIIIIIllll;
                this.triangles[llllllllllllllllllIlIIlIIIIIlIll * 3 + 1][0] = llllllllllllllllllIlIIlIIIIlIlll[0];
                this.triangles[llllllllllllllllllIlIIlIIIIIlIll * 3 + 1][1] = llllllllllllllllllIlIIlIIIIlIlll[1];
                this.triangles[llllllllllllllllllIlIIlIIIIIlIll * 3 + 2][0] = llllllllllllllllllIlIIlIIIIlIllI[0];
                this.triangles[llllllllllllllllllIlIIlIIIIIlIll * 3 + 2][1] = llllllllllllllllllIlIIlIIIIlIllI[1];
                ++llllllllllllllllllIlIIlIIIIIlIll;
            }
            for (int llllllllllllllllllIlIIlIIIIlIIIl = 0; llllllllllllllllllIlIIlIIIIlIIIl < 3; ++llllllllllllllllllIlIIlIIIIlIIIl) {
                int llllllllllllllllllIlIIlIIIIlIlII = llllllllllllllllllIlIIlIIIIlIIIl + 1;
                if (llllllllllllllllllIlIIlIIIIlIlII > 2) {
                    llllllllllllllllllIlIIlIIIIlIlII = 0;
                }
                final float[] llllllllllllllllllIlIIlIIIIlIIll = llllllllllllllllllIlIIlIIIIIllII.getTrianglePoint(llllllllllllllllllIlIIlIIIIIlllI, llllllllllllllllllIlIIlIIIIlIIIl);
                final float[] llllllllllllllllllIlIIlIIIIlIIlI = llllllllllllllllllIlIIlIIIIIllII.getTrianglePoint(llllllllllllllllllIlIIlIIIIIlllI, llllllllllllllllllIlIIlIIIIlIlII);
                llllllllllllllllllIlIIlIIIIlIIlI[0] = (llllllllllllllllllIlIIlIIIIlIIll[0] + llllllllllllllllllIlIIlIIIIlIIlI[0]) / 2.0f;
                llllllllllllllllllIlIIlIIIIlIIlI[1] = (llllllllllllllllllIlIIlIIIIlIIll[1] + llllllllllllllllllIlIIlIIIIlIIlI[1]) / 2.0f;
                this.triangles[llllllllllllllllllIlIIlIIIIIlIll * 3 + 0][0] = llllllllllllllllllIlIIlIIIIlIIII;
                this.triangles[llllllllllllllllllIlIIlIIIIIlIll * 3 + 0][1] = llllllllllllllllllIlIIlIIIIIllll;
                this.triangles[llllllllllllllllllIlIIlIIIIIlIll * 3 + 1][0] = llllllllllllllllllIlIIlIIIIlIIll[0];
                this.triangles[llllllllllllllllllIlIIlIIIIIlIll * 3 + 1][1] = llllllllllllllllllIlIIlIIIIlIIll[1];
                this.triangles[llllllllllllllllllIlIIlIIIIIlIll * 3 + 2][0] = llllllllllllllllllIlIIlIIIIlIIlI[0];
                this.triangles[llllllllllllllllllIlIIlIIIIIlIll * 3 + 2][1] = llllllllllllllllllIlIIlIIIIlIIlI[1];
                ++llllllllllllllllllIlIIlIIIIIlIll;
            }
        }
    }
    
    @Override
    public int getTriangleCount() {
        return this.triangles.length / 3;
    }
}
