package org.newdawn.slick.svg.inkscape;

import org.w3c.dom.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.svg.*;
import java.util.*;

public class PolygonProcessor implements ElementProcessor
{
    @Override
    public void process(final Loader llllllllllllllllIlIlIlIIIllllIll, final Element llllllllllllllllIlIlIlIIIllllIlI, final Diagram llllllllllllllllIlIlIlIIIlllIIII, final Transform llllllllllllllllIlIlIlIIIllIllll) throws ParsingException {
        Transform llllllllllllllllIlIlIlIIIlllIlll = Util.getTransform(llllllllllllllllIlIlIlIIIllllIlI);
        llllllllllllllllIlIlIlIIIlllIlll = new Transform(llllllllllllllllIlIlIlIIIllIllll, llllllllllllllllIlIlIlIIIlllIlll);
        String llllllllllllllllIlIlIlIIIlllIllI = llllllllllllllllIlIlIlIIIllllIlI.getAttribute("points");
        if (llllllllllllllllIlIlIlIIIllllIlI.getNodeName().equals("path")) {
            llllllllllllllllIlIlIlIIIlllIllI = llllllllllllllllIlIlIlIIIllllIlI.getAttribute("d");
        }
        final StringTokenizer llllllllllllllllIlIlIlIIIlllIlIl = new StringTokenizer(llllllllllllllllIlIlIlIIIlllIllI, ", ");
        final Polygon llllllllllllllllIlIlIlIIIlllIlII = new Polygon();
        final int llllllllllllllllIlIlIlIIIlllIIll = processPoly(llllllllllllllllIlIlIlIIIlllIlII, llllllllllllllllIlIlIlIIIllllIlI, llllllllllllllllIlIlIlIIIlllIlIl);
        final NonGeometricData llllllllllllllllIlIlIlIIIlllIIlI = Util.getNonGeometricData(llllllllllllllllIlIlIlIIIllllIlI);
        if (llllllllllllllllIlIlIlIIIlllIIll > 3) {
            final Shape llllllllllllllllIlIlIlIIIlllllIl = llllllllllllllllIlIlIlIIIlllIlII.transform(llllllllllllllllIlIlIlIIIlllIlll);
            llllllllllllllllIlIlIlIIIlllIIII.addFigure(new Figure(5, llllllllllllllllIlIlIlIIIlllllIl, llllllllllllllllIlIlIlIIIlllIIlI, llllllllllllllllIlIlIlIIIlllIlll));
        }
    }
    
    @Override
    public boolean handles(final Element llllllllllllllllIlIlIlIIIllIIlIl) {
        return llllllllllllllllIlIlIlIIIllIIlIl.getNodeName().equals("polygon") || (llllllllllllllllIlIlIlIIIllIIlIl.getNodeName().equals("path") && !"arc".equals(llllllllllllllllIlIlIlIIIllIIlIl.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type")));
    }
    
    private static int processPoly(final Polygon llllllllllllllllIlIlIlIIlIIllIlI, final Element llllllllllllllllIlIlIlIIlIIlIIlI, final StringTokenizer llllllllllllllllIlIlIlIIlIIlIIIl) throws ParsingException {
        int llllllllllllllllIlIlIlIIlIIlIlll = 0;
        final ArrayList llllllllllllllllIlIlIlIIlIIlIllI = new ArrayList();
        boolean llllllllllllllllIlIlIlIIlIIlIlIl = false;
        boolean llllllllllllllllIlIlIlIIlIIlIlII = false;
        while (llllllllllllllllIlIlIlIIlIIlIIIl.hasMoreTokens()) {
            final String llllllllllllllllIlIlIlIIlIIlllIl = llllllllllllllllIlIlIlIIlIIlIIIl.nextToken();
            if (llllllllllllllllIlIlIlIIlIIlllIl.equals("L")) {
                continue;
            }
            if (llllllllllllllllIlIlIlIIlIIlllIl.equals("z")) {
                llllllllllllllllIlIlIlIIlIIlIlII = true;
                break;
            }
            if (llllllllllllllllIlIlIlIIlIIlllIl.equals("M")) {
                if (llllllllllllllllIlIlIlIIlIIlIlIl) {
                    return 0;
                }
                llllllllllllllllIlIlIlIIlIIlIlIl = true;
            }
            else {
                if (llllllllllllllllIlIlIlIIlIIlllIl.equals("C")) {
                    return 0;
                }
                final String llllllllllllllllIlIlIlIIlIIlllII = llllllllllllllllIlIlIlIIlIIlllIl;
                final String llllllllllllllllIlIlIlIIlIIllIll = llllllllllllllllIlIlIlIIlIIlIIIl.nextToken();
                try {
                    final float llllllllllllllllIlIlIlIIlIlIIIII = Float.parseFloat(llllllllllllllllIlIlIlIIlIIlllII);
                    final float llllllllllllllllIlIlIlIIlIIlllll = Float.parseFloat(llllllllllllllllIlIlIlIIlIIllIll);
                    llllllllllllllllIlIlIlIIlIIllIlI.addPoint(llllllllllllllllIlIlIlIIlIlIIIII, llllllllllllllllIlIlIlIIlIIlllll);
                    ++llllllllllllllllIlIlIlIIlIIlIlll;
                }
                catch (NumberFormatException llllllllllllllllIlIlIlIIlIIllllI) {
                    throw new ParsingException(llllllllllllllllIlIlIlIIlIIlIIlI.getAttribute("id"), "Invalid token in points list", llllllllllllllllIlIlIlIIlIIllllI);
                }
            }
        }
        llllllllllllllllIlIlIlIIlIIllIlI.setClosed(llllllllllllllllIlIlIlIIlIIlIlII);
        return llllllllllllllllIlIlIlIIlIIlIlll;
    }
}
