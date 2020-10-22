package org.newdawn.slick.font.effects;

import java.awt.image.*;
import java.util.*;
import org.newdawn.slick.*;
import org.newdawn.slick.font.*;
import java.awt.*;

public class ShadowEffect implements ConfigurableEffect
{
    private /* synthetic */ float opacity;
    private /* synthetic */ int blurPasses;
    private /* synthetic */ float xDistance;
    private /* synthetic */ int blurKernelSize;
    private /* synthetic */ float yDistance;
    public static final /* synthetic */ float[][] GAUSSIAN_BLUR_KERNELS;
    private /* synthetic */ Color color;
    
    private void blur(final BufferedImage llIlllllIlllIl) {
        final float[] llIllllllIIlIl = ShadowEffect.GAUSSIAN_BLUR_KERNELS[this.blurKernelSize - 1];
        final Kernel llIllllllIIlII = new Kernel(llIllllllIIlIl.length, 1, llIllllllIIlIl);
        final Kernel llIllllllIIIll = new Kernel(1, llIllllllIIlIl.length, llIllllllIIlIl);
        final RenderingHints llIllllllIIIlI = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        final ConvolveOp llIllllllIIIIl = new ConvolveOp(llIllllllIIlII, 1, llIllllllIIIlI);
        final ConvolveOp llIllllllIIIII = new ConvolveOp(llIllllllIIIll, 1, llIllllllIIIlI);
        final BufferedImage llIlllllIlllll = EffectUtil.getScratchImage();
        for (int llIllllllIlIII = 0; llIllllllIlIII < this.blurPasses; ++llIllllllIlIII) {
            llIllllllIIIIl.filter(llIlllllIlllIl, llIlllllIlllll);
            llIllllllIIIII.filter(llIlllllIlllll, llIlllllIlllIl);
        }
    }
    
    public int getBlurPasses() {
        return this.blurPasses;
    }
    
    private static float[][] generatePascalsTriangle(int llIlllIllIIllI) {
        if (llIlllIllIIllI < 2) {
            llIlllIllIIllI = 2;
        }
        final float[][] llIlllIllIIlll = new float[llIlllIllIIllI][];
        llIlllIllIIlll[0] = new float[1];
        llIlllIllIIlll[1] = new float[2];
        llIlllIllIIlll[0][0] = 1.0f;
        llIlllIllIIlll[1][0] = 1.0f;
        llIlllIllIIlll[1][1] = 1.0f;
        for (int llIlllIllIlIIl = 2; llIlllIllIlIIl < llIlllIllIIllI; ++llIlllIllIlIIl) {
            (llIlllIllIIlll[llIlllIllIlIIl] = new float[llIlllIllIlIIl + 1])[0] = 1.0f;
            llIlllIllIIlll[llIlllIllIlIIl][llIlllIllIlIIl] = 1.0f;
            for (int llIlllIllIlIlI = 1; llIlllIllIlIlI < llIlllIllIIlll[llIlllIllIlIIl].length - 1; ++llIlllIllIlIlI) {
                llIlllIllIIlll[llIlllIllIlIIl][llIlllIllIlIlI] = llIlllIllIIlll[llIlllIllIlIIl - 1][llIlllIllIlIlI - 1] + llIlllIllIIlll[llIlllIllIlIIl - 1][llIlllIllIlIlI];
            }
        }
        return llIlllIllIIlll;
    }
    
    @Override
    public String toString() {
        return "Shadow";
    }
    
    @Override
    public List getValues() {
        final List llIllllIIlIlll = new ArrayList();
        llIllllIIlIlll.add(EffectUtil.colorValue("Color", this.color));
        llIllllIIlIlll.add(EffectUtil.floatValue("Opacity", this.opacity, 0.0f, 1.0f, "This setting sets the translucency of the shadow."));
        llIllllIIlIlll.add(EffectUtil.floatValue("X distance", this.xDistance, Float.MIN_VALUE, Float.MAX_VALUE, "This setting is the amount of pixels to offset the shadow on the x axis. The glyphs will need padding so the shadow doesn't get clipped."));
        llIllllIIlIlll.add(EffectUtil.floatValue("Y distance", this.yDistance, Float.MIN_VALUE, Float.MAX_VALUE, "This setting is the amount of pixels to offset the shadow on the y axis. The glyphs will need padding so the shadow doesn't get clipped."));
        final List llIllllIIlIllI = new ArrayList();
        llIllllIIlIllI.add(new String[] { "None", "0" });
        for (int llIllllIIllIIl = 2; llIllllIIllIIl < 16; ++llIllllIIllIIl) {
            llIllllIIlIllI.add(new String[] { String.valueOf(llIllllIIllIIl) });
        }
        final String[][] llIllllIIlIlIl = llIllllIIlIllI.toArray(new String[llIllllIIlIllI.size()][]);
        llIllllIIlIlll.add(EffectUtil.optionValue("Blur kernel size", String.valueOf(this.blurKernelSize), llIllllIIlIlIl, "This setting controls how many neighboring pixels are used to blur the shadow. Set to \"None\" for no blur."));
        llIllllIIlIlll.add(EffectUtil.intValue("Blur passes", this.blurPasses, "The setting is the number of times to apply a blur to the shadow. Set to \"0\" for no blur."));
        return llIllllIIlIlll;
    }
    
    public float getOpacity() {
        return this.opacity;
    }
    
    static {
        NUM_KERNELS = 16;
        GAUSSIAN_BLUR_KERNELS = generateGaussianBlurKernels(16);
    }
    
    public void setColor(final Color llIlllllIIlllI) {
        this.color = llIlllllIIlllI;
    }
    
