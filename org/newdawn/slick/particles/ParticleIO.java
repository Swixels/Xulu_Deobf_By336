package org.newdawn.slick.particles;

import org.newdawn.slick.util.*;
import java.util.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.*;
import java.io.*;

public class ParticleIO
{
    public static ConfigurableEmitter loadEmitter(final String llllllllllllllllllIlllIllIlIllII, final ConfigurableEmitterFactory llllllllllllllllllIlllIllIlIlIll) throws IOException {
        return loadEmitter(ResourceLoader.getResourceAsStream(llllllllllllllllllIlllIllIlIllII), llllllllllllllllllIlllIllIlIlIll);
    }
    
    public static ConfigurableEmitter loadEmitter(final File llllllllllllllllllIlllIllIlIIllI, final ConfigurableEmitterFactory llllllllllllllllllIlllIllIlIIIll) throws IOException {
        return loadEmitter(new FileInputStream(llllllllllllllllllIlllIllIlIIllI), llllllllllllllllllIlllIllIlIIIll);
    }
    
    private static void parseValueElement(final Element llllllllllllllllllIlllIIlllIIIll, final ConfigurableEmitter.Value llllllllllllllllllIlllIIllIllllI) {
        if (llllllllllllllllllIlllIIlllIIIll == null) {
            return;
        }
        final String llllllllllllllllllIlllIIlllIIIIl = llllllllllllllllllIlllIIlllIIIll.getAttribute("type");
        final String llllllllllllllllllIlllIIlllIIIII = llllllllllllllllllIlllIIlllIIIll.getAttribute("value");
        if (llllllllllllllllllIlllIIlllIIIIl == null || llllllllllllllllllIlllIIlllIIIIl.length() == 0) {
            if (llllllllllllllllllIlllIIllIllllI instanceof ConfigurableEmitter.SimpleValue) {
                ((ConfigurableEmitter.SimpleValue)llllllllllllllllllIlllIIllIllllI).setValue(Float.parseFloat(llllllllllllllllllIlllIIlllIIIII));
            }
            else if (llllllllllllllllllIlllIIllIllllI instanceof ConfigurableEmitter.RandomValue) {
                ((ConfigurableEmitter.RandomValue)llllllllllllllllllIlllIIllIllllI).setValue(Float.parseFloat(llllllllllllllllllIlllIIlllIIIII));
            }
            else {
                Log.warn(String.valueOf(new StringBuilder().append("problems reading element, skipping: ").append(llllllllllllllllllIlllIIlllIIIll)));
            }
        }
        else if (llllllllllllllllllIlllIIlllIIIIl.equals("simple")) {
            ((ConfigurableEmitter.SimpleValue)llllllllllllllllllIlllIIllIllllI).setValue(Float.parseFloat(llllllllllllllllllIlllIIlllIIIII));
        }
        else if (llllllllllllllllllIlllIIlllIIIIl.equals("random")) {
            ((ConfigurableEmitter.RandomValue)llllllllllllllllllIlllIIllIllllI).setValue(Float.parseFloat(llllllllllllllllllIlllIIlllIIIII));
        }
        else if (llllllllllllllllllIlllIIlllIIIIl.equals("linear")) {
            final String llllllllllllllllllIlllIIlllIlIII = llllllllllllllllllIlllIIlllIIIll.getAttribute("min");
            final String llllllllllllllllllIlllIIlllIIlll = llllllllllllllllllIlllIIlllIIIll.getAttribute("max");
            final String llllllllllllllllllIlllIIlllIIllI = llllllllllllllllllIlllIIlllIIIll.getAttribute("active");
            final NodeList llllllllllllllllllIlllIIlllIIlIl = llllllllllllllllllIlllIIlllIIIll.getElementsByTagName("point");
            final ArrayList llllllllllllllllllIlllIIlllIIlII = new ArrayList();
            for (int llllllllllllllllllIlllIIlllIlIIl = 0; llllllllllllllllllIlllIIlllIlIIl < llllllllllllllllllIlllIIlllIIlIl.getLength(); ++llllllllllllllllllIlllIIlllIlIIl) {
                final Element llllllllllllllllllIlllIIlllIllII = (Element)llllllllllllllllllIlllIIlllIIlIl.item(llllllllllllllllllIlllIIlllIlIIl);
                final float llllllllllllllllllIlllIIlllIlIll = Float.parseFloat(llllllllllllllllllIlllIIlllIllII.getAttribute("x"));
                final float llllllllllllllllllIlllIIlllIlIlI = Float.parseFloat(llllllllllllllllllIlllIIlllIllII.getAttribute("y"));
                llllllllllllllllllIlllIIlllIIlII.add(new Vector2f(llllllllllllllllllIlllIIlllIlIll, llllllllllllllllllIlllIIlllIlIlI));
            }
            ((ConfigurableEmitter.LinearInterpolator)llllllllllllllllllIlllIIllIllllI).setCurve(llllllllllllllllllIlllIIlllIIlII);
            ((ConfigurableEmitter.LinearInterpolator)llllllllllllllllllIlllIIllIllllI).setMin(Integer.parseInt(llllllllllllllllllIlllIIlllIlIII));
            ((ConfigurableEmitter.LinearInterpolator)llllllllllllllllllIlllIIllIllllI).setMax(Integer.parseInt(llllllllllllllllllIlllIIlllIIlll));
            ((ConfigurableEmitter.LinearInterpolator)llllllllllllllllllIlllIIllIllllI).setActive("true".equals(llllllllllllllllllIlllIIlllIIllI));
        }
        else {
            Log.warn(String.valueOf(new StringBuilder().append("unkown type detected: ").append(llllllllllllllllllIlllIIlllIIIIl)));
        }
    }
    
