package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import net.minecraft.entity.player.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.item.*;
import dev.xulu.newgui.util.*;
import java.awt.*;
import net.minecraft.entity.monster.*;
import com.elementars.eclient.module.*;
import java.util.*;
import net.minecraft.client.renderer.culling.*;
import net.minecraft.client.entity.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.event.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.entity.*;
import com.elementars.eclient.friend.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.*;
import com.elementars.eclient.event.events.*;

public class OutlineESP extends Module
{
    public final /* synthetic */ Value<String> mode;
    public final /* synthetic */ Value<Boolean> chams;
    public final /* synthetic */ Value<Float> width;
    private final /* synthetic */ Value<Integer> blue;
    public final /* synthetic */ Value<String> color;
    private final /* synthetic */ Value<Integer> green;
    public final /* synthetic */ Value<Boolean> crystals;
    public final /* synthetic */ Value<Boolean> renderEntities;
    /* synthetic */ float gamma;
    /* synthetic */ ICamera camera;
    public static /* synthetic */ Value<Boolean> future;
    public final /* synthetic */ Value<Boolean> mobs;
    public final /* synthetic */ Value<Boolean> renderCrystals;
    /* synthetic */ boolean fancyGraphics;
    private final /* synthetic */ Value<Integer> red;
    public final /* synthetic */ Value<Boolean> animals;
    public final /* synthetic */ Value<Boolean> players;
    public final /* synthetic */ Value<Boolean> friends;
    public final /* synthetic */ Value<Boolean> onTop;
    
    public static void renderNormal(final float lllllllllllllllllIlllIIllIIlIllI) {
        for (final Entity lllllllllllllllllIlllIIllIIlIlll : Wrapper.getMinecraft().world.loadedEntityList) {
            if (lllllllllllllllllIlllIIllIIlIlll != Wrapper.getMinecraft().getRenderViewEntity()) {
                if (!(lllllllllllllllllIlllIIllIIlIlll instanceof AbstractClientPlayer)) {
                    continue;
                }
                final AbstractClientPlayer lllllllllllllllllIlllIIllIIllIll = (AbstractClientPlayer)lllllllllllllllllIlllIIllIIlIlll;
                final double lllllllllllllllllIlllIIllIIllIlI = lllllllllllllllllIlllIIllIIlIlll.lastTickPosX + (lllllllllllllllllIlllIIllIIlIlll.posX - lllllllllllllllllIlllIIllIIlIlll.lastTickPosX) * lllllllllllllllllIlllIIllIIlIllI;
                final double lllllllllllllllllIlllIIllIIllIIl = lllllllllllllllllIlllIIllIIlIlll.lastTickPosY + (lllllllllllllllllIlllIIllIIlIlll.posY - lllllllllllllllllIlllIIllIIlIlll.lastTickPosY) * lllllllllllllllllIlllIIllIIlIllI;
                final double lllllllllllllllllIlllIIllIIllIII = lllllllllllllllllIlllIIllIIlIlll.lastTickPosZ + (lllllllllllllllllIlllIIllIIlIlll.posZ - lllllllllllllllllIlllIIllIIlIlll.lastTickPosZ) * lllllllllllllllllIlllIIllIIlIllI;
                OutlineESP.mc.renderGlobal.renderManager.playerRenderer.doRender(lllllllllllllllllIlllIIllIIllIll, lllllllllllllllllIlllIIllIIllIlI - OutlineESP.mc.renderManager.renderPosX, lllllllllllllllllIlllIIllIIllIIl - OutlineESP.mc.renderManager.renderPosY, lllllllllllllllllIlllIIllIIllIII - OutlineESP.mc.renderManager.renderPosZ, lllllllllllllllllIlllIIllIIlIlll.rotationYaw, lllllllllllllllllIlllIIllIIlIllI);
            }
        }
    }
    
    public static void renderColor(final float lllllllllllllllllIlllIIllIIIIIIl) {
        for (final Entity lllllllllllllllllIlllIIllIIIIIll : Wrapper.getMinecraft().world.loadedEntityList) {
            if (lllllllllllllllllIlllIIllIIIIIll != Wrapper.getMinecraft().getRenderViewEntity()) {
                if (!(lllllllllllllllllIlllIIllIIIIIll instanceof AbstractClientPlayer)) {
                    continue;
                }
                final AbstractClientPlayer lllllllllllllllllIlllIIllIIIIlll = (AbstractClientPlayer)lllllllllllllllllIlllIIllIIIIIll;
                final double lllllllllllllllllIlllIIllIIIIllI = lllllllllllllllllIlllIIllIIIIIll.lastTickPosX + (lllllllllllllllllIlllIIllIIIIIll.posX - lllllllllllllllllIlllIIllIIIIIll.lastTickPosX) * lllllllllllllllllIlllIIllIIIIIIl;
                final double lllllllllllllllllIlllIIllIIIIlIl = lllllllllllllllllIlllIIllIIIIIll.lastTickPosY + (lllllllllllllllllIlllIIllIIIIIll.posY - lllllllllllllllllIlllIIllIIIIIll.lastTickPosY) * lllllllllllllllllIlllIIllIIIIIIl;
                final double lllllllllllllllllIlllIIllIIIIlII = lllllllllllllllllIlllIIllIIIIIll.lastTickPosZ + (lllllllllllllllllIlllIIllIIIIIll.posZ - lllllllllllllllllIlllIIllIIIIIll.lastTickPosZ) * lllllllllllllllllIlllIIllIIIIIIl;
                if (lllllllllllllllllIlllIIllIIIIIll instanceof EntityPlayer) {
                    GL11.glColor3f(5.0f, 255.0f, 240.0f);
                }
                else if (lllllllllllllllllIlllIIllIIIIIll instanceof EntityEnderCrystal) {
                    OutlineUtils2.setColor(ColorUtil.getClickGUIColor());
                }
                else if (EntityUtil.isPassive(lllllllllllllllllIlllIIllIIIIIll)) {
                    if (OutlineESP.future.getValue()) {
                        OutlineUtils2.setColor(new Color(0, 196, 0));
                    }
                    else {
                        OutlineUtils2.setColor(new Color(5, 255, 240));
                    }
                }
                else if (!EntityUtil.isPassive(lllllllllllllllllIlllIIllIIIIIll) || lllllllllllllllllIlllIIllIIIIIll instanceof EntitySpider) {
                    if (OutlineESP.future.getValue()) {
                        OutlineUtils2.setColor(new Color(191, 57, 59));
                    }
                    else {
                        OutlineUtils2.setColor(new Color(255, 0, 102));
                    }
                }
                else {
                    OutlineUtils2.setColor(new Color(1.0f, 1.0f, 0.0f));
                }
                OutlineESP.mc.renderGlobal.renderManager.playerRenderer.doRender(lllllllllllllllllIlllIIllIIIIlll, lllllllllllllllllIlllIIllIIIIllI - OutlineESP.mc.renderManager.renderPosX, lllllllllllllllllIlllIIllIIIIlIl - OutlineESP.mc.renderManager.renderPosY, lllllllllllllllllIlllIIllIIIIlII - OutlineESP.mc.renderManager.renderPosZ, lllllllllllllllllIlllIIllIIIIIll.rotationYaw, lllllllllllllllllIlllIIllIIIIIIl);
            }
        }
    }
    
