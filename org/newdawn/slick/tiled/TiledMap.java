package org.newdawn.slick.tiled;

import java.util.*;
import org.xml.sax.*;
import java.io.*;
import org.newdawn.slick.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.newdawn.slick.*;

public class TiledMap
{
    protected /* synthetic */ int tileHeight;
    protected /* synthetic */ ArrayList tileSets;
    protected /* synthetic */ Properties props;
    protected /* synthetic */ ArrayList objectGroups;
    protected /* synthetic */ int width;
    protected /* synthetic */ String tilesLocation;
    protected /* synthetic */ int orientation;
    protected /* synthetic */ int tileWidth;
    protected /* synthetic */ ArrayList layers;
    private static /* synthetic */ boolean headless;
    private /* synthetic */ boolean loadTileSets;
    protected /* synthetic */ int height;
    
    public String getMapProperty(final String llllllllllllllllIlllIllIIIIlIIlI, final String llllllllllllllllIlllIllIIIIlIIIl) {
        if (this.props == null) {
            return llllllllllllllllIlllIllIIIIlIIIl;
        }
        return this.props.getProperty(llllllllllllllllIlllIllIIIIlIIlI, llllllllllllllllIlllIllIIIIlIIIl);
    }
    
    public TiledMap(final InputStream llllllllllllllllIlllIllIllIIlIIl) throws SlickException {
        this.tileSets = new ArrayList();
        this.layers = new ArrayList();
        this.objectGroups = new ArrayList();
        this.loadTileSets = true;
        this.load(llllllllllllllllIlllIllIllIIlIIl, "");
    }
    
    public TileSet findTileSet(final int llllllllllllllllIlllIIllIllllllI) {
        for (int llllllllllllllllIlllIIlllIIIIllI = 0; llllllllllllllllIlllIIlllIIIIllI < this.tileSets.size(); ++llllllllllllllllIlllIIlllIIIIllI) {
            final TileSet llllllllllllllllIlllIIlllIIIIlll = this.tileSets.get(llllllllllllllllIlllIIlllIIIIllI);
            if (llllllllllllllllIlllIIlllIIIIlll.contains(llllllllllllllllIlllIIllIllllllI)) {
                return llllllllllllllllIlllIIlllIIIIlll;
            }
        }
        return null;
    }
    
    public void render(final int llllllllllllllllIlllIlIlIlIIlIIl, final int llllllllllllllllIlllIlIlIlIIlIII, final int llllllllllllllllIlllIlIlIlIIIlll, final int llllllllllllllllIlllIlIlIlIIIllI, final int llllllllllllllllIlllIlIlIIllIlII, final int llllllllllllllllIlllIlIlIlIIIIlI, final int llllllllllllllllIlllIlIlIIllIIlI, final boolean llllllllllllllllIlllIlIlIlIIIIII) {
        final Layer llllllllllllllllIlllIlIlIIllllll = this.layers.get(llllllllllllllllIlllIlIlIIllIIlI);
        switch (this.orientation) {
            case 1: {
                for (int llllllllllllllllIlllIlIlIlIIlIll = 0; llllllllllllllllIlllIlIlIlIIlIll < llllllllllllllllIlllIlIlIlIIIIlI; ++llllllllllllllllIlllIlIlIlIIlIll) {
                    llllllllllllllllIlllIlIlIIllllll.render(llllllllllllllllIlllIlIlIlIIlIIl, llllllllllllllllIlllIlIlIlIIlIII, llllllllllllllllIlllIlIlIlIIIlll, llllllllllllllllIlllIlIlIlIIIllI, llllllllllllllllIlllIlIlIIllIlII, llllllllllllllllIlllIlIlIlIIlIll, llllllllllllllllIlllIlIlIlIIIIII, this.tileWidth, this.tileHeight);
                }
                break;
            }
            case 2: {
                this.renderIsometricMap(llllllllllllllllIlllIlIlIlIIlIIl, llllllllllllllllIlllIlIlIlIIlIII, llllllllllllllllIlllIlIlIlIIIlll, llllllllllllllllIlllIlIlIlIIIllI, llllllllllllllllIlllIlIlIIllIlII, llllllllllllllllIlllIlIlIlIIIIlI, llllllllllllllllIlllIlIlIIllllll, llllllllllllllllIlllIlIlIlIIIIII);
                break;
            }
        }
    }
    
