package org.newdawn.slick.util.pathfinding;

import org.newdawn.slick.util.pathfinding.heuristics.*;
import java.util.*;

public class AStarPathFinder implements PathFindingContext, PathFinder
{
    private /* synthetic */ ArrayList closed;
    private /* synthetic */ Mover mover;
    private /* synthetic */ int sourceX;
    private /* synthetic */ boolean allowDiagMovement;
    private /* synthetic */ AStarHeuristic heuristic;
    private /* synthetic */ int maxSearchDistance;
    private /* synthetic */ Node[][] nodes;
    private /* synthetic */ TileBasedMap map;
    private /* synthetic */ PriorityList open;
    private /* synthetic */ int distance;
    private /* synthetic */ Node current;
    private /* synthetic */ int sourceY;
    
    @Override
    public int getSourceX() {
        return this.sourceX;
    }
    
    protected boolean isValidLocation(final Mover llllllllllllllllllIlIIlIlllIlIII, final int llllllllllllllllllIlIIlIlllIIIII, final int llllllllllllllllllIlIIlIllIlllll, final int llllllllllllllllllIlIIlIlllIIlIl, final int llllllllllllllllllIlIIlIlllIIlII) {
        boolean llllllllllllllllllIlIIlIlllIIIll = llllllllllllllllllIlIIlIlllIIlIl < 0 || llllllllllllllllllIlIIlIlllIIlII < 0 || llllllllllllllllllIlIIlIlllIIlIl >= this.map.getWidthInTiles() || llllllllllllllllllIlIIlIlllIIlII >= this.map.getHeightInTiles();
        if (!llllllllllllllllllIlIIlIlllIIIll && (llllllllllllllllllIlIIlIlllIIIII != llllllllllllllllllIlIIlIlllIIlIl || llllllllllllllllllIlIIlIllIlllll != llllllllllllllllllIlIIlIlllIIlII)) {
            this.mover = llllllllllllllllllIlIIlIlllIlIII;
            this.sourceX = llllllllllllllllllIlIIlIlllIIIII;
            this.sourceY = llllllllllllllllllIlIIlIllIlllll;
            llllllllllllllllllIlIIlIlllIIIll = this.map.blocked(this, llllllllllllllllllIlIIlIlllIIlIl, llllllllllllllllllIlIIlIlllIIlII);
        }
        return !llllllllllllllllllIlIIlIlllIIIll;
    }
    
    protected void addToClosed(final Node llllllllllllllllllIlIIlIlllllIll) {
        llllllllllllllllllIlIIlIlllllIll.setClosed(true);
        this.closed.add(llllllllllllllllllIlIIlIlllllIll);
    }
    
    public int getCurrentX() {
        if (this.current == null) {
            return -1;
        }
        return this.current.x;
    }
    
    protected Node getFirstInOpen() {
        return (Node)this.open.first();
    }
    
