package org.newdawn.slick.svg.inkscape;

import org.w3c.dom.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.svg.*;

public class RectProcessor implements ElementProcessor
{
    @Override
    public void process(final Loader lllllllllllllllllIlIlIIIIlllIlll, final Element lllllllllllllllllIlIlIIIIlllIllI, final Diagram lllllllllllllllllIlIlIIIIlllIlIl, final Transform lllllllllllllllllIlIlIIIIllIlIIl) throws ParsingException {
        Transform lllllllllllllllllIlIlIIIIlllIIll = Util.getTransform(lllllllllllllllllIlIlIIIIlllIllI);
        lllllllllllllllllIlIlIIIIlllIIll = new Transform(lllllllllllllllllIlIlIIIIllIlIIl, lllllllllllllllllIlIlIIIIlllIIll);
        final float lllllllllllllllllIlIlIIIIlllIIlI = Float.parseFloat(lllllllllllllllllIlIlIIIIlllIllI.getAttribute("width"));
        final float lllllllllllllllllIlIlIIIIlllIIIl = Float.parseFloat(lllllllllllllllllIlIlIIIIlllIllI.getAttribute("height"));
        final float lllllllllllllllllIlIlIIIIlllIIII = Float.parseFloat(lllllllllllllllllIlIlIIIIlllIllI.getAttribute("x"));
        final float lllllllllllllllllIlIlIIIIllIllll = Float.parseFloat(lllllllllllllllllIlIlIIIIlllIllI.getAttribute("y"));
        final Rectangle lllllllllllllllllIlIlIIIIllIlllI = new Rectangle(lllllllllllllllllIlIlIIIIlllIIII, lllllllllllllllllIlIlIIIIllIllll, lllllllllllllllllIlIlIIIIlllIIlI + 1.0f, lllllllllllllllllIlIlIIIIlllIIIl + 1.0f);
        final Shape lllllllllllllllllIlIlIIIIllIllIl = lllllllllllllllllIlIlIIIIllIlllI.transform(lllllllllllllllllIlIlIIIIlllIIll);
        final NonGeometricData lllllllllllllllllIlIlIIIIllIllII = Util.getNonGeometricData(lllllllllllllllllIlIlIIIIlllIllI);
        lllllllllllllllllIlIlIIIIllIllII.addAttribute("width", String.valueOf(new StringBuilder().append("").append(lllllllllllllllllIlIlIIIIlllIIlI)));
        lllllllllllllllllIlIlIIIIllIllII.addAttribute("height", String.valueOf(new StringBuilder().append("").append(lllllllllllllllllIlIlIIIIlllIIIl)));
        lllllllllllllllllIlIlIIIIllIllII.addAttribute("x", String.valueOf(new StringBuilder().append("").append(lllllllllllllllllIlIlIIIIlllIIII)));
        lllllllllllllllllIlIlIIIIllIllII.addAttribute("y", String.valueOf(new StringBuilder().append("").append(lllllllllllllllllIlIlIIIIllIllll)));
        lllllllllllllllllIlIlIIIIlllIlIl.addFigure(new Figure(3, lllllllllllllllllIlIlIIIIllIllIl, lllllllllllllllllIlIlIIIIllIllII, lllllllllllllllllIlIlIIIIlllIIll));
    }
    
    @Override
    public boolean handles(final Element lllllllllllllllllIlIlIIIIlIlllIl) {
        return lllllllllllllllllIlIlIIIIlIlllIl.getNodeName().equals("rect");
    }
}
