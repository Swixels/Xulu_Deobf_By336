package org.newdawn.slick.tests;

import org.lwjgl.opengl.*;
import org.newdawn.slick.*;

public class DistanceFieldTest extends BasicGame
{
    private /* synthetic */ AngelCodeFont font;
    
    public DistanceFieldTest() {
        super("DistanceMapTest Test");
    }
    
    public static void main(final String[] lllllllllllllllllIIIIIlllIlIlIIl) {
        try {
            final AppGameContainer lllllllllllllllllIIIIIlllIlIlIll = new AppGameContainer(new DistanceFieldTest());
            lllllllllllllllllIIIIIlllIlIlIll.setDisplayMode(800, 600, false);
            lllllllllllllllllIIIIIlllIlIlIll.start();
        }
        catch (SlickException lllllllllllllllllIIIIIlllIlIlIlI) {
            lllllllllllllllllIIIIIlllIlIlIlI.printStackTrace();
        }
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIIIIIlllIlllIll, final int lllllllllllllllllIIIIIlllIlllIlI) throws SlickException {
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIIIIIlllIllIlIl, final Graphics lllllllllllllllllIIIIIlllIllIlII) throws SlickException {
        final String lllllllllllllllllIIIIIlllIllIIll = "abc";
        this.font.drawString(610.0f, 100.0f, lllllllllllllllllIIIIIlllIllIIll);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glAlphaFunc(518, 0.5f);
        this.font.drawString(610.0f, 150.0f, lllllllllllllllllIIIIIlllIllIIll);
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        lllllllllllllllllIIIIIlllIllIlII.translate(-50.0f, -130.0f);
        lllllllllllllllllIIIIIlllIllIlII.scale(10.0f, 10.0f);
        this.font.drawString(0.0f, 0.0f, lllllllllllllllllIIIIIlllIllIIll);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glAlphaFunc(518, 0.5f);
        this.font.drawString(0.0f, 26.0f, lllllllllllllllllIIIIIlllIllIIll);
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        lllllllllllllllllIIIIIlllIllIlII.resetTransform();
        lllllllllllllllllIIIIIlllIllIlII.setColor(Color.lightGray);
        lllllllllllllllllIIIIIlllIllIlII.drawString("Original Size on Sheet", 620.0f, 210.0f);
        lllllllllllllllllIIIIIlllIllIlII.drawString("10x Scale Up", 40.0f, 575.0f);
        lllllllllllllllllIIIIIlllIllIlII.setColor(Color.darkGray);
        lllllllllllllllllIIIIIlllIllIlII.drawRect(40.0f, 40.0f, 560.0f, 530.0f);
        lllllllllllllllllIIIIIlllIllIlII.drawRect(610.0f, 105.0f, 150.0f, 100.0f);
        lllllllllllllllllIIIIIlllIllIlII.setColor(Color.white);
        lllllllllllllllllIIIIIlllIllIlII.drawString("512x512 Font Sheet", 620.0f, 300.0f);
        lllllllllllllllllIIIIIlllIllIlII.drawString("NEHE Charset", 620.0f, 320.0f);
        lllllllllllllllllIIIIIlllIllIlII.drawString("4096x4096 (8x) Source Image", 620.0f, 340.0f);
        lllllllllllllllllIIIIIlllIllIlII.drawString("ScanSize = 20", 620.0f, 360.0f);
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIIIIIlllIllllll) throws SlickException {
        this.font = new AngelCodeFont("testdata/distance.fnt", "testdata/distance-dis.png");
        lllllllllllllllllIIIIIlllIllllll.getGraphics().setBackground(Color.black);
    }
    
    @Override
    public void keyPressed(final int lllllllllllllllllIIIIIlllIlIlllI, final char lllllllllllllllllIIIIIlllIlIllIl) {
    }
}