    @Override
    public Path findPath(final Mover llllllllllllllllllIlIIllIIllIIII, final int llllllllllllllllllIlIIllIIlIIllI, final int llllllllllllllllllIlIIllIIlIlllI, final int llllllllllllllllllIlIIllIIlIllIl, final int llllllllllllllllllIlIIllIIlIllII) {
        this.current = null;
        this.mover = llllllllllllllllllIlIIllIIllIIII;
        this.sourceX = llllllllllllllllllIlIIllIIlIllIl;
        this.sourceY = llllllllllllllllllIlIIllIIlIllII;
        this.distance = 0;
        if (this.map.blocked(this, llllllllllllllllllIlIIllIIlIllIl, llllllllllllllllllIlIIllIIlIllII)) {
            return null;
        }
        for (int llllllllllllllllllIlIIllIIlllIlI = 0; llllllllllllllllllIlIIllIIlllIlI < this.map.getWidthInTiles(); ++llllllllllllllllllIlIIllIIlllIlI) {
            for (int llllllllllllllllllIlIIllIIlllIll = 0; llllllllllllllllllIlIIllIIlllIll < this.map.getHeightInTiles(); ++llllllllllllllllllIlIIllIIlllIll) {
                this.nodes[llllllllllllllllllIlIIllIIlllIlI][llllllllllllllllllIlIIllIIlllIll].reset();
            }
        }
        this.nodes[llllllllllllllllllIlIIllIIlIIllI][llllllllllllllllllIlIIllIIlIlllI].cost = 0.0f;
        this.nodes[llllllllllllllllllIlIIllIIlIIllI][llllllllllllllllllIlIIllIIlIlllI].depth = 0;
        this.closed.clear();
        this.open.clear();
        this.addToOpen(this.nodes[llllllllllllllllllIlIIllIIlIIllI][llllllllllllllllllIlIIllIIlIlllI]);
        this.nodes[llllllllllllllllllIlIIllIIlIllIl][llllllllllllllllllIlIIllIIlIllII].parent = null;
        int llllllllllllllllllIlIIllIIlIlIll = 0;
        while (llllllllllllllllllIlIIllIIlIlIll < this.maxSearchDistance && this.open.size() != 0) {
            int llllllllllllllllllIlIIllIIllIIll = llllllllllllllllllIlIIllIIlIIllI;
            int llllllllllllllllllIlIIllIIllIIlI = llllllllllllllllllIlIIllIIlIlllI;
            if (this.current != null) {
                llllllllllllllllllIlIIllIIllIIll = this.current.x;
                llllllllllllllllllIlIIllIIllIIlI = this.current.y;
            }
            this.current = this.getFirstInOpen();
            this.distance = this.current.depth;
            if (this.current == this.nodes[llllllllllllllllllIlIIllIIlIllIl][llllllllllllllllllIlIIllIIlIllII] && this.isValidLocation(llllllllllllllllllIlIIllIIllIIII, llllllllllllllllllIlIIllIIllIIll, llllllllllllllllllIlIIllIIllIIlI, llllllllllllllllllIlIIllIIlIllIl, llllllllllllllllllIlIIllIIlIllII)) {
                break;
            }
            this.removeFromOpen(this.current);
            this.addToClosed(this.current);
            for (int llllllllllllllllllIlIIllIIllIlII = -1; llllllllllllllllllIlIIllIIllIlII < 2; ++llllllllllllllllllIlIIllIIllIlII) {
                for (int llllllllllllllllllIlIIllIIllIlIl = -1; llllllllllllllllllIlIIllIIllIlIl < 2; ++llllllllllllllllllIlIIllIIllIlIl) {
                    if (llllllllllllllllllIlIIllIIllIlII != 0 || llllllllllllllllllIlIIllIIllIlIl != 0) {
                        if (this.allowDiagMovement || llllllllllllllllllIlIIllIIllIlII == 0 || llllllllllllllllllIlIIllIIllIlIl == 0) {
                            final int llllllllllllllllllIlIIllIIllIlll = llllllllllllllllllIlIIllIIllIlII + this.current.x;
                            final int llllllllllllllllllIlIIllIIllIllI = llllllllllllllllllIlIIllIIllIlIl + this.current.y;
                            if (this.isValidLocation(llllllllllllllllllIlIIllIIllIIII, this.current.x, this.current.y, llllllllllllllllllIlIIllIIllIlll, llllllllllllllllllIlIIllIIllIllI)) {
                                final float llllllllllllllllllIlIIllIIlllIIl = this.current.cost + this.getMovementCost(llllllllllllllllllIlIIllIIllIIII, this.current.x, this.current.y, llllllllllllllllllIlIIllIIllIlll, llllllllllllllllllIlIIllIIllIllI);
                                final Node llllllllllllllllllIlIIllIIlllIII = this.nodes[llllllllllllllllllIlIIllIIllIlll][llllllllllllllllllIlIIllIIllIllI];
                                this.map.pathFinderVisited(llllllllllllllllllIlIIllIIllIlll, llllllllllllllllllIlIIllIIllIllI);
                                if (llllllllllllllllllIlIIllIIlllIIl < llllllllllllllllllIlIIllIIlllIII.cost) {
                                    if (this.inOpenList(llllllllllllllllllIlIIllIIlllIII)) {
                                        this.removeFromOpen(llllllllllllllllllIlIIllIIlllIII);
                                    }
                                    if (this.inClosedList(llllllllllllllllllIlIIllIIlllIII)) {
                                        this.removeFromClosed(llllllllllllllllllIlIIllIIlllIII);
                                    }
                                }
                                if (!this.inOpenList(llllllllllllllllllIlIIllIIlllIII) && !this.inClosedList(llllllllllllllllllIlIIllIIlllIII)) {
                                    llllllllllllllllllIlIIllIIlllIII.cost = llllllllllllllllllIlIIllIIlllIIl;
                                    llllllllllllllllllIlIIllIIlllIII.heuristic = this.getHeuristicCost(llllllllllllllllllIlIIllIIllIIII, llllllllllllllllllIlIIllIIllIlll, llllllllllllllllllIlIIllIIllIllI, llllllllllllllllllIlIIllIIlIllIl, llllllllllllllllllIlIIllIIlIllII);
                                    llllllllllllllllllIlIIllIIlIlIll = Math.max(llllllllllllllllllIlIIllIIlIlIll, llllllllllllllllllIlIIllIIlllIII.setParent(this.current));
                                    this.addToOpen(llllllllllllllllllIlIIllIIlllIII);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (this.nodes[llllllllllllllllllIlIIllIIlIllIl][llllllllllllllllllIlIIllIIlIllII].parent == null) {
            return null;
        }
        final Path llllllllllllllllllIlIIllIIlIlIlI = new Path();
        for (Node llllllllllllllllllIlIIllIIlIlIIl = this.nodes[llllllllllllllllllIlIIllIIlIllIl][llllllllllllllllllIlIIllIIlIllII]; llllllllllllllllllIlIIllIIlIlIIl != this.nodes[llllllllllllllllllIlIIllIIlIIllI][llllllllllllllllllIlIIllIIlIlllI]; llllllllllllllllllIlIIllIIlIlIIl = llllllllllllllllllIlIIllIIlIlIIl.parent) {
            llllllllllllllllllIlIIllIIlIlIlI.prependStep(llllllllllllllllllIlIIllIIlIlIIl.x, llllllllllllllllllIlIIllIIlIlIIl.y);
        }
        llllllllllllllllllIlIIllIIlIlIlI.prependStep(llllllllllllllllllIlIIllIIlIIllI, llllllllllllllllllIlIIllIIlIlllI);
        return llllllllllllllllllIlIIllIIlIlIlI;
    }
    
    protected boolean inClosedList(final Node llllllllllllllllllIlIIlIllllIlll) {
        return llllllllllllllllllIlIIlIllllIlll.isClosed();
    }
    
    public int getCurrentY() {
        if (this.current == null) {
            return -1;
        }
        return this.current.y;
    }
    
    public float getHeuristicCost(final Mover llllllllllllllllllIlIIlIllIIIIlI, final int llllllllllllllllllIlIIlIllIIIIIl, final int llllllllllllllllllIlIIlIlIlllIlI, final int llllllllllllllllllIlIIlIlIlllIIl, final int llllllllllllllllllIlIIlIlIlllIII) {
        return this.heuristic.getCost(this.map, llllllllllllllllllIlIIlIllIIIIlI, llllllllllllllllllIlIIlIllIIIIIl, llllllllllllllllllIlIIlIlIlllIlI, llllllllllllllllllIlIIlIlIlllIIl, llllllllllllllllllIlIIlIlIlllIII);
    }
    
    public AStarPathFinder(final TileBasedMap llllllllllllllllllIlIIllIlIlIIII, final int llllllllllllllllllIlIIllIlIlIlII, final boolean llllllllllllllllllIlIIllIlIlIIll, final AStarHeuristic llllllllllllllllllIlIIllIlIIllIl) {
        this.closed = new ArrayList();
        this.open = new PriorityList();
        this.heuristic = llllllllllllllllllIlIIllIlIIllIl;
        this.map = llllllllllllllllllIlIIllIlIlIIII;
        this.maxSearchDistance = llllllllllllllllllIlIIllIlIlIlII;
        this.allowDiagMovement = llllllllllllllllllIlIIllIlIlIIll;
        this.nodes = new Node[llllllllllllllllllIlIIllIlIlIIII.getWidthInTiles()][llllllllllllllllllIlIIllIlIlIIII.getHeightInTiles()];
        for (int llllllllllllllllllIlIIllIlIlIlll = 0; llllllllllllllllllIlIIllIlIlIlll < llllllllllllllllllIlIIllIlIlIIII.getWidthInTiles(); ++llllllllllllllllllIlIIllIlIlIlll) {
            for (int llllllllllllllllllIlIIllIlIllIII = 0; llllllllllllllllllIlIIllIlIllIII < llllllllllllllllllIlIIllIlIlIIII.getHeightInTiles(); ++llllllllllllllllllIlIIllIlIllIII) {
                this.nodes[llllllllllllllllllIlIIllIlIlIlll][llllllllllllllllllIlIIllIlIllIII] = new Node(llllllllllllllllllIlIIllIlIlIlll, llllllllllllllllllIlIIllIlIllIII);
            }
        }
    }
    
    @Override
    public int getSourceY() {
        return this.sourceY;
    }
    
    public float getMovementCost(final Mover llllllllllllllllllIlIIlIllIIlllI, final int llllllllllllllllllIlIIlIllIlIIll, final int llllllllllllllllllIlIIlIllIIllII, final int llllllllllllllllllIlIIlIllIIlIll, final int llllllllllllllllllIlIIlIllIlIIII) {
        this.mover = llllllllllllllllllIlIIlIllIIlllI;
        this.sourceX = llllllllllllllllllIlIIlIllIlIIll;
        this.sourceY = llllllllllllllllllIlIIlIllIIllII;
        return this.map.getCost(this, llllllllllllllllllIlIIlIllIIlIll, llllllllllllllllllIlIIlIllIlIIII);
    }
    
    protected void addToOpen(final Node llllllllllllllllllIlIIllIIIIllIl) {
        llllllllllllllllllIlIIllIIIIllIl.setOpen(true);
        this.open.add(llllllllllllllllllIlIIllIIIIllIl);
    }
    
    protected void removeFromOpen(final Node llllllllllllllllllIlIIllIIIIIIIl) {
        llllllllllllllllllIlIIllIIIIIIIl.setOpen(false);
        this.open.remove(llllllllllllllllllIlIIllIIIIIIIl);
    }
    
    public AStarPathFinder(final TileBasedMap llllllllllllllllllIlIIllIllIIllI, final int llllllllllllllllllIlIIllIllIIlIl, final boolean llllllllllllllllllIlIIllIllIIIII) {
        this(llllllllllllllllllIlIIllIllIIllI, llllllllllllllllllIlIIllIllIIlIl, llllllllllllllllllIlIIllIllIIIII, new ClosestHeuristic());
    }
    
    @Override
    public Mover getMover() {
        return this.mover;
    }
    
    protected boolean inOpenList(final Node llllllllllllllllllIlIIllIIIIlIII) {
        return llllllllllllllllllIlIIllIIIIlIII.isOpen();
    }
    
    protected void removeFromClosed(final Node llllllllllllllllllIlIIlIllllIIIl) {
        llllllllllllllllllIlIIlIllllIIIl.setClosed(false);
        this.closed.remove(llllllllllllllllllIlIIlIllllIIIl);
    }
    
    @Override
    public int getSearchDistance() {
        return this.distance;
    }
    
    private class Node implements Comparable
    {
        private /* synthetic */ float cost;
        private /* synthetic */ boolean closed;
        private /* synthetic */ int y;
        private /* synthetic */ Node parent;
        private /* synthetic */ int depth;
        private /* synthetic */ int x;
        private /* synthetic */ boolean open;
        private /* synthetic */ float heuristic;
        
        @Override
        public int compareTo(final Object lllllllllllllllllllIIIlIIIIllIIl) {
            final Node lllllllllllllllllllIIIlIIIIllIII = (Node)lllllllllllllllllllIIIlIIIIllIIl;
            final float lllllllllllllllllllIIIlIIIIlIlll = this.heuristic + this.cost;
            final float lllllllllllllllllllIIIlIIIIlIllI = lllllllllllllllllllIIIlIIIIllIII.heuristic + lllllllllllllllllllIIIlIIIIllIII.cost;
            if (lllllllllllllllllllIIIlIIIIlIlll < lllllllllllllllllllIIIlIIIIlIllI) {
                return -1;
            }
            if (lllllllllllllllllllIIIlIIIIlIlll > lllllllllllllllllllIIIlIIIIlIllI) {
                return 1;
            }
            return 0;
        }
        
        public void setOpen(final boolean lllllllllllllllllllIIIlIIIIIlIll) {
            this.open = lllllllllllllllllllIIIlIIIIIlIll;
        }
        
        public int setParent(final Node lllllllllllllllllllIIIlIIIlIIIlI) {
            this.depth = lllllllllllllllllllIIIlIIIlIIIlI.depth + 1;
            this.parent = lllllllllllllllllllIIIlIIIlIIIlI;
            return this.depth;
        }
        
        public boolean isOpen() {
            return this.open;
        }
        
        public Node(final int lllllllllllllllllllIIIlIIIlIIlll, final int lllllllllllllllllllIIIlIIIlIlIlI) {
            this.x = lllllllllllllllllllIIIlIIIlIIlll;
            this.y = lllllllllllllllllllIIIlIIIlIlIlI;
        }
        
        public void setClosed(final boolean lllllllllllllllllllIIIlIIIIIIIlI) {
            this.closed = lllllllllllllllllllIIIlIIIIIIIlI;
        }
        
        public boolean isClosed() {
            return this.closed;
        }
        
        @Override
        public String toString() {
            return String.valueOf(new StringBuilder().append("[Node ").append(this.x).append(",").append(this.y).append("]"));
        }
        
        public void reset() {
            this.closed = false;
            this.open = false;
            this.cost = 0.0f;
            this.depth = 0;
        }
    }
    
    private class PriorityList
    {
        private /* synthetic */ List list;
        
        public void clear() {
            this.list.clear();
        }
        
        public int size() {
            return this.list.size();
        }
        
        public Object first() {
            return this.list.get(0);
        }
        
        public void add(final Object llllllllllllllllllIllIIlIlIIIIIl) {
            for (int llllllllllllllllllIllIIlIlIIIIll = 0; llllllllllllllllllIllIIlIlIIIIll < this.list.size(); ++llllllllllllllllllIllIIlIlIIIIll) {
                if (this.list.get(llllllllllllllllllIllIIlIlIIIIll).compareTo(llllllllllllllllllIllIIlIlIIIIIl) > 0) {
                    this.list.add(llllllllllllllllllIllIIlIlIIIIll, llllllllllllllllllIllIIlIlIIIIIl);
                    break;
                }
            }
            if (!this.list.contains(llllllllllllllllllIllIIlIlIIIIIl)) {
                this.list.add(llllllllllllllllllIllIIlIlIIIIIl);
            }
        }
        
        public boolean contains(final Object llllllllllllllllllIllIIlIIllIIIl) {
            return this.list.contains(llllllllllllllllllIllIIlIIllIIIl);
        }
        
        private PriorityList() {
            this.list = new LinkedList();
        }
        
        public void remove(final Object llllllllllllllllllIllIIlIIlllIII) {
            this.list.remove(llllllllllllllllllIllIIlIIlllIII);
        }
        
        @Override
        public String toString() {
            String llllllllllllllllllIllIIlIIlIlIIl = "{";
            for (int llllllllllllllllllIllIIlIIlIlIll = 0; llllllllllllllllllIllIIlIIlIlIll < this.size(); ++llllllllllllllllllIllIIlIIlIlIll) {
                llllllllllllllllllIllIIlIIlIlIIl = String.valueOf(new StringBuilder().append(llllllllllllllllllIllIIlIIlIlIIl).append(this.list.get(llllllllllllllllllIllIIlIIlIlIll).toString()).append(","));
            }
            llllllllllllllllllIllIIlIIlIlIIl = String.valueOf(new StringBuilder().append(llllllllllllllllllIllIIlIIlIlIIl).append("}"));
            return llllllllllllllllllIllIIlIIlIlIIl;
        }
    }
}
