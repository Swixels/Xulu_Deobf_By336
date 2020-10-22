package org.newdawn.slick.font.effects;

import java.awt.image.*;
import org.newdawn.slick.*;
import org.newdawn.slick.font.*;
import java.util.*;
import java.awt.*;

public class OutlineEffect implements ConfigurableEffect
{
    private /* synthetic */ float width;
    private /* synthetic */ int join;
    private /* synthetic */ Stroke stroke;
    private /* synthetic */ Color color;
    
    public float getWidth() {
        return this.width;
    }
    
    public OutlineEffect(final int lllllIlIlIIIlll, final Color lllllIlIlIIIIll) {
        this.width = 2.0f;
        this.color = Color.black;
        this.join = 2;
        this.width = (float)lllllIlIlIIIlll;
        this.color = lllllIlIlIIIIll;
    }
    
    public OutlineEffect() {
        this.width = 2.0f;
        this.color = Color.black;
        this.join = 2;
    }
    
    public void setJoin(final int lllllIlIIIlIllI) {
        this.join = lllllIlIIIlIllI;
    }
    
    public Stroke getStroke() {
        if (this.stroke == null) {
            return new BasicStroke(this.width, 2, this.join);
        }
        return this.stroke;
    }
    
    public void setWidth(final int lllllIlIIllIIIl) {
        this.width = (float)lllllIlIIllIIIl;
    }
    
    public int getJoin() {
        return this.join;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    @Override
    public void draw(final BufferedImage lllllIlIIlllllI, Graphics2D lllllIlIIlllIIl, final UnicodeFont lllllIlIIllllII, final Glyph lllllIlIIlllIII) {
        lllllIlIIlllIIl = (int)((Graphics)lllllIlIIlllIIl).create();
        if (this.stroke != null) {
            ((Graphics2D)lllllIlIIlllIIl).setStroke(this.stroke);
        }
        else {
            ((Graphics2D)lllllIlIIlllIIl).setStroke(this.getStroke());
        }
        ((Graphics)lllllIlIIlllIIl).setColor(this.color);
        ((Graphics2D)lllllIlIIlllIIl).draw(lllllIlIIlllIII.getShape());
        ((Graphics)lllllIlIIlllIIl).dispose();
    }
    
    public void setColor(final Color lllllIlIIlIlIII) {
        this.color = lllllIlIIlIlIII;
    }
    
    public void setStroke(final Stroke lllllIlIIIllIlI) {
        this.stroke = lllllIlIIIllIlI;
    }
    
    @Override
    public List getValues() {
        final List lllllIlIIIIllll = new ArrayList();
        lllllIlIIIIllll.add(EffectUtil.colorValue("Color", this.color));
        lllllIlIIIIllll.add(EffectUtil.floatValue("Width", this.width, 0.1f, 999.0f, "This setting controls the width of the outline. The glyphs will need padding so the outline doesn't get clipped."));
        lllllIlIIIIllll.add(EffectUtil.optionValue("Join", String.valueOf(this.join), new String[][] { { "Bevel", "2" }, { "Miter", "0" }, { "Round", "1" } }, "This setting defines how the corners of the outline are drawn. This is usually only noticeable at large outline widths."));
        return lllllIlIIIIllll;
    }
    
    @Override
    public String toString() {
        return "Outline";
    }
    
    @Override
    public void setValues(final List lllllIlIIIIIIll) {
        for (final Value lllllIlIIIIlIII : lllllIlIIIIIIll) {
            if (lllllIlIIIIlIII.getName().equals("Color")) {
                this.color = (Color)lllllIlIIIIlIII.getObject();
            }
            else if (lllllIlIIIIlIII.getName().equals("Width")) {
                this.width = (float)lllllIlIIIIlIII.getObject();
            }
            else {
                if (!lllllIlIIIIlIII.getName().equals("Join")) {
                    continue;
                }
                this.join = Integer.parseInt((String)lllllIlIIIIlIII.getObject());
            }
        }
    }
}
