package org.newdawn.slick.tests;

import java.util.*;
import java.awt.*;
import org.newdawn.slick.*;

public class TrueTypeFontPerformanceTest extends BasicGame
{
    private /* synthetic */ ArrayList lines;
    private /* synthetic */ Font awtFont;
    private /* synthetic */ TrueTypeFont font;
    private /* synthetic */ boolean visible;
    private /* synthetic */ String text;
    
    @Override
    public void render(final GameContainer llllllllllllllllllIIIllIlIlIIIIl, final Graphics llllllllllllllllllIIIllIlIIllllI) {
        llllllllllllllllllIIIllIlIIllllI.setFont(this.font);
        if (this.visible) {
            for (int llllllllllllllllllIIIllIlIlIIIll = 0; llllllllllllllllllIIIllIlIlIIIll < this.lines.size(); ++llllllllllllllllllIIIllIlIlIIIll) {
                this.font.drawString(10.0f, (float)(50 + llllllllllllllllllIIIllIlIlIIIll * 20), this.lines.get(llllllllllllllllllIIIllIlIlIIIll), (llllllllllllllllllIIIllIlIlIIIll > 10) ? Color.red : Color.green);
            }
        }
    }
    
    @Override
    public void init(final GameContainer llllllllllllllllllIIIllIlIlIlIll) throws SlickException {
        this.awtFont = new Font("Verdana", 0, 16);
        this.font = new TrueTypeFont(this.awtFont, false);
        for (int llllllllllllllllllIIIllIlIlIllIl = 0; llllllllllllllllllIIIllIlIlIllIl < 2; ++llllllllllllllllllIIIllIlIlIllIl) {
            for (int llllllllllllllllllIIIllIlIlIlllI = 90, llllllllllllllllllIIIllIlIlIllll = 0; llllllllllllllllllIIIllIlIlIllll < this.text.length(); llllllllllllllllllIIIllIlIlIllll += llllllllllllllllllIIIllIlIlIlllI) {
                if (llllllllllllllllllIIIllIlIlIllll + llllllllllllllllllIIIllIlIlIlllI > this.text.length()) {
                    llllllllllllllllllIIIllIlIlIlllI = this.text.length() - llllllllllllllllllIIIllIlIlIllll;
                }
                this.lines.add(this.text.substring(llllllllllllllllllIIIllIlIlIllll, llllllllllllllllllIIIllIlIlIllll + llllllllllllllllllIIIllIlIlIlllI));
            }
            this.lines.add("");
        }
    }
    
    public static void main(final String[] llllllllllllllllllIIIllIlIIIllll) {
        try {
            final AppGameContainer llllllllllllllllllIIIllIlIIlIIIl = new AppGameContainer(new TrueTypeFontPerformanceTest());
            llllllllllllllllllIIIllIlIIlIIIl.setDisplayMode(800, 600, false);
            llllllllllllllllllIIIllIlIIlIIIl.start();
        }
        catch (SlickException llllllllllllllllllIIIllIlIIlIIII) {
            llllllllllllllllllIIIllIlIIlIIII.printStackTrace();
        }
    }
    
    @Override
    public void keyPressed(final int llllllllllllllllllIIIllIlIIlIIll, final char llllllllllllllllllIIIllIlIIlIlIl) {
        if (llllllllllllllllllIIIllIlIIlIIll == 1) {
            System.exit(0);
        }
        if (llllllllllllllllllIIIllIlIIlIIll == 57) {
            this.visible = !this.visible;
        }
    }
    
    @Override
    public void update(final GameContainer llllllllllllllllllIIIllIlIIllIll, final int llllllllllllllllllIIIllIlIIllIlI) throws SlickException {
    }
    
    public TrueTypeFontPerformanceTest() {
        super("Font Performance Test");
        this.text = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin bibendum. Aliquam ac sapien a elit congue iaculis. Quisque et justo quis mi mattis euismod. Donec elementum, mi quis aliquet varius, nisi leo volutpat magna, quis ultricies eros augue at risus. Integer non magna at lorem sodales molestie. Integer diam nulla, ornare sit amet, mattis quis, euismod et, mauris. Proin eget tellus non nisl mattis laoreet. Nunc at nunc id elit pretium tempor. Duis vulputate, nibh eget rhoncus eleifend, tellus lectus sollicitudin mi, rhoncus tincidunt nisi massa vitae ipsum. Praesent tellus diam, luctus ut, eleifend nec, auctor et, orci. Praesent eu elit. Pellentesque ante orci, volutpat placerat, ornare eget, cursus sit amet, eros. Duis pede sapien, euismod a, volutpat pellentesque, convallis eu, mauris. Nunc eros. Ut eu risus et felis laoreet viverra. Curabitur a metus.";
        this.lines = new ArrayList();
        this.visible = true;
    }
}
