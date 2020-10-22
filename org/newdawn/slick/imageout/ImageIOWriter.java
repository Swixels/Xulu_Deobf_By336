package org.newdawn.slick.imageout;

import java.nio.*;
import java.awt.color.*;
import java.awt.*;
import java.util.*;
import javax.imageio.*;
import org.newdawn.slick.*;
import java.awt.image.*;
import java.io.*;

public class ImageIOWriter implements ImageWriter
{
    @Override
    public void saveImage(final Image lIlIlIIlIIIlIll, final String lIlIlIIlIIIlIlI, final OutputStream lIlIlIIIllllllI, final boolean lIlIlIIIlllllIl) throws IOException {
        int lIlIlIIlIIIIlll = 4 * lIlIlIIlIIIlIll.getWidth() * lIlIlIIlIIIlIll.getHeight();
        if (!lIlIlIIIlllllIl) {
            lIlIlIIlIIIIlll = 3 * lIlIlIIlIIIlIll.getWidth() * lIlIlIIlIIIlIll.getHeight();
        }
        final ByteBuffer lIlIlIIlIIIIllI = ByteBuffer.allocate(lIlIlIIlIIIIlll);
        for (int lIlIlIIlIIlIIIl = 0; lIlIlIIlIIlIIIl < lIlIlIIlIIIlIll.getHeight(); ++lIlIlIIlIIlIIIl) {
            for (int lIlIlIIlIIlIIlI = 0; lIlIlIIlIIlIIlI < lIlIlIIlIIIlIll.getWidth(); ++lIlIlIIlIIlIIlI) {
                final Color lIlIlIIlIIlIIll = lIlIlIIlIIIlIll.getColor(lIlIlIIlIIlIIlI, lIlIlIIlIIlIIIl);
                lIlIlIIlIIIIllI.put((byte)(lIlIlIIlIIlIIll.r * 255.0f));
                lIlIlIIlIIIIllI.put((byte)(lIlIlIIlIIlIIll.g * 255.0f));
                lIlIlIIlIIIIllI.put((byte)(lIlIlIIlIIlIIll.b * 255.0f));
                if (lIlIlIIIlllllIl) {
                    lIlIlIIlIIIIllI.put((byte)(lIlIlIIlIIlIIll.a * 255.0f));
                }
            }
        }
        final DataBufferByte lIlIlIIlIIIIlIl = new DataBufferByte(lIlIlIIlIIIIllI.array(), lIlIlIIlIIIIlll);
        PixelInterleavedSampleModel lIlIlIIlIIIIlII = null;
        ColorModel lIlIlIIlIIIIIll = null;
        if (lIlIlIIIlllllIl) {
            final int[] lIlIlIIlIIlIIII = { 0, 1, 2, 3 };
            final PixelInterleavedSampleModel lIlIlIIlIIIllll = new PixelInterleavedSampleModel(0, lIlIlIIlIIIlIll.getWidth(), lIlIlIIlIIIlIll.getHeight(), 4, 4 * lIlIlIIlIIIlIll.getWidth(), lIlIlIIlIIlIIII);
            final ColorModel lIlIlIIlIIIlllI = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 8 }, true, false, 3, 0);
        }
        else {
            final int[] lIlIlIIlIIIllIl = { 0, 1, 2 };
            lIlIlIIlIIIIlII = new PixelInterleavedSampleModel(0, lIlIlIIlIIIlIll.getWidth(), lIlIlIIlIIIlIll.getHeight(), 3, 3 * lIlIlIIlIIIlIll.getWidth(), lIlIlIIlIIIllIl);
            lIlIlIIlIIIIIll = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 0 }, false, false, 1, 0);
        }
        final WritableRaster lIlIlIIlIIIIIlI = Raster.createWritableRaster(lIlIlIIlIIIIlII, lIlIlIIlIIIIlIl, new Point(0, 0));
        final BufferedImage lIlIlIIlIIIIIIl = new BufferedImage(lIlIlIIlIIIIIll, lIlIlIIlIIIIIlI, false, null);
        ImageIO.write(lIlIlIIlIIIIIIl, lIlIlIIlIIIlIlI, lIlIlIIIllllllI);
    }
}
