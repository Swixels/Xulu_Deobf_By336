package org.newdawn.slick;

public abstract class BasicGame implements InputListener, Game
{
    private /* synthetic */ String title;
    protected /* synthetic */ boolean[] controllerLeft;
    protected /* synthetic */ boolean[][] controllerButton;
    protected /* synthetic */ boolean[] controllerRight;
    protected /* synthetic */ boolean[] controllerDown;
    protected /* synthetic */ boolean[] controllerUp;
    
    @Override
    public void mouseWheelMoved(final int llllllllIIlIIll) {
    }
    
    static {
        MAX_CONTROLLERS = 20;
        MAX_CONTROLLER_BUTTONS = 100;
    }
    
    @Override
    public void keyPressed(final int lllllllllllIIlI, final char lllllllllllIIIl) {
    }
    
    public BasicGame(final String llllllllllllIlI) {
        this.controllerLeft = new boolean[20];
        this.controllerRight = new boolean[20];
        this.controllerUp = new boolean[20];
        this.controllerDown = new boolean[20];
        this.controllerButton = new boolean[20][100];
        this.title = llllllllllllIlI;
    }
    
    @Override
    public String getTitle() {
        return this.title;
    }
    
    @Override
    public void controllerUpPressed(final int llllllllIlIIIIl) {
        this.controllerUp[llllllllIlIIIIl] = true;
    }
    
    @Override
    public abstract void init(final GameContainer p0) throws SlickException;
    
    @Override
    public abstract void update(final GameContainer p0, final int p1) throws SlickException;
    
    @Override
    public void controllerRightReleased(final int llllllllIlIIlIl) {
        this.controllerRight[llllllllIlIIlIl] = false;
    }
    
    @Override
    public void mouseClicked(final int llllllllllIIIlI, final int llllllllllIIIIl, final int llllllllllIIIII, final int lllllllllIlllll) {
    }
    
    @Override
    public void inputStarted() {
    }
    
    @Override
    public void mouseReleased(final int llllllllIIlIlll, final int llllllllIIlIllI, final int llllllllIIlIlIl) {
    }
    
    @Override
    public boolean isAcceptingInput() {
        return true;
    }
    
    @Override
    public void controllerLeftPressed(final int llllllllIlllIIl) {
        this.controllerLeft[llllllllIlllIIl] = true;
    }
    
    @Override
    public boolean closeRequested() {
        return true;
    }
    
    @Override
    public void mouseDragged(final int llllllllllIIlll, final int llllllllllIIllI, final int llllllllllIIlIl, final int llllllllllIIlII) {
    }
    
    @Override
    public void mousePressed(final int lllllllllIlllIl, final int lllllllllIlllII, final int lllllllllIllIll) {
    }
    
    @Override
    public void controllerButtonReleased(final int lllllllllIIllIl, final int lllllllllIIlIIl) {
        this.controllerButton[lllllllllIIllIl][lllllllllIIlIIl] = false;
    }
    
    @Override
    public void controllerUpReleased(final int llllllllIIllIIl) {
        this.controllerUp[llllllllIIllIIl] = false;
    }
    
    @Override
    public void inputEnded() {
    }
    
    @Override
    public void controllerRightPressed(final int llllllllIlIllIl) {
        this.controllerRight[llllllllIlIllIl] = true;
    }
    
    @Override
    public void controllerLeftReleased(final int llllllllIllIIll) {
        this.controllerLeft[llllllllIllIIll] = false;
    }
    
    @Override
    public void controllerDownReleased(final int llllllllIllllIl) {
        this.controllerDown[llllllllIllllIl] = false;
    }
    
    @Override
    public void mouseMoved(final int llllllllllIllII, final int llllllllllIlIll, final int llllllllllIlIlI, final int llllllllllIlIIl) {
    }
    
    @Override
    public void setInput(final Input llllllllllllIII) {
    }
    
    @Override
    public void controllerButtonPressed(final int lllllllllIlIllI, final int lllllllllIlIIlI) {
        this.controllerButton[lllllllllIlIllI][lllllllllIlIIlI] = true;
    }
    
    @Override
    public void controllerDownPressed(final int lllllllllIIIlIl) {
        this.controllerDown[lllllllllIIIlIl] = true;
    }
    
    @Override
    public void keyReleased(final int llllllllllIllll, final char llllllllllIlllI) {
    }
}
