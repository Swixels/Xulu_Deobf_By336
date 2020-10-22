package org.newdawn.slick.font.effects;

import java.awt.image.*;
import org.newdawn.slick.*;
import org.newdawn.slick.font.*;
import java.awt.*;
import java.util.*;

public class GradientEffect implements ConfigurableEffect
{
    private /* synthetic */ float scale;
    private /* synthetic */ int offset;
    private /* synthetic */ Color topColor;
    private /* synthetic */ Color bottomColor;
    private /* synthetic */ boolean cyclic;
    
    public Color getTopColor() {
        return this.topColor;
    }
    
    @Override
    public void draw(final BufferedImage lIIIllIlIlIIlII, final Graphics2D lIIIllIlIlIIIll, final UnicodeFont lIIIllIlIlIIIlI, final Glyph lIIIllIlIIllIlI) {
        final int lIIIllIlIlIIIII = lIIIllIlIlIIIlI.getAscent();
        final float lIIIllIlIIlllll = lIIIllIlIlIIIII * this.scale;
        final float lIIIllIlIIllllI = -lIIIllIlIIllIlI.getYOffset() + lIIIllIlIlIIIlI.getDescent() + this.offset + lIIIllIlIlIIIII / 2 - lIIIllIlIIlllll / 2.0f;
        lIIIllIlIlIIIll.setPaint(new GradientPaint(0.0f, lIIIllIlIIllllI, this.topColor, 0.0f, lIIIllIlIIllllI + lIIIllIlIIlllll, this.bottomColor, this.cyclic));
        lIIIllIlIlIIIll.fill(lIIIllIlIIllIlI.getShape());
    }
    
    public void setBottomColor(final Color lIIIllIlIIIIlll) {
        this.bottomColor = lIIIllIlIIIIlll;
    }
    
    public void setScale(final float lIIIllIIlllIIll) {
        this.scale = lIIIllIIlllIIll;
    }
    
    public GradientEffect() {
        this.topColor = Color.cyan;
        this.bottomColor = Color.blue;
        this.offset = 0;
        this.scale = 1.0f;
    }
    
    public Color getBottomColor() {
        return this.bottomColor;
    }
    
    public void setOffset(final int lIIIllIIlllllII) {
        this.offset = lIIIllIIlllllII;
    }
    
    public boolean isCyclic() {
        return this.cyclic;
    }
    
    @Override
    public String toString() {
        return "Gradient";
    }
    
    public int getOffset() {
        return this.offset;
    }
    
    @Override
    public void setValues(final List lIIIllIIlIllIll) {
        for (final Value lIIIllIIlIllllI : lIIIllIIlIllIll) {
            if (lIIIllIIlIllllI.getName().equals("Top color")) {
                this.topColor = (Color)lIIIllIIlIllllI.getObject();
            }
            else if (lIIIllIIlIllllI.getName().equals("Bottom color")) {
                this.bottomColor = (Color)lIIIllIIlIllllI.getObject();
            }
            else if (lIIIllIIlIllllI.getName().equals("Offset")) {
                this.offset = (int)lIIIllIIlIllllI.getObject();
            }
            else if (lIIIllIIlIllllI.getName().equals("Scale")) {
                this.scale = (float)lIIIllIIlIllllI.getObject();
            }
            else {
                if (!lIIIllIIlIllllI.getName().equals("Cyclic")) {
                    continue;
                }
                this.cyclic = (boolean)lIIIllIIlIllllI.getObject();
            }
        }
    }
    
    @Override
    public List getValues() {
        final List lIIIllIIllIIlIl = new ArrayList();
        lIIIllIIllIIlIl.add(EffectUtil.colorValue("Top color", this.topColor));
        lIIIllIIllIIlIl.add(EffectUtil.colorValue("Bottom color", this.bottomColor));
        lIIIllIIllIIlIl.add(EffectUtil.intValue("Offset", this.offset, "This setting allows you to move the gradient up or down. The gradient is normally centered on the glyph."));
        lIIIllIIllIIlIl.add(EffectUtil.floatValue("Scale", this.scale, 0.0f, 1.0f, "This setting allows you to change the height of the gradient by apercentage. The gradient is normally the height of most glyphs in the font."));
        lIIIllIIllIIlIl.add(EffectUtil.booleanValue("Cyclic", this.cyclic, "If this setting is checked, the gradient will repeat."));
        return lIIIllIIllIIlIl;
    }
    
    public void setTopColor(final Color lIIIllIlIIIlllI) {
        this.topColor = lIIIllIlIIIlllI;
    }
    
    public void setCyclic(final boolean lIIIllIIllIllII) {
        this.cyclic = lIIIllIIllIllII;
    }
    
    public GradientEffect(final Color lIIIllIlIllIIll, final Color lIIIllIlIlIlllI, final float lIIIllIlIlIllIl) {
        this.topColor = Color.cyan;
        this.bottomColor = Color.blue;
        this.offset = 0;
        this.scale = 1.0f;
        this.topColor = lIIIllIlIllIIll;
        this.bottomColor = lIIIllIlIlIlllI;
        this.scale = lIIIllIlIlIllIl;
    }
    
    public float getScale() {
        return this.scale;
    }
}
