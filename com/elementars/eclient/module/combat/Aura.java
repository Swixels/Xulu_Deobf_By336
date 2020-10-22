package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import com.elementars.eclient.friend.*;
import com.elementars.eclient.util.*;
import net.minecraft.item.*;
import com.elementars.eclient.module.*;
import net.minecraft.nbt.*;

public class Aura extends Module
{
    /* synthetic */ boolean isSpoofingAngles;
    final /* synthetic */ Value<Boolean> sharpness;
    /* synthetic */ double pitch;
    final /* synthetic */ Value<Boolean> wait;
    final /* synthetic */ Value<Boolean> walls;
    final /* synthetic */ Value<Boolean> animals;
    /* synthetic */ double yaw;
    final /* synthetic */ Value<Double> range;
    final /* synthetic */ Value<Boolean> rotate;
    final /* synthetic */ Value<Boolean> players;
    final /* synthetic */ Value<Boolean> mobs;
    
    @EventTarget
    public void onSend(final EventSendPacket lllllllllllllllllIllIlIIIIlIIllI) {
        final Packet lllllllllllllllllIllIlIIIIlIlIII = lllllllllllllllllIllIlIIIIlIIllI.getPacket();
        if (lllllllllllllllllIllIlIIIIlIlIII instanceof CPacketPlayer && this.rotate.getValue() && this.isSpoofingAngles) {
            ((CPacketPlayer)lllllllllllllllllIllIlIIIIlIlIII).yaw = (float)this.yaw;
            ((CPacketPlayer)lllllllllllllllllIllIlIIIIlIlIII).pitch = (float)this.pitch;
        }
    }
    
    private boolean shouldPause() {
        return (Xulu.MODULE_MANAGER.getModule(Surround.class).isToggled() && Surround.isExposed() && Xulu.MODULE_MANAGER.getModuleT(Surround.class).findObiInHotbar() != -1) || Xulu.MODULE_MANAGER.getModule(AutoTrap.class).isToggled() || Xulu.MODULE_MANAGER.getModule(HoleFill.class).isToggled() || (Xulu.MODULE_MANAGER.getModule(HoleBlocker.class).isToggled() && HoleBlocker.isExposed() && Xulu.MODULE_MANAGER.getModuleT(Surround.class).findObiInHotbar() != -1);
    }
    
    private void lookAtPacket(final double lllllllllllllllllIllIlIIIlIIIlll, final double lllllllllllllllllIllIlIIIlIIIIII, final double lllllllllllllllllIllIlIIIlIIIlIl, final EntityPlayer lllllllllllllllllIllIlIIIlIIIlII) {
        final double[] lllllllllllllllllIllIlIIIlIIIIll = AutoCrystal.calculateLookAt(lllllllllllllllllIllIlIIIlIIIlll, lllllllllllllllllIllIlIIIlIIIIII, lllllllllllllllllIllIlIIIlIIIlIl, lllllllllllllllllIllIlIIIlIIIlII);
        this.setYawAndPitch((float)lllllllllllllllllIllIlIIIlIIIIll[0], (float)lllllllllllllllllIllIlIIIlIIIIll[1]);
    }
    
    @Override
    public void onDisable() {
        this.resetRotation();
    }
    
    private boolean canEntityFeetBeSeen(final Entity lllllllllllllllllIllIlIIIlIIllll) {
        return Aura.mc.world.rayTraceBlocks(new Vec3d(Aura.mc.player.posX, Aura.mc.player.posX + Aura.mc.player.getEyeHeight(), Aura.mc.player.posZ), new Vec3d(lllllllllllllllllIllIlIIIlIIllll.posX, lllllllllllllllllIllIlIIIlIIllll.posY, lllllllllllllllllIllIlIIIlIIllll.posZ), false, true, false) == null;
    }
    