    private static void parseRangeElement(final Element llllllllllllllllllIlllIIlllllIll, final ConfigurableEmitter.Range llllllllllllllllllIlllIIlllllIlI) {
        if (llllllllllllllllllIlllIIlllllIll == null) {
            return;
        }
        llllllllllllllllllIlllIIlllllIlI.setMin(Float.parseFloat(llllllllllllllllllIlllIIlllllIll.getAttribute("min")));
        llllllllllllllllllIlllIIlllllIlI.setMax(Float.parseFloat(llllllllllllllllllIlllIIlllllIll.getAttribute("max")));
        llllllllllllllllllIlllIIlllllIlI.setEnabled("true".equals(llllllllllllllllllIlllIIlllllIll.getAttribute("enabled")));
    }
    
    private static Element createRangeElement(final Document llllllllllllllllllIlllIlIIlIIIlI, final String llllllllllllllllllIlllIlIIlIIIIl, final ConfigurableEmitter.Range llllllllllllllllllIlllIlIIlIIlII) {
        final Element llllllllllllllllllIlllIlIIlIIIll = llllllllllllllllllIlllIlIIlIIIlI.createElement(llllllllllllllllllIlllIlIIlIIIIl);
        llllllllllllllllllIlllIlIIlIIIll.setAttribute("min", String.valueOf(new StringBuilder().append("").append(llllllllllllllllllIlllIlIIlIIlII.getMin())));
        llllllllllllllllllIlllIlIIlIIIll.setAttribute("max", String.valueOf(new StringBuilder().append("").append(llllllllllllllllllIlllIlIIlIIlII.getMax())));
        llllllllllllllllllIlllIlIIlIIIll.setAttribute("enabled", String.valueOf(new StringBuilder().append("").append(llllllllllllllllllIlllIlIIlIIlII.isEnabled())));
        return llllllllllllllllllIlllIlIIlIIIll;
    }
    
    public static ConfigurableEmitter loadEmitter(final InputStream llllllllllllllllllIlllIllIlIllll) throws IOException {
        return loadEmitter(llllllllllllllllllIlllIllIlIllll, null);
    }
    
    public static ParticleSystem loadConfiguredSystem(final InputStream llllllllllllllllllIllllIIIIllllI, final Color llllllllllllllllllIllllIIIIllIll) throws IOException {
        return loadConfiguredSystem(llllllllllllllllllIllllIIIIllllI, null, null, llllllllllllllllllIllllIIIIllIll);
    }
    
    public static ParticleSystem loadConfiguredSystem(final File llllllllllllllllllIllllIIIlIIIIl) throws IOException {
        return loadConfiguredSystem(new FileInputStream(llllllllllllllllllIllllIIIlIIIIl), null, null, null);
    }
    
    private static Element getFirstNamedElement(final Element llllllllllllllllllIlllIlIllIllll, final String llllllllllllllllllIlllIlIllIlllI) {
        final NodeList llllllllllllllllllIlllIlIllIllIl = llllllllllllllllllIlllIlIllIllll.getElementsByTagName(llllllllllllllllllIlllIlIllIlllI);
        if (llllllllllllllllllIlllIlIllIllIl.getLength() == 0) {
            return null;
        }
        return (Element)llllllllllllllllllIlllIlIllIllIl.item(0);
    }
    
    public static ConfigurableEmitter loadEmitter(final String llllllllllllllllllIlllIllIllIlIl) throws IOException {
        return loadEmitter(ResourceLoader.getResourceAsStream(llllllllllllllllllIlllIllIllIlIl), null);
    }
    
    public static ConfigurableEmitter loadEmitter(final File llllllllllllllllllIlllIllIllIIlI) throws IOException {
        return loadEmitter(new FileInputStream(llllllllllllllllllIlllIllIllIIlI), null);
    }
    
