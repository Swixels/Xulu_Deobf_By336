package org.newdawn.slick.tests;

import org.newdawn.slick.opengl.*;
import org.newdawn.slick.*;
import org.lwjgl.*;
import org.lwjgl.opengl.*;
import java.nio.*;

public class SlickCallableTest extends BasicGame
{
    private /* synthetic */ Image image;
    private /* synthetic */ Image back;
    private /* synthetic */ Animation homer;
    private /* synthetic */ float rot;
    private /* synthetic */ AngelCodeFont font;
    
    public SlickCallableTest() {
        super("Slick Callable Test");
    }
    
    @Override
    public void render(final GameContainer llllllllllllllllllIIIIIIIIIlllII, final Graphics llllllllllllllllllIIIIIIIIIllIll) throws SlickException {
        llllllllllllllllllIIIIIIIIIllIll.scale(2.0f, 2.0f);
        llllllllllllllllllIIIIIIIIIllIll.fillRect(0.0f, 0.0f, 800.0f, 600.0f, this.back, 0.0f, 0.0f);
        llllllllllllllllllIIIIIIIIIllIll.resetTransform();
        llllllllllllllllllIIIIIIIIIllIll.drawImage(this.image, 100.0f, 100.0f);
        this.image.draw(100.0f, 200.0f, 80.0f, 200.0f);
        this.font.drawString(100.0f, 200.0f, "Text Drawn before the callable");
        final SlickCallable llllllllllllllllllIIIIIIIIIllIlI = new SlickCallable() {
            @Override
            protected void performGLOperations() throws SlickException {
                SlickCallableTest.this.renderGL();
            }
        };
        llllllllllllllllllIIIIIIIIIllIlI.call();
        this.homer.draw(450.0f, 250.0f, 80.0f, 200.0f);
        this.font.drawString(150.0f, 300.0f, "Text Drawn after the callable");
    }
    
    @Override
    public void init(final GameContainer llllllllllllllllllIIIIIIIIlIIllI) throws SlickException {
        this.image = new Image("testdata/rocket.png");
        this.back = new Image("testdata/sky.jpg");
        this.font = new AngelCodeFont("testdata/hiero.fnt", "testdata/hiero.png");
        final SpriteSheet llllllllllllllllllIIIIIIIIlIIlIl = new SpriteSheet("testdata/homeranim.png", 36, 65);
        this.homer = new Animation(llllllllllllllllllIIIIIIIIlIIlIl, 0, 0, 7, 0, true, 150, true);
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIllllllllIlIlll, final int lllllllllllllllllIllllllllIlIllI) {
        this.rot += lllllllllllllllllIllllllllIlIllI * 0.1f;
    }
    
    public static void main(final String[] lllllllllllllllllIllllllllIlIIII) {
        try {
            final AppGameContainer lllllllllllllllllIllllllllIlIIlI = new AppGameContainer(new SlickCallableTest());
            lllllllllllllllllIllllllllIlIIlI.setDisplayMode(800, 600, false);
            lllllllllllllllllIllllllllIlIIlI.start();
        }
        catch (SlickException lllllllllllllllllIllllllllIlIIIl) {
            lllllllllllllllllIllllllllIlIIIl.printStackTrace();
        }
    }
    
