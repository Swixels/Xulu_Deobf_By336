package com.elementars.eclient.util;

import java.awt.*;
import org.lwjgl.opengl.*;

public class HueCycler
{
    /* synthetic */ int index;
    /* synthetic */ int[] cycles;
    
    public void reset(final int lllllIIlIIIIl) {
        this.index = lllllIIlIIIIl;
    }
    
    public int next() {
        final int lllllIIIlllIl = this.cycles[this.index];
        ++this.index;
        if (this.index >= this.cycles.length) {
            this.index = 0;
        }
        return lllllIIIlllIl;
    }
    
    public HueCycler(final int lllllIIllIIIl) {
        this.index = 0;
        if (lllllIIllIIIl <= 0) {
            throw new IllegalArgumentException("cycles <= 0");
        }
        this.cycles = new int[lllllIIllIIIl];
        double lllllIIllIIII = 0.0;
        final double lllllIIlIllll = 1.0 / lllllIIllIIIl;
        for (int lllllIIllIIll = 0; lllllIIllIIll < lllllIIllIIIl; ++lllllIIllIIll) {
            this.cycles[lllllIIllIIll] = Color.HSBtoRGB((float)lllllIIllIIII, 1.0f, 1.0f);
            lllllIIllIIII += lllllIIlIllll;
        }
    }
    
    public void reset() {
        this.index = 0;
    }
    
    public int current() {
        return this.cycles[this.index];
    }
    
    public void set() {
        final int lllllIIIIlllI = this.cycles[this.index];
        final float lllllIIIIllIl = (lllllIIIIlllI >> 16 & 0xFF) / 255.0f;
        final float lllllIIIIllII = (lllllIIIIlllI >> 8 & 0xFF) / 255.0f;
        final float lllllIIIIlIll = (lllllIIIIlllI & 0xFF) / 255.0f;
        GL11.glColor3f(lllllIIIIllIl, lllllIIIIllII, lllllIIIIlIll);
    }
    
    public void setNext(final float llllIlllllllI) {
        final int llllIllllllIl = this.next();
        final float llllIllllllII = (llllIllllllIl >> 16 & 0xFF) / 255.0f;
        final float llllIlllllIll = (llllIllllllIl >> 8 & 0xFF) / 255.0f;
        final float llllIlllllIlI = (llllIllllllIl & 0xFF) / 255.0f;
        GL11.glColor4f(llllIllllllII, llllIlllllIll, llllIlllllIlI, llllIlllllllI);
    }
    
    public void setNext() {
        final int lllllIIIlIlll = this.next();
    }
}
