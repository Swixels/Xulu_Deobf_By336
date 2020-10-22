package org.newdawn.slick.tests;

import org.newdawn.slick.svg.*;
import org.newdawn.slick.opengl.renderer.*;
import org.newdawn.slick.*;

public class InkscapeTest extends BasicGame
{
    private /* synthetic */ SimpleDiagramRenderer[] renderer;
    private /* synthetic */ float zoom;
    private /* synthetic */ float y;
    private /* synthetic */ float x;
    
    @Override
    public void init(final GameContainer lllllllllllllllllIlIlllIlIlllIIl) throws SlickException {
        lllllllllllllllllIlIlllIlIlllIIl.getGraphics().setBackground(Color.white);
        InkscapeLoader.RADIAL_TRIANGULATION_LEVEL = 2;
        this.renderer[3] = new SimpleDiagramRenderer(InkscapeLoader.load("testdata/svg/clonetest.svg"));
        lllllllllllllllllIlIlllIlIlllIIl.getGraphics().setBackground(new Color(0.5f, 0.7f, 1.0f));
    }
    
    public InkscapeTest() {
        super("Inkscape Test");
        this.renderer = new SimpleDiagramRenderer[5];
        this.zoom = 1.0f;
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIlIlllIlIllIIlI, final int lllllllllllllllllIlIlllIlIlIlllI) throws SlickException {
        if (lllllllllllllllllIlIlllIlIllIIlI.getInput().isKeyDown(16)) {
            this.zoom += lllllllllllllllllIlIlllIlIlIlllI * 0.01f;
            if (this.zoom > 10.0f) {
                this.zoom = 10.0f;
            }
        }
        if (lllllllllllllllllIlIlllIlIllIIlI.getInput().isKeyDown(30)) {
            this.zoom -= lllllllllllllllllIlIlllIlIlIlllI * 0.01f;
            if (this.zoom < 0.1f) {
                this.zoom = 0.1f;
            }
        }
        if (lllllllllllllllllIlIlllIlIllIIlI.getInput().isKeyDown(205)) {
            this.x += lllllllllllllllllIlIlllIlIlIlllI * 0.1f;
        }
        if (lllllllllllllllllIlIlllIlIllIIlI.getInput().isKeyDown(203)) {
            this.x -= lllllllllllllllllIlIlllIlIlIlllI * 0.1f;
        }
        if (lllllllllllllllllIlIlllIlIllIIlI.getInput().isKeyDown(208)) {
            this.y += lllllllllllllllllIlIlllIlIlIlllI * 0.1f;
        }
        if (lllllllllllllllllIlIlllIlIllIIlI.getInput().isKeyDown(200)) {
            this.y -= lllllllllllllllllIlIlllIlIlIlllI * 0.1f;
        }
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIlIlllIlIlIlIlI, final Graphics lllllllllllllllllIlIlllIlIlIIlll) throws SlickException {
        lllllllllllllllllIlIlllIlIlIIlll.scale(this.zoom, this.zoom);
        lllllllllllllllllIlIlllIlIlIIlll.translate(this.x, this.y);
        lllllllllllllllllIlIlllIlIlIIlll.scale(0.3f, 0.3f);
        lllllllllllllllllIlIlllIlIlIIlll.scale(3.3333333f, 3.3333333f);
        lllllllllllllllllIlIlllIlIlIIlll.translate(400.0f, 0.0f);
        lllllllllllllllllIlIlllIlIlIIlll.translate(100.0f, 300.0f);
        lllllllllllllllllIlIlllIlIlIIlll.scale(0.7f, 0.7f);
        lllllllllllllllllIlIlllIlIlIIlll.scale(1.4285715f, 1.4285715f);
        lllllllllllllllllIlIlllIlIlIIlll.scale(0.5f, 0.5f);
        lllllllllllllllllIlIlllIlIlIIlll.translate(-1100.0f, -380.0f);
        this.renderer[3].render(lllllllllllllllllIlIlllIlIlIIlll);
        lllllllllllllllllIlIlllIlIlIIlll.scale(2.0f, 2.0f);
        lllllllllllllllllIlIlllIlIlIIlll.resetTransform();
    }
    
    public static void main(final String[] lllllllllllllllllIlIlllIlIlIIIll) {
        try {
            Renderer.setRenderer(2);
            Renderer.setLineStripRenderer(4);
            final AppGameContainer lllllllllllllllllIlIlllIlIlIIlIl = new AppGameContainer(new InkscapeTest());
            lllllllllllllllllIlIlllIlIlIIlIl.setDisplayMode(800, 600, false);
            lllllllllllllllllIlIlllIlIlIIlIl.start();
        }
        catch (SlickException lllllllllllllllllIlIlllIlIlIIlII) {
            lllllllllllllllllIlIlllIlIlIIlII.printStackTrace();
        }
    }
}
