package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import java.util.function.*;

public class Crystals extends Element
{
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
    
    private static void preitemrender() {
        GL11.glPushMatrix();
        GL11.glDepthMask(true);
        GlStateManager.clear(256);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.scale(1.0f, 1.0f, 0.01f);
    }
    
    public Crystals() {
        super("Crystals");
    }
    
    @Override
    public void onEnable() {
        this.width = 16.0;
        this.height = 16.0;
        super.onEnable();
    }
    
    @Override
    public void onRender() {
        int lIlIllllIlIlIl = Crystals.mc.player.inventory.mainInventory.stream().filter(lIlIllllIIIlIl -> lIlIllllIIIlIl.getItem() == Items.END_CRYSTAL).mapToInt(ItemStack::func_190916_E).sum();
        if (Crystals.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            lIlIllllIlIlIl += Crystals.mc.player.getHeldItemOffhand().stackSize;
        }
        final ItemStack lIlIllllIlIlII = new ItemStack(Items.END_CRYSTAL, lIlIllllIlIlIl);
        this.itemrender(lIlIllllIlIlII);
    }
    
    private void itemrender(final ItemStack lIlIllllIIlIlI) {
        preitemrender();
        Crystals.mc.getRenderItem().renderItemAndEffectIntoGUI(lIlIllllIIlIlI, (int)this.x, (int)this.y);
        Crystals.mc.getRenderItem().renderItemOverlays(Crystals.mc.fontRenderer, lIlIllllIIlIlI, (int)this.x, (int)this.y);
        postitemrender();
    }
}