    public OutlineESP() {
        super("OutlineESP", "Outlines entities", 0, Category.RENDER, true);
        this.mode = this.register(new Value<String>("Mode", this, "Outline", new ArrayList<String>(Arrays.asList("Outline", "Wireframe", "Solid", "Shader"))));
        this.width = this.register(new Value<Float>("Line Width", this, 1.0f, 0.0f, 10.0f));
        this.chams = this.register(new Value<Boolean>("Chams", this, false));
        this.onTop = this.register(new Value<Boolean>("OnTop", this, true));
        this.players = this.register(new Value<Boolean>("Players", this, true));
        this.animals = this.register(new Value<Boolean>("Animals", this, true));
        this.mobs = this.register(new Value<Boolean>("Mobs", this, true));
        this.crystals = this.register(new Value<Boolean>("Crystals", this, true));
        this.color = this.register(new Value<String>("Color Mode", this, "Tracers", new String[] { "ClickGui", "Tracers", "Target", "Rainbow" }));
        this.renderEntities = this.register(new Value<Boolean>("Render Entities", this, true));
        this.renderCrystals = this.register(new Value<Boolean>("Render Crystals", this, true));
        this.friends = this.register(new Value<Boolean>("Friends", this, true));
        this.red = this.register(new Value<Integer>("Target Red", this, 255, 0, 255));
        this.green = this.register(new Value<Integer>("Target Green", this, 0, 0, 255));
        this.blue = this.register(new Value<Integer>("TargetBlue", this, 0, 0, 255));
        this.camera = (ICamera)new Frustum();
        OutlineESP.future = this.register(new Value<Boolean>("Future Colors", this, true));
    }
    
