package org.reflections.scanners;

import org.reflections.*;
import java.lang.annotation.*;

public class TypeAnnotationsScanner extends AbstractScanner
{
    @Override
    public void scan(final Object lllllllllllllllllIlIllIllIIIlIll, final Store lllllllllllllllllIlIllIllIIIlllI) {
        final String lllllllllllllllllIlIllIllIIIllIl = this.getMetadataAdapter().getClassName(lllllllllllllllllIlIllIllIIIlIll);
        for (final String lllllllllllllllllIlIllIllIIlIIIl : this.getMetadataAdapter().getClassAnnotationNames(lllllllllllllllllIlIllIllIIIlIll)) {
            if (this.acceptResult(lllllllllllllllllIlIllIllIIlIIIl) || lllllllllllllllllIlIllIllIIlIIIl.equals(Inherited.class.getName())) {
                this.put(lllllllllllllllllIlIllIllIIIlllI, lllllllllllllllllIlIllIllIIlIIIl, lllllllllllllllllIlIllIllIIIllIl);
            }
        }
    }
}
