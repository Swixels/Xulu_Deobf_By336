package org.newdawn.slick.svg;

import java.util.*;

public class Diagram
{
    private /* synthetic */ HashMap patterns;
    private /* synthetic */ HashMap figureMap;
    private /* synthetic */ float width;
    private /* synthetic */ HashMap gradients;
    private /* synthetic */ float height;
    private /* synthetic */ ArrayList figures;
    
    public void removeFigure(final Figure lIlIIlIIllIIIII) {
        this.figures.remove(lIlIIlIIllIIIII);
        this.figureMap.remove(lIlIIlIIllIIIII.getData().getAttribute("id"));
    }
    
    public Diagram(final float lIlIIlIlIlIllIl, final float lIlIIlIlIlIlIIl) {
        this.figures = new ArrayList();
        this.patterns = new HashMap();
        this.gradients = new HashMap();
        this.figureMap = new HashMap();
        this.width = lIlIIlIlIlIllIl;
        this.height = lIlIIlIlIlIlIIl;
    }
    
    public String[] getPatternDefNames() {
        return (String[])this.patterns.keySet().toArray(new String[0]);
    }
    
    public String getPatternDef(final String lIlIIlIlIIIllIl) {
        return this.patterns.get(lIlIIlIlIIIllIl);
    }
    
    public Figure getFigure(final int lIlIIlIIllIIllI) {
        return this.figures.get(lIlIIlIIllIIllI);
    }
    
    public void addFigure(final Figure lIlIIlIIlllIIII) {
        this.figures.add(lIlIIlIIlllIIII);
        this.figureMap.put(lIlIIlIIlllIIII.getData().getAttribute("id"), lIlIIlIIlllIIII);
        final String lIlIIlIIlllIIll = lIlIIlIIlllIIII.getData().getAsReference("fill");
        final Gradient lIlIIlIIlllIIlI = this.getGradient(lIlIIlIIlllIIll);
        if (lIlIIlIIlllIIlI != null && lIlIIlIIlllIIlI.isRadial()) {
            for (int lIlIIlIIlllIllI = 0; lIlIIlIIlllIllI < InkscapeLoader.RADIAL_TRIANGULATION_LEVEL; ++lIlIIlIIlllIllI) {
                lIlIIlIIlllIIII.getShape().increaseTriangulation();
            }
        }
    }
    
    public float getWidth() {
        return this.width;
    }
    
    public Figure getFigureByID(final String lIlIIlIIlllllII) {
        return this.figureMap.get(lIlIIlIIlllllII);
    }
    
    public Gradient getGradient(final String lIlIIlIlIIIIlIl) {
        return this.gradients.get(lIlIIlIlIIIIlIl);
    }
    
    public void addPatternDef(final String lIlIIlIlIIllllI, final String lIlIIlIlIIlllIl) {
        this.patterns.put(lIlIIlIlIIllllI, lIlIIlIlIIlllIl);
    }
    
    public int getFigureCount() {
        return this.figures.size();
    }
    
    public float getHeight() {
        return this.height;
    }
    
    public void addGradient(final String lIlIIlIlIIlIlIl, final Gradient lIlIIlIlIIlIlII) {
        this.gradients.put(lIlIIlIlIIlIlIl, lIlIIlIlIIlIlII);
    }
}
