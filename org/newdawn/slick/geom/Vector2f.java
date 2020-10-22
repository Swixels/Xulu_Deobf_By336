package org.newdawn.slick.geom;

import java.io.*;
import org.newdawn.slick.util.*;

public strictfp class Vector2f implements Serializable
{
    public /* synthetic */ float x;
    public /* synthetic */ float y;
    
    public Vector2f(final float[] lllIIllllIlIIll) {
        this.x = lllIIllllIlIIll[0];
        this.y = lllIIllllIlIIll[1];
    }
    
    public strictfp Vector2f set(final float lllIIllIllIlIIl, final float lllIIllIllIIlII) {
        this.x = lllIIllIllIlIIl;
        this.y = lllIIllIllIIlII;
        return this;
    }
    
    public strictfp float getY() {
        return this.y;
    }
    
    public strictfp Vector2f getPerpendicular() {
        return new Vector2f(-this.y, this.x);
    }
    
    public strictfp void projectOntoUnit(final Vector2f lllIIllIIIIlllI, final Vector2f lllIIllIIIIllIl) {
        final float lllIIllIIIIllII = lllIIllIIIIlllI.dot(this);
        lllIIllIIIIllIl.x = lllIIllIIIIllII * lllIIllIIIIlllI.getX();
        lllIIllIIIIllIl.y = lllIIllIIIIllII * lllIIllIIIIlllI.getY();
    }
    
    public strictfp Vector2f sub(final double lllIIlllIlIllII) {
        this.setTheta(this.getTheta() - lllIIlllIlIllII);
        return this;
    }
    
    public strictfp float lengthSquared() {
        return this.x * this.x + this.y * this.y;
    }
    
    public strictfp float length() {
        return (float)Math.sqrt(this.lengthSquared());
    }
    
    public strictfp Vector2f scale(final float lllIIllIIlIllII) {
        this.x *= lllIIllIIlIllII;
        this.y *= lllIIllIIlIllII;
        return this;
    }
    
    public strictfp Vector2f set(final float[] lllIIllIlIlIlIl) {
        return this.set(lllIIllIlIlIlIl[0], lllIIllIlIlIlIl[1]);
    }
    
    public strictfp Vector2f normalise() {
        final float lllIIllIIlIlIII = this.length();
        if (lllIIllIIlIlIII == 0.0f) {
            return this;
        }
        this.x /= lllIIllIIlIlIII;
        this.y /= lllIIllIIlIlIII;
        return this;
    }
    
    public strictfp Vector2f sub(final Vector2f lllIIllIIllIlII) {
        this.x -= lllIIllIIllIlII.getX();
        this.y -= lllIIllIIllIlII.getY();
        return this;
    }
    
    public strictfp Vector2f copy() {
        return new Vector2f(this.x, this.y);
    }
    
    public strictfp float distance(final Vector2f lllIIlIlllllllI) {
        return (float)Math.sqrt(this.distanceSquared(lllIIlIlllllllI));
    }
    
    public Vector2f() {
    }
    
    public strictfp Vector2f getNormal() {
        final Vector2f lllIIllIIIllllI = this.copy();
        lllIIllIIIllllI.normalise();
        return lllIIllIIIllllI;
    }
    
    public strictfp void setTheta(double lllIIllllIIIIII) {
        if (lllIIllllIIIIII < -360.0 || lllIIllllIIIIII > 360.0) {
            lllIIllllIIIIII %= 360.0;
        }
        if (lllIIllllIIIIII < 0.0) {
            lllIIllllIIIIII += 360.0;
        }
        double lllIIllllIIIIll = this.getTheta();
        if (lllIIllllIIIIII < -360.0 || lllIIllllIIIIII > 360.0) {
            lllIIllllIIIIll %= 360.0;
        }
        if (lllIIllllIIIIII < 0.0) {
            lllIIllllIIIIll += 360.0;
        }
        final float lllIIllllIIIIlI = this.length();
        this.x = lllIIllllIIIIlI * (float)FastTrig.cos(StrictMath.toRadians(lllIIllllIIIIII));
        this.y = lllIIllllIIIIlI * (float)FastTrig.sin(StrictMath.toRadians(lllIIllllIIIIII));
    }
    
    public strictfp Vector2f add(final double lllIIlllIllIlIl) {
        this.setTheta(this.getTheta() + lllIIlllIllIlIl);
        return this;
    }
    
    public strictfp Vector2f negate() {
        return new Vector2f(-this.x, -this.y);
    }
    
    public strictfp float getX() {
        return this.x;
    }
    
    @Override
    public strictfp boolean equals(final Object lllIIlIlllIIlll) {
        if (lllIIlIlllIIlll instanceof Vector2f) {
            final Vector2f lllIIlIlllIlIIl = (Vector2f)lllIIlIlllIIlll;
            return lllIIlIlllIlIIl.x == this.x && lllIIlIlllIlIIl.y == this.y;
        }
        return false;
    }
    
    public Vector2f(final double lllIIllllIIlIll) {
        this.x = 1.0f;
        this.y = 0.0f;
        this.setTheta(lllIIllllIIlIll);
    }
    
    public strictfp float distanceSquared(final Vector2f lllIIlIllllIllI) {
        final float lllIIlIllllIlIl = lllIIlIllllIllI.getX() - this.getX();
        final float lllIIlIllllIlII = lllIIlIllllIllI.getY() - this.getY();
        return lllIIlIllllIlIl * lllIIlIllllIlIl + lllIIlIllllIlII * lllIIlIllllIlII;
    }
    
    @Override
    public strictfp int hashCode() {
        return 997 * (int)this.x ^ 991 * (int)this.y;
    }
    
    @Override
    public strictfp String toString() {
        return String.valueOf(new StringBuilder().append("[Vector2f ").append(this.x).append(",").append(this.y).append(" (").append(this.length()).append(")]"));
    }
    
    public strictfp Vector2f negateLocal() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }
    
    public strictfp float dot(final Vector2f lllIIllIllllIlI) {
        return this.x * lllIIllIllllIlI.getX() + this.y * lllIIllIllllIlI.getY();
    }
    
    public strictfp double getTheta() {
        double lllIIlllIlIIlIl = StrictMath.toDegrees(StrictMath.atan2(this.y, this.x));
        if (lllIIlllIlIIlIl < -360.0 || lllIIlllIlIIlIl > 360.0) {
            lllIIlllIlIIlIl %= 360.0;
        }
        if (lllIIlllIlIIlIl < 0.0) {
            lllIIlllIlIIlIl += 360.0;
        }
        return lllIIlllIlIIlIl;
    }
    
    public Vector2f(final float lllIIlllIIIllll, final float lllIIlllIIlIIIl) {
        this.x = lllIIlllIIIllll;
        this.y = lllIIlllIIlIIIl;
    }
    
    public Vector2f(final Vector2f lllIIlllIIlIlll) {
        this(lllIIlllIIlIlll.getX(), lllIIlllIIlIlll.getY());
    }
    
    public strictfp void set(final Vector2f lllIIlllIIIlIlI) {
        this.set(lllIIlllIIIlIlI.getX(), lllIIlllIIIlIlI.getY());
    }
    
    public strictfp Vector2f add(final Vector2f lllIIllIIlllIlI) {
        this.x += lllIIllIIlllIlI.getX();
        this.y += lllIIllIIlllIlI.getY();
        return this;
    }
}
