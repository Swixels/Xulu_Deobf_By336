package org.newdawn.slick.tests;

import org.newdawn.slick.util.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;

public class GraphicsTest extends BasicGame
{
    private /* synthetic */ GameContainer container;
    private /* synthetic */ float ang;
    private /* synthetic */ boolean clip;
    private /* synthetic */ Image image;
    private /* synthetic */ Polygon poly;
    
    @Override
    public void keyPressed(final int lllllllllllllllllllIlllIlIlIlIlI, final char lllllllllllllllllllIlllIlIlIlIIl) {
        if (lllllllllllllllllllIlllIlIlIlIlI == 1) {
            System.exit(0);
        }
        if (lllllllllllllllllllIlllIlIlIlIlI == 57) {
            this.clip = !this.clip;
        }
    }
    
    public GraphicsTest() {
        super("Graphics Test");
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllllIlllIllIIIllI) throws SlickException {
        this.container = lllllllllllllllllllIlllIllIIIllI;
        this.image = new Image("testdata/logo.tga", true);
        final Image lllllllllllllllllllIlllIllIIIlIl = new Image("testdata/palette_tool.png");
        lllllllllllllllllllIlllIllIIIllI.setMouseCursor(lllllllllllllllllllIlllIllIIIlIl, 0, 0);
        lllllllllllllllllllIlllIllIIIllI.setIcons(new String[] { "testdata/icon.tga" });
        lllllllllllllllllllIlllIllIIIllI.setTargetFrameRate(100);
        this.poly = new Polygon();
        float lllllllllllllllllllIlllIllIIIlII = 100.0f;
        for (int lllllllllllllllllllIlllIllIIlIII = 0; lllllllllllllllllllIlllIllIIlIII < 360; lllllllllllllllllllIlllIllIIlIII += 30) {
            if (lllllllllllllllllllIlllIllIIIlII == 100.0f) {
                lllllllllllllllllllIlllIllIIIlII = 50.0f;
            }
            else {
                lllllllllllllllllllIlllIllIIIlII = 100.0f;
            }
            this.poly.addPoint((float)FastTrig.cos(Math.toRadians(lllllllllllllllllllIlllIllIIlIII)) * lllllllllllllllllllIlllIllIIIlII, (float)FastTrig.sin(Math.toRadians(lllllllllllllllllllIlllIllIIlIII)) * lllllllllllllllllllIlllIllIIIlII);
        }
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllllIlllIlIlllIIl, final Graphics lllllllllllllllllllIlllIlIllIllI) throws SlickException {
        lllllllllllllllllllIlllIlIllIllI.setColor(Color.white);
        lllllllllllllllllllIlllIlIllIllI.setAntiAlias(true);
        for (int lllllllllllllllllllIlllIlIlllIll = 0; lllllllllllllllllllIlllIlIlllIll < 360; lllllllllllllllllllIlllIlIlllIll += 10) {
            lllllllllllllllllllIlllIlIllIllI.drawLine(700.0f, 100.0f, (float)(int)(700.0 + Math.cos(Math.toRadians(lllllllllllllllllllIlllIlIlllIll)) * 100.0), (float)(int)(100.0 + Math.sin(Math.toRadians(lllllllllllllllllllIlllIlIlllIll)) * 100.0));
        }
        lllllllllllllllllllIlllIlIllIllI.setAntiAlias(false);
        lllllllllllllllllllIlllIlIllIllI.setColor(Color.yellow);
        lllllllllllllllllllIlllIlIllIllI.drawString("The Graphics Test!", 300.0f, 50.0f);
        lllllllllllllllllllIlllIlIllIllI.setColor(Color.white);
        lllllllllllllllllllIlllIlIllIllI.drawString("Space - Toggles clipping", 400.0f, 80.0f);
        lllllllllllllllllllIlllIlIllIllI.drawString("Frame rate capped to 100", 400.0f, 120.0f);
        if (this.clip) {
            lllllllllllllllllllIlllIlIllIllI.setColor(Color.gray);
            lllllllllllllllllllIlllIlIllIllI.drawRect(100.0f, 260.0f, 400.0f, 100.0f);
            lllllllllllllllllllIlllIlIllIllI.setClip(100, 260, 400, 100);
        }
        lllllllllllllllllllIlllIlIllIllI.setColor(Color.yellow);
        lllllllllllllllllllIlllIlIllIllI.translate(100.0f, 120.0f);
        lllllllllllllllllllIlllIlIllIllI.fill(this.poly);
        lllllllllllllllllllIlllIlIllIllI.setColor(Color.blue);
        lllllllllllllllllllIlllIlIllIllI.setLineWidth(3.0f);
        lllllllllllllllllllIlllIlIllIllI.draw(this.poly);
        lllllllllllllllllllIlllIlIllIllI.setLineWidth(1.0f);
        lllllllllllllllllllIlllIlIllIllI.translate(0.0f, 230.0f);
        lllllllllllllllllllIlllIlIllIllI.draw(this.poly);
        lllllllllllllllllllIlllIlIllIllI.resetTransform();
        lllllllllllllllllllIlllIlIllIllI.setColor(Color.magenta);
        lllllllllllllllllllIlllIlIllIllI.drawRoundRect(10.0f, 10.0f, 100.0f, 100.0f, 10);
        lllllllllllllllllllIlllIlIllIllI.fillRoundRect(10.0f, 210.0f, 100.0f, 100.0f, 10);
        lllllllllllllllllllIlllIlIllIllI.rotate(400.0f, 300.0f, this.ang);
        lllllllllllllllllllIlllIlIllIllI.setColor(Color.green);
        lllllllllllllllllllIlllIlIllIllI.drawRect(200.0f, 200.0f, 200.0f, 200.0f);
        lllllllllllllllllllIlllIlIllIllI.setColor(Color.blue);
        lllllllllllllllllllIlllIlIllIllI.fillRect(250.0f, 250.0f, 100.0f, 100.0f);
        lllllllllllllllllllIlllIlIllIllI.drawImage(this.image, 300.0f, 270.0f);
        lllllllllllllllllllIlllIlIllIllI.setColor(Color.red);
        lllllllllllllllllllIlllIlIllIllI.drawOval(100.0f, 100.0f, 200.0f, 200.0f);
        lllllllllllllllllllIlllIlIllIllI.setColor(Color.red.darker());
        lllllllllllllllllllIlllIlIllIllI.fillOval(300.0f, 300.0f, 150.0f, 100.0f);
        lllllllllllllllllllIlllIlIllIllI.setAntiAlias(true);
        lllllllllllllllllllIlllIlIllIllI.setColor(Color.white);
        lllllllllllllllllllIlllIlIllIllI.setLineWidth(5.0f);
        lllllllllllllllllllIlllIlIllIllI.drawOval(300.0f, 300.0f, 150.0f, 100.0f);
        lllllllllllllllllllIlllIlIllIllI.setAntiAlias(true);
        lllllllllllllllllllIlllIlIllIllI.resetTransform();
        if (this.clip) {
            lllllllllllllllllllIlllIlIllIllI.clearClip();
        }
    }
    
    public static void main(final String[] lllllllllllllllllllIlllIlIlIIIll) {
        try {
            final AppGameContainer lllllllllllllllllllIlllIlIlIIlIl = new AppGameContainer(new GraphicsTest());
            lllllllllllllllllllIlllIlIlIIlIl.setDisplayMode(800, 600, false);
            lllllllllllllllllllIlllIlIlIIlIl.start();
        }
        catch (SlickException lllllllllllllllllllIlllIlIlIIlII) {
            lllllllllllllllllllIlllIlIlIIlII.printStackTrace();
        }
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllllIlllIlIllIIIl, final int lllllllllllllllllllIlllIlIllIIII) {
        this.ang += lllllllllllllllllllIlllIlIllIIII * 0.1f;
    }
}
