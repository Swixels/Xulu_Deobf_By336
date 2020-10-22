package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import java.util.function.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;

public class Exp extends Element
{
    @Override
    public void onEnable() {
        this.width = 16.0;
        this.height = 16.0;
        super.onEnable();
    }
    
    private void itemrender(final ItemStack lllllllllllllllllIIlIIllIIIlIIIl) {
        preitemrender();
        Exp.mc.getRenderItem().renderItemAndEffectIntoGUI(lllllllllllllllllIIlIIllIIIlIIIl, (int)this.x, (int)this.y);
        Exp.mc.getRenderItem().renderItemOverlays(Exp.mc.fontRenderer, lllllllllllllllllIIlIIllIIIlIIIl, (int)this.x, (int)this.y);
        postitemrender();
    }
    
    @Override
    public void onRender() {
        int lllllllllllllllllIIlIIllIIIlllII = Exp.mc.player.inventory.mainInventory.stream().filter(lllllllllllllllllIIlIIllIIIIIlll -> lllllllllllllllllIIlIIllIIIIIlll.getItem() == Items.EXPERIENCE_BOTTLE).mapToInt(ItemStack::func_190916_E).sum();
        if (Exp.mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE) {
            ++lllllllllllllllllIIlIIllIIIlllII;
        }
        final ItemStack lllllllllllllllllIIlIIllIIIllIll = new ItemStack(Items.EXPERIENCE_BOTTLE, lllllllllllllllllIIlIIllIIIlllII);
        this.itemrender(lllllllllllllllllIIlIIllIIIllIll);
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
    
    public Exp() {
        super("Exp");
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
}
