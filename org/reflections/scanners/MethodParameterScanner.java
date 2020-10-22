package org.reflections.scanners;

import org.reflections.*;
import org.reflections.adapters.*;
import java.util.*;

public class MethodParameterScanner extends AbstractScanner
{
    @Override
    public void scan(final Object lllllllllllllllllIIlIllIllIIIIIl, final Store lllllllllllllllllIIlIllIllIIIIII) {
        final MetadataAdapter lllllllllllllllllIIlIllIllIIIIll = this.getMetadataAdapter();
        for (final Object lllllllllllllllllIIlIllIllIIlIII : lllllllllllllllllIIlIllIllIIIIll.getMethods(lllllllllllllllllIIlIllIllIIIIIl)) {
            final String lllllllllllllllllIIlIllIllIIllIl = lllllllllllllllllIIlIllIllIIIIll.getParameterNames(lllllllllllllllllIIlIllIllIIlIII).toString();
            if (this.acceptResult(lllllllllllllllllIIlIllIllIIllIl)) {
                this.put(lllllllllllllllllIIlIllIllIIIIII, lllllllllllllllllIIlIllIllIIllIl, lllllllllllllllllIIlIllIllIIIIll.getMethodFullKey(lllllllllllllllllIIlIllIllIIIIIl, lllllllllllllllllIIlIllIllIIlIII));
            }
            final String lllllllllllllllllIIlIllIllIIlIll = lllllllllllllllllIIlIllIllIIIIll.getReturnTypeName(lllllllllllllllllIIlIllIllIIlIII);
            if (this.acceptResult(lllllllllllllllllIIlIllIllIIlIll)) {
                this.put(lllllllllllllllllIIlIllIllIIIIII, lllllllllllllllllIIlIllIllIIlIll, lllllllllllllllllIIlIllIllIIIIll.getMethodFullKey(lllllllllllllllllIIlIllIllIIIIIl, lllllllllllllllllIIlIllIllIIlIII));
            }
            final List<String> lllllllllllllllllIIlIllIllIIlIIl = lllllllllllllllllIIlIllIllIIIIll.getParameterNames(lllllllllllllllllIIlIllIllIIlIII);
            for (int lllllllllllllllllIIlIllIllIlIIII = 0; lllllllllllllllllIIlIllIllIlIIII < lllllllllllllllllIIlIllIllIIlIIl.size(); ++lllllllllllllllllIIlIllIllIlIIII) {
                for (final Object lllllllllllllllllIIlIllIllIlIIlI : lllllllllllllllllIIlIllIllIIIIll.getParameterAnnotationNames(lllllllllllllllllIIlIllIllIIlIII, lllllllllllllllllIIlIllIllIlIIII)) {
                    if (this.acceptResult((String)lllllllllllllllllIIlIllIllIlIIlI)) {
                        this.put(lllllllllllllllllIIlIllIllIIIIII, (String)lllllllllllllllllIIlIllIllIlIIlI, lllllllllllllllllIIlIllIllIIIIll.getMethodFullKey(lllllllllllllllllIIlIllIllIIIIIl, lllllllllllllllllIIlIllIllIIlIII));
                    }
                }
            }
        }
    }
}
