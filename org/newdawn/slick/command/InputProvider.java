package org.newdawn.slick.command;

import org.newdawn.slick.*;
import java.util.*;
import org.newdawn.slick.util.*;

public class InputProvider
{
    private /* synthetic */ ArrayList listeners;
    private /* synthetic */ boolean active;
    private /* synthetic */ Input input;
    private /* synthetic */ HashMap commandState;
    private /* synthetic */ HashMap commands;
    
    public void addListener(final InputProviderListener lllllllllllllllllllIIlIIllIlIIII) {
        this.listeners.add(lllllllllllllllllllIIlIIllIlIIII);
    }
    
    private CommandState getState(final Command lllllllllllllllllllIIIllllllIIll) {
        return this.commandState.get(lllllllllllllllllllIIIllllllIIll);
    }
    
    public void removeListener(final InputProviderListener lllllllllllllllllllIIlIIllIIllII) {
        this.listeners.remove(lllllllllllllllllllIIlIIllIIllII);
    }
    
    public boolean isCommandControlPressed(final Command lllllllllllllllllllIIIlllllIIlIl) {
        return this.getState(lllllllllllllllllllIIIlllllIIlIl).isPressed();
    }
    
    protected void fireReleased(final Command lllllllllllllllllllIIIllllIlIlII) {
        this.getState(lllllllllllllllllllIIIllllIlIlII).down = false;
        if (!this.isActive()) {
            return;
        }
        for (int lllllllllllllllllllIIIllllIllIII = 0; lllllllllllllllllllIIIllllIllIII < this.listeners.size(); ++lllllllllllllllllllIIIllllIllIII) {
            this.listeners.get(lllllllllllllllllllIIIllllIllIII).controlReleased(lllllllllllllllllllIIIllllIlIlII);
        }
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public boolean isCommandControlDown(final Command lllllllllllllllllllIIIlllllIllIl) {
        return this.getState(lllllllllllllllllllIIIlllllIllIl).isDown();
    }
    
    public List getUniqueCommands() {
        final List lllllllllllllllllllIIlIIlllllIII = new ArrayList();
        for (final Command lllllllllllllllllllIIlIIlllllIll : this.commands.values()) {
            if (!lllllllllllllllllllIIlIIlllllIII.contains(lllllllllllllllllllIIlIIlllllIll)) {
                lllllllllllllllllllIIlIIlllllIII.add(lllllllllllllllllllIIlIIlllllIll);
            }
        }
        return lllllllllllllllllllIIlIIlllllIII;
    }
    
    public InputProvider(final Input lllllllllllllllllllIIlIlIIIIIIII) {
        this.listeners = new ArrayList();
        this.commandState = new HashMap();
        this.active = true;
        this.input = lllllllllllllllllllIIlIlIIIIIIII;
        lllllllllllllllllllIIlIlIIIIIIII.addListener(new InputListenerImpl());
        this.commands = new HashMap();
    }
    
    public void bindCommand(final Control lllllllllllllllllllIIlIIllIIIlIl, final Command lllllllllllllllllllIIlIIllIIIIIl) {
        this.commands.put(lllllllllllllllllllIIlIIllIIIlIl, lllllllllllllllllllIIlIIllIIIIIl);
        if (this.commandState.get(lllllllllllllllllllIIlIIllIIIIIl) == null) {
            this.commandState.put(lllllllllllllllllllIIlIIllIIIIIl, new CommandState());
        }
    }
    
    protected void firePressed(final Command lllllllllllllllllllIIIllllIlllIl) {
        this.getState(lllllllllllllllllllIIIllllIlllIl).down = true;
        this.getState(lllllllllllllllllllIIIllllIlllIl).pressed = true;
        if (!this.isActive()) {
            return;
        }
        for (int lllllllllllllllllllIIIlllllIIIIl = 0; lllllllllllllllllllIIIlllllIIIIl < this.listeners.size(); ++lllllllllllllllllllIIIlllllIIIIl) {
            this.listeners.get(lllllllllllllllllllIIIlllllIIIIl).controlPressed(lllllllllllllllllllIIIllllIlllIl);
        }
    }
    
    public void unbindCommand(final Control lllllllllllllllllllIIIlllllllIll) {
        final Command lllllllllllllllllllIIIlllllllIlI = this.commands.remove(lllllllllllllllllllIIIlllllllIll);
        if (lllllllllllllllllllIIIlllllllIlI != null && !this.commands.keySet().contains(lllllllllllllllllllIIIlllllllIlI)) {
            this.commandState.remove(lllllllllllllllllllIIIlllllllIlI);
        }
    }
    
    public List getControlsFor(final Command lllllllllllllllllllIIlIIlllIIlll) {
        final List lllllllllllllllllllIIlIIlllIIllI = new ArrayList();
        for (final Map.Entry lllllllllllllllllllIIlIIlllIllII : this.commands.entrySet()) {
            final Control lllllllllllllllllllIIlIIlllIlIll = lllllllllllllllllllIIlIIlllIllII.getKey();
            final Command lllllllllllllllllllIIlIIlllIlIlI = lllllllllllllllllllIIlIIlllIllII.getValue();
            if (lllllllllllllllllllIIlIIlllIlIlI == lllllllllllllllllllIIlIIlllIIlll) {
                lllllllllllllllllllIIlIIlllIIllI.add(lllllllllllllllllllIIlIIlllIlIll);
            }
        }
        return lllllllllllllllllllIIlIIlllIIllI;
    }
    
    public void clearCommand(final Command lllllllllllllllllllIIlIIIIIIIIlI) {
        final List lllllllllllllllllllIIlIIIIIIIlII = this.getControlsFor(lllllllllllllllllllIIlIIIIIIIIlI);
        for (int lllllllllllllllllllIIlIIIIIIIlll = 0; lllllllllllllllllllIIlIIIIIIIlll < lllllllllllllllllllIIlIIIIIIIlII.size(); ++lllllllllllllllllllIIlIIIIIIIlll) {
            this.unbindCommand(lllllllllllllllllllIIlIIIIIIIlII.get(lllllllllllllllllllIIlIIIIIIIlll));
        }
    }
    
    public void setActive(final boolean lllllllllllllllllllIIlIIllIllIll) {
        this.active = lllllllllllllllllllIIlIIllIllIll;
    }
    
    private class InputListenerImpl extends InputAdapter
    {
        @Override
        public void controllerLeftReleased(final int lllllllllllllllllIIIllIllIIlIIlI) {
            final Command lllllllllllllllllIIIllIllIIlIlII = InputProvider.this.commands.get(new ControllerDirectionControl(lllllllllllllllllIIIllIllIIlIIlI, ControllerDirectionControl.LEFT));
            if (lllllllllllllllllIIIllIllIIlIlII != null) {
                InputProvider.this.fireReleased(lllllllllllllllllIIIllIllIIlIlII);
            }
        }
        
        @Override
        public void mouseReleased(final int lllllllllllllllllIIIllIllIlIlIIl, final int lllllllllllllllllIIIllIllIlIlIII, final int lllllllllllllllllIIIllIllIlIIlll) {
            final Command lllllllllllllllllIIIllIllIlIIllI = InputProvider.this.commands.get(new MouseButtonControl(lllllllllllllllllIIIllIllIlIlIIl));
            if (lllllllllllllllllIIIllIllIlIIllI != null) {
                InputProvider.this.fireReleased(lllllllllllllllllIIIllIllIlIIllI);
            }
        }
        
        @Override
        public void controllerDownPressed(final int lllllllllllllllllIIIllIlIllIIlIl) {
            final Command lllllllllllllllllIIIllIlIllIIlll = InputProvider.this.commands.get(new ControllerDirectionControl(lllllllllllllllllIIIllIlIllIIlIl, ControllerDirectionControl.DOWN));
            if (lllllllllllllllllIIIllIlIllIIlll != null) {
                InputProvider.this.firePressed(lllllllllllllllllIIIllIlIllIIlll);
            }
        }
        
        @Override
        public void controllerButtonReleased(final int lllllllllllllllllIIIllIlIlIIIlIl, final int lllllllllllllllllIIIllIlIlIIlIII) {
            final Command lllllllllllllllllIIIllIlIlIIIlll = InputProvider.this.commands.get(new ControllerButtonControl(lllllllllllllllllIIIllIlIlIIIlIl, lllllllllllllllllIIIllIlIlIIlIII));
            if (lllllllllllllllllIIIllIlIlIIIlll != null) {
                InputProvider.this.fireReleased(lllllllllllllllllIIIllIlIlIIIlll);
            }
        }
        
        @Override
        public void controllerButtonPressed(final int lllllllllllllllllIIIllIlIlIlIlIl, final int lllllllllllllllllIIIllIlIlIlIIII) {
            final Command lllllllllllllllllIIIllIlIlIlIIll = InputProvider.this.commands.get(new ControllerButtonControl(lllllllllllllllllIIIllIlIlIlIlIl, lllllllllllllllllIIIllIlIlIlIIII));
            if (lllllllllllllllllIIIllIlIlIlIIll != null) {
                InputProvider.this.firePressed(lllllllllllllllllIIIllIlIlIlIIll);
            }
        }
        
        @Override
        public void keyPressed(final int lllllllllllllllllIIIllIlllIIIlII, final char lllllllllllllllllIIIllIlllIIIlll) {
            final Command lllllllllllllllllIIIllIlllIIIllI = InputProvider.this.commands.get(new KeyControl(lllllllllllllllllIIIllIlllIIIlII));
            if (lllllllllllllllllIIIllIlllIIIllI != null) {
                InputProvider.this.firePressed(lllllllllllllllllIIIllIlllIIIllI);
            }
        }
        
        @Override
        public void keyReleased(final int lllllllllllllllllIIIllIllIlllIlI, final char lllllllllllllllllIIIllIllIllllIl) {
            final Command lllllllllllllllllIIIllIllIllllII = InputProvider.this.commands.get(new KeyControl(lllllllllllllllllIIIllIllIlllIlI));
            if (lllllllllllllllllIIIllIllIllllII != null) {
                InputProvider.this.fireReleased(lllllllllllllllllIIIllIllIllllII);
            }
        }
        
        @Override
        public void controllerRightReleased(final int lllllllllllllllllIIIllIllIIIIIll) {
            final Command lllllllllllllllllIIIllIllIIIIIlI = InputProvider.this.commands.get(new ControllerDirectionControl(lllllllllllllllllIIIllIllIIIIIll, ControllerDirectionControl.RIGHT));
            if (lllllllllllllllllIIIllIllIIIIIlI != null) {
                InputProvider.this.fireReleased(lllllllllllllllllIIIllIllIIIIIlI);
            }
        }
        
        @Override
        public void controllerUpPressed(final int lllllllllllllllllIIIllIlIllllIlI) {
            final Command lllllllllllllllllIIIllIlIllllIIl = InputProvider.this.commands.get(new ControllerDirectionControl(lllllllllllllllllIIIllIlIllllIlI, ControllerDirectionControl.UP));
            if (lllllllllllllllllIIIllIlIllllIIl != null) {
                InputProvider.this.firePressed(lllllllllllllllllIIIllIlIllllIIl);
            }
        }
        
        @Override
        public void controllerUpReleased(final int lllllllllllllllllIIIllIlIllIlllI) {
            final Command lllllllllllllllllIIIllIlIlllIIII = InputProvider.this.commands.get(new ControllerDirectionControl(lllllllllllllllllIIIllIlIllIlllI, ControllerDirectionControl.UP));
            if (lllllllllllllllllIIIllIlIlllIIII != null) {
                InputProvider.this.fireReleased(lllllllllllllllllIIIllIlIlllIIII);
            }
        }
        
        @Override
        public void controllerDownReleased(final int lllllllllllllllllIIIllIlIlIlllll) {
            final Command lllllllllllllllllIIIllIlIlIllllI = InputProvider.this.commands.get(new ControllerDirectionControl(lllllllllllllllllIIIllIlIlIlllll, ControllerDirectionControl.DOWN));
            if (lllllllllllllllllIIIllIlIlIllllI != null) {
                InputProvider.this.fireReleased(lllllllllllllllllIIIllIlIlIllllI);
            }
        }
        
        @Override
        public void controllerRightPressed(final int lllllllllllllllllIIIllIllIIIllII) {
            final Command lllllllllllllllllIIIllIllIIIlIll = InputProvider.this.commands.get(new ControllerDirectionControl(lllllllllllllllllIIIllIllIIIllII, ControllerDirectionControl.RIGHT));
            if (lllllllllllllllllIIIllIllIIIlIll != null) {
                InputProvider.this.firePressed(lllllllllllllllllIIIllIllIIIlIll);
            }
        }
        
        @Override
        public void controllerLeftPressed(final int lllllllllllllllllIIIllIllIIllllI) {
            final Command lllllllllllllllllIIIllIllIIlllIl = InputProvider.this.commands.get(new ControllerDirectionControl(lllllllllllllllllIIIllIllIIllllI, ControllerDirectionControl.LEFT));
            if (lllllllllllllllllIIIllIllIIlllIl != null) {
                InputProvider.this.firePressed(lllllllllllllllllIIIllIllIIlllIl);
            }
        }
        
        @Override
        public boolean isAcceptingInput() {
            return true;
        }
        
        @Override
        public void mousePressed(final int lllllllllllllllllIIIllIllIllIlII, final int lllllllllllllllllIIIllIllIllIIll, final int lllllllllllllllllIIIllIllIllIIlI) {
            final Command lllllllllllllllllIIIllIllIllIIIl = InputProvider.this.commands.get(new MouseButtonControl(lllllllllllllllllIIIllIllIllIlII));
            if (lllllllllllllllllIIIllIllIllIIIl != null) {
                InputProvider.this.firePressed(lllllllllllllllllIIIllIllIllIIIl);
            }
        }
    }
    
    private class CommandState
    {
        private /* synthetic */ boolean down;
        private /* synthetic */ boolean pressed;
        
        public boolean isPressed() {
            if (this.pressed) {
                this.pressed = false;
                return true;
            }
            return false;
        }
        
        public boolean isDown() {
            return this.down;
        }
    }
}
