package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class AnimationTest extends BasicGame
{
    private /* synthetic */ Animation manual;
    private /* synthetic */ int start;
    private /* synthetic */ GameContainer container;
    private /* synthetic */ Animation limited;
    private /* synthetic */ Animation animation;
    private /* synthetic */ Animation pingPong;
    
    @Override
    public void init(final GameContainer llllllllllllllllIllIIllIlllIIIll) throws SlickException {
        this.container = llllllllllllllllIllIIllIlllIIIll;
        final SpriteSheet llllllllllllllllIllIIllIlllIIlIl = new SpriteSheet("testdata/homeranim.png", 36, 65);
        this.animation = new Animation();
        for (int llllllllllllllllIllIIllIlllIlIlI = 0; llllllllllllllllIllIIllIlllIlIlI < 8; ++llllllllllllllllIllIIllIlllIlIlI) {
            this.animation.addFrame(llllllllllllllllIllIIllIlllIIlIl.getSprite(llllllllllllllllIllIIllIlllIlIlI, 0), 150);
        }
        this.limited = new Animation();
        for (int llllllllllllllllIllIIllIlllIlIIl = 0; llllllllllllllllIllIIllIlllIlIIl < 8; ++llllllllllllllllIllIIllIlllIlIIl) {
            this.limited.addFrame(llllllllllllllllIllIIllIlllIIlIl.getSprite(llllllllllllllllIllIIllIlllIlIIl, 0), 150);
        }
        this.limited.stopAt(7);
        this.manual = new Animation(false);
        for (int llllllllllllllllIllIIllIlllIlIII = 0; llllllllllllllllIllIIllIlllIlIII < 8; ++llllllllllllllllIllIIllIlllIlIII) {
            this.manual.addFrame(llllllllllllllllIllIIllIlllIIlIl.getSprite(llllllllllllllllIllIIllIlllIlIII, 0), 150);
        }
        this.pingPong = new Animation(llllllllllllllllIllIIllIlllIIlIl, 0, 0, 7, 0, true, 150, true);
        this.pingPong.setPingPong(true);
        llllllllllllllllIllIIllIlllIIIll.getGraphics().setBackground(new Color(0.4f, 0.6f, 0.6f));
    }
    
    @Override
    public void keyPressed(final int llllllllllllllllIllIIllIlIllIIII, final char llllllllllllllllIllIIllIlIllIIlI) {
        if (llllllllllllllllIllIIllIlIllIIII == 1) {
            this.container.exit();
        }
        if (llllllllllllllllIllIIllIlIllIIII == 57) {
            this.limited.restart();
        }
    }
    
    @Override
    public void update(final GameContainer llllllllllllllllIllIIllIllIlIlIl, final int llllllllllllllllIllIIllIllIIllll) {
        if (llllllllllllllllIllIIllIllIlIlIl.getInput().isKeyDown(2)) {
            this.manual.update(llllllllllllllllIllIIllIllIIllll);
        }
        if (this.start >= 0) {
            this.start -= llllllllllllllllIllIIllIllIIllll;
        }
    }
    
    public static void main(final String[] llllllllllllllllIllIIllIlIlllIIl) {
        try {
            final AppGameContainer llllllllllllllllIllIIllIllIIIlIl = new AppGameContainer(new AnimationTest());
            llllllllllllllllIllIIllIllIIIlIl.setDisplayMode(800, 600, false);
            llllllllllllllllIllIIllIllIIIlIl.start();
        }
        catch (SlickException llllllllllllllllIllIIllIllIIIIll) {
            llllllllllllllllIllIIllIllIIIIll.printStackTrace();
        }
    }
    
    public AnimationTest() {
        super("Animation Test");
        this.start = 5000;
    }
    
    @Override
    public void render(final GameContainer llllllllllllllllIllIIllIllIlllIl, final Graphics llllllllllllllllIllIIllIllIlllII) {
        llllllllllllllllIllIIllIllIlllII.drawString("Space to restart() animation", 100.0f, 50.0f);
        llllllllllllllllIllIIllIllIlllII.drawString(String.valueOf(new StringBuilder().append("Til Limited animation: ").append(this.start)), 100.0f, 500.0f);
        llllllllllllllllIllIIllIllIlllII.drawString("Hold 1 to move the manually animated", 100.0f, 70.0f);
        llllllllllllllllIllIIllIllIlllII.drawString(String.valueOf(new StringBuilder().append("PingPong Frame:").append(this.pingPong.getFrame())), 600.0f, 70.0f);
        llllllllllllllllIllIIllIllIlllII.scale(-1.0f, 1.0f);
        this.animation.draw(-100.0f, 100.0f);
        this.animation.draw(-200.0f, 100.0f, 144.0f, 260.0f);
        if (this.start < 0) {
            this.limited.draw(-400.0f, 100.0f, 144.0f, 260.0f);
        }
        this.manual.draw(-600.0f, 100.0f, 144.0f, 260.0f);
        this.pingPong.draw(-700.0f, 100.0f, 72.0f, 130.0f);
    }
}
