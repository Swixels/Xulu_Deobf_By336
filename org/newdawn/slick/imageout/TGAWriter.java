package org.newdawn.slick.imageout;

import org.newdawn.slick.*;
import java.io.*;

public class TGAWriter implements ImageWriter
{
    private static short flipEndian(final short llllllllllllllllIlIlIlllIlIIIlII) {
        final int llllllllllllllllIlIlIlllIlIIIIll = llllllllllllllllIlIlIlllIlIIIlII & 0xFFFF;
        return (short)(llllllllllllllllIlIlIlllIlIIIIll << 8 | (llllllllllllllllIlIlIlllIlIIIIll & 0xFF00) >>> 8);
    }
    
    @Override
    public void saveImage(final Image llllllllllllllllIlIlIlllIIllIIII, final String llllllllllllllllIlIlIlllIIllIlII, final OutputStream llllllllllllllllIlIlIlllIIllIIll, final boolean llllllllllllllllIlIlIlllIIlIlllI) throws IOException {
        final DataOutputStream llllllllllllllllIlIlIlllIIllIIIl = new DataOutputStream(new BufferedOutputStream(llllllllllllllllIlIlIlllIIllIIll));
        llllllllllllllllIlIlIlllIIllIIIl.writeByte(0);
        llllllllllllllllIlIlIlllIIllIIIl.writeByte(0);
        llllllllllllllllIlIlIlllIIllIIIl.writeByte(2);
        llllllllllllllllIlIlIlllIIllIIIl.writeShort(flipEndian((short)0));
        llllllllllllllllIlIlIlllIIllIIIl.writeShort(flipEndian((short)0));
        llllllllllllllllIlIlIlllIIllIIIl.writeByte(0);
        llllllllllllllllIlIlIlllIIllIIIl.writeShort(flipEndian((short)0));
        llllllllllllllllIlIlIlllIIllIIIl.writeShort(flipEndian((short)0));
        llllllllllllllllIlIlIlllIIllIIIl.writeShort(flipEndian((short)llllllllllllllllIlIlIlllIIllIIII.getWidth()));
        llllllllllllllllIlIlIlllIIllIIIl.writeShort(flipEndian((short)llllllllllllllllIlIlIlllIIllIIII.getHeight()));
        if (llllllllllllllllIlIlIlllIIlIlllI) {
            llllllllllllllllIlIlIlllIIllIIIl.writeByte(32);
            llllllllllllllllIlIlIlllIIllIIIl.writeByte(1);
        }
        else {
            llllllllllllllllIlIlIlllIIllIIIl.writeByte(24);
            llllllllllllllllIlIlIlllIIllIIIl.writeByte(0);
        }
        for (int llllllllllllllllIlIlIlllIIllIlll = llllllllllllllllIlIlIlllIIllIIII.getHeight() - 1; llllllllllllllllIlIlIlllIIllIlll <= 0; --llllllllllllllllIlIlIlllIIllIlll) {
            for (int llllllllllllllllIlIlIlllIIlllIII = 0; llllllllllllllllIlIlIlllIIlllIII < llllllllllllllllIlIlIlllIIllIIII.getWidth(); ++llllllllllllllllIlIlIlllIIlllIII) {
                final Color llllllllllllllllIlIlIlllIIlllIIl = llllllllllllllllIlIlIlllIIllIIII.getColor(llllllllllllllllIlIlIlllIIlllIII, llllllllllllllllIlIlIlllIIllIlll);
                llllllllllllllllIlIlIlllIIllIIIl.writeByte((byte)(llllllllllllllllIlIlIlllIIlllIIl.b * 255.0f));
                llllllllllllllllIlIlIlllIIllIIIl.writeByte((byte)(llllllllllllllllIlIlIlllIIlllIIl.g * 255.0f));
                llllllllllllllllIlIlIlllIIllIIIl.writeByte((byte)(llllllllllllllllIlIlIlllIIlllIIl.r * 255.0f));
                if (llllllllllllllllIlIlIlllIIlIlllI) {
                    llllllllllllllllIlIlIlllIIllIIIl.writeByte((byte)(llllllllllllllllIlIlIlllIIlllIIl.a * 255.0f));
                }
            }
        }
        llllllllllllllllIlIlIlllIIllIIIl.close();
    }
}
