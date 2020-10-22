package org.reflections.scanners;

import org.reflections.*;
import java.lang.reflect.*;
import org.reflections.util.*;
import org.reflections.adapters.*;
import javassist.bytecode.*;
import java.util.*;

public class MethodParameterNamesScanner extends AbstractScanner
{
    @Override
    public void scan(final Object lIIIlIlIll, final Store lIIIlIIlII) {
        final MetadataAdapter lIIIlIlIIl = this.getMetadataAdapter();
        for (final Object lIIIlIlllI : lIIIlIlIIl.getMethods(lIIIlIlIll)) {
            final String lIIIlIllll = lIIIlIlIIl.getMethodFullKey(lIIIlIlIll, lIIIlIlllI);
            if (this.acceptResult(lIIIlIllll)) {
                final CodeAttribute lIIIllIlII = ((MethodInfo)lIIIlIlllI).getCodeAttribute();
                final LocalVariableAttribute lIIIllIIlI = (lIIIllIlII != null) ? ((LocalVariableAttribute)lIIIllIlII.getAttribute("LocalVariableTable")) : null;
                final int lIIIllIIIl = (lIIIllIIlI != null) ? lIIIllIIlI.tableLength() : 0;
                int lIIIllIIII = Modifier.isStatic(((MethodInfo)lIIIlIlllI).getAccessFlags()) ? 0 : 1;
                if (lIIIllIIII >= lIIIllIIIl) {
                    continue;
                }
                final List<String> lIIIllIlIl = new ArrayList<String>(lIIIllIIIl - lIIIllIIII);
                while (lIIIllIIII < lIIIllIIIl) {
                    lIIIllIlIl.add(((MethodInfo)lIIIlIlllI).getConstPool().getUtf8Info(lIIIllIIlI.nameIndex(lIIIllIIII++)));
                }
                this.put(lIIIlIIlII, lIIIlIllll, Utils.join(lIIIllIlIl, ", "));
            }
        }
    }
}
