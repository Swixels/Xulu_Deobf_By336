package org.newdawn.slick.tests;

import java.util.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.opengl.renderer.*;
import org.newdawn.slick.*;

public class ShapeTest extends BasicGame
{
    private /* synthetic */ Polygon randomShape;
    private /* synthetic */ char[] lastChar;
    private /* synthetic */ Rectangle rect;
    private /* synthetic */ ArrayList shapes;
    private /* synthetic */ RoundedRectangle roundRect;
    private /* synthetic */ Ellipse ellipse;
    private /* synthetic */ Polygon polygon;
    private /* synthetic */ Circle circle;
    private /* synthetic */ boolean[] keys;
    
    @Override
    public void render(final GameContainer lIlIIIlllIIIIl, final Graphics lIlIIIlllIIIII) {
        lIlIIIlllIIIII.setColor(Color.green);
        for (int lIlIIIlllIIIll = 0; lIlIIIlllIIIll < this.shapes.size(); ++lIlIIIlllIIIll) {
            lIlIIIlllIIIII.fill(this.shapes.get(lIlIIIlllIIIll));
        }
        lIlIIIlllIIIII.fill(this.randomShape);
        lIlIIIlllIIIII.setColor(Color.black);
        lIlIIIlllIIIII.setAntiAlias(true);
        lIlIIIlllIIIII.draw(this.randomShape);
        lIlIIIlllIIIII.setAntiAlias(false);
        lIlIIIlllIIIII.setColor(Color.white);
        lIlIIIlllIIIII.drawString("keys", 10.0f, 300.0f);
        lIlIIIlllIIIII.drawString("wasd - move rectangle", 10.0f, 315.0f);
        lIlIIIlllIIIII.drawString("WASD - resize rectangle", 10.0f, 330.0f);
        lIlIIIlllIIIII.drawString("tgfh - move rounded rectangle", 10.0f, 345.0f);
        lIlIIIlllIIIII.drawString("TGFH - resize rounded rectangle", 10.0f, 360.0f);
        lIlIIIlllIIIII.drawString("ry - resize corner radius on rounded rectangle", 10.0f, 375.0f);
        lIlIIIlllIIIII.drawString("ikjl - move ellipse", 10.0f, 390.0f);
        lIlIIIlllIIIII.drawString("IKJL - resize ellipse", 10.0f, 405.0f);
        lIlIIIlllIIIII.drawString("Arrows - move circle", 10.0f, 420.0f);
        lIlIIIlllIIIII.drawString("Page Up/Page Down - resize circle", 10.0f, 435.0f);
        lIlIIIlllIIIII.drawString("numpad 8546 - move polygon", 10.0f, 450.0f);
    }
    
    public void createPoly(final float lIlIIIllllIIll, final float lIlIIIlllIllIl) {
        final int lIlIIIllllIIIl = 20;
        final int lIlIIIllllIIII = 10;
        this.randomShape = new Polygon();
        this.randomShape.addPoint((float)(0 + (int)(Math.random() * lIlIIIllllIIII)), (float)(0 + (int)(Math.random() * lIlIIIllllIIII)));
        this.randomShape.addPoint((float)(lIlIIIllllIIIl - (int)(Math.random() * lIlIIIllllIIII)), (float)(0 + (int)(Math.random() * lIlIIIllllIIII)));
        this.randomShape.addPoint((float)(lIlIIIllllIIIl - (int)(Math.random() * lIlIIIllllIIII)), (float)(lIlIIIllllIIIl - (int)(Math.random() * lIlIIIllllIIII)));
        this.randomShape.addPoint((float)(0 + (int)(Math.random() * lIlIIIllllIIII)), (float)(lIlIIIllllIIIl - (int)(Math.random() * lIlIIIllllIIII)));
        this.randomShape.setCenterX(lIlIIIllllIIll);
        this.randomShape.setCenterY(lIlIIIlllIllIl);
    }
    
    @Override
    public void keyPressed(final int lIlIIIllIlIIII, final char lIlIIIllIIllll) {
        this.keys[lIlIIIllIlIIII] = true;
        this.lastChar[lIlIIIllIlIIII] = lIlIIIllIIllll;
    }
    
    @Override
    public void init(final GameContainer lIlIIIlllIlIII) throws SlickException {
        this.shapes = new ArrayList();
        this.rect = new Rectangle(10.0f, 10.0f, 100.0f, 80.0f);
        this.shapes.add(this.rect);
        this.roundRect = new RoundedRectangle(150.0f, 10.0f, 60.0f, 80.0f, 20.0f);
        this.shapes.add(this.roundRect);
        this.ellipse = new Ellipse(350.0f, 40.0f, 50.0f, 30.0f);
        this.shapes.add(this.ellipse);
        this.circle = new Circle(470.0f, 60.0f, 50.0f);
        this.shapes.add(this.circle);
        this.polygon = new Polygon(new float[] { 550.0f, 10.0f, 600.0f, 40.0f, 620.0f, 100.0f, 570.0f, 130.0f });
        this.shapes.add(this.polygon);
        this.keys = new boolean[256];
        this.lastChar = new char[256];
        this.createPoly(200.0f, 200.0f);
    }
    
