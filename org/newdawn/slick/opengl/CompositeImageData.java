package org.newdawn.slick.opengl;

import java.util.*;
import java.nio.*;
import org.newdawn.slick.util.*;
import java.io.*;

public class CompositeImageData implements LoadableImageData
{
    private /* synthetic */ ArrayList sources;
    private /* synthetic */ LoadableImageData picked;
    
    @Override
    public ByteBuffer getImageBufferData() {
        this.checkPicked();
        return this.picked.getImageBufferData();
    }
    
    @Override
    public ByteBuffer loadImage(final InputStream llIIllIIIlIlIlI, final boolean llIIllIIIlIlIIl, final boolean llIIllIIIlIlIII, final int[] llIIllIIIlIIlll) throws IOException {
        final CompositeIOException llIIllIIIlIlllI = new CompositeIOException();
        ByteBuffer llIIllIIIlIllIl = null;
        final BufferedInputStream llIIllIIIlIllII = new BufferedInputStream(llIIllIIIlIlIlI, llIIllIIIlIlIlI.available());
        llIIllIIIlIllII.mark(llIIllIIIlIlIlI.available());
        int llIIllIIIllIlII = 0;
        while (llIIllIIIllIlII < this.sources.size()) {
            llIIllIIIlIllII.reset();
            try {
                final LoadableImageData llIIllIIIllIllI = this.sources.get(llIIllIIIllIlII);
                llIIllIIIlIllIl = llIIllIIIllIllI.loadImage(llIIllIIIlIllII, llIIllIIIlIlIIl, llIIllIIIlIlIII, llIIllIIIlIIlll);
                this.picked = llIIllIIIllIllI;
            }
            catch (Exception llIIllIIIllIlIl) {
                Log.warn(String.valueOf(new StringBuilder().append(this.sources.get(llIIllIIIllIlII).getClass()).append(" failed to read the data")), llIIllIIIllIlIl);
                llIIllIIIlIlllI.addException(llIIllIIIllIlIl);
                ++llIIllIIIllIlII;
                continue;
            }
            break;
        }
        if (this.picked == null) {
            throw llIIllIIIlIlllI;
        }
        return llIIllIIIlIllIl;
    }
    
    private void checkPicked() {
        if (this.picked == null) {
            throw new RuntimeException("Attempt to make use of uninitialised or invalid composite image data");
        }
    }
    
    public void add(final LoadableImageData llIIllIIlIlIIll) {
        this.sources.add(llIIllIIlIlIIll);
    }
    
    @Override
    public int getDepth() {
        this.checkPicked();
        return this.picked.getDepth();
    }
    
    @Override
    public void configureEdging(final boolean llIIllIIIIIIlIl) {
        for (int llIIllIIIIIlIIl = 0; llIIllIIIIIlIIl < this.sources.size(); ++llIIllIIIIIlIIl) {
            this.sources.get(llIIllIIIIIlIIl).configureEdging(llIIllIIIIIIlIl);
        }
    }
    
    public CompositeImageData() {
        this.sources = new ArrayList();
    }
    
    @Override
    public int getTexWidth() {
        this.checkPicked();
        return this.picked.getTexWidth();
    }
    
    @Override
    public ByteBuffer loadImage(final InputStream llIIllIIlIIIIll, final boolean llIIllIIlIIIllI, final int[] llIIllIIlIIIIIl) throws IOException {
        return this.loadImage(llIIllIIlIIIIll, llIIllIIlIIIllI, false, llIIllIIlIIIIIl);
    }
    
    @Override
    public int getTexHeight() {
        this.checkPicked();
        return this.picked.getTexHeight();
    }
    
    @Override
    public int getWidth() {
        this.checkPicked();
        return this.picked.getWidth();
    }
    
    @Override
    public ByteBuffer loadImage(final InputStream llIIllIIlIIllIl) throws IOException {
        return this.loadImage(llIIllIIlIIllIl, false, null);
    }
    
    @Override
    public int getHeight() {
        this.checkPicked();
        return this.picked.getHeight();
    }
}
