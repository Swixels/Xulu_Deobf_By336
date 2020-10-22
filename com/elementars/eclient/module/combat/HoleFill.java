package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import net.minecraft.block.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.friend.*;
import net.minecraft.client.multiplayer.*;
import com.elementars.eclient.module.*;
import net.minecraft.init.*;
import com.elementars.eclient.enemy.*;
import net.minecraft.entity.*;
import net.minecraft.block.material.*;
import net.minecraft.item.*;
import com.elementars.eclient.util.*;
import java.util.*;
import java.util.function.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;

public class HoleFill extends Module
{
    public final /* synthetic */ Value<Boolean> noGlitchBlocks;
    private final /* synthetic */ Value<Boolean> triggerable;
    private /* synthetic */ int waitCounter;
    private final /* synthetic */ Value<Boolean> useEC;
    /* synthetic */ int delay;
    private final /* synthetic */ Value<Integer> waitTick;
    private final /* synthetic */ Value<Boolean> pre;
    private /* synthetic */ List<Block> whiteList;
    private /* synthetic */ ArrayList<BlockPos> holes;
    private final /* synthetic */ Value<Boolean> rotate;
    private final /* synthetic */ Value<Boolean> chat;
    private final /* synthetic */ Value<Integer> yRange;
    private final /* synthetic */ Value<Integer> range;
    