    @EventTarget
    public void renderPost(final EventPostRenderLayers lllllllllllllllllIlllIIlllIlllII) {
        if (!lllllllllllllllllIlllIIlllIlllII.renderer.bindEntityTexture((Entity)lllllllllllllllllIlllIIlllIlllII.entity)) {
            return;
        }
        final Vec3d lllllllllllllllllIlllIIlllIllllI = MathUtil.interpolateEntity((Entity)OutlineESP.mc.player, lllllllllllllllllIlllIIlllIlllII.getPartialTicks());
        this.camera.setPosition(lllllllllllllllllIlllIIlllIllllI.x, lllllllllllllllllIlllIIlllIllllI.y, lllllllllllllllllIlllIIlllIllllI.z);
        if (!this.camera.isBoundingBoxInFrustum(lllllllllllllllllIlllIIlllIlllII.entity.getEntityBoundingBox())) {
            return;
        }
        if (lllllllllllllllllIlllIIlllIlllII.getEventState() == Event.State.PRE) {
            this.fancyGraphics = OutlineESP.mc.gameSettings.fancyGraphics;
            OutlineESP.mc.gameSettings.fancyGraphics = false;
            this.gamma = OutlineESP.mc.gameSettings.gammaSetting;
            OutlineESP.mc.gameSettings.gammaSetting = 10000.0f;
            if (this.onTop.getValue() && this.renderEntities.getValue()) {
                lllllllllllllllllIlllIIlllIlllII.modelBase.render((Entity)lllllllllllllllllIlllIIlllIlllII.entity, lllllllllllllllllIlllIIlllIlllII.limbSwing, lllllllllllllllllIlllIIlllIlllII.limbSwingAmount, lllllllllllllllllIlllIIlllIlllII.ageInTicks, lllllllllllllllllIlllIIlllIlllII.netHeadYaw, lllllllllllllllllIlllIIlllIlllII.headPitch, lllllllllllllllllIlllIIlllIlllII.scaleIn);
            }
            if (this.mode.getValue().equalsIgnoreCase("Shader")) {
                if (lllllllllllllllllIlllIIlllIlllII.entity instanceof EntityOtherPlayerMP && this.players.getValue()) {
                    lllllllllllllllllIlllIIlllIlllII.entity.setGlowing(true);
                }
                else if (this.animals.getValue() && EntityUtil.isPassive((Entity)lllllllllllllllllIlllIIlllIlllII.entity)) {
                    lllllllllllllllllIlllIIlllIlllII.entity.setGlowing(true);
                }
                else if (this.mobs.getValue() && !EntityUtil.isPassive((Entity)lllllllllllllllllIlllIIlllIlllII.entity)) {
                    lllllllllllllllllIlllIIlllIlllII.entity.setGlowing(true);
                }
                else {
                    lllllllllllllllllIlllIIlllIlllII.entity.setGlowing(false);
                }
            }
            else {
                lllllllllllllllllIlllIIlllIlllII.entity.setGlowing(false);
                if (!this.mode.getValue().equalsIgnoreCase("Outline")) {
                    if (lllllllllllllllllIlllIIlllIlllII.entity instanceof EntityOtherPlayerMP && this.players.getValue()) {
                        GL11.glPushMatrix();
                        GL11.glEnable(32823);
                        GL11.glPolygonOffset(1.0f, -100000.0f);
                        GL11.glPushAttrib(1048575);
                        if (this.mode.getValue().equalsIgnoreCase("Solid")) {
                            GL11.glPolygonMode(1028, 6913);
                        }
                        else {
                            GL11.glPolygonMode(1032, 6913);
                        }
                        GL11.glDisable(3553);
                        GL11.glDisable(2896);
                        GL11.glDisable(2929);
                        GL11.glEnable(2848);
                        GL11.glEnable(3042);
                        GL11.glBlendFunc(770, 771);
                        OutlineUtils.setColor(this.getColor((EntityPlayer)lllllllllllllllllIlllIIlllIlllII.entity));
                        GL11.glLineWidth((float)this.width.getValue());
                        lllllllllllllllllIlllIIlllIlllII.modelBase.render((Entity)lllllllllllllllllIlllIIlllIlllII.entity, lllllllllllllllllIlllIIlllIlllII.limbSwing, lllllllllllllllllIlllIIlllIlllII.limbSwingAmount, lllllllllllllllllIlllIIlllIlllII.ageInTicks, lllllllllllllllllIlllIIlllIlllII.netHeadYaw, lllllllllllllllllIlllIIlllIlllII.headPitch, lllllllllllllllllIlllIIlllIlllII.scaleIn);
                        GL11.glPopAttrib();
                        GL11.glPolygonOffset(1.0f, 100000.0f);
                        GL11.glDisable(32823);
                        GL11.glPopMatrix();
                    }
                    else if (this.animals.getValue() && EntityUtil.isPassive((Entity)lllllllllllllllllIlllIIlllIlllII.entity)) {
                        GL11.glPushMatrix();
                        GL11.glPushAttrib(1048575);
                        GL11.glPolygonMode(1032, 6913);
                        GL11.glDisable(3553);
                        GL11.glDisable(2896);
                        GL11.glDisable(2929);
                        GL11.glEnable(2848);
                        GL11.glEnable(3042);
                        GL11.glBlendFunc(770, 771);
                        OutlineUtils.setColor(getEntityColor((Entity)lllllllllllllllllIlllIIlllIlllII.entity));
                        GL11.glLineWidth((float)this.width.getValue());
                        lllllllllllllllllIlllIIlllIlllII.modelBase.render((Entity)lllllllllllllllllIlllIIlllIlllII.entity, lllllllllllllllllIlllIIlllIlllII.limbSwing, lllllllllllllllllIlllIIlllIlllII.limbSwingAmount, lllllllllllllllllIlllIIlllIlllII.ageInTicks, lllllllllllllllllIlllIIlllIlllII.netHeadYaw, lllllllllllllllllIlllIIlllIlllII.headPitch, lllllllllllllllllIlllIIlllIlllII.scaleIn);
                        GL11.glPopAttrib();
                        GL11.glPopMatrix();
                    }
                    else if (this.mobs.getValue() && !EntityUtil.isPassive((Entity)lllllllllllllllllIlllIIlllIlllII.entity)) {
                        GL11.glPushMatrix();
                        GL11.glPushAttrib(1048575);
                        GL11.glPolygonMode(1032, 6913);
                        GL11.glDisable(3553);
                        GL11.glDisable(2896);
                        GL11.glDisable(2929);
                        GL11.glEnable(2848);
                        GL11.glEnable(3042);
                        GL11.glBlendFunc(770, 771);
                        OutlineUtils.setColor(getEntityColor((Entity)lllllllllllllllllIlllIIlllIlllII.entity));
                        GL11.glLineWidth((float)this.width.getValue());
                        lllllllllllllllllIlllIIlllIlllII.modelBase.render((Entity)lllllllllllllllllIlllIIlllIlllII.entity, lllllllllllllllllIlllIIlllIlllII.limbSwing, lllllllllllllllllIlllIIlllIlllII.limbSwingAmount, lllllllllllllllllIlllIIlllIlllII.ageInTicks, lllllllllllllllllIlllIIlllIlllII.netHeadYaw, lllllllllllllllllIlllIIlllIlllII.headPitch, lllllllllllllllllIlllIIlllIlllII.scaleIn);
                        GL11.glPopAttrib();
                        GL11.glPopMatrix();
                    }
                }
                else if (lllllllllllllllllIlllIIlllIlllII.entity instanceof EntityOtherPlayerMP && this.players.getValue()) {
                    final Color lllllllllllllllllIlllIIllllIIlIl = this.getColor((EntityPlayer)lllllllllllllllllIlllIIlllIlllII.entity);
                    lllllllllllllllllIlllIIlllIlllII.modelBase.render((Entity)lllllllllllllllllIlllIIlllIlllII.entity, lllllllllllllllllIlllIIlllIlllII.limbSwing, lllllllllllllllllIlllIIlllIlllII.limbSwingAmount, lllllllllllllllllIlllIIlllIlllII.ageInTicks, lllllllllllllllllIlllIIlllIlllII.netHeadYaw, lllllllllllllllllIlllIIlllIlllII.headPitch, lllllllllllllllllIlllIIlllIlllII.scaleIn);
                    OutlineUtils.renderOne(this.width.getValue());
                    lllllllllllllllllIlllIIlllIlllII.modelBase.render((Entity)lllllllllllllllllIlllIIlllIlllII.entity, lllllllllllllllllIlllIIlllIlllII.limbSwing, lllllllllllllllllIlllIIlllIlllII.limbSwingAmount, lllllllllllllllllIlllIIlllIlllII.ageInTicks, lllllllllllllllllIlllIIlllIlllII.netHeadYaw, lllllllllllllllllIlllIIlllIlllII.headPitch, lllllllllllllllllIlllIIlllIlllII.scaleIn);
                    OutlineUtils.renderTwo();
                    lllllllllllllllllIlllIIlllIlllII.modelBase.render((Entity)lllllllllllllllllIlllIIlllIlllII.entity, lllllllllllllllllIlllIIlllIlllII.limbSwing, lllllllllllllllllIlllIIlllIlllII.limbSwingAmount, lllllllllllllllllIlllIIlllIlllII.ageInTicks, lllllllllllllllllIlllIIlllIlllII.netHeadYaw, lllllllllllllllllIlllIIlllIlllII.headPitch, lllllllllllllllllIlllIIlllIlllII.scaleIn);
                    OutlineUtils.renderThree();
                    OutlineUtils.renderFour(lllllllllllllllllIlllIIllllIIlIl);
                    OutlineUtils.setColor(lllllllllllllllllIlllIIllllIIlIl);
                    lllllllllllllllllIlllIIlllIlllII.modelBase.render((Entity)lllllllllllllllllIlllIIlllIlllII.entity, lllllllllllllllllIlllIIlllIlllII.limbSwing, lllllllllllllllllIlllIIlllIlllII.limbSwingAmount, lllllllllllllllllIlllIIlllIlllII.ageInTicks, lllllllllllllllllIlllIIlllIlllII.netHeadYaw, lllllllllllllllllIlllIIlllIlllII.headPitch, lllllllllllllllllIlllIIlllIlllII.scaleIn);
                    OutlineUtils.renderFive();
                    OutlineUtils.setColor(Color.WHITE);
                }
                else if (this.animals.getValue() && EntityUtil.isPassive((Entity)lllllllllllllllllIlllIIlllIlllII.entity)) {
                    Color lllllllllllllllllIlllIIllllIIIll = null;
                    if (OutlineESP.future.getValue()) {
                        final Color lllllllllllllllllIlllIIllllIIlII = new Color(0, 196, 0);
                    }
                    else {
                        lllllllllllllllllIlllIIllllIIIll = new Color(5, 255, 240);
                    }
                    GL11.glLineWidth(5.0f);
                    lllllllllllllllllIlllIIlllIlllII.modelBase.render((Entity)lllllllllllllllllIlllIIlllIlllII.entity, lllllllllllllllllIlllIIlllIlllII.limbSwing, lllllllllllllllllIlllIIlllIlllII.limbSwingAmount, lllllllllllllllllIlllIIlllIlllII.ageInTicks, lllllllllllllllllIlllIIlllIlllII.netHeadYaw, lllllllllllllllllIlllIIlllIlllII.headPitch, lllllllllllllllllIlllIIlllIlllII.scaleIn);
                    OutlineUtils.renderOne(this.width.getValue());
                    lllllllllllllllllIlllIIlllIlllII.modelBase.render((Entity)lllllllllllllllllIlllIIlllIlllII.entity, lllllllllllllllllIlllIIlllIlllII.limbSwing, lllllllllllllllllIlllIIlllIlllII.limbSwingAmount, lllllllllllllllllIlllIIlllIlllII.ageInTicks, lllllllllllllllllIlllIIlllIlllII.netHeadYaw, lllllllllllllllllIlllIIlllIlllII.headPitch, lllllllllllllllllIlllIIlllIlllII.scaleIn);
                    OutlineUtils.renderTwo();
                    lllllllllllllllllIlllIIlllIlllII.modelBase.render((Entity)lllllllllllllllllIlllIIlllIlllII.entity, lllllllllllllllllIlllIIlllIlllII.limbSwing, lllllllllllllllllIlllIIlllIlllII.limbSwingAmount, lllllllllllllllllIlllIIlllIlllII.ageInTicks, lllllllllllllllllIlllIIlllIlllII.netHeadYaw, lllllllllllllllllIlllIIlllIlllII.headPitch, lllllllllllllllllIlllIIlllIlllII.scaleIn);
                    OutlineUtils.renderThree();
                    OutlineUtils.renderFour(lllllllllllllllllIlllIIllllIIIll);
                    lllllllllllllllllIlllIIlllIlllII.modelBase.render((Entity)lllllllllllllllllIlllIIlllIlllII.entity, lllllllllllllllllIlllIIlllIlllII.limbSwing, lllllllllllllllllIlllIIlllIlllII.limbSwingAmount, lllllllllllllllllIlllIIlllIlllII.ageInTicks, lllllllllllllllllIlllIIlllIlllII.netHeadYaw, lllllllllllllllllIlllIIlllIlllII.headPitch, lllllllllllllllllIlllIIlllIlllII.scaleIn);
                    OutlineUtils.renderFive();
                }
                else if (this.mobs.getValue() && !EntityUtil.isPassive((Entity)lllllllllllllllllIlllIIlllIlllII.entity) && !(lllllllllllllllllIlllIIlllIlllII.entity instanceof EntityPlayer)) {
                    Color lllllllllllllllllIlllIIllllIIIIl = null;
                    if (OutlineESP.future.getValue()) {
                        final Color lllllllllllllllllIlllIIllllIIIlI = new Color(191, 57, 59);
                    }
                    else {
                        lllllllllllllllllIlllIIllllIIIIl = new Color(255, 0, 102);
                    }
                    GL11.glLineWidth(5.0f);
                    lllllllllllllllllIlllIIlllIlllII.modelBase.render((Entity)lllllllllllllllllIlllIIlllIlllII.entity, lllllllllllllllllIlllIIlllIlllII.limbSwing, lllllllllllllllllIlllIIlllIlllII.limbSwingAmount, lllllllllllllllllIlllIIlllIlllII.ageInTicks, lllllllllllllllllIlllIIlllIlllII.netHeadYaw, lllllllllllllllllIlllIIlllIlllII.headPitch, lllllllllllllllllIlllIIlllIlllII.scaleIn);
                    OutlineUtils.renderOne(this.width.getValue());
                    lllllllllllllllllIlllIIlllIlllII.modelBase.render((Entity)lllllllllllllllllIlllIIlllIlllII.entity, lllllllllllllllllIlllIIlllIlllII.limbSwing, lllllllllllllllllIlllIIlllIlllII.limbSwingAmount, lllllllllllllllllIlllIIlllIlllII.ageInTicks, lllllllllllllllllIlllIIlllIlllII.netHeadYaw, lllllllllllllllllIlllIIlllIlllII.headPitch, lllllllllllllllllIlllIIlllIlllII.scaleIn);
                    OutlineUtils.renderTwo();
                    lllllllllllllllllIlllIIlllIlllII.modelBase.render((Entity)lllllllllllllllllIlllIIlllIlllII.entity, lllllllllllllllllIlllIIlllIlllII.limbSwing, lllllllllllllllllIlllIIlllIlllII.limbSwingAmount, lllllllllllllllllIlllIIlllIlllII.ageInTicks, lllllllllllllllllIlllIIlllIlllII.netHeadYaw, lllllllllllllllllIlllIIlllIlllII.headPitch, lllllllllllllllllIlllIIlllIlllII.scaleIn);
                    OutlineUtils.renderThree();
                    OutlineUtils.renderFour(lllllllllllllllllIlllIIllllIIIIl);
                    lllllllllllllllllIlllIIlllIlllII.modelBase.render((Entity)lllllllllllllllllIlllIIlllIlllII.entity, lllllllllllllllllIlllIIlllIlllII.limbSwing, lllllllllllllllllIlllIIlllIlllII.limbSwingAmount, lllllllllllllllllIlllIIlllIlllII.ageInTicks, lllllllllllllllllIlllIIlllIlllII.netHeadYaw, lllllllllllllllllIlllIIlllIlllII.headPitch, lllllllllllllllllIlllIIlllIlllII.scaleIn);
                    OutlineUtils.renderFive();
                }
            }
            if (!this.onTop.getValue() && this.renderEntities.getValue()) {
                lllllllllllllllllIlllIIlllIlllII.modelBase.render((Entity)lllllllllllllllllIlllIIlllIlllII.entity, lllllllllllllllllIlllIIlllIlllII.limbSwing, lllllllllllllllllIlllIIlllIlllII.limbSwingAmount, lllllllllllllllllIlllIIlllIlllII.ageInTicks, lllllllllllllllllIlllIIlllIlllII.netHeadYaw, lllllllllllllllllIlllIIlllIlllII.headPitch, lllllllllllllllllIlllIIlllIlllII.scaleIn);
            }
            OutlineUtils.setColor(Color.WHITE);
            OutlineESP.mc.gameSettings.fancyGraphics = this.fancyGraphics;
            OutlineESP.mc.gameSettings.gammaSetting = this.gamma;
            lllllllllllllllllIlllIIlllIlllII.setCancelled(true);
        }
    }
    
