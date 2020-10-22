package org.newdawn.slick.tests;

import java.util.*;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class GeomUtilTileTest extends BasicGame implements GeomUtilListener
{
    private /* synthetic */ ArrayList intersections;
    private /* synthetic */ Shape[][] quadSpaceShapes;
    private /* synthetic */ GeomUtil util;
    private /* synthetic */ ArrayList used;
    private /* synthetic */ ArrayList original;
    private /* synthetic */ ArrayList[][] quadSpace;
    private /* synthetic */ ArrayList combined;
    
    private ArrayList combine(final ArrayList lllllllllllllllllIlIlIIlIIlIIIII) {
        ArrayList lllllllllllllllllIlIlIIlIIlIIlIl = lllllllllllllllllIlIlIIlIIlIIIII;
        ArrayList lllllllllllllllllIlIlIIlIIlIIlII = lllllllllllllllllIlIlIIlIIlIIIII;
        for (boolean lllllllllllllllllIlIlIIlIIlIIIll = true; lllllllllllllllllIlIlIIlIIlIIlII.size() != lllllllllllllllllIlIlIIlIIlIIlIl.size() || lllllllllllllllllIlIlIIlIIlIIIll; lllllllllllllllllIlIlIIlIIlIIIll = false, lllllllllllllllllIlIlIIlIIlIIlIl = lllllllllllllllllIlIlIIlIIlIIlII, lllllllllllllllllIlIlIIlIIlIIlII = this.combineImpl(lllllllllllllllllIlIlIIlIIlIIlII)) {}
        final ArrayList lllllllllllllllllIlIlIIlIIlIIIlI = new ArrayList();
        for (int lllllllllllllllllIlIlIIlIIlIlIII = 0; lllllllllllllllllIlIlIIlIIlIlIII < lllllllllllllllllIlIlIIlIIlIIlII.size(); ++lllllllllllllllllIlIlIIlIIlIlIII) {
            lllllllllllllllllIlIlIIlIIlIIIlI.add(lllllllllllllllllIlIlIIlIIlIIlII.get(lllllllllllllllllIlIlIIlIIlIlIII).prune());
        }
        return lllllllllllllllllIlIlIIlIIlIIIlI;
    }
    
    @Override
    public void pointIntersected(final float lllllllllllllllllIlIlIIIllIIlIII, final float lllllllllllllllllIlIlIIIllIIIlll) {
        this.intersections.add(new Vector2f(lllllllllllllllllIlIlIIIllIIlIII, lllllllllllllllllIlIlIIIllIIIlll));
    }
    
    private void addToQuadSpace(final Shape lllllllllllllllllIlIlIIlIllIlllI) {
        for (int lllllllllllllllllIlIlIIlIlllIIII = this.quadSpace.length, lllllllllllllllllIlIlIIlIlllIIll = 0; lllllllllllllllllIlIlIIlIlllIIll < lllllllllllllllllIlIlIIlIlllIIII; ++lllllllllllllllllIlIlIIlIlllIIll) {
            for (int lllllllllllllllllIlIlIIlIlllIlII = 0; lllllllllllllllllIlIlIIlIlllIlII < lllllllllllllllllIlIlIIlIlllIIII; ++lllllllllllllllllIlIlIIlIlllIlII) {
                if (this.collides(lllllllllllllllllIlIlIIlIllIlllI, this.quadSpaceShapes[lllllllllllllllllIlIlIIlIlllIIll][lllllllllllllllllIlIlIIlIlllIlII])) {
                    this.quadSpace[lllllllllllllllllIlIlIIlIlllIIll][lllllllllllllllllIlIlIIlIlllIlII].add(lllllllllllllllllIlIlIIlIllIlllI);
                }
            }
        }
    }
    
    @Override
    public void pointUsed(final float lllllllllllllllllIlIlIIIllIIIIlI, final float lllllllllllllllllIlIlIIIllIIIIIl) {
        this.used.add(new Vector2f(lllllllllllllllllIlIlIIIllIIIIlI, lllllllllllllllllIlIlIIIllIIIIIl));
    }
    
    public static void main(final String[] lllllllllllllllllIlIlIIIllIlIlII) {
        try {
            final AppGameContainer lllllllllllllllllIlIlIIIllIlIllI = new AppGameContainer(new GeomUtilTileTest());
            lllllllllllllllllIlIlIIIllIlIllI.setDisplayMode(800, 600, false);
            lllllllllllllllllIlIlIIIllIlIllI.start();
        }
        catch (SlickException lllllllllllllllllIlIlIIIllIlIlIl) {
            lllllllllllllllllIlIlIIIllIlIlIl.printStackTrace();
        }
    }
    
    private ArrayList combineQuadSpace() {
        boolean lllllllllllllllllIlIlIIlIIlllIII = true;
        while (lllllllllllllllllIlIlIIlIIlllIII) {
            lllllllllllllllllIlIlIIlIIlllIII = false;
            for (int lllllllllllllllllIlIlIIlIIllllII = 0; lllllllllllllllllIlIlIIlIIllllII < this.quadSpace.length; ++lllllllllllllllllIlIlIIlIIllllII) {
                for (int lllllllllllllllllIlIlIIlIIllllIl = 0; lllllllllllllllllIlIlIIlIIllllIl < this.quadSpace.length; ++lllllllllllllllllIlIlIIlIIllllIl) {
                    final ArrayList lllllllllllllllllIlIlIIlIlIIIIII = this.quadSpace[lllllllllllllllllIlIlIIlIIllllII][lllllllllllllllllIlIlIIlIIllllIl];
                    final int lllllllllllllllllIlIlIIlIIllllll = lllllllllllllllllIlIlIIlIlIIIIII.size();
                    this.combine(lllllllllllllllllIlIlIIlIlIIIIII);
                    final int lllllllllllllllllIlIlIIlIIlllllI = lllllllllllllllllIlIlIIlIlIIIIII.size();
                    lllllllllllllllllIlIlIIlIIlllIII |= (lllllllllllllllllIlIlIIlIIllllll != lllllllllllllllllIlIlIIlIIlllllI);
                }
            }
        }
        final HashSet lllllllllllllllllIlIlIIlIIllIlll = new HashSet();
        for (int lllllllllllllllllIlIlIIlIIlllIlI = 0; lllllllllllllllllIlIlIIlIIlllIlI < this.quadSpace.length; ++lllllllllllllllllIlIlIIlIIlllIlI) {
            for (int lllllllllllllllllIlIlIIlIIlllIll = 0; lllllllllllllllllIlIlIIlIIlllIll < this.quadSpace.length; ++lllllllllllllllllIlIlIIlIIlllIll) {
                lllllllllllllllllIlIlIIlIIllIlll.addAll(this.quadSpace[lllllllllllllllllIlIlIIlIIlllIlI][lllllllllllllllllIlIlIIlIIlllIll]);
            }
        }
        return new ArrayList(lllllllllllllllllIlIlIIlIIllIlll);
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIlIlIIIlllIlllI, final int lllllllllllllllllIlIlIIIlllIllIl) throws SlickException {
    }
    
    private ArrayList combineImpl(final ArrayList lllllllllllllllllIlIlIIlIIIIllII) {
        ArrayList lllllllllllllllllIlIlIIlIIIIlIll = new ArrayList(lllllllllllllllllIlIlIIlIIIIllII);
        if (this.quadSpace != null) {
            lllllllllllllllllIlIlIIlIIIIlIll = lllllllllllllllllIlIlIIlIIIIllII;
        }
        for (int lllllllllllllllllIlIlIIlIIIIlllI = 0; lllllllllllllllllIlIlIIlIIIIlllI < lllllllllllllllllIlIlIIlIIIIllII.size(); ++lllllllllllllllllIlIlIIlIIIIlllI) {
            final Shape lllllllllllllllllIlIlIIlIIIIllll = (Shape)lllllllllllllllllIlIlIIlIIIIllII.get(lllllllllllllllllIlIlIIlIIIIlllI);
            for (int lllllllllllllllllIlIlIIlIIIlIIII = lllllllllllllllllIlIlIIlIIIIlllI + 1; lllllllllllllllllIlIlIIlIIIlIIII < lllllllllllllllllIlIlIIlIIIIllII.size(); ++lllllllllllllllllIlIlIIlIIIlIIII) {
                final Shape lllllllllllllllllIlIlIIlIIIlIIlI = (Shape)lllllllllllllllllIlIlIIlIIIIllII.get(lllllllllllllllllIlIlIIlIIIlIIII);
                if (lllllllllllllllllIlIlIIlIIIIllll.intersects(lllllllllllllllllIlIlIIlIIIlIIlI)) {
                    final Shape[] lllllllllllllllllIlIlIIlIIIlIIIl = this.util.union(lllllllllllllllllIlIlIIlIIIIllll, lllllllllllllllllIlIlIIlIIIlIIlI);
                    if (lllllllllllllllllIlIlIIlIIIlIIIl.length == 1) {
                        if (this.quadSpace != null) {
                            this.removeFromQuadSpace(lllllllllllllllllIlIlIIlIIIIllll);
                            this.removeFromQuadSpace(lllllllllllllllllIlIlIIlIIIlIIlI);
                            this.addToQuadSpace(lllllllllllllllllIlIlIIlIIIlIIIl[0]);
                        }
                        else {
                            lllllllllllllllllIlIlIIlIIIIlIll.remove(lllllllllllllllllIlIlIIlIIIIllll);
                            lllllllllllllllllIlIlIIlIIIIlIll.remove(lllllllllllllllllIlIlIIlIIIlIIlI);
                            lllllllllllllllllIlIlIIlIIIIlIll.add(lllllllllllllllllIlIlIIlIIIlIIIl[0]);
                        }
                        return lllllllllllllllllIlIlIIlIIIIlIll;
                    }
                }
            }
        }
        return lllllllllllllllllIlIlIIlIIIIlIll;
    }
    
    public GeomUtilTileTest() {
        super("GeomUtilTileTest");
        this.util = new GeomUtil();
        this.original = new ArrayList();
        this.combined = new ArrayList();
        this.intersections = new ArrayList();
        this.used = new ArrayList();
    }
    
    @Override
    public void pointExcluded(final float lllllllllllllllllIlIlIIIllIlIIIl, final float lllllllllllllllllIlIlIIIllIlIIII) {
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIlIlIIIllIlllll, final Graphics lllllllllllllllllIlIlIIIllIlllII) throws SlickException {
        lllllllllllllllllIlIlIIIllIlllII.setColor(Color.green);
        for (int lllllllllllllllllIlIlIIIlllIIlIl = 0; lllllllllllllllllIlIlIIIlllIIlIl < this.original.size(); ++lllllllllllllllllIlIlIIIlllIIlIl) {
            final Shape lllllllllllllllllIlIlIIIlllIIllI = this.original.get(lllllllllllllllllIlIlIIIlllIIlIl);
            lllllllllllllllllIlIlIIIllIlllII.draw(lllllllllllllllllIlIlIIIlllIIllI);
        }
        lllllllllllllllllIlIlIIIllIlllII.setColor(Color.white);
        if (this.quadSpaceShapes != null) {
            lllllllllllllllllIlIlIIIllIlllII.draw(this.quadSpaceShapes[0][0]);
        }
        lllllllllllllllllIlIlIIIllIlllII.translate(0.0f, 320.0f);
        for (int lllllllllllllllllIlIlIIIlllIIIIl = 0; lllllllllllllllllIlIlIIIlllIIIIl < this.combined.size(); ++lllllllllllllllllIlIlIIIlllIIIIl) {
            lllllllllllllllllIlIlIIIllIlllII.setColor(Color.white);
            final Shape lllllllllllllllllIlIlIIIlllIIIlI = this.combined.get(lllllllllllllllllIlIlIIIlllIIIIl);
            lllllllllllllllllIlIlIIIllIlllII.draw(lllllllllllllllllIlIlIIIlllIIIlI);
            for (int lllllllllllllllllIlIlIIIlllIIIll = 0; lllllllllllllllllIlIlIIIlllIIIll < lllllllllllllllllIlIlIIIlllIIIlI.getPointCount(); ++lllllllllllllllllIlIlIIIlllIIIll) {
                lllllllllllllllllIlIlIIIllIlllII.setColor(Color.yellow);
                final float[] lllllllllllllllllIlIlIIIlllIIlII = lllllllllllllllllIlIlIIIlllIIIlI.getPoint(lllllllllllllllllIlIlIIIlllIIIll);
                lllllllllllllllllIlIlIIIllIlllII.fillOval(lllllllllllllllllIlIlIIIlllIIlII[0] - 1.0f, lllllllllllllllllIlIlIIIlllIIlII[1] - 1.0f, 3.0f, 3.0f);
            }
        }
    }
    
    private void generateSpace(final ArrayList lllllllllllllllllIlIlIIllIIlIlIl, final float lllllllllllllllllIlIlIIllIIlIlII, final float lllllllllllllllllIlIlIIllIIlIIll, final float lllllllllllllllllIlIlIIllIIlIIlI, final float lllllllllllllllllIlIlIIllIIllIlI, final int lllllllllllllllllIlIlIIllIIllIIl) {
        this.quadSpace = new ArrayList[lllllllllllllllllIlIlIIllIIllIIl][lllllllllllllllllIlIlIIllIIllIIl];
        this.quadSpaceShapes = new Shape[lllllllllllllllllIlIlIIllIIllIIl][lllllllllllllllllIlIlIIllIIllIIl];
        final float lllllllllllllllllIlIlIIllIIllIII = (lllllllllllllllllIlIlIIllIIlIIlI - lllllllllllllllllIlIlIIllIIlIlII) / lllllllllllllllllIlIlIIllIIllIIl;
        final float lllllllllllllllllIlIlIIllIIlIlll = (lllllllllllllllllIlIlIIllIIllIlI - lllllllllllllllllIlIlIIllIIlIIll) / lllllllllllllllllIlIlIIllIIllIIl;
        for (int lllllllllllllllllIlIlIIllIlIIIII = 0; lllllllllllllllllIlIlIIllIlIIIII < lllllllllllllllllIlIlIIllIIllIIl; ++lllllllllllllllllIlIlIIllIlIIIII) {
            for (int lllllllllllllllllIlIlIIllIlIIIIl = 0; lllllllllllllllllIlIlIIllIlIIIIl < lllllllllllllllllIlIlIIllIIllIIl; ++lllllllllllllllllIlIlIIllIlIIIIl) {
                this.quadSpace[lllllllllllllllllIlIlIIllIlIIIII][lllllllllllllllllIlIlIIllIlIIIIl] = new ArrayList();
                final Polygon lllllllllllllllllIlIlIIllIlIIIlI = new Polygon();
                lllllllllllllllllIlIlIIllIlIIIlI.addPoint(lllllllllllllllllIlIlIIllIIlIlII + lllllllllllllllllIlIlIIllIIllIII * lllllllllllllllllIlIlIIllIlIIIII, lllllllllllllllllIlIlIIllIIlIIll + lllllllllllllllllIlIlIIllIIlIlll * lllllllllllllllllIlIlIIllIlIIIIl);
                lllllllllllllllllIlIlIIllIlIIIlI.addPoint(lllllllllllllllllIlIlIIllIIlIlII + lllllllllllllllllIlIlIIllIIllIII * lllllllllllllllllIlIlIIllIlIIIII + lllllllllllllllllIlIlIIllIIllIII, lllllllllllllllllIlIlIIllIIlIIll + lllllllllllllllllIlIlIIllIIlIlll * lllllllllllllllllIlIlIIllIlIIIIl);
                lllllllllllllllllIlIlIIllIlIIIlI.addPoint(lllllllllllllllllIlIlIIllIIlIlII + lllllllllllllllllIlIlIIllIIllIII * lllllllllllllllllIlIlIIllIlIIIII + lllllllllllllllllIlIlIIllIIllIII, lllllllllllllllllIlIlIIllIIlIIll + lllllllllllllllllIlIlIIllIIlIlll * lllllllllllllllllIlIlIIllIlIIIIl + lllllllllllllllllIlIlIIllIIlIlll);
                lllllllllllllllllIlIlIIllIlIIIlI.addPoint(lllllllllllllllllIlIlIIllIIlIlII + lllllllllllllllllIlIlIIllIIllIII * lllllllllllllllllIlIlIIllIlIIIII, lllllllllllllllllIlIlIIllIIlIIll + lllllllllllllllllIlIlIIllIIlIlll * lllllllllllllllllIlIlIIllIlIIIIl + lllllllllllllllllIlIlIIllIIlIlll);
                for (int lllllllllllllllllIlIlIIllIlIIIll = 0; lllllllllllllllllIlIlIIllIlIIIll < lllllllllllllllllIlIlIIllIIlIlIl.size(); ++lllllllllllllllllIlIlIIllIlIIIll) {
                    final Shape lllllllllllllllllIlIlIIllIlIIlII = lllllllllllllllllIlIlIIllIIlIlIl.get(lllllllllllllllllIlIlIIllIlIIIll);
                    if (this.collides(lllllllllllllllllIlIlIIllIlIIlII, lllllllllllllllllIlIlIIllIlIIIlI)) {
                        this.quadSpace[lllllllllllllllllIlIlIIllIlIIIII][lllllllllllllllllIlIlIIllIlIIIIl].add(lllllllllllllllllIlIlIIllIlIIlII);
                    }
                }
                this.quadSpaceShapes[lllllllllllllllllIlIlIIllIlIIIII][lllllllllllllllllIlIlIIllIlIIIIl] = lllllllllllllllllIlIlIIllIlIIIlI;
            }
        }
    }
    
    public boolean collides(final Shape lllllllllllllllllIlIlIIIllllIlll, final Shape lllllllllllllllllIlIlIIIlllllIII) {
        if (lllllllllllllllllIlIlIIIllllIlll.intersects(lllllllllllllllllIlIlIIIlllllIII)) {
            return true;
        }
        for (int lllllllllllllllllIlIlIIIllllllIl = 0; lllllllllllllllllIlIlIIIllllllIl < lllllllllllllllllIlIlIIIllllIlll.getPointCount(); ++lllllllllllllllllIlIlIIIllllllIl) {
            final float[] lllllllllllllllllIlIlIIIlllllllI = lllllllllllllllllIlIlIIIllllIlll.getPoint(lllllllllllllllllIlIlIIIllllllIl);
            if (lllllllllllllllllIlIlIIIlllllIII.contains(lllllllllllllllllIlIlIIIlllllllI[0], lllllllllllllllllIlIlIIIlllllllI[1])) {
                return true;
            }
        }
        for (int lllllllllllllllllIlIlIIIlllllIll = 0; lllllllllllllllllIlIlIIIlllllIll < lllllllllllllllllIlIlIIIlllllIII.getPointCount(); ++lllllllllllllllllIlIlIIIlllllIll) {
            final float[] lllllllllllllllllIlIlIIIllllllII = lllllllllllllllllIlIlIIIlllllIII.getPoint(lllllllllllllllllIlIlIIIlllllIll);
            if (lllllllllllllllllIlIlIIIllllIlll.contains(lllllllllllllllllIlIlIIIllllllII[0], lllllllllllllllllIlIlIIIllllllII[1])) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIlIlIIIllllIIIl) throws SlickException {
        this.util.setListener(this);
        this.init();
    }
    
    public void init() {
        final int lllllllllllllllllIlIlIIlIlIlIllI = 10;
        final int[][] lllllllllllllllllIlIlIIlIlIlIlIl = { { 0, 0, 0, 0, 0, 0, 0, 3, 0, 0 }, { 0, 1, 1, 1, 0, 0, 1, 1, 1, 0 }, { 0, 1, 1, 0, 0, 0, 5, 1, 6, 0 }, { 0, 1, 2, 0, 0, 0, 4, 1, 1, 0 }, { 0, 1, 1, 0, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 3, 0, 1, 1, 0, 0 }, { 0, 0, 0, 1, 1, 0, 0, 0, 1, 0 }, { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
        for (int lllllllllllllllllIlIlIIlIlIllIII = 0; lllllllllllllllllIlIlIIlIlIllIII < lllllllllllllllllIlIlIIlIlIlIlIl[0].length; ++lllllllllllllllllIlIlIIlIlIllIII) {
            for (int lllllllllllllllllIlIlIIlIlIllIIl = 0; lllllllllllllllllIlIlIIlIlIllIIl < lllllllllllllllllIlIlIIlIlIlIlIl.length; ++lllllllllllllllllIlIlIIlIlIllIIl) {
                if (lllllllllllllllllIlIlIIlIlIlIlIl[lllllllllllllllllIlIlIIlIlIllIIl][lllllllllllllllllIlIlIIlIlIllIII] != 0) {
                    switch (lllllllllllllllllIlIlIIlIlIlIlIl[lllllllllllllllllIlIlIIlIlIllIIl][lllllllllllllllllIlIlIIlIlIllIII]) {
                        case 1: {
                            final Polygon lllllllllllllllllIlIlIIlIlIlllll = new Polygon();
                            lllllllllllllllllIlIlIIlIlIlllll.addPoint((float)(lllllllllllllllllIlIlIIlIlIllIII * 32), (float)(lllllllllllllllllIlIlIIlIlIllIIl * 32));
                            lllllllllllllllllIlIlIIlIlIlllll.addPoint((float)(lllllllllllllllllIlIlIIlIlIllIII * 32 + 32), (float)(lllllllllllllllllIlIlIIlIlIllIIl * 32));
                            lllllllllllllllllIlIlIIlIlIlllll.addPoint((float)(lllllllllllllllllIlIlIIlIlIllIII * 32 + 32), (float)(lllllllllllllllllIlIlIIlIlIllIIl * 32 + 32));
                            lllllllllllllllllIlIlIIlIlIlllll.addPoint((float)(lllllllllllllllllIlIlIIlIlIllIII * 32), (float)(lllllllllllllllllIlIlIIlIlIllIIl * 32 + 32));
                            this.original.add(lllllllllllllllllIlIlIIlIlIlllll);
                            break;
                        }
                        case 2: {
                            final Polygon lllllllllllllllllIlIlIIlIlIllllI = new Polygon();
                            lllllllllllllllllIlIlIIlIlIllllI.addPoint((float)(lllllllllllllllllIlIlIIlIlIllIII * 32), (float)(lllllllllllllllllIlIlIIlIlIllIIl * 32));
                            lllllllllllllllllIlIlIIlIlIllllI.addPoint((float)(lllllllllllllllllIlIlIIlIlIllIII * 32 + 32), (float)(lllllllllllllllllIlIlIIlIlIllIIl * 32));
                            lllllllllllllllllIlIlIIlIlIllllI.addPoint((float)(lllllllllllllllllIlIlIIlIlIllIII * 32), (float)(lllllllllllllllllIlIlIIlIlIllIIl * 32 + 32));
                            this.original.add(lllllllllllllllllIlIlIIlIlIllllI);
                            break;
                        }
                        case 3: {
                            final Circle lllllllllllllllllIlIlIIlIlIlllIl = new Circle((float)(lllllllllllllllllIlIlIIlIlIllIII * 32 + 16), (float)(lllllllllllllllllIlIlIIlIlIllIIl * 32 + 32), 16.0f, 16);
                            this.original.add(lllllllllllllllllIlIlIIlIlIlllIl);
                            break;
                        }
                        case 4: {
                            final Polygon lllllllllllllllllIlIlIIlIlIlllII = new Polygon();
                            lllllllllllllllllIlIlIIlIlIlllII.addPoint((float)(lllllllllllllllllIlIlIIlIlIllIII * 32 + 32), (float)(lllllllllllllllllIlIlIIlIlIllIIl * 32));
                            lllllllllllllllllIlIlIIlIlIlllII.addPoint((float)(lllllllllllllllllIlIlIIlIlIllIII * 32 + 32), (float)(lllllllllllllllllIlIlIIlIlIllIIl * 32 + 32));
                            lllllllllllllllllIlIlIIlIlIlllII.addPoint((float)(lllllllllllllllllIlIlIIlIlIllIII * 32), (float)(lllllllllllllllllIlIlIIlIlIllIIl * 32 + 32));
                            this.original.add(lllllllllllllllllIlIlIIlIlIlllII);
                            break;
                        }
                        case 5: {
                            final Polygon lllllllllllllllllIlIlIIlIlIllIll = new Polygon();
                            lllllllllllllllllIlIlIIlIlIllIll.addPoint((float)(lllllllllllllllllIlIlIIlIlIllIII * 32), (float)(lllllllllllllllllIlIlIIlIlIllIIl * 32));
                            lllllllllllllllllIlIlIIlIlIllIll.addPoint((float)(lllllllllllllllllIlIlIIlIlIllIII * 32 + 32), (float)(lllllllllllllllllIlIlIIlIlIllIIl * 32));
                            lllllllllllllllllIlIlIIlIlIllIll.addPoint((float)(lllllllllllllllllIlIlIIlIlIllIII * 32 + 32), (float)(lllllllllllllllllIlIlIIlIlIllIIl * 32 + 32));
                            this.original.add(lllllllllllllllllIlIlIIlIlIllIll);
                            break;
                        }
                        case 6: {
                            final Polygon lllllllllllllllllIlIlIIlIlIllIlI = new Polygon();
                            lllllllllllllllllIlIlIIlIlIllIlI.addPoint((float)(lllllllllllllllllIlIlIIlIlIllIII * 32), (float)(lllllllllllllllllIlIlIIlIlIllIIl * 32));
                            lllllllllllllllllIlIlIIlIlIllIlI.addPoint((float)(lllllllllllllllllIlIlIIlIlIllIII * 32 + 32), (float)(lllllllllllllllllIlIlIIlIlIllIIl * 32));
                            lllllllllllllllllIlIlIIlIlIllIlI.addPoint((float)(lllllllllllllllllIlIlIIlIlIllIII * 32), (float)(lllllllllllllllllIlIlIIlIlIllIIl * 32 + 32));
                            this.original.add(lllllllllllllllllIlIlIIlIlIllIlI);
                            break;
                        }
                    }
                }
            }
        }
        final long lllllllllllllllllIlIlIIlIlIlIlII = System.currentTimeMillis();
        this.generateSpace(this.original, 0.0f, 0.0f, (float)((lllllllllllllllllIlIlIIlIlIlIllI + 1) * 32), (float)((lllllllllllllllllIlIlIIlIlIlIllI + 1) * 32), 8);
        this.combined = this.combineQuadSpace();
        final long lllllllllllllllllIlIlIIlIlIlIIll = System.currentTimeMillis();
        System.out.println(String.valueOf(new StringBuilder().append("Combine took: ").append(lllllllllllllllllIlIlIIlIlIlIIll - lllllllllllllllllIlIlIIlIlIlIlII)));
        System.out.println(String.valueOf(new StringBuilder().append("Combine result: ").append(this.combined.size())));
    }
    
    private void removeFromQuadSpace(final Shape lllllllllllllllllIlIlIIlIlllllIl) {
        for (int lllllllllllllllllIlIlIIlIlllllll = this.quadSpace.length, lllllllllllllllllIlIlIIllIIIIIlI = 0; lllllllllllllllllIlIlIIllIIIIIlI < lllllllllllllllllIlIlIIlIlllllll; ++lllllllllllllllllIlIlIIllIIIIIlI) {
            for (int lllllllllllllllllIlIlIIllIIIIIll = 0; lllllllllllllllllIlIlIIllIIIIIll < lllllllllllllllllIlIlIIlIlllllll; ++lllllllllllllllllIlIlIIllIIIIIll) {
                this.quadSpace[lllllllllllllllllIlIlIIllIIIIIlI][lllllllllllllllllIlIlIIllIIIIIll].remove(lllllllllllllllllIlIlIIlIlllllIl);
            }
        }
    }
}
