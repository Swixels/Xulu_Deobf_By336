package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import dev.xulu.settings.*;
import dev.xulu.newgui.util.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.core.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.module.*;

public class OldName extends Element
{
    private final /* synthetic */ Value<Boolean> rainbow;
    
    @Override
    public void onEnable() {
        this.width = OldName.fontRenderer.getStringWidth("Elementars.com") + 2;
        this.height = OldName.fontRenderer.FONT_HEIGHT + 2;
        super.onEnable();
    }
    
    @Override
    public void onRender() {
        int llllllllllllllllIlIlIlIIIIIlllII = ColorUtil.getClickGUIColor().getRGB();
        if (this.rainbow.getValue()) {
            llllllllllllllllIlIlIlIIIIIlllII = Xulu.rgb;
        }
        if (Xulu.CustomFont) {
            Xulu.cFontRenderer.drawStringWithShadow("Elementars.com", this.x + 1.0, this.y + 1.0, ColorUtils.changeAlpha(llllllllllllllllIlIlIlIIIIIlllII, Global.hudAlpha.getValue()));
        }
        else {
            Wrapper.getMinecraft().fontRenderer.drawStringWithShadow("Elementars.com", (float)this.x + 1.0f, (float)this.y + 1.0f, ColorUtils.changeAlpha(llllllllllllllllIlIlIlIIIIIlllII, Global.hudAlpha.getValue()));
        }
    }
    
    public OldName() {
        super("OldName");
        this.rainbow = this.register(new Value<Boolean>("Rainbow", this, false));
    }
}
