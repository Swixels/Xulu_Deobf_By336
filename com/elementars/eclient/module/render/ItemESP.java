package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.entity.item.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.*;
import com.elementars.eclient.module.*;
import java.util.*;
import net.minecraft.entity.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import com.elementars.eclient.*;
import net.minecraft.util.math.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import com.elementars.eclient.util.*;

public class ItemESP extends Module
{
    public static /* synthetic */ ItemESP INSTANCE;
    private final /* synthetic */ Value<Integer> pblue;
    private final /* synthetic */ Value<Boolean> xpbottles;
    private final /* synthetic */ Value<Boolean> items;
    public final /* synthetic */ Value<String> mode;
    private final /* synthetic */ Value<Integer> outlinewidth;
    private final /* synthetic */ Value<Integer> alpha;
    private final /* synthetic */ Value<Integer> blue;
    private final /* synthetic */ Value<Integer> palpha;
    private final /* synthetic */ Value<RenderMode> outline;
    private final /* synthetic */ Value<Integer> red;
    private final /* synthetic */ Value<Integer> alphaF;
    private final /* synthetic */ Value<Integer> width;
    private final /* synthetic */ Value<Integer> green;
    private final /* synthetic */ Value<Integer> pred;
    private final /* synthetic */ Value<Boolean> pearls;
    private final /* synthetic */ Value<Boolean> rainbow;
    private final /* synthetic */ Value<Integer> pgreen;
    
