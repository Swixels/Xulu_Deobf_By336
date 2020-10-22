package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;
import net.minecraft.client.gui.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.*;
import dev.xulu.settings.*;
import com.elementars.eclient.module.*;

public class Armor extends Element
{
    private final /* synthetic */ Value<Integer> h;
    private final /* synthetic */ Value<Integer> w;
    private final /* synthetic */ Value<Boolean> progress;
    private final /* synthetic */ Value<Boolean> cf;
    private static /* synthetic */ RenderItem itemRender;
    private final /* synthetic */ Value<String> aligned;
    private final /* synthetic */ Value<Boolean> fixed;
    private final /* synthetic */ Value<Boolean> damage;
    
    static {
        Armor.itemRender = Minecraft.getMinecraft().getRenderItem();
    }
    
    @Override
    public void onRender() {
        GlStateManager.enableTexture2D();
        final ScaledResolution lllllllllllllllllIIIllIIIIlIlIII = new ScaledResolution(Armor.mc);
        final int lllllllllllllllllIIIllIIIIlIIlll = lllllllllllllllllIIIllIIIIlIlIII.getScaledWidth() / 2;
        int lllllllllllllllllIIIllIIIIlIIllI = 0;
        final int lllllllllllllllllIIIllIIIIlIIlIl = lllllllllllllllllIIIllIIIIlIlIII.getScaledHeight() - 55 - (Armor.mc.player.isInWater() ? 10 : 0);
        for (final ItemStack lllllllllllllllllIIIllIIIIlIlIlI : Armor.mc.player.inventory.armorInventory) {
            ++lllllllllllllllllIIIllIIIIlIIllI;
            int lllllllllllllllllIIIllIIIIlIllIl = 0;
            int lllllllllllllllllIIIllIIIIlIllII = 0;
            final String s = this.aligned.getValue();
            float lllllllllllllllllIIIllIIIIIllIlI = -1;
            switch (s.hashCode()) {
                case -1731035146: {
                    if (s.equals("X-axis")) {
                        lllllllllllllllllIIIllIIIIIllIlI = 0;
                        break;
                    }
                    break;
                }
                case -1702405995: {
                    if (s.equals("Y-axis")) {
                        lllllllllllllllllIIIllIIIIIllIlI = 1;
                        break;
                    }
                    break;
                }
            }
            switch (lllllllllllllllllIIIllIIIIIllIlI) {
                case 0.0f: {
                    lllllllllllllllllIIIllIIIIlIllIl = (int)this.x - 90 + (9 - lllllllllllllllllIIIllIIIIlIIllI) * 20 + 2 - 12;
                    lllllllllllllllllIIIllIIIIlIllII = (int)this.y;
                    if (this.fixed.getValue()) {
                        lllllllllllllllllIIIllIIIIlIllIl = lllllllllllllllllIIIllIIIIlIIlll - 90 + (9 - lllllllllllllllllIIIllIIIIlIIllI) * 20 + 2;
                        lllllllllllllllllIIIllIIIIlIllII = lllllllllllllllllIIIllIIIIlIIlIl;
                        break;
                    }
                    break;
                }
                case 1.0f: {
                    lllllllllllllllllIIIllIIIIlIllIl = (int)this.x;
                    lllllllllllllllllIIIllIIIIlIllII = (int)this.y - (lllllllllllllllllIIIllIIIIlIIllI - 1) * 18 + 54;
                    if (this.fixed.getValue()) {
                        lllllllllllllllllIIIllIIIIlIllIl = lllllllllllllllllIIIllIIIIlIIlll;
                        lllllllllllllllllIIIllIIIIlIllII = lllllllllllllllllIIIllIIIIlIIlIl - (lllllllllllllllllIIIllIIIIlIIllI - 1) * 18 + 54;
                        break;
                    }
                    break;
                }
            }
            if (this.progress.getValue()) {
                final String s2 = this.aligned.getValue();
                lllllllllllllllllIIIllIIIIIllIlI = -1;
                switch (s2.hashCode()) {
                    case -1731035146: {
                        if (s2.equals("X-axis")) {
                            lllllllllllllllllIIIllIIIIIllIlI = 0;
                            break;
                        }
                        break;
                    }
                    case -1702405995: {
                        if (s2.equals("Y-axis")) {
                            lllllllllllllllllIIIllIIIIIllIlI = 1;
                            break;
                        }
                        break;
                    }
                }
                switch (lllllllllllllllllIIIllIIIIIllIlI) {
                    case 0.0f: {
                        Gui.drawRect(lllllllllllllllllIIIllIIIIlIllIl - 1, lllllllllllllllllIIIllIIIIlIllII + 16, lllllllllllllllllIIIllIIIIlIllIl + 19, lllllllllllllllllIIIllIIIIlIllII - 51 + 16, ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 60));
                        break;
                    }
                    case 1.0f: {
                        Gui.drawRect(lllllllllllllllllIIIllIIIIlIllIl, lllllllllllllllllIIIllIIIIlIllII, lllllllllllllllllIIIllIIIIlIllIl + 69, lllllllllllllllllIIIllIIIIlIllII + 18, ColorUtils.changeAlpha(ColorUtils.Colors.BLACK, 60));
                        break;
                    }
                }
                if (lllllllllllllllllIIIllIIIIlIlIlI.isEmpty()) {
                    continue;
                }
                final float lllllllllllllllllIIIllIIIIllIIlI = (lllllllllllllllllIIIllIIIIlIlIlI.getMaxDamage() - (float)lllllllllllllllllIIIllIIIIlIlIlI.getItemDamage()) / lllllllllllllllllIIIllIIIIlIlIlI.getMaxDamage();
                final float lllllllllllllllllIIIllIIIIllIIIl = 1.0f - lllllllllllllllllIIIllIIIIllIIlI;
                final short lllllllllllllllllIIIllIIIIIllIIl = (short)this.aligned.getValue();
                int n = -1;
                switch (((String)lllllllllllllllllIIIllIIIIIllIIl).hashCode()) {
                    case -1731035146: {
                        if (((String)lllllllllllllllllIIIllIIIIIllIIl).equals("X-axis")) {
                            n = 0;
                            break;
                        }
                        break;
                    }
                    case -1702405995: {
                        if (((String)lllllllllllllllllIIIllIIIIIllIIl).equals("Y-axis")) {
                            n = 1;
                            break;
                        }
                        break;
                    }
                }
                switch (n) {
                    case 0: {
                        Gui.drawRect(lllllllllllllllllIIIllIIIIlIllIl - 1, lllllllllllllllllIIIllIIIIlIllII + 16, lllllllllllllllllIIIllIIIIlIllIl + 19, (int)(lllllllllllllllllIIIllIIIIlIllII - lllllllllllllllllIIIllIIIIllIIlI * 51.0f) + 16, ColorUtils.changeAlpha(ColourHolder.toHex((int)(lllllllllllllllllIIIllIIIIllIIIl * 255.0f), (int)(lllllllllllllllllIIIllIIIIllIIlI * 255.0f), 0), 150));
                        break;
                    }
                    case 1: {
                        Gui.drawRect(lllllllllllllllllIIIllIIIIlIllIl, lllllllllllllllllIIIllIIIIlIllII, (int)(lllllllllllllllllIIIllIIIIlIllIl + lllllllllllllllllIIIllIIIIllIIlI * 69.0f), lllllllllllllllllIIIllIIIIlIllII + 18, ColorUtils.changeAlpha(ColourHolder.toHex((int)(lllllllllllllllllIIIllIIIIllIIIl * 255.0f), (int)(lllllllllllllllllIIIllIIIIllIIlI * 255.0f), 0), 150));
                        break;
                    }
                }
            }
            else if (lllllllllllllllllIIIllIIIIlIlIlI.isEmpty()) {
                continue;
            }
            GlStateManager.enableDepth();
            Armor.itemRender.zLevel = 200.0f;
            Armor.itemRender.renderItemAndEffectIntoGUI(lllllllllllllllllIIIllIIIIlIlIlI, lllllllllllllllllIIIllIIIIlIllIl, lllllllllllllllllIIIllIIIIlIllII);
            Armor.itemRender.renderItemOverlayIntoGUI(Armor.mc.fontRenderer, lllllllllllllllllIIIllIIIIlIlIlI, lllllllllllllllllIIIllIIIIlIllIl, lllllllllllllllllIIIllIIIIlIllII, "");
            Armor.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            final String lllllllllllllllllIIIllIIIIlIlIll = (lllllllllllllllllIIIllIIIIlIlIlI.getCount() > 1) ? String.valueOf(new StringBuilder().append(lllllllllllllllllIIIllIIIIlIlIlI.getCount()).append("")) : "";
            Armor.mc.fontRenderer.drawStringWithShadow(lllllllllllllllllIIIllIIIIlIlIll, (float)(lllllllllllllllllIIIllIIIIlIllIl + 19 - 2 - Armor.mc.fontRenderer.getStringWidth(lllllllllllllllllIIIllIIIIlIlIll)), (float)((int)this.y + 9), 16777215);
            if (this.damage.getValue()) {
                final float lllllllllllllllllIIIllIIIIllIIII = (lllllllllllllllllIIIllIIIIlIlIlI.getMaxDamage() - (float)lllllllllllllllllIIIllIIIIlIlIlI.getItemDamage()) / lllllllllllllllllIIIllIIIIlIlIlI.getMaxDamage();
                final float lllllllllllllllllIIIllIIIIlIllll = 1.0f - lllllllllllllllllIIIllIIIIllIIII;
                final int lllllllllllllllllIIIllIIIIlIlllI = 100 - (int)(lllllllllllllllllIIIllIIIIlIllll * 100.0f);
                final byte lllllllllllllllllIIIllIIIIIlIlll = (byte)this.aligned.getValue();
                long lllllllllllllllllIIIllIIIIIlIllI = -1;
                switch (((String)lllllllllllllllllIIIllIIIIIlIlll).hashCode()) {
                    case -1731035146: {
                        if (((String)lllllllllllllllllIIIllIIIIIlIlll).equals("X-axis")) {
                            lllllllllllllllllIIIllIIIIIlIllI = 0;
                            break;
                        }
                        break;
                    }
                    case -1702405995: {
                        if (((String)lllllllllllllllllIIIllIIIIIlIlll).equals("Y-axis")) {
                            lllllllllllllllllIIIllIIIIIlIllI = 1;
                            break;
                        }
                        break;
                    }
                }
                switch (lllllllllllllllllIIIllIIIIIlIllI) {
                    case 0L: {
                        if (this.cf.getValue()) {
                            Xulu.cFontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(lllllllllllllllllIIIllIIIIlIlllI).append("")), lllllllllllllllllIIIllIIIIlIllIl + 8 - Xulu.cFontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(lllllllllllllllllIIIllIIIIlIlllI).append(""))) / 2, lllllllllllllllllIIIllIIIIlIllII - 11, ColourHolder.toHex((int)(lllllllllllllllllIIIllIIIIlIllll * 255.0f), (int)(lllllllllllllllllIIIllIIIIllIIII * 255.0f), 0));
                            continue;
                        }
                        Armor.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(lllllllllllllllllIIIllIIIIlIlllI).append("")), (float)(lllllllllllllllllIIIllIIIIlIllIl + 9 - Armor.fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(lllllllllllllllllIIIllIIIIlIlllI).append(""))) / 2), (float)(lllllllllllllllllIIIllIIIIlIllII - 11), ColourHolder.toHex((int)(lllllllllllllllllIIIllIIIIlIllll * 255.0f), (int)(lllllllllllllllllIIIllIIIIllIIII * 255.0f), 0));
                        continue;
                    }
                    case 1L: {
                        if (this.cf.getValue()) {
                            Xulu.cFontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(lllllllllllllllllIIIllIIIIlIlllI).append("")), lllllllllllllllllIIIllIIIIlIllIl + 18, lllllllllllllllllIIIllIIIIlIllII + 5, ColourHolder.toHex((int)(lllllllllllllllllIIIllIIIIlIllll * 255.0f), (int)(lllllllllllllllllIIIllIIIIllIIII * 255.0f), 0));
                            continue;
                        }
                        Armor.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(lllllllllllllllllIIIllIIIIlIlllI).append("")), (float)(lllllllllllllllllIIIllIIIIlIllIl + 18), (float)(lllllllllllllllllIIIllIIIIlIllII + 5), ColourHolder.toHex((int)(lllllllllllllllllIIIllIIIIlIllll * 255.0f), (int)(lllllllllllllllllIIIllIIIIllIIII * 255.0f), 0));
                        continue;
                    }
                }
            }
        }
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
    }
    
    @Override
    public void onEnable() {
        final byte lllllllllllllllllIIIllIIIlIIIIll = (byte)this.aligned.getValue();
        String lllllllllllllllllIIIllIIIlIIIIlI = (String)(-1);
        switch (((String)lllllllllllllllllIIIllIIIlIIIIll).hashCode()) {
            case -1731035146: {
                if (((String)lllllllllllllllllIIIllIIIlIIIIll).equals("X-axis")) {
                    lllllllllllllllllIIIllIIIlIIIIlI = (String)0;
                    break;
                }
                break;
            }
            case -1702405995: {
                if (((String)lllllllllllllllllIIIllIIIlIIIIll).equals("Y-axis")) {
                    lllllllllllllllllIIIllIIIlIIIIlI = (String)1;
                    break;
                }
                break;
            }
        }
        switch (lllllllllllllllllIIIllIIIlIIIIlI) {
            case 0L: {
                this.height = 16.0;
                this.width = 79.0;
                break;
            }
            case 1L: {
                this.height = 72.0;
                this.width = 16.0;
                break;
            }
        }
    }
    
    public Armor() {
        super("Armor");
        final double lllllllllllllllllIIIllIIIIIIllIl;
        Exception lllllllllllllllllIIIllIIIIIIllII;
        this.aligned = this.register(new Value<String>("Aligned", this, "X-axis", new String[] { "X-axis", "Y-axis" })).onChanged(lllllllllllllllllIIIllIIIIIlIIII -> {
            lllllllllllllllllIIIllIIIIIIllIl = lllllllllllllllllIIIllIIIIIlIIII.getNew();
            lllllllllllllllllIIIllIIIIIIllII = (Exception)(-1);
            switch (((String)lllllllllllllllllIIIllIIIIIIllIl).hashCode()) {
                case -1731035146: {
                    if (((String)lllllllllllllllllIIIllIIIIIIllIl).equals("X-axis")) {
                        lllllllllllllllllIIIllIIIIIIllII = (Exception)0;
                        break;
                    }
                    else {
                        break;
                    }
                    break;
                }
                case -1702405995: {
                    if (((String)lllllllllllllllllIIIllIIIIIIllIl).equals("Y-axis")) {
                        lllllllllllllllllIIIllIIIIIIllII = (Exception)1;
                        break;
                    }
                    else {
                        break;
                    }
                    break;
                }
            }
            switch (lllllllllllllllllIIIllIIIIIIllII) {
                case 0L: {
                    this.height = 16.0;
                    this.width = 79.0;
                    break;
                }
                case 1L: {
                    this.height = 72.0;
                    this.width = 16.0;
                    break;
                }
            }
            return;
        });
        this.progress = this.register(new Value<Boolean>("Durab. Bar", this, false));
        this.w = this.register(new Value<Integer>("Width", this, 0, 0, 100));
        this.h = this.register(new Value<Integer>("Height", this, 0, 0, 100));
        this.cf = this.register(new Value<Boolean>("Custom Font", this, false));
        this.damage = this.register(new Value<Boolean>("Damage", this, false));
        this.fixed = this.register(new Value<Boolean>("Fixed", this, false));
    }
}
