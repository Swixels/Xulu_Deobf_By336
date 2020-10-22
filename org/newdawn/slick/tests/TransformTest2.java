package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class TransformTest2 extends BasicGame
{
    private /* synthetic */ boolean moveLeft;
    private /* synthetic */ float camY;
    private /* synthetic */ float scale;
    private /* synthetic */ boolean scaleUp;
    private /* synthetic */ float camX;
    private /* synthetic */ boolean moveRight;
    private /* synthetic */ boolean moveUp;
    private /* synthetic */ boolean scaleDown;
    private /* synthetic */ boolean moveDown;
    
    public static void main(final String[] llllllllllllllllllIllllllIIllIIl) {
        try {
            final AppGameContainer llllllllllllllllllIllllllIIllIll = new AppGameContainer(new TransformTest2());
            llllllllllllllllllIllllllIIllIll.setDisplayMode(640, 480, false);
            llllllllllllllllllIllllllIIllIll.start();
        }
        catch (SlickException llllllllllllllllllIllllllIIllIlI) {
            llllllllllllllllllIllllllIIllIlI.printStackTrace();
        }
    }
    
    @Override
    public void keyPressed(final int llllllllllllllllllIllllllIlIIlII, final char llllllllllllllllllIllllllIlIIllI) {
        if (llllllllllllllllllIllllllIlIIlII == 1) {
            System.exit(0);
        }
        if (llllllllllllllllllIllllllIlIIlII == 16) {
            this.scaleUp = true;
        }
        if (llllllllllllllllllIllllllIlIIlII == 30) {
            this.scaleDown = true;
        }
        if (llllllllllllllllllIllllllIlIIlII == 203) {
            this.moveLeft = true;
        }
        if (llllllllllllllllllIllllllIlIIlII == 200) {
            this.moveUp = true;
        }
        if (llllllllllllllllllIllllllIlIIlII == 205) {
            this.moveRight = true;
        }
        if (llllllllllllllllllIllllllIlIIlII == 208) {
            this.moveDown = true;
        }
    }
    
    public TransformTest2() {
        super("Transform Test");
        this.scale = 1.0f;
        this.camX = 320.0f;
        this.camY = 240.0f;
    }
    
    @Override
    public void update(final GameContainer llllllllllllllllllIllllllIllIIII, final int llllllllllllllllllIllllllIlIllII) {
        if (this.scaleUp) {
            this.scale += llllllllllllllllllIllllllIlIllII * 0.001f;
        }
        if (this.scaleDown) {
            this.scale -= llllllllllllllllllIllllllIlIllII * 0.001f;
        }
        final float llllllllllllllllllIllllllIlIlllI = llllllllllllllllllIllllllIlIllII * 0.4f * (1.0f / this.scale);
        if (this.moveLeft) {
            this.camX -= llllllllllllllllllIllllllIlIlllI;
        }
        if (this.moveUp) {
            this.camY -= llllllllllllllllllIllllllIlIlllI;
        }
        if (this.moveRight) {
            this.camX += llllllllllllllllllIllllllIlIlllI;
        }
        if (this.moveDown) {
            this.camY += llllllllllllllllllIllllllIlIlllI;
        }
    }
    
    @Override
    public void keyReleased(final int llllllllllllllllllIllllllIlIIIII, final char llllllllllllllllllIllllllIIlllll) {
        if (llllllllllllllllllIllllllIlIIIII == 16) {
            this.scaleUp = false;
        }
        if (llllllllllllllllllIllllllIlIIIII == 30) {
            this.scaleDown = false;
        }
        if (llllllllllllllllllIllllllIlIIIII == 203) {
            this.moveLeft = false;
        }
        if (llllllllllllllllllIllllllIlIIIII == 200) {
            this.moveUp = false;
        }
        if (llllllllllllllllllIllllllIlIIIII == 205) {
            this.moveRight = false;
        }
        if (llllllllllllllllllIllllllIlIIIII == 208) {
            this.moveDown = false;
        }
    }
    
    @Override
    public void render(final GameContainer llllllllllllllllllIllllllIlllIlI, final Graphics llllllllllllllllllIllllllIlllIIl) {
        llllllllllllllllllIllllllIlllIIl.translate(320.0f, 240.0f);
        llllllllllllllllllIllllllIlllIIl.translate(-this.camX * this.scale, -this.camY * this.scale);
        llllllllllllllllllIllllllIlllIIl.scale(this.scale, this.scale);
        llllllllllllllllllIllllllIlllIIl.setColor(Color.red);
        for (int llllllllllllllllllIllllllIllllII = 0; llllllllllllllllllIllllllIllllII < 10; ++llllllllllllllllllIllllllIllllII) {
            for (int llllllllllllllllllIllllllIllllIl = 0; llllllllllllllllllIllllllIllllIl < 10; ++llllllllllllllllllIllllllIllllIl) {
                llllllllllllllllllIllllllIlllIIl.fillRect((float)(-500 + llllllllllllllllllIllllllIllllII * 100), (float)(-500 + llllllllllllllllllIllllllIllllIl * 100), 80.0f, 80.0f);
            }
        }
        llllllllllllllllllIllllllIlllIIl.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        llllllllllllllllllIllllllIlllIIl.fillRect(-320.0f, -240.0f, 640.0f, 480.0f);
        llllllllllllllllllIllllllIlllIIl.setColor(Color.white);
        llllllllllllllllllIllllllIlllIIl.drawRect(-320.0f, -240.0f, 640.0f, 480.0f);
    }
    
    @Override
    public void init(final GameContainer llllllllllllllllllIlllllllIIIIlI) throws SlickException {
        llllllllllllllllllIlllllllIIIIlI.setTargetFrameRate(100);
    }
}
