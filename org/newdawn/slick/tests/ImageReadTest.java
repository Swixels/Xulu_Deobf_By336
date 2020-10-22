package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class ImageReadTest extends BasicGame
{
    private /* synthetic */ Graphics g;
    private /* synthetic */ Color[] read;
    private /* synthetic */ Image image;
    
    @Override
    public void update(final GameContainer llIIIlIlIllllIl, final int llIIIlIllIIIIIl) {
        final int llIIIlIllIIIIII = llIIIlIlIllllIl.getInput().getMouseX();
        final int llIIIlIlIllllll = llIIIlIlIllllIl.getInput().getMouseY();
        if (llIIIlIllIIIIII >= 100 && llIIIlIlIllllll >= 100 && llIIIlIllIIIIII < 200 && llIIIlIlIllllll < 200) {
            this.read[4] = this.image.getColor(llIIIlIllIIIIII - 100, llIIIlIlIllllll - 100);
        }
        else {
            this.read[4] = Color.black;
        }
        this.read[5] = this.g.getPixel(llIIIlIllIIIIII, llIIIlIlIllllll);
    }
    
    @Override
    public void render(final GameContainer llIIIlIllIIlIll, final Graphics llIIIlIllIIlIII) {
        this.g = llIIIlIllIIlIII;
        this.image.draw(100.0f, 100.0f);
        llIIIlIllIIlIII.setColor(Color.white);
        llIIIlIllIIlIII.drawString("Move mouse over test image", 200.0f, 20.0f);
        llIIIlIllIIlIII.setColor(this.read[0]);
        llIIIlIllIIlIII.drawString(this.read[0].toString(), 100.0f, 300.0f);
        llIIIlIllIIlIII.setColor(this.read[1]);
        llIIIlIllIIlIII.drawString(this.read[1].toString(), 150.0f, 320.0f);
        llIIIlIllIIlIII.setColor(this.read[2]);
        llIIIlIllIIlIII.drawString(this.read[2].toString(), 200.0f, 340.0f);
        llIIIlIllIIlIII.setColor(this.read[3]);
        llIIIlIllIIlIII.drawString(this.read[3].toString(), 250.0f, 360.0f);
        if (this.read[4] != null) {
            llIIIlIllIIlIII.setColor(this.read[4]);
            llIIIlIllIIlIII.drawString(String.valueOf(new StringBuilder().append("On image: ").append(this.read[4].toString())), 100.0f, 250.0f);
        }
        if (this.read[5] != null) {
            llIIIlIllIIlIII.setColor(Color.white);
            llIIIlIllIIlIII.drawString(String.valueOf(new StringBuilder().append("On screen: ").append(this.read[5].toString())), 100.0f, 270.0f);
        }
    }
    
    @Override
    public void init(final GameContainer llIIIlIllIlIIII) throws SlickException {
        this.image = new Image("testdata/testcard.png");
        this.read[0] = this.image.getColor(0, 0);
        this.read[1] = this.image.getColor(30, 40);
        this.read[2] = this.image.getColor(55, 70);
        this.read[3] = this.image.getColor(80, 90);
    }
    
    public static void main(final String[] llIIIlIlIllIlll) {
        try {
            final AppGameContainer llIIIlIlIlllIIl = new AppGameContainer(new ImageReadTest());
            llIIIlIlIlllIIl.setDisplayMode(800, 600, false);
            llIIIlIlIlllIIl.start();
        }
        catch (SlickException llIIIlIlIlllIII) {
            llIIIlIlIlllIII.printStackTrace();
        }
    }
    
    public ImageReadTest() {
        super("Image Read Test");
        this.read = new Color[6];
    }
}
