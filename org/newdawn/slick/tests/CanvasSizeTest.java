package org.newdawn.slick.tests;

import org.newdawn.slick.*;
import java.awt.*;
import java.awt.event.*;
import org.newdawn.slick.util.*;

public class CanvasSizeTest extends BasicGame
{
    @Override
    public void init(final GameContainer llllllllllIIlI) throws SlickException {
        System.out.println(String.valueOf(new StringBuilder().append(llllllllllIIlI.getWidth()).append(", ").append(llllllllllIIlI.getHeight())));
    }
    
    @Override
    public void render(final GameContainer lllllllllIllll, final Graphics lllllllllIlllI) throws SlickException {
    }
    
    public static void main(final String[] lllllllllIIlIl) {
        try {
            final CanvasGameContainer lllllllllIlIII = new CanvasGameContainer(new CanvasSizeTest());
            lllllllllIlIII.setSize(640, 480);
            final Frame lllllllllIIlll = new Frame("Test");
            lllllllllIIlll.setLayout(new GridLayout(1, 2));
            lllllllllIIlll.add(lllllllllIlIII);
            lllllllllIIlll.pack();
            lllllllllIIlll.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(final WindowEvent llllllllllllllllllIIIIIIIIlIIIIl) {
                    System.exit(0);
                }
            });
            lllllllllIIlll.setVisible(true);
            lllllllllIlIII.start();
        }
        catch (Exception lllllllllIIllI) {
            Log.error(lllllllllIIllI);
        }
    }
    
    public CanvasSizeTest() {
        super("Test");
    }
    
    @Override
    public void update(final GameContainer lllllllllIllII, final int lllllllllIlIll) throws SlickException {
    }
}
