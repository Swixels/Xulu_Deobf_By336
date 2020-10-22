package org.newdawn.slick;

import org.newdawn.slick.gui.*;
import org.newdawn.slick.openal.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.*;
import java.io.*;
import org.newdawn.slick.opengl.renderer.*;
import java.util.*;
import org.newdawn.slick.util.*;
import org.lwjgl.*;

public abstract class GameContainer implements GUIContext
{
    protected /* synthetic */ boolean clearEachFrame;
    protected /* synthetic */ boolean vsync;
    protected /* synthetic */ int height;
    protected static /* synthetic */ SGL GL;
    private /* synthetic */ Font defaultFont;
    protected static /* synthetic */ boolean stencil;
    protected /* synthetic */ boolean alwaysRender;
    protected /* synthetic */ boolean paused;
    protected /* synthetic */ int recordedFPS;
    protected /* synthetic */ Input input;
    protected /* synthetic */ int samples;
    protected static /* synthetic */ Drawable SHARED_DRAWABLE;
    protected /* synthetic */ int fps;
    protected /* synthetic */ int targetFPS;
    protected /* synthetic */ long minimumLogicInterval;
    protected /* synthetic */ boolean smoothDeltas;
    protected /* synthetic */ boolean forceExit;
    protected /* synthetic */ long storedDelta;
    protected /* synthetic */ long maximumLogicInterval;
    protected /* synthetic */ boolean supportsMultiSample;
    protected /* synthetic */ long lastFPS;
    protected /* synthetic */ long lastFrame;
    private /* synthetic */ Graphics graphics;
    protected /* synthetic */ Game lastGame;
    protected /* synthetic */ Game game;
    private /* synthetic */ boolean showFPS;
    protected /* synthetic */ int width;
    protected /* synthetic */ boolean running;
    
    protected void updateAndRender(int llllllllllllllllIlIllIllllIIIIlI) throws SlickException {
        if (this.smoothDeltas && this.getFPS() != 0) {
            llllllllllllllllIlIllIllllIIIIlI = 1000 / this.getFPS();
        }
        this.input.poll(this.width, this.height);
        Music.poll(llllllllllllllllIlIllIllllIIIIlI);
        Label_0232: {
            if (!this.paused) {
                this.storedDelta += llllllllllllllllIlIllIllllIIIIlI;
                if (this.storedDelta < this.minimumLogicInterval) {
                    break Label_0232;
                }
                try {
                    if (this.maximumLogicInterval != 0L) {
                        final long llllllllllllllllIlIllIllllIIlIIl = this.storedDelta / this.maximumLogicInterval;
                        for (int llllllllllllllllIlIllIllllIIlIlI = 0; llllllllllllllllIlIllIllllIIlIlI < llllllllllllllllIlIllIllllIIlIIl; ++llllllllllllllllIlIllIllllIIlIlI) {
                            this.game.update(this, (int)this.maximumLogicInterval);
                        }
                        final int llllllllllllllllIlIllIllllIIlIII = (int)(this.storedDelta % this.maximumLogicInterval);
                        if (llllllllllllllllIlIllIllllIIlIII > this.minimumLogicInterval) {
                            this.game.update(this, (int)(llllllllllllllllIlIllIllllIIlIII % this.maximumLogicInterval));
                            this.storedDelta = 0L;
                        }
                        else {
                            this.storedDelta = llllllllllllllllIlIllIllllIIlIII;
                        }
                    }
                    else {
                        this.game.update(this, (int)this.storedDelta);
                        this.storedDelta = 0L;
                    }
                    break Label_0232;
                }
                catch (Throwable llllllllllllllllIlIllIllllIIIlll) {
                    Log.error(llllllllllllllllIlIllIllllIIIlll);
                    throw new SlickException("Game.update() failure - check the game code.");
                }
            }
            this.game.update(this, 0);
        }
        if (this.hasFocus() || this.getAlwaysRender()) {
            if (this.clearEachFrame) {
                GameContainer.GL.glClear(16640);
            }
            GameContainer.GL.glLoadIdentity();
            this.graphics.resetTransform();
            this.graphics.resetFont();
            this.graphics.resetLineWidth();
            this.graphics.setAntiAlias(false);
            try {
                this.game.render(this, this.graphics);
            }
            catch (Throwable llllllllllllllllIlIllIllllIIIllI) {
                Log.error(llllllllllllllllIlIllIllllIIIllI);
                throw new SlickException("Game.render() failure - check the game code.");
            }
            this.graphics.resetTransform();
            if (this.showFPS) {
                this.defaultFont.drawString(10.0f, 10.0f, String.valueOf(new StringBuilder().append("FPS: ").append(this.recordedFPS)));
            }
            GameContainer.GL.flush();
        }
        if (this.targetFPS != -1) {
            Display.sync(this.targetFPS);
        }
    }
    
