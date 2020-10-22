package org.newdawn.slick;

import org.newdawn.slick.util.*;
import java.io.*;
import java.util.*;
import org.newdawn.slick.opengl.renderer.*;

public class AngelCodeFont implements Font
{
    private /* synthetic */ CharDef[] chars;
    private /* synthetic */ DisplayList eldestDisplayList;
    private /* synthetic */ int lineHeight;
    private /* synthetic */ int eldestDisplayListID;
    private final /* synthetic */ LinkedHashMap displayLists;
    private /* synthetic */ int baseDisplayListID;
    private static /* synthetic */ SGL GL;
    private /* synthetic */ boolean displayListCaching;
    private /* synthetic */ Image fontImage;
    
    private CharDef parseChar(final String lIIlllllllIlII) throws SlickException {
        final CharDef lIIlllllllIIll = new CharDef();
        final StringTokenizer lIIlllllllIIlI = new StringTokenizer(lIIlllllllIlII, " =");
        lIIlllllllIIlI.nextToken();
        lIIlllllllIIlI.nextToken();
        lIIlllllllIIll.id = Short.parseShort(lIIlllllllIIlI.nextToken());
        if (lIIlllllllIIll.id < 0) {
            return null;
        }
        if (lIIlllllllIIll.id > 255) {
            throw new SlickException(String.valueOf(new StringBuilder().append("Invalid character '").append(lIIlllllllIIll.id).append("': AngelCodeFont does not support characters above ").append(255)));
        }
        lIIlllllllIIlI.nextToken();
        lIIlllllllIIll.x = Short.parseShort(lIIlllllllIIlI.nextToken());
        lIIlllllllIIlI.nextToken();
        lIIlllllllIIll.y = Short.parseShort(lIIlllllllIIlI.nextToken());
        lIIlllllllIIlI.nextToken();
        lIIlllllllIIll.width = Short.parseShort(lIIlllllllIIlI.nextToken());
        lIIlllllllIIlI.nextToken();
        lIIlllllllIIll.height = Short.parseShort(lIIlllllllIIlI.nextToken());
        lIIlllllllIIlI.nextToken();
        lIIlllllllIIll.xoffset = Short.parseShort(lIIlllllllIIlI.nextToken());
        lIIlllllllIIlI.nextToken();
        lIIlllllllIIll.yoffset = Short.parseShort(lIIlllllllIIlI.nextToken());
        lIIlllllllIIlI.nextToken();
        lIIlllllllIIll.xadvance = Short.parseShort(lIIlllllllIIlI.nextToken());
        lIIlllllllIIll.init();
        if (lIIlllllllIIll.id != 32) {
            this.lineHeight = Math.max(lIIlllllllIIll.height + lIIlllllllIIll.yoffset, this.lineHeight);
        }
        return lIIlllllllIIll;
    }
    
