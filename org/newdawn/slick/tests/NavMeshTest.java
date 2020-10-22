package org.newdawn.slick.tests;

import org.newdawn.slick.util.pathfinding.navmesh.*;
import org.newdawn.slick.*;
import java.io.*;
import org.newdawn.slick.util.pathfinding.*;
import org.newdawn.slick.util.*;

public class NavMeshTest extends BasicGame implements PathFindingContext
{
    private /* synthetic */ float ey;
    private /* synthetic */ NavPath path;
    private /* synthetic */ DataMap dataMap;
    private /* synthetic */ boolean showSpaces;
    private /* synthetic */ NavMeshBuilder builder;
    private /* synthetic */ NavMesh navMesh;
    private /* synthetic */ float sy;
    private /* synthetic */ float ex;
    private /* synthetic */ float sx;
    private /* synthetic */ boolean showLinks;
    
    public NavMeshTest() {
        super("Nav-mesh Test");
        this.showSpaces = true;
        this.showLinks = true;
    }
    
    @Override
    public int getSourceY() {
        return 0;
    }
    
    public static void main(final String[] llllllllllllllllIlllIIIIlIIlIIII) {
        Bootstrap.runAsApplication(new NavMeshTest(), 600, 600, false);
    }
    
    @Override
    public int getSourceX() {
        return 0;
    }
    
    @Override
    public void render(final GameContainer llllllllllllllllIlllIIIIlIlIllll, final Graphics llllllllllllllllIlllIIIIlIlIllII) throws SlickException {
        llllllllllllllllIlllIIIIlIlIllII.translate(50.0f, 50.0f);
        for (int llllllllllllllllIlllIIIIlIllIlll = 0; llllllllllllllllIlllIIIIlIllIlll < 50; ++llllllllllllllllIlllIIIIlIllIlll) {
            for (int llllllllllllllllIlllIIIIlIlllIII = 0; llllllllllllllllIlllIIIIlIlllIII < 50; ++llllllllllllllllIlllIIIIlIlllIII) {
                if (this.dataMap.blocked(this, llllllllllllllllIlllIIIIlIllIlll, llllllllllllllllIlllIIIIlIlllIII)) {
                    llllllllllllllllIlllIIIIlIlIllII.setColor(Color.gray);
                    llllllllllllllllIlllIIIIlIlIllII.fillRect((float)(llllllllllllllllIlllIIIIlIllIlll * 10 + 1), (float)(llllllllllllllllIlllIIIIlIlllIII * 10 + 1), 8.0f, 8.0f);
                }
            }
        }
        if (this.showSpaces) {
            for (int llllllllllllllllIlllIIIIlIllIIlI = 0; llllllllllllllllIlllIIIIlIllIIlI < this.navMesh.getSpaceCount(); ++llllllllllllllllIlllIIIIlIllIIlI) {
                final Space llllllllllllllllIlllIIIIlIllIIll = this.navMesh.getSpace(llllllllllllllllIlllIIIIlIllIIlI);
                if (this.builder.clear(this.dataMap, llllllllllllllllIlllIIIIlIllIIll)) {
                    llllllllllllllllIlllIIIIlIlIllII.setColor(new Color(1.0f, 1.0f, 0.0f, 0.5f));
                    llllllllllllllllIlllIIIIlIlIllII.fillRect(llllllllllllllllIlllIIIIlIllIIll.getX() * 10.0f, llllllllllllllllIlllIIIIlIllIIll.getY() * 10.0f, llllllllllllllllIlllIIIIlIllIIll.getWidth() * 10.0f, llllllllllllllllIlllIIIIlIllIIll.getHeight() * 10.0f);
                }
                llllllllllllllllIlllIIIIlIlIllII.setColor(Color.yellow);
                llllllllllllllllIlllIIIIlIlIllII.drawRect(llllllllllllllllIlllIIIIlIllIIll.getX() * 10.0f, llllllllllllllllIlllIIIIlIllIIll.getY() * 10.0f, llllllllllllllllIlllIIIIlIllIIll.getWidth() * 10.0f, llllllllllllllllIlllIIIIlIllIIll.getHeight() * 10.0f);
                if (this.showLinks) {
                    for (int llllllllllllllllIlllIIIIlIllIlII = llllllllllllllllIlllIIIIlIllIIll.getLinkCount(), llllllllllllllllIlllIIIIlIllIlIl = 0; llllllllllllllllIlllIIIIlIllIlIl < llllllllllllllllIlllIIIIlIllIlII; ++llllllllllllllllIlllIIIIlIllIlIl) {
                        final Link llllllllllllllllIlllIIIIlIllIllI = llllllllllllllllIlllIIIIlIllIIll.getLink(llllllllllllllllIlllIIIIlIllIlIl);
                        llllllllllllllllIlllIIIIlIlIllII.setColor(Color.red);
                        llllllllllllllllIlllIIIIlIlIllII.fillRect(llllllllllllllllIlllIIIIlIllIllI.getX() * 10.0f - 2.0f, llllllllllllllllIlllIIIIlIllIllI.getY() * 10.0f - 2.0f, 5.0f, 5.0f);
                    }
                }
            }
        }
        if (this.path != null) {
            llllllllllllllllIlllIIIIlIlIllII.setColor(Color.white);
            for (int llllllllllllllllIlllIIIIlIllIIIl = 0; llllllllllllllllIlllIIIIlIllIIIl < this.path.length() - 1; ++llllllllllllllllIlllIIIIlIllIIIl) {
                llllllllllllllllIlllIIIIlIlIllII.drawLine(this.path.getX(llllllllllllllllIlllIIIIlIllIIIl) * 10.0f, this.path.getY(llllllllllllllllIlllIIIIlIllIIIl) * 10.0f, this.path.getX(llllllllllllllllIlllIIIIlIllIIIl + 1) * 10.0f, this.path.getY(llllllllllllllllIlllIIIIlIllIIIl + 1) * 10.0f);
            }
        }
    }
    
