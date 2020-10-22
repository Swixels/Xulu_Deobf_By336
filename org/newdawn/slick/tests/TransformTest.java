package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class TransformTest extends BasicGame
{
    private /* synthetic */ boolean scaleUp;
    private /* synthetic */ float scale;
    private /* synthetic */ boolean scaleDown;
    
    @Override
    public void keyPressed(final int llllllllllllllllllIllIIlllIlIIlI, final char llllllllllllllllllIllIIlllIlIlII) {
        if (llllllllllllllllllIllIIlllIlIIlI == 1) {
            System.exit(0);
        }
        if (llllllllllllllllllIllIIlllIlIIlI == 16) {
            this.scaleUp = true;
        }
        if (llllllllllllllllllIllIIlllIlIIlI == 30) {
            this.scaleDown = true;
        }
    }
    
    @Override
    public void render(final GameContainer llllllllllllllllllIllIIllllllIIl, final Graphics llllllllllllllllllIllIIllllllIII) {
        llllllllllllllllllIllIIllllllIII.translate(320.0f, 240.0f);
        llllllllllllllllllIllIIllllllIII.scale(this.scale, this.scale);
        llllllllllllllllllIllIIllllllIII.setColor(Color.red);
        for (int llllllllllllllllllIllIIlllllllIl = 0; llllllllllllllllllIllIIlllllllIl < 10; ++llllllllllllllllllIllIIlllllllIl) {
            for (int llllllllllllllllllIllIIlllllllll = 0; llllllllllllllllllIllIIlllllllll < 10; ++llllllllllllllllllIllIIlllllllll) {
                llllllllllllllllllIllIIllllllIII.fillRect((float)(-500 + llllllllllllllllllIllIIlllllllIl * 100), (float)(-500 + llllllllllllllllllIllIIlllllllll * 100), 80.0f, 80.0f);
            }
        }
        llllllllllllllllllIllIIllllllIII.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        llllllllllllllllllIllIIllllllIII.fillRect(-320.0f, -240.0f, 640.0f, 480.0f);
        llllllllllllllllllIllIIllllllIII.setColor(Color.white);
        llllllllllllllllllIllIIllllllIII.drawRect(-320.0f, -240.0f, 640.0f, 480.0f);
    }
    
    @Override
    public void init(final GameContainer llllllllllllllllllIllIlIIIIIllII) throws SlickException {
        llllllllllllllllllIllIlIIIIIllII.setTargetFrameRate(100);
    }
    
    public static void main(final String[] llllllllllllllllllIllIIllIlllllI) {
        try {
            final AppGameContainer llllllllllllllllllIllIIlllIIIIlI = new AppGameContainer(new TransformTest());
            llllllllllllllllllIllIIlllIIIIlI.setDisplayMode(640, 480, false);
            llllllllllllllllllIllIIlllIIIIlI.start();
        }
        catch (SlickException llllllllllllllllllIllIIlllIIIIII) {
            llllllllllllllllllIllIIlllIIIIII.printStackTrace();
        }
    }
    
    @Override
    public void keyReleased(final int llllllllllllllllllIllIIlllIIlIll, final char llllllllllllllllllIllIIlllIIllIl) {
        if (llllllllllllllllllIllIIlllIIlIll == 16) {
            this.scaleUp = false;
        }
        if (llllllllllllllllllIllIIlllIIlIll == 30) {
            this.scaleDown = false;
        }
    }
    
    public TransformTest() {
        super("Transform Test");
        this.scale = 1.0f;
    }
    
    @Override
    public void update(final GameContainer llllllllllllllllllIllIIllllIIllI, final int llllllllllllllllllIllIIllllIIlIl) {
        if (this.scaleUp) {
            this.scale += llllllllllllllllllIllIIllllIIlIl * 0.001f;
        }
        if (this.scaleDown) {
            this.scale -= llllllllllllllllllIllIIllllIIlIl * 0.001f;
        }
    }
}
