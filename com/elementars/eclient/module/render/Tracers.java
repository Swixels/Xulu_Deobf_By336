package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import org.lwjgl.opengl.*;
import com.elementars.eclient.*;
import net.minecraft.util.math.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.friend.*;
import java.awt.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.client.renderer.*;
import java.util.function.*;
import net.minecraft.entity.monster.*;
import com.elementars.eclient.module.*;
import java.util.*;

public class Tracers extends Module
{
    private final /* synthetic */ Value<Float> opacity;
    private final /* synthetic */ Value<Boolean> spine;
    private final /* synthetic */ Value<Boolean> friends;
    private final /* synthetic */ Value<Boolean> mobs;
    private final /* synthetic */ Value<Float> range;
    private final /* synthetic */ Value<Boolean> players;
    private final /* synthetic */ Value<Boolean> animals;
    /* synthetic */ HueCycler cycler;
    
    @Override
    public void onDisable() {
        Tracers.EVENT_BUS.unregister((Object)this);
    }
    
    public static void drawLineFromPosToPos(final double llllllllllllllllIlIllllIlIIIlllI, final double llllllllllllllllIlIllllIlIIIlIll, final double llllllllllllllllIlIllllIlIIIlIlI, final double llllllllllllllllIlIllllIlIIIlIIl, final double llllllllllllllllIlIllllIlIIIlIII, final double llllllllllllllllIlIllllIlIIIIlll, final double llllllllllllllllIlIllllIlIIlIlIl, final float llllllllllllllllIlIllllIlIIIIlIl, final float llllllllllllllllIlIllllIlIIlIIll, final float llllllllllllllllIlIllllIlIIlIIlI, final float llllllllllllllllIlIllllIlIIIllIl) {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.5f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(llllllllllllllllIlIllllIlIIIIlIl, llllllllllllllllIlIllllIlIIlIIll, llllllllllllllllIlIllllIlIIlIIlI, llllllllllllllllIlIllllIlIIIllIl);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLoadIdentity();
        final boolean llllllllllllllllIlIllllIlIIlIIII = Tracers.mc.gameSettings.viewBobbing;
        Tracers.mc.gameSettings.viewBobbing = false;
        Tracers.mc.entityRenderer.orientCamera(Tracers.mc.getRenderPartialTicks());
        GL11.glBegin(1);
        GL11.glVertex3d(llllllllllllllllIlIllllIlIIIlllI, llllllllllllllllIlIllllIlIIIlIll, llllllllllllllllIlIllllIlIIIlIlI);
        GL11.glVertex3d(llllllllllllllllIlIllllIlIIIlIIl, llllllllllllllllIlIllllIlIIIlIII, llllllllllllllllIlIllllIlIIIIlll);
        if (Xulu.MODULE_MANAGER.getModuleT(Tracers.class).spine.getValue()) {
            GL11.glVertex3d(llllllllllllllllIlIllllIlIIIlIIl, llllllllllllllllIlIllllIlIIIlIII, llllllllllllllllIlIllllIlIIIIlll);
            GL11.glVertex3d(llllllllllllllllIlIllllIlIIIlIIl, llllllllllllllllIlIllllIlIIIlIII + llllllllllllllllIlIllllIlIIlIlIl, llllllllllllllllIlIllllIlIIIIlll);
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glColor3d(1.0, 1.0, 1.0);
        Tracers.mc.gameSettings.viewBobbing = llllllllllllllllIlIllllIlIIlIIII;
    }
    
    @Override
    public void onUpdate() {
        this.cycler.next();
    }
    
    public static void drawLine(final double llllllllllllllllIlIllllIlIlllIIl, final double llllllllllllllllIlIllllIlIlIllll, final double llllllllllllllllIlIllllIlIlIlllI, final double llllllllllllllllIlIllllIlIlIllIl, final float llllllllllllllllIlIllllIlIllIlIl, final float llllllllllllllllIlIllllIlIllIlII, final float llllllllllllllllIlIllllIlIllIIll, final float llllllllllllllllIlIllllIlIllIIlI) {
        final Vec3d llllllllllllllllIlIllllIlIllIIIl = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-(float)Math.toRadians(Minecraft.getMinecraft().player.rotationPitch)).rotateYaw(-(float)Math.toRadians(Minecraft.getMinecraft().player.rotationYaw));
        drawLineFromPosToPos(llllllllllllllllIlIllllIlIllIIIl.x, llllllllllllllllIlIllllIlIllIIIl.y + Tracers.mc.player.getEyeHeight(), llllllllllllllllIlIllllIlIllIIIl.z, llllllllllllllllIlIllllIlIlllIIl, llllllllllllllllIlIllllIlIlIllll, llllllllllllllllIlIllllIlIlIlllI, llllllllllllllllIlIllllIlIlIllIl, llllllllllllllllIlIllllIlIllIlIl, llllllllllllllllIlIllllIlIllIlII, llllllllllllllllIlIllllIlIllIIll, llllllllllllllllIlIllllIlIllIIlI);
    }
    
