package org.reflections.scanners;

import java.util.function.*;
import org.reflections.util.*;
import org.reflections.adapters.*;
import org.reflections.vfs.*;
import org.reflections.*;

public abstract class AbstractScanner implements Scanner
{
    private /* synthetic */ Configuration configuration;
    private /* synthetic */ Predicate<String> resultFilter;
    
    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }
    
    protected void put(final Store lllllllllllllllllIIlllIlIIlllIIl, final String lllllllllllllllllIIlllIlIIlllIII, final String lllllllllllllllllIIlllIlIIllIlll) {
        lllllllllllllllllIIlllIlIIlllIIl.put(Utils.index(this.getClass()), lllllllllllllllllIIlllIlIIlllIII, lllllllllllllllllIIlllIlIIllIlll);
    }
    
    @Override
    public boolean acceptResult(final String lllllllllllllllllIIlllIlIIIllIll) {
        return lllllllllllllllllIIlllIlIIIllIll != null && this.resultFilter.test(lllllllllllllllllIIlllIlIIIllIll);
    }
    
    @Override
    public void setConfiguration(final Configuration lllllllllllllllllIIlllIlIIlIlllI) {
        this.configuration = lllllllllllllllllIIlllIlIIlIlllI;
    }
    
    public abstract void scan(final Object p0, final Store p1);
    
    public Predicate<String> getResultFilter() {
        return this.resultFilter;
    }
    
    @Override
    public boolean equals(final Object lllllllllllllllllIIlllIlIIIlIIII) {
        return this == lllllllllllllllllIIlllIlIIIlIIII || (lllllllllllllllllIIlllIlIIIlIIII != null && this.getClass() == lllllllllllllllllIIlllIlIIIlIIII.getClass());
    }
    
    protected MetadataAdapter getMetadataAdapter() {
        return this.configuration.getMetadataAdapter();
    }
    
    public void setResultFilter(final Predicate<String> lllllllllllllllllIIlllIlIIlIIlll) {
        this.resultFilter = lllllllllllllllllIIlllIlIIlIIlll;
    }
    
    @Override
    public Object scan(final Vfs.File lllllllllllllllllIIlllIlIlIIIllI, Object lllllllllllllllllIIlllIlIlIIIlIl, final Store lllllllllllllllllIIlllIlIlIIIlII) {
        if (lllllllllllllllllIIlllIlIlIIIlIl == null) {
            try {
                lllllllllllllllllIIlllIlIlIIIlIl = this.configuration.getMetadataAdapter().getOrCreateClassObject(lllllllllllllllllIIlllIlIlIIIllI);
            }
            catch (Exception lllllllllllllllllIIlllIlIlIIllII) {
                throw new ReflectionsException(String.valueOf(new StringBuilder().append("could not create class object from file ").append(lllllllllllllllllIIlllIlIlIIIllI.getRelativePath())), lllllllllllllllllIIlllIlIlIIllII);
            }
        }
        this.scan(lllllllllllllllllIIlllIlIlIIIlIl, lllllllllllllllllIIlllIlIlIIIlII);
        return lllllllllllllllllIIlllIlIlIIIlIl;
    }
    
    @Override
    public boolean acceptsInput(final String lllllllllllllllllIIlllIlIlIlIIlI) {
        return this.getMetadataAdapter().acceptsInput(lllllllllllllllllIIlllIlIlIlIIlI);
    }
    
    public Configuration getConfiguration() {
        return this.configuration;
    }
    
    @Override
    public Scanner filterResultsBy(final Predicate<String> lllllllllllllllllIIlllIlIIIlllll) {
        this.setResultFilter(lllllllllllllllllIIlllIlIIIlllll);
        return this;
    }
    
    public AbstractScanner() {
        this.resultFilter = (lllllllllllllllllIIlllIlIIIIllII -> true);
    }
}
