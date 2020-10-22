package org.newdawn.slick.font;

import org.newdawn.slick.font.effects.*;
import java.util.*;
import java.io.*;
import org.newdawn.slick.*;
import org.newdawn.slick.util.*;

public class HieroSettings
{
    private /* synthetic */ int paddingBottom;
    private /* synthetic */ int glyphPageWidth;
    private /* synthetic */ int paddingAdvanceX;
    private /* synthetic */ int paddingRight;
    private /* synthetic */ boolean bold;
    private final /* synthetic */ List effects;
    private /* synthetic */ int paddingAdvanceY;
    private /* synthetic */ int glyphPageHeight;
    private /* synthetic */ boolean italic;
    private /* synthetic */ int fontSize;
    private /* synthetic */ int paddingLeft;
    private /* synthetic */ int paddingTop;
    
    public void setPaddingTop(final int lIllIlIlIlllllI) {
        this.paddingTop = lIllIlIlIlllllI;
    }
    
    public void setGlyphPageWidth(final int lIllIlIlIIIlIII) {
        this.glyphPageWidth = lIllIlIlIIIlIII;
    }
    
    public void setPaddingLeft(final int lIllIlIlIllIlll) {
        this.paddingLeft = lIllIlIlIllIlll;
    }
    
    public int getPaddingAdvanceY() {
        return this.paddingAdvanceY;
    }
    
    public boolean isBold() {
        return this.bold;
    }
    
    public void setPaddingAdvanceX(final int lIllIlIlIIllIlI) {
        this.paddingAdvanceX = lIllIlIlIIllIlI;
    }
    
    public int getPaddingBottom() {
        return this.paddingBottom;
    }
    
    public void save(final File lIllIlIIlIlIlII) throws IOException {
        final PrintStream lIllIlIIlIlIIll = new PrintStream(new FileOutputStream(lIllIlIIlIlIlII));
        lIllIlIIlIlIIll.println(String.valueOf(new StringBuilder().append("font.size=").append(this.fontSize)));
        lIllIlIIlIlIIll.println(String.valueOf(new StringBuilder().append("font.bold=").append(this.bold)));
        lIllIlIIlIlIIll.println(String.valueOf(new StringBuilder().append("font.italic=").append(this.italic)));
        lIllIlIIlIlIIll.println();
        lIllIlIIlIlIIll.println(String.valueOf(new StringBuilder().append("pad.top=").append(this.paddingTop)));
        lIllIlIIlIlIIll.println(String.valueOf(new StringBuilder().append("pad.right=").append(this.paddingRight)));
        lIllIlIIlIlIIll.println(String.valueOf(new StringBuilder().append("pad.bottom=").append(this.paddingBottom)));
        lIllIlIIlIlIIll.println(String.valueOf(new StringBuilder().append("pad.left=").append(this.paddingLeft)));
        lIllIlIIlIlIIll.println(String.valueOf(new StringBuilder().append("pad.advance.x=").append(this.paddingAdvanceX)));
        lIllIlIIlIlIIll.println(String.valueOf(new StringBuilder().append("pad.advance.y=").append(this.paddingAdvanceY)));
        lIllIlIIlIlIIll.println();
        lIllIlIIlIlIIll.println(String.valueOf(new StringBuilder().append("glyph.page.width=").append(this.glyphPageWidth)));
        lIllIlIIlIlIIll.println(String.valueOf(new StringBuilder().append("glyph.page.height=").append(this.glyphPageHeight)));
        lIllIlIIlIlIIll.println();
        for (final ConfigurableEffect lIllIlIIlIlIlll : this.effects) {
            lIllIlIIlIlIIll.println(String.valueOf(new StringBuilder().append("effect.class=").append(lIllIlIIlIlIlll.getClass().getName())));
            for (final ConfigurableEffect.Value lIllIlIIlIllIIl : lIllIlIIlIlIlll.getValues()) {
                lIllIlIIlIlIIll.println(String.valueOf(new StringBuilder().append("effect.").append(lIllIlIIlIllIIl.getName()).append("=").append(lIllIlIIlIllIIl.getString())));
            }
            lIllIlIIlIlIIll.println();
        }
        lIllIlIIlIlIIll.close();
    }
    
    public List getEffects() {
        return this.effects;
    }
    
    public int getPaddingLeft() {
        return this.paddingLeft;
    }
    
