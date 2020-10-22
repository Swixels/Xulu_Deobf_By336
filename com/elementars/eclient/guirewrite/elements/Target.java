package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import dev.xulu.settings.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.entity.*;
import com.elementars.eclient.*;
import com.mojang.realmsclient.gui.*;
import com.elementars.eclient.command.*;
import com.elementars.eclient.module.combat.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import net.minecraft.client.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.module.*;

public class Target extends Element
{
    /* synthetic */ BlockPos SOUTH;
    private final /* synthetic */ Value<Boolean> cf;
    private /* synthetic */ EntityPlayer target;
    /* synthetic */ BlockPos WEST;
    /* synthetic */ BlockPos NORTH;
    private static /* synthetic */ RenderItem itemRender;
    /* synthetic */ BlockPos EAST;
    
    @Override
    public void onEnable() {
        this.width = 200.0;
        this.height = 100.0;
        super.onEnable();
    }
    
    private List<ItemStack> getNorth(final EntityPlayer lllllllllllllllllIlllllIIIlIlllI) {
        final BlockPos lllllllllllllllllIlllllIIIlIllIl = new BlockPos(lllllllllllllllllIlllllIIIlIlllI.posX, lllllllllllllllllIlllllIIIlIlllI.posY, lllllllllllllllllIlllllIIIlIlllI.posZ);
        final List<ItemStack> lllllllllllllllllIlllllIIIlIllII = new ArrayList<ItemStack>(Arrays.asList(this.isBrockOrObby(lllllllllllllllllIlllllIIIlIllIl.add(this.NORTH.x, this.NORTH.y, this.NORTH.z)) ? new ItemStack(Target.mc.world.getBlockState(lllllllllllllllllIlllllIIIlIllIl.add(this.NORTH.x, this.NORTH.y, this.NORTH.z)).getBlock()) : new ItemStack(Items.AIR), this.isBrockOrObby(lllllllllllllllllIlllllIIIlIllIl.add(this.EAST.x, this.EAST.y, this.EAST.z)) ? new ItemStack(Target.mc.world.getBlockState(lllllllllllllllllIlllllIIIlIllIl.add(this.EAST.x, this.EAST.y, this.EAST.z)).getBlock()) : new ItemStack(Items.AIR), this.isBrockOrObby(lllllllllllllllllIlllllIIIlIllIl.add(this.SOUTH.x, this.SOUTH.y, this.SOUTH.z)) ? new ItemStack(Target.mc.world.getBlockState(lllllllllllllllllIlllllIIIlIllIl.add(this.SOUTH.x, this.SOUTH.y, this.SOUTH.z)).getBlock()) : new ItemStack(Items.AIR), this.isBrockOrObby(lllllllllllllllllIlllllIIIlIllIl.add(this.WEST.x, this.WEST.y, this.WEST.z)) ? new ItemStack(Target.mc.world.getBlockState(lllllllllllllllllIlllllIIIlIllIl.add(this.WEST.x, this.WEST.y, this.WEST.z)).getBlock()) : new ItemStack(Items.AIR)));
        return lllllllllllllllllIlllllIIIlIllII;
    }
    
    @Override
    public void onUpdate() {
        if (Target.mc.player == null) {
            return;
        }
        final List<EntityPlayer> lllllllllllllllllIlllllIIlIIIIlI = new ArrayList<EntityPlayer>(Target.mc.world.playerEntities);
        lllllllllllllllllIlllllIIlIIIIlI.removeIf(lllllllllllllllllIllllIllllIlIll -> lllllllllllllllllIllllIllllIlIll == Target.mc.player);
        lllllllllllllllllIlllllIIlIIIIlI.sort(Comparator.comparing(lllllllllllllllllIllllIllllIllll -> Target.mc.player.getDistance(lllllllllllllllllIllllIllllIllll)));
        if (!lllllllllllllllllIlllllIIlIIIIlI.isEmpty()) {
            this.target = lllllllllllllllllIlllllIIlIIIIlI.get(0);
        }
        else {
            this.target = null;
        }
        super.onUpdate();
    }
    