    @Override
    public void mousePressed(final int llllllllllllllllIlllIIIIlIIllIll, final int llllllllllllllllIlllIIIIlIIlIlII, final int llllllllllllllllIlllIIIIlIIlIIll) {
        final float llllllllllllllllIlllIIIIlIIllIII = (llllllllllllllllIlllIIIIlIIlIlII - 50) / 10.0f;
        final float llllllllllllllllIlllIIIIlIIlIlll = (llllllllllllllllIlllIIIIlIIlIIll - 50) / 10.0f;
        if (llllllllllllllllIlllIIIIlIIllIll == 0) {
            this.sx = llllllllllllllllIlllIIIIlIIllIII;
            this.sy = llllllllllllllllIlllIIIIlIIlIlll;
        }
        else {
            this.ex = llllllllllllllllIlllIIIIlIIllIII;
            this.ey = llllllllllllllllIlllIIIIlIIlIlll;
        }
        this.path = this.navMesh.findPath(this.sx, this.sy, this.ex, this.ey, true);
    }
    
    @Override
    public void update(final GameContainer llllllllllllllllIlllIIIIllIIIIll, final int llllllllllllllllIlllIIIIllIIIIlI) throws SlickException {
        if (llllllllllllllllIlllIIIIllIIIIll.getInput().isKeyPressed(2)) {
            this.showLinks = !this.showLinks;
        }
        if (llllllllllllllllIlllIIIIllIIIIll.getInput().isKeyPressed(3)) {
            this.showSpaces = !this.showSpaces;
        }
    }
    
    @Override
    public void init(final GameContainer llllllllllllllllIlllIIIIllIIlIlI) throws SlickException {
        llllllllllllllllIlllIIIIllIIlIlI.setShowFPS(false);
        try {
            this.dataMap = new DataMap("testdata/map.dat");
        }
        catch (IOException llllllllllllllllIlllIIIIllIIllII) {
            throw new SlickException("Failed to load map data", llllllllllllllllIlllIIIIllIIllII);
        }
        this.builder = new NavMeshBuilder();
        this.navMesh = this.builder.build(this.dataMap);
        System.out.println(String.valueOf(new StringBuilder().append("Navmesh shapes: ").append(this.navMesh.getSpaceCount())));
    }
    
    @Override
    public int getSearchDistance() {
        return 0;
    }
    
    @Override
    public Mover getMover() {
        return null;
    }
    
    private class DataMap implements TileBasedMap
    {
        private /* synthetic */ byte[] map;
        
        @Override
        public void pathFinderVisited(final int llllllllllllllllIlIllIIIIIIIlllI, final int llllllllllllllllIlIllIIIIIIIllII) {
        }
        
        public DataMap(final String llllllllllllllllIlIllIIIIIllIIlI) throws IOException {
            this.map = new byte[2500];
            ResourceLoader.getResourceAsStream(llllllllllllllllIlIllIIIIIllIIlI).read(this.map);
        }
        
        @Override
        public boolean blocked(final PathFindingContext llllllllllllllllIlIllIIIIIlIIIlI, final int llllllllllllllllIlIllIIIIIlIIIIl, final int llllllllllllllllIlIllIIIIIlIIIII) {
            return llllllllllllllllIlIllIIIIIlIIIIl >= 0 && llllllllllllllllIlIllIIIIIlIIIII >= 0 && llllllllllllllllIlIllIIIIIlIIIIl < 50 && llllllllllllllllIlIllIIIIIlIIIII < 50 && this.map[llllllllllllllllIlIllIIIIIlIIIIl + llllllllllllllllIlIllIIIIIlIIIII * 50] != 0;
        }
        
        @Override
        public int getWidthInTiles() {
            return 50;
        }
        
        @Override
        public int getHeightInTiles() {
            return 50;
        }
        
        @Override
        public float getCost(final PathFindingContext llllllllllllllllIlIllIIIIIIllIIl, final int llllllllllllllllIlIllIIIIIIllIII, final int llllllllllllllllIlIllIIIIIIlIllI) {
            return 1.0f;
        }
    }
}
