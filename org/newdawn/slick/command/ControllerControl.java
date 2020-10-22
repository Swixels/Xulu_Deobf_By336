package org.newdawn.slick.command;

abstract class ControllerControl implements Control
{
    private /* synthetic */ int button;
    private /* synthetic */ int controllerNumber;
    private /* synthetic */ int event;
    
    protected ControllerControl(final int llllllllllllllllIllllIllllIIIlIl, final int llllllllllllllllIllllIllllIIlIII, final int llllllllllllllllIllllIllllIIIlll) {
        this.event = llllllllllllllllIllllIllllIIlIII;
        this.button = llllllllllllllllIllllIllllIIIlll;
        this.controllerNumber = llllllllllllllllIllllIllllIIIlIl;
    }
    
    static {
        BUTTON_EVENT = 0;
        LEFT_EVENT = 1;
        RIGHT_EVENT = 2;
        UP_EVENT = 3;
        DOWN_EVENT = 4;
    }
    
    @Override
    public int hashCode() {
        return this.event + this.button + this.controllerNumber;
    }
    
    @Override
    public boolean equals(final Object llllllllllllllllIllllIlllIlllllI) {
        if (llllllllllllllllIllllIlllIlllllI == null) {
            return false;
        }
        if (!(llllllllllllllllIllllIlllIlllllI instanceof ControllerControl)) {
            return false;
        }
        final ControllerControl llllllllllllllllIllllIlllIllllIl = (ControllerControl)llllllllllllllllIllllIlllIlllllI;
        return llllllllllllllllIllllIlllIllllIl.controllerNumber == this.controllerNumber && llllllllllllllllIllllIlllIllllIl.event == this.event && llllllllllllllllIllllIlllIllllIl.button == this.button;
    }
}
