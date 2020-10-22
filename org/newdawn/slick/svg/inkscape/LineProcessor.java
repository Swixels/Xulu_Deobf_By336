package org.newdawn.slick.svg.inkscape;

import org.w3c.dom.*;
import java.util.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.svg.*;

public class LineProcessor implements ElementProcessor
{
    @Override
    public void process(final Loader lllllllllllllllllIlIIlIllllllIlI, final Element lllllllllllllllllIlIIlIllllIllIl, final Diagram lllllllllllllllllIlIIlIllllIllII, final Transform lllllllllllllllllIlIIlIlllllIlll) throws ParsingException {
        Transform lllllllllllllllllIlIIlIlllllIllI = Util.getTransform(lllllllllllllllllIlIIlIllllIllIl);
        lllllllllllllllllIlIIlIlllllIllI = new Transform(lllllllllllllllllIlIIlIlllllIlll, lllllllllllllllllIlIIlIlllllIllI);
        if (lllllllllllllllllIlIIlIllllIllIl.getNodeName().equals("line")) {
            final float lllllllllllllllllIlIIllIIIIIIllI = Float.parseFloat(lllllllllllllllllIlIIlIllllIllIl.getAttribute("x1"));
            final float lllllllllllllllllIlIIllIIIIIIlII = Float.parseFloat(lllllllllllllllllIlIIlIllllIllIl.getAttribute("x2"));
            final float lllllllllllllllllIlIIllIIIIIIlIl = Float.parseFloat(lllllllllllllllllIlIIlIllllIllIl.getAttribute("y1"));
            final float lllllllllllllllllIlIIllIIIIIIIll = Float.parseFloat(lllllllllllllllllIlIIlIllllIllIl.getAttribute("y2"));
        }
        else {
            final String lllllllllllllllllIlIIlIllllllllI = lllllllllllllllllIlIIlIllllIllIl.getAttribute("d");
            final StringTokenizer lllllllllllllllllIlIIlIlllllllIl = new StringTokenizer(lllllllllllllllllIlIIlIllllllllI, ", ");
            final Polygon lllllllllllllllllIlIIlIlllllllII = new Polygon();
            if (processPoly(lllllllllllllllllIlIIlIlllllllII, lllllllllllllllllIlIIlIllllIllIl, lllllllllllllllllIlIIlIlllllllIl) != 2) {
                return;
            }
            final float lllllllllllllllllIlIIllIIIIIIIlI = lllllllllllllllllIlIIlIlllllllII.getPoint(0)[0];
            final float lllllllllllllllllIlIIllIIIIIIIIl = lllllllllllllllllIlIIlIlllllllII.getPoint(0)[1];
            final float lllllllllllllllllIlIIllIIIIIIIII = lllllllllllllllllIlIIlIlllllllII.getPoint(1)[0];
            final float lllllllllllllllllIlIIlIlllllllll = lllllllllllllllllIlIIlIlllllllII.getPoint(1)[1];
        }
        final float lllllllllllllllllIlIIlIlllllIlIl;
        final float lllllllllllllllllIlIIlIlllllIlII;
        final float lllllllllllllllllIlIIlIlllllIIll;
        final float lllllllllllllllllIlIIlIlllllIIlI;
        final float[] lllllllllllllllllIlIIlIlllllIIIl = { lllllllllllllllllIlIIlIlllllIlIl, lllllllllllllllllIlIIlIlllllIlII, lllllllllllllllllIlIIlIlllllIIll, lllllllllllllllllIlIIlIlllllIIlI };
        final float[] lllllllllllllllllIlIIlIlllllIIII = new float[4];
        lllllllllllllllllIlIIlIlllllIllI.transform(lllllllllllllllllIlIIlIlllllIIIl, 0, lllllllllllllllllIlIIlIlllllIIII, 0, 2);
        final Line lllllllllllllllllIlIIlIllllIllll = new Line(lllllllllllllllllIlIIlIlllllIIII[0], lllllllllllllllllIlIIlIlllllIIII[1], lllllllllllllllllIlIIlIlllllIIII[2], lllllllllllllllllIlIIlIlllllIIII[3]);
        final NonGeometricData lllllllllllllllllIlIIlIllllIlllI = Util.getNonGeometricData(lllllllllllllllllIlIIlIllllIllIl);
        lllllllllllllllllIlIIlIllllIlllI.addAttribute("x1", String.valueOf(new StringBuilder().append("").append(lllllllllllllllllIlIIlIlllllIlIl)));
        lllllllllllllllllIlIIlIllllIlllI.addAttribute("x2", String.valueOf(new StringBuilder().append("").append(lllllllllllllllllIlIIlIlllllIIll)));
        lllllllllllllllllIlIIlIllllIlllI.addAttribute("y1", String.valueOf(new StringBuilder().append("").append(lllllllllllllllllIlIIlIlllllIlII)));
        lllllllllllllllllIlIIlIllllIlllI.addAttribute("y2", String.valueOf(new StringBuilder().append("").append(lllllllllllllllllIlIIlIlllllIIlI)));
        lllllllllllllllllIlIIlIllllIllII.addFigure(new Figure(2, lllllllllllllllllIlIIlIllllIllll, lllllllllllllllllIlIIlIllllIlllI, lllllllllllllllllIlIIlIlllllIllI));
    }
    
