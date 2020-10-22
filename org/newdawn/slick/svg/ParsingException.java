package org.newdawn.slick.svg;

import org.newdawn.slick.*;
import org.w3c.dom.*;

public class ParsingException extends SlickException
{
    public ParsingException(final String lllllllllllllllllIlIllllIlIIlIIl, final String lllllllllllllllllIlIllllIlIIIlIl) {
        super(String.valueOf(new StringBuilder().append("(").append(lllllllllllllllllIlIllllIlIIlIIl).append(") ").append(lllllllllllllllllIlIllllIlIIIlIl)));
    }
    
    public ParsingException(final String lllllllllllllllllIlIllllIllIIIII, final String lllllllllllllllllIlIllllIlIlllll, final Throwable lllllllllllllllllIlIllllIlIllllI) {
        super(String.valueOf(new StringBuilder().append("(").append(lllllllllllllllllIlIllllIllIIIII).append(") ").append(lllllllllllllllllIlIllllIlIlllll)), lllllllllllllllllIlIllllIlIllllI);
    }
    
    public ParsingException(final Element lllllllllllllllllIlIllllIIllllIl, final String lllllllllllllllllIlIllllIIllllll) {
        super(String.valueOf(new StringBuilder().append("(").append(lllllllllllllllllIlIllllIIllllIl.getAttribute("id")).append(") ").append(lllllllllllllllllIlIllllIIllllll)));
    }
    
    public ParsingException(final Element lllllllllllllllllIlIllllIlIlIIII, final String lllllllllllllllllIlIllllIlIIllll, final Throwable lllllllllllllllllIlIllllIlIlIIlI) {
        super(String.valueOf(new StringBuilder().append("(").append(lllllllllllllllllIlIllllIlIlIIII.getAttribute("id")).append(") ").append(lllllllllllllllllIlIllllIlIIllll)), lllllllllllllllllIlIllllIlIlIIlI);
    }
}