    protected void renderIsometricMap(final int llllllllllllllllIlllIlIIlIIIlIll, final int llllllllllllllllIlllIlIIIllllIIl, final int llllllllllllllllIlllIlIIlIIIlIIl, final int llllllllllllllllIlllIlIIlIIIlIII, final int llllllllllllllllIlllIlIIIllllIII, final int llllllllllllllllIlllIlIIIlllIlll, final Layer llllllllllllllllIlllIlIIlIIIIlIl, final boolean llllllllllllllllIlllIlIIlIIIIlII) {
        ArrayList llllllllllllllllIlllIlIIlIIIIIll = this.layers;
        if (llllllllllllllllIlllIlIIlIIIIlIl != null) {
            llllllllllllllllIlllIlIIlIIIIIll = new ArrayList();
            llllllllllllllllIlllIlIIlIIIIIll.add(llllllllllllllllIlllIlIIlIIIIlIl);
        }
        final int llllllllllllllllIlllIlIIlIIIIIlI = llllllllllllllllIlllIlIIIllllIII * llllllllllllllllIlllIlIIIlllIlll;
        int llllllllllllllllIlllIlIIlIIIIIIl = 0;
        boolean llllllllllllllllIlllIlIIlIIIIIII = false;
        int llllllllllllllllIlllIlIIIlllllll = llllllllllllllllIlllIlIIlIIIlIll;
        int llllllllllllllllIlllIlIIIllllllI = llllllllllllllllIlllIlIIIllllIIl;
        int llllllllllllllllIlllIlIIIlllllIl = 0;
        int llllllllllllllllIlllIlIIIlllllII = 0;
        while (!llllllllllllllllIlllIlIIlIIIIIII) {
            int llllllllllllllllIlllIlIIlIIlIIII = llllllllllllllllIlllIlIIIlllllIl;
            int llllllllllllllllIlllIlIIlIIIllll = llllllllllllllllIlllIlIIIlllllII;
            int llllllllllllllllIlllIlIIlIIIlllI = llllllllllllllllIlllIlIIIlllllll;
            int llllllllllllllllIlllIlIIlIIIllIl = 0;
            if (llllllllllllllllIlllIlIIIlllIlll > llllllllllllllllIlllIlIIIllllIII) {
                llllllllllllllllIlllIlIIlIIIllIl = ((llllllllllllllllIlllIlIIIlllllII < llllllllllllllllIlllIlIIIllllIII - 1) ? llllllllllllllllIlllIlIIIlllllII : ((llllllllllllllllIlllIlIIIllllIII - llllllllllllllllIlllIlIIlIIlIIII < llllllllllllllllIlllIlIIIlllIlll) ? (llllllllllllllllIlllIlIIIllllIII - llllllllllllllllIlllIlIIlIIlIIII - 1) : (llllllllllllllllIlllIlIIIllllIII - 1)));
            }
            else {
                llllllllllllllllIlllIlIIlIIIllIl = ((llllllllllllllllIlllIlIIIlllllII < llllllllllllllllIlllIlIIIlllIlll - 1) ? llllllllllllllllIlllIlIIIlllllII : ((llllllllllllllllIlllIlIIIllllIII - llllllllllllllllIlllIlIIlIIlIIII < llllllllllllllllIlllIlIIIlllIlll) ? (llllllllllllllllIlllIlIIIllllIII - llllllllllllllllIlllIlIIlIIlIIII - 1) : (llllllllllllllllIlllIlIIIlllIlll - 1)));
            }
            for (int llllllllllllllllIlllIlIIlIIlIIIl = 0; llllllllllllllllIlllIlIIlIIlIIIl <= llllllllllllllllIlllIlIIlIIIllIl; ++llllllllllllllllIlllIlIIlIIlIIIl) {
                for (int llllllllllllllllIlllIlIIlIIlIIlI = 0; llllllllllllllllIlllIlIIlIIlIIlI < llllllllllllllllIlllIlIIlIIIIIll.size(); ++llllllllllllllllIlllIlIIlIIlIIlI) {
                    final Layer llllllllllllllllIlllIlIIlIIlIIll = llllllllllllllllIlllIlIIlIIIIIll.get(llllllllllllllllIlllIlIIlIIlIIlI);
                    llllllllllllllllIlllIlIIlIIlIIll.render(llllllllllllllllIlllIlIIlIIIlllI, llllllllllllllllIlllIlIIIllllllI, llllllllllllllllIlllIlIIlIIlIIII, llllllllllllllllIlllIlIIlIIIllll, 1, 0, llllllllllllllllIlllIlIIlIIIIlII, this.tileWidth, this.tileHeight);
                }
                llllllllllllllllIlllIlIIlIIIlllI += this.tileWidth;
                ++llllllllllllllllIlllIlIIlIIIIIIl;
                ++llllllllllllllllIlllIlIIlIIlIIII;
                --llllllllllllllllIlllIlIIlIIIllll;
            }
            if (llllllllllllllllIlllIlIIIlllllII < llllllllllllllllIlllIlIIIlllIlll - 1) {
                ++llllllllllllllllIlllIlIIIlllllII;
                llllllllllllllllIlllIlIIIlllllll -= this.tileWidth / 2;
                llllllllllllllllIlllIlIIIllllllI += this.tileHeight / 2;
            }
            else {
                ++llllllllllllllllIlllIlIIIlllllIl;
                llllllllllllllllIlllIlIIIlllllll += this.tileWidth / 2;
                llllllllllllllllIlllIlIIIllllllI += this.tileHeight / 2;
            }
            if (llllllllllllllllIlllIlIIlIIIIIIl >= llllllllllllllllIlllIlIIlIIIIIlI) {
                llllllllllllllllIlllIlIIlIIIIIII = true;
            }
        }
    }
    
    public String getObjectName(final int llllllllllllllllIlllIIllIlIIlIlI, final int llllllllllllllllIlllIIllIlIIllII) {
        if (llllllllllllllllIlllIIllIlIIlIlI >= 0 && llllllllllllllllIlllIIllIlIIlIlI < this.objectGroups.size()) {
            final ObjectGroup llllllllllllllllIlllIIllIlIlIIII = this.objectGroups.get(llllllllllllllllIlllIIllIlIIlIlI);
            if (llllllllllllllllIlllIIllIlIIllII >= 0 && llllllllllllllllIlllIIllIlIIllII < llllllllllllllllIlllIIllIlIlIIII.objects.size()) {
                final GroupObject llllllllllllllllIlllIIllIlIlIIlI = llllllllllllllllIlllIIllIlIlIIII.objects.get(llllllllllllllllIlllIIllIlIIllII);
                return llllllllllllllllIlllIIllIlIlIIlI.name;
            }
        }
        return null;
    }
    
