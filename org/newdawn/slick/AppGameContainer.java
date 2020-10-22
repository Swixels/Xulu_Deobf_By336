package org.newdawn.slick;

import java.nio.*;
import org.newdawn.slick.util.*;
import org.lwjgl.opengl.*;
import org.lwjgl.input.*;
import java.security.*;
import org.newdawn.slick.openal.*;
import org.newdawn.slick.opengl.*;
import org.lwjgl.openal.*;
import org.lwjgl.*;
import java.io.*;

public class AppGameContainer extends GameContainer
{
    protected /* synthetic */ boolean updateOnlyOnVisible;
    protected /* synthetic */ boolean alphaSupport;
    protected /* synthetic */ DisplayMode targetDisplayMode;
    protected /* synthetic */ DisplayMode originalDisplayMode;
    
    @Override
    public void setIcons(final String[] llllllllllllllllllllIIlIllIlIIII) throws SlickException {
        final ByteBuffer[] llllllllllllllllllllIIlIllIIllll = new ByteBuffer[llllllllllllllllllllIIlIllIlIIII.length];
        for (int llllllllllllllllllllIIlIllIlIIlI = 0; llllllllllllllllllllIIlIllIlIIlI < llllllllllllllllllllIIlIllIlIIII.length; ++llllllllllllllllllllIIlIllIlIIlI) {
            boolean llllllllllllllllllllIIlIllIlIIll = true;
            LoadableImageData llllllllllllllllllllIIlIllIlIlII = null;
            if (llllllllllllllllllllIIlIllIlIIII[llllllllllllllllllllIIlIllIlIIlI].endsWith(".tga")) {
                final LoadableImageData llllllllllllllllllllIIlIllIlIllI = new TGAImageData();
            }
            else {
                llllllllllllllllllllIIlIllIlIIll = false;
                llllllllllllllllllllIIlIllIlIlII = new ImageIOImageData();
            }
            try {
                llllllllllllllllllllIIlIllIIllll[llllllllllllllllllllIIlIllIlIIlI] = llllllllllllllllllllIIlIllIlIlII.loadImage(ResourceLoader.getResourceAsStream(llllllllllllllllllllIIlIllIlIIII[llllllllllllllllllllIIlIllIlIIlI]), llllllllllllllllllllIIlIllIlIIll, false, null);
            }
            catch (Exception llllllllllllllllllllIIlIllIlIlIl) {
                Log.error(llllllllllllllllllllIIlIllIlIlIl);
                throw new SlickException("Failed to set the icon");
            }
        }
        Display.setIcon(llllllllllllllllllllIIlIllIIllll);
    }
    
    @Override
    public void setMouseCursor(final String llllllllllllllllllllIIllIlIlIllI, final int llllllllllllllllllllIIllIlIlIIlI, final int llllllllllllllllllllIIllIlIlIIIl) throws SlickException {
        try {
            final Cursor llllllllllllllllllllIIllIlIllIIl = CursorLoader.get().getCursor(llllllllllllllllllllIIllIlIlIllI, llllllllllllllllllllIIllIlIlIIlI, llllllllllllllllllllIIllIlIlIIIl);
            Mouse.setNativeCursor(llllllllllllllllllllIIllIlIllIIl);
        }
        catch (Throwable llllllllllllllllllllIIllIlIllIII) {
            Log.error("Failed to load and apply cursor.", llllllllllllllllllllIIllIlIllIII);
            throw new SlickException("Failed to set mouse cursor", llllllllllllllllllllIIllIlIllIII);
        }
    }
    
