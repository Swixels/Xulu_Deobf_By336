package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class BigImageTest extends BasicGame
{
    private /* synthetic */ Image imageX;
    private /* synthetic */ Image scaledSub;
    private /* synthetic */ Image original;
    private /* synthetic */ Image imageY;
    private /* synthetic */ float x;
    private /* synthetic */ float y;
    private /* synthetic */ SpriteSheet bigSheet;
    private /* synthetic */ float ang;
    private /* synthetic */ Image sub;
    private /* synthetic */ Image image;
    
    public BigImageTest() {
        super("Big Image Test");
        this.ang = 30.0f;
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIlllllllIlIIlII) throws SlickException {
        final BigImage bigImage = new BigImage("testdata/bigimage.tga", 2, 512);
        this.image = bigImage;
        this.original = bigImage;
        this.sub = this.image.getSubImage(210, 210, 200, 130);
        this.scaledSub = this.sub.getScaledCopy(2.0f);
        this.image = this.image.getScaledCopy(0.3f);
        this.imageX = this.image.getFlippedCopy(true, false);
        this.imageY = this.imageX.getFlippedCopy(true, true);
        this.bigSheet = new SpriteSheet(this.original, 16, 16);
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIlllllllIIlIIll, final Graphics lllllllllllllllllIlllllllIIlIIII) {
        this.original.draw(0.0f, 0.0f, new Color(1.0f, 1.0f, 1.0f, 0.4f));
        this.image.draw(this.x, this.y);
        this.imageX.draw(this.x + 400.0f, this.y);
        this.imageY.draw(this.x, this.y + 300.0f);
        this.scaledSub.draw(this.x + 300.0f, this.y + 300.0f);
        this.bigSheet.getSprite(7, 5).draw(50.0f, 10.0f);
        lllllllllllllllllIlllllllIIlIIII.setColor(Color.white);
        lllllllllllllllllIlllllllIIlIIII.drawRect(50.0f, 10.0f, 64.0f, 64.0f);
        lllllllllllllllllIlllllllIIlIIII.rotate(this.x + 400.0f, this.y + 165.0f, this.ang);
        lllllllllllllllllIlllllllIIlIIII.drawImage(this.sub, this.x + 300.0f, this.y + 100.0f);
    }
    
    public static void main(final String[] lllllllllllllllllIlllllllIIIllII) {
        try {
            final AppGameContainer lllllllllllllllllIlllllllIIIlllI = new AppGameContainer(new BigImageTest());
            lllllllllllllllllIlllllllIIIlllI.setDisplayMode(800, 600, false);
            lllllllllllllllllIlllllllIIIlllI.start();
        }
        catch (SlickException lllllllllllllllllIlllllllIIIllIl) {
            lllllllllllllllllIlllllllIIIllIl.printStackTrace();
        }
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIllllllIlllllIl, final int lllllllllllllllllIlllllllIIIIIII) throws SlickException {
        this.ang += lllllllllllllllllIlllllllIIIIIII * 0.1f;
        if (lllllllllllllllllIllllllIlllllIl.getInput().isKeyDown(203)) {
            this.x -= lllllllllllllllllIlllllllIIIIIII * 0.1f;
        }
        if (lllllllllllllllllIllllllIlllllIl.getInput().isKeyDown(205)) {
            this.x += lllllllllllllllllIlllllllIIIIIII * 0.1f;
        }
        if (lllllllllllllllllIllllllIlllllIl.getInput().isKeyDown(200)) {
            this.y -= lllllllllllllllllIlllllllIIIIIII * 0.1f;
        }
        if (lllllllllllllllllIllllllIlllllIl.getInput().isKeyDown(208)) {
            this.y += lllllllllllllllllIlllllllIIIIIII * 0.1f;
        }
    }
}
