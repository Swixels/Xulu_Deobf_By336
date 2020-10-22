package org.newdawn.slick.tests;

import java.util.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.*;

public class InputTest extends BasicGame
{
    private /* synthetic */ boolean buttonDown;
    private /* synthetic */ Color[] cols;
    private /* synthetic */ int ypos;
    private /* synthetic */ ArrayList lines;
    private /* synthetic */ boolean rshift;
    private /* synthetic */ String message;
    private /* synthetic */ AppGameContainer app;
    private /* synthetic */ int index;
    private /* synthetic */ boolean lshift;
    private /* synthetic */ boolean space;
    private /* synthetic */ Input input;
    private /* synthetic */ float y;
    private /* synthetic */ float x;
    
    @Override
    public void mouseMoved(final int lIlIllIIllIlIII, final int lIlIllIIllIIlll, final int lIlIllIIllIIllI, final int lIlIllIIllIlIlI) {
        if (this.buttonDown) {
            this.lines.add(new Line(lIlIllIIllIlIII, lIlIllIIllIIlll, lIlIllIIllIIllI, lIlIllIIllIlIlI));
        }
    }
    
    @Override
    public void render(final GameContainer lIlIllIlIllllll, final Graphics lIlIllIlIlllIll) {
        lIlIllIlIlllIll.drawString(String.valueOf(new StringBuilder().append("left shift down: ").append(this.lshift)), 100.0f, 240.0f);
        lIlIllIlIlllIll.drawString(String.valueOf(new StringBuilder().append("right shift down: ").append(this.rshift)), 100.0f, 260.0f);
        lIlIllIlIlllIll.drawString(String.valueOf(new StringBuilder().append("space down: ").append(this.space)), 100.0f, 280.0f);
        lIlIllIlIlllIll.setColor(Color.white);
        lIlIllIlIlllIll.drawString(this.message, 10.0f, 50.0f);
        lIlIllIlIlllIll.drawString(String.valueOf(new StringBuilder().append("").append(lIlIllIlIllllll.getInput().getMouseY())), 10.0f, 400.0f);
        lIlIllIlIlllIll.drawString("Use the primary gamepad to control the blob, and hit a gamepad button to change the color", 10.0f, 90.0f);
        for (int lIlIllIllIIIIIl = 0; lIlIllIllIIIIIl < this.lines.size(); ++lIlIllIllIIIIIl) {
            final Line lIlIllIllIIIIlI = this.lines.get(lIlIllIllIIIIIl);
            lIlIllIllIIIIlI.draw(lIlIllIlIlllIll);
        }
        lIlIllIlIlllIll.setColor(this.cols[this.index]);
        lIlIllIlIlllIll.fillOval((float)(int)this.x, (float)(int)this.y, 50.0f, 50.0f);
        lIlIllIlIlllIll.setColor(Color.yellow);
        lIlIllIlIlllIll.fillRect(50.0f, (float)(200 + this.ypos), 40.0f, 40.0f);
    }
    
    @Override
    public void keyReleased(final int lIlIllIlIlIIIIl, final char lIlIllIlIlIIIII) {
        this.message = String.valueOf(new StringBuilder().append("You pressed key code ").append(lIlIllIlIlIIIIl).append(" (character = ").append(lIlIllIlIlIIIII).append(")"));
    }
    
    @Override
    public void mouseWheelMoved(final int lIlIllIIlllIllI) {
        this.message = String.valueOf(new StringBuilder().append("Mouse wheel moved: ").append(lIlIllIIlllIllI));
        if (lIlIllIIlllIllI < 0) {
            this.ypos -= 10;
        }
        if (lIlIllIIlllIllI > 0) {
            this.ypos += 10;
        }
    }
    
    @Override
    public void update(final GameContainer lIlIllIlIllIlII, final int lIlIllIlIllIIII) {
        this.lshift = lIlIllIlIllIlII.getInput().isKeyDown(42);
        this.rshift = lIlIllIlIllIlII.getInput().isKeyDown(54);
        this.space = lIlIllIlIllIlII.getInput().isKeyDown(57);
        if (this.controllerLeft[0]) {
            this.x -= lIlIllIlIllIIII * 0.1f;
        }
        if (this.controllerRight[0]) {
            this.x += lIlIllIlIllIIII * 0.1f;
        }
        if (this.controllerUp[0]) {
            this.y -= lIlIllIlIllIIII * 0.1f;
        }
        if (this.controllerDown[0]) {
            this.y += lIlIllIlIllIIII * 0.1f;
        }
    }
    
