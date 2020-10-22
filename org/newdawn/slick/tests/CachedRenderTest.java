package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class CachedRenderTest extends BasicGame
{
    private /* synthetic */ CachedRender cached;
    private /* synthetic */ boolean drawCached;
    private /* synthetic */ Runnable operations;
    
    public static void main(final String[] lllllllllllllllllIIIlllIIIIIIIIl) {
        try {
            final AppGameContainer lllllllllllllllllIIIlllIIIIIIIll = new AppGameContainer(new CachedRenderTest());
            lllllllllllllllllIIIlllIIIIIIIll.setDisplayMode(800, 600, false);
            lllllllllllllllllIIIlllIIIIIIIll.start();
        }
        catch (SlickException lllllllllllllllllIIIlllIIIIIIIlI) {
            lllllllllllllllllIIIlllIIIIIIIlI.printStackTrace();
        }
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIIIlllIIIIIlIII, final Graphics lllllllllllllllllIIIlllIIIIIIlll) throws SlickException {
        lllllllllllllllllIIIlllIIIIIIlll.setColor(Color.white);
        lllllllllllllllllIIIlllIIIIIIlll.drawString("Press space to toggle caching", 10.0f, 130.0f);
        if (this.drawCached) {
            lllllllllllllllllIIIlllIIIIIIlll.drawString("Drawing from cache", 10.0f, 100.0f);
            this.cached.render();
        }
        else {
            lllllllllllllllllIIIlllIIIIIIlll.drawString("Drawing direct", 10.0f, 100.0f);
            this.operations.run();
        }
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIIIlllIIIIlIIll) throws SlickException {
        this.operations = new Runnable() {
            @Override
            public void run() {
                for (int lllllllllllllllllIIIIIlIIlIlllII = 0; lllllllllllllllllIIIIIlIIlIlllII < 100; ++lllllllllllllllllIIIIIlIIlIlllII) {
                    final int lllllllllllllllllIIIIIlIIlIlllIl = lllllllllllllllllIIIIIlIIlIlllII + 100;
                    lllllllllllllllllIIIlllIIIIlIIll.getGraphics().setColor(new Color(lllllllllllllllllIIIIIlIIlIlllIl, lllllllllllllllllIIIIIlIIlIlllIl, lllllllllllllllllIIIIIlIIlIlllIl, lllllllllllllllllIIIIIlIIlIlllIl));
                    lllllllllllllllllIIIlllIIIIlIIll.getGraphics().drawOval((float)(lllllllllllllllllIIIIIlIIlIlllII * 5 + 50), (float)(lllllllllllllllllIIIIIlIIlIlllII * 3 + 50), 100.0f, 100.0f);
                }
            }
        };
        this.cached = new CachedRender(this.operations);
    }
    
    public CachedRenderTest() {
        super("Cached Render Test");
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIIIlllIIIIIllll, final int lllllllllllllllllIIIlllIIIIIlllI) throws SlickException {
        if (lllllllllllllllllIIIlllIIIIIllll.getInput().isKeyPressed(57)) {
            this.drawCached = !this.drawCached;
        }
    }
}
