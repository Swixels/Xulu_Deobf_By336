package org.newdawn.slick;

import java.net.*;
import java.io.*;
import org.newdawn.slick.opengl.*;

public class SpriteSheet extends Image
{
    private /* synthetic */ Image target;
    private /* synthetic */ int tw;
    private /* synthetic */ int spacing;
    private /* synthetic */ int th;
    private /* synthetic */ Image[][] subImages;
    private /* synthetic */ int margin;
    
    public SpriteSheet(final Image llllllllllllllllIllIllIIIlllIlll, final int llllllllllllllllIllIllIIIlllllII, final int llllllllllllllllIllIllIIIllllIll, final int llllllllllllllllIllIllIIIllllIlI, final int llllllllllllllllIllIllIIIlllIIll) {
        super(llllllllllllllllIllIllIIIlllIlll);
        this.margin = 0;
        this.target = llllllllllllllllIllIllIIIlllIlll;
        this.tw = llllllllllllllllIllIllIIIlllllII;
        this.th = llllllllllllllllIllIllIIIllllIll;
        this.spacing = llllllllllllllllIllIllIIIllllIlI;
        this.margin = llllllllllllllllIllIllIIIlllIIll;
        this.initImpl();
    }
    
    public SpriteSheet(final Image llllllllllllllllIllIllIIlIIIIlll, final int llllllllllllllllIllIllIIlIIIIllI, final int llllllllllllllllIllIllIIlIIIIlIl) {
        super(llllllllllllllllIllIllIIlIIIIlll);
        this.margin = 0;
        this.target = llllllllllllllllIllIllIIlIIIIlll;
        this.tw = llllllllllllllllIllIllIIlIIIIllI;
        this.th = llllllllllllllllIllIllIIlIIIIlIl;
        this.initImpl();
    }
    
    public SpriteSheet(final String llllllllllllllllIllIllIIIlIIlIll, final int llllllllllllllllIllIllIIIlIIlllI, final int llllllllllllllllIllIllIIIlIIllIl) throws SlickException {
        this(llllllllllllllllIllIllIIIlIIlIll, llllllllllllllllIllIllIIIlIIlllI, llllllllllllllllIllIllIIIlIIllIl, null);
    }
    
    public SpriteSheet(final String llllllllllllllllIllIllIIIlIlllIl, final int llllllllllllllllIllIllIIIlIlllII, final int llllllllllllllllIllIllIIIlIllIll, final int llllllllllllllllIllIllIIIlIllIlI) throws SlickException {
        this(llllllllllllllllIllIllIIIlIlllIl, llllllllllllllllIllIllIIIlIlllII, llllllllllllllllIllIllIIIlIllIll, null, llllllllllllllllIllIllIIIlIllIlI);
    }
    