    public static void oisD(final Color lllllllllllllllllIlllIIlIllllIIl) {
        GL11.glColor4f(lllllllllllllllllIlllIIlIllllIIl.getRed() / 255.0f, lllllllllllllllllIlllIIlIllllIIl.getGreen() / 255.0f, lllllllllllllllllIlllIIlIllllIIl.getBlue() / 255.0f, lllllllllllllllllIlllIIlIllllIIl.getAlpha() / 255.0f);
    }
    
    public static void renderNormal2(final float lllllllllllllllllIlllIIlllIIIIIl) {
        for (final Entity lllllllllllllllllIlllIIlllIIIIll : Wrapper.getMinecraft().world.loadedEntityList) {
            if (lllllllllllllllllIlllIIlllIIIIll != Wrapper.getMinecraft().getRenderViewEntity()) {
                if (!(lllllllllllllllllIlllIIlllIIIIll instanceof EntityPlayer)) {
                    continue;
                }
                final Render lllllllllllllllllIlllIIlllIIlIII = Wrapper.getMinecraft().getRenderManager().getEntityRenderObject(lllllllllllllllllIlllIIlllIIIIll);
                final RenderLivingBase lllllllllllllllllIlllIIlllIIIlll = (lllllllllllllllllIlllIIlllIIlIII instanceof RenderLivingBase) ? lllllllllllllllllIlllIIlllIIlIII : null;
                final double lllllllllllllllllIlllIIlllIIIllI = lllllllllllllllllIlllIIlllIIIIll.lastTickPosX + (lllllllllllllllllIlllIIlllIIIIll.posX - lllllllllllllllllIlllIIlllIIIIll.lastTickPosX) * lllllllllllllllllIlllIIlllIIIIIl;
                final double lllllllllllllllllIlllIIlllIIIlIl = lllllllllllllllllIlllIIlllIIIIll.lastTickPosY + (lllllllllllllllllIlllIIlllIIIIll.posY - lllllllllllllllllIlllIIlllIIIIll.lastTickPosY) * lllllllllllllllllIlllIIlllIIIIIl;
                final double lllllllllllllllllIlllIIlllIIIlII = lllllllllllllllllIlllIIlllIIIIll.lastTickPosZ + (lllllllllllllllllIlllIIlllIIIIll.posZ - lllllllllllllllllIlllIIlllIIIIll.lastTickPosZ) * lllllllllllllllllIlllIIlllIIIIIl;
                if (lllllllllllllllllIlllIIlllIIIlll == null) {
                    continue;
                }
                lllllllllllllllllIlllIIlllIIIlll.doRender((EntityLivingBase)lllllllllllllllllIlllIIlllIIIIll, lllllllllllllllllIlllIIlllIIIllI - OutlineESP.mc.renderManager.renderPosX, lllllllllllllllllIlllIIlllIIIlIl - OutlineESP.mc.renderManager.renderPosY, lllllllllllllllllIlllIIlllIIIlII - OutlineESP.mc.renderManager.renderPosZ, lllllllllllllllllIlllIIlllIIIIll.rotationYaw, OutlineESP.mc.getRenderPartialTicks());
            }
        }
    }
    
