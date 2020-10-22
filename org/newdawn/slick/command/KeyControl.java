package org.newdawn.slick.command;

public class KeyControl implements Control
{
    private /* synthetic */ int keycode;
    
    @Override
    public boolean equals(final Object llllllllllllllllIlIlllIIllIIlIIl) {
        return llllllllllllllllIlIlllIIllIIlIIl instanceof KeyControl && ((KeyControl)llllllllllllllllIlIlllIIllIIlIIl).keycode == this.keycode;
    }
    
    public KeyControl(final int llllllllllllllllIlIlllIIllIIllll) {
        this.keycode = llllllllllllllllIlIlllIIllIIllll;
    }
    
    @Override
    public int hashCode() {
        return this.keycode;
    }
}
