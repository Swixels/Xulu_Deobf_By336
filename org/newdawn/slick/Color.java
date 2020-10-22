package org.newdawn.slick;

import java.io.*;
import org.newdawn.slick.opengl.renderer.*;
import java.nio.*;

public class Color implements Serializable
{
    public /* synthetic */ float a;
    protected transient /* synthetic */ SGL GL;
    public /* synthetic */ float b;
    public /* synthetic */ float g;
    public /* synthetic */ float r;
    
    public Color darker(float lllllllllllllllllllIIllIlIIIlIll) {
        lllllllllllllllllllIIllIlIIIlIll = 1.0f - lllllllllllllllllllIIllIlIIIlIll;
        final Color lllllllllllllllllllIIllIlIIIllIl = new Color(this.r * lllllllllllllllllllIIllIlIIIlIll, this.g * lllllllllllllllllllIIllIlIIIlIll, this.b * lllllllllllllllllllIIllIlIIIlIll, this.a);
        return lllllllllllllllllllIIllIlIIIllIl;
    }
    
    public int getGreen() {
        return (int)(this.g * 255.0f);
    }
    
    public Color(final int lllllllllllllllllllIIllIllIlIIlI, final int lllllllllllllllllllIIllIllIIllIl, final int lllllllllllllllllllIIllIllIIllII) {
        this.GL = Renderer.get();
        this.a = 1.0f;
        this.r = lllllllllllllllllllIIllIllIlIIlI / 255.0f;
        this.g = lllllllllllllllllllIIllIllIIllIl / 255.0f;
        this.b = lllllllllllllllllllIIllIllIIllII / 255.0f;
        this.a = 1.0f;
    }
    
    public Color(final FloatBuffer lllllllllllllllllllIIllIllllIIll) {
        this.GL = Renderer.get();
        this.a = 1.0f;
        this.r = lllllllllllllllllllIIllIllllIIll.get();
        this.g = lllllllllllllllllllIIllIllllIIll.get();
        this.b = lllllllllllllllllllIIllIllllIIll.get();
        this.a = lllllllllllllllllllIIllIllllIIll.get();
    }
    
    static {
        transparent = new Color(0.0f, 0.0f, 0.0f, 0.0f);
        white = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        yellow = new Color(1.0f, 1.0f, 0.0f, 1.0f);
        red = new Color(1.0f, 0.0f, 0.0f, 1.0f);
        blue = new Color(0.0f, 0.0f, 1.0f, 1.0f);
        green = new Color(0.0f, 1.0f, 0.0f, 1.0f);
        black = new Color(0.0f, 0.0f, 0.0f, 1.0f);
        gray = new Color(0.5f, 0.5f, 0.5f, 1.0f);
        cyan = new Color(0.0f, 1.0f, 1.0f, 1.0f);
        darkGray = new Color(0.3f, 0.3f, 0.3f, 1.0f);
        lightGray = new Color(0.7f, 0.7f, 0.7f, 1.0f);
        pink = new Color(255, 175, 175, 255);
        orange = new Color(255, 200, 0, 255);
        magenta = new Color(255, 0, 255, 255);
    }
    
    @Override
    public int hashCode() {
        return (int)(this.r + this.g + this.b + this.a) * 255;
    }
    
    public int getAlpha() {
        return (int)(this.a * 255.0f);
    }
    
    public void bind() {
        this.GL.glColor4f(this.r, this.g, this.b, this.a);
    }
    
    public Color brighter() {
        return this.brighter(0.2f);
    }
    
    public int getBlueByte() {
        return (int)(this.b * 255.0f);
    }
    
    @Override
    public boolean equals(final Object lllllllllllllllllllIIllIlIIlllII) {
        if (lllllllllllllllllllIIllIlIIlllII instanceof Color) {
            final Color lllllllllllllllllllIIllIlIIllllI = (Color)lllllllllllllllllllIIllIlIIlllII;
            return lllllllllllllllllllIIllIlIIllllI.r == this.r && lllllllllllllllllllIIllIlIIllllI.g == this.g && lllllllllllllllllllIIllIlIIllllI.b == this.b && lllllllllllllllllllIIllIlIIllllI.a == this.a;
        }
        return false;
    }
    
