package org.newdawn.slick;

import org.newdawn.slick.util.*;
import java.io.*;

public class SpriteSheetFont implements Font
{
    private /* synthetic */ int horizontalCount;
    private /* synthetic */ SpriteSheet font;
    private /* synthetic */ int charHeight;
    private /* synthetic */ int charWidth;
    private /* synthetic */ char startingCharacter;
    private /* synthetic */ int numChars;
    
    @Override
    public int getLineHeight() {
        return this.charHeight;
    }
    
    @Override
    public void drawString(final float lIlIllllIIll, final float lIlIllllIlll, final String lIlIllllIllI, final Color lIlIllllIIII) {
        this.drawString(lIlIllllIIll, lIlIllllIlll, lIlIllllIllI, lIlIllllIIII, 0, lIlIllllIllI.length() - 1);
    }
    
    @Override
    public int getHeight(final String lIlIllIIlIII) {
        return this.charHeight;
    }
    
    @Override
    public void drawString(final float lIlIllIlIlIl, final float lIlIllIlIlII, final String lIlIllIllIlI, final Color lIlIllIlIIlI, final int lIlIllIllIII, final int lIlIllIlIlll) {
        try {
            final byte[] lIlIllIlllll = lIlIllIllIlI.getBytes("US-ASCII");
            for (int lIlIlllIIIII = 0; lIlIlllIIIII < lIlIllIlllll.length; ++lIlIlllIIIII) {
                final int lIlIlllIIIIl = lIlIllIlllll[lIlIlllIIIII] - this.startingCharacter;
                if (lIlIlllIIIIl < this.numChars) {
                    final int lIlIlllIIIll = lIlIlllIIIIl % this.horizontalCount;
                    final int lIlIlllIIIlI = lIlIlllIIIIl / this.horizontalCount;
                    if (lIlIlllIIIII >= lIlIllIllIII || lIlIlllIIIII <= lIlIllIlIlll) {
                        this.font.getSprite(lIlIlllIIIll, lIlIlllIIIlI).draw(lIlIllIlIlIl + lIlIlllIIIII * this.charWidth, lIlIllIlIlII, lIlIllIlIIlI);
                    }
                }
            }
        }
        catch (UnsupportedEncodingException lIlIllIllllI) {
            Log.error(lIlIllIllllI);
        }
    }
    
    public SpriteSheetFont(final SpriteSheet lIllIIIIllIl, final char lIllIIIlIIII) {
        this.font = lIllIIIIllIl;
        this.startingCharacter = lIllIIIlIIII;
        this.horizontalCount = lIllIIIIllIl.getHorizontalCount();
        final int lIllIIIIllll = lIllIIIIllIl.getVerticalCount();
        this.charWidth = lIllIIIIllIl.getWidth() / this.horizontalCount;
        this.charHeight = lIllIIIIllIl.getHeight() / lIllIIIIllll;
        this.numChars = this.horizontalCount * lIllIIIIllll;
    }
    
    @Override
    public int getWidth(final String lIlIllIIIIll) {
        return this.charWidth * lIlIllIIIIll.length();
    }
    
    @Override
    public void drawString(final float lIllIIIIIlIl, final float lIllIIIIIlII, final String lIllIIIIIIll) {
        this.drawString(lIllIIIIIlIl, lIllIIIIIlII, lIllIIIIIIll, Color.white);
    }
}
