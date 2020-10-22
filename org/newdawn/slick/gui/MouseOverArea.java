package org.newdawn.slick.gui;

import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;

public class MouseOverArea extends AbstractComponent
{
    private /* synthetic */ Color mouseDownColor;
    private /* synthetic */ Image normalImage;
    private /* synthetic */ Sound mouseDownSound;
    private /* synthetic */ boolean mouseUp;
    private /* synthetic */ Color currentColor;
    private /* synthetic */ boolean mouseDown;
    private /* synthetic */ boolean over;
    private /* synthetic */ Color normalColor;
    private /* synthetic */ int state;
    private /* synthetic */ Image mouseDownImage;
    private /* synthetic */ Image currentImage;
    private /* synthetic */ Image mouseOverImage;
    private /* synthetic */ Sound mouseOverSound;
    private /* synthetic */ Shape area;
    private /* synthetic */ Color mouseOverColor;
    
    public void setMouseDownImage(final Image lIIlllIlIllIIIl) {
        this.mouseDownImage = lIIlllIlIllIIIl;
    }
    
    @Override
    public void setLocation(final int lIIlllIIlIlIlIl, final int lIIlllIIlIlIIIl) {
        this.setLocation((float)lIIlllIIlIlIlIl, (float)lIIlllIIlIlIIIl);
    }
    
    public void setMouseOverImage(final Image lIIlllIlIllIlll) {
        this.mouseOverImage = lIIlllIlIllIlll;
    }
    
    public void setMouseOverSound(final Sound lIIlllIlIIlllIl) {
        this.mouseOverSound = lIIlllIlIIlllIl;
    }
    
    static {
        MOUSE_DOWN = 2;
        MOUSE_OVER = 3;
        NORMAL = 1;
    }
    
    @Override
    public void mouseMoved(final int lIIlllIlIIlIIII, final int lIIlllIlIIIllll, final int lIIlllIlIIIlllI, final int lIIlllIlIIIlIlI) {
        this.over = this.area.contains((float)lIIlllIlIIIlllI, (float)lIIlllIlIIIlIlI);
    }
    
    @Override
    public int getX() {
        return (int)this.area.getX();
    }
    
    @Override
    public int getWidth() {
        return (int)(this.area.getMaxX() - this.area.getX());
    }
    
    @Override
    public void mouseDragged(final int lIIlllIIllllllI, final int lIIlllIlIIIIIlI, final int lIIlllIIlllllII, final int lIIlllIIllllIll) {
        this.mouseMoved(lIIlllIIllllllI, lIIlllIlIIIIIlI, lIIlllIIlllllII, lIIlllIIllllIll);
    }
    
    @Override
    public int getHeight() {
        return (int)(this.area.getMaxY() - this.area.getY());
    }
    
    public MouseOverArea(final GUIContext lIIllllIIIIlIll, final Image lIIllllIIIIIIll, final int lIIllllIIIIlIIl, final int lIIllllIIIIlIII, final int lIIllllIIIIIlll, final int lIIlllIllllllll) {
        this(lIIllllIIIIlIll, lIIllllIIIIIIll, new Rectangle((float)lIIllllIIIIlIIl, (float)lIIllllIIIIlIII, (float)lIIllllIIIIIlll, (float)lIIlllIllllllll));
    }
    
    public MouseOverArea(final GUIContext lIIllllIlIIIlIl, final Image lIIllllIlIIIlII, final int lIIllllIIllllIl, final int lIIllllIIllllII, final ComponentListener lIIllllIlIIIIIl) {
        this(lIIllllIlIIIlIl, lIIllllIlIIIlII, lIIllllIIllllIl, lIIllllIIllllII, lIIllllIlIIIlII.getWidth(), lIIllllIlIIIlII.getHeight());
        this.addListener(lIIllllIlIIIIIl);
    }
    
    public void setMouseDownSound(final Sound lIIlllIlIIlIlIl) {
        this.mouseDownSound = lIIlllIlIIlIlIl;
    }
    
    public void setNormalImage(final Image lIIlllIlIllllIl) {
        this.normalImage = lIIlllIlIllllIl;
    }
    
    public void setMouseOverColor(final Color lIIlllIllIIlIIl) {
        this.mouseOverColor = lIIlllIllIIlIIl;
    }
    
    @Override
    public int getY() {
        return (int)this.area.getY();
    }
    
    private void updateImage() {
        if (!this.over) {
            this.currentImage = this.normalImage;
            this.currentColor = this.normalColor;
            this.state = 1;
            this.mouseUp = false;
        }
        else {
            if (this.mouseDown) {
                if (this.state != 2 && this.mouseUp) {
                    if (this.mouseDownSound != null) {
                        this.mouseDownSound.play();
                    }
                    this.currentImage = this.mouseDownImage;
                    this.currentColor = this.mouseDownColor;
                    this.state = 2;
                    this.notifyListeners();
                    this.mouseUp = false;
                }
                return;
            }
            this.mouseUp = true;
            if (this.state != 3) {
                if (this.mouseOverSound != null) {
                    this.mouseOverSound.play();
                }
                this.currentImage = this.mouseOverImage;
                this.currentColor = this.mouseOverColor;
                this.state = 3;
            }
        }
        this.mouseDown = false;
        this.state = 1;
    }
    
