package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import dev.xulu.settings.*;
import com.elementars.eclient.module.*;
import dev.xulu.newgui.util.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.core.*;
import com.elementars.eclient.util.*;

public class StickyNotes extends Element
{
    public static /* synthetic */ String[] text;
    private final /* synthetic */ Value<Boolean> rainbow;
    public static /* synthetic */ String saveText;
    
    @Override
    public void onMiddleClick() {
        TextNotes.initTextBox();
    }
    
    public static void processText(final String llllllllllllllllIllllIIlIIlIIIll) {
        StickyNotes.text = llllllllllllllllIllllIIlIIlIIIll.split("@");
        StickyNotes.saveText = llllllllllllllllIllllIIlIIlIIIll;
    }
    
    public StickyNotes() {
        super("StickyNotes");
        this.rainbow = this.register(new Value<Boolean>("Rainbow", this, false));
    }
    
    @Override
    public void onRender() {
        this.width = StickyNotes.fontRenderer.getStringWidth(ListHelper.longest(StickyNotes.text)) + 2;
        this.height = (StickyNotes.fontRenderer.FONT_HEIGHT + 1) * StickyNotes.text.length + 1;
        int llllllllllllllllIllllIIlIIlIlllI = ColorUtil.getClickGUIColor().getRGB();
        if (this.rainbow.getValue()) {
            llllllllllllllllIllllIIlIIlIlllI = Xulu.rgb;
        }
        double llllllllllllllllIllllIIlIIlIllIl = this.y;
        final boolean llllllllllllllllIllllIIlIIlIlIIl = (Object)StickyNotes.text;
        for (boolean llllllllllllllllIllllIIlIIlIlIII = llllllllllllllllIllllIIlIIlIlIIl.length != 0, llllllllllllllllIllllIIlIIlIIlll = false; llllllllllllllllIllllIIlIIlIIlll < llllllllllllllllIllllIIlIIlIlIII; ++llllllllllllllllIllllIIlIIlIIlll) {
            final String llllllllllllllllIllllIIlIIllIIII = llllllllllllllllIllllIIlIIlIlIIl[llllllllllllllllIllllIIlIIlIIlll];
            final String llllllllllllllllIllllIIlIIllIIIl = llllllllllllllllIllllIIlIIllIIII.replaceAll("&", String.valueOf('§'));
            if (Xulu.CustomFont) {
                Xulu.cFontRenderer.drawStringWithShadow(llllllllllllllllIllllIIlIIllIIIl, this.x + 1.0, llllllllllllllllIllllIIlIIlIllIl + 1.0, ColorUtils.changeAlpha(llllllllllllllllIllllIIlIIlIlllI, Global.hudAlpha.getValue()));
            }
            else {
                StickyNotes.fontRenderer.drawStringWithShadow(llllllllllllllllIllllIIlIIllIIIl, (float)this.x + 1.0f, (float)llllllllllllllllIllllIIlIIlIllIl + 1.0f, ColorUtils.changeAlpha(llllllllllllllllIllllIIlIIlIlllI, Global.hudAlpha.getValue()));
            }
            llllllllllllllllIllllIIlIIlIllIl += 10.0;
        }
    }
    
    static {
        StickyNotes.text = new String[] { "Put text here" };
    }
}
