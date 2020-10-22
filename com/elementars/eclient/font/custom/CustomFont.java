package com.elementars.eclient.font.custom;

import net.minecraft.client.renderer.texture.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import java.util.*;

public class CustomFont extends CFont
{
    private final /* synthetic */ int[] colorCode;
    protected /* synthetic */ DynamicTexture texItalicBold;
    protected /* synthetic */ CharData[] boldChars;
    protected /* synthetic */ DynamicTexture texItalic;
    protected /* synthetic */ CharData[] italicChars;
    protected /* synthetic */ CharData[] boldItalicChars;
    protected /* synthetic */ DynamicTexture texBold;
    
    @Override
    public void setAntiAlias(final boolean llllllllllllllllIlllIlIIIIlIIIIl) {
        super.setAntiAlias(llllllllllllllllIlllIlIIIIlIIIIl);
        this.setupBoldItalicIDs();
    }
    
    public List<String> formatString(final String llllllllllllllllIlllIIllIlllllIl, final double llllllllllllllllIlllIIllIllllIll) {
        final List llllllllllllllllIlllIIlllIIIIlll = new ArrayList();
        String llllllllllllllllIlllIIlllIIIIlIl = "";
        char llllllllllllllllIlllIIlllIIIIIll = '\uffff';
        final char[] llllllllllllllllIlllIIlllIIIIIIl = llllllllllllllllIlllIIllIlllllIl.toCharArray();
        for (int llllllllllllllllIlllIIlllIIIllIl = 0; llllllllllllllllIlllIIlllIIIllIl < llllllllllllllllIlllIIlllIIIIIIl.length; ++llllllllllllllllIlllIIlllIIIllIl) {
            final char llllllllllllllllIlllIIlllIIIlllI = llllllllllllllllIlllIIlllIIIIIIl[llllllllllllllllIlllIIlllIIIllIl];
            if (llllllllllllllllIlllIIlllIIIlllI == '§' && llllllllllllllllIlllIIlllIIIllIl < llllllllllllllllIlllIIlllIIIIIIl.length - 1) {
                llllllllllllllllIlllIIlllIIIIIll = llllllllllllllllIlllIIlllIIIIIIl[llllllllllllllllIlllIIlllIIIllIl + 1];
            }
            if (this.getStringWidth(String.valueOf(new StringBuilder().append(llllllllllllllllIlllIIlllIIIIlIl).append(llllllllllllllllIlllIIlllIIIlllI))) < llllllllllllllllIlllIIllIllllIll) {
                llllllllllllllllIlllIIlllIIIIlIl = String.valueOf(new StringBuilder().append(llllllllllllllllIlllIIlllIIIIlIl).append(llllllllllllllllIlllIIlllIIIlllI));
            }
            else {
                llllllllllllllllIlllIIlllIIIIlll.add(llllllllllllllllIlllIIlllIIIIlIl);
                llllllllllllllllIlllIIlllIIIIlIl = String.valueOf(new StringBuilder().append("§").append(llllllllllllllllIlllIIlllIIIIIll).append(llllllllllllllllIlllIIlllIIIlllI));
            }
        }
        if (llllllllllllllllIlllIIlllIIIIlIl.length() > 0) {
            llllllllllllllllIlllIIlllIIIIlll.add(llllllllllllllllIlllIIlllIIIIlIl);
        }
        return (List<String>)llllllllllllllllIlllIIlllIIIIlll;
    }
    
