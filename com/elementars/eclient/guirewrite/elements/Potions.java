package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import dev.xulu.settings.*;
import java.text.*;
import net.minecraft.potion.*;
import net.minecraft.init.*;
import com.mojang.realmsclient.gui.*;
import java.awt.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.core.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.module.*;
import java.util.*;

public class Potions extends Element
{
    /* synthetic */ Value<Boolean> onlysw;
    /* synthetic */ Value<Boolean> pc;
    /* synthetic */ Value<Integer> blue;
    /* synthetic */ DecimalFormat df;
    /* synthetic */ Value<String> order;
    /* synthetic */ Value<String> align;
    /* synthetic */ Value<Integer> green;
    /* synthetic */ Value<Integer> red;
    
    @Override
    public void onEnable() {
        this.width = 50.0;
        this.height = 50.0;
    }
    
    @Override
    public void onRender() {
        float lllllllllllllllllIllllIIIIllIllI = (float)this.y;
        final float lllllllllllllllllIllllIIIIllIlIl = (float)this.x;
        if (Potions.mc.player == null) {
            return;
        }
        final List<String> lllllllllllllllllIllllIIIIllIlII = new ArrayList<String>();
        if (this.order.getValue().equalsIgnoreCase("Down")) {
            lllllllllllllllllIllllIIIIllIllI += (float)(this.height - 10.0);
        }
        for (final PotionEffect lllllllllllllllllIllllIIIIlllIII : Potions.mc.player.getActivePotionEffects()) {
            if (this.onlysw.getValue() && lllllllllllllllllIllllIIIIlllIII.getPotion() != MobEffects.STRENGTH && lllllllllllllllllIllllIIIIlllIII.getPotion() != MobEffects.WEAKNESS) {
                continue;
            }
            final String lllllllllllllllllIllllIIIIlllIlI = String.valueOf(new StringBuilder().append(PotionUtil.getPotionName(lllllllllllllllllIllllIIIIlllIII.getPotion())).append(" ").append((lllllllllllllllllIllllIIIIlllIII.getAmplifier() + 1 != 1) ? String.valueOf(new StringBuilder().append(lllllllllllllllllIllllIIIIlllIII.getAmplifier() + 1).append(" ")) : "").append(ChatFormatting.GRAY).append((this.getTime(lllllllllllllllllIllllIIIIlllIII.getDuration() / 20).length() == 1) ? "0" : "").append(this.getTime(lllllllllllllllllIllllIIIIlllIII.getDuration() / 20)));
            int lllllllllllllllllIllllIIIIlllIIl = new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue()).getRGB();
            if (this.pc.getValue()) {
                lllllllllllllllllIllllIIIIlllIIl = lllllllllllllllllIllllIIIIlllIII.getPotion().getLiquidColor();
            }
            if (Xulu.CustomFont) {
                Xulu.cFontRenderer.drawStringWithShadow(lllllllllllllllllIllllIIIIlllIlI, (this.align.getValue().equalsIgnoreCase("Right") ? (lllllllllllllllllIllllIIIIllIlIl - Xulu.cFontRenderer.getStringWidth(lllllllllllllllllIllllIIIIlllIlI) + this.getFrame().width) : lllllllllllllllllIllllIIIIllIlIl) + 1.0, lllllllllllllllllIllllIIIIllIllI + 1.0f, ColorUtils.changeAlpha(lllllllllllllllllIllllIIIIlllIIl, Global.hudAlpha.getValue()));
            }
            else {
                Wrapper.getMinecraft().fontRenderer.drawStringWithShadow(lllllllllllllllllIllllIIIIlllIlI, (float)(this.align.getValue().equalsIgnoreCase("Right") ? (lllllllllllllllllIllllIIIIllIlIl - Wrapper.getMinecraft().fontRenderer.getStringWidth(lllllllllllllllllIllllIIIIlllIlI) + this.getFrame().width) : lllllllllllllllllIllllIIIIllIlIl) + 1.0f, lllllllllllllllllIllllIIIIllIllI + 1.0f, ColorUtils.changeAlpha(lllllllllllllllllIllllIIIIlllIIl, Global.hudAlpha.getValue()));
            }
            lllllllllllllllllIllllIIIIllIlII.add(lllllllllllllllllIllllIIIIlllIlI);
            lllllllllllllllllIllllIIIIllIllI += (this.order.getValue().equalsIgnoreCase("Up") ? 10 : -10);
        }
    }
    
    public Potions() {
        super("Potions");
        this.df = new DecimalFormat("00");
        this.pc = this.register(new Value<Boolean>("Use Potion Color", this, true));
        this.red = this.register(new Value<Integer>("Red", this, 0, 0, 255));
        this.green = this.register(new Value<Integer>("Green", this, 0, 0, 255));
        this.blue = this.register(new Value<Integer>("Blue", this, 0, 0, 255));
        this.onlysw = this.register(new Value<Boolean>("Only Str & Weak", this, false));
        this.align = this.register(new Value<String>("Align", this, "Left", new ArrayList<String>(Arrays.asList("Left", "Right"))));
        this.order = this.register(new Value<String>("Order", this, "Up", new ArrayList<String>(Arrays.asList("Up", "Down"))));
    }
    
    public String getTime(int lllllllllllllllllIllllIIIIlIIlll) {
        int lllllllllllllllllIllllIIIIlIIllI;
        for (lllllllllllllllllIllllIIIIlIIllI = 0; lllllllllllllllllIllllIIIIlIIlll > 59; lllllllllllllllllIllllIIIIlIIlll -= 60, ++lllllllllllllllllIllllIIIIlIIllI) {}
        return String.valueOf(new StringBuilder().append(lllllllllllllllllIllllIIIIlIIllI).append(":").append(this.df.format(lllllllllllllllllIllllIIIIlIIlll)));
    }
}