    public static void drawLineToEntity(final Entity llllllllllllllllIlIllllIllIIlIII, final float llllllllllllllllIlIllllIllIIIlll, final float llllllllllllllllIlIllllIllIIIllI, final float llllllllllllllllIlIllllIllIIIlIl, final float llllllllllllllllIlIllllIllIIIlII) {
        final double[] llllllllllllllllIlIllllIllIIlIIl = interpolate(llllllllllllllllIlIllllIllIIlIII);
        drawLine(llllllllllllllllIlIllllIllIIlIIl[0], llllllllllllllllIlIllllIllIIlIIl[1], llllllllllllllllIlIllllIllIIlIIl[2], llllllllllllllllIlIllllIllIIlIII.height, llllllllllllllllIlIllllIllIIIlll, llllllllllllllllIlIllllIllIIIllI, llllllllllllllllIlIllllIllIIIlIl, llllllllllllllllIlIllllIllIIIlII);
    }
    
    private int getColour(final Entity llllllllllllllllIlIllllIlllIlIII) {
        if (llllllllllllllllIlIllllIlllIlIII instanceof EntityPlayer) {
            if (Friends.isFriend(llllllllllllllllIlIllllIlllIlIII.getName())) {
                return new Color(0.27f, 0.7f, 0.92f).getRGB();
            }
            final float llllllllllllllllIlIllllIlllIlIll = Tracers.mc.player.getDistance(llllllllllllllllIlIllllIlllIlIII);
            if (llllllllllllllllIlIllllIlllIlIll <= 32.0f) {
                return new Color(1.0f - llllllllllllllllIlIllllIlllIlIll / 32.0f / 2.0f, llllllllllllllllIlIllllIlllIlIll / 32.0f, 0.0f).getRGB();
            }
            return new Color(0.0f, 0.9f, 0.0f).getRGB();
        }
        else {
            if (EntityUtil.isPassive(llllllllllllllllIlIllllIlllIlIII)) {
                return ColorUtils.Colors.GREEN;
            }
            return ColorUtils.Colors.RED;
        }
    }
    
    public static double interpolate(final double llllllllllllllllIlIllllIlllIIlII, final double llllllllllllllllIlIllllIlllIIIll) {
        return llllllllllllllllIlIllllIlllIIIll + (llllllllllllllllIlIllllIlllIIlII - llllllllllllllllIlIllllIlllIIIll) * Tracers.mc.getRenderPartialTicks();
    }
    
