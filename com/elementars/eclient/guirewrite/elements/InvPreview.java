package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import dev.xulu.settings.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import com.elementars.eclient.util.*;
import net.minecraft.client.gui.*;
import com.elementars.eclient.module.*;

public class InvPreview extends Element
{
    private /* synthetic */ Value<String> background;
    private static final /* synthetic */ ResourceLocation box;
    
    private static void postboxrender() {
        GlStateManager.disableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.popMatrix();
        GL11.glPopMatrix();
    }
    
    static {
        box = new ResourceLocation("textures/gui/container/generic_54.png");
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
    
    private void itemrender(final NonNullList<ItemStack> lllllllllllllllllIlllIlllIlllIIl, final int lllllllllllllllllIlllIlllIlllIII, final int lllllllllllllllllIlllIlllIllIlll) {
        for (int lllllllllllllllllIlllIlllIllllII = lllllllllllllllllIlllIlllIlllIIl.size(), lllllllllllllllllIlllIlllIlllIll = 9; lllllllllllllllllIlllIlllIlllIll < lllllllllllllllllIlllIlllIllllII; ++lllllllllllllllllIlllIlllIlllIll) {
            final int lllllllllllllllllIlllIlllIlllllI = lllllllllllllllllIlllIlllIlllIII + 1 + lllllllllllllllllIlllIlllIlllIll % 9 * 18;
            final int lllllllllllllllllIlllIlllIllllIl = lllllllllllllllllIlllIlllIllIlll + 1 + (lllllllllllllllllIlllIlllIlllIll / 9 - 1) * 18;
            preitemrender();
            InvPreview.mc.getRenderItem().renderItemAndEffectIntoGUI((ItemStack)lllllllllllllllllIlllIlllIlllIIl.get(lllllllllllllllllIlllIlllIlllIll), lllllllllllllllllIlllIlllIlllllI, lllllllllllllllllIlllIlllIllllIl);
            InvPreview.mc.getRenderItem().renderItemOverlays(InvPreview.mc.fontRenderer, (ItemStack)lllllllllllllllllIlllIlllIlllIIl.get(lllllllllllllllllIlllIlllIlllIll), lllllllllllllllllIlllIlllIlllllI, lllllllllllllllllIlllIlllIllllIl);
            postitemrender();
        }
    }
    
    @Override
    public void onRender() {
        final NonNullList<ItemStack> lllllllllllllllllIlllIllllIIllll = (NonNullList<ItemStack>)InvPreview.mc.player.inventory.mainInventory;
        if (this.background.getValue().equalsIgnoreCase("Texture")) {
            this.boxrender((int)this.x, (int)this.y);
        }
        else if (this.background.getValue().equalsIgnoreCase("Transparent")) {
            Gui.drawRect((int)this.x, (int)this.y, (int)this.x + (int)this.width, (int)this.y + (int)this.height, ColorUtils.changeAlpha(-15592942, 100));
        }
        this.itemrender(lllllllllllllllllIlllIllllIIllll, (int)this.x, (int)this.y);
    }
    
    @Override
    public void onEnable() {
        this.width = 162.0;
        this.height = 54.0;
        super.onEnable();
    }
    
    public InvPreview() {
        super("InvPreview");
        this.background = this.register(new Value<String>("Background", this, "Texture", new String[] { "Texture", "Transparent", "None" }));
    }
    
    private void boxrender(final int lllllllllllllllllIlllIllllIIIlll, final int lllllllllllllllllIlllIllllIIlIII) {
        preboxrender();
        InvPreview.mc.renderEngine.bindTexture(InvPreview.box);
        InvPreview.mc.ingameGUI.drawTexturedModalRect(lllllllllllllllllIlllIllllIIIlll, lllllllllllllllllIlllIllllIIlIII, 7, 17, 162, 54);
        postboxrender();
    }
    
    private static void preboxrender() {
        GL11.glPushMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.disableAlpha();
        GlStateManager.clear(256);
        GlStateManager.enableBlend();
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