    protected void initGL() {
        Log.info(String.valueOf(new StringBuilder().append("Starting display ").append(this.width).append("x").append(this.height)));
        GameContainer.GL.initDisplay(this.width, this.height);
        if (this.input == null) {
            this.input = new Input(this.height);
        }
        this.input.init(this.height);
        if (this.game instanceof InputListener) {
            this.input.removeListener((InputListener)this.game);
            this.input.addListener((InputListener)this.game);
        }
        if (this.graphics != null) {
            this.graphics.setDimensions(this.getWidth(), this.getHeight());
        }
        this.lastGame = this.game;
    }
    
    public boolean getAlwaysRender() {
        return this.alwaysRender;
    }
    
    public Graphics getGraphics() {
        return this.graphics;
    }
    
    public boolean isVSyncRequested() {
        return this.vsync;
    }
    
    @Override
    public abstract void setMouseCursor(final String p0, final int p1, final int p2) throws SlickException;
    
    @Override
    public int getHeight() {
        return this.height;
    }
    
    protected void enterOrtho() {
        this.enterOrtho(this.width, this.height);
    }
    
    public float getMusicVolume() {
        return SoundStore.get().getMusicVolume();
    }
    
    public void setMultiSample(final int llllllllllllllllIlIlllIIlIllIIII) {
        this.samples = llllllllllllllllIlIlllIIlIllIIII;
    }
    
    public boolean isMusicOn() {
        return SoundStore.get().musicOn();
    }
    
    public void setVerbose(final boolean llllllllllllllllIlIllIlllIIlIllI) {
        Log.setVerbose(llllllllllllllllIlIllIlllIIlIllI);
    }
    
    public void setForceExit(final boolean llllllllllllllllIlIlllIIlIIlllIl) {
        this.forceExit = llllllllllllllllIlIlllIIlIIlllIl;
    }
    
    @Override
    public int getWidth() {
        return this.width;
    }
    
    @Override
    public abstract void setMouseCursor(final Cursor p0, final int p1, final int p2) throws SlickException;
    
    public static void enableSharedContext() throws SlickException {
        try {
            GameContainer.SHARED_DRAWABLE = (Drawable)new Pbuffer(64, 64, new PixelFormat(8, 0, 0), (Drawable)null);
        }
        catch (LWJGLException llllllllllllllllIlIlllIIlIIIlIII) {
            throw new SlickException("Unable to create the pbuffer used for shard context, buffers not supported", (Throwable)llllllllllllllllIlIlllIIlIIIlIII);
        }
    }
    
    @Override
    public abstract int getScreenWidth();
    
    public void setUpdateOnlyWhenVisible(final boolean llllllllllllllllIlIllIlllIlllllI) {
    }
    
    public void setTargetFrameRate(final int llllllllllllllllIlIllIlllIlIIlll) {
        this.targetFPS = llllllllllllllllIlIllIlllIlIIlll;
    }
    
    public boolean isShowingFPS() {
        return this.showFPS;
    }
    
    public void reinit() throws SlickException {
    }
    
    @Override
    public Font getDefaultFont() {
        return this.defaultFont;
    }
    
    @Override
    public abstract void setMouseCursor(final ImageData p0, final int p1, final int p2) throws SlickException;
    
    public int getSamples() {
        return this.samples;
    }
    
    public boolean isPaused() {
        return this.paused;
    }
    
    public void setSmoothDeltas(final boolean llllllllllllllllIlIlllIIlIIlIlll) {
        this.smoothDeltas = llllllllllllllllIlIlllIIlIIlIlll;
    }
    
    public static void enableStencil() {
        GameContainer.stencil = true;
    }
    
    public boolean supportsMultiSample() {
        return this.supportsMultiSample;
    }
    
    public boolean isFullscreen() {
        return false;
    }
    
    public boolean isUpdatingOnlyWhenVisible() {
        return true;
    }
    
