package org.newdawn.slick.tests;

import java.util.*;
import org.newdawn.slick.*;

public class FontPerformanceTest extends BasicGame
{
    private /* synthetic */ ArrayList lines;
    private /* synthetic */ String text;
    private /* synthetic */ AngelCodeFont font;
    private /* synthetic */ boolean visible;
    
    public FontPerformanceTest() {
        super("Font Performance Test");
        this.text = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin bibendum. Aliquam ac sapien a elit congue iaculis. Quisque et justo quis mi mattis euismod. Donec elementum, mi quis aliquet varius, nisi leo volutpat magna, quis ultricies eros augue at risus. Integer non magna at lorem sodales molestie. Integer diam nulla, ornare sit amet, mattis quis, euismod et, mauris. Proin eget tellus non nisl mattis laoreet. Nunc at nunc id elit pretium tempor. Duis vulputate, nibh eget rhoncus eleifend, tellus lectus sollicitudin mi, rhoncus tincidunt nisi massa vitae ipsum. Praesent tellus diam, luctus ut, eleifend nec, auctor et, orci. Praesent eu elit. Pellentesque ante orci, volutpat placerat, ornare eget, cursus sit amet, eros. Duis pede sapien, euismod a, volutpat pellentesque, convallis eu, mauris. Nunc eros. Ut eu risus et felis laoreet viverra. Curabitur a metus.";
        this.lines = new ArrayList();
        this.visible = true;
    }
    
    public static void main(final String[] lllllllllllllllllIIlllIIllIIlIIl) {
        try {
            final AppGameContainer lllllllllllllllllIIlllIIllIIlIll = new AppGameContainer(new FontPerformanceTest());
            lllllllllllllllllIIlllIIllIIlIll.setDisplayMode(800, 600, false);
            lllllllllllllllllIIlllIIllIIlIll.start();
        }
        catch (SlickException lllllllllllllllllIIlllIIllIIlIlI) {
            lllllllllllllllllIIlllIIllIIlIlI.printStackTrace();
        }
    }
    
    @Override
    public void keyPressed(final int lllllllllllllllllIIlllIIllIlIIII, final char lllllllllllllllllIIlllIIllIIllll) {
        if (lllllllllllllllllIIlllIIllIlIIII == 1) {
            System.exit(0);
        }
        if (lllllllllllllllllIIlllIIllIlIIII == 57) {
            this.visible = !this.visible;
        }
    }
    
    @Override
    public void update(final GameContainer lllllllllllllllllIIlllIIllIlIlIl, final int lllllllllllllllllIIlllIIllIlIlII) throws SlickException {
    }
    
    @Override
    public void render(final GameContainer lllllllllllllllllIIlllIIllIllIll, final Graphics lllllllllllllllllIIlllIIllIllIII) {
        lllllllllllllllllIIlllIIllIllIII.setFont(this.font);
        if (this.visible) {
            for (int lllllllllllllllllIIlllIIllIlllIl = 0; lllllllllllllllllIIlllIIllIlllIl < this.lines.size(); ++lllllllllllllllllIIlllIIllIlllIl) {
                this.font.drawString(10.0f, (float)(50 + lllllllllllllllllIIlllIIllIlllIl * 20), this.lines.get(lllllllllllllllllIIlllIIllIlllIl), (lllllllllllllllllIIlllIIllIlllIl > 10) ? Color.red : Color.green);
            }
        }
    }
    
    @Override
    public void init(final GameContainer lllllllllllllllllIIlllIIlllIIlIl) throws SlickException {
        this.font = new AngelCodeFont("testdata/perffont.fnt", "testdata/perffont.png");
        for (int lllllllllllllllllIIlllIIlllIIlll = 0; lllllllllllllllllIIlllIIlllIIlll < 2; ++lllllllllllllllllIIlllIIlllIIlll) {
            for (int lllllllllllllllllIIlllIIlllIlIII = 90, lllllllllllllllllIIlllIIlllIlIIl = 0; lllllllllllllllllIIlllIIlllIlIIl < this.text.length(); lllllllllllllllllIIlllIIlllIlIIl += lllllllllllllllllIIlllIIlllIlIII) {
                if (lllllllllllllllllIIlllIIlllIlIIl + lllllllllllllllllIIlllIIlllIlIII > this.text.length()) {
                    lllllllllllllllllIIlllIIlllIlIII = this.text.length() - lllllllllllllllllIIlllIIlllIlIIl;
                }
                this.lines.add(this.text.substring(lllllllllllllllllIIlllIIlllIlIIl, lllllllllllllllllIIlllIIlllIlIIl + lllllllllllllllllIIlllIIlllIlIII));
            }
            this.lines.add("");
        }
    }
}
