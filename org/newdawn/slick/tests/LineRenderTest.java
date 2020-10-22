package org.newdawn.slick.tests;

import org.newdawn.slick.geom.*;
import org.newdawn.slick.opengl.renderer.*;
import org.newdawn.slick.*;

public class LineRenderTest extends BasicGame
{
    private /* synthetic */ Path path;
    private /* synthetic */ boolean antialias;
    private /* synthetic */ float width;
    private /* synthetic */ Polygon polygon;
    
    @Override
    public void render(final GameContainer lllllllllllllllllIllllllIIIllIII, final Graphics lllllllllllllllllIllllllIIIlIlll) throws SlickException {
        lllllllllllllllllIllllllIIIlIlll.setAntiAlias(this.antialias);
        lllllllllllllllllIllllllIIIlIlll.setLineWidth(50.0f);
        lllllllllllllllllIllllllIIIlIlll.setColor(Color.red);
        lllllllllllllllllIllllllIIIlIlll.draw(this.path);
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIllllllIIlIIlII) throws SlickException {
        this.polygon.addPoint(100.0f, 100.0f);
        this.polygon.addPoint(200.0f, 80.0f);
        this.polygon.addPoint(320.0f, 150.0f);
        this.polygon.addPoint(230.0f, 210.0f);
        this.polygon.addPoint(170.0f, 260.0f);
        this.path.curveTo(200.0f, 200.0f, 200.0f, 100.0f, 100.0f, 200.0f);
        this.path.curveTo(400.0f, 100.0f, 400.0f, 200.0f, 200.0f, 100.0f);
        this.path.curveTo(500.0f, 500.0f, 400.0f, 200.0f, 200.0f, 100.0f);
    }
    
    public static void main(final String[] lllllllllllllllllIllllllIIIlIIIl) {
        try {
            Renderer.setLineStripRenderer(4);
            Renderer.getLineStripRenderer().setLineCaps(true);
            final AppGameContainer lllllllllllllllllIllllllIIIlIIll = new AppGameContainer(new LineRenderTest());
            lllllllllllllllllIllllllIIIlIIll.setDisplayMode(800, 600, false);
            lllllllllllllllllIllllllIIIlIIll.start();
        }
        catch (SlickException lllllllllllllllllIllllllIIIlIIlI) {
            lllllllllllllllllIllllllIIIlIIlI.printStackTrace();
        }
    }
    
    public LineRenderTest() {
        super("LineRenderTest");
        this.polygon = new Polygon();
        this.path = new Path(100.0f, 100.0f);
        this.width = 10.0f;
        this.antialias = true;
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIllllllIIIlllII, final int lllllllllllllllllIllllllIIIllllI) throws SlickException {
        if (lllllllllllllllllIllllllIIIlllII.getInput().isKeyPressed(57)) {
            this.antialias = !this.antialias;
        }
    }
}
