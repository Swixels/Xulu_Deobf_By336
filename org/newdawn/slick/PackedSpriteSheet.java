package org.newdawn.slick;

import java.util.*;
import org.newdawn.slick.util.*;
import java.io.*;

public class PackedSpriteSheet
{
    private /* synthetic */ int filter;
    private /* synthetic */ HashMap sections;
    private /* synthetic */ Image image;
    private /* synthetic */ String basePath;
    
    private void loadDefinition(final String lIlIlIlllIIlIlI, final Color lIlIlIlllIIllIl) throws SlickException {
        final BufferedReader lIlIlIlllIIllII = new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsStream(lIlIlIlllIIlIlI)));
        try {
            this.image = new Image(String.valueOf(new StringBuilder().append(this.basePath).append(lIlIlIlllIIllII.readLine())), false, this.filter, lIlIlIlllIIllIl);
            while (lIlIlIlllIIllII.ready()) {
                if (lIlIlIlllIIllII.readLine() == null) {
                    break;
                }
                final Section lIlIlIlllIlIIIl = new Section(lIlIlIlllIIllII);
                this.sections.put(lIlIlIlllIlIIIl.name, lIlIlIlllIlIIIl);
                if (lIlIlIlllIIllII.readLine() == null) {
                    break;
                }
            }
        }
        catch (Exception lIlIlIlllIlIIII) {
            Log.error(lIlIlIlllIlIIII);
            throw new SlickException("Failed to process definitions file - invalid format?", lIlIlIlllIlIIII);
        }
    }
    
    public PackedSpriteSheet(final String lIlIllIIIIIllIl) throws SlickException {
        this(lIlIllIIIIIllIl, null);
    }
    
    public PackedSpriteSheet(final String lIlIlIlllllllll, final int lIlIlIllllllIll) throws SlickException {
        this(lIlIlIlllllllll, lIlIlIllllllIll, null);
    }
    
    public PackedSpriteSheet(String lIlIlIlllllIIIl, final int lIlIlIlllllIlII, final Color lIlIlIllllIllll) throws SlickException {
        this.sections = new HashMap();
        this.filter = 2;
        this.filter = lIlIlIlllllIlII;
        lIlIlIlllllIIIl = (Exception)((String)lIlIlIlllllIIIl).replace('\\', '/');
        this.basePath = ((String)lIlIlIlllllIIIl).substring(0, ((String)lIlIlIlllllIIIl).lastIndexOf("/") + 1);
        this.loadDefinition((String)lIlIlIlllllIIIl, lIlIlIllllIllll);
    }
    
    public SpriteSheet getSpriteSheet(final String lIlIlIlllIlllIl) {
        final Image lIlIlIlllIlllII = this.getSprite(lIlIlIlllIlllIl);
        final Section lIlIlIlllIllIll = this.sections.get(lIlIlIlllIlllIl);
        return new SpriteSheet(lIlIlIlllIlllII, lIlIlIlllIllIll.width / lIlIlIlllIllIll.tilesx, lIlIlIlllIllIll.height / lIlIlIlllIllIll.tilesy);
    }
    
    public Image getSprite(final String lIlIlIllllIIlll) {
        final Section lIlIlIllllIIllI = this.sections.get(lIlIlIllllIIlll);
        if (lIlIlIllllIIllI == null) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("Unknown sprite from packed sheet: ").append(lIlIlIllllIIlll)));
        }
        return this.image.getSubImage(lIlIlIllllIIllI.x, lIlIlIllllIIllI.y, lIlIlIllllIIllI.width, lIlIlIllllIIllI.height);
    }
    
    public Image getFullImage() {
        return this.image;
    }
    
    public PackedSpriteSheet(String lIlIllIIIIIIlIl, final Color lIlIllIIIIIIlll) throws SlickException {
        this.sections = new HashMap();
        this.filter = 2;
        lIlIllIIIIIIlIl = (short)((String)lIlIllIIIIIIlIl).replace('\\', '/');
        this.basePath = ((String)lIlIllIIIIIIlIl).substring(0, ((String)lIlIllIIIIIIlIl).lastIndexOf("/") + 1);
        this.loadDefinition((String)lIlIllIIIIIIlIl, lIlIllIIIIIIlll);
    }
    
    private class Section
    {
        public /* synthetic */ int y;
        public /* synthetic */ int tilesx;
        public /* synthetic */ int tilesy;
        public /* synthetic */ int width;
        public /* synthetic */ int x;
        public /* synthetic */ int height;
        public /* synthetic */ String name;
        
        public Section(final BufferedReader lllllllllllllllllllllIlllllIIIll) throws IOException {
            this.name = lllllllllllllllllllllIlllllIIIll.readLine().trim();
            this.x = Integer.parseInt(lllllllllllllllllllllIlllllIIIll.readLine().trim());
            this.y = Integer.parseInt(lllllllllllllllllllllIlllllIIIll.readLine().trim());
            this.width = Integer.parseInt(lllllllllllllllllllllIlllllIIIll.readLine().trim());
            this.height = Integer.parseInt(lllllllllllllllllllllIlllllIIIll.readLine().trim());
            this.tilesx = Integer.parseInt(lllllllllllllllllllllIlllllIIIll.readLine().trim());
            this.tilesy = Integer.parseInt(lllllllllllllllllllllIlllllIIIll.readLine().trim());
            lllllllllllllllllllllIlllllIIIll.readLine().trim();
            lllllllllllllllllllllIlllllIIIll.readLine().trim();
            this.tilesx = Math.max(1, this.tilesx);
            this.tilesy = Math.max(1, this.tilesy);
        }
    }
}
