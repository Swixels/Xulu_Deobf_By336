package com.elementars.eclient.font;

import net.minecraft.client.gui.*;
import java.awt.*;
import net.minecraft.client.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.resources.*;
import net.minecraft.util.*;
import java.util.*;

public class XFontRenderer extends FontRenderer
{
    private /* synthetic */ XFont italicFont;
    private /* synthetic */ boolean bidi;
    private /* synthetic */ String colorcodeIdentifiers;
    private final /* synthetic */ int[] colorCode;
    private /* synthetic */ XFont boldItalicFont;
    public final /* synthetic */ Random fontRandom;
    private /* synthetic */ XFont font;
    private /* synthetic */ XFont boldFont;
    private final /* synthetic */ Color[] customColorCodes;
    
    private int sizeStringToWidth(final String lllllllllllllllllIIIlIIlIlIlllII, final int lllllllllllllllllIIIlIIlIllIIIlI) {
        final int lllllllllllllllllIIIlIIlIllIIIIl = lllllllllllllllllIIIlIIlIlIlllII.length();
        int lllllllllllllllllIIIlIIlIllIIIII = 0;
        int lllllllllllllllllIIIlIIlIlIlllll = 0;
        int lllllllllllllllllIIIlIIlIlIllllI = -1;
        boolean lllllllllllllllllIIIlIIlIllIIlIl = false;
        while (lllllllllllllllllIIIlIIlIlIlllll < lllllllllllllllllIIIlIIlIllIIIIl) {
            final char lllllllllllllllllIIIlIIlIllIIllI = lllllllllllllllllIIIlIIlIlIlllII.charAt(lllllllllllllllllIIIlIIlIlIlllll);
            Label_0167: {
                switch (lllllllllllllllllIIIlIIlIllIIllI) {
                    case '\n': {
                        --lllllllllllllllllIIIlIIlIlIlllll;
                        break Label_0167;
                    }
                    case '§': {
                        if (lllllllllllllllllIIIlIIlIlIlllll < lllllllllllllllllIIIlIIlIllIIIIl - 1) {
                            ++lllllllllllllllllIIIlIIlIlIlllll;
                            final char lllllllllllllllllIIIlIIlIllIIlll = lllllllllllllllllIIIlIIlIlIlllII.charAt(lllllllllllllllllIIIlIIlIlIlllll);
                            if (lllllllllllllllllIIIlIIlIllIIlll != 'l' && lllllllllllllllllIIIlIIlIllIIlll != 'L') {
                                if (lllllllllllllllllIIIlIIlIllIIlll == 'r' || lllllllllllllllllIIIlIIlIllIIlll == 'R' || isFormatColor(lllllllllllllllllIIIlIIlIllIIlll)) {
                                    lllllllllllllllllIIIlIIlIllIIlIl = false;
                                }
                            }
                            else {
                                lllllllllllllllllIIIlIIlIllIIlIl = true;
                            }
                        }
                        break Label_0167;
                    }
                    case ' ': {
                        lllllllllllllllllIIIlIIlIlIllllI = lllllllllllllllllIIIlIIlIlIlllll;
                        break;
                    }
                }
                lllllllllllllllllIIIlIIlIllIIIII += this.getStringWidth(Character.toString(lllllllllllllllllIIIlIIlIllIIllI));
                if (lllllllllllllllllIIIlIIlIllIIlIl) {
                    ++lllllllllllllllllIIIlIIlIllIIIII;
                }
            }
            if (lllllllllllllllllIIIlIIlIllIIllI == '\n') {
                lllllllllllllllllIIIlIIlIlIllllI = ++lllllllllllllllllIIIlIIlIlIlllll;
                break;
            }
            if (lllllllllllllllllIIIlIIlIllIIIII > lllllllllllllllllIIIlIIlIllIIIlI) {
                break;
            }
            ++lllllllllllllllllIIIlIIlIlIlllll;
        }
        return (lllllllllllllllllIIIlIIlIlIlllll != lllllllllllllllllIIIlIIlIllIIIIl && lllllllllllllllllIIIlIIlIlIllllI != -1 && lllllllllllllllllIIIlIIlIlIllllI < lllllllllllllllllIIIlIIlIlIlllll) ? lllllllllllllllllIIIlIIlIlIllllI : lllllllllllllllllIIIlIIlIlIlllll;
    }
    
    public void drawCenteredString(final String lllllllllllllllllIIIlIlIIlIIlIII, final int lllllllllllllllllIIIlIlIIlIIIlll, final int lllllllllllllllllIIIlIlIIlIIllII, final int lllllllllllllllllIIIlIlIIlIIlIll, final boolean lllllllllllllllllIIIlIlIIlIIlIlI) {
        if (lllllllllllllllllIIIlIlIIlIIlIlI) {
            this.drawStringWithShadow(lllllllllllllllllIIIlIlIIlIIlIII, lllllllllllllllllIIIlIlIIlIIIlll - this.getStringWidth(lllllllllllllllllIIIlIlIIlIIlIII) / 2, lllllllllllllllllIIIlIlIIlIIllII, lllllllllllllllllIIIlIlIIlIIlIll);
        }
        else {
            this.drawString(lllllllllllllllllIIIlIlIIlIIlIII, lllllllllllllllllIIIlIlIIlIIIlll - this.getStringWidth(lllllllllllllllllIIIlIlIIlIIlIII) / 2, lllllllllllllllllIIIlIlIIlIIllII, lllllllllllllllllIIIlIlIIlIIlIll);
        }
    }
    
