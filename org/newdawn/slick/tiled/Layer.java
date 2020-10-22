package org.newdawn.slick.tiled;

import java.util.*;
import java.util.zip.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.*;
import java.io.*;
import org.w3c.dom.*;

public class Layer
{
    public /* synthetic */ String name;
    public /* synthetic */ int height;
    private final /* synthetic */ TiledMap map;
    public /* synthetic */ int width;
    public /* synthetic */ int[][][] data;
    public /* synthetic */ int index;
    public /* synthetic */ Properties props;
    private static /* synthetic */ byte[] baseCodes;
    
    public void render(final int llllIllIllIIlI, final int llllIllIlIIlll, final int llllIllIlIIllI, final int llllIllIlIIlIl, final int llllIllIlIlllI, final int llllIllIlIIIll, final boolean llllIllIlIllII, final int llllIllIlIlIll, final int llllIllIlIIIII) {
        for (int llllIllIllIlII = 0; llllIllIllIlII < this.map.getTileSetCount(); ++llllIllIllIlII) {
            TileSet llllIllIllIlIl = null;
            for (int llllIllIllIllI = 0; llllIllIllIllI < llllIllIlIlllI; ++llllIllIllIllI) {
                if (llllIllIlIIllI + llllIllIllIllI >= 0) {
                    if (llllIllIlIIlIl + llllIllIlIIIll >= 0) {
                        if (llllIllIlIIllI + llllIllIllIllI < this.width) {
                            if (llllIllIlIIlIl + llllIllIlIIIll < this.height) {
                                if (this.data[llllIllIlIIllI + llllIllIllIllI][llllIllIlIIlIl + llllIllIlIIIll][0] == llllIllIllIlII) {
                                    if (llllIllIllIlIl == null) {
                                        llllIllIllIlIl = this.map.getTileSet(llllIllIllIlII);
                                        llllIllIllIlIl.tiles.startUse();
                                    }
                                    final int llllIllIlllIIl = llllIllIllIlIl.getTileX(this.data[llllIllIlIIllI + llllIllIllIllI][llllIllIlIIlIl + llllIllIlIIIll][1]);
                                    final int llllIllIlllIII = llllIllIllIlIl.getTileY(this.data[llllIllIlIIllI + llllIllIllIllI][llllIllIlIIlIl + llllIllIlIIIll][1]);
                                    final int llllIllIllIlll = llllIllIllIlIl.tileHeight - llllIllIlIIIII;
                                    llllIllIllIlIl.tiles.renderInUse(llllIllIllIIlI + llllIllIllIllI * llllIllIlIlIll, llllIllIlIIlll + llllIllIlIIIll * llllIllIlIIIII - llllIllIllIlll, llllIllIlllIIl, llllIllIlllIII);
                                }
                            }
                        }
                    }
                }
            }
            if (llllIllIlIllII) {
                if (llllIllIllIlIl != null) {
                    llllIllIllIlIl.tiles.endUse();
                    llllIllIllIlIl = null;
                }
                this.map.renderedLine(llllIllIlIIIll, llllIllIlIIIll + llllIllIlIIlIl, this.index);
            }
            if (llllIllIllIlIl != null) {
                llllIllIllIlIl.tiles.endUse();
            }
        }
    }
    
    public int getTileID(final int llllIlllIlllIl, final int llllIlllIllIIl) {
        return this.data[llllIlllIlllIl][llllIlllIllIIl][2];
    }
    
