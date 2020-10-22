package org.newdawn.slick.tests;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class TexturePaintTest extends BasicGame
{
    private /* synthetic */ Image image;
    private /* synthetic */ Rectangle texRect;
    private /* synthetic */ TexCoordGenerator texPaint;
    private /* synthetic */ Polygon poly;
    
    public TexturePaintTest() {
        super("Texture Paint Test");
        this.poly = new Polygon();
        this.texRect = new Rectangle(50.0f, 50.0f, 100.0f, 100.0f);
    }
    
    @Override
    public void update(final GameContainer lIlIllIII, final int lIlIlIlll) throws SlickException {
    }
    
    public static void main(final String[] lIlIIllII) {
        try {
            final AppGameContainer lIlIIlllI = new AppGameContainer(new TexturePaintTest());
            lIlIIlllI.setDisplayMode(800, 600, false);
            lIlIIlllI.start();
        }
        catch (SlickException lIlIIllIl) {
            lIlIIllIl.printStackTrace();
        }
    }
    
    @Override
    public void init(final GameContainer lIlIllIll) throws SlickException {
        this.poly.addPoint(120.0f, 120.0f);
        this.poly.addPoint(420.0f, 100.0f);
        this.poly.addPoint(620.0f, 420.0f);
        this.poly.addPoint(300.0f, 320.0f);
        this.image = new Image("testdata/rocks.png");
        this.texPaint = new TexCoordGenerator() {
            @Override
            public Vector2f getCoordFor(final float lllllllllllllllllIIIlIlIlllllIll, final float lllllllllllllllllIIIlIlIllllllll) {
                final float lllllllllllllllllIIIlIlIlllllllI = (TexturePaintTest.this.texRect.getX() - lllllllllllllllllIIIlIlIlllllIll) / TexturePaintTest.this.texRect.getWidth();
                final float lllllllllllllllllIIIlIlIllllllIl = (TexturePaintTest.this.texRect.getY() - lllllllllllllllllIIIlIlIllllllll) / TexturePaintTest.this.texRect.getHeight();
                return new Vector2f(lllllllllllllllllIIIlIlIlllllllI, lllllllllllllllllIIIlIlIllllllIl);
            }
        };
    }
    
    @Override
    public void render(final GameContainer lIlIlIIll, final Graphics lIlIlIIII) throws SlickException {
        lIlIlIIII.setColor(Color.white);
        lIlIlIIII.texture(this.poly, this.image);
        ShapeRenderer.texture(this.poly, this.image, this.texPaint);
    }
}
