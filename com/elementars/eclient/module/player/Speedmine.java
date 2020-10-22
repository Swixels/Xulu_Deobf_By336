package com.elementars.eclient.module.player;

import dev.xulu.settings.*;
import net.minecraft.block.state.*;
import com.elementars.eclient.util.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import net.minecraft.world.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import com.elementars.eclient.module.combat.*;
import com.elementars.eclient.event.events.*;
import com.elementars.eclient.event.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.item.*;
import com.elementars.eclient.module.*;

public class Speedmine extends Module
{
    private final /* synthetic */ Value<Integer> boxAlpha;
    private /* synthetic */ boolean isMining;
    public /* synthetic */ Value<Boolean> noDelay;
    public /* synthetic */ Value<Boolean> outline;
    public /* synthetic */ Value<Boolean> noBreakAnim;
    public /* synthetic */ Value<Boolean> pickaxe;
    private final /* synthetic */ Value<Float> lineWidth;
    public /* synthetic */ Value<Mode> mode;
    public /* synthetic */ Value<Boolean> doubleBreak;
    public /* synthetic */ Value<Boolean> allow;
    public /* synthetic */ IBlockState currentBlockState;
    private final /* synthetic */ Timer timer;
    public /* synthetic */ Value<Boolean> reset;
    public /* synthetic */ Value<Float> damage;
    public /* synthetic */ Value<Boolean> noSwing;
    private /* synthetic */ BlockPos lastPos;
    private static /* synthetic */ Speedmine INSTANCE;
    public /* synthetic */ Value<Boolean> webSwitch;
    public /* synthetic */ BlockPos currentPos;
    private /* synthetic */ EnumFacing lastFacing;
    public /* synthetic */ Value<Boolean> box;
    public /* synthetic */ Value<Boolean> tweaks;
    public /* synthetic */ Value<Boolean> noTrace;
    public /* synthetic */ Value<Boolean> render;
    
