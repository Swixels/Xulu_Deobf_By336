package org.newdawn.slick.svg.inkscape;

import org.w3c.dom.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.svg.*;

public class GroupProcessor implements ElementProcessor
{
    @Override
    public void process(final Loader lllIlIIlIIIllII, final Element lllIlIIlIIIlIll, final Diagram lllIlIIlIIIlIlI, final Transform lllIlIIlIIIlIIl) throws ParsingException {
        Transform lllIlIIlIIIlIII = Util.getTransform(lllIlIIlIIIlIll);
        lllIlIIlIIIlIII = new Transform(lllIlIIlIIIlIIl, lllIlIIlIIIlIII);
        lllIlIIlIIIllII.loadChildren(lllIlIIlIIIlIll, lllIlIIlIIIlIII);
    }
    
    @Override
    public boolean handles(final Element lllIlIIlIIlIIll) {
        return lllIlIIlIIlIIll.getNodeName().equals("g");
    }
}