    public Image getSubImage(final int llllllllllllllllIllIllIIIIIIIIlI, final int llllllllllllllllIllIllIIIIIIIlII) {
        this.init();
        if (llllllllllllllllIllIllIIIIIIIIlI < 0 || llllllllllllllllIllIllIIIIIIIIlI >= this.subImages.length) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("SubImage out of sheet bounds: ").append(llllllllllllllllIllIllIIIIIIIIlI).append(",").append(llllllllllllllllIllIllIIIIIIIlII)));
        }
        if (llllllllllllllllIllIllIIIIIIIlII < 0 || llllllllllllllllIllIllIIIIIIIlII >= this.subImages[0].length) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("SubImage out of sheet bounds: ").append(llllllllllllllllIllIllIIIIIIIIlI).append(",").append(llllllllllllllllIllIllIIIIIIIlII)));
        }
        return this.subImages[llllllllllllllllIllIllIIIIIIIIlI][llllllllllllllllIllIllIIIIIIIlII];
    }
    
    public SpriteSheet(final URL llllllllllllllllIllIllIIlIIlIIll, final int llllllllllllllllIllIllIIlIIlIIlI, final int llllllllllllllllIllIllIIlIIlIIIl) throws SlickException, IOException {
        this(new Image(llllllllllllllllIllIllIIlIIlIIll.openStream(), llllllllllllllllIllIllIIlIIlIIll.toString(), false), llllllllllllllllIllIllIIlIIlIIlI, llllllllllllllllIllIllIIlIIlIIIl);
    }
    
    public int getHorizontalCount() {
        this.target.init();
        this.initImpl();
        return this.subImages.length;
    }
    
    public SpriteSheet(final Image llllllllllllllllIllIllIIIllIllII, final int llllllllllllllllIllIllIIIllIIllI, final int llllllllllllllllIllIllIIIllIIlIl, final int llllllllllllllllIllIllIIIllIlIIl) {
        this(llllllllllllllllIllIllIIIllIllII, llllllllllllllllIllIllIIIllIIllI, llllllllllllllllIllIllIIIllIIlIl, llllllllllllllllIllIllIIIllIlIIl, 0);
    }
    
    @Override
    public void endUse() {
        if (this.target == this) {
            super.endUse();
            return;
        }
        this.target.endUse();
    }
    
    public Image getSprite(final int llllllllllllllllIllIlIlllllllIIl, final int llllllllllllllllIllIlIlllllllIII) {
        this.target.init();
        this.initImpl();
        if (llllllllllllllllIllIlIlllllllIIl < 0 || llllllllllllllllIllIlIlllllllIIl >= this.subImages.length) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("SubImage out of sheet bounds: ").append(llllllllllllllllIllIlIlllllllIIl).append(",").append(llllllllllllllllIllIlIlllllllIII)));
        }
        if (llllllllllllllllIllIlIlllllllIII < 0 || llllllllllllllllIllIlIlllllllIII >= this.subImages[0].length) {
            throw new RuntimeException(String.valueOf(new StringBuilder().append("SubImage out of sheet bounds: ").append(llllllllllllllllIllIlIlllllllIIl).append(",").append(llllllllllllllllIllIlIlllllllIII)));
        }
        return this.target.getSubImage(llllllllllllllllIllIlIlllllllIIl * (this.tw + this.spacing) + this.margin, llllllllllllllllIllIlIlllllllIII * (this.th + this.spacing) + this.margin, this.tw, this.th);
    }
    
    public void renderInUse(final int llllllllllllllllIllIlIlllllIIllI, final int llllllllllllllllIllIlIlllllIIlIl, final int llllllllllllllllIllIlIlllllIlIIl, final int llllllllllllllllIllIlIlllllIIIll) {
        this.subImages[llllllllllllllllIllIlIlllllIlIIl][llllllllllllllllIllIlIlllllIIIll].drawEmbedded((float)llllllllllllllllIllIlIlllllIIllI, (float)llllllllllllllllIllIlIlllllIIlIl, (float)this.tw, (float)this.th);
    }
    
    public SpriteSheet(final String llllllllllllllllIllIllIIIIllIIlI, final int llllllllllllllllIllIllIIIIllIIIl, final int llllllllllllllllIllIllIIIIllIIII, final Color llllllllllllllllIllIllIIIIlIllll, final int llllllllllllllllIllIllIIIIlIlllI) throws SlickException {
        super(llllllllllllllllIllIllIIIIllIIlI, false, 2, llllllllllllllllIllIllIIIIlIllll);
        this.margin = 0;
        this.target = this;
        this.tw = llllllllllllllllIllIllIIIIllIIIl;
        this.th = llllllllllllllllIllIllIIIIllIIII;
        this.spacing = llllllllllllllllIllIllIIIIlIlllI;
    }
    
    public SpriteSheet(final String llllllllllllllllIllIllIIIlIIIIlI, final int llllllllllllllllIllIllIIIlIIIIIl, final int llllllllllllllllIllIllIIIIlllIll, final Color llllllllllllllllIllIllIIIIlllIlI) throws SlickException {
        this(llllllllllllllllIllIllIIIlIIIIlI, llllllllllllllllIllIllIIIlIIIIIl, llllllllllllllllIllIllIIIIlllIll, llllllllllllllllIllIllIIIIlllIlI, 0);
    }
    
    public SpriteSheet(final String llllllllllllllllIllIllIIIIlIIIIl, final InputStream llllllllllllllllIllIllIIIIlIIIII, final int llllllllllllllllIllIllIIIIIlllll, final int llllllllllllllllIllIllIIIIIllIIl) throws SlickException {
        super(llllllllllllllllIllIllIIIIlIIIII, llllllllllllllllIllIllIIIIlIIIIl, false);
        this.margin = 0;
        this.target = this;
        this.tw = llllllllllllllllIllIllIIIIIlllll;
        this.th = llllllllllllllllIllIllIIIIIllIIl;
    }
    
    @Override
    public void startUse() {
        if (this.target == this) {
            super.startUse();
            return;
        }
        this.target.startUse();
    }
    
    public int getVerticalCount() {
        this.target.init();
        this.initImpl();
        return this.subImages[0].length;
    }
    
    @Override
    public void setTexture(final Texture llllllllllllllllIllIlIllllIllIIl) {
        if (this.target == this) {
            super.setTexture(llllllllllllllllIllIlIllllIllIIl);
            return;
        }
        this.target.setTexture(llllllllllllllllIllIlIllllIllIIl);
    }
    
    @Override
    protected void initImpl() {
        if (this.subImages != null) {
            return;
        }
        final int llllllllllllllllIllIllIIIIIlIIII = (this.getWidth() - this.margin * 2 - this.tw) / (this.tw + this.spacing) + 1;
        int llllllllllllllllIllIllIIIIIIllll = (this.getHeight() - this.margin * 2 - this.th) / (this.th + this.spacing) + 1;
        if ((this.getHeight() - this.th) % (this.th + this.spacing) != 0) {
            ++llllllllllllllllIllIllIIIIIIllll;
        }
        this.subImages = new Image[llllllllllllllllIllIllIIIIIlIIII][llllllllllllllllIllIllIIIIIIllll];
        for (int llllllllllllllllIllIllIIIIIlIIlI = 0; llllllllllllllllIllIllIIIIIlIIlI < llllllllllllllllIllIllIIIIIlIIII; ++llllllllllllllllIllIllIIIIIlIIlI) {
            for (int llllllllllllllllIllIllIIIIIlIIll = 0; llllllllllllllllIllIllIIIIIlIIll < llllllllllllllllIllIllIIIIIIllll; ++llllllllllllllllIllIllIIIIIlIIll) {
                this.subImages[llllllllllllllllIllIllIIIIIlIIlI][llllllllllllllllIllIllIIIIIlIIll] = this.getSprite(llllllllllllllllIllIllIIIIIlIIlI, llllllllllllllllIllIllIIIIIlIIll);
            }
        }
    }
}