    @Override
    public void onWorldRender(final RenderEvent lllllllllllllllllIIlllllIlIllIII) {
        if (this.rainbow.getValue()) {
            final int lllllllllllllllllIIlllllIlIlllII = RainbowUtils.r;
            final int lllllllllllllllllIIlllllIlIllIll = RainbowUtils.g;
            final int lllllllllllllllllIIlllllIlIllIlI = RainbowUtils.b;
        }
        else {
            final int lllllllllllllllllIIlllllIlIlIlll = this.red.getValue();
            final int lllllllllllllllllIIlllllIlIlIllI = this.green.getValue();
            final int lllllllllllllllllIIlllllIlIlIlIl = this.blue.getValue();
        }
        XuluTessellator.prepare(7);
        RenderManager lllllllllllllllllIIllllIlllIlIII;
        Timer lllllllllllllllllIIllllIlllIIlll;
        double lllllllllllllllllIIllllIlllIIllI;
        double lllllllllllllllllIIllllIlllIIlIl;
        double lllllllllllllllllIIllllIlllIIlII;
        AxisAlignedBB lllllllllllllllllIIllllIlllIIIll;
        AxisAlignedBB lllllllllllllllllIIllllIlllIIIlI;
        final int llllllllllllllllIllIIlIlllIlllll;
        final int llllllllllllllllIllIIlIlllIllllI;
        final int llllllllllllllllIllIIlIlllIlllIl;
        ItemESP.mc.world.loadedEntityList.stream().filter(lllllllllllllllllIIllllIllIlIIII -> (lllllllllllllllllIIllllIllIlIIII instanceof EntityItem && this.items.getValue()) || (lllllllllllllllllIIllllIllIlIIII instanceof EntityExpBottle && this.xpbottles.getValue()) || (lllllllllllllllllIIllllIllIlIIII instanceof EntityEnderPearl && this.pearls.getValue())).forEach(lllllllllllllllllIIllllIlllIlIIl -> {
            if (this.mode.getValue().equalsIgnoreCase("text")) {
                this.drawText(lllllllllllllllllIIllllIlllIlIIl);
                return;
            }
            else if (this.mode.getValue().equalsIgnoreCase("shader")) {
                lllllllllllllllllIIllllIlllIlIIl.setGlowing(true);
                return;
            }
            else if (this.mode.getValue().equalsIgnoreCase("chams")) {
                return;
            }
            else {
                lllllllllllllllllIIllllIlllIlIIl.setGlowing(false);
                lllllllllllllllllIIllllIlllIlIII = ItemESP.mc.renderManager;
                lllllllllllllllllIIllllIlllIIlll = ItemESP.mc.timer;
                lllllllllllllllllIIllllIlllIIllI = lllllllllllllllllIIllllIlllIlIIl.lastTickPosX + (lllllllllllllllllIIllllIlllIlIIl.posX - lllllllllllllllllIIllllIlllIlIIl.lastTickPosX) * lllllllllllllllllIIllllIlllIIlll.renderPartialTicks - lllllllllllllllllIIllllIlllIlIII.renderPosX;
                lllllllllllllllllIIllllIlllIIlIl = lllllllllllllllllIIllllIlllIlIIl.lastTickPosY + (lllllllllllllllllIIllllIlllIlIIl.posY - lllllllllllllllllIIllllIlllIlIIl.lastTickPosY) * lllllllllllllllllIIllllIlllIIlll.renderPartialTicks - lllllllllllllllllIIllllIlllIlIII.renderPosY;
                lllllllllllllllllIIllllIlllIIlII = lllllllllllllllllIIllllIlllIlIIl.lastTickPosZ + (lllllllllllllllllIIllllIlllIlIIl.posZ - lllllllllllllllllIIllllIlllIlIIl.lastTickPosZ) * lllllllllllllllllIIllllIlllIIlll.renderPartialTicks - lllllllllllllllllIIllllIlllIlIII.renderPosZ;
                lllllllllllllllllIIllllIlllIIIll = lllllllllllllllllIIllllIlllIlIIl.getEntityBoundingBox();
                lllllllllllllllllIIllllIlllIIIlI = new AxisAlignedBB(lllllllllllllllllIIllllIlllIIIll.minX - lllllllllllllllllIIllllIlllIlIIl.posX + lllllllllllllllllIIllllIlllIIllI - 0.05, lllllllllllllllllIIllllIlllIIIll.minY - lllllllllllllllllIIllllIlllIlIIl.posY + lllllllllllllllllIIllllIlllIIlIl, lllllllllllllllllIIllllIlllIIIll.minZ - lllllllllllllllllIIllllIlllIlIIl.posZ + lllllllllllllllllIIllllIlllIIlII - 0.05, lllllllllllllllllIIllllIlllIIIll.maxX - lllllllllllllllllIIllllIlllIlIIl.posX + lllllllllllllllllIIllllIlllIIllI + 0.05, lllllllllllllllllIIllllIlllIIIll.maxY - lllllllllllllllllIIllllIlllIlIIl.posY + lllllllllllllllllIIllllIlllIIlIl + 0.15, lllllllllllllllllIIllllIlllIIIll.maxZ - lllllllllllllllllIIllllIlllIlIIl.posZ + lllllllllllllllllIIllllIlllIIlII + 0.05);
                if (lllllllllllllllllIIllllIlllIlIIl instanceof EntityEnderPearl) {
                    if (this.rainbow.getValue()) {
                        switch (this.outline.getValue()) {
                            case SOLID: {
                                XuluTessellator.drawBox2(lllllllllllllllllIIllllIlllIIIlI, RainbowUtils.r, RainbowUtils.g, RainbowUtils.b, this.palpha.getValue(), 63);
                                break;
                            }
                            case OUTLINE: {
                                XuluTessellator.drawBoundingBox(lllllllllllllllllIIllllIlllIIIlI, this.width.getValue(), RainbowUtils.r, RainbowUtils.g, RainbowUtils.b, this.palpha.getValue());
                                break;
                            }
                            case FULL: {
                                XuluTessellator.drawFullBoxAA(lllllllllllllllllIIllllIlllIIIlI, this.width.getValue(), RainbowUtils.r, RainbowUtils.g, RainbowUtils.b, this.palpha.getValue(), this.alphaF.getValue());
                                break;
                            }
                        }
                    }
                    else {
                        switch (this.outline.getValue()) {
                            case SOLID: {
                                XuluTessellator.drawBox2(lllllllllllllllllIIllllIlllIIIlI, this.pred.getValue(), this.pgreen.getValue(), this.pblue.getValue(), this.palpha.getValue(), 63);
                                break;
                            }
                            case OUTLINE: {
                                XuluTessellator.drawBoundingBox(lllllllllllllllllIIllllIlllIIIlI, this.width.getValue(), this.pred.getValue(), this.pgreen.getValue(), this.pblue.getValue(), this.palpha.getValue());
                                break;
                            }
                            case FULL: {
                                XuluTessellator.drawFullBoxAA(lllllllllllllllllIIllllIlllIIIlI, this.width.getValue(), this.pred.getValue(), this.pgreen.getValue(), this.pblue.getValue(), this.palpha.getValue(), this.alphaF.getValue());
                                break;
                            }
                        }
                    }
                }
                else {
                    switch (this.outline.getValue()) {
                        case SOLID: {
                            XuluTessellator.drawBox2(lllllllllllllllllIIllllIlllIIIlI, llllllllllllllllIllIIlIlllIlllll, llllllllllllllllIllIIlIlllIllllI, llllllllllllllllIllIIlIlllIlllIl, this.alpha.getValue(), 63);
                            break;
                        }
                        case OUTLINE: {
                            XuluTessellator.drawBoundingBox(lllllllllllllllllIIllllIlllIIIlI, this.width.getValue(), llllllllllllllllIllIIlIlllIlllll, llllllllllllllllIllIIlIlllIllllI, llllllllllllllllIllIIlIlllIlllIl, this.alpha.getValue());
                            break;
                        }
                        case FULL: {
                            XuluTessellator.drawFullBoxAA(lllllllllllllllllIIllllIlllIIIlI, this.width.getValue(), llllllllllllllllIllIIlIlllIlllll, llllllllllllllllIllIIlIlllIllllI, llllllllllllllllIllIIlIlllIlllIl, this.alpha.getValue(), this.alphaF.getValue());
                            break;
                        }
                    }
                }
                return;
            }
        });
        XuluTessellator.release();
    }
    
