package org.reflections.scanners;

import org.reflections.*;
import org.reflections.util.*;
import java.util.*;

public class TypeElementsScanner extends AbstractScanner
{
    private /* synthetic */ boolean publicOnly;
    private /* synthetic */ boolean includeAnnotations;
    private /* synthetic */ boolean includeMethods;
    private /* synthetic */ boolean includeFields;
    
    public TypeElementsScanner includeAnnotations() {
        return this.includeAnnotations(true);
    }
    
    @Override
    public void scan(final Object lllllllllllllllllIIlllIIIIlllIIl, final Store lllllllllllllllllIIlllIIIIllIlII) {
        final String lllllllllllllllllIIlllIIIIllIlll = this.getMetadataAdapter().getClassName(lllllllllllllllllIIlllIIIIlllIIl);
        if (!this.acceptResult(lllllllllllllllllIIlllIIIIllIlll)) {
            return;
        }
        this.put(lllllllllllllllllIIlllIIIIllIlII, lllllllllllllllllIIlllIIIIllIlll, "");
        if (this.includeFields) {
            for (final Object lllllllllllllllllIIlllIIIIlllllI : this.getMetadataAdapter().getFields(lllllllllllllllllIIlllIIIIlllIIl)) {
                final String lllllllllllllllllIIlllIIIIllllll = this.getMetadataAdapter().getFieldName(lllllllllllllllllIIlllIIIIlllllI);
                this.put(lllllllllllllllllIIlllIIIIllIlII, lllllllllllllllllIIlllIIIIllIlll, lllllllllllllllllIIlllIIIIllllll);
            }
        }
        if (this.includeMethods) {
            for (final Object lllllllllllllllllIIlllIIIIllllII : this.getMetadataAdapter().getMethods(lllllllllllllllllIIlllIIIIlllIIl)) {
                if (!this.publicOnly || this.getMetadataAdapter().isPublic(lllllllllllllllllIIlllIIIIllllII)) {
                    final String lllllllllllllllllIIlllIIIIllllIl = String.valueOf(new StringBuilder().append(this.getMetadataAdapter().getMethodName(lllllllllllllllllIIlllIIIIllllII)).append("(").append(Utils.join(this.getMetadataAdapter().getParameterNames(lllllllllllllllllIIlllIIIIllllII), ", ")).append(")"));
                    this.put(lllllllllllllllllIIlllIIIIllIlII, lllllllllllllllllIIlllIIIIllIlll, lllllllllllllllllIIlllIIIIllllIl);
                }
            }
        }
        if (this.includeAnnotations) {
            for (final Object lllllllllllllllllIIlllIIIIlllIll : this.getMetadataAdapter().getClassAnnotationNames(lllllllllllllllllIIlllIIIIlllIIl)) {
                this.put(lllllllllllllllllIIlllIIIIllIlII, lllllllllllllllllIIlllIIIIllIlll, String.valueOf(new StringBuilder().append("@").append(lllllllllllllllllIIlllIIIIlllIll)));
            }
        }
    }
    
    public TypeElementsScanner includeMethods() {
        return this.includeMethods(true);
    }
    
    public TypeElementsScanner includeFields(final boolean lllllllllllllllllIIlllIIIIlIlIIl) {
        this.includeFields = lllllllllllllllllIIlllIIIIlIlIIl;
        return this;
    }
    
    public TypeElementsScanner publicOnly(final boolean lllllllllllllllllIIlllIIIIIIlIlI) {
        this.publicOnly = lllllllllllllllllIIlllIIIIIIlIlI;
        return this;
    }
    
    public TypeElementsScanner includeAnnotations(final boolean lllllllllllllllllIIlllIIIIIlIIII) {
        this.includeAnnotations = lllllllllllllllllIIlllIIIIIlIIII;
        return this;
    }
    
    public TypeElementsScanner publicOnly() {
        return this.publicOnly(true);
    }
    
    public TypeElementsScanner() {
        this.includeFields = true;
        this.includeMethods = true;
        this.includeAnnotations = true;
        this.publicOnly = true;
    }
    
    public TypeElementsScanner includeFields() {
        return this.includeFields(true);
    }
    
    public TypeElementsScanner includeMethods(final boolean lllllllllllllllllIIlllIIIIIllIIl) {
        this.includeMethods = lllllllllllllllllIIlllIIIIIllIIl;
        return this;
    }
}