    public int getGreenByte() {
        return (int)(this.g * 255.0f);
    }
    
    public Color brighter(float lllllllllllllllllllIIIIIIIlIlIlI) {
        ++lllllllllllllllllllIIIIIIIlIlIlI;
        final Color lllllllllllllllllllIIllIIllIlIIl = new Color(this.r * lllllllllllllllllllIIIIIIIlIlIlI, this.g * lllllllllllllllllllIIIIIIIlIlIlI, this.b * lllllllllllllllllllIIIIIIIlIlIlI, this.a);
        return lllllllllllllllllllIIllIIllIlIIl;
    }
    
    public Color multiply(final Color lllllllllllllllllllIIIIIIIlIIlIl) {
        return new Color(this.r * lllllllllllllllllllIIIIIIIlIIlIl.r, this.g * lllllllllllllllllllIIIIIIIlIIlIl.g, this.b * lllllllllllllllllllIIIIIIIlIIlIl.b, this.a * lllllllllllllllllllIIIIIIIlIIlIl.a);
    }
    
    public Color(final int lllllllllllllllllllIIllIllIIIIII, final int lllllllllllllllllllIIllIllIIIlII, final int lllllllllllllllllllIIllIllIIIIll, final int lllllllllllllllllllIIllIllIIIIlI) {
        this.GL = Renderer.get();
        this.a = 1.0f;
        this.r = lllllllllllllllllllIIllIllIIIIII / 255.0f;
        this.g = lllllllllllllllllllIIllIllIIIlII / 255.0f;
        this.b = lllllllllllllllllllIIllIllIIIIll / 255.0f;
        this.a = lllllllllllllllllllIIllIllIIIIlI / 255.0f;
    }
    
    public Color(final float lllllllllllllllllllIIllIlllIlIIl, final float lllllllllllllllllllIIllIlllIlIII, final float lllllllllllllllllllIIllIlllIIlll) {
        this.GL = Renderer.get();
        this.a = 1.0f;
        this.r = lllllllllllllllllllIIllIlllIlIIl;
        this.g = lllllllllllllllllllIIllIlllIlIII;
        this.b = lllllllllllllllllllIIllIlllIIlll;
        this.a = 1.0f;
    }
    
    public int getRed() {
        return (int)(this.r * 255.0f);
    }
    
    public int getAlphaByte() {
        return (int)(this.a * 255.0f);
    }
    
    public Color(final float lllllllllllllllllllIIllIllIllIll, final float lllllllllllllllllllIIllIllIllIlI, final float lllllllllllllllllllIIllIllIllIIl, final float lllllllllllllllllllIIllIllIllIII) {
        this.GL = Renderer.get();
        this.a = 1.0f;
        this.r = Math.min(lllllllllllllllllllIIllIllIllIll, 1.0f);
        this.g = Math.min(lllllllllllllllllllIIllIllIllIlI, 1.0f);
        this.b = Math.min(lllllllllllllllllllIIllIllIllIIl, 1.0f);
        this.a = Math.min(lllllllllllllllllllIIllIllIllIII, 1.0f);
    }
    
    @Override
    public String toString() {
        return String.valueOf(new StringBuilder().append("Color (").append(this.r).append(",").append(this.g).append(",").append(this.b).append(",").append(this.a).append(")"));
    }
    
    public Color addToCopy(final Color lllllllllllllllllllIIIIIIIIlIIlI) {
        final Color color;
        final Color lllllllllllllllllllIIIIIIIIlIIIl = color = new Color(this.r, this.g, this.b, this.a);
        color.r += lllllllllllllllllllIIIIIIIIlIIlI.r;
        final Color color2 = lllllllllllllllllllIIIIIIIIlIIIl;
        color2.g += lllllllllllllllllllIIIIIIIIlIIlI.g;
        final Color color3 = lllllllllllllllllllIIIIIIIIlIIIl;
        color3.b += lllllllllllllllllllIIIIIIIIlIIlI.b;
        final Color color4 = lllllllllllllllllllIIIIIIIIlIIIl;
        color4.a += lllllllllllllllllllIIIIIIIIlIIlI.a;
        return lllllllllllllllllllIIIIIIIIlIIIl;
    }
    
