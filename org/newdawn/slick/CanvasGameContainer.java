package org.newdawn.slick;

import java.awt.*;
import javax.swing.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.newdawn.slick.util.*;

public class CanvasGameContainer extends Canvas
{
    protected /* synthetic */ Container container;
    protected /* synthetic */ Game game;
    
    public GameContainer getContainer() {
        return this.container;
    }
    
    public CanvasGameContainer(final Game llllllllllllllllIllIlllIIlIllIlI) throws SlickException {
        this(llllllllllllllllIllIlllIIlIllIlI, false);
    }
    
    public void dispose() {
    }
    
    private void scheduleUpdate() {
        if (!this.isVisible()) {
            return;
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    CanvasGameContainer.this.container.gameLoop();
                }
                catch (SlickException llllllllllllllllIlIllIllIIllIlll) {
                    llllllllllllllllIlIllIllIIllIlll.printStackTrace();
                }
                CanvasGameContainer.this.container.checkDimensions();
                CanvasGameContainer.this.scheduleUpdate();
            }
        });
    }
    
    public void start() throws SlickException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Input.disableControllers();
                    try {
                        Display.setParent((Canvas)CanvasGameContainer.this);
                    }
                    catch (LWJGLException lllllllllllllllllIIlIIIllIllIIlI) {
                        throw new SlickException("Failed to setParent of canvas", (Throwable)lllllllllllllllllIIlIIIllIllIIlI);
                    }
                    CanvasGameContainer.this.container.setup();
                    CanvasGameContainer.this.scheduleUpdate();
                }
                catch (SlickException lllllllllllllllllIIlIIIllIllIIIl) {
                    lllllllllllllllllIIlIIIllIllIIIl.printStackTrace();
                    System.exit(0);
                }
            }
        });
    }
    
    public CanvasGameContainer(final Game llllllllllllllllIllIlllIIlIlIIll, final boolean llllllllllllllllIllIlllIIlIIllll) throws SlickException {
        this.game = llllllllllllllllIllIlllIIlIlIIll;
        this.setIgnoreRepaint(true);
        this.requestFocus();
        this.setSize(500, 500);
        this.container = new Container(llllllllllllllllIllIlllIIlIlIIll, llllllllllllllllIllIlllIIlIIllll);
        this.container.setForceExit(false);
    }
    
    private class Container extends AppGameContainer
    {
        @Override
        public int getWidth() {
            return CanvasGameContainer.this.getWidth();
        }
        
        @Override
        protected void updateFPS() {
            super.updateFPS();
        }
        
        @Override
        protected boolean running() {
            return super.running() && CanvasGameContainer.this.isDisplayable();
        }
        
        @Override
        public int getHeight() {
            return CanvasGameContainer.this.getHeight();
        }
        
        public Container(final Game llllllllllllllllIllIIlIIIIllIlll, final boolean llllllllllllllllIllIIlIIIIllIllI) throws SlickException {
            super(llllllllllllllllIllIIlIIIIllIlll, CanvasGameContainer.this.getWidth(), CanvasGameContainer.this.getHeight(), false);
            this.width = CanvasGameContainer.this.getWidth();
            this.height = CanvasGameContainer.this.getHeight();
            if (llllllllllllllllIllIIlIIIIllIllI) {
                enableSharedContext();
            }
        }
        
        public void checkDimensions() {
            if (this.width == CanvasGameContainer.this.getWidth()) {
                if (this.height == CanvasGameContainer.this.getHeight()) {
                    return;
                }
            }
            try {
                this.setDisplayMode(CanvasGameContainer.this.getWidth(), CanvasGameContainer.this.getHeight(), false);
            }
            catch (SlickException llllllllllllllllIllIIlIIIIlIIIll) {
                Log.error(llllllllllllllllIllIIlIIIIlIIIll);
            }
        }
    }
}
