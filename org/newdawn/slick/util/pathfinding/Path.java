package org.newdawn.slick.util.pathfinding;

import java.io.*;
import java.util.*;

public class Path implements Serializable
{
    private /* synthetic */ ArrayList steps;
    
    public Path() {
        this.steps = new ArrayList();
    }
    
    public void appendStep(final int lIIIlllllIIlll, final int lIIIlllllIIIll) {
        this.steps.add(new Step(lIIIlllllIIlll, lIIIlllllIIIll));
    }
    
    public int getX(final int lIIIllllllIlII) {
        return this.getStep(lIIIllllllIlII).x;
    }
    
    public int getY(final int lIIIlllllIlllI) {
        return this.getStep(lIIIlllllIlllI).y;
    }
    
    public int getLength() {
        return this.steps.size();
    }
    
    public Step getStep(final int lIIIlllllllIII) {
        return this.steps.get(lIIIlllllllIII);
    }
    
    public void prependStep(final int lIIIllllIllllI, final int lIIIllllIlllIl) {
        this.steps.add(0, new Step(lIIIllllIllllI, lIIIllllIlllIl));
    }
    
    public boolean contains(final int lIIIllllIlIlIl, final int lIIIllllIlIIIl) {
        return this.steps.contains(new Step(lIIIllllIlIlIl, lIIIllllIlIIIl));
    }
    
    public class Step implements Serializable
    {
        private /* synthetic */ int x;
        private /* synthetic */ int y;
        
        public int getY() {
            return this.y;
        }
        
        @Override
        public int hashCode() {
            return this.x * this.y;
        }
        
        @Override
        public boolean equals(final Object llIIIIIIIlIlI) {
            if (llIIIIIIIlIlI instanceof Step) {
                final Step llIIIIIIIllII = (Step)llIIIIIIIlIlI;
                return llIIIIIIIllII.x == this.x && llIIIIIIIllII.y == this.y;
            }
            return false;
        }
        
        public Step(final int llIIIIIIllllI, final int llIIIIIIllIIl) {
            this.x = llIIIIIIllllI;
            this.y = llIIIIIIllIIl;
        }
        
        public int getX() {
            return this.x;
        }
    }
}
