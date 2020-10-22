package org.newdawn.slick.state;

import java.util.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.transition.*;

public abstract class StateBasedGame implements Game, InputListener
{
    private /* synthetic */ HashMap states;
    private /* synthetic */ GameState currentState;
    private /* synthetic */ Transition enterTransition;
    private /* synthetic */ GameState nextState;
    private /* synthetic */ GameContainer container;
    private /* synthetic */ Transition leaveTransition;
    private /* synthetic */ String title;
    
    @Override
    public final void init(final GameContainer llllllllllllllllllIIlllIIIlIlllI) throws SlickException {
        this.container = llllllllllllllllllIIlllIIIlIlllI;
        this.initStatesList(llllllllllllllllllIIlllIIIlIlllI);
        for (final GameState llllllllllllllllllIIlllIIIllIIII : this.states.values()) {
            llllllllllllllllllIIlllIIIllIIII.init(llllllllllllllllllIIlllIIIlIlllI, this);
        }
        if (this.currentState != null) {
            this.currentState.enter(llllllllllllllllllIIlllIIIlIlllI, this);
        }
    }
    
    @Override
    public void controllerLeftPressed(final int llllllllllllllllllIIllIlllIlllII) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.controllerLeftPressed(llllllllllllllllllIIllIlllIlllII);
    }
    
    @Override
    public String getTitle() {
        return this.title;
    }
    
    @Override
    public void mouseWheelMoved(final int llllllllllllllllllIIllIlIlIlllIl) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.mouseWheelMoved(llllllllllllllllllIIllIlIlIlllIl);
    }
    
    protected void postUpdateState(final GameContainer llllllllllllllllllIIlllIIIIIlIIl, final int llllllllllllllllllIIlllIIIIIlIII) throws SlickException {
    }
    
    @Override
    public void keyPressed(final int llllllllllllllllllIIllIllIllIlll, final char llllllllllllllllllIIllIllIllIIll) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.keyPressed(llllllllllllllllllIIllIllIllIlll, llllllllllllllllllIIllIllIllIIll);
    }
    
    protected void postRenderState(final GameContainer llllllllllllllllllIIlllIIIIllIll, final Graphics llllllllllllllllllIIlllIIIIllIlI) throws SlickException {
    }
    
    protected void preUpdateState(final GameContainer llllllllllllllllllIIlllIIIIIllII, final int llllllllllllllllllIIlllIIIIIlIll) throws SlickException {
    }
    
    public GameContainer getContainer() {
        return this.container;
    }
    
    @Override
    public void mouseReleased(final int llllllllllllllllllIIllIlIllIIlll, final int llllllllllllllllllIIllIlIllIlIlI, final int llllllllllllllllllIIllIlIllIIlIl) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.mouseReleased(llllllllllllllllllIIllIlIllIIlll, llllllllllllllllllIIllIlIllIlIlI, llllllllllllllllllIIllIlIllIIlIl);
    }
    
    @Override
    public boolean isAcceptingInput() {
        return !this.transitioning() && this.currentState.isAcceptingInput();
    }
    
    @Override
    public void controllerRightReleased(final int llllllllllllllllllIIllIlllIIlIII) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.controllerRightReleased(llllllllllllllllllIIllIlllIIlIII);
    }
    
    @Override
    public void setInput(final Input llllllllllllllllllIIlllIIlIlIIll) {
    }
    
    @Override
    public boolean closeRequested() {
        return true;
    }
    
    public abstract void initStatesList(final GameContainer p0) throws SlickException;
    
    @Override
    public void mouseMoved(final int llllllllllllllllllIIllIllIIllllI, final int llllllllllllllllllIIllIllIIlllIl, final int llllllllllllllllllIIllIllIIlllII, final int llllllllllllllllllIIllIllIlIIIII) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.mouseMoved(llllllllllllllllllIIllIllIIllllI, llllllllllllllllllIIllIllIIlllIl, llllllllllllllllllIIllIllIIlllII, llllllllllllllllllIIllIllIlIIIII);
    }
    
    public void enterState(final int llllllllllllllllllIIlllIIIlllIll, Transition llllllllllllllllllIIlllIIIllIllI, Transition llllllllllllllllllIIlllIIIllIlIl) {
        if (llllllllllllllllllIIlllIIIllIllI == null) {
            llllllllllllllllllIIlllIIIllIllI = new EmptyTransition();
        }
        if (llllllllllllllllllIIlllIIIllIlIl == null) {
            llllllllllllllllllIIlllIIIllIlIl = new EmptyTransition();
        }
        this.leaveTransition = llllllllllllllllllIIlllIIIllIllI;
        this.enterTransition = llllllllllllllllllIIlllIIIllIlIl;
        this.nextState = this.getState(llllllllllllllllllIIlllIIIlllIll);
        if (this.nextState == null) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("No game state registered with the ID: ").append(llllllllllllllllllIIlllIIIlllIll)));
        }
        this.leaveTransition.init(this.currentState, this.nextState);
    }
    
    @Override
    public void mouseClicked(final int llllllllllllllllllIIllIllIIIIIII, final int llllllllllllllllllIIllIllIIIIlII, final int llllllllllllllllllIIllIllIIIIIll, final int llllllllllllllllllIIllIllIIIIIlI) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.mouseClicked(llllllllllllllllllIIllIllIIIIIII, llllllllllllllllllIIllIllIIIIlII, llllllllllllllllllIIllIllIIIIIll, llllllllllllllllllIIllIllIIIIIlI);
    }
    
    public GameState getCurrentState() {
        return this.currentState;
    }
    
    @Override
    public void keyReleased(final int llllllllllllllllllIIllIllIlIlIll, final char llllllllllllllllllIIllIllIlIlIlI) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.keyReleased(llllllllllllllllllIIllIllIlIlIll, llllllllllllllllllIIllIllIlIlIlI);
    }
    
    protected void preRenderState(final GameContainer llllllllllllllllllIIlllIIIIllllI, final Graphics llllllllllllllllllIIlllIIIIlllIl) throws SlickException {
    }
    
    public void addState(final GameState llllllllllllllllllIIlllIIlIIllll) {
        this.states.put(new Integer(llllllllllllllllllIIlllIIlIIllll.getID()), llllllllllllllllllIIlllIIlIIllll);
        if (this.currentState.getID() == -1) {
            this.currentState = llllllllllllllllllIIlllIIlIIllll;
        }
    }
    
    public StateBasedGame(final String llllllllllllllllllIIlllIIllIIIIl) {
        this.states = new HashMap();
        this.title = llllllllllllllllllIIlllIIllIIIIl;
        this.currentState = new BasicGameState() {
            @Override
            public void update(final GameContainer llllIlIlIllIlll, final StateBasedGame llllIlIlIllIllI, final int llllIlIlIllIlIl) throws SlickException {
            }
            
            @Override
            public int getID() {
                return -1;
            }
            
            public void render(final StateBasedGame llllIlIlIlllIlI, final Graphics llllIlIlIlllIIl) throws SlickException {
            }
            
            @Override
            public void render(final GameContainer llllIlIlIllIIll, final StateBasedGame llllIlIlIllIIlI, final Graphics llllIlIlIllIIIl) throws SlickException {
            }
            
            @Override
            public void init(final GameContainer llllIlIlIllllIl, final StateBasedGame llllIlIlIllllII) throws SlickException {
            }
        };
    }
    
    @Override
    public void controllerDownReleased(final int llllllllllllllllllIIllIllllIIIlI) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.controllerDownReleased(llllllllllllllllllIIllIllllIIIlI);
    }
    
    @Override
    public void controllerButtonPressed(final int llllllllllllllllllIIllIlllllIllI, final int llllllllllllllllllIIllIllllllIII) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.controllerButtonPressed(llllllllllllllllllIIllIlllllIllI, llllllllllllllllllIIllIllllllIII);
    }
    
    @Override
    public final void update(final GameContainer llllllllllllllllllIIlllIIIIlIIII, final int llllllllllllllllllIIlllIIIIlIIlI) throws SlickException {
        this.preUpdateState(llllllllllllllllllIIlllIIIIlIIII, llllllllllllllllllIIlllIIIIlIIlI);
        if (this.leaveTransition != null) {
            this.leaveTransition.update(this, llllllllllllllllllIIlllIIIIlIIII, llllllllllllllllllIIlllIIIIlIIlI);
            if (!this.leaveTransition.isComplete()) {
                return;
            }
            this.currentState.leave(llllllllllllllllllIIlllIIIIlIIII, this);
            final GameState llllllllllllllllllIIlllIIIIlIlIl = this.currentState;
            this.currentState = this.nextState;
            this.nextState = null;
            this.leaveTransition = null;
            this.currentState.enter(llllllllllllllllllIIlllIIIIlIIII, this);
            if (this.enterTransition != null) {
                this.enterTransition.init(this.currentState, llllllllllllllllllIIlllIIIIlIlIl);
            }
        }
        if (this.enterTransition != null) {
            this.enterTransition.update(this, llllllllllllllllllIIlllIIIIlIIII, llllllllllllllllllIIlllIIIIlIIlI);
            if (!this.enterTransition.isComplete()) {
                return;
            }
            this.enterTransition = null;
        }
        this.currentState.update(llllllllllllllllllIIlllIIIIlIIII, this, llllllllllllllllllIIlllIIIIlIIlI);
        this.postUpdateState(llllllllllllllllllIIlllIIIIlIIII, llllllllllllllllllIIlllIIIIlIIlI);
    }
    
    @Override
    public void controllerDownPressed(final int llllllllllllllllllIIllIllllIIllI) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.controllerDownPressed(llllllllllllllllllIIllIllllIIllI);
    }
    
    @Override
    public void inputEnded() {
    }
    
    public void enterState(final int llllllllllllllllllIIlllIIlIIIIll) {
        this.enterState(llllllllllllllllllIIlllIIlIIIIll, new EmptyTransition(), new EmptyTransition());
    }
    
    private boolean transitioning() {
        return this.leaveTransition != null || this.enterTransition != null;
    }
    
    @Override
    public void mousePressed(final int llllllllllllllllllIIllIlIlllIlll, final int llllllllllllllllllIIllIlIlllIIlI, final int llllllllllllllllllIIllIlIlllIIIl) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.mousePressed(llllllllllllllllllIIllIlIlllIlll, llllllllllllllllllIIllIlIlllIIlI, llllllllllllllllllIIllIlIlllIIIl);
    }
    
    public int getStateCount() {
        return this.states.keySet().size();
    }
    
    @Override
    public void controllerRightPressed(final int llllllllllllllllllIIllIlllIIlllI) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.controllerRightPressed(llllllllllllllllllIIllIlllIIlllI);
    }
    
    @Override
    public void controllerUpPressed(final int llllllllllllllllllIIllIlllIIIIlI) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.controllerUpPressed(llllllllllllllllllIIllIlllIIIIlI);
    }
    
    public GameState getState(final int llllllllllllllllllIIlllIIlIIlIIl) {
        return this.states.get(new Integer(llllllllllllllllllIIlllIIlIIlIIl));
    }
    
    @Override
    public void controllerButtonReleased(final int llllllllllllllllllIIllIlllllIIII, final int llllllllllllllllllIIllIllllIllll) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.controllerButtonReleased(llllllllllllllllllIIllIlllllIIII, llllllllllllllllllIIllIllllIllll);
    }
    
    public int getCurrentStateID() {
        return this.currentState.getID();
    }
    
    @Override
    public final void render(final GameContainer llllllllllllllllllIIlllIIIlIIIIl, final Graphics llllllllllllllllllIIlllIIIlIIIll) throws SlickException {
        this.preRenderState(llllllllllllllllllIIlllIIIlIIIIl, llllllllllllllllllIIlllIIIlIIIll);
        if (this.leaveTransition != null) {
            this.leaveTransition.preRender(this, llllllllllllllllllIIlllIIIlIIIIl, llllllllllllllllllIIlllIIIlIIIll);
        }
        else if (this.enterTransition != null) {
            this.enterTransition.preRender(this, llllllllllllllllllIIlllIIIlIIIIl, llllllllllllllllllIIlllIIIlIIIll);
        }
        this.currentState.render(llllllllllllllllllIIlllIIIlIIIIl, this, llllllllllllllllllIIlllIIIlIIIll);
        if (this.leaveTransition != null) {
            this.leaveTransition.postRender(this, llllllllllllllllllIIlllIIIlIIIIl, llllllllllllllllllIIlllIIIlIIIll);
        }
        else if (this.enterTransition != null) {
            this.enterTransition.postRender(this, llllllllllllllllllIIlllIIIlIIIIl, llllllllllllllllllIIlllIIIlIIIll);
        }
        this.postRenderState(llllllllllllllllllIIlllIIIlIIIIl, llllllllllllllllllIIlllIIIlIIIll);
    }
    
    @Override
    public void controllerLeftReleased(final int llllllllllllllllllIIllIlllIlIlII) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.controllerLeftReleased(llllllllllllllllllIIllIlllIlIlII);
    }
    
    @Override
    public void controllerUpReleased(final int llllllllllllllllllIIllIllIllllII) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.controllerUpReleased(llllllllllllllllllIIllIllIllllII);
    }
    
    @Override
    public void inputStarted() {
    }
    
    @Override
    public void mouseDragged(final int llllllllllllllllllIIllIllIIIllll, final int llllllllllllllllllIIllIllIIIlllI, final int llllllllllllllllllIIllIllIIlIIlI, final int llllllllllllllllllIIllIllIIIllII) {
        if (this.transitioning()) {
            return;
        }
        this.currentState.mouseDragged(llllllllllllllllllIIllIllIIIllll, llllllllllllllllllIIllIllIIIlllI, llllllllllllllllllIIllIllIIlIIlI, llllllllllllllllllIIllIllIIIllII);
    }
}
