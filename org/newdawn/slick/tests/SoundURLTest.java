package org.newdawn.slick.tests;

import org.newdawn.slick.*;
import org.newdawn.slick.util.*;

public class SoundURLTest extends BasicGame
{
    private /* synthetic */ Music music;
    private /* synthetic */ Sound charlie;
    private /* synthetic */ Sound burp;
    private /* synthetic */ Music musicb;
    private /* synthetic */ Music musica;
    private /* synthetic */ Sound sound;
    private /* synthetic */ Sound engine;
    private /* synthetic */ int volume;
    
    public static void main(final String[] lllllllllllllllllIIlIlllllIIlIII) {
        try {
            final AppGameContainer lllllllllllllllllIIlIlllllIIlIlI = new AppGameContainer(new SoundURLTest());
            lllllllllllllllllIIlIlllllIIlIlI.setDisplayMode(800, 600, false);
            lllllllllllllllllIIlIlllllIIlIlI.start();
        }
        catch (SlickException lllllllllllllllllIIlIlllllIIlIIl) {
            lllllllllllllllllIIlIlllllIIlIIl.printStackTrace();
        }
    }
    
    @Override
    public void keyPressed(final int lllllllllllllllllIIlIlllllIlIIII, final char lllllllllllllllllIIlIlllllIIllll) {
        if (lllllllllllllllllIIlIlllllIlIIII == 1) {
            System.exit(0);
        }
        if (lllllllllllllllllIIlIlllllIlIIII == 57) {
            this.sound.play();
        }
        if (lllllllllllllllllIIlIlllllIlIIII == 48) {
            this.burp.play();
        }
        if (lllllllllllllllllIIlIlllllIlIIII == 30) {
            this.sound.playAt(-1.0f, 0.0f, 0.0f);
        }
        if (lllllllllllllllllIIlIlllllIlIIII == 38) {
            this.sound.playAt(1.0f, 0.0f, 0.0f);
        }
        if (lllllllllllllllllIIlIlllllIlIIII == 28) {
            this.charlie.play(1.0f, 1.0f);
        }
        if (lllllllllllllllllIIlIlllllIlIIII == 25) {
            if (this.music.playing()) {
                this.music.pause();
            }
            else {
                this.music.resume();
            }
        }
        if (lllllllllllllllllIIlIlllllIlIIII == 46) {
            this.music.stop();
            if (this.music == this.musica) {
                this.music = this.musicb;
            }
            else {
                this.music = this.musica;
            }
            this.music.loop();
        }
        if (lllllllllllllllllIIlIlllllIlIIII == 18) {
            if (this.engine.playing()) {
                this.engine.stop();
            }
            else {
                this.engine.loop();
            }
        }
        if (lllllllllllllllllIIlIlllllIIllll == '+') {
            ++this.volume;
            this.setVolume();
        }
        if (lllllllllllllllllIIlIlllllIIllll == '-') {
            --this.volume;
            this.setVolume();
        }
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIIlIlllllIllIIl, final int lllllllllllllllllIIlIlllllIllIII) {
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIIlIlllllIllllI, final Graphics lllllllllllllllllIIlIlllllIllIll) {
        lllllllllllllllllIIlIlllllIllIll.setColor(Color.white);
        lllllllllllllllllIIlIlllllIllIll.drawString("The OGG loop is now streaming from the file, woot.", 100.0f, 60.0f);
        lllllllllllllllllIIlIlllllIllIll.drawString("Press space for sound effect (OGG)", 100.0f, 100.0f);
        lllllllllllllllllIIlIlllllIllIll.drawString("Press P to pause/resume music (XM)", 100.0f, 130.0f);
        lllllllllllllllllIIlIlllllIllIll.drawString("Press E to pause/resume engine sound (WAV)", 100.0f, 190.0f);
        lllllllllllllllllIIlIlllllIllIll.drawString("Press enter for charlie (WAV)", 100.0f, 160.0f);
        lllllllllllllllllIIlIlllllIllIll.drawString("Press C to change music", 100.0f, 210.0f);
        lllllllllllllllllIIlIlllllIllIll.drawString("Press B to burp (AIF)", 100.0f, 240.0f);
        lllllllllllllllllIIlIlllllIllIll.drawString("Press + or - to change volume of music", 100.0f, 270.0f);
        lllllllllllllllllIIlIlllllIllIll.setColor(Color.blue);
        lllllllllllllllllIIlIlllllIllIll.drawString(String.valueOf(new StringBuilder().append("Music Volume Level: ").append(this.volume / 10.0f)), 150.0f, 300.0f);
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIIlIllllllIIIll) throws SlickException {
        this.sound = new Sound(ResourceLoader.getResource("testdata/restart.ogg"));
        this.charlie = new Sound(ResourceLoader.getResource("testdata/cbrown01.wav"));
        this.engine = new Sound(ResourceLoader.getResource("testdata/engine.wav"));
        final Music music = new Music(ResourceLoader.getResource("testdata/restart.ogg"), false);
        this.musica = music;
        this.music = music;
        this.musicb = new Music(ResourceLoader.getResource("testdata/kirby.ogg"), false);
        this.burp = new Sound(ResourceLoader.getResource("testdata/burp.aif"));
    }
    
    private void setVolume() {
        if (this.volume > 10) {
            this.volume = 10;
        }
        else if (this.volume < 0) {
            this.volume = 0;
        }
        this.music.setVolume(this.volume / 10.0f);
    }
    
    public SoundURLTest() {
        super("Sound URL Test");
        this.volume = 1;
    }
}
