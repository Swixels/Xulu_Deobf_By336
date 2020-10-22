package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import dev.xulu.settings.*;
import dev.xulu.newgui.util.*;
import com.elementars.eclient.*;
import java.util.*;
import com.elementars.eclient.friend.*;
import net.minecraft.entity.player.*;
import java.util.function.*;
import java.util.stream.*;
import com.elementars.eclient.module.core.*;
import com.elementars.eclient.util.*;
import java.awt.*;
import com.elementars.eclient.module.*;

public class TheGoons extends Element
{
    private final /* synthetic */ Value<Boolean> rainbow;
    private final /* synthetic */ Value<Integer> green;
    private final /* synthetic */ Value<Integer> blue;
    private final /* synthetic */ Value<Integer> red;
    
    @Override
    public void onRender() {
        int llllIIllIlllll = ColorUtil.getClickGUIColor().getRGB();
        if (this.rainbow.getValue()) {
            llllIIllIlllll = Xulu.rgb;
        }
        final List<String> llllIIllIllllI = (List<String>)TheGoons.mc.world.playerEntities.stream().filter(llllIIllIlIlII -> Friends.isFriend(llllIIllIlIlII.getName())).map(EntityPlayer::func_70005_c_).collect(Collectors.toList());
        llllIIllIllllI.add("The Goons");
        this.width = TheGoons.fontRenderer.getStringWidth(ListHelper.longest(llllIIllIllllI)) + 2;
        this.height = (TheGoons.fontRenderer.FONT_HEIGHT + 1) * llllIIllIllllI.size() + 1;
        float llllIIllIlllIl = (float)this.y;
        if (Xulu.CustomFont) {
            Xulu.cFontRenderer.drawStringWithShadow("The Goons", (float)this.x + 1.0f, llllIIllIlllIl + 1.0f, ColorUtils.changeAlpha(llllIIllIlllll, Global.hudAlpha.getValue()));
        }
        else {
            Wrapper.getMinecraft().fontRenderer.drawStringWithShadow("The Goons", (float)this.x + 1.0f, llllIIllIlllIl + 1.0f, ColorUtils.changeAlpha(llllIIllIlllll, Global.hudAlpha.getValue()));
        }
        llllIIllIlllIl += 10.0f;
        for (final EntityPlayer llllIIlllIIIIl : TheGoons.mc.world.playerEntities) {
            if (llllIIlllIIIIl.getName().equals(TheGoons.mc.player.getName())) {
                continue;
            }
            if (!Friends.isFriend(llllIIlllIIIIl.getName())) {
                continue;
            }
            if (Xulu.CustomFont) {
                Xulu.cFontRenderer.drawStringWithShadow(llllIIlllIIIIl.getName(), (float)this.x + 1.0f, llllIIllIlllIl + 1.0f, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), Global.hudAlpha.getValue()).getRGB());
            }
            else {
                Wrapper.getMinecraft().fontRenderer.drawStringWithShadow(llllIIlllIIIIl.getName(), (float)this.x + 1.0f, llllIIllIlllIl + 1.0f, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), Global.hudAlpha.getValue()).getRGB());
            }
            llllIIllIlllIl += 10.0f;
        }
    }
    
    public TheGoons() {
        super("TheGoons");
        this.rainbow = this.register(new Value<Boolean>("Rainbow", this, false));
        this.red = this.register(new Value<Integer>("Friend Red", this, 255, 0, 255));
        this.green = this.register(new Value<Integer>("Friend Green", this, 255, 0, 255));
        this.blue = this.register(new Value<Integer>("Friend Blue", this, 255, 0, 255));
    }
}
