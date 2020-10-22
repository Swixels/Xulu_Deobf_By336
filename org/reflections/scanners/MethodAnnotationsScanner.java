package org.reflections.scanners;

import org.reflections.*;

public class MethodAnnotationsScanner extends AbstractScanner
{
    @Override
    public void scan(final Object lllllllllllllllllIlllllIlllIIIlI, final Store lllllllllllllllllIlllllIlllIIIIl) {
        for (final Object lllllllllllllllllIlllllIlllIIlII : this.getMetadataAdapter().getMethods(lllllllllllllllllIlllllIlllIIIlI)) {
            for (final String lllllllllllllllllIlllllIlllIIlIl : this.getMetadataAdapter().getMethodAnnotationNames(lllllllllllllllllIlllllIlllIIlII)) {
                if (this.acceptResult(lllllllllllllllllIlllllIlllIIlIl)) {
                    this.put(lllllllllllllllllIlllllIlllIIIIl, lllllllllllllllllIlllllIlllIIlIl, this.getMetadataAdapter().getMethodFullKey(lllllllllllllllllIlllllIlllIIIlI, lllllllllllllllllIlllllIlllIIlII));
                }
            }
        }
    }
}
