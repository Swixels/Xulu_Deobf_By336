package org.newdawn.slick.geom;

import org.newdawn.slick.opengl.*;
import org.newdawn.slick.opengl.renderer.*;
import org.newdawn.slick.*;

public final class ShapeRenderer
{
    private static /* synthetic */ SGL GL;
    private static /* synthetic */ LineStripRenderer LSR;
    
    public static final void fill(final Shape lIlIIllllllllI, final ShapeFill lIlIIlllllllIl) {
        if (!validFill(lIlIIllllllllI)) {
            return;
        }
        final Texture lIlIlIIIIIIIII = TextureImpl.getLastBind();
        TextureImpl.bindNone();
        final float[] lIlIIlllllllll = lIlIIllllllllI.getCenter();
        fill(lIlIIllllllllI, new PointCallback() {
            @Override
            public float[] preRenderPoint(final Shape lllllllllllllllllllIIIIIllIlIIIl, final float lllllllllllllllllllIIIIIllIlIIII, final float lllllllllllllllllllIIIIIllIIllll) {
                lIlIIlllllllIl.colorAt(lllllllllllllllllllIIIIIllIlIIIl, lllllllllllllllllllIIIIIllIlIIII, lllllllllllllllllllIIIIIllIIllll).bind();
                final Vector2f lllllllllllllllllllIIIIIllIIlllI = lIlIIlllllllIl.getOffsetAt(lllllllllllllllllllIIIIIllIlIIIl, lllllllllllllllllllIIIIIllIlIIII, lllllllllllllllllllIIIIIllIIllll);
                return new float[] { lllllllllllllllllllIIIIIllIIlllI.x + lllllllllllllllllllIIIIIllIlIIII, lllllllllllllllllllIIIIIllIIlllI.y + lllllllllllllllllllIIIIIllIIllll };
            }
        });
        if (lIlIlIIIIIIIII == null) {
            TextureImpl.bindNone();
        }
        else {
            lIlIlIIIIIIIII.bind();
        }
    }
    
    static {
        ShapeRenderer.GL = Renderer.get();
        ShapeRenderer.LSR = Renderer.getLineStripRenderer();
    }
    
    private static final void fill(final Shape lIlIlIIlIlIIIl, final PointCallback lIlIlIIlIlIIll) {
        final Triangulator lIlIlIIlIlIIlI = lIlIlIIlIlIIIl.getTriangles();
        ShapeRenderer.GL.glBegin(4);
        for (int lIlIlIIlIlIlIl = 0; lIlIlIIlIlIlIl < lIlIlIIlIlIIlI.getTriangleCount(); ++lIlIlIIlIlIlIl) {
            for (int lIlIlIIlIlIllI = 0; lIlIlIIlIlIllI < 3; ++lIlIlIIlIlIllI) {
                final float[] lIlIlIIlIllIII = lIlIlIIlIlIIlI.getTrianglePoint(lIlIlIIlIlIlIl, lIlIlIIlIlIllI);
                final float[] lIlIlIIlIlIlll = lIlIlIIlIlIIll.preRenderPoint(lIlIlIIlIlIIIl, lIlIlIIlIllIII[0], lIlIlIIlIllIII[1]);
                if (lIlIlIIlIlIlll == null) {
                    ShapeRenderer.GL.glVertex2f(lIlIlIIlIllIII[0], lIlIlIIlIllIII[1]);
                }
                else {
                    ShapeRenderer.GL.glVertex2f(lIlIlIIlIlIlll[0], lIlIlIIlIlIlll[1]);
                }
            }
        }
        ShapeRenderer.GL.glEnd();
    }
    
    public static final void textureFit(final Shape lIlIlIIlIIIIlI, final Image lIlIlIIIllllll) {
        textureFit(lIlIlIIlIIIIlI, lIlIlIIIllllll, 1.0f, 1.0f);
    }
    
    public static final void texture(final Shape lIlIIlllIlIIII, final Image lIlIIlllIlIlII, final TexCoordGenerator lIlIIlllIIlllI) {
        final Texture lIlIIlllIlIIlI = TextureImpl.getLastBind();
        lIlIIlllIlIlII.getTexture().bind();
        final float[] lIlIIlllIlIIIl = lIlIIlllIlIIII.getCenter();
        fill(lIlIIlllIlIIII, new PointCallback() {
            @Override
            public float[] preRenderPoint(final Shape llllllllllllllllIlIlllllllIIIIll, final float llllllllllllllllIlIllllllIllllII, final float llllllllllllllllIlIllllllIlllIlI) {
                final Vector2f llllllllllllllllIlIlllllllIIIIII = lIlIIlllIIlllI.getCoordFor(llllllllllllllllIlIllllllIllllII, llllllllllllllllIlIllllllIlllIlI);
                ShapeRenderer.GL.glTexCoord2f(llllllllllllllllIlIlllllllIIIIII.x, llllllllllllllllIlIlllllllIIIIII.y);
                return new float[] { llllllllllllllllIlIllllllIllllII, llllllllllllllllIlIllllllIlllIlI };
            }
        });
        if (lIlIIlllIlIIlI == null) {
            TextureImpl.bindNone();
        }
        else {
            lIlIIlllIlIIlI.bind();
        }
    }
    
