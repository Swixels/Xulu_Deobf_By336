package org.newdawn.slick.svg.inkscape;

import org.newdawn.slick.svg.*;
import org.w3c.dom.*;

public class InkscapeNonGeometricData extends NonGeometricData
{
    private /* synthetic */ Element element;
    
    public InkscapeNonGeometricData(final String llllllllllllllllIllIIlllIlIIIIlI, final Element llllllllllllllllIllIIlllIlIIIIIl) {
        super(llllllllllllllllIllIIlllIlIIIIlI);
        this.element = llllllllllllllllIllIIlllIlIIIIIl;
    }
    
    @Override
    public String getAttribute(final String llllllllllllllllIllIIlllIIlIlllI) {
        String llllllllllllllllIllIIlllIIllIIIl = super.getAttribute(llllllllllllllllIllIIlllIIlIlllI);
        if (llllllllllllllllIllIIlllIIllIIIl == null) {
            llllllllllllllllIllIIlllIIllIIIl = this.element.getAttribute(llllllllllllllllIllIIlllIIlIlllI);
        }
        return llllllllllllllllIllIIlllIIllIIIl;
    }
    
    public Element getElement() {
        return this.element;
    }
}
