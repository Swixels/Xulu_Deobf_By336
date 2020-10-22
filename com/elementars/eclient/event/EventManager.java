package com.elementars.eclient.event;

import java.lang.reflect.*;
import java.util.*;
import java.lang.annotation.*;

public class EventManager
{
    private /* synthetic */ Map<Class<? extends Event>, ArrayHelper<Data>> REGISTRY_MAP;
    
    public void unregister(final Object lllllllllllllllllIIlIIllIlIlIllI, final Class<? extends Event> lllllllllllllllllIIlIIllIlIllIII) {
        if (this.REGISTRY_MAP.containsKey(lllllllllllllllllIIlIIllIlIllIII)) {
            for (final Data lllllllllllllllllIIlIIllIlIllIll : this.REGISTRY_MAP.get(lllllllllllllllllIIlIIllIlIllIII)) {
                if (lllllllllllllllllIIlIIllIlIllIll.source.equals(lllllllllllllllllIIlIIllIlIlIllI)) {
                    this.REGISTRY_MAP.get(lllllllllllllllllIIlIIllIlIllIII).remove(lllllllllllllllllIIlIIllIlIllIll);
                }
            }
            this.cleanMap(true);
        }
    }
    
    public void register(final Object lllllllllllllllllIIlIIlllIIllIII) {
        final int lllllllllllllllllIIlIIlllIIlIlIl = (Object)lllllllllllllllllIIlIIlllIIllIII.getClass().getDeclaredMethods();
        final long lllllllllllllllllIIlIIlllIIlIlII = lllllllllllllllllIIlIIlllIIlIlIl.length;
        for (char lllllllllllllllllIIlIIlllIIlIIll = '\0'; lllllllllllllllllIIlIIlllIIlIIll < lllllllllllllllllIIlIIlllIIlIlII; ++lllllllllllllllllIIlIIlllIIlIIll) {
            final Method lllllllllllllllllIIlIIlllIIllIlI = lllllllllllllllllIIlIIlllIIlIlIl[lllllllllllllllllIIlIIlllIIlIIll];
            if (!this.isMethodBad(lllllllllllllllllIIlIIlllIIllIlI)) {
                this.register(lllllllllllllllllIIlIIlllIIllIlI, lllllllllllllllllIIlIIlllIIllIII);
            }
        }
    }
    
    public EventManager() {
        this.REGISTRY_MAP = new HashMap<Class<? extends Event>, ArrayHelper<Data>>();
    }
    
    public ArrayHelper<Data> get(final Class<? extends Event> lllllllllllllllllIIlIIllIIIIIIII) {
        return this.REGISTRY_MAP.get(lllllllllllllllllIIlIIllIIIIIIII);
    }
    
    private void sortListValue(final Class<? extends Event> lllllllllllllllllIIlIIllIIllIIIl) {
        final ArrayHelper<Data> lllllllllllllllllIIlIIllIIllIIII = new ArrayHelper<Data>();
        final short lllllllllllllllllIIlIIllIIlIllII = (Object)Priority.VALUE_ARRAY;
        for (String lllllllllllllllllIIlIIllIIlIlIll = (String)lllllllllllllllllIIlIIllIIlIllII.length, lllllllllllllllllIIlIIllIIlIlIlI = (String)0; lllllllllllllllllIIlIIllIIlIlIlI < lllllllllllllllllIIlIIllIIlIlIll; ++lllllllllllllllllIIlIIllIIlIlIlI) {
            final byte lllllllllllllllllIIlIIllIIllIIll = lllllllllllllllllIIlIIllIIlIllII[lllllllllllllllllIIlIIllIIlIlIlI];
            for (final Data lllllllllllllllllIIlIIllIIllIlII : this.REGISTRY_MAP.get(lllllllllllllllllIIlIIllIIllIIIl)) {
                if (lllllllllllllllllIIlIIllIIllIlII.priority == lllllllllllllllllIIlIIllIIllIIll) {
                    lllllllllllllllllIIlIIllIIllIIII.add(lllllllllllllllllIIlIIllIIllIlII);
                }
            }
        }
        this.REGISTRY_MAP.put(lllllllllllllllllIIlIIllIIllIIIl, lllllllllllllllllIIlIIllIIllIIII);
    }
    
    public void register(final Object lllllllllllllllllIIlIIlllIIIIlIl, final Class<? extends Event> lllllllllllllllllIIlIIlllIIIIlll) {
        final long lllllllllllllllllIIlIIlllIIIIIll = (Object)lllllllllllllllllIIlIIlllIIIIlIl.getClass().getDeclaredMethods();
        for (short lllllllllllllllllIIlIIlllIIIIIlI = (short)lllllllllllllllllIIlIIlllIIIIIll.length, lllllllllllllllllIIlIIlllIIIIIIl = 0; lllllllllllllllllIIlIIlllIIIIIIl < lllllllllllllllllIIlIIlllIIIIIlI; ++lllllllllllllllllIIlIIlllIIIIIIl) {
            final Method lllllllllllllllllIIlIIlllIIIlIlI = lllllllllllllllllIIlIIlllIIIIIll[lllllllllllllllllIIlIIlllIIIIIIl];
            if (!this.isMethodBad(lllllllllllllllllIIlIIlllIIIlIlI, lllllllllllllllllIIlIIlllIIIIlll)) {
                this.register(lllllllllllllllllIIlIIlllIIIlIlI, lllllllllllllllllIIlIIlllIIIIlIl);
            }
        }
    }
    