    public static final void texture(final Shape lIlIIllllIlIIl, final Image lIlIIllllIIIII, final float lIlIIllllIIlll, final float lIlIIlllIllllI, final ShapeFill lIlIIlllIlllIl) {
        if (!validFill(lIlIIllllIlIIl)) {
            return;
        }
        final Texture lIlIIllllIIIll = TextureImpl.getLastBind();
        lIlIIllllIIIII.getTexture().bind();
        final float[] lIlIIllllIIIlI = lIlIIllllIlIIl.getCenter();
        fill(lIlIIllllIlIIl, new PointCallback() {
            @Override
            public float[] preRenderPoint(final Shape llllllllllllllllIlIlIllIIIlIIIIl, float llllllllllllllllIlIlIllIIIlIIIII, float llllllllllllllllIlIlIllIIIlIIllI) {
                lIlIIlllIlllIl.colorAt(llllllllllllllllIlIlIllIIIlIIIIl, llllllllllllllllIlIlIllIIIlIIIII - lIlIIllllIIIlI[0], llllllllllllllllIlIlIllIIIlIIllI - lIlIIllllIIIlI[1]).bind();
                final Vector2f llllllllllllllllIlIlIllIIIlIIlIl = lIlIIlllIlllIl.getOffsetAt(llllllllllllllllIlIlIllIIIlIIIIl, llllllllllllllllIlIlIllIIIlIIIII, llllllllllllllllIlIlIllIIIlIIllI);
                llllllllllllllllIlIlIllIIIlIIIII += llllllllllllllllIlIlIllIIIlIIlIl.x;
                llllllllllllllllIlIlIllIIIlIIllI += llllllllllllllllIlIlIllIIIlIIlIl.y;
                float llllllllllllllllIlIlIllIIIlIIlII = llllllllllllllllIlIlIllIIIlIIIII * lIlIIllllIIlll;
                float llllllllllllllllIlIlIllIIIlIIIll = llllllllllllllllIlIlIllIIIlIIllI * lIlIIlllIllllI;
                llllllllllllllllIlIlIllIIIlIIlII = lIlIIllllIIIII.getTextureOffsetX() + lIlIIllllIIIII.getTextureWidth() * llllllllllllllllIlIlIllIIIlIIlII;
                llllllllllllllllIlIlIllIIIlIIIll = lIlIIllllIIIII.getTextureOffsetY() + lIlIIllllIIIII.getTextureHeight() * llllllllllllllllIlIlIllIIIlIIIll;
                ShapeRenderer.GL.glTexCoord2f(llllllllllllllllIlIlIllIIIlIIlII, llllllllllllllllIlIlIllIIIlIIIll);
                return new float[] { llllllllllllllllIlIlIllIIIlIIlIl.x + llllllllllllllllIlIlIllIIIlIIIII, llllllllllllllllIlIlIllIIIlIIlIl.y + llllllllllllllllIlIlIllIIIlIIllI };
            }
        });
        if (lIlIIllllIIIll == null) {
            TextureImpl.bindNone();
        }
        else {
            lIlIIllllIIIll.bind();
        }
    }
    