    public AngelCodeFont(final String lIlIIIIIlllIlI, final InputStream lIlIIIIIlllIIl, final InputStream lIlIIIIIlllIII, final boolean lIlIIIIIllllII) throws SlickException {
        this.displayListCaching = true;
        this.baseDisplayListID = -1;
        this.displayLists = new LinkedHashMap(200, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry lllllllllllllllllllIIlIlllIllIlI) {
                AngelCodeFont.this.eldestDisplayList = lllllllllllllllllllIIlIlllIllIlI.getValue();
                AngelCodeFont.this.eldestDisplayListID = AngelCodeFont.this.eldestDisplayList.id;
                return false;
            }
        };
        this.fontImage = new Image(lIlIIIIIlllIII, lIlIIIIIlllIlI, false);
        this.displayListCaching = lIlIIIIIllllII;
        this.parseFnt(lIlIIIIIlllIIl);
    }
    
    public AngelCodeFont(final String lIlIIIIllIIlII, final Image lIlIIIIllIIIll, final boolean lIlIIIIlIllllI) throws SlickException {
        this.displayListCaching = true;
        this.baseDisplayListID = -1;
        this.displayLists = new LinkedHashMap(200, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry lllllllllllllllllllIIlIlllIllIlI) {
                AngelCodeFont.this.eldestDisplayList = lllllllllllllllllllIIlIlllIllIlI.getValue();
                AngelCodeFont.this.eldestDisplayListID = AngelCodeFont.this.eldestDisplayList.id;
                return false;
            }
        };
        this.fontImage = lIlIIIIllIIIll;
        this.displayListCaching = lIlIIIIlIllllI;
        this.parseFnt(ResourceLoader.getResourceAsStream(lIlIIIIllIIlII));
    }
    
    private void render(final String lIIllllIlIIIII, final int lIIllllIlIIlll, final int lIIllllIlIIllI) {
        AngelCodeFont.GL.glBegin(7);
        int lIIllllIlIIlIl = 0;
        int lIIllllIlIIlII = 0;
        CharDef lIIllllIlIIIll = null;
        final char[] lIIllllIlIIIlI = lIIllllIlIIIII.toCharArray();
        for (int lIIllllIlIlIlI = 0; lIIllllIlIlIlI < lIIllllIlIIIlI.length; ++lIIllllIlIlIlI) {
            final int lIIllllIlIllII = lIIllllIlIIIlI[lIIllllIlIlIlI];
            if (lIIllllIlIllII == 10) {
                lIIllllIlIIlIl = 0;
                lIIllllIlIIlII += this.getLineHeight();
            }
            else if (lIIllllIlIllII < this.chars.length) {
                final CharDef lIIllllIlIlIll = this.chars[lIIllllIlIllII];
                if (lIIllllIlIlIll != null) {
                    if (lIIllllIlIIIll != null) {
                        lIIllllIlIIlIl += lIIllllIlIIIll.getKerning(lIIllllIlIllII);
                    }
                    lIIllllIlIIIll = lIIllllIlIlIll;
                    if (lIIllllIlIlIlI >= lIIllllIlIIlll && lIIllllIlIlIlI <= lIIllllIlIIllI) {
                        lIIllllIlIlIll.draw((float)lIIllllIlIIlIl, (float)lIIllllIlIIlII);
                    }
                    lIIllllIlIIlIl += lIIllllIlIlIll.xadvance;
                }
            }
        }
        AngelCodeFont.GL.glEnd();
    }
    
    public AngelCodeFont(final String lIlIIIIlIIllII, final InputStream lIlIIIIlIIlIll, final InputStream lIlIIIIlIIIllI) throws SlickException {
        this.displayListCaching = true;
        this.baseDisplayListID = -1;
        this.displayLists = new LinkedHashMap(200, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry lllllllllllllllllllIIlIlllIllIlI) {
                AngelCodeFont.this.eldestDisplayList = lllllllllllllllllllIIlIlllIllIlI.getValue();
                AngelCodeFont.this.eldestDisplayListID = AngelCodeFont.this.eldestDisplayList.id;
                return false;
            }
        };
        this.fontImage = new Image(lIlIIIIlIIIllI, lIlIIIIlIIllII, false);
        this.parseFnt(lIlIIIIlIIlIll);
    }
    
    public AngelCodeFont(final String lIlIIIIllIlllI, final String lIlIIIIllIllIl) throws SlickException {
        this.displayListCaching = true;
        this.baseDisplayListID = -1;
        this.displayLists = new LinkedHashMap(200, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry lllllllllllllllllllIIlIlllIllIlI) {
                AngelCodeFont.this.eldestDisplayList = lllllllllllllllllllIIlIlllIllIlI.getValue();
                AngelCodeFont.this.eldestDisplayListID = AngelCodeFont.this.eldestDisplayList.id;
                return false;
            }
        };
        this.fontImage = new Image(lIlIIIIllIllIl);
        this.parseFnt(ResourceLoader.getResourceAsStream(lIlIIIIllIlllI));
    }
    
    @Override
    public void drawString(final float lIIlllllIIIllI, final float lIIlllllIIIlIl, final String lIIlllllIIIlII, final Color lIIllllIllllII, final int lIIllllIlllIll, final int lIIlllllIIIIIl) {
        this.fontImage.bind();
        lIIllllIllllII.bind();
        AngelCodeFont.GL.glTranslatef(lIIlllllIIIllI, lIIlllllIIIlIl, 0.0f);
        if (this.displayListCaching && lIIllllIlllIll == 0 && lIIlllllIIIIIl == lIIlllllIIIlII.length() - 1) {
            DisplayList lIIlllllIIlIII = this.displayLists.get(lIIlllllIIIlII);
            if (lIIlllllIIlIII != null) {
                AngelCodeFont.GL.glCallList(lIIlllllIIlIII.id);
            }
            else {
                lIIlllllIIlIII = new DisplayList();
                lIIlllllIIlIII.text = lIIlllllIIIlII;
                final int lIIlllllIIlIIl = this.displayLists.size();
                if (lIIlllllIIlIIl < 200) {
                    lIIlllllIIlIII.id = this.baseDisplayListID + lIIlllllIIlIIl;
                }
                else {
                    lIIlllllIIlIII.id = this.eldestDisplayListID;
                    this.displayLists.remove(this.eldestDisplayList.text);
                }
                this.displayLists.put(lIIlllllIIIlII, lIIlllllIIlIII);
                AngelCodeFont.GL.glNewList(lIIlllllIIlIII.id, 4865);
                this.render(lIIlllllIIIlII, lIIllllIlllIll, lIIlllllIIIIIl);
                AngelCodeFont.GL.glEndList();
            }
        }
        else {
            this.render(lIIlllllIIIlII, lIIllllIlllIll, lIIlllllIIIIIl);
        }
        AngelCodeFont.GL.glTranslatef(-lIIlllllIIIllI, -lIIlllllIIIlIl, 0.0f);
    }
    
    public int getYOffset(final String lIIllllIIIIlIl) {
        DisplayList lIIllllIIIlIIl = null;
        if (this.displayListCaching) {
            lIIllllIIIlIIl = this.displayLists.get(lIIllllIIIIlIl);
            if (lIIllllIIIlIIl != null && lIIllllIIIlIIl.yOffset != null) {
                return lIIllllIIIlIIl.yOffset;
            }
        }
        int lIIllllIIIlIII = lIIllllIIIIlIl.indexOf(10);
        if (lIIllllIIIlIII == -1) {
            lIIllllIIIlIII = lIIllllIIIIlIl.length();
        }
        int lIIllllIIIIlll = 10000;
        for (int lIIllllIIIllII = 0; lIIllllIIIllII < lIIllllIIIlIII; ++lIIllllIIIllII) {
            final int lIIllllIIIlllI = lIIllllIIIIlIl.charAt(lIIllllIIIllII);
            final CharDef lIIllllIIIllIl = this.chars[lIIllllIIIlllI];
            if (lIIllllIIIllIl != null) {
                lIIllllIIIIlll = Math.min(lIIllllIIIllIl.yoffset, lIIllllIIIIlll);
            }
        }
        if (lIIllllIIIlIIl != null) {
            lIIllllIIIlIIl.yOffset = new Short((short)lIIllllIIIIlll);
        }
        return lIIllllIIIIlll;
    }
    
    private void parseFnt(final InputStream lIlIIIIIIIlIIl) throws SlickException {
        if (this.displayListCaching) {
            this.baseDisplayListID = AngelCodeFont.GL.glGenLists(200);
            if (this.baseDisplayListID == 0) {
                this.displayListCaching = false;
            }
        }
        try {
            final BufferedReader lIlIIIIIIlIlIl = new BufferedReader(new InputStreamReader(lIlIIIIIIIlIIl));
            final String lIlIIIIIIlIlII = lIlIIIIIIlIlIl.readLine();
            final String lIlIIIIIIlIIll = lIlIIIIIIlIlIl.readLine();
            final String lIlIIIIIIlIIlI = lIlIIIIIIlIlIl.readLine();
            final Map lIlIIIIIIlIIIl = new HashMap(64);
            final List lIlIIIIIIlIIII = new ArrayList(255);
            int lIlIIIIIIIllll = 0;
            boolean lIlIIIIIIIlllI = false;
            while (!lIlIIIIIIIlllI) {
                final String lIlIIIIIIlllll = lIlIIIIIIlIlIl.readLine();
                if (lIlIIIIIIlllll == null) {
                    lIlIIIIIIIlllI = true;
                }
                else {
                    if (!lIlIIIIIIlllll.startsWith("chars c")) {
                        if (lIlIIIIIIlllll.startsWith("char")) {
                            final CharDef lIlIIIIIlIIlIl = this.parseChar(lIlIIIIIIlllll);
                            if (lIlIIIIIlIIlIl != null) {
                                lIlIIIIIIIllll = Math.max(lIlIIIIIIIllll, lIlIIIIIlIIlIl.id);
                                lIlIIIIIIlIIII.add(lIlIIIIIlIIlIl);
                            }
                        }
                    }
                    if (lIlIIIIIIlllll.startsWith("kernings c")) {
                        continue;
                    }
                    if (!lIlIIIIIIlllll.startsWith("kerning")) {
                        continue;
                    }
                    final StringTokenizer lIlIIIIIlIIlII = new StringTokenizer(lIlIIIIIIlllll, " =");
                    lIlIIIIIlIIlII.nextToken();
                    lIlIIIIIlIIlII.nextToken();
                    final short lIlIIIIIlIIIll = Short.parseShort(lIlIIIIIlIIlII.nextToken());
                    lIlIIIIIlIIlII.nextToken();
                    final int lIlIIIIIlIIIlI = Integer.parseInt(lIlIIIIIlIIlII.nextToken());
                    lIlIIIIIlIIlII.nextToken();
                    final int lIlIIIIIlIIIIl = Integer.parseInt(lIlIIIIIlIIlII.nextToken());
                    List lIlIIIIIlIIIII = lIlIIIIIIlIIIl.get(new Short(lIlIIIIIlIIIll));
                    if (lIlIIIIIlIIIII == null) {
                        lIlIIIIIlIIIII = new ArrayList();
                        lIlIIIIIIlIIIl.put(new Short(lIlIIIIIlIIIll), lIlIIIIIlIIIII);
                    }
                    lIlIIIIIlIIIII.add(new Short((short)(lIlIIIIIlIIIIl << 8 | lIlIIIIIlIIIlI)));
                }
            }
            this.chars = new CharDef[lIlIIIIIIIllll + 1];
            for (final CharDef lIlIIIIIIllllI : lIlIIIIIIlIIII) {
                this.chars[lIlIIIIIIllllI.id] = lIlIIIIIIllllI;
            }
            for (final Map.Entry lIlIIIIIIllIll : lIlIIIIIIlIIIl.entrySet()) {
                final short lIlIIIIIIllIlI = lIlIIIIIIllIll.getKey();
                final List lIlIIIIIIllIIl = lIlIIIIIIllIll.getValue();
                final short[] lIlIIIIIIllIII = new short[lIlIIIIIIllIIl.size()];
                int lIlIIIIIIlIlll = 0;
                final Iterator lIlIIIIIIlllII = lIlIIIIIIllIIl.iterator();
                while (lIlIIIIIIlllII.hasNext()) {
                    lIlIIIIIIllIII[lIlIIIIIIlIlll] = lIlIIIIIIlllII.next();
                    ++lIlIIIIIIlIlll;
                }
                this.chars[lIlIIIIIIllIlI].kerning = lIlIIIIIIllIII;
            }
        }
        catch (IOException lIlIIIIIIIllIl) {
            Log.error(lIlIIIIIIIllIl);
            throw new SlickException(String.valueOf(new StringBuilder().append("Failed to parse font file: ").append(lIlIIIIIIIlIIl)));
        }
    }
    
    @Override
    public int getHeight(final String lIIlllIlllIIlI) {
        DisplayList lIIlllIlllIIIl = null;
        if (this.displayListCaching) {
            lIIlllIlllIIIl = this.displayLists.get(lIIlllIlllIIlI);
            if (lIIlllIlllIIIl != null && lIIlllIlllIIIl.height != null) {
                return lIIlllIlllIIIl.height;
            }
        }
        int lIIlllIlllIIII = 0;
        int lIIlllIllIllll = 0;
        for (int lIIlllIlllIlII = 0; lIIlllIlllIlII < lIIlllIlllIIlI.length(); ++lIIlllIlllIlII) {
            final int lIIlllIlllIllI = lIIlllIlllIIlI.charAt(lIIlllIlllIlII);
            if (lIIlllIlllIllI == 10) {
                ++lIIlllIlllIIII;
                lIIlllIllIllll = 0;
            }
            else if (lIIlllIlllIllI != 32) {
                final CharDef lIIlllIlllIlIl = this.chars[lIIlllIlllIllI];
                if (lIIlllIlllIlIl != null) {
                    lIIlllIllIllll = Math.max(lIIlllIlllIlIl.height + lIIlllIlllIlIl.yoffset, lIIlllIllIllll);
                }
            }
        }
        lIIlllIllIllll += lIIlllIlllIIII * this.getLineHeight();
        if (lIIlllIlllIIIl != null) {
            lIIlllIlllIIIl.height = new Short((short)lIIlllIllIllll);
        }
        return lIIlllIllIllll;
    }
    
    @Override
    public void drawString(final float lIIllllllIlIII, final float lIIllllllIIlll, final String lIIllllllIIIlI) {
        this.drawString(lIIllllllIlIII, lIIllllllIIlll, lIIllllllIIIlI, Color.white);
    }
    
    @Override
    public int getLineHeight() {
        return this.lineHeight;
    }
    
    @Override
    public void drawString(final float lIIlllllIlIllI, final float lIIlllllIllIlI, final String lIIlllllIlIlII, final Color lIIlllllIllIII) {
        this.drawString(lIIlllllIlIllI, lIIlllllIllIlI, lIIlllllIlIlII, lIIlllllIllIII, 0, lIIlllllIlIlII.length() - 1);
    }
    
    @Override
    public int getWidth(final String lIIlllIlIlIlll) {
        DisplayList lIIlllIlIlIllI = null;
        if (this.displayListCaching) {
            lIIlllIlIlIllI = this.displayLists.get(lIIlllIlIlIlll);
            if (lIIlllIlIlIllI != null && lIIlllIlIlIllI.width != null) {
                return lIIlllIlIlIllI.width;
            }
        }
        int lIIlllIlIlIlIl = 0;
        int lIIlllIlIlIlII = 0;
        CharDef lIIlllIlIlIIll = null;
        for (int lIIlllIlIllIlI = 0, lIIlllIlIllIIl = lIIlllIlIlIlll.length(); lIIlllIlIllIlI < lIIlllIlIllIIl; ++lIIlllIlIllIlI) {
            final int lIIlllIlIlllII = lIIlllIlIlIlll.charAt(lIIlllIlIllIlI);
            if (lIIlllIlIlllII == 10) {
                lIIlllIlIlIlII = 0;
            }
            else if (lIIlllIlIlllII < this.chars.length) {
                final CharDef lIIlllIlIllIll = this.chars[lIIlllIlIlllII];
                if (lIIlllIlIllIll != null) {
                    if (lIIlllIlIlIIll != null) {
                        lIIlllIlIlIlII += lIIlllIlIlIIll.getKerning(lIIlllIlIlllII);
                    }
                    lIIlllIlIlIIll = lIIlllIlIllIll;
                    if (lIIlllIlIllIlI < lIIlllIlIllIIl - 1) {
                        lIIlllIlIlIlII += lIIlllIlIllIll.xadvance;
                    }
                    else {
                        lIIlllIlIlIlII += lIIlllIlIllIll.width;
                    }
                    lIIlllIlIlIlIl = Math.max(lIIlllIlIlIlIl, lIIlllIlIlIlII);
                }
            }
        }
        if (lIIlllIlIlIllI != null) {
            lIIlllIlIlIllI.width = new Short((short)lIIlllIlIlIlIl);
        }
        return lIIlllIlIlIlIl;
    }
    
    public AngelCodeFont(final String lIlIIIIlllIlll, final Image lIlIIIIlllIllI) throws SlickException {
        this.displayListCaching = true;
        this.baseDisplayListID = -1;
        this.displayLists = new LinkedHashMap(200, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry lllllllllllllllllllIIlIlllIllIlI) {
                AngelCodeFont.this.eldestDisplayList = lllllllllllllllllllIIlIlllIllIlI.getValue();
                AngelCodeFont.this.eldestDisplayListID = AngelCodeFont.this.eldestDisplayList.id;
                return false;
            }
        };
        this.fontImage = lIlIIIIlllIllI;
        this.parseFnt(ResourceLoader.getResourceAsStream(lIlIIIIlllIlll));
    }
    
    public AngelCodeFont(final String lIlIIIIlIlIlII, final String lIlIIIIlIlIIll, final boolean lIlIIIIlIlIllI) throws SlickException {
        this.displayListCaching = true;
        this.baseDisplayListID = -1;
        this.displayLists = new LinkedHashMap(200, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry lllllllllllllllllllIIlIlllIllIlI) {
                AngelCodeFont.this.eldestDisplayList = lllllllllllllllllllIIlIlllIllIlI.getValue();
                AngelCodeFont.this.eldestDisplayListID = AngelCodeFont.this.eldestDisplayList.id;
                return false;
            }
        };
        this.fontImage = new Image(lIlIIIIlIlIIll);
        this.displayListCaching = lIlIIIIlIlIllI;
        this.parseFnt(ResourceLoader.getResourceAsStream(lIlIIIIlIlIlII));
    }
    
    static {
        DISPLAY_LIST_CACHE_SIZE = 200;
        MAX_CHAR = 255;
        AngelCodeFont.GL = Renderer.get();
    }
    
    private class CharDef
    {
        public /* synthetic */ short xoffset;
        public /* synthetic */ short id;
        public /* synthetic */ short[] kerning;
        public /* synthetic */ short yoffset;
        public /* synthetic */ short x;
        public /* synthetic */ short width;
        public /* synthetic */ short xadvance;
        public /* synthetic */ short height;
        public /* synthetic */ short y;
        public /* synthetic */ Image image;
        
        @Override
        public String toString() {
            return String.valueOf(new StringBuilder().append("[CharDef id=").append(this.id).append(" x=").append(this.x).append(" y=").append(this.y).append("]"));
        }
        
        public void init() {
            this.image = AngelCodeFont.this.fontImage.getSubImage(this.x, this.y, this.width, this.height);
        }
        
        public int getKerning(final int lllllllllllllllllIllIIlIIIIllIlI) {
            if (this.kerning == null) {
                return 0;
            }
            int lllllllllllllllllIllIIlIIIIlllIl = 0;
            int lllllllllllllllllIllIIlIIIIlllII = this.kerning.length - 1;
            while (lllllllllllllllllIllIIlIIIIlllIl <= lllllllllllllllllIllIIlIIIIlllII) {
                final int lllllllllllllllllIllIIlIIIlIIIlI = lllllllllllllllllIllIIlIIIIlllIl + lllllllllllllllllIllIIlIIIIlllII >>> 1;
                final int lllllllllllllllllIllIIlIIIlIIIIl = this.kerning[lllllllllllllllllIllIIlIIIlIIIlI];
                final int lllllllllllllllllIllIIlIIIlIIIII = lllllllllllllllllIllIIlIIIlIIIIl & 0xFF;
                if (lllllllllllllllllIllIIlIIIlIIIII < lllllllllllllllllIllIIlIIIIllIlI) {
                    lllllllllllllllllIllIIlIIIIlllIl = lllllllllllllllllIllIIlIIIlIIIlI + 1;
                }
                else {
                    if (lllllllllllllllllIllIIlIIIlIIIII <= lllllllllllllllllIllIIlIIIIllIlI) {
                        return lllllllllllllllllIllIIlIIIlIIIIl >> 8;
                    }
                    lllllllllllllllllIllIIlIIIIlllII = lllllllllllllllllIllIIlIIIlIIIlI - 1;
                }
            }
            return 0;
        }
        
        public void draw(final float lllllllllllllllllIllIIlIIIlIlIll, final float lllllllllllllllllIllIIlIIIlIllIl) {
            this.image.drawEmbedded(lllllllllllllllllIllIIlIIIlIlIll + this.xoffset, lllllllllllllllllIllIIlIIIlIllIl + this.yoffset, this.width, this.height);
        }
    }
    
    private static class DisplayList
    {
        /* synthetic */ String text;
        /* synthetic */ Short width;
        /* synthetic */ int id;
        /* synthetic */ Short yOffset;
        /* synthetic */ Short height;
    }
}
