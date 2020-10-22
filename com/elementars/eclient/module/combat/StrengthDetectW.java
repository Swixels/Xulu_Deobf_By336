package com.elementars.eclient.module.combat;

import net.minecraft.entity.player.*;
import com.elementars.eclient.command.*;
import net.minecraft.init.*;
import com.elementars.eclient.event.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.elementars.eclient.*;
import com.elementars.eclient.util.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.potion.*;
import net.minecraft.network.play.server.*;
import com.elementars.eclient.module.*;
import java.util.*;
import net.minecraft.entity.*;

public class StrengthDetectW extends Module
{
    public /* synthetic */ Set<EntityPlayer> strengthedPlayers;
    public /* synthetic */ Set<EntityPlayer> renderPlayers;
    
    @EventTarget
    public void onItem(final EventDrinkPotion llllllllllllllllIllllllIIlIIIIII) {
        Command.sendChatMessage("drink event");
        if (llllllllllllllllIllllllIIlIIIIII.getEntityLivingBase() instanceof EntityPlayer) {
            final EntityPlayer llllllllllllllllIllllllIIlIIIlII = (EntityPlayer)llllllllllllllllIllllllIIlIIIIII.getEntityLivingBase();
            if (llllllllllllllllIllllllIIlIIIIII.getStack().getItem() == Items.POTIONITEM) {
                for (final PotionEffect llllllllllllllllIllllllIIlIIIlIl : PotionUtils.getEffectsFromStack(llllllllllllllllIllllllIIlIIIIII.getStack())) {
                    if (llllllllllllllllIllllllIIlIIIlIl.getPotion().equals(MobEffects.STRENGTH)) {
                        this.strengthedPlayers.add(llllllllllllllllIllllllIIlIIIlII);
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onUseItem(final LivingEntityUseItemEvent.Finish llllllllllllllllIllllllIIlIlIIII) {
        Command.sendChatMessage("finish use item");
        if (llllllllllllllllIllllllIIlIlIIII.getEntityLiving() instanceof EntityPlayer) {
            final EntityPlayer llllllllllllllllIllllllIIlIlIIlI = (EntityPlayer)llllllllllllllllIllllllIIlIlIIII.getEntityLiving();
            if (llllllllllllllllIllllllIIlIlIIII.getItem().getItem() == Items.POTIONITEM) {
                for (final PotionEffect llllllllllllllllIllllllIIlIlIIll : PotionUtils.getEffectsFromStack(llllllllllllllllIllllllIIlIlIIII.getItem())) {
                    if (llllllllllllllllIllllllIIlIlIIll.getPotion().equals(MobEffects.STRENGTH)) {
                        this.strengthedPlayers.add(llllllllllllllllIllllllIIlIlIIlI);
                    }
                }
            }
        }
    }
    
    @Override
    public void onDisable() {
        Xulu.EVENT_MANAGER.unregister(this);
        StrengthDetectW.EVENT_BUS.unregister((Object)this);
    }
    
    @Override
    public void onUpdate() {
        if (StrengthDetect.mc.player == null) {
            return;
        }
        for (final EntityPlayer llllllllllllllllIllllllIIlIlllIl : StrengthDetect.mc.world.playerEntities) {
            if (EntityUtil.isLiving((Entity)llllllllllllllllIllllllIIlIlllIl)) {
                if (((EntityLivingBase)llllllllllllllllIllllllIIlIlllIl).getHealth() <= 0.0f) {
                    continue;
                }
                if (llllllllllllllllIllllllIIlIlllIl.isPotionActive(MobEffects.STRENGTH) && !this.strengthedPlayers.contains(llllllllllllllllIllllllIIlIlllIl)) {
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append("\u00c2§4[").append(llllllllllllllllIllllllIIlIlllIl.getDisplayNameString()).append("]\u00c2§r is now strong")));
                    this.strengthedPlayers.add(llllllllllllllllIllllllIIlIlllIl);
                }
                if (this.strengthedPlayers.contains(llllllllllllllllIllllllIIlIlllIl) && !llllllllllllllllIllllllIIlIlllIl.isPotionActive(MobEffects.STRENGTH)) {
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append("\u00c2§3[").append(llllllllllllllllIllllllIIlIlllIl.getDisplayNameString()).append("]\u00c2§r is no longer strong")));
                    this.strengthedPlayers.remove(llllllllllllllllIllllllIIlIlllIl);
                }
                this.checkRender();
            }
        }
    }
    
    @Override
    public void onWorldRender(final RenderEvent llllllllllllllllIllllllIIIIlIllI) {
        if (StrengthDetectW.mc.getRenderManager().options == null) {
            return;
        }
        final boolean llllllllllllllllIllllllIIIIlIlIl = StrengthDetectW.mc.getRenderManager().options.thirdPersonView == 2;
        final float llllllllllllllllIllllllIIIIlIlII = StrengthDetectW.mc.getRenderManager().playerViewY;
        for (final EntityPlayer llllllllllllllllIllllllIIIIllIII : this.strengthedPlayers) {
            if (llllllllllllllllIllllllIIIIllIII.getName() == StrengthDetect.mc.player.getName()) {
                return;
            }
            GlStateManager.pushMatrix();
            final Vec3d llllllllllllllllIllllllIIIIllIIl = EntityUtil.getInterpolatedPos((Entity)llllllllllllllllIllllllIIIIllIII, llllllllllllllllIllllllIIIIlIllI.getPartialTicks());
            GlStateManager.translate(llllllllllllllllIllllllIIIIllIIl.x - StrengthDetect.mc.getRenderManager().renderPosX, llllllllllllllllIllllllIIIIllIIl.y - StrengthDetect.mc.getRenderManager().renderPosY, llllllllllllllllIllllllIIIIllIIl.z - StrengthDetect.mc.getRenderManager().renderPosZ);
            GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(-llllllllllllllllIllllllIIIIlIlII, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate((float)(llllllllllllllllIllllllIIIIlIlIl ? -1 : 1), 1.0f, 0.0f, 0.0f);
            GlStateManager.disableLighting();
            GlStateManager.depthMask(false);
            GlStateManager.disableDepth();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GL11.glColor3f(1.0f, 0.2f, 0.2f);
            GlStateManager.disableTexture2D();
            GL11.glLineWidth(4.0f);
            GL11.glEnable(2848);
            GL11.glBegin(2);
            GL11.glVertex2d((double)(-llllllllllllllllIllllllIIIIllIII.width / 2.0f), 0.0);
            GL11.glVertex2d((double)(-llllllllllllllllIllllllIIIIllIII.width / 2.0f), (double)llllllllllllllllIllllllIIIIllIII.height);
            GL11.glVertex2d((double)(llllllllllllllllIllllllIIIIllIII.width / 2.0f), (double)llllllllllllllllIllllllIIIIllIII.height);
            GL11.glVertex2d((double)(llllllllllllllllIllllllIIIIllIII.width / 2.0f), 0.0);
            GL11.glEnd();
            GlStateManager.popMatrix();
        }
        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GlStateManager.disableDepth();
        GlStateManager.enableCull();
        GlStateManager.glLineWidth(1.0f);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
    }
    
    public void checkRender() {
        try {
            this.renderPlayers.clear();
            for (final EntityPlayer llllllllllllllllIllllllIIIlIIllI : StrengthDetect.mc.world.playerEntities) {
                if (EntityUtil.isLiving((Entity)llllllllllllllllIllllllIIIlIIllI)) {
                    if (((EntityLivingBase)llllllllllllllllIllllllIIIlIIllI).getHealth() <= 0.0f) {
                        continue;
                    }
                    this.renderPlayers.add(llllllllllllllllIllllllIIIlIIllI);
                }
            }
            for (final EntityPlayer llllllllllllllllIllllllIIIlIIlIl : this.strengthedPlayers) {
                if (!this.renderPlayers.contains(llllllllllllllllIllllllIIIlIIlIl)) {
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append("\u00c2§3[").append(llllllllllllllllIllllllIIIlIIlIl.getDisplayNameString()).append("]\u00c2§r is (probably) no longer strong")));
                    this.strengthedPlayers.remove(llllllllllllllllIllllllIIIlIIlIl);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    @EventTarget
    public void onPacket(final EventReceivePacket llllllllllllllllIllllllIIIllIIII) {
        if (llllllllllllllllIllllllIIIllIIII.getPacket() instanceof SPacketEntityEffect) {
            Command.sendChatMessage("OO");
            final SPacketEntityEffect llllllllllllllllIllllllIIIllIlIl = (SPacketEntityEffect)llllllllllllllllIllllllIIIllIIII.getPacket();
            if (Potion.getPotionById((int)llllllllllllllllIllllllIIIllIlIl.getEffectId()) == MobEffects.STRENGTH) {
                Command.sendChatMessage("is this strength");
                final EntityPlayer llllllllllllllllIllllllIIIllIllI = (StrengthDetectW.mc.world.getEntityByID(llllllllllllllllIllllllIIIllIlIl.getEntityId()) instanceof EntityPlayer) ? StrengthDetectW.mc.world.getEntityByID(llllllllllllllllIllllllIIIllIlIl.getEntityId()) : null;
                if (llllllllllllllllIllllllIIIllIllI != null) {
                    Command.sendChatMessage("we got a player right?");
                    this.strengthedPlayers.add(llllllllllllllllIllllllIIIllIllI);
                }
            }
        }
        if (llllllllllllllllIllllllIIIllIIII.getPacket() instanceof SPacketEntityStatus) {
            Command.sendChatMessage("status");
            final SPacketEntityStatus llllllllllllllllIllllllIIIllIIlI = (SPacketEntityStatus)llllllllllllllllIllllllIIIllIIII.getPacket();
            if (llllllllllllllllIllllllIIIllIIlI.getOpCode() == 9 && llllllllllllllllIllllllIIIllIIlI.getEntity(StrengthDetectW.world) instanceof EntityPlayer) {
                Command.sendChatMessage("use item status");
                final EntityPlayer llllllllllllllllIllllllIIIllIIll = (EntityPlayer)llllllllllllllllIllllllIIIllIIlI.getEntity(StrengthDetectW.world);
                if (llllllllllllllllIllllllIIIllIIll.getHeldItemMainhand().getItem() == Items.POTIONITEM) {
                    Command.sendChatMessage("holding a potion?");
                    for (final PotionEffect llllllllllllllllIllllllIIIllIlII : PotionUtils.getEffectsFromStack(llllllllllllllllIllllllIIIllIIll.getHeldItemMainhand())) {
                        if (llllllllllllllllIllllllIIIllIlII.getPotion().equals(MobEffects.STRENGTH)) {
                            Command.sendChatMessage("we got strength");
                            this.strengthedPlayers.add(llllllllllllllllIllllllIIIllIIll);
                        }
                    }
                }
            }
        }
    }
    
    public StrengthDetectW() {
        super("StrengthDetectW", "test", 0, Category.COMBAT, true);
    }
    
    @Override
    public void onEnable() {
        this.strengthedPlayers = new HashSet<EntityPlayer>();
        this.renderPlayers = new HashSet<EntityPlayer>();
        Xulu.EVENT_MANAGER.register(this);
        StrengthDetectW.EVENT_BUS.register((Object)this);
    }
}
