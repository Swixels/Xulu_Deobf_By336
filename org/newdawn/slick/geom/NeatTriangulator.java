package org.newdawn.slick.geom;

public class NeatTriangulator implements Triangulator
{
    private /* synthetic */ Triangle[] triangles;
    private /* synthetic */ int numTriangles;
    private /* synthetic */ int numEdges;
    private /* synthetic */ float[] pointsX;
    private /* synthetic */ int numPoints;
    private /* synthetic */ float[] pointsY;
    private /* synthetic */ int[] V;
    private /* synthetic */ Edge[] edges;
    private /* synthetic */ float offset;
    
    private boolean snip(final int lIlllIIIIlIII, final int lIllIllllllII, final int lIllIlllllIll, final int lIllIlllllIlI) {
        final float lIlllIIIIIlII = this.pointsX[this.V[lIlllIIIIlIII]];
        final float lIlllIIIIIIll = this.pointsY[this.V[lIlllIIIIlIII]];
        final float lIlllIIIIIIlI = this.pointsX[this.V[lIllIllllllII]];
        final float lIlllIIIIIIIl = this.pointsY[this.V[lIllIllllllII]];
        final float lIlllIIIIIIII = this.pointsX[this.V[lIllIlllllIll]];
        final float lIllIllllllll = this.pointsY[this.V[lIllIlllllIll]];
        if (1.0E-6f > (lIlllIIIIIIlI - lIlllIIIIIlII) * (lIllIllllllll - lIlllIIIIIIll) - (lIlllIIIIIIIl - lIlllIIIIIIll) * (lIlllIIIIIIII - lIlllIIIIIlII)) {
            return false;
        }
        for (int lIlllIIIIlIlI = 0; lIlllIIIIlIlI < lIllIlllllIlI; ++lIlllIIIIlIlI) {
            if (lIlllIIIIlIlI != lIlllIIIIlIII && lIlllIIIIlIlI != lIllIllllllII && lIlllIIIIlIlI != lIllIlllllIll) {
                final float lIlllIIIIllII = this.pointsX[this.V[lIlllIIIIlIlI]];
                final float lIlllIIIIlIll = this.pointsY[this.V[lIlllIIIIlIlI]];
                if (insideTriangle(lIlllIIIIIlII, lIlllIIIIIIll, lIlllIIIIIIlI, lIlllIIIIIIIl, lIlllIIIIIIII, lIllIllllllll, lIlllIIIIllII, lIlllIIIIlIll)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public void startHole() {
    }
    
    public void basicTriangulation() throws InternalException {
        int lIllIllIIlllI = this.numPoints;
        if (lIllIllIIlllI < 3) {
            return;
        }
        this.numEdges = 0;
        this.numTriangles = 0;
        this.V = new int[lIllIllIIlllI];
        if (0.0 < this.area()) {
            for (int lIllIllIllIIl = 0; lIllIllIllIIl < lIllIllIIlllI; ++lIllIllIllIIl) {
                this.V[lIllIllIllIIl] = lIllIllIllIIl;
            }
        }
        else {
            for (int lIllIllIllIII = 0; lIllIllIllIII < lIllIllIIlllI; ++lIllIllIllIII) {
                this.V[lIllIllIllIII] = this.numPoints - 1 - lIllIllIllIII;
            }
        }
        int lIllIllIIllIl = 2 * lIllIllIIlllI;
        int lIllIllIIllII = lIllIllIIlllI - 1;
        while (lIllIllIIlllI > 2) {
            if (0 >= lIllIllIIllIl--) {
                throw new InternalException("Bad polygon");
            }
            int lIllIllIlIIIl = lIllIllIIllII;
            if (lIllIllIIlllI <= lIllIllIlIIIl) {
                lIllIllIlIIIl = 0;
            }
            lIllIllIIllII = lIllIllIlIIIl + 1;
            if (lIllIllIIlllI <= lIllIllIIllII) {
                lIllIllIIllII = 0;
            }
            int lIllIllIlIIII = lIllIllIIllII + 1;
            if (lIllIllIIlllI <= lIllIllIlIIII) {
                lIllIllIlIIII = 0;
            }
            if (!this.snip(lIllIllIlIIIl, lIllIllIIllII, lIllIllIlIIII, lIllIllIIlllI)) {
                continue;
            }
            final int lIllIllIlIlIl = this.V[lIllIllIlIIIl];
            final int lIllIllIlIlII = this.V[lIllIllIIllII];
            final int lIllIllIlIIll = this.V[lIllIllIlIIII];
            if (this.numTriangles == this.triangles.length) {
                final Triangle[] lIllIllIlIlll = new Triangle[this.triangles.length * 2];
                System.arraycopy(this.triangles, 0, lIllIllIlIlll, 0, this.numTriangles);
                this.triangles = lIllIllIlIlll;
            }
            this.triangles[this.numTriangles] = new Triangle(lIllIllIlIlIl, lIllIllIlIlII, lIllIllIlIIll);
            this.addEdge(lIllIllIlIlIl, lIllIllIlIlII, this.numTriangles);
            this.addEdge(lIllIllIlIlII, lIllIllIlIIll, this.numTriangles);
            this.addEdge(lIllIllIlIIll, lIllIllIlIlIl, this.numTriangles);
            ++this.numTriangles;
            int lIllIllIlIIlI = lIllIllIIllII;
            for (int lIllIllIlIllI = lIllIllIIllII + 1; lIllIllIlIllI < lIllIllIIlllI; ++lIllIllIlIllI) {
                this.V[lIllIllIlIIlI] = this.V[lIllIllIlIllI];
                ++lIllIllIlIIlI;
            }
            --lIllIllIIlllI;
            lIllIllIIllIl = 2 * lIllIllIIlllI;
        }
        this.V = null;
    }
    
    @Override
    public float[] getTrianglePoint(final int lIllIIlIlllll, final int lIllIIllIIIll) {
        final float lIllIIllIIIlI = this.pointsX[this.triangles[lIllIIlIlllll].v[lIllIIllIIIll]];
        final float lIllIIllIIIIl = this.pointsY[this.triangles[lIllIIlIlllll].v[lIllIIllIIIll]];
        return new float[] { lIllIIllIIIlI, lIllIIllIIIIl };
    }
    
    void markSuspect(final int lIllllIlIIlIl, final int lIllllIlIIlII, final boolean lIllllIlIlIII) throws InternalException {
        final int lIllllIlIIlll;
        if (0 > (lIllllIlIIlll = this.findEdge(lIllllIlIIlIl, lIllllIlIIlII))) {
            throw new InternalException("Attempt to mark unknown edge");
        }
        this.edges[lIllllIlIIlll].suspect = lIllllIlIlIII;
    }
    
    @Override
    public void addPolyPoint(final float lIllIIlllIIII, float lIllIIllIllll) {
        for (int lIllIIlllIllI = 0; lIllIIlllIllI < this.numPoints; ++lIllIIlllIllI) {
            if (this.pointsX[lIllIIlllIllI] == lIllIIlllIIII && this.pointsY[lIllIIlllIllI] == lIllIIllIllll) {
                lIllIIllIllll += this.offset;
                this.offset += 1.0E-6f;
            }
        }
        if (this.numPoints == this.pointsX.length) {
            float[] lIllIIlllIlIl = new float[this.numPoints * 2];
            System.arraycopy(this.pointsX, 0, lIllIIlllIlIl, 0, this.numPoints);
            this.pointsX = lIllIIlllIlIl;
            lIllIIlllIlIl = new float[this.numPoints * 2];
            System.arraycopy(this.pointsY, 0, lIllIIlllIlIl, 0, this.numPoints);
            this.pointsY = lIllIIlllIlIl;
        }
        this.pointsX[this.numPoints] = lIllIIlllIIII;
        this.pointsY[this.numPoints] = lIllIIllIllll;
        ++this.numPoints;
    }
    
    public NeatTriangulator() {
        this.offset = 1.0E-6f;
        this.pointsX = new float[100];
        this.pointsY = new float[100];
        this.numPoints = 0;
        this.edges = new Edge[100];
        this.numEdges = 0;
        this.triangles = new Triangle[100];
        this.numTriangles = 0;
    }
    
    @Override
    public int getTriangleCount() {
        return this.numTriangles;
    }
    
    private void addEdge(final int lIlllllIIIlIl, final int lIlllllIIIlII, final int lIlllllIIIIll) {
        int lIlllllIIllII = this.findEdge(lIlllllIIIlIl, lIlllllIIIlII);
        Edge lIlllllIIlIIl;
        int lIlllllIIlIll;
        int lIlllllIIlIlI;
        if (lIlllllIIllII < 0) {
            if (this.numEdges == this.edges.length) {
                final Edge[] lIlllllIlIllI = new Edge[this.edges.length * 2];
                System.arraycopy(this.edges, 0, lIlllllIlIllI, 0, this.numEdges);
                this.edges = lIlllllIlIllI;
            }
            final int lIlllllIlIlIl = -1;
            final int lIlllllIlIlII = -1;
            lIlllllIIllII = this.numEdges++;
            final Edge[] edges = this.edges;
            final int n = lIlllllIIllII;
            final Edge edge = new Edge();
            edges[n] = edge;
            final Edge lIlllllIlIIll = edge;
        }
        else {
            lIlllllIIlIIl = this.edges[lIlllllIIllII];
            lIlllllIIlIll = lIlllllIIlIIl.t0;
            lIlllllIIlIlI = lIlllllIIlIIl.t1;
        }
        int lIlllllIIlIII = 0;
        int lIlllllIIIlll = 0;
        if (lIlllllIIIlIl < lIlllllIIIlII) {
            final int lIlllllIlIIlI = lIlllllIIIlIl;
            final int lIlllllIlIIIl = lIlllllIIIlII;
            lIlllllIIlIll = lIlllllIIIIll;
        }
        else {
            lIlllllIIlIII = lIlllllIIIlII;
            lIlllllIIIlll = lIlllllIIIlIl;
            lIlllllIIlIlI = lIlllllIIIIll;
        }
        lIlllllIIlIIl.v0 = lIlllllIIlIII;
        lIlllllIIlIIl.v1 = lIlllllIIIlll;
        lIlllllIIlIIl.t0 = lIlllllIIlIll;
        lIlllllIIlIIl.t1 = lIlllllIIlIlI;
        lIlllllIIlIIl.suspect = true;
    }
    
    @Override
    public boolean triangulate() {
        try {
            this.basicTriangulation();
            return true;
        }
        catch (InternalException lIllIIllllllI) {
            this.numEdges = 0;
            return false;
        }
    }
    
    private static boolean insideTriangle(final float lIlllIlIIlIII, final float lIlllIlIIIlll, final float lIlllIlIIIllI, final float lIlllIIlIlllI, final float lIlllIlIIIlII, final float lIlllIIlIllII, final float lIlllIIlIlIll, final float lIlllIlIIIIIl) {
        final float lIlllIlIIIIII = lIlllIlIIIlII - lIlllIlIIIllI;
        final float lIlllIIllllll = lIlllIIlIllII - lIlllIIlIlllI;
        final float lIlllIIlllllI = lIlllIlIIlIII - lIlllIlIIIlII;
        final float lIlllIIllllIl = lIlllIlIIIlll - lIlllIIlIllII;
        final float lIlllIIllllII = lIlllIlIIIllI - lIlllIlIIlIII;
        final float lIlllIIlllIll = lIlllIIlIlllI - lIlllIlIIIlll;
        final float lIlllIIlllIlI = lIlllIIlIlIll - lIlllIlIIlIII;
        final float lIlllIIlllIIl = lIlllIlIIIIIl - lIlllIlIIIlll;
        final float lIlllIIlllIII = lIlllIIlIlIll - lIlllIlIIIllI;
        final float lIlllIIllIlll = lIlllIlIIIIIl - lIlllIIlIlllI;
        final float lIlllIIllIllI = lIlllIIlIlIll - lIlllIlIIIlII;
        final float lIlllIIllIlIl = lIlllIlIIIIIl - lIlllIIlIllII;
        final float lIlllIIllIlII = lIlllIlIIIIII * lIlllIIllIlll - lIlllIIllllll * lIlllIIlllIII;
        final float lIlllIIllIIll = lIlllIIllllII * lIlllIIlllIIl - lIlllIIlllIll * lIlllIIlllIlI;
        final float lIlllIIllIIlI = lIlllIIlllllI * lIlllIIllIlIl - lIlllIIllllIl * lIlllIIllIllI;
        return lIlllIIllIlII >= 0.0 && lIlllIIllIIlI >= 0.0 && lIlllIIllIIll >= 0.0;
    }
    
    private float area() {
        float lIllIlllIlIlI = 0.0f;
        int lIllIlllIlIIl = this.numPoints - 1;
        for (int lIllIlllIllII = 0; lIllIlllIllII < this.numPoints; lIllIlllIlIIl = lIllIlllIllII++) {
            lIllIlllIlIlI += this.pointsX[lIllIlllIlIIl] * this.pointsY[lIllIlllIllII] - this.pointsY[lIllIlllIlIIl] * this.pointsX[lIllIlllIllII];
        }
        return lIllIlllIlIlI * 0.5f;
    }
    
    private static float rho(final float lIlllIlllllIl, final float lIlllIlllIIIl, final float lIlllIllllIll, final float lIlllIllIllll, final float lIlllIllIlllI, final float lIlllIllIllIl) {
        final float lIlllIlllIlll = lIlllIllIlllI - lIlllIllllIll;
        final float lIlllIlllIllI = lIlllIllIllIl - lIlllIllIllll;
        final float lIlllIlllIlIl = lIlllIlllllIl - lIlllIllIlllI;
        final float lIlllIlllIlII = lIlllIlllIIIl - lIlllIllIllIl;
        float lIlllIlllIIll = lIlllIlllIlll * lIlllIlllIlII - lIlllIlllIllI * lIlllIlllIlIl;
        if (lIlllIlllIIll > 0.0f) {
            if (lIlllIlllIIll < 1.0E-6f) {
                lIlllIlllIIll = 1.0E-6f;
            }
            final float lIllllIIIIlIl = lIlllIlllIlll * lIlllIlllIlll;
            final float lIllllIIIIlII = lIlllIlllIllI * lIlllIlllIllI;
            final float lIllllIIIIIll = lIlllIlllIlIl * lIlllIlllIlIl;
            final float lIllllIIIIIlI = lIlllIlllIlII * lIlllIlllIlII;
            final float lIllllIIIIIIl = lIlllIllllIll - lIlllIlllllIl;
            final float lIllllIIIIIII = lIlllIllIllll - lIlllIlllIIIl;
            final float lIlllIlllllll = lIllllIIIIIIl * lIllllIIIIIIl;
            final float lIlllIllllllI = lIllllIIIIIII * lIllllIIIIIII;
            return (lIllllIIIIlIl + lIllllIIIIlII) * (lIllllIIIIIll + lIllllIIIIIlI) * (lIlllIlllllll + lIlllIllllllI) / (lIlllIlllIIll * lIlllIlllIIll);
        }
        return -1.0f;
    }
    
    private void optimize() throws InternalException {
        Edge lIllIlIlIlIII;
        while ((lIllIlIlIlIII = this.chooseSuspect()) != null) {
            final int lIllIlIlIIlll = lIllIlIlIlIII.v0;
            final int lIllIlIlIIllI = lIllIlIlIlIII.v1;
            final int lIllIlIlIIlIl = lIllIlIlIlIII.t0;
            final int lIllIlIlIIlII = lIllIlIlIlIII.t1;
            int lIllIlIlIIIll = -1;
            int lIllIlIlIIIlI = -1;
            for (int lIllIlIlIlIll = 0; lIllIlIlIlIll < 3; ++lIllIlIlIlIll) {
                final int lIllIlIlIllII = this.triangles[lIllIlIlIIlIl].v[lIllIlIlIlIll];
                if (lIllIlIlIIlll != lIllIlIlIllII && lIllIlIlIIllI != lIllIlIlIllII) {
                    lIllIlIlIIIlI = lIllIlIlIllII;
                    break;
                }
            }
            for (int lIllIlIlIlIIl = 0; lIllIlIlIlIIl < 3; ++lIllIlIlIlIIl) {
                final int lIllIlIlIlIlI = this.triangles[lIllIlIlIIlII].v[lIllIlIlIlIIl];
                if (lIllIlIlIIlll != lIllIlIlIlIlI && lIllIlIlIIllI != lIllIlIlIlIlI) {
                    lIllIlIlIIIll = lIllIlIlIlIlI;
                    break;
                }
            }
            if (-1 == lIllIlIlIIIll || -1 == lIllIlIlIIIlI) {
                throw new InternalException("can't find quad");
            }
            final float lIllIlIlIIIIl = this.pointsX[lIllIlIlIIlll];
            final float lIllIlIlIIIII = this.pointsY[lIllIlIlIIlll];
            final float lIllIlIIlllll = this.pointsX[lIllIlIlIIIll];
            final float lIllIlIIllllI = this.pointsY[lIllIlIlIIIll];
            final float lIllIlIIlllIl = this.pointsX[lIllIlIlIIllI];
            final float lIllIlIIlllII = this.pointsY[lIllIlIlIIllI];
            final float lIllIlIIllIll = this.pointsX[lIllIlIlIIIlI];
            final float lIllIlIIllIlI = this.pointsY[lIllIlIlIIIlI];
            float lIllIlIIllIIl = rho(lIllIlIlIIIIl, lIllIlIlIIIII, lIllIlIIlllll, lIllIlIIllllI, lIllIlIIlllIl, lIllIlIIlllII);
            final float lIllIlIIllIII = rho(lIllIlIlIIIIl, lIllIlIlIIIII, lIllIlIIlllIl, lIllIlIIlllII, lIllIlIIllIll, lIllIlIIllIlI);
            float lIllIlIIlIlll = rho(lIllIlIIlllll, lIllIlIIllllI, lIllIlIIlllIl, lIllIlIIlllII, lIllIlIIllIll, lIllIlIIllIlI);
            final float lIllIlIIlIllI = rho(lIllIlIIlllll, lIllIlIIllllI, lIllIlIIllIll, lIllIlIIllIlI, lIllIlIlIIIIl, lIllIlIlIIIII);
            if (0.0f > lIllIlIIllIIl || 0.0f > lIllIlIIllIII) {
                throw new InternalException("original triangles backwards");
            }
            if (0.0f > lIllIlIIlIlll || 0.0f > lIllIlIIlIllI) {
                continue;
            }
            if (lIllIlIIllIIl > lIllIlIIllIII) {
                lIllIlIIllIIl = lIllIlIIllIII;
            }
            if (lIllIlIIlIlll > lIllIlIIlIllI) {
                lIllIlIIlIlll = lIllIlIIlIllI;
            }
            if (lIllIlIIllIIl <= lIllIlIIlIlll) {
                continue;
            }
            this.deleteEdge(lIllIlIlIIlll, lIllIlIlIIllI);
            this.triangles[lIllIlIlIIlIl].v[0] = lIllIlIlIIIll;
            this.triangles[lIllIlIlIIlIl].v[1] = lIllIlIlIIllI;
            this.triangles[lIllIlIlIIlIl].v[2] = lIllIlIlIIIlI;
            this.triangles[lIllIlIlIIlII].v[0] = lIllIlIlIIIll;
            this.triangles[lIllIlIlIIlII].v[1] = lIllIlIlIIIlI;
            this.triangles[lIllIlIlIIlII].v[2] = lIllIlIlIIlll;
            this.addEdge(lIllIlIlIIIll, lIllIlIlIIllI, lIllIlIlIIlIl);
            this.addEdge(lIllIlIlIIllI, lIllIlIlIIIlI, lIllIlIlIIlIl);
            this.addEdge(lIllIlIlIIIlI, lIllIlIlIIIll, lIllIlIlIIlIl);
            this.addEdge(lIllIlIlIIIlI, lIllIlIlIIlll, lIllIlIlIIlII);
            this.addEdge(lIllIlIlIIlll, lIllIlIlIIIll, lIllIlIlIIlII);
            this.addEdge(lIllIlIlIIIll, lIllIlIlIIIlI, lIllIlIlIIlII);
            this.markSuspect(lIllIlIlIIIll, lIllIlIlIIIlI, false);
        }
    }
    
    private void deleteEdge(final int lIllllIllIlll, final int lIllllIllIllI) throws InternalException {
        final int lIllllIllIlIl;
        if (0 > (lIllllIllIlIl = this.findEdge(lIllllIllIlll, lIllllIllIllI))) {
            throw new InternalException("Attempt to delete unknown edge");
        }
        final Edge[] edges = this.edges;
        final int n = lIllllIllIlIl;
        final Edge[] edges2 = this.edges;
        final int numEdges = this.numEdges - 1;
        this.numEdges = numEdges;
        edges[n] = edges2[numEdges];
    }
    
    private Edge chooseSuspect() {
        for (int lIllllIIlllIl = 0; lIllllIIlllIl < this.numEdges; ++lIllllIIlllIl) {
            final Edge lIllllIIllllI = this.edges[lIllllIIlllIl];
            if (lIllllIIllllI.suspect) {
                lIllllIIllllI.suspect = false;
                if (lIllllIIllllI.t0 >= 0 && lIllllIIllllI.t1 >= 0) {
                    return lIllllIIllllI;
                }
            }
        }
        return null;
    }
    
    public void clear() {
        this.numPoints = 0;
        this.numEdges = 0;
        this.numTriangles = 0;
    }
    
    private int findEdge(final int lIllllllIIlIl, final int lIllllllIlIIl) {
        int lIllllllIlIII = 0;
        int lIllllllIIlll = 0;
        if (lIllllllIIlIl < lIllllllIlIIl) {
            final int lIllllllIlllI = lIllllllIIlIl;
            final int lIllllllIllIl = lIllllllIlIIl;
        }
        else {
            lIllllllIlIII = lIllllllIlIIl;
            lIllllllIIlll = lIllllllIIlIl;
        }
        for (int lIllllllIllII = 0; lIllllllIllII < this.numEdges; ++lIllllllIllII) {
            if (this.edges[lIllllllIllII].v0 == lIllllllIlIII && this.edges[lIllllllIllII].v1 == lIllllllIIlll) {
                return lIllllllIllII;
            }
        }
        return -1;
    }
    
    class Edge
    {
        /* synthetic */ int t0;
        /* synthetic */ int v1;
        /* synthetic */ boolean suspect;
        /* synthetic */ int t1;
        /* synthetic */ int v0;
        
        Edge() {
            this.v0 = -1;
            this.v1 = -1;
            this.t0 = -1;
            this.t1 = -1;
        }
    }
    
    class InternalException extends Exception
    {
        public InternalException(final String lIlIIllIIlIIlIl) {
            super(lIlIIllIIlIIlIl);
        }
    }
    
    class Triangle
    {
        /* synthetic */ int[] v;
        
        Triangle(final int llllllllllllllllIlIlIIIllIIlllll, final int llllllllllllllllIlIlIIIllIIllIIl, final int llllllllllllllllIlIlIIIllIIllIII) {
            this.v = new int[3];
            this.v[0] = llllllllllllllllIlIlIIIllIIlllll;
            this.v[1] = llllllllllllllllIlIlIIIllIIllIIl;
            this.v[2] = llllllllllllllllIlIlIIIllIIllIII;
        }
    }
}
