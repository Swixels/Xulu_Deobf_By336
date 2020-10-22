package org.newdawn.slick;

import java.applet.*;
import org.newdawn.slick.util.*;
import java.nio.*;
import org.lwjgl.input.*;
import org.lwjgl.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.openal.*;
import org.lwjgl.opengl.*;
import java.io.*;
import java.awt.*;

public class AppletGameContainer extends Applet
{
    protected /* synthetic */ boolean alphaSupport;
    protected /* synthetic */ Thread gameThread;
    protected /* synthetic */ ContainerPanel canvas;
    protected /* synthetic */ Container container;
    protected /* synthetic */ Canvas displayParent;
    
    @Override
    public void start() {
    }
    
    @Override
    public void destroy() {
        if (this.displayParent != null) {
            this.remove(this.displayParent);
        }
        super.destroy();
        Log.info("Clear up");
    }
    
    @Override
    public void init() {
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.setIgnoreRepaint(true);
        try {
            final Game llIIIlIIIlIIl = (Game)Class.forName(this.getParameter("game")).newInstance();
            this.container = new Container(llIIIlIIIlIIl);
            this.canvas = new ContainerPanel(this.container);
            this.displayParent = new Canvas() {
                @Override
                public final void addNotify() {
                    super.addNotify();
                    AppletGameContainer.this.startLWJGL();
                }
                
                @Override
                public final void removeNotify() {
                    AppletGameContainer.this.destroyLWJGL();
                    super.removeNotify();
                }
            };
            this.displayParent.setSize(this.getWidth(), this.getHeight());
            this.add(this.displayParent);
            this.displayParent.setFocusable(true);
            this.displayParent.requestFocus();
            this.displayParent.setIgnoreRepaint(true);
            this.setVisible(true);
        }
        catch (Exception llIIIlIIIlIII) {
            Log.error(llIIIlIIIlIII);
            throw new RuntimeException("Unable to create game container");
        }
    }
    
    private void destroyLWJGL() {
        this.container.stopApplet();
        try {
            this.gameThread.join();
        }
        catch (InterruptedException llIIIlIIlIlII) {
            Log.error(llIIIlIIlIlII);
        }
    }
    
    @Override
    public void stop() {
    }
    
    public GameContainer getContainer() {
        return this.container;
    }
    
    public void startLWJGL() {
        if (this.gameThread != null) {
            return;
        }
        this.gameThread = new Thread() {
            @Override
            public void run() {
                try {
                    AppletGameContainer.this.canvas.start();
                }
                catch (Exception llllllllllllllllllIIlIlllIlIIIII) {
                    llllllllllllllllllIIlIlllIlIIIII.printStackTrace();
                    if (Display.isCreated()) {
                        Display.destroy();
                    }
                    AppletGameContainer.this.displayParent.setVisible(false);
                    AppletGameContainer.this.add(new ConsolePanel(llllllllllllllllllIIlIlllIlIIIII));
                    AppletGameContainer.this.validate();
                }
            }
        };
        this.gameThread.start();
    }
    
    public AppletGameContainer() {
        this.alphaSupport = true;
    }
    
    public class Container extends GameContainer
    {
        @Override
        public void setMouseCursor(final Image llllllllllllllllIllIllIlllllIIlI, final int llllllllllllllllIllIllIlllllIlIl, final int llllllllllllllllIllIllIlllllIIII) throws SlickException {
            try {
                final Image llllllllllllllllIllIllIlllllllII = new Image(this.get2Fold(llllllllllllllllIllIllIlllllIIlI.getWidth()), this.get2Fold(llllllllllllllllIllIllIlllllIIlI.getHeight()));
                final Graphics llllllllllllllllIllIllIllllllIll = llllllllllllllllIllIllIlllllllII.getGraphics();
                final ByteBuffer llllllllllllllllIllIllIllllllIlI = BufferUtils.createByteBuffer(llllllllllllllllIllIllIlllllllII.getWidth() * llllllllllllllllIllIllIlllllllII.getHeight() * 4);
                llllllllllllllllIllIllIllllllIll.drawImage(llllllllllllllllIllIllIlllllIIlI.getFlippedCopy(false, true), 0.0f, 0.0f);
                llllllllllllllllIllIllIllllllIll.flush();
                llllllllllllllllIllIllIllllllIll.getArea(0, 0, llllllllllllllllIllIllIlllllllII.getWidth(), llllllllllllllllIllIllIlllllllII.getHeight(), llllllllllllllllIllIllIllllllIlI);
                final Cursor llllllllllllllllIllIllIllllllIIl = CursorLoader.get().getCursor(llllllllllllllllIllIllIllllllIlI, llllllllllllllllIllIllIlllllIlIl, llllllllllllllllIllIllIlllllIIII, llllllllllllllllIllIllIlllllllII.getWidth(), llllllllllllllllIllIllIlllllllII.getHeight());
                Mouse.setNativeCursor(llllllllllllllllIllIllIllllllIIl);
            }
            catch (Throwable llllllllllllllllIllIllIllllllIII) {
                Log.error("Failed to load and apply cursor.", llllllllllllllllIllIllIllllllIII);
                throw new SlickException("Failed to set mouse cursor", llllllllllllllllIllIllIllllllIII);
            }
        }
        
