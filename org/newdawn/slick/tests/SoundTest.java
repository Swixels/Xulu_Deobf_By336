package org.newdawn.slick.tests;

import org.newdawn.slick.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.openal.*;
import java.io.*;

public class SoundTest extends BasicGame
{
    private /* synthetic */ Sound charlie;
    private /* synthetic */ GameContainer myContainer;
    private /* synthetic */ Sound burp;
    private /* synthetic */ int[] engines;
    private /* synthetic */ int volume;
    private /* synthetic */ Sound sound;
    private /* synthetic */ Audio engine;
    private /* synthetic */ Music music;
    private /* synthetic */ Music musica;
    private /* synthetic */ Music musicb;
    
    @Override
    public void keyPressed(final int lIIllIlIIllllll, final char lIIllIlIlIIIIIl) {
        if (lIIllIlIIllllll == 1) {
            System.exit(0);
        }
        if (lIIllIlIIllllll == 57) {
            this.sound.play();
        }
        if (lIIllIlIIllllll == 48) {
            this.burp.play();
        }
        if (lIIllIlIIllllll == 30) {
            this.sound.playAt(-1.0f, 0.0f, 0.0f);
        }
        if (lIIllIlIIllllll == 38) {
            this.sound.playAt(1.0f, 0.0f, 0.0f);
        }
        if (lIIllIlIIllllll == 28) {
            this.charlie.play(1.0f, 1.0f);
        }
        if (lIIllIlIIllllll == 25) {
            if (this.music.playing()) {
                this.music.pause();
            }
            else {
                this.music.resume();
            }
        }
        if (lIIllIlIIllllll == 46) {
            this.music.stop();
            if (this.music == this.musica) {
                this.music = this.musicb;
            }
            else {
                this.music = this.musica;
            }
            this.music.loop();
        }
        for (int lIIllIlIlIIlIII = 0; lIIllIlIlIIlIII < 3; ++lIIllIlIlIIlIII) {
            if (lIIllIlIIllllll == 2 + lIIllIlIlIIlIII) {
                if (this.engines[lIIllIlIlIIlIII] != 0) {
                    System.out.println(String.valueOf(new StringBuilder().append("Stop ").append(lIIllIlIlIIlIII)));
                    SoundStore.get().stopSoundEffect(this.engines[lIIllIlIlIIlIII]);
                    this.engines[lIIllIlIlIIlIII] = 0;
                }
                else {
                    System.out.println(String.valueOf(new StringBuilder().append("Start ").append(lIIllIlIlIIlIII)));
                    this.engines[lIIllIlIlIIlIII] = this.engine.playAsSoundEffect(1.0f, 1.0f, true);
                }
            }
        }
        if (lIIllIlIlIIIIIl == '+') {
            ++this.volume;
            this.setVolume();
        }
        if (lIIllIlIlIIIIIl == '-') {
            --this.volume;
            this.setVolume();
        }
        if (lIIllIlIIllllll == 21) {
            int lIIllIlIlIIIlll = (int)(this.music.getVolume() * 10.0f);
            if (--lIIllIlIlIIIlll < 0) {
                lIIllIlIlIIIlll = 0;
            }
            this.music.setVolume(lIIllIlIlIIIlll / 10.0f);
        }
        if (lIIllIlIIllllll == 45) {
            int lIIllIlIlIIIllI = (int)(this.music.getVolume() * 10.0f);
            if (++lIIllIlIlIIIllI > 10) {
                lIIllIlIlIIIllI = 10;
            }
            this.music.setVolume(lIIllIlIlIIIllI / 10.0f);
        }
        if (lIIllIlIIllllll == 49) {
            int lIIllIlIlIIIlIl = (int)(this.myContainer.getSoundVolume() * 10.0f);
            if (--lIIllIlIlIIIlIl < 0) {
                lIIllIlIlIIIlIl = 0;
            }
            this.myContainer.setSoundVolume(lIIllIlIlIIIlIl / 10.0f);
        }
        if (lIIllIlIIllllll == 50) {
            int lIIllIlIlIIIlII = (int)(this.myContainer.getSoundVolume() * 10.0f);
            if (++lIIllIlIlIIIlII > 10) {
                lIIllIlIlIIIlII = 10;
            }
            this.myContainer.setSoundVolume(lIIllIlIlIIIlII / 10.0f);
        }
    }
    
