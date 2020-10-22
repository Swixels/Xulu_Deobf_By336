package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import dev.xulu.settings.*;
import com.elementars.eclient.module.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.gui.*;
import org.lwjgl.input.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.entity.*;

public class Player extends Element
{
    private final /* synthetic */ Value<Integer> scale;
    private final /* synthetic */ Value<String> noLook;
    
    public Player() {
        super("Player");
        this.noLook = this.register(new Value<String>("Look Mode", this, "Mouse", new String[] { "Mouse", "Free", "None" }));
        this.scale = this.register(new Value<Integer>("Scale", this, 30, 1, 100));
    }
    
    public void drawPlayer(final EntityPlayer llIllIllIIllIll, final int llIllIllIIlIlII, final int llIllIllIIlIIll) {
        final EntityPlayer llIllIllIIllIII = llIllIllIIllIll;
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
        GlStateManager.enableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.rotate(0.0f, 0.0f, 5.0f, 0.0f);
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)(llIllIllIIlIlII + 16), (float)(llIllIllIIlIIll + 55), 50.0f);
        GlStateManager.scale(-1.0f * this.scale.getValue(), 1.0f * this.scale.getValue(), 1.0f * this.scale.getValue());
        GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(135.0f, 0.0f, 1.0f, 0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-(float)Math.atan(llIllIllIIlIIll / 40.0f) * 20.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(0.0f, 0.0f, 0.0f);
        final RenderManager llIllIllIIlIlll = Player.mc.getRenderManager();
        llIllIllIIlIlll.setPlayerViewY(180.0f);
        llIllIllIIlIlll.setRenderShadow(false);
        try {
            llIllIllIIlIlll.renderEntity((Entity)llIllIllIIllIII, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        }
        catch (Exception ex) {}
        llIllIllIIlIlll.setRenderShadow(true);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.depthFunc(515);
        GlStateManager.resetColor();
        GlStateManager.disableDepth();
        GlStateManager.popMatrix();
    }
    
    @Override
    public void onEnable() {
        this.width = 34.0;
        this.height = 63.0;
        super.onEnable();
    }
    
    @Override
    public void onRender() {
        final ScaledResolution llIllIllIlIIllI = new ScaledResolution(Player.mc);
        if (Player.mc.player == null) {
            return;
        }
        if (Player.mc.gameSettings.thirdPersonView != 0) {
            return;
        }
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        if (this.noLook.getValue().equalsIgnoreCase("Free")) {
            this.drawPlayer((EntityPlayer)Player.mc.player, (int)this.x, (int)this.y);
        }
        else {
            GuiInventory.drawEntityOnScreen((int)this.x + 17, (int)this.y + 60, 30, this.noLook.getValue().equalsIgnoreCase("None") ? 0.0f : ((float)this.x - Mouse.getX()), this.noLook.getValue().equalsIgnoreCase("None") ? 0.0f : (-llIllIllIlIIllI.getScaledHeight() + (float)Mouse.getY()), (EntityLivingBase)Player.mc.player);
        }
        GlStateManager.popMatrix();
    }
}
