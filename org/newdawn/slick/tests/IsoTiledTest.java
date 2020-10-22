package org.newdawn.slick.tests;

import org.newdawn.slick.tiled.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.*;

public class IsoTiledTest extends BasicGame
{
    private /* synthetic */ TiledMap tilemap;
    
    public IsoTiledTest() {
        super("Isometric Tiled Map Test");
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIIIIIIlIllIllIl) throws SlickException {
        this.tilemap = new TiledMap("testdata/isoexample.tmx", "testdata/");
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIIIIIIlIlIlllIl, final Graphics lllllllllllllllllIIIIIIlIlIlllII) throws SlickException {
        this.tilemap.render(350, 150);
    }
    
    public static void main(final String[] lllllllllllllllllIIIIIIlIlIllIIl) {
        Bootstrap.runAsApplication(new IsoTiledTest(), 800, 600, false);
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIIIIIIlIllIIllI, final int lllllllllllllllllIIIIIIlIllIIlIl) throws SlickException {
    }
}