    public void scale(final float lllllllllllllllllllIIIIIIIIlIlll) {
        this.r *= lllllllllllllllllllIIIIIIIIlIlll;
        this.g *= lllllllllllllllllllIIIIIIIIlIlll;
        this.b *= lllllllllllllllllllIIIIIIIIlIlll;
        this.a *= lllllllllllllllllllIIIIIIIIlIlll;
    }
    
    public void add(final Color lllllllllllllllllllIIIIIIIIlllll) {
        this.r += lllllllllllllllllllIIIIIIIIlllll.r;
        this.g += lllllllllllllllllllIIIIIIIIlllll.g;
        this.b += lllllllllllllllllllIIIIIIIIlllll.b;
        this.a += lllllllllllllllllllIIIIIIIIlllll.a;
    }
    
    public int getRedByte() {
        return (int)(this.r * 255.0f);
    }
    
    public Color scaleCopy(final float lllllllllllllllllllIIIIIIIIIIllI) {
        final Color color;
        final Color lllllllllllllllllllIIIIIIIIIlIII = color = new Color(this.r, this.g, this.b, this.a);
        color.r *= lllllllllllllllllllIIIIIIIIIIllI;
        final Color color2 = lllllllllllllllllllIIIIIIIIIlIII;
        color2.g *= lllllllllllllllllllIIIIIIIIIIllI;
        final Color color3 = lllllllllllllllllllIIIIIIIIIlIII;
        color3.b *= lllllllllllllllllllIIIIIIIIIIllI;
        final Color color4 = lllllllllllllllllllIIIIIIIIIlIII;
        color4.a *= lllllllllllllllllllIIIIIIIIIIllI;
        return lllllllllllllllllllIIIIIIIIIlIII;
    }
    
    public static Color decode(final String lllllllllllllllllllIIllIlIlIlIIl) {
        return new Color(Integer.decode(lllllllllllllllllllIIllIlIlIlIIl));
    }
    
    public int getBlue() {
        return (int)(this.b * 255.0f);
    }
    
    public Color(final Color lllllllllllllllllllIIllIlllllIll) {
        this.GL = Renderer.get();
        this.a = 1.0f;
        this.r = lllllllllllllllllllIIllIlllllIll.r;
        this.g = lllllllllllllllllllIIllIlllllIll.g;
        this.b = lllllllllllllllllllIIllIlllllIll.b;
        this.a = lllllllllllllllllllIIllIlllllIll.a;
    }
    
    public Color darker() {
        return this.darker(0.5f);
    }
    
    public Color(final int lllllllllllllllllllIIllIlIllIlIl) {
        this.GL = Renderer.get();
        this.a = 1.0f;
        final int lllllllllllllllllllIIllIlIllIlII = (lllllllllllllllllllIIllIlIllIlIl & 0xFF0000) >> 16;
        final int lllllllllllllllllllIIllIlIllIIll = (lllllllllllllllllllIIllIlIllIlIl & 0xFF00) >> 8;
        final int lllllllllllllllllllIIllIlIllIIlI = lllllllllllllllllllIIllIlIllIlIl & 0xFF;
        int lllllllllllllllllllIIllIlIllIIIl = (lllllllllllllllllllIIllIlIllIlIl & 0xFF000000) >> 24;
        if (lllllllllllllllllllIIllIlIllIIIl < 0) {
            lllllllllllllllllllIIllIlIllIIIl += 256;
        }
        if (lllllllllllllllllllIIllIlIllIIIl == 0) {
            lllllllllllllllllllIIllIlIllIIIl = 255;
        }
        this.r = lllllllllllllllllllIIllIlIllIlII / 255.0f;
        this.g = lllllllllllllllllllIIllIlIllIIll / 255.0f;
        this.b = lllllllllllllllllllIIllIlIllIIlI / 255.0f;
        this.a = lllllllllllllllllllIIllIlIllIIIl / 255.0f;
    }
}