    public void renderGL() {
        final FloatBuffer llllllllllllllllllIIIIIIIIIlIIIl = BufferUtils.createFloatBuffer(4);
        llllllllllllllllllIIIIIIIIIlIIIl.put(new float[] { 5.0f, 5.0f, 10.0f, 0.0f }).flip();
        final FloatBuffer llllllllllllllllllIIIIIIIIIlIIII = BufferUtils.createFloatBuffer(4);
        llllllllllllllllllIIIIIIIIIlIIII.put(new float[] { 0.8f, 0.1f, 0.0f, 1.0f }).flip();
        GL11.glLight(16384, 4611, llllllllllllllllllIIIIIIIIIlIIIl);
        GL11.glEnable(16384);
        GL11.glEnable(2884);
        GL11.glEnable(2929);
        GL11.glEnable(2896);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        final float llllllllllllllllllIIIIIIIIIIllll = 0.75f;
        GL11.glFrustum(-1.0, 1.0, (double)(-llllllllllllllllllIIIIIIIIIIllll), (double)llllllllllllllllllIIIIIIIIIIllll, 5.0, 60.0);
        GL11.glMatrixMode(5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0f, 0.0f, -40.0f);
        GL11.glRotatef(this.rot, 0.0f, 1.0f, 1.0f);
        GL11.glMaterial(1028, 5634, llllllllllllllllllIIIIIIIIIlIIII);
        this.gear(0.5f, 2.0f, 2.0f, 10, 0.7f);
    }
    