    @Override
    public void onWorldRender(final RenderEvent llllllllllllllllIlIlllllIIIlIIll) {
        GlStateManager.pushMatrix();
        int llllllllllllllllIlIllllIIlllIlll;
        final float llllllllllllllllIlIllllIIlllIllI;
        final float llllllllllllllllIlIllllIIlllIlIl;
        final float llllllllllllllllIlIllllIIlllIlII;
        Minecraft.getMinecraft().world.loadedEntityList.stream().filter(EntityUtil::isLiving).filter(llllllllllllllllIlIllllIIllIIIII -> !EntityUtil.isFakeLocalPlayer(llllllllllllllllIlIllllIIllIIIII)).filter(llllllllllllllllIlIllllIIllIIIlI -> (llllllllllllllllIlIllllIIllIIIlI instanceof EntityPlayer) ? (this.players.getValue() && Tracers.mc.player != llllllllllllllllIlIllllIIllIIIlI) : (EntityUtil.isPassive(llllllllllllllllIlIllllIIllIIIlI) ? this.animals.getValue() : ((boolean)this.mobs.getValue()))).filter(llllllllllllllllIlIllllIIllIlIlI -> Tracers.mc.player.getDistance(llllllllllllllllIlIllllIIllIlIlI) < this.range.getValue()).forEach(llllllllllllllllIlIllllIIlllIIlI -> {
            llllllllllllllllIlIllllIIlllIlll = this.getColour(llllllllllllllllIlIllllIIlllIIlI);
            if (llllllllllllllllIlIllllIIlllIlll == Integer.MIN_VALUE) {
                if (!this.friends.getValue()) {
                    return;
                }
                else {
                    llllllllllllllllIlIllllIIlllIlll = this.cycler.current();
                }
            }
            llllllllllllllllIlIllllIIlllIllI = (llllllllllllllllIlIllllIIlllIlll >>> 16 & 0xFF) / 255.0f;
            llllllllllllllllIlIllllIIlllIlIl = (llllllllllllllllIlIllllIIlllIlll >>> 8 & 0xFF) / 255.0f;
            llllllllllllllllIlIllllIIlllIlII = (llllllllllllllllIlIllllIIlllIlll & 0xFF) / 255.0f;
            drawLineToEntity(llllllllllllllllIlIllllIIlllIIlI, llllllllllllllllIlIllllIIlllIllI, llllllllllllllllIlIllllIIlllIlIl, llllllllllllllllIlIllllIIlllIlII, this.opacity.getValue());
            return;
        });
        GlStateManager.popMatrix();
    }
    
    private EntityType getType(final Entity llllllllllllllllIlIllllIlIIIIIII) {
        if (EntityUtil.isDrivenByPlayer(llllllllllllllllIlIllllIlIIIIIII) || EntityUtil.isFakeLocalPlayer(llllllllllllllllIlIllllIlIIIIIII)) {
            return EntityType.INVALID;
        }
        if (EntityUtil.isPlayer(llllllllllllllllIlIllllIlIIIIIII)) {
            return EntityType.PLAYER;
        }
        if (EntityUtil.isPassive(llllllllllllllllIlIllllIlIIIIIII)) {
            return EntityType.ANIMAL;
        }
        if (!EntityUtil.isPassive(llllllllllllllllIlIllllIlIIIIIII) || llllllllllllllllIlIllllIlIIIIIII instanceof EntitySpider) {
            return EntityType.HOSTILE;
        }
        return EntityType.HOSTILE;
    }
    
    @Override
    public void onEnable() {
        Tracers.EVENT_BUS.register((Object)this);
    }
    
    public Tracers() {
        super("Tracers", "Draws a line to entities in render distance", 0, Category.RENDER, true);
        this.spine = this.register(new Value<Boolean>("Spine", this, false));
        this.players = this.register(new Value<Boolean>("Players", this, true));
        this.friends = this.register(new Value<Boolean>("Friends", this, true));
        this.animals = this.register(new Value<Boolean>("Animals", this, false));
        this.mobs = this.register(new Value<Boolean>("Mobs", this, false));
        this.range = this.register(new Value<Float>("Range", this, 200.0f, 1.0f, 1000.0f));
        this.opacity = this.register(new Value<Float>("Opacity", this, 1.0f, 0.0f, 1.0f));
        this.cycler = new HueCycler(3600);
    }
    