    @Override
    public void mousePressed(final int lIIlllIIlllIlIl, final int lIIlllIIlllIIII, final int lIIlllIIllIllll) {
        this.over = this.area.contains((float)lIIlllIIlllIIII, (float)lIIlllIIllIllll);
        if (lIIlllIIlllIlIl == 0) {
            this.mouseDown = true;
        }
    }
    
    @Override
    public void render(final GUIContext lIIlllIlIlIlIIl, final Graphics lIIlllIlIlIlIII) {
        if (this.currentImage != null) {
            final int lIIlllIlIlIllII = (int)(this.area.getX() + (this.getWidth() - this.currentImage.getWidth()) / 2);
            final int lIIlllIlIlIlIll = (int)(this.area.getY() + (this.getHeight() - this.currentImage.getHeight()) / 2);
            this.currentImage.draw((float)lIIlllIlIlIllII, (float)lIIlllIlIlIlIll, this.currentColor);
        }
        else {
            lIIlllIlIlIlIII.setColor(this.currentColor);
            lIIlllIlIlIlIII.fill(this.area);
        }
        this.updateImage();
    }
    
    public void setNormalColor(final Color lIIlllIllIIllll) {
        this.normalColor = lIIlllIllIIllll;
    }
    
    public boolean isMouseOver() {
        return this.over;
    }
    
    public void setX(final float lIIlllIlllIIIll) {
        this.area.setX(lIIlllIlllIIIll);
    }
    
    public void setY(final float lIIlllIllIlllIl) {
        this.area.setY(lIIlllIllIlllIl);
    }
    
    public MouseOverArea(final GUIContext lIIllllIIIllIlI, final Image lIIllllIIlIIIIl, final int lIIllllIIIllIII, final int lIIllllIIIlllll, final int lIIllllIIIllllI, final int lIIllllIIIlIlIl, final ComponentListener lIIllllIIIlllII) {
        this(lIIllllIIIllIlI, lIIllllIIlIIIIl, lIIllllIIIllIII, lIIllllIIIlllll, lIIllllIIIllllI, lIIllllIIIlIlIl);
        this.addListener(lIIllllIIIlllII);
    }
    
    public void setMouseDownColor(final Color lIIlllIllIIIlIl) {
        this.mouseDownColor = lIIlllIllIIIlIl;
    }
    
    public MouseOverArea(final GUIContext lIIllllIIllIlII, final Image lIIllllIIlIlllI, final int lIIllllIIlIllIl, final int lIIllllIIlIllII) {
        this(lIIllllIIllIlII, lIIllllIIlIlllI, lIIllllIIlIllIl, lIIllllIIlIllII, lIIllllIIlIlllI.getWidth(), lIIllllIIlIlllI.getHeight());
    }
    
    public MouseOverArea(final GUIContext lIIlllIllllIIll, final Image lIIlllIllllIIlI, final Shape lIIlllIllllIllI) {
        super(lIIlllIllllIIll);
        this.normalColor = Color.white;
        this.mouseOverColor = Color.white;
        this.mouseDownColor = Color.white;
        this.state = 1;
        this.area = lIIlllIllllIllI;
        this.normalImage = lIIlllIllllIIlI;
        this.currentImage = lIIlllIllllIIlI;
        this.mouseOverImage = lIIlllIllllIIlI;
        this.mouseDownImage = lIIlllIllllIIlI;
        this.currentColor = this.normalColor;
        this.state = 1;
        final Input lIIlllIllllIlIl = lIIlllIllllIIll.getInput();
        this.over = this.area.contains((float)lIIlllIllllIlIl.getMouseX(), (float)lIIlllIllllIlIl.getMouseY());
        this.mouseDown = lIIlllIllllIlIl.isMouseButtonDown(0);
        this.updateImage();
    }
    
    @Override
    public void mouseReleased(final int lIIlllIIllIlIIl, final int lIIlllIIllIIlII, final int lIIlllIIllIIIll) {
        this.over = this.area.contains((float)lIIlllIIllIIlII, (float)lIIlllIIllIIIll);
        if (lIIlllIIllIlIIl == 0) {
            this.mouseDown = false;
        }
    }
    
    public void setLocation(final float lIIlllIlllIlIII, final float lIIlllIlllIlIlI) {
        if (this.area != null) {
            this.area.setX(lIIlllIlllIlIII);
            this.area.setY(lIIlllIlllIlIlI);
        }
    }
}
