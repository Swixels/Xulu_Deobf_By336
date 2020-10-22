package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import java.util.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.client.model.*;
import org.lwjgl.opengl.*;
import java.util.function.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.module.*;
import net.minecraft.client.renderer.culling.*;
import com.elementars.eclient.friend.*;
import net.minecraft.client.renderer.*;
import dev.xulu.newgui.util.*;
import net.minecraft.entity.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.*;
import java.awt.*;

public class Skeleton extends Module
{
    private /* synthetic */ ICamera camera;
    private final /* synthetic */ Value<Integer> red;
    private final /* synthetic */ Value<Boolean> friends;
    private final /* synthetic */ Value<Integer> blue;
    private static final /* synthetic */ HashMap<EntityPlayer, float[][]> entities;
    private final /* synthetic */ Value<String> color;
    private final /* synthetic */ Value<Integer> green;
    private final /* synthetic */ Value<Float> width;
    
    static {
        entities = new HashMap<EntityPlayer, float[][]>();
    }
    
    private boolean doesntContain(final EntityPlayer llllllllllllllllllllllIllIIIIllI) {
        return !Skeleton.mc.world.playerEntities.contains(llllllllllllllllllllllIllIIIIllI);
    }
    
    public static void addEntity(final EntityPlayer llllllllllllllllllllllIllIIIlIll, final ModelPlayer llllllllllllllllllllllIllIIIlIlI) {
        Skeleton.entities.put(llllllllllllllllllllllIllIIIlIll, new float[][] { { llllllllllllllllllllllIllIIIlIlI.bipedHead.rotateAngleX, llllllllllllllllllllllIllIIIlIlI.bipedHead.rotateAngleY, llllllllllllllllllllllIllIIIlIlI.bipedHead.rotateAngleZ }, { llllllllllllllllllllllIllIIIlIlI.bipedRightArm.rotateAngleX, llllllllllllllllllllllIllIIIlIlI.bipedRightArm.rotateAngleY, llllllllllllllllllllllIllIIIlIlI.bipedRightArm.rotateAngleZ }, { llllllllllllllllllllllIllIIIlIlI.bipedLeftLeg.rotateAngleX, llllllllllllllllllllllIllIIIlIlI.bipedLeftLeg.rotateAngleY, llllllllllllllllllllllIllIIIlIlI.bipedLeftLeg.rotateAngleZ }, { llllllllllllllllllllllIllIIIlIlI.bipedRightLeg.rotateAngleX, llllllllllllllllllllllIllIIIlIlI.bipedRightLeg.rotateAngleY, llllllllllllllllllllllIllIIIlIlI.bipedRightLeg.rotateAngleZ }, { llllllllllllllllllllllIllIIIlIlI.bipedLeftLeg.rotateAngleX, llllllllllllllllllllllIllIIIlIlI.bipedLeftLeg.rotateAngleY, llllllllllllllllllllllIllIIIlIlI.bipedLeftLeg.rotateAngleZ } });
    }
    
    @Override
    public void onWorldRender(final RenderEvent llllllllllllllllllllllIllIllllIl) {
        if (Skeleton.mc.getRenderManager() == null || Skeleton.mc.getRenderManager().options == null) {
            return;
        }
        this.startEnd(true);
        GL11.glEnable(2903);
        GL11.glDisable(2848);
        Skeleton.entities.keySet().removeIf(this::doesntContain);
        Skeleton.mc.world.playerEntities.forEach(llllllllllllllllllllllIlIlllllIl -> this.drawSkeleton(llllllllllllllllllllllIllIllllIl, llllllllllllllllllllllIlIlllllIl));
        Gui.drawRect(0, 0, 0, 0, 0);
        this.startEnd(false);
    }
    