    public ItemESP() {
        super("ItemESP", "Highlights items", 0, Category.RENDER, true);
        this.mode = this.register(new Value<String>("Mode", this, "Box", new ArrayList<String>(Arrays.asList("Box", "Text", "Shader", "Chams"))));
        this.outlinewidth = this.register(new Value<Integer>("Shader Width", this, 1, 1, 10));
        this.rainbow = this.register(new Value<Boolean>("Rainbow", this, false));
        this.items = this.register(new Value<Boolean>("Items", this, true));
        this.xpbottles = this.register(new Value<Boolean>("EXP Bottles", this, true));
        this.pearls = this.register(new Value<Boolean>("Pearls", this, true));
        this.red = this.register(new Value<Integer>("Red", this, 255, 0, 255));
        this.green = this.register(new Value<Integer>("Green", this, 0, 0, 255));
        this.blue = this.register(new Value<Integer>("Blue", this, 0, 0, 255));
        this.alpha = this.register(new Value<Integer>("Alpha", this, 80, 0, 255));
        this.alphaF = this.register(new Value<Integer>("Full Alpha", this, 80, 0, 255));
        this.pred = this.register(new Value<Integer>("Pearl Red", this, 255, 0, 255));
        this.pgreen = this.register(new Value<Integer>("Pearl Green", this, 255, 0, 255));
        this.pblue = this.register(new Value<Integer>("Pearl Blue", this, 255, 0, 255));
        this.palpha = this.register(new Value<Integer>("Pearl Alpha", this, 255, 0, 255));
        this.outline = this.register(new Value<RenderMode>("Render Mode", this, RenderMode.SOLID, RenderMode.values()));
        this.width = this.register(new Value<Integer>("Width", this, 1, 1, 10));
        ItemESP.INSTANCE = this;
    }
    
