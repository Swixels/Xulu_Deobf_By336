package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import com.elementars.eclient.*;
import org.lwjgl.opengl.*;
import net.minecraft.enchantment.*;
import net.minecraft.nbt.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.client.network.*;
import net.minecraft.init.*;
import com.elementars.eclient.module.*;
import java.util.*;
import net.minecraft.client.renderer.culling.*;
import com.elementars.eclient.friend.*;
import com.elementars.eclient.enemy.*;
import com.elementars.eclient.command.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.module.combat.*;
import com.mojang.realmsclient.gui.*;
import java.awt.*;
import com.elementars.eclient.util.*;
import net.minecraft.client.gui.*;

public class Nametags extends Module
{
    private final /* synthetic */ Value<Integer> red;
    private final /* synthetic */ Value<String> friendMode;
    private final /* synthetic */ Value<Integer> Ered;
    private final /* synthetic */ Value<Integer> Egreen;
    private final /* synthetic */ Value<Boolean> Orainbow;
    private final /* synthetic */ Value<Boolean> durability;
    private final /* synthetic */ Value<Boolean> enemies;
    private final /* synthetic */ Value<Boolean> reversedHand;
    private final /* synthetic */ Value<Float> height;
    private final /* synthetic */ Value<Boolean> cf;
    private final /* synthetic */ Value<Integer> Eblue;
    /* synthetic */ boolean shownItem;
    private final /* synthetic */ Value<Float> Owidth;
    private /* synthetic */ ICamera camera;
    private final /* synthetic */ Value<Boolean> gameMode;
    public static /* synthetic */ Nametags INSTANCE;
    private final /* synthetic */ Value<Boolean> pingColor;
    private final /* synthetic */ Value<Boolean> maxText;
    private final /* synthetic */ Value<Boolean> friends;
    private final /* synthetic */ Value<Boolean> armor;
    private final /* synthetic */ Value<Integer> Ogreen;
    private final /* synthetic */ Value<Boolean> max;
    private final /* synthetic */ RenderUtils renderUtils;
    private final /* synthetic */ Value<Boolean> outline;
    private final /* synthetic */ Value<Integer> blue;
    private final /* synthetic */ Value<Boolean> invisibles;
    private final /* synthetic */ Value<Integer> Oalpha;
    private final /* synthetic */ Value<Float> scale;
    private final /* synthetic */ Value<Boolean> ping;
    private final /* synthetic */ Value<Integer> Oblue;
    private final /* synthetic */ Value<Boolean> health;
    private final /* synthetic */ Value<Integer> green;
    private final /* synthetic */ Value<Integer> Ored;
    private final /* synthetic */ Value<Boolean> item;
    private final /* synthetic */ Value<Boolean> reversed;
    private final /* synthetic */ Value<Boolean> pops;
    
    public void drawBorderRect(final float llllllllllllllllIlIllIIlllIlIlIl, final float llllllllllllllllIlIllIIlllIlIlII, final float llllllllllllllllIlIllIIlllIlIIll, final float llllllllllllllllIlIllIIlllIllIIl, final int llllllllllllllllIlIllIIlllIlIIIl, final int llllllllllllllllIlIllIIlllIlIIII, final float llllllllllllllllIlIllIIlllIIllll) {
        drawGuiRect(llllllllllllllllIlIllIIlllIlIlIl + llllllllllllllllIlIllIIlllIIllll, llllllllllllllllIlIllIIlllIlIlII + llllllllllllllllIlIllIIlllIIllll, llllllllllllllllIlIllIIlllIlIIll - llllllllllllllllIlIllIIlllIIllll, llllllllllllllllIlIllIIlllIllIIl - llllllllllllllllIlIllIIlllIIllll, llllllllllllllllIlIllIIlllIlIIII);
        drawGuiRect(llllllllllllllllIlIllIIlllIlIlIl, llllllllllllllllIlIllIIlllIlIlII, llllllllllllllllIlIllIIlllIlIlIl + llllllllllllllllIlIllIIlllIIllll, llllllllllllllllIlIllIIlllIllIIl, llllllllllllllllIlIllIIlllIlIIIl);
        drawGuiRect(llllllllllllllllIlIllIIlllIlIlIl + llllllllllllllllIlIllIIlllIIllll, llllllllllllllllIlIllIIlllIlIlII, llllllllllllllllIlIllIIlllIlIIll, llllllllllllllllIlIllIIlllIlIlII + llllllllllllllllIlIllIIlllIIllll, llllllllllllllllIlIllIIlllIlIIIl);
        drawGuiRect(llllllllllllllllIlIllIIlllIlIlIl + llllllllllllllllIlIllIIlllIIllll, llllllllllllllllIlIllIIlllIllIIl - llllllllllllllllIlIllIIlllIIllll, llllllllllllllllIlIllIIlllIlIIll, llllllllllllllllIlIllIIlllIllIIl, llllllllllllllllIlIllIIlllIlIIIl);
        drawGuiRect(llllllllllllllllIlIllIIlllIlIIll - llllllllllllllllIlIllIIlllIIllll, llllllllllllllllIlIllIIlllIlIlII + llllllllllllllllIlIllIIlllIIllll, llllllllllllllllIlIllIIlllIlIIll, llllllllllllllllIlIllIIlllIllIIl - llllllllllllllllIlIllIIlllIIllll, llllllllllllllllIlIllIIlllIlIIIl);
    }
    
    public float getNametagSize(final EntityLivingBase llllllllllllllllIlIllIIllllIlIlI) {
        final ScaledResolution llllllllllllllllIlIllIIllllIlIIl = new ScaledResolution(Nametags.mc);
        final double llllllllllllllllIlIllIIllllIlIII = llllllllllllllllIlIllIIllllIlIIl.getScaleFactor() / Math.pow(llllllllllllllllIlIllIIllllIlIIl.getScaleFactor(), 2.0);
        return (float)llllllllllllllllIlIllIIllllIlIII + Nametags.mc.player.getDistance((Entity)llllllllllllllllIlIllIIllllIlIlI) / 7.0f;
    }
    