        @Override
        public void setMouseCursor(final ImageData llllllllllllllllIllIllIlllIlllll, final int llllllllllllllllIllIllIlllIllllI, final int llllllllllllllllIllIllIlllIlllIl) throws SlickException {
            try {
                final Cursor llllllllllllllllIllIllIllllIIlIl = CursorLoader.get().getCursor(llllllllllllllllIllIllIlllIlllll, llllllllllllllllIllIllIlllIllllI, llllllllllllllllIllIllIlllIlllIl);
                Mouse.setNativeCursor(llllllllllllllllIllIllIllllIIlIl);
            }
            catch (Throwable llllllllllllllllIllIllIllllIIlII) {
                Log.error("Failed to load and apply cursor.", llllllllllllllllIllIllIllllIIlII);
                throw new SlickException("Failed to set mouse cursor", llllllllllllllllIllIllIllllIIlII);
            }
        }
        
        @Override
        public void setMouseCursor(final Cursor llllllllllllllllIllIllIlllIlIlll, final int llllllllllllllllIllIllIlllIlIllI, final int llllllllllllllllIllIllIlllIlIlIl) throws SlickException {
            try {
                Mouse.setNativeCursor(llllllllllllllllIllIllIlllIlIlll);
            }
            catch (Throwable llllllllllllllllIllIllIlllIllIIl) {
                Log.error("Failed to load and apply cursor.", llllllllllllllllIllIllIlllIllIIl);
                throw new SlickException("Failed to set mouse cursor", llllllllllllllllIllIllIlllIllIIl);
            }
        }
        
        public void stopApplet() {
            this.running = false;
        }
        
        @Override
        public boolean isMouseGrabbed() {
            return Mouse.isGrabbed();
        }
        
        @Override
        public void setDefaultMouseCursor() {
        }
        
        @Override
        public boolean hasFocus() {
            return true;
        }
        
        @Override
        public int getScreenHeight() {
            return 0;
        }
        
        @Override
        public void setMouseCursor(final String llllllllllllllllIllIlllIIIIlIIlI, final int llllllllllllllllIllIlllIIIIlIIIl, final int llllllllllllllllIllIlllIIIIlIIII) throws SlickException {
            try {
                final Cursor llllllllllllllllIllIlllIIIIlIlIl = CursorLoader.get().getCursor(llllllllllllllllIllIlllIIIIlIIlI, llllllllllllllllIllIlllIIIIlIIIl, llllllllllllllllIllIlllIIIIlIIII);
                Mouse.setNativeCursor(llllllllllllllllIllIlllIIIIlIlIl);
            }
            catch (Throwable llllllllllllllllIllIlllIIIIlIlII) {
                Log.error("Failed to load and apply cursor.", llllllllllllllllIllIlllIIIIlIlII);
                throw new SlickException("Failed to set mouse cursor", llllllllllllllllIllIlllIIIIlIlII);
            }
        }
        
        public boolean isRunning() {
            return this.running;
        }
        
        @Override
        public boolean isFullscreen() {
            return Display.isFullscreen();
        }
        
        public boolean supportsAlphaInBackBuffer() {
            return AppletGameContainer.this.alphaSupport;
        }
        
        @Override
        public int getScreenWidth() {
            return 0;
        }
        
        public Container(final Game llllllllllllllllIllIlllIIIlllIlI) {
            super(llllllllllllllllIllIlllIIIlllIlI);
            this.width = AppletGameContainer.this.getWidth();
            this.height = AppletGameContainer.this.getHeight();
        }
        
