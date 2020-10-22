package org.newdawn.slick.command;

public class MouseButtonControl implements Control
{
    private /* synthetic */ int button;
    
    @Override
    public int hashCode() {
        return this.button;
    }
    
    public MouseButtonControl(final int llIlIlIllIllIll) {
        this.button = llIlIlIllIllIll;
    }
    
    @Override
    public boolean equals(final Object llIlIlIllIlIlIl) {
        return llIlIlIllIlIlIl instanceof MouseButtonControl && ((MouseButtonControl)llIlIlIllIlIlIl).button == this.button;
    }
}
