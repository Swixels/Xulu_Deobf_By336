package org.newdawn.slick.svg.inkscape;

import org.w3c.dom.*;
import org.newdawn.slick.svg.*;
import org.newdawn.slick.geom.*;

public class UseProcessor implements ElementProcessor
{
    @Override
    public void process(final Loader llIllllllIIlI, final Element llIllllllIIIl, final Diagram llIlllllIIlIl, final Transform llIlllllIllll) throws ParsingException {
        final String llIlllllIlllI = llIllllllIIIl.getAttributeNS("http://www.w3.org/1999/xlink", "href");
        final String llIlllllIllIl = Util.getAsReference(llIlllllIlllI);
        final Figure llIlllllIllII = llIlllllIIlIl.getFigureByID(llIlllllIllIl);
        if (llIlllllIllII == null) {
            throw new ParsingException(llIllllllIIIl, String.valueOf(new StringBuilder().append("Unable to locate referenced element: ").append(llIlllllIllIl)));
        }
        final Transform llIlllllIlIll = Util.getTransform(llIllllllIIIl);
        final Transform llIlllllIlIlI = llIlllllIlIll.concatenate(llIlllllIllII.getTransform());
        final NonGeometricData llIlllllIlIIl = Util.getNonGeometricData(llIllllllIIIl);
        final Shape llIlllllIlIII = llIlllllIllII.getShape().transform(llIlllllIlIlI);
        llIlllllIlIIl.addAttribute("fill", llIlllllIllII.getData().getAttribute("fill"));
        llIlllllIlIIl.addAttribute("stroke", llIlllllIllII.getData().getAttribute("stroke"));
        llIlllllIlIIl.addAttribute("opacity", llIlllllIllII.getData().getAttribute("opacity"));
        llIlllllIlIIl.addAttribute("stroke-width", llIlllllIllII.getData().getAttribute("stroke-width"));
        final Figure llIlllllIIlll = new Figure(llIlllllIllII.getType(), llIlllllIlIII, llIlllllIlIIl, llIlllllIlIlI);
        llIlllllIIlIl.addFigure(llIlllllIIlll);
    }
    
    @Override
    public boolean handles(final Element llIlllllllllI) {
        return llIlllllllllI.getNodeName().equals("use");
    }
}
