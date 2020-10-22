package org.newdawn.slick.command;

public class ControllerDirectionControl extends ControllerControl
{
    static {
        LEFT = new Direction(1);
        UP = new Direction(3);
        DOWN = new Direction(4);
        RIGHT = new Direction(2);
    }
    
    public ControllerDirectionControl(final int lllllllllllllllllIlllIIIlIIllIlI, final Direction lllllllllllllllllIlllIIIlIIllIIl) {
        super(lllllllllllllllllIlllIIIlIIllIlI, lllllllllllllllllIlllIIIlIIllIIl.event, 0);
    }
    
    private static class Direction
    {
        private /* synthetic */ int event;
        
        public Direction(final int llIIIIIlllIlIll) {
            this.event = llIIIIIlllIlIll;
        }
    }
}
