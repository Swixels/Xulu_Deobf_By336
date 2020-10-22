package org.reflections.scanners;

import org.reflections.util.*;
import java.util.function.*;
import org.reflections.*;

public class SubTypesScanner extends AbstractScanner
{
    public SubTypesScanner() {
        this(true);
    }
    
    public SubTypesScanner(final boolean lIlIIllllIIll) {
        if (lIlIIllllIIll) {
            this.filterResultsBy(new FilterBuilder().exclude(Object.class.getName()));
        }
    }
    
    @Override
    public void scan(final Object lIlIIlllIIlII, final Store lIlIIlllIIIll) {
        final String lIlIIlllIIlll = this.getMetadataAdapter().getClassName(lIlIIlllIIlII);
        final String lIlIIlllIIllI = this.getMetadataAdapter().getSuperclassName(lIlIIlllIIlII);
        if (this.acceptResult(lIlIIlllIIllI)) {
            this.put(lIlIIlllIIIll, lIlIIlllIIllI, lIlIIlllIIlll);
        }
        for (final String lIlIIlllIlIll : this.getMetadataAdapter().getInterfacesNames(lIlIIlllIIlII)) {
            if (this.acceptResult(lIlIIlllIlIll)) {
                this.put(lIlIIlllIIIll, lIlIIlllIlIll, lIlIIlllIIlll);
            }
        }
    }
}