    public XFontRenderer(final Font lllllllllllllllllIIIlIlIIllllIlI, final boolean lllllllllllllllllIIIlIlIIlllIlIl, final int lllllllllllllllllIIIlIlIIllllIII) {
        super(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/font/ascii.png"), Minecraft.getMinecraft().getTextureManager(), false);
        this.fontRandom = new Random();
        this.customColorCodes = new Color[256];
        this.colorCode = new int[32];
        this.colorcodeIdentifiers = "0123456789abcdefklmnor";
        this.setFont(lllllllllllllllllIIIlIlIIllllIlI, lllllllllllllllllIIIlIlIIlllIlIl, lllllllllllllllllIIIlIlIIllllIII);
        this.customColorCodes[113] = new Color(0, 90, 163);
        this.colorcodeIdentifiers = this.setupColorcodeIdentifier();
        this.setupMinecraftColorcodes();
        this.FONT_HEIGHT = this.getHeight();
    }
    
    private void setupMinecraftColorcodes() {
        for (int lllllllllllllllllIIIlIIIlIllIIII = 0; lllllllllllllllllIIIlIIIlIllIIII < 32; ++lllllllllllllllllIIIlIIIlIllIIII) {
            final int lllllllllllllllllIIIlIIIlIllIlII = (lllllllllllllllllIIIlIIIlIllIIII >> 3 & 0x1) * 85;
            int lllllllllllllllllIIIlIIIlIllIIll = (lllllllllllllllllIIIlIIIlIllIIII >> 2 & 0x1) * 170 + lllllllllllllllllIIIlIIIlIllIlII;
            int lllllllllllllllllIIIlIIIlIllIIlI = (lllllllllllllllllIIIlIIIlIllIIII >> 1 & 0x1) * 170 + lllllllllllllllllIIIlIIIlIllIlII;
            int lllllllllllllllllIIIlIIIlIllIIIl = (lllllllllllllllllIIIlIIIlIllIIII >> 0 & 0x1) * 170 + lllllllllllllllllIIIlIIIlIllIlII;
            if (lllllllllllllllllIIIlIIIlIllIIII == 6) {
                lllllllllllllllllIIIlIIIlIllIIll += 85;
            }
            if (lllllllllllllllllIIIlIIIlIllIIII >= 16) {
                lllllllllllllllllIIIlIIIlIllIIll /= 4;
                lllllllllllllllllIIIlIIIlIllIIlI /= 4;
                lllllllllllllllllIIIlIIIlIllIIIl /= 4;
            }
            this.colorCode[lllllllllllllllllIIIlIIIlIllIIII] = ((lllllllllllllllllIIIlIIIlIllIIll & 0xFF) << 16 | (lllllllllllllllllIIIlIIIlIllIIlI & 0xFF) << 8 | (lllllllllllllllllIIIlIIIlIllIIIl & 0xFF));
        }
    }
    
    private void drawLine(final double lllllllllllllllllIIIlIIIllIIllIl, final double lllllllllllllllllIIIlIIIllIIIlll, final double lllllllllllllllllIIIlIIIllIIlIll, final double lllllllllllllllllIIIlIIIllIIIlIl, final float lllllllllllllllllIIIlIIIllIIlIIl) {
        GL11.glDisable(3553);
        GL11.glLineWidth(lllllllllllllllllIIIlIIIllIIlIIl);
        GL11.glBegin(1);
        GL11.glVertex2d(lllllllllllllllllIIIlIIIllIIllIl, lllllllllllllllllIIIlIIIllIIIlll);
        GL11.glVertex2d(lllllllllllllllllIIIlIIIllIIlIll, lllllllllllllllllIIIlIIIllIIIlIl);
        GL11.glEnd();
        GL11.glEnable(3553);
    }
    
    public String getFontName() {
        return this.font.getFont().getFontName();
    }
    
    public Color getColor(final int lllllllllllllllllIIIlIIIIlIlIlII, final float lllllllllllllllllIIIlIIIIlIlIIIl) {
        return new Color((lllllllllllllllllIIIlIIIIlIlIlII >> 16) / 255.0f, (lllllllllllllllllIIIlIIIIlIlIlII >> 8 & 0xFF) / 255.0f, (lllllllllllllllllIIIlIIIIlIlIlII & 0xFF) / 255.0f, lllllllllllllllllIIIlIIIIlIlIIIl);
    }
    
    private static boolean isFormatSpecial(final char lllllllllllllllllIIIlIIllIIIIIlI) {
        return (lllllllllllllllllIIIlIIllIIIIIlI >= 'k' && lllllllllllllllllIIIlIIllIIIIIlI <= 'o') || (lllllllllllllllllIIIlIIllIIIIIlI >= 'K' && lllllllllllllllllIIIlIIllIIIIIlI <= 'O') || lllllllllllllllllIIIlIIllIIIIIlI == 'r' || lllllllllllllllllIIIlIIllIIIIIlI == 'R';
    }
    
    public List<String> listFormattedStringToWidth(final String lllllllllllllllllIIIlIIIIlllIIIl, final int lllllllllllllllllIIIlIIIIlllIIll) {
        return Arrays.asList(this.wrapFormattedStringToWidth(lllllllllllllllllIIIlIIIIlllIIIl, lllllllllllllllllIIIlIIIIlllIIll).split("\n"));
    }
    
    public int getColorCode(final char lllllllllllllllllIIIlIIlIlllllIl) {
        return this.colorCode["0123456789abcdef".indexOf(lllllllllllllllllIIIlIIlIlllllIl)];
    }
    
    public int getStringWidth(final String lllllllllllllllllIIIlIIlIIllIlIl) {
        if (lllllllllllllllllIIIlIIlIIllIlIl == null) {
            return 0;
        }
        if (lllllllllllllllllIIIlIIlIIllIlIl.contains("§")) {
            final String[] lllllllllllllllllIIIlIIlIIlllIll = lllllllllllllllllIIIlIIlIIllIlIl.split("§");
            XFont lllllllllllllllllIIIlIIlIIlllIlI = this.font;
            int lllllllllllllllllIIIlIIlIIlllIIl = 0;
            boolean lllllllllllllllllIIIlIIlIIlllIII = false;
            boolean lllllllllllllllllIIIlIIlIIllIlll = false;
            for (int lllllllllllllllllIIIlIIlIIllllII = 0; lllllllllllllllllIIIlIIlIIllllII < lllllllllllllllllIIIlIIlIIlllIll.length; ++lllllllllllllllllIIIlIIlIIllllII) {
                if (lllllllllllllllllIIIlIIlIIlllIll[lllllllllllllllllIIIlIIlIIllllII].length() > 0) {
                    if (lllllllllllllllllIIIlIIlIIllllII == 0) {
                        lllllllllllllllllIIIlIIlIIlllIIl += lllllllllllllllllIIIlIIlIIlllIlI.getStringWidth(lllllllllllllllllIIIlIIlIIlllIll[lllllllllllllllllIIIlIIlIIllllII]);
                    }
                    else {
                        final String lllllllllllllllllIIIlIIlIIllllll = lllllllllllllllllIIIlIIlIIlllIll[lllllllllllllllllIIIlIIlIIllllII].substring(1);
                        final char lllllllllllllllllIIIlIIlIIlllllI = lllllllllllllllllIIIlIIlIIlllIll[lllllllllllllllllIIIlIIlIIllllII].charAt(0);
                        final int lllllllllllllllllIIIlIIlIIllllIl = this.colorcodeIdentifiers.indexOf(lllllllllllllllllIIIlIIlIIlllllI);
                        if (lllllllllllllllllIIIlIIlIIllllIl != -1) {
                            if (lllllllllllllllllIIIlIIlIIllllIl < 16) {
                                lllllllllllllllllIIIlIIlIIlllIII = false;
                                lllllllllllllllllIIIlIIlIIllIlll = false;
                            }
                            else if (lllllllllllllllllIIIlIIlIIllllIl != 16) {
                                if (lllllllllllllllllIIIlIIlIIllllIl == 17) {
                                    lllllllllllllllllIIIlIIlIIlllIII = true;
                                }
                                else if (lllllllllllllllllIIIlIIlIIllllIl != 18) {
                                    if (lllllllllllllllllIIIlIIlIIllllIl != 19) {
                                        if (lllllllllllllllllIIIlIIlIIllllIl == 20) {
                                            lllllllllllllllllIIIlIIlIIllIlll = true;
                                        }
                                        else if (lllllllllllllllllIIIlIIlIIllllIl == 21) {
                                            lllllllllllllllllIIIlIIlIIlllIII = false;
                                            lllllllllllllllllIIIlIIlIIllIlll = false;
                                        }
                                    }
                                }
                            }
                        }
                        if (lllllllllllllllllIIIlIIlIIlllIII && lllllllllllllllllIIIlIIlIIllIlll) {
                            lllllllllllllllllIIIlIIlIIlllIlI = this.boldItalicFont;
                        }
                        else if (lllllllllllllllllIIIlIIlIIlllIII) {
                            lllllllllllllllllIIIlIIlIIlllIlI = this.boldFont;
                        }
                        else if (lllllllllllllllllIIIlIIlIIllIlll) {
                            lllllllllllllllllIIIlIIlIIlllIlI = this.italicFont;
                        }
                        else {
                            lllllllllllllllllIIIlIIlIIlllIlI = this.font;
                        }
                        lllllllllllllllllIIIlIIlIIlllIIl += lllllllllllllllllIIIlIIlIIlllIlI.getStringWidth(lllllllllllllllllIIIlIIlIIllllll);
                    }
                }
            }
            return lllllllllllllllllIIIlIIlIIlllIIl / 2;
        }
        return this.font.getStringWidth(lllllllllllllllllIIIlIIlIIllIlIl) / 2;
    }
    
    public int getHeight() {
        return this.font.getHeight() / 2;
    }
    
    public void setBidiFlag(final boolean lllllllllllllllllIIIlIIlIlllIlll) {
        this.bidi = lllllllllllllllllIIIlIIlIlllIlll;
    }
    
    protected String wrapFormattedStringToWidth(final String lllllllllllllllllIIIlIIIIlIllllI, final int lllllllllllllllllIIIlIIIIllIIIIl) {
        final int lllllllllllllllllIIIlIIIIllIIIII = this.sizeStringToWidth(lllllllllllllllllIIIlIIIIlIllllI, lllllllllllllllllIIIlIIIIllIIIIl);
        if (lllllllllllllllllIIIlIIIIlIllllI.length() <= lllllllllllllllllIIIlIIIIllIIIII) {
            return lllllllllllllllllIIIlIIIIlIllllI;
        }
        final String lllllllllllllllllIIIlIIIIllIIlll = lllllllllllllllllIIIlIIIIlIllllI.substring(0, lllllllllllllllllIIIlIIIIllIIIII);
        final char lllllllllllllllllIIIlIIIIllIIllI = lllllllllllllllllIIIlIIIIlIllllI.charAt(lllllllllllllllllIIIlIIIIllIIIII);
        final boolean lllllllllllllllllIIIlIIIIllIIlIl = lllllllllllllllllIIIlIIIIllIIllI == ' ' || lllllllllllllllllIIIlIIIIllIIllI == '\n';
        final String lllllllllllllllllIIIlIIIIllIIlII = String.valueOf(new StringBuilder().append(getFormatFromString(lllllllllllllllllIIIlIIIIllIIlll)).append(lllllllllllllllllIIIlIIIIlIllllI.substring(lllllllllllllllllIIIlIIIIllIIIII + (lllllllllllllllllIIIlIIIIllIIlIl ? 1 : 0))));
        return String.valueOf(new StringBuilder().append(lllllllllllllllllIIIlIIIIllIIlll).append("\n").append(this.wrapFormattedStringToWidth(lllllllllllllllllIIIlIIIIllIIlII, lllllllllllllllllIIIlIIIIllIIIIl)));
    }
    
    public XFont getFont() {
        return this.font;
    }
    
    public int drawString(final String lllllllllllllllllIIIlIlIIllIllIl, final float lllllllllllllllllIIIlIlIIllIllII, final float lllllllllllllllllIIIlIlIIllIlIll, final int lllllllllllllllllIIIlIlIIllIIlIl) {
        return this.drawString(lllllllllllllllllIIIlIlIIllIllIl, lllllllllllllllllIIIlIlIIllIllII, lllllllllllllllllIIIlIlIIllIlIll, lllllllllllllllllIIIlIlIIllIIlIl, false);
    }
    
    public static String getFormatFromString(final String lllllllllllllllllIIIlIIllIIIllII) {
        String lllllllllllllllllIIIlIIllIIIlIll = "";
        int lllllllllllllllllIIIlIIllIIIlIlI = -1;
        final int lllllllllllllllllIIIlIIllIIIlIIl = lllllllllllllllllIIIlIIllIIIllII.length();
        while ((lllllllllllllllllIIIlIIllIIIlIlI = lllllllllllllllllIIIlIIllIIIllII.indexOf(167, lllllllllllllllllIIIlIIllIIIlIlI + 1)) != -1) {
            if (lllllllllllllllllIIIlIIllIIIlIlI < lllllllllllllllllIIIlIIllIIIlIIl - 1) {
                final char lllllllllllllllllIIIlIIllIIIllIl = lllllllllllllllllIIIlIIllIIIllII.charAt(lllllllllllllllllIIIlIIllIIIlIlI + 1);
                if (isFormatColor(lllllllllllllllllIIIlIIllIIIllIl)) {
                    lllllllllllllllllIIIlIIllIIIlIll = String.valueOf(new StringBuilder().append("§").append(lllllllllllllllllIIIlIIllIIIllIl));
                }
                else {
                    if (!isFormatSpecial(lllllllllllllllllIIIlIIllIIIllIl)) {
                        continue;
                    }
                    lllllllllllllllllIIIlIIllIIIlIll = String.valueOf(new StringBuilder().append(lllllllllllllllllIIIlIIllIIIlIll).append("§").append(lllllllllllllllllIIIlIIllIIIllIl));
                }
            }
        }
        return lllllllllllllllllIIIlIIllIIIlIll;
    }
    
    public String trimStringToWidth(final String lllllllllllllllllIIIlIIIlIIIIlII, final int lllllllllllllllllIIIlIIIlIIIIIll, final boolean lllllllllllllllllIIIlIIIlIIIIIlI) {
        final StringBuilder lllllllllllllllllIIIlIIIlIIIlIll = new StringBuilder();
        int lllllllllllllllllIIIlIIIlIIIlIlI = 0;
        final int lllllllllllllllllIIIlIIIlIIIlIIl = lllllllllllllllllIIIlIIIlIIIIIlI ? (lllllllllllllllllIIIlIIIlIIIIlII.length() - 1) : 0;
        final int lllllllllllllllllIIIlIIIlIIIlIII = lllllllllllllllllIIIlIIIlIIIIIlI ? -1 : 1;
        boolean lllllllllllllllllIIIlIIIlIIIIlll = false;
        boolean lllllllllllllllllIIIlIIIlIIIIllI = false;
        for (int lllllllllllllllllIIIlIIIlIIlIIII = lllllllllllllllllIIIlIIIlIIIlIIl; lllllllllllllllllIIIlIIIlIIlIIII >= 0 && lllllllllllllllllIIIlIIIlIIlIIII < lllllllllllllllllIIIlIIIlIIIIlII.length() && lllllllllllllllllIIIlIIIlIIIlIlI < lllllllllllllllllIIIlIIIlIIIIIll; lllllllllllllllllIIIlIIIlIIlIIII += lllllllllllllllllIIIlIIIlIIIlIII) {
            final char lllllllllllllllllIIIlIIIlIIlIIlI = lllllllllllllllllIIIlIIIlIIIIlII.charAt(lllllllllllllllllIIIlIIIlIIlIIII);
            final int lllllllllllllllllIIIlIIIlIIlIIIl = this.getStringWidth(Character.toString(lllllllllllllllllIIIlIIIlIIlIIlI));
            if (lllllllllllllllllIIIlIIIlIIIIlll) {
                lllllllllllllllllIIIlIIIlIIIIlll = false;
                if (lllllllllllllllllIIIlIIIlIIlIIlI != 'l' && lllllllllllllllllIIIlIIIlIIlIIlI != 'L') {
                    if (lllllllllllllllllIIIlIIIlIIlIIlI == 'r' || lllllllllllllllllIIIlIIIlIIlIIlI == 'R') {
                        lllllllllllllllllIIIlIIIlIIIIllI = false;
                    }
                }
                else {
                    lllllllllllllllllIIIlIIIlIIIIllI = true;
                }
            }
            else if (lllllllllllllllllIIIlIIIlIIlIIIl < 0) {
                lllllllllllllllllIIIlIIIlIIIIlll = true;
            }
            else {
                lllllllllllllllllIIIlIIIlIIIlIlI += lllllllllllllllllIIIlIIIlIIlIIIl;
                if (lllllllllllllllllIIIlIIIlIIIIllI) {
                    ++lllllllllllllllllIIIlIIIlIIIlIlI;
                }
            }
            if (lllllllllllllllllIIIlIIIlIIIlIlI > lllllllllllllllllIIIlIIIlIIIIIll) {
                break;
            }
            if (lllllllllllllllllIIIlIIIlIIIIIlI) {
                lllllllllllllllllIIIlIIIlIIIlIll.insert(0, lllllllllllllllllIIIlIIIlIIlIIlI);
            }
            else {
                lllllllllllllllllIIIlIIIlIIIlIll.append(lllllllllllllllllIIIlIIIlIIlIIlI);
            }
        }
        return String.valueOf(lllllllllllllllllIIIlIIIlIIIlIll);
    }
    
    public void drawCenteredStringXY(final String lllllllllllllllllIIIlIlIIIllIllI, final int lllllllllllllllllIIIlIlIIIlllIll, final int lllllllllllllllllIIIlIlIIIllIlII, final int lllllllllllllllllIIIlIlIIIlllIIl, final boolean lllllllllllllllllIIIlIlIIIllIIlI) {
        this.drawCenteredString(lllllllllllllllllIIIlIlIIIllIllI, lllllllllllllllllIIIlIlIIIlllIll, lllllllllllllllllIIIlIlIIIllIlII - this.getHeight() / 2, lllllllllllllllllIIIlIlIIIlllIIl, lllllllllllllllllIIIlIlIIIllIIlI);
    }
    
    public void setAntiAliasing(final boolean lllllllllllllllllIIIlIIIlIllllIl) {
        this.font.setAntiAlias(lllllllllllllllllIIIlIIIlIllllIl);
        this.boldFont.setAntiAlias(lllllllllllllllllIIIlIIIlIllllIl);
        this.italicFont.setAntiAlias(lllllllllllllllllIIIlIIIlIllllIl);
        this.boldItalicFont.setAntiAlias(lllllllllllllllllIIIlIIIlIllllIl);
    }
    
    public void setFont(final Font lllllllllllllllllIIIlIIlIIlIIIlI, final boolean lllllllllllllllllIIIlIIlIIIlllIl, final int lllllllllllllllllIIIlIIlIIIlllII) {
        final short lllllllllllllllllIIIlIIlIIIllIll = (short)this;
        synchronized (this) {
            this.font = new XFont(lllllllllllllllllIIIlIIlIIlIIIlI, lllllllllllllllllIIIlIIlIIIlllIl, lllllllllllllllllIIIlIIlIIIlllII);
            this.boldFont = new XFont(lllllllllllllllllIIIlIIlIIlIIIlI.deriveFont(1), lllllllllllllllllIIIlIIlIIIlllIl, lllllllllllllllllIIIlIIlIIIlllII);
            this.italicFont = new XFont(lllllllllllllllllIIIlIIlIIlIIIlI.deriveFont(2), lllllllllllllllllIIIlIIlIIIlllIl, lllllllllllllllllIIIlIIlIIIlllII);
            this.boldItalicFont = new XFont(lllllllllllllllllIIIlIIlIIlIIIlI.deriveFont(3), lllllllllllllllllIIIlIIlIIIlllIl, lllllllllllllllllIIIlIIlIIIlllII);
            this.FONT_HEIGHT = this.getHeight();
        }
    }
    
    private static boolean isFormatColor(final char lllllllllllllllllIIIlIIlIlIlIIIl) {
        return (lllllllllllllllllIIIlIIlIlIlIIIl >= '0' && lllllllllllllllllIIIlIIlIlIlIIIl <= '9') || (lllllllllllllllllIIIlIIlIlIlIIIl >= 'a' && lllllllllllllllllIIIlIIlIlIlIIIl <= 'f') || (lllllllllllllllllIIIlIIlIlIlIIIl >= 'A' && lllllllllllllllllIIIlIIlIlIlIIIl <= 'F');
    }
    
    public int getSize() {
        return this.font.getFont().getSize();
    }
    
    public void drawStringWithShadow(final String lllllllllllllllllIIIlIlIIlIllllI, final int lllllllllllllllllIIIlIlIIlIlllIl, final int lllllllllllllllllIIIlIlIIlIlIlll, final int lllllllllllllllllIIIlIlIIlIlIllI) {
        this.drawString(lllllllllllllllllIIIlIlIIlIllllI, lllllllllllllllllIIIlIlIIlIlllIl + 0.75f, lllllllllllllllllIIIlIlIIlIlIlll + 0.75f, lllllllllllllllllIIIlIlIIlIlIllI, true);
        this.drawString(lllllllllllllllllIIIlIlIIlIllllI, (float)lllllllllllllllllIIIlIlIIlIlllIl, (float)lllllllllllllllllIIIlIlIIlIlIlll, lllllllllllllllllIIIlIlIIlIlIllI, false);
    }
    
    public int drawString(final String lllllllllllllllllIIIlIlIIIIIllll, final float lllllllllllllllllIIIlIlIIIIIlllI, final float lllllllllllllllllIIIlIlIIIIlIlIl, final int lllllllllllllllllIIIlIlIIIIIllII, final boolean lllllllllllllllllIIIlIlIIIIIlIll) {
        int lllllllllllllllllIIIlIlIIIIlIIlI = 0;
        final String[] lllllllllllllllllIIIlIlIIIIlIIIl = lllllllllllllllllIIIlIlIIIIIllll.split("\n");
        for (int lllllllllllllllllIIIlIlIIIIllIIl = 0; lllllllllllllllllIIIlIlIIIIllIIl < lllllllllllllllllIIIlIlIIIIlIIIl.length; ++lllllllllllllllllIIIlIlIIIIllIIl) {
            lllllllllllllllllIIIlIlIIIIlIIlI = this.drawLine(lllllllllllllllllIIIlIlIIIIlIIIl[lllllllllllllllllIIIlIlIIIIllIIl], lllllllllllllllllIIIlIlIIIIIlllI, lllllllllllllllllIIIlIlIIIIlIlIl + lllllllllllllllllIIIlIlIIIIllIIl * this.getHeight(), lllllllllllllllllIIIlIlIIIIIllII, lllllllllllllllllIIIlIlIIIIIlIll);
        }
        return lllllllllllllllllIIIlIlIIIIlIIlI;
    }
    
    public boolean isAntiAliasing() {
        return this.font.isAntiAlias() && this.boldFont.isAntiAlias() && this.italicFont.isAntiAlias() && this.boldItalicFont.isAntiAlias();
    }
    
    public int getCharWidth(final char lllllllllllllllllIIIlIIlIlIIllIl) {
        return this.getStringWidth(Character.toString(lllllllllllllllllIIIlIIlIlIIllIl));
    }
    
    public List<String> formatString(final String lllllllllllllllllIIIlIIIllIllIlI, final double lllllllllllllllllIIIlIIIllIlllll) {
        final List<String> lllllllllllllllllIIIlIIIllIllllI = new ArrayList<String>();
        String lllllllllllllllllIIIlIIIllIlllIl = "";
        char lllllllllllllllllIIIlIIIllIlllII = '\uffff';
        for (int lllllllllllllllllIIIlIIIlllIIIlI = 0; lllllllllllllllllIIIlIIIlllIIIlI < lllllllllllllllllIIIlIIIllIllIlI.toCharArray().length; ++lllllllllllllllllIIIlIIIlllIIIlI) {
            final char lllllllllllllllllIIIlIIIlllIIIll = lllllllllllllllllIIIlIIIllIllIlI.toCharArray()[lllllllllllllllllIIIlIIIlllIIIlI];
            if (lllllllllllllllllIIIlIIIlllIIIll == '§' && lllllllllllllllllIIIlIIIlllIIIlI < lllllllllllllllllIIIlIIIllIllIlI.toCharArray().length - 1) {
                lllllllllllllllllIIIlIIIllIlllII = lllllllllllllllllIIIlIIIllIllIlI.toCharArray()[lllllllllllllllllIIIlIIIlllIIIlI + 1];
            }
            if (this.getStringWidth(String.valueOf(new StringBuilder().append(lllllllllllllllllIIIlIIIllIlllIl).append(lllllllllllllllllIIIlIIIlllIIIll))) < lllllllllllllllllIIIlIIIllIlllll) {
                lllllllllllllllllIIIlIIIllIlllIl = String.valueOf(new StringBuilder().append(lllllllllllllllllIIIlIIIllIlllIl).append(lllllllllllllllllIIIlIIIlllIIIll));
            }
            else {
                lllllllllllllllllIIIlIIIllIllllI.add(lllllllllllllllllIIIlIIIllIlllIl);
                lllllllllllllllllIIIlIIIllIlllIl = ((lllllllllllllllllIIIlIIIllIlllII == -1) ? String.valueOf(lllllllllllllllllIIIlIIIlllIIIll) : String.valueOf(new StringBuilder().append("§").append(lllllllllllllllllIIIlIIIllIlllII).append(String.valueOf(lllllllllllllllllIIIlIIIlllIIIll))));
            }
        }
        if (!lllllllllllllllllIIIlIIIllIlllIl.equals("")) {
            lllllllllllllllllIIIlIIIllIllllI.add(lllllllllllllllllIIIlIIIllIlllIl);
        }
        return lllllllllllllllllIIIlIIIllIllllI;
    }
    
    public void drawCenteredString(final String lllllllllllllllllIIIlIlIIIlIIllI, final int lllllllllllllllllIIIlIlIIIlIIlIl, final int lllllllllllllllllIIIlIlIIIlIIlII, final int lllllllllllllllllIIIlIlIIIlIlIII) {
        this.drawStringWithShadow(lllllllllllllllllIIIlIlIIIlIIllI, lllllllllllllllllIIIlIlIIIlIIlIl - this.getStringWidth(lllllllllllllllllIIIlIlIIIlIIllI) / 2, lllllllllllllllllIIIlIlIIIlIIlII, lllllllllllllllllIIIlIlIIIlIlIII);
    }
    
    public boolean getBidiFlag() {
        return this.bidi;
    }
    
    private int drawLine(final String lllllllllllllllllIIIlIIlllIllIlI, final float lllllllllllllllllIIIlIIlllIllIIl, final float lllllllllllllllllIIIlIIlllIIllII, int lllllllllllllllllIIIlIIlllIIlIll, final boolean lllllllllllllllllIIIlIIlllIIlIlI) {
        if (lllllllllllllllllIIIlIIlllIllIlI == null) {
            return 0;
        }
        GL11.glPushMatrix();
        GL11.glTranslated(lllllllllllllllllIIIlIIlllIllIIl - 1.5, lllllllllllllllllIIIlIIlllIIllII + 0.5, 0.0);
        final boolean lllllllllllllllllIIIlIIlllIlIlIl = GL11.glGetBoolean(3042);
        GlStateManager.enableAlpha();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3553);
        if ((lllllllllllllllllIIIlIIlllIIlIll & 0xFC000000) == 0x0) {
            lllllllllllllllllIIIlIIlllIIlIll |= 0xFF000000;
        }
        final float lllllllllllllllllIIIlIIlllIlIlII = (lllllllllllllllllIIIlIIlllIIlIll >> 16 & 0xFF) / 255.0f;
        final float lllllllllllllllllIIIlIIlllIlIIll = (lllllllllllllllllIIIlIIlllIIlIll >> 8 & 0xFF) / 255.0f;
        final float lllllllllllllllllIIIlIIlllIlIIlI = (lllllllllllllllllIIIlIIlllIIlIll & 0xFF) / 255.0f;
        final float lllllllllllllllllIIIlIIlllIlIIIl = (lllllllllllllllllIIIlIIlllIIlIll >> 24 & 0xFF) / 255.0f;
        final Color lllllllllllllllllIIIlIIlllIlIIII = new Color(lllllllllllllllllIIIlIIlllIlIlII, lllllllllllllllllIIIlIIlllIlIIll, lllllllllllllllllIIIlIIlllIlIIlI, lllllllllllllllllIIIlIIlllIlIIIl);
        if (lllllllllllllllllIIIlIIlllIllIlI.contains("§")) {
            final String[] lllllllllllllllllIIIlIIllllIIlII = lllllllllllllllllIIIlIIlllIllIlI.split("§");
            Color lllllllllllllllllIIIlIIllllIIIll = lllllllllllllllllIIIlIIlllIlIIII;
            XFont lllllllllllllllllIIIlIIllllIIIlI = this.font;
            int lllllllllllllllllIIIlIIllllIIIIl = 0;
            boolean lllllllllllllllllIIIlIIllllIIIII = false;
            boolean lllllllllllllllllIIIlIIlllIlllll = false;
            boolean lllllllllllllllllIIIlIIlllIllllI = false;
            boolean lllllllllllllllllIIIlIIlllIlllIl = false;
            boolean lllllllllllllllllIIIlIIlllIlllII = false;
            for (int lllllllllllllllllIIIlIIllllIIlIl = 0; lllllllllllllllllIIIlIIllllIIlIl < lllllllllllllllllIIIlIIllllIIlII.length; ++lllllllllllllllllIIIlIIllllIIlIl) {
                if (lllllllllllllllllIIIlIIllllIIlII[lllllllllllllllllIIIlIIllllIIlIl].length() > 0) {
                    if (lllllllllllllllllIIIlIIllllIIlIl == 0) {
                        lllllllllllllllllIIIlIIllllIIIlI.drawString(lllllllllllllllllIIIlIIllllIIlII[lllllllllllllllllIIIlIIllllIIlIl], lllllllllllllllllIIIlIIllllIIIIl, 0.0, lllllllllllllllllIIIlIIllllIIIll, lllllllllllllllllIIIlIIlllIIlIlI);
                        lllllllllllllllllIIIlIIllllIIIIl += lllllllllllllllllIIIlIIllllIIIlI.getStringWidth(lllllllllllllllllIIIlIIllllIIlII[lllllllllllllllllIIIlIIllllIIlIl]);
                    }
                    else {
                        final String lllllllllllllllllIIIlIIllllIlIlI = lllllllllllllllllIIIlIIllllIIlII[lllllllllllllllllIIIlIIllllIIlIl].substring(1);
                        final char lllllllllllllllllIIIlIIllllIlIIl = lllllllllllllllllIIIlIIllllIIlII[lllllllllllllllllIIIlIIllllIIlIl].charAt(0);
                        final int lllllllllllllllllIIIlIIllllIlIII = this.colorcodeIdentifiers.indexOf(lllllllllllllllllIIIlIIllllIlIIl);
                        if (lllllllllllllllllIIIlIIllllIlIII != -1) {
                            if (lllllllllllllllllIIIlIIllllIlIII < 16) {
                                final int lllllllllllllllllIIIlIIllllIllII = this.colorCode[lllllllllllllllllIIIlIIllllIlIII];
                                lllllllllllllllllIIIlIIllllIIIll = this.getColor(lllllllllllllllllIIIlIIllllIllII, lllllllllllllllllIIIlIIlllIlIIIl);
                                lllllllllllllllllIIIlIIlllIlllll = false;
                                lllllllllllllllllIIIlIIlllIllllI = false;
                                lllllllllllllllllIIIlIIllllIIIII = false;
                                lllllllllllllllllIIIlIIlllIlllII = false;
                                lllllllllllllllllIIIlIIlllIlllIl = false;
                            }
                            else if (lllllllllllllllllIIIlIIllllIlIII == 16) {
                                lllllllllllllllllIIIlIIllllIIIII = true;
                            }
                            else if (lllllllllllllllllIIIlIIllllIlIII == 17) {
                                lllllllllllllllllIIIlIIlllIlllll = true;
                            }
                            else if (lllllllllllllllllIIIlIIllllIlIII == 18) {
                                lllllllllllllllllIIIlIIlllIlllIl = true;
                            }
                            else if (lllllllllllllllllIIIlIIllllIlIII == 19) {
                                lllllllllllllllllIIIlIIlllIlllII = true;
                            }
                            else if (lllllllllllllllllIIIlIIllllIlIII == 20) {
                                lllllllllllllllllIIIlIIlllIllllI = true;
                            }
                            else if (lllllllllllllllllIIIlIIllllIlIII == 21) {
                                lllllllllllllllllIIIlIIlllIlllll = false;
                                lllllllllllllllllIIIlIIlllIllllI = false;
                                lllllllllllllllllIIIlIIllllIIIII = false;
                                lllllllllllllllllIIIlIIlllIlllII = false;
                                lllllllllllllllllIIIlIIlllIlllIl = false;
                                lllllllllllllllllIIIlIIllllIIIll = lllllllllllllllllIIIlIIlllIlIIII;
                            }
                            else if (lllllllllllllllllIIIlIIllllIlIII > 21) {
                                final Color lllllllllllllllllIIIlIIllllIlIll = this.customColorCodes[lllllllllllllllllIIIlIIllllIlIIl];
                                lllllllllllllllllIIIlIIllllIIIll = new Color(lllllllllllllllllIIIlIIllllIlIll.getRed() / 255.0f, lllllllllllllllllIIIlIIllllIlIll.getGreen() / 255.0f, lllllllllllllllllIIIlIIllllIlIll.getBlue() / 255.0f, lllllllllllllllllIIIlIIlllIlIIIl);
                            }
                        }
                        if (lllllllllllllllllIIIlIIlllIlllll && lllllllllllllllllIIIlIIlllIllllI) {
                            this.boldItalicFont.drawString(lllllllllllllllllIIIlIIllllIIIII ? this.toRandom(lllllllllllllllllIIIlIIllllIIIlI, lllllllllllllllllIIIlIIllllIlIlI) : lllllllllllllllllIIIlIIllllIlIlI, lllllllllllllllllIIIlIIllllIIIIl, 0.0, lllllllllllllllllIIIlIIllllIIIll, lllllllllllllllllIIIlIIlllIIlIlI);
                            lllllllllllllllllIIIlIIllllIIIlI = this.boldItalicFont;
                        }
                        else if (lllllllllllllllllIIIlIIlllIlllll) {
                            this.boldFont.drawString(lllllllllllllllllIIIlIIllllIIIII ? this.toRandom(lllllllllllllllllIIIlIIllllIIIlI, lllllllllllllllllIIIlIIllllIlIlI) : lllllllllllllllllIIIlIIllllIlIlI, lllllllllllllllllIIIlIIllllIIIIl, 0.0, lllllllllllllllllIIIlIIllllIIIll, lllllllllllllllllIIIlIIlllIIlIlI);
                            lllllllllllllllllIIIlIIllllIIIlI = this.boldFont;
                        }
                        else if (lllllllllllllllllIIIlIIlllIllllI) {
                            this.italicFont.drawString(lllllllllllllllllIIIlIIllllIIIII ? this.toRandom(lllllllllllllllllIIIlIIllllIIIlI, lllllllllllllllllIIIlIIllllIlIlI) : lllllllllllllllllIIIlIIllllIlIlI, lllllllllllllllllIIIlIIllllIIIIl, 0.0, lllllllllllllllllIIIlIIllllIIIll, lllllllllllllllllIIIlIIlllIIlIlI);
                            lllllllllllllllllIIIlIIllllIIIlI = this.italicFont;
                        }
                        else {
                            this.font.drawString(lllllllllllllllllIIIlIIllllIIIII ? this.toRandom(lllllllllllllllllIIIlIIllllIIIlI, lllllllllllllllllIIIlIIllllIlIlI) : lllllllllllllllllIIIlIIllllIlIlI, lllllllllllllllllIIIlIIllllIIIIl, 0.0, lllllllllllllllllIIIlIIllllIIIll, lllllllllllllllllIIIlIIlllIIlIlI);
                            lllllllllllllllllIIIlIIllllIIIlI = this.font;
                        }
                        final float lllllllllllllllllIIIlIIllllIIlll = this.font.getHeight() / 16.0f;
                        final int lllllllllllllllllIIIlIIllllIIllI = lllllllllllllllllIIIlIIllllIIIlI.getStringHeight(lllllllllllllllllIIIlIIllllIlIlI);
                        if (lllllllllllllllllIIIlIIlllIlllIl) {
                            this.drawLine(lllllllllllllllllIIIlIIllllIIIIl / 2.0 + 1.0, lllllllllllllllllIIIlIIllllIIllI / 3, (lllllllllllllllllIIIlIIllllIIIIl + lllllllllllllllllIIIlIIllllIIIlI.getStringWidth(lllllllllllllllllIIIlIIllllIlIlI)) / 2.0 + 1.0, lllllllllllllllllIIIlIIllllIIllI / 3, lllllllllllllllllIIIlIIllllIIlll);
                        }
                        if (lllllllllllllllllIIIlIIlllIlllII) {
                            this.drawLine(lllllllllllllllllIIIlIIllllIIIIl / 2.0 + 1.0, lllllllllllllllllIIIlIIllllIIllI / 2, (lllllllllllllllllIIIlIIllllIIIIl + lllllllllllllllllIIIlIIllllIIIlI.getStringWidth(lllllllllllllllllIIIlIIllllIlIlI)) / 2.0 + 1.0, lllllllllllllllllIIIlIIllllIIllI / 2, lllllllllllllllllIIIlIIllllIIlll);
                        }
                        lllllllllllllllllIIIlIIllllIIIIl += lllllllllllllllllIIIlIIllllIIIlI.getStringWidth(lllllllllllllllllIIIlIIllllIlIlI);
                    }
                }
            }
        }
        else {
            this.font.drawString(lllllllllllllllllIIIlIIlllIllIlI, 0.0, 0.0, lllllllllllllllllIIIlIIlllIlIIII, lllllllllllllllllIIIlIIlllIIlIlI);
        }
        if (!lllllllllllllllllIIIlIIlllIlIlIl) {
            GL11.glDisable(3042);
        }
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        return (int)(lllllllllllllllllIIIlIIlllIllIIl + this.getStringWidth(lllllllllllllllllIIIlIIlllIllIlI));
    }
    
