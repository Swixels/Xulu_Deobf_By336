package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.renderer.*;
import net.minecraftforge.fml.client.config.*;
import java.awt.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.*;
import net.minecraft.client.gui.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.elementars.eclient.module.*;

public class ToolTips extends Module
{
    public static /* synthetic */ Value<Integer> Og;
    public static /* synthetic */ Value<Boolean> cf;
    public static /* synthetic */ Value<Integer> g;
    public static /* synthetic */ Value<Boolean> outline;
    public static /* synthetic */ Value<Integer> Ob;
    public static /* synthetic */ Value<Boolean> cb;
    public static /* synthetic */ Value<Integer> b;
    public static /* synthetic */ Value<Integer> r;
    public static /* synthetic */ Value<Integer> Or;
    
    @Override
    public void onDisable() {
        ToolTips.EVENT_BUS.unregister((Object)this);
    }
    
    @Override
    public void onEnable() {
        ToolTips.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void onToolText(final RenderTooltipEvent.Pre lIlllIlIllIlII) {
        final int lIlllIllIIIIIl = lIlllIlIllIlII.getX();
        final int lIlllIllIIIIII = lIlllIlIllIlII.getY();
        final int lIlllIlIllllll = lIlllIlIllIlII.getScreenWidth();
        final int lIlllIlIlllllI = lIlllIlIllIlII.getScreenHeight();
        final int lIlllIlIllllIl = lIlllIlIllIlII.getMaxWidth();
        List<String> lIlllIlIllllII = (List<String>)lIlllIlIllIlII.getLines();
        final FontRenderer lIlllIlIlllIll = lIlllIlIllIlII.getFontRenderer();
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        int lIlllIlIlllIlI = 0;
        for (final String lIlllIllIlIIIl : lIlllIlIllllII) {
            final int lIlllIllIlIIlI = lIlllIlIlllIll.getStringWidth(lIlllIllIlIIIl);
            if (lIlllIllIlIIlI > lIlllIlIlllIlI) {
                lIlllIlIlllIlI = lIlllIllIlIIlI;
            }
        }
        boolean lIlllIlIlllIIl = false;
        int lIlllIlIlllIII = 1;
        int lIlllIlIllIlll = lIlllIllIIIIIl + 12;
        if (lIlllIlIllIlll + lIlllIlIlllIlI + 4 > lIlllIlIllllll) {
            lIlllIlIllIlll = lIlllIllIIIIIl - 16 - lIlllIlIlllIlI;
            if (lIlllIlIllIlll < 4) {
                if (lIlllIllIIIIIl > lIlllIlIllllll / 2) {
                    lIlllIlIlllIlI = lIlllIllIIIIIl - 12 - 8;
                }
                else {
                    lIlllIlIlllIlI = lIlllIlIllllll - 16 - lIlllIllIIIIIl;
                }
                lIlllIlIlllIIl = true;
            }
        }
        if (lIlllIlIllllIl > 0 && lIlllIlIlllIlI > lIlllIlIllllIl) {
            lIlllIlIlllIlI = lIlllIlIllllIl;
            lIlllIlIlllIIl = true;
        }
        if (lIlllIlIlllIIl) {
            int lIlllIllIIlIll = 0;
            final List<String> lIlllIllIIlIlI = new ArrayList<String>();
            for (int lIlllIllIIllII = 0; lIlllIllIIllII < lIlllIlIllllII.size(); ++lIlllIllIIllII) {
                final String lIlllIllIIlllI = lIlllIlIllllII.get(lIlllIllIIllII);
                final List<String> lIlllIllIIllIl = (List<String>)lIlllIlIlllIll.listFormattedStringToWidth(lIlllIllIIlllI, lIlllIlIlllIlI);
                if (lIlllIllIIllII == 0) {
                    lIlllIlIlllIII = lIlllIllIIllIl.size();
                }
                for (final String lIlllIllIIllll : lIlllIllIIllIl) {
                    final int lIlllIllIlIIII = lIlllIlIlllIll.getStringWidth(lIlllIllIIllll);
                    if (lIlllIllIlIIII > lIlllIllIIlIll) {
                        lIlllIllIIlIll = lIlllIllIlIIII;
                    }
                    lIlllIllIIlIlI.add(lIlllIllIIllll);
                }
            }
            lIlllIlIlllIlI = lIlllIllIIlIll;
            lIlllIlIllllII = lIlllIllIIlIlI;
            if (lIlllIllIIIIIl > lIlllIlIllllll / 2) {
                lIlllIlIllIlll = lIlllIllIIIIIl - 16 - lIlllIlIlllIlI;
            }
            else {
                lIlllIlIllIlll = lIlllIllIIIIIl + 12;
            }
        }
        int lIlllIlIllIllI = lIlllIllIIIIII - 12;
        int lIlllIlIllIlIl = 8;
        if (lIlllIlIllllII.size() > 1) {
            lIlllIlIllIlIl += (lIlllIlIllllII.size() - 1) * 10;
            if (lIlllIlIllllII.size() > lIlllIlIlllIII) {
                lIlllIlIllIlIl += 2;
            }
        }
        if (lIlllIlIllIllI < 4) {
            lIlllIlIllIllI = 4;
        }
        else if (lIlllIlIllIllI + lIlllIlIllIlIl + 4 > lIlllIlIlllllI) {
            lIlllIlIllIllI = lIlllIlIlllllI - lIlllIlIllIlIl - 4;
        }
        if (!ToolTips.cb.getValue()) {
            final int lIlllIllIIlIIl = 300;
            final int lIlllIllIIlIII = -267386864;
            final int lIlllIllIIIlll = 1347420415;
            final int lIlllIllIIIllI = (lIlllIllIIIlll & 0xFEFEFE) >> 1 | (lIlllIllIIIlll & 0xFF000000);
            GuiUtils.drawGradientRect(300, lIlllIlIllIlll - 3, lIlllIlIllIllI - 4, lIlllIlIllIlll + lIlllIlIlllIlI + 3, lIlllIlIllIllI - 3, lIlllIllIIlIII, lIlllIllIIlIII);
            GuiUtils.drawGradientRect(300, lIlllIlIllIlll - 3, lIlllIlIllIllI + lIlllIlIllIlIl + 3, lIlllIlIllIlll + lIlllIlIlllIlI + 3, lIlllIlIllIllI + lIlllIlIllIlIl + 4, lIlllIllIIlIII, lIlllIllIIlIII);
            GuiUtils.drawGradientRect(300, lIlllIlIllIlll - 3, lIlllIlIllIllI - 3, lIlllIlIllIlll + lIlllIlIlllIlI + 3, lIlllIlIllIllI + lIlllIlIllIlIl + 3, lIlllIllIIlIII, lIlllIllIIlIII);
            GuiUtils.drawGradientRect(300, lIlllIlIllIlll - 4, lIlllIlIllIllI - 3, lIlllIlIllIlll - 3, lIlllIlIllIllI + lIlllIlIllIlIl + 3, lIlllIllIIlIII, lIlllIllIIlIII);
            GuiUtils.drawGradientRect(300, lIlllIlIllIlll + lIlllIlIlllIlI + 3, lIlllIlIllIllI - 3, lIlllIlIllIlll + lIlllIlIlllIlI + 4, lIlllIlIllIllI + lIlllIlIllIlIl + 3, lIlllIllIIlIII, lIlllIllIIlIII);
            GuiUtils.drawGradientRect(300, lIlllIlIllIlll - 3, lIlllIlIllIllI - 3 + 1, lIlllIlIllIlll - 3 + 1, lIlllIlIllIllI + lIlllIlIllIlIl + 3 - 1, lIlllIllIIIlll, lIlllIllIIIllI);
            GuiUtils.drawGradientRect(300, lIlllIlIllIlll + lIlllIlIlllIlI + 2, lIlllIlIllIllI - 3 + 1, lIlllIlIllIlll + lIlllIlIlllIlI + 3, lIlllIlIllIllI + lIlllIlIllIlIl + 3 - 1, lIlllIllIIIlll, lIlllIllIIIllI);
            GuiUtils.drawGradientRect(300, lIlllIlIllIlll - 3, lIlllIlIllIllI - 3, lIlllIlIllIlll + lIlllIlIlllIlI + 3, lIlllIlIllIllI - 3 + 1, lIlllIllIIIlll, lIlllIllIIIlll);
            GuiUtils.drawGradientRect(300, lIlllIlIllIlll - 3, lIlllIlIllIllI + lIlllIlIllIlIl + 2, lIlllIlIllIlll + lIlllIlIlllIlI + 3, lIlllIlIllIllI + lIlllIlIllIlIl + 3, lIlllIllIIIllI, lIlllIllIIIllI);
        }
        else {
            Gui.drawRect(lIlllIlIllIlll - 3, lIlllIlIllIllI - 4, lIlllIlIllIlll + lIlllIlIlllIlI + 3, lIlllIlIllIllI + lIlllIlIllIlIl + 4, new Color(ToolTips.r.getValue(), ToolTips.g.getValue(), ToolTips.b.getValue()).getRGB());
            if (ToolTips.outline.getValue()) {
                XuluTessellator.drawRectOutline(lIlllIlIllIlll - 3, lIlllIlIllIllI - 4, lIlllIlIllIlll + lIlllIlIlllIlI + 3, lIlllIlIllIllI + lIlllIlIllIlIl + 4, 1.0, new Color(ToolTips.Or.getValue(), ToolTips.Og.getValue(), ToolTips.Ob.getValue()).getRGB());
            }
        }
        for (int lIlllIllIIIlII = 0; lIlllIllIIIlII < lIlllIlIllllII.size(); ++lIlllIllIIIlII) {
            final String lIlllIllIIIlIl = lIlllIlIllllII.get(lIlllIllIIIlII);
            if (ToolTips.cf.getValue()) {
                Xulu.cFontRenderer.drawStringWithShadow(lIlllIllIIIlIl, (float)lIlllIlIllIlll, (float)lIlllIlIllIllI, -1);
            }
            else {
                lIlllIlIlllIll.drawStringWithShadow(lIlllIllIIIlIl, (float)lIlllIlIllIlll, (float)lIlllIlIllIllI, -1);
            }
            if (lIlllIllIIIlII + 1 == lIlllIlIlllIII) {
                lIlllIlIllIllI += 2;
            }
            lIlllIlIllIllI += 10;
        }
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableRescaleNormal();
        lIlllIlIllIlII.setCanceled(true);
    }
    
    public ToolTips() {
        super("ToolTips", "Custom ToolTips", 0, Category.RENDER, true);
        ToolTips.cf = this.register(new Value<Boolean>("Custom Font", this, true));
        ToolTips.cb = this.register(new Value<Boolean>("Custom Background", this, true));
        ToolTips.outline = this.register(new Value<Boolean>("Outline", this, true));
        ToolTips.r = this.register(new Value<Integer>("Red", this, 7, 0, 255));
        ToolTips.g = this.register(new Value<Integer>("Green", this, 12, 0, 255));
        ToolTips.b = this.register(new Value<Integer>("Blue", this, 17, 0, 255));
        ToolTips.Or = this.register(new Value<Integer>("Outline Red", this, 10, 0, 255));
        ToolTips.Og = this.register(new Value<Integer>("Outline Green", this, 12, 0, 255));
        ToolTips.Ob = this.register(new Value<Integer>("Outline Blue", this, 43, 0, 255));
    }
}
