package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import java.util.function.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;

public class Totems extends Element
{
    @Override
    public void onRender() {
        int llllllllllllllllIlIlIIlllIIIllll = Totems.mc.player.inventory.mainInventory.stream().filter(llllllllllllllllIlIlIIlllIIIIIll -> llllllllllllllllIlIlIIlllIIIIIll.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::func_190916_E).sum();
        if (Totems.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
            ++llllllllllllllllIlIlIIlllIIIllll;
        }
        final ItemStack llllllllllllllllIlIlIIlllIIIlllI = new ItemStack(Items.TOTEM_OF_UNDYING, llllllllllllllllIlIlIIlllIIIllll);
        this.itemrender(llllllllllllllllIlIlIIlllIIIlllI);
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
    
    private void itemrender(final ItemStack llllllllllllllllIlIlIIlllIIIIlll) {
        preitemrender();
        Totems.mc.getRenderItem().renderItemAndEffectIntoGUI(llllllllllllllllIlIlIIlllIIIIlll, (int)this.x, (int)this.y);
        Totems.mc.getRenderItem().renderItemOverlays(Totems.mc.fontRenderer, llllllllllllllllIlIlIIlllIIIIlll, (int)this.x, (int)this.y);
        postitemrender();
    }
    
    public Totems() {
        super("Totems");
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
    
    @Override
    public void onEnable() {
        this.width = 16.0;
        this.height = 16.0;
        super.onEnable();
    }
}
