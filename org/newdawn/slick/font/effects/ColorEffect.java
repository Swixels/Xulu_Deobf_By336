package org.newdawn.slick.font.effects;

import java.awt.image.*;
import java.awt.*;
import org.newdawn.slick.*;
import org.newdawn.slick.font.*;
import java.util.*;

public class ColorEffect implements ConfigurableEffect
{
    private /* synthetic */ Color color;
    
    @Override
    public List getValues() {
        final List lllllllllllllllllllIIlIlllIlllll = new ArrayList();
        lllllllllllllllllllIIlIlllIlllll.add(EffectUtil.colorValue("Color", this.color));
        return lllllllllllllllllllIIlIlllIlllll;
    }
    
    public void setColor(final Color lllllllllllllllllllIIlIllllIlIll) {
        if (lllllllllllllllllllIIlIllllIlIll == null) {
            throw new IllegalArgumentException("color cannot be null.");
        }
        this.color = lllllllllllllllllllIIlIllllIlIll;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    @Override
    public String toString() {
        return "Color";
    }
    
    public ColorEffect() {
        this.color = Color.white;
    }
    
    public ColorEffect(final Color lllllllllllllllllllIIllIIIIIIlll) {
        this.color = Color.white;
        this.color = lllllllllllllllllllIIllIIIIIIlll;
    }
    
    @Override
    public void draw(final BufferedImage lllllllllllllllllllIIllIIIIIIIII, final Graphics2D lllllllllllllllllllIIlIlllllllll, final UnicodeFont lllllllllllllllllllIIlIllllllllI, final Glyph lllllllllllllllllllIIlIllllllIlI) {
        lllllllllllllllllllIIlIlllllllll.setColor(this.color);
        lllllllllllllllllllIIlIlllllllll.fill(lllllllllllllllllllIIlIllllllIlI.getShape());
    }
    
    @Override
    public void setValues(final List lllllllllllllllllllIIlIlllIIlllI) {
        for (final Value lllllllllllllllllllIIlIlllIlIIll : lllllllllllllllllllIIlIlllIIlllI) {
            if (lllllllllllllllllllIIlIlllIlIIll.getName().equals("Color")) {
                this.setColor((Color)lllllllllllllllllllIIlIlllIlIIll.getObject());
            }
        }
    }
}
