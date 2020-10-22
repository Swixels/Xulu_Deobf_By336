package org.newdawn.slick.state;

import org.newdawn.slick.*;

public abstract class BasicGameState implements GameState
{
    @Override
    public void controllerButtonReleased(final int lllIlIlIIIlII, final int lllIlIlIIIIll) {
    }
    
    @Override
    public void mouseDragged(final int lllIlIIlIIllI, final int lllIlIIlIIlIl, final int lllIlIIlIIlII, final int lllIlIIlIIIll) {
    }
    
    @Override
    public void keyPressed(final int lllIlIIllIIIl, final char lllIlIIllIIII) {
    }
    
    @Override
    public void controllerLeftReleased(final int lllIlIIlllIll) {
    }
    
    @Override
    public abstract int getID();
    
    @Override
    public void controllerButtonPressed(final int lllIlIlIIIlll, final int lllIlIlIIIllI) {
    }
    
    @Override
    public void inputStarted() {
    }
    
    @Override
    public void setInput(final Input lllIlIlIIlIlI) {
    }
    
    @Override
    public void controllerDownPressed(final int lllIlIlIIIIIl) {
    }
    
    @Override
    public void mouseReleased(final int lllIlIIIllIII, final int lllIlIIIlIlll, final int lllIlIIIlIllI) {
    }
    
    @Override
    public void controllerDownReleased(final int lllIlIIllllll) {
    }
    
    @Override
    public void mouseWheelMoved(final int lllIlIIIIlllI) {
    }
    
    @Override
    public void mousePressed(final int lllIlIIIlllII, final int lllIlIIIllIll, final int lllIlIIIllIlI) {
    }
    
    @Override
    public void controllerRightPressed(final int lllIlIIlllIIl) {
    }
    
    @Override
    public void enter(final GameContainer lllIlIIIlIlII, final StateBasedGame lllIlIIIlIIll) throws SlickException {
    }
    
    @Override
    public void leave(final GameContainer lllIlIIIlIIIl, final StateBasedGame lllIlIIIlIIII) throws SlickException {
    }
    
    @Override
    public boolean isAcceptingInput() {
        return true;
    }
    
    @Override
    public void inputEnded() {
    }
    
    @Override
    public void mouseMoved(final int lllIlIIlIlIll, final int lllIlIIlIlIlI, final int lllIlIIlIlIIl, final int lllIlIIlIlIII) {
    }
    
    @Override
    public void controllerUpPressed(final int lllIlIIllIlIl) {
    }
    
    @Override
    public void controllerRightReleased(final int lllIlIIllIlll) {
    }
    
    @Override
    public void controllerUpReleased(final int lllIlIIllIIll) {
    }
    
    @Override
    public void mouseClicked(final int lllIlIIlIIIIl, final int lllIlIIlIIIII, final int lllIlIIIlllll, final int lllIlIIIllllI) {
    }
    
    @Override
    public void keyReleased(final int lllIlIIlIlllI, final char lllIlIIlIllIl) {
    }
    
    @Override
    public void controllerLeftPressed(final int lllIlIIllllIl) {
    }
}