    public static void main(final String[] lIIllIlIIllIllI) {
        try {
            final AppGameContainer lIIllIlIIlllIII = new AppGameContainer(new SoundTest());
            lIIllIlIIlllIII.setDisplayMode(800, 600, false);
            lIIllIlIIlllIII.start();
        }
        catch (SlickException lIIllIlIIllIlll) {
            lIIllIlIIllIlll.printStackTrace();
        }
    }
    
    @Override
    public void update(final GameContainer lIIllIlIlIIlllI, final int lIIllIlIlIIllIl) {
    }
    
    public SoundTest() {
        super("Sound And Music Test");
        this.volume = 10;
        this.engines = new int[3];
    }
    
    private void setVolume() {
        if (this.volume > 10) {
            this.volume = 10;
        }
        else if (this.volume < 0) {
            this.volume = 0;
        }
        this.myContainer.setMusicVolume(this.volume / 10.0f);
    }
    
    @Override
    public void render(final GameContainer lIIllIlIlIlIIIl, final Graphics lIIllIlIlIlIIII) {
        lIIllIlIlIlIIII.setColor(Color.white);
        lIIllIlIlIlIIII.drawString("The OGG loop is now streaming from the file, woot.", 100.0f, 60.0f);
        lIIllIlIlIlIIII.drawString("Press space for sound effect (OGG)", 100.0f, 100.0f);
        lIIllIlIlIlIIII.drawString("Press P to pause/resume music (XM)", 100.0f, 130.0f);
        lIIllIlIlIlIIII.drawString("Press E to pause/resume engine sound (WAV)", 100.0f, 190.0f);
        lIIllIlIlIlIIII.drawString("Press enter for charlie (WAV)", 100.0f, 160.0f);
        lIIllIlIlIlIIII.drawString("Press C to change music", 100.0f, 210.0f);
        lIIllIlIlIlIIII.drawString("Press B to burp (AIF)", 100.0f, 240.0f);
        lIIllIlIlIlIIII.drawString("Press + or - to change global volume of music", 100.0f, 270.0f);
        lIIllIlIlIlIIII.drawString("Press Y or X to change individual volume of music", 100.0f, 300.0f);
        lIIllIlIlIlIIII.drawString("Press N or M to change global volume of sound fx", 100.0f, 330.0f);
        lIIllIlIlIlIIII.setColor(Color.blue);
        lIIllIlIlIlIIII.drawString(String.valueOf(new StringBuilder().append("Global Sound Volume Level: ").append(lIIllIlIlIlIIIl.getSoundVolume())), 150.0f, 390.0f);
        lIIllIlIlIlIIII.drawString(String.valueOf(new StringBuilder().append("Global Music Volume Level: ").append(lIIllIlIlIlIIIl.getMusicVolume())), 150.0f, 420.0f);
        lIIllIlIlIlIIII.drawString(String.valueOf(new StringBuilder().append("Current Music Volume Level: ").append(this.music.getVolume())), 150.0f, 450.0f);
    }
    
    @Override
    public void init(final GameContainer lIIllIlIlIlllII) throws SlickException {
        SoundStore.get().setMaxSources(32);
        this.myContainer = lIIllIlIlIlllII;
        this.sound = new Sound("testdata/restart.ogg");
        this.charlie = new Sound("testdata/cbrown01.wav");
        try {
            this.engine = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("testdata/engine.wav"));
        }
        catch (IOException lIIllIlIlIllllI) {
            throw new SlickException("Failed to load engine", lIIllIlIlIllllI);
        }
        final Music music = new Music("testdata/SMB-X.XM");
        this.musica = music;
        this.music = music;
        this.musicb = new Music("testdata/kirby.ogg", true);
        this.burp = new Sound("testdata/burp.aif");
        this.music.play();
    }
}