    public static Color getEntityColor(final Entity lllllllllllllllllIlllIIlIlllIlII) {
        if (lllllllllllllllllIlllIIlIlllIlII instanceof EntityPlayer) {
            if (Friends.isFriend(lllllllllllllllllIlllIIlIlllIlII.getName())) {
                return new Color(0.27f, 0.7f, 0.92f);
            }
            final float lllllllllllllllllIlllIIlIlllIlIl = OutlineESP.mc.player.getDistance(lllllllllllllllllIlllIIlIlllIlII);
            if (lllllllllllllllllIlllIIlIlllIlIl <= 32.0f) {
                return new Color(1.0f - lllllllllllllllllIlllIIlIlllIlIl / 32.0f / 2.0f, lllllllllllllllllIlllIIlIlllIlIl / 32.0f, 0.0f);
            }
            return new Color(0.0f, 0.9f, 0.0f);
        }
        else {
            if (lllllllllllllllllIlllIIlIlllIlII instanceof EntityEnderCrystal) {
                return ColorUtil.getClickGUIColor();
            }
            if (EntityUtil.isPassive(lllllllllllllllllIlllIIlIlllIlII)) {
                if (OutlineESP.future.getValue()) {
                    return new Color(0, 196, 0);
                }
                return new Color(5, 255, 240);
            }
            else {
                if (EntityUtil.isPassive(lllllllllllllllllIlllIIlIlllIlII) && !(lllllllllllllllllIlllIIlIlllIlII instanceof EntitySpider)) {
                    return new Color(1.0f, 1.0f, 0.0f);
                }
                if (OutlineESP.future.getValue()) {
                    return new Color(191, 57, 59);
                }
                return new Color(255, 0, 102);
            }
        }
    }
    
    public static void renderColor2(final float lllllllllllllllllIlllIIllIlIlIll) {
        for (final Entity lllllllllllllllllIlllIIllIlIllII : Wrapper.getMinecraft().world.loadedEntityList) {
            if (lllllllllllllllllIlllIIllIlIllII != Wrapper.getMinecraft().getRenderViewEntity()) {
                if (!(lllllllllllllllllIlllIIllIlIllII instanceof EntityPlayer)) {
                    continue;
                }
                final Render lllllllllllllllllIlllIIllIllIIIl = Wrapper.getMinecraft().getRenderManager().getEntityRenderObject(lllllllllllllllllIlllIIllIlIllII);
                final RenderLivingBase lllllllllllllllllIlllIIllIllIIII = (lllllllllllllllllIlllIIllIllIIIl instanceof RenderLivingBase) ? lllllllllllllllllIlllIIllIllIIIl : null;
                if (lllllllllllllllllIlllIIllIlIllII instanceof EntityPlayer) {
                    GL11.glColor3f(5.0f, 255.0f, 240.0f);
                }
                else if (lllllllllllllllllIlllIIllIlIllII instanceof EntityEnderCrystal) {
                    OutlineUtils2.setColor(ColorUtil.getClickGUIColor());
                }
                else if (EntityUtil.isPassive(lllllllllllllllllIlllIIllIlIllII)) {
                    if (OutlineESP.future.getValue()) {
                        OutlineUtils2.setColor(new Color(0, 196, 0));
                    }
                    else {
                        OutlineUtils2.setColor(new Color(5, 255, 240));
                    }
                }
                else if (!EntityUtil.isPassive(lllllllllllllllllIlllIIllIlIllII) || lllllllllllllllllIlllIIllIlIllII instanceof EntitySpider) {
                    if (OutlineESP.future.getValue()) {
                        OutlineUtils2.setColor(new Color(191, 57, 59));
                    }
                    else {
                        OutlineUtils2.setColor(new Color(255, 0, 102));
                    }
                }
                else {
                    OutlineUtils2.setColor(new Color(1.0f, 1.0f, 0.0f));
                }
                final double lllllllllllllllllIlllIIllIlIllll = lllllllllllllllllIlllIIllIlIllII.lastTickPosX + (lllllllllllllllllIlllIIllIlIllII.posX - lllllllllllllllllIlllIIllIlIllII.lastTickPosX) * lllllllllllllllllIlllIIllIlIlIll;
                final double lllllllllllllllllIlllIIllIlIlllI = lllllllllllllllllIlllIIllIlIllII.lastTickPosY + (lllllllllllllllllIlllIIllIlIllII.posY - lllllllllllllllllIlllIIllIlIllII.lastTickPosY) * lllllllllllllllllIlllIIllIlIlIll;
                final double lllllllllllllllllIlllIIllIlIllIl = lllllllllllllllllIlllIIllIlIllII.lastTickPosZ + (lllllllllllllllllIlllIIllIlIllII.posZ - lllllllllllllllllIlllIIllIlIllII.lastTickPosZ) * lllllllllllllllllIlllIIllIlIlIll;
                if (lllllllllllllllllIlllIIllIllIIII == null) {
                    continue;
                }
                lllllllllllllllllIlllIIllIllIIII.doRender((EntityLivingBase)lllllllllllllllllIlllIIllIlIllII, lllllllllllllllllIlllIIllIlIllll, lllllllllllllllllIlllIIllIlIlllI, lllllllllllllllllIlllIIllIlIllIl, lllllllllllllllllIlllIIllIlIllII.rotationYaw, OutlineESP.mc.getRenderPartialTicks());
            }
        }
    }
    