    protected GameContainer(final Game llllllllllllllllIlIlllIIlIlllIlI) {
        this.running = true;
        this.targetFPS = -1;
        this.showFPS = true;
        this.minimumLogicInterval = 1L;
        this.maximumLogicInterval = 0L;
        this.clearEachFrame = true;
        this.forceExit = true;
        this.game = llllllllllllllllIlIlllIIlIlllIlI;
        this.lastFrame = this.getTime();
        getBuildVersion();
        Log.checkVerboseLogSetting();
    }
    
    public abstract boolean hasFocus();
    
    protected int getDelta() {
        final long llllllllllllllllIlIllIlllllIIIlI = this.getTime();
        final int llllllllllllllllIlIllIlllllIIIIl = (int)(llllllllllllllllIlIllIlllllIIIlI - this.lastFrame);
        this.lastFrame = llllllllllllllllIlIllIlllllIIIlI;
        return llllllllllllllllIlIllIlllllIIIIl;
    }
    
    @Override
    public abstract int getScreenHeight();
    
    protected void initSystem() throws SlickException {
        this.initGL();
        this.setMusicVolume(1.0f);
        this.setSoundVolume(1.0f);
        this.graphics = new Graphics(this.width, this.height);
        this.defaultFont = this.graphics.getFont();
    }
    
    protected boolean running() {
        return this.running;
    }
    
    public void setAnimatedMouseCursor(final String llllllllllllllllIlIllIllllllIIll, final int llllllllllllllllIlIllIllllllIIlI, final int llllllllllllllllIlIllIllllllIIIl, final int llllllllllllllllIlIllIllllllIIII, final int llllllllllllllllIlIllIlllllIllll, final int[] llllllllllllllllIlIllIlllllIlllI) throws SlickException {
        try {
            final Cursor llllllllllllllllIlIllIlllllllllI = CursorLoader.get().getAnimatedCursor(llllllllllllllllIlIllIllllllIIll, llllllllllllllllIlIllIllllllIIlI, llllllllllllllllIlIllIllllllIIIl, llllllllllllllllIlIllIllllllIIII, llllllllllllllllIlIllIlllllIllll, llllllllllllllllIlIllIlllllIlllI);
            this.setMouseCursor(llllllllllllllllIlIllIlllllllllI, llllllllllllllllIlIllIllllllIIlI, llllllllllllllllIlIllIllllllIIIl);
        }
        catch (IOException llllllllllllllllIlIllIllllllllIl) {
            throw new SlickException("Failed to set mouse cursor", llllllllllllllllIlIllIllllllllIl);
        }
        catch (LWJGLException llllllllllllllllIlIllIllllllllII) {
            throw new SlickException("Failed to set mouse cursor", (Throwable)llllllllllllllllIlIllIllllllllII);
        }
    }
    
    public float getAspectRatio() {
        return (float)(this.getWidth() / this.getHeight());
    }
    
    public void setMusicVolume(final float llllllllllllllllIlIlllIIIIlIIIlI) {
        SoundStore.get().setMusicVolume(llllllllllllllllIlIlllIIIIlIIIlI);
    }
    
    @Override
    public abstract void setDefaultMouseCursor();
    
    public void setMaximumLogicUpdateInterval(final int llllllllllllllllIlIllIllllIIllll) {
        this.maximumLogicInterval = llllllllllllllllIlIllIllllIIllll;
    }
    
    public void setVSync(final boolean llllllllllllllllIlIllIlllIIlllll) {
        this.vsync = llllllllllllllllIlIllIlllIIlllll;
        Display.setVSyncEnabled(llllllllllllllllIlIllIlllIIlllll);
    }
    
    public void sleep(final int llllllllllllllllIlIlllIIIIIIlIIl) {
        final long llllllllllllllllIlIlllIIIIIIlIll = this.getTime() + llllllllllllllllIlIlllIIIIIIlIIl;
        while (this.getTime() < llllllllllllllllIlIlllIIIIIIlIll) {
            try {
                Thread.sleep(1L);
            }
            catch (Exception llllllllllllllllIlIlllIIIIIIIlll) {}
        }
    }
    
    public void setSoundVolume(final float llllllllllllllllIlIlllIIIIlIIllI) {
        SoundStore.get().setSoundVolume(llllllllllllllllIlIlllIIIIlIIllI);
    }
    
