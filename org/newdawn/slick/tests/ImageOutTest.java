package org.newdawn.slick.tests;

import org.newdawn.slick.*;
import org.newdawn.slick.particles.*;
import java.io.*;
import org.newdawn.slick.imageout.*;

public class ImageOutTest extends BasicGame
{
    private /* synthetic */ ParticleSystem fire;
    private /* synthetic */ GameContainer container;
    private /* synthetic */ Graphics g;
    private /* synthetic */ Image copy;
    private /* synthetic */ String message;
    
    public static void main(final String[] llllllllllllllllllIIIIIlllIIlllI) {
        try {
            final AppGameContainer llllllllllllllllllIIIIIlllIlIIII = new AppGameContainer(new ImageOutTest());
            llllllllllllllllllIIIIIlllIlIIII.setDisplayMode(800, 600, false);
            llllllllllllllllllIIIIIlllIlIIII.start();
        }
        catch (SlickException llllllllllllllllllIIIIIlllIIllll) {
            llllllllllllllllllIIIIIlllIIllll.printStackTrace();
        }
    }
    
    @Override
    public void render(final GameContainer llllllllllllllllllIIIIIlllllIlll, final Graphics llllllllllllllllllIIIIIlllllIIIl) {
        llllllllllllllllllIIIIIlllllIIIl.drawString("T - TGA Snapshot", 10.0f, 50.0f);
        llllllllllllllllllIIIIIlllllIIIl.drawString("J - JPG Snapshot", 10.0f, 70.0f);
        llllllllllllllllllIIIIIlllllIIIl.drawString("P - PNG Snapshot", 10.0f, 90.0f);
        llllllllllllllllllIIIIIlllllIIIl.setDrawMode(Graphics.MODE_ADD);
        llllllllllllllllllIIIIIlllllIIIl.drawImage(this.copy, 200.0f, 300.0f);
        llllllllllllllllllIIIIIlllllIIIl.setDrawMode(Graphics.MODE_NORMAL);
        llllllllllllllllllIIIIIlllllIIIl.drawString(this.message, 10.0f, 400.0f);
        llllllllllllllllllIIIIIlllllIIIl.drawRect(200.0f, 0.0f, 400.0f, 300.0f);
        llllllllllllllllllIIIIIlllllIIIl.translate(400.0f, 250.0f);
        this.fire.render();
        this.g = llllllllllllllllllIIIIIlllllIIIl;
    }
    
    @Override
    public void init(final GameContainer llllllllllllllllllIIIIlIIIIIlIIl) throws SlickException {
        this.container = llllllllllllllllllIIIIlIIIIIlIIl;
        try {
            this.fire = ParticleIO.loadConfiguredSystem("testdata/system.xml");
        }
        catch (IOException llllllllllllllllllIIIIlIIIIIllll) {
            throw new SlickException("Failed to load particle systems", llllllllllllllllllIIIIlIIIIIllll);
        }
        this.copy = new Image(400, 300);
        final String[] llllllllllllllllllIIIIlIIIIIlIll = ImageOut.getSupportedFormats();
        this.message = "Formats supported: ";
        for (int llllllllllllllllllIIIIlIIIIIlllI = 0; llllllllllllllllllIIIIlIIIIIlllI < llllllllllllllllllIIIIlIIIIIlIll.length; ++llllllllllllllllllIIIIlIIIIIlllI) {
            this.message = String.valueOf(new StringBuilder().append(this.message).append(llllllllllllllllllIIIIlIIIIIlIll[llllllllllllllllllIIIIlIIIIIlllI]));
            if (llllllllllllllllllIIIIlIIIIIlllI < llllllllllllllllllIIIIlIIIIIlIll.length - 1) {
                this.message = String.valueOf(new StringBuilder().append(this.message).append(","));
            }
        }
    }
    
    public ImageOutTest() {
        super("Image Out Test");
    }
    
    private void writeTo(final String llllllllllllllllllIIIIIllllIIllI) throws SlickException {
        this.g.copyArea(this.copy, 200, 0);
        ImageOut.write(this.copy, llllllllllllllllllIIIIIllllIIllI);
        this.message = String.valueOf(new StringBuilder().append("Written ").append(llllllllllllllllllIIIIIllllIIllI));
    }
    
    @Override
    public void update(final GameContainer llllllllllllllllllIIIIIlllIlIlll, final int llllllllllllllllllIIIIIlllIllIIl) throws SlickException {
        this.fire.update(llllllllllllllllllIIIIIlllIllIIl);
        if (llllllllllllllllllIIIIIlllIlIlll.getInput().isKeyPressed(25)) {
            this.writeTo("ImageOutTest.png");
        }
        if (llllllllllllllllllIIIIIlllIlIlll.getInput().isKeyPressed(36)) {
            this.writeTo("ImageOutTest.jpg");
        }
        if (llllllllllllllllllIIIIIlllIlIlll.getInput().isKeyPressed(20)) {
            this.writeTo("ImageOutTest.tga");
        }
    }
    
    @Override
    public void keyPressed(final int llllllllllllllllllIIIIIlllIIIlIl, final char llllllllllllllllllIIIIIlllIIIlII) {
        if (llllllllllllllllllIIIIIlllIIIlIl == 1) {
            this.container.exit();
        }
    }
}
