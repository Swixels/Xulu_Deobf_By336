package com.elementars.eclient.util;

import org.lwjgl.opengl.*;
import java.awt.*;

public class ColourHolder
{
    /* synthetic */ int b;
    /* synthetic */ int r;
    /* synthetic */ int g;
    /* synthetic */ int a;
    
    public ColourHolder(final int lllllllllllllllllIlllIllIllIIIII, final int lllllllllllllllllIlllIllIllIIlII, final int lllllllllllllllllIlllIllIllIIIll, final int lllllllllllllllllIlllIllIlIlllIl) {
        this.r = lllllllllllllllllIlllIllIllIIIII;
        this.g = lllllllllllllllllIlllIllIllIIlII;
        this.b = lllllllllllllllllIlllIllIllIIIll;
        this.a = lllllllllllllllllIlllIllIlIlllIl;
    }
    
    public void setGLColour() {
        this.setGLColour(-1, -1, -1, -1);
    }
    
    public int getG() {
        return this.g;
    }
    
    public ColourHolder setR(final int lllllllllllllllllIlllIllIIIlllII) {
        this.r = lllllllllllllllllIlllIllIIIlllII;
        return this;
    }
    
    public ColourHolder darker() {
        return new ColourHolder(Math.max(this.r - 10, 0), Math.max(this.g - 10, 0), Math.max(this.b - 10, 0), this.getA());
    }
    
    public void becomeGLColour() {
    }
    
    public ColourHolder setA(final int lllllllllllllllllIlllIllIIIIlIII) {
        this.a = lllllllllllllllllIlllIllIIIIlIII;
        return this;
    }
    
    public ColourHolder(final int lllllllllllllllllIlllIllIllIlllI, final int lllllllllllllllllIlllIllIllIllIl, final int lllllllllllllllllIlllIllIllIllII) {
        this.r = lllllllllllllllllIlllIllIllIlllI;
        this.g = lllllllllllllllllIlllIllIllIllIl;
        this.b = lllllllllllllllllIlllIllIllIllII;
        this.a = 255;
    }
    
    public void setGLColour(final int lllllllllllllllllIlllIllIlIIlIII, final int lllllllllllllllllIlllIllIlIIIlll, final int lllllllllllllllllIlllIllIlIIlIll, final int lllllllllllllllllIlllIllIlIIIlIl) {
        GL11.glColor4f(((lllllllllllllllllIlllIllIlIIlIII == -1) ? this.r : lllllllllllllllllIlllIllIlIIlIII) / 255.0f, ((lllllllllllllllllIlllIllIlIIIlll == -1) ? this.g : lllllllllllllllllIlllIllIlIIIlll) / 255.0f, ((lllllllllllllllllIlllIllIlIIlIll == -1) ? this.b : lllllllllllllllllIlllIllIlIIlIll) / 255.0f, ((lllllllllllllllllIlllIllIlIIIlIl == -1) ? this.a : lllllllllllllllllIlllIllIlIIIlIl) / 255.0f);
    }
    
    public int toHex() {
        return toHex(this.r, this.g, this.b);
    }
    
    public ColourHolder setB(final int lllllllllllllllllIlllIllIIIlIlII) {
        this.b = lllllllllllllllllIlllIllIIIlIlII;
        return this;
    }
    
    public ColourHolder brighter() {
        return new ColourHolder(Math.min(this.r + 10, 255), Math.min(this.g + 10, 255), Math.min(this.b + 10, 255), this.getA());
    }
    
    public ColourHolder clone() {
        return new ColourHolder(this.r, this.g, this.b, this.a);
    }
    
    public ColourHolder setG(final int lllllllllllllllllIlllIllIIIlIIII) {
        this.g = lllllllllllllllllIlllIllIIIlIIII;
        return this;
    }
    
    public int getA() {
        return this.a;
    }
    
    public void becomeHex(final int lllllllllllllllllIlllIllIIlllllI) {
        this.setR((lllllllllllllllllIlllIllIIlllllI & 0xFF0000) >> 16);
        this.setG((lllllllllllllllllIlllIllIIlllllI & 0xFF00) >> 8);
        this.setB(lllllllllllllllllIlllIllIIlllllI & 0xFF);
        this.setA(255);
    }
    
    public int getB() {
        return this.b;
    }
    
    public static ColourHolder fromHex(final int lllllllllllllllllIlllIllIIlllIIl) {
        final ColourHolder lllllllllllllllllIlllIllIIlllIlI = new ColourHolder(0, 0, 0);
        lllllllllllllllllIlllIllIIlllIlI.becomeHex(lllllllllllllllllIlllIllIIlllIIl);
        return lllllllllllllllllIlllIllIIlllIlI;
    }
    
    public static int toHex(final int lllllllllllllllllIlllIllIIllIlII, final int lllllllllllllllllIlllIllIIllIIII, final int lllllllllllllllllIlllIllIIlIllll) {
        return 0xFF000000 | (lllllllllllllllllIlllIllIIllIlII & 0xFF) << 16 | (lllllllllllllllllIlllIllIIllIIII & 0xFF) << 8 | (lllllllllllllllllIlllIllIIlIllll & 0xFF);
    }
    
    public int getR() {
        return this.r;
    }
    
    public Color toJavaColour() {
        return new Color(this.r, this.g, this.b, this.a);
    }
}