    static {
        GameContainer.GL = Renderer.get();
    }
    
    public void exit() {
        this.running = false;
    }
    
    public boolean isSoundOn() {
        return SoundStore.get().soundsOn();
    }
    
    public float getSoundVolume() {
        return SoundStore.get().getSoundVolume();
    }
    
    public static Drawable getSharedContext() {
        return GameContainer.SHARED_DRAWABLE;
    }
    
    public void setDefaultFont(final Font llllllllllllllllIlIlllIIlIllIlII) {
        if (llllllllllllllllIlIlllIIlIllIlII != null) {
            this.defaultFont = llllllllllllllllIlIlllIIlIllIlII;
        }
        else {
            Log.warn("Please provide a non null font");
        }
    }
    
    public abstract void setMouseCursor(final Image p0, final int p1, final int p2) throws SlickException;
    
    public void setMinimumLogicUpdateInterval(final int llllllllllllllllIlIllIllllIlIlIl) {
        this.minimumLogicInterval = llllllllllllllllIlIllIllllIlIlIl;
    }
    
    public void pause() {
        this.setPaused(true);
    }
    
    public void setAlwaysRender(final boolean llllllllllllllllIlIlllIIIlIllIlI) {
        this.alwaysRender = llllllllllllllllIlIlllIIIlIllIlI;
    }
    
    public int getFPS() {
        return this.recordedFPS;
    }
    
    @Override
    public Input getInput() {
        return this.input;
    }
    
    protected void enterOrtho(final int llllllllllllllllIlIllIlllIIIlIIl, final int llllllllllllllllIlIllIlllIIIlIII) {
        GameContainer.GL.enterOrtho(llllllllllllllllIlIllIlllIIIlIIl, llllllllllllllllIlIllIlllIIIlIII);
    }
    
    public abstract void setIcons(final String[] p0) throws SlickException;
    
    public abstract void setIcon(final String p0) throws SlickException;
    
    protected void updateFPS() {
        if (this.getTime() - this.lastFPS > 1000L) {
            this.lastFPS = this.getTime();
            this.recordedFPS = this.fps;
            this.fps = 0;
        }
        ++this.fps;
    }
    
    public void setFullscreen(final boolean llllllllllllllllIlIlllIIlIIIlIlI) throws SlickException {
    }
    
    public void setShowFPS(final boolean llllllllllllllllIlIllIlllIllIIII) {
        this.showFPS = llllllllllllllllIlIllIlllIllIIII;
    }
    
    public void resume() {
        this.setPaused(false);
    }
    
    public abstract boolean isMouseGrabbed();
    
    public abstract void setMouseGrabbed(final boolean p0);
    
    public static int getBuildVersion() {
        try {
            final Properties llllllllllllllllIlIlllIIIlIIlllI = new Properties();
            llllllllllllllllIlIlllIIIlIIlllI.load(ResourceLoader.getResourceAsStream("version"));
            final int llllllllllllllllIlIlllIIIlIIllII = Integer.parseInt(llllllllllllllllIlIlllIIIlIIlllI.getProperty("build"));
            Log.info(String.valueOf(new StringBuilder().append("Slick Build #").append(llllllllllllllllIlIlllIIIlIIllII)));
            return llllllllllllllllIlIlllIIIlIIllII;
        }
        catch (Exception llllllllllllllllIlIlllIIIlIIlIll) {
            Log.error("Unable to determine Slick build number");
            return -1;
        }
    }
    
    public void setMusicOn(final boolean llllllllllllllllIlIlllIIIIlllIIl) {
        SoundStore.get().setMusicOn(llllllllllllllllIlIlllIIIIlllIIl);
    }
    
    @Override
    public long getTime() {
        return Sys.getTime() * 1000L / Sys.getTimerResolution();
    }
    
    public void setClearEachFrame(final boolean llllllllllllllllIlIlllIIlIIIIIII) {
        this.clearEachFrame = llllllllllllllllIlIlllIIlIIIIIII;
    }
    
    public void setSoundOn(final boolean llllllllllllllllIlIlllIIIIllIIlI) {
        SoundStore.get().setSoundsOn(llllllllllllllllIlIlllIIIIllIIlI);
    }
    
    public void setPaused(final boolean llllllllllllllllIlIlllIIIllIIlll) {
        this.paused = llllllllllllllllIlIlllIIIllIIlll;
    }
}
