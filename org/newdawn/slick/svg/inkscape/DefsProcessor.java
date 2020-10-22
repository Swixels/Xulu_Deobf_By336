package org.newdawn.slick.svg.inkscape;

import org.newdawn.slick.geom.*;
import org.newdawn.slick.util.*;
import java.util.*;
import org.newdawn.slick.*;
import org.w3c.dom.*;
import org.newdawn.slick.svg.*;

public class DefsProcessor implements ElementProcessor
{
    @Override
    public void process(final Loader lllllllllllllllllIlIlIllIIIIIlII, final Element lllllllllllllllllIlIlIlIlllllIll, final Diagram lllllllllllllllllIlIlIllIIIIIIlI, final Transform lllllllllllllllllIlIlIllIIIIIIIl) throws ParsingException {
        final NodeList lllllllllllllllllIlIlIllIIIIIIII = lllllllllllllllllIlIlIlIlllllIll.getElementsByTagName("pattern");
        for (int lllllllllllllllllIlIlIllIIlIIIIl = 0; lllllllllllllllllIlIlIllIIlIIIIl < lllllllllllllllllIlIlIllIIIIIIII.getLength(); ++lllllllllllllllllIlIlIllIIlIIIIl) {
            final Element lllllllllllllllllIlIlIllIIlIIllI = (Element)lllllllllllllllllIlIlIllIIIIIIII.item(lllllllllllllllllIlIlIllIIlIIIIl);
            final NodeList lllllllllllllllllIlIlIllIIlIIlIl = lllllllllllllllllIlIlIllIIlIIllI.getElementsByTagName("image");
            if (lllllllllllllllllIlIlIllIIlIIlIl.getLength() == 0) {
                Log.warn("Pattern 1981 does not specify an image. Only image patterns are supported.");
            }
            else {
                final Element lllllllllllllllllIlIlIllIIlIIlII = (Element)lllllllllllllllllIlIlIllIIlIIlIl.item(0);
                final String lllllllllllllllllIlIlIllIIlIIIll = lllllllllllllllllIlIlIllIIlIIllI.getAttribute("id");
                final String lllllllllllllllllIlIlIllIIlIIIlI = lllllllllllllllllIlIlIllIIlIIlII.getAttributeNS("http://www.w3.org/1999/xlink", "href");
                lllllllllllllllllIlIlIllIIIIIIlI.addPatternDef(lllllllllllllllllIlIlIllIIlIIIll, lllllllllllllllllIlIlIllIIlIIIlI);
            }
        }
        final NodeList lllllllllllllllllIlIlIlIllllllll = lllllllllllllllllIlIlIlIlllllIll.getElementsByTagName("linearGradient");
        final ArrayList lllllllllllllllllIlIlIlIlllllllI = new ArrayList();
        for (int lllllllllllllllllIlIlIllIIIlIlII = 0; lllllllllllllllllIlIlIllIIIlIlII < lllllllllllllllllIlIlIlIllllllll.getLength(); ++lllllllllllllllllIlIlIllIIIlIlII) {
            final Element lllllllllllllllllIlIlIllIIIllIII = (Element)lllllllllllllllllIlIlIlIllllllll.item(lllllllllllllllllIlIlIllIIIlIlII);
            final String lllllllllllllllllIlIlIllIIIlIlll = lllllllllllllllllIlIlIllIIIllIII.getAttribute("id");
            final Gradient lllllllllllllllllIlIlIllIIIlIllI = new Gradient(lllllllllllllllllIlIlIllIIIlIlll, false);
            lllllllllllllllllIlIlIllIIIlIllI.setTransform(Util.getTransform(lllllllllllllllllIlIlIllIIIllIII, "gradientTransform"));
            if (this.stringLength(lllllllllllllllllIlIlIllIIIllIII.getAttribute("x1")) > 0) {
                lllllllllllllllllIlIlIllIIIlIllI.setX1(Float.parseFloat(lllllllllllllllllIlIlIllIIIllIII.getAttribute("x1")));
            }
            if (this.stringLength(lllllllllllllllllIlIlIllIIIllIII.getAttribute("x2")) > 0) {
                lllllllllllllllllIlIlIllIIIlIllI.setX2(Float.parseFloat(lllllllllllllllllIlIlIllIIIllIII.getAttribute("x2")));
            }
            if (this.stringLength(lllllllllllllllllIlIlIllIIIllIII.getAttribute("y1")) > 0) {
                lllllllllllllllllIlIlIllIIIlIllI.setY1(Float.parseFloat(lllllllllllllllllIlIlIllIIIllIII.getAttribute("y1")));
            }
            if (this.stringLength(lllllllllllllllllIlIlIllIIIllIII.getAttribute("y2")) > 0) {
                lllllllllllllllllIlIlIllIIIlIllI.setY2(Float.parseFloat(lllllllllllllllllIlIlIllIIIllIII.getAttribute("y2")));
            }
            final String lllllllllllllllllIlIlIllIIIlIlIl = lllllllllllllllllIlIlIllIIIllIII.getAttributeNS("http://www.w3.org/1999/xlink", "href");
            if (this.stringLength(lllllllllllllllllIlIlIllIIIlIlIl) > 0) {
                lllllllllllllllllIlIlIllIIIlIllI.reference(lllllllllllllllllIlIlIllIIIlIlIl.substring(1));
                lllllllllllllllllIlIlIlIlllllllI.add(lllllllllllllllllIlIlIllIIIlIllI);
            }
            else {
                final NodeList lllllllllllllllllIlIlIllIIIllIIl = lllllllllllllllllIlIlIllIIIllIII.getElementsByTagName("stop");
                for (int lllllllllllllllllIlIlIllIIIllIlI = 0; lllllllllllllllllIlIlIllIIIllIlI < lllllllllllllllllIlIlIllIIIllIIl.getLength(); ++lllllllllllllllllIlIlIllIIIllIlI) {
                    final Element lllllllllllllllllIlIlIllIIlIIIII = (Element)lllllllllllllllllIlIlIllIIIllIIl.item(lllllllllllllllllIlIlIllIIIllIlI);
                    final float lllllllllllllllllIlIlIllIIIlllll = Float.parseFloat(lllllllllllllllllIlIlIllIIlIIIII.getAttribute("offset"));
                    final String lllllllllllllllllIlIlIllIIIllllI = Util.extractStyle(lllllllllllllllllIlIlIllIIlIIIII.getAttribute("style"), "stop-color");
                    final String lllllllllllllllllIlIlIllIIIlllIl = Util.extractStyle(lllllllllllllllllIlIlIllIIlIIIII.getAttribute("style"), "stop-opacity");
                    final int lllllllllllllllllIlIlIllIIIlllII = Integer.parseInt(lllllllllllllllllIlIlIllIIIllllI.substring(1), 16);
                    final Color lllllllllllllllllIlIlIllIIIllIll = new Color(lllllllllllllllllIlIlIllIIIlllII);
                    lllllllllllllllllIlIlIllIIIllIll.a = Float.parseFloat(lllllllllllllllllIlIlIllIIIlllIl);
                    lllllllllllllllllIlIlIllIIIlIllI.addStep(lllllllllllllllllIlIlIllIIIlllll, lllllllllllllllllIlIlIllIIIllIll);
                }
                lllllllllllllllllIlIlIllIIIlIllI.getImage();
            }
            lllllllllllllllllIlIlIllIIIIIIlI.addGradient(lllllllllllllllllIlIlIllIIIlIlll, lllllllllllllllllIlIlIllIIIlIllI);
        }
        final NodeList lllllllllllllllllIlIlIlIllllllIl = lllllllllllllllllIlIlIlIlllllIll.getElementsByTagName("radialGradient");
        for (int lllllllllllllllllIlIlIllIIIIIlll = 0; lllllllllllllllllIlIlIllIIIIIlll < lllllllllllllllllIlIlIlIllllllIl.getLength(); ++lllllllllllllllllIlIlIllIIIIIlll) {
            final Element lllllllllllllllllIlIlIllIIIIlIll = (Element)lllllllllllllllllIlIlIlIllllllIl.item(lllllllllllllllllIlIlIllIIIIIlll);
            final String lllllllllllllllllIlIlIllIIIIlIlI = lllllllllllllllllIlIlIllIIIIlIll.getAttribute("id");
            final Gradient lllllllllllllllllIlIlIllIIIIlIIl = new Gradient(lllllllllllllllllIlIlIllIIIIlIlI, true);
            lllllllllllllllllIlIlIllIIIIlIIl.setTransform(Util.getTransform(lllllllllllllllllIlIlIllIIIIlIll, "gradientTransform"));
            if (this.stringLength(lllllllllllllllllIlIlIllIIIIlIll.getAttribute("cx")) > 0) {
                lllllllllllllllllIlIlIllIIIIlIIl.setX1(Float.parseFloat(lllllllllllllllllIlIlIllIIIIlIll.getAttribute("cx")));
            }
            if (this.stringLength(lllllllllllllllllIlIlIllIIIIlIll.getAttribute("cy")) > 0) {
                lllllllllllllllllIlIlIllIIIIlIIl.setY1(Float.parseFloat(lllllllllllllllllIlIlIllIIIIlIll.getAttribute("cy")));
            }
            if (this.stringLength(lllllllllllllllllIlIlIllIIIIlIll.getAttribute("fx")) > 0) {
                lllllllllllllllllIlIlIllIIIIlIIl.setX2(Float.parseFloat(lllllllllllllllllIlIlIllIIIIlIll.getAttribute("fx")));
            }
            if (this.stringLength(lllllllllllllllllIlIlIllIIIIlIll.getAttribute("fy")) > 0) {
                lllllllllllllllllIlIlIllIIIIlIIl.setY2(Float.parseFloat(lllllllllllllllllIlIlIllIIIIlIll.getAttribute("fy")));
            }
            if (this.stringLength(lllllllllllllllllIlIlIllIIIIlIll.getAttribute("r")) > 0) {
                lllllllllllllllllIlIlIllIIIIlIIl.setR(Float.parseFloat(lllllllllllllllllIlIlIllIIIIlIll.getAttribute("r")));
            }
            final String lllllllllllllllllIlIlIllIIIIlIII = lllllllllllllllllIlIlIllIIIIlIll.getAttributeNS("http://www.w3.org/1999/xlink", "href");
            if (this.stringLength(lllllllllllllllllIlIlIllIIIIlIII) > 0) {
                lllllllllllllllllIlIlIllIIIIlIIl.reference(lllllllllllllllllIlIlIllIIIIlIII.substring(1));
                lllllllllllllllllIlIlIlIlllllllI.add(lllllllllllllllllIlIlIllIIIIlIIl);
            }
            else {
                final NodeList lllllllllllllllllIlIlIllIIIIllII = lllllllllllllllllIlIlIllIIIIlIll.getElementsByTagName("stop");
                for (int lllllllllllllllllIlIlIllIIIIllIl = 0; lllllllllllllllllIlIlIllIIIIllIl < lllllllllllllllllIlIlIllIIIIllII.getLength(); ++lllllllllllllllllIlIlIllIIIIllIl) {
                    final Element lllllllllllllllllIlIlIllIIIlIIll = (Element)lllllllllllllllllIlIlIllIIIIllII.item(lllllllllllllllllIlIlIllIIIIllIl);
                    final float lllllllllllllllllIlIlIllIIIlIIlI = Float.parseFloat(lllllllllllllllllIlIlIllIIIlIIll.getAttribute("offset"));
                    final String lllllllllllllllllIlIlIllIIIlIIIl = Util.extractStyle(lllllllllllllllllIlIlIllIIIlIIll.getAttribute("style"), "stop-color");
                    final String lllllllllllllllllIlIlIllIIIlIIII = Util.extractStyle(lllllllllllllllllIlIlIllIIIlIIll.getAttribute("style"), "stop-opacity");
                    final int lllllllllllllllllIlIlIllIIIIllll = Integer.parseInt(lllllllllllllllllIlIlIllIIIlIIIl.substring(1), 16);
                    final Color lllllllllllllllllIlIlIllIIIIlllI = new Color(lllllllllllllllllIlIlIllIIIIllll);
                    lllllllllllllllllIlIlIllIIIIlllI.a = Float.parseFloat(lllllllllllllllllIlIlIllIIIlIIII);
                    lllllllllllllllllIlIlIllIIIIlIIl.addStep(lllllllllllllllllIlIlIllIIIlIIlI, lllllllllllllllllIlIlIllIIIIlllI);
                }
                lllllllllllllllllIlIlIllIIIIlIIl.getImage();
            }
            lllllllllllllllllIlIlIllIIIIIIlI.addGradient(lllllllllllllllllIlIlIllIIIIlIlI, lllllllllllllllllIlIlIllIIIIlIIl);
        }
        for (int lllllllllllllllllIlIlIllIIIIIllI = 0; lllllllllllllllllIlIlIllIIIIIllI < lllllllllllllllllIlIlIlIlllllllI.size(); ++lllllllllllllllllIlIlIllIIIIIllI) {
            lllllllllllllllllIlIlIlIlllllllI.get(lllllllllllllllllIlIlIllIIIIIllI).resolve(lllllllllllllllllIlIlIllIIIIIIlI);
            lllllllllllllllllIlIlIlIlllllllI.get(lllllllllllllllllIlIlIllIIIIIllI).getImage();
        }
    }
    
    @Override
    public boolean handles(final Element lllllllllllllllllIlIlIllIIllllII) {
        return lllllllllllllllllIlIlIllIIllllII.getNodeName().equals("defs");
    }
    
    private int stringLength(final String lllllllllllllllllIlIlIlIlllIIlIl) {
        if (lllllllllllllllllIlIlIlIlllIIlIl == null) {
            return 0;
        }
        return lllllllllllllllllIlIlIlIlllIIlIl.length();
    }
}