    public void setXDistance(final float llIlllllIIIIll) {
        this.xDistance = llIlllllIIIIll;
    }
    
    @Override
    public void setValues(final List llIllllIIIlIIl) {
        for (final Value llIllllIIIllII : llIllllIIIlIIl) {
            if (llIllllIIIllII.getName().equals("Color")) {
                this.color = (Color)llIllllIIIllII.getObject();
            }
            else if (llIllllIIIllII.getName().equals("Opacity")) {
                this.opacity = (float)llIllllIIIllII.getObject();
            }
            else if (llIllllIIIllII.getName().equals("X distance")) {
                this.xDistance = (float)llIllllIIIllII.getObject();
            }
            else if (llIllllIIIllII.getName().equals("Y distance")) {
                this.yDistance = (float)llIllllIIIllII.getObject();
            }
            else if (llIllllIIIllII.getName().equals("Blur kernel size")) {
                this.blurKernelSize = Integer.parseInt((String)llIllllIIIllII.getObject());
            }
            else {
                if (!llIllllIIIllII.getName().equals("Blur passes")) {
                    continue;
                }
                this.blurPasses = (int)llIllllIIIllII.getObject();
            }
        }
    }
    
    @Override
    public void draw(final BufferedImage llIllllllllllI, Graphics2D llIllllllllIII, final UnicodeFont llIlllllllllII, final Glyph llIlllllllIllI) {
        llIllllllllIII = (double)((Graphics)llIllllllllIII).create();
        ((Graphics2D)llIllllllllIII).translate(this.xDistance, this.yDistance);
        ((Graphics)llIllllllllIII).setColor(new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), Math.round(this.opacity * 255.0f)));
        ((Graphics2D)llIllllllllIII).fill(llIlllllllIllI.getShape());
        for (final Effect lllIIIIIIIIIIl : llIlllllllllII.getEffects()) {
            if (lllIIIIIIIIIIl instanceof OutlineEffect) {
                final Composite lllIIIIIIIIIlI = ((Graphics2D)llIllllllllIII).getComposite();
                ((Graphics2D)llIllllllllIII).setComposite(AlphaComposite.Src);
                ((Graphics2D)llIllllllllIII).setStroke(((OutlineEffect)lllIIIIIIIIIIl).getStroke());
                ((Graphics2D)llIllllllllIII).draw(llIlllllllIllI.getShape());
                ((Graphics2D)llIllllllllIII).setComposite(lllIIIIIIIIIlI);
                break;
            }
        }
        ((Graphics)llIllllllllIII).dispose();
        if (this.blurKernelSize > 1 && this.blurKernelSize < 16 && this.blurPasses > 0) {
            this.blur(llIllllllllllI);
        }
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public float getYDistance() {
        return this.yDistance;
    }
    
    public int getBlurKernelSize() {
        return this.blurKernelSize;
    }
    
    public void setBlurKernelSize(final int llIllllIllIIll) {
        this.blurKernelSize = llIllllIllIIll;
    }
    
    private static float[][] generateGaussianBlurKernels(final int llIlllIllllIII) {
        final float[][] llIlllIlllIlll = generatePascalsTriangle(llIlllIllllIII);
        final float[][] llIlllIlllIllI = new float[llIlllIlllIlll.length][];
        for (int llIlllIllllIIl = 0; llIlllIllllIIl < llIlllIlllIllI.length; ++llIlllIllllIIl) {
            float llIlllIllllIll = 0.0f;
            llIlllIlllIllI[llIlllIllllIIl] = new float[llIlllIlllIlll[llIlllIllllIIl].length];
            for (int llIlllIlllllIl = 0; llIlllIlllllIl < llIlllIlllIlll[llIlllIllllIIl].length; ++llIlllIlllllIl) {
                llIlllIllllIll += llIlllIlllIlll[llIlllIllllIIl][llIlllIlllllIl];
            }
            final float llIlllIllllIlI = 1.0f / llIlllIllllIll;
            for (int llIlllIlllllII = 0; llIlllIlllllII < llIlllIlllIlll[llIlllIllllIIl].length; ++llIlllIlllllII) {
                llIlllIlllIllI[llIlllIllllIIl][llIlllIlllllII] = llIlllIllllIlI * llIlllIlllIlll[llIlllIllllIIl][llIlllIlllllII];
            }
        }
        return llIlllIlllIllI;
    }
    
    public float getXDistance() {
        return this.xDistance;
    }
    
    public void setBlurPasses(final int llIllllIlIlIII) {
        this.blurPasses = llIllllIlIlIII;
    }
    
    public ShadowEffect() {
        this.color = Color.black;
        this.opacity = 0.6f;
        this.xDistance = 2.0f;
        this.yDistance = 2.0f;
        this.blurKernelSize = 0;
        this.blurPasses = 1;
    }
    
    public void setYDistance(final float llIllllIlllIlI) {
        this.yDistance = llIllllIlllIlI;
    }
    
    public void setOpacity(final float llIllllIIlllll) {
        this.opacity = llIllllIIlllll;
    }
    
    public ShadowEffect(final Color lllIIIIIIIlllI, final int lllIIIIIIIllIl, final int lllIIIIIIlIIIl, final float lllIIIIIIlIIII) {
        this.color = Color.black;
        this.opacity = 0.6f;
        this.xDistance = 2.0f;
        this.yDistance = 2.0f;
        this.blurKernelSize = 0;
        this.blurPasses = 1;
        this.color = lllIIIIIIIlllI;
        this.xDistance = (float)lllIIIIIIIllIl;
        this.yDistance = (float)lllIIIIIIlIIIl;
        this.opacity = lllIIIIIIlIIII;
    }
}
