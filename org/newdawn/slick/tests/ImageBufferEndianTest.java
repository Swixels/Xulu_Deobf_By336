package org.newdawn.slick.tests;

import java.nio.*;
import org.newdawn.slick.*;

public class ImageBufferEndianTest extends BasicGame
{
    private /* synthetic */ Image fromRed;
    private /* synthetic */ ImageBuffer redImageBuffer;
    private /* synthetic */ ImageBuffer blueImageBuffer;
    private /* synthetic */ String endian;
    private /* synthetic */ Image fromBlue;
    
    public ImageBufferEndianTest() {
        super("ImageBuffer Endian Test");
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIllIlIIlIlIlIIl, final int lllllllllllllllllIllIlIIlIlIlIII) throws SlickException {
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIllIlIIllIIIlIl, final Graphics lllllllllllllllllIllIlIIllIIIIlI) throws SlickException {
        lllllllllllllllllIllIlIIllIIIIlI.setColor(Color.white);
        lllllllllllllllllIllIlIIllIIIIlI.drawString(String.valueOf(new StringBuilder().append("Endianness is ").append(this.endian)), 10.0f, 100.0f);
        lllllllllllllllllIllIlIIllIIIIlI.drawString("Image below should be red", 10.0f, 200.0f);
        lllllllllllllllllIllIlIIllIIIIlI.drawImage(this.fromRed, 10.0f, 220.0f);
        lllllllllllllllllIllIlIIllIIIIlI.drawString("Image below should be blue", 410.0f, 200.0f);
        lllllllllllllllllIllIlIIllIIIIlI.drawImage(this.fromBlue, 410.0f, 220.0f);
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIllIlIIlIllllll) throws SlickException {
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            this.endian = "Big endian";
        }
        else if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            this.endian = "Little endian";
        }
        else {
            this.endian = "no idea";
        }
        this.redImageBuffer = new ImageBuffer(100, 100);
        this.fillImageBufferWithColor(this.redImageBuffer, Color.red, 100, 100);
        this.blueImageBuffer = new ImageBuffer(100, 100);
        this.fillImageBufferWithColor(this.blueImageBuffer, Color.blue, 100, 100);
        this.fromRed = this.redImageBuffer.getImage();
        this.fromBlue = this.blueImageBuffer.getImage();
    }
    
    public static void main(final String[] lllllllllllllllllIllIlIIllIIlIlI) {
        try {
            final AppGameContainer lllllllllllllllllIllIlIIllIIllII = new AppGameContainer(new ImageBufferEndianTest());
            lllllllllllllllllIllIlIIllIIllII.setDisplayMode(800, 600, false);
            lllllllllllllllllIllIlIIllIIllII.start();
        }
        catch (SlickException lllllllllllllllllIllIlIIllIIlIll) {
            lllllllllllllllllIllIlIIllIIlIll.printStackTrace();
        }
    }
    
    private void fillImageBufferWithColor(final ImageBuffer lllllllllllllllllIllIlIIlIllIIII, final Color lllllllllllllllllIllIlIIlIllIIll, final int lllllllllllllllllIllIlIIlIlIlllI, final int lllllllllllllllllIllIlIIlIllIIIl) {
        for (int lllllllllllllllllIllIlIIlIllIllI = 0; lllllllllllllllllIllIlIIlIllIllI < lllllllllllllllllIllIlIIlIlIlllI; ++lllllllllllllllllIllIlIIlIllIllI) {
            for (int lllllllllllllllllIllIlIIlIllIlll = 0; lllllllllllllllllIllIlIIlIllIlll < lllllllllllllllllIllIlIIlIllIIIl; ++lllllllllllllllllIllIlIIlIllIlll) {
                lllllllllllllllllIllIlIIlIllIIII.setRGBA(lllllllllllllllllIllIlIIlIllIllI, lllllllllllllllllIllIlIIlIllIlll, lllllllllllllllllIllIlIIlIllIIll.getRed(), lllllllllllllllllIllIlIIlIllIIll.getGreen(), lllllllllllllllllIllIlIIlIllIIll.getBlue(), lllllllllllllllllIllIlIIlIllIIll.getAlpha());
            }
        }
    }
}