    private void gear(final float lllllllllllllllllIlllllllllIlIII, final float lllllllllllllllllIlllllllllIIlll, final float lllllllllllllllllIlllllllllIIllI, final int lllllllllllllllllIlllllllllIllll, final float lllllllllllllllllIlllllllllIlllI) {
        final float lllllllllllllllllIlllllllllIllII = lllllllllllllllllIlllllllllIlIII;
        final float lllllllllllllllllIlllllllllIlIll = lllllllllllllllllIlllllllllIIlll - lllllllllllllllllIlllllllllIlllI / 2.0f;
        final float lllllllllllllllllIlllllllllIlIlI = lllllllllllllllllIlllllllllIIlll + lllllllllllllllllIlllllllllIlllI / 2.0f;
        final float lllllllllllllllllIlllllllllIlIIl = 6.2831855f / lllllllllllllllllIlllllllllIllll / 4.0f;
        GL11.glShadeModel(7424);
        GL11.glNormal3f(0.0f, 0.0f, 1.0f);
        GL11.glBegin(8);
        for (int lllllllllllllllllIlllllllllIllIl = 0; lllllllllllllllllIlllllllllIllIl <= lllllllllllllllllIlllllllllIllll; ++lllllllllllllllllIlllllllllIllIl) {
            final float lllllllllllllllllIllllllllllllII = lllllllllllllllllIlllllllllIllIl * 2.0f * 3.1415927f / lllllllllllllllllIlllllllllIllll;
            GL11.glVertex3f(lllllllllllllllllIlllllllllIllII * (float)Math.cos(lllllllllllllllllIllllllllllllII), lllllllllllllllllIlllllllllIllII * (float)Math.sin(lllllllllllllllllIllllllllllllII), lllllllllllllllllIlllllllllIIllI * 0.5f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIll * (float)Math.cos(lllllllllllllllllIllllllllllllII), lllllllllllllllllIlllllllllIlIll * (float)Math.sin(lllllllllllllllllIllllllllllllII), lllllllllllllllllIlllllllllIIllI * 0.5f);
            if (lllllllllllllllllIlllllllllIllIl < lllllllllllllllllIlllllllllIllll) {
                GL11.glVertex3f(lllllllllllllllllIlllllllllIllII * (float)Math.cos(lllllllllllllllllIllllllllllllII), lllllllllllllllllIlllllllllIllII * (float)Math.sin(lllllllllllllllllIllllllllllllII), lllllllllllllllllIlllllllllIIllI * 0.5f);
                GL11.glVertex3f(lllllllllllllllllIlllllllllIlIll * (float)Math.cos(lllllllllllllllllIllllllllllllII + 3.0f * lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIlIll * (float)Math.sin(lllllllllllllllllIllllllllllllII + 3.0f * lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIIllI * 0.5f);
            }
        }
        GL11.glEnd();
        GL11.glBegin(7);
        for (int lllllllllllllllllIlllllllllIllIl = 0; lllllllllllllllllIlllllllllIllIl < lllllllllllllllllIlllllllllIllll; ++lllllllllllllllllIlllllllllIllIl) {
            final float lllllllllllllllllIlllllllllllIll = lllllllllllllllllIlllllllllIllIl * 2.0f * 3.1415927f / lllllllllllllllllIlllllllllIllll;
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIll * (float)Math.cos(lllllllllllllllllIlllllllllllIll), lllllllllllllllllIlllllllllIlIll * (float)Math.sin(lllllllllllllllllIlllllllllllIll), lllllllllllllllllIlllllllllIIllI * 0.5f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIlI * (float)Math.cos(lllllllllllllllllIlllllllllllIll + lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIlIlI * (float)Math.sin(lllllllllllllllllIlllllllllllIll + lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIIllI * 0.5f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIlI * (float)Math.cos(lllllllllllllllllIlllllllllllIll + 2.0f * lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIlIlI * (float)Math.sin(lllllllllllllllllIlllllllllllIll + 2.0f * lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIIllI * 0.5f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIll * (float)Math.cos(lllllllllllllllllIlllllllllllIll + 3.0f * lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIlIll * (float)Math.sin(lllllllllllllllllIlllllllllllIll + 3.0f * lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIIllI * 0.5f);
        }
        GL11.glEnd();
        GL11.glNormal3f(0.0f, 0.0f, -1.0f);
        GL11.glBegin(8);
        for (int lllllllllllllllllIlllllllllIllIl = 0; lllllllllllllllllIlllllllllIllIl <= lllllllllllllllllIlllllllllIllll; ++lllllllllllllllllIlllllllllIllIl) {
            final float lllllllllllllllllIlllllllllllIlI = lllllllllllllllllIlllllllllIllIl * 2.0f * 3.1415927f / lllllllllllllllllIlllllllllIllll;
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIll * (float)Math.cos(lllllllllllllllllIlllllllllllIlI), lllllllllllllllllIlllllllllIlIll * (float)Math.sin(lllllllllllllllllIlllllllllllIlI), -lllllllllllllllllIlllllllllIIllI * 0.5f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIllII * (float)Math.cos(lllllllllllllllllIlllllllllllIlI), lllllllllllllllllIlllllllllIllII * (float)Math.sin(lllllllllllllllllIlllllllllllIlI), -lllllllllllllllllIlllllllllIIllI * 0.5f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIll * (float)Math.cos(lllllllllllllllllIlllllllllllIlI + 3.0f * lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIlIll * (float)Math.sin(lllllllllllllllllIlllllllllllIlI + 3.0f * lllllllllllllllllIlllllllllIlIIl), -lllllllllllllllllIlllllllllIIllI * 0.5f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIllII * (float)Math.cos(lllllllllllllllllIlllllllllllIlI), lllllllllllllllllIlllllllllIllII * (float)Math.sin(lllllllllllllllllIlllllllllllIlI), -lllllllllllllllllIlllllllllIIllI * 0.5f);
        }
        GL11.glEnd();
        GL11.glBegin(7);
        for (int lllllllllllllllllIlllllllllIllIl = 0; lllllllllllllllllIlllllllllIllIl < lllllllllllllllllIlllllllllIllll; ++lllllllllllllllllIlllllllllIllIl) {
            final float lllllllllllllllllIlllllllllllIIl = lllllllllllllllllIlllllllllIllIl * 2.0f * 3.1415927f / lllllllllllllllllIlllllllllIllll;
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIll * (float)Math.cos(lllllllllllllllllIlllllllllllIIl + 3.0f * lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIlIll * (float)Math.sin(lllllllllllllllllIlllllllllllIIl + 3.0f * lllllllllllllllllIlllllllllIlIIl), -lllllllllllllllllIlllllllllIIllI * 0.5f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIlI * (float)Math.cos(lllllllllllllllllIlllllllllllIIl + 2.0f * lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIlIlI * (float)Math.sin(lllllllllllllllllIlllllllllllIIl + 2.0f * lllllllllllllllllIlllllllllIlIIl), -lllllllllllllllllIlllllllllIIllI * 0.5f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIlI * (float)Math.cos(lllllllllllllllllIlllllllllllIIl + lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIlIlI * (float)Math.sin(lllllllllllllllllIlllllllllllIIl + lllllllllllllllllIlllllllllIlIIl), -lllllllllllllllllIlllllllllIIllI * 0.5f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIll * (float)Math.cos(lllllllllllllllllIlllllllllllIIl), lllllllllllllllllIlllllllllIlIll * (float)Math.sin(lllllllllllllllllIlllllllllllIIl), -lllllllllllllllllIlllllllllIIllI * 0.5f);
        }
        GL11.glEnd();
        GL11.glNormal3f(0.0f, 0.0f, 1.0f);
        GL11.glBegin(8);
        for (int lllllllllllllllllIlllllllllIllIl = 0; lllllllllllllllllIlllllllllIllIl < lllllllllllllllllIlllllllllIllll; ++lllllllllllllllllIlllllllllIllIl) {
            final float lllllllllllllllllIlllllllllllIII = lllllllllllllllllIlllllllllIllIl * 2.0f * 3.1415927f / lllllllllllllllllIlllllllllIllll;
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIll * (float)Math.cos(lllllllllllllllllIlllllllllllIII), lllllllllllllllllIlllllllllIlIll * (float)Math.sin(lllllllllllllllllIlllllllllllIII), lllllllllllllllllIlllllllllIIllI * 0.5f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIll * (float)Math.cos(lllllllllllllllllIlllllllllllIII), lllllllllllllllllIlllllllllIlIll * (float)Math.sin(lllllllllllllllllIlllllllllllIII), -lllllllllllllllllIlllllllllIIllI * 0.5f);
            float lllllllllllllllllIllllllllllIlll = lllllllllllllllllIlllllllllIlIlI * (float)Math.cos(lllllllllllllllllIlllllllllllIII + lllllllllllllllllIlllllllllIlIIl) - lllllllllllllllllIlllllllllIlIll * (float)Math.cos(lllllllllllllllllIlllllllllllIII);
            float lllllllllllllllllIllllllllllIllI = lllllllllllllllllIlllllllllIlIlI * (float)Math.sin(lllllllllllllllllIlllllllllllIII + lllllllllllllllllIlllllllllIlIIl) - lllllllllllllllllIlllllllllIlIll * (float)Math.sin(lllllllllllllllllIlllllllllllIII);
            final float lllllllllllllllllIllllllllllIlIl = (float)Math.sqrt(lllllllllllllllllIllllllllllIlll * lllllllllllllllllIllllllllllIlll + lllllllllllllllllIllllllllllIllI * lllllllllllllllllIllllllllllIllI);
            lllllllllllllllllIllllllllllIlll /= lllllllllllllllllIllllllllllIlIl;
            lllllllllllllllllIllllllllllIllI /= lllllllllllllllllIllllllllllIlIl;
            GL11.glNormal3f(lllllllllllllllllIllllllllllIllI, -lllllllllllllllllIllllllllllIlll, 0.0f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIlI * (float)Math.cos(lllllllllllllllllIlllllllllllIII + lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIlIlI * (float)Math.sin(lllllllllllllllllIlllllllllllIII + lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIIllI * 0.5f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIlI * (float)Math.cos(lllllllllllllllllIlllllllllllIII + lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIlIlI * (float)Math.sin(lllllllllllllllllIlllllllllllIII + lllllllllllllllllIlllllllllIlIIl), -lllllllllllllllllIlllllllllIIllI * 0.5f);
            GL11.glNormal3f((float)Math.cos(lllllllllllllllllIlllllllllllIII), (float)Math.sin(lllllllllllllllllIlllllllllllIII), 0.0f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIlI * (float)Math.cos(lllllllllllllllllIlllllllllllIII + 2.0f * lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIlIlI * (float)Math.sin(lllllllllllllllllIlllllllllllIII + 2.0f * lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIIllI * 0.5f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIlI * (float)Math.cos(lllllllllllllllllIlllllllllllIII + 2.0f * lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIlIlI * (float)Math.sin(lllllllllllllllllIlllllllllllIII + 2.0f * lllllllllllllllllIlllllllllIlIIl), -lllllllllllllllllIlllllllllIIllI * 0.5f);
            lllllllllllllllllIllllllllllIlll = lllllllllllllllllIlllllllllIlIll * (float)Math.cos(lllllllllllllllllIlllllllllllIII + 3.0f * lllllllllllllllllIlllllllllIlIIl) - lllllllllllllllllIlllllllllIlIlI * (float)Math.cos(lllllllllllllllllIlllllllllllIII + 2.0f * lllllllllllllllllIlllllllllIlIIl);
            lllllllllllllllllIllllllllllIllI = lllllllllllllllllIlllllllllIlIll * (float)Math.sin(lllllllllllllllllIlllllllllllIII + 3.0f * lllllllllllllllllIlllllllllIlIIl) - lllllllllllllllllIlllllllllIlIlI * (float)Math.sin(lllllllllllllllllIlllllllllllIII + 2.0f * lllllllllllllllllIlllllllllIlIIl);
            GL11.glNormal3f(lllllllllllllllllIllllllllllIllI, -lllllllllllllllllIllllllllllIlll, 0.0f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIll * (float)Math.cos(lllllllllllllllllIlllllllllllIII + 3.0f * lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIlIll * (float)Math.sin(lllllllllllllllllIlllllllllllIII + 3.0f * lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIIllI * 0.5f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIlIll * (float)Math.cos(lllllllllllllllllIlllllllllllIII + 3.0f * lllllllllllllllllIlllllllllIlIIl), lllllllllllllllllIlllllllllIlIll * (float)Math.sin(lllllllllllllllllIlllllllllllIII + 3.0f * lllllllllllllllllIlllllllllIlIIl), -lllllllllllllllllIlllllllllIIllI * 0.5f);
            GL11.glNormal3f((float)Math.cos(lllllllllllllllllIlllllllllllIII), (float)Math.sin(lllllllllllllllllIlllllllllllIII), 0.0f);
        }
        GL11.glVertex3f(lllllllllllllllllIlllllllllIlIll * (float)Math.cos(0.0), lllllllllllllllllIlllllllllIlIll * (float)Math.sin(0.0), lllllllllllllllllIlllllllllIIllI * 0.5f);
        GL11.glVertex3f(lllllllllllllllllIlllllllllIlIll * (float)Math.cos(0.0), lllllllllllllllllIlllllllllIlIll * (float)Math.sin(0.0), -lllllllllllllllllIlllllllllIIllI * 0.5f);
        GL11.glEnd();
        GL11.glShadeModel(7425);
        GL11.glBegin(8);
        for (int lllllllllllllllllIlllllllllIllIl = 0; lllllllllllllllllIlllllllllIllIl <= lllllllllllllllllIlllllllllIllll; ++lllllllllllllllllIlllllllllIllIl) {
            final float lllllllllllllllllIllllllllllIlII = lllllllllllllllllIlllllllllIllIl * 2.0f * 3.1415927f / lllllllllllllllllIlllllllllIllll;
            GL11.glNormal3f(-(float)Math.cos(lllllllllllllllllIllllllllllIlII), -(float)Math.sin(lllllllllllllllllIllllllllllIlII), 0.0f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIllII * (float)Math.cos(lllllllllllllllllIllllllllllIlII), lllllllllllllllllIlllllllllIllII * (float)Math.sin(lllllllllllllllllIllllllllllIlII), -lllllllllllllllllIlllllllllIIllI * 0.5f);
            GL11.glVertex3f(lllllllllllllllllIlllllllllIllII * (float)Math.cos(lllllllllllllllllIllllllllllIlII), lllllllllllllllllIlllllllllIllII * (float)Math.sin(lllllllllllllllllIllllllllllIlII), lllllllllllllllllIlllllllllIIllI * 0.5f);
        }
        GL11.glEnd();
    }
}
