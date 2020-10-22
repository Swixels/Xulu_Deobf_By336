package org.newdawn.slick.tests;

import org.newdawn.slick.*;
import org.newdawn.slick.command.*;

public class InputProviderTest extends BasicGame implements InputProviderListener
{
    private /* synthetic */ Command run;
    private /* synthetic */ InputProvider provider;
    private /* synthetic */ Command attack;
    private /* synthetic */ Command jump;
    private /* synthetic */ String message;
    
    @Override
    public void controlReleased(final Command lIlIlllllIIIIIl) {
        this.message = String.valueOf(new StringBuilder().append("Released: ").append(lIlIlllllIIIIIl));
    }
    
    @Override
    public void render(final GameContainer lIlIlllllIlIIll, final Graphics lIlIlllllIlIIlI) {
        lIlIlllllIlIIlI.drawString("Press A, W, Left, Up, space, mouse button 1,and gamepad controls", 10.0f, 50.0f);
        lIlIlllllIlIIlI.drawString(this.message, 100.0f, 150.0f);
    }
    
    public static void main(final String[] lIlIllllIllllIl) {
        try {
            final AppGameContainer lIlIllllIllllll = new AppGameContainer(new InputProviderTest());
            lIlIllllIllllll.setDisplayMode(800, 600, false);
            lIlIllllIllllll.start();
        }
        catch (SlickException lIlIllllIlllllI) {
            lIlIllllIlllllI.printStackTrace();
        }
    }
    
    @Override
    public void controlPressed(final Command lIlIlllllIIlIIl) {
        this.message = String.valueOf(new StringBuilder().append("Pressed: ").append(lIlIlllllIIlIIl));
    }
    
    public InputProviderTest() {
        super("InputProvider Test");
        this.attack = new BasicCommand("attack");
        this.jump = new BasicCommand("jump");
        this.run = new BasicCommand("run");
        this.message = "";
    }
    
    @Override
    public void init(final GameContainer lIlIlllllIlIlll) throws SlickException {
        this.provider = new InputProvider(lIlIlllllIlIlll.getInput());
        this.provider.addListener(this);
        this.provider.bindCommand(new KeyControl(203), this.run);
        this.provider.bindCommand(new KeyControl(30), this.run);
        this.provider.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.LEFT), this.run);
        this.provider.bindCommand(new KeyControl(200), this.jump);
        this.provider.bindCommand(new KeyControl(17), this.jump);
        this.provider.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.UP), this.jump);
        this.provider.bindCommand(new KeyControl(57), this.attack);
        this.provider.bindCommand(new MouseButtonControl(0), this.attack);
        this.provider.bindCommand(new ControllerButtonControl(0, 1), this.attack);
    }
    
    @Override
    public void update(final GameContainer lIlIlllllIIlllI, final int lIlIlllllIIllIl) {
    }
}
