package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.event.events.*;
import com.elementars.eclient.module.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;
import net.minecraft.block.state.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.friend.*;
import java.util.*;

public class FuckedDetect extends Module
{
    private final /* synthetic */ Value<Integer> blue;
    private final /* synthetic */ Value<Integer> green;
    private final /* synthetic */ Value<Integer> red;
    private final /* synthetic */ Value<Boolean> drawOwn;
    public /* synthetic */ Set<EntityPlayer> fuckedPlayers;
    private final /* synthetic */ Value<Integer> distance;
    private final /* synthetic */ Value<String> renderMode;
    private final /* synthetic */ Value<Integer> alpha;
    private final /* synthetic */ Value<Boolean> drawFriends;
    
    @Override
    public void onWorldRender(final RenderEvent llllllllllllllllllIIIIIlIlllIlII) {
        for (final EntityPlayer llllllllllllllllllIIIIIlIlllIllI : this.fuckedPlayers) {
            this.drawBlock(new BlockPos(llllllllllllllllllIIIIIlIlllIllI.posX, llllllllllllllllllIIIIIlIlllIllI.posY, llllllllllllllllllIIIIIlIlllIllI.posZ), this.red.getValue(), this.green.getValue(), this.blue.getValue());
        }
    }
    
    public FuckedDetect() {
        super("FuckedDetector", "Detects when people are fucked", 0, Category.COMBAT, true);
        this.red = this.register(new Value<Integer>("Red", this, 255, 0, 255));
        this.green = this.register(new Value<Integer>("Green", this, 255, 0, 255));
        this.blue = this.register(new Value<Integer>("Blue", this, 255, 0, 255));
        this.alpha = this.register(new Value<Integer>("Alpha", this, 70, 0, 255));
        this.distance = this.register(new Value<Integer>("Draw Distance", this, 20, 0, 30));
        this.drawFriends = this.register(new Value<Boolean>("Draw Friends", this, false));
        this.drawOwn = this.register(new Value<Boolean>("Draw Own", this, false));
        this.renderMode = this.register(new Value<String>("RenderMode", this, "Solid", new ArrayList<String>(Arrays.asList("Solid", "Flat", "Outline", "Full"))));
    }
    
    public static Vec3d interpolateEntity(final Entity llllllllllllllllllIIIIIlIlIlIlIl, final float llllllllllllllllllIIIIIlIlIlIlII) {
        return new Vec3d(llllllllllllllllllIIIIIlIlIlIlIl.lastTickPosX + (llllllllllllllllllIIIIIlIlIlIlIl.posX - llllllllllllllllllIIIIIlIlIlIlIl.lastTickPosX) * llllllllllllllllllIIIIIlIlIlIlII, llllllllllllllllllIIIIIlIlIlIlIl.lastTickPosY + (llllllllllllllllllIIIIIlIlIlIlIl.posY - llllllllllllllllllIIIIIlIlIlIlIl.lastTickPosY) * llllllllllllllllllIIIIIlIlIlIlII, llllllllllllllllllIIIIIlIlIlIlIl.lastTickPosZ + (llllllllllllllllllIIIIIlIlIlIlIl.posZ - llllllllllllllllllIIIIIlIlIlIlIl.lastTickPosZ) * llllllllllllllllllIIIIIlIlIlIlII);
    }
    
    @Override
    public void onUpdate() {
        if (FuckedDetect.mc.player.isDead || FuckedDetect.mc.player == null || !this.isToggled()) {
            return;
        }
        this.getList();
    }
    
