package org.newdawn.slick.tests;

import org.newdawn.slick.openal.*;
import org.newdawn.slick.*;

public class SoundPositionTest extends BasicGame
{
    private /* synthetic */ GameContainer myContainer;
    private /* synthetic */ Music music;
    private /* synthetic */ int[] engines;
    
    @Override
    public void keyPressed(final int lllllllllllllllllIIIllllllIlIlll, final char lllllllllllllllllIIIllllllIllIIl) {
        if (lllllllllllllllllIIIllllllIlIlll == 57) {
            if (this.music.playing()) {
                this.music.pause();
            }
            else {
                this.music.resume();
            }
        }
        if (lllllllllllllllllIIIllllllIlIlll == 205) {
            this.music.setPosition(this.music.getPosition() + 5.0f);
        }
    }
    
    public static void main(final String[] lllllllllllllllllIIIllllllIlIIll) {
        try {
            final AppGameContainer lllllllllllllllllIIIllllllIlIlIl = new AppGameContainer(new SoundPositionTest());
            lllllllllllllllllIIIllllllIlIlIl.setDisplayMode(800, 600, false);
            lllllllllllllllllIIIllllllIlIlIl.start();
        }
        catch (SlickException lllllllllllllllllIIIllllllIlIlII) {
            lllllllllllllllllIIIllllllIlIlII.printStackTrace();
        }
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIIIlllllllIlIII) throws SlickException {
        SoundStore.get().setMaxSources(32);
        this.myContainer = lllllllllllllllllIIIlllllllIlIII;
        this.music = new Music("testdata/kirby.ogg", true);
        this.music.play();
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIIIlllllllIIlII, final Graphics lllllllllllllllllIIIlllllllIIIIl) {
        lllllllllllllllllIIIlllllllIIIIl.setColor(Color.white);
        lllllllllllllllllIIIlllllllIIIIl.drawString(String.valueOf(new StringBuilder().append("Position: ").append(this.music.getPosition())), 100.0f, 100.0f);
        lllllllllllllllllIIIlllllllIIIIl.drawString("Space - Pause/Resume", 100.0f, 130.0f);
        lllllllllllllllllIIIlllllllIIIIl.drawString("Right Arrow - Advance 5 seconds", 100.0f, 145.0f);
    }
    
    public SoundPositionTest() {
        super("Music Position Test");
        this.engines = new int[3];
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIIIllllllIlllll, final int lllllllllllllllllIIIllllllIllllI) {
    }
}