    public static ConfigurableEmitter loadEmitter(final InputStream llllllllllllllllllIlllIllIIllIII, ConfigurableEmitterFactory llllllllllllllllllIlllIllIIlIlIl) throws IOException {
        if (llllllllllllllllllIlllIllIIlIlIl == null) {
            llllllllllllllllllIlllIllIIlIlIl = new ConfigurableEmitterFactory() {
                @Override
                public ConfigurableEmitter createEmitter(final String lIIIlllIIlIlIl) {
                    return new ConfigurableEmitter(lIIIlllIIlIlIl);
                }
            };
        }
        try {
            final DocumentBuilder llllllllllllllllllIlllIllIIlllIl = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            final Document llllllllllllllllllIlllIllIIlllII = llllllllllllllllllIlllIllIIlllIl.parse(llllllllllllllllllIlllIllIIllIII);
            if (!llllllllllllllllllIlllIllIIlllII.getDocumentElement().getNodeName().equals("emitter")) {
                throw new IOException("Not a particle emitter file");
            }
            final ConfigurableEmitter llllllllllllllllllIlllIllIIllIll = ((ConfigurableEmitterFactory)llllllllllllllllllIlllIllIIlIlIl).createEmitter("new");
            elementToEmitter(llllllllllllllllllIlllIllIIlllII.getDocumentElement(), llllllllllllllllllIlllIllIIllIll);
            return llllllllllllllllllIlllIllIIllIll;
        }
        catch (IOException llllllllllllllllllIlllIllIIllIlI) {
            Log.error(llllllllllllllllllIlllIllIIllIlI);
            throw llllllllllllllllllIlllIllIIllIlI;
        }
        catch (Exception llllllllllllllllllIlllIllIIllIIl) {
            Log.error(llllllllllllllllllIlllIllIIllIIl);
            throw new IOException("Unable to load emitter");
        }
    }
    
    public static void saveConfiguredSystem(final OutputStream llllllllllllllllllIlllIlllIIIIII, final ParticleSystem llllllllllllllllllIlllIllIllllll) throws IOException {
        try {
            final DocumentBuilder llllllllllllllllllIlllIlllIIlIlI = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            final Document llllllllllllllllllIlllIlllIIlIIl = llllllllllllllllllIlllIlllIIlIlI.newDocument();
            final Element llllllllllllllllllIlllIlllIIlIII = llllllllllllllllllIlllIlllIIlIIl.createElement("system");
            llllllllllllllllllIlllIlllIIlIII.setAttribute("additive", String.valueOf(new StringBuilder().append("").append(llllllllllllllllllIlllIllIllllll.getBlendingMode() == 1)));
            llllllllllllllllllIlllIlllIIlIII.setAttribute("points", String.valueOf(new StringBuilder().append("").append(llllllllllllllllllIlllIllIllllll.usePoints())));
            llllllllllllllllllIlllIlllIIlIIl.appendChild(llllllllllllllllllIlllIlllIIlIII);
            for (int llllllllllllllllllIlllIlllIIlIll = 0; llllllllllllllllllIlllIlllIIlIll < llllllllllllllllllIlllIllIllllll.getEmitterCount(); ++llllllllllllllllllIlllIlllIIlIll) {
                final ParticleEmitter llllllllllllllllllIlllIlllIIllII = llllllllllllllllllIlllIllIllllll.getEmitter(llllllllllllllllllIlllIlllIIlIll);
                if (!(llllllllllllllllllIlllIlllIIllII instanceof ConfigurableEmitter)) {
                    throw new RuntimeException("Only ConfigurableEmitter instances can be stored");
                }
                final Element llllllllllllllllllIlllIlllIIllIl = emitterToElement(llllllllllllllllllIlllIlllIIlIIl, (ConfigurableEmitter)llllllllllllllllllIlllIlllIIllII);
                llllllllllllllllllIlllIlllIIlIII.appendChild(llllllllllllllllllIlllIlllIIllIl);
            }
            final Result llllllllllllllllllIlllIlllIIIlll = new StreamResult(new OutputStreamWriter(llllllllllllllllllIlllIlllIIIIII, "utf-8"));
            final DOMSource llllllllllllllllllIlllIlllIIIllI = new DOMSource(llllllllllllllllllIlllIlllIIlIIl);
            final TransformerFactory llllllllllllllllllIlllIlllIIIlIl = TransformerFactory.newInstance();
            final Transformer llllllllllllllllllIlllIlllIIIlII = llllllllllllllllllIlllIlllIIIlIl.newTransformer();
            llllllllllllllllllIlllIlllIIIlII.setOutputProperty("indent", "yes");
            llllllllllllllllllIlllIlllIIIlII.transform(llllllllllllllllllIlllIlllIIIllI, llllllllllllllllllIlllIlllIIIlll);
        }
        catch (Exception llllllllllllllllllIlllIlllIIIIll) {
            Log.error(llllllllllllllllllIlllIlllIIIIll);
            throw new IOException("Unable to save configured particle system");
        }
    }
    
