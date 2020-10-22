package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import dev.xulu.settings.*;
import dev.xulu.newgui.util.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.core.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.module.*;

public class Watermark extends Element
{
    private final /* synthetic */ Value<Boolean> rainbow;
    private final /* synthetic */ Value<String> text;
    
    @Override
    public void onEnable() {
        this.width = Watermark.fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(this.text.getValue()).append(" ").append("v1.5.2"))) + 2;
        this.height = Watermark.fontRenderer.FONT_HEIGHT + 2;
        super.onEnable();
    }
    
    @Override
    public void onRender() {
        int lllllllllllllllllIllIllIIIlllIll = ColorUtil.getClickGUIColor().getRGB();
        if (this.rainbow.getValue()) {
            lllllllllllllllllIllIllIIIlllIll = Xulu.rgb;
        }
        if (Xulu.CustomFont) {
            Xulu.cFontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(this.text.getValue()).append(" ").append("v1.5.2")), this.x + 1.0, this.y + 1.0, ColorUtils.changeAlpha(lllllllllllllllllIllIllIIIlllIll, Global.hudAlpha.getValue()));
        }
        else {
            Wrapper.getMinecraft().fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(this.text.getValue()).append(" ").append("v1.5.2")), (float)this.x + 1.0f, (float)this.y + 1.0f, ColorUtils.changeAlpha(lllllllllllllllllIllIllIIIlllIll, Global.hudAlpha.getValue()));
        }
    }
    
    public Watermark() {
        super("Watermark");
        this.text = this.register(new Value<String>("Mode", this, "Xulu", new String[] { "Xulu", "PK Client", "WideHack" })).onChanged(lllllllllllllllllIllIllIIIllIlIl -> this.width = Watermark.fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(lllllllllllllllllIllIllIIIllIlIl.getNew()).append(" ").append("v1.5.2"))) + 2);
        this.rainbow = this.register(new Value<Boolean>("Rainbow", this, false));
    }
}