    static {
        AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
            @Override
            public Object run() {
                try {
                    Display.getDisplayMode();
                }
                catch (Exception llIIlIlIIIIII) {
                    Log.error(llIIlIlIIIIII);
                }
                return null;
            }
        });
    }
    
    public void setTitle(final String llllllllllllllllllllIIlllIIIIIlI) {
        Display.setTitle(llllllllllllllllllllIIlllIIIIIlI);
    }
    
    public AppGameContainer(final Game llllllllllllllllllllIIlllIIlIlll) throws SlickException {
        this(llllllllllllllllllllIIlllIIlIlll, 640, 480, false);
    }
    
    @Override
    public boolean isUpdatingOnlyWhenVisible() {
        return this.updateOnlyOnVisible;
    }
    
    @Override
    public boolean hasFocus() {
        return Display.isActive();
    }
    
    public boolean supportsAlphaInBackBuffer() {
        return this.alphaSupport;
    }
    
    public AppGameContainer(final Game llllllllllllllllllllIIlllIIlIIII, final int llllllllllllllllllllIIlllIIIllll, final int llllllllllllllllllllIIlllIIIlllI, final boolean llllllllllllllllllllIIlllIIIlIII) throws SlickException {
        super(llllllllllllllllllllIIlllIIlIIII);
        this.updateOnlyOnVisible = true;
        this.alphaSupport = false;
        this.originalDisplayMode = Display.getDisplayMode();
        this.setDisplayMode(llllllllllllllllllllIIlllIIIllll, llllllllllllllllllllIIlllIIIlllI, llllllllllllllllllllIIlllIIIlIII);
    }
    
    @Override
    public void setMouseCursor(final Cursor llllllllllllllllllllIIllIIllllIl, final int llllllllllllllllllllIIllIIllllII, final int llllllllllllllllllllIIllIIlllIll) throws SlickException {
        try {
            Mouse.setNativeCursor(llllllllllllllllllllIIllIIllllIl);
        }
        catch (Throwable llllllllllllllllllllIIllIIllllll) {
            Log.error("Failed to load and apply cursor.", llllllllllllllllllllIIllIIllllll);
            throw new SlickException("Failed to set mouse cursor", llllllllllllllllllllIIllIIllllll);
        }
    }
    
    @Override
    public void setUpdateOnlyWhenVisible(final boolean llllllllllllllllllllIIlIllllIIll) {
        this.updateOnlyOnVisible = llllllllllllllllllllIIlIllllIIll;
    }
    
    private void tryCreateDisplay(final PixelFormat llllllllllllllllllllIIllIIIlIIII) throws LWJGLException {
        if (AppGameContainer.SHARED_DRAWABLE == null) {
            Display.create(llllllllllllllllllllIIllIIIlIIII);
        }
        else {
            Display.create(llllllllllllllllllllIIllIIIlIIII, AppGameContainer.SHARED_DRAWABLE);
        }
    }
    
    @Override
    public int getScreenHeight() {
        return this.originalDisplayMode.getHeight();
    }
    
    @Override
    public void setMouseGrabbed(final boolean llllllllllllllllllllIIlIlllIIlll) {
        Mouse.setGrabbed(llllllllllllllllllllIIlIlllIIlll);
    }
    
    @Override
    public void reinit() throws SlickException {
        InternalTextureLoader.get().clear();
        SoundStore.get().clear();
        this.initSystem();
        this.enterOrtho();
        try {
            this.game.init(this);
        }
        catch (SlickException llllllllllllllllllllIIllIIIlIllI) {
            Log.error(llllllllllllllllllllIIllIIIlIllI);
            this.running = false;
        }
    }
    
    @Override
    public void setFullscreen(final boolean llllllllllllllllllllIIllIlIlllll) throws SlickException {
        if (this.isFullscreen() == llllllllllllllllllllIIllIlIlllll) {
            return;
        }
        Label_0063: {
            if (!llllllllllllllllllllIIllIlIlllll) {
                try {
                    Display.setFullscreen(llllllllllllllllllllIIllIlIlllll);
                    break Label_0063;
                }
                catch (LWJGLException llllllllllllllllllllIIllIllIIIll) {
                    throw new SlickException(String.valueOf(new StringBuilder().append("Unable to set fullscreen=").append(llllllllllllllllllllIIllIlIlllll)), (Throwable)llllllllllllllllllllIIllIllIIIll);
                }
            }
            this.setDisplayMode(this.width, this.height, llllllllllllllllllllIIllIlIlllll);
        }
        this.getDelta();
    }
    
    @Override
    public void setDefaultMouseCursor() {
        try {
            Mouse.setNativeCursor((Cursor)null);
        }
        catch (LWJGLException llllllllllllllllllllIIlIllIIIlll) {
            Log.error("Failed to reset mouse cursor", (Throwable)llllllllllllllllllllIIlIllIIIlll);
        }
    }
    
    @Override
    public void setMouseCursor(final Image llllllllllllllllllllIIllIIlIIIll, final int llllllllllllllllllllIIllIIlIIIlI, final int llllllllllllllllllllIIllIIlIIIIl) throws SlickException {
        try {
            final Image llllllllllllllllllllIIllIIlIlIIl = new Image(this.get2Fold(llllllllllllllllllllIIllIIlIIIll.getWidth()), this.get2Fold(llllllllllllllllllllIIllIIlIIIll.getHeight()));
            final Graphics llllllllllllllllllllIIllIIlIlIII = llllllllllllllllllllIIllIIlIlIIl.getGraphics();
            final ByteBuffer llllllllllllllllllllIIllIIlIIlll = BufferUtils.createByteBuffer(llllllllllllllllllllIIllIIlIlIIl.getWidth() * llllllllllllllllllllIIllIIlIlIIl.getHeight() * 4);
            llllllllllllllllllllIIllIIlIlIII.drawImage(llllllllllllllllllllIIllIIlIIIll.getFlippedCopy(false, true), 0.0f, 0.0f);
            llllllllllllllllllllIIllIIlIlIII.flush();
            llllllllllllllllllllIIllIIlIlIII.getArea(0, 0, llllllllllllllllllllIIllIIlIlIIl.getWidth(), llllllllllllllllllllIIllIIlIlIIl.getHeight(), llllllllllllllllllllIIllIIlIIlll);
            final Cursor llllllllllllllllllllIIllIIlIIllI = CursorLoader.get().getCursor(llllllllllllllllllllIIllIIlIIlll, llllllllllllllllllllIIllIIlIIIlI, llllllllllllllllllllIIllIIlIIIIl, llllllllllllllllllllIIllIIlIlIIl.getWidth(), llllllllllllllllllllIIllIIlIIIll.getHeight());
            Mouse.setNativeCursor(llllllllllllllllllllIIllIIlIIllI);
        }
        catch (Throwable llllllllllllllllllllIIllIIlIIlIl) {
            Log.error("Failed to load and apply cursor.", llllllllllllllllllllIIllIIlIIlIl);
            throw new SlickException("Failed to set mouse cursor", llllllllllllllllllllIIllIIlIIlIl);
        }
    }
    
    public void start() throws SlickException {
        try {
            this.setup();
            this.getDelta();
            while (this.running()) {
                this.gameLoop();
            }
        }
        finally {
            this.destroy();
        }
        if (this.forceExit) {
            System.exit(0);
        }
    }
    
    @Override
    public void setMouseCursor(final ImageData llllllllllllllllllllIIllIlIIIlIl, final int llllllllllllllllllllIIllIlIIIlII, final int llllllllllllllllllllIIllIlIIIllI) throws SlickException {
        try {
            final Cursor llllllllllllllllllllIIllIlIIlIll = CursorLoader.get().getCursor(llllllllllllllllllllIIllIlIIIlIl, llllllllllllllllllllIIllIlIIIlII, llllllllllllllllllllIIllIlIIIllI);
            Mouse.setNativeCursor(llllllllllllllllllllIIllIlIIlIll);
        }
        catch (Throwable llllllllllllllllllllIIllIlIIlIlI) {
            Log.error("Failed to load and apply cursor.", llllllllllllllllllllIIllIlIIlIlI);
            throw new SlickException("Failed to set mouse cursor", llllllllllllllllllllIIllIlIIlIlI);
        }
    }
    
    public void setDisplayMode(final int llllllllllllllllllllIIllIlllIIlI, final int llllllllllllllllllllIIllIllIllIl, final boolean llllllllllllllllllllIIllIlllIIII) throws SlickException {
        if (this.width == llllllllllllllllllllIIllIlllIIlI && this.height == llllllllllllllllllllIIllIllIllIl && this.isFullscreen() == llllllllllllllllllllIIllIlllIIII) {
            return;
        }
        try {
            this.targetDisplayMode = null;
            if (llllllllllllllllllllIIllIlllIIII) {
                final DisplayMode[] llllllllllllllllllllIIllIlllIllI = Display.getAvailableDisplayModes();
                int llllllllllllllllllllIIllIlllIlIl = 0;
                for (int llllllllllllllllllllIIllIlllIlll = 0; llllllllllllllllllllIIllIlllIlll < llllllllllllllllllllIIllIlllIllI.length; ++llllllllllllllllllllIIllIlllIlll) {
                    final DisplayMode llllllllllllllllllllIIllIllllIII = llllllllllllllllllllIIllIlllIllI[llllllllllllllllllllIIllIlllIlll];
                    if (llllllllllllllllllllIIllIllllIII.getWidth() == llllllllllllllllllllIIllIlllIIlI && llllllllllllllllllllIIllIllllIII.getHeight() == llllllllllllllllllllIIllIllIllIl) {
                        if ((this.targetDisplayMode == null || llllllllllllllllllllIIllIllllIII.getFrequency() >= llllllllllllllllllllIIllIlllIlIl) && (this.targetDisplayMode == null || llllllllllllllllllllIIllIllllIII.getBitsPerPixel() > this.targetDisplayMode.getBitsPerPixel())) {
                            this.targetDisplayMode = llllllllllllllllllllIIllIllllIII;
                            llllllllllllllllllllIIllIlllIlIl = this.targetDisplayMode.getFrequency();
                        }
                        if (llllllllllllllllllllIIllIllllIII.getBitsPerPixel() == this.originalDisplayMode.getBitsPerPixel() && llllllllllllllllllllIIllIllllIII.getFrequency() == this.originalDisplayMode.getFrequency()) {
                            this.targetDisplayMode = llllllllllllllllllllIIllIllllIII;
                            break;
                        }
                    }
                }
            }
            else {
                this.targetDisplayMode = new DisplayMode(llllllllllllllllllllIIllIlllIIlI, llllllllllllllllllllIIllIllIllIl);
            }
            if (this.targetDisplayMode == null) {
                throw new SlickException(String.valueOf(new StringBuilder().append("Failed to find value mode: ").append(llllllllllllllllllllIIllIlllIIlI).append("x").append(llllllllllllllllllllIIllIllIllIl).append(" fs=").append(llllllllllllllllllllIIllIlllIIII)));
            }
            this.width = llllllllllllllllllllIIllIlllIIlI;
            this.height = llllllllllllllllllllIIllIllIllIl;
            Display.setDisplayMode(this.targetDisplayMode);
            Display.setFullscreen(llllllllllllllllllllIIllIlllIIII);
            if (Display.isCreated()) {
                this.initGL();
                this.enterOrtho();
            }
            if (this.targetDisplayMode.getBitsPerPixel() == 16) {
                InternalTextureLoader.get().set16BitMode();
            }
        }
        catch (LWJGLException llllllllllllllllllllIIllIlllIlII) {
            throw new SlickException(String.valueOf(new StringBuilder().append("Unable to setup mode ").append(llllllllllllllllllllIIllIlllIIlI).append("x").append(llllllllllllllllllllIIllIllIllIl).append(" fullscreen=").append(llllllllllllllllllllIIllIlllIIII)), (Throwable)llllllllllllllllllllIIllIlllIlII);
        }
        this.getDelta();
    }
    
    @Override
    public void setIcon(final String llllllllllllllllllllIIlIlllIllII) throws SlickException {
        this.setIcons(new String[] { llllllllllllllllllllIIlIlllIllII });
    }
    
    public void destroy() {
        Display.destroy();
        AL.destroy();
    }
    
    protected void setup() throws SlickException {
        if (this.targetDisplayMode == null) {
            this.setDisplayMode(640, 480, false);
        }
        Display.setTitle(this.game.getTitle());
        Log.info(String.valueOf(new StringBuilder().append("LWJGL Version: ").append(Sys.getVersion())));
        Log.info(String.valueOf(new StringBuilder().append("OriginalDisplayMode: ").append(this.originalDisplayMode)));
        Log.info(String.valueOf(new StringBuilder().append("TargetDisplayMode: ").append(this.targetDisplayMode)));
        AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
            @Override
            public Object run() {
                try {
                    final PixelFormat llIllIIIIIIll = new PixelFormat(8, 8, GameContainer.stencil ? 8 : 0, AppGameContainer.this.samples);
                    AppGameContainer.this.tryCreateDisplay(llIllIIIIIIll);
                    AppGameContainer.this.supportsMultiSample = true;
                }
                catch (Exception llIlIllllllll) {
                    Display.destroy();
                    try {
                        final PixelFormat llIllIIIIIIlI = new PixelFormat(8, 8, GameContainer.stencil ? 8 : 0);
                        AppGameContainer.this.tryCreateDisplay(llIllIIIIIIlI);
                        AppGameContainer.this.alphaSupport = false;
                    }
                    catch (Exception llIllIIIIIIII) {
                        Display.destroy();
                        try {
                            AppGameContainer.this.tryCreateDisplay(new PixelFormat());
                        }
                        catch (Exception llIllIIIIIIIl) {
                            Log.error(llIllIIIIIIIl);
                        }
                    }
                }
                return null;
            }
        });
        if (!Display.isCreated()) {
            throw new SlickException("Failed to initialise the LWJGL display");
        }
        this.initSystem();
        this.enterOrtho();
        try {
            this.getInput().initControllers();
        }
        catch (SlickException llllllllllllllllllllIIllIIIIIlll) {
            Log.info("Controllers not available");
        }
        catch (Throwable llllllllllllllllllllIIllIIIIIllI) {
            Log.info("Controllers not available");
        }
        try {
            this.game.init(this);
        }
        catch (SlickException llllllllllllllllllllIIllIIIIIlIl) {
            Log.error(llllllllllllllllllllIIllIIIIIlIl);
            this.running = false;
        }
    }
    
    @Override
    public boolean isFullscreen() {
        return Display.isFullscreen();
    }
    
    @Override
    public boolean isMouseGrabbed() {
        return Mouse.isGrabbed();
    }
    
    private int get2Fold(final int llllllllllllllllllllIIllIIllIIll) {
        int llllllllllllllllllllIIllIIllIlII;
        for (llllllllllllllllllllIIllIIllIlII = 2; llllllllllllllllllllIIllIIllIlII < llllllllllllllllllllIIllIIllIIll; llllllllllllllllllllIIllIIllIlII *= 2) {}
        return llllllllllllllllllllIIllIIllIlII;
    }
    
    @Override
    public int getScreenWidth() {
        return this.originalDisplayMode.getWidth();
    }
    
    protected void gameLoop() throws SlickException {
        final int llllllllllllllllllllIIlIllllllII = this.getDelta();
        if (!Display.isVisible() && this.updateOnlyOnVisible) {
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
        }
        else {
            try {
                this.updateAndRender(llllllllllllllllllllIIlIllllllII);
            }
            catch (SlickException llllllllllllllllllllIIlIlllllllI) {
                Log.error(llllllllllllllllllllIIlIlllllllI);
                this.running = false;
                return;
            }
        }
        this.updateFPS();
        Display.update();
        if (Display.isCloseRequested() && this.game.closeRequested()) {
            this.running = false;
        }
    }
    
    private class NullOutputStream extends OutputStream
    {
        @Override
        public void write(final int llIllIIllIllIlI) throws IOException {
        }
    }
}
