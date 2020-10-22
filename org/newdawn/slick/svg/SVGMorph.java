package org.newdawn.slick.svg;

import java.util.*;
import org.newdawn.slick.geom.*;

public class SVGMorph extends Diagram
{
    private /* synthetic */ ArrayList figures;
    
    @Override
    public Figure getFigure(final int lIIIlIIllIIIlI) {
        return this.figures.get(lIIIlIIllIIIlI);
    }
    
    public void addStep(final Diagram lIIIlIlIlIIlII) {
        if (lIIIlIlIlIIlII.getFigureCount() != this.figures.size()) {
            throw new RuntimeException("Mismatched diagrams, missing ids");
        }
        for (int lIIIlIlIlIlIII = 0; lIIIlIlIlIlIII < lIIIlIlIlIIlII.getFigureCount(); ++lIIIlIlIlIlIII) {
            final Figure lIIIlIlIlIlIlI = lIIIlIlIlIIlII.getFigure(lIIIlIlIlIlIII);
            final String lIIIlIlIlIlIIl = lIIIlIlIlIlIlI.getData().getMetaData();
            for (int lIIIlIlIlIlIll = 0; lIIIlIlIlIlIll < this.figures.size(); ++lIIIlIlIlIlIll) {
                final Figure lIIIlIlIlIllII = this.figures.get(lIIIlIlIlIlIll);
                if (lIIIlIlIlIllII.getData().getMetaData().equals(lIIIlIlIlIlIIl)) {
                    final MorphShape lIIIlIlIlIllIl = (MorphShape)lIIIlIlIlIllII.getShape();
                    lIIIlIlIlIllIl.addShape(lIIIlIlIlIlIlI.getShape());
                    break;
                }
            }
        }
    }
    
    public SVGMorph(final Diagram lIIIlIlIlllIll) {
        super(lIIIlIlIlllIll.getWidth(), lIIIlIlIlllIll.getHeight());
        this.figures = new ArrayList();
        for (int lIIIlIlIllllIl = 0; lIIIlIlIllllIl < lIIIlIlIlllIll.getFigureCount(); ++lIIIlIlIllllIl) {
            final Figure lIIIlIlIllllll = lIIIlIlIlllIll.getFigure(lIIIlIlIllllIl);
            final Figure lIIIlIlIlllllI = new Figure(lIIIlIlIllllll.getType(), new MorphShape(lIIIlIlIllllll.getShape()), lIIIlIlIllllll.getData(), lIIIlIlIllllll.getTransform());
            this.figures.add(lIIIlIlIlllllI);
        }
    }
    
    @Override
    public int getFigureCount() {
        return this.figures.size();
    }
    
    public void setMorphTime(final float lIIIlIIllIlllI) {
        for (int lIIIlIIlllIIlI = 0; lIIIlIIlllIIlI < this.figures.size(); ++lIIIlIIlllIIlI) {
            final Figure lIIIlIIlllIlII = this.figures.get(lIIIlIIlllIIlI);
            final MorphShape lIIIlIIlllIIll = (MorphShape)lIIIlIIlllIlII.getShape();
            lIIIlIIlllIIll.setMorphTime(lIIIlIIllIlllI);
        }
    }
    
    public void setExternalDiagram(final Diagram lIIIlIlIIlIIII) {
        for (int lIIIlIlIIlIIlI = 0; lIIIlIlIIlIIlI < this.figures.size(); ++lIIIlIlIIlIIlI) {
            final Figure lIIIlIlIIlIIll = this.figures.get(lIIIlIlIIlIIlI);
            for (int lIIIlIlIIlIlII = 0; lIIIlIlIIlIlII < lIIIlIlIIlIIII.getFigureCount(); ++lIIIlIlIIlIlII) {
                final Figure lIIIlIlIIlIlIl = lIIIlIlIIlIIII.getFigure(lIIIlIlIIlIlII);
                if (lIIIlIlIIlIlIl.getData().getMetaData().equals(lIIIlIlIIlIIll.getData().getMetaData())) {
                    final MorphShape lIIIlIlIIlIllI = (MorphShape)lIIIlIlIIlIIll.getShape();
                    lIIIlIlIIlIllI.setExternalFrame(lIIIlIlIIlIlIl.getShape());
                    break;
                }
            }
        }
    }
    
    public void updateMorphTime(final float lIIIlIIlllllIl) {
        for (int lIIIlIlIIIIIIl = 0; lIIIlIlIIIIIIl < this.figures.size(); ++lIIIlIlIIIIIIl) {
            final Figure lIIIlIlIIIIIll = this.figures.get(lIIIlIlIIIIIIl);
            final MorphShape lIIIlIlIIIIIlI = (MorphShape)lIIIlIlIIIIIll.getShape();
            lIIIlIlIIIIIlI.updateMorphTime(lIIIlIIlllllIl);
        }
    }
}
