package com.elementars.eclient.util;

public class Pair<T, S>
{
    /* synthetic */ S value;
    /* synthetic */ T key;
    
    public void setValue(final S lllllllllllllllllllIllIIlIlIIIIl) {
        this.value = lllllllllllllllllllIllIIlIlIIIIl;
    }
    
    public S getValue() {
        return this.value;
    }
    
    public void setKey(final T lllllllllllllllllllIllIIlIlIlIII) {
        this.key = lllllllllllllllllllIllIIlIlIlIII;
    }
    
    public T getKey() {
        return this.key;
    }
    
    public Pair(final T lllllllllllllllllllIllIIllIIIlII, final S lllllllllllllllllllIllIIllIIIllI) {
        this.key = lllllllllllllllllllIllIIllIIIlII;
        this.value = lllllllllllllllllllIllIIllIIIllI;
    }
}