        private int get2Fold(final int llllllllllllllllIllIlllIIIIIlIII) {
            int llllllllllllllllIllIlllIIIIIIlll;
            for (llllllllllllllllIllIlllIIIIIIlll = 2; llllllllllllllllIllIlllIIIIIIlll < llllllllllllllllIllIlllIIIIIlIII; llllllllllllllllIllIlllIIIIIIlll *= 2) {}
            return llllllllllllllllIllIlllIIIIIIlll;
        }
        
        @Override
        public void setIcon(final String llllllllllllllllIllIlllIIIIlllll) throws SlickException {
        }
        
        public void runloop() throws Exception {
            while (this.running) {
                final int llllllllllllllllIllIllIllIlIllIl = this.getDelta();
                this.updateAndRender(llllllllllllllllIllIllIllIlIllIl);
                this.updateFPS();
                Display.update();
            }
            Display.destroy();
        }
        
        @Override
        public void setFullscreen(final boolean llllllllllllllllIllIllIllIlllIlI) throws SlickException {
            if (llllllllllllllllIllIllIllIlllIlI == this.isFullscreen()) {
                return;
            }
            try {
                if (llllllllllllllllIllIllIllIlllIlI) {
                    final int llllllllllllllllIllIllIlllIIIlII = Display.getDisplayMode().getWidth();
                    final int llllllllllllllllIllIllIlllIIIIll = Display.getDisplayMode().getHeight();
                    final float llllllllllllllllIllIllIlllIIIIlI = this.width / (float)this.height;
                    final float llllllllllllllllIllIllIlllIIIIIl = llllllllllllllllIllIllIlllIIIlII / (float)llllllllllllllllIllIllIlllIIIIll;
                    int llllllllllllllllIllIllIlllIIIIII = 0;
                    int llllllllllllllllIllIllIllIllllll = 0;
                    if (llllllllllllllllIllIllIlllIIIIlI >= llllllllllllllllIllIllIlllIIIIIl) {
                        final int llllllllllllllllIllIllIlllIIIllI = llllllllllllllllIllIllIlllIIIlII;
                        final int llllllllllllllllIllIllIlllIIIlIl = (int)(this.height / (this.width / (float)llllllllllllllllIllIllIlllIIIlII));
                    }
                    else {
                        llllllllllllllllIllIllIlllIIIIII = (int)(this.width / (this.height / (float)llllllllllllllllIllIllIlllIIIIll));
                        llllllllllllllllIllIllIllIllllll = llllllllllllllllIllIllIlllIIIIll;
                    }
                    final int llllllllllllllllIllIllIllIlllllI = (llllllllllllllllIllIllIlllIIIlII - llllllllllllllllIllIllIlllIIIIII) / 2;
                    final int llllllllllllllllIllIllIllIllllIl = (llllllllllllllllIllIllIlllIIIIll - llllllllllllllllIllIllIllIllllll) / 2;
                    GL11.glViewport(llllllllllllllllIllIllIllIlllllI, llllllllllllllllIllIllIllIllllIl, llllllllllllllllIllIllIlllIIIIII, llllllllllllllllIllIllIllIllllll);
                    this.enterOrtho();
                    this.getInput().setOffset(-llllllllllllllllIllIllIllIlllllI * (float)this.width / llllllllllllllllIllIllIlllIIIIII, -llllllllllllllllIllIllIllIllllIl * (float)this.height / llllllllllllllllIllIllIllIllllll);
                    this.getInput().setScale(this.width / (float)llllllllllllllllIllIllIlllIIIIII, this.height / (float)llllllllllllllllIllIllIllIllllll);
                    this.width = llllllllllllllllIllIllIlllIIIlII;
                    this.height = llllllllllllllllIllIllIlllIIIIll;
                    Display.setFullscreen(true);
                }
                else {
                    this.getInput().setOffset(0.0f, 0.0f);
                    this.getInput().setScale(1.0f, 1.0f);
                    this.width = AppletGameContainer.this.getWidth();
                    this.height = AppletGameContainer.this.getHeight();
                    GL11.glViewport(0, 0, this.width, this.height);
                    this.enterOrtho();
                    Display.setFullscreen(false);
                }
            }
            catch (LWJGLException llllllllllllllllIllIllIllIllllII) {
                Log.error((Throwable)llllllllllllllllIllIllIllIllllII);
            }
        }
        
        public Applet getApplet() {
            return AppletGameContainer.this;
        }
        
