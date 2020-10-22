package org.reflections.scanners;

import org.reflections.vfs.*;
import org.reflections.*;

public class ResourcesScanner extends AbstractScanner
{
    @Override
    public boolean acceptsInput(final String lllllllllllllllllIIIllllIIlllIlI) {
        return !lllllllllllllllllIIIllllIIlllIlI.endsWith(".class");
    }
    
    @Override
    public Object scan(final Vfs.File lllllllllllllllllIIIllllIIlIllll, final Object lllllllllllllllllIIIllllIIlIlllI, final Store lllllllllllllllllIIIllllIIllIIIl) {
        this.put(lllllllllllllllllIIIllllIIllIIIl, lllllllllllllllllIIIllllIIlIllll.getName(), lllllllllllllllllIIIllllIIlIllll.getRelativePath());
        return lllllllllllllllllIIIllllIIlIlllI;
    }
    
    @Override
    public void scan(final Object lllllllllllllllllIIIllllIIlIlIll, final Store lllllllllllllllllIIIllllIIlIlIlI) {
        throw new UnsupportedOperationException();
    }
}
