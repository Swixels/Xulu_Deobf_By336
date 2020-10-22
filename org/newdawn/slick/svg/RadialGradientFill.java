package org.newdawn.slick.svg;

import org.newdawn.slick.geom.*;

public class RadialGradientFill implements TexCoordGenerator
{
    private /* synthetic */ Gradient gradient;
    private /* synthetic */ float radius;
    private /* synthetic */ Vector2f centre;
    
    @Override
    public Vector2f getCoordFor(final float lllllllIllIIlII, final float lllllllIllIIIll) {
        float lllllllIllIIIlI = this.centre.distance(new Vector2f(lllllllIllIIlII, lllllllIllIIIll));
        lllllllIllIIIlI /= this.radius;
        if (lllllllIllIIIlI > 0.99f) {
            lllllllIllIIIlI = 0.99f;
        }
        return new Vector2f(lllllllIllIIIlI, 0.0f);
    }
    
    public RadialGradientFill(final Shape lllllllIllllIIl, final Transform lllllllIllllIII, final Gradient lllllllIllIllll) {
        this.gradient = lllllllIllIllll;
        this.radius = lllllllIllIllll.getR();
        final float lllllllIlllIllI = lllllllIllIllll.getX1();
        final float lllllllIlllIlIl = lllllllIllIllll.getY1();
        final float[] lllllllIlllIlII = { lllllllIlllIllI, lllllllIlllIlIl };
        lllllllIllIllll.getTransform().transform(lllllllIlllIlII, 0, lllllllIlllIlII, 0, 1);
        lllllllIllllIII.transform(lllllllIlllIlII, 0, lllllllIlllIlII, 0, 1);
        final float[] lllllllIlllIIll = { lllllllIlllIllI, lllllllIlllIlIl - this.radius };
        lllllllIllIllll.getTransform().transform(lllllllIlllIIll, 0, lllllllIlllIIll, 0, 1);
        lllllllIllllIII.transform(lllllllIlllIIll, 0, lllllllIlllIIll, 0, 1);
        this.centre = new Vector2f(lllllllIlllIlII[0], lllllllIlllIlII[1]);
        final Vector2f lllllllIlllIIlI = new Vector2f(lllllllIlllIIll[0], lllllllIlllIIll[1]);
        this.radius = lllllllIlllIIlI.distance(this.centre);
    }
}