    @Override
    public void onDisable() {
        ItemESP.mc.world.loadedEntityList.stream().filter(lllllllllllllllllIIllllIllIIIlll -> (lllllllllllllllllIIllllIllIIIlll instanceof EntityItem && this.items.getValue()) || (lllllllllllllllllIIllllIllIIIlll instanceof EntityExpBottle && this.xpbottles.getValue()) || (lllllllllllllllllIIllllIllIIIlll instanceof EntityEnderPearl && this.pearls.getValue())).forEach(lllllllllllllllllIIllllIllIIllIl -> lllllllllllllllllIIllllIllIIllIl.setGlowing(false));
    }
    
    public void render1(final float lllllllllllllllllIIlllllIIIllIII) {
        RenderHelper.enableStandardItemLighting();
        for (final Entity lllllllllllllllllIIlllllIIIllIlI : ItemESP.mc.world.loadedEntityList) {
            if (lllllllllllllllllIIlllllIIIllIlI instanceof EntityItem) {
                final EntityItem lllllllllllllllllIIlllllIIIllIll = (EntityItem)lllllllllllllllllIIlllllIIIllIlI;
                GL11.glPushMatrix();
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                ItemESP.mc.getRenderManager().renderShadow = false;
                ItemESP.mc.getRenderManager().renderEntityStatic((Entity)lllllllllllllllllIIlllllIIIllIll, lllllllllllllllllIIlllllIIIllIII, true);
                GL11.glPopMatrix();
            }
        }
    }
    
