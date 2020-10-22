package org.reflections.scanners;

import org.reflections.*;
import java.util.*;

public class FieldAnnotationsScanner extends AbstractScanner
{
    @Override
    public void scan(final Object llllllllllllllllIllIIIIIlIIIIllI, final Store llllllllllllllllIllIIIIIlIIIlIlI) {
        final String llllllllllllllllIllIIIIIlIIIlIIl = this.getMetadataAdapter().getClassName(llllllllllllllllIllIIIIIlIIIIllI);
        final List<Object> llllllllllllllllIllIIIIIlIIIlIII = this.getMetadataAdapter().getFields(llllllllllllllllIllIIIIIlIIIIllI);
        for (final Object llllllllllllllllIllIIIIIlIIIllIl : llllllllllllllllIllIIIIIlIIIlIII) {
            final List<String> llllllllllllllllIllIIIIIlIIIlllI = this.getMetadataAdapter().getFieldAnnotationNames(llllllllllllllllIllIIIIIlIIIllIl);
            for (final String llllllllllllllllIllIIIIIlIIIllll : llllllllllllllllIllIIIIIlIIIlllI) {
                if (this.acceptResult(llllllllllllllllIllIIIIIlIIIllll)) {
                    final String llllllllllllllllIllIIIIIlIIlIIII = this.getMetadataAdapter().getFieldName(llllllllllllllllIllIIIIIlIIIllIl);
                    this.put(llllllllllllllllIllIIIIIlIIIlIlI, llllllllllllllllIllIIIIIlIIIllll, String.format("%s.%s", llllllllllllllllIllIIIIIlIIIlIIl, llllllllllllllllIllIIIIIlIIlIIII));
                }
            }
        }
    }
}
