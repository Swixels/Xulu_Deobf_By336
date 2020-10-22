package org.newdawn.slick.svg.inkscape;

import org.w3c.dom.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.svg.*;

public class EllipseProcessor implements ElementProcessor
{
    @Override
    public void process(final Loader lllIIIIlllIIIll, final Element lllIIIIlllIIIlI, final Diagram lllIIIIllIlIllI, final Transform lllIIIIlllIIIII) throws ParsingException {
        Transform lllIIIIllIlllll = Util.getTransform(lllIIIIlllIIIlI);
        lllIIIIllIlllll = new Transform(lllIIIIlllIIIII, lllIIIIllIlllll);
        final float lllIIIIllIllllI = Util.getFloatAttribute(lllIIIIlllIIIlI, "cx");
        final float lllIIIIllIlllIl = Util.getFloatAttribute(lllIIIIlllIIIlI, "cy");
        final float lllIIIIllIlllII = Util.getFloatAttribute(lllIIIIlllIIIlI, "rx");
        final float lllIIIIllIllIll = Util.getFloatAttribute(lllIIIIlllIIIlI, "ry");
        final Ellipse lllIIIIllIllIlI = new Ellipse(lllIIIIllIllllI, lllIIIIllIlllIl, lllIIIIllIlllII, lllIIIIllIllIll);
        final Shape lllIIIIllIllIIl = lllIIIIllIllIlI.transform(lllIIIIllIlllll);
        final NonGeometricData lllIIIIllIllIII = Util.getNonGeometricData(lllIIIIlllIIIlI);
        lllIIIIllIllIII.addAttribute("cx", String.valueOf(new StringBuilder().append("").append(lllIIIIllIllllI)));
        lllIIIIllIllIII.addAttribute("cy", String.valueOf(new StringBuilder().append("").append(lllIIIIllIlllIl)));
        lllIIIIllIllIII.addAttribute("rx", String.valueOf(new StringBuilder().append("").append(lllIIIIllIlllII)));
        lllIIIIllIllIII.addAttribute("ry", String.valueOf(new StringBuilder().append("").append(lllIIIIllIllIll)));
        lllIIIIllIlIllI.addFigure(new Figure(1, lllIIIIllIllIIl, lllIIIIllIllIII, lllIIIIllIlllll));
    }
    
    @Override
    public boolean handles(final Element lllIIIIllIIlIIl) {
        return lllIIIIllIIlIIl.getNodeName().equals("ellipse") || (lllIIIIllIIlIIl.getNodeName().equals("path") && "arc".equals(lllIIIIllIIlIIl.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type")));
    }
}