    private Color getColor(final EntityPlayer lllllllllllllllllIlllIIlllIlIlII) {
        if (Friends.isFriend(lllllllllllllllllIlllIIlllIlIlII.getName()) && this.friends.getValue()) {
            return new Color(0.27f, 0.7f, 0.92f);
        }
        if (this.color.getValue().equalsIgnoreCase("ClickGui")) {
            return ColorUtil.getClickGUIColor();
        }
        if (this.color.getValue().equalsIgnoreCase("Tracers")) {
            final float lllllllllllllllllIlllIIlllIlIllI = OutlineESP.mc.player.getDistance((Entity)lllllllllllllllllIlllIIlllIlIlII);
            if (lllllllllllllllllIlllIIlllIlIllI <= 32.0f) {
                return new Color(1.0f - lllllllllllllllllIlllIIlllIlIllI / 32.0f / 2.0f, lllllllllllllllllIlllIIlllIlIllI / 32.0f, 0.0f);
            }
            return new Color(0.0f, 0.9f, 0.0f);
        }
        else if (this.color.getValue().equalsIgnoreCase("Target")) {
            if (TargetPlayers.targettedplayers.containsKey(lllllllllllllllllIlllIIlllIlIlII.getName())) {
                return new Color(this.red.getValue() / 255.0f, this.green.getValue() / 255.0f, this.blue.getValue() / 255.0f);
            }
            return ColorUtil.getClickGUIColor();
        }
        else {
            if (this.color.getValue().equalsIgnoreCase("Rainbow")) {
                return new Color(Xulu.rgb);
            }
            return new Color(1.0f, 1.0f, 1.0f);
        }
    }
    
