package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import java.text.*;
import dev.xulu.settings.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.*;
import com.mojang.realmsclient.gui.*;
import com.elementars.eclient.friend.*;
import com.elementars.eclient.command.*;
import com.elementars.eclient.module.render.*;
import net.minecraft.entity.*;
import com.elementars.eclient.module.combat.*;
import com.elementars.eclient.module.core.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.module.*;

public class TextRadar extends Element
{
    /* synthetic */ DecimalFormat decimalFormat;
    private final /* synthetic */ Value<Boolean> pops;
    
    @Override
    public void onEnable() {
        this.width = 80.0;
        this.height = 80.0;
        super.onEnable();
    }
    
    @Override
    public void onRender() {
        float llIIlIlIlIIIllI = (float)this.y;
        for (final EntityPlayer llIIlIlIlIIlIII : TextRadar.mc.world.playerEntities) {
            if (llIIlIlIlIIlIII.getName().equals(TextRadar.mc.player.getName())) {
                continue;
            }
            if (Xulu.CustomFont) {
                Xulu.cFontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(ChatFormatting.GRAY).append("- ").append(Friends.isFriend(llIIlIlIlIIlIII.getName()) ? String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append(ColorTextUtils.getColor(ExtraTab.INSTANCE.color.getValue()).substring(1)).append(llIIlIlIlIIlIII.getName())) : llIIlIlIlIIlIII.getName()).append(" ").append(ChatFormatting.RED).append(this.decimalFormat.format(llIIlIlIlIIlIII.getHealth())).append(" ").append(ChatFormatting.DARK_GREEN).append((int)TextRadar.mc.player.getDistance((Entity)llIIlIlIlIIlIII)).append((PopCounter.INSTANCE.popMap.containsKey(llIIlIlIlIIlIII) && this.pops.getValue()) ? String.valueOf(new StringBuilder().append(" ").append(ChatFormatting.DARK_RED).append("-").append(PopCounter.INSTANCE.popMap.get(llIIlIlIlIIlIII))) : "")), (float)this.x, llIIlIlIlIIIllI, ColorUtils.changeAlpha(ColorUtils.Colors.WHITE, Global.hudAlpha.getValue()));
            }
            else {
                Wrapper.getMinecraft().fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(ChatFormatting.GRAY).append("- ").append(Friends.isFriend(llIIlIlIlIIlIII.getName()) ? String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append(ColorTextUtils.getColor(ExtraTab.INSTANCE.color.getValue()).substring(1)).append(llIIlIlIlIIlIII.getName())) : llIIlIlIlIIlIII.getName()).append(" ").append(ChatFormatting.RED).append(this.decimalFormat.format(llIIlIlIlIIlIII.getHealth())).append(" ").append(ChatFormatting.DARK_GREEN).append((int)TextRadar.mc.player.getDistance((Entity)llIIlIlIlIIlIII)).append((PopCounter.INSTANCE.popMap.containsKey(llIIlIlIlIIlIII) && this.pops.getValue()) ? String.valueOf(new StringBuilder().append(" ").append(ChatFormatting.DARK_RED).append("-").append(PopCounter.INSTANCE.popMap.get(llIIlIlIlIIlIII))) : "")), (float)this.x, llIIlIlIlIIIllI, ColorUtils.changeAlpha(ColorUtils.Colors.WHITE, Global.hudAlpha.getValue()));
            }
            llIIlIlIlIIIllI += 10.0f;
        }
    }
    
    public TextRadar() {
        super("TextRadar");
        this.pops = this.register(new Value<Boolean>("Pop Count", this, true));
        this.decimalFormat = new DecimalFormat("#.#");
    }
}