    public Layer(final TiledMap llllIlllllIllI, final Element llllIllllIlllI) throws SlickException {
        this.map = llllIlllllIllI;
        this.name = llllIllllIlllI.getAttribute("name");
        this.width = Integer.parseInt(llllIllllIlllI.getAttribute("width"));
        this.height = Integer.parseInt(llllIllllIlllI.getAttribute("height"));
        this.data = new int[this.width][this.height][3];
        final Element llllIlllllIlII = (Element)llllIllllIlllI.getElementsByTagName("properties").item(0);
        if (llllIlllllIlII != null) {
            final NodeList lllllIIIIIIIIl = llllIlllllIlII.getElementsByTagName("property");
            if (lllllIIIIIIIIl != null) {
                this.props = new Properties();
                for (int lllllIIIIIIIlI = 0; lllllIIIIIIIlI < lllllIIIIIIIIl.getLength(); ++lllllIIIIIIIlI) {
                    final Element lllllIIIIIIlIl = (Element)lllllIIIIIIIIl.item(lllllIIIIIIIlI);
                    final String lllllIIIIIIlII = lllllIIIIIIlIl.getAttribute("name");
                    final String lllllIIIIIIIll = lllllIIIIIIlIl.getAttribute("value");
                    this.props.setProperty(lllllIIIIIIlII, lllllIIIIIIIll);
                }
            }
        }
        final Element llllIlllllIIll = (Element)llllIllllIlllI.getElementsByTagName("data").item(0);
        final String llllIlllllIIlI = llllIlllllIIll.getAttribute("encoding");
        final String llllIlllllIIIl = llllIlllllIIll.getAttribute("compression");
        if (llllIlllllIIlI.equals("base64") && llllIlllllIIIl.equals("gzip")) {
            try {
                final Node llllIlllllllII = llllIlllllIIll.getFirstChild();
                final char[] llllIllllllIll = llllIlllllllII.getNodeValue().trim().toCharArray();
                final byte[] llllIllllllIlI = this.decodeBase64(llllIllllllIll);
                final GZIPInputStream llllIllllllIIl = new GZIPInputStream(new ByteArrayInputStream(llllIllllllIlI));
                for (int llllIlllllllIl = 0; llllIlllllllIl < this.height; ++llllIlllllllIl) {
                    for (int llllIllllllllI = 0; llllIllllllllI < this.width; ++llllIllllllllI) {
                        int llllIlllllllll = 0;
                        llllIlllllllll |= llllIllllllIIl.read();
                        llllIlllllllll |= llllIllllllIIl.read() << 8;
                        llllIlllllllll |= llllIllllllIIl.read() << 16;
                        llllIlllllllll |= llllIllllllIIl.read() << 24;
                        if (llllIlllllllll == 0) {
                            this.data[llllIllllllllI][llllIlllllllIl][0] = -1;
                            this.data[llllIllllllllI][llllIlllllllIl][1] = 0;
                            this.data[llllIllllllllI][llllIlllllllIl][2] = 0;
                        }
                        else {
                            final TileSet lllllIIIIIIIII = llllIlllllIllI.findTileSet(llllIlllllllll);
                            if (lllllIIIIIIIII != null) {
                                this.data[llllIllllllllI][llllIlllllllIl][0] = lllllIIIIIIIII.index;
                                this.data[llllIllllllllI][llllIlllllllIl][1] = llllIlllllllll - lllllIIIIIIIII.firstGID;
                            }
                            this.data[llllIllllllllI][llllIlllllllIl][2] = llllIlllllllll;
                        }
                    }
                }
                return;
            }
            catch (IOException llllIllllllIII) {
                Log.error(llllIllllllIII);
                throw new SlickException("Unable to decode base 64 block");
            }
            throw new SlickException(String.valueOf(new StringBuilder().append("Unsupport tiled map type: ").append(llllIlllllIIlI).append(",").append(llllIlllllIIIl).append(" (only gzip base64 supported)")));
        }
        throw new SlickException(String.valueOf(new StringBuilder().append("Unsupport tiled map type: ").append(llllIlllllIIlI).append(",").append(llllIlllllIIIl).append(" (only gzip base64 supported)")));
    }
    
