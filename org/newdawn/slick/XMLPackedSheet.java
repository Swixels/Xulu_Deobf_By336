package org.newdawn.slick;

import java.util.*;
import org.newdawn.slick.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class XMLPackedSheet
{
    private /* synthetic */ HashMap sprites;
    private /* synthetic */ Image image;
    
    public XMLPackedSheet(final String llllllllllllllllIlIIlllIlIIlIlII, final String llllllllllllllllIlIIlllIlIIlIllI) throws SlickException {
        this.sprites = new HashMap();
        this.image = new Image(llllllllllllllllIlIIlllIlIIlIlII, false, 2);
        try {
            final DocumentBuilder llllllllllllllllIlIIlllIlIIlllII = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            final Document llllllllllllllllIlIIlllIlIIllIll = llllllllllllllllIlIIlllIlIIlllII.parse(ResourceLoader.getResourceAsStream(llllllllllllllllIlIIlllIlIIlIllI));
            final NodeList llllllllllllllllIlIIlllIlIIllIlI = llllllllllllllllIlIIlllIlIIllIll.getElementsByTagName("sprite");
            for (int llllllllllllllllIlIIlllIlIIlllIl = 0; llllllllllllllllIlIIlllIlIIlllIl < llllllllllllllllIlIIlllIlIIllIlI.getLength(); ++llllllllllllllllIlIIlllIlIIlllIl) {
                final Element llllllllllllllllIlIIlllIlIlIIIll = (Element)llllllllllllllllIlIIlllIlIIllIlI.item(llllllllllllllllIlIIlllIlIIlllIl);
                final String llllllllllllllllIlIIlllIlIlIIIlI = llllllllllllllllIlIIlllIlIlIIIll.getAttribute("name");
                final int llllllllllllllllIlIIlllIlIlIIIIl = Integer.parseInt(llllllllllllllllIlIIlllIlIlIIIll.getAttribute("x"));
                final int llllllllllllllllIlIIlllIlIlIIIII = Integer.parseInt(llllllllllllllllIlIIlllIlIlIIIll.getAttribute("y"));
                final int llllllllllllllllIlIIlllIlIIlllll = Integer.parseInt(llllllllllllllllIlIIlllIlIlIIIll.getAttribute("width"));
                final int llllllllllllllllIlIIlllIlIIllllI = Integer.parseInt(llllllllllllllllIlIIlllIlIlIIIll.getAttribute("height"));
                this.sprites.put(llllllllllllllllIlIIlllIlIlIIIlI, this.image.getSubImage(llllllllllllllllIlIIlllIlIlIIIIl, llllllllllllllllIlIIlllIlIlIIIII, llllllllllllllllIlIIlllIlIIlllll, llllllllllllllllIlIIlllIlIIllllI));
            }
        }
        catch (Exception llllllllllllllllIlIIlllIlIIllIIl) {
            throw new SlickException("Failed to parse sprite sheet XML", llllllllllllllllIlIIlllIlIIllIIl);
        }
    }
    
    public Image getSprite(final String llllllllllllllllIlIIlllIlIIIIlIl) {
        return this.sprites.get(llllllllllllllllIlIIlllIlIIIIlIl);
    }
}