    public String trimStringToWidth(final String lllllllllllllllllIIIlIIIlIlIIIIl, final int lllllllllllllllllIIIlIIIlIlIIIII) {
        return this.trimStringToWidth(lllllllllllllllllIIIlIIIlIlIIIIl, lllllllllllllllllIIIlIIIlIlIIIII, false);
    }
    
    public int getStringHeight(final String lllllllllllllllllIIIlIIllIIllIII) {
        if (lllllllllllllllllIIIlIIllIIllIII == null) {
            return 0;
        }
        return this.font.getStringHeight(lllllllllllllllllIIIlIIllIIllIII) / 2;
    }
    
    public List<String> wrapWords(final String lllllllllllllllllIIIlIIIllllIlll, final double lllllllllllllllllIIIlIIIlllllIlI) {
        final List<String> lllllllllllllllllIIIlIIIlllllIIl = new ArrayList<String>();
        if (this.getStringWidth(lllllllllllllllllIIIlIIIllllIlll) > lllllllllllllllllIIIlIIIlllllIlI) {
            final String[] lllllllllllllllllIIIlIIIllllllll = lllllllllllllllllIIIlIIIllllIlll.split(" ");
            String lllllllllllllllllIIIlIIIlllllllI = "";
            char lllllllllllllllllIIIlIIIllllllIl = '\uffff';
            double lllllllllllllllllIIIlIIIllllIIIl = (Object)lllllllllllllllllIIIlIIIllllllll;
            for (short lllllllllllllllllIIIlIIIllllIIII = (short)lllllllllllllllllIIIlIIIllllIIIl.length, lllllllllllllllllIIIlIIIlllIllll = 0; lllllllllllllllllIIIlIIIlllIllll < lllllllllllllllllIIIlIIIllllIIII; ++lllllllllllllllllIIIlIIIlllIllll) {
                final String lllllllllllllllllIIIlIIlIIIIIIIl = lllllllllllllllllIIIlIIIllllIIIl[lllllllllllllllllIIIlIIIlllIllll];
                for (int lllllllllllllllllIIIlIIlIIIIIIlI = 0; lllllllllllllllllIIIlIIlIIIIIIlI < lllllllllllllllllIIIlIIlIIIIIIIl.toCharArray().length; ++lllllllllllllllllIIIlIIlIIIIIIlI) {
                    final char lllllllllllllllllIIIlIIlIIIIIIll = lllllllllllllllllIIIlIIlIIIIIIIl.toCharArray()[lllllllllllllllllIIIlIIlIIIIIIlI];
                    if (lllllllllllllllllIIIlIIlIIIIIIll == '§' && lllllllllllllllllIIIlIIlIIIIIIlI < lllllllllllllllllIIIlIIlIIIIIIIl.toCharArray().length - 1) {
                        lllllllllllllllllIIIlIIIllllllIl = lllllllllllllllllIIIlIIlIIIIIIIl.toCharArray()[lllllllllllllllllIIIlIIlIIIIIIlI + 1];
                    }
                }
                if (this.getStringWidth(String.valueOf(new StringBuilder().append(lllllllllllllllllIIIlIIIlllllllI).append(lllllllllllllllllIIIlIIlIIIIIIIl).append(" "))) < lllllllllllllllllIIIlIIIlllllIlI) {
                    lllllllllllllllllIIIlIIIlllllllI = String.valueOf(new StringBuilder().append(lllllllllllllllllIIIlIIIlllllllI).append(lllllllllllllllllIIIlIIlIIIIIIIl).append(" "));
                }
                else {
                    lllllllllllllllllIIIlIIIlllllIIl.add(lllllllllllllllllIIIlIIIlllllllI);
                    lllllllllllllllllIIIlIIIlllllllI = ((lllllllllllllllllIIIlIIIllllllIl == -1) ? String.valueOf(new StringBuilder().append(lllllllllllllllllIIIlIIlIIIIIIIl).append(" ")) : String.valueOf(new StringBuilder().append("§").append(lllllllllllllllllIIIlIIIllllllIl).append(lllllllllllllllllIIIlIIlIIIIIIIl).append(" ")));
                }
            }
            if (!lllllllllllllllllIIIlIIIlllllllI.equals("")) {
                if (this.getStringWidth(lllllllllllllllllIIIlIIIlllllllI) < lllllllllllllllllIIIlIIIlllllIlI) {
                    lllllllllllllllllIIIlIIIlllllIIl.add((lllllllllllllllllIIIlIIIllllllIl == -1) ? String.valueOf(new StringBuilder().append(lllllllllllllllllIIIlIIIlllllllI).append(" ")) : String.valueOf(new StringBuilder().append("§").append(lllllllllllllllllIIIlIIIllllllIl).append(lllllllllllllllllIIIlIIIlllllllI).append(" ")));
                    lllllllllllllllllIIIlIIIlllllllI = "";
                }
                else {
                    lllllllllllllllllIIIlIIIllllIIIl = (double)this.formatString(lllllllllllllllllIIIlIIIlllllllI, lllllllllllllllllIIIlIIIlllllIlI).iterator();
                    while (((Iterator)lllllllllllllllllIIIlIIIllllIIIl).hasNext()) {
                        final String lllllllllllllllllIIIlIIlIIIIIIII = ((Iterator<String>)lllllllllllllllllIIIlIIIllllIIIl).next();
                        lllllllllllllllllIIIlIIIlllllIIl.add(lllllllllllllllllIIIlIIlIIIIIIII);
                    }
                }
            }
        }
        else {
            lllllllllllllllllIIIlIIIlllllIIl.add(lllllllllllllllllIIIlIIIllllIlll);
        }
        return lllllllllllllllllIIIlIIIlllllIIl;
    }
    