    @EventTarget
    public void onBlockEvent(final EventPlayerDamageBlock llllllllllllllllIlIIllIlllIIIlll) {
        if (Speedmine.mc.player == null || Speedmine.mc.world == null) {
            return;
        }
        if (this.tweaks.getValue()) {
            if (canBreak(llllllllllllllllIlIIllIlllIIIlll.getPos())) {
                if (this.reset.getValue()) {
                    Speedmine.mc.playerController.isHittingBlock = false;
                }
                switch (this.mode.getValue()) {
                    case PACKET: {
                        if (this.currentPos == null) {
                            this.currentPos = llllllllllllllllIlIIllIlllIIIlll.getPos();
                            this.currentBlockState = Speedmine.mc.world.getBlockState(this.currentPos);
                            this.timer.reset();
                        }
                        Speedmine.mc.player.swingArm(EnumHand.MAIN_HAND);
                        Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, llllllllllllllllIlIIllIlllIIIlll.getPos(), llllllllllllllllIlIIllIlllIIIlll.getFacing()));
                        Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, llllllllllllllllIlIIllIlllIIIlll.getPos(), llllllllllllllllIlIIllIlllIIIlll.getFacing()));
                        llllllllllllllllIlIIllIlllIIIlll.setCancelled(true);
                        break;
                    }
                    case DAMAGE: {
                        if (Speedmine.mc.playerController.curBlockDamageMP >= this.damage.getValue()) {
                            Speedmine.mc.playerController.curBlockDamageMP = 1.0f;
                            break;
                        }
                        break;
                    }
                    case INSTANT: {
                        Speedmine.mc.player.swingArm(EnumHand.MAIN_HAND);
                        Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, llllllllllllllllIlIIllIlllIIIlll.getPos(), llllllllllllllllIlIIllIlllIIIlll.getFacing()));
                        Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, llllllllllllllllIlIIllIlllIIIlll.getPos(), llllllllllllllllIlIIllIlllIIIlll.getFacing()));
                        Speedmine.mc.playerController.onPlayerDestroyBlock(llllllllllllllllIlIIllIlllIIIlll.getPos());
                        Speedmine.mc.world.setBlockToAir(llllllllllllllllIlIIllIlllIIIlll.getPos());
                        break;
                    }
                }
            }
            if (this.doubleBreak.getValue()) {
                final BlockPos llllllllllllllllIlIIllIlllIIlIll = llllllllllllllllIlIIllIlllIIIlll.getPos().add(0, 1, 0);
                if (canBreak(llllllllllllllllIlIIllIlllIIlIll) && Speedmine.mc.player.getDistance((double)llllllllllllllllIlIIllIlllIIlIll.getX(), (double)llllllllllllllllIlIIllIlllIIlIll.getY(), (double)llllllllllllllllIlIIllIlllIIlIll.getZ()) <= 5.0) {
                    Speedmine.mc.player.swingArm(EnumHand.MAIN_HAND);
                    Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, llllllllllllllllIlIIllIlllIIlIll, llllllllllllllllIlIIllIlllIIIlll.getFacing()));
                    Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, llllllllllllllllIlIIllIlllIIlIll, llllllllllllllllIlIIllIlllIIIlll.getFacing()));
                    Speedmine.mc.playerController.onPlayerDestroyBlock(llllllllllllllllIlIIllIlllIIlIll);
                    Speedmine.mc.world.setBlockToAir(llllllllllllllllIlIIllIlllIIlIll);
                }
            }
        }
    }
    
    public void showAnimation() {
        this.showAnimation(false, null, null);
    }
    
    public static boolean canBreak(final BlockPos llllllllllllllllIlIIllIllIlIllIl) {
        final IBlockState llllllllllllllllIlIIllIllIlIllll = Speedmine.mc.world.getBlockState(llllllllllllllllIlIIllIllIlIllIl);
        final Block llllllllllllllllIlIIllIllIlIlllI = llllllllllllllllIlIIllIllIlIllll.getBlock();
        return llllllllllllllllIlIIllIllIlIlllI.getBlockHardness(llllllllllllllllIlIIllIllIlIllll, (World)Speedmine.mc.world, llllllllllllllllIlIIllIllIlIllIl) != -1.0f;
    }
    
    @Override
    public void onUpdate() {
        if (Speedmine.mc.world == null || Speedmine.mc.player == null) {
            return;
        }
        if (this.currentPos != null) {
            if (!Speedmine.mc.world.getBlockState(this.currentPos).equals(this.currentBlockState) || Speedmine.mc.world.getBlockState(this.currentPos).getBlock() == Blocks.AIR) {
                this.currentPos = null;
                this.currentBlockState = null;
            }
            else if (this.webSwitch.getValue() && this.currentBlockState.getBlock() == Blocks.WEB && Speedmine.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
                MiddleClickPearl.switchToHotbarSlot(ItemSword.class, false);
            }
        }
        if (this.noDelay.getValue()) {
            Speedmine.mc.playerController.blockHitDelay = 0;
        }
        if (this.isMining && this.lastPos != null && this.lastFacing != null && this.noBreakAnim.getValue()) {
            Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.lastPos, this.lastFacing));
        }
        if (this.reset.getValue() && Speedmine.mc.gameSettings.keyBindUseItem.isKeyDown() && !this.allow.getValue()) {
            Speedmine.mc.playerController.isHittingBlock = false;
        }
    }
    
    @EventTarget
    public void onClickBlock(final EventClickBlock llllllllllllllllIlIIllIlllIlIIII) {
        if (Speedmine.mc.player == null || Speedmine.mc.world == null) {
            return;
        }
        if (this.reset.getValue() && Speedmine.mc.playerController.curBlockDamageMP > 0.1f) {
            Speedmine.mc.playerController.isHittingBlock = true;
        }
    }
    
    private void setInstance() {
        Speedmine.INSTANCE = this;
    }
    
    static {
        Speedmine.INSTANCE = new Speedmine();
    }
    
    public static Speedmine getInstance() {
        if (Speedmine.INSTANCE == null) {
            Speedmine.INSTANCE = new Speedmine();
        }
        return Speedmine.INSTANCE;
    }
    
    private void showAnimation(final boolean llllllllllllllllIlIIllIllIllllII, final BlockPos llllllllllllllllIlIIllIllIllllll, final EnumFacing llllllllllllllllIlIIllIllIlllIlI) {
        this.isMining = llllllllllllllllIlIIllIllIllllII;
        this.lastPos = llllllllllllllllIlIIllIllIllllll;
        this.lastFacing = llllllllllllllllIlIIllIllIlllIlI;
    }
    
    @EventTarget
    public void onPacketSend(final EventSendPacket llllllllllllllllIlIIllIlllIlIllI) {
        if (Speedmine.mc.world == null || Speedmine.mc.player == null) {
            return;
        }
        if (llllllllllllllllIlIIllIlllIlIllI.getEventState() == Event.State.PRE) {
            if (this.noSwing.getValue() && llllllllllllllllIlIIllIlllIlIllI.getPacket() instanceof CPacketAnimation) {
                llllllllllllllllIlIIllIlllIlIllI.setCancelled(true);
            }
            if (this.noBreakAnim.getValue() && llllllllllllllllIlIIllIlllIlIllI.getPacket() instanceof CPacketPlayerDigging) {
                final CPacketPlayerDigging llllllllllllllllIlIIllIlllIllIlI = (CPacketPlayerDigging)llllllllllllllllIlIIllIlllIlIllI.getPacket();
                if (llllllllllllllllIlIIllIlllIllIlI != null && llllllllllllllllIlIIllIlllIllIlI.getPosition() != null) {
                    try {
                        for (final Entity llllllllllllllllIlIIllIlllIllIll : Speedmine.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(llllllllllllllllIlIIllIlllIllIlI.getPosition()))) {
                            if (llllllllllllllllIlIIllIlllIllIll instanceof EntityEnderCrystal) {
                                this.showAnimation();
                                return;
                            }
                        }
                    }
                    catch (Exception ex) {}
                    if (llllllllllllllllIlIIllIlllIllIlI.getAction().equals((Object)CPacketPlayerDigging.Action.START_DESTROY_BLOCK)) {
                        this.showAnimation(true, llllllllllllllllIlIIllIlllIllIlI.getPosition(), llllllllllllllllIlIIllIlllIllIlI.getFacing());
                    }
                    if (llllllllllllllllIlIIllIlllIllIlI.getAction().equals((Object)CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK)) {
                        this.showAnimation();
                    }
                }
            }
        }
    }
    
    public Speedmine() {
        super("Speedmine", "Speeds up mining.", 0, Category.PLAYER, true);
        this.tweaks = this.register(new Value<Boolean>("Tweaks", this, true));
        this.mode = this.register(new Value<Mode>("Mode", this, Mode.PACKET, Mode.values())).visibleWhen(llllllllllllllllIlIIllIllIIlIIII -> this.tweaks.getValue());
        this.reset = this.register(new Value<Boolean>("Reset", this, true));
        this.damage = this.register(new Value<Float>("Damage", this, 0.7f, 0.0f, 1.0f)).visibleWhen(llllllllllllllllIlIIllIllIIlIlII -> this.mode.getValue() == Mode.DAMAGE && this.tweaks.getValue());
        this.noBreakAnim = this.register(new Value<Boolean>("NoBreakAnim", this, false));
        this.noDelay = this.register(new Value<Boolean>("NoDelay", this, false));
        this.noSwing = this.register(new Value<Boolean>("NoSwing", this, false));
        this.noTrace = this.register(new Value<Boolean>("NoTrace", this, false));
        this.allow = this.register(new Value<Boolean>("AllowMultiTask", this, false));
        this.pickaxe = this.register(new Value<Boolean>("Pickaxe", this, true)).visibleWhen(llllllllllllllllIlIIllIllIIllIII -> this.noTrace.getValue());
        this.doubleBreak = this.register(new Value<Boolean>("DoubleBreak", this, false));
        this.webSwitch = this.register(new Value<Boolean>("WebSwitch", this, false));
        this.render = this.register(new Value<Boolean>("Render", this, false));
        this.box = this.register(new Value<Boolean>("Box", this, false)).visibleWhen(llllllllllllllllIlIIllIllIIlllII -> this.render.getValue());
        this.outline = this.register(new Value<Boolean>("Outline", this, true)).visibleWhen(llllllllllllllllIlIIllIllIlIIIII -> this.render.getValue());
        this.boxAlpha = this.register(new Value<Integer>("BoxAlpha", this, 85, 0, 255)).visibleWhen(llllllllllllllllIlIIllIllIlIIlII -> this.box.getValue() && this.render.getValue());
        this.lineWidth = this.register(new Value<Float>("LineWidth", this, 1.0f, 0.1f, 5.0f)).visibleWhen(llllllllllllllllIlIIllIllIlIlIII -> this.outline.getValue() && this.render.getValue());
        this.timer = new Timer();
        this.isMining = false;
        this.lastPos = null;
        this.lastFacing = null;
        this.setInstance();
    }
    
    @Override
    public String getHudInfo() {
        return this.mode.getValue().name();
    }
    
    public enum Mode
    {
        DAMAGE, 
        INSTANT, 
        PACKET;
    }
}
