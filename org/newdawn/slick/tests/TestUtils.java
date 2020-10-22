package org.newdawn.slick.tests;

import org.newdawn.slick.util.*;
import org.newdawn.slick.opengl.*;
import java.io.*;
import org.newdawn.slick.*;
import org.lwjgl.input.*;
import org.newdawn.slick.openal.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class TestUtils
{
    private /* synthetic */ Audio oggStream;
    private /* synthetic */ Audio aifEffect;
    private /* synthetic */ Texture texture;
    private /* synthetic */ Audio modStream;
    private /* synthetic */ Audio oggEffect;
    private /* synthetic */ Audio wavEffect;
    private /* synthetic */ Font font;
    
    public void init() {
        Log.setVerbose(false);
        final java.awt.Font lIIlIlIllIlIlIl = new java.awt.Font("Times New Roman", 1, 16);
        this.font = new TrueTypeFont(lIIlIlIllIlIlIl, false);
        try {
            this.texture = TextureLoader.getTexture("PNG", new FileInputStream("testdata/rocks.png"));
            System.out.println(String.valueOf(new StringBuilder().append("Texture loaded: ").append(this.texture)));
            System.out.println(String.valueOf(new StringBuilder().append(">> Image width: ").append(this.texture.getImageWidth())));
            System.out.println(String.valueOf(new StringBuilder().append(">> Image height: ").append(this.texture.getImageWidth())));
            System.out.println(String.valueOf(new StringBuilder().append(">> Texture width: ").append(this.texture.getTextureWidth())));
            System.out.println(String.valueOf(new StringBuilder().append(">> Texture height: ").append(this.texture.getTextureHeight())));
            System.out.println(String.valueOf(new StringBuilder().append(">> Texture ID: ").append(this.texture.getTextureID())));
        }
        catch (IOException lIIlIlIllIllIII) {
            lIIlIlIllIllIII.printStackTrace();
        }
        try {
            this.oggEffect = AudioLoader.getAudio("OGG", new FileInputStream("testdata/restart.ogg"));
            this.oggStream = AudioLoader.getStreamingAudio("OGG", new File("testdata/bongos.ogg").toURL());
            this.modStream = AudioLoader.getStreamingAudio("MOD", new File("testdata/SMB-X.XM").toURL());
            this.modStream.playAsMusic(1.0f, 1.0f, true);
            this.aifEffect = AudioLoader.getAudio("AIF", new FileInputStream("testdata/burp.aif"));
            this.wavEffect = AudioLoader.getAudio("WAV", new FileInputStream("testdata/cbrown01.wav"));
        }
        catch (IOException lIIlIlIllIlIlll) {
            lIIlIlIllIlIlll.printStackTrace();
        }
    }
    
    public void render() {
        Color.white.bind();
        this.texture.bind();
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex2f(100.0f, 100.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2f((float)(100 + this.texture.getTextureWidth()), 100.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex2f((float)(100 + this.texture.getTextureWidth()), (float)(100 + this.texture.getTextureHeight()));
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2f(100.0f, (float)(100 + this.texture.getTextureHeight()));
        GL11.glEnd();
        this.font.drawString(150.0f, 300.0f, "HELLO LWJGL WORLD", Color.yellow);
    }
    
    public static void main(final String[] lIIlIlIllIIlIlI) {
        final TestUtils lIIlIlIllIIlIIl = new TestUtils();
        lIIlIlIllIIlIIl.start();
    }
    
    public void update() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == 16) {
                    this.oggEffect.playAsSoundEffect(1.0f, 1.0f, false);
                }
                if (Keyboard.getEventKey() == 17) {
                    this.oggStream.playAsMusic(1.0f, 1.0f, true);
                }
                if (Keyboard.getEventKey() == 18) {
                    this.modStream.playAsMusic(1.0f, 1.0f, true);
                }
                if (Keyboard.getEventKey() == 19) {
                    this.aifEffect.playAsSoundEffect(1.0f, 1.0f, false);
                }
                if (Keyboard.getEventKey() != 20) {
                    continue;
                }
                this.wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
            }
        }
        SoundStore.get().poll(0);
    }
    
    public void start() {
        this.initGL(800, 600);
        this.init();
        while (true) {
            this.update();
            GL11.glClear(16384);
            this.render();
            Display.update();
            Display.sync(100);
            if (Display.isCloseRequested()) {
                System.exit(0);
            }
        }
    }
    
    private void initGL(final int lIIlIlIlllIIIlI, final int lIIlIlIllIllllI) {
        try {
            Display.setDisplayMode(new DisplayMode(lIIlIlIlllIIIlI, lIIlIlIllIllllI));
            Display.create();
            Display.setVSyncEnabled(true);
        }
        catch (LWJGLException lIIlIlIlllIIlII) {
            lIIlIlIlllIIlII.printStackTrace();
            System.exit(0);
        }
        GL11.glEnable(3553);
        GL11.glShadeModel(7425);
        GL11.glDisable(2929);
        GL11.glDisable(2896);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glClearDepth(1.0);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glViewport(0, 0, lIIlIlIlllIIIlI, lIIlIlIllIllllI);
        GL11.glMatrixMode(5888);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0, (double)lIIlIlIlllIIIlI, (double)lIIlIlIllIllllI, 0.0, 1.0, -1.0);
        GL11.glMatrixMode(5888);
    }
}
