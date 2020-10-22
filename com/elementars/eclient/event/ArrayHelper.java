package com.elementars.eclient.event;

import java.util.*;

public class ArrayHelper<T> implements Iterable<T>
{
    private /* synthetic */ T[] elements;
    
    public void clear() {
        this.elements = (T[])new Object[0];
    }
    
    public void add(final T llIIlllIIlIlllI) {
        if (llIIlllIIlIlllI != null) {
            final Object[] llIIlllIIllIlII = new Object[this.size() + 1];
            for (int llIIlllIIllIllI = 0; llIIlllIIllIllI < llIIlllIIllIlII.length; ++llIIlllIIllIllI) {
                if (llIIlllIIllIllI < this.size()) {
                    llIIlllIIllIlII[llIIlllIIllIllI] = this.get(llIIlllIIllIllI);
                }
                else {
                    llIIlllIIllIlII[llIIlllIIllIllI] = llIIlllIIlIlllI;
                }
            }
            this.set(llIIlllIIllIlII);
        }
    }
    
    public void set(final T[] llIIllIlllllIIl) {
        this.elements = llIIllIlllllIIl;
    }
    
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    public T get(final int llIIllIllllIlIl) {
        return this.array()[llIIllIllllIlIl];
    }
    
    public void remove(final T llIIlllIIIIlIlI) {
        if (this.contains(llIIlllIIIIlIlI)) {
            final Object[] llIIlllIIIIllIl = new Object[this.size() - 1];
            boolean llIIlllIIIIllII = true;
            for (int llIIlllIIIIlllI = 0; llIIlllIIIIlllI < this.size(); ++llIIlllIIIIlllI) {
                if (llIIlllIIIIllII && this.get(llIIlllIIIIlllI).equals(llIIlllIIIIlIlI)) {
                    llIIlllIIIIllII = false;
                }
                else {
                    llIIlllIIIIllIl[llIIlllIIIIllII ? llIIlllIIIIlllI : (llIIlllIIIIlllI - 1)] = this.get(llIIlllIIIIlllI);
                }
            }
            this.set(llIIlllIIIIllIl);
        }
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private /* synthetic */ int index = 0;
            
            @Override
            public T next() {
                return ArrayHelper.this.get(this.index++);
            }
            
            @Override
            public void remove() {
                ArrayHelper.this.remove(ArrayHelper.this.get(this.index));
            }
            
            @Override
            public boolean hasNext() {
                return this.index < ArrayHelper.this.size() && ArrayHelper.this.get(this.index) != null;
            }
        };
    }
    
    public boolean contains(final T llIIlllIIIllIII) {
        Object[] llIIlllIIIllIlI;
        for (int llIIlllIIIllllI = (llIIlllIIIllIlI = this.array()).length, llIIlllIIIlllIl = 0; llIIlllIIIlllIl < llIIlllIIIllllI; ++llIIlllIIIlllIl) {
            final T llIIlllIIIlllll = (T)llIIlllIIIllIlI[llIIlllIIIlllIl];
            if (llIIlllIIIlllll.equals(llIIlllIIIllIII)) {
                return true;
            }
        }
        return false;
    }
    
    public T[] array() {
        return this.elements;
    }
    
    public int size() {
        return this.array().length;
    }
    
    public ArrayHelper() {
        this.elements = (T[])new Object[0];
    }
    
    public ArrayHelper(final T[] llIIlllIlIlIlll) {
        this.elements = llIIlllIlIlIlll;
    }
}
