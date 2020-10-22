package org.newdawn.slick.opengl.renderer;

public class Renderer
{
    private static /* synthetic */ SGL renderer;
    private static /* synthetic */ LineStripRenderer lineStripRenderer;
    
    public static LineStripRenderer getLineStripRenderer() {
        return Renderer.lineStripRenderer;
    }
    
    static {
        IMMEDIATE_RENDERER = 1;
        VERTEX_ARRAY_RENDERER = 2;
        DEFAULT_LINE_STRIP_RENDERER = 3;
        QUAD_BASED_LINE_STRIP_RENDERER = 4;
        Renderer.renderer = new ImmediateModeOGLRenderer();
        Renderer.lineStripRenderer = new DefaultLineStripRenderer();
    }
    
    public static SGL get() {
        return Renderer.renderer;
    }
    
    public static void setLineStripRenderer(final int lIlIllllIIIIll) {
        switch (lIlIllllIIIIll) {
            case 3: {
                setLineStripRenderer(new DefaultLineStripRenderer());
            }
            case 4: {
                setLineStripRenderer(new QuadBasedLineStripRenderer());
            }
            default: {
                throw new RuntimeException(String.valueOf(new StringBuilder().append("Unknown line strip renderer type: ").append(lIlIllllIIIIll)));
            }
        }
    }
    
    public static void setRenderer(final int lIlIllllIIlllI) {
        switch (lIlIllllIIlllI) {
            case 1: {
                setRenderer(new ImmediateModeOGLRenderer());
            }
            case 2: {
                setRenderer(new VAOGLRenderer());
            }
            default: {
                throw new RuntimeException(String.valueOf(new StringBuilder().append("Unknown renderer type: ").append(lIlIllllIIlllI)));
            }
        }
    }
    
    public static void setRenderer(final SGL lIlIlllIllllIl) {
        Renderer.renderer = lIlIlllIllllIl;
    }
    
    public static void setLineStripRenderer(final LineStripRenderer lIlIllllIIIIIl) {
        Renderer.lineStripRenderer = lIlIllllIIIIIl;
    }
}
