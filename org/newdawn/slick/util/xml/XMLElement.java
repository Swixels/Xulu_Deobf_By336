package org.newdawn.slick.util.xml;

import org.w3c.dom.*;

public class XMLElement
{
    private /* synthetic */ XMLElementList children;
    private /* synthetic */ String name;
    private /* synthetic */ Element dom;
    
    public XMLElementList getChildrenByName(final String lIlllllIlIIlIII) {
        final XMLElementList lIlllllIlIIlIll = new XMLElementList();
        final XMLElementList lIlllllIlIIlIlI = this.getChildren();
        for (int lIlllllIlIIlllI = 0; lIlllllIlIIlllI < lIlllllIlIIlIlI.size(); ++lIlllllIlIIlllI) {
            if (lIlllllIlIIlIlI.get(lIlllllIlIIlllI).getName().equals(lIlllllIlIIlIII)) {
                lIlllllIlIIlIll.add(lIlllllIlIIlIlI.get(lIlllllIlIIlllI));
            }
        }
        return lIlllllIlIIlIll;
    }
    
    public int getIntAttribute(final String lIllllllIlIIIlI) throws SlickXMLException {
        try {
            return Integer.parseInt(this.getAttribute(lIllllllIlIIIlI));
        }
        catch (NumberFormatException lIllllllIlIIlII) {
            throw new SlickXMLException(String.valueOf(new StringBuilder().append("Value read: '").append(this.getAttribute(lIllllllIlIIIlI)).append("' is not an integer")), lIllllllIlIIlII);
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getIntAttribute(final String lIllllllIIlIlIl, final int lIllllllIIlIlll) throws SlickXMLException {
        try {
            return Integer.parseInt(this.getAttribute(lIllllllIIlIlIl, String.valueOf(new StringBuilder().append("").append(lIllllllIIlIlll))));
        }
        catch (NumberFormatException lIllllllIIllIlI) {
            throw new SlickXMLException(String.valueOf(new StringBuilder().append("Value read: '").append(this.getAttribute(lIllllllIIlIlIl, String.valueOf(new StringBuilder().append("").append(lIllllllIIlIlll)))).append("' is not an integer")), lIllllllIIllIlI);
        }
    }
    
    public double getDoubleAttribute(final String lIllllllIIIIIII, final double lIllllllIIIIIlI) throws SlickXMLException {
        try {
            return Double.parseDouble(this.getAttribute(lIllllllIIIIIII, String.valueOf(new StringBuilder().append("").append(lIllllllIIIIIlI))));
        }
        catch (NumberFormatException lIllllllIIIIlIl) {
            throw new SlickXMLException(String.valueOf(new StringBuilder().append("Value read: '").append(this.getAttribute(lIllllllIIIIIII, String.valueOf(new StringBuilder().append("").append(lIllllllIIIIIlI)))).append("' is not a double")), lIllllllIIIIlIl);
        }
    }
    
    @Override
    public String toString() {
        String lIlllllIIlllllI = String.valueOf(new StringBuilder().append("[XML ").append(this.getName()));
        final String[] lIlllllIIllllIl = this.getAttributeNames();
        for (int lIlllllIlIIIIII = 0; lIlllllIlIIIIII < lIlllllIIllllIl.length; ++lIlllllIlIIIIII) {
            lIlllllIIlllllI = String.valueOf(new StringBuilder().append(lIlllllIIlllllI).append(" ").append(lIlllllIIllllIl[lIlllllIlIIIIII]).append("=").append(this.getAttribute(lIlllllIIllllIl[lIlllllIlIIIIII])));
        }
        lIlllllIIlllllI = String.valueOf(new StringBuilder().append(lIlllllIIlllllI).append("]"));
        return lIlllllIIlllllI;
    }
    
    public boolean getBooleanAttribute(final String lIlllllIllIllll, final boolean lIlllllIllIlllI) throws SlickXMLException {
        final String lIlllllIllIllIl = this.getAttribute(lIlllllIllIllll, String.valueOf(new StringBuilder().append("").append(lIlllllIllIlllI)));
        if (lIlllllIllIllIl.equalsIgnoreCase("true")) {
            return true;
        }
        if (lIlllllIllIllIl.equalsIgnoreCase("false")) {
            return false;
        }
        throw new SlickXMLException(String.valueOf(new StringBuilder().append("Value read: '").append(this.getAttribute(lIlllllIllIllll, String.valueOf(new StringBuilder().append("").append(lIlllllIllIlllI)))).append("' is not a boolean")));
    }
    
    public String getAttribute(final String lIllllllIlIlIlI, final String lIllllllIlIlIIl) {
        final String lIllllllIlIllII = this.dom.getAttribute(lIllllllIlIlIlI);
        if (lIllllllIlIllII == null || lIllllllIlIllII.length() == 0) {
            return lIllllllIlIlIIl;
        }
        return lIllllllIlIllII;
    }
    
    XMLElement(final Element lIlllllllIIlIIl) {
        this.dom = lIlllllllIIlIIl;
        this.name = this.dom.getTagName();
    }
    
    public XMLElementList getChildren() {
        if (this.children != null) {
            return this.children;
        }
        final NodeList lIlllllIlIlIlll = this.dom.getChildNodes();
        this.children = new XMLElementList();
        for (int lIlllllIlIllIIl = 0; lIlllllIlIllIIl < lIlllllIlIlIlll.getLength(); ++lIlllllIlIllIIl) {
            if (lIlllllIlIlIlll.item(lIlllllIlIllIIl) instanceof Element) {
                this.children.add(new XMLElement((Element)lIlllllIlIlIlll.item(lIlllllIlIllIIl)));
            }
        }
        return this.children;
    }
    
    public String[] getAttributeNames() {
        final NamedNodeMap lIlllllllIIIIlI = this.dom.getAttributes();
        final String[] lIlllllllIIIIIl = new String[lIlllllllIIIIlI.getLength()];
        for (int lIlllllllIIIlII = 0; lIlllllllIIIlII < lIlllllllIIIIIl.length; ++lIlllllllIIIlII) {
            lIlllllllIIIIIl[lIlllllllIIIlII] = lIlllllllIIIIlI.item(lIlllllllIIIlII).getNodeName();
        }
        return lIlllllllIIIIIl;
    }
    
    public boolean getBooleanAttribute(final String lIlllllIllllIIl) throws SlickXMLException {
        final String lIlllllIllllIII = this.getAttribute(lIlllllIllllIIl);
        if (lIlllllIllllIII.equalsIgnoreCase("true")) {
            return true;
        }
        if (lIlllllIllllIII.equalsIgnoreCase("false")) {
            return false;
        }
        throw new SlickXMLException(String.valueOf(new StringBuilder().append("Value read: '").append(this.getAttribute(lIlllllIllllIIl)).append("' is not a boolean")));
    }
    
    public double getDoubleAttribute(final String lIllllllIIIllIl) throws SlickXMLException {
        try {
            return Double.parseDouble(this.getAttribute(lIllllllIIIllIl));
        }
        catch (NumberFormatException lIllllllIIIllll) {
            throw new SlickXMLException(String.valueOf(new StringBuilder().append("Value read: '").append(this.getAttribute(lIllllllIIIllIl)).append("' is not a double")), lIllllllIIIllll);
        }
    }
    
    public String getContent() {
        String lIlllllIllIIIlI = "";
        final NodeList lIlllllIllIIIIl = this.dom.getChildNodes();
        for (int lIlllllIllIIlII = 0; lIlllllIllIIlII < lIlllllIllIIIIl.getLength(); ++lIlllllIllIIlII) {
            if (lIlllllIllIIIIl.item(lIlllllIllIIlII) instanceof Text) {
                lIlllllIllIIIlI = String.valueOf(new StringBuilder().append(lIlllllIllIIIlI).append(lIlllllIllIIIIl.item(lIlllllIllIIlII).getNodeValue()));
            }
        }
        return lIlllllIllIIIlI;
    }
    
    public String getAttribute(final String lIllllllIllIllI) {
        return this.dom.getAttribute(lIllllllIllIllI);
    }
}