    private void drawSkeleton(final RenderEvent llllllllllllllllllllllIllIIlllll, final EntityPlayer llllllllllllllllllllllIllIlIIlIl) {
        final double llllllllllllllllllllllIllIlIIlII = Skeleton.mc.player.lastTickPosX + (Skeleton.mc.player.posX - Skeleton.mc.player.lastTickPosX) * llllllllllllllllllllllIllIIlllll.getPartialTicks();
        final double llllllllllllllllllllllIllIlIIIll = Skeleton.mc.player.lastTickPosY + (Skeleton.mc.player.posY - Skeleton.mc.player.lastTickPosY) * llllllllllllllllllllllIllIIlllll.getPartialTicks();
        final double llllllllllllllllllllllIllIlIIIlI = Skeleton.mc.player.lastTickPosZ + (Skeleton.mc.player.posZ - Skeleton.mc.player.lastTickPosZ) * llllllllllllllllllllllIllIIlllll.getPartialTicks();
        this.camera.setPosition(llllllllllllllllllllllIllIlIIlII, llllllllllllllllllllllIllIlIIIll, llllllllllllllllllllllIllIlIIIlI);
        final float[][] llllllllllllllllllllllIllIlIIIIl = Skeleton.entities.get(llllllllllllllllllllllIllIlIIlIl);
        if (llllllllllllllllllllllIllIlIIIIl != null && llllllllllllllllllllllIllIlIIlIl.isEntityAlive() && this.camera.isBoundingBoxInFrustum(llllllllllllllllllllllIllIlIIlIl.getEntityBoundingBox()) && !llllllllllllllllllllllIllIlIIlIl.isDead && llllllllllllllllllllllIllIlIIlIl != Skeleton.mc.player && !llllllllllllllllllllllIllIlIIlIl.isPlayerSleeping()) {
            GL11.glPushMatrix();
            GL11.glEnable(2848);
            GL11.glLineWidth((float)this.width.getValue());
            this.glColor(llllllllllllllllllllllIllIlIIlIl);
            final Vec3d llllllllllllllllllllllIllIlIllIl = this.getVec3(llllllllllllllllllllllIllIIlllll, llllllllllllllllllllllIllIlIIlIl);
            final double llllllllllllllllllllllIllIlIllII = llllllllllllllllllllllIllIlIllIl.x - Skeleton.mc.getRenderManager().renderPosX;
            final double llllllllllllllllllllllIllIlIlIll = llllllllllllllllllllllIllIlIllIl.y - Skeleton.mc.getRenderManager().renderPosY;
            final double llllllllllllllllllllllIllIlIlIlI = llllllllllllllllllllllIllIlIllIl.z - Skeleton.mc.getRenderManager().renderPosZ;
            GL11.glTranslated(llllllllllllllllllllllIllIlIllII, llllllllllllllllllllllIllIlIlIll, llllllllllllllllllllllIllIlIlIlI);
            final float llllllllllllllllllllllIllIlIlIIl = llllllllllllllllllllllIllIlIIlIl.prevRenderYawOffset + (llllllllllllllllllllllIllIlIIlIl.renderYawOffset - llllllllllllllllllllllIllIlIIlIl.prevRenderYawOffset) * llllllllllllllllllllllIllIIlllll.getPartialTicks();
            GL11.glRotatef(-llllllllllllllllllllllIllIlIlIIl, 0.0f, 1.0f, 0.0f);
            GL11.glTranslated(0.0, 0.0, llllllllllllllllllllllIllIlIIlIl.isSneaking() ? -0.235 : 0.0);
            final float llllllllllllllllllllllIllIlIlIII = llllllllllllllllllllllIllIlIIlIl.isSneaking() ? 0.6f : 0.75f;
            GL11.glPushMatrix();
            this.glColor(llllllllllllllllllllllIllIlIIlIl);
            GL11.glTranslated(-0.125, (double)llllllllllllllllllllllIllIlIlIII, 0.0);
            if (llllllllllllllllllllllIllIlIIIIl[3][0] != 0.0f) {
                GL11.glRotatef(llllllllllllllllllllllIllIlIIIIl[3][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
            }
            if (llllllllllllllllllllllIllIlIIIIl[3][1] != 0.0f) {
                GL11.glRotatef(llllllllllllllllllllllIllIlIIIIl[3][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
            }
            if (llllllllllllllllllllllIllIlIIIIl[3][2] != 0.0f) {
                GL11.glRotatef(llllllllllllllllllllllIllIlIIIIl[3][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
            }
            GL11.glBegin(3);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(0.0, (double)(-llllllllllllllllllllllIllIlIlIII), 0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            this.glColor(llllllllllllllllllllllIllIlIIlIl);
            GL11.glTranslated(0.125, (double)llllllllllllllllllllllIllIlIlIII, 0.0);
            if (llllllllllllllllllllllIllIlIIIIl[4][0] != 0.0f) {
                GL11.glRotatef(llllllllllllllllllllllIllIlIIIIl[4][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
            }
            if (llllllllllllllllllllllIllIlIIIIl[4][1] != 0.0f) {
                GL11.glRotatef(llllllllllllllllllllllIllIlIIIIl[4][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
            }
            if (llllllllllllllllllllllIllIlIIIIl[4][2] != 0.0f) {
                GL11.glRotatef(llllllllllllllllllllllIllIlIIIIl[4][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
            }
            GL11.glBegin(3);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(0.0, (double)(-llllllllllllllllllllllIllIlIlIII), 0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glTranslated(0.0, 0.0, llllllllllllllllllllllIllIlIIlIl.isSneaking() ? 0.25 : 0.0);
            GL11.glPushMatrix();
            this.glColor(llllllllllllllllllllllIllIlIIlIl);
            GL11.glTranslated(0.0, llllllllllllllllllllllIllIlIIlIl.isSneaking() ? -0.05 : 0.0, llllllllllllllllllllllIllIlIIlIl.isSneaking() ? -0.01725 : 0.0);
            GL11.glPushMatrix();
            this.glColor(llllllllllllllllllllllIllIlIIlIl);
            GL11.glTranslated(-0.375, llllllllllllllllllllllIllIlIlIII + 0.55, 0.0);
            if (llllllllllllllllllllllIllIlIIIIl[1][0] != 0.0f) {
                GL11.glRotatef(llllllllllllllllllllllIllIlIIIIl[1][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
            }
            if (llllllllllllllllllllllIllIlIIIIl[1][1] != 0.0f) {
                GL11.glRotatef(llllllllllllllllllllllIllIlIIIIl[1][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
            }
            if (llllllllllllllllllllllIllIlIIIIl[1][2] != 0.0f) {
                GL11.glRotatef(-llllllllllllllllllllllIllIlIIIIl[1][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
            }
            GL11.glBegin(3);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(0.0, -0.5, 0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated(0.375, llllllllllllllllllllllIllIlIlIII + 0.55, 0.0);
            if (llllllllllllllllllllllIllIlIIIIl[2][0] != 0.0f) {
                GL11.glRotatef(llllllllllllllllllllllIllIlIIIIl[2][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
            }
            if (llllllllllllllllllllllIllIlIIIIl[2][1] != 0.0f) {
                GL11.glRotatef(llllllllllllllllllllllIllIlIIIIl[2][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
            }
            if (llllllllllllllllllllllIllIlIIIIl[2][2] != 0.0f) {
                GL11.glRotatef(-llllllllllllllllllllllIllIlIIIIl[2][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
            }
            GL11.glBegin(3);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(0.0, -0.5, 0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glRotatef(llllllllllllllllllllllIllIlIlIIl - llllllllllllllllllllllIllIlIIlIl.rotationYawHead, 0.0f, 1.0f, 0.0f);
            GL11.glPushMatrix();
            this.glColor(llllllllllllllllllllllIllIlIIlIl);
            GL11.glTranslated(0.0, llllllllllllllllllllllIllIlIlIII + 0.55, 0.0);
            if (llllllllllllllllllllllIllIlIIIIl[0][0] != 0.0f) {
                GL11.glRotatef(llllllllllllllllllllllIllIlIIIIl[0][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
            }
            GL11.glBegin(3);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(0.0, 0.3, 0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glRotatef(llllllllllllllllllllllIllIlIIlIl.isSneaking() ? 25.0f : 0.0f, 1.0f, 0.0f, 0.0f);
            GL11.glTranslated(0.0, llllllllllllllllllllllIllIlIIlIl.isSneaking() ? -0.16175 : 0.0, llllllllllllllllllllllIllIlIIlIl.isSneaking() ? -0.48025 : 0.0);
            GL11.glPushMatrix();
            GL11.glTranslated(0.0, (double)llllllllllllllllllllllIllIlIlIII, 0.0);
            GL11.glBegin(3);
            GL11.glVertex3d(-0.125, 0.0, 0.0);
            GL11.glVertex3d(0.125, 0.0, 0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            this.glColor(llllllllllllllllllllllIllIlIIlIl);
            GL11.glTranslated(0.0, (double)llllllllllllllllllllllIllIlIlIII, 0.0);
            GL11.glBegin(3);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(0.0, 0.55, 0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated(0.0, llllllllllllllllllllllIllIlIlIII + 0.55, 0.0);
            GL11.glBegin(3);
            GL11.glVertex3d(-0.375, 0.0, 0.0);
            GL11.glVertex3d(0.375, 0.0, 0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPopMatrix();
        }
    }
    
    private Vec3d getVec3(final RenderEvent llllllllllllllllllllllIlllIlIllI, final EntityPlayer llllllllllllllllllllllIlllIIllll) {
        final float llllllllllllllllllllllIlllIlIlII = llllllllllllllllllllllIlllIlIllI.getPartialTicks();
        final double llllllllllllllllllllllIlllIlIIll = llllllllllllllllllllllIlllIIllll.lastTickPosX + (llllllllllllllllllllllIlllIIllll.posX - llllllllllllllllllllllIlllIIllll.lastTickPosX) * llllllllllllllllllllllIlllIlIlII;
        final double llllllllllllllllllllllIlllIlIIlI = llllllllllllllllllllllIlllIIllll.lastTickPosY + (llllllllllllllllllllllIlllIIllll.posY - llllllllllllllllllllllIlllIIllll.lastTickPosY) * llllllllllllllllllllllIlllIlIlII;
        final double llllllllllllllllllllllIlllIlIIIl = llllllllllllllllllllllIlllIIllll.lastTickPosZ + (llllllllllllllllllllllIlllIIllll.posZ - llllllllllllllllllllllIlllIIllll.lastTickPosZ) * llllllllllllllllllllllIlllIlIlII;
        return new Vec3d(llllllllllllllllllllllIlllIlIIll, llllllllllllllllllllllIlllIlIIlI, llllllllllllllllllllllIlllIlIIIl);
    }
    
    public Skeleton() {
        super("Skeleton", "Renders player entities skeletons", 0, Category.RENDER, true);
        this.color = this.register(new Value<String>("Color Mode", this, "White", new String[] { "White", "ClickGui", "Tracers", "Target", "Rainbow" }));
        this.friends = this.register(new Value<Boolean>("Friends", this, true));
        this.width = this.register(new Value<Float>("Width", this, 1.5f, 0.0f, 10.0f));
        this.red = this.register(new Value<Integer>("Target Red", this, 255, 0, 255));
        this.green = this.register(new Value<Integer>("Target Green", this, 0, 0, 255));
        this.blue = this.register(new Value<Integer>("TargetBlue", this, 0, 0, 255));
        this.camera = (ICamera)new Frustum();
    }
    
    private void glColor(final EntityPlayer llllllllllllllllllllllIlllIIIlII) {
        if (Friends.isFriend(llllllllllllllllllllllIlllIIIlII.getName()) && this.friends.getValue()) {
            GlStateManager.color(0.27f, 0.7f, 0.92f, 1.0f);
            return;
        }
        if (this.color.getValue().equalsIgnoreCase("ClickGui")) {
            GlStateManager.color(ColorUtil.getClickGUIColor().getRed() / 255.0f, ColorUtil.getClickGUIColor().getGreen() / 255.0f, ColorUtil.getClickGUIColor().getBlue() / 255.0f, 1.0f);
        }
        else if (this.color.getValue().equalsIgnoreCase("Tracers")) {
            final float llllllllllllllllllllllIlllIIIlll = Skeleton.mc.player.getDistance((Entity)llllllllllllllllllllllIlllIIIlII);
            if (llllllllllllllllllllllIlllIIIlll <= 32.0f) {
                GlStateManager.color(1.0f - llllllllllllllllllllllIlllIIIlll / 32.0f / 2.0f, llllllllllllllllllllllIlllIIIlll / 32.0f, 0.0f, 1.0f);
            }
            else {
                GlStateManager.color(0.0f, 0.9f, 0.0f, 1.0f);
            }
        }
        else if (this.color.getValue().equalsIgnoreCase("Target")) {
            if (TargetPlayers.targettedplayers.containsKey(llllllllllllllllllllllIlllIIIlII.getName())) {
                GlStateManager.color(this.red.getValue() / 255.0f, this.green.getValue() / 255.0f, this.blue.getValue() / 255.0f, 1.0f);
            }
            else {
                GlStateManager.color(ColorUtil.getClickGUIColor().getRed() / 255.0f, ColorUtil.getClickGUIColor().getGreen() / 255.0f, ColorUtil.getClickGUIColor().getBlue() / 255.0f, 1.0f);
            }
        }
        else if (this.color.getValue().equalsIgnoreCase("Rainbow")) {
            final Color llllllllllllllllllllllIlllIIIllI = new Color(Xulu.rgb);
            GlStateManager.color(llllllllllllllllllllllIlllIIIllI.getRed() / 255.0f, llllllllllllllllllllllIlllIIIllI.getGreen() / 255.0f, llllllllllllllllllllllIlllIIIllI.getBlue() / 255.0f, llllllllllllllllllllllIlllIIIllI.getAlpha() / 255.0f);
        }
        else {
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
    
    private void startEnd(final boolean llllllllllllllllllllllIllIIlIIII) {
        if (llllllllllllllllllllllIllIIlIIII) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GL11.glEnable(2848);
            GlStateManager.disableDepth();
            GlStateManager.disableTexture2D();
            GL11.glHint(3154, 4354);
        }
        else {
            GlStateManager.disableBlend();
            GlStateManager.enableTexture2D();
            GL11.glDisable(2848);
            GlStateManager.enableDepth();
            GlStateManager.popMatrix();
        }
        GlStateManager.depthMask(!llllllllllllllllllllllIllIIlIIII);
    }
}
