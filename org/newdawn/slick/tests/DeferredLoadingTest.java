package org.newdawn.slick.tests;

import org.newdawn.slick.loading.*;
import java.io.*;
import org.newdawn.slick.*;

public class DeferredLoadingTest extends BasicGame
{
    private /* synthetic */ Music music;
    private /* synthetic */ DeferredResource nextResource;
    private /* synthetic */ Image image;
    private /* synthetic */ Font font;
    private /* synthetic */ Sound sound;
    private /* synthetic */ boolean started;
    
    @Override
    public void keyPressed(final int llllllllllllllllllIIIllIllllIllI, final char llllllllllllllllllIIIllIllllIlIl) {
    }
    
    @Override
    public void init(final GameContainer llllllllllllllllllIIIlllIIIlIllI) throws SlickException {
        LoadingList.setDeferredLoading(true);
        new Sound("testdata/cbrown01.wav");
        new Sound("testdata/engine.wav");
        this.sound = new Sound("testdata/restart.ogg");
        new Music("testdata/testloop.ogg");
        this.music = new Music("testdata/SMB-X.XM");
        new Image("testdata/cursor.png");
        new Image("testdata/cursor.tga");
        new Image("testdata/cursor.png");
        new Image("testdata/cursor.png");
        new Image("testdata/dungeontiles.gif");
        new Image("testdata/logo.gif");
        this.image = new Image("testdata/logo.tga");
        new Image("testdata/logo.png");
        new Image("testdata/rocket.png");
        new Image("testdata/testpack.png");
        this.font = new AngelCodeFont("testdata/demo.fnt", "testdata/demo_00.tga");
    }
    
    @Override
    public void update(final GameContainer llllllllllllllllllIIIlllIIIIIIII, final int llllllllllllllllllIIIllIllllllll) throws SlickException {
        if (this.nextResource != null) {
            try {
                this.nextResource.load();
                try {
                    Thread.sleep(50L);
                }
                catch (Exception ex) {}
            }
            catch (IOException llllllllllllllllllIIIlllIIIIIIlI) {
                throw new SlickException(String.valueOf(new StringBuilder().append("Failed to load: ").append(this.nextResource.getDescription())), llllllllllllllllllIIIlllIIIIIIlI);
            }
            this.nextResource = null;
        }
        if (LoadingList.get().getRemainingResources() > 0) {
            this.nextResource = LoadingList.get().getNext();
        }
        else if (!this.started) {
            this.started = true;
            this.music.loop();
            this.sound.play();
        }
    }
    
    @Override
    public void render(final GameContainer llllllllllllllllllIIIlllIIIIlllI, final Graphics llllllllllllllllllIIIlllIIIIllIl) {
        if (this.nextResource != null) {
            llllllllllllllllllIIIlllIIIIllIl.drawString(String.valueOf(new StringBuilder().append("Loading: ").append(this.nextResource.getDescription())), 100.0f, 100.0f);
        }
        final int llllllllllllllllllIIIlllIIIIllII = LoadingList.get().getTotalResources();
        final int llllllllllllllllllIIIlllIIIIlIll = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources();
        final float llllllllllllllllllIIIlllIIIIlIlI = llllllllllllllllllIIIlllIIIIlIll / (float)llllllllllllllllllIIIlllIIIIllII;
        llllllllllllllllllIIIlllIIIIllIl.fillRect(100.0f, 150.0f, (float)(llllllllllllllllllIIIlllIIIIlIll * 40), 20.0f);
        llllllllllllllllllIIIlllIIIIllIl.drawRect(100.0f, 150.0f, (float)(llllllllllllllllllIIIlllIIIIllII * 40), 20.0f);
        if (this.started) {
            this.image.draw(100.0f, 200.0f);
            this.font.drawString(100.0f, 500.0f, "LOADING COMPLETE");
        }
    }
    
    public DeferredLoadingTest() {
        super("Deferred Loading Test");
    }
    
    public static void main(final String[] llllllllllllllllllIIIllIlllllIIl) {
        try {
            final AppGameContainer llllllllllllllllllIIIllIlllllIll = new AppGameContainer(new DeferredLoadingTest());
            llllllllllllllllllIIIllIlllllIll.setDisplayMode(800, 600, false);
            llllllllllllllllllIIIllIlllllIll.start();
        }
        catch (SlickException llllllllllllllllllIIIllIlllllIlI) {
            llllllllllllllllllIIIllIlllllIlI.printStackTrace();
        }
    }
}