    @EventTarget
    public void onModelRender(final EventModelRender lllllllllllllllllIlllIIllllIllll) {
        final Vec3d lllllllllllllllllIlllIIllllIlllI = MathUtil.interpolateEntity((Entity)OutlineESP.mc.player, lllllllllllllllllIlllIIllllIllll.getPartialTicks());
        this.camera.setPosition(lllllllllllllllllIlllIIllllIlllI.x, lllllllllllllllllIlllIIllllIlllI.y, lllllllllllllllllIlllIIllllIlllI.z);
        if (!this.camera.isBoundingBoxInFrustum(lllllllllllllllllIlllIIllllIllll.entity.getEntityBoundingBox())) {
            return;
        }
        if (lllllllllllllllllIlllIIllllIllll.getEventState() == Event.State.PRE) {
            this.fancyGraphics = OutlineESP.mc.gameSettings.fancyGraphics;
            OutlineESP.mc.gameSettings.fancyGraphics = false;
            this.gamma = OutlineESP.mc.gameSettings.gammaSetting;
            OutlineESP.mc.gameSettings.gammaSetting = 10000.0f;
            if (this.onTop.getValue() && this.renderEntities.getValue()) {
                lllllllllllllllllIlllIIllllIllll.modelBase.render(lllllllllllllllllIlllIIllllIllll.entity, lllllllllllllllllIlllIIllllIllll.limbSwing, lllllllllllllllllIlllIIllllIllll.limbSwingAmount, lllllllllllllllllIlllIIllllIllll.ageInTicks, lllllllllllllllllIlllIIllllIllll.netHeadYaw, lllllllllllllllllIlllIIllllIllll.headPitch, lllllllllllllllllIlllIIllllIllll.scaleFactor);
            }
            if (this.mode.getValue().equalsIgnoreCase("Shader")) {
                if (lllllllllllllllllIlllIIllllIllll.entity instanceof EntityOtherPlayerMP && this.players.getValue()) {
                    lllllllllllllllllIlllIIllllIllll.entity.setGlowing(true);
                }
                else if (this.animals.getValue() && EntityUtil.isPassive(lllllllllllllllllIlllIIllllIllll.entity)) {
                    lllllllllllllllllIlllIIllllIllll.entity.setGlowing(true);
                }
                else if (this.mobs.getValue() && !EntityUtil.isPassive(lllllllllllllllllIlllIIllllIllll.entity)) {
                    lllllllllllllllllIlllIIllllIllll.entity.setGlowing(true);
                }
                else {
                    lllllllllllllllllIlllIIllllIllll.entity.setGlowing(false);
                }
            }
            else {
                lllllllllllllllllIlllIIllllIllll.entity.setGlowing(false);
                if (!this.mode.getValue().equalsIgnoreCase("Outline")) {
                    if (lllllllllllllllllIlllIIllllIllll.entity instanceof EntityOtherPlayerMP && this.players.getValue()) {
                        GL11.glPushMatrix();
                        GL11.glEnable(32823);
                        GL11.glPolygonOffset(1.0f, -100000.0f);
                        GL11.glPushAttrib(1048575);
                        if (this.mode.getValue().equalsIgnoreCase("Solid")) {
                            GL11.glPolygonMode(1028, 6913);
                        }
                        else {
                            GL11.glPolygonMode(1032, 6913);
                        }
                        GL11.glDisable(3553);
                        GL11.glDisable(2896);
                        GL11.glDisable(2929);
                        GL11.glEnable(2848);
                        GL11.glEnable(3042);
                        GL11.glBlendFunc(770, 771);
                        OutlineUtils.setColor(this.getColor((EntityPlayer)lllllllllllllllllIlllIIllllIllll.entity));
                        GL11.glLineWidth((float)this.width.getValue());
                        lllllllllllllllllIlllIIllllIllll.modelBase.render(lllllllllllllllllIlllIIllllIllll.entity, lllllllllllllllllIlllIIllllIllll.limbSwing, lllllllllllllllllIlllIIllllIllll.limbSwingAmount, lllllllllllllllllIlllIIllllIllll.ageInTicks, lllllllllllllllllIlllIIllllIllll.netHeadYaw, lllllllllllllllllIlllIIllllIllll.headPitch, lllllllllllllllllIlllIIllllIllll.scaleFactor);
                        GL11.glPopAttrib();
                        GL11.glPolygonOffset(1.0f, 100000.0f);
                        GL11.glDisable(32823);
                        GL11.glPopMatrix();
                    }
                    else if (this.animals.getValue() && EntityUtil.isPassive(lllllllllllllllllIlllIIllllIllll.entity)) {
                        GL11.glPushMatrix();
                        GL11.glPushAttrib(1048575);
                        GL11.glPolygonMode(1032, 6913);
                        GL11.glDisable(3553);
                        GL11.glDisable(2896);
                        GL11.glDisable(2929);
                        GL11.glEnable(2848);
                        GL11.glEnable(3042);
                        GL11.glBlendFunc(770, 771);
                        OutlineUtils.setColor(getEntityColor(lllllllllllllllllIlllIIllllIllll.entity));
                        GL11.glLineWidth((float)this.width.getValue());
                        lllllllllllllllllIlllIIllllIllll.modelBase.render(lllllllllllllllllIlllIIllllIllll.entity, lllllllllllllllllIlllIIllllIllll.limbSwing, lllllllllllllllllIlllIIllllIllll.limbSwingAmount, lllllllllllllllllIlllIIllllIllll.ageInTicks, lllllllllllllllllIlllIIllllIllll.netHeadYaw, lllllllllllllllllIlllIIllllIllll.headPitch, lllllllllllllllllIlllIIllllIllll.scaleFactor);
                        GL11.glPopAttrib();
                        GL11.glPopMatrix();
                    }
                    else if (this.mobs.getValue() && !EntityUtil.isPassive(lllllllllllllllllIlllIIllllIllll.entity)) {
                        GL11.glPushMatrix();
                        GL11.glPushAttrib(1048575);
                        GL11.glPolygonMode(1032, 6913);
                        GL11.glDisable(3553);
                        GL11.glDisable(2896);
                        GL11.glDisable(2929);
                        GL11.glEnable(2848);
                        GL11.glEnable(3042);
                        GL11.glBlendFunc(770, 771);
                        OutlineUtils.setColor(getEntityColor(lllllllllllllllllIlllIIllllIllll.entity));
                        GL11.glLineWidth((float)this.width.getValue());
                        lllllllllllllllllIlllIIllllIllll.modelBase.render(lllllllllllllllllIlllIIllllIllll.entity, lllllllllllllllllIlllIIllllIllll.limbSwing, lllllllllllllllllIlllIIllllIllll.limbSwingAmount, lllllllllllllllllIlllIIllllIllll.ageInTicks, lllllllllllllllllIlllIIllllIllll.netHeadYaw, lllllllllllllllllIlllIIllllIllll.headPitch, lllllllllllllllllIlllIIllllIllll.scaleFactor);
                        GL11.glPopAttrib();
                        GL11.glPopMatrix();
                    }
                }
                else if (lllllllllllllllllIlllIIllllIllll.entity instanceof EntityOtherPlayerMP && this.players.getValue()) {
                    final Color lllllllllllllllllIlllIIlllllIlIl = this.getColor((EntityPlayer)lllllllllllllllllIlllIIllllIllll.entity);
                    lllllllllllllllllIlllIIllllIllll.modelBase.render(lllllllllllllllllIlllIIllllIllll.entity, lllllllllllllllllIlllIIllllIllll.limbSwing, lllllllllllllllllIlllIIllllIllll.limbSwingAmount, lllllllllllllllllIlllIIllllIllll.ageInTicks, lllllllllllllllllIlllIIllllIllll.netHeadYaw, lllllllllllllllllIlllIIllllIllll.headPitch, lllllllllllllllllIlllIIllllIllll.scaleFactor);
                    OutlineUtils.renderOne(this.width.getValue());
                    lllllllllllllllllIlllIIllllIllll.modelBase.render(lllllllllllllllllIlllIIllllIllll.entity, lllllllllllllllllIlllIIllllIllll.limbSwing, lllllllllllllllllIlllIIllllIllll.limbSwingAmount, lllllllllllllllllIlllIIllllIllll.ageInTicks, lllllllllllllllllIlllIIllllIllll.netHeadYaw, lllllllllllllllllIlllIIllllIllll.headPitch, lllllllllllllllllIlllIIllllIllll.scaleFactor);
                    OutlineUtils.renderTwo();
                    lllllllllllllllllIlllIIllllIllll.modelBase.render(lllllllllllllllllIlllIIllllIllll.entity, lllllllllllllllllIlllIIllllIllll.limbSwing, lllllllllllllllllIlllIIllllIllll.limbSwingAmount, lllllllllllllllllIlllIIllllIllll.ageInTicks, lllllllllllllllllIlllIIllllIllll.netHeadYaw, lllllllllllllllllIlllIIllllIllll.headPitch, lllllllllllllllllIlllIIllllIllll.scaleFactor);
                    OutlineUtils.renderThree();
                    OutlineUtils.renderFour(lllllllllllllllllIlllIIlllllIlIl);
                    OutlineUtils.setColor(lllllllllllllllllIlllIIlllllIlIl);
                    lllllllllllllllllIlllIIllllIllll.modelBase.render(lllllllllllllllllIlllIIllllIllll.entity, lllllllllllllllllIlllIIllllIllll.limbSwing, lllllllllllllllllIlllIIllllIllll.limbSwingAmount, lllllllllllllllllIlllIIllllIllll.ageInTicks, lllllllllllllllllIlllIIllllIllll.netHeadYaw, lllllllllllllllllIlllIIllllIllll.headPitch, lllllllllllllllllIlllIIllllIllll.scaleFactor);
                    OutlineUtils.renderFive();
                    OutlineUtils.setColor(Color.WHITE);
                }
                else if (this.animals.getValue() && EntityUtil.isPassive(lllllllllllllllllIlllIIllllIllll.entity)) {
                    Color lllllllllllllllllIlllIIlllllIIll = null;
                    if (OutlineESP.future.getValue()) {
                        final Color lllllllllllllllllIlllIIlllllIlII = new Color(0, 196, 0);
                    }
                    else {
                        lllllllllllllllllIlllIIlllllIIll = new Color(5, 255, 240);
                    }
                    GL11.glLineWidth(5.0f);
                    lllllllllllllllllIlllIIllllIllll.modelBase.render(lllllllllllllllllIlllIIllllIllll.entity, lllllllllllllllllIlllIIllllIllll.limbSwing, lllllllllllllllllIlllIIllllIllll.limbSwingAmount, lllllllllllllllllIlllIIllllIllll.ageInTicks, lllllllllllllllllIlllIIllllIllll.netHeadYaw, lllllllllllllllllIlllIIllllIllll.headPitch, lllllllllllllllllIlllIIllllIllll.scaleFactor);
                    OutlineUtils.renderOne(this.width.getValue());
                    lllllllllllllllllIlllIIllllIllll.modelBase.render(lllllllllllllllllIlllIIllllIllll.entity, lllllllllllllllllIlllIIllllIllll.limbSwing, lllllllllllllllllIlllIIllllIllll.limbSwingAmount, lllllllllllllllllIlllIIllllIllll.ageInTicks, lllllllllllllllllIlllIIllllIllll.netHeadYaw, lllllllllllllllllIlllIIllllIllll.headPitch, lllllllllllllllllIlllIIllllIllll.scaleFactor);
                    OutlineUtils.renderTwo();
                    lllllllllllllllllIlllIIllllIllll.modelBase.render(lllllllllllllllllIlllIIllllIllll.entity, lllllllllllllllllIlllIIllllIllll.limbSwing, lllllllllllllllllIlllIIllllIllll.limbSwingAmount, lllllllllllllllllIlllIIllllIllll.ageInTicks, lllllllllllllllllIlllIIllllIllll.netHeadYaw, lllllllllllllllllIlllIIllllIllll.headPitch, lllllllllllllllllIlllIIllllIllll.scaleFactor);
                    OutlineUtils.renderThree();
                    OutlineUtils.renderFour(lllllllllllllllllIlllIIlllllIIll);
                    lllllllllllllllllIlllIIllllIllll.modelBase.render(lllllllllllllllllIlllIIllllIllll.entity, lllllllllllllllllIlllIIllllIllll.limbSwing, lllllllllllllllllIlllIIllllIllll.limbSwingAmount, lllllllllllllllllIlllIIllllIllll.ageInTicks, lllllllllllllllllIlllIIllllIllll.netHeadYaw, lllllllllllllllllIlllIIllllIllll.headPitch, lllllllllllllllllIlllIIllllIllll.scaleFactor);
                    OutlineUtils.renderFive();
                }
                else if (this.mobs.getValue() && !EntityUtil.isPassive(lllllllllllllllllIlllIIllllIllll.entity) && !(lllllllllllllllllIlllIIllllIllll.entity instanceof EntityPlayer)) {
                    Color lllllllllllllllllIlllIIlllllIIIl = null;
                    if (OutlineESP.future.getValue()) {
                        final Color lllllllllllllllllIlllIIlllllIIlI = new Color(191, 57, 59);
                    }
                    else {
                        lllllllllllllllllIlllIIlllllIIIl = new Color(255, 0, 102);
                    }
                    GL11.glLineWidth(5.0f);
                    lllllllllllllllllIlllIIllllIllll.modelBase.render(lllllllllllllllllIlllIIllllIllll.entity, lllllllllllllllllIlllIIllllIllll.limbSwing, lllllllllllllllllIlllIIllllIllll.limbSwingAmount, lllllllllllllllllIlllIIllllIllll.ageInTicks, lllllllllllllllllIlllIIllllIllll.netHeadYaw, lllllllllllllllllIlllIIllllIllll.headPitch, lllllllllllllllllIlllIIllllIllll.scaleFactor);
                    OutlineUtils.renderOne(this.width.getValue());
                    lllllllllllllllllIlllIIllllIllll.modelBase.render(lllllllllllllllllIlllIIllllIllll.entity, lllllllllllllllllIlllIIllllIllll.limbSwing, lllllllllllllllllIlllIIllllIllll.limbSwingAmount, lllllllllllllllllIlllIIllllIllll.ageInTicks, lllllllllllllllllIlllIIllllIllll.netHeadYaw, lllllllllllllllllIlllIIllllIllll.headPitch, lllllllllllllllllIlllIIllllIllll.scaleFactor);
                    OutlineUtils.renderTwo();
                    lllllllllllllllllIlllIIllllIllll.modelBase.render(lllllllllllllllllIlllIIllllIllll.entity, lllllllllllllllllIlllIIllllIllll.limbSwing, lllllllllllllllllIlllIIllllIllll.limbSwingAmount, lllllllllllllllllIlllIIllllIllll.ageInTicks, lllllllllllllllllIlllIIllllIllll.netHeadYaw, lllllllllllllllllIlllIIllllIllll.headPitch, lllllllllllllllllIlllIIllllIllll.scaleFactor);
                    OutlineUtils.renderThree();
                    OutlineUtils.renderFour(lllllllllllllllllIlllIIlllllIIIl);
                    lllllllllllllllllIlllIIllllIllll.modelBase.render(lllllllllllllllllIlllIIllllIllll.entity, lllllllllllllllllIlllIIllllIllll.limbSwing, lllllllllllllllllIlllIIllllIllll.limbSwingAmount, lllllllllllllllllIlllIIllllIllll.ageInTicks, lllllllllllllllllIlllIIllllIllll.netHeadYaw, lllllllllllllllllIlllIIllllIllll.headPitch, lllllllllllllllllIlllIIllllIllll.scaleFactor);
                    OutlineUtils.renderFive();
                }
            }
            if (!this.onTop.getValue() && this.renderEntities.getValue()) {
                lllllllllllllllllIlllIIllllIllll.modelBase.render(lllllllllllllllllIlllIIllllIllll.entity, lllllllllllllllllIlllIIllllIllll.limbSwing, lllllllllllllllllIlllIIllllIllll.limbSwingAmount, lllllllllllllllllIlllIIllllIllll.ageInTicks, lllllllllllllllllIlllIIllllIllll.netHeadYaw, lllllllllllllllllIlllIIllllIllll.headPitch, lllllllllllllllllIlllIIllllIllll.scaleFactor);
            }
            OutlineUtils.setColor(Color.WHITE);
            OutlineESP.mc.gameSettings.fancyGraphics = this.fancyGraphics;
            OutlineESP.mc.gameSettings.gammaSetting = this.gamma;
            lllllllllllllllllIlllIIllllIllll.setCancelled(true);
        }
    }
}
