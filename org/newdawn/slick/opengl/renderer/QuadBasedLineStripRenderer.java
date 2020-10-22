package org.newdawn.slick.opengl.renderer;

public class QuadBasedLineStripRenderer implements LineStripRenderer
{
    private /* synthetic */ boolean renderHalf;
    private /* synthetic */ float width;
    private /* synthetic */ SGL GL;
    private /* synthetic */ int cpt;
    public static /* synthetic */ int MAX_POINTS;
    private /* synthetic */ float[] colours;
    private /* synthetic */ boolean lineCaps;
    private /* synthetic */ boolean antialias;
    private /* synthetic */ float[] points;
    private /* synthetic */ int pts;
    private /* synthetic */ DefaultLineStripRenderer def;
    
    @Override
    public void setAntiAlias(final boolean lIIlIIlllIll) {
        this.def.setAntiAlias(lIIlIIlllIll);
        this.antialias = lIIlIIlllIll;
    }
    
    @Override
    public void vertex(final float lIIlIlIIlIll, final float lIIlIlIIlIlI) {
        if (this.width == 1.0f) {
            this.def.vertex(lIIlIlIIlIll, lIIlIlIIlIlI);
            return;
        }
        this.points[this.pts * 2] = lIIlIlIIlIll;
        this.points[this.pts * 2 + 1] = lIIlIlIIlIlI;
        ++this.pts;
        final int lIIlIlIIlIIl = this.pts - 1;
        this.color(this.colours[lIIlIlIIlIIl * 4], this.colours[lIIlIlIIlIIl * 4 + 1], this.colours[lIIlIlIIlIIl * 4 + 2], this.colours[lIIlIlIIlIIl * 4 + 3]);
    }
    
    static {
        QuadBasedLineStripRenderer.MAX_POINTS = 10000;
    }
    
    @Override
    public boolean applyGLLineFixes() {
        if (this.width == 1.0f) {
            return this.def.applyGLLineFixes();
        }
        return this.def.applyGLLineFixes();
    }
    
    public void renderLines(final float[] lIIlIIllIIIl, final int lIIlIIllIIll) {
        if (this.antialias) {
            this.GL.glEnable(2881);
            this.renderLinesImpl(lIIlIIllIIIl, lIIlIIllIIll, this.width + 1.0f);
        }
        this.GL.glDisable(2881);
        this.renderLinesImpl(lIIlIIllIIIl, lIIlIIllIIll, this.width);
        if (this.antialias) {
            this.GL.glEnable(2881);
        }
    }
    
    public QuadBasedLineStripRenderer() {
        this.GL = Renderer.get();
        this.width = 1.0f;
        this.def = new DefaultLineStripRenderer();
        this.lineCaps = false;
        this.points = new float[QuadBasedLineStripRenderer.MAX_POINTS * 2];
        this.colours = new float[QuadBasedLineStripRenderer.MAX_POINTS * 4];
    }
    
    @Override
    public void color(final float lIIIllIlIIll, final float lIIIllIlIIlI, final float lIIIllIlIIIl, final float lIIIllIlIIII) {
        if (this.width == 1.0f) {
            this.def.color(lIIIllIlIIll, lIIIllIlIIlI, lIIIllIlIIIl, lIIIllIlIIII);
            return;
        }
        this.colours[this.pts * 4] = lIIIllIlIIll;
        this.colours[this.pts * 4 + 1] = lIIIllIlIIlI;
        this.colours[this.pts * 4 + 2] = lIIIllIlIIIl;
        this.colours[this.pts * 4 + 3] = lIIIllIlIIII;
        ++this.cpt;
    }
    
    @Override
    public void start() {
        if (this.width == 1.0f) {
            this.def.start();
            return;
        }
        this.pts = 0;
        this.cpt = 0;
        this.GL.flush();
        final float[] lIIlIlIlIllI = this.GL.getCurrentColor();
        this.color(lIIlIlIlIllI[0], lIIlIlIlIllI[1], lIIlIlIlIllI[2], lIIlIlIlIllI[3]);
    }
    
