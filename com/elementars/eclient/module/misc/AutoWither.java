package com.elementars.eclient.module.misc;

import dev.xulu.settings.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import com.elementars.eclient.module.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;

public class AutoWither extends Module
{
    private /* synthetic */ int bodySlot;
    private final /* synthetic */ Value<String> useMode;
    private final /* synthetic */ Value<Boolean> debug;
    private static /* synthetic */ boolean isSneaking;
    private /* synthetic */ boolean rotationPlaceableZ;
    private final /* synthetic */ Value<Float> placeRange;
    private /* synthetic */ BlockPos placeTarget;
    private /* synthetic */ int buildStage;
    private /* synthetic */ int headSlot;
    private final /* synthetic */ Value<Integer> delay;
    private final /* synthetic */ Value<Boolean> rotate;
    private /* synthetic */ boolean rotationPlaceableX;
    private /* synthetic */ int delayStep;
    
    private boolean placingIsBlocked(final BlockPos lllllllllllllllllIlIllIIlllIlllI) {
        final Block lllllllllllllllllIlIllIIlllIllll = AutoWither.mc.world.getBlockState(lllllllllllllllllIlIllIIlllIlllI).getBlock();
        if (!(lllllllllllllllllIlIllIIlllIllll instanceof BlockAir)) {
            return true;
        }
        for (final Entity lllllllllllllllllIlIllIIllllIIlI : AutoWither.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(lllllllllllllllllIlIllIIlllIlllI))) {
            if (!(lllllllllllllllllIlIllIIllllIIlI instanceof EntityItem)) {
                if (lllllllllllllllllIlIllIIllllIIlI instanceof EntityXPOrb) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }
    
    private boolean checkBlocksInHotbar() {
        this.headSlot = -1;
        this.bodySlot = -1;
        for (int lllllllllllllllllIlIllIlIIlIllll = 0; lllllllllllllllllIlIllIlIIlIllll < 9; ++lllllllllllllllllIlIllIlIIlIllll) {
            final ItemStack lllllllllllllllllIlIllIlIIllIIII = AutoWither.mc.player.inventory.getStackInSlot(lllllllllllllllllIlIllIlIIlIllll);
            if (lllllllllllllllllIlIllIlIIllIIII != ItemStack.EMPTY) {
                if (lllllllllllllllllIlIllIlIIllIIII.getItem() == Items.SKULL && lllllllllllllllllIlIllIlIIllIIII.getItemDamage() == 1) {
                    if (AutoWither.mc.player.inventory.getStackInSlot(lllllllllllllllllIlIllIlIIlIllll).stackSize >= 3) {
                        this.headSlot = lllllllllllllllllIlIllIlIIlIllll;
                    }
                }
                else if (lllllllllllllllllIlIllIlIIllIIII.getItem() instanceof ItemBlock) {
                    final Block lllllllllllllllllIlIllIlIIllIIIl = ((ItemBlock)lllllllllllllllllIlIllIlIIllIIII.getItem()).getBlock();
                    if (lllllllllllllllllIlIllIlIIllIIIl instanceof BlockSoulSand && AutoWither.mc.player.inventory.getStackInSlot(lllllllllllllllllIlIllIlIIlIllll).stackSize >= 4) {
                        this.bodySlot = lllllllllllllllllIlIllIlIIlIllll;
                    }
                    if (lllllllllllllllllIlIllIlIIllIIIl == Blocks.SNOW) {
                        if (AutoWither.mc.player.inventory.getStackInSlot(lllllllllllllllllIlIllIlIIlIllll).stackSize >= 2) {
                            this.bodySlot = lllllllllllllllllIlIllIlIIlIllll;
                        }
                    }
                }
            }
        }
        return this.bodySlot != -1 && this.headSlot != -1;
    }
    
    public AutoWither() {
        super("AutoWither", "Automatically places withers", 0, Category.MISC, true);
        this.useMode = this.register(new Value<String>("UseMode", this, "Spam", EnumUtil.enumConverter(UseMode.class)));
        this.placeRange = this.register(new Value<Float>("PlaceRange", this, 3.5f, 2.0f, 10.0f));
        this.delay = this.register(new Value<Integer>("Delay", this, 20, 12, 100));
        this.rotate = this.register(new Value<Boolean>("Rotate", this, true));
        this.debug = this.register(new Value<Boolean>("Debug", this, false));
    }
    
    private static EnumFacing getPlaceableSide(final BlockPos lllllllllllllllllIlIllIlIlIIIIII) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_1        /* lllllllllllllllllIlIllIlIIlllllI */
        //     4: aload_1         /* lllllllllllllllllIlIllIlIIlllllI */
        //     5: arraylength    
        //     6: istore_2        /* lllllllllllllllllIlIllIlIIllllIl */
        //     7: iconst_0       
        //     8: istore_3        /* lllllllllllllllllIlIllIlIIllllII */
        //     9: iload_3         /* lllllllllllllllllIlIllIlIIllllII */
        //    10: iload_2         /* lllllllllllllllllIlIllIlIIllllIl */
        //    11: if_icmpge       124
        //    14: aload_1         /* lllllllllllllllllIlIllIlIIlllllI */
        //    15: iload_3         /* lllllllllllllllllIlIllIlIIllllII */
        //    16: aaload         
        //    17: astore          lllllllllllllllllIlIllIlIlIIIIIl
        //    19: aload_0         /* lllllllllllllllllIlIllIlIIllllll */
        //    20: aload           lllllllllllllllllIlIllIlIlIIIIIl
        //    22: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //    25: astore          lllllllllllllllllIlIllIlIlIIIIlI
        //    27: getstatic       com/elementars/eclient/module/misc/AutoWither.mc:Lnet/minecraft/client/Minecraft;
        //    30: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    33: aload           lllllllllllllllllIlIllIlIlIIIIlI
        //    35: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    38: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //    43: getstatic       com/elementars/eclient/module/misc/AutoWither.mc:Lnet/minecraft/client/Minecraft;
        //    46: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    49: aload           lllllllllllllllllIlIllIlIlIIIIlI
        //    51: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    54: iconst_0       
        //    55: invokevirtual   net/minecraft/block/Block.canCollideCheck:(Lnet/minecraft/block/state/IBlockState;Z)Z
        //    58: ifeq            118
        //    61: getstatic       com/elementars/eclient/module/misc/AutoWither.mc:Lnet/minecraft/client/Minecraft;
        //    64: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    67: aload           lllllllllllllllllIlIllIlIlIIIIlI
        //    69: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    72: dup            
        //    73: astore          lllllllllllllllllIlIllIlIlIIIIll
        //    75: invokeinterface net/minecraft/block/state/IBlockState.getMaterial:()Lnet/minecraft/block/material/Material;
        //    80: invokevirtual   net/minecraft/block/material/Material.isReplaceable:()Z
        //    83: ifne            118
        //    86: aload           lllllllllllllllllIlIllIlIlIIIIll
        //    88: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //    93: instanceof      Lnet/minecraft/block/BlockTallGrass;
        //    96: ifne            118
        //    99: aload           lllllllllllllllllIlIllIlIlIIIIll
        //   101: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   106: instanceof      Lnet/minecraft/block/BlockDeadBush;
        //   109: ifeq            115
        //   112: goto            118
        //   115: aload           lllllllllllllllllIlIllIlIlIIIIIl
        //   117: areturn        
        //   118: iinc            lllllllllllllllllIlIllIlIIllllII, 1
        //   121: goto            9
        //   124: aconst_null    
        //   125: areturn        
        //    StackMapTable: 00 04 FE 00 09 07 01 04 01 01 FE 00 69 07 00 FE 07 00 38 07 01 06 FF 00 02 00 07 07 01 06 07 01 04 01 01 07 00 FE 00 07 01 06 00 00 F8 00 05
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
    
    private boolean testStructure() {
        return this.testWitherStructure();
    }
    
    private static void placeBlock(final BlockPos lllllllllllllllllIlIllIlIlIlIIIl, final boolean lllllllllllllllllIlIllIlIlIlIIII) {
        final EnumFacing lllllllllllllllllIlIllIlIlIlIllI = getPlaceableSide(lllllllllllllllllIlIllIlIlIlIIIl);
        if (lllllllllllllllllIlIllIlIlIlIllI == null) {
            return;
        }
        final BlockPos lllllllllllllllllIlIllIlIlIlIlIl = lllllllllllllllllIlIllIlIlIlIIIl.offset(lllllllllllllllllIlIllIlIlIlIllI);
        final EnumFacing lllllllllllllllllIlIllIlIlIlIlII = lllllllllllllllllIlIllIlIlIlIllI.getOpposite();
        final Vec3d lllllllllllllllllIlIllIlIlIlIIll = new Vec3d((Vec3i)lllllllllllllllllIlIllIlIlIlIlIl).add(0.5, 0.5, 0.5).add(new Vec3d(lllllllllllllllllIlIllIlIlIlIlII.getDirectionVec()).scale(0.5));
        final Block lllllllllllllllllIlIllIlIlIlIIlI = AutoWither.mc.world.getBlockState(lllllllllllllllllIlIllIlIlIlIlIl).getBlock();
        if (!AutoWither.isSneaking && (BlockInteractionHelper.blackList.contains(lllllllllllllllllIlIllIlIlIlIIlI) || BlockInteractionHelper.shulkerList.contains(lllllllllllllllllIlIllIlIlIlIIlI))) {
            AutoWither.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoWither.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            AutoWither.isSneaking = true;
        }
        if (lllllllllllllllllIlIllIlIlIlIIII) {
            BlockInteractionHelper.faceVectorPacketInstant(lllllllllllllllllIlIllIlIlIlIIll);
        }
        AutoWither.mc.playerController.processRightClickBlock(AutoWither.mc.player, AutoWither.mc.world, lllllllllllllllllIlIllIlIlIlIlIl, lllllllllllllllllIlIllIlIlIlIlII, lllllllllllllllllIlIllIlIlIlIIll, EnumHand.MAIN_HAND);
        AutoWither.mc.player.swingArm(EnumHand.MAIN_HAND);
        AutoWither.mc.rightClickDelayTimer = 4;
    }
    
    @Override
    public void onUpdate() {
        if (AutoWither.mc.player == null) {
            return;
        }
        if (this.buildStage == 1) {
            AutoWither.isSneaking = false;
            this.rotationPlaceableX = false;
            this.rotationPlaceableZ = false;
            if (!this.checkBlocksInHotbar()) {
                this.sendDebugMessage("no blocks in hotbar");
                return;
            }
            final List<BlockPos> lllllllllllllllllIlIllIlIIIIIIlI = BlockInteractionHelper.getSphere(AutoWither.mc.player.getPosition().down(), this.placeRange.getValue(), this.placeRange.getValue().intValue(), false, true, 0);
            boolean lllllllllllllllllIlIllIlIIIIIIIl = true;
            for (final BlockPos lllllllllllllllllIlIllIlIIIIIllI : lllllllllllllllllIlIllIlIIIIIIlI) {
                this.placeTarget = lllllllllllllllllIlIllIlIIIIIllI.down();
                if (!this.testStructure()) {
                    continue;
                }
                lllllllllllllllllIlIllIlIIIIIIIl = false;
                break;
            }
            if (lllllllllllllllllIlIllIlIIIIIIIl) {
                if (this.useMode.getValue().equalsIgnoreCase("Single")) {
                    if (this.debug.getValue()) {
                        this.sendDebugMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED.toString()).append("Position not valid, disabling.")));
                    }
                    this.disable();
                }
                return;
            }
            AutoWither.mc.player.inventory.currentItem = this.bodySlot;
            byte lllllllllllllllllIlIllIIlllllIlI = (Object)BodyParts.bodyBase;
            final int length = lllllllllllllllllIlIllIIlllllIlI.length;
            for (double lllllllllllllllllIlIllIIlllllIII = 0; lllllllllllllllllIlIllIIlllllIII < length; ++lllllllllllllllllIlIllIIlllllIII) {
                final BlockPos lllllllllllllllllIlIllIlIIIIIlIl = lllllllllllllllllIlIllIIlllllIlI[lllllllllllllllllIlIllIIlllllIII];
                placeBlock(this.placeTarget.add((Vec3i)lllllllllllllllllIlIllIlIIIIIlIl), this.rotate.getValue());
            }
            if (this.rotationPlaceableX) {
                lllllllllllllllllIlIllIIlllllIlI = (byte)(Object)BodyParts.ArmsX;
                final int length2 = lllllllllllllllllIlIllIIlllllIlI.length;
                for (double lllllllllllllllllIlIllIIlllllIII = 0; lllllllllllllllllIlIllIIlllllIII < length2; ++lllllllllllllllllIlIllIIlllllIII) {
                    final BlockPos lllllllllllllllllIlIllIlIIIIIlII = lllllllllllllllllIlIllIIlllllIlI[lllllllllllllllllIlIllIIlllllIII];
                    placeBlock(this.placeTarget.add((Vec3i)lllllllllllllllllIlIllIlIIIIIlII), this.rotate.getValue());
                }
            }
            else if (this.rotationPlaceableZ) {
                lllllllllllllllllIlIllIIlllllIlI = (byte)(Object)BodyParts.ArmsZ;
                final int length3 = lllllllllllllllllIlIllIIlllllIlI.length;
                for (double lllllllllllllllllIlIllIIlllllIII = 0; lllllllllllllllllIlIllIIlllllIII < length3; ++lllllllllllllllllIlIllIIlllllIII) {
                    final BlockPos lllllllllllllllllIlIllIlIIIIIIll = lllllllllllllllllIlIllIIlllllIlI[lllllllllllllllllIlIllIIlllllIII];
                    placeBlock(this.placeTarget.add((Vec3i)lllllllllllllllllIlIllIlIIIIIIll), this.rotate.getValue());
                }
            }
            this.buildStage = 2;
        }
        else if (this.buildStage == 2) {
            AutoWither.mc.player.inventory.currentItem = this.headSlot;
            if (this.rotationPlaceableX) {
                final BlockPos[] access$300 = BodyParts.headsX;
                final double lllllllllllllllllIlIllIIlllllIll = access$300.length;
                for (byte lllllllllllllllllIlIllIIlllllIlI = 0; lllllllllllllllllIlIllIIlllllIlI < lllllllllllllllllIlIllIIlllllIll; ++lllllllllllllllllIlIllIIlllllIlI) {
                    final BlockPos lllllllllllllllllIlIllIlIIIIIIII = access$300[lllllllllllllllllIlIllIIlllllIlI];
                    placeBlock(this.placeTarget.add((Vec3i)lllllllllllllllllIlIllIlIIIIIIII), this.rotate.getValue());
                }
            }
            else if (this.rotationPlaceableZ) {
                final BlockPos[] access$301 = BodyParts.headsZ;
                final double lllllllllllllllllIlIllIIlllllIll = access$301.length;
                for (byte lllllllllllllllllIlIllIIlllllIlI = 0; lllllllllllllllllIlIllIIlllllIlI < lllllllllllllllllIlIllIIlllllIll; ++lllllllllllllllllIlIllIIlllllIlI) {
                    final BlockPos lllllllllllllllllIlIllIIllllllll = access$301[lllllllllllllllllIlIllIIlllllIlI];
                    placeBlock(this.placeTarget.add((Vec3i)lllllllllllllllllIlIllIIllllllll), this.rotate.getValue());
                }
            }
            if (AutoWither.isSneaking) {
                AutoWither.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoWither.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                AutoWither.isSneaking = false;
            }
            if (this.useMode.getValue().equalsIgnoreCase("Single")) {
                this.disable();
            }
            this.buildStage = 3;
        }
        else if (this.buildStage == 3) {
            if (this.delayStep < this.delay.getValue()) {
                ++this.delayStep;
            }
            else {
                this.delayStep = 1;
                this.buildStage = 1;
            }
        }
    }
    
    @Override
    public void onEnable() {
        if (AutoWither.mc.player == null) {
            this.disable();
            return;
        }
        this.buildStage = 1;
        this.delayStep = 1;
    }
    
    private boolean testWitherStructure() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_1        /* lllllllllllllllllIlIllIlIIIllIII */
        //     2: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //     3: iconst_1       
        //     4: putfield        com/elementars/eclient/module/misc/AutoWither.rotationPlaceableX:Z
        //     7: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //     8: iconst_1       
        //     9: putfield        com/elementars/eclient/module/misc/AutoWither.rotationPlaceableZ:Z
        //    12: iconst_0       
        //    13: istore_2        /* lllllllllllllllllIlIllIlIIIlIIll */
        //    14: getstatic       com/elementars/eclient/module/misc/AutoWither.mc:Lnet/minecraft/client/Minecraft;
        //    17: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    20: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //    21: getfield        com/elementars/eclient/module/misc/AutoWither.placeTarget:Lnet/minecraft/util/math/BlockPos;
        //    24: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    27: ifnonnull       32
        //    30: iconst_0       
        //    31: ireturn        
        //    32: getstatic       com/elementars/eclient/module/misc/AutoWither.mc:Lnet/minecraft/client/Minecraft;
        //    35: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    38: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //    39: getfield        com/elementars/eclient/module/misc/AutoWither.placeTarget:Lnet/minecraft/util/math/BlockPos;
        //    42: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    45: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //    50: astore_3        /* lllllllllllllllllIlIllIlIIIlIllI */
        //    51: aload_3         /* lllllllllllllllllIlIllIlIIIlIllI */
        //    52: instanceof      Lnet/minecraft/block/BlockTallGrass;
        //    55: ifne            65
        //    58: aload_3         /* lllllllllllllllllIlIllIlIIIlIllI */
        //    59: instanceof      Lnet/minecraft/block/BlockDeadBush;
        //    62: ifeq            67
        //    65: iconst_1       
        //    66: istore_2        /* lllllllllllllllllIlIllIlIIIlIlll */
        //    67: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //    68: getfield        com/elementars/eclient/module/misc/AutoWither.placeTarget:Lnet/minecraft/util/math/BlockPos;
        //    71: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //    74: invokestatic    com/elementars/eclient/module/misc/AutoWither.getPlaceableSide:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/EnumFacing;
        //    77: ifnonnull       82
        //    80: iconst_0       
        //    81: ireturn        
        //    82: invokestatic    com/elementars/eclient/module/misc/AutoWither$BodyParts.access$000:()[Lnet/minecraft/util/math/BlockPos;
        //    85: astore          lllllllllllllllllIlIllIlIIIlIIIl
        //    87: aload           lllllllllllllllllIlIllIlIIIlIIIl
        //    89: arraylength    
        //    90: istore          lllllllllllllllllIlIllIlIIIlIIII
        //    92: iconst_0       
        //    93: istore          lllllllllllllllllIlIllIlIIIIllll
        //    95: iload           lllllllllllllllllIlIllIlIIIIllll
        //    97: iload           lllllllllllllllllIlIllIlIIIlIIII
        //    99: if_icmpge       136
        //   102: aload           lllllllllllllllllIlIllIlIIIlIIIl
        //   104: iload           lllllllllllllllllIlIllIlIIIIllll
        //   106: aaload         
        //   107: astore          lllllllllllllllllIlIllIlIIIllllI
        //   109: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   110: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   111: getfield        com/elementars/eclient/module/misc/AutoWither.placeTarget:Lnet/minecraft/util/math/BlockPos;
        //   114: aload           lllllllllllllllllIlIllIlIIIllllI
        //   116: invokevirtual   net/minecraft/util/math/BlockPos.add:(Lnet/minecraft/util/math/Vec3i;)Lnet/minecraft/util/math/BlockPos;
        //   119: invokespecial   com/elementars/eclient/module/misc/AutoWither.placingIsBlocked:(Lnet/minecraft/util/math/BlockPos;)Z
        //   122: ifne            128
        //   125: goto            130
        //   128: iconst_0       
        //   129: istore_1        /* lllllllllllllllllIlIllIlIIIllIII */
        //   130: iinc            lllllllllllllllllIlIllIlIIIIllll, 1
        //   133: goto            95
        //   136: invokestatic    com/elementars/eclient/module/misc/AutoWither$BodyParts.access$100:()[Lnet/minecraft/util/math/BlockPos;
        //   139: astore          lllllllllllllllllIlIllIlIIIlIIIl
        //   141: aload           lllllllllllllllllIlIllIlIIIlIIIl
        //   143: arraylength    
        //   144: istore          lllllllllllllllllIlIllIlIIIlIIII
        //   146: iconst_0       
        //   147: istore          lllllllllllllllllIlIllIlIIIIllll
        //   149: iload           lllllllllllllllllIlIllIlIIIIllll
        //   151: iload           lllllllllllllllllIlIllIlIIIlIIII
        //   153: if_icmpge       212
        //   156: aload           lllllllllllllllllIlIllIlIIIlIIIl
        //   158: iload           lllllllllllllllllIlIllIlIIIIllll
        //   160: aaload         
        //   161: astore          lllllllllllllllllIlIllIlIIIlllIl
        //   163: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   164: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   165: getfield        com/elementars/eclient/module/misc/AutoWither.placeTarget:Lnet/minecraft/util/math/BlockPos;
        //   168: aload           lllllllllllllllllIlIllIlIIIlllIl
        //   170: invokevirtual   net/minecraft/util/math/BlockPos.add:(Lnet/minecraft/util/math/Vec3i;)Lnet/minecraft/util/math/BlockPos;
        //   173: invokespecial   com/elementars/eclient/module/misc/AutoWither.placingIsBlocked:(Lnet/minecraft/util/math/BlockPos;)Z
        //   176: ifne            201
        //   179: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   180: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   181: getfield        com/elementars/eclient/module/misc/AutoWither.placeTarget:Lnet/minecraft/util/math/BlockPos;
        //   184: aload           lllllllllllllllllIlIllIlIIIlllIl
        //   186: invokevirtual   net/minecraft/util/math/BlockPos.down:()Lnet/minecraft/util/math/BlockPos;
        //   189: invokevirtual   net/minecraft/util/math/BlockPos.add:(Lnet/minecraft/util/math/Vec3i;)Lnet/minecraft/util/math/BlockPos;
        //   192: invokespecial   com/elementars/eclient/module/misc/AutoWither.placingIsBlocked:(Lnet/minecraft/util/math/BlockPos;)Z
        //   195: ifne            201
        //   198: goto            206
        //   201: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   202: iconst_0       
        //   203: putfield        com/elementars/eclient/module/misc/AutoWither.rotationPlaceableX:Z
        //   206: iinc            lllllllllllllllllIlIllIlIIIIllll, 1
        //   209: goto            149
        //   212: invokestatic    com/elementars/eclient/module/misc/AutoWither$BodyParts.access$200:()[Lnet/minecraft/util/math/BlockPos;
        //   215: astore          lllllllllllllllllIlIllIlIIIlIIIl
        //   217: aload           lllllllllllllllllIlIllIlIIIlIIIl
        //   219: arraylength    
        //   220: istore          lllllllllllllllllIlIllIlIIIlIIII
        //   222: iconst_0       
        //   223: istore          lllllllllllllllllIlIllIlIIIIllll
        //   225: iload           lllllllllllllllllIlIllIlIIIIllll
        //   227: iload           lllllllllllllllllIlIllIlIIIlIIII
        //   229: if_icmpge       288
        //   232: aload           lllllllllllllllllIlIllIlIIIlIIIl
        //   234: iload           lllllllllllllllllIlIllIlIIIIllll
        //   236: aaload         
        //   237: astore          lllllllllllllllllIlIllIlIIIlllII
        //   239: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   240: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   241: getfield        com/elementars/eclient/module/misc/AutoWither.placeTarget:Lnet/minecraft/util/math/BlockPos;
        //   244: aload           lllllllllllllllllIlIllIlIIIlllII
        //   246: invokevirtual   net/minecraft/util/math/BlockPos.add:(Lnet/minecraft/util/math/Vec3i;)Lnet/minecraft/util/math/BlockPos;
        //   249: invokespecial   com/elementars/eclient/module/misc/AutoWither.placingIsBlocked:(Lnet/minecraft/util/math/BlockPos;)Z
        //   252: ifne            277
        //   255: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   256: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   257: getfield        com/elementars/eclient/module/misc/AutoWither.placeTarget:Lnet/minecraft/util/math/BlockPos;
        //   260: aload           lllllllllllllllllIlIllIlIIIlllII
        //   262: invokevirtual   net/minecraft/util/math/BlockPos.down:()Lnet/minecraft/util/math/BlockPos;
        //   265: invokevirtual   net/minecraft/util/math/BlockPos.add:(Lnet/minecraft/util/math/Vec3i;)Lnet/minecraft/util/math/BlockPos;
        //   268: invokespecial   com/elementars/eclient/module/misc/AutoWither.placingIsBlocked:(Lnet/minecraft/util/math/BlockPos;)Z
        //   271: ifne            277
        //   274: goto            282
        //   277: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   278: iconst_0       
        //   279: putfield        com/elementars/eclient/module/misc/AutoWither.rotationPlaceableZ:Z
        //   282: iinc            lllllllllllllllllIlIllIlIIIIllll, 1
        //   285: goto            225
        //   288: invokestatic    com/elementars/eclient/module/misc/AutoWither$BodyParts.access$300:()[Lnet/minecraft/util/math/BlockPos;
        //   291: astore          lllllllllllllllllIlIllIlIIIlIIIl
        //   293: aload           lllllllllllllllllIlIllIlIIIlIIIl
        //   295: arraylength    
        //   296: istore          lllllllllllllllllIlIllIlIIIlIIII
        //   298: iconst_0       
        //   299: istore          lllllllllllllllllIlIllIlIIIIllll
        //   301: iload           lllllllllllllllllIlIllIlIIIIllll
        //   303: iload           lllllllllllllllllIlIllIlIIIlIIII
        //   305: if_icmpge       345
        //   308: aload           lllllllllllllllllIlIllIlIIIlIIIl
        //   310: iload           lllllllllllllllllIlIllIlIIIIllll
        //   312: aaload         
        //   313: astore          lllllllllllllllllIlIllIlIIIllIll
        //   315: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   316: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   317: getfield        com/elementars/eclient/module/misc/AutoWither.placeTarget:Lnet/minecraft/util/math/BlockPos;
        //   320: aload           lllllllllllllllllIlIllIlIIIllIll
        //   322: invokevirtual   net/minecraft/util/math/BlockPos.add:(Lnet/minecraft/util/math/Vec3i;)Lnet/minecraft/util/math/BlockPos;
        //   325: invokespecial   com/elementars/eclient/module/misc/AutoWither.placingIsBlocked:(Lnet/minecraft/util/math/BlockPos;)Z
        //   328: ifne            334
        //   331: goto            339
        //   334: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   335: iconst_0       
        //   336: putfield        com/elementars/eclient/module/misc/AutoWither.rotationPlaceableX:Z
        //   339: iinc            lllllllllllllllllIlIllIlIIIIllll, 1
        //   342: goto            301
        //   345: invokestatic    com/elementars/eclient/module/misc/AutoWither$BodyParts.access$400:()[Lnet/minecraft/util/math/BlockPos;
        //   348: astore          lllllllllllllllllIlIllIlIIIlIIIl
        //   350: aload           lllllllllllllllllIlIllIlIIIlIIIl
        //   352: arraylength    
        //   353: istore          lllllllllllllllllIlIllIlIIIlIIII
        //   355: iconst_0       
        //   356: istore          lllllllllllllllllIlIllIlIIIIllll
        //   358: iload           lllllllllllllllllIlIllIlIIIIllll
        //   360: iload           lllllllllllllllllIlIllIlIIIlIIII
        //   362: if_icmpge       402
        //   365: aload           lllllllllllllllllIlIllIlIIIlIIIl
        //   367: iload           lllllllllllllllllIlIllIlIIIIllll
        //   369: aaload         
        //   370: astore          lllllllllllllllllIlIllIlIIIllIlI
        //   372: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   373: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   374: getfield        com/elementars/eclient/module/misc/AutoWither.placeTarget:Lnet/minecraft/util/math/BlockPos;
        //   377: aload           lllllllllllllllllIlIllIlIIIllIlI
        //   379: invokevirtual   net/minecraft/util/math/BlockPos.add:(Lnet/minecraft/util/math/Vec3i;)Lnet/minecraft/util/math/BlockPos;
        //   382: invokespecial   com/elementars/eclient/module/misc/AutoWither.placingIsBlocked:(Lnet/minecraft/util/math/BlockPos;)Z
        //   385: ifne            391
        //   388: goto            396
        //   391: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   392: iconst_0       
        //   393: putfield        com/elementars/eclient/module/misc/AutoWither.rotationPlaceableZ:Z
        //   396: iinc            lllllllllllllllllIlIllIlIIIIllll, 1
        //   399: goto            358
        //   402: iload_2         /* lllllllllllllllllIlIllIlIIIlIlll */
        //   403: ifne            428
        //   406: iload_1         /* lllllllllllllllllIlIllIlIIIllIII */
        //   407: ifeq            428
        //   410: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   411: getfield        com/elementars/eclient/module/misc/AutoWither.rotationPlaceableX:Z
        //   414: ifne            424
        //   417: aload_0         /* lllllllllllllllllIlIllIlIIIllIIl */
        //   418: getfield        com/elementars/eclient/module/misc/AutoWither.rotationPlaceableZ:Z
        //   421: ifeq            428
        //   424: iconst_1       
        //   425: goto            429
        //   428: iconst_0       
        //   429: ireturn        
        //    StackMapTable: 00 1B FD 00 20 01 01 FC 00 20 07 00 40 01 0E FE 00 0C 07 01 F0 01 01 FC 00 20 07 01 06 01 FA 00 05 0C FC 00 33 07 01 06 04 FA 00 05 0C FC 00 33 07 01 06 04 FA 00 05 0C FC 00 20 07 01 06 04 FA 00 05 0C FC 00 20 07 01 06 04 FA 00 05 15 03 40 01
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
    
    private static class BodyParts
    {
        private static final /* synthetic */ BlockPos[] headsZ;
        private static final /* synthetic */ BlockPos[] bodyBase;
        private static final /* synthetic */ BlockPos[] ArmsX;
        private static final /* synthetic */ BlockPos[] ArmsZ;
        private static final /* synthetic */ BlockPos[] headsX;
        
        static {
            bodyBase = new BlockPos[] { new BlockPos(0, 1, 0), new BlockPos(0, 2, 0) };
            ArmsX = new BlockPos[] { new BlockPos(-1, 2, 0), new BlockPos(1, 2, 0) };
            ArmsZ = new BlockPos[] { new BlockPos(0, 2, -1), new BlockPos(0, 2, 1) };
            headsX = new BlockPos[] { new BlockPos(0, 3, 0), new BlockPos(-1, 3, 0), new BlockPos(1, 3, 0) };
            headsZ = new BlockPos[] { new BlockPos(0, 3, 0), new BlockPos(0, 3, -1), new BlockPos(0, 3, 1) };
            head = new BlockPos[] { new BlockPos(0, 3, 0) };
        }
    }
    
    private enum UseMode
    {
        SINGLE, 
        SPAM;
    }
}
