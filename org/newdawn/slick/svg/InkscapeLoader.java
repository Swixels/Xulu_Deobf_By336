package org.newdawn.slick.svg;

import java.util.*;
import org.newdawn.slick.util.*;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.svg.inkscape.*;
import org.xml.sax.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class InkscapeLoader implements Loader
{
    private static /* synthetic */ ArrayList processors;
    public static /* synthetic */ int RADIAL_TRIANGULATION_LEVEL;
    private /* synthetic */ Diagram diagram;
    
    private InkscapeLoader() {
    }
    
    public static Diagram load(final String lllllllllllllllllIlIIllIlllIlIII, final boolean lllllllllllllllllIlIIllIlllIIlll) throws SlickException {
        return load(ResourceLoader.getResourceAsStream(lllllllllllllllllIlIIllIlllIlIII), lllllllllllllllllIlIIllIlllIIlll);
    }
    
    public static Diagram load(final InputStream lllllllllllllllllIlIIllIllIlllll, final boolean lllllllllllllllllIlIIllIllIlllII) throws SlickException {
        return new InkscapeLoader().loadDiagram(lllllllllllllllllIlIIllIllIlllll, lllllllllllllllllIlIIllIllIlllII);
    }
    
    public static void addElementProcessor(final ElementProcessor lllllllllllllllllIlIIllIlllIlIll) {
        InkscapeLoader.processors.add(lllllllllllllllllIlIIllIlllIlIll);
    }
    
    private Diagram loadDiagram(final InputStream lllllllllllllllllIlIIllIllIlIIll) throws SlickException {
        return this.loadDiagram(lllllllllllllllllIlIIllIllIlIIll, false);
    }
    
    public static Diagram load(final String lllllllllllllllllIlIIllIlllIIIll) throws SlickException {
        return load(ResourceLoader.getResourceAsStream(lllllllllllllllllIlIIllIlllIIIll), false);
    }
    
    private void loadElement(final Element lllllllllllllllllIlIIllIlIIlIllI, final Transform lllllllllllllllllIlIIllIlIIllIII) throws ParsingException {
        for (int lllllllllllllllllIlIIllIlIIllIll = 0; lllllllllllllllllIlIIllIlIIllIll < InkscapeLoader.processors.size(); ++lllllllllllllllllIlIIllIlIIllIll) {
            final ElementProcessor lllllllllllllllllIlIIllIlIIlllII = InkscapeLoader.processors.get(lllllllllllllllllIlIIllIlIIllIll);
            if (lllllllllllllllllIlIIllIlIIlllII.handles(lllllllllllllllllIlIIllIlIIlIllI)) {
                lllllllllllllllllIlIIllIlIIlllII.process(this, lllllllllllllllllIlIIllIlIIlIllI, this.diagram, lllllllllllllllllIlIIllIlIIllIII);
            }
        }
    }
    
    @Override
    public void loadChildren(final Element lllllllllllllllllIlIIllIlIlIlIIl, final Transform lllllllllllllllllIlIIllIlIlIIlII) throws ParsingException {
        final NodeList lllllllllllllllllIlIIllIlIlIIlll = lllllllllllllllllIlIIllIlIlIlIIl.getChildNodes();
        for (int lllllllllllllllllIlIIllIlIlIlIll = 0; lllllllllllllllllIlIIllIlIlIlIll < lllllllllllllllllIlIIllIlIlIIlll.getLength(); ++lllllllllllllllllIlIIllIlIlIlIll) {
            if (lllllllllllllllllIlIIllIlIlIIlll.item(lllllllllllllllllIlIIllIlIlIlIll) instanceof Element) {
                this.loadElement((Element)lllllllllllllllllIlIIllIlIlIIlll.item(lllllllllllllllllIlIIllIlIlIlIll), lllllllllllllllllIlIIllIlIlIIlII);
            }
        }
    }
    
    static {
        InkscapeLoader.RADIAL_TRIANGULATION_LEVEL = 1;
        InkscapeLoader.processors = new ArrayList();
        addElementProcessor(new RectProcessor());
        addElementProcessor(new EllipseProcessor());
        addElementProcessor(new PolygonProcessor());
        addElementProcessor(new PathProcessor());
        addElementProcessor(new LineProcessor());
        addElementProcessor(new GroupProcessor());
        addElementProcessor(new DefsProcessor());
        addElementProcessor(new UseProcessor());
    }
    
    private Diagram loadDiagram(final InputStream lllllllllllllllllIlIIllIlIllllIl, final boolean lllllllllllllllllIlIIllIlIllllII) throws SlickException {
        try {
            final DocumentBuilderFactory lllllllllllllllllIlIIllIllIIIlll = DocumentBuilderFactory.newInstance();
            lllllllllllllllllIlIIllIllIIIlll.setValidating(false);
            lllllllllllllllllIlIIllIllIIIlll.setNamespaceAware(true);
            final DocumentBuilder lllllllllllllllllIlIIllIllIIIllI = lllllllllllllllllIlIIllIllIIIlll.newDocumentBuilder();
            lllllllllllllllllIlIIllIllIIIllI.setEntityResolver(new EntityResolver() {
                @Override
                public InputSource resolveEntity(final String llllllllllllllllIllIIlIIIIIIlllI, final String llllllllllllllllIllIIlIIIIIIllIl) throws SAXException, IOException {
                    return new InputSource(new ByteArrayInputStream(new byte[0]));
                }
            });
            final Document lllllllllllllllllIlIIllIllIIIlIl = lllllllllllllllllIlIIllIllIIIllI.parse(lllllllllllllllllIlIIllIlIllllIl);
            final Element lllllllllllllllllIlIIllIllIIIlII = lllllllllllllllllIlIIllIllIIIlIl.getDocumentElement();
            String lllllllllllllllllIlIIllIllIIIIll;
            for (lllllllllllllllllIlIIllIllIIIIll = lllllllllllllllllIlIIllIllIIIlII.getAttribute("width"); Character.isLetter(lllllllllllllllllIlIIllIllIIIIll.charAt(lllllllllllllllllIlIIllIllIIIIll.length() - 1)); lllllllllllllllllIlIIllIllIIIIll = lllllllllllllllllIlIIllIllIIIIll.substring(0, lllllllllllllllllIlIIllIllIIIIll.length() - 1)) {}
            String lllllllllllllllllIlIIllIllIIIIlI;
            for (lllllllllllllllllIlIIllIllIIIIlI = lllllllllllllllllIlIIllIllIIIlII.getAttribute("height"); Character.isLetter(lllllllllllllllllIlIIllIllIIIIlI.charAt(lllllllllllllllllIlIIllIllIIIIlI.length() - 1)); lllllllllllllllllIlIIllIllIIIIlI = lllllllllllllllllIlIIllIllIIIIlI.substring(0, lllllllllllllllllIlIIllIllIIIIlI.length() - 1)) {}
            final float lllllllllllllllllIlIIllIllIIIIIl = Float.parseFloat(lllllllllllllllllIlIIllIllIIIIll);
            float lllllllllllllllllIlIIllIllIIIIII = Float.parseFloat(lllllllllllllllllIlIIllIllIIIIlI);
            this.diagram = new Diagram(lllllllllllllllllIlIIllIllIIIIIl, lllllllllllllllllIlIIllIllIIIIII);
            if (!lllllllllllllllllIlIIllIlIllllII) {
                lllllllllllllllllIlIIllIllIIIIII = 0.0f;
            }
            this.loadChildren(lllllllllllllllllIlIIllIllIIIlII, Transform.createTranslateTransform(0.0f, -lllllllllllllllllIlIIllIllIIIIII));
            return this.diagram;
        }
        catch (Exception lllllllllllllllllIlIIllIlIllllll) {
            throw new SlickException("Failed to load inkscape document", lllllllllllllllllIlIIllIlIllllll);
        }
    }
}
