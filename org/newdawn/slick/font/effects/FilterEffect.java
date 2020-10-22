package org.newdawn.slick.font.effects;

import org.newdawn.slick.*;
import org.newdawn.slick.font.*;
import java.awt.*;
import java.awt.image.*;

public class FilterEffect implements Effect
{
    private /* synthetic */ BufferedImageOp filter;
    
    public void setFilter(final BufferedImageOp llIlIlIlllIIIll) {
        this.filter = llIlIlIlllIIIll;
    }
    
    public FilterEffect() {
    }
    
    public FilterEffect(final BufferedImageOp llIlIlIlllllIII) {
        this.filter = llIlIlIlllllIII;
    }
    
    public BufferedImageOp getFilter() {
        return this.filter;
    }
    
    @Override
    public void draw(final BufferedImage llIlIlIlllIlIll, final Graphics2D llIlIlIllllIIII, final UnicodeFont llIlIlIlllIllll, final Glyph llIlIlIlllIlllI) {
        final BufferedImage llIlIlIlllIllIl = EffectUtil.getScratchImage();
        this.filter.filter(llIlIlIlllIlIll, llIlIlIlllIllIl);
        llIlIlIlllIlIll.getGraphics().drawImage(llIlIlIlllIllIl, 0, 0, null);
    }
}
