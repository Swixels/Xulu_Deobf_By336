package org.newdawn.slick.tests;

import org.newdawn.slick.util.*;
import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.*;

public class TransitionTest extends StateBasedGame
{
    private /* synthetic */ int index;
    private /* synthetic */ Class[][] transitions;
    
    public Transition[] getNextTransitionPair() {
        final Transition[] lllllllllllllllllIIIIlIIIlllIIIl = new Transition[2];
        try {
            if (this.transitions[this.index][0] != null) {
                lllllllllllllllllIIIIlIIIlllIIIl[0] = this.transitions[this.index][0].newInstance();
            }
            if (this.transitions[this.index][1] != null) {
                lllllllllllllllllIIIIlIIIlllIIIl[1] = this.transitions[this.index][1].newInstance();
            }
        }
        catch (Throwable lllllllllllllllllIIIIlIIIlllIIll) {
            Log.error(lllllllllllllllllIIIIlIIIlllIIll);
        }
        ++this.index;
        if (this.index >= this.transitions.length) {
            this.index = 0;
        }
        return lllllllllllllllllIIIIlIIIlllIIIl;
    }
    
    @Override
    public void initStatesList(final GameContainer lllllllllllllllllIIIIlIIIllllIII) throws SlickException {
        this.addState(new ImageState(0, "testdata/wallpaper/paper1.png", 1));
        this.addState(new ImageState(1, "testdata/wallpaper/paper2.png", 2));
        this.addState(new ImageState(2, "testdata/bigimage.tga", 0));
    }
    
    public static void main(final String[] lllllllllllllllllIIIIlIIIllIlIlI) {
        try {
            final AppGameContainer lllllllllllllllllIIIIlIIIllIllII = new AppGameContainer(new TransitionTest());
            lllllllllllllllllIIIIlIIIllIllII.setDisplayMode(800, 600, false);
            lllllllllllllllllIIIIlIIIllIllII.start();
        }
        catch (SlickException lllllllllllllllllIIIIlIIIllIlIll) {
            lllllllllllllllllIIIIlIIIllIlIll.printStackTrace();
        }
    }
    
    public TransitionTest() {
        super("Transition Test - Hit Space To Transition");
        this.transitions = new Class[][] { { null, VerticalSplitTransition.class }, { FadeOutTransition.class, FadeInTransition.class }, { null, RotateTransition.class }, { null, HorizontalSplitTransition.class }, { null, BlobbyTransition.class }, { null, SelectTransition.class } };
    }
    
    private class ImageState extends BasicGameState
    {
        private /* synthetic */ int next;
        private /* synthetic */ String ref;
        private /* synthetic */ int id;
        private /* synthetic */ Image image;
        
        public ImageState(final int lllllllllllllllllIIlIIIIIIIlIlll, final String lllllllllllllllllIIlIIIIIIIlIIIl, final int lllllllllllllllllIIlIIIIIIIlIlIl) {
            this.ref = lllllllllllllllllIIlIIIIIIIlIIIl;
            this.id = lllllllllllllllllIIlIIIIIIIlIlll;
            this.next = lllllllllllllllllIIlIIIIIIIlIlIl;
        }
        
        @Override
        public int getID() {
            return this.id;
        }
        
        @Override
        public void update(final GameContainer lllllllllllllllllIIIllllllllIlIl, final StateBasedGame lllllllllllllllllIIIllllllllIlII, final int lllllllllllllllllIIIllllllllIlll) throws SlickException {
            if (lllllllllllllllllIIIllllllllIlIl.getInput().isKeyPressed(57)) {
                final Transition[] lllllllllllllllllIIIlllllllllIll = TransitionTest.this.getNextTransitionPair();
                lllllllllllllllllIIIllllllllIlII.enterState(this.next, lllllllllllllllllIIIlllllllllIll[0], lllllllllllllllllIIIlllllllllIll[1]);
            }
        }
        
        @Override
        public void init(final GameContainer lllllllllllllllllIIlIIIIIIIIlIlI, final StateBasedGame lllllllllllllllllIIlIIIIIIIIlIIl) throws SlickException {
            this.image = new Image(this.ref);
        }
        
        @Override
        public void render(final GameContainer lllllllllllllllllIIlIIIIIIIIIlII, final StateBasedGame lllllllllllllllllIIlIIIIIIIIIIll, final Graphics lllllllllllllllllIIlIIIIIIIIIIII) throws SlickException {
            this.image.draw(0.0f, 0.0f, 800.0f, 600.0f);
            lllllllllllllllllIIlIIIIIIIIIIII.setColor(Color.red);
            lllllllllllllllllIIlIIIIIIIIIIII.fillRect(-50.0f, 200.0f, 50.0f, 50.0f);
        }
    }
}
