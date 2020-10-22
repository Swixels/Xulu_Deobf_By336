package org.newdawn.slick.svg;

import org.newdawn.slick.geom.*;

public class LinearGradientFill implements TexCoordGenerator
{
    private /* synthetic */ Gradient gradient;
    private /* synthetic */ Line line;
    private /* synthetic */ Vector2f start;
    private /* synthetic */ Vector2f end;
    
    @Override
    public Vector2f getCoordFor(final float lllllllllllllllllIlIllllllllIlII, final float lllllllllllllllllIlIlllllllIlllI) {
        final Vector2f lllllllllllllllllIlIllllllllIIlI = new Vector2f();
        this.line.getClosestPoint(new Vector2f(lllllllllllllllllIlIllllllllIlII, lllllllllllllllllIlIlllllllIlllI), lllllllllllllllllIlIllllllllIIlI);
        float lllllllllllllllllIlIllllllllIIIl = lllllllllllllllllIlIllllllllIIlI.distance(this.start);
        lllllllllllllllllIlIllllllllIIIl /= this.line.length();
        return new Vector2f(lllllllllllllllllIlIllllllllIIIl, 0.0f);
    }
    
    public LinearGradientFill(final Shape lllllllllllllllllIllIIIIIIIlIIII, final Transform lllllllllllllllllIllIIIIIIIIIlII, final Gradient lllllllllllllllllIllIIIIIIIIlllI) {
        this.gradient = lllllllllllllllllIllIIIIIIIIlllI;
        final float lllllllllllllllllIllIIIIIIIIllIl = lllllllllllllllllIllIIIIIIIIlllI.getX1();
        final float lllllllllllllllllIllIIIIIIIIllII = lllllllllllllllllIllIIIIIIIIlllI.getY1();
        final float lllllllllllllllllIllIIIIIIIIlIll = lllllllllllllllllIllIIIIIIIIlllI.getX2();
        final float lllllllllllllllllIllIIIIIIIIlIlI = lllllllllllllllllIllIIIIIIIIlllI.getY2();
        final float lllllllllllllllllIllIIIIIIIIlIIl = lllllllllllllllllIllIIIIIIIIlIlI - lllllllllllllllllIllIIIIIIIIllII;
        final float lllllllllllllllllIllIIIIIIIIlIII = lllllllllllllllllIllIIIIIIIIlIll - lllllllllllllllllIllIIIIIIIIllIl;
        final float[] lllllllllllllllllIllIIIIIIIIIlll = { lllllllllllllllllIllIIIIIIIIllIl, lllllllllllllllllIllIIIIIIIIllII + lllllllllllllllllIllIIIIIIIIlIIl / 2.0f };
        lllllllllllllllllIllIIIIIIIIlllI.getTransform().transform(lllllllllllllllllIllIIIIIIIIIlll, 0, lllllllllllllllllIllIIIIIIIIIlll, 0, 1);
        lllllllllllllllllIllIIIIIIIIIlII.transform(lllllllllllllllllIllIIIIIIIIIlll, 0, lllllllllllllllllIllIIIIIIIIIlll, 0, 1);
        final float[] lllllllllllllllllIllIIIIIIIIIllI = { lllllllllllllllllIllIIIIIIIIllIl + lllllllllllllllllIllIIIIIIIIlIII, lllllllllllllllllIllIIIIIIIIllII + lllllllllllllllllIllIIIIIIIIlIIl / 2.0f };
        lllllllllllllllllIllIIIIIIIIlllI.getTransform().transform(lllllllllllllllllIllIIIIIIIIIllI, 0, lllllllllllllllllIllIIIIIIIIIllI, 0, 1);
        lllllllllllllllllIllIIIIIIIIIlII.transform(lllllllllllllllllIllIIIIIIIIIllI, 0, lllllllllllllllllIllIIIIIIIIIllI, 0, 1);
        this.start = new Vector2f(lllllllllllllllllIllIIIIIIIIIlll[0], lllllllllllllllllIllIIIIIIIIIlll[1]);
        this.end = new Vector2f(lllllllllllllllllIllIIIIIIIIIllI[0], lllllllllllllllllIllIIIIIIIIIllI[1]);
        this.line = new Line(this.start, this.end);
    }
}