    public void unregister(final Object lllllllllllllllllIIlIIllIllIIlll) {
        for (final ArrayHelper<Data> lllllllllllllllllIIlIIllIllIlIIl : this.REGISTRY_MAP.values()) {
            for (final Data lllllllllllllllllIIlIIllIllIlIlI : lllllllllllllllllIIlIIllIllIlIIl) {
                if (lllllllllllllllllIIlIIllIllIlIlI.source.equals(lllllllllllllllllIIlIIllIllIIlll)) {
                    lllllllllllllllllIIlIIllIllIlIIl.remove(lllllllllllllllllIIlIIllIllIlIlI);
                }
            }
        }
        this.cleanMap(true);
    }
    
    private void register(final Method lllllllllllllllllIIlIIllIlllIlII, final Object lllllllllllllllllIIlIIllIlllIIll) {
        final Class<?> lllllllllllllllllIIlIIllIlllIlll = lllllllllllllllllIIlIIllIlllIlII.getParameterTypes()[0];
        final Data lllllllllllllllllIIlIIllIlllIllI = new Data(lllllllllllllllllIIlIIllIlllIIll, lllllllllllllllllIIlIIllIlllIlII, lllllllllllllllllIIlIIllIlllIlII.getAnnotation(EventTarget.class).value());
        if (!lllllllllllllllllIIlIIllIlllIllI.target.isAccessible()) {
            lllllllllllllllllIIlIIllIlllIllI.target.setAccessible(true);
        }
        if (this.REGISTRY_MAP.containsKey(lllllllllllllllllIIlIIllIlllIlll)) {
            if (!this.REGISTRY_MAP.get(lllllllllllllllllIIlIIllIlllIlll).contains(lllllllllllllllllIIlIIllIlllIllI)) {
                this.REGISTRY_MAP.get(lllllllllllllllllIIlIIllIlllIlll).add(lllllllllllllllllIIlIIllIlllIllI);
                this.sortListValue((Class<? extends Event>)lllllllllllllllllIIlIIllIlllIlll);
            }
        }
        else {
            this.REGISTRY_MAP.put((Class<? extends Event>)lllllllllllllllllIIlIIllIlllIlll, new ArrayHelper<Data>() {
                {
                    this.add(lllllllllllllllllIIlIIllIlllIllI);
                }
            });
        }
    }
    
    public void removeEnty(final Class<? extends Event> lllllllllllllllllIIlIIllIlIIIIlI) {
        final Iterator<Map.Entry<Class<? extends Event>, ArrayHelper<Data>>> lllllllllllllllllIIlIIllIlIIIlII = this.REGISTRY_MAP.entrySet().iterator();
        while (lllllllllllllllllIIlIIllIlIIIlII.hasNext()) {
            if (lllllllllllllllllIIlIIllIlIIIlII.next().getKey().equals(lllllllllllllllllIIlIIllIlIIIIlI)) {
                lllllllllllllllllIIlIIllIlIIIlII.remove();
                break;
            }
        }
    }
    
    private boolean isMethodBad(final Method lllllllllllllllllIIlIIllIIIllIIl) {
        return lllllllllllllllllIIlIIllIIIllIIl.getParameterTypes().length != 1 || !lllllllllllllllllIIlIIllIIIllIIl.isAnnotationPresent(EventTarget.class);
    }
    
    private boolean isMethodBad(final Method lllllllllllllllllIIlIIllIIIIlIII, final Class<? extends Event> lllllllllllllllllIIlIIllIIIIIllI) {
        return this.isMethodBad(lllllllllllllllllIIlIIllIIIIlIII) || lllllllllllllllllIIlIIllIIIIlIII.getParameterTypes()[0].equals(lllllllllllllllllIIlIIllIIIIIllI);
    }
    
    public void cleanMap(final boolean lllllllllllllllllIIlIIllIlIIlllI) {
        final Iterator<Map.Entry<Class<? extends Event>, ArrayHelper<Data>>> lllllllllllllllllIIlIIllIlIIllIl = this.REGISTRY_MAP.entrySet().iterator();
        while (lllllllllllllllllIIlIIllIlIIllIl.hasNext()) {
            if (!lllllllllllllllllIIlIIllIlIIlllI || lllllllllllllllllIIlIIllIlIIllIl.next().getValue().isEmpty()) {
                lllllllllllllllllIIlIIllIlIIllIl.remove();
            }
        }
    }
    
    public void shutdown() {
        this.REGISTRY_MAP.clear();
    }
}