    public static void main(final String[] lIlIIIllIIIlII) {
        try {
            Renderer.setRenderer(2);
            final AppGameContainer lIlIIIllIIIllI = new AppGameContainer(new ShapeTest());
            lIlIIIllIIIllI.setDisplayMode(800, 600, false);
            lIlIIIllIIIllI.start();
        }
        catch (SlickException lIlIIIllIIIlIl) {
            lIlIIIllIIIlIl.printStackTrace();
        }
    }
    
    @Override
    public void update(final GameContainer lIlIIIllIllIlI, final int lIlIIIllIllIIl) {
        this.createPoly(200.0f, 200.0f);
        if (this.keys[1]) {
            System.exit(0);
        }
        if (this.keys[17]) {
            if (this.lastChar[17] == 'w') {
                this.rect.setY(this.rect.getY() - 1.0f);
            }
            else {
                this.rect.setHeight(this.rect.getHeight() - 1.0f);
            }
        }
        if (this.keys[31]) {
            if (this.lastChar[31] == 's') {
                this.rect.setY(this.rect.getY() + 1.0f);
            }
            else {
                this.rect.setHeight(this.rect.getHeight() + 1.0f);
            }
        }
        if (this.keys[30]) {
            if (this.lastChar[30] == 'a') {
                this.rect.setX(this.rect.getX() - 1.0f);
            }
            else {
                this.rect.setWidth(this.rect.getWidth() - 1.0f);
            }
        }
        if (this.keys[32]) {
            if (this.lastChar[32] == 'd') {
                this.rect.setX(this.rect.getX() + 1.0f);
            }
            else {
                this.rect.setWidth(this.rect.getWidth() + 1.0f);
            }
        }
        if (this.keys[20]) {
            if (this.lastChar[20] == 't') {
                this.roundRect.setY(this.roundRect.getY() - 1.0f);
            }
            else {
                this.roundRect.setHeight(this.roundRect.getHeight() - 1.0f);
            }
        }
        if (this.keys[34]) {
            if (this.lastChar[34] == 'g') {
                this.roundRect.setY(this.roundRect.getY() + 1.0f);
            }
            else {
                this.roundRect.setHeight(this.roundRect.getHeight() + 1.0f);
            }
        }
        if (this.keys[33]) {
            if (this.lastChar[33] == 'f') {
                this.roundRect.setX(this.roundRect.getX() - 1.0f);
            }
            else {
                this.roundRect.setWidth(this.roundRect.getWidth() - 1.0f);
            }
        }
        if (this.keys[35]) {
            if (this.lastChar[35] == 'h') {
                this.roundRect.setX(this.roundRect.getX() + 1.0f);
            }
            else {
                this.roundRect.setWidth(this.roundRect.getWidth() + 1.0f);
            }
        }
        if (this.keys[19]) {
            this.roundRect.setCornerRadius(this.roundRect.getCornerRadius() - 1.0f);
        }
        if (this.keys[21]) {
            this.roundRect.setCornerRadius(this.roundRect.getCornerRadius() + 1.0f);
        }
        if (this.keys[23]) {
            if (this.lastChar[23] == 'i') {
                this.ellipse.setCenterY(this.ellipse.getCenterY() - 1.0f);
            }
            else {
                this.ellipse.setRadius2(this.ellipse.getRadius2() - 1.0f);
            }
        }
        if (this.keys[37]) {
            if (this.lastChar[37] == 'k') {
                this.ellipse.setCenterY(this.ellipse.getCenterY() + 1.0f);
            }
            else {
                this.ellipse.setRadius2(this.ellipse.getRadius2() + 1.0f);
            }
        }
        if (this.keys[36]) {
            if (this.lastChar[36] == 'j') {
                this.ellipse.setCenterX(this.ellipse.getCenterX() - 1.0f);
            }
            else {
                this.ellipse.setRadius1(this.ellipse.getRadius1() - 1.0f);
            }
        }
        if (this.keys[38]) {
            if (this.lastChar[38] == 'l') {
                this.ellipse.setCenterX(this.ellipse.getCenterX() + 1.0f);
            }
            else {
                this.ellipse.setRadius1(this.ellipse.getRadius1() + 1.0f);
            }
        }
        if (this.keys[200]) {
            this.circle.setCenterY(this.circle.getCenterY() - 1.0f);
        }
        if (this.keys[208]) {
            this.circle.setCenterY(this.circle.getCenterY() + 1.0f);
        }
        if (this.keys[203]) {
            this.circle.setCenterX(this.circle.getCenterX() - 1.0f);
        }
        if (this.keys[205]) {
            this.circle.setCenterX(this.circle.getCenterX() + 1.0f);
        }
        if (this.keys[201]) {
            this.circle.setRadius(this.circle.getRadius() - 1.0f);
        }
        if (this.keys[209]) {
            this.circle.setRadius(this.circle.getRadius() + 1.0f);
        }
        if (this.keys[72]) {
            this.polygon.setY(this.polygon.getY() - 1.0f);
        }
        if (this.keys[76]) {
            this.polygon.setY(this.polygon.getY() + 1.0f);
        }
        if (this.keys[75]) {
            this.polygon.setX(this.polygon.getX() - 1.0f);
        }
        if (this.keys[77]) {
            this.polygon.setX(this.polygon.getX() + 1.0f);
        }
    }
    
    public ShapeTest() {
        super("Geom Test");
        this.randomShape = new Polygon();
    }
    
    @Override
    public void keyReleased(final int lIlIIIllIIlIII, final char lIlIIIllIIlIlI) {
        this.keys[lIlIIIllIIlIII] = false;
    }
}
