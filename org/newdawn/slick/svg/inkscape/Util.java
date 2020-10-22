package org.newdawn.slick.svg.inkscape;

import org.w3c.dom.*;
import org.newdawn.slick.geom.*;
import java.util.*;
import org.newdawn.slick.svg.*;

public class Util
{
    static float getFloatAttribute(final Element lllIllllIlIllIl, final String lllIllllIlIllII) throws ParsingException {
        String lllIllllIlIlIll = lllIllllIlIllIl.getAttribute(lllIllllIlIllII);
        if (lllIllllIlIlIll == null || lllIllllIlIlIll.equals("")) {
            lllIllllIlIlIll = lllIllllIlIllIl.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", lllIllllIlIllII);
        }
        try {
            return Float.parseFloat(lllIllllIlIlIll);
        }
        catch (NumberFormatException lllIllllIlIlllI) {
            throw new ParsingException(lllIllllIlIllIl, String.valueOf(new StringBuilder().append("Invalid value for: ").append(lllIllllIlIllII)), lllIllllIlIlllI);
        }
    }
    
    static String getStyle(final Element lllIllllllIIlll, final String lllIllllllIIIlI) {
        final String lllIllllllIIlIl = lllIllllllIIlll.getAttribute(lllIllllllIIIlI);
        if (lllIllllllIIlIl != null && lllIllllllIIlIl.length() > 0) {
            return lllIllllllIIlIl;
        }
        final String lllIllllllIIlII = lllIllllllIIlll.getAttribute("style");
        return extractStyle(lllIllllllIIlII, lllIllllllIIIlI);
    }
    
    public static String getAsReference(String lllIllllIlIIIlI) {
        if (lllIllllIlIIIlI.length() < 2) {
            return "";
        }
        lllIllllIlIIIlI = lllIllllIlIIIlI.substring(1, lllIllllIlIIIlI.length());
        return lllIllllIlIIIlI;
    }
    
    static String getMetaData(final Element lllIllllllIllll) {
        final String lllIllllllIlllI = lllIllllllIllll.getAttributeNS("http://www.inkscape.org/namespaces/inkscape", "label");
        if (lllIllllllIlllI != null && !lllIllllllIlllI.equals("")) {
            return lllIllllllIlllI;
        }
        return lllIllllllIllll.getAttribute("id");
    }
    
    static {
        XLINK = "http://www.w3.org/1999/xlink";
        INKSCAPE = "http://www.inkscape.org/namespaces/inkscape";
        SODIPODI = "http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd";
    }
    
    static Transform getTransform(final Element lllIlllllIIllll) {
        return getTransform(lllIlllllIIllll, "transform");
    }
    
    static Transform getTransform(final Element lllIllllIllllII, final String lllIllllIlllllI) {
        String lllIllllIllllIl = lllIllllIllllII.getAttribute(lllIllllIlllllI);
        if (lllIllllIllllIl == null) {
            return new Transform();
        }
        if (lllIllllIllllIl.equals("")) {
            return new Transform();
        }
        if (lllIllllIllllIl.startsWith("translate")) {
            lllIllllIllllIl = lllIllllIllllIl.substring(0, lllIllllIllllIl.length() - 1);
            lllIllllIllllIl = lllIllllIllllIl.substring("translate(".length());
            final StringTokenizer lllIlllllIIIllI = new StringTokenizer(lllIllllIllllIl, ", ");
            final float lllIlllllIIIlIl = Float.parseFloat(lllIlllllIIIllI.nextToken());
            final float lllIlllllIIIlII = Float.parseFloat(lllIlllllIIIllI.nextToken());
            return Transform.createTranslateTransform(lllIlllllIIIlIl, lllIlllllIIIlII);
        }
        if (lllIllllIllllIl.startsWith("matrix")) {
            final float[] lllIlllllIIIIlI = new float[6];
            lllIllllIllllIl = lllIllllIllllIl.substring(0, lllIllllIllllIl.length() - 1);
            lllIllllIllllIl = lllIllllIllllIl.substring("matrix(".length());
            final StringTokenizer lllIlllllIIIIIl = new StringTokenizer(lllIllllIllllIl, ", ");
            final float[] lllIlllllIIIIII = new float[6];
            for (int lllIlllllIIIIll = 0; lllIlllllIIIIll < lllIlllllIIIIII.length; ++lllIlllllIIIIll) {
                lllIlllllIIIIII[lllIlllllIIIIll] = Float.parseFloat(lllIlllllIIIIIl.nextToken());
            }
            lllIlllllIIIIlI[0] = lllIlllllIIIIII[0];
            lllIlllllIIIIlI[1] = lllIlllllIIIIII[2];
            lllIlllllIIIIlI[2] = lllIlllllIIIIII[4];
            lllIlllllIIIIlI[3] = lllIlllllIIIIII[1];
            lllIlllllIIIIlI[4] = lllIlllllIIIIII[3];
            lllIlllllIIIIlI[5] = lllIlllllIIIIII[5];
            return new Transform(lllIlllllIIIIlI);
        }
        return new Transform();
    }
    
    static NonGeometricData getNonGeometricData(final Element lllIlllllllIlII) {
        final String lllIlllllllIllI = getMetaData(lllIlllllllIlII);
        final NonGeometricData lllIlllllllIlIl = new InkscapeNonGeometricData(lllIlllllllIllI, lllIlllllllIlII);
        lllIlllllllIlIl.addAttribute("id", lllIlllllllIlII.getAttribute("id"));
        lllIlllllllIlIl.addAttribute("fill", getStyle(lllIlllllllIlII, "fill"));
        lllIlllllllIlIl.addAttribute("stroke", getStyle(lllIlllllllIlII, "stroke"));
        lllIlllllllIlIl.addAttribute("opacity", getStyle(lllIlllllllIlII, "opacity"));
        lllIlllllllIlIl.addAttribute("stroke-dasharray", getStyle(lllIlllllllIlII, "stroke-dasharray"));
        lllIlllllllIlIl.addAttribute("stroke-dashoffset", getStyle(lllIlllllllIlII, "stroke-dashoffset"));
        lllIlllllllIlIl.addAttribute("stroke-miterlimit", getStyle(lllIlllllllIlII, "stroke-miterlimit"));
        lllIlllllllIlIl.addAttribute("stroke-opacity", getStyle(lllIlllllllIlII, "stroke-opacity"));
        lllIlllllllIlIl.addAttribute("stroke-width", getStyle(lllIlllllllIlII, "stroke-width"));
        return lllIlllllllIlIl;
    }
    
    static String extractStyle(final String lllIlllllIlIlIl, final String lllIlllllIlIlll) {
        if (lllIlllllIlIlIl == null) {
            return "";
        }
        final StringTokenizer lllIlllllIlIllI = new StringTokenizer(lllIlllllIlIlIl, ";");
        while (lllIlllllIlIllI.hasMoreTokens()) {
            final String lllIlllllIllIlI = lllIlllllIlIllI.nextToken();
            final String lllIlllllIllIIl = lllIlllllIllIlI.substring(0, lllIlllllIllIlI.indexOf(58));
            if (lllIlllllIllIIl.equals(lllIlllllIlIlll)) {
                return lllIlllllIllIlI.substring(lllIlllllIllIlI.indexOf(58) + 1);
            }
        }
        return "";
    }
}
