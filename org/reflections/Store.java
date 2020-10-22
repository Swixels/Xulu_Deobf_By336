package org.reflections;

import java.util.concurrent.*;
import org.reflections.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.*;

public class Store
{
    private final /* synthetic */ ConcurrentHashMap<String, Map<String, Collection<String>>> storeMap;
    
    private Set<String> get(final String llllllllllllllllllIIlllllllIIIII, final Collection<String> llllllllllllllllllIIlllllllIIlII) {
        final Map<String, Collection<String>> llllllllllllllllllIIlllllllIIIll = this.get(llllllllllllllllllIIlllllllIIIII);
        final Set<String> llllllllllllllllllIIlllllllIIIlI = new LinkedHashSet<String>();
        for (final String llllllllllllllllllIIlllllllIlIIl : llllllllllllllllllIIlllllllIIlII) {
            final Collection<String> llllllllllllllllllIIlllllllIlIll = llllllllllllllllllIIlllllllIIIll.get(llllllllllllllllllIIlllllllIlIIl);
            if (llllllllllllllllllIIlllllllIlIll != null) {
                llllllllllllllllllIIlllllllIIIlI.addAll(llllllllllllllllllIIlllllllIlIll);
            }
        }
        return llllllllllllllllllIIlllllllIIIlI;
    }
    
    public Set<String> getAll(final Class<?> llllllllllllllllllIIlllllIIllIll, final Collection<String> llllllllllllllllllIIlllllIIlllIl) {
        return this.getAllIncluding(llllllllllllllllllIIlllllIIllIll, this.get(llllllllllllllllllIIlllllIIllIll, llllllllllllllllllIIlllllIIlllIl));
    }
    
    public Set<String> getAllIncluding(final Class<?> llllllllllllllllllIIlllllIlllIll, final Collection<String> llllllllllllllllllIIlllllIlllIlI) {
        final String llllllllllllllllllIIlllllIlllIIl = Utils.index(llllllllllllllllllIIlllllIlllIll);
        final Map<String, Collection<String>> llllllllllllllllllIIlllllIlllIII = this.get(llllllllllllllllllIIlllllIlllIIl);
        final List<String> llllllllllllllllllIIlllllIllIlll = new ArrayList<String>(llllllllllllllllllIIlllllIlllIlI);
        final Set<String> llllllllllllllllllIIlllllIllIllI = new HashSet<String>();
        for (int llllllllllllllllllIIlllllIllllIl = 0; llllllllllllllllllIIlllllIllllIl < llllllllllllllllllIIlllllIllIlll.size(); ++llllllllllllllllllIIlllllIllllIl) {
            final String llllllllllllllllllIIlllllIlllllI = llllllllllllllllllIIlllllIllIlll.get(llllllllllllllllllIIlllllIllllIl);
            if (llllllllllllllllllIIlllllIllIllI.add(llllllllllllllllllIIlllllIlllllI)) {
                final Collection<String> llllllllllllllllllIIlllllIllllll = llllllllllllllllllIIlllllIlllIII.get(llllllllllllllllllIIlllllIlllllI);
                if (llllllllllllllllllIIlllllIllllll != null) {
                    llllllllllllllllllIIlllllIllIlll.addAll(llllllllllllllllllIIlllllIllllll);
                }
            }
        }
        return llllllllllllllllllIIlllllIllIllI;
    }
    
    private Map<String, Collection<String>> get(final String llllllllllllllllllIlIIIIIIlIIlII) {
        final Map<String, Collection<String>> llllllllllllllllllIlIIIIIIlIIIll = this.storeMap.get(llllllllllllllllllIlIIIIIIlIIlII);
        if (llllllllllllllllllIlIIIIIIlIIIll == null) {
            throw new ReflectionsException(String.valueOf(new StringBuilder().append("Scanner ").append(llllllllllllllllllIlIIIIIIlIIlII).append(" was not configured")));
        }
        return llllllllllllllllllIlIIIIIIlIIIll;
    }
    
    public Set<String> get(final Class<?> llllllllllllllllllIlIIIIIIIIIIIl, final Collection<String> llllllllllllllllllIlIIIIIIIIIIII) {
        return this.get(Utils.index(llllllllllllllllllIlIIIIIIIIIIIl), llllllllllllllllllIlIIIIIIIIIIII);
    }
    
