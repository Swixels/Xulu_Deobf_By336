package org.newdawn.slick.tests;

import org.newdawn.slick.opengl.pbuffer.*;
import org.newdawn.slick.*;

public class ImageGraphicsTest extends BasicGame
{
    private /* synthetic */ Graphics gTarget;
    private /* synthetic */ String using;
    private /* synthetic */ Image testImage;
    private /* synthetic */ Image cut;
    private /* synthetic */ float ang;
    private /* synthetic */ Image preloaded;
    private /* synthetic */ Font testFont;
    private /* synthetic */ Image target;
    private /* synthetic */ Graphics offscreenPreload;
    
    @Override
    public void render(final GameContainer lllllllllllllllllllIlIIIlIIIIIlI, final Graphics lllllllllllllllllllIlIIIIllllllI) throws SlickException {
        this.gTarget.setBackground(new Color(0, 0, 0, 0));
        this.gTarget.clear();
        this.gTarget.rotate(200.0f, 160.0f, this.ang);
        this.gTarget.setFont(this.testFont);
        this.gTarget.fillRect(10.0f, 10.0f, 50.0f, 50.0f);
        this.gTarget.drawString("HELLO WORLD", 10.0f, 10.0f);
        this.gTarget.drawImage(this.testImage, 100.0f, 150.0f);
        this.gTarget.drawImage(this.testImage, 100.0f, 50.0f);
        this.gTarget.drawImage(this.testImage, 50.0f, 75.0f);
        this.gTarget.flush();
        lllllllllllllllllllIlIIIIllllllI.setColor(Color.red);
        lllllllllllllllllllIlIIIIllllllI.fillRect(250.0f, 50.0f, 200.0f, 200.0f);
        this.target.draw(300.0f, 100.0f);
        this.target.draw(300.0f, 410.0f, 200.0f, 150.0f);
        this.target.draw(505.0f, 410.0f, 100.0f, 75.0f);
        lllllllllllllllllllIlIIIIllllllI.setColor(Color.white);
        lllllllllllllllllllIlIIIIllllllI.drawString("Testing On Offscreen Buffer", 300.0f, 80.0f);
        lllllllllllllllllllIlIIIIllllllI.setColor(Color.green);
        lllllllllllllllllllIlIIIIllllllI.drawRect(300.0f, 100.0f, (float)this.target.getWidth(), (float)this.target.getHeight());
        lllllllllllllllllllIlIIIIllllllI.drawRect(300.0f, 410.0f, (float)(this.target.getWidth() / 2), (float)(this.target.getHeight() / 2));
        lllllllllllllllllllIlIIIIllllllI.drawRect(505.0f, 410.0f, (float)(this.target.getWidth() / 4), (float)(this.target.getHeight() / 4));
        lllllllllllllllllllIlIIIIllllllI.setColor(Color.white);
        lllllllllllllllllllIlIIIIllllllI.drawString("Testing Font On Back Buffer", 10.0f, 100.0f);
        lllllllllllllllllllIlIIIIllllllI.drawString(String.valueOf(new StringBuilder().append("Using: ").append(this.using)), 10.0f, 580.0f);
        lllllllllllllllllllIlIIIIllllllI.setColor(Color.red);
        lllllllllllllllllllIlIIIIllllllI.fillRect(10.0f, 120.0f, 200.0f, 5.0f);
        final int lllllllllllllllllllIlIIIlIIIIIII = (int)(60.0 + Math.sin(this.ang / 60.0f) * 50.0);
        lllllllllllllllllllIlIIIIllllllI.copyArea(this.cut, lllllllllllllllllllIlIIIlIIIIIII, 50);
        this.cut.draw(30.0f, 250.0f);
        lllllllllllllllllllIlIIIIllllllI.setColor(Color.white);
        lllllllllllllllllllIlIIIIllllllI.drawRect(30.0f, 250.0f, (float)this.cut.getWidth(), (float)this.cut.getHeight());
        lllllllllllllllllllIlIIIIllllllI.setColor(Color.gray);
        lllllllllllllllllllIlIIIIllllllI.drawRect((float)lllllllllllllllllllIlIIIlIIIIIII, 50.0f, (float)this.cut.getWidth(), (float)this.cut.getHeight());
        this.preloaded.draw(2.0f, 400.0f);
        lllllllllllllllllllIlIIIIllllllI.setColor(Color.blue);
        lllllllllllllllllllIlIIIIllllllI.drawRect(2.0f, 400.0f, (float)this.preloaded.getWidth(), (float)this.preloaded.getHeight());
    }
    
    public ImageGraphicsTest() {
        super("Image Graphics Test");
        this.using = "none";
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllllIlIIIIllllIIl, final int lllllllllllllllllllIlIIIIlllIllI) {
        this.ang += lllllllllllllllllllIlIIIIlllIllI * 0.1f;
    }
    
    public static void main(final String[] lllllllllllllllllllIlIIIIlllIIlI) {
        try {
            GraphicsFactory.setUseFBO(false);
            final AppGameContainer lllllllllllllllllllIlIIIIlllIlII = new AppGameContainer(new ImageGraphicsTest());
            lllllllllllllllllllIlIIIIlllIlII.setDisplayMode(800, 600, false);
            lllllllllllllllllllIlIIIIlllIlII.start();
        }
        catch (SlickException lllllllllllllllllllIlIIIIlllIIll) {
            lllllllllllllllllllIlIIIIlllIIll.printStackTrace();
        }
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllllIlIIIlIIIlIII) throws SlickException {
        this.testImage = new Image("testdata/logo.png");
        this.preloaded = new Image("testdata/logo.png");
        this.testFont = new AngelCodeFont("testdata/hiero.fnt", "testdata/hiero.png");
        this.target = new Image(400, 300);
        this.cut = new Image(100, 100);
        this.gTarget = this.target.getGraphics();
        this.offscreenPreload = this.preloaded.getGraphics();
        this.offscreenPreload.drawString("Drawing over a loaded image", 5.0f, 15.0f);
        this.offscreenPreload.setLineWidth(5.0f);
        this.offscreenPreload.setAntiAlias(true);
        this.offscreenPreload.setColor(Color.blue.brighter());
        this.offscreenPreload.drawOval(200.0f, 30.0f, 50.0f, 50.0f);
        this.offscreenPreload.setColor(Color.white);
        this.offscreenPreload.drawRect(190.0f, 20.0f, 70.0f, 70.0f);
        this.offscreenPreload.flush();
        if (GraphicsFactory.usingFBO()) {
            this.using = "FBO (Frame Buffer Objects)";
        }
        else if (GraphicsFactory.usingPBuffer()) {
            this.using = "Pbuffer (Pixel Buffers)";
        }
        System.out.println(this.preloaded.getColor(50, 50));
    }
}
