package org.newdawn.slick.tests;

import org.newdawn.slick.tiled.*;
import org.newdawn.slick.*;

public class TileMapTest extends BasicGame
{
    private /* synthetic */ String monsterDifficulty;
    private /* synthetic */ String nonExistingLayerProperty;
    private /* synthetic */ TiledMap map;
    private /* synthetic */ int originalTileID;
    private /* synthetic */ String nonExistingMapProperty;
    private /* synthetic */ int updateCounter;
    private static /* synthetic */ int UPDATE_TIME;
    private /* synthetic */ String mapName;
    
    @Override
    public void keyPressed(final int llllllllllllllllIlllllIlIIlIIIll, final char llllllllllllllllIlllllIlIIlIIIlI) {
        if (llllllllllllllllIlllllIlIIlIIIll == 1) {
            System.exit(0);
        }
    }
    
    public TileMapTest() {
        super("Tile Map Test");
        this.updateCounter = 0;
        this.originalTileID = 0;
    }
    
    static {
        TileMapTest.UPDATE_TIME = 1000;
    }
    
    @Override
    public void update(final GameContainer llllllllllllllllIlllllIlIIlIlIlI, final int llllllllllllllllIlllllIlIIlIIlll) {
        this.updateCounter += llllllllllllllllIlllllIlIIlIIlll;
        if (this.updateCounter > TileMapTest.UPDATE_TIME) {
            this.updateCounter -= TileMapTest.UPDATE_TIME;
            final int llllllllllllllllIlllllIlIIlIllII = this.map.getTileId(10, 10, 0);
            if (llllllllllllllllIlllllIlIIlIllII != this.originalTileID) {
                this.map.setTileId(10, 10, 0, this.originalTileID);
            }
            else {
                this.map.setTileId(10, 10, 0, 1);
            }
        }
    }
    
    @Override
    public void render(final GameContainer llllllllllllllllIlllllIlIIllIIll, final Graphics llllllllllllllllIlllllIlIIllIIlI) {
        this.map.render(10, 10, 4, 4, 15, 15);
        llllllllllllllllIlllllIlIIllIIlI.scale(0.35f, 0.35f);
        this.map.render(1400, 0);
        llllllllllllllllIlllllIlIIllIIlI.resetTransform();
        llllllllllllllllIlllllIlIIllIIlI.drawString(String.valueOf(new StringBuilder().append("map name: ").append(this.mapName)), 10.0f, 500.0f);
        llllllllllllllllIlllllIlIIllIIlI.drawString(String.valueOf(new StringBuilder().append("monster difficulty: ").append(this.monsterDifficulty)), 10.0f, 550.0f);
        llllllllllllllllIlllllIlIIllIIlI.drawString(String.valueOf(new StringBuilder().append("non existing map property: ").append(this.nonExistingMapProperty)), 10.0f, 525.0f);
        llllllllllllllllIlllllIlIIllIIlI.drawString(String.valueOf(new StringBuilder().append("non existing layer property: ").append(this.nonExistingLayerProperty)), 10.0f, 575.0f);
    }
    
    @Override
    public void init(final GameContainer llllllllllllllllIlllllIlIIlllIII) throws SlickException {
        this.map = new TiledMap("testdata/testmap.tmx", "testdata");
        this.mapName = this.map.getMapProperty("name", "Unknown map name");
        this.monsterDifficulty = this.map.getLayerProperty(0, "monsters", "easy peasy");
        this.nonExistingMapProperty = this.map.getMapProperty("zaphod", "Undefined map property");
        this.nonExistingLayerProperty = this.map.getLayerProperty(1, "beeblebrox", "Undefined layer property");
        this.originalTileID = this.map.getTileId(10, 10, 0);
    }
    
    public static void main(final String[] llllllllllllllllIlllllIlIIIlllIl) {
        try {
            final AppGameContainer llllllllllllllllIlllllIlIIIlllll = new AppGameContainer(new TileMapTest());
            llllllllllllllllIlllllIlIIIlllll.setDisplayMode(800, 600, false);
            llllllllllllllllIlllllIlIIIlllll.start();
        }
        catch (SlickException llllllllllllllllIlllllIlIIIllllI) {
            llllllllllllllllIlllllIlIIIllllI.printStackTrace();
        }
    }
}
