package org.newdawn.slick.svg;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.opengl.renderer.*;
import org.newdawn.slick.opengl.*;

public class SimpleDiagramRenderer
{
    public /* synthetic */ Diagram diagram;
    protected static /* synthetic */ SGL GL;
    public /* synthetic */ int list;
    
    public SimpleDiagramRenderer(final Diagram lIlIIllIllllII) {
        this.list = -1;
        this.diagram = lIlIIllIllllII;
    }
    
    public static void render(final Graphics lIlIIllIlIIlIl, final Diagram lIlIIllIlIIllI) {
        for (int lIlIIllIlIlIII = 0; lIlIIllIlIlIII < lIlIIllIlIIllI.getFigureCount(); ++lIlIIllIlIlIII) {
            final Figure lIlIIllIlIlIIl = lIlIIllIlIIllI.getFigure(lIlIIllIlIlIII);
            if (lIlIIllIlIlIIl.getData().isFilled()) {
                if (lIlIIllIlIlIIl.getData().isColor("fill")) {
                    lIlIIllIlIIlIl.setColor(lIlIIllIlIlIIl.getData().getAsColor("fill"));
                    lIlIIllIlIIlIl.fill(lIlIIllIlIIllI.getFigure(lIlIIllIlIlIII).getShape());
                    lIlIIllIlIIlIl.setAntiAlias(true);
                    lIlIIllIlIIlIl.draw(lIlIIllIlIIllI.getFigure(lIlIIllIlIlIII).getShape());
                    lIlIIllIlIIlIl.setAntiAlias(false);
                }
                final String lIlIIllIlIlIlI = lIlIIllIlIlIIl.getData().getAsReference("fill");
                if (lIlIIllIlIIllI.getPatternDef(lIlIIllIlIlIlI) != null) {
                    System.out.println("PATTERN");
                }
                if (lIlIIllIlIIllI.getGradient(lIlIIllIlIlIlI) != null) {
                    final Gradient lIlIIllIlIllIl = lIlIIllIlIIllI.getGradient(lIlIIllIlIlIlI);
                    final Shape lIlIIllIlIllII = lIlIIllIlIIllI.getFigure(lIlIIllIlIlIII).getShape();
                    TexCoordGenerator lIlIIllIlIlIll = null;
                    if (lIlIIllIlIllIl.isRadial()) {
                        lIlIIllIlIlIll = new RadialGradientFill(lIlIIllIlIllII, lIlIIllIlIIllI.getFigure(lIlIIllIlIlIII).getTransform(), lIlIIllIlIllIl);
                    }
                    else {
                        lIlIIllIlIlIll = new LinearGradientFill(lIlIIllIlIllII, lIlIIllIlIIllI.getFigure(lIlIIllIlIlIII).getTransform(), lIlIIllIlIllIl);
                    }
                    Color.white.bind();
                    ShapeRenderer.texture(lIlIIllIlIllII, lIlIIllIlIllIl.getImage(), lIlIIllIlIlIll);
                }
            }
            if (lIlIIllIlIlIIl.getData().isStroked() && lIlIIllIlIlIIl.getData().isColor("stroke")) {
                lIlIIllIlIIlIl.setColor(lIlIIllIlIlIIl.getData().getAsColor("stroke"));
                lIlIIllIlIIlIl.setLineWidth(lIlIIllIlIlIIl.getData().getAsFloat("stroke-width"));
                lIlIIllIlIIlIl.setAntiAlias(true);
                lIlIIllIlIIlIl.draw(lIlIIllIlIIllI.getFigure(lIlIIllIlIlIII).getShape());
                lIlIIllIlIIlIl.setAntiAlias(false);
                lIlIIllIlIIlIl.resetLineWidth();
            }
        }
    }
    
    static {
        SimpleDiagramRenderer.GL = Renderer.get();
    }
    
    public void render(final Graphics lIlIIllIlllIII) {
        if (this.list == -1) {
            this.list = SimpleDiagramRenderer.GL.glGenLists(1);
            SimpleDiagramRenderer.GL.glNewList(this.list, 4864);
            render(lIlIIllIlllIII, this.diagram);
            SimpleDiagramRenderer.GL.glEndList();
        }
        SimpleDiagramRenderer.GL.glCallList(this.list);
        TextureImpl.bindNone();
    }
}
