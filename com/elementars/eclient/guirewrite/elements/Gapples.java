package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import net.minecraft.item.*;
import java.util.function.*;
import net.minecraft.init.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;

public class Gapples extends Element
{
    @Override
    public void onRender() {
        int lllllllllllllllllllllllIIlIIllll = Gapples.mc.player.inventory.mainInventory.stream().filter(lllllllllllllllllllllllIIlIIIIll -> lllllllllllllllllllllllIIlIIIIll.getItem() instanceof ItemAppleGold).mapToInt(ItemStack::func_190916_E).sum();
        if (Gapples.mc.player.getHeldItemOffhand().getItem() instanceof ItemAppleGold) {
            lllllllllllllllllllllllIIlIIllll += Gapples.mc.player.getHeldItemOffhand().stackSize;
        }
        final ItemStack lllllllllllllllllllllllIIlIIlllI = new ItemStack(Items.GOLDEN_APPLE, lllllllllllllllllllllllIIlIIllll);
        lllllllllllllllllllllllIIlIIlllI.addEnchantment(Enchantments.SHARPNESS, 32767);
        this.itemrender(lllllllllllllllllllllllIIlIIlllI);
    }
    
    @Override
    public void onEnable() {
        this.width = 16.0;
        this.height = 16.0;
        super.onEnable();
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
    
    public Gapples() {
        super("Gapples");
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
    
    private void itemrender(final ItemStack lllllllllllllllllllllllIIlIIIlll) {
        preitemrender();
        Gapples.mc.getRenderItem().renderItemAndEffectIntoGUI(lllllllllllllllllllllllIIlIIIlll, (int)this.x, (int)this.y);
        Gapples.mc.getRenderItem().renderItemOverlays(Gapples.mc.fontRenderer, lllllllllllllllllllllllIIlIIIlll, (int)this.x, (int)this.y);
        postitemrender();
    }
}