    private void drawRainbowToEntity(final Entity llllllllllllllllIlIllllIllllIlll, final float llllllllllllllllIlIllllIllllIllI) {
        final Vec3d llllllllllllllllIlIlllllIIIIIIII = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-(float)Math.toRadians(Minecraft.getMinecraft().player.rotationPitch)).rotateYaw(-(float)Math.toRadians(Minecraft.getMinecraft().player.rotationYaw));
        final double[] llllllllllllllllIlIllllIllllllll = interpolate(llllllllllllllllIlIllllIllllIlll);
        final double llllllllllllllllIlIllllIlllllllI = llllllllllllllllIlIllllIllllllll[0];
        final double llllllllllllllllIlIllllIllllllIl = llllllllllllllllIlIllllIllllllll[1];
        final double llllllllllllllllIlIllllIllllllII = llllllllllllllllIlIllllIllllllll[2];
        final double llllllllllllllllIlIllllIlllllIll = llllllllllllllllIlIlllllIIIIIIII.x;
        final double llllllllllllllllIlIllllIlllllIlI = llllllllllllllllIlIlllllIIIIIIII.y + Tracers.mc.player.getEyeHeight();
        final double llllllllllllllllIlIllllIlllllIIl = llllllllllllllllIlIlllllIIIIIIII.z;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.5f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        this.cycler.reset();
        this.cycler.setNext(llllllllllllllllIlIllllIllllIllI);
        GlStateManager.disableLighting();
        GL11.glLoadIdentity();
        Tracers.mc.entityRenderer.orientCamera(Tracers.mc.getRenderPartialTicks());
        GL11.glBegin(1);
        GL11.glVertex3d(llllllllllllllllIlIllllIlllllllI, llllllllllllllllIlIllllIllllllIl, llllllllllllllllIlIllllIllllllII);
        GL11.glVertex3d(llllllllllllllllIlIllllIlllllIll, llllllllllllllllIlIllllIlllllIlI, llllllllllllllllIlIllllIlllllIIl);
        this.cycler.setNext(llllllllllllllllIlIllllIllllIllI);
        GL11.glVertex3d(llllllllllllllllIlIllllIlllllIll, llllllllllllllllIlIllllIlllllIlI, llllllllllllllllIlIllllIlllllIIl);
        GL11.glVertex3d(llllllllllllllllIlIllllIlllllIll, llllllllllllllllIlIllllIlllllIlI, llllllllllllllllIlIllllIlllllIIl);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glColor3d(1.0, 1.0, 1.0);
        GlStateManager.enableLighting();
    }
    
    public static double[] interpolate(final Entity llllllllllllllllIlIllllIllIlllII) {
        final double llllllllllllllllIlIllllIllIllIll = interpolate(llllllllllllllllIlIllllIllIlllII.posX, llllllllllllllllIlIllllIllIlllII.lastTickPosX) - Tracers.mc.getRenderManager().renderPosX;
        final double llllllllllllllllIlIllllIllIllIlI = interpolate(llllllllllllllllIlIllllIllIlllII.posY, llllllllllllllllIlIllllIllIlllII.lastTickPosY) - Tracers.mc.getRenderManager().renderPosY;
        final double llllllllllllllllIlIllllIllIllIIl = interpolate(llllllllllllllllIlIllllIllIlllII.posZ, llllllllllllllllIlIllllIllIlllII.lastTickPosZ) - Tracers.mc.getRenderManager().renderPosZ;
        return new double[] { llllllllllllllllIlIllllIllIllIll, llllllllllllllllIlIllllIllIllIlI, llllllllllllllllIlIllllIllIllIIl };
    }
    
    private enum EntityType
    {
        ANIMAL, 
        INVALID, 
        HOSTILE, 
        PLAYER;
    }
    
    private class EntityRelations implements Comparable<EntityRelations>
    {
        private final /* synthetic */ Entity entity;
        private final /* synthetic */ EntityType entityType;
        
        public boolean isOptionEnabled() {
            switch (this.entityType) {
                case PLAYER: {
                    return Tracers.this.players.getValue();
                }
                case HOSTILE: {
                    return Tracers.this.mobs.getValue();
                }
                default: {
                    return Tracers.this.animals.getValue();
                }
            }
        }
        
        public Entity getEntity() {
            return this.entity;
        }
        
        public EntityRelations(final Entity lIIlIlIIlllllll) {
            Objects.requireNonNull(lIIlIlIIlllllll);
            this.entity = lIIlIlIIlllllll;
            this.entityType = Tracers.this.getType(lIIlIlIIlllllll);
        }
        
        public EntityType getEntityType() {
            return this.entityType;
        }
        
        public float getDepth() {
            switch (this.entityType) {
                case PLAYER: {
                    return 15.0f;
                }
                case HOSTILE: {
                    return 10.0f;
                }
                case ANIMAL: {
                    return 5.0f;
                }
                default: {
                    return 0.0f;
                }
            }
        }
        
        public Color getColor() {
            switch (this.entityType) {
                case PLAYER: {
                    return Color.YELLOW;
                }
                case HOSTILE: {
                    return Color.RED;
                }
                default: {
                    return Color.GREEN;
                }
            }
        }
        
        @Override
        public int compareTo(final EntityRelations lIIlIlIIllIlIlI) {
            return this.getEntityType().compareTo(lIIlIlIIllIlIlI.getEntityType());
        }
    }
}
