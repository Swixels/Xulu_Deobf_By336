package org.newdawn.slick.tests;

import java.util.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.*;

public class TestBox extends BasicGame
{
    private /* synthetic */ int index;
    private /* synthetic */ ArrayList games;
    private /* synthetic */ AppGameContainer container;
    private /* synthetic */ BasicGame currentGame;
    
    private void startGame() {
        try {
            this.currentGame = this.games.get(this.index).newInstance();
            this.container.getGraphics().setBackground(Color.black);
            this.currentGame.init(this.container);
            this.currentGame.render(this.container, this.container.getGraphics());
        }
        catch (Exception llIlIIllIIlIl) {
            Log.error(llIlIIllIIlIl);
        }
        this.container.setTitle(this.currentGame.getTitle());
    }
    
    @Override
    public void render(final GameContainer llIlIIlIIlIll, final Graphics llIlIIlIIlIlI) throws SlickException {
        SlickCallable.enterSafeBlock();
        this.currentGame.render(llIlIIlIIlIll, llIlIIlIIlIlI);
        SlickCallable.leaveSafeBlock();
    }
    
    @Override
    public void controllerUpPressed(final int llIlIIIIIlllI) {
        this.currentGame.controllerUpPressed(llIlIIIIIlllI);
    }
    
    @Override
    public void init(final GameContainer llIlIIlIlllII) throws SlickException {
        if (this.games.size() == 0) {
            this.currentGame = new BasicGame("NULL") {
                @Override
                public void update(final GameContainer lIIllIIlIlIlIIl, final int lIIllIIlIlIlIII) throws SlickException {
                }
                
                @Override
                public void render(final GameContainer lIIllIIlIlIIllI, final Graphics lIIllIIlIlIIlIl) throws SlickException {
                }
                
                @Override
                public void init(final GameContainer lIIllIIlIlIlIll) throws SlickException {
                }
            };
            this.currentGame.init(llIlIIlIlllII);
            this.index = -1;
        }
        else {
            this.index = 0;
            this.container = (AppGameContainer)llIlIIlIlllII;
            this.startGame();
        }
    }
    
    @Override
    public void update(final GameContainer llIlIIlIlIlII, final int llIlIIlIlIIll) throws SlickException {
        this.currentGame.update(llIlIIlIlIlII, llIlIIlIlIIll);
    }
    
    @Override
    public void controllerRightPressed(final int llIlIIIIllIlI) {
        this.currentGame.controllerRightPressed(llIlIIIIllIlI);
    }
    
    @Override
    public void keyPressed(final int llIlIIIIIIIll, final char llIlIIIIIIIlI) {
        this.currentGame.keyPressed(llIlIIIIIIIll, llIlIIIIIIIlI);
        if (llIlIIIIIIIll == 28) {
            this.nextGame();
        }
    }
    
    public static void main(final String[] llIIlllIIIIll) {
        try {
            final TestBox llIIlllIIIllI = new TestBox();
            llIIlllIIIllI.addGame(AnimationTest.class);
            llIIlllIIIllI.addGame(AntiAliasTest.class);
            llIIlllIIIllI.addGame(BigImageTest.class);
            llIIlllIIIllI.addGame(ClipTest.class);
            llIIlllIIIllI.addGame(DuplicateEmitterTest.class);
            llIIlllIIIllI.addGame(FlashTest.class);
            llIIlllIIIllI.addGame(FontPerformanceTest.class);
            llIIlllIIIllI.addGame(FontTest.class);
            llIIlllIIIllI.addGame(GeomTest.class);
            llIIlllIIIllI.addGame(GradientTest.class);
            llIIlllIIIllI.addGame(GraphicsTest.class);
            llIIlllIIIllI.addGame(ImageBufferTest.class);
            llIIlllIIIllI.addGame(ImageReadTest.class);
            llIIlllIIIllI.addGame(ImageTest.class);
            llIIlllIIIllI.addGame(KeyRepeatTest.class);
            llIIlllIIIllI.addGame(MusicListenerTest.class);
            llIIlllIIIllI.addGame(PackedSheetTest.class);
            llIIlllIIIllI.addGame(PedigreeTest.class);
            llIIlllIIIllI.addGame(PureFontTest.class);
            llIIlllIIIllI.addGame(ShapeTest.class);
            llIIlllIIIllI.addGame(SoundTest.class);
            llIIlllIIIllI.addGame(SpriteSheetFontTest.class);
            llIIlllIIIllI.addGame(TransparentColorTest.class);
            final AppGameContainer llIIlllIIIlIl = new AppGameContainer(llIIlllIIIllI);
            llIIlllIIIlIl.setDisplayMode(800, 600, false);
            llIIlllIIIlIl.start();
        }
        catch (SlickException llIIlllIIIlII) {
            llIIlllIIIlII.printStackTrace();
        }
    }
    