    private static Element emitterToElement(final Document llllllllllllllllllIlllIlIIllIlll, final ConfigurableEmitter llllllllllllllllllIlllIlIIllIIIl) {
        final Element llllllllllllllllllIlllIlIIllIlIl = llllllllllllllllllIlllIlIIllIlll.createElement("emitter");
        llllllllllllllllllIlllIlIIllIlIl.setAttribute("name", llllllllllllllllllIlllIlIIllIIIl.name);
        llllllllllllllllllIlllIlIIllIlIl.setAttribute("imageName", (llllllllllllllllllIlllIlIIllIIIl.imageName == null) ? "" : llllllllllllllllllIlllIlIIllIIIl.imageName);
        llllllllllllllllllIlllIlIIllIlIl.setAttribute("useOriented", llllllllllllllllllIlllIlIIllIIIl.useOriented ? "true" : "false");
        llllllllllllllllllIlllIlIIllIlIl.setAttribute("useAdditive", llllllllllllllllllIlllIlIIllIIIl.useAdditive ? "true" : "false");
        if (llllllllllllllllllIlllIlIIllIIIl.usePoints == 1) {
            llllllllllllllllllIlllIlIIllIlIl.setAttribute("renderType", "inherit");
        }
        if (llllllllllllllllllIlllIlIIllIIIl.usePoints == 2) {
            llllllllllllllllllIlllIlIIllIlIl.setAttribute("renderType", "points");
        }
        if (llllllllllllllllllIlllIlIIllIIIl.usePoints == 3) {
            llllllllllllllllllIlllIlIIllIlIl.setAttribute("renderType", "quads");
        }
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createRangeElement(llllllllllllllllllIlllIlIIllIlll, "spawnInterval", llllllllllllllllllIlllIlIIllIIIl.spawnInterval));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createRangeElement(llllllllllllllllllIlllIlIIllIlll, "spawnCount", llllllllllllllllllIlllIlIIllIIIl.spawnCount));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createRangeElement(llllllllllllllllllIlllIlIIllIlll, "initialLife", llllllllllllllllllIlllIlIIllIIIl.initialLife));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createRangeElement(llllllllllllllllllIlllIlIIllIlll, "initialSize", llllllllllllllllllIlllIlIIllIIIl.initialSize));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createRangeElement(llllllllllllllllllIlllIlIIllIlll, "xOffset", llllllllllllllllllIlllIlIIllIIIl.xOffset));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createRangeElement(llllllllllllllllllIlllIlIIllIlll, "yOffset", llllllllllllllllllIlllIlIIllIIIl.yOffset));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createRangeElement(llllllllllllllllllIlllIlIIllIlll, "initialDistance", llllllllllllllllllIlllIlIIllIIIl.initialDistance));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createRangeElement(llllllllllllllllllIlllIlIIllIlll, "speed", llllllllllllllllllIlllIlIIllIIIl.speed));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createRangeElement(llllllllllllllllllIlllIlIIllIlll, "length", llllllllllllllllllIlllIlIIllIIIl.length));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createRangeElement(llllllllllllllllllIlllIlIIllIlll, "emitCount", llllllllllllllllllIlllIlIIllIIIl.emitCount));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createValueElement(llllllllllllllllllIlllIlIIllIlll, "spread", llllllllllllllllllIlllIlIIllIIIl.spread));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createValueElement(llllllllllllllllllIlllIlIIllIlll, "angularOffset", llllllllllllllllllIlllIlIIllIIIl.angularOffset));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createValueElement(llllllllllllllllllIlllIlIIllIlll, "growthFactor", llllllllllllllllllIlllIlIIllIIIl.growthFactor));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createValueElement(llllllllllllllllllIlllIlIIllIlll, "gravityFactor", llllllllllllllllllIlllIlIIllIIIl.gravityFactor));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createValueElement(llllllllllllllllllIlllIlIIllIlll, "windFactor", llllllllllllllllllIlllIlIIllIIIl.windFactor));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createValueElement(llllllllllllllllllIlllIlIIllIlll, "startAlpha", llllllllllllllllllIlllIlIIllIIIl.startAlpha));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createValueElement(llllllllllllllllllIlllIlIIllIlll, "endAlpha", llllllllllllllllllIlllIlIIllIIIl.endAlpha));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createValueElement(llllllllllllllllllIlllIlIIllIlll, "alpha", llllllllllllllllllIlllIlIIllIIIl.alpha));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createValueElement(llllllllllllllllllIlllIlIIllIlll, "size", llllllllllllllllllIlllIlIIllIIIl.size));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createValueElement(llllllllllllllllllIlllIlIIllIlll, "velocity", llllllllllllllllllIlllIlIIllIIIl.velocity));
        llllllllllllllllllIlllIlIIllIlIl.appendChild(createValueElement(llllllllllllllllllIlllIlIIllIlll, "scaleY", llllllllllllllllllIlllIlIIllIIIl.scaleY));
        final Element llllllllllllllllllIlllIlIIllIlII = llllllllllllllllllIlllIlIIllIlll.createElement("color");
        final ArrayList llllllllllllllllllIlllIlIIllIIll = llllllllllllllllllIlllIlIIllIIIl.colors;
        for (int llllllllllllllllllIlllIlIIlllIII = 0; llllllllllllllllllIlllIlIIlllIII < llllllllllllllllllIlllIlIIllIIll.size(); ++llllllllllllllllllIlllIlIIlllIII) {
            final ConfigurableEmitter.ColorRecord llllllllllllllllllIlllIlIIlllIlI = llllllllllllllllllIlllIlIIllIIll.get(llllllllllllllllllIlllIlIIlllIII);
            final Element llllllllllllllllllIlllIlIIlllIIl = llllllllllllllllllIlllIlIIllIlll.createElement("step");
            llllllllllllllllllIlllIlIIlllIIl.setAttribute("offset", String.valueOf(new StringBuilder().append("").append(llllllllllllllllllIlllIlIIlllIlI.pos)));
            llllllllllllllllllIlllIlIIlllIIl.setAttribute("r", String.valueOf(new StringBuilder().append("").append(llllllllllllllllllIlllIlIIlllIlI.col.r)));
            llllllllllllllllllIlllIlIIlllIIl.setAttribute("g", String.valueOf(new StringBuilder().append("").append(llllllllllllllllllIlllIlIIlllIlI.col.g)));
            llllllllllllllllllIlllIlIIlllIIl.setAttribute("b", String.valueOf(new StringBuilder().append("").append(llllllllllllllllllIlllIlIIlllIlI.col.b)));
            llllllllllllllllllIlllIlIIllIlII.appendChild(llllllllllllllllllIlllIlIIlllIIl);
        }
        llllllllllllllllllIlllIlIIllIlIl.appendChild(llllllllllllllllllIlllIlIIllIlII);
        return llllllllllllllllllIlllIlIIllIlIl;
    }
    
    public static ParticleSystem loadConfiguredSystem(final String llllllllllllllllllIllllIIIIlIIll, final ConfigurableEmitterFactory llllllllllllllllllIllllIIIIlIIlI) throws IOException {
        return loadConfiguredSystem(ResourceLoader.getResourceAsStream(llllllllllllllllllIllllIIIIlIIll), llllllllllllllllllIllllIIIIlIIlI, null, null);
    }
    
    public static ParticleSystem loadConfiguredSystem(final InputStream llllllllllllllllllIllllIIIIIlIIl, final ConfigurableEmitterFactory llllllllllllllllllIllllIIIIIIllI) throws IOException {
        return loadConfiguredSystem(llllllllllllllllllIllllIIIIIlIIl, llllllllllllllllllIllllIIIIIIllI, null, null);
    }
    
    public static ParticleSystem loadConfiguredSystem(final String llllllllllllllllllIllllIIIlIlIII, final Color llllllllllllllllllIllllIIIlIIlll) throws IOException {
        return loadConfiguredSystem(ResourceLoader.getResourceAsStream(llllllllllllllllllIllllIIIlIlIII), null, null, llllllllllllllllllIllllIIIlIIlll);
    }
    
    public static ParticleSystem loadConfiguredSystem(final String llllllllllllllllllIllllIIIlIIlIl) throws IOException {
        return loadConfiguredSystem(ResourceLoader.getResourceAsStream(llllllllllllllllllIllllIIIlIIlIl), null, null, null);
    }
    
    public static ParticleSystem loadConfiguredSystem(final InputStream llllllllllllllllllIllllIIIIllIIl) throws IOException {
        return loadConfiguredSystem(llllllllllllllllllIllllIIIIllIIl, null, null, null);
    }
    
    public static void saveConfiguredSystem(final File llllllllllllllllllIlllIlllIllIlI, final ParticleSystem llllllllllllllllllIlllIlllIllIIl) throws IOException {
        saveConfiguredSystem(new FileOutputStream(llllllllllllllllllIlllIlllIllIlI), llllllllllllllllllIlllIlllIllIIl);
    }
    
    private static void elementToEmitter(final Element llllllllllllllllllIlllIlIlIIllll, final ConfigurableEmitter llllllllllllllllllIlllIlIlIlIlIl) {
        llllllllllllllllllIlllIlIlIlIlIl.name = llllllllllllllllllIlllIlIlIIllll.getAttribute("name");
        llllllllllllllllllIlllIlIlIlIlIl.setImageName(llllllllllllllllllIlllIlIlIIllll.getAttribute("imageName"));
        final String llllllllllllllllllIlllIlIlIlIlII = llllllllllllllllllIlllIlIlIIllll.getAttribute("renderType");
        llllllllllllllllllIlllIlIlIlIlIl.usePoints = 1;
        if (llllllllllllllllllIlllIlIlIlIlII.equals("quads")) {
            llllllllllllllllllIlllIlIlIlIlIl.usePoints = 3;
        }
        if (llllllllllllllllllIlllIlIlIlIlII.equals("points")) {
            llllllllllllllllllIlllIlIlIlIlIl.usePoints = 2;
        }
        final String llllllllllllllllllIlllIlIlIlIIll = llllllllllllllllllIlllIlIlIIllll.getAttribute("useOriented");
        if (llllllllllllllllllIlllIlIlIlIIll != null) {
            llllllllllllllllllIlllIlIlIlIlIl.useOriented = "true".equals(llllllllllllllllllIlllIlIlIlIIll);
        }
        final String llllllllllllllllllIlllIlIlIlIIlI = llllllllllllllllllIlllIlIlIIllll.getAttribute("useAdditive");
        if (llllllllllllllllllIlllIlIlIlIIlI != null) {
            llllllllllllllllllIlllIlIlIlIlIl.useAdditive = "true".equals(llllllllllllllllllIlllIlIlIlIIlI);
        }
        parseRangeElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "spawnInterval"), llllllllllllllllllIlllIlIlIlIlIl.spawnInterval);
        parseRangeElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "spawnCount"), llllllllllllllllllIlllIlIlIlIlIl.spawnCount);
        parseRangeElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "initialLife"), llllllllllllllllllIlllIlIlIlIlIl.initialLife);
        parseRangeElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "initialSize"), llllllllllllllllllIlllIlIlIlIlIl.initialSize);
        parseRangeElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "xOffset"), llllllllllllllllllIlllIlIlIlIlIl.xOffset);
        parseRangeElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "yOffset"), llllllllllllllllllIlllIlIlIlIlIl.yOffset);
        parseRangeElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "initialDistance"), llllllllllllllllllIlllIlIlIlIlIl.initialDistance);
        parseRangeElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "speed"), llllllllllllllllllIlllIlIlIlIlIl.speed);
        parseRangeElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "length"), llllllllllllllllllIlllIlIlIlIlIl.length);
        parseRangeElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "emitCount"), llllllllllllllllllIlllIlIlIlIlIl.emitCount);
        parseValueElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "spread"), llllllllllllllllllIlllIlIlIlIlIl.spread);
        parseValueElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "angularOffset"), llllllllllllllllllIlllIlIlIlIlIl.angularOffset);
        parseValueElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "growthFactor"), llllllllllllllllllIlllIlIlIlIlIl.growthFactor);
        parseValueElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "gravityFactor"), llllllllllllllllllIlllIlIlIlIlIl.gravityFactor);
        parseValueElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "windFactor"), llllllllllllllllllIlllIlIlIlIlIl.windFactor);
        parseValueElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "startAlpha"), llllllllllllllllllIlllIlIlIlIlIl.startAlpha);
        parseValueElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "endAlpha"), llllllllllllllllllIlllIlIlIlIlIl.endAlpha);
        parseValueElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "alpha"), llllllllllllllllllIlllIlIlIlIlIl.alpha);
        parseValueElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "size"), llllllllllllllllllIlllIlIlIlIlIl.size);
        parseValueElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "velocity"), llllllllllllllllllIlllIlIlIlIlIl.velocity);
        parseValueElement(getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "scaleY"), llllllllllllllllllIlllIlIlIlIlIl.scaleY);
        final Element llllllllllllllllllIlllIlIlIlIIIl = getFirstNamedElement(llllllllllllllllllIlllIlIlIIllll, "color");
        final NodeList llllllllllllllllllIlllIlIlIlIIII = llllllllllllllllllIlllIlIlIlIIIl.getElementsByTagName("step");
        llllllllllllllllllIlllIlIlIlIlIl.colors.clear();
        for (int llllllllllllllllllIlllIlIlIlIlll = 0; llllllllllllllllllIlllIlIlIlIlll < llllllllllllllllllIlllIlIlIlIIII.getLength(); ++llllllllllllllllllIlllIlIlIlIlll) {
            final Element llllllllllllllllllIlllIlIlIlllII = (Element)llllllllllllllllllIlllIlIlIlIIII.item(llllllllllllllllllIlllIlIlIlIlll);
            final float llllllllllllllllllIlllIlIlIllIll = Float.parseFloat(llllllllllllllllllIlllIlIlIlllII.getAttribute("offset"));
            final float llllllllllllllllllIlllIlIlIllIlI = Float.parseFloat(llllllllllllllllllIlllIlIlIlllII.getAttribute("r"));
            final float llllllllllllllllllIlllIlIlIllIIl = Float.parseFloat(llllllllllllllllllIlllIlIlIlllII.getAttribute("g"));
            final float llllllllllllllllllIlllIlIlIllIII = Float.parseFloat(llllllllllllllllllIlllIlIlIlllII.getAttribute("b"));
            llllllllllllllllllIlllIlIlIlIlIl.addColorPoint(llllllllllllllllllIlllIlIlIllIll, new Color(llllllllllllllllllIlllIlIlIllIlI, llllllllllllllllllIlllIlIlIllIIl, llllllllllllllllllIlllIlIlIllIII, 1.0f));
        }
        llllllllllllllllllIlllIlIlIlIlIl.replay();
    }
    
    public static ParticleSystem loadConfiguredSystem(final InputStream llllllllllllllllllIlllIllllIlIIl, ConfigurableEmitterFactory llllllllllllllllllIlllIllllIlIII, ParticleSystem llllllllllllllllllIlllIllllIIlll, final Color llllllllllllllllllIlllIllllIIllI) throws IOException {
        if (llllllllllllllllllIlllIllllIlIII == null) {
            llllllllllllllllllIlllIllllIlIII = new ConfigurableEmitterFactory() {
                @Override
                public ConfigurableEmitter createEmitter(final String lllllllIlIlIlIl) {
                    return new ConfigurableEmitter(lllllllIlIlIlIl);
                }
            };
        }
        try {
            final DocumentBuilder llllllllllllllllllIlllIlllllIlIl = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            final Document llllllllllllllllllIlllIlllllIlII = llllllllllllllllllIlllIlllllIlIl.parse(llllllllllllllllllIlllIllllIlIIl);
            final Element llllllllllllllllllIlllIlllllIIll = llllllllllllllllllIlllIlllllIlII.getDocumentElement();
            if (!llllllllllllllllllIlllIlllllIIll.getNodeName().equals("system")) {
                throw new IOException("Not a particle system file");
            }
            if (llllllllllllllllllIlllIllllIIlll == null) {
                llllllllllllllllllIlllIllllIIlll = new ParticleSystem("org/newdawn/slick/data/particle.tga", 2000, llllllllllllllllllIlllIllllIIllI);
            }
            final boolean llllllllllllllllllIlllIlllllIIlI = "true".equals(llllllllllllllllllIlllIlllllIIll.getAttribute("additive"));
            if (llllllllllllllllllIlllIlllllIIlI) {
                llllllllllllllllllIlllIllllIIlll.setBlendingMode(1);
            }
            else {
                llllllllllllllllllIlllIllllIIlll.setBlendingMode(2);
            }
            final boolean llllllllllllllllllIlllIlllllIIIl = "true".equals(llllllllllllllllllIlllIlllllIIll.getAttribute("points"));
            llllllllllllllllllIlllIllllIIlll.setUsePoints(llllllllllllllllllIlllIlllllIIIl);
            final NodeList llllllllllllllllllIlllIlllllIIII = llllllllllllllllllIlllIlllllIIll.getElementsByTagName("emitter");
            for (int llllllllllllllllllIlllIlllllIllI = 0; llllllllllllllllllIlllIlllllIllI < llllllllllllllllllIlllIlllllIIII.getLength(); ++llllllllllllllllllIlllIlllllIllI) {
                final Element llllllllllllllllllIlllIllllllIII = (Element)llllllllllllllllllIlllIlllllIIII.item(llllllllllllllllllIlllIlllllIllI);
                final ConfigurableEmitter llllllllllllllllllIlllIlllllIlll = ((ConfigurableEmitterFactory)llllllllllllllllllIlllIllllIlIII).createEmitter("new");
                elementToEmitter(llllllllllllllllllIlllIllllllIII, llllllllllllllllllIlllIlllllIlll);
                llllllllllllllllllIlllIllllIIlll.addEmitter(llllllllllllllllllIlllIlllllIlll);
            }
            llllllllllllllllllIlllIllllIIlll.setRemoveCompletedEmitters(false);
            return llllllllllllllllllIlllIllllIIlll;
        }
        catch (IOException llllllllllllllllllIlllIllllIllll) {
            Log.error(llllllllllllllllllIlllIllllIllll);
            throw llllllllllllllllllIlllIllllIllll;
        }
        catch (Exception llllllllllllllllllIlllIllllIlllI) {
            Log.error(llllllllllllllllllIlllIllllIlllI);
            throw new IOException("Unable to load particle system config");
        }
    }
    
    public static void saveEmitter(final File llllllllllllllllllIlllIllIIIllll, final ConfigurableEmitter llllllllllllllllllIlllIllIIIllII) throws IOException {
        saveEmitter(new FileOutputStream(llllllllllllllllllIlllIllIIIllll), llllllllllllllllllIlllIllIIIllII);
    }
    
    private static Element createValueElement(final Document llllllllllllllllllIlllIlIIIlIIIl, final String llllllllllllllllllIlllIlIIIIIlll, final ConfigurableEmitter.Value llllllllllllllllllIlllIlIIIIIllI) {
        final Element llllllllllllllllllIlllIlIIIIlIll = llllllllllllllllllIlllIlIIIlIIIl.createElement(llllllllllllllllllIlllIlIIIIIlll);
        if (llllllllllllllllllIlllIlIIIIIllI instanceof ConfigurableEmitter.SimpleValue) {
            llllllllllllllllllIlllIlIIIIlIll.setAttribute("type", "simple");
            llllllllllllllllllIlllIlIIIIlIll.setAttribute("value", String.valueOf(new StringBuilder().append("").append(llllllllllllllllllIlllIlIIIIIllI.getValue(0.0f))));
        }
        else if (llllllllllllllllllIlllIlIIIIIllI instanceof ConfigurableEmitter.RandomValue) {
            llllllllllllllllllIlllIlIIIIlIll.setAttribute("type", "random");
            llllllllllllllllllIlllIlIIIIlIll.setAttribute("value", String.valueOf(new StringBuilder().append("").append(((ConfigurableEmitter.RandomValue)llllllllllllllllllIlllIlIIIIIllI).getValue())));
        }
        else if (llllllllllllllllllIlllIlIIIIIllI instanceof ConfigurableEmitter.LinearInterpolator) {
            llllllllllllllllllIlllIlIIIIlIll.setAttribute("type", "linear");
            llllllllllllllllllIlllIlIIIIlIll.setAttribute("min", String.valueOf(new StringBuilder().append("").append(((ConfigurableEmitter.LinearInterpolator)llllllllllllllllllIlllIlIIIIIllI).getMin())));
            llllllllllllllllllIlllIlIIIIlIll.setAttribute("max", String.valueOf(new StringBuilder().append("").append(((ConfigurableEmitter.LinearInterpolator)llllllllllllllllllIlllIlIIIIIllI).getMax())));
            llllllllllllllllllIlllIlIIIIlIll.setAttribute("active", String.valueOf(new StringBuilder().append("").append(((ConfigurableEmitter.LinearInterpolator)llllllllllllllllllIlllIlIIIIIllI).isActive())));
            final ArrayList llllllllllllllllllIlllIlIIIlIIlI = ((ConfigurableEmitter.LinearInterpolator)llllllllllllllllllIlllIlIIIIIllI).getCurve();
            for (int llllllllllllllllllIlllIlIIIlIIll = 0; llllllllllllllllllIlllIlIIIlIIll < llllllllllllllllllIlllIlIIIlIIlI.size(); ++llllllllllllllllllIlllIlIIIlIIll) {
                final Vector2f llllllllllllllllllIlllIlIIIlIllI = llllllllllllllllllIlllIlIIIlIIlI.get(llllllllllllllllllIlllIlIIIlIIll);
                final Element llllllllllllllllllIlllIlIIIlIlII = llllllllllllllllllIlllIlIIIlIIIl.createElement("point");
                llllllllllllllllllIlllIlIIIlIlII.setAttribute("x", String.valueOf(new StringBuilder().append("").append(llllllllllllllllllIlllIlIIIlIllI.x)));
                llllllllllllllllllIlllIlIIIlIlII.setAttribute("y", String.valueOf(new StringBuilder().append("").append(llllllllllllllllllIlllIlIIIlIllI.y)));
                llllllllllllllllllIlllIlIIIIlIll.appendChild(llllllllllllllllllIlllIlIIIlIlII);
            }
        }
        else {
            Log.warn(String.valueOf(new StringBuilder().append("unkown value type ignored: ").append(llllllllllllllllllIlllIlIIIIIllI.getClass())));
        }
        return llllllllllllllllllIlllIlIIIIlIll;
    }
    
    public static ParticleSystem loadConfiguredSystem(final File llllllllllllllllllIllllIIIIIllIl, final ConfigurableEmitterFactory llllllllllllllllllIllllIIIIIllII) throws IOException {
        return loadConfiguredSystem(new FileInputStream(llllllllllllllllllIllllIIIIIllIl), llllllllllllllllllIllllIIIIIllII, null, null);
    }
    
    public static void saveEmitter(final OutputStream llllllllllllllllllIlllIlIlllllII, final ConfigurableEmitter llllllllllllllllllIlllIlIllllIll) throws IOException {
        try {
            final DocumentBuilder llllllllllllllllllIlllIllIIIIIll = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            final Document llllllllllllllllllIlllIllIIIIIlI = llllllllllllllllllIlllIllIIIIIll.newDocument();
            llllllllllllllllllIlllIllIIIIIlI.appendChild(emitterToElement(llllllllllllllllllIlllIllIIIIIlI, llllllllllllllllllIlllIlIllllIll));
            final Result llllllllllllllllllIlllIllIIIIIIl = new StreamResult(new OutputStreamWriter(llllllllllllllllllIlllIlIlllllII, "utf-8"));
            final DOMSource llllllllllllllllllIlllIllIIIIIII = new DOMSource(llllllllllllllllllIlllIllIIIIIlI);
            final TransformerFactory llllllllllllllllllIlllIlIlllllll = TransformerFactory.newInstance();
            final Transformer llllllllllllllllllIlllIlIllllllI = llllllllllllllllllIlllIlIlllllll.newTransformer();
            llllllllllllllllllIlllIlIllllllI.setOutputProperty("indent", "yes");
            llllllllllllllllllIlllIlIllllllI.transform(llllllllllllllllllIlllIllIIIIIII, llllllllllllllllllIlllIllIIIIIIl);
        }
        catch (Exception llllllllllllllllllIlllIlIlllllIl) {
            Log.error(llllllllllllllllllIlllIlIlllllIl);
            throw new IOException("Failed to save emitter");
        }
    }
}