    public void renderLinesImpl(final float[] lIIIllllIlIl, final int lIIIlllllllI, final float lIIIllllIIll) {
        final float lIIIllllllII = lIIIllllIIll / 2.0f;
        float lIIIlllllIll = 0.0f;
        float lIIIlllllIlI = 0.0f;
        float lIIIlllllIIl = 0.0f;
        float lIIIlllllIII = 0.0f;
        this.GL.glBegin(7);
        for (int lIIlIIIIlIll = 0; lIIlIIIIlIll < lIIIlllllllI + 1; ++lIIlIIIIlIll) {
            int lIIlIIIllIII = lIIlIIIIlIll;
            int lIIlIIIlIlll = lIIlIIIIlIll + 1;
            int lIIlIIIlIllI = lIIlIIIIlIll - 1;
            if (lIIlIIIlIllI < 0) {
                lIIlIIIlIllI += lIIIlllllllI;
            }
            if (lIIlIIIlIlll >= lIIIlllllllI) {
                lIIlIIIlIlll -= lIIIlllllllI;
            }
            if (lIIlIIIllIII >= lIIIlllllllI) {
                lIIlIIIllIII -= lIIIlllllllI;
            }
            final float lIIlIIIlIlIl = lIIIllllIlIl[lIIlIIIllIII * 2];
            final float lIIlIIIlIlII = lIIIllllIlIl[lIIlIIIllIII * 2 + 1];
            final float lIIlIIIlIIll = lIIIllllIlIl[lIIlIIIlIlll * 2];
            final float lIIlIIIlIIlI = lIIIllllIlIl[lIIlIIIlIlll * 2 + 1];
            float lIIlIIIlIIIl = lIIlIIIlIIll - lIIlIIIlIlIl;
            float lIIlIIIlIIII = lIIlIIIlIIlI - lIIlIIIlIlII;
            if (lIIlIIIlIIIl != 0.0f || lIIlIIIlIIII != 0.0f) {
                final float lIIlIIIIllll = lIIlIIIlIIIl * lIIlIIIlIIIl + lIIlIIIlIIII * lIIlIIIlIIII;
                final float lIIlIIIIlllI = (float)Math.sqrt(lIIlIIIIllll);
                lIIlIIIlIIIl *= lIIIllllllII;
                lIIlIIIlIIII *= lIIIllllllII;
                lIIlIIIlIIIl /= lIIlIIIIlllI;
                final float lIIlIIIIllIl;
                lIIlIIIlIIII = (lIIlIIIIllIl = lIIlIIIlIIII / lIIlIIIIlllI);
                final float lIIlIIIIllII = -lIIlIIIlIIIl;
                if (lIIlIIIIlIll != 0) {
                    this.bindColor(lIIlIIIlIllI);
                    this.GL.glVertex3f(lIIIlllllIll, lIIIlllllIlI, 0.0f);
                    this.GL.glVertex3f(lIIIlllllIIl, lIIIlllllIII, 0.0f);
                    this.bindColor(lIIlIIIllIII);
                    this.GL.glVertex3f(lIIlIIIlIlIl + lIIlIIIIllIl, lIIlIIIlIlII + lIIlIIIIllII, 0.0f);
                    this.GL.glVertex3f(lIIlIIIlIlIl - lIIlIIIIllIl, lIIlIIIlIlII - lIIlIIIIllII, 0.0f);
                }
                lIIIlllllIll = lIIlIIIlIIll - lIIlIIIIllIl;
                lIIIlllllIlI = lIIlIIIlIIlI - lIIlIIIIllII;
                lIIIlllllIIl = lIIlIIIlIIll + lIIlIIIIllIl;
                lIIIlllllIII = lIIlIIIlIIlI + lIIlIIIIllII;
                if (lIIlIIIIlIll < lIIIlllllllI - 1) {
                    this.bindColor(lIIlIIIllIII);
                    this.GL.glVertex3f(lIIlIIIlIlIl + lIIlIIIIllIl, lIIlIIIlIlII + lIIlIIIIllII, 0.0f);
                    this.GL.glVertex3f(lIIlIIIlIlIl - lIIlIIIIllIl, lIIlIIIlIlII - lIIlIIIIllII, 0.0f);
                    this.bindColor(lIIlIIIlIlll);
                    this.GL.glVertex3f(lIIlIIIlIIll - lIIlIIIIllIl, lIIlIIIlIIlI - lIIlIIIIllII, 0.0f);
                    this.GL.glVertex3f(lIIlIIIlIIll + lIIlIIIIllIl, lIIlIIIlIIlI + lIIlIIIIllII, 0.0f);
                }
            }
        }
        this.GL.glEnd();
        final float lIIIllllIlll = (lIIIllllllII <= 12.5f) ? 5.0f : (180.0f / (float)Math.ceil(lIIIllllllII / 2.5));
        if (this.lineCaps) {
            final float lIIlIIIIlIII = lIIIllllIlIl[2] - lIIIllllIlIl[0];
            final float lIIlIIIIIlll = lIIIllllIlIl[3] - lIIIllllIlIl[1];
            final float lIIlIIIIIllI = (float)Math.toDegrees(Math.atan2(lIIlIIIIIlll, lIIlIIIIlIII)) + 90.0f;
            if (lIIlIIIIlIII != 0.0f || lIIlIIIIIlll != 0.0f) {
                this.GL.glBegin(6);
                this.bindColor(0);
                this.GL.glVertex2f(lIIIllllIlIl[0], lIIIllllIlIl[1]);
                for (int lIIlIIIIlIIl = 0; lIIlIIIIlIIl < 180.0f + lIIIllllIlll; lIIlIIIIlIIl += (int)lIIIllllIlll) {
                    final float lIIlIIIIlIlI = (float)Math.toRadians(lIIlIIIIIllI + lIIlIIIIlIIl);
                    this.GL.glVertex2f(lIIIllllIlIl[0] + (float)(Math.cos(lIIlIIIIlIlI) * lIIIllllllII), lIIIllllIlIl[1] + (float)(Math.sin(lIIlIIIIlIlI) * lIIIllllllII));
                }
                this.GL.glEnd();
            }
        }
        if (this.lineCaps) {
            final float lIIlIIIIIIll = lIIIllllIlIl[lIIIlllllllI * 2 - 2] - lIIIllllIlIl[lIIIlllllllI * 2 - 4];
            final float lIIlIIIIIIlI = lIIIllllIlIl[lIIIlllllllI * 2 - 1] - lIIIllllIlIl[lIIIlllllllI * 2 - 3];
            final float lIIlIIIIIIIl = (float)Math.toDegrees(Math.atan2(lIIlIIIIIIlI, lIIlIIIIIIll)) - 90.0f;
            if (lIIlIIIIIIll != 0.0f || lIIlIIIIIIlI != 0.0f) {
                this.GL.glBegin(6);
                this.bindColor(lIIIlllllllI - 1);
                this.GL.glVertex2f(lIIIllllIlIl[lIIIlllllllI * 2 - 2], lIIIllllIlIl[lIIIlllllllI * 2 - 1]);
                for (int lIIlIIIIIlII = 0; lIIlIIIIIlII < 180.0f + lIIIllllIlll; lIIlIIIIIlII += (int)lIIIllllIlll) {
                    final float lIIlIIIIIlIl = (float)Math.toRadians(lIIlIIIIIIIl + lIIlIIIIIlII);
                    this.GL.glVertex2f(lIIIllllIlIl[lIIIlllllllI * 2 - 2] + (float)(Math.cos(lIIlIIIIIlIl) * lIIIllllllII), lIIIllllIlIl[lIIIlllllllI * 2 - 1] + (float)(Math.sin(lIIlIIIIIlIl) * lIIIllllllII));
                }
                this.GL.glEnd();
            }
        }
    }
    
    @Override
    public void end() {
        if (this.width == 1.0f) {
            this.def.end();
            return;
        }
        this.renderLines(this.points, this.pts);
    }
    
    private void bindColor(final int lIIIllIlllII) {
        if (lIIIllIlllII < this.cpt) {
            if (this.renderHalf) {
                this.GL.glColor4f(this.colours[lIIIllIlllII * 4] * 0.5f, this.colours[lIIIllIlllII * 4 + 1] * 0.5f, this.colours[lIIIllIlllII * 4 + 2] * 0.5f, this.colours[lIIIllIlllII * 4 + 3] * 0.5f);
            }
            else {
                this.GL.glColor4f(this.colours[lIIIllIlllII * 4], this.colours[lIIIllIlllII * 4 + 1], this.colours[lIIIllIlllII * 4 + 2], this.colours[lIIIllIlllII * 4 + 3]);
            }
        }
    }
    
    @Override
    public void setLineCaps(final boolean lIIlIlIllIlI) {
        this.lineCaps = lIIlIlIllIlI;
    }
    
    @Override
    public void setWidth(final float lIIlIIllllll) {
        this.width = lIIlIIllllll;
    }
}
