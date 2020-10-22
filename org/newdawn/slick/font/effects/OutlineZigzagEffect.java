package org.newdawn.slick.font.effects;

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class OutlineZigzagEffect extends OutlineEffect
{
    private /* synthetic */ float wavelength;
    private /* synthetic */ float amplitude;
    
    public float getAmplitude() {
        return this.amplitude;
    }
    
    @Override
    public void setValues(final List lllIlllIllIl) {
        super.setValues(lllIlllIllIl);
        for (final ConfigurableEffect.Value lllIllllIIII : lllIlllIllIl) {
            if (lllIllllIIII.getName().equals("Wavelength")) {
                this.wavelength = (float)lllIllllIIII.getObject();
            }
            else {
                if (!lllIllllIIII.getName().equals("Amplitude")) {
                    continue;
                }
                this.amplitude = (float)lllIllllIIII.getObject();
            }
        }
    }
    
    public OutlineZigzagEffect(final int llllIIIIIIII, final Color lllIllllllll) {
        super(llllIIIIIIII, lllIllllllll);
        this.amplitude = 1.0f;
        this.wavelength = 3.0f;
    }
    
    public OutlineZigzagEffect() {
        this.amplitude = 1.0f;
        this.wavelength = 3.0f;
        this.setStroke(new ZigzagStroke());
    }
    
    public void setWavelength(final float llllIIIlIIII) {
        this.wavelength = llllIIIlIIII;
    }
    
    @Override
    public List getValues() {
        final List lllIllllIlll = super.getValues();
        lllIllllIlll.add(EffectUtil.floatValue("Wavelength", this.wavelength, 1.0f, 100.0f, "This setting controls the wavelength of the outline. The smaller the value, the more segments will be used to draw the outline."));
        lllIllllIlll.add(EffectUtil.floatValue("Amplitude", this.amplitude, 0.5f, 50.0f, "This setting controls the amplitude of the outline. The bigger the value, the more the zigzags will vary."));
        return lllIllllIlll;
    }
    
    @Override
    public String toString() {
        return "Outline (Zigzag)";
    }
    
    public void setAmplitude(final float llllIIIIIlll) {
        this.amplitude = llllIIIIIlll;
    }
    
    public float getWavelength() {
        return this.wavelength;
    }
    
    private class ZigzagStroke implements Stroke
    {
        @Override
        public Shape createStrokedShape(final Shape lllllllllllllllllIIlIllIIllIIlIl) {
            final GeneralPath lllllllllllllllllIIlIllIIllIIlII = new GeneralPath();
            final PathIterator lllllllllllllllllIIlIllIIllIIIll = new FlatteningPathIterator(lllllllllllllllllIIlIllIIllIIlIl.getPathIterator(null), 1.0);
            final float[] lllllllllllllllllIIlIllIIllIIIlI = new float[6];
            float lllllllllllllllllIIlIllIIllIIIIl = 0.0f;
            float lllllllllllllllllIIlIllIIllIIIII = 0.0f;
            float lllllllllllllllllIIlIllIIlIlllll = 0.0f;
            float lllllllllllllllllIIlIllIIlIllllI = 0.0f;
            float lllllllllllllllllIIlIllIIlIlllIl = 0.0f;
            float lllllllllllllllllIIlIllIIlIlllII = 0.0f;
            int lllllllllllllllllIIlIllIIlIllIll = 0;
            float lllllllllllllllllIIlIllIIlIllIlI = 0.0f;
            int lllllllllllllllllIIlIllIIlIllIIl = 0;
            while (!lllllllllllllllllIIlIllIIllIIIll.isDone()) {
                lllllllllllllllllIIlIllIIlIllIll = lllllllllllllllllIIlIllIIllIIIll.currentSegment(lllllllllllllllllIIlIllIIllIIIlI);
                switch (lllllllllllllllllIIlIllIIlIllIll) {
                    case 0: {
                        lllllllllllllllllIIlIllIIlIlllll = (lllllllllllllllllIIlIllIIllIIIIl = lllllllllllllllllIIlIllIIllIIIlI[0]);
                        lllllllllllllllllIIlIllIIlIllllI = (lllllllllllllllllIIlIllIIllIIIII = lllllllllllllllllIIlIllIIllIIIlI[1]);
                        lllllllllllllllllIIlIllIIllIIlII.moveTo(lllllllllllllllllIIlIllIIllIIIIl, lllllllllllllllllIIlIllIIllIIIII);
                        lllllllllllllllllIIlIllIIlIllIlI = OutlineZigzagEffect.this.wavelength / 2.0f;
                        break;
                    }
                    case 4: {
                        lllllllllllllllllIIlIllIIllIIIlI[0] = lllllllllllllllllIIlIllIIllIIIIl;
                        lllllllllllllllllIIlIllIIllIIIlI[1] = lllllllllllllllllIIlIllIIllIIIII;
                    }
                    case 1: {
                        lllllllllllllllllIIlIllIIlIlllIl = lllllllllllllllllIIlIllIIllIIIlI[0];
                        lllllllllllllllllIIlIllIIlIlllII = lllllllllllllllllIIlIllIIllIIIlI[1];
                        final float lllllllllllllllllIIlIllIIllIlIIl = lllllllllllllllllIIlIllIIlIlllIl - lllllllllllllllllIIlIllIIlIlllll;
                        final float lllllllllllllllllIIlIllIIllIlIII = lllllllllllllllllIIlIllIIlIlllII - lllllllllllllllllIIlIllIIlIllllI;
                        final float lllllllllllllllllIIlIllIIllIIlll = (float)Math.sqrt(lllllllllllllllllIIlIllIIllIlIIl * lllllllllllllllllIIlIllIIllIlIIl + lllllllllllllllllIIlIllIIllIlIII * lllllllllllllllllIIlIllIIllIlIII);
                        if (lllllllllllllllllIIlIllIIllIIlll >= lllllllllllllllllIIlIllIIlIllIlI) {
                            final float lllllllllllllllllIIlIllIIllIlIlI = 1.0f / lllllllllllllllllIIlIllIIllIIlll;
                            while (lllllllllllllllllIIlIllIIllIIlll >= lllllllllllllllllIIlIllIIlIllIlI) {
                                final float lllllllllllllllllIIlIllIIllIllII = lllllllllllllllllIIlIllIIlIlllll + lllllllllllllllllIIlIllIIlIllIlI * lllllllllllllllllIIlIllIIllIlIIl * lllllllllllllllllIIlIllIIllIlIlI;
                                final float lllllllllllllllllIIlIllIIllIlIll = lllllllllllllllllIIlIllIIlIllllI + lllllllllllllllllIIlIllIIlIllIlI * lllllllllllllllllIIlIllIIllIlIII * lllllllllllllllllIIlIllIIllIlIlI;
                                if ((lllllllllllllllllIIlIllIIlIllIIl & 0x1) == 0x0) {
                                    lllllllllllllllllIIlIllIIllIIlII.lineTo(lllllllllllllllllIIlIllIIllIllII + OutlineZigzagEffect.this.amplitude * lllllllllllllllllIIlIllIIllIlIII * lllllllllllllllllIIlIllIIllIlIlI, lllllllllllllllllIIlIllIIllIlIll - OutlineZigzagEffect.this.amplitude * lllllllllllllllllIIlIllIIllIlIIl * lllllllllllllllllIIlIllIIllIlIlI);
                                }
                                else {
                                    lllllllllllllllllIIlIllIIllIIlII.lineTo(lllllllllllllllllIIlIllIIllIllII - OutlineZigzagEffect.this.amplitude * lllllllllllllllllIIlIllIIllIlIII * lllllllllllllllllIIlIllIIllIlIlI, lllllllllllllllllIIlIllIIllIlIll + OutlineZigzagEffect.this.amplitude * lllllllllllllllllIIlIllIIllIlIIl * lllllllllllllllllIIlIllIIllIlIlI);
                                }
                                lllllllllllllllllIIlIllIIlIllIlI += OutlineZigzagEffect.this.wavelength;
                                ++lllllllllllllllllIIlIllIIlIllIIl;
                            }
                        }
                        lllllllllllllllllIIlIllIIlIllIlI -= lllllllllllllllllIIlIllIIllIIlll;
                        lllllllllllllllllIIlIllIIlIlllll = lllllllllllllllllIIlIllIIlIlllIl;
                        lllllllllllllllllIIlIllIIlIllllI = lllllllllllllllllIIlIllIIlIlllII;
                        if (lllllllllllllllllIIlIllIIlIllIll == 4) {
                            lllllllllllllllllIIlIllIIllIIlII.closePath();
                            break;
                        }
                        break;
                    }
                }
                lllllllllllllllllIIlIllIIllIIIll.next();
            }
            return new BasicStroke(OutlineZigzagEffect.this.getWidth(), 2, OutlineZigzagEffect.this.getJoin()).createStrokedShape(lllllllllllllllllIIlIllIIllIIlII);
        }
    }
}