    public int getTileId(final int llllllllllllllllIlllIllIIlIlIIII, final int llllllllllllllllIlllIllIIlIIllll, final int llllllllllllllllIlllIllIIlIIlllI) {
        final Layer llllllllllllllllIlllIllIIlIlIIll = this.layers.get(llllllllllllllllIlllIllIIlIIlllI);
        return llllllllllllllllIlllIllIIlIlIIll.getTileID(llllllllllllllllIlllIllIIlIlIIII, llllllllllllllllIlllIllIIlIIllll);
    }
    
    private static void setHeadless(final boolean llllllllllllllllIlllIlllIIIIIlIl) {
        TiledMap.headless = llllllllllllllllIlllIlllIIIIIlIl;
    }
    
    public String getObjectImage(final int llllllllllllllllIlllIIlIlIllllIl, final int llllllllllllllllIlllIIlIlIllllll) {
        if (llllllllllllllllIlllIIlIlIllllIl >= 0 && llllllllllllllllIlllIIlIlIllllIl < this.objectGroups.size()) {
            final ObjectGroup llllllllllllllllIlllIIlIllIIIllI = this.objectGroups.get(llllllllllllllllIlllIIlIlIllllIl);
            if (llllllllllllllllIlllIIlIlIllllll >= 0 && llllllllllllllllIlllIIlIlIllllll < llllllllllllllllIlllIIlIllIIIllI.objects.size()) {
                final GroupObject llllllllllllllllIlllIIlIllIIIlll = llllllllllllllllIlllIIlIllIIIllI.objects.get(llllllllllllllllIlllIIlIlIllllll);
                if (llllllllllllllllIlllIIlIllIIIlll == null) {
                    return null;
                }
                return llllllllllllllllIlllIIlIllIIIlll.image;
            }
        }
        return null;
    }
    
    public int getTileWidth() {
        return this.tileWidth;
    }
    
    public TiledMap(final String llllllllllllllllIlllIllIlllllIll) throws SlickException {
        this(llllllllllllllllIlllIllIlllllIll, true);
    }
    
    public String getObjectType(final int llllllllllllllllIlllIIllIIlllIll, final int llllllllllllllllIlllIIllIIllllIl) {
        if (llllllllllllllllIlllIIllIIlllIll >= 0 && llllllllllllllllIlllIIllIIlllIll < this.objectGroups.size()) {
            final ObjectGroup llllllllllllllllIlllIIllIlIIIIII = this.objectGroups.get(llllllllllllllllIlllIIllIIlllIll);
            if (llllllllllllllllIlllIIllIIllllIl >= 0 && llllllllllllllllIlllIIllIIllllIl < llllllllllllllllIlllIIllIlIIIIII.objects.size()) {
                final GroupObject llllllllllllllllIlllIIllIlIIIIIl = llllllllllllllllIlllIIllIlIIIIII.objects.get(llllllllllllllllIlllIIllIIllllIl);
                return llllllllllllllllIlllIIllIlIIIIIl.type;
            }
        }
        return null;
    }
    
    public TiledMap(final InputStream llllllllllllllllIlllIllIllIIIlII, final String llllllllllllllllIlllIllIllIIIIll) throws SlickException {
        this.tileSets = new ArrayList();
        this.layers = new ArrayList();
        this.objectGroups = new ArrayList();
        this.loadTileSets = true;
        this.load(llllllllllllllllIlllIllIllIIIlII, llllllllllllllllIlllIllIllIIIIll);
    }
    
    public void render(final int llllllllllllllllIlllIlIllIlIlIII, final int llllllllllllllllIlllIlIllIlIllII) {
        this.render(llllllllllllllllIlllIlIllIlIlIII, llllllllllllllllIlllIlIllIlIllII, 0, 0, this.width, this.height, false);
    }
    
    public int getTileHeight() {
        return this.tileHeight;
    }
    
    public TiledMap(final String llllllllllllllllIlllIllIllIlIlll, final String llllllllllllllllIlllIllIllIllIlI) throws SlickException {
        this.tileSets = new ArrayList();
        this.layers = new ArrayList();
        this.objectGroups = new ArrayList();
        this.loadTileSets = true;
        this.load(ResourceLoader.getResourceAsStream(llllllllllllllllIlllIllIllIlIlll), llllllllllllllllIlllIllIllIllIlI);
    }
    
    public TileSet getTileSetByGID(final int llllllllllllllllIlllIIlllIIllIIl) {
        for (int llllllllllllllllIlllIIlllIIlllIl = 0; llllllllllllllllIlllIIlllIIlllIl < this.tileSets.size(); ++llllllllllllllllIlllIIlllIIlllIl) {
            final TileSet llllllllllllllllIlllIIlllIIllllI = this.tileSets.get(llllllllllllllllIlllIIlllIIlllIl);
            if (llllllllllllllllIlllIIlllIIllllI.contains(llllllllllllllllIlllIIlllIIllIIl)) {
                return llllllllllllllllIlllIIlllIIllllI;
            }
        }
        return null;
    }
    
    public String getLayerProperty(final int llllllllllllllllIlllIlIlllllIlIl, final String llllllllllllllllIlllIlIlllllIIlI, final String llllllllllllllllIlllIlIllllIllll) {
        final Layer llllllllllllllllIlllIlIllllllIII = this.layers.get(llllllllllllllllIlllIlIlllllIlIl);
        if (llllllllllllllllIlllIlIllllllIII == null || llllllllllllllllIlllIlIllllllIII.props == null) {
            return llllllllllllllllIlllIlIllllIllll;
        }
        return llllllllllllllllIlllIlIllllllIII.props.getProperty(llllllllllllllllIlllIlIlllllIIlI, llllllllllllllllIlllIlIllllIllll);
    }
    