    private void drawText(final Entity lllllllllllllllllIIlllllIIllllll) {
        GlStateManager.pushMatrix();
        final double lllllllllllllllllIIlllllIIlllllI = 1.0;
        final String lllllllllllllllllIIlllllIIllllIl = (lllllllllllllllllIIlllllIIllllll instanceof EntityItem) ? ((EntityItem)lllllllllllllllllIIlllllIIllllll).getItem().getDisplayName() : ((lllllllllllllllllIIlllllIIllllll instanceof EntityEnderPearl) ? "Thrown Ender Pearl" : ((lllllllllllllllllIIlllllIIllllll instanceof EntityExpBottle) ? "Thrown Exp Bottle" : "null"));
        final Vec3d lllllllllllllllllIIlllllIIllllII = EntityUtil.getInterpolatedRenderPos(lllllllllllllllllIIlllllIIllllll, ItemESP.mc.getRenderPartialTicks());
        final float lllllllllllllllllIIlllllIIlllIll = lllllllllllllllllIIlllllIIllllll.height / 2.0f + 0.5f;
        final double lllllllllllllllllIIlllllIIlllIlI = lllllllllllllllllIIlllllIIllllII.x;
        final double lllllllllllllllllIIlllllIIlllIIl = lllllllllllllllllIIlllllIIllllII.y + lllllllllllllllllIIlllllIIlllIll;
        final double lllllllllllllllllIIlllllIIlllIII = lllllllllllllllllIIlllllIIllllII.z;
        final float lllllllllllllllllIIlllllIIllIlll = ItemESP.mc.getRenderManager().playerViewY;
        final float lllllllllllllllllIIlllllIIllIllI = ItemESP.mc.getRenderManager().playerViewX;
        final boolean lllllllllllllllllIIlllllIIllIlIl = ItemESP.mc.getRenderManager().options.thirdPersonView == 2;
        GlStateManager.translate(lllllllllllllllllIIlllllIIlllIlI, lllllllllllllllllIIlllllIIlllIIl, lllllllllllllllllIIlllllIIlllIII);
        GlStateManager.rotate(-lllllllllllllllllIIlllllIIllIlll, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate((lllllllllllllllllIIlllllIIllIlIl ? -1 : 1) * lllllllllllllllllIIlllllIIllIllI, 1.0f, 0.0f, 0.0f);
        final float lllllllllllllllllIIlllllIIllIlII = ItemESP.mc.player.getDistance(lllllllllllllllllIIlllllIIllllll);
        final float lllllllllllllllllIIlllllIIllIIll = lllllllllllllllllIIlllllIIllIlII / 8.0f * (float)Math.pow(1.258925437927246, lllllllllllllllllIIlllllIIlllllI);
        GlStateManager.scale(lllllllllllllllllIIlllllIIllIIll, lllllllllllllllllIIlllllIIllIIll, lllllllllllllllllIIlllllIIllIIll);
        final FontRenderer lllllllllllllllllIIlllllIIllIIlI = ItemESP.mc.fontRenderer;
        GlStateManager.scale(-0.025f, -0.025f, 0.025f);
        final String lllllllllllllllllIIlllllIIllIIIl = String.valueOf(new StringBuilder().append(lllllllllllllllllIIlllllIIllllIl).append((lllllllllllllllllIIlllllIIllllll instanceof EntityItem) ? String.valueOf(new StringBuilder().append(" x").append(((EntityItem)lllllllllllllllllIIlllllIIllllll).getItem().getCount())) : ""));
        final int lllllllllllllllllIIlllllIIllIIII = lllllllllllllllllIIlllllIIllIIlI.getStringWidth(lllllllllllllllllIIlllllIIllIIIl) / 2;
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
        if (Xulu.CustomFont) {
            Xulu.cFontRenderer.drawStringWithShadow(lllllllllllllllllIIlllllIIllIIIl, -lllllllllllllllllIIlllllIIllIIII, 9.0, ColorUtils.Colors.WHITE);
        }
        else {
            GlStateManager.enableTexture2D();
            lllllllllllllllllIIlllllIIllIIlI.drawStringWithShadow(lllllllllllllllllIIlllllIIllIIIl, (float)(-lllllllllllllllllIIlllllIIllIIII), 9.0f, ColorUtils.Colors.WHITE);
            GlStateManager.disableTexture2D();
        }
        GlStateManager.glNormal3f(0.0f, 0.0f, 0.0f);
        GlStateManager.popMatrix();
    }
    
    public void render2(final float lllllllllllllllllIIlllllIIIIIlIl) {
        RenderHelper.enableStandardItemLighting();
        int lllllllllllllllllIIlllllIIIIIlII = 0;
        int lllllllllllllllllIIlllllIIIIIIll = 0;
        int lllllllllllllllllIIlllllIIIIIIlI = 0;
        if (this.rainbow.getValue()) {
            final int lllllllllllllllllIIlllllIIIIlIll = RainbowUtils.r;
            final int lllllllllllllllllIIlllllIIIIlIlI = RainbowUtils.g;
            final int lllllllllllllllllIIlllllIIIIlIIl = RainbowUtils.b;
        }
        else {
            lllllllllllllllllIIlllllIIIIIlII = this.red.getValue();
            lllllllllllllllllIIlllllIIIIIIll = this.green.getValue();
            lllllllllllllllllIIlllllIIIIIIlI = this.blue.getValue();
        }
        for (final Entity lllllllllllllllllIIlllllIIIIIlll : ItemESP.mc.world.loadedEntityList) {
            if (lllllllllllllllllIIlllllIIIIIlll instanceof EntityItem) {
                final EntityItem lllllllllllllllllIIlllllIIIIlIII = (EntityItem)lllllllllllllllllIIlllllIIIIIlll;
                GlStateManager.pushMatrix();
                OutlineUtils2.setColor(new Color(lllllllllllllllllIIlllllIIIIIlII, lllllllllllllllllIIlllllIIIIIIll, lllllllllllllllllIIlllllIIIIIIlI));
                ItemESP.mc.getRenderManager().renderShadow = false;
                ItemESP.mc.getRenderManager().renderEntityStatic((Entity)lllllllllllllllllIIlllllIIIIlIII, lllllllllllllllllIIlllllIIIIIlIl, true);
                GlStateManager.popMatrix();
            }
        }
    }
    
    private enum RenderMode
    {
        FULL, 
        SOLID, 
        OUTLINE;
    }
}
