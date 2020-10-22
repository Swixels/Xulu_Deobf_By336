package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import org.lwjgl.opengl.*;
import com.google.common.base.*;
import net.minecraft.entity.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.event.events.*;
import com.elementars.eclient.*;
import java.awt.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.math.*;
import org.lwjgl.util.glu.*;
import net.minecraft.item.*;
import java.util.*;

public class Trajectories extends Module
{
    private /* synthetic */ Value<Integer> blueC;
    private /* synthetic */ Value<Float> scale;
    private /* synthetic */ Value<Integer> green;
    private /* synthetic */ Value<Integer> red;
    private /* synthetic */ Value<Integer> greenC;
    private /* synthetic */ Value<Integer> blue;
    private /* synthetic */ Value<Boolean> rainbow;
    private /* synthetic */ Value<Integer> redC;
    
    public void drawLine3D(final double lIIIIIIllIlIl, final double lIIIIIIllIIIl, final double lIIIIIIllIIII) {
        GL11.glVertex3d(lIIIIIIllIlIl, lIIIIIIllIIIl, lIIIIIIllIIII);
    }
    
    public void disableGL3D() {
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glDepthMask(true);
        GL11.glCullFace(1029);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    
    private List getEntitiesWithinAABB(final AxisAlignedBB lIIIIIIlIIlII) {
        final ArrayList lIIIIIIlIIIll = new ArrayList();
        final int lIIIIIIlIIIlI = MathHelper.floor((lIIIIIIlIIlII.minX - 2.0) / 16.0);
        final int lIIIIIIlIIIIl = MathHelper.floor((lIIIIIIlIIlII.maxX + 2.0) / 16.0);
        final int lIIIIIIlIIIII = MathHelper.floor((lIIIIIIlIIlII.minZ - 2.0) / 16.0);
        final int lIIIIIIIlllll = MathHelper.floor((lIIIIIIlIIlII.maxZ + 2.0) / 16.0);
        for (int lIIIIIIlIIllI = lIIIIIIlIIIlI; lIIIIIIlIIllI <= lIIIIIIlIIIIl; ++lIIIIIIlIIllI) {
            for (int lIIIIIIlIIlll = lIIIIIIlIIIII; lIIIIIIlIIlll <= lIIIIIIIlllll; ++lIIIIIIlIIlll) {
                if (Trajectories.mc.world.getChunkProvider().getLoadedChunk(lIIIIIIlIIllI, lIIIIIIlIIlll) != null) {
                    Trajectories.mc.world.getChunk(lIIIIIIlIIllI, lIIIIIIlIIlll).getEntitiesWithinAABBForEntity((Entity)Trajectories.mc.player, lIIIIIIlIIlII, (List)lIIIIIIlIIIll, (Predicate)null);
                }
            }
        }
        return lIIIIIIlIIIll;
    }
    
    public Trajectories() {
        super("Trajectories", "Ingrosware trajectories", 0, Category.RENDER, true);
        this.scale = this.register(new Value<Float>("Scale", this, 1.0f, 1.0f, 2.0f));
        this.rainbow = this.register(new Value<Boolean>("Rainbow", this, false));
        this.red = this.register(new Value<Integer>("Red", this, 0, 0, 255));
        this.green = this.register(new Value<Integer>("Green", this, 255, 0, 255));
        this.blue = this.register(new Value<Integer>("Blue", this, 0, 0, 255));
        this.redC = this.register(new Value<Integer>("Charge Red", this, 204, 0, 255));
        this.greenC = this.register(new Value<Integer>("Charge Green", this, 127, 0, 255));
        this.blueC = this.register(new Value<Integer>("Charge Blue", this, 0, 0, 255));
    }
    
    @Override
    public void onWorldRender(final RenderEvent lIIIIIlIlllIl) {
        final Color lIIIIIlIlllll = new Color(Xulu.rgb);
        if (Trajectories.mc.world != null && Trajectories.mc.player != null) {
            final double lIIIIIllIIlII = Trajectories.mc.player.lastTickPosX + (Trajectories.mc.player.posX - Trajectories.mc.player.lastTickPosX) * lIIIIIlIlllIl.getPartialTicks();
            final double lIIIIIllIIIll = Trajectories.mc.player.lastTickPosY + (Trajectories.mc.player.posY - Trajectories.mc.player.lastTickPosY) * lIIIIIlIlllIl.getPartialTicks();
            final double lIIIIIllIIIlI = Trajectories.mc.player.lastTickPosZ + (Trajectories.mc.player.posZ - Trajectories.mc.player.lastTickPosZ) * lIIIIIlIlllIl.getPartialTicks();
            Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND);
            if (Trajectories.mc.gameSettings.thirdPersonView == 0 && (Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemBow || Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemFishingRod || Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemEnderPearl || Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemEgg || Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSnowball || Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemExpBottle)) {
                GL11.glPushMatrix();
                final Item lIIIIIlllIIll = Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem();
                double lIIIIIlllIIlI = lIIIIIllIIlII - MathHelper.cos(Trajectories.mc.player.rotationYaw / 180.0f * 3.1415927f) * 0.16f;
                double lIIIIIlllIIIl = lIIIIIllIIIll + Trajectories.mc.player.getEyeHeight() - 0.1000000014901161;
                double lIIIIIlllIIII = lIIIIIllIIIlI - MathHelper.sin(Trajectories.mc.player.rotationYaw / 180.0f * 3.1415927f) * 0.16f;
                double lIIIIIllIllll = -MathHelper.sin(Trajectories.mc.player.rotationYaw / 180.0f * 3.1415927f) * MathHelper.cos(Trajectories.mc.player.rotationPitch / 180.0f * 3.1415927f) * ((lIIIIIlllIIll instanceof ItemBow) ? 1.0 : 0.4);
                double lIIIIIllIlllI = -MathHelper.sin(Trajectories.mc.player.rotationPitch / 180.0f * 3.1415927f) * ((lIIIIIlllIIll instanceof ItemBow) ? 1.0 : 0.4);
                double lIIIIIllIllIl = MathHelper.cos(Trajectories.mc.player.rotationYaw / 180.0f * 3.1415927f) * MathHelper.cos(Trajectories.mc.player.rotationPitch / 180.0f * 3.1415927f) * ((lIIIIIlllIIll instanceof ItemBow) ? 1.0 : 0.4);
                final int lIIIIIllIllII = 72000 - Trajectories.mc.player.getItemInUseCount();
                float lIIIIIllIlIll = lIIIIIllIllII / 20.0f;
                lIIIIIllIlIll = (lIIIIIllIlIll * lIIIIIllIlIll + lIIIIIllIlIll * 2.0f) / 3.0f;
                if (lIIIIIllIlIll > 1.0f) {
                    lIIIIIllIlIll = 1.0f;
                }
                final float lIIIIIllIlIlI = MathHelper.sqrt(lIIIIIllIllll * lIIIIIllIllll + lIIIIIllIlllI * lIIIIIllIlllI + lIIIIIllIllIl * lIIIIIllIllIl);
                lIIIIIllIllll /= lIIIIIllIlIlI;
                lIIIIIllIlllI /= lIIIIIllIlIlI;
                lIIIIIllIllIl /= lIIIIIllIlIlI;
                final float lIIIIIllIlIIl = (lIIIIIlllIIll instanceof ItemBow) ? (lIIIIIllIlIll * 2.0f) : ((lIIIIIlllIIll instanceof ItemFishingRod) ? 1.25f : ((Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.EXPERIENCE_BOTTLE) ? 0.9f : 1.0f));
                lIIIIIllIllll *= lIIIIIllIlIIl * ((lIIIIIlllIIll instanceof ItemFishingRod) ? 0.75f : ((Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.EXPERIENCE_BOTTLE) ? 0.75f : 1.5f));
                lIIIIIllIlllI *= lIIIIIllIlIIl * ((lIIIIIlllIIll instanceof ItemFishingRod) ? 0.75f : ((Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.EXPERIENCE_BOTTLE) ? 0.75f : 1.5f));
                lIIIIIllIllIl *= lIIIIIllIlIIl * ((lIIIIIlllIIll instanceof ItemFishingRod) ? 0.75f : ((Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.EXPERIENCE_BOTTLE) ? 0.75f : 1.5f));
                this.enableGL3D(2.0f);
                if (this.rainbow.getValue()) {
                    GlStateManager.color(lIIIIIlIlllll.getRed() / 255.0f, lIIIIIlIlllll.getGreen() / 255.0f, lIIIIIlIlllll.getBlue() / 255.0f, 1.0f);
                }
                else if (lIIIIIllIlIll > 0.6f) {
                    GlStateManager.color(this.red.getValue() / 255.0f, this.green.getValue() / 255.0f, this.blue.getValue() / 255.0f, 1.0f);
                }
                else {
                    GlStateManager.color(this.redC.getValue() / 255.0f, this.greenC.getValue() / 255.0f, this.blueC.getValue() / 255.0f, 1.0f);
                }
                GL11.glEnable(2848);
                final float lIIIIIllIlIII = (float)((lIIIIIlllIIll instanceof ItemBow) ? 0.3 : 0.25);
                boolean lIIIIIllIIlll = false;
                Entity lIIIIIllIIllI = null;
                RayTraceResult lIIIIIllIIlIl = null;
                while (!lIIIIIllIIlll && lIIIIIlllIIIl > 0.0) {
                    final Vec3d lIIIIIlllllII = new Vec3d(lIIIIIlllIIlI, lIIIIIlllIIIl, lIIIIIlllIIII);
                    final Vec3d lIIIIIllllIll = new Vec3d(lIIIIIlllIIlI + lIIIIIllIllll, lIIIIIlllIIIl + lIIIIIllIlllI, lIIIIIlllIIII + lIIIIIllIllIl);
                    final RayTraceResult lIIIIIllllIlI = Trajectories.mc.world.rayTraceBlocks(lIIIIIlllllII, lIIIIIllllIll, false, true, false);
                    if (lIIIIIllllIlI != null && lIIIIIllllIlI.typeOfHit != RayTraceResult.Type.MISS) {
                        lIIIIIllIIlIl = lIIIIIllllIlI;
                        lIIIIIllIIlll = true;
                    }
                    final AxisAlignedBB lIIIIIllllIIl = new AxisAlignedBB(lIIIIIlllIIlI - lIIIIIllIlIII, lIIIIIlllIIIl - lIIIIIllIlIII, lIIIIIlllIIII - lIIIIIllIlIII, lIIIIIlllIIlI + lIIIIIllIlIII, lIIIIIlllIIIl + lIIIIIllIlIII, lIIIIIlllIIII + lIIIIIllIlIII);
                    final List lIIIIIllllIII = this.getEntitiesWithinAABB(lIIIIIllllIIl.offset(lIIIIIllIllll, lIIIIIllIlllI, lIIIIIllIllIl).expand(1.0, 1.0, 1.0));
                    for (final Object lIIIIIllllllI : lIIIIIllllIII) {
                        final Entity lIIIIIlllllIl = (Entity)lIIIIIllllllI;
                        if (lIIIIIlllllIl.canBeCollidedWith() && lIIIIIlllllIl != Trajectories.mc.player) {
                            final float lIIIIlIIIIIIl = 0.3f;
                            final AxisAlignedBB lIIIIlIIIIIII = lIIIIIlllllIl.getEntityBoundingBox().expand((double)lIIIIlIIIIIIl, (double)lIIIIlIIIIIIl, (double)lIIIIlIIIIIIl);
                            final RayTraceResult lIIIIIlllllll = lIIIIlIIIIIII.calculateIntercept(lIIIIIlllllII, lIIIIIllllIll);
                            if (lIIIIIlllllll == null) {
                                continue;
                            }
                            lIIIIIllIIlll = true;
                            lIIIIIllIIllI = lIIIIIlllllIl;
                            lIIIIIllIIlIl = lIIIIIlllllll;
                        }
                    }
                    if (lIIIIIllIIllI != null) {
                        GlStateManager.color(1.0f, 0.0f, 0.0f, 1.0f);
                    }
                    lIIIIIlllIIlI += lIIIIIllIllll;
                    lIIIIIlllIIIl += lIIIIIllIlllI;
                    lIIIIIlllIIII += lIIIIIllIllIl;
                    final float lIIIIIlllIllI = 0.99f;
                    lIIIIIllIllll *= lIIIIIlllIllI;
                    lIIIIIllIlllI *= lIIIIIlllIllI;
                    lIIIIIllIllIl *= lIIIIIlllIllI;
                    lIIIIIllIlllI -= ((lIIIIIlllIIll instanceof ItemBow) ? 0.05 : 0.03);
                    this.drawLine3D(lIIIIIlllIIlI - lIIIIIllIIlII, lIIIIIlllIIIl - lIIIIIllIIIll, lIIIIIlllIIII - lIIIIIllIIIlI);
                }
                if (lIIIIIllIIlIl != null && lIIIIIllIIlIl.typeOfHit == RayTraceResult.Type.BLOCK) {
                    GlStateManager.translate(lIIIIIlllIIlI - lIIIIIllIIlII, lIIIIIlllIIIl - lIIIIIllIIIll, lIIIIIlllIIII - lIIIIIllIIIlI);
                    final int lIIIIIlllIlIl = lIIIIIllIIlIl.sideHit.getIndex();
                    if (lIIIIIlllIlIl == 2) {
                        GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
                    }
                    else if (lIIIIIlllIlIl == 3) {
                        GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
                    }
                    else if (lIIIIIlllIlIl == 4) {
                        GlStateManager.rotate(90.0f, 0.0f, 0.0f, 1.0f);
                    }
                    else if (lIIIIIlllIlIl == 5) {
                        GlStateManager.rotate(90.0f, 0.0f, 0.0f, 1.0f);
                    }
                    final Cylinder lIIIIIlllIlII = new Cylinder();
                    GlStateManager.rotate(-90.0f, 1.0f, 0.0f, 0.0f);
                    GlStateManager.scale((float)this.scale.getValue(), (float)this.scale.getValue(), (float)this.scale.getValue());
                    lIIIIIlllIlII.setDrawStyle(100011);
                    if (lIIIIIllIIllI != null) {
                        GlStateManager.color(0.0f, 0.0f, 0.0f, 1.0f);
                        GL11.glLineWidth(2.5f);
                        lIIIIIlllIlII.draw(0.6f, 0.3f, 0.0f, 4, 1);
                        GL11.glLineWidth(0.1f);
                        if (this.rainbow.getValue()) {
                            GlStateManager.color(lIIIIIlIlllll.getRed() / 255.0f, lIIIIIlIlllll.getGreen() / 255.0f, lIIIIIlIlllll.getBlue() / 255.0f, 1.0f);
                        }
                        else {
                            GlStateManager.color(this.red.getValue() / 255.0f, this.green.getValue() / 255.0f, this.blue.getValue() / 255.0f, 1.0f);
                        }
                    }
                    lIIIIIlllIlII.draw(0.6f, 0.3f, 0.0f, 4, 1);
                }
                this.disableGL3D();
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glPopMatrix();
            }
        }
    }
    
    public void enableGL3D(final float lIIIIIIlllIll) {
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glEnable(2884);
        Trajectories.mc.entityRenderer.disableLightmap();
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glLineWidth(lIIIIIIlllIll);
    }
}
