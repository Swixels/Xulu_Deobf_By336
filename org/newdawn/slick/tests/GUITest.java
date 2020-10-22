package org.newdawn.slick.tests;

import org.newdawn.slick.gui.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.*;

public class GUITest extends BasicGame implements ComponentListener
{
    private /* synthetic */ String message;
    private /* synthetic */ MouseOverArea[] areas;
    private /* synthetic */ AppGameContainer app;
    private /* synthetic */ TextField field;
    private /* synthetic */ Image image;
    private /* synthetic */ TextField field2;
    private /* synthetic */ GameContainer container;
    private /* synthetic */ Font font;
    private /* synthetic */ Image background;
    
    public GUITest() {
        super("GUI Test");
        this.areas = new MouseOverArea[4];
        this.message = "Demo Menu System with stock images";
    }
    
    @Override
    public void init(final GameContainer lIlIIIIllIlIIII) throws SlickException {
        if (lIlIIIIllIlIIII instanceof AppGameContainer) {
            this.app = (AppGameContainer)lIlIIIIllIlIIII;
            this.app.setIcon("testdata/icon.tga");
        }
        this.font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
        this.field = new TextField(lIlIIIIllIlIIII, this.font, 150, 20, 500, 35, new ComponentListener() {
            @Override
            public void componentActivated(final AbstractComponent lllllllllllllllllIIlIIIIlIIIIIll) {
                GUITest.this.message = String.valueOf(new StringBuilder().append("Entered1: ").append(GUITest.this.field.getText()));
                GUITest.this.field2.setFocus(true);
            }
        });
        this.field2 = new TextField(lIlIIIIllIlIIII, this.font, 150, 70, 500, 35, new ComponentListener() {
            @Override
            public void componentActivated(final AbstractComponent llllllllllllllllllIIllIIlIIllllI) {
                GUITest.this.message = String.valueOf(new StringBuilder().append("Entered2: ").append(GUITest.this.field2.getText()));
                GUITest.this.field.setFocus(true);
            }
        });
        this.field2.setBorderColor(Color.red);
        this.container = lIlIIIIllIlIIII;
        this.image = new Image("testdata/logo.tga");
        this.background = new Image("testdata/dungeontiles.gif");
        lIlIIIIllIlIIII.setMouseCursor("testdata/cursor.tga", 0, 0);
        for (int lIlIIIIllIlIIlI = 0; lIlIIIIllIlIIlI < 4; ++lIlIIIIllIlIIlI) {
            (this.areas[lIlIIIIllIlIIlI] = new MouseOverArea(lIlIIIIllIlIIII, this.image, 300, 100 + lIlIIIIllIlIIlI * 100, 200, 90, this)).setNormalColor(new Color(1.0f, 1.0f, 1.0f, 0.8f));
            this.areas[lIlIIIIllIlIIlI].setMouseOverColor(new Color(1.0f, 1.0f, 1.0f, 0.9f));
        }
    }
    
    @Override
    public void update(final GameContainer lIlIIIIlIllllll, final int lIlIIIIlIlllllI) {
    }
    
    public static void main(final String[] lIlIIIIlIllIIII) {
        try {
            final AppGameContainer lIlIIIIlIllIIlI = new AppGameContainer(new GUITest());
            lIlIIIIlIllIIlI.setDisplayMode(800, 600, false);
            lIlIIIIlIllIIlI.start();
        }
        catch (SlickException lIlIIIIlIllIIIl) {
            lIlIIIIlIllIIIl.printStackTrace();
        }
    }
    
    @Override
    public void componentActivated(final AbstractComponent lIlIIIIlIlIlIIl) {
        System.out.println(String.valueOf(new StringBuilder().append("ACTIVL : ").append(lIlIIIIlIlIlIIl)));
        for (int lIlIIIIlIlIlIll = 0; lIlIIIIlIlIlIll < 4; ++lIlIIIIlIlIlIll) {
            if (lIlIIIIlIlIlIIl == this.areas[lIlIIIIlIlIlIll]) {
                this.message = String.valueOf(new StringBuilder().append("Option ").append(lIlIIIIlIlIlIll + 1).append(" pressed!"));
            }
        }
        if (lIlIIIIlIlIlIIl == this.field2) {}
    }
    
    @Override
    public void keyPressed(final int lIlIIIIlIllIlIl, final char lIlIIIIlIllIlll) {
        if (lIlIIIIlIllIlIl == 1) {
            System.exit(0);
        }
        if (lIlIIIIlIllIlIl == 60) {
            this.app.setDefaultMouseCursor();
        }
        if (lIlIIIIlIllIlIl == 59 && this.app != null) {
            try {
                this.app.setDisplayMode(640, 480, false);
            }
            catch (SlickException lIlIIIIlIlllIlI) {
                Log.error(lIlIIIIlIlllIlI);
            }
        }
    }
    
    @Override
    public void render(final GameContainer lIlIIIIllIIIIll, final Graphics lIlIIIIllIIIIlI) {
        this.background.draw(0.0f, 0.0f, 800.0f, 500.0f);
        for (int lIlIIIIllIIlIII = 0; lIlIIIIllIIlIII < 4; ++lIlIIIIllIIlIII) {
            this.areas[lIlIIIIllIIlIII].render(lIlIIIIllIIIIll, lIlIIIIllIIIIlI);
        }
        this.field.render(lIlIIIIllIIIIll, lIlIIIIllIIIIlI);
        this.field2.render(lIlIIIIllIIIIll, lIlIIIIllIIIIlI);
        lIlIIIIllIIIIlI.setFont(this.font);
        lIlIIIIllIIIIlI.drawString(this.message, 200.0f, 550.0f);
    }
}