    public static final void draw(final Shape lIlIlIIlllIlII, final ShapeFill lIlIlIIlllIIll) {
        final float[] lIlIlIIlllIIlI = lIlIlIIlllIlII.getPoints();
        final Texture lIlIlIIlllIIIl = TextureImpl.getLastBind();
        TextureImpl.bindNone();
        final float[] lIlIlIIlllIIII = lIlIlIIlllIlII.getCenter();
        ShapeRenderer.GL.glBegin(3);
        for (int lIlIlIIlllIllI = 0; lIlIlIIlllIllI < lIlIlIIlllIIlI.length; lIlIlIIlllIllI += 2) {
            lIlIlIIlllIIll.colorAt(lIlIlIIlllIlII, lIlIlIIlllIIlI[lIlIlIIlllIllI], lIlIlIIlllIIlI[lIlIlIIlllIllI + 1]).bind();
            final Vector2f lIlIlIIlllIlll = lIlIlIIlllIIll.getOffsetAt(lIlIlIIlllIlII, lIlIlIIlllIIlI[lIlIlIIlllIllI], lIlIlIIlllIIlI[lIlIlIIlllIllI + 1]);
            ShapeRenderer.GL.glVertex2f(lIlIlIIlllIIlI[lIlIlIIlllIllI] + lIlIlIIlllIlll.x, lIlIlIIlllIIlI[lIlIlIIlllIllI + 1] + lIlIlIIlllIlll.y);
        }
        if (lIlIlIIlllIlII.closed()) {
            lIlIlIIlllIIll.colorAt(lIlIlIIlllIlII, lIlIlIIlllIIlI[0], lIlIlIIlllIIlI[1]).bind();
            final Vector2f lIlIlIIlllIlIl = lIlIlIIlllIIll.getOffsetAt(lIlIlIIlllIlII, lIlIlIIlllIIlI[0], lIlIlIIlllIIlI[1]);
            ShapeRenderer.GL.glVertex2f(lIlIlIIlllIIlI[0] + lIlIlIIlllIlIl.x, lIlIlIIlllIIlI[1] + lIlIlIIlllIlIl.y);
        }
        ShapeRenderer.GL.glEnd();
        if (lIlIlIIlllIIIl == null) {
            TextureImpl.bindNone();
        }
        else {
            lIlIlIIlllIIIl.bind();
        }
    }
    
    public static boolean validFill(final Shape lIlIlIIllIIlll) {
        return lIlIlIIllIIlll.getTriangles() != null && lIlIlIIllIIlll.getTriangles().getTriangleCount() != 0;
    }
    
    public static final void texture(final Shape lIlIlIIIlllIII, final Image lIlIlIIIllIIIl, final float lIlIlIIIllIllI, final float lIlIlIIIllIlIl) {
        if (!validFill(lIlIlIIIlllIII)) {
            return;
        }
        final Texture lIlIlIIIllIlII = TextureImpl.getLastBind();
        lIlIlIIIllIIIl.getTexture().bind();
        fill(lIlIlIIIlllIII, new PointCallback() {
            @Override
            public float[] preRenderPoint(final Shape llllllllllllllllIlIlIIlIIIIIlIIl, final float llllllllllllllllIlIlIIlIIIIIlIII, final float llllllllllllllllIlIlIIlIIIIIIlll) {
                float llllllllllllllllIlIlIIlIIIIIIllI = llllllllllllllllIlIlIIlIIIIIlIII * lIlIlIIIllIllI;
                float llllllllllllllllIlIlIIlIIIIIIlIl = llllllllllllllllIlIlIIlIIIIIIlll * lIlIlIIIllIlIl;
                llllllllllllllllIlIlIIlIIIIIIllI = lIlIlIIIllIIIl.getTextureOffsetX() + lIlIlIIIllIIIl.getTextureWidth() * llllllllllllllllIlIlIIlIIIIIIllI;
                llllllllllllllllIlIlIIlIIIIIIlIl = lIlIlIIIllIIIl.getTextureOffsetY() + lIlIlIIIllIIIl.getTextureHeight() * llllllllllllllllIlIlIIlIIIIIIlIl;
                ShapeRenderer.GL.glTexCoord2f(llllllllllllllllIlIlIIlIIIIIIllI, llllllllllllllllIlIlIIlIIIIIIlIl);
                return null;
            }
        });
        final float[] lIlIlIIIllIIll = lIlIlIIIlllIII.getPoints();
        if (lIlIlIIIllIlII == null) {
            TextureImpl.bindNone();
        }
        else {
            lIlIlIIIllIlII.bind();
        }
    }
    
    public static final void draw(final Shape lIlIlIlIIIIlIl) {
        final Texture lIlIlIlIIIIlII = TextureImpl.getLastBind();
        TextureImpl.bindNone();
        final float[] lIlIlIlIIIIIll = lIlIlIlIIIIlIl.getPoints();
        ShapeRenderer.LSR.start();
        for (int lIlIlIlIIIIllI = 0; lIlIlIlIIIIllI < lIlIlIlIIIIIll.length; lIlIlIlIIIIllI += 2) {
            ShapeRenderer.LSR.vertex(lIlIlIlIIIIIll[lIlIlIlIIIIllI], lIlIlIlIIIIIll[lIlIlIlIIIIllI + 1]);
        }
        if (lIlIlIlIIIIlIl.closed()) {
            ShapeRenderer.LSR.vertex(lIlIlIlIIIIIll[0], lIlIlIlIIIIIll[1]);
        }
        ShapeRenderer.LSR.end();
        if (lIlIlIlIIIIlII == null) {
            TextureImpl.bindNone();
        }
        else {
            lIlIlIlIIIIlII.bind();
        }
    }
    