        public void initApplet() throws SlickException {
            this.initSystem();
            this.enterOrtho();
            try {
                this.getInput().initControllers();
            }
            catch (SlickException llllllllllllllllIllIlllIIIllIlII) {
                Log.info("Controllers not available");
            }
            catch (Throwable llllllllllllllllIllIlllIIIllIIll) {
                Log.info("Controllers not available");
            }
            this.game.init(this);
            this.getDelta();
        }
        
        @Override
        public void setIcons(final String[] llllllllllllllllIllIllIllllIlIlI) throws SlickException {
        }
        
        @Override
        public void setMouseGrabbed(final boolean llllllllllllllllIllIlllIIIIllIll) {
            Mouse.setGrabbed(llllllllllllllllIllIlllIIIIllIll);
        }
    }
    
    public class ContainerPanel
    {
        private /* synthetic */ Container container;
        
        public void start() throws Exception {
            Display.setParent(AppletGameContainer.this.displayParent);
            Display.setVSyncEnabled(true);
            try {
                this.createDisplay();
            }
            catch (LWJGLException llIIIIIllIIII) {
                llIIIIIllIIII.printStackTrace();
                Thread.sleep(1000L);
                this.createDisplay();
            }
            this.initGL();
            AppletGameContainer.this.displayParent.requestFocus();
            this.container.runloop();
        }
        
        protected void initGL() {
            try {
                InternalTextureLoader.get().clear();
                SoundStore.get().clear();
                this.container.initApplet();
            }
            catch (Exception llIIIIIlIlIlI) {
                Log.error(llIIIIIlIlIlI);
                this.container.stopApplet();
            }
        }
        
        public ContainerPanel(final Container llIIIIIllllII) {
            this.container = llIIIIIllllII;
        }
        
        private void createDisplay() throws Exception {
            try {
                Display.create(new PixelFormat(8, 8, GameContainer.stencil ? 8 : 0));
                AppletGameContainer.this.alphaSupport = true;
            }
            catch (Exception llIIIIIllIllI) {
                AppletGameContainer.this.alphaSupport = false;
                Display.destroy();
                Display.create();
            }
        }
    }
    
    public class ConsolePanel extends Panel
    {
        /* synthetic */ TextArea textArea;
        
        public ConsolePanel(final Exception lllllllllllllllllIlIlIllIllllllI) {
            this.textArea = new TextArea();
            this.setLayout(new BorderLayout());
            this.setBackground(Color.black);
            this.setForeground(Color.white);
            final Font lllllllllllllllllIlIlIlllIIIIllI = new Font("Arial", 1, 14);
            final Label lllllllllllllllllIlIlIlllIIIIlIl = new Label("SLICK CONSOLE", 1);
            lllllllllllllllllIlIlIlllIIIIlIl.setFont(lllllllllllllllllIlIlIlllIIIIllI);
            this.add(lllllllllllllllllIlIlIlllIIIIlIl, "First");
            final StringWriter lllllllllllllllllIlIlIlllIIIIlII = new StringWriter();
            lllllllllllllllllIlIlIllIllllllI.printStackTrace(new PrintWriter(lllllllllllllllllIlIlIlllIIIIlII));
            this.textArea.setText(lllllllllllllllllIlIlIlllIIIIlII.toString());
            this.textArea.setEditable(false);
            this.add(this.textArea, "Center");
            this.add(new Panel(), "Before");
            this.add(new Panel(), "After");
            final Panel lllllllllllllllllIlIlIlllIIIIIll = new Panel();
            lllllllllllllllllIlIlIlllIIIIIll.setLayout(new GridLayout(0, 1));
            final Label lllllllllllllllllIlIlIlllIIIIIlI = new Label("An error occured while running the applet.", 1);
            final Label lllllllllllllllllIlIlIlllIIIIIIl = new Label("Plese contact support to resolve this issue.", 1);
            lllllllllllllllllIlIlIlllIIIIIlI.setFont(lllllllllllllllllIlIlIlllIIIIllI);
            lllllllllllllllllIlIlIlllIIIIIIl.setFont(lllllllllllllllllIlIlIlllIIIIllI);
            lllllllllllllllllIlIlIlllIIIIIll.add(lllllllllllllllllIlIlIlllIIIIIlI);
            lllllllllllllllllIlIlIlllIIIIIll.add(lllllllllllllllllIlIlIlllIIIIIIl);
            this.add(lllllllllllllllllIlIlIlllIIIIIll, "Last");
        }
    }
}
