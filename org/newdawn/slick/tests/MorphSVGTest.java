package org.newdawn.slick.tests;

import org.newdawn.slick.svg.*;
import org.newdawn.slick.*;

public class MorphSVGTest extends BasicGame
{
    private /* synthetic */ Diagram base;
    private /* synthetic */ SVGMorph morph;
    private /* synthetic */ float x;
    
    @Override
    public void render(final GameContainer lIIIllIllllllII, final Graphics lIIIllIlllllIIl) throws SlickException {
        lIIIllIlllllIIl.translate(this.x, 0.0f);
        SimpleDiagramRenderer.render(lIIIllIlllllIIl, this.morph);
    }
    
    @Override
    public void update(final GameContainer lIIIlllIIIIIIll, final int lIIIlllIIIIIIlI) throws SlickException {
        this.morph.updateMorphTime(lIIIlllIIIIIIlI * 0.003f);
        this.x += lIIIlllIIIIIIlI * 0.2f;
        if (this.x > 550.0f) {
            this.x = -450.0f;
        }
    }
    
    public MorphSVGTest() {
        super("MorphShapeTest");
        this.x = -300.0f;
    }
    
    @Override
    public void init(final GameContainer lIIIlllIIIIIlll) throws SlickException {
        this.base = InkscapeLoader.load("testdata/svg/walk1.svg");
        this.morph = new SVGMorph(this.base);
        this.morph.addStep(InkscapeLoader.load("testdata/svg/walk2.svg"));
        this.morph.addStep(InkscapeLoader.load("testdata/svg/walk3.svg"));
        this.morph.addStep(InkscapeLoader.load("testdata/svg/walk4.svg"));
        lIIIlllIIIIIlll.setVSync(true);
    }
    
    public static void main(final String[] lIIIllIllllIlIl) {
        try {
            final AppGameContainer lIIIllIllllIlll = new AppGameContainer(new MorphSVGTest());
            lIIIllIllllIlll.setDisplayMode(800, 600, false);
            lIIIllIllllIlll.start();
        }
        catch (SlickException lIIIllIllllIllI) {
            lIIIllIllllIllI.printStackTrace();
        }
    }
}