    @Override
    public boolean handles(final Element lllllllllllllllllIlIIlIlllIllllI) {
        return lllllllllllllllllIlIIlIlllIllllI.getNodeName().equals("line") || (lllllllllllllllllIlIIlIlllIllllI.getNodeName().equals("path") && !"arc".equals(lllllllllllllllllIlIIlIlllIllllI.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type")));
    }
    
    private static int processPoly(final Polygon lllllllllllllllllIlIIllIIIIllIll, final Element lllllllllllllllllIlIIllIIIIllllI, final StringTokenizer lllllllllllllllllIlIIllIIIIllIIl) throws ParsingException {
        int lllllllllllllllllIlIIllIIIIlllII = 0;
        while (lllllllllllllllllIlIIllIIIIllIIl.hasMoreTokens()) {
            final String lllllllllllllllllIlIIllIIIlIIIlI = lllllllllllllllllIlIIllIIIIllIIl.nextToken();
            if (lllllllllllllllllIlIIllIIIlIIIlI.equals("L")) {
                continue;
            }
            if (lllllllllllllllllIlIIllIIIlIIIlI.equals("z")) {
                break;
            }
            if (lllllllllllllllllIlIIllIIIlIIIlI.equals("M")) {
                continue;
            }
            if (lllllllllllllllllIlIIllIIIlIIIlI.equals("C")) {
                return 0;
            }
            final String lllllllllllllllllIlIIllIIIlIIIIl = lllllllllllllllllIlIIllIIIlIIIlI;
            final String lllllllllllllllllIlIIllIIIlIIIII = lllllllllllllllllIlIIllIIIIllIIl.nextToken();
            try {
                final float lllllllllllllllllIlIIllIIIlIIlIl = Float.parseFloat(lllllllllllllllllIlIIllIIIlIIIIl);
                final float lllllllllllllllllIlIIllIIIlIIlII = Float.parseFloat(lllllllllllllllllIlIIllIIIlIIIII);
                lllllllllllllllllIlIIllIIIIllIll.addPoint(lllllllllllllllllIlIIllIIIlIIlIl, lllllllllllllllllIlIIllIIIlIIlII);
                ++lllllllllllllllllIlIIllIIIIlllII;
            }
            catch (NumberFormatException lllllllllllllllllIlIIllIIIlIIIll) {
                throw new ParsingException(lllllllllllllllllIlIIllIIIIllllI.getAttribute("id"), "Invalid token in points list", lllllllllllllllllIlIIllIIIlIIIll);
            }
        }
        return lllllllllllllllllIlIIllIIIIlllII;
    }
}