    private void drawBlock(final BlockPos llllllllllllllllllIIIIIlIllIIlII, final int llllllllllllllllllIIIIIlIlIllllI, final int llllllllllllllllllIIIIIlIlIlllIl, final int llllllllllllllllllIIIIIlIllIIIIl) {
        if (this.renderMode.getValue().equalsIgnoreCase("Solid")) {
            XuluTessellator.prepare(7);
            XuluTessellator.drawBox(llllllllllllllllllIIIIIlIllIIlII, llllllllllllllllllIIIIIlIlIllllI, llllllllllllllllllIIIIIlIlIlllIl, llllllllllllllllllIIIIIlIllIIIIl, this.alpha.getValue(), 63);
            XuluTessellator.release();
        }
        else if (this.renderMode.getValue().equalsIgnoreCase("Flat")) {
            XuluTessellator.prepare(7);
            XuluTessellator.drawBox(llllllllllllllllllIIIIIlIllIIlII, llllllllllllllllllIIIIIlIlIllllI, llllllllllllllllllIIIIIlIlIlllIl, llllllllllllllllllIIIIIlIllIIIIl, this.alpha.getValue(), 1);
            XuluTessellator.release();
        }
        else if (this.renderMode.getValue().equalsIgnoreCase("Outline")) {
            final IBlockState llllllllllllllllllIIIIIlIllIlIIl = FuckedDetect.mc.world.getBlockState(llllllllllllllllllIIIIIlIllIIlII);
            final Vec3d llllllllllllllllllIIIIIlIllIlIII = MathUtil.interpolateEntity((Entity)FuckedDetect.mc.player, FuckedDetect.mc.getRenderPartialTicks());
            XuluTessellator.drawBoundingBox(llllllllllllllllllIIIIIlIllIlIIl.getSelectedBoundingBox((World)FuckedDetect.mc.world, llllllllllllllllllIIIIIlIllIIlII).grow(0.0020000000949949026).offset(-llllllllllllllllllIIIIIlIllIlIII.x, -llllllllllllllllllIIIIIlIllIlIII.y, -llllllllllllllllllIIIIIlIllIlIII.z), 1.5f, llllllllllllllllllIIIIIlIlIllllI, llllllllllllllllllIIIIIlIlIlllIl, llllllllllllllllllIIIIIlIllIIIIl, this.alpha.getValue());
        }
        else if (this.renderMode.getValue().equalsIgnoreCase("Full")) {
            final IBlockState llllllllllllllllllIIIIIlIllIIlll = FuckedDetect.mc.world.getBlockState(llllllllllllllllllIIIIIlIllIIlII);
            final Vec3d llllllllllllllllllIIIIIlIllIIllI = MathUtil.interpolateEntity((Entity)FuckedDetect.mc.player, FuckedDetect.mc.getRenderPartialTicks());
            XuluTessellator.drawFullBox(llllllllllllllllllIIIIIlIllIIlll.getSelectedBoundingBox((World)FuckedDetect.mc.world, llllllllllllllllllIIIIIlIllIIlII).grow(0.0020000000949949026).offset(-llllllllllllllllllIIIIIlIllIIllI.x, -llllllllllllllllllIIIIIlIllIIllI.y, -llllllllllllllllllIIIIIlIllIIllI.z), llllllllllllllllllIIIIIlIllIIlII, 1.5f, llllllllllllllllllIIIIIlIlIllllI, llllllllllllllllllIIIIIlIlIlllIl, llllllllllllllllllIIIIIlIllIIIIl, this.alpha.getValue(), 255);
        }
    }
    