    private void setupBoldItalicIDs() {
        this.texBold = this.setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
        this.texItalic = this.setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
        this.texItalicBold = this.setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics, this.boldItalicChars);
    }
    
    public List<String> wrapWords(final String llllllllllllllllIlllIIlllIllIlII, final double llllllllllllllllIlllIIlllIllIlll) {
        final List llllllllllllllllIlllIIlllIllIllI = new ArrayList();
        if (this.getStringWidth(llllllllllllllllIlllIIlllIllIlII) > llllllllllllllllIlllIIlllIllIlll) {
            final String[] llllllllllllllllIlllIIlllIlllllI = llllllllllllllllIlllIIlllIllIlII.split(" ");
            String llllllllllllllllIlllIIlllIllllII = "";
            char llllllllllllllllIlllIIlllIlllIlI = '\uffff';
            int llllllllllllllllIlllIIlllIlIllIl = (Object)llllllllllllllllIlllIIlllIlllllI;
            final int length = llllllllllllllllIlllIIlllIlIllIl.length;
            for (short llllllllllllllllIlllIIlllIlIlIlI = 0; llllllllllllllllIlllIIlllIlIlIlI < length; ++llllllllllllllllIlllIIlllIlIlIlI) {
                final String llllllllllllllllIlllIIllllIIIIlI = llllllllllllllllIlllIIlllIlIllIl[llllllllllllllllIlllIIlllIlIlIlI];
                for (int llllllllllllllllIlllIIllllIIIlII = 0; llllllllllllllllIlllIIllllIIIlII < llllllllllllllllIlllIIllllIIIIlI.toCharArray().length; ++llllllllllllllllIlllIIllllIIIlII) {
                    final char llllllllllllllllIlllIIllllIIIllI = llllllllllllllllIlllIIllllIIIIlI.toCharArray()[llllllllllllllllIlllIIllllIIIlII];
                    if (llllllllllllllllIlllIIllllIIIllI == '§' && llllllllllllllllIlllIIllllIIIlII < llllllllllllllllIlllIIllllIIIIlI.toCharArray().length - 1) {
                        llllllllllllllllIlllIIlllIlllIlI = llllllllllllllllIlllIIllllIIIIlI.toCharArray()[llllllllllllllllIlllIIllllIIIlII + 1];
                    }
                }
                if (this.getStringWidth(String.valueOf(new StringBuilder().append(llllllllllllllllIlllIIlllIllllII).append(llllllllllllllllIlllIIllllIIIIlI).append(" "))) < llllllllllllllllIlllIIlllIllIlll) {
                    llllllllllllllllIlllIIlllIllllII = String.valueOf(new StringBuilder().append(llllllllllllllllIlllIIlllIllllII).append(llllllllllllllllIlllIIllllIIIIlI).append(" "));
                }
                else {
                    llllllllllllllllIlllIIlllIllIllI.add(llllllllllllllllIlllIIlllIllllII);
                    llllllllllllllllIlllIIlllIllllII = String.valueOf(new StringBuilder().append("§").append(llllllllllllllllIlllIIlllIlllIlI).append(llllllllllllllllIlllIIllllIIIIlI).append(" "));
                }
            }
            if (llllllllllllllllIlllIIlllIllllII.length() > 0) {
                if (this.getStringWidth(llllllllllllllllIlllIIlllIllllII) < llllllllllllllllIlllIIlllIllIlll) {
                    llllllllllllllllIlllIIlllIllIllI.add(String.valueOf(new StringBuilder().append("§").append(llllllllllllllllIlllIIlllIlllIlI).append(llllllllllllllllIlllIIlllIllllII).append(" ")));
                    llllllllllllllllIlllIIlllIllllII = "";
                }
                else {
                    llllllllllllllllIlllIIlllIlIllIl = (int)this.formatString(llllllllllllllllIlllIIlllIllllII, llllllllllllllllIlllIIlllIllIlll).iterator();
                    while (((Iterator)llllllllllllllllIlllIIlllIlIllIl).hasNext()) {
                        final String llllllllllllllllIlllIIllllIIIIII = ((Iterator<String>)llllllllllllllllIlllIIlllIlIllIl).next();
                        llllllllllllllllIlllIIlllIllIllI.add(llllllllllllllllIlllIIllllIIIIII);
                    }
                }
            }
        }
        else {
            llllllllllllllllIlllIIlllIllIllI.add(llllllllllllllllIlllIIlllIllIlII);
        }
        return (List<String>)llllllllllllllllIlllIIlllIllIllI;
    }
    
    public float drawStringWithShadow(final String llllllllllllllllIlllIllIIIIIIIIl, final double llllllllllllllllIlllIlIlllllllll, final double llllllllllllllllIlllIlIlllllllII, final int llllllllllllllllIlllIlIllllIlIlI) {
        final float llllllllllllllllIlllIlIlllllIllI = this.drawString(llllllllllllllllIlllIllIIIIIIIIl, llllllllllllllllIlllIlIlllllllll + 1.0, llllllllllllllllIlllIlIlllllllII + 1.0, llllllllllllllllIlllIlIllllIlIlI, true);
        return Math.max(llllllllllllllllIlllIlIlllllIllI, this.drawString(llllllllllllllllIlllIllIIIIIIIIl, llllllllllllllllIlllIlIlllllllll, llllllllllllllllIlllIlIlllllllII, llllllllllllllllIlllIlIllllIlIlI, false));
    }
    
    public float drawCenteredString(final String llllllllllllllllIlllIlIllIllIlll, final float llllllllllllllllIlllIlIllIllIllI, final float llllllllllllllllIlllIlIllIllIlII, final int llllllllllllllllIlllIlIllIlIIlll) {
        return this.drawString(llllllllllllllllIlllIlIllIllIlll, llllllllllllllllIlllIlIllIllIllI - this.getStringWidth(llllllllllllllllIlllIlIllIllIlll) / 2, llllllllllllllllIlllIlIllIllIlII, llllllllllllllllIlllIlIllIlIIlll);
    }
    
    public float drawString(final String llllllllllllllllIlllIlIIlllIIIlI, final double llllllllllllllllIlllIlIIlllIIIII, final double llllllllllllllllIlllIlIIllIlllll, int llllllllllllllllIlllIlIIllIIIIll, final boolean llllllllllllllllIlllIlIIllIlllII) {
        double llllllllllllllllIlllIlIIllIllIlI = llllllllllllllllIlllIlIIlllIIIII;
        double llllllllllllllllIlllIlIIllIllIII = llllllllllllllllIlllIlIIllIlllll;
        if (!com.elementars.eclient.module.core.CustomFont.shadow.getValue() && llllllllllllllllIlllIlIIllIlllII) {
            llllllllllllllllIlllIlIIllIllIlI -= 0.5;
            llllllllllllllllIlllIlIIllIllIII -= 0.5;
        }
        --llllllllllllllllIlllIlIIllIllIlI;
        if (llllllllllllllllIlllIlIIlllIIIlI == null) {
            return 0.0f;
        }
        if (llllllllllllllllIlllIlIIllIIIIll == 553648127) {
            llllllllllllllllIlllIlIIllIIIIll = 16777215;
        }
        if ((llllllllllllllllIlllIlIIllIIIIll & 0xFC000000) == 0x0) {
            llllllllllllllllIlllIlIIllIIIIll |= 0xFF000000;
        }
        if (llllllllllllllllIlllIlIIllIlllII) {
            llllllllllllllllIlllIlIIllIIIIll = ((llllllllllllllllIlllIlIIllIIIIll & 0xFCFCFC) >> 2 | (llllllllllllllllIlllIlIIllIIIIll & 0xFF000000));
        }
        CharData[] llllllllllllllllIlllIlIIllIlIllI = this.charData;
        final float llllllllllllllllIlllIlIIllIlIlII = (float)(llllllllllllllllIlllIlIIllIIIIll >> 24 & 0xFF) / 255.0f;
        boolean llllllllllllllllIlllIlIIllIlIIlI = false;
        boolean llllllllllllllllIlllIlIIllIlIIII = false;
        boolean llllllllllllllllIlllIlIIllIIlllI = false;
        boolean llllllllllllllllIlllIlIIllIIllII = false;
        final boolean llllllllllllllllIlllIlIIllIIlIlI = true;
        llllllllllllllllIlllIlIIllIllIlI *= 2.0;
        llllllllllllllllIlllIlIIllIllIII = (llllllllllllllllIlllIlIIllIllIII - 3.0) * 2.0;
        GL11.glPushMatrix();
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.color((float)(llllllllllllllllIlllIlIIllIIIIll >> 16 & 0xFF) / 255.0f, (float)(llllllllllllllllIlllIlIIllIIIIll >> 8 & 0xFF) / 255.0f, (float)(llllllllllllllllIlllIlIIllIIIIll & 0xFF) / 255.0f, llllllllllllllllIlllIlIIllIlIlII);
        final int llllllllllllllllIlllIlIIlllIIllI = llllllllllllllllIlllIlIIlllIIIlI.length();
        GlStateManager.enableTexture2D();
        GlStateManager.bindTexture(this.tex.getGlTextureId());
        GL11.glBindTexture(3553, this.tex.getGlTextureId());
        for (int llllllllllllllllIlllIlIIlllIlIII = 0; llllllllllllllllIlllIlIIlllIlIII < llllllllllllllllIlllIlIIlllIIllI; ++llllllllllllllllIlllIlIIlllIlIII) {
            final char llllllllllllllllIlllIlIIlllIlIIl = llllllllllllllllIlllIlIIlllIIIlI.charAt(llllllllllllllllIlllIlIIlllIlIII);
            if (llllllllllllllllIlllIlIIlllIlIIl == '§' && llllllllllllllllIlllIlIIlllIlIII < llllllllllllllllIlllIlIIlllIIllI) {
                int llllllllllllllllIlllIlIIlllIlIlI = 21;
                try {
                    llllllllllllllllIlllIlIIlllIlIlI = "0123456789abcdefklmnor".indexOf(llllllllllllllllIlllIlIIlllIIIlI.charAt(llllllllllllllllIlllIlIIlllIlIII + 1));
                }
                catch (Exception llllllllllllllllIlllIlIIlllIllII) {
                    llllllllllllllllIlllIlIIlllIllII.printStackTrace();
                }
                if (llllllllllllllllIlllIlIIlllIlIlI < 16) {
                    llllllllllllllllIlllIlIIllIlIIlI = false;
                    llllllllllllllllIlllIlIIllIlIIII = false;
                    llllllllllllllllIlllIlIIllIIllII = false;
                    llllllllllllllllIlllIlIIllIIlllI = false;
                    GlStateManager.bindTexture(this.tex.getGlTextureId());
                    llllllllllllllllIlllIlIIllIlIllI = this.charData;
                    if (llllllllllllllllIlllIlIIlllIlIlI < 0 || llllllllllllllllIlllIlIIlllIlIlI > 15) {
                        llllllllllllllllIlllIlIIlllIlIlI = 15;
                    }
                    if (llllllllllllllllIlllIlIIllIlllII) {
                        llllllllllllllllIlllIlIIlllIlIlI += 16;
                    }
                    final int llllllllllllllllIlllIlIIlllIlIll = this.colorCode[llllllllllllllllIlllIlIIlllIlIlI];
                    GlStateManager.color((llllllllllllllllIlllIlIIlllIlIll >> 16 & 0xFF) / 255.0f, (llllllllllllllllIlllIlIIlllIlIll >> 8 & 0xFF) / 255.0f, (llllllllllllllllIlllIlIIlllIlIll & 0xFF) / 255.0f, llllllllllllllllIlllIlIIllIlIlII);
                }
                else if (llllllllllllllllIlllIlIIlllIlIlI == 17) {
                    llllllllllllllllIlllIlIIllIlIIlI = true;
                    if (llllllllllllllllIlllIlIIllIlIIII) {
                        GlStateManager.bindTexture(this.texItalicBold.getGlTextureId());
                        llllllllllllllllIlllIlIIllIlIllI = this.boldItalicChars;
                    }
                    else {
                        GlStateManager.bindTexture(this.texBold.getGlTextureId());
                        llllllllllllllllIlllIlIIllIlIllI = this.boldChars;
                    }
                }
                else if (llllllllllllllllIlllIlIIlllIlIlI == 18) {
                    llllllllllllllllIlllIlIIllIIlllI = true;
                }
                else if (llllllllllllllllIlllIlIIlllIlIlI == 19) {
                    llllllllllllllllIlllIlIIllIIllII = true;
                }
                else if (llllllllllllllllIlllIlIIlllIlIlI == 20) {
                    llllllllllllllllIlllIlIIllIlIIII = true;
                    if (llllllllllllllllIlllIlIIllIlIIlI) {
                        GlStateManager.bindTexture(this.texItalicBold.getGlTextureId());
                        llllllllllllllllIlllIlIIllIlIllI = this.boldItalicChars;
                    }
                    else {
                        GlStateManager.bindTexture(this.texItalic.getGlTextureId());
                        llllllllllllllllIlllIlIIllIlIllI = this.italicChars;
                    }
                }
                else if (llllllllllllllllIlllIlIIlllIlIlI == 21) {
                    llllllllllllllllIlllIlIIllIlIIlI = false;
                    llllllllllllllllIlllIlIIllIlIIII = false;
                    llllllllllllllllIlllIlIIllIIllII = false;
                    llllllllllllllllIlllIlIIllIIlllI = false;
                    GlStateManager.color((float)(llllllllllllllllIlllIlIIllIIIIll >> 16 & 0xFF) / 255.0f, (float)(llllllllllllllllIlllIlIIllIIIIll >> 8 & 0xFF) / 255.0f, (float)(llllllllllllllllIlllIlIIllIIIIll & 0xFF) / 255.0f, llllllllllllllllIlllIlIIllIlIlII);
                    GlStateManager.bindTexture(this.tex.getGlTextureId());
                    llllllllllllllllIlllIlIIllIlIllI = this.charData;
                }
                ++llllllllllllllllIlllIlIIlllIlIII;
            }
            else if (llllllllllllllllIlllIlIIlllIlIIl < llllllllllllllllIlllIlIIllIlIllI.length && llllllllllllllllIlllIlIIlllIlIIl >= '\0') {
                GL11.glBegin(4);
                this.drawChar(llllllllllllllllIlllIlIIllIlIllI, llllllllllllllllIlllIlIIlllIlIIl, (float)llllllllllllllllIlllIlIIllIllIlI, (float)llllllllllllllllIlllIlIIllIllIII);
                GL11.glEnd();
                if (llllllllllllllllIlllIlIIllIIlllI) {
                    this.drawLine(llllllllllllllllIlllIlIIllIllIlI, llllllllllllllllIlllIlIIllIllIII + llllllllllllllllIlllIlIIllIlIllI[llllllllllllllllIlllIlIIlllIlIIl].height / 2, llllllllllllllllIlllIlIIllIllIlI + llllllllllllllllIlllIlIIllIlIllI[llllllllllllllllIlllIlIIlllIlIIl].width - 8.0, llllllllllllllllIlllIlIIllIllIII + llllllllllllllllIlllIlIIllIlIllI[llllllllllllllllIlllIlIIlllIlIIl].height / 2, 1.0f);
                }
                if (llllllllllllllllIlllIlIIllIIllII) {
                    this.drawLine(llllllllllllllllIlllIlIIllIllIlI, llllllllllllllllIlllIlIIllIllIII + llllllllllllllllIlllIlIIllIlIllI[llllllllllllllllIlllIlIIlllIlIIl].height - 2.0, llllllllllllllllIlllIlIIllIllIlI + llllllllllllllllIlllIlIIllIlIllI[llllllllllllllllIlllIlIIlllIlIIl].width - 8.0, llllllllllllllllIlllIlIIllIllIII + llllllllllllllllIlllIlIIllIlIllI[llllllllllllllllIlllIlIIlllIlIIl].height - 2.0, 1.0f);
                }
                llllllllllllllllIlllIlIIllIllIlI += llllllllllllllllIlllIlIIllIlIllI[llllllllllllllllIlllIlIIlllIlIIl].width - 8 + this.charOffset;
            }
        }
        GL11.glHint(3155, 4352);
        GL11.glPopMatrix();
        return (float)llllllllllllllllIlllIlIIllIllIlI / 2.0f;
    }
    
    public float drawCenteredStringWithShadow(final String llllllllllllllllIlllIlIlIllllIll, final float llllllllllllllllIlllIlIllIIIlIII, final float llllllllllllllllIlllIlIlIlllIllI, final int llllllllllllllllIlllIlIllIIIIIll) {
        final float llllllllllllllllIlllIlIllIIIIIII = this.drawString(llllllllllllllllIlllIlIlIllllIll, llllllllllllllllIlllIlIllIIIlIII - this.getStringWidth(llllllllllllllllIlllIlIlIllllIll) / 2 + 1.0, llllllllllllllllIlllIlIlIlllIllI + 1.0, llllllllllllllllIlllIlIllIIIIIll, true);
        return this.drawString(llllllllllllllllIlllIlIlIllllIll, llllllllllllllllIlllIlIllIIIlIII - this.getStringWidth(llllllllllllllllIlllIlIlIllllIll) / 2, llllllllllllllllIlllIlIlIlllIllI, llllllllllllllllIlllIlIllIIIIIll);
    }
    
    private void drawLine(final double llllllllllllllllIlllIlIIIIIIIIlI, final double llllllllllllllllIlllIlIIIIIIIllI, final double llllllllllllllllIlllIlIIIIIIIlIl, final double llllllllllllllllIlllIIllllllllll, final float llllllllllllllllIlllIlIIIIIIIIll) {
        GL11.glDisable(3553);
        GL11.glLineWidth(llllllllllllllllIlllIlIIIIIIIIll);
        GL11.glBegin(1);
        GL11.glVertex2d(llllllllllllllllIlllIlIIIIIIIIlI, llllllllllllllllIlllIlIIIIIIIllI);
        GL11.glVertex2d(llllllllllllllllIlllIlIIIIIIIlIl, llllllllllllllllIlllIIllllllllll);
        GL11.glEnd();
        GL11.glEnable(3553);
    }
    
    @Override
    public int getStringWidth(final String llllllllllllllllIlllIlIIIlIIIIll) {
        if (llllllllllllllllIlllIlIIIlIIIIll == null) {
            return 0;
        }
        int llllllllllllllllIlllIlIIIlIIIIlI = 0;
        CharData[] llllllllllllllllIlllIlIIIlIIIIIl = this.charData;
        boolean llllllllllllllllIlllIlIIIlIIIIII = false;
        boolean llllllllllllllllIlllIlIIIIllllll = false;
        for (int llllllllllllllllIlllIlIIIlIIIllI = llllllllllllllllIlllIlIIIlIIIIll.length(), llllllllllllllllIlllIlIIIlIIIlIl = 0; llllllllllllllllIlllIlIIIlIIIlIl < llllllllllllllllIlllIlIIIlIIIllI; ++llllllllllllllllIlllIlIIIlIIIlIl) {
            final char llllllllllllllllIlllIlIIIlIIIlll = llllllllllllllllIlllIlIIIlIIIIll.charAt(llllllllllllllllIlllIlIIIlIIIlIl);
            if (llllllllllllllllIlllIlIIIlIIIlll == '§' && llllllllllllllllIlllIlIIIlIIIlIl < llllllllllllllllIlllIlIIIlIIIllI) {
                final int llllllllllllllllIlllIlIIIlIIlIIl = "0123456789abcdefklmnor".indexOf(llllllllllllllllIlllIlIIIlIIIlll);
                if (llllllllllllllllIlllIlIIIlIIlIIl < 16) {
                    llllllllllllllllIlllIlIIIlIIIIII = false;
                    llllllllllllllllIlllIlIIIIllllll = false;
                }
                else if (llllllllllllllllIlllIlIIIlIIlIIl == 17) {
                    llllllllllllllllIlllIlIIIlIIIIII = true;
                    if (llllllllllllllllIlllIlIIIIllllll) {
                        llllllllllllllllIlllIlIIIlIIIIIl = this.boldItalicChars;
                    }
                    else {
                        llllllllllllllllIlllIlIIIlIIIIIl = this.boldChars;
                    }
                }
                else if (llllllllllllllllIlllIlIIIlIIlIIl == 20) {
                    llllllllllllllllIlllIlIIIIllllll = true;
                    if (llllllllllllllllIlllIlIIIlIIIIII) {
                        llllllllllllllllIlllIlIIIlIIIIIl = this.boldItalicChars;
                    }
                    else {
                        llllllllllllllllIlllIlIIIlIIIIIl = this.italicChars;
                    }
                }
                else if (llllllllllllllllIlllIlIIIlIIlIIl == 21) {
                    llllllllllllllllIlllIlIIIlIIIIII = false;
                    llllllllllllllllIlllIlIIIIllllll = false;
                    llllllllllllllllIlllIlIIIlIIIIIl = this.charData;
                }
                ++llllllllllllllllIlllIlIIIlIIIlIl;
            }
            else if (llllllllllllllllIlllIlIIIlIIIlll < llllllllllllllllIlllIlIIIlIIIIIl.length && llllllllllllllllIlllIlIIIlIIIlll >= '\0') {
                llllllllllllllllIlllIlIIIlIIIIlI += llllllllllllllllIlllIlIIIlIIIIIl[llllllllllllllllIlllIlIIIlIIIlll].width - 8 + this.charOffset;
            }
        }
        return llllllllllllllllIlllIlIIIlIIIIlI / 2;
    }
    
    @Override
    public void setFractionalMetrics(final boolean llllllllllllllllIlllIlIIIIIllIIl) {
        super.setFractionalMetrics(llllllllllllllllIlllIlIIIIIllIIl);
        this.setupBoldItalicIDs();
    }
    
    private void setupMinecraftColorcodes() {
        for (int llllllllllllllllIlllIIllIlIllIIl = 0; llllllllllllllllIlllIIllIlIllIIl < 32; ++llllllllllllllllIlllIIllIlIllIIl) {
            final int llllllllllllllllIlllIIllIlIllllI = (llllllllllllllllIlllIIllIlIllIIl >> 3 & 0x1) * 85;
            int llllllllllllllllIlllIIllIlIlllIl = (llllllllllllllllIlllIIllIlIllIIl >> 2 & 0x1) * 170 + llllllllllllllllIlllIIllIlIllllI;
            int llllllllllllllllIlllIIllIlIlllII = (llllllllllllllllIlllIIllIlIllIIl >> 1 & 0x1) * 170 + llllllllllllllllIlllIIllIlIllllI;
            int llllllllllllllllIlllIIllIlIllIll = (llllllllllllllllIlllIIllIlIllIIl >> 0 & 0x1) * 170 + llllllllllllllllIlllIIllIlIllllI;
            if (llllllllllllllllIlllIIllIlIllIIl == 6) {
                llllllllllllllllIlllIIllIlIlllIl += 85;
            }
            if (llllllllllllllllIlllIIllIlIllIIl >= 16) {
                llllllllllllllllIlllIIllIlIlllIl /= 4;
                llllllllllllllllIlllIIllIlIlllII /= 4;
                llllllllllllllllIlllIIllIlIllIll /= 4;
            }
            this.colorCode[llllllllllllllllIlllIIllIlIllIIl] = ((llllllllllllllllIlllIIllIlIlllIl & 0xFF) << 16 | (llllllllllllllllIlllIIllIlIlllII & 0xFF) << 8 | (llllllllllllllllIlllIIllIlIllIll & 0xFF));
        }
    }
    
    public CustomFont(final Font llllllllllllllllIlllIllIIIlIIIII, final boolean llllllllllllllllIlllIllIIIIllIll, final boolean llllllllllllllllIlllIllIIIIllIlI) {
        super(llllllllllllllllIlllIllIIIlIIIII, llllllllllllllllIlllIllIIIIllIll, llllllllllllllllIlllIllIIIIllIlI);
        this.boldChars = new CharData[256];
        this.italicChars = new CharData[256];
        this.boldItalicChars = new CharData[256];
        this.colorCode = new int[32];
        this.setupMinecraftColorcodes();
        this.setupBoldItalicIDs();
    }
    
    @Override
    public void setFont(final Font llllllllllllllllIlllIlIIIIlIIlll) {
        super.setFont(llllllllllllllllIlllIlIIIIlIIlll);
        this.setupBoldItalicIDs();
    }
    
    public float drawString(final String llllllllllllllllIlllIlIlllIlllII, final float llllllllllllllllIlllIlIlllIllIll, final float llllllllllllllllIlllIlIlllIIlllI, final int llllllllllllllllIlllIlIlllIIlIll) {
        return this.drawString(llllllllllllllllIlllIlIlllIlllII, llllllllllllllllIlllIlIlllIllIll, llllllllllllllllIlllIlIlllIIlllI, llllllllllllllllIlllIlIlllIIlIll, false);
    }
}
