package org.newdawn.slick.tests;

import org.newdawn.slick.gui.*;
import org.newdawn.slick.*;

public class SavedStateTest extends BasicGame implements ComponentListener
{
    private /* synthetic */ String message;
    private /* synthetic */ int ageValue;
    private /* synthetic */ TextField name;
    private /* synthetic */ String nameValue;
    private /* synthetic */ TextField age;
    private static /* synthetic */ AppGameContainer container;
    private /* synthetic */ SavedState state;
    
    @Override
    public void update(final GameContainer llllllllllllllllllIllIllIlllIlII, final int llllllllllllllllllIllIllIlllIIll) throws SlickException {
    }
    
    @Override
    public void init(final GameContainer llllllllllllllllllIllIllIlllllll) throws SlickException {
        this.state = new SavedState("testdata");
        this.nameValue = this.state.getString("name", "DefaultName");
        this.ageValue = (int)this.state.getNumber("age", 64.0);
        this.name = new TextField(llllllllllllllllllIllIllIlllllll, llllllllllllllllllIllIllIlllllll.getDefaultFont(), 100, 100, 300, 20, this);
        this.age = new TextField(llllllllllllllllllIllIllIlllllll, llllllllllllllllllIllIllIlllllll.getDefaultFont(), 100, 150, 201, 20, this);
    }
    
    @Override
    public void render(final GameContainer llllllllllllllllllIllIllIllllIlI, final Graphics llllllllllllllllllIllIllIlllIllI) {
        this.name.render(llllllllllllllllllIllIllIllllIlI, llllllllllllllllllIllIllIlllIllI);
        this.age.render(llllllllllllllllllIllIllIllllIlI, llllllllllllllllllIllIllIlllIllI);
        llllllllllllllllllIllIllIllllIlI.getDefaultFont().drawString(100.0f, 300.0f, String.valueOf(new StringBuilder().append("Stored Name: ").append(this.nameValue)));
        llllllllllllllllllIllIllIllllIlI.getDefaultFont().drawString(100.0f, 350.0f, String.valueOf(new StringBuilder().append("Stored Age: ").append(this.ageValue)));
        llllllllllllllllllIllIllIllllIlI.getDefaultFont().drawString(200.0f, 500.0f, this.message);
    }
    
    public SavedStateTest() {
        super("Saved State Test");
        this.nameValue = "none";
        this.ageValue = 0;
        this.message = "Enter a name and age to store";
    }
    
    @Override
    public void componentActivated(final AbstractComponent llllllllllllllllllIllIllIllIIlII) {
        if (llllllllllllllllllIllIllIllIIlII == this.name) {
            this.nameValue = this.name.getText();
            this.state.setString("name", this.nameValue);
        }
        if (llllllllllllllllllIllIllIllIIlII == this.age) {
            try {
                this.ageValue = Integer.parseInt(this.age.getText());
                this.state.setNumber("age", this.ageValue);
            }
            catch (NumberFormatException ex) {}
        }
        try {
            this.state.save();
        }
        catch (Exception llllllllllllllllllIllIllIllIIllI) {
            this.message = String.valueOf(new StringBuilder().append(System.currentTimeMillis()).append(" : Failed to save state"));
        }
    }
    
    @Override
    public void keyPressed(final int llllllllllllllllllIllIllIlllIIII, final char llllllllllllllllllIllIllIllIllll) {
        if (llllllllllllllllllIllIllIlllIIII == 1) {
            System.exit(0);
        }
    }
    
    public static void main(final String[] llllllllllllllllllIllIllIllIlIll) {
        try {
            (SavedStateTest.container = new AppGameContainer(new SavedStateTest())).setDisplayMode(800, 600, false);
            SavedStateTest.container.start();
        }
        catch (SlickException llllllllllllllllllIllIllIllIllII) {
            llllllllllllllllllIllIllIllIllII.printStackTrace();
        }
    }
}
