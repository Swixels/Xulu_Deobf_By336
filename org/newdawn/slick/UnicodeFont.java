package org.newdawn.slick;

import org.newdawn.slick.opengl.renderer.*;
import org.newdawn.slick.util.*;
import java.io.*;
import java.lang.reflect.*;
import org.newdawn.slick.font.*;
import java.awt.font.*;
import java.text.*;
import java.awt.*;
import java.util.*;
import org.newdawn.slick.opengl.*;

public class UnicodeFont implements Font
{
    protected /* synthetic */ boolean displayListCaching;
    protected /* synthetic */ java.awt.Font font;
    protected /* synthetic */ int descent;
    protected final /* synthetic */ List glyphPages;
    protected static final /* synthetic */ SGL GL;
    protected /* synthetic */ int ascent;
    protected /* synthetic */ int paddingBottom;
    protected /* synthetic */ Glyph missingGlyph;
    protected /* synthetic */ int paddingLeft;
    protected /* synthetic */ int spaceWidth;
    protected /* synthetic */ int glyphPageWidth;
    protected /* synthetic */ int paddingAdvanceX;
    protected final /* synthetic */ List effects;
    protected static final /* synthetic */ DisplayList EMPTY_DISPLAY_LIST;
    protected final /* synthetic */ LinkedHashMap displayLists;
    protected /* synthetic */ String ttfFileRef;
    protected /* synthetic */ int glyphPageHeight;
    protected /* synthetic */ int paddingRight;
    protected final /* synthetic */ Glyph[][] glyphs;
    protected /* synthetic */ int eldestDisplayListID;
    private static final /* synthetic */ Comparator heightComparator;
    protected final /* synthetic */ List queuedGlyphs;
    protected /* synthetic */ int paddingAdvanceY;
    protected /* synthetic */ int paddingTop;
    protected /* synthetic */ int baseDisplayListID;
    protected /* synthetic */ int leading;
    
    public int getPaddingRight() {
        return this.paddingRight;
    }
    
    static {
        MAX_GLYPH_CODE = 1114111;
        DISPLAY_LIST_CACHE_SIZE = 200;
        PAGE_SIZE = 512;
        PAGES = 2175;
        GL = Renderer.get();
        EMPTY_DISPLAY_LIST = new DisplayList();
        heightComparator = new Comparator() {
            @Override
            public int compare(final Object llllllllllllllllIllIllIlIIIllIll, final Object llllllllllllllllIllIllIlIIIllIlI) {
                return ((Glyph)llllllllllllllllIllIllIlIIIllIll).getHeight() - ((Glyph)llllllllllllllllIllIllIlIIIllIlI).getHeight();
            }
        };
    }
    
    public void setPaddingTop(final int lllllllllllllllllllIlIllllllIIII) {
        this.paddingTop = lllllllllllllllllllIlIllllllIIII;
    }
    