    private void setYawAndPitch(final float lllllllllllllllllIllIlIIIIlllIII, final float lllllllllllllllllIllIlIIIIllIlll) {
        this.yaw = lllllllllllllllllIllIlIIIIlllIII;
        this.pitch = lllllllllllllllllIllIlIIIIllIlll;
        this.isSpoofingAngles = true;
    }
    
    @Override
    public void onUpdate() {
        if (Aura.mc.player.isDead) {
            return;
        }
        if (this.shouldPause()) {
            this.resetRotation();
            return;
        }
        final boolean lllllllllllllllllIllIlIIIllllIll = Aura.mc.player.getHeldItemOffhand().getItem().equals(Items.SHIELD) && Aura.mc.player.getActiveHand() == EnumHand.OFF_HAND;
        final boolean lllllllllllllllllIllIlIIIllllIlI = Aura.mc.player.getHeldItemOffhand().getItem().equals(Items.GOLDEN_APPLE) && Aura.mc.player.getActiveHand() == EnumHand.OFF_HAND;
        if (Aura.mc.player.isHandActive() && !lllllllllllllllllIllIlIIIllllIll && !lllllllllllllllllIllIlIIIllllIlI) {
            return;
        }
        if (this.wait.getValue()) {
            if (Aura.mc.player.getCooledAttackStrength(this.getLagComp()) < 1.0f) {
                return;
            }
            if (Aura.mc.player.ticksExisted % 2 != 0) {
                return;
            }
        }
        for (final Entity lllllllllllllllllIllIlIIIlllllIl : Minecraft.getMinecraft().world.loadedEntityList) {
            if (!EntityUtil.isLiving(lllllllllllllllllIllIlIIIlllllIl)) {
                continue;
            }
            if (lllllllllllllllllIllIlIIIlllllIl == Aura.mc.player) {
                continue;
            }
            if (Aura.mc.player.getDistance(lllllllllllllllllIllIlIIIlllllIl) > this.range.getValue()) {
                continue;
            }
            if (((EntityLivingBase)lllllllllllllllllIllIlIIIlllllIl).getHealth() <= 0.0f) {
                continue;
            }
            if (((EntityLivingBase)lllllllllllllllllIllIlIIIlllllIl).hurtTime != 0 && this.wait.getValue()) {
                continue;
            }
            if (!this.walls.getValue() && !Aura.mc.player.canEntityBeSeen(lllllllllllllllllIllIlIIIlllllIl) && !this.canEntityFeetBeSeen(lllllllllllllllllIllIlIIIlllllIl)) {
                continue;
            }
            if (this.players.getValue() && lllllllllllllllllIllIlIIIlllllIl instanceof EntityPlayer && !Friends.isFriend(lllllllllllllllllIllIlIIIlllllIl.getName())) {
                this.attack(lllllllllllllllllIllIlIIIlllllIl);
                return;
            }
            Label_0453: {
                if (EntityUtil.isPassive(lllllllllllllllllIllIlIIIlllllIl)) {
                    if (this.animals.getValue()) {
                        break Label_0453;
                    }
                    continue;
                }
                else {
                    if (EntityUtil.isMobAggressive(lllllllllllllllllIllIlIIIlllllIl) && this.mobs.getValue()) {
                        break Label_0453;
                    }
                    continue;
                }
                continue;
            }
            this.attack(lllllllllllllllllIllIlIIIlllllIl);
            return;
        }
        this.resetRotation();
    }
    
    private void resetRotation() {
        if (this.isSpoofingAngles) {
            this.yaw = Aura.mc.player.rotationYaw;
            this.pitch = Aura.mc.player.rotationPitch;
            this.isSpoofingAngles = false;
        }
    }
    
    private float getLagComp() {
        if (this.wait.getValue()) {
            return -(20.0f - LagCompensator.INSTANCE.getTickRate());
        }
        return 0.0f;
    }
    