    static {
        ORTHOGONAL = 1;
        ISOMETRIC = 2;
    }
    
    public int getObjectWidth(final int llllllllllllllllIlllIIlIlllIllll, final int llllllllllllllllIlllIIlIlllIllIl) {
        if (llllllllllllllllIlllIIlIlllIllll >= 0 && llllllllllllllllIlllIIlIlllIllll < this.objectGroups.size()) {
            final ObjectGroup llllllllllllllllIlllIIlIlllllIIl = this.objectGroups.get(llllllllllllllllIlllIIlIlllIllll);
            if (llllllllllllllllIlllIIlIlllIllIl >= 0 && llllllllllllllllIlllIIlIlllIllIl < llllllllllllllllIlllIIlIlllllIIl.objects.size()) {
                final GroupObject llllllllllllllllIlllIIlIlllllIlI = llllllllllllllllIlllIIlIlllllIIl.objects.get(llllllllllllllllIlllIIlIlllIllIl);
                return llllllllllllllllIlllIIlIlllllIlI.width;
            }
        }
        return -1;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    private void load(final InputStream llllllllllllllllIlllIIllllIIllll, final String llllllllllllllllIlllIIllllIlIIlI) throws SlickException {
        this.tilesLocation = llllllllllllllllIlllIIllllIlIIlI;
        try {
            final DocumentBuilderFactory llllllllllllllllIlllIIlllllIIIII = DocumentBuilderFactory.newInstance();
            llllllllllllllllIlllIIlllllIIIII.setValidating(false);
            final DocumentBuilder llllllllllllllllIlllIIllllIlllll = llllllllllllllllIlllIIlllllIIIII.newDocumentBuilder();
            llllllllllllllllIlllIIllllIlllll.setEntityResolver(new EntityResolver() {
                @Override
                public InputSource resolveEntity(final String lIIIllIIIIlIlll, final String lIIIllIIIIlIllI) throws SAXException, IOException {
                    return new InputSource(new ByteArrayInputStream(new byte[0]));
                }
            });
            final Document llllllllllllllllIlllIIllllIllllI = llllllllllllllllIlllIIllllIlllll.parse(llllllllllllllllIlllIIllllIIllll);
            final Element llllllllllllllllIlllIIllllIlllIl = llllllllllllllllIlllIIllllIllllI.getDocumentElement();
            if (llllllllllllllllIlllIIllllIlllIl.getAttribute("orientation").equals("orthogonal")) {
                this.orientation = 1;
            }
            else {
                this.orientation = 2;
            }
            this.width = this.parseInt(llllllllllllllllIlllIIllllIlllIl.getAttribute("width"));
            this.height = this.parseInt(llllllllllllllllIlllIIllllIlllIl.getAttribute("height"));
            this.tileWidth = this.parseInt(llllllllllllllllIlllIIllllIlllIl.getAttribute("tilewidth"));
            this.tileHeight = this.parseInt(llllllllllllllllIlllIIllllIlllIl.getAttribute("tileheight"));
            final Element llllllllllllllllIlllIIllllIlllII = (Element)llllllllllllllllIlllIIllllIlllIl.getElementsByTagName("properties").item(0);
            if (llllllllllllllllIlllIIllllIlllII != null) {
                final NodeList llllllllllllllllIlllIIlllllIllII = llllllllllllllllIlllIIllllIlllII.getElementsByTagName("property");
                if (llllllllllllllllIlllIIlllllIllII != null) {
                    this.props = new Properties();
                    for (int llllllllllllllllIlllIIlllllIllIl = 0; llllllllllllllllIlllIIlllllIllIl < llllllllllllllllIlllIIlllllIllII.getLength(); ++llllllllllllllllIlllIIlllllIllIl) {
                        final Element llllllllllllllllIlllIIllllllIIII = (Element)llllllllllllllllIlllIIlllllIllII.item(llllllllllllllllIlllIIlllllIllIl);
                        final String llllllllllllllllIlllIIlllllIllll = llllllllllllllllIlllIIllllllIIII.getAttribute("name");
                        final String llllllllllllllllIlllIIlllllIlllI = llllllllllllllllIlllIIllllllIIII.getAttribute("value");
                        this.props.setProperty(llllllllllllllllIlllIIlllllIllll, llllllllllllllllIlllIIlllllIlllI);
                    }
                }
            }
            if (this.loadTileSets) {
                TileSet llllllllllllllllIlllIIlllllIlIIl = null;
                TileSet llllllllllllllllIlllIIlllllIlIII = null;
                final NodeList llllllllllllllllIlllIIlllllIIlll = llllllllllllllllIlllIIllllIlllIl.getElementsByTagName("tileset");
                for (int llllllllllllllllIlllIIlllllIlIlI = 0; llllllllllllllllIlllIIlllllIlIlI < llllllllllllllllIlllIIlllllIIlll.getLength(); ++llllllllllllllllIlllIIlllllIlIlI) {
                    final Element llllllllllllllllIlllIIlllllIlIll = (Element)llllllllllllllllIlllIIlllllIIlll.item(llllllllllllllllIlllIIlllllIlIlI);
                    llllllllllllllllIlllIIlllllIlIIl = new TileSet(this, llllllllllllllllIlllIIlllllIlIll, !TiledMap.headless);
                    llllllllllllllllIlllIIlllllIlIIl.index = llllllllllllllllIlllIIlllllIlIlI;
                    if (llllllllllllllllIlllIIlllllIlIII != null) {
                        llllllllllllllllIlllIIlllllIlIII.setLimit(llllllllllllllllIlllIIlllllIlIIl.firstGID - 1);
                    }
                    llllllllllllllllIlllIIlllllIlIII = llllllllllllllllIlllIIlllllIlIIl;
                    this.tileSets.add(llllllllllllllllIlllIIlllllIlIIl);
                }
            }
            final NodeList llllllllllllllllIlllIIllllIllIlI = llllllllllllllllIlllIIllllIlllIl.getElementsByTagName("layer");
            for (int llllllllllllllllIlllIIlllllIIlII = 0; llllllllllllllllIlllIIlllllIIlII < llllllllllllllllIlllIIllllIllIlI.getLength(); ++llllllllllllllllIlllIIlllllIIlII) {
                final Element llllllllllllllllIlllIIlllllIIllI = (Element)llllllllllllllllIlllIIllllIllIlI.item(llllllllllllllllIlllIIlllllIIlII);
                final Layer llllllllllllllllIlllIIlllllIIlIl = new Layer(this, llllllllllllllllIlllIIlllllIIllI);
                llllllllllllllllIlllIIlllllIIlIl.index = llllllllllllllllIlllIIlllllIIlII;
                this.layers.add(llllllllllllllllIlllIIlllllIIlIl);
            }
            final NodeList llllllllllllllllIlllIIllllIllIII = llllllllllllllllIlllIIllllIlllIl.getElementsByTagName("objectgroup");
            for (int llllllllllllllllIlllIIlllllIIIIl = 0; llllllllllllllllIlllIIlllllIIIIl < llllllllllllllllIlllIIllllIllIII.getLength(); ++llllllllllllllllIlllIIlllllIIIIl) {
                final Element llllllllllllllllIlllIIlllllIIIll = (Element)llllllllllllllllIlllIIllllIllIII.item(llllllllllllllllIlllIIlllllIIIIl);
                final ObjectGroup llllllllllllllllIlllIIlllllIIIlI = new ObjectGroup(llllllllllllllllIlllIIlllllIIIll);
                llllllllllllllllIlllIIlllllIIIlI.index = llllllllllllllllIlllIIlllllIIIIl;
                this.objectGroups.add(llllllllllllllllIlllIIlllllIIIlI);
            }
        }
        catch (Exception llllllllllllllllIlllIIllllIlIllI) {
            Log.error(llllllllllllllllIlllIIllllIlIllI);
            throw new SlickException("Failed to parse tilemap", llllllllllllllllIlllIIllllIlIllI);
        }
    }
    
    public TiledMap(String llllllllllllllllIlllIllIlllIIlll, final boolean llllllllllllllllIlllIllIlllIIllI) throws SlickException {
        this.tileSets = new ArrayList();
        this.layers = new ArrayList();
        this.objectGroups = new ArrayList();
        this.loadTileSets = true;
        this.loadTileSets = llllllllllllllllIlllIllIlllIIllI;
        llllllllllllllllIlllIllIlllIIlll = (boolean)((String)llllllllllllllllIlllIllIlllIIlll).replace('\\', '/');
        this.load(ResourceLoader.getResourceAsStream((String)llllllllllllllllIlllIllIlllIIlll), ((String)llllllllllllllllIlllIllIlllIIlll).substring(0, ((String)llllllllllllllllIlllIllIlllIIlll).lastIndexOf("/")));
    }
    
    public int getTileSetCount() {
        return this.tileSets.size();
    }
    
    public int getLayerCount() {
        return this.layers.size();
    }
    
    public void render(final int llllllllllllllllIlllIlIlIllllIIl, final int llllllllllllllllIlllIlIlIllIllII, final int llllllllllllllllIlllIlIlIllIlIlI, final int llllllllllllllllIlllIlIlIlllIIll, final int llllllllllllllllIlllIlIlIllIIllI, final int llllllllllllllllIlllIlIlIllIIlII) {
        this.render(llllllllllllllllIlllIlIlIllllIIl, llllllllllllllllIlllIlIlIllIllII, llllllllllllllllIlllIlIlIllIlIlI, llllllllllllllllIlllIlIlIlllIIll, llllllllllllllllIlllIlIlIllIIllI, llllllllllllllllIlllIlIlIllIIlII, false);
    }
    
    public String getTilesLocation() {
        return this.tilesLocation;
    }
    
    public Image getTileImage(final int llllllllllllllllIlllIllIIllllllI, final int llllllllllllllllIlllIllIIlllllIl, final int llllllllllllllllIlllIllIIlllIlIl) {
        final Layer llllllllllllllllIlllIllIIllllIll = this.layers.get(llllllllllllllllIlllIllIIlllIlIl);
        final int llllllllllllllllIlllIllIIllllIlI = llllllllllllllllIlllIllIIllllIll.data[llllllllllllllllIlllIllIIllllllI][llllllllllllllllIlllIllIIlllllIl][0];
        if (llllllllllllllllIlllIllIIllllIlI >= 0 && llllllllllllllllIlllIllIIllllIlI < this.tileSets.size()) {
            final TileSet llllllllllllllllIlllIllIlIIIIIlI = this.tileSets.get(llllllllllllllllIlllIllIIllllIlI);
            final int llllllllllllllllIlllIllIlIIIIIIl = llllllllllllllllIlllIllIlIIIIIlI.getTileX(llllllllllllllllIlllIllIIllllIll.data[llllllllllllllllIlllIllIIllllllI][llllllllllllllllIlllIllIIlllllIl][1]);
            final int llllllllllllllllIlllIllIlIIIIIII = llllllllllllllllIlllIllIlIIIIIlI.getTileY(llllllllllllllllIlllIllIIllllIll.data[llllllllllllllllIlllIllIIllllllI][llllllllllllllllIlllIllIIlllllIl][1]);
            return llllllllllllllllIlllIllIlIIIIIlI.tiles.getSprite(llllllllllllllllIlllIllIlIIIIIIl, llllllllllllllllIlllIllIlIIIIIII);
        }
        return null;
    }
    
    public int getObjectCount(final int llllllllllllllllIlllIIllIllIIlll) {
        if (llllllllllllllllIlllIIllIllIIlll >= 0 && llllllllllllllllIlllIIllIllIIlll < this.objectGroups.size()) {
            final ObjectGroup llllllllllllllllIlllIIllIllIlIIl = this.objectGroups.get(llllllllllllllllIlllIIllIllIIlll);
            return llllllllllllllllIlllIIllIllIlIIl.objects.size();
        }
        return -1;
    }
    
    public String getTileProperty(final int llllllllllllllllIlllIlIlllIlIIII, final String llllllllllllllllIlllIlIlllIIllIl, final String llllllllllllllllIlllIlIlllIIIIIl) {
        if (llllllllllllllllIlllIlIlllIlIIII == 0) {
            return llllllllllllllllIlllIlIlllIIIIIl;
        }
        final TileSet llllllllllllllllIlllIlIlllIIlIII = this.findTileSet(llllllllllllllllIlllIlIlllIlIIII);
        final Properties llllllllllllllllIlllIlIlllIIIllI = llllllllllllllllIlllIlIlllIIlIII.getProperties(llllllllllllllllIlllIlIlllIlIIII);
        if (llllllllllllllllIlllIlIlllIIIllI == null) {
            return llllllllllllllllIlllIlIlllIIIIIl;
        }
        return llllllllllllllllIlllIlIlllIIIllI.getProperty(llllllllllllllllIlllIlIlllIIllIl, llllllllllllllllIlllIlIlllIIIIIl);
    }
    
    public void setTileId(final int llllllllllllllllIlllIllIIIlllIlI, final int llllllllllllllllIlllIllIIIlIlIlI, final int llllllllllllllllIlllIllIIIllIllI, final int llllllllllllllllIlllIllIIIlIIllI) {
        final Layer llllllllllllllllIlllIllIIIllIIlI = this.layers.get(llllllllllllllllIlllIllIIIllIllI);
        llllllllllllllllIlllIllIIIllIIlI.setTileID(llllllllllllllllIlllIllIIIlllIlI, llllllllllllllllIlllIllIIIlIlIlI, llllllllllllllllIlllIllIIIlIIllI);
    }
    
    public int getObjectY(final int llllllllllllllllIlllIIllIIIIIlII, final int llllllllllllllllIlllIIllIIIIIlll) {
        if (llllllllllllllllIlllIIllIIIIIlII >= 0 && llllllllllllllllIlllIIllIIIIIlII < this.objectGroups.size()) {
            final ObjectGroup llllllllllllllllIlllIIllIIIIllIl = this.objectGroups.get(llllllllllllllllIlllIIllIIIIIlII);
            if (llllllllllllllllIlllIIllIIIIIlll >= 0 && llllllllllllllllIlllIIllIIIIIlll < llllllllllllllllIlllIIllIIIIllIl.objects.size()) {
                final GroupObject llllllllllllllllIlllIIllIIIIllll = llllllllllllllllIlllIIllIIIIllIl.objects.get(llllllllllllllllIlllIIllIIIIIlll);
                return llllllllllllllllIlllIIllIIIIllll.y;
            }
        }
        return -1;
    }
    
    public void render(final int llllllllllllllllIlllIlIllIIllIlI, final int llllllllllllllllIlllIlIllIIllIIl, final int llllllllllllllllIlllIlIllIIlIlII) {
        this.render(llllllllllllllllIlllIlIllIIllIlI, llllllllllllllllIlllIlIllIIllIIl, 0, 0, this.getWidth(), this.getHeight(), llllllllllllllllIlllIlIllIIlIlII, false);
    }
    
    public TileSet getTileSet(final int llllllllllllllllIlllIIlllIlIIlIl) {
        return this.tileSets.get(llllllllllllllllIlllIIlllIlIIlIl);
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getLayerIndex(final String llllllllllllllllIlllIllIlIlIlIII) {
        final int llllllllllllllllIlllIllIlIlIIlll = 0;
        for (int llllllllllllllllIlllIllIlIlIlIlI = 0; llllllllllllllllIlllIllIlIlIlIlI < this.layers.size(); ++llllllllllllllllIlllIllIlIlIlIlI) {
            final Layer llllllllllllllllIlllIllIlIlIlIll = this.layers.get(llllllllllllllllIlllIllIlIlIlIlI);
            if (llllllllllllllllIlllIllIlIlIlIll.name.equals(llllllllllllllllIlllIllIlIlIlIII)) {
                return llllllllllllllllIlllIllIlIlIlIlI;
            }
        }
        return -1;
    }
    
    private int parseInt(final String llllllllllllllllIlllIlIIIlIllIlI) {
        try {
            return Integer.parseInt(llllllllllllllllIlllIlIIIlIllIlI);
        }
        catch (NumberFormatException llllllllllllllllIlllIlIIIlIlllIl) {
            return 0;
        }
    }
    
    public String getObjectProperty(final int llllllllllllllllIlllIIlIlIlIIIlI, final int llllllllllllllllIlllIIlIlIlIIIIl, final String llllllllllllllllIlllIIlIlIIlllll, final String llllllllllllllllIlllIIlIlIIlIlll) {
        if (llllllllllllllllIlllIIlIlIlIIIlI >= 0 && llllllllllllllllIlllIIlIlIlIIIlI < this.objectGroups.size()) {
            final ObjectGroup llllllllllllllllIlllIIlIlIlIIlII = this.objectGroups.get(llllllllllllllllIlllIIlIlIlIIIlI);
            if (llllllllllllllllIlllIIlIlIlIIIIl >= 0 && llllllllllllllllIlllIIlIlIlIIIIl < llllllllllllllllIlllIIlIlIlIIlII.objects.size()) {
                final GroupObject llllllllllllllllIlllIIlIlIlIIlIl = llllllllllllllllIlllIIlIlIlIIlII.objects.get(llllllllllllllllIlllIIlIlIlIIIIl);
                if (llllllllllllllllIlllIIlIlIlIIlIl == null) {
                    return llllllllllllllllIlllIIlIlIIlIlll;
                }
                if (llllllllllllllllIlllIIlIlIlIIlIl.props == null) {
                    return llllllllllllllllIlllIIlIlIIlIlll;
                }
                return llllllllllllllllIlllIIlIlIlIIlIl.props.getProperty(llllllllllllllllIlllIIlIlIIlllll, llllllllllllllllIlllIIlIlIIlIlll);
            }
        }
        return llllllllllllllllIlllIIlIlIIlIlll;
    }
    
    public int getObjectHeight(final int llllllllllllllllIlllIIlIllIlIIll, final int llllllllllllllllIlllIIlIllIIllll) {
        if (llllllllllllllllIlllIIlIllIlIIll >= 0 && llllllllllllllllIlllIIlIllIlIIll < this.objectGroups.size()) {
            final ObjectGroup llllllllllllllllIlllIIlIllIlIlIl = this.objectGroups.get(llllllllllllllllIlllIIlIllIlIIll);
            if (llllllllllllllllIlllIIlIllIIllll >= 0 && llllllllllllllllIlllIIlIllIIllll < llllllllllllllllIlllIIlIllIlIlIl.objects.size()) {
                final GroupObject llllllllllllllllIlllIIlIllIlIllI = llllllllllllllllIlllIIlIllIlIlIl.objects.get(llllllllllllllllIlllIIlIllIIllll);
                return llllllllllllllllIlllIIlIllIlIllI.height;
            }
        }
        return -1;
    }
    
    public int getObjectGroupCount() {
        return this.objectGroups.size();
    }
    
    protected void renderedLine(final int llllllllllllllllIlllIIllIlllIIll, final int llllllllllllllllIlllIIllIlllIIIl, final int llllllllllllllllIlllIIllIlllIIII) {
    }
    
    public int getObjectX(final int llllllllllllllllIlllIIllIIlIIlIl, final int llllllllllllllllIlllIIllIIlIIIll) {
        if (llllllllllllllllIlllIIllIIlIIlIl >= 0 && llllllllllllllllIlllIIllIIlIIlIl < this.objectGroups.size()) {
            final ObjectGroup llllllllllllllllIlllIIllIIlIlIIl = this.objectGroups.get(llllllllllllllllIlllIIllIIlIIlIl);
            if (llllllllllllllllIlllIIllIIlIIIll >= 0 && llllllllllllllllIlllIIllIIlIIIll < llllllllllllllllIlllIIllIIlIlIIl.objects.size()) {
                final GroupObject llllllllllllllllIlllIIllIIlIlIll = llllllllllllllllIlllIIllIIlIlIIl.objects.get(llllllllllllllllIlllIIllIIlIIIll);
                return llllllllllllllllIlllIIllIIlIlIll.x;
            }
        }
        return -1;
    }
    
    public void render(final int llllllllllllllllIlllIlIlIIIIllII, final int llllllllllllllllIlllIlIlIIIlIlIl, final int llllllllllllllllIlllIlIlIIIIlIlI, final int llllllllllllllllIlllIlIlIIIlIIIl, final int llllllllllllllllIlllIlIlIIIlIIII, final int llllllllllllllllIlllIlIlIIIIllll, final boolean llllllllllllllllIlllIlIlIIIIlllI) {
        switch (this.orientation) {
            case 1: {
                for (int llllllllllllllllIlllIlIlIIIllIlI = 0; llllllllllllllllIlllIlIlIIIllIlI < llllllllllllllllIlllIlIlIIIIllll; ++llllllllllllllllIlllIlIlIIIllIlI) {
                    for (int llllllllllllllllIlllIlIlIIIllIll = 0; llllllllllllllllIlllIlIlIIIllIll < this.layers.size(); ++llllllllllllllllIlllIlIlIIIllIll) {
                        final Layer llllllllllllllllIlllIlIlIIIlllIl = this.layers.get(llllllllllllllllIlllIlIlIIIllIll);
                        llllllllllllllllIlllIlIlIIIlllIl.render(llllllllllllllllIlllIlIlIIIIllII, llllllllllllllllIlllIlIlIIIlIlIl, llllllllllllllllIlllIlIlIIIIlIlI, llllllllllllllllIlllIlIlIIIlIIIl, llllllllllllllllIlllIlIlIIIlIIII, llllllllllllllllIlllIlIlIIIllIlI, llllllllllllllllIlllIlIlIIIIlllI, this.tileWidth, this.tileHeight);
                    }
                }
                break;
            }
            case 2: {
                this.renderIsometricMap(llllllllllllllllIlllIlIlIIIIllII, llllllllllllllllIlllIlIlIIIlIlIl, llllllllllllllllIlllIlIlIIIIlIlI, llllllllllllllllIlllIlIlIIIlIIIl, llllllllllllllllIlllIlIlIIIlIIII, llllllllllllllllIlllIlIlIIIIllll, null, llllllllllllllllIlllIlIlIIIIlllI);
                break;
            }
        }
    }
    
    protected class GroupObject
    {
        public /* synthetic */ int height;
        public /* synthetic */ int index;
        public /* synthetic */ int y;
        public /* synthetic */ int width;
        public /* synthetic */ int x;
        private /* synthetic */ String image;
        public /* synthetic */ String name;
        public /* synthetic */ Properties props;
        public /* synthetic */ String type;
        
        public GroupObject(final Element lllllllllllllllllIIllIIllIlIlIll) throws SlickException {
            this.name = lllllllllllllllllIIllIIllIlIlIll.getAttribute("name");
            this.type = lllllllllllllllllIIllIIllIlIlIll.getAttribute("type");
            this.x = Integer.parseInt(lllllllllllllllllIIllIIllIlIlIll.getAttribute("x"));
            this.y = Integer.parseInt(lllllllllllllllllIIllIIllIlIlIll.getAttribute("y"));
            this.width = Integer.parseInt(lllllllllllllllllIIllIIllIlIlIll.getAttribute("width"));
            this.height = Integer.parseInt(lllllllllllllllllIIllIIllIlIlIll.getAttribute("height"));
            final Element lllllllllllllllllIIllIIllIlIlIlI = (Element)lllllllllllllllllIIllIIllIlIlIll.getElementsByTagName("image").item(0);
            if (lllllllllllllllllIIllIIllIlIlIlI != null) {
                this.image = lllllllllllllllllIIllIIllIlIlIlI.getAttribute("source");
            }
            final Element lllllllllllllllllIIllIIllIlIlIIl = (Element)lllllllllllllllllIIllIIllIlIlIll.getElementsByTagName("properties").item(0);
            if (lllllllllllllllllIIllIIllIlIlIIl != null) {
                final NodeList lllllllllllllllllIIllIIllIlIlllI = lllllllllllllllllIIllIIllIlIlIIl.getElementsByTagName("property");
                if (lllllllllllllllllIIllIIllIlIlllI != null) {
                    this.props = new Properties();
                    for (int lllllllllllllllllIIllIIllIlIllll = 0; lllllllllllllllllIIllIIllIlIllll < lllllllllllllllllIIllIIllIlIlllI.getLength(); ++lllllllllllllllllIIllIIllIlIllll) {
                        final Element lllllllllllllllllIIllIIllIllIIlI = (Element)lllllllllllllllllIIllIIllIlIlllI.item(lllllllllllllllllIIllIIllIlIllll);
                        final String lllllllllllllllllIIllIIllIllIIIl = lllllllllllllllllIIllIIllIllIIlI.getAttribute("name");
                        final String lllllllllllllllllIIllIIllIllIIII = lllllllllllllllllIIllIIllIllIIlI.getAttribute("value");
                        this.props.setProperty(lllllllllllllllllIIllIIllIllIIIl, lllllllllllllllllIIllIIllIllIIII);
                    }
                }
            }
        }
    }
    
    protected class ObjectGroup
    {
        public /* synthetic */ int index;
        public /* synthetic */ int height;
        public /* synthetic */ ArrayList objects;
        public /* synthetic */ int width;
        public /* synthetic */ Properties props;
        public /* synthetic */ String name;
        
        public ObjectGroup(final Element lllIIIIIIlllllI) throws SlickException {
            this.name = lllIIIIIIlllllI.getAttribute("name");
            this.width = Integer.parseInt(lllIIIIIIlllllI.getAttribute("width"));
            this.height = Integer.parseInt(lllIIIIIIlllllI.getAttribute("height"));
            this.objects = new ArrayList();
            final Element lllIIIIIlIIIIlI = (Element)lllIIIIIIlllllI.getElementsByTagName("properties").item(0);
            if (lllIIIIIlIIIIlI != null) {
                final NodeList lllIIIIIlIIlIIl = lllIIIIIlIIIIlI.getElementsByTagName("property");
                if (lllIIIIIlIIlIIl != null) {
                    this.props = new Properties();
                    for (int lllIIIIIlIIlIlI = 0; lllIIIIIlIIlIlI < lllIIIIIlIIlIIl.getLength(); ++lllIIIIIlIIlIlI) {
                        final Element lllIIIIIlIIllIl = (Element)lllIIIIIlIIlIIl.item(lllIIIIIlIIlIlI);
                        final String lllIIIIIlIIllII = lllIIIIIlIIllIl.getAttribute("name");
                        final String lllIIIIIlIIlIll = lllIIIIIlIIllIl.getAttribute("value");
                        this.props.setProperty(lllIIIIIlIIllII, lllIIIIIlIIlIll);
                    }
                }
            }
            final NodeList lllIIIIIlIIIIIl = lllIIIIIIlllllI.getElementsByTagName("object");
            for (int lllIIIIIlIIIllI = 0; lllIIIIIlIIIllI < lllIIIIIlIIIIIl.getLength(); ++lllIIIIIlIIIllI) {
                final Element lllIIIIIlIIlIII = (Element)lllIIIIIlIIIIIl.item(lllIIIIIlIIIllI);
                final GroupObject lllIIIIIlIIIlll = new GroupObject(lllIIIIIlIIlIII);
                lllIIIIIlIIIlll.index = lllIIIIIlIIIllI;
                this.objects.add(lllIIIIIlIIIlll);
            }
        }
    }
}