    public Set<String> values(final String llllllllllllllllllIIllllIlllllII) {
        final Map<String, Collection<String>> llllllllllllllllllIIllllIllllIlI = this.storeMap.get(llllllllllllllllllIIllllIlllllII);
        return (Set<String>)((llllllllllllllllllIIllllIllllIlI != null) ? llllllllllllllllllIIllllIllllIlI.values().stream().flatMap((Function<? super Collection<String>, ? extends Stream<?>>)Collection::stream).collect((Collector<? super Object, ?, Set<? super Object>>)Collectors.toSet()) : Collections.emptySet());
    }
    
    public Set<String> get(final Class<?> llllllllllllllllllIlIIIIIIIlIllI, final String llllllllllllllllllIlIIIIIIIlIlII) {
        return this.get(Utils.index(llllllllllllllllllIlIIIIIIIlIllI), Collections.singletonList(llllllllllllllllllIlIIIIIIIlIlII));
    }
    
    public boolean put(final Class<?> llllllllllllllllllIIllllIllIlllI, final String llllllllllllllllllIIllllIllIlIII, final String llllllllllllllllllIIllllIllIIlll) {
        return this.put(Utils.index(llllllllllllllllllIIllllIllIlllI), llllllllllllllllllIIllllIllIlIII, llllllllllllllllllIIllllIllIIlll);
    }
    
    public Set<String> getAll(final Class<?> llllllllllllllllllIIlllllIlIIlII, final String llllllllllllllllllIIlllllIlIIIll) {
        return this.getAllIncluding(llllllllllllllllllIIlllllIlIIlII, this.get(llllllllllllllllllIIlllllIlIIlII, llllllllllllllllllIIlllllIlIIIll));
    }
    
    public Set<String> get(final String llllllllllllllllllIlIIIIIIIIllII, final String llllllllllllllllllIlIIIIIIIIlllI) {
        return this.get(llllllllllllllllllIlIIIIIIIIllII, Collections.singletonList(llllllllllllllllllIlIIIIIIIIlllI));
    }
    
    protected Store() {
        this.storeMap = new ConcurrentHashMap<String, Map<String, Collection<String>>>();
    }
    
    public boolean put(final String llllllllllllllllllIIllllIlIlIllI, final String llllllllllllllllllIIllllIlIlIlII, final String llllllllllllllllllIIllllIlIlIIlI) {
        return this.storeMap.computeIfAbsent(llllllllllllllllllIIllllIlIlIllI, llllllllllllllllllIIlllIlIIlIlII -> new ConcurrentHashMap()).computeIfAbsent(llllllllllllllllllIIllllIlIlIlII, llllllllllllllllllIIlllIlIIlIlIl -> new ArrayList()).add(llllllllllllllllllIIllllIlIlIIlI);
    }
    
    void merge(final Store llllllllllllllllllIIllllIIlllIII) {
        if (llllllllllllllllllIIllllIIlllIII != null) {
            for (final String llllllllllllllllllIIllllIIlllIlI : llllllllllllllllllIIllllIIlllIII.keySet()) {
                final Map<String, Collection<String>> llllllllllllllllllIIllllIIlllIll = llllllllllllllllllIIllllIIlllIII.get(llllllllllllllllllIIllllIIlllIlI);
                if (llllllllllllllllllIIllllIIlllIll != null) {
                    for (final String llllllllllllllllllIIllllIIllllII : llllllllllllllllllIIllllIIlllIll.keySet()) {
                        for (final String llllllllllllllllllIIllllIIllllIl : llllllllllllllllllIIllllIIlllIll.get(llllllllllllllllllIIllllIIllllII)) {
                            this.put(llllllllllllllllllIIllllIIlllIlI, llllllllllllllllllIIllllIIllllII, llllllllllllllllllIIllllIIllllIl);
                        }
                    }
                }
            }
        }
    }
    
    public Set<String> keySet() {
        return this.storeMap.keySet();
    }
    
    public Set<String> keys(final String llllllllllllllllllIIlllllIIlIIll) {
        final Map<String, Collection<String>> llllllllllllllllllIIlllllIIlIIlI = this.storeMap.get(llllllllllllllllllIIlllllIIlIIll);
        return (llllllllllllllllllIIlllllIIlIIlI != null) ? new HashSet<String>(llllllllllllllllllIIlllllIIlIIlI.keySet()) : Collections.emptySet();
    }
}