    private void attack(final Entity lllllllllllllllllIllIlIIIlIllIIl) {
        if (this.sharpness.getValue() && !this.checkSharpness(Aura.mc.player.getHeldItemMainhand())) {
            int lllllllllllllllllIllIlIIIlIlllIl = -1;
            for (int lllllllllllllllllIllIlIIIlIllllI = 0; lllllllllllllllllIllIlIIIlIllllI < 9; ++lllllllllllllllllIllIlIIIlIllllI) {
                final ItemStack lllllllllllllllllIllIlIIIlIlllll = Aura.mc.player.inventory.getStackInSlot(lllllllllllllllllIllIlIIIlIllllI);
                if (lllllllllllllllllIllIlIIIlIlllll != ItemStack.EMPTY) {
                    if (this.checkSharpness(lllllllllllllllllIllIlIIIlIlllll)) {
                        lllllllllllllllllIllIlIIIlIlllIl = lllllllllllllllllIllIlIIIlIllllI;
                        break;
                    }
                }
            }
            if (lllllllllllllllllIllIlIIIlIlllIl != -1) {
                Aura.mc.player.inventory.currentItem = lllllllllllllllllIllIlIIIlIlllIl;
            }
        }
        if (this.rotate.getValue()) {
            this.lookAtPacket(lllllllllllllllllIllIlIIIlIllIIl.posX, lllllllllllllllllIllIlIIIlIllIIl.posY, lllllllllllllllllIllIlIIIlIllIIl.posZ, (EntityPlayer)Aura.mc.player);
        }
        Aura.mc.playerController.attackEntity((EntityPlayer)Aura.mc.player, lllllllllllllllllIllIlIIIlIllIIl);
        Aura.mc.player.swingArm(EnumHand.MAIN_HAND);
    }
    
    public Aura() {
        super("Aura", "Hits people", 0, Category.COMBAT, true);
        this.players = this.register(new Value<Boolean>("Players", this, true));
        this.animals = this.register(new Value<Boolean>("Animals", this, false));
        this.mobs = this.register(new Value<Boolean>("Mobs", this, false));
        this.range = this.register(new Value<Double>("Range", this, 5.5, 1.0, 10.0));
        this.wait = this.register(new Value<Boolean>("Wait", this, true));
        this.walls = this.register(new Value<Boolean>("Walls", this, false));
        this.rotate = this.register(new Value<Boolean>("Rotate", this, true));
        this.sharpness = this.register(new Value<Boolean>("32k Switch", this, false));
        this.isSpoofingAngles = false;
    }
    
    private boolean checkSharpness(final ItemStack lllllllllllllllllIllIlIIIllIlIll) {
        if (lllllllllllllllllIllIlIIIllIlIll.getTagCompound() == null) {
            return false;
        }
        final NBTTagList lllllllllllllllllIllIlIIIllIlIlI = (NBTTagList)lllllllllllllllllIllIlIIIllIlIll.getTagCompound().getTag("ench");
        if (lllllllllllllllllIllIlIIIllIlIlI == null) {
            return false;
        }
        int lllllllllllllllllIllIlIIIllIllIl = 0;
        while (lllllllllllllllllIllIlIIIllIllIl < lllllllllllllllllIllIlIIIllIlIlI.tagCount()) {
            final NBTTagCompound lllllllllllllllllIllIlIIIllIlllI = lllllllllllllllllIllIlIIIllIlIlI.getCompoundTagAt(lllllllllllllllllIllIlIIIllIllIl);
            if (lllllllllllllllllIllIlIIIllIlllI.getInteger("id") == 16) {
                final int lllllllllllllllllIllIlIIIllIllll = lllllllllllllllllIllIlIIIllIlllI.getInteger("lvl");
                if (lllllllllllllllllIllIlIIIllIllll >= 16) {
                    return true;
                }
                break;
            }
            else {
                ++lllllllllllllllllIllIlIIIllIllIl;
            }
        }
        return false;
    }
}
