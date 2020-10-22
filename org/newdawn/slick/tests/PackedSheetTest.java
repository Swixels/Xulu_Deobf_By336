package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class PackedSheetTest extends BasicGame
{
    private /* synthetic */ Image rocket;
    private /* synthetic */ float ang;
    private /* synthetic */ float r;
    private /* synthetic */ PackedSpriteSheet sheet;
    private /* synthetic */ Animation runner;
    private /* synthetic */ GameContainer container;
    
    @Override
    public void keyPressed(final int llllllllllllllllllIllIllllIIIlII, final char llllllllllllllllllIllIllllIIIllI) {
        if (llllllllllllllllllIllIllllIIIlII == 1) {
            this.container.exit();
        }
    }
    
    public static void main(final String[] llllllllllllllllllIllIllllIIllII) {
        try {
            final AppGameContainer llllllllllllllllllIllIllllIIlllI = new AppGameContainer(new PackedSheetTest());
            llllllllllllllllllIllIllllIIlllI.setDisplayMode(800, 600, false);
            llllllllllllllllllIllIllllIIlllI.start();
        }
        catch (SlickException llllllllllllllllllIllIllllIIllIl) {
            llllllllllllllllllIllIllllIIllIl.printStackTrace();
        }
    }
    
    @Override
    public void init(final GameContainer llllllllllllllllllIllIlllllIIlII) throws SlickException {
        this.container = llllllllllllllllllIllIlllllIIlII;
        this.sheet = new PackedSpriteSheet("testdata/testpack.def", 2);
        this.rocket = this.sheet.getSprite("rocket");
        final SpriteSheet llllllllllllllllllIllIlllllIIIll = this.sheet.getSpriteSheet("runner");
        this.runner = new Animation();
        for (int llllllllllllllllllIllIlllllIIllI = 0; llllllllllllllllllIllIlllllIIllI < 2; ++llllllllllllllllllIllIlllllIIllI) {
            for (int llllllllllllllllllIllIlllllIIlll = 0; llllllllllllllllllIllIlllllIIlll < 6; ++llllllllllllllllllIllIlllllIIlll) {
                this.runner.addFrame(llllllllllllllllllIllIlllllIIIll.getSprite(llllllllllllllllllIllIlllllIIlll, llllllllllllllllllIllIlllllIIllI), 50);
            }
        }
    }
    
    public PackedSheetTest() {
        super("Packed Sprite Sheet Test");
        this.r = -500.0f;
    }
    
    @Override
    public void update(final GameContainer llllllllllllllllllIllIllllIlIIll, final int llllllllllllllllllIllIllllIlIIII) {
        this.r += llllllllllllllllllIllIllllIlIIII * 0.4f;
        if (this.r > 900.0f) {
            this.r = -500.0f;
        }
        this.ang += llllllllllllllllllIllIllllIlIIII * 0.1f;
    }
    
    @Override
    public void render(final GameContainer llllllllllllllllllIllIllllIllIlI, final Graphics llllllllllllllllllIllIllllIllIIl) {
        this.rocket.draw((float)(int)this.r, 100.0f);
        this.runner.draw(250.0f, 250.0f);
        llllllllllllllllllIllIllllIllIIl.scale(1.2f, 1.2f);
        this.runner.draw(250.0f, 250.0f);
        llllllllllllllllllIllIllllIllIIl.scale(1.2f, 1.2f);
        this.runner.draw(250.0f, 250.0f);
        llllllllllllllllllIllIllllIllIIl.resetTransform();
        llllllllllllllllllIllIllllIllIIl.rotate(670.0f, 470.0f, this.ang);
        this.sheet.getSprite("floppy").draw(600.0f, 400.0f);
    }
}
