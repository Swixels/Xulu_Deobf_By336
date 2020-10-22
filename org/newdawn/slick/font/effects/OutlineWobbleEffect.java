package org.newdawn.slick.font.effects;

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class OutlineWobbleEffect extends OutlineEffect
{
    private /* synthetic */ float amplitude;
    private /* synthetic */ float detail;
    
    public float getAmplitude() {
        return this.amplitude;
    }
    
    @Override
    public void setValues(final List lllllllllllllllllIllIIIIllIIIIll) {
        super.setValues(lllllllllllllllllIllIIIIllIIIIll);
        for (final ConfigurableEffect.Value lllllllllllllllllIllIIIIllIIIllI : lllllllllllllllllIllIIIIllIIIIll) {
            if (lllllllllllllllllIllIIIIllIIIllI.getName().equals("Detail")) {
                this.detail = (float)lllllllllllllllllIllIIIIllIIIllI.getObject();
            }
            else {
                if (!lllllllllllllllllIllIIIIllIIIllI.getName().equals("Amplitude")) {
                    continue;
                }
                this.amplitude = (float)lllllllllllllllllIllIIIIllIIIllI.getObject();
            }
        }
    }
    
    @Override
    public String toString() {
        return "Outline (Wobble)";
    }
    
    public void setAmplitude(final float lllllllllllllllllIllIIIIllIlllIl) {
        this.amplitude = lllllllllllllllllIllIIIIllIlllIl;
    }
    
    @Override
    public List getValues() {
        final List lllllllllllllllllIllIIIIllIIllIl = super.getValues();
        lllllllllllllllllIllIIIIllIIllIl.remove(2);
        lllllllllllllllllIllIIIIllIIllIl.add(EffectUtil.floatValue("Detail", this.detail, 1.0f, 50.0f, "This setting controls how detailed the outline will be. Smaller numbers cause the outline to have more detail."));
        lllllllllllllllllIllIIIIllIIllIl.add(EffectUtil.floatValue("Amplitude", this.amplitude, 0.5f, 50.0f, "This setting controls the amplitude of the outline."));
        return lllllllllllllllllIllIIIIllIIllIl;
    }
    
    public void setDetail(final float lllllllllllllllllIllIIIIlllIIlII) {
        this.detail = lllllllllllllllllIllIIIIlllIIlII;
    }
    
    public OutlineWobbleEffect(final int lllllllllllllllllIllIIIIllIlIllI, final Color lllllllllllllllllIllIIIIllIlIlIl) {
        super(lllllllllllllllllIllIIIIllIlIllI, lllllllllllllllllIllIIIIllIlIlIl);
        this.detail = 1.0f;
        this.amplitude = 1.0f;
    }
    
    public float getDetail() {
        return this.detail;
    }
    
    public OutlineWobbleEffect() {
        this.detail = 1.0f;
        this.amplitude = 1.0f;
        this.setStroke(new WobbleStroke());
    }
    
    private class WobbleStroke implements Stroke
    {
        @Override
        public Shape createStrokedShape(Shape llllllllllllllllllllllIIIIIIllll) {
            final GeneralPath llllllllllllllllllllllIIIIIllIll = new GeneralPath();
            llllllllllllllllllllllIIIIIIllll = (int)new BasicStroke(OutlineWobbleEffect.this.getWidth(), 2, OutlineWobbleEffect.this.getJoin()).createStrokedShape((Shape)llllllllllllllllllllllIIIIIIllll);
            final PathIterator llllllllllllllllllllllIIIIIllIlI = new FlatteningPathIterator(((Shape)llllllllllllllllllllllIIIIIIllll).getPathIterator(null), 1.0);
            final float[] llllllllllllllllllllllIIIIIllIIl = new float[6];
            float llllllllllllllllllllllIIIIIllIII = 0.0f;
            float llllllllllllllllllllllIIIIIlIlll = 0.0f;
            float llllllllllllllllllllllIIIIIlIllI = 0.0f;
            float llllllllllllllllllllllIIIIIlIlIl = 0.0f;
            float llllllllllllllllllllllIIIIIlIlII = 0.0f;
            float llllllllllllllllllllllIIIIIlIIll = 0.0f;
            int llllllllllllllllllllllIIIIIlIIlI = 0;
            float llllllllllllllllllllllIIIIIlIIIl = 0.0f;
            while (!llllllllllllllllllllllIIIIIllIlI.isDone()) {
                llllllllllllllllllllllIIIIIlIIlI = llllllllllllllllllllllIIIIIllIlI.currentSegment(llllllllllllllllllllllIIIIIllIIl);
                switch (llllllllllllllllllllllIIIIIlIIlI) {
                    case 0: {
                        llllllllllllllllllllllIIIIIlIllI = (llllllllllllllllllllllIIIIIllIII = this.randomize(llllllllllllllllllllllIIIIIllIIl[0]));
                        llllllllllllllllllllllIIIIIlIlIl = (llllllllllllllllllllllIIIIIlIlll = this.randomize(llllllllllllllllllllllIIIIIllIIl[1]));
                        llllllllllllllllllllllIIIIIllIll.moveTo(llllllllllllllllllllllIIIIIllIII, llllllllllllllllllllllIIIIIlIlll);
                        llllllllllllllllllllllIIIIIlIIIl = 0.0f;
                        break;
                    }
                    case 4: {
                        llllllllllllllllllllllIIIIIllIIl[0] = llllllllllllllllllllllIIIIIllIII;
                        llllllllllllllllllllllIIIIIllIIl[1] = llllllllllllllllllllllIIIIIlIlll;
                    }
                    case 1: {
                        llllllllllllllllllllllIIIIIlIlII = this.randomize(llllllllllllllllllllllIIIIIllIIl[0]);
                        llllllllllllllllllllllIIIIIlIIll = this.randomize(llllllllllllllllllllllIIIIIllIIl[1]);
                        final float llllllllllllllllllllllIIIIlIIIII = llllllllllllllllllllllIIIIIlIlII - llllllllllllllllllllllIIIIIlIllI;
                        final float llllllllllllllllllllllIIIIIlllll = llllllllllllllllllllllIIIIIlIIll - llllllllllllllllllllllIIIIIlIlIl;
                        final float llllllllllllllllllllllIIIIIllllI = (float)Math.sqrt(llllllllllllllllllllllIIIIlIIIII * llllllllllllllllllllllIIIIlIIIII + llllllllllllllllllllllIIIIIlllll * llllllllllllllllllllllIIIIIlllll);
                        if (llllllllllllllllllllllIIIIIllllI >= llllllllllllllllllllllIIIIIlIIIl) {
                            final float llllllllllllllllllllllIIIIlIIIIl = 1.0f / llllllllllllllllllllllIIIIIllllI;
                            while (llllllllllllllllllllllIIIIIllllI >= llllllllllllllllllllllIIIIIlIIIl) {
                                final float llllllllllllllllllllllIIIIlIIIll = llllllllllllllllllllllIIIIIlIllI + llllllllllllllllllllllIIIIIlIIIl * llllllllllllllllllllllIIIIlIIIII * llllllllllllllllllllllIIIIlIIIIl;
                                final float llllllllllllllllllllllIIIIlIIIlI = llllllllllllllllllllllIIIIIlIlIl + llllllllllllllllllllllIIIIIlIIIl * llllllllllllllllllllllIIIIIlllll * llllllllllllllllllllllIIIIlIIIIl;
                                llllllllllllllllllllllIIIIIllIll.lineTo(this.randomize(llllllllllllllllllllllIIIIlIIIll), this.randomize(llllllllllllllllllllllIIIIlIIIlI));
                                llllllllllllllllllllllIIIIIlIIIl += OutlineWobbleEffect.this.detail;
                            }
                        }
                        llllllllllllllllllllllIIIIIlIIIl -= llllllllllllllllllllllIIIIIllllI;
                        llllllllllllllllllllllIIIIIlIllI = llllllllllllllllllllllIIIIIlIlII;
                        llllllllllllllllllllllIIIIIlIlIl = llllllllllllllllllllllIIIIIlIIll;
                        break;
                    }
                }
                llllllllllllllllllllllIIIIIllIlI.next();
            }
            return llllllllllllllllllllllIIIIIllIll;
        }
        
        private float randomize(final float lllllllllllllllllllllIlllllllIII) {
            return lllllllllllllllllllllIlllllllIII + (float)Math.random() * OutlineWobbleEffect.this.amplitude * 2.0f - 1.0f;
        }
    }
}