    @Override
    public void onEnable() {
        this.delay = 20;
        if (HoleFill.mc.player != null && this.chat.getValue()) {
            this.sendDebugMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("Enabled!")));
        }
    }
    
    @Override
    public void onDisable() {
        if (HoleFill.mc.player != null && this.chat.getValue()) {
            this.sendDebugMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("Disabled!")));
        }
    }
    
    private static PlayerControllerMP getPlayerController() {
        return HoleFill.mc.playerController;
    }
    
    public HoleFill() {
        super("HoleFill", "Fills holes", 0, Category.COMBAT, true);
        this.range = this.register(new Value<Integer>("Range", this, 5, 1, 10));
        this.yRange = this.register(new Value<Integer>("YRange", this, 2, 1, 10));
        this.rotate = this.register(new Value<Boolean>("Rotate", this, true));
        this.triggerable = this.register(new Value<Boolean>("Triggerable", this, true));
        this.waitTick = this.register(new Value<Integer>("TickDelay", this, 1, 0, 10));
        this.useEC = this.register(new Value<Boolean>("UseEnderchests", this, false));
        this.noGlitchBlocks = this.register(new Value<Boolean>("NoGlitchBlocks", this, true));
        this.pre = this.register(new Value<Boolean>("Prioritize Enemies", this, false));
        this.chat = this.register(new Value<Boolean>("Chat", this, false));
        this.holes = new ArrayList<BlockPos>();
        this.whiteList = Arrays.asList(Blocks.OBSIDIAN);
    }
    
    private void place(final BlockPos llllllllllllllllllIIIIllIlIIIIlI) {
        for (final Entity llllllllllllllllllIIIIllIlIIIllI : HoleFill.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(llllllllllllllllllIIIIllIlIIIIlI))) {
            if (llllllllllllllllllIIIIllIlIIIllI instanceof EntityLivingBase) {
                return;
            }
        }
        placeBlockScaffold(llllllllllllllllllIIIIllIlIIIIlI, this.rotate.getValue());
        ++this.waitCounter;
    }
    
    @Override
    public void onUpdate() {
        if (this.triggerable.getValue()) {
            if (this.delay > 0) {
                --this.delay;
            }
            else {
                this.toggle();
            }
        }
        this.holes = new ArrayList<BlockPos>();
        if (this.useEC.getValue()) {
            if (!this.whiteList.contains(Blocks.ENDER_CHEST)) {
                this.whiteList.add(Blocks.ENDER_CHEST);
            }
        }
        else {
            this.whiteList.remove(Blocks.ENDER_CHEST);
        }
        final Iterable<BlockPos> llllllllllllllllllIIIIllIlIllIlI = (Iterable<BlockPos>)BlockPos.getAllInBox(HoleFill.mc.player.getPosition().add(-this.range.getValue(), -this.yRange.getValue(), -this.range.getValue()), HoleFill.mc.player.getPosition().add((double)this.range.getValue(), (double)this.yRange.getValue(), (double)this.range.getValue()));
        for (final BlockPos llllllllllllllllllIIIIllIlIlllll : llllllllllllllllllIIIIllIlIllIlI) {
            if (!HoleFill.mc.world.getBlockState(llllllllllllllllllIIIIllIlIlllll).getMaterial().blocksMovement() && !HoleFill.mc.world.getBlockState(llllllllllllllllllIIIIllIlIlllll.add(0, 1, 0)).getMaterial().blocksMovement()) {
                final boolean llllllllllllllllllIIIIllIllIIIII = (HoleFill.mc.world.getBlockState(llllllllllllllllllIIIIllIlIlllll.add(1, 0, 0)).getBlock() == Blocks.BEDROCK | HoleFill.mc.world.getBlockState(llllllllllllllllllIIIIllIlIlllll.add(1, 0, 0)).getBlock() == Blocks.OBSIDIAN) && (HoleFill.mc.world.getBlockState(llllllllllllllllllIIIIllIlIlllll.add(0, 0, 1)).getBlock() == Blocks.BEDROCK | HoleFill.mc.world.getBlockState(llllllllllllllllllIIIIllIlIlllll.add(0, 0, 1)).getBlock() == Blocks.OBSIDIAN) && (HoleFill.mc.world.getBlockState(llllllllllllllllllIIIIllIlIlllll.add(-1, 0, 0)).getBlock() == Blocks.BEDROCK | HoleFill.mc.world.getBlockState(llllllllllllllllllIIIIllIlIlllll.add(-1, 0, 0)).getBlock() == Blocks.OBSIDIAN) && (HoleFill.mc.world.getBlockState(llllllllllllllllllIIIIllIlIlllll.add(0, 0, -1)).getBlock() == Blocks.BEDROCK | HoleFill.mc.world.getBlockState(llllllllllllllllllIIIIllIlIlllll.add(0, 0, -1)).getBlock() == Blocks.OBSIDIAN) && HoleFill.mc.world.getBlockState(llllllllllllllllllIIIIllIlIlllll.add(0, 0, 0)).getMaterial() == Material.AIR && HoleFill.mc.world.getBlockState(llllllllllllllllllIIIIllIlIlllll.add(0, 1, 0)).getMaterial() == Material.AIR && HoleFill.mc.world.getBlockState(llllllllllllllllllIIIIllIlIlllll.add(0, 2, 0)).getMaterial() == Material.AIR;
                if (!llllllllllllllllllIIIIllIllIIIII) {
                    continue;
                }
                this.holes.add(llllllllllllllllllIIIIllIlIlllll);
            }
        }
        int llllllllllllllllllIIIIllIlIllIIl = -1;
        for (int llllllllllllllllllIIIIllIlIlllII = 0; llllllllllllllllllIIIIllIlIlllII < 9; ++llllllllllllllllllIIIIllIlIlllII) {
            final ItemStack llllllllllllllllllIIIIllIlIlllIl = HoleFill.mc.player.inventory.getStackInSlot(llllllllllllllllllIIIIllIlIlllII);
            if (llllllllllllllllllIIIIllIlIlllIl != ItemStack.EMPTY && llllllllllllllllllIIIIllIlIlllIl.getItem() instanceof ItemBlock) {
                final Block llllllllllllllllllIIIIllIlIllllI = ((ItemBlock)llllllllllllllllllIIIIllIlIlllIl.getItem()).getBlock();
                if (this.whiteList.contains(llllllllllllllllllIIIIllIlIllllI)) {
                    llllllllllllllllllIIIIllIlIllIIl = llllllllllllllllllIIIIllIlIlllII;
                    break;
                }
            }
        }
        if (llllllllllllllllllIIIIllIlIllIIl == -1) {
            return;
        }
        final int llllllllllllllllllIIIIllIlIllIII = HoleFill.mc.player.inventory.currentItem;
        final BoolSwitch llllllllllllllllllIIIIllIlIlIlll = new BoolSwitch(false);
        TargetPlayers.targettedplayers.keySet().stream().map(llllllllllllllllllIIIIlIlllIIlII -> HoleFill.mc.world.getPlayerEntityByName(llllllllllllllllllIIIIlIlllIIlII)).filter(Objects::nonNull).filter(llllllllllllllllllIIIIlIlllIlIII -> HoleFill.mc.player.getDistance(llllllllllllllllllIIIIlIlllIlIII) <= this.range.getValue()).min(Comparator.comparing(llllllllllllllllllIIIIlIlllIllIl -> HoleFill.mc.player.getDistance(llllllllllllllllllIIIIlIlllIllIl))).ifPresent(llllllllllllllllllIIIIlIllllIIlI -> {
            llllllllllllllllllIIIIllIlIlIlll.setValue(true);
            this.holes.sort(Comparator.comparing((Function<? super Object, ? extends Comparable>)llllllllllllllllllIIIIlIllllIIlI::func_174818_b));
            return;
        });
        if (!llllllllllllllllllIIIIllIlIlIlll.isValue()) {
            HoleFill.mc.world.playerEntities.stream().filter(llllllllllllllllllIIIIlIlllllIII -> !Friends.isFriend(llllllllllllllllllIIIIlIlllllIII.getName())).filter(llllllllllllllllllIIIIlIlllllIll -> HoleFill.mc.player.getDistance(llllllllllllllllllIIIIlIlllllIll) <= this.range.getValue()).min(Comparator.comparing(llllllllllllllllllIIIIllIIIIIIlI -> HoleFill.mc.player.getDistance(llllllllllllllllllIIIIllIIIIIIlI))).ifPresent(llllllllllllllllllIIIIllIIIIIllI -> this.holes.sort(Comparator.comparing((Function<? super Object, ? extends Comparable>)llllllllllllllllllIIIIllIIIIIllI::func_174818_b)));
            if (this.pre.getValue()) {
                HoleFill.mc.world.playerEntities.stream().filter(llllllllllllllllllIIIIllIIIIlIlI -> !Friends.isFriend(llllllllllllllllllIIIIllIIIIlIlI.getName()) && Enemies.isEnemy(llllllllllllllllllIIIIllIIIIlIlI.getName())).filter(llllllllllllllllllIIIIllIIIIllIl -> HoleFill.mc.player.getDistance(llllllllllllllllllIIIIllIIIIllIl) <= this.range.getValue()).min(Comparator.comparing(llllllllllllllllllIIIIllIIIlIlII -> HoleFill.mc.player.getDistance(llllllllllllllllllIIIIllIIIlIlII))).ifPresent(llllllllllllllllllIIIIllIIIlIllI -> this.holes.sort(Comparator.comparing((Function<? super Object, ? extends Comparable>)llllllllllllllllllIIIIllIIIlIllI::func_174818_b)));
            }
        }
        if (this.waitTick.getValue() > 0.0) {
            if (this.waitCounter < this.waitTick.getValue()) {
                HoleFill.mc.player.inventory.currentItem = llllllllllllllllllIIIIllIlIllIIl;
                this.holes.forEach(this::place);
                HoleFill.mc.player.inventory.currentItem = llllllllllllllllllIIIIllIlIllIII;
                return;
            }
            this.waitCounter = 0;
        }
    }
    
    public static boolean placeBlockScaffold(final BlockPos llllllllllllllllllIIIIllIIlIlllI, final boolean llllllllllllllllllIIIIllIIllIIII) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: getstatic       com/elementars/eclient/module/combat/HoleFill.mc:Lnet/minecraft/client/Minecraft;
        //     7: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    10: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //    13: getstatic       com/elementars/eclient/module/combat/HoleFill.mc:Lnet/minecraft/client/Minecraft;
        //    16: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    19: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //    22: getstatic       com/elementars/eclient/module/combat/HoleFill.mc:Lnet/minecraft/client/Minecraft;
        //    25: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    28: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getEyeHeight:()F
        //    31: f2d            
        //    32: dadd           
        //    33: getstatic       com/elementars/eclient/module/combat/HoleFill.mc:Lnet/minecraft/client/Minecraft;
        //    36: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    39: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //    42: invokespecial   net/minecraft/util/math/Vec3d.<init>:(DDD)V
        //    45: astore_2        /* llllllllllllllllllIIIIllIIlIllll */
        //    46: invokestatic    net/minecraft/util/EnumFacing.values:()[Lnet/minecraft/util/EnumFacing;
        //    49: astore_3        /* llllllllllllllllllIIIIllIIlIlIll */
        //    50: aload_3         /* llllllllllllllllllIIIIllIIlIlIll */
        //    51: arraylength    
        //    52: istore          llllllllllllllllllIIIIllIIlIlIlI
        //    54: iconst_0       
        //    55: istore          llllllllllllllllllIIIIllIIlIlIIl
        //    57: iload           llllllllllllllllllIIIIllIIlIlIIl
        //    59: iload           llllllllllllllllllIIIIllIIlIlIlI
        //    61: if_icmpge       308
        //    64: aload_3         /* llllllllllllllllllIIIIllIIlIlIll */
        //    65: iload           llllllllllllllllllIIIIllIIlIlIIl
        //    67: aaload         
        //    68: astore          llllllllllllllllllIIIIllIIllIIlI
        //    70: aload_0         /* llllllllllllllllllIIIIllIIllIIIl */
        //    71: aload           llllllllllllllllllIIIIllIIllIIlI
        //    73: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //    76: astore          llllllllllllllllllIIIIllIIllIlII
        //    78: aload           llllllllllllllllllIIIIllIIllIIlI
        //    80: invokevirtual   net/minecraft/util/EnumFacing.getOpposite:()Lnet/minecraft/util/EnumFacing;
        //    83: astore          llllllllllllllllllIIIIllIIllIIll
        //    85: aload           llllllllllllllllllIIIIllIIllIlII
        //    87: invokestatic    com/elementars/eclient/util/BlockInteractionHelper.canBeClicked:(Lnet/minecraft/util/math/BlockPos;)Z
        //    90: ifeq            302
        //    93: new             Lnet/minecraft/util/math/Vec3d;
        //    96: dup            
        //    97: aload           llllllllllllllllllIIIIllIIllIlII
        //    99: invokespecial   net/minecraft/util/math/Vec3d.<init>:(Lnet/minecraft/util/math/Vec3i;)V
        //   102: ldc2_w          0.5
        //   105: ldc2_w          0.5
        //   108: ldc2_w          0.5
        //   111: invokevirtual   net/minecraft/util/math/Vec3d.add:(DDD)Lnet/minecraft/util/math/Vec3d;
        //   114: new             Lnet/minecraft/util/math/Vec3d;
        //   117: dup            
        //   118: aload           llllllllllllllllllIIIIllIIllIIll
        //   120: invokevirtual   net/minecraft/util/EnumFacing.getDirectionVec:()Lnet/minecraft/util/math/Vec3i;
        //   123: invokespecial   net/minecraft/util/math/Vec3d.<init>:(Lnet/minecraft/util/math/Vec3i;)V
        //   126: ldc2_w          0.5
        //   129: invokevirtual   net/minecraft/util/math/Vec3d.scale:(D)Lnet/minecraft/util/math/Vec3d;
        //   132: invokevirtual   net/minecraft/util/math/Vec3d.add:(Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/Vec3d;
        //   135: astore          llllllllllllllllllIIIIllIIllIlIl
        //   137: iload_1         /* llllllllllllllllllIIIIllIIlIllIl */
        //   138: ifeq            146
        //   141: aload           llllllllllllllllllIIIIllIIllIlIl
        //   143: invokestatic    com/elementars/eclient/util/BlockInteractionHelper.faceVectorPacketInstant:(Lnet/minecraft/util/math/Vec3d;)V
        //   146: getstatic       com/elementars/eclient/module/combat/HoleFill.mc:Lnet/minecraft/client/Minecraft;
        //   149: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   152: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //   155: new             Lnet/minecraft/network/play/client/CPacketEntityAction;
        //   158: dup            
        //   159: getstatic       com/elementars/eclient/module/combat/HoleFill.mc:Lnet/minecraft/client/Minecraft;
        //   162: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   165: getstatic       net/minecraft/network/play/client/CPacketEntityAction$Action.START_SNEAKING:Lnet/minecraft/network/play/client/CPacketEntityAction$Action;
        //   168: invokespecial   net/minecraft/network/play/client/CPacketEntityAction.<init>:(Lnet/minecraft/entity/Entity;Lnet/minecraft/network/play/client/CPacketEntityAction$Action;)V
        //   171: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //   174: aload           llllllllllllllllllIIIIllIIllIlII
        //   176: aload           llllllllllllllllllIIIIllIIllIIll
        //   178: aload           llllllllllllllllllIIIIllIIllIlIl
        //   180: invokestatic    com/elementars/eclient/module/combat/HoleFill.processRightClickBlock:(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;Lnet/minecraft/util/math/Vec3d;)V
        //   183: getstatic       com/elementars/eclient/module/combat/HoleFill.mc:Lnet/minecraft/client/Minecraft;
        //   186: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   189: getstatic       net/minecraft/util/EnumHand.MAIN_HAND:Lnet/minecraft/util/EnumHand;
        //   192: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.swingArm:(Lnet/minecraft/util/EnumHand;)V
        //   195: getstatic       com/elementars/eclient/module/combat/HoleFill.mc:Lnet/minecraft/client/Minecraft;
        //   198: iconst_0       
        //   199: putfield        net/minecraft/client/Minecraft.rightClickDelayTimer:I
        //   202: getstatic       com/elementars/eclient/module/combat/HoleFill.mc:Lnet/minecraft/client/Minecraft;
        //   205: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   208: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //   211: new             Lnet/minecraft/network/play/client/CPacketEntityAction;
        //   214: dup            
        //   215: getstatic       com/elementars/eclient/module/combat/HoleFill.mc:Lnet/minecraft/client/Minecraft;
        //   218: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   221: getstatic       net/minecraft/network/play/client/CPacketEntityAction$Action.STOP_SNEAKING:Lnet/minecraft/network/play/client/CPacketEntityAction$Action;
        //   224: invokespecial   net/minecraft/network/play/client/CPacketEntityAction.<init>:(Lnet/minecraft/entity/Entity;Lnet/minecraft/network/play/client/CPacketEntityAction$Action;)V
        //   227: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //   230: getstatic       com/elementars/eclient/Xulu.MODULE_MANAGER:Lcom/elementars/eclient/module/ModuleManager;
        //   233: ldc             Lcom/elementars/eclient/module/combat/HoleFill;.class
        //   235: invokevirtual   com/elementars/eclient/module/ModuleManager.getModuleT:(Ljava/lang/Class;)Lcom/elementars/eclient/module/Module;
        //   238: checkcast       Lcom/elementars/eclient/module/combat/HoleFill;
        //   241: getfield        com/elementars/eclient/module/combat/HoleFill.noGlitchBlocks:Ldev/xulu/settings/Value;
        //   244: invokevirtual   dev/xulu/settings/Value.getValue:()Ljava/lang/Object;
        //   247: checkcast       Ljava/lang/Boolean;
        //   250: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   253: ifeq            300
        //   256: getstatic       com/elementars/eclient/module/combat/Surround.mc:Lnet/minecraft/client/Minecraft;
        //   259: getfield        net/minecraft/client/Minecraft.playerController:Lnet/minecraft/client/multiplayer/PlayerControllerMP;
        //   262: invokevirtual   net/minecraft/client/multiplayer/PlayerControllerMP.getCurrentGameType:()Lnet/minecraft/world/GameType;
        //   265: getstatic       net/minecraft/world/GameType.CREATIVE:Lnet/minecraft/world/GameType;
        //   268: invokevirtual   net/minecraft/world/GameType.equals:(Ljava/lang/Object;)Z
        //   271: ifne            300
        //   274: getstatic       com/elementars/eclient/module/combat/Surround.mc:Lnet/minecraft/client/Minecraft;
        //   277: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   280: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //   283: new             Lnet/minecraft/network/play/client/CPacketPlayerDigging;
        //   286: dup            
        //   287: getstatic       net/minecraft/network/play/client/CPacketPlayerDigging$Action.START_DESTROY_BLOCK:Lnet/minecraft/network/play/client/CPacketPlayerDigging$Action;
        //   290: aload           llllllllllllllllllIIIIllIIllIlII
        //   292: aload           llllllllllllllllllIIIIllIIllIIll
        //   294: invokespecial   net/minecraft/network/play/client/CPacketPlayerDigging.<init>:(Lnet/minecraft/network/play/client/CPacketPlayerDigging$Action;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)V
        //   297: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //   300: iconst_1       
        //   301: ireturn        
        //   302: iinc            llllllllllllllllllIIIIllIIlIlIIl, 1
        //   305: goto            57
        //   308: iconst_0       
        //   309: ireturn        
        //    StackMapTable: 00 05 FF 00 39 00 06 07 01 96 01 07 02 77 07 02 8F 01 01 00 00 FF 00 58 00 0A 07 01 96 01 07 02 77 07 02 8F 01 01 07 02 89 07 01 96 07 02 89 07 02 77 00 00 FB 00 99 FA 00 01 F8 00 05
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.lang.Thread.run(Thread.java:748)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static void processRightClickBlock(final BlockPos llllllllllllllllllIIIIllIIIllllI, final EnumFacing llllllllllllllllllIIIIllIIIlllIl, final Vec3d llllllllllllllllllIIIIllIIIlllII) {
        getPlayerController().processRightClickBlock(HoleFill.mc.player, HoleFill.mc.world, llllllllllllllllllIIIIllIIIllllI, llllllllllllllllllIIIIllIIIlllIl, llllllllllllllllllIIIIllIIIlllII, EnumHand.MAIN_HAND);
    }
}