    private static void postitemrender() {
        GlStateManager.scale(1.0f, 1.0f, 1.0f);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableLighting();
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GL11.glPopMatrix();
    }
    
    public String getPing(final float lllllllllllllllllIlllllIIIllllIl) {
        if (lllllllllllllllllIlllllIIIllllIl > 200.0f) {
            return "c";
        }
        if (lllllllllllllllllIlllllIIIllllIl > 100.0f) {
            return "e";
        }
        return "a";
    }
    
    @Override
    public void onRender() {
        if (Target.mc.player == null || Target.mc.world == null) {
            return;
        }
        if (this.target != null) {
            Gui.drawRect((int)this.x, (int)this.y, (int)this.x + (int)this.width, (int)this.y + (int)this.height, ColorUtils.changeAlpha(Color.BLACK.getRGB(), 50));
            GlStateManager.pushMatrix();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            try {
                GuiInventory.drawEntityOnScreen((int)this.x + 30, (int)this.y + 90, 45, 0.0f, 0.0f, (EntityLivingBase)this.target);
            }
            catch (Exception lllllllllllllllllIlllllIIIIIlIlI) {
                lllllllllllllllllIlllllIIIIIlIlI.printStackTrace();
            }
            GlStateManager.popMatrix();
            if (this.cf.getValue()) {
                Xulu.cFontRenderer.drawStringWithShadow(this.target.getName(), this.x + 62.0, this.y + 3.0, -1);
                Xulu.cFontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append((Target.mc.getConnection() == null || Target.mc.getConnection().getPlayerInfo(this.target.entityUniqueID) == null) ? String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("-1")) : String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append(this.getPing((float)Target.mc.getConnection().getPlayerInfo(this.target.entityUniqueID).getResponseTime())).append(Target.mc.getConnection().getPlayerInfo(this.target.entityUniqueID).getResponseTime()))).append("ms")), this.x + 62.0, this.y + 18.0, -1);
                Xulu.cFontRenderer.drawStringWithShadow(PopCounter.INSTANCE.popMap.containsKey(this.target) ? String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("").append(PopCounter.INSTANCE.popMap.get(this.target)).append(" Pops")) : String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("0 Pops")), this.x + 62.0, this.y + 33.0, -1);
                Xulu.cFontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append(this.getDistance(Target.mc.player.getDistance((Entity)this.target))).append((int)Target.mc.player.getDistance((Entity)this.target)).append(" blocks away")), this.x + 62.0, this.y + 48.0, -1);
            }
            else {
                Target.fontRenderer.drawStringWithShadow(this.target.getName(), (float)this.x + 62.0f, (float)this.y + 3.0f, -1);
                Target.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append((Target.mc.getConnection() == null || Target.mc.getConnection().getPlayerInfo(this.target.entityUniqueID) == null) ? String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("-1")) : String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append(this.getPing((float)Target.mc.getConnection().getPlayerInfo(this.target.entityUniqueID).getResponseTime())).append(Target.mc.getConnection().getPlayerInfo(this.target.entityUniqueID).getResponseTime()))).append("ms")), (float)this.x + 62.0f, (float)this.y + 18.0f, -1);
                Target.fontRenderer.drawStringWithShadow(PopCounter.INSTANCE.popMap.containsKey(this.target) ? String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("").append(PopCounter.INSTANCE.popMap.get(this.target)).append(" Pops")) : String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("0 Pops")), (float)this.x + 62.0f, (float)this.y + 33.0f, -1);
                Target.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append(this.getDistance(Target.mc.player.getDistance((Entity)this.target))).append((int)Target.mc.player.getDistance((Entity)this.target)).append(" blocks away")), (float)((int)this.x + 62), (float)((int)this.y + 48), -1);
            }
            final float lllllllllllllllllIlllllIIIIIIIlI = (float)MathHelper.clamp(MathHelper.ceil(this.target.getHealth()), 0, 20);
            final float lllllllllllllllllIlllllIIIIIIIIl = (20.0f - lllllllllllllllllIlllllIIIIIIIlI) / 20.0f;
            final float lllllllllllllllllIlllllIIIIIIIII = 1.0f - lllllllllllllllllIlllllIIIIIIIIl;
            Gui.drawRect((int)this.x, (int)this.y + (int)this.height - 3, (int)(this.x + lllllllllllllllllIlllllIIIIIIIII * this.width), (int)this.y + (int)this.height, ColorUtils.changeAlpha(ColourHolder.toHex((int)(lllllllllllllllllIlllllIIIIIIIIl * 255.0f), (int)(lllllllllllllllllIlllllIIIIIIIII * 255.0f), 0), 200));
            this.itemrender(this.getNorth(this.target), (int)this.x + (int)this.width - 52, (int)this.y + 4);
            GlStateManager.pushMatrix();
            GlStateManager.enableTexture2D();
            int lllllllllllllllllIllllIlllllllll = 0;
            for (final ItemStack lllllllllllllllllIlllllIIIIIIIll : this.target.inventory.armorInventory) {
                ++lllllllllllllllllIllllIlllllllll;
                if (lllllllllllllllllIlllllIIIIIIIll.isEmpty()) {
                    continue;
                }
                final int lllllllllllllllllIlllllIIIIIlIIl = (int)this.x - 90 + (9 - lllllllllllllllllIllllIlllllllll) * 20 + 2 - 12 + 60;
                final int lllllllllllllllllIlllllIIIIIlIII = (int)this.y + (int)this.height - 24;
                GlStateManager.enableDepth();
                Target.itemRender.zLevel = 200.0f;
                Target.itemRender.renderItemAndEffectIntoGUI(lllllllllllllllllIlllllIIIIIIIll, lllllllllllllllllIlllllIIIIIlIIl, lllllllllllllllllIlllllIIIIIlIII);
                Target.itemRender.renderItemOverlayIntoGUI(Target.mc.fontRenderer, lllllllllllllllllIlllllIIIIIIIll, lllllllllllllllllIlllllIIIIIlIIl, lllllllllllllllllIlllllIIIIIlIII, "");
                Target.itemRender.zLevel = 0.0f;
                GlStateManager.enableTexture2D();
                GlStateManager.disableLighting();
                GlStateManager.disableDepth();
                final String lllllllllllllllllIlllllIIIIIIlll = (lllllllllllllllllIlllllIIIIIIIll.getCount() > 1) ? String.valueOf(new StringBuilder().append(lllllllllllllllllIlllllIIIIIIIll.getCount()).append("")) : "";
                Target.mc.fontRenderer.drawStringWithShadow(lllllllllllllllllIlllllIIIIIIlll, (float)(lllllllllllllllllIlllllIIIIIlIIl + 19 - 2 - Target.mc.fontRenderer.getStringWidth(lllllllllllllllllIlllllIIIIIIlll)), (float)((int)this.y + 9), 16777215);
                final float lllllllllllllllllIlllllIIIIIIllI = (lllllllllllllllllIlllllIIIIIIIll.getMaxDamage() - (float)lllllllllllllllllIlllllIIIIIIIll.getItemDamage()) / lllllllllllllllllIlllllIIIIIIIll.getMaxDamage();
                final float lllllllllllllllllIlllllIIIIIIlIl = 1.0f - lllllllllllllllllIlllllIIIIIIllI;
                final int lllllllllllllllllIlllllIIIIIIlII = 100 - (int)(lllllllllllllllllIlllllIIIIIIlIl * 100.0f);
                if (this.cf.getValue()) {
                    Xulu.cFontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(lllllllllllllllllIlllllIIIIIIlII).append("")), lllllllllllllllllIlllllIIIIIlIIl + 8 - Xulu.cFontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(lllllllllllllllllIlllllIIIIIIlII).append(""))) / 2, lllllllllllllllllIlllllIIIIIlIII - 11, ColourHolder.toHex((int)(lllllllllllllllllIlllllIIIIIIlIl * 255.0f), (int)(lllllllllllllllllIlllllIIIIIIllI * 255.0f), 0));
                }
                else {
                    Target.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(lllllllllllllllllIlllllIIIIIIlII).append("")), (float)(lllllllllllllllllIlllllIIIIIlIIl + 9 - Target.fontRenderer.getStringWidth(String.valueOf(new StringBuilder().append(lllllllllllllllllIlllllIIIIIIlII).append(""))) / 2), (float)(lllllllllllllllllIlllllIIIIIlIII - 11), ColourHolder.toHex((int)(lllllllllllllllllIlllllIIIIIIlIl * 255.0f), (int)(lllllllllllllllllIlllllIIIIIIllI * 255.0f), 0));
                }
            }
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
            GlStateManager.popMatrix();
        }
    }
    
    private static void preitemrender() {
        GL11.glPushMatrix();
        GL11.glDepthMask(true);
        GlStateManager.clear(256);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.scale(1.0f, 1.0f, 0.01f);
    }
    
    private boolean isBrockOrObby(final BlockPos lllllllllllllllllIlllllIIIllIlIl) {
        return Target.mc.world.getBlockState(lllllllllllllllllIlllllIIIllIlIl).getBlock() == Blocks.BEDROCK || Target.mc.world.getBlockState(lllllllllllllllllIlllllIIIllIlIl).getBlock() == Blocks.OBSIDIAN;
    }
    
    static {
        Target.itemRender = Minecraft.getMinecraft().getRenderItem();
    }
    
    private void itemrender(final List<ItemStack> lllllllllllllllllIlllllIIIIlllII, final int lllllllllllllllllIlllllIIIIllIll, final int lllllllllllllllllIlllllIIIIllIlI) {
        final ArrayList<Pair<Integer, Integer>> lllllllllllllllllIlllllIIIIlllIl = new ArrayList<Pair<Integer, Integer>>((Collection<? extends Pair<Integer, Integer>>)Arrays.asList(new Pair((T)(lllllllllllllllllIlllllIIIIllIll + 16), (S)lllllllllllllllllIlllllIIIIllIlI), new Pair((T)(lllllllllllllllllIlllllIIIIllIll + 32), (S)(lllllllllllllllllIlllllIIIIllIlI + 16)), new Pair((T)(lllllllllllllllllIlllllIIIIllIll + 16), (S)(lllllllllllllllllIlllllIIIIllIlI + 32)), new Pair((T)lllllllllllllllllIlllllIIIIllIll, (S)(lllllllllllllllllIlllllIIIIllIlI + 16))));
        for (int lllllllllllllllllIlllllIIIlIIIlI = 0; lllllllllllllllllIlllllIIIlIIIlI < 4; ++lllllllllllllllllIlllllIIIlIIIlI) {
            preitemrender();
            Target.mc.getRenderItem().renderItemAndEffectIntoGUI((ItemStack)lllllllllllllllllIlllllIIIIlllII.get(lllllllllllllllllIlllllIIIlIIIlI), (int)lllllllllllllllllIlllllIIIIlllIl.get(lllllllllllllllllIlllllIIIlIIIlI).getKey(), (int)lllllllllllllllllIlllllIIIIlllIl.get(lllllllllllllllllIlllllIIIlIIIlI).getValue());
            postitemrender();
        }
    }
    
    public Target() {
        super("Target");
        this.cf = this.register(new Value<Boolean>("Custom Font", this, false));
        this.NORTH = new BlockPos(0, 0, -1);
        this.EAST = new BlockPos(1, 0, 0);
        this.SOUTH = new BlockPos(0, 0, 1);
        this.WEST = new BlockPos(-1, 0, 0);
    }
    
    private String getDistance(final double lllllllllllllllllIlllllIIIlllIIl) {
        if (lllllllllllllllllIlllllIIIlllIIl < 15.0) {
            return "c";
        }
        return "a";
    }
}
