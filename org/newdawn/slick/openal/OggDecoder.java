package org.newdawn.slick.openal;

import java.io.*;
import java.nio.*;

public class OggDecoder
{
    private /* synthetic */ byte[] convbuffer;
    private /* synthetic */ int convsize;
    
    public OggDecoder() {
        this.convsize = 16384;
        this.convbuffer = new byte[this.convsize];
    }
    
    public OggData getData(final InputStream lllllIlllIIIIII) throws IOException {
        if (lllllIlllIIIIII == null) {
            throw new IOException("Failed to read OGG, source does not exist?");
        }
        final ByteArrayOutputStream lllllIllIllllll = new ByteArrayOutputStream();
        final OggInputStream lllllIllIlllllI = new OggInputStream(lllllIlllIIIIII);
        final boolean lllllIllIllllIl = false;
        while (!lllllIllIlllllI.atEnd()) {
            lllllIllIllllll.write(lllllIllIlllllI.read());
        }
        final OggData lllllIllIllllII = new OggData();
        lllllIllIllllII.channels = lllllIllIlllllI.getChannels();
        lllllIllIllllII.rate = lllllIllIlllllI.getRate();
        final byte[] lllllIllIlllIll = lllllIllIllllll.toByteArray();
        lllllIllIllllII.data = ByteBuffer.allocateDirect(lllllIllIlllIll.length);
        lllllIllIllllII.data.put(lllllIllIlllIll);
        lllllIllIllllII.data.rewind();
        return lllllIllIllllII;
    }
}
