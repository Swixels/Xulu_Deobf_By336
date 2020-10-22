package org.newdawn.slick.tiled;

import java.util.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class TileSet
{
    protected /* synthetic */ int tileMargin;
    public /* synthetic */ SpriteSheet tiles;
    private final /* synthetic */ TiledMap map;
    private /* synthetic */ HashMap props;
    public /* synthetic */ int tilesAcross;
    public /* synthetic */ int tileHeight;
    public /* synthetic */ String name;
    public /* synthetic */ int lastGID;
    public /* synthetic */ int firstGID;
    public /* synthetic */ int tileWidth;
    public /* synthetic */ int tilesDown;
    protected /* synthetic */ int tileSpacing;
    
    public int getTileSpacing() {
        return this.tileSpacing;
    }
    
    public void setLimit(final int lllllllllllllllllIllllIIlIllllll) {
        this.lastGID = lllllllllllllllllIllllIIlIllllll;
    }
    
    public int getTileX(final int lllllllllllllllllIllllIIllIIlIll) {
        return lllllllllllllllllIllllIIllIIlIll % this.tilesAcross;
    }
    
    public Properties getProperties(final int lllllllllllllllllIllllIIllIlIIll) {
        return this.props.get(new Integer(lllllllllllllllllIllllIIllIlIIll));
    }
    
    public int getTileY(final int lllllllllllllllllIllllIIllIIIlIl) {
        return lllllllllllllllllIllllIIllIIIlIl / this.tilesAcross;
    }
    
    public void setTileSetImage(final Image lllllllllllllllllIllllIIllIllIIl) {
        this.tiles = new SpriteSheet(lllllllllllllllllIllllIIllIllIIl, this.tileWidth, this.tileHeight, this.tileSpacing, this.tileMargin);
        this.tilesAcross = this.tiles.getHorizontalCount();
        this.tilesDown = this.tiles.getVerticalCount();
        if (this.tilesAcross <= 0) {
            this.tilesAcross = 1;
        }
        if (this.tilesDown <= 0) {
            this.tilesDown = 1;
        }
        this.lastGID = this.tilesAcross * this.tilesDown + this.firstGID - 1;
    }
    
    public int getTileWidth() {
        return this.tileWidth;
    }
    
    public int getTileHeight() {
        return this.tileHeight;
    }
    
    public int getTileMargin() {
        return this.tileMargin;
    }
    
    public TileSet(final TiledMap lllllllllllllllllIllllIlIIIIllll, Element lllllllllllllllllIllllIIllllllll, final boolean lllllllllllllllllIllllIlIIIIllIl) throws SlickException {
        this.lastGID = Integer.MAX_VALUE;
        this.props = new HashMap();
        this.tileSpacing = 0;
        this.tileMargin = 0;
        this.map = lllllllllllllllllIllllIlIIIIllll;
        this.name = lllllllllllllllllIllllIIllllllll.getAttribute("name");
        this.firstGID = Integer.parseInt(lllllllllllllllllIllllIIllllllll.getAttribute("firstgid"));
        final String lllllllllllllllllIllllIlIIIIllII = lllllllllllllllllIllllIIllllllll.getAttribute("source");
        if (lllllllllllllllllIllllIlIIIIllII != null && !lllllllllllllllllIllllIlIIIIllII.equals("")) {
            try {
                final InputStream lllllllllllllllllIllllIlIIlIIIIl = ResourceLoader.getResourceAsStream(String.valueOf(new StringBuilder().append(lllllllllllllllllIllllIlIIIIllll.getTilesLocation()).append("/").append(lllllllllllllllllIllllIlIIIIllII)));
                final DocumentBuilder lllllllllllllllllIllllIlIIlIIIII = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                final Document lllllllllllllllllIllllIlIIIlllll = lllllllllllllllllIllllIlIIlIIIII.parse(lllllllllllllllllIllllIlIIlIIIIl);
                final Element lllllllllllllllllIllllIlIIIllllI = lllllllllllllllllIllllIIllllllll = lllllllllllllllllIllllIlIIIlllll.getDocumentElement();
            }
            catch (Exception lllllllllllllllllIllllIlIIIlllIl) {
                Log.error(lllllllllllllllllIllllIlIIIlllIl);
                throw new SlickException(String.valueOf(new StringBuilder().append("Unable to load or parse sourced tileset: ").append(this.map.tilesLocation).append("/").append(lllllllllllllllllIllllIlIIIIllII)));
            }
        }
        final String lllllllllllllllllIllllIlIIIIlIll = lllllllllllllllllIllllIIllllllll.getAttribute("tilewidth");
        final String lllllllllllllllllIllllIlIIIIlIlI = lllllllllllllllllIllllIIllllllll.getAttribute("tileheight");
        if (lllllllllllllllllIllllIlIIIIlIll.length() == 0 || lllllllllllllllllIllllIlIIIIlIlI.length() == 0) {
            throw new SlickException("TiledMap requires that the map be created with tilesets that use a single image.  Check the WiKi for more complete information.");
        }
        this.tileWidth = Integer.parseInt(lllllllllllllllllIllllIlIIIIlIll);
        this.tileHeight = Integer.parseInt(lllllllllllllllllIllllIlIIIIlIlI);
        final String lllllllllllllllllIllllIlIIIIlIIl = lllllllllllllllllIllllIIllllllll.getAttribute("spacing");
        if (lllllllllllllllllIllllIlIIIIlIIl != null && !lllllllllllllllllIllllIlIIIIlIIl.equals("")) {
            this.tileSpacing = Integer.parseInt(lllllllllllllllllIllllIlIIIIlIIl);
        }
        final String lllllllllllllllllIllllIlIIIIlIII = lllllllllllllllllIllllIIllllllll.getAttribute("margin");
        if (lllllllllllllllllIllllIlIIIIlIII != null && !lllllllllllllllllIllllIlIIIIlIII.equals("")) {
            this.tileMargin = Integer.parseInt(lllllllllllllllllIllllIlIIIIlIII);
        }
        final NodeList lllllllllllllllllIllllIlIIIIIlll = lllllllllllllllllIllllIIllllllll.getElementsByTagName("image");
        final Element lllllllllllllllllIllllIlIIIIIllI = (Element)lllllllllllllllllIllllIlIIIIIlll.item(0);
        final String lllllllllllllllllIllllIlIIIIIlIl = lllllllllllllllllIllllIlIIIIIllI.getAttribute("source");
        Color lllllllllllllllllIllllIlIIIIIlII = null;
        final String lllllllllllllllllIllllIlIIIIIIll = lllllllllllllllllIllllIlIIIIIllI.getAttribute("trans");
        if (lllllllllllllllllIllllIlIIIIIIll != null && lllllllllllllllllIllllIlIIIIIIll.length() > 0) {
            final int lllllllllllllllllIllllIlIIIlllII = Integer.parseInt(lllllllllllllllllIllllIlIIIIIIll, 16);
            lllllllllllllllllIllllIlIIIIIlII = new Color(lllllllllllllllllIllllIlIIIlllII);
        }
        if (lllllllllllllllllIllllIlIIIIllIl) {
            final Image lllllllllllllllllIllllIlIIIllIll = new Image(String.valueOf(new StringBuilder().append(lllllllllllllllllIllllIlIIIIllll.getTilesLocation()).append("/").append(lllllllllllllllllIllllIlIIIIIlIl)), false, 2, lllllllllllllllllIllllIlIIIIIlII);
            this.setTileSetImage(lllllllllllllllllIllllIlIIIllIll);
        }
        final NodeList lllllllllllllllllIllllIlIIIIIIlI = lllllllllllllllllIllllIIllllllll.getElementsByTagName("tile");
        for (int lllllllllllllllllIllllIlIIIlIIIl = 0; lllllllllllllllllIllllIlIIIlIIIl < lllllllllllllllllIllllIlIIIIIIlI.getLength(); ++lllllllllllllllllIllllIlIIIlIIIl) {
            final Element lllllllllllllllllIllllIlIIIlIllI = (Element)lllllllllllllllllIllllIlIIIIIIlI.item(lllllllllllllllllIllllIlIIIlIIIl);
            int lllllllllllllllllIllllIlIIIlIlIl = Integer.parseInt(lllllllllllllllllIllllIlIIIlIllI.getAttribute("id"));
            lllllllllllllllllIllllIlIIIlIlIl += this.firstGID;
            final Properties lllllllllllllllllIllllIlIIIlIlII = new Properties();
            final Element lllllllllllllllllIllllIlIIIlIIll = (Element)lllllllllllllllllIllllIlIIIlIllI.getElementsByTagName("properties").item(0);
            final NodeList lllllllllllllllllIllllIlIIIlIIlI = lllllllllllllllllIllllIlIIIlIIll.getElementsByTagName("property");
            for (int lllllllllllllllllIllllIlIIIlIlll = 0; lllllllllllllllllIllllIlIIIlIlll < lllllllllllllllllIllllIlIIIlIIlI.getLength(); ++lllllllllllllllllIllllIlIIIlIlll) {
                final Element lllllllllllllllllIllllIlIIIllIlI = (Element)lllllllllllllllllIllllIlIIIlIIlI.item(lllllllllllllllllIllllIlIIIlIlll);
                final String lllllllllllllllllIllllIlIIIllIIl = lllllllllllllllllIllllIlIIIllIlI.getAttribute("name");
                final String lllllllllllllllllIllllIlIIIllIII = lllllllllllllllllIllllIlIIIllIlI.getAttribute("value");
                lllllllllllllllllIllllIlIIIlIlII.setProperty(lllllllllllllllllIllllIlIIIllIIl, lllllllllllllllllIllllIlIIIllIII);
            }
            this.props.put(new Integer(lllllllllllllllllIllllIlIIIlIlIl), lllllllllllllllllIllllIlIIIlIlII);
        }
    }
    
    public boolean contains(final int lllllllllllllllllIllllIIlIlllIIl) {
        return lllllllllllllllllIllllIIlIlllIIl >= this.firstGID && lllllllllllllllllIllllIIlIlllIIl <= this.lastGID;
    }
}
