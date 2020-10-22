package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import net.minecraft.util.*;
import dev.xulu.settings.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.item.*;
import com.elementars.eclient.util.*;
import net.minecraft.client.gui.*;
import com.elementars.eclient.module.*;

public class CraftingPreview extends Element
{
    private static final /* synthetic */ ResourceLocation box;
    private /* synthetic */ Value<String> background;
    
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
    
    private void itemrender(final ItemStack llllllllllllllllIlIllIllIIIIIlII, final int llllllllllllllllIlIllIllIIIIIIII, final int llllllllllllllllIlIllIlIllllllll) {
        preitemrender();
        InvPreview.mc.getRenderItem().renderItemAndEffectIntoGUI(llllllllllllllllIlIllIllIIIIIlII, llllllllllllllllIlIllIllIIIIIIII, llllllllllllllllIlIllIlIllllllll);
        InvPreview.mc.getRenderItem().renderItemOverlays(InvPreview.mc.fontRenderer, llllllllllllllllIlIllIllIIIIIlII, llllllllllllllllIlIllIllIIIIIIII, llllllllllllllllIlIllIlIllllllll);
        postitemrender();
    }
    
    @Override
    public void onEnable() {
        this.width = 36.0;
        this.height = 36.0;
        super.onEnable();
    }
    
    private static void postboxrender() {
        GlStateManager.disableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.popMatrix();
        GL11.glPopMatrix();
    }
    
    private void boxrender(final int llllllllllllllllIlIllIllIIIIllII, final int llllllllllllllllIlIllIllIIIIlIIl) {
        preboxrender();
        InvPreview.mc.renderEngine.bindTexture(CraftingPreview.box);
        InvPreview.mc.ingameGUI.drawTexturedModalRect(llllllllllllllllIlIllIllIIIIllII, llllllllllllllllIlIllIllIIIIlIIl, 7, 17, 36, 36);
        postboxrender();
    }
    
    @Override
    public void onRender() {
        if (this.background.getValue().equalsIgnoreCase("Texture")) {
            this.boxrender((int)this.x, (int)this.y);
        }
        else if (this.background.getValue().equalsIgnoreCase("Transparent")) {
            Gui.drawRect((int)this.x, (int)this.y, (int)this.x + (int)this.width, (int)this.y + (int)this.height, ColorUtils.changeAlpha(-15592942, 100));
        }
        for (int llllllllllllllllIlIllIllIIIlIllI = 1; llllllllllllllllIlIllIllIIIlIllI < 5; ++llllllllllllllllIlIllIllIIIlIllI) {
            final ItemStack llllllllllllllllIlIllIllIIIllIIl = (ItemStack)CraftingPreview.mc.player.inventoryContainer.getInventory().get(llllllllllllllllIlIllIllIIIlIllI);
            final int llllllllllllllllIlIllIllIIIllIII = (int)this.x + 1 + (llllllllllllllllIlIllIllIIIlIllI - 1) * 18 - ((llllllllllllllllIlIllIllIIIlIllI > 2) ? 36 : 0);
            final int llllllllllllllllIlIllIllIIIlIlll = (int)this.y + 1 + ((llllllllllllllllIlIllIllIIIlIllI > 2) ? 18 : 0);
            this.itemrender(llllllllllllllllIlIllIllIIIllIIl, llllllllllllllllIlIllIllIIIllIII, llllllllllllllllIlIllIllIIIlIlll);
        }
    }
    
    static {
        box = new ResourceLocation("textures/gui/container/generic_54.png");
    }
    
    public CraftingPreview() {
        super("CraftingPreview");
        this.background = this.register(new Value<String>("Background", this, "Texture", new String[] { "Texture", "Transparent", "None" }));
    }
    
    private static void preboxrender() {
        GL11.glPushMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.disableAlpha();
        GlStateManager.clear(256);
        GlStateManager.enableBlend();
    }
}
