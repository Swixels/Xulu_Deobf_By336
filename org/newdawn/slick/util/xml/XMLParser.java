package org.newdawn.slick.util.xml;

import org.newdawn.slick.util.*;
import org.newdawn.slick.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class XMLParser
{
    private static /* synthetic */ DocumentBuilderFactory factory;
    
    public XMLElement parse(final String lIlIlIIIllIIlII) throws SlickException {
        return this.parse(lIlIlIIIllIIlII, ResourceLoader.getResourceAsStream(lIlIlIIIllIIlII));
    }
    
    public XMLElement parse(final String lIlIlIIIlIllIIl, final InputStream lIlIlIIIlIllIII) throws SlickXMLException {
        try {
            if (XMLParser.factory == null) {
                XMLParser.factory = DocumentBuilderFactory.newInstance();
            }
            final DocumentBuilder lIlIlIIIlIlllll = XMLParser.factory.newDocumentBuilder();
            final Document lIlIlIIIlIllllI = lIlIlIIIlIlllll.parse(lIlIlIIIlIllIII);
            return new XMLElement(lIlIlIIIlIllllI.getDocumentElement());
        }
        catch (Exception lIlIlIIIlIlllIl) {
            throw new SlickXMLException(String.valueOf(new StringBuilder().append("Failed to parse document: ").append(lIlIlIIIlIllIIl)), lIlIlIIIlIlllIl);
        }
    }
}