    public void renderEnchantText(final EntityPlayer llllllllllllllllIlIllIIIlIllllll, final ItemStack llllllllllllllllIlIllIIIlIllIlll, final int llllllllllllllllIlIllIIIlIllIllI, final int llllllllllllllllIlIllIIIlIllllII) {
        int llllllllllllllllIlIllIIIlIlllIll = llllllllllllllllIlIllIIIlIllllII;
        int llllllllllllllllIlIllIIIlIlllIlI = llllllllllllllllIlIllIIIlIllllII;
        if ((llllllllllllllllIlIllIIIlIllIlll.getItem() instanceof ItemArmor || llllllllllllllllIlIllIIIlIllIlll.getItem() instanceof ItemSword || llllllllllllllllIlIllIIIlIllIlll.getItem() instanceof ItemTool) && this.durability.getValue()) {
            final float llllllllllllllllIlIllIIIllIIlIII = (llllllllllllllllIlIllIIIlIllIlll.getMaxDamage() - (float)llllllllllllllllIlIllIIIlIllIlll.getItemDamage()) / llllllllllllllllIlIllIIIlIllIlll.getMaxDamage();
            final float llllllllllllllllIlIllIIIllIIIlll = 1.0f - llllllllllllllllIlIllIIIllIIlIII;
            final int llllllllllllllllIlIllIIIllIIIllI = 100 - (int)(llllllllllllllllIlIllIIIllIIIlll * 100.0f);
            if (this.cf.getValue()) {
                Xulu.cFontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(llllllllllllllllIlIllIIIllIIIllI).append("%")), llllllllllllllllIlIllIIIlIllIllI * 2 + 4, llllllllllllllllIlIllIIIlIllllII - 10, ColourHolder.toHex((int)(llllllllllllllllIlIllIIIllIIIlll * 255.0f), (int)(llllllllllllllllIlIllIIIllIIlIII * 255.0f), 0));
            }
            else {
                Nametags.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(llllllllllllllllIlIllIIIllIIIllI).append("%")), (float)(llllllllllllllllIlIllIIIlIllIllI * 2 + 4), (float)(llllllllllllllllIlIllIIIlIllllII - 10), ColourHolder.toHex((int)(llllllllllllllllIlIllIIIllIIIlll * 255.0f), (int)(llllllllllllllllIlIllIIIllIIlIII * 255.0f), 0));
            }
        }
        if (this.max.getValue() && this.isMaxEnchants(llllllllllllllllIlIllIIIlIllIlll)) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f, 1.0f, 0.0f);
            if (this.maxText.getValue()) {
                if (this.cf.getValue()) {
                    Xulu.cFontRenderer.drawStringWithShadow("Max", llllllllllllllllIlIllIIIlIllIllI * 2 + 7, llllllllllllllllIlIllIIIlIlllIlI + 24, ColorUtils.Colors.RED);
                }
                else {
                    Nametags.mc.fontRenderer.drawStringWithShadow("Max", (float)(llllllllllllllllIlIllIIIlIllIllI * 2 + 7), (float)(llllllllllllllllIlIllIIIlIlllIlI + 24), ColorUtils.Colors.RED);
                }
            }
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
            return;
        }
        final NBTTagList llllllllllllllllIlIllIIIlIlllIIl = llllllllllllllllIlIllIIIlIllIlll.getEnchantmentTagList();
        if (llllllllllllllllIlIllIIIlIlllIIl != null) {
            for (int llllllllllllllllIlIllIIIllIIIIIl = 0; llllllllllllllllIlIllIIIllIIIIIl < llllllllllllllllIlIllIIIlIlllIIl.tagCount(); ++llllllllllllllllIlIllIIIllIIIIIl) {
                final short llllllllllllllllIlIllIIIllIIIlII = llllllllllllllllIlIllIIIlIlllIIl.getCompoundTagAt(llllllllllllllllIlIllIIIllIIIIIl).getShort("id");
                final short llllllllllllllllIlIllIIIllIIIIll = llllllllllllllllIlIllIIIlIlllIIl.getCompoundTagAt(llllllllllllllllIlIllIIIllIIIIIl).getShort("lvl");
                final Enchantment llllllllllllllllIlIllIIIllIIIIlI = Enchantment.getEnchantmentByID((int)llllllllllllllllIlIllIIIllIIIlII);
                if (llllllllllllllllIlIllIIIllIIIIlI != null) {
                    if (!llllllllllllllllIlIllIIIllIIIIlI.isCurse()) {
                        String llllllllllllllllIlIllIIIllIIIlIl = (llllllllllllllllIlIllIIIllIIIIll == 1) ? llllllllllllllllIlIllIIIllIIIIlI.getTranslatedName((int)llllllllllllllllIlIllIIIllIIIIll).substring(0, 3).toLowerCase() : String.valueOf(new StringBuilder().append(llllllllllllllllIlIllIIIllIIIIlI.getTranslatedName((int)llllllllllllllllIlIllIIIllIIIIll).substring(0, 2).toLowerCase()).append(llllllllllllllllIlIllIIIllIIIIll));
                        llllllllllllllllIlIllIIIllIIIlIl = String.valueOf(new StringBuilder().append(llllllllllllllllIlIllIIIllIIIlIl.substring(0, 1).toUpperCase()).append(llllllllllllllllIlIllIIIllIIIlIl.substring(1)));
                        GL11.glPushMatrix();
                        GL11.glScalef(1.0f, 1.0f, 0.0f);
                        if (this.cf.getValue()) {
                            Xulu.cFontRenderer.drawStringWithShadow(llllllllllllllllIlIllIIIllIIIlIl, llllllllllllllllIlIllIIIlIllIllI * 2 + 3, llllllllllllllllIlIllIIIlIlllIlI, -1);
                        }
                        else {
                            Nametags.mc.fontRenderer.drawStringWithShadow(llllllllllllllllIlIllIIIllIIIlIl, (float)(llllllllllllllllIlIllIIIlIllIllI * 2 + 3), (float)llllllllllllllllIlIllIIIlIlllIlI, -1);
                        }
                        GL11.glScalef(1.0f, 1.0f, 1.0f);
                        GL11.glPopMatrix();
                        llllllllllllllllIlIllIIIlIlllIll += 8;
                        llllllllllllllllIlIllIIIlIlllIlI += 8;
                    }
                }
            }
        }
    }
    
    public static void drawGuiRect(final double llllllllllllllllIlIllIIllIllllII, final double llllllllllllllllIlIllIIlllIIIlII, final double llllllllllllllllIlIllIIllIlllIlI, final double llllllllllllllllIlIllIIlllIIIIlI, final int llllllllllllllllIlIllIIlllIIIIIl) {
        final float llllllllllllllllIlIllIIlllIIIIII = (llllllllllllllllIlIllIIlllIIIIIl >> 24 & 0xFF) / 255.0f;
        final float llllllllllllllllIlIllIIllIllllll = (llllllllllllllllIlIllIIlllIIIIIl >> 16 & 0xFF) / 255.0f;
        final float llllllllllllllllIlIllIIllIlllllI = (llllllllllllllllIlIllIIlllIIIIIl >> 8 & 0xFF) / 255.0f;
        final float llllllllllllllllIlIllIIllIllllIl = (llllllllllllllllIlIllIIlllIIIIIl & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(llllllllllllllllIlIllIIllIllllll, llllllllllllllllIlIllIIllIlllllI, llllllllllllllllIlIllIIllIllllIl, llllllllllllllllIlIllIIlllIIIIII);
        GL11.glBegin(7);
        GL11.glVertex2d(llllllllllllllllIlIllIIllIlllIlI, llllllllllllllllIlIllIIlllIIIlII);
        GL11.glVertex2d(llllllllllllllllIlIllIIllIllllII, llllllllllllllllIlIllIIlllIIIlII);
        GL11.glVertex2d(llllllllllllllllIlIllIIllIllllII, llllllllllllllllIlIllIIlllIIIIlI);
        GL11.glVertex2d(llllllllllllllllIlIllIIllIlllIlI, llllllllllllllllIlIllIIlllIIIIlI);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public String getShortName(final String llllllllllllllllIlIllIlIIlIlllll) {
        if (llllllllllllllllIlIllIlIIlIlllll.equalsIgnoreCase("survival")) {
            return "S";
        }
        if (llllllllllllllllIlIllIlIIlIlllll.equalsIgnoreCase("creative")) {
            return "C";
        }
        if (llllllllllllllllIlIllIlIIlIlllll.equalsIgnoreCase("adventure")) {
            return "A";
        }
        if (llllllllllllllllIlIllIlIIlIlllll.equalsIgnoreCase("spectator")) {
            return "SP";
        }
        return "NONE";
    }
    
    public String getHealth(final float llllllllllllllllIlIllIlIIlIllIll) {
        if (llllllllllllllllIlIllIlIIlIllIll > 18.0f) {
            return "a";
        }
        if (llllllllllllllllIlIllIlIIlIllIll > 16.0f) {
            return "2";
        }
        if (llllllllllllllllIlIllIlIIlIllIll > 12.0f) {
            return "e";
        }
        if (llllllllllllllllIlIllIlIIlIllIll > 8.0f) {
            return "6";
        }
        if (llllllllllllllllIlIllIlIIlIllIll > 5.0f) {
            return "c";
        }
        return "4";
    }
    
    public static void fakeGuiRect(double llllllllllllllllIlIllIIllIIllIll, double llllllllllllllllIlIllIIllIIllIlI, double llllllllllllllllIlIllIIllIIllIIl, double llllllllllllllllIlIllIIllIIllIII, final int llllllllllllllllIlIllIIllIIlIlll) {
        if (llllllllllllllllIlIllIIllIIllIll < llllllllllllllllIlIllIIllIIllIIl) {
            final double llllllllllllllllIlIllIIllIlIlIII = llllllllllllllllIlIllIIllIIllIll;
            llllllllllllllllIlIllIIllIIllIll = llllllllllllllllIlIllIIllIIllIIl;
            llllllllllllllllIlIllIIllIIllIIl = llllllllllllllllIlIllIIllIlIlIII;
        }
        if (llllllllllllllllIlIllIIllIIllIlI < llllllllllllllllIlIllIIllIIllIII) {
            final double llllllllllllllllIlIllIIllIlIIlll = llllllllllllllllIlIllIIllIIllIlI;
            llllllllllllllllIlIllIIllIIllIlI = llllllllllllllllIlIllIIllIIllIII;
            llllllllllllllllIlIllIIllIIllIII = llllllllllllllllIlIllIIllIlIIlll;
        }
        final float llllllllllllllllIlIllIIllIlIIIIl = (llllllllllllllllIlIllIIllIIlIlll >> 24 & 0xFF) / 255.0f;
        final float llllllllllllllllIlIllIIllIlIIIII = (llllllllllllllllIlIllIIllIIlIlll >> 16 & 0xFF) / 255.0f;
        final float llllllllllllllllIlIllIIllIIlllll = (llllllllllllllllIlIllIIllIIlIlll >> 8 & 0xFF) / 255.0f;
        final float llllllllllllllllIlIllIIllIIllllI = (llllllllllllllllIlIllIIllIIlIlll & 0xFF) / 255.0f;
        final Tessellator llllllllllllllllIlIllIIllIIlllIl = Tessellator.getInstance();
        final BufferBuilder llllllllllllllllIlIllIIllIIlllII = llllllllllllllllIlIllIIllIIlllIl.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(llllllllllllllllIlIllIIllIlIIIII, llllllllllllllllIlIllIIllIIlllll, llllllllllllllllIlIllIIllIIllllI, llllllllllllllllIlIllIIllIlIIIIl);
        llllllllllllllllIlIllIIllIIlllII.begin(7, DefaultVertexFormats.POSITION);
        llllllllllllllllIlIllIIllIIlllII.pos(llllllllllllllllIlIllIIllIIllIll, llllllllllllllllIlIllIIllIIllIII, 0.0).endVertex();
        llllllllllllllllIlIllIIllIIlllII.pos(llllllllllllllllIlIllIIllIIllIIl, llllllllllllllllIlIllIIllIIllIII, 0.0).endVertex();
        llllllllllllllllIlIllIIllIIlllII.pos(llllllllllllllllIlIllIIllIIllIIl, llllllllllllllllIlIllIIllIIllIlI, 0.0).endVertex();
        llllllllllllllllIlIllIIllIIlllII.pos(llllllllllllllllIlIllIIllIIllIll, llllllllllllllllIlIllIIllIIllIlI, 0.0).endVertex();
        llllllllllllllllIlIllIIllIIlllIl.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public void renderItem(final EntityPlayer llllllllllllllllIlIllIIlIllIllII, final ItemStack llllllllllllllllIlIllIIlIlllIIlI, final int llllllllllllllllIlIllIIlIllIlIlI, final int llllllllllllllllIlIllIIlIllIlIIl, final int llllllllllllllllIlIllIIlIllIllll, final boolean llllllllllllllllIlIllIIlIllIIlll) {
        GL11.glPushMatrix();
        GL11.glDepthMask(true);
        GlStateManager.clear(256);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        Nametags.mc.getRenderItem().zLevel = -100.0f;
        GlStateManager.scale(1.0f, 1.0f, 0.01f);
        Nametags.mc.getRenderItem().renderItemAndEffectIntoGUI(llllllllllllllllIlIllIIlIlllIIlI, llllllllllllllllIlIllIIlIllIlIlI, llllllllllllllllIlIllIIlIllIlIIl / 2 - 12);
        if (this.durability.getValue()) {
            Nametags.mc.getRenderItem().renderItemOverlays(Nametags.mc.fontRenderer, llllllllllllllllIlIllIIlIlllIIlI, llllllllllllllllIlIllIIlIllIlIlI, llllllllllllllllIlIllIIlIllIlIIl / 2 - 12);
        }
        Nametags.mc.getRenderItem().zLevel = 0.0f;
        GlStateManager.scale(1.0f, 1.0f, 1.0f);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableLighting();
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.disableDepth();
        this.renderEnchantText(llllllllllllllllIlIllIIlIllIllII, llllllllllllllllIlIllIIlIlllIIlI, llllllllllllllllIlIllIIlIllIlIlI, llllllllllllllllIlIllIIlIllIlIIl - 18);
        if (!this.shownItem && this.item.getValue() && llllllllllllllllIlIllIIlIllIIlll) {
            if (this.cf.getValue()) {
                Xulu.cFontRenderer.drawStringWithShadow(llllllllllllllllIlIllIIlIlllIIlI.getDisplayName().equalsIgnoreCase("Air") ? "" : llllllllllllllllIlIllIIlIlllIIlI.getDisplayName(), llllllllllllllllIlIllIIlIllIllll * 2 + 95 - Xulu.cFontRenderer.getStringWidth(llllllllllllllllIlIllIIlIlllIIlI.getDisplayName()) / 2, llllllllllllllllIlIllIIlIllIlIIl - 37, -1);
            }
            else {
                Nametags.mc.fontRenderer.drawStringWithShadow(llllllllllllllllIlIllIIlIlllIIlI.getDisplayName().equalsIgnoreCase("Air") ? "" : llllllllllllllllIlIllIIlIlllIIlI.getDisplayName(), (float)(llllllllllllllllIlIllIIlIllIllll * 2 + 95 - Nametags.mc.fontRenderer.getStringWidth(llllllllllllllllIlIllIIlIlllIIlI.getDisplayName()) / 2), (float)(llllllllllllllllIlIllIIlIllIlIIl - 37), -1);
            }
            this.shownItem = true;
        }
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GL11.glPopMatrix();
    }
    
    @Override
    public void onWorldRender(final RenderEvent llllllllllllllllIlIllIlIIlllIIlI) {
        if (Nametags.mc.player == null) {
            return;
        }
        final double llllllllllllllllIlIllIlIIlllIIIl = Nametags.mc.player.lastTickPosX + (Nametags.mc.player.posX - Nametags.mc.player.lastTickPosX) * llllllllllllllllIlIllIlIIlllIIlI.getPartialTicks();
        final double llllllllllllllllIlIllIlIIlllIIII = Nametags.mc.player.lastTickPosY + (Nametags.mc.player.posY - Nametags.mc.player.lastTickPosY) * llllllllllllllllIlIllIlIIlllIIlI.getPartialTicks();
        final double llllllllllllllllIlIllIlIIllIllll = Nametags.mc.player.lastTickPosZ + (Nametags.mc.player.posZ - Nametags.mc.player.lastTickPosZ) * llllllllllllllllIlIllIlIIlllIIlI.getPartialTicks();
        this.camera.setPosition(llllllllllllllllIlIllIlIIlllIIIl, llllllllllllllllIlIllIlIIlllIIII, llllllllllllllllIlIllIlIIllIllll);
        final List<EntityPlayer> llllllllllllllllIlIllIlIIllIlllI = new ArrayList<EntityPlayer>(Nametags.mc.world.playerEntities);
        llllllllllllllllIlIllIlIIllIlllI.sort(Comparator.comparing(llllllllllllllllIlIllIIIlIlIlIll -> Nametags.mc.player.getDistance((Entity)llllllllllllllllIlIllIIIlIlIlIll)).reversed());
        for (final EntityPlayer llllllllllllllllIlIllIlIIlllIlII : llllllllllllllllIlIllIlIIllIlllI) {
            final NetworkPlayerInfo llllllllllllllllIlIllIlIIlllIlIl = Nametags.mc.player.connection.getPlayerInfo(llllllllllllllllIlIllIlIIlllIlII.getGameProfile().getId());
            if (!this.camera.isBoundingBoxInFrustum(llllllllllllllllIlIllIlIIlllIlII.getEntityBoundingBox())) {
                continue;
            }
            if (llllllllllllllllIlIllIlIIlllIlII == Nametags.mc.getRenderViewEntity() || !llllllllllllllllIlIllIlIIlllIlII.isEntityAlive()) {
                continue;
            }
            final double llllllllllllllllIlIllIlIIllllIII = llllllllllllllllIlIllIlIIlllIlII.lastTickPosX + (llllllllllllllllIlIllIlIIlllIlII.posX - llllllllllllllllIlIllIlIIlllIlII.lastTickPosX) * Nametags.mc.timer.renderPartialTicks - Nametags.mc.renderManager.renderPosX;
            final double llllllllllllllllIlIllIlIIlllIlll = llllllllllllllllIlIllIlIIlllIlII.lastTickPosY + (llllllllllllllllIlIllIlIIlllIlII.posY - llllllllllllllllIlIllIlIIlllIlII.lastTickPosY) * Nametags.mc.timer.renderPartialTicks - Nametags.mc.renderManager.renderPosY;
            final double llllllllllllllllIlIllIlIIlllIllI = llllllllllllllllIlIllIlIIlllIlII.lastTickPosZ + (llllllllllllllllIlIllIlIIlllIlII.posZ - llllllllllllllllIlIllIlIIlllIlII.lastTickPosZ) * Nametags.mc.timer.renderPartialTicks - Nametags.mc.renderManager.renderPosZ;
            if (llllllllllllllllIlIllIlIIlllIlIl != null && this.getShortName(llllllllllllllllIlIllIlIIlllIlIl.getGameType().getName()).equalsIgnoreCase("SP") && !this.invisibles.getValue()) {
                continue;
            }
            if (llllllllllllllllIlIllIlIIlllIlII.getName().startsWith("Body #")) {
                continue;
            }
            this.renderNametag(llllllllllllllllIlIllIlIIlllIlII, llllllllllllllllIlIllIlIIllllIII, llllllllllllllllIlIllIlIIlllIlll, llllllllllllllllIlIllIlIIlllIllI);
        }
    }
    
    public String getPing(final float llllllllllllllllIlIllIlIIlIlIlll) {
        if (llllllllllllllllIlIllIlIIlIlIlll > 200.0f) {
            return "c";
        }
        if (llllllllllllllllIlIllIlIIlIlIlll > 100.0f) {
            return "e";
        }
        return "a";
    }
    
    public boolean isMaxEnchants(final ItemStack llllllllllllllllIlIllIIlIlIlIIII) {
        final NBTTagList llllllllllllllllIlIllIIlIlIIllll = llllllllllllllllIlIllIIlIlIlIIII.getEnchantmentTagList();
        final List<String> llllllllllllllllIlIllIIlIlIIlllI = new ArrayList<String>();
        int llllllllllllllllIlIllIIlIlIIllIl = 0;
        if (llllllllllllllllIlIllIIlIlIIllll == null) {
            return false;
        }
        for (int llllllllllllllllIlIllIIlIlIllIll = 0; llllllllllllllllIlIllIIlIlIllIll < llllllllllllllllIlIllIIlIlIIllll.tagCount(); ++llllllllllllllllIlIllIIlIlIllIll) {
            final short llllllllllllllllIlIllIIlIlIllllI = llllllllllllllllIlIllIIlIlIIllll.getCompoundTagAt(llllllllllllllllIlIllIIlIlIllIll).getShort("id");
            final short llllllllllllllllIlIllIIlIlIlllIl = llllllllllllllllIlIllIIlIlIIllll.getCompoundTagAt(llllllllllllllllIlIllIIlIlIllIll).getShort("lvl");
            final Enchantment llllllllllllllllIlIllIIlIlIlllII = Enchantment.getEnchantmentByID((int)llllllllllllllllIlIllIIlIlIllllI);
            if (llllllllllllllllIlIllIIlIlIlllII != null) {
                llllllllllllllllIlIllIIlIlIIlllI.add(llllllllllllllllIlIllIIlIlIlllII.getTranslatedName((int)llllllllllllllllIlIllIIlIlIlllIl));
            }
        }
        if (llllllllllllllllIlIllIIlIlIlIIII.getItem() == Items.DIAMOND_HELMET) {
            final int llllllllllllllllIlIllIIlIlIllIIl = 5;
            for (final String llllllllllllllllIlIllIIlIlIllIlI : llllllllllllllllIlIllIIlIlIIlllI) {
                if (llllllllllllllllIlIllIIlIlIllIlI.equalsIgnoreCase("Protection IV")) {
                    ++llllllllllllllllIlIllIIlIlIIllIl;
                }
                if (llllllllllllllllIlIllIIlIlIllIlI.equalsIgnoreCase("Respiration III")) {
                    ++llllllllllllllllIlIllIIlIlIIllIl;
                }
                if (llllllllllllllllIlIllIIlIlIllIlI.equalsIgnoreCase("Aqua Affinity")) {
                    ++llllllllllllllllIlIllIIlIlIIllIl;
                }
                if (llllllllllllllllIlIllIIlIlIllIlI.equalsIgnoreCase("Unbreaking III")) {
                    ++llllllllllllllllIlIllIIlIlIIllIl;
                }
                if (llllllllllllllllIlIllIIlIlIllIlI.equalsIgnoreCase("Mending")) {
                    ++llllllllllllllllIlIllIIlIlIIllIl;
                }
            }
            return llllllllllllllllIlIllIIlIlIIllIl >= llllllllllllllllIlIllIIlIlIllIIl;
        }
        if (llllllllllllllllIlIllIIlIlIlIIII.getItem() == Items.DIAMOND_CHESTPLATE) {
            final int llllllllllllllllIlIllIIlIlIlIlll = 3;
            for (final String llllllllllllllllIlIllIIlIlIllIII : llllllllllllllllIlIllIIlIlIIlllI) {
                if (llllllllllllllllIlIllIIlIlIllIII.equalsIgnoreCase("Protection IV")) {
                    ++llllllllllllllllIlIllIIlIlIIllIl;
                }
                if (llllllllllllllllIlIllIIlIlIllIII.equalsIgnoreCase("Unbreaking III")) {
                    ++llllllllllllllllIlIllIIlIlIIllIl;
                }
                if (llllllllllllllllIlIllIIlIlIllIII.equalsIgnoreCase("Mending")) {
                    ++llllllllllllllllIlIllIIlIlIIllIl;
                }
            }
            return llllllllllllllllIlIllIIlIlIIllIl >= llllllllllllllllIlIllIIlIlIlIlll;
        }
        if (llllllllllllllllIlIllIIlIlIlIIII.getItem() == Items.DIAMOND_LEGGINGS) {
            final int llllllllllllllllIlIllIIlIlIlIlIl = 3;
            for (final String llllllllllllllllIlIllIIlIlIlIllI : llllllllllllllllIlIllIIlIlIIlllI) {
                if (llllllllllllllllIlIllIIlIlIlIllI.equalsIgnoreCase("Blast Protection IV")) {
                    ++llllllllllllllllIlIllIIlIlIIllIl;
                }
                if (llllllllllllllllIlIllIIlIlIlIllI.equalsIgnoreCase("Unbreaking III")) {
                    ++llllllllllllllllIlIllIIlIlIIllIl;
                }
                if (llllllllllllllllIlIllIIlIlIlIllI.equalsIgnoreCase("Mending")) {
                    ++llllllllllllllllIlIllIIlIlIIllIl;
                }
            }
            return llllllllllllllllIlIllIIlIlIIllIl >= llllllllllllllllIlIllIIlIlIlIlIl;
        }
        if (llllllllllllllllIlIllIIlIlIlIIII.getItem() == Items.DIAMOND_BOOTS) {
            final int llllllllllllllllIlIllIIlIlIlIIll = 5;
            for (final String llllllllllllllllIlIllIIlIlIlIlII : llllllllllllllllIlIllIIlIlIIlllI) {
                if (llllllllllllllllIlIllIIlIlIlIlII.equalsIgnoreCase("Protection IV")) {
                    ++llllllllllllllllIlIllIIlIlIIllIl;
                }
                if (llllllllllllllllIlIllIIlIlIlIlII.equalsIgnoreCase("Feather Falling IV")) {
                    ++llllllllllllllllIlIllIIlIlIIllIl;
                }
                if (llllllllllllllllIlIllIIlIlIlIlII.equalsIgnoreCase("Depth Strider III")) {
                    ++llllllllllllllllIlIllIIlIlIIllIl;
                }
                if (llllllllllllllllIlIllIIlIlIlIlII.equalsIgnoreCase("Unbreaking III")) {
                    ++llllllllllllllllIlIllIIlIlIIllIl;
                }
                if (llllllllllllllllIlIllIIlIlIlIlII.equalsIgnoreCase("Mending")) {
                    ++llllllllllllllllIlIllIIlIlIIllIl;
                }
            }
            return llllllllllllllllIlIllIIlIlIIllIl >= llllllllllllllllIlIllIIlIlIlIIll;
        }
        return false;
    }
    
    public static void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    
    public Nametags() {
        super("NameTags", "Enhances nametags", 0, Category.RENDER, true);
        this.renderUtils = new RenderUtils();
        this.outline = this.register(new Value<Boolean>("Outline", this, true));
        this.Orainbow = this.register(new Value<Boolean>("Outline Rainbow", this, false));
        this.Ored = this.register(new Value<Integer>("Outline Red", this, 0, 0, 255));
        this.Ogreen = this.register(new Value<Integer>("Outline Green", this, 0, 0, 255));
        this.Oblue = this.register(new Value<Integer>("Outline Blue", this, 0, 0, 255));
        this.Oalpha = this.register(new Value<Integer>("Outline Alpha", this, 150, 0, 255));
        this.Owidth = this.register(new Value<Float>("Outline Width", this, 1.5f, 0.0f, 3.0f));
        this.reversed = this.register(new Value<Boolean>("Reversed", this, false));
        this.reversedHand = this.register(new Value<Boolean>("Reversed Hand", this, false));
        this.cf = this.register(new Value<Boolean>("Custom Font", this, false));
        this.max = this.register(new Value<Boolean>("Show Max Enchants", this, true));
        this.maxText = this.register(new Value<Boolean>("Show Max Text", this, true));
        this.health = this.register(new Value<Boolean>("Health", this, true));
        this.gameMode = this.register(new Value<Boolean>("GameMode", this, true));
        this.ping = this.register(new Value<Boolean>("Ping", this, true));
        this.pingColor = this.register(new Value<Boolean>("Ping Color", this, true));
        this.armor = this.register(new Value<Boolean>("Armor", this, true));
        this.durability = this.register(new Value<Boolean>("Durability", this, true));
        this.item = this.register(new Value<Boolean>("Item Name", this, true));
        this.invisibles = this.register(new Value<Boolean>("Invisibles", this, false));
        this.pops = this.register(new Value<Boolean>("Pop Count", this, true));
        this.scale = this.register(new Value<Float>("Scale", this, 0.05f, 0.01f, 0.09f));
        this.height = this.register(new Value<Float>("Height", this, 2.5f, 0.5f, 5.0f));
        this.friendMode = this.register(new Value<String>("Friend Mode", this, "Text", new ArrayList<String>(Arrays.asList("Text", "Box"))));
        this.friends = this.register(new Value<Boolean>("Friends", this, true));
        this.enemies = this.register(new Value<Boolean>("Enemies", this, true));
        this.red = this.register(new Value<Integer>("Friend Red", this, 0, 0, 255));
        this.green = this.register(new Value<Integer>("Friend Green", this, 130, 0, 255));
        this.blue = this.register(new Value<Integer>("Friend Blue", this, 130, 0, 255));
        this.Ered = this.register(new Value<Integer>("Enemy Red", this, 200, 0, 255));
        this.Egreen = this.register(new Value<Integer>("Enemy Green", this, 0, 0, 255));
        this.Eblue = this.register(new Value<Integer>("Enemy Blue", this, 0, 0, 255));
        this.camera = (ICamera)new Frustum();
        Nametags.INSTANCE = this;
    }
    
    private String getName(final EntityPlayer llllllllllllllllIlIllIlIIlIlIIll) {
        if (Nicknames.hasNickname(llllllllllllllllIlIllIlIIlIlIIll.getName())) {
            return Nicknames.getNickname(llllllllllllllllIlIllIlIIlIlIIll.getName());
        }
        return llllllllllllllllIlIllIlIIlIlIIll.getName();
    }
    
    private void renderDurabilityText(final EntityPlayer llllllllllllllllIlIllIIlIIIIlllI, final ItemStack llllllllllllllllIlIllIIlIIIIIlIl, final int llllllllllllllllIlIllIIlIIIIIIll, final int llllllllllllllllIlIllIIlIIIIlIIl) {
        GL11.glPushMatrix();
        GL11.glDepthMask(true);
        GlStateManager.clear(256);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.scale(1.0f, 1.0f, 0.01f);
        GlStateManager.scale(1.0f, 1.0f, 1.0f);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableLighting();
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.disableDepth();
        if (llllllllllllllllIlIllIIlIIIIIlIl.getItem() instanceof ItemArmor || llllllllllllllllIlIllIIlIIIIIlIl.getItem() instanceof ItemSword || llllllllllllllllIlIllIIlIIIIIlIl.getItem() instanceof ItemTool) {
            final float llllllllllllllllIlIllIIlIIIlIlIl = (llllllllllllllllIlIllIIlIIIIIlIl.getMaxDamage() - (float)llllllllllllllllIlIllIIlIIIIIlIl.getItemDamage()) / llllllllllllllllIlIllIIlIIIIIlIl.getMaxDamage();
            final float llllllllllllllllIlIllIIlIIIlIIll = 1.0f - llllllllllllllllIlIllIIlIIIlIlIl;
            final int llllllllllllllllIlIllIIlIIIlIIlI = 100 - (int)(llllllllllllllllIlIllIIlIIIlIIll * 100.0f);
            if (this.cf.getValue()) {
                Xulu.cFontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(llllllllllllllllIlIllIIlIIIlIIlI).append("%")), llllllllllllllllIlIllIIlIIIIIIll * 2 + 4, llllllllllllllllIlIllIIlIIIIlIIl - 10, ColourHolder.toHex((int)(llllllllllllllllIlIllIIlIIIlIIll * 255.0f), (int)(llllllllllllllllIlIllIIlIIIlIlIl * 255.0f), 0));
            }
            else {
                Nametags.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(llllllllllllllllIlIllIIlIIIlIIlI).append("%")), (float)(llllllllllllllllIlIllIIlIIIIIIll * 2 + 4), (float)(llllllllllllllllIlIllIIlIIIIlIIl - 10), ColourHolder.toHex((int)(llllllllllllllllIlIllIIlIIIlIIll * 255.0f), (int)(llllllllllllllllIlIllIIlIIIlIlIl * 255.0f), 0));
            }
        }
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GL11.glPopMatrix();
    }
    
    public static void drawBorderedRect(final double llllllllllllllllIlIllIIllIIIIIlI, final double llllllllllllllllIlIllIIllIIIIIIl, final double llllllllllllllllIlIllIIllIIIIlll, final double llllllllllllllllIlIllIIlIlllllll, final double llllllllllllllllIlIllIIllIIIIlIl, final int llllllllllllllllIlIllIIllIIIIlII, final int llllllllllllllllIlIllIIllIIIIIll) {
        GlStateManager.pushMatrix();
        enableGL2D();
        fakeGuiRect(llllllllllllllllIlIllIIllIIIIIlI + llllllllllllllllIlIllIIllIIIIlIl, llllllllllllllllIlIllIIllIIIIIIl + llllllllllllllllIlIllIIllIIIIlIl, llllllllllllllllIlIllIIllIIIIlll - llllllllllllllllIlIllIIllIIIIlIl, llllllllllllllllIlIllIIlIlllllll - llllllllllllllllIlIllIIllIIIIlIl, llllllllllllllllIlIllIIllIIIIlII);
        fakeGuiRect(llllllllllllllllIlIllIIllIIIIIlI + llllllllllllllllIlIllIIllIIIIlIl, llllllllllllllllIlIllIIllIIIIIIl, llllllllllllllllIlIllIIllIIIIlll - llllllllllllllllIlIllIIllIIIIlIl, llllllllllllllllIlIllIIllIIIIIIl + llllllllllllllllIlIllIIllIIIIlIl, llllllllllllllllIlIllIIllIIIIIll);
        fakeGuiRect(llllllllllllllllIlIllIIllIIIIIlI, llllllllllllllllIlIllIIllIIIIIIl, llllllllllllllllIlIllIIllIIIIIlI + llllllllllllllllIlIllIIllIIIIlIl, llllllllllllllllIlIllIIlIlllllll, llllllllllllllllIlIllIIllIIIIIll);
        fakeGuiRect(llllllllllllllllIlIllIIllIIIIlll - llllllllllllllllIlIllIIllIIIIlIl, llllllllllllllllIlIllIIllIIIIIIl, llllllllllllllllIlIllIIllIIIIlll, llllllllllllllllIlIllIIlIlllllll, llllllllllllllllIlIllIIllIIIIIll);
        fakeGuiRect(llllllllllllllllIlIllIIllIIIIIlI + llllllllllllllllIlIllIIllIIIIlIl, llllllllllllllllIlIllIIlIlllllll - llllllllllllllllIlIllIIllIIIIlIl, llllllllllllllllIlIllIIllIIIIlll - llllllllllllllllIlIllIIllIIIIlIl, llllllllllllllllIlIllIIlIlllllll, llllllllllllllllIlIllIIllIIIIIll);
        disableGL2D();
        GlStateManager.popMatrix();
    }
    
    public void renderNametag(final EntityPlayer llllllllllllllllIlIllIlIIIIIIlIl, final double llllllllllllllllIlIllIlIIIIlIlIl, final double llllllllllllllllIlIllIlIIIIlIlII, final double llllllllllllllllIlIllIlIIIIlIIll) {
        final int llllllllllllllllIlIllIlIIIIlIIlI = 0;
        this.shownItem = false;
        GlStateManager.pushMatrix();
        final FontRenderer llllllllllllllllIlIllIlIIIIlIIIl = Wrapper.getMinecraft().fontRenderer;
        final NetworkPlayerInfo llllllllllllllllIlIllIlIIIIlIIII = Nametags.mc.player.connection.getPlayerInfo(llllllllllllllllIlIllIlIIIIIIlIl.getGameProfile().getId());
        final boolean llllllllllllllllIlIllIlIIIIIllll = Friends.isFriend(llllllllllllllllIlIllIlIIIIIIlIl.getName()) && this.friends.getValue();
        final boolean llllllllllllllllIlIllIlIIIIIlllI = Enemies.isEnemy(llllllllllllllllIlIllIlIIIIIIlIl.getName()) && this.enemies.getValue();
        String llllllllllllllllIlIllIlIIIIIllIl = String.valueOf(new StringBuilder().append(((llllllllllllllllIlIllIlIIIIIllll || llllllllllllllllIlIllIlIIIIIlllI) && this.friendMode.getValue().equalsIgnoreCase("Text")) ? String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append(llllllllllllllllIlIllIlIIIIIllll ? "b" : "c")) : (llllllllllllllllIlIllIlIIIIIIlIl.isSneaking() ? String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append("9")) : String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append("r")))).append(this.getName(llllllllllllllllIlIllIlIIIIIIlIl)).append((this.gameMode.getValue() && llllllllllllllllIlIllIlIIIIlIIII != null) ? String.valueOf(new StringBuilder().append(" [").append(this.getShortName(llllllllllllllllIlIllIlIIIIlIIII.getGameType().getName())).append("]")) : "").append((this.ping.getValue() && llllllllllllllllIlIllIlIIIIlIIII != null) ? String.valueOf(new StringBuilder().append(" ").append(this.pingColor.getValue() ? String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append(this.getPing((float)llllllllllllllllIlIllIlIIIIlIIII.getResponseTime()))) : "").append(llllllllllllllllIlIllIlIIIIlIIII.getResponseTime()).append("ms")) : "").append(this.health.getValue() ? String.valueOf(new StringBuilder().append(" ").append(Command.SECTIONSIGN()).append(this.getHealth(llllllllllllllllIlIllIlIIIIIIlIl.getHealth() + llllllllllllllllIlIllIlIIIIIIlIl.getAbsorptionAmount())).append(MathHelper.ceil(llllllllllllllllIlIllIlIIIIIIlIl.getHealth() + llllllllllllllllIlIllIlIIIIIIlIl.getAbsorptionAmount()))) : "").append((PopCounter.INSTANCE.popMap.containsKey(llllllllllllllllIlIllIlIIIIIIlIl) && this.pops.getValue()) ? String.valueOf(new StringBuilder().append(" ").append(ChatFormatting.DARK_RED).append("-").append(PopCounter.INSTANCE.popMap.get(llllllllllllllllIlIllIlIIIIIIlIl))) : ""));
        llllllllllllllllIlIllIlIIIIIllIl = llllllllllllllllIlIllIlIIIIIllIl.replace(".0", "");
        final float llllllllllllllllIlIllIlIIIIIllII = Nametags.mc.player.getDistance((Entity)llllllllllllllllIlIllIlIIIIIIlIl);
        final float llllllllllllllllIlIllIlIIIIIlIll = ((llllllllllllllllIlIllIlIIIIIllII / 5.0f <= 2.0f) ? 2.0f : (llllllllllllllllIlIllIlIIIIIllII / 5.0f * (this.scale.getValue() * 10.0f + 1.0f))) * 2.5f * (this.scale.getValue() / 10.0f);
        final float llllllllllllllllIlIllIlIIIIIlIlI = this.scale.getValue() * this.getNametagSize((EntityLivingBase)llllllllllllllllIlIllIlIIIIIIlIl);
        GL11.glTranslated((double)(float)llllllllllllllllIlIllIlIIIIlIlIl, (float)llllllllllllllllIlIllIlIIIIlIlII + this.height.getValue() - (llllllllllllllllIlIllIlIIIIIIlIl.isSneaking() ? 0.4 : 0.0) + ((llllllllllllllllIlIllIlIIIIIllII / 5.0f > 2.0f) ? (llllllllllllllllIlIllIlIIIIIllII / 12.0f - 0.7) : 0.0), (double)(float)llllllllllllllllIlIllIlIIIIlIIll);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-Nametags.mc.renderManager.playerViewY, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(Nametags.mc.renderManager.playerViewX, (Nametags.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GL11.glScalef(-llllllllllllllllIlIllIlIIIIIlIll, -llllllllllllllllIlIllIlIIIIIlIll, llllllllllllllllIlIllIlIIIIIlIll);
        this.renderUtils.disableGlCap(2896, 2929);
        this.renderUtils.enableGlCap(3042);
        GL11.glBlendFunc(770, 771);
        int llllllllllllllllIlIllIlIIIIIlIIl = 0;
        if (this.cf.getValue()) {
            final int llllllllllllllllIlIllIlIIIllIIII = Xulu.cFontRenderer.getStringWidth(llllllllllllllllIlIllIlIIIIIllIl) / 2 + 1;
        }
        else {
            llllllllllllllllIlIllIlIIIIIlIIl = llllllllllllllllIlIllIlIIIIlIIIl.getStringWidth(llllllllllllllllIlIllIlIIIIIllIl) / 2;
        }
        final int llllllllllllllllIlIllIlIIIIIlIII = ((llllllllllllllllIlIllIlIIIIIllll || llllllllllllllllIlIllIlIIIIIlllI) && this.friendMode.getValue().equalsIgnoreCase("Box")) ? (llllllllllllllllIlIllIlIIIIIllll ? new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue()).getRGB() : new Color(this.Ered.getValue(), this.Egreen.getValue(), this.Eblue.getValue()).getRGB()) : ColorUtils.Colors.BLACK;
        int llllllllllllllllIlIllIlIIIIIIlll = new Color(this.Ored.getValue(), this.Ogreen.getValue(), this.Oblue.getValue(), this.Oalpha.getValue()).getRGB();
        if (this.Orainbow.getValue()) {
            llllllllllllllllIlIllIlIIIIIIlll = ColorUtils.changeAlpha(Xulu.rgb, this.Oalpha.getValue());
        }
        Gui.drawRect(-llllllllllllllllIlIllIlIIIIIlIIl - 2, 10, llllllllllllllllIlIllIlIIIIIlIIl + 1, 20, ColorUtils.changeAlpha(llllllllllllllllIlIllIlIIIIIlIII, 120));
        if (this.outline.getValue()) {
            XuluTessellator.drawOutlineLine(-llllllllllllllllIlIllIlIIIIIlIIl - 2, 10.0, llllllllllllllllIlIllIlIIIIIlIIl + 1, 20.0, this.Owidth.getValue(), llllllllllllllllIlIllIlIIIIIIlll);
        }
        if (this.cf.getValue()) {
            Xulu.cFontRenderer.drawStringWithShadow(llllllllllllllllIlIllIlIIIIIllIl, -llllllllllllllllIlIllIlIIIIIlIIl, 10.0, -1);
        }
        else {
            Nametags.mc.fontRenderer.drawStringWithShadow(llllllllllllllllIlIllIlIIIIIllIl, (float)(-llllllllllllllllIlIllIlIIIIIlIIl), 11.0f, -1);
        }
        if (this.armor.getValue()) {
            int llllllllllllllllIlIllIlIIIlIIlII = -6;
            int llllllllllllllllIlIllIlIIIlIIIll = 0;
            for (final ItemStack llllllllllllllllIlIllIlIIIlIllll : llllllllllllllllIlIllIlIIIIIIlIl.inventory.armorInventory) {
                if (llllllllllllllllIlIllIlIIIlIllll != null) {
                    llllllllllllllllIlIllIlIIIlIIlII -= 8;
                    if (llllllllllllllllIlIllIlIIIlIllll.getItem() == Items.AIR) {
                        continue;
                    }
                    ++llllllllllllllllIlIllIlIIIlIIIll;
                }
            }
            if (llllllllllllllllIlIllIlIIIIIIlIl.getHeldItemOffhand().getItem() != Items.AIR) {
                ++llllllllllllllllIlIllIlIIIlIIIll;
            }
            final int llllllllllllllllIlIllIlIIIlIIIlI = llllllllllllllllIlIllIlIIIlIIlII - 8;
            llllllllllllllllIlIllIlIIIlIIlII += 8 * (5 - llllllllllllllllIlIllIlIIIlIIIll) - ((llllllllllllllllIlIllIlIIIlIIIll == 0) ? 4 : 0);
            Label_1561: {
                Label_1540: {
                    if (this.reversedHand.getValue()) {
                        if (llllllllllllllllIlIllIlIIIIIIlIl.getHeldItemOffhand().getItem() == Items.AIR) {
                            break Label_1540;
                        }
                    }
                    else if (llllllllllllllllIlIllIlIIIIIIlIl.getHeldItemMainhand().getItem() == Items.AIR) {
                        break Label_1540;
                    }
                    llllllllllllllllIlIllIlIIIlIIlII -= 8;
                    if (this.reversedHand.getValue()) {
                        final ItemStack llllllllllllllllIlIllIlIIIlIlllI = llllllllllllllllIlIllIlIIIIIIlIl.getHeldItemOffhand().copy();
                        this.renderItem(llllllllllllllllIlIllIlIIIIIIlIl, llllllllllllllllIlIllIlIIIlIlllI, llllllllllllllllIlIllIlIIIlIIlII, -10, llllllllllllllllIlIllIlIIIlIIIlI, false);
                    }
                    else {
                        final ItemStack llllllllllllllllIlIllIlIIIlIllIl = llllllllllllllllIlIllIlIIIIIIlIl.getHeldItemMainhand().copy();
                        this.renderItem(llllllllllllllllIlIllIlIIIIIIlIl, llllllllllllllllIlIllIlIIIlIllIl, llllllllllllllllIlIllIlIIIlIIlII, -10, llllllllllllllllIlIllIlIIIlIIIlI, true);
                    }
                    llllllllllllllllIlIllIlIIIlIIlII += 16;
                    break Label_1561;
                }
                if (!this.reversedHand.getValue()) {
                    this.shownItem = true;
                }
            }
            if (this.reversed.getValue()) {
                for (int llllllllllllllllIlIllIlIIIlIlIlI = 0; llllllllllllllllIlIllIlIIIlIlIlI <= 3; ++llllllllllllllllIlIllIlIIIlIlIlI) {
                    final ItemStack llllllllllllllllIlIllIlIIIlIlIll = (ItemStack)llllllllllllllllIlIllIlIIIIIIlIl.inventory.armorInventory.get(llllllllllllllllIlIllIlIIIlIlIlI);
                    if (llllllllllllllllIlIllIlIIIlIlIll != null && llllllllllllllllIlIllIlIIIlIlIll.getItem() != Items.AIR) {
                        final ItemStack llllllllllllllllIlIllIlIIIlIllII = llllllllllllllllIlIllIlIIIlIlIll.copy();
                        this.renderItem(llllllllllllllllIlIllIlIIIIIIlIl, llllllllllllllllIlIllIlIIIlIllII, llllllllllllllllIlIllIlIIIlIIlII, -10, llllllllllllllllIlIllIlIIIlIIIlI, false);
                        llllllllllllllllIlIllIlIIIlIIlII += 16;
                    }
                }
            }
            else {
                for (int llllllllllllllllIlIllIlIIIlIIlll = 3; llllllllllllllllIlIllIlIIIlIIlll >= 0; --llllllllllllllllIlIllIlIIIlIIlll) {
                    final ItemStack llllllllllllllllIlIllIlIIIlIlIII = (ItemStack)llllllllllllllllIlIllIlIIIIIIlIl.inventory.armorInventory.get(llllllllllllllllIlIllIlIIIlIIlll);
                    if (llllllllllllllllIlIllIlIIIlIlIII != null && llllllllllllllllIlIllIlIIIlIlIII.getItem() != Items.AIR) {
                        final ItemStack llllllllllllllllIlIllIlIIIlIlIIl = llllllllllllllllIlIllIlIIIlIlIII.copy();
                        this.renderItem(llllllllllllllllIlIllIlIIIIIIlIl, llllllllllllllllIlIllIlIIIlIlIIl, llllllllllllllllIlIllIlIIIlIIlII, -10, llllllllllllllllIlIllIlIIIlIIIlI, false);
                        llllllllllllllllIlIllIlIIIlIIlII += 16;
                    }
                }
            }
            Label_1839: {
                if (this.reversedHand.getValue()) {
                    if (llllllllllllllllIlIllIlIIIIIIlIl.getHeldItemMainhand().getItem() == Items.AIR) {
                        break Label_1839;
                    }
                }
                else if (llllllllllllllllIlIllIlIIIIIIlIl.getHeldItemOffhand().getItem() == Items.AIR) {
                    break Label_1839;
                }
                llllllllllllllllIlIllIlIIIlIIlII += 0;
                if (this.reversedHand.getValue()) {
                    final ItemStack llllllllllllllllIlIllIlIIIlIIllI = llllllllllllllllIlIllIlIIIIIIlIl.getHeldItemMainhand().copy();
                    this.renderItem(llllllllllllllllIlIllIlIIIIIIlIl, llllllllllllllllIlIllIlIIIlIIllI, llllllllllllllllIlIllIlIIIlIIlII, -10, llllllllllllllllIlIllIlIIIlIIIlI, true);
                }
                else {
                    final ItemStack llllllllllllllllIlIllIlIIIlIIlIl = llllllllllllllllIlIllIlIIIIIIlIl.getHeldItemOffhand().copy();
                    this.renderItem(llllllllllllllllIlIllIlIIIIIIlIl, llllllllllllllllIlIllIlIIIlIIlIl, llllllllllllllllIlIllIlIIIlIIlII, -10, llllllllllllllllIlIllIlIIIlIIIlI, false);
                }
                llllllllllllllllIlIllIlIIIlIIlII += 8;
            }
            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
            GlStateManager.enableTexture2D();
        }
        else if (this.durability.getValue()) {
            int llllllllllllllllIlIllIlIIIIllIlI = -6;
            int llllllllllllllllIlIllIlIIIIllIIl = 0;
            for (final ItemStack llllllllllllllllIlIllIlIIIlIIIIl : llllllllllllllllIlIllIlIIIIIIlIl.inventory.armorInventory) {
                if (llllllllllllllllIlIllIlIIIlIIIIl != null) {
                    llllllllllllllllIlIllIlIIIIllIlI -= 8;
                    if (llllllllllllllllIlIllIlIIIlIIIIl.getItem() == Items.AIR) {
                        continue;
                    }
                    ++llllllllllllllllIlIllIlIIIIllIIl;
                }
            }
            if (llllllllllllllllIlIllIlIIIIIIlIl.getHeldItemOffhand().getItem() != Items.AIR) {
                ++llllllllllllllllIlIllIlIIIIllIIl;
            }
            final int llllllllllllllllIlIllIlIIIIllIII = llllllllllllllllIlIllIlIIIIllIlI - 8;
            llllllllllllllllIlIllIlIIIIllIlI += 8 * (5 - llllllllllllllllIlIllIlIIIIllIIl) - ((llllllllllllllllIlIllIlIIIIllIIl == 0) ? 4 : 0);
            if (this.reversed.getValue()) {
                for (int llllllllllllllllIlIllIlIIIIllllI = 0; llllllllllllllllIlIllIlIIIIllllI <= 3; ++llllllllllllllllIlIllIlIIIIllllI) {
                    final ItemStack llllllllllllllllIlIllIlIIIIlllll = (ItemStack)llllllllllllllllIlIllIlIIIIIIlIl.inventory.armorInventory.get(llllllllllllllllIlIllIlIIIIllllI);
                    if (llllllllllllllllIlIllIlIIIIlllll != null && llllllllllllllllIlIllIlIIIIlllll.getItem() != Items.AIR) {
                        final ItemStack llllllllllllllllIlIllIlIIIlIIIII = llllllllllllllllIlIllIlIIIIlllll.copy();
                        this.renderDurabilityText(llllllllllllllllIlIllIlIIIIIIlIl, llllllllllllllllIlIllIlIIIlIIIII, llllllllllllllllIlIllIlIIIIllIlI, -10);
                        llllllllllllllllIlIllIlIIIIllIlI += 16;
                    }
                }
            }
            else {
                for (int llllllllllllllllIlIllIlIIIIllIll = 3; llllllllllllllllIlIllIlIIIIllIll >= 0; --llllllllllllllllIlIllIlIIIIllIll) {
                    final ItemStack llllllllllllllllIlIllIlIIIIlllII = (ItemStack)llllllllllllllllIlIllIlIIIIIIlIl.inventory.armorInventory.get(llllllllllllllllIlIllIlIIIIllIll);
                    if (llllllllllllllllIlIllIlIIIIlllII != null && llllllllllllllllIlIllIlIIIIlllII.getItem() != Items.AIR) {
                        final ItemStack llllllllllllllllIlIllIlIIIIlllIl = llllllllllllllllIlIllIlIIIIlllII.copy();
                        this.renderDurabilityText(llllllllllllllllIlIllIlIIIIIIlIl, llllllllllllllllIlIllIlIIIIlllIl, llllllllllllllllIlIllIlIIIIllIlI, -10);
                        llllllllllllllllIlIllIlIIIIllIlI += 16;
                    }
                }
            }
            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
            GlStateManager.enableTexture2D();
        }
        this.renderUtils.resetCaps();
        GlStateManager.resetColor();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }
    
    public static void enableGL2D() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }
}