    @Override
    public void keyPressed(final int lIlIllIlIlIIlll, final char lIlIllIlIlIlIIl) {
        if (lIlIllIlIlIIlll == 1) {
            System.exit(0);
        }
        if (lIlIllIlIlIIlll == 59 && this.app != null) {
            try {
                this.app.setDisplayMode(600, 600, false);
                this.app.reinit();
            }
            catch (Exception lIlIllIlIlIllII) {
                Log.error(lIlIllIlIlIllII);
            }
        }
    }
    
    public InputTest() {
        super("Input Test");
        this.message = "Press any key, mouse button, or drag the mouse";
        this.lines = new ArrayList();
        this.cols = new Color[] { Color.red, Color.green, Color.blue, Color.white, Color.magenta, Color.cyan };
    }
    
    public static void main(final String[] lIlIllIIlIllIII) {
        try {
            final AppGameContainer lIlIllIIlIllIlI = new AppGameContainer(new InputTest());
            lIlIllIIlIllIlI.setDisplayMode(800, 600, false);
            lIlIllIIlIllIlI.start();
        }
        catch (SlickException lIlIllIIlIllIIl) {
            lIlIllIIlIllIIl.printStackTrace();
        }
    }
    
    @Override
    public void controllerButtonPressed(final int lIlIllIIllIIIII, final int lIlIllIIlIlllll) {
        super.controllerButtonPressed(lIlIllIIllIIIII, lIlIllIIlIlllll);
        ++this.index;
        this.index %= this.cols.length;
    }
    
    @Override
    public void mousePressed(final int lIlIllIlIIlIIll, final int lIlIllIlIIlIIlI, final int lIlIllIlIIlIlIl) {
        if (lIlIllIlIIlIIll == 0) {
            this.buttonDown = true;
        }
        this.message = String.valueOf(new StringBuilder().append("Mouse pressed ").append(lIlIllIlIIlIIll).append(" ").append(lIlIllIlIIlIIlI).append(",").append(lIlIllIlIIlIlIl));
    }
    
    @Override
    public void mouseReleased(final int lIlIllIlIIIlIll, final int lIlIllIlIIIlIlI, final int lIlIllIlIIIlIIl) {
        if (lIlIllIlIIIlIll == 0) {
            this.buttonDown = false;
        }
        this.message = String.valueOf(new StringBuilder().append("Mouse released ").append(lIlIllIlIIIlIll).append(" ").append(lIlIllIlIIIlIlI).append(",").append(lIlIllIlIIIlIIl));
    }
    
    @Override
    public void mouseClicked(final int lIlIllIlIIIIIII, final int lIlIllIIlllllII, final int lIlIllIIllllllI, final int lIlIllIIllllIlI) {
        System.out.println(String.valueOf(new StringBuilder().append("CLICKED:").append(lIlIllIIlllllII).append(",").append(lIlIllIIllllllI).append(" ").append(lIlIllIIllllIlI)));
    }
    
    @Override
    public void init(final GameContainer lIlIllIllIIlIlI) throws SlickException {
        if (lIlIllIllIIlIlI instanceof AppGameContainer) {
            this.app = (AppGameContainer)lIlIllIllIIlIlI;
        }
        this.input = lIlIllIllIIlIlI.getInput();
        this.x = 300.0f;
        this.y = 300.0f;
    }
    
    private class Line
    {
        private /* synthetic */ int oldy;
        private /* synthetic */ int newx;
        private /* synthetic */ int oldx;
        private /* synthetic */ int newy;
        
        public Line(final int llIlIllIIllIIl, final int llIlIllIIllIII, final int llIlIllIIlIlll, final int llIlIllIIlIIII) {
            this.oldx = llIlIllIIllIIl;
            this.oldy = llIlIllIIllIII;
            this.newx = llIlIllIIlIlll;
            this.newy = llIlIllIIlIIII;
        }
        
        public void draw(final Graphics llIlIllIIIlIlI) {
            llIlIllIIIlIlI.drawLine((float)this.oldx, (float)this.oldy, (float)this.newx, (float)this.newy);
        }
    }
}
