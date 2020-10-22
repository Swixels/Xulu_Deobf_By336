package org.newdawn.slick.tests;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class GeomAccuracyTest extends BasicGame
{
    private /* synthetic */ int curTest;
    private /* synthetic */ GameContainer container;
    private /* synthetic */ Color geomColor;
    private /* synthetic */ boolean hideOverlay;
    private /* synthetic */ int colorIndex;
    private /* synthetic */ Image magImage;
    private /* synthetic */ Color overlayColor;
    
    public static void main(final String[] lllllllllllllllllIIIllIIlIIIlIIl) {
        try {
            final AppGameContainer lllllllllllllllllIIIllIIlIIIlIll = new AppGameContainer(new GeomAccuracyTest());
            lllllllllllllllllIIIllIIlIIIlIll.setDisplayMode(800, 600, false);
            lllllllllllllllllIIIllIIlIIIlIll.start();
        }
        catch (SlickException lllllllllllllllllIIIllIIlIIIlIlI) {
            lllllllllllllllllIIIllIIlIIIlIlI.printStackTrace();
        }
    }
    
    void ovalTest(final Graphics lllllllllllllllllIIIllIIlIlIIlll) {
        lllllllllllllllllIIIllIIlIlIIlll.setColor(this.geomColor);
        lllllllllllllllllIIIllIIlIlIIlll.drawOval(100.0f, 100.0f, 99.0f, 99.0f);
        lllllllllllllllllIIIllIIlIlIIlll.fillOval(100.0f, 250.0f, 99.0f, 99.0f);
        Ellipse lllllllllllllllllIIIllIIlIlIlIIl = new Ellipse(449.0f, 149.0f, 49.0f, 49.0f);
        lllllllllllllllllIIIllIIlIlIIlll.draw(lllllllllllllllllIIIllIIlIlIlIIl);
        lllllllllllllllllIIIllIIlIlIlIIl = new Ellipse(449.0f, 299.0f, 49.0f, 49.0f);
        lllllllllllllllllIIIllIIlIlIIlll.fill(lllllllllllllllllIIIllIIlIlIlIIl);
        if (!this.hideOverlay) {
            lllllllllllllllllIIIllIIlIlIIlll.setColor(this.overlayColor);
            lllllllllllllllllIIIllIIlIlIIlll.drawLine(100.0f, 149.0f, 198.0f, 149.0f);
            lllllllllllllllllIIIllIIlIlIIlll.drawLine(149.0f, 100.0f, 149.0f, 198.0f);
            lllllllllllllllllIIIllIIlIlIIlll.drawLine(100.0f, 299.0f, 198.0f, 299.0f);
            lllllllllllllllllIIIllIIlIlIIlll.drawLine(149.0f, 250.0f, 149.0f, 348.0f);
            lllllllllllllllllIIIllIIlIlIIlll.drawLine(400.0f, 149.0f, 498.0f, 149.0f);
            lllllllllllllllllIIIllIIlIlIIlll.drawLine(449.0f, 100.0f, 449.0f, 198.0f);
            lllllllllllllllllIIIllIIlIlIIlll.drawLine(400.0f, 299.0f, 498.0f, 299.0f);
            lllllllllllllllllIIIllIIlIlIIlll.drawLine(449.0f, 250.0f, 449.0f, 348.0f);
        }
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIIIllIIlIIllIII, final int lllllllllllllllllIIIllIIlIIlIlll) {
    }
    
    @Override
    public void keyPressed(final int lllllllllllllllllIIIllIIlIIlIIll, final char lllllllllllllllllIIIllIIlIIlIIlI) {
        if (lllllllllllllllllIIIllIIlIIlIIll == 1) {
            System.exit(0);
        }
        if (lllllllllllllllllIIIllIIlIIlIIll == 49) {
            ++this.curTest;
            this.curTest %= 3;
        }
        if (lllllllllllllllllIIIllIIlIIlIIll == 46) {
            ++this.colorIndex;
            this.colorIndex %= 4;
            this.setColors();
        }
        if (lllllllllllllllllIIIllIIlIIlIIll == 20) {
            this.hideOverlay = !this.hideOverlay;
        }
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIIIllIIlIllIlll, final Graphics lllllllllllllllllIIIllIIlIlllIlI) {
        String lllllllllllllllllIIIllIIlIlllIIl = new String();
        switch (this.curTest) {
            case 0: {
                lllllllllllllllllIIIllIIlIlllIIl = "Rectangles";
                this.rectTest(lllllllllllllllllIIIllIIlIlllIlI);
                break;
            }
            case 1: {
                lllllllllllllllllIIIllIIlIlllIIl = "Ovals";
                this.ovalTest(lllllllllllllllllIIIllIIlIlllIlI);
                break;
            }
            case 2: {
                lllllllllllllllllIIIllIIlIlllIIl = "Arcs";
                this.arcTest(lllllllllllllllllIIIllIIlIlllIlI);
                break;
            }
        }
        lllllllllllllllllIIIllIIlIlllIlI.setColor(Color.white);
        lllllllllllllllllIIIllIIlIlllIlI.drawString("Press T to toggle overlay", 200.0f, 55.0f);
        lllllllllllllllllIIIllIIlIlllIlI.drawString("Press N to switch tests", 200.0f, 35.0f);
        lllllllllllllllllIIIllIIlIlllIlI.drawString("Press C to cycle drawing colors", 200.0f, 15.0f);
        lllllllllllllllllIIIllIIlIlllIlI.drawString("Current Test:", 400.0f, 35.0f);
        lllllllllllllllllIIIllIIlIlllIlI.setColor(Color.blue);
        lllllllllllllllllIIIllIIlIlllIlI.drawString(lllllllllllllllllIIIllIIlIlllIIl, 485.0f, 35.0f);
        lllllllllllllllllIIIllIIlIlllIlI.setColor(Color.white);
        lllllllllllllllllIIIllIIlIlllIlI.drawString("Normal:", 10.0f, 150.0f);
        lllllllllllllllllIIIllIIlIlllIlI.drawString("Filled:", 10.0f, 300.0f);
        lllllllllllllllllIIIllIIlIlllIlI.drawString("Drawn with Graphics context", 125.0f, 400.0f);
        lllllllllllllllllIIIllIIlIlllIlI.drawString("Drawn using Shapes", 450.0f, 400.0f);
        lllllllllllllllllIIIllIIlIlllIlI.copyArea(this.magImage, lllllllllllllllllIIIllIIlIllIlll.getInput().getMouseX() - 10, lllllllllllllllllIIIllIIlIllIlll.getInput().getMouseY() - 10);
        this.magImage.draw(351.0f, 451.0f, 5.0f);
        lllllllllllllllllIIIllIIlIlllIlI.drawString("Mag Area -", 250.0f, 475.0f);
        lllllllllllllllllIIIllIIlIlllIlI.setColor(Color.darkGray);
        lllllllllllllllllIIIllIIlIlllIlI.drawRect(350.0f, 450.0f, 106.0f, 106.0f);
        lllllllllllllllllIIIllIIlIlllIlI.setColor(Color.white);
        lllllllllllllllllIIIllIIlIlllIlI.drawString("NOTE:", 500.0f, 450.0f);
        lllllllllllllllllIIIllIIlIlllIlI.drawString("lines should be flush with edges", 525.0f, 470.0f);
        lllllllllllllllllIIIllIIlIlllIlI.drawString("corners should be symetric", 525.0f, 490.0f);
    }
    
    void arcTest(final Graphics lllllllllllllllllIIIllIIlIllIIIl) {
        if (!this.hideOverlay) {
            lllllllllllllllllIIIllIIlIllIIIl.setColor(this.overlayColor);
            lllllllllllllllllIIIllIIlIllIIIl.drawLine(198.0f, 100.0f, 198.0f, 198.0f);
            lllllllllllllllllIIIllIIlIllIIIl.drawLine(100.0f, 198.0f, 198.0f, 198.0f);
        }
        lllllllllllllllllIIIllIIlIllIIIl.setColor(this.geomColor);
        lllllllllllllllllIIIllIIlIllIIIl.drawArc(100.0f, 100.0f, 99.0f, 99.0f, 0.0f, 90.0f);
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIIIllIIllIIIIll) throws SlickException {
        this.container = lllllllllllllllllIIIllIIllIIIIll;
        this.geomColor = Color.magenta;
        this.overlayColor = Color.white;
        this.magImage = new Image(21, 21);
    }
    
    void rectTest(final Graphics lllllllllllllllllIIIllIIlIIlllII) {
        lllllllllllllllllIIIllIIlIIlllII.setColor(this.geomColor);
        lllllllllllllllllIIIllIIlIIlllII.drawRect(100.0f, 100.0f, 99.0f, 99.0f);
        lllllllllllllllllIIIllIIlIIlllII.fillRect(100.0f, 250.0f, 99.0f, 99.0f);
        lllllllllllllllllIIIllIIlIIlllII.drawRoundRect(250.0f, 100.0f, 99.0f, 99.0f, 10);
        lllllllllllllllllIIIllIIlIIlllII.fillRoundRect(250.0f, 250.0f, 99.0f, 99.0f, 10);
        Rectangle lllllllllllllllllIIIllIIlIIlllll = new Rectangle(400.0f, 100.0f, 99.0f, 99.0f);
        lllllllllllllllllIIIllIIlIIlllII.draw(lllllllllllllllllIIIllIIlIIlllll);
        lllllllllllllllllIIIllIIlIIlllll = new Rectangle(400.0f, 250.0f, 99.0f, 99.0f);
        lllllllllllllllllIIIllIIlIIlllII.fill(lllllllllllllllllIIIllIIlIIlllll);
        RoundedRectangle lllllllllllllllllIIIllIIlIIllllI = new RoundedRectangle(550.0f, 100.0f, 99.0f, 99.0f, 10.0f);
        lllllllllllllllllIIIllIIlIIlllII.draw(lllllllllllllllllIIIllIIlIIllllI);
        lllllllllllllllllIIIllIIlIIllllI = new RoundedRectangle(550.0f, 250.0f, 99.0f, 99.0f, 10.0f);
        lllllllllllllllllIIIllIIlIIlllII.fill(lllllllllllllllllIIIllIIlIIllllI);
        if (!this.hideOverlay) {
            lllllllllllllllllIIIllIIlIIlllII.setColor(this.overlayColor);
            lllllllllllllllllIIIllIIlIIlllII.drawLine(100.0f, 149.0f, 198.0f, 149.0f);
            lllllllllllllllllIIIllIIlIIlllII.drawLine(149.0f, 100.0f, 149.0f, 198.0f);
            lllllllllllllllllIIIllIIlIIlllII.drawLine(250.0f, 149.0f, 348.0f, 149.0f);
            lllllllllllllllllIIIllIIlIIlllII.drawLine(299.0f, 100.0f, 299.0f, 198.0f);
            lllllllllllllllllIIIllIIlIIlllII.drawLine(400.0f, 149.0f, 498.0f, 149.0f);
            lllllllllllllllllIIIllIIlIIlllII.drawLine(449.0f, 100.0f, 449.0f, 198.0f);
            lllllllllllllllllIIIllIIlIIlllII.drawLine(550.0f, 149.0f, 648.0f, 149.0f);
            lllllllllllllllllIIIllIIlIIlllII.drawLine(599.0f, 100.0f, 599.0f, 198.0f);
            lllllllllllllllllIIIllIIlIIlllII.drawLine(100.0f, 299.0f, 198.0f, 299.0f);
            lllllllllllllllllIIIllIIlIIlllII.drawLine(149.0f, 250.0f, 149.0f, 348.0f);
            lllllllllllllllllIIIllIIlIIlllII.drawLine(250.0f, 299.0f, 348.0f, 299.0f);
            lllllllllllllllllIIIllIIlIIlllII.drawLine(299.0f, 250.0f, 299.0f, 348.0f);
            lllllllllllllllllIIIllIIlIIlllII.drawLine(400.0f, 299.0f, 498.0f, 299.0f);
            lllllllllllllllllIIIllIIlIIlllII.drawLine(449.0f, 250.0f, 449.0f, 348.0f);
            lllllllllllllllllIIIllIIlIIlllII.drawLine(550.0f, 299.0f, 648.0f, 299.0f);
            lllllllllllllllllIIIllIIlIIlllII.drawLine(599.0f, 250.0f, 599.0f, 348.0f);
        }
    }
    
    static {
        NUMTESTS = 3;
    }
    
    public GeomAccuracyTest() {
        super("Geometry Accuracy Tests");
    }
    
    private void setColors() {
        switch (this.colorIndex) {
            case 0: {
                this.overlayColor = Color.white;
                this.geomColor = Color.magenta;
                break;
            }
            case 1: {
                this.overlayColor = Color.magenta;
                this.geomColor = Color.white;
                break;
            }
            case 2: {
                this.overlayColor = Color.red;
                this.geomColor = Color.green;
                break;
            }
            case 3: {
                this.overlayColor = Color.red;
                this.geomColor = Color.white;
                break;
            }
        }
    }
}