    public UnicodeFont(final String lllllllllllllllllllIllIlllIIIlIl, final HieroSettings lllllllllllllllllllIllIlllIIIIII) throws SlickException {
        this.glyphs = new Glyph[2175][];
        this.glyphPages = new ArrayList();
        this.queuedGlyphs = new ArrayList(256);
        this.effects = new ArrayList();
        this.glyphPageWidth = 512;
        this.glyphPageHeight = 512;
        this.displayListCaching = true;
        this.baseDisplayListID = -1;
        this.displayLists = new LinkedHashMap(200, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry llllllllllllllllIlIlIllIIlIIlIII) {
                final DisplayList llllllllllllllllIlIlIllIIlIIIlll = llllllllllllllllIlIlIllIIlIIlIII.getValue();
                if (llllllllllllllllIlIlIllIIlIIIlll != null) {
                    UnicodeFont.this.eldestDisplayListID = llllllllllllllllIlIlIllIIlIIIlll.id;
                }
                return this.size() > 200;
            }
        };
        this.ttfFileRef = lllllllllllllllllllIllIlllIIIlIl;
        final java.awt.Font lllllllllllllllllllIllIlllIIIIll = createFont(lllllllllllllllllllIllIlllIIIlIl);
        this.initializeFont(lllllllllllllllllllIllIlllIIIIll, lllllllllllllllllllIllIlllIIIIII.getFontSize(), lllllllllllllllllllIllIlllIIIIII.isBold(), lllllllllllllllllllIllIlllIIIIII.isItalic());
        this.loadSettings(lllllllllllllllllllIllIlllIIIIII);
    }
    
    public int getPaddingTop() {
        return this.paddingTop;
    }
    
    public int getGlyphPageWidth() {
        return this.glyphPageWidth;
    }
    
    public List getEffects() {
        return this.effects;
    }
    
    public java.awt.Font getFont() {
        return this.font;
    }
    
    public int getPaddingAdvanceX() {
        return this.paddingAdvanceX;
    }
    
    public UnicodeFont(final java.awt.Font lllllllllllllllllllIllIllIIIllII, final int lllllllllllllllllllIllIllIIlIIII, final boolean lllllllllllllllllllIllIllIIIllll, final boolean lllllllllllllllllllIllIllIIIlllI) {
        this.glyphs = new Glyph[2175][];
        this.glyphPages = new ArrayList();
        this.queuedGlyphs = new ArrayList(256);
        this.effects = new ArrayList();
        this.glyphPageWidth = 512;
        this.glyphPageHeight = 512;
        this.displayListCaching = true;
        this.baseDisplayListID = -1;
        this.displayLists = new LinkedHashMap(200, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry llllllllllllllllIlIlIllIIlIIlIII) {
                final DisplayList llllllllllllllllIlIlIllIIlIIIlll = llllllllllllllllIlIlIllIIlIIlIII.getValue();
                if (llllllllllllllllIlIlIllIIlIIIlll != null) {
                    UnicodeFont.this.eldestDisplayListID = llllllllllllllllIlIlIllIIlIIIlll.id;
                }
                return this.size() > 200;
            }
        };
        this.initializeFont(lllllllllllllllllllIllIllIIIllII, lllllllllllllllllllIllIllIIlIIII, lllllllllllllllllllIllIllIIIllll, lllllllllllllllllllIllIllIIIlllI);
    }
    
    public int getGlyphPageHeight() {
        return this.glyphPageHeight;
    }
    
    public List getGlyphPages() {
        return this.glyphPages;
    }
    
    public void addGlyphs(final int lllllllllllllllllllIllIlIllIIIIl, final int lllllllllllllllllllIllIlIlIlllIl) {
        for (int lllllllllllllllllllIllIlIllIIIll = lllllllllllllllllllIllIlIllIIIIl; lllllllllllllllllllIllIlIllIIIll <= lllllllllllllllllllIllIlIlIlllIl; ++lllllllllllllllllllIllIlIllIIIll) {
            this.addGlyphs(new String(Character.toChars(lllllllllllllllllllIllIlIllIIIll)));
        }
    }
    
    private static java.awt.Font createFont(final String lllllllllllllllllllIllIlllIlIllI) throws SlickException {
        try {
            return java.awt.Font.createFont(0, ResourceLoader.getResourceAsStream(lllllllllllllllllllIllIlllIlIllI));
        }
        catch (FontFormatException lllllllllllllllllllIllIlllIllIII) {
            throw new SlickException(String.valueOf(new StringBuilder().append("Invalid font: ").append(lllllllllllllllllllIllIlllIlIllI)), lllllllllllllllllllIllIlllIllIII);
        }
        catch (IOException lllllllllllllllllllIllIlllIlIlll) {
            throw new SlickException(String.valueOf(new StringBuilder().append("Error reading font: ").append(lllllllllllllllllllIllIlllIlIllI)), lllllllllllllllllllIllIlllIlIlll);
        }
    }
    
    public void setGlyphPageHeight(final int lllllllllllllllllllIlIlllIlIIlIl) {
        this.glyphPageHeight = lllllllllllllllllllIlIlllIlIIlIl;
    }
    
    public String getFontFile() {
        if (this.ttfFileRef == null) {
            try {
                final Object lllllllllllllllllllIlIlllIIlIIII = Class.forName("sun.font.FontManager").getDeclaredMethod("getFont2D", java.awt.Font.class).invoke(null, this.font);
                final Field lllllllllllllllllllIlIlllIIIllll = Class.forName("sun.font.PhysicalFont").getDeclaredField("platName");
                lllllllllllllllllllIlIlllIIIllll.setAccessible(true);
                this.ttfFileRef = (String)lllllllllllllllllllIlIlllIIIllll.get(lllllllllllllllllllIlIlllIIlIIII);
            }
            catch (Throwable t) {}
            if (this.ttfFileRef == null) {
                this.ttfFileRef = "";
            }
        }
        if (this.ttfFileRef.length() == 0) {
            return null;
        }
        return this.ttfFileRef;
    }
    
    public int getSpaceWidth() {
        return this.spaceWidth;
    }
    
    public int getPaddingAdvanceY() {
        return this.paddingAdvanceY;
    }
    
    public UnicodeFont(final String lllllllllllllllllllIllIlllIIllll, final String lllllllllllllllllllIllIlllIIlllI) throws SlickException {
        this(lllllllllllllllllllIllIlllIIllll, new HieroSettings(lllllllllllllllllllIllIlllIIlllI));
    }
    
    public UnicodeFont(final java.awt.Font lllllllllllllllllllIllIllIIlllll, final HieroSettings lllllllllllllllllllIllIllIIllllI) {
        this.glyphs = new Glyph[2175][];
        this.glyphPages = new ArrayList();
        this.queuedGlyphs = new ArrayList(256);
        this.effects = new ArrayList();
        this.glyphPageWidth = 512;
        this.glyphPageHeight = 512;
        this.displayListCaching = true;
        this.baseDisplayListID = -1;
        this.displayLists = new LinkedHashMap(200, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry llllllllllllllllIlIlIllIIlIIlIII) {
                final DisplayList llllllllllllllllIlIlIllIIlIIIlll = llllllllllllllllIlIlIllIIlIIlIII.getValue();
                if (llllllllllllllllIlIlIllIIlIIIlll != null) {
                    UnicodeFont.this.eldestDisplayListID = llllllllllllllllIlIlIllIIlIIIlll.id;
                }
                return this.size() > 200;
            }
        };
        this.initializeFont(lllllllllllllllllllIllIllIIlllll, lllllllllllllllllllIllIllIIllllI.getFontSize(), lllllllllllllllllllIllIllIIllllI.isBold(), lllllllllllllllllllIllIllIIllllI.isItalic());
        this.loadSettings(lllllllllllllllllllIllIllIIllllI);
    }
    
    protected Rectangle getGlyphBounds(final GlyphVector lllllllllllllllllllIllIIIlIlllII, final int lllllllllllllllllllIllIIIllIIIII, final int lllllllllllllllllllIllIIIlIllIlI) {
        final Rectangle lllllllllllllllllllIllIIIlIllllI = lllllllllllllllllllIllIIIlIlllII.getGlyphPixelBounds(lllllllllllllllllllIllIIIllIIIII, GlyphPage.renderContext, 0.0f, 0.0f);
        if (lllllllllllllllllllIllIIIlIllIlI == 32) {
            lllllllllllllllllllIllIIIlIllllI.width = this.spaceWidth;
        }
        return lllllllllllllllllllIllIIIlIllllI;
    }
    
    public void destroy() {
        this.clearGlyphs();
    }
    
    public void setPaddingAdvanceY(final int lllllllllllllllllllIlIllllIIIIIl) {
        this.paddingAdvanceY = lllllllllllllllllllIlIllllIIIIIl;
    }
    
    public boolean loadGlyphs() throws SlickException {
        return this.loadGlyphs(-1);
    }
    
    public int getYOffset(String lllllllllllllllllllIlIllllllllll) {
        if (lllllllllllllllllllIlIllllllllll == null) {
            throw new IllegalArgumentException("text cannot be null.");
        }
        DisplayList lllllllllllllllllllIllIIIIIIIlIl = null;
        if (this.displayListCaching) {
            lllllllllllllllllllIllIIIIIIIlIl = this.displayLists.get(lllllllllllllllllllIlIllllllllll);
            if (lllllllllllllllllllIllIIIIIIIlIl != null && lllllllllllllllllllIllIIIIIIIlIl.yOffset != null) {
                return lllllllllllllllllllIllIIIIIIIlIl.yOffset;
            }
        }
        final int lllllllllllllllllllIllIIIIIIIlII = ((String)lllllllllllllllllllIlIllllllllll).indexOf(10);
        if (lllllllllllllllllllIllIIIIIIIlII != -1) {
            lllllllllllllllllllIlIllllllllll = ((String)lllllllllllllllllllIlIllllllllll).substring(0, lllllllllllllllllllIllIIIIIIIlII);
        }
        final char[] lllllllllllllllllllIllIIIIIIIIll = ((String)lllllllllllllllllllIlIllllllllll).toCharArray();
        final GlyphVector lllllllllllllllllllIllIIIIIIIIlI = this.font.layoutGlyphVector(GlyphPage.renderContext, lllllllllllllllllllIllIIIIIIIIll, 0, lllllllllllllllllllIllIIIIIIIIll.length, 0);
        final int lllllllllllllllllllIllIIIIIIIIIl = this.ascent + lllllllllllllllllllIllIIIIIIIIlI.getPixelBounds(null, 0.0f, 0.0f).y;
        if (lllllllllllllllllllIllIIIIIIIlIl != null) {
            lllllllllllllllllllIllIIIIIIIlIl.yOffset = new Short((short)lllllllllllllllllllIllIIIIIIIIIl);
        }
        return lllllllllllllllllllIllIIIIIIIIIl;
    }
    
    private void loadSettings(final HieroSettings lllllllllllllllllllIllIlIllIlIlI) {
        this.paddingTop = lllllllllllllllllllIllIlIllIlIlI.getPaddingTop();
        this.paddingLeft = lllllllllllllllllllIllIlIllIlIlI.getPaddingLeft();
        this.paddingBottom = lllllllllllllllllllIllIlIllIlIlI.getPaddingBottom();
        this.paddingRight = lllllllllllllllllllIllIlIllIlIlI.getPaddingRight();
        this.paddingAdvanceX = lllllllllllllllllllIllIlIllIlIlI.getPaddingAdvanceX();
        this.paddingAdvanceY = lllllllllllllllllllIllIlIllIlIlI.getPaddingAdvanceY();
        this.glyphPageWidth = lllllllllllllllllllIllIlIllIlIlI.getGlyphPageWidth();
        this.glyphPageHeight = lllllllllllllllllllIllIlIllIlIlI.getGlyphPageHeight();
        this.effects.addAll(lllllllllllllllllllIllIlIllIlIlI.getEffects());
    }
    
    public int getDescent() {
        return this.descent;
    }
    
    public boolean isCaching() {
        return this.displayListCaching;
    }
    
    public int getPaddingBottom() {
        return this.paddingBottom;
    }
    
    public void clearGlyphs() {
        for (int lllllllllllllllllllIllIlIIlIIlII = 0; lllllllllllllllllllIllIlIIlIIlII < 2175; ++lllllllllllllllllllIllIlIIlIIlII) {
            this.glyphs[lllllllllllllllllllIllIlIIlIIlII] = null;
        }
        for (final GlyphPage lllllllllllllllllllIllIlIIlIIIll : this.glyphPages) {
            try {
                lllllllllllllllllllIllIlIIlIIIll.getImage().destroy();
            }
            catch (SlickException ex) {}
        }
        this.glyphPages.clear();
        if (this.baseDisplayListID != -1) {
            UnicodeFont.GL.glDeleteLists(this.baseDisplayListID, this.displayLists.size());
            this.baseDisplayListID = -1;
        }
        this.queuedGlyphs.clear();
        this.missingGlyph = null;
    }
    
    public void setPaddingLeft(final int lllllllllllllllllllIlIlllllIIlIl) {
        this.paddingLeft = lllllllllllllllllllIlIlllllIIlIl;
    }
    
    @Override
    public int getLineHeight() {
        return this.descent + this.ascent + this.leading + this.paddingTop + this.paddingBottom + this.paddingAdvanceY;
    }
    
    public void addNeheGlyphs() {
        this.addGlyphs(32, 128);
    }
    
    public void setPaddingBottom(final int lllllllllllllllllllIlIllllIllllI) {
        this.paddingBottom = lllllllllllllllllllIlIllllIllllI;
    }
    
    public int getPaddingLeft() {
        return this.paddingLeft;
    }
    
    public void addAsciiGlyphs() {
        this.addGlyphs(32, 255);
    }
    
    public UnicodeFont(final java.awt.Font lllllllllllllllllllIllIllIlIlIll, final String lllllllllllllllllllIllIllIlIlIlI) throws SlickException {
        this(lllllllllllllllllllIllIllIlIlIll, new HieroSettings(lllllllllllllllllllIllIllIlIlIlI));
    }
    
    public void addGlyphs(final String lllllllllllllllllllIllIlIlIIlllI) {
        if (lllllllllllllllllllIllIlIlIIlllI == null) {
            throw new IllegalArgumentException("text cannot be null.");
        }
        final char[] lllllllllllllllllllIllIlIlIIllIl = lllllllllllllllllllIllIlIlIIlllI.toCharArray();
        final GlyphVector lllllllllllllllllllIllIlIlIIllII = this.font.layoutGlyphVector(GlyphPage.renderContext, lllllllllllllllllllIllIlIlIIllIl, 0, lllllllllllllllllllIllIlIlIIllIl.length, 0);
        for (int lllllllllllllllllllIllIlIlIlIIIl = 0, lllllllllllllllllllIllIlIlIlIIII = lllllllllllllllllllIllIlIlIIllII.getNumGlyphs(); lllllllllllllllllllIllIlIlIlIIIl < lllllllllllllllllllIllIlIlIlIIII; ++lllllllllllllllllllIllIlIlIlIIIl) {
            final int lllllllllllllllllllIllIlIlIlIIll = lllllllllllllllllllIllIlIlIIlllI.codePointAt(lllllllllllllllllllIllIlIlIIllII.getGlyphCharIndex(lllllllllllllllllllIllIlIlIlIIIl));
            final Rectangle lllllllllllllllllllIllIlIlIlIIlI = this.getGlyphBounds(lllllllllllllllllllIllIlIlIIllII, lllllllllllllllllllIllIlIlIlIIIl, lllllllllllllllllllIllIlIlIlIIll);
            this.getGlyph(lllllllllllllllllllIllIlIlIIllII.getGlyphCode(lllllllllllllllllllIllIlIlIlIIIl), lllllllllllllllllllIllIlIlIlIIll, lllllllllllllllllllIllIlIlIlIIlI, lllllllllllllllllllIllIlIlIIllII, lllllllllllllllllllIllIlIlIlIIIl);
        }
    }
    
    @Override
    public void drawString(final float lllllllllllllllllllIllIIlIIllIll, final float lllllllllllllllllllIllIIlIIllIlI, final String lllllllllllllllllllIllIIlIIlIlIl) {
        this.drawString(lllllllllllllllllllIllIIlIIllIll, lllllllllllllllllllIllIIlIIllIlI, lllllllllllllllllllIllIIlIIlIlIl, Color.white);
    }
    
    public void setDisplayListCaching(final boolean lllllllllllllllllllIlIlllIIlIlII) {
        this.displayListCaching = lllllllllllllllllllIlIlllIIlIlII;
    }
    
    protected Glyph getGlyph(final int lllllllllllllllllllIllIIIllllIlI, final int lllllllllllllllllllIllIIIllIllll, final Rectangle lllllllllllllllllllIllIIIllllIII, final GlyphVector lllllllllllllllllllIllIIIlllIlll, final int lllllllllllllllllllIllIIIllIllII) {
        if (lllllllllllllllllllIllIIIllllIlI < 0 || lllllllllllllllllllIllIIIllllIlI >= 1114111) {
            return new Glyph(lllllllllllllllllllIllIIIllIllll, lllllllllllllllllllIllIIIllllIII, lllllllllllllllllllIllIIIlllIlll, lllllllllllllllllllIllIIIllIllII, this) {
                @Override
                public boolean isMissing() {
                    return true;
                }
            };
        }
        final int lllllllllllllllllllIllIIIlllIlIl = lllllllllllllllllllIllIIIllllIlI / 512;
        final int lllllllllllllllllllIllIIIlllIlII = lllllllllllllllllllIllIIIllllIlI & 0x1FF;
        Glyph lllllllllllllllllllIllIIIlllIIll = null;
        Glyph[] lllllllllllllllllllIllIIIlllIIlI = this.glyphs[lllllllllllllllllllIllIIIlllIlIl];
        if (lllllllllllllllllllIllIIIlllIIlI != null) {
            lllllllllllllllllllIllIIIlllIIll = lllllllllllllllllllIllIIIlllIIlI[lllllllllllllllllllIllIIIlllIlII];
            if (lllllllllllllllllllIllIIIlllIIll != null) {
                return lllllllllllllllllllIllIIIlllIIll;
            }
        }
        else {
            final Glyph[][] glyphs = this.glyphs;
            final int n = lllllllllllllllllllIllIIIlllIlIl;
            final Glyph[] array = new Glyph[512];
            glyphs[n] = array;
            lllllllllllllllllllIllIIIlllIIlI = array;
        }
        final Glyph[] array2 = lllllllllllllllllllIllIIIlllIIlI;
        final int n2 = lllllllllllllllllllIllIIIlllIlII;
        final Glyph glyph = new Glyph(lllllllllllllllllllIllIIIllIllll, lllllllllllllllllllIllIIIllllIII, lllllllllllllllllllIllIIIlllIlll, lllllllllllllllllllIllIIIllIllII, this);
        array2[n2] = glyph;
        lllllllllllllllllllIllIIIlllIIll = glyph;
        this.queuedGlyphs.add(lllllllllllllllllllIllIIIlllIIll);
        return lllllllllllllllllllIllIIIlllIIll;
    }
    
    @Override
    public void drawString(final float lllllllllllllllllllIllIIlIIIlllI, final float lllllllllllllllllllIllIIlIIIllIl, final String lllllllllllllllllllIllIIlIIIllII, final Color lllllllllllllllllllIllIIlIIIIllI) {
        this.drawString(lllllllllllllllllllIllIIlIIIlllI, lllllllllllllllllllIllIIlIIIllIl, lllllllllllllllllllIllIIlIIIllII, lllllllllllllllllllIllIIlIIIIllI, 0, lllllllllllllllllllIllIIlIIIllII.length());
    }
    
    @Override
    public int getHeight(final String lllllllllllllllllllIllIIIIIllIII) {
        if (lllllllllllllllllllIllIIIIIllIII == null) {
            throw new IllegalArgumentException("text cannot be null.");
        }
        if (lllllllllllllllllllIllIIIIIllIII.length() == 0) {
            return 0;
        }
        if (this.displayListCaching) {
            final DisplayList lllllllllllllllllllIllIIIIlIIlIl = this.displayLists.get(lllllllllllllllllllIllIIIIIllIII);
            if (lllllllllllllllllllIllIIIIlIIlIl != null) {
                return lllllllllllllllllllIllIIIIlIIlIl.height;
            }
        }
        final char[] lllllllllllllllllllIllIIIIIlllIl = lllllllllllllllllllIllIIIIIllIII.toCharArray();
        final GlyphVector lllllllllllllllllllIllIIIIIlllII = this.font.layoutGlyphVector(GlyphPage.renderContext, lllllllllllllllllllIllIIIIIlllIl, 0, lllllllllllllllllllIllIIIIIlllIl.length, 0);
        int lllllllllllllllllllIllIIIIIllIll = 0;
        int lllllllllllllllllllIllIIIIIllIlI = 0;
        for (int lllllllllllllllllllIllIIIIlIIIIl = 0, lllllllllllllllllllIllIIIIlIIIII = lllllllllllllllllllIllIIIIIlllII.getNumGlyphs(); lllllllllllllllllllIllIIIIlIIIIl < lllllllllllllllllllIllIIIIlIIIII; ++lllllllllllllllllllIllIIIIlIIIIl) {
            final int lllllllllllllllllllIllIIIIlIIlII = lllllllllllllllllllIllIIIIIlllII.getGlyphCharIndex(lllllllllllllllllllIllIIIIlIIIIl);
            final int lllllllllllllllllllIllIIIIlIIIll = lllllllllllllllllllIllIIIIIllIII.codePointAt(lllllllllllllllllllIllIIIIlIIlII);
            if (lllllllllllllllllllIllIIIIlIIIll != 32) {
                final Rectangle lllllllllllllllllllIllIIIIlIIIlI = this.getGlyphBounds(lllllllllllllllllllIllIIIIIlllII, lllllllllllllllllllIllIIIIlIIIIl, lllllllllllllllllllIllIIIIlIIIll);
                lllllllllllllllllllIllIIIIIllIlI = Math.max(lllllllllllllllllllIllIIIIIllIlI, this.ascent + lllllllllllllllllllIllIIIIlIIIlI.y + lllllllllllllllllllIllIIIIlIIIlI.height);
                if (lllllllllllllllllllIllIIIIlIIIll == 10) {
                    ++lllllllllllllllllllIllIIIIIllIll;
                    lllllllllllllllllllIllIIIIIllIlI = 0;
                }
            }
        }
        return lllllllllllllllllllIllIIIIIllIll * this.getLineHeight() + lllllllllllllllllllIllIIIIIllIlI;
    }
    
    @Override
    public void drawString(final float lllllllllllllllllllIllIIlIllIlll, final float lllllllllllllllllllIllIIlIllIllI, final String lllllllllllllllllllIllIIlIlIlIll, final Color lllllllllllllllllllIllIIlIlIlIIl, final int lllllllllllllllllllIllIIlIllIIlI, final int lllllllllllllllllllIllIIlIllIIIl) {
        this.drawDisplayList(lllllllllllllllllllIllIIlIllIlll, lllllllllllllllllllIllIIlIllIllI, lllllllllllllllllllIllIIlIlIlIll, lllllllllllllllllllIllIIlIlIlIIl, lllllllllllllllllllIllIIlIllIIlI, lllllllllllllllllllIllIIlIllIIIl);
    }
    
    public void setPaddingAdvanceX(final int lllllllllllllllllllIlIllllIIlIlI) {
        this.paddingAdvanceX = lllllllllllllllllllIlIllllIIlIlI;
    }
    
    public void setGlyphPageWidth(final int lllllllllllllllllllIlIlllIlIlllI) {
        this.glyphPageWidth = lllllllllllllllllllIlIlllIlIlllI;
    }
    
    private void initializeFont(final java.awt.Font lllllllllllllllllllIllIlIlllIlIl, final int lllllllllllllllllllIllIlIlllIlII, final boolean lllllllllllllllllllIllIlIlllIIll, final boolean lllllllllllllllllllIllIlIlllIIlI) {
        final Map lllllllllllllllllllIllIlIllllIlI = lllllllllllllllllllIllIlIlllIlIl.getAttributes();
        lllllllllllllllllllIllIlIllllIlI.put(TextAttribute.SIZE, new Float((float)lllllllllllllllllllIllIlIlllIlII));
        lllllllllllllllllllIllIlIllllIlI.put(TextAttribute.WEIGHT, lllllllllllllllllllIllIlIlllIIll ? TextAttribute.WEIGHT_BOLD : TextAttribute.WEIGHT_REGULAR);
        lllllllllllllllllllIllIlIllllIlI.put(TextAttribute.POSTURE, lllllllllllllllllllIllIlIlllIIlI ? TextAttribute.POSTURE_OBLIQUE : TextAttribute.POSTURE_REGULAR);
        try {
            lllllllllllllllllllIllIlIllllIlI.put(TextAttribute.class.getDeclaredField("KERNING").get(null), TextAttribute.class.getDeclaredField("KERNING_ON").get(null));
        }
        catch (Exception ex) {}
        this.font = lllllllllllllllllllIllIlIlllIlIl.deriveFont(lllllllllllllllllllIllIlIllllIlI);
        final FontMetrics lllllllllllllllllllIllIlIllllIIl = GlyphPage.getScratchGraphics().getFontMetrics(this.font);
        this.ascent = lllllllllllllllllllIllIlIllllIIl.getAscent();
        this.descent = lllllllllllllllllllIllIlIllllIIl.getDescent();
        this.leading = lllllllllllllllllllIllIlIllllIIl.getLeading();
        final char[] lllllllllllllllllllIllIlIllllIII = " ".toCharArray();
        final GlyphVector lllllllllllllllllllIllIlIlllIlll = this.font.layoutGlyphVector(GlyphPage.renderContext, lllllllllllllllllllIllIlIllllIII, 0, lllllllllllllllllllIllIlIllllIII.length, 0);
        this.spaceWidth = lllllllllllllllllllIllIlIlllIlll.getGlyphLogicalBounds(0).getBounds().width;
    }
    
    public int getLeading() {
        return this.leading;
    }
    
    public void setPaddingRight(final int lllllllllllllllllllIlIllllIlIlIl) {
        this.paddingRight = lllllllllllllllllllIlIllllIlIlIl;
    }
    
    @Override
    public int getWidth(final String lllllllllllllllllllIllIIIIlllIll) {
        if (lllllllllllllllllllIllIIIIlllIll == null) {
            throw new IllegalArgumentException("text cannot be null.");
        }
        if (lllllllllllllllllllIllIIIIlllIll.length() == 0) {
            return 0;
        }
        if (this.displayListCaching) {
            final DisplayList lllllllllllllllllllIllIIIlIIlIIl = this.displayLists.get(lllllllllllllllllllIllIIIIlllIll);
            if (lllllllllllllllllllIllIIIlIIlIIl != null) {
                return lllllllllllllllllllIllIIIlIIlIIl.width;
            }
        }
        final char[] lllllllllllllllllllIllIIIlIIIIIl = lllllllllllllllllllIllIIIIlllIll.toCharArray();
        final GlyphVector lllllllllllllllllllIllIIIlIIIIII = this.font.layoutGlyphVector(GlyphPage.renderContext, lllllllllllllllllllIllIIIlIIIIIl, 0, lllllllllllllllllllIllIIIlIIIIIl.length, 0);
        int lllllllllllllllllllIllIIIIllllll = 0;
        int lllllllllllllllllllIllIIIIlllllI = 0;
        boolean lllllllllllllllllllIllIIIIllllIl = false;
        for (int lllllllllllllllllllIllIIIlIIIlIl = 0, lllllllllllllllllllIllIIIlIIIlII = lllllllllllllllllllIllIIIlIIIIII.getNumGlyphs(); lllllllllllllllllllIllIIIlIIIlIl < lllllllllllllllllllIllIIIlIIIlII; ++lllllllllllllllllllIllIIIlIIIlIl) {
            final int lllllllllllllllllllIllIIIlIIlIII = lllllllllllllllllllIllIIIlIIIIII.getGlyphCharIndex(lllllllllllllllllllIllIIIlIIIlIl);
            final int lllllllllllllllllllIllIIIlIIIlll = lllllllllllllllllllIllIIIIlllIll.codePointAt(lllllllllllllllllllIllIIIlIIlIII);
            final Rectangle lllllllllllllllllllIllIIIlIIIllI = this.getGlyphBounds(lllllllllllllllllllIllIIIlIIIIII, lllllllllllllllllllIllIIIlIIIlIl, lllllllllllllllllllIllIIIlIIIlll);
            if (lllllllllllllllllllIllIIIIllllIl && lllllllllllllllllllIllIIIlIIIlll != 10) {
                lllllllllllllllllllIllIIIIlllllI = -lllllllllllllllllllIllIIIlIIIllI.x;
            }
            if (lllllllllllllllllllIllIIIlIIIlIl > 0) {
                lllllllllllllllllllIllIIIIlllllI += this.paddingLeft + this.paddingRight + this.paddingAdvanceX;
            }
            lllllllllllllllllllIllIIIIllllll = Math.max(lllllllllllllllllllIllIIIIllllll, lllllllllllllllllllIllIIIlIIIllI.x + lllllllllllllllllllIllIIIIlllllI + lllllllllllllllllllIllIIIlIIIllI.width);
            if (lllllllllllllllllllIllIIIlIIIlll == 10) {
                lllllllllllllllllllIllIIIIllllIl = true;
            }
        }
        return lllllllllllllllllllIllIIIIllllll;
    }
    
    public boolean loadGlyphs(int lllllllllllllllllllIllIlIIlIllII) throws SlickException {
        if (this.queuedGlyphs.isEmpty()) {
            return false;
        }
        if (this.effects.isEmpty()) {
            throw new IllegalStateException("The UnicodeFont must have at least one effect before any glyphs can be loaded.");
        }
        final Iterator lllllllllllllllllllIllIlIIllIIll = this.queuedGlyphs.iterator();
        while (lllllllllllllllllllIllIlIIllIIll.hasNext()) {
            final Glyph lllllllllllllllllllIllIlIIllIlIl = lllllllllllllllllllIllIlIIllIIll.next();
            final int lllllllllllllllllllIllIlIIllIlII = lllllllllllllllllllIllIlIIllIlIl.getCodePoint();
            if (lllllllllllllllllllIllIlIIllIlIl.getWidth() == 0 || lllllllllllllllllllIllIlIIllIlII == 32) {
                lllllllllllllllllllIllIlIIllIIll.remove();
            }
            else {
                if (!lllllllllllllllllllIllIlIIllIlIl.isMissing()) {
                    continue;
                }
                if (this.missingGlyph != null) {
                    if (lllllllllllllllllllIllIlIIllIlIl == this.missingGlyph) {
                        continue;
                    }
                    lllllllllllllllllllIllIlIIllIIll.remove();
                }
                else {
                    this.missingGlyph = lllllllllllllllllllIllIlIIllIlIl;
                }
            }
        }
        Collections.sort((List<Object>)this.queuedGlyphs, UnicodeFont.heightComparator);
        for (final GlyphPage lllllllllllllllllllIllIlIIllIIlI : this.glyphPages) {
            lllllllllllllllllllIllIlIIlIllII -= lllllllllllllllllllIllIlIIllIIlI.loadGlyphs(this.queuedGlyphs, (int)lllllllllllllllllllIllIlIIlIllII);
            if (lllllllllllllllllllIllIlIIlIllII == 0 || this.queuedGlyphs.isEmpty()) {
                return true;
            }
        }
        while (!this.queuedGlyphs.isEmpty()) {
            final GlyphPage lllllllllllllllllllIllIlIIllIIII = new GlyphPage(this, this.glyphPageWidth, this.glyphPageHeight);
            this.glyphPages.add(lllllllllllllllllllIllIlIIllIIII);
            lllllllllllllllllllIllIlIIlIllII -= lllllllllllllllllllIllIlIIllIIII.loadGlyphs(this.queuedGlyphs, (int)lllllllllllllllllllIllIlIIlIllII);
            if (lllllllllllllllllllIllIlIIlIllII == 0) {
                return true;
            }
        }
        return true;
    }
    
    public int getAscent() {
        return this.ascent;
    }
    
    public UnicodeFont(final java.awt.Font lllllllllllllllllllIllIllIIllIII) {
        this.glyphs = new Glyph[2175][];
        this.glyphPages = new ArrayList();
        this.queuedGlyphs = new ArrayList(256);
        this.effects = new ArrayList();
        this.glyphPageWidth = 512;
        this.glyphPageHeight = 512;
        this.displayListCaching = true;
        this.baseDisplayListID = -1;
        this.displayLists = new LinkedHashMap(200, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry llllllllllllllllIlIlIllIIlIIlIII) {
                final DisplayList llllllllllllllllIlIlIllIIlIIIlll = llllllllllllllllIlIlIllIIlIIlIII.getValue();
                if (llllllllllllllllIlIlIllIIlIIIlll != null) {
                    UnicodeFont.this.eldestDisplayListID = llllllllllllllllIlIlIllIIlIIIlll.id;
                }
                return this.size() > 200;
            }
        };
        this.initializeFont(lllllllllllllllllllIllIllIIllIII, lllllllllllllllllllIllIllIIllIII.getSize(), lllllllllllllllllllIllIllIIllIII.isBold(), lllllllllllllllllllIllIllIIllIII.isItalic());
    }
    
    public DisplayList drawDisplayList(float lllllllllllllllllllIllIIlllIIIll, float lllllllllllllllllllIllIIlllIIIlI, final String lllllllllllllllllllIllIIllllIIll, final Color lllllllllllllllllllIllIIllllIIlI, final int lllllllllllllllllllIllIIllllIIIl, final int lllllllllllllllllllIllIIllIllllI) {
        if (lllllllllllllllllllIllIIllllIIll == null) {
            throw new IllegalArgumentException("text cannot be null.");
        }
        if (lllllllllllllllllllIllIIllllIIll.length() == 0) {
            return UnicodeFont.EMPTY_DISPLAY_LIST;
        }
        if (lllllllllllllllllllIllIIllllIIlI == null) {
            throw new IllegalArgumentException("color cannot be null.");
        }
        lllllllllllllllllllIllIIlllIIIll -= this.paddingLeft;
        lllllllllllllllllllIllIIlllIIIlI -= (byte)this.paddingTop;
        final String lllllllllllllllllllIllIIlllIllll = lllllllllllllllllllIllIIllllIIll.substring(lllllllllllllllllllIllIIllllIIIl, lllllllllllllllllllIllIIllIllllI);
        lllllllllllllllllllIllIIllllIIlI.bind();
        TextureImpl.bindNone();
        DisplayList lllllllllllllllllllIllIIlllIlllI = null;
        if (this.displayListCaching && this.queuedGlyphs.isEmpty()) {
            if (this.baseDisplayListID == -1) {
                this.baseDisplayListID = UnicodeFont.GL.glGenLists(200);
                if (this.baseDisplayListID == 0) {
                    this.baseDisplayListID = -1;
                    this.displayListCaching = false;
                    return new DisplayList();
                }
            }
            lllllllllllllllllllIllIIlllIlllI = this.displayLists.get(lllllllllllllllllllIllIIlllIllll);
            if (lllllllllllllllllllIllIIlllIlllI != null) {
                if (!lllllllllllllllllllIllIIlllIlllI.invalid) {
                    UnicodeFont.GL.glTranslatef(lllllllllllllllllllIllIIlllIIIll, lllllllllllllllllllIllIIlllIIIlI, 0.0f);
                    UnicodeFont.GL.glCallList(lllllllllllllllllllIllIIlllIlllI.id);
                    UnicodeFont.GL.glTranslatef(-lllllllllllllllllllIllIIlllIIIll, (float)(-lllllllllllllllllllIllIIlllIIIlI), 0.0f);
                    return lllllllllllllllllllIllIIlllIlllI;
                }
                lllllllllllllllllllIllIIlllIlllI.invalid = false;
            }
            else if (lllllllllllllllllllIllIIlllIlllI == null) {
                lllllllllllllllllllIllIIlllIlllI = new DisplayList();
                final int lllllllllllllllllllIllIIllllllll = this.displayLists.size();
                this.displayLists.put(lllllllllllllllllllIllIIlllIllll, lllllllllllllllllllIllIIlllIlllI);
                if (lllllllllllllllllllIllIIllllllll < 200) {
                    lllllllllllllllllllIllIIlllIlllI.id = this.baseDisplayListID + lllllllllllllllllllIllIIllllllll;
                }
                else {
                    lllllllllllllllllllIllIIlllIlllI.id = this.eldestDisplayListID;
                }
            }
            this.displayLists.put(lllllllllllllllllllIllIIlllIllll, lllllllllllllllllllIllIIlllIlllI);
        }
        UnicodeFont.GL.glTranslatef(lllllllllllllllllllIllIIlllIIIll, lllllllllllllllllllIllIIlllIIIlI, 0.0f);
        if (lllllllllllllllllllIllIIlllIlllI != null) {
            UnicodeFont.GL.glNewList(lllllllllllllllllllIllIIlllIlllI.id, 4865);
        }
        final char[] lllllllllllllllllllIllIIlllIllIl = lllllllllllllllllllIllIIllllIIll.substring(0, lllllllllllllllllllIllIIllIllllI).toCharArray();
        final GlyphVector lllllllllllllllllllIllIIlllIllII = this.font.layoutGlyphVector(GlyphPage.renderContext, lllllllllllllllllllIllIIlllIllIl, 0, lllllllllllllllllllIllIIlllIllIl.length, 0);
        int lllllllllllllllllllIllIIlllIlIll = 0;
        int lllllllllllllllllllIllIIlllIlIlI = 0;
        int lllllllllllllllllllIllIIlllIlIIl = 0;
        int lllllllllllllllllllIllIIlllIlIII = 0;
        int lllllllllllllllllllIllIIlllIIlll = this.ascent;
        boolean lllllllllllllllllllIllIIlllIIllI = false;
        Texture lllllllllllllllllllIllIIlllIIlIl = null;
        for (int lllllllllllllllllllIllIIlllllIII = 0, lllllllllllllllllllIllIIllllIlll = lllllllllllllllllllIllIIlllIllII.getNumGlyphs(); lllllllllllllllllllIllIIlllllIII < lllllllllllllllllllIllIIllllIlll; ++lllllllllllllllllllIllIIlllllIII) {
            final int lllllllllllllllllllIllIIllllllIl = lllllllllllllllllllIllIIlllIllII.getGlyphCharIndex(lllllllllllllllllllIllIIlllllIII);
            if (lllllllllllllllllllIllIIllllllIl >= lllllllllllllllllllIllIIllllIIIl) {
                if (lllllllllllllllllllIllIIllllllIl > lllllllllllllllllllIllIIllIllllI) {
                    break;
                }
                final int lllllllllllllllllllIllIIllllllII = lllllllllllllllllllIllIIllllIIll.codePointAt(lllllllllllllllllllIllIIllllllIl);
                final Rectangle lllllllllllllllllllIllIIlllllIll = this.getGlyphBounds(lllllllllllllllllllIllIIlllIllII, lllllllllllllllllllIllIIlllllIII, lllllllllllllllllllIllIIllllllII);
                final Glyph lllllllllllllllllllIllIIlllllIlI = this.getGlyph(lllllllllllllllllllIllIIlllIllII.getGlyphCode(lllllllllllllllllllIllIIlllllIII), lllllllllllllllllllIllIIllllllII, lllllllllllllllllllIllIIlllllIll, lllllllllllllllllllIllIIlllIllII, lllllllllllllllllllIllIIlllllIII);
                if (lllllllllllllllllllIllIIlllIIllI && lllllllllllllllllllIllIIllllllII != 10) {
                    lllllllllllllllllllIllIIlllIlIII = -lllllllllllllllllllIllIIlllllIll.x;
                    lllllllllllllllllllIllIIlllIIllI = false;
                }
                Image lllllllllllllllllllIllIIlllllIIl = lllllllllllllllllllIllIIlllllIlI.getImage();
                if (lllllllllllllllllllIllIIlllllIIl == null && this.missingGlyph != null && lllllllllllllllllllIllIIlllllIlI.isMissing()) {
                    lllllllllllllllllllIllIIlllllIIl = this.missingGlyph.getImage();
                }
                if (lllllllllllllllllllIllIIlllllIIl != null) {
                    final Texture lllllllllllllllllllIllIIlllllllI = lllllllllllllllllllIllIIlllllIIl.getTexture();
                    if (lllllllllllllllllllIllIIlllIIlIl != null && lllllllllllllllllllIllIIlllIIlIl != lllllllllllllllllllIllIIlllllllI) {
                        UnicodeFont.GL.glEnd();
                        lllllllllllllllllllIllIIlllIIlIl = null;
                    }
                    if (lllllllllllllllllllIllIIlllIIlIl == null) {
                        lllllllllllllllllllIllIIlllllllI.bind();
                        UnicodeFont.GL.glBegin(7);
                        lllllllllllllllllllIllIIlllIIlIl = lllllllllllllllllllIllIIlllllllI;
                    }
                    lllllllllllllllllllIllIIlllllIIl.drawEmbedded((float)(lllllllllllllllllllIllIIlllllIll.x + lllllllllllllllllllIllIIlllIlIII), (float)(lllllllllllllllllllIllIIlllllIll.y + lllllllllllllllllllIllIIlllIIlll), (float)lllllllllllllllllllIllIIlllllIIl.getWidth(), (float)lllllllllllllllllllIllIIlllllIIl.getHeight());
                }
                if (lllllllllllllllllllIllIIlllllIII >= 0) {
                    lllllllllllllllllllIllIIlllIlIII += this.paddingRight + this.paddingLeft + this.paddingAdvanceX;
                }
                lllllllllllllllllllIllIIlllIlIll = Math.max(lllllllllllllllllllIllIIlllIlIll, lllllllllllllllllllIllIIlllllIll.x + lllllllllllllllllllIllIIlllIlIII + lllllllllllllllllllIllIIlllllIll.width);
                lllllllllllllllllllIllIIlllIlIlI = Math.max(lllllllllllllllllllIllIIlllIlIlI, this.ascent + lllllllllllllllllllIllIIlllllIll.y + lllllllllllllllllllIllIIlllllIll.height);
                if (lllllllllllllllllllIllIIllllllII == 10) {
                    lllllllllllllllllllIllIIlllIIllI = true;
                    lllllllllllllllllllIllIIlllIIlll += this.getLineHeight();
                    ++lllllllllllllllllllIllIIlllIlIIl;
                    lllllllllllllllllllIllIIlllIlIlI = 0;
                }
            }
        }
        if (lllllllllllllllllllIllIIlllIIlIl != null) {
            UnicodeFont.GL.glEnd();
        }
        if (lllllllllllllllllllIllIIlllIlllI != null) {
            UnicodeFont.GL.glEndList();
            if (!this.queuedGlyphs.isEmpty()) {
                lllllllllllllllllllIllIIlllIlllI.invalid = true;
            }
        }
        UnicodeFont.GL.glTranslatef(-lllllllllllllllllllIllIIlllIIIll, (float)(-lllllllllllllllllllIllIIlllIIIlI), 0.0f);
        if (lllllllllllllllllllIllIIlllIlllI == null) {
            lllllllllllllllllllIllIIlllIlllI = new DisplayList();
        }
        lllllllllllllllllllIllIIlllIlllI.width = (short)lllllllllllllllllllIllIIlllIlIll;
        lllllllllllllllllllIllIIlllIlllI.height = (short)(lllllllllllllllllllIllIIlllIlIIl * this.getLineHeight() + lllllllllllllllllllIllIIlllIlIlI);
        return lllllllllllllllllllIllIIlllIlllI;
    }
    
    public UnicodeFont(final String lllllllllllllllllllIllIllIlllIII, final int lllllllllllllllllllIllIllIllIlll, final boolean lllllllllllllllllllIllIllIllIIIl, final boolean lllllllllllllllllllIllIllIllIIII) throws SlickException {
        this.glyphs = new Glyph[2175][];
        this.glyphPages = new ArrayList();
        this.queuedGlyphs = new ArrayList(256);
        this.effects = new ArrayList();
        this.glyphPageWidth = 512;
        this.glyphPageHeight = 512;
        this.displayListCaching = true;
        this.baseDisplayListID = -1;
        this.displayLists = new LinkedHashMap(200, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry llllllllllllllllIlIlIllIIlIIlIII) {
                final DisplayList llllllllllllllllIlIlIllIIlIIIlll = llllllllllllllllIlIlIllIIlIIlIII.getValue();
                if (llllllllllllllllIlIlIllIIlIIIlll != null) {
                    UnicodeFont.this.eldestDisplayListID = llllllllllllllllIlIlIllIIlIIIlll.id;
                }
                return this.size() > 200;
            }
        };
        this.ttfFileRef = lllllllllllllllllllIllIllIlllIII;
        this.initializeFont(createFont(lllllllllllllllllllIllIllIlllIII), lllllllllllllllllllIllIllIllIlll, lllllllllllllllllllIllIllIllIIIl, lllllllllllllllllllIllIllIllIIII);
    }
    
    public static class DisplayList
    {
        public /* synthetic */ int id;
        public /* synthetic */ Short yOffset;
        public /* synthetic */ boolean invalid;
        public /* synthetic */ short height;
        public /* synthetic */ short width;
    }
}
