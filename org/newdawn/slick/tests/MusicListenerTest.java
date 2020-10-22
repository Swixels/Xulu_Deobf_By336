package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class MusicListenerTest extends BasicGame implements MusicListener
{
    private /* synthetic */ Music music;
    private /* synthetic */ boolean musicSwapped;
    private /* synthetic */ Music stream;
    private /* synthetic */ boolean musicEnded;
    
    public MusicListenerTest() {
        super("Music Listener Test");
        this.musicEnded = false;
        this.musicSwapped = false;
    }
    
    @Override
    public void init(final GameContainer llllIllllIIl) throws SlickException {
        this.music = new Music("testdata/restart.ogg", false);
        this.stream = new Music("testdata/restart.ogg", false);
        this.music.addListener(this);
        this.stream.addListener(this);
    }
    
    @Override
    public void update(final GameContainer llllIlllIllI, final int llllIlllIlIl) throws SlickException {
    }
    
    @Override
    public void render(final GameContainer llllIllIlIII, final Graphics llllIllIIlll) throws SlickException {
        llllIllIIlll.drawString("Press M to play music", 100.0f, 100.0f);
        llllIllIIlll.drawString("Press S to stream music", 100.0f, 150.0f);
        if (this.musicEnded) {
            llllIllIIlll.drawString("Music Ended", 100.0f, 200.0f);
        }
        if (this.musicSwapped) {
            llllIllIIlll.drawString("Music Swapped", 100.0f, 250.0f);
        }
    }
    
    @Override
    public void musicEnded(final Music llllIlllIIlI) {
        this.musicEnded = true;
    }
    
    @Override
    public void musicSwapped(final Music llllIllIlllI, final Music llllIllIllIl) {
        this.musicSwapped = true;
    }
    
    @Override
    public void keyPressed(final int llllIllIIIIl, final char llllIllIIIII) {
        if (llllIllIIIIl == 50) {
            this.musicEnded = false;
            this.musicSwapped = false;
            this.music.play();
        }
        if (llllIllIIIIl == 31) {
            this.musicEnded = false;
            this.musicSwapped = false;
            this.stream.play();
        }
    }
    
    public static void main(final String[] llllIlIllIlI) {
        try {
            final AppGameContainer llllIlIlllII = new AppGameContainer(new MusicListenerTest());
            llllIlIlllII.setDisplayMode(800, 600, false);
            llllIlIlllII.start();
        }
        catch (SlickException llllIlIllIll) {
            llllIlIllIll.printStackTrace();
        }
    }
}
