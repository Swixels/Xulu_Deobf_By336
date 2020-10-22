package org.newdawn.slick.svg;

import java.util.*;
import org.newdawn.slick.*;

public class NonGeometricData
{
    private /* synthetic */ String metaData;
    private /* synthetic */ Properties props;
    
    public boolean isFilled() {
        return this.isColor("fill");
    }
    
    public boolean isColor(final String llllllllllllllllllIllllIlllIlIIl) {
        return this.getAttribute(llllllllllllllllllIllllIlllIlIIl).startsWith("#");
    }
    
    public Color getAsColor(final String llllllllllllllllllIllllIllIllIll) {
        if (!this.isColor(llllllllllllllllllIllllIllIllIll)) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("Attribute ").append(llllllllllllllllllIllllIllIllIll).append(" is not specified as a color:").append(this.getAttribute(llllllllllllllllllIllllIllIllIll))));
        }
        final int llllllllllllllllllIllllIllIllIlI = Integer.parseInt(this.getAttribute(llllllllllllllllllIllllIllIllIll).substring(1), 16);
        return new Color(llllllllllllllllllIllllIllIllIlI);
    }
    
    public float getAsFloat(final String llllllllllllllllllIllllIllIIIlII) {
        final String llllllllllllllllllIllllIllIIIllI = this.getAttribute(llllllllllllllllllIllllIllIIIlII);
        if (llllllllllllllllllIllllIllIIIllI == null) {
            return 0.0f;
        }
        try {
            return Float.parseFloat(llllllllllllllllllIllllIllIIIllI);
        }
        catch (NumberFormatException llllllllllllllllllIllllIllIIlIIl) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("Attribute ").append(llllllllllllllllllIllllIllIIIlII).append(" is not specified as a float:").append(this.getAttribute(llllllllllllllllllIllllIllIIIlII))));
        }
    }
    
    public String getMetaData() {
        return this.metaData;
    }
    
    public boolean isStroked() {
        return this.isColor("stroke") && this.getAsFloat("stroke-width") > 0.0f;
    }
    
    public String getAsReference(final String llllllllllllllllllIllllIllIIllll) {
        String llllllllllllllllllIllllIllIlIIIl = this.getAttribute(llllllllllllllllllIllllIllIIllll);
        if (llllllllllllllllllIllllIllIlIIIl.length() < 7) {
            return "";
        }
        llllllllllllllllllIllllIllIlIIIl = llllllllllllllllllIllllIllIlIIIl.substring(5, llllllllllllllllllIllllIllIlIIIl.length() - 1);
        return llllllllllllllllllIllllIllIlIIIl;
    }
    
    public void addAttribute(final String llllllllllllllllllIllllIllllIIII, String llllllllllllllllllIllllIlllIllll) {
        if (llllllllllllllllllIllllIlllIllll == null) {
            llllllllllllllllllIllllIlllIllll = "";
        }
        if (llllllllllllllllllIllllIllllIIII.equals("fill")) {
            llllllllllllllllllIllllIlllIllll = this.morphColor(llllllllllllllllllIllllIlllIllll);
        }
        if (llllllllllllllllllIllllIllllIIII.equals("stroke-opacity") && llllllllllllllllllIllllIlllIllll.equals("0")) {
            this.props.setProperty("stroke", "none");
        }
        if (llllllllllllllllllIllllIllllIIII.equals("stroke-width")) {
            if (llllllllllllllllllIllllIlllIllll.equals("")) {
                llllllllllllllllllIllllIlllIllll = "1";
            }
            if (llllllllllllllllllIllllIlllIllll.endsWith("px")) {
                llllllllllllllllllIllllIlllIllll = llllllllllllllllllIllllIlllIllll.substring(0, llllllllllllllllllIllllIlllIllll.length() - 2);
            }
        }
        if (llllllllllllllllllIllllIllllIIII.equals("stroke")) {
            if ("none".equals(this.props.getProperty("stroke"))) {
                return;
            }
            if ("".equals(this.props.getProperty("stroke"))) {
                return;
            }
            llllllllllllllllllIllllIlllIllll = this.morphColor(llllllllllllllllllIllllIlllIllll);
        }
        this.props.setProperty(llllllllllllllllllIllllIllllIIII, llllllllllllllllllIllllIlllIllll);
    }
    
    public NonGeometricData(final String llllllllllllllllllIllllIllllllII) {
        this.metaData = "";
        this.props = new Properties();
        this.metaData = llllllllllllllllllIllllIllllllII;
        this.addAttribute("stroke-width", "1");
    }
    
    static {
        NONE = "none";
        FILL = "fill";
        ID = "id";
        STROKE_OPACITY = "stroke-opacity";
        STROKE_MITERLIMIT = "stroke-miterlimit";
        STROKE_DASHARRAY = "stroke-dasharray";
        STROKE_DASHOFFSET = "stroke-dashoffset";
        STROKE_WIDTH = "stroke-width";
        OPACITY = "opacity";
        STROKE = "stroke";
    }
    
    private String morphColor(final String llllllllllllllllllIllllIlllllIII) {
        if (llllllllllllllllllIllllIlllllIII.equals("")) {
            return "#000000";
        }
        if (llllllllllllllllllIllllIlllllIII.equals("white")) {
            return "#ffffff";
        }
        if (llllllllllllllllllIllllIlllllIII.equals("black")) {
            return "#000000";
        }
        return llllllllllllllllllIllllIlllllIII;
    }
    
    public String getAttribute(final String llllllllllllllllllIllllIlllIIIlI) {
        return this.props.getProperty(llllllllllllllllllIllllIlllIIIlI);
    }
}
