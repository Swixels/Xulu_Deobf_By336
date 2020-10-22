package org.newdawn.slick.svg.inkscape;

import org.w3c.dom.*;
import java.util.*;
import org.newdawn.slick.svg.*;
import org.newdawn.slick.geom.*;

public class PathProcessor implements ElementProcessor
{
    private static Path processPoly(final Element lllllllllllllllllllIllllIlIIIIlI, final StringTokenizer lllllllllllllllllllIllllIlIIIIIl) throws ParsingException {
        final int lllllllllllllllllllIllllIlIIIIII = 0;
        final ArrayList lllllllllllllllllllIllllIIllllll = new ArrayList();
        boolean lllllllllllllllllllIllllIIlllllI = false;
        boolean lllllllllllllllllllIllllIIllllIl = false;
        Path lllllllllllllllllllIllllIIllllII = null;
        while (lllllllllllllllllllIllllIlIIIIIl.hasMoreTokens()) {
            try {
                final String lllllllllllllllllllIllllIlIIIlII = lllllllllllllllllllIllllIlIIIIIl.nextToken();
                if (lllllllllllllllllllIllllIlIIIlII.equals("L")) {
                    final float lllllllllllllllllllIllllIlIlIIII = Float.parseFloat(lllllllllllllllllllIllllIlIIIIIl.nextToken());
                    final float lllllllllllllllllllIllllIlIIllll = Float.parseFloat(lllllllllllllllllllIllllIlIIIIIl.nextToken());
                    lllllllllllllllllllIllllIIllllII.lineTo(lllllllllllllllllllIllllIlIlIIII, lllllllllllllllllllIllllIlIIllll);
                    continue;
                }
                if (lllllllllllllllllllIllllIlIIIlII.equals("z")) {
                    lllllllllllllllllllIllllIIllllII.close();
                    continue;
                }
                if (lllllllllllllllllllIllllIlIIIlII.equals("M")) {
                    if (!lllllllllllllllllllIllllIIlllllI) {
                        lllllllllllllllllllIllllIIlllllI = true;
                        final float lllllllllllllllllllIllllIlIIlllI = Float.parseFloat(lllllllllllllllllllIllllIlIIIIIl.nextToken());
                        final float lllllllllllllllllllIllllIlIIllIl = Float.parseFloat(lllllllllllllllllllIllllIlIIIIIl.nextToken());
                        lllllllllllllllllllIllllIIllllII = new Path(lllllllllllllllllllIllllIlIIlllI, lllllllllllllllllllIllllIlIIllIl);
                        continue;
                    }
                    lllllllllllllllllllIllllIIllllIl = true;
                    final float lllllllllllllllllllIllllIlIIllII = Float.parseFloat(lllllllllllllllllllIllllIlIIIIIl.nextToken());
                    final float lllllllllllllllllllIllllIlIIlIll = Float.parseFloat(lllllllllllllllllllIllllIlIIIIIl.nextToken());
                    lllllllllllllllllllIllllIIllllII.startHole(lllllllllllllllllllIllllIlIIllII, lllllllllllllllllllIllllIlIIlIll);
                    continue;
                }
                else {
                    if (lllllllllllllllllllIllllIlIIIlII.equals("C")) {
                        lllllllllllllllllllIllllIIllllIl = true;
                        final float lllllllllllllllllllIllllIlIIlIlI = Float.parseFloat(lllllllllllllllllllIllllIlIIIIIl.nextToken());
                        final float lllllllllllllllllllIllllIlIIlIIl = Float.parseFloat(lllllllllllllllllllIllllIlIIIIIl.nextToken());
                        final float lllllllllllllllllllIllllIlIIlIII = Float.parseFloat(lllllllllllllllllllIllllIlIIIIIl.nextToken());
                        final float lllllllllllllllllllIllllIlIIIlll = Float.parseFloat(lllllllllllllllllllIllllIlIIIIIl.nextToken());
                        final float lllllllllllllllllllIllllIlIIIllI = Float.parseFloat(lllllllllllllllllllIllllIlIIIIIl.nextToken());
                        final float lllllllllllllllllllIllllIlIIIlIl = Float.parseFloat(lllllllllllllllllllIllllIlIIIIIl.nextToken());
                        lllllllllllllllllllIllllIIllllII.curveTo(lllllllllllllllllllIllllIlIIIllI, lllllllllllllllllllIllllIlIIIlIl, lllllllllllllllllllIllllIlIIlIlI, lllllllllllllllllllIllllIlIIlIIl, lllllllllllllllllllIllllIlIIlIII, lllllllllllllllllllIllllIlIIIlll);
                        continue;
                    }
                    continue;
                }
            }
            catch (NumberFormatException lllllllllllllllllllIllllIlIIIIll) {
                throw new ParsingException(lllllllllllllllllllIllllIlIIIIlI.getAttribute("id"), "Invalid token in points list", lllllllllllllllllllIllllIlIIIIll);
            }
            break;
        }
        if (!lllllllllllllllllllIllllIIllllIl) {
            return null;
        }
        return lllllllllllllllllllIllllIIllllII;
    }
    
    @Override
    public void process(final Loader lllllllllllllllllllIllllIIlIIIlI, final Element lllllllllllllllllllIllllIIIllIIl, final Diagram lllllllllllllllllllIllllIIIllIII, final Transform lllllllllllllllllllIllllIIIlIlll) throws ParsingException {
        Transform lllllllllllllllllllIllllIIIllllI = Util.getTransform(lllllllllllllllllllIllllIIIllIIl);
        lllllllllllllllllllIllllIIIllllI = new Transform(lllllllllllllllllllIllllIIIlIlll, lllllllllllllllllllIllllIIIllllI);
        String lllllllllllllllllllIllllIIIlllIl = lllllllllllllllllllIllllIIIllIIl.getAttribute("points");
        if (lllllllllllllllllllIllllIIIllIIl.getNodeName().equals("path")) {
            lllllllllllllllllllIllllIIIlllIl = lllllllllllllllllllIllllIIIllIIl.getAttribute("d");
        }
        final StringTokenizer lllllllllllllllllllIllllIIIlllII = new StringTokenizer(lllllllllllllllllllIllllIIIlllIl, ", ");
        final Path lllllllllllllllllllIllllIIIllIll = processPoly(lllllllllllllllllllIllllIIIllIIl, lllllllllllllllllllIllllIIIlllII);
        final NonGeometricData lllllllllllllllllllIllllIIIllIlI = Util.getNonGeometricData(lllllllllllllllllllIllllIIIllIIl);
        if (lllllllllllllllllllIllllIIIllIll != null) {
            final Shape lllllllllllllllllllIllllIIlIIlII = lllllllllllllllllllIllllIIIllIll.transform(lllllllllllllllllllIllllIIIllllI);
            lllllllllllllllllllIllllIIIllIII.addFigure(new Figure(4, lllllllllllllllllllIllllIIlIIlII, lllllllllllllllllllIllllIIIllIlI, lllllllllllllllllllIllllIIIllllI));
        }
    }
    
    @Override
    public boolean handles(final Element lllllllllllllllllllIllllIIIIlllI) {
        return lllllllllllllllllllIllllIIIIlllI.getNodeName().equals("path") && !"arc".equals(lllllllllllllllllllIllllIIIIlllI.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type"));
    }
}