    public static final void texture(final Shape lIlIlIIlIIlIII, final Image lIlIlIIlIIIlll) {
        texture(lIlIlIIlIIlIII, lIlIlIIlIIIlll, 0.01f, 0.01f);
    }
    
    public static final void fill(final Shape lIlIlIIllIIIIl) {
        if (!validFill(lIlIlIIllIIIIl)) {
            return;
        }
        final Texture lIlIlIIllIIIlI = TextureImpl.getLastBind();
        TextureImpl.bindNone();
        fill(lIlIlIIllIIIIl, new PointCallback() {
            @Override
            public float[] preRenderPoint(final Shape llllllllllllllllIllIlIllIllllIll, final float llllllllllllllllIllIlIllIllllIlI, final float llllllllllllllllIllIlIllIllllIIl) {
                return null;
            }
        });
        if (lIlIlIIllIIIlI == null) {
            TextureImpl.bindNone();
        }
        else {
            lIlIlIIllIIIlI.bind();
        }
    }
    
    public static final void textureFit(final Shape lIlIlIIIIlIlll, final Image lIlIlIIIIlIllI, final float lIlIlIIIIlIlII, final float lIlIlIIIIlllll) {
        if (!validFill(lIlIlIIIIlIlll)) {
            return;
        }
        final float[] lIlIlIIIIllllI = lIlIlIIIIlIlll.getPoints();
        final Texture lIlIlIIIIlllIl = TextureImpl.getLastBind();
        lIlIlIIIIlIllI.getTexture().bind();
        final float lIlIlIIIIlllII = lIlIlIIIIlIlll.getX();
        final float lIlIlIIIIllIll = lIlIlIIIIlIlll.getY();
        final float lIlIlIIIIllIlI = lIlIlIIIIlIlll.getMaxX() - lIlIlIIIIlllII;
        final float lIlIlIIIIllIIl = lIlIlIIIIlIlll.getMaxY() - lIlIlIIIIllIll;
        fill(lIlIlIIIIlIlll, new PointCallback() {
            @Override
            public float[] preRenderPoint(final Shape llllllllllllllllIlIIllIlllllIlII, float llllllllllllllllIlIIllIllllIllIl, float llllllllllllllllIlIIllIlllllIIlI) {
                llllllllllllllllIlIIllIllllIllIl -= (String)llllllllllllllllIlIIllIlllllIlII.getMinX();
                llllllllllllllllIlIIllIlllllIIlI -= llllllllllllllllIlIIllIlllllIlII.getMinY();
                llllllllllllllllIlIIllIllllIllIl /= (String)(llllllllllllllllIlIIllIlllllIlII.getMaxX() - llllllllllllllllIlIIllIlllllIlII.getMinX());
                llllllllllllllllIlIIllIlllllIIlI /= llllllllllllllllIlIIllIlllllIlII.getMaxY() - llllllllllllllllIlIIllIlllllIlII.getMinY();
                float llllllllllllllllIlIIllIlllllIIIl = (float)(llllllllllllllllIlIIllIllllIllIl * lIlIlIIIIlIlII);
                float llllllllllllllllIlIIllIlllllIIII = llllllllllllllllIlIIllIlllllIIlI * lIlIlIIIIlllll;
                llllllllllllllllIlIIllIlllllIIIl = lIlIlIIIIlIllI.getTextureOffsetX() + lIlIlIIIIlIllI.getTextureWidth() * llllllllllllllllIlIIllIlllllIIIl;
                llllllllllllllllIlIIllIlllllIIII = lIlIlIIIIlIllI.getTextureOffsetY() + lIlIlIIIIlIllI.getTextureHeight() * llllllllllllllllIlIIllIlllllIIII;
                ShapeRenderer.GL.glTexCoord2f(llllllllllllllllIlIIllIlllllIIIl, llllllllllllllllIlIIllIlllllIIII);
                return null;
            }
        });
        if (lIlIlIIIIlllIl == null) {
            TextureImpl.bindNone();
        }
        else {
            lIlIlIIIIlllIl.bind();
        }
    }
    
    private interface PointCallback
    {
        float[] preRenderPoint(final Shape p0, final float p1, final float p2);
    }
}