    private boolean canPlaceCrystal(final BlockPos llllllllllllllllllIIIIIlIlIIllII) {
        final BlockPos llllllllllllllllllIIIIIlIlIIlllI = llllllllllllllllllIIIIIlIlIIllII.add(0, 1, 0);
        final BlockPos llllllllllllllllllIIIIIlIlIIllIl = llllllllllllllllllIIIIIlIlIIllII.add(0, 2, 0);
        return (FuckedDetect.mc.world.getBlockState(llllllllllllllllllIIIIIlIlIIllII).getBlock() == Blocks.BEDROCK || FuckedDetect.mc.world.getBlockState(llllllllllllllllllIIIIIlIlIIllII).getBlock() == Blocks.OBSIDIAN) && FuckedDetect.mc.world.getBlockState(llllllllllllllllllIIIIIlIlIIlllI).getBlock() == Blocks.AIR && FuckedDetect.mc.world.getBlockState(llllllllllllllllllIIIIIlIlIIllIl).getBlock() == Blocks.AIR && FuckedDetect.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(llllllllllllllllllIIIIIlIlIIlllI)).isEmpty() && FuckedDetect.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(llllllllllllllllllIIIIIlIlIIllIl)).isEmpty();
    }
    
    public Set<EntityPlayer> getList() {
        this.fuckedPlayers.clear();
        for (final EntityPlayer llllllllllllllllllIIIIIlIIllllIl : FuckedDetect.mc.world.playerEntities) {
            if (EntityUtil.isLiving((Entity)llllllllllllllllllIIIIIlIIllllIl)) {
                if (llllllllllllllllllIIIIIlIIllllIl.getHealth() <= 0.0f) {
                    continue;
                }
                if (!this.checkHole(llllllllllllllllllIIIIIlIIllllIl)) {
                    continue;
                }
                if (!this.drawOwn.getValue() && llllllllllllllllllIIIIIlIIllllIl.getName() == FuckedDetect.mc.player.getName()) {
                    continue;
                }
                if (!this.drawFriends.getValue() && Friends.isFriend(llllllllllllllllllIIIIIlIIllllIl.getName())) {
                    continue;
                }
                if (FuckedDetect.mc.player.getDistance((Entity)llllllllllllllllllIIIIIlIIllllIl) > this.distance.getValue()) {
                    continue;
                }
                this.fuckedPlayers.add(llllllllllllllllllIIIIIlIIllllIl);
            }
        }
        return this.fuckedPlayers;
    }
    
    public Boolean checkHole(final EntityPlayer llllllllllllllllllIIIIIlIlIIIlIl) {
        final BlockPos llllllllllllllllllIIIIIlIlIIIlII = new BlockPos(llllllllllllllllllIIIIIlIlIIIlIl.posX, llllllllllllllllllIIIIIlIlIIIlIl.posY - 1.0, llllllllllllllllllIIIIIlIlIIIlIl.posZ);
        if (FuckedDetect.mc.world.getBlockState(llllllllllllllllllIIIIIlIlIIIlII).getBlock() == Blocks.AIR) {
            return false;
        }
        if (this.canPlaceCrystal(llllllllllllllllllIIIIIlIlIIIlII.south()) || (this.canPlaceCrystal(llllllllllllllllllIIIIIlIlIIIlII.south().south()) && FuckedDetect.mc.world.getBlockState(llllllllllllllllllIIIIIlIlIIIlII.add(0, 1, 1)).getBlock() == Blocks.AIR)) {
            return true;
        }
        if (this.canPlaceCrystal(llllllllllllllllllIIIIIlIlIIIlII.east()) || (this.canPlaceCrystal(llllllllllllllllllIIIIIlIlIIIlII.east().east()) && FuckedDetect.mc.world.getBlockState(llllllllllllllllllIIIIIlIlIIIlII.add(1, 1, 0)).getBlock() == Blocks.AIR)) {
            return true;
        }
        if (this.canPlaceCrystal(llllllllllllllllllIIIIIlIlIIIlII.west()) || (this.canPlaceCrystal(llllllllllllllllllIIIIIlIlIIIlII.west().west()) && FuckedDetect.mc.world.getBlockState(llllllllllllllllllIIIIIlIlIIIlII.add(-1, 1, 0)).getBlock() == Blocks.AIR)) {
            return true;
        }
        return this.canPlaceCrystal(llllllllllllllllllIIIIIlIlIIIlII.north()) || (this.canPlaceCrystal(llllllllllllllllllIIIIIlIlIIIlII.north().north()) && FuckedDetect.mc.world.getBlockState(llllllllllllllllllIIIIIlIlIIIlII.add(0, 1, -1)).getBlock() == Blocks.AIR);
    }
    
    @Override
    public void onEnable() {
        this.fuckedPlayers = new HashSet<EntityPlayer>();
    }
}