    @Override
    public void controllerDownPressed(final int llIlIIIllIIlI) {
        this.currentGame.controllerDownPressed(llIlIIIllIIlI);
    }
    
    @Override
    public void mouseReleased(final int llIIlllIlIIIl, final int llIIlllIlIIII, final int llIIlllIIllll) {
        this.currentGame.mouseReleased(llIIlllIlIIIl, llIIlllIlIIII, llIIlllIIllll);
    }
    
    @Override
    public void controllerDownReleased(final int llIlIIIlIllII) {
        this.currentGame.controllerDownReleased(llIlIIIlIllII);
    }
    
    @Override
    public void mouseWheelMoved(final int llIIlllIIlIIl) {
        this.currentGame.mouseWheelMoved(llIIlllIIlIIl);
    }
    
    @Override
    public void controllerLeftReleased(final int llIlIIIlIIIII) {
        this.currentGame.controllerLeftReleased(llIlIIIlIIIII);
    }
    
    @Override
    public void controllerButtonReleased(final int llIlIIIlllIIl, final int llIlIIIlllIII) {
        this.currentGame.controllerButtonReleased(llIlIIIlllIIl, llIlIIIlllIII);
    }
    
    @Override
    public void controllerLeftPressed(final int llIlIIIlIIllI) {
        this.currentGame.controllerLeftPressed(llIlIIIlIIllI);
    }
    
    @Override
    public void mouseMoved(final int llIIllllIlIlI, final int llIIllllIlllI, final int llIIllllIllIl, final int llIIllllIIlll) {
        this.currentGame.mouseMoved(llIIllllIlIlI, llIIllllIlllI, llIIllllIllIl, llIIllllIIlll);
    }
    
    private void nextGame() {
        if (this.index == -1) {
            return;
        }
        ++this.index;
        if (this.index >= this.games.size()) {
            this.index = 0;
        }
        this.startGame();
    }
    
    @Override
    public void keyReleased(final int llIIllllllIlI, final char llIIlllllIllI) {
        this.currentGame.keyReleased(llIIllllllIlI, llIIlllllIllI);
    }
    
    @Override
    public void mousePressed(final int llIIlllIlllIl, final int llIIlllIlllII, final int llIIlllIlllll) {
        this.currentGame.mousePressed(llIIlllIlllIl, llIIlllIlllII, llIIlllIlllll);
    }
    
    @Override
    public void controllerUpReleased(final int llIlIIIIIlIlI) {
        this.currentGame.controllerUpReleased(llIlIIIIIlIlI);
    }
    
    @Override
    public void controllerRightReleased(final int llIlIIIIlIllI) {
        this.currentGame.controllerRightReleased(llIlIIIIlIllI);
    }
    
    public void addGame(final Class llIlIIllIlIll) {
        this.games.add(llIlIIllIlIll);
    }
    
    @Override
    public void controllerButtonPressed(final int llIlIIlIIIIlI, final int llIlIIlIIIIIl) {
        this.currentGame.controllerButtonPressed(llIlIIlIIIIlI, llIlIIlIIIIIl);
    }
    
    public TestBox() {
        super("Test Box");
        this.games = new ArrayList();
    }
}