    public void onResourceManagerReload(final IResourceManager lllllllllllllllllIIIlIIIIlIIIllI) {
    }
    
    private String setupColorcodeIdentifier() {
        String lllllllllllllllllIIIlIIIIlIIlIll = "0123456789abcdefklmnor";
        for (int lllllllllllllllllIIIlIIIIlIIllIl = 0; lllllllllllllllllIIIlIIIIlIIllIl < this.customColorCodes.length; ++lllllllllllllllllIIIlIIIIlIIllIl) {
            if (this.customColorCodes[lllllllllllllllllIIIlIIIIlIIllIl] != null) {
                lllllllllllllllllIIIlIIIIlIIlIll = String.valueOf(new StringBuilder().append(lllllllllllllllllIIIlIIIIlIIlIll).append((char)lllllllllllllllllIIIlIIIIlIIllIl));
            }
        }
        return lllllllllllllllllIIIlIIIIlIIlIll;
    }
    
    private String toRandom(final XFont lllllllllllllllllIIIlIIllIlIlIII, final String lllllllllllllllllIIIlIIllIlIIIll) {
        String lllllllllllllllllIIIlIIllIlIIllI = "";
        final String lllllllllllllllllIIIlIIllIlIIlIl = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8£\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1ªº¿®¬½¼¡«»\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261±\u2265\u2264\u2320\u2321\u00f7\u2248°\u2219·\u221a\u207f²\u25a0\u0000";
        final int lllllllllllllllllIIIlIIllIlIIIII = (Object)lllllllllllllllllIIIlIIllIlIIIll.toCharArray();
        final int lllllllllllllllllIIIlIIllIIlllll = lllllllllllllllllIIIlIIllIlIIIII.length;
        for (double lllllllllllllllllIIIlIIllIIllllI = 0; lllllllllllllllllIIIlIIllIIllllI < lllllllllllllllllIIIlIIllIIlllll; ++lllllllllllllllllIIIlIIllIIllllI) {
            final char lllllllllllllllllIIIlIIllIlIlIlI = lllllllllllllllllIIIlIIllIlIIIII[lllllllllllllllllIIIlIIllIIllllI];
            if (ChatAllowedCharacters.isAllowedCharacter(lllllllllllllllllIIIlIIllIlIlIlI)) {
                final int lllllllllllllllllIIIlIIllIlIlIll = this.fontRandom.nextInt("\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8£\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1ªº¿®¬½¼¡«»\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261±\u2265\u2264\u2320\u2321\u00f7\u2248°\u2219·\u221a\u207f²\u25a0\u0000".length());
                lllllllllllllllllIIIlIIllIlIIllI = String.valueOf(new StringBuilder().append(lllllllllllllllllIIIlIIllIlIIllI).append("\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8£\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1ªº¿®¬½¼¡«»\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261±\u2265\u2264\u2320\u2321\u00f7\u2248°\u2219·\u221a\u207f²\u25a0\u0000".toCharArray()[lllllllllllllllllIIIlIIllIlIlIll]));
            }
        }
        return lllllllllllllllllIIIlIIllIlIIllI;
    }
}