    private byte[] decodeBase64(final char[] llllIllIIIIlIl) {
        int llllIllIIIlIll = llllIllIIIIlIl.length;
        for (int llllIllIIlIIII = 0; llllIllIIlIIII < llllIllIIIIlIl.length; ++llllIllIIlIIII) {
            if (llllIllIIIIlIl[llllIllIIlIIII] > '\u00ff' || Layer.baseCodes[llllIllIIIIlIl[llllIllIIlIIII]] < 0) {
                --llllIllIIIlIll;
            }
        }
        int llllIllIIIlIlI = llllIllIIIlIll / 4 * 3;
        if (llllIllIIIlIll % 4 == 3) {
            llllIllIIIlIlI += 2;
        }
        if (llllIllIIIlIll % 4 == 2) {
            ++llllIllIIIlIlI;
        }
        final byte[] llllIllIIIlIIl = new byte[llllIllIIIlIlI];
        int llllIllIIIlIII = 0;
        int llllIllIIIIlll = 0;
        int llllIllIIIIllI = 0;
        for (int llllIllIIIlllI = 0; llllIllIIIlllI < llllIllIIIIlIl.length; ++llllIllIIIlllI) {
            final int llllIllIIIllll = (llllIllIIIIlIl[llllIllIIIlllI] > '\u00ff') ? -1 : Layer.baseCodes[llllIllIIIIlIl[llllIllIIIlllI]];
            if (llllIllIIIllll >= 0) {
                llllIllIIIIlll <<= 6;
                llllIllIIIlIII += 6;
                llllIllIIIIlll |= llllIllIIIllll;
                if (llllIllIIIlIII >= 8) {
                    llllIllIIIlIII -= 8;
                    llllIllIIIlIIl[llllIllIIIIllI++] = (byte)(llllIllIIIIlll >> llllIllIIIlIII & 0xFF);
                }
            }
        }
        if (llllIllIIIIllI != llllIllIIIlIIl.length) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("Data length appears to be wrong (wrote ").append(llllIllIIIIllI).append(" should be ").append(llllIllIIIlIIl.length).append(")")));
        }
        return llllIllIIIlIIl;
    }
    
    public void setTileID(final int llllIlllIIllIl, final int llllIlllIlIIII, final int llllIlllIIllll) {
        if (llllIlllIIllll == 0) {
            this.data[llllIlllIIllIl][llllIlllIlIIII][0] = -1;
            this.data[llllIlllIIllIl][llllIlllIlIIII][1] = 0;
            this.data[llllIlllIIllIl][llllIlllIlIIII][2] = 0;
        }
        else {
            final TileSet llllIlllIlIIll = this.map.findTileSet(llllIlllIIllll);
            this.data[llllIlllIIllIl][llllIlllIlIIII][0] = llllIlllIlIIll.index;
            this.data[llllIlllIIllIl][llllIlllIlIIII][1] = llllIlllIIllll - llllIlllIlIIll.firstGID;
            this.data[llllIlllIIllIl][llllIlllIlIIII][2] = llllIlllIIllll;
        }
    }
    
    static {
        Layer.baseCodes = new byte[256];
        for (int llllIlIllllIll = 0; llllIlIllllIll < 256; ++llllIlIllllIll) {
            Layer.baseCodes[llllIlIllllIll] = -1;
        }
        for (int llllIlIllllIlI = 65; llllIlIllllIlI <= 90; ++llllIlIllllIlI) {
            Layer.baseCodes[llllIlIllllIlI] = (byte)(llllIlIllllIlI - 65);
        }
        for (int llllIlIllllIIl = 97; llllIlIllllIIl <= 122; ++llllIlIllllIIl) {
            Layer.baseCodes[llllIlIllllIIl] = (byte)(26 + llllIlIllllIIl - 97);
        }
        for (int llllIlIllllIII = 48; llllIlIllllIII <= 57; ++llllIlIllllIII) {
            Layer.baseCodes[llllIlIllllIII] = (byte)(52 + llllIlIllllIII - 48);
        }
        Layer.baseCodes[43] = 62;
        Layer.baseCodes[47] = 63;
    }
}