    public HieroSettings(final InputStream lIllIlIllIlIIlI) throws SlickException {
        this.fontSize = 12;
        this.bold = false;
        this.italic = false;
        this.glyphPageWidth = 512;
        this.glyphPageHeight = 512;
        this.effects = new ArrayList();
        try {
            final BufferedReader lIllIlIllIlIlIl = new BufferedReader(new InputStreamReader(lIllIlIllIlIIlI));
            while (true) {
                String lIllIlIllIllIIl = lIllIlIllIlIlIl.readLine();
                if (lIllIlIllIllIIl == null) {
                    break;
                }
                lIllIlIllIllIIl = lIllIlIllIllIIl.trim();
                if (lIllIlIllIllIIl.length() == 0) {
                    continue;
                }
                final String[] lIllIlIllIllIII = lIllIlIllIllIIl.split("=", 2);
                String lIllIlIllIlIlll = lIllIlIllIllIII[0].trim();
                final String lIllIlIllIlIllI = lIllIlIllIllIII[1];
                if (lIllIlIllIlIlll.equals("font.size")) {
                    this.fontSize = Integer.parseInt(lIllIlIllIlIllI);
                }
                else if (lIllIlIllIlIlll.equals("font.bold")) {
                    this.bold = Boolean.valueOf(lIllIlIllIlIllI);
                }
                else if (lIllIlIllIlIlll.equals("font.italic")) {
                    this.italic = Boolean.valueOf(lIllIlIllIlIllI);
                }
                else if (lIllIlIllIlIlll.equals("pad.top")) {
                    this.paddingTop = Integer.parseInt(lIllIlIllIlIllI);
                }
                else if (lIllIlIllIlIlll.equals("pad.right")) {
                    this.paddingRight = Integer.parseInt(lIllIlIllIlIllI);
                }
                else if (lIllIlIllIlIlll.equals("pad.bottom")) {
                    this.paddingBottom = Integer.parseInt(lIllIlIllIlIllI);
                }
                else if (lIllIlIllIlIlll.equals("pad.left")) {
                    this.paddingLeft = Integer.parseInt(lIllIlIllIlIllI);
                }
                else if (lIllIlIllIlIlll.equals("pad.advance.x")) {
                    this.paddingAdvanceX = Integer.parseInt(lIllIlIllIlIllI);
                }
                else if (lIllIlIllIlIlll.equals("pad.advance.y")) {
                    this.paddingAdvanceY = Integer.parseInt(lIllIlIllIlIllI);
                }
                else if (lIllIlIllIlIlll.equals("glyph.page.width")) {
                    this.glyphPageWidth = Integer.parseInt(lIllIlIllIlIllI);
                }
                else if (lIllIlIllIlIlll.equals("glyph.page.height")) {
                    this.glyphPageHeight = Integer.parseInt(lIllIlIllIlIllI);
                }
                else {
                    if (lIllIlIllIlIlll.equals("effect.class")) {
                        try {
                            this.effects.add(Class.forName(lIllIlIllIlIllI).newInstance());
                            continue;
                        }
                        catch (Exception lIllIlIllIllllI) {
                            throw new SlickException(String.valueOf(new StringBuilder().append("Unable to create effect instance: ").append(lIllIlIllIlIllI)), lIllIlIllIllllI);
                        }
                    }
                    if (!lIllIlIllIlIlll.startsWith("effect.")) {
                        continue;
                    }
                    lIllIlIllIlIlll = lIllIlIllIlIlll.substring(7);
                    final ConfigurableEffect lIllIlIllIllIll = this.effects.get(this.effects.size() - 1);
                    final List lIllIlIllIllIlI = lIllIlIllIllIll.getValues();
                    for (final ConfigurableEffect.Value lIllIlIllIlllIl : lIllIlIllIllIlI) {
                        if (lIllIlIllIlllIl.getName().equals(lIllIlIllIlIlll)) {
                            lIllIlIllIlllIl.setString(lIllIlIllIlIllI);
                            break;
                        }
                    }
                    lIllIlIllIllIll.setValues(lIllIlIllIllIlI);
                }
            }
            lIllIlIllIlIlIl.close();
        }
        catch (Exception lIllIlIllIlIlII) {
            throw new SlickException("Unable to load Hiero font file", lIllIlIllIlIlII);
        }
    }
    
    public void setFontSize(final int lIllIlIIllllIII) {
        this.fontSize = lIllIlIIllllIII;
    }
    
    public void setPaddingRight(final int lIllIlIlIlIIlIl) {
        this.paddingRight = lIllIlIlIlIIlIl;
    }
    
    public void setPaddingAdvanceY(final int lIllIlIlIIlIIIl) {
        this.paddingAdvanceY = lIllIlIlIIlIIIl;
    }
    
    public boolean isItalic() {
        return this.italic;
    }
    
    public HieroSettings(final String lIllIlIlllIllII) throws SlickException {
        this(ResourceLoader.getResourceAsStream(lIllIlIlllIllII));
    }
    
    public HieroSettings() {
        this.fontSize = 12;
        this.bold = false;
        this.italic = false;
        this.glyphPageWidth = 512;
        this.glyphPageHeight = 512;
        this.effects = new ArrayList();
    }
    
    public int getPaddingRight() {
        return this.paddingRight;
    }
    
    public int getPaddingAdvanceX() {
        return this.paddingAdvanceX;
    }
    
    public int getFontSize() {
        return this.fontSize;
    }
    
    public void setPaddingBottom(final int lIllIlIlIlIllII) {
        this.paddingBottom = lIllIlIlIlIllII;
    }
    
    public void setGlyphPageHeight(final int lIllIlIlIIIIIIl) {
        this.glyphPageHeight = lIllIlIlIIIIIIl;
    }
    
    public void setItalic(final boolean lIllIlIIllIIllI) {
        this.italic = lIllIlIIllIIllI;
    }
    
    public int getGlyphPageWidth() {
        return this.glyphPageWidth;
    }
    
    public int getPaddingTop() {
        return this.paddingTop;
    }
    
    public int getGlyphPageHeight() {
        return this.glyphPageHeight;
    }
    
    public void setBold(final boolean lIllIlIIllIllll) {
        this.bold = lIllIlIIllIllll;
    }
}
