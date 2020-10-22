package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import java.util.function.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;

public class Obsidian extends Element
{
    @Override
    public void onRender() {
        int llllIIlIIlIllll = Obsidian.mc.player.inventory.mainInventory.stream().filter(llllIIlIIlIIIll -> llllIIlIIlIIIll.getItem() == Item.getItemFromBlock(Blocks.OBSIDIAN)).mapToInt(ItemStack::func_190916_E).sum();
        if (Obsidian.mc.player.getHeldItemOffhand().getItem() == Item.getItemFromBlock(Blocks.OBSIDIAN)) {
            llllIIlIIlIllll += Obsidian.mc.player.getHeldItemOffhand().stackSize;
        }
        final ItemStack llllIIlIIlIlllI = new ItemStack(Item.getItemFromBlock(Blocks.OBSIDIAN), llllIIlIIlIllll);
        this.itemrender(llllIIlIIlIlllI);
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
    
    public Obsidian() {
        super("Obsidian");
    }
    
    private void itemrender(final ItemStack llllIIlIIlIIlIl) {
        preitemrender();
        Obsidian.mc.getRenderItem().renderItemAndEffectIntoGUI(llllIIlIIlIIlIl, (int)this.x, (int)this.y);
        Obsidian.mc.getRenderItem().renderItemOverlays(Obsidian.mc.fontRenderer, llllIIlIIlIIlIl, (int)this.x, (int)this.y);
        postitemrender();
    }
    
    @Override
    public void onEnable() {
        this.width = 16.0;
        this.height = 16.0;
        super.onEnable();
    }
}
