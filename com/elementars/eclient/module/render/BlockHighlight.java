package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.client.*;
import net.minecraft.block.material.*;
import net.minecraft.entity.*;
import com.elementars.eclient.util.*;
import net.minecraft.block.state.*;
import net.minecraft.util.math.*;
import net.minecraftforge.client.event.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.common.*;
import com.elementars.eclient.module.*;

public class BlockHighlight extends Module
{
    private final /* synthetic */ Value<Integer> alphaF;
    private final /* synthetic */ Value<Boolean> rainbow;
    private final /* synthetic */ Value<Integer> green;
    private static /* synthetic */ BlockPos position;
    private final /* synthetic */ Value<Integer> red;
    private final /* synthetic */ Value<Float> width;
    private final /* synthetic */ Value<Integer> alpha;
    private final /* synthetic */ Value<String> mode;
    private final /* synthetic */ Value<Integer> blue;
    
    @Override
    public void onWorldRender(final RenderEvent llllllllllllllllIllllIIllIIlIIII) {
        final Minecraft llllllllllllllllIllllIIllIIIllll = Minecraft.getMinecraft();
        final RayTraceResult llllllllllllllllIllllIIllIIIlllI = llllllllllllllllIllllIIllIIIllll.objectMouseOver;
        if (llllllllllllllllIllllIIllIIIlllI != null && llllllllllllllllIllllIIllIIIlllI.typeOfHit == RayTraceResult.Type.BLOCK) {
            final BlockPos llllllllllllllllIllllIIllIIlIIll = llllllllllllllllIllllIIllIIIlllI.getBlockPos();
            final IBlockState llllllllllllllllIllllIIllIIlIIlI = llllllllllllllllIllllIIllIIIllll.world.getBlockState(llllllllllllllllIllllIIllIIlIIll);
            if (llllllllllllllllIllllIIllIIlIIlI.getMaterial() != Material.AIR && llllllllllllllllIllllIIllIIIllll.world.getWorldBorder().contains(llllllllllllllllIllllIIllIIlIIll)) {
                final Vec3d llllllllllllllllIllllIIllIIlIlll = MathUtil.interpolateEntity((Entity)llllllllllllllllIllllIIllIIIllll.player, llllllllllllllllIllllIIllIIIllll.getRenderPartialTicks());
                int llllllllllllllllIllllIIllIIlIllI = this.red.getValue();
                int llllllllllllllllIllllIIllIIlIlIl = this.green.getValue();
                int llllllllllllllllIllllIIllIIlIlII = this.blue.getValue();
                if (this.rainbow.getValue()) {
                    llllllllllllllllIllllIIllIIlIllI = RainbowUtils.r;
                    llllllllllllllllIllllIIllIIlIlIl = RainbowUtils.g;
                    llllllllllllllllIllllIIllIIlIlII = RainbowUtils.b;
                }
                if (this.mode.getValue().equalsIgnoreCase("Solid")) {
                    XuluTessellator.prepare(7);
                    XuluTessellator.drawBox(llllllllllllllllIllllIIllIIlIIll, llllllllllllllllIllllIIllIIlIllI, llllllllllllllllIllllIIllIIlIlIl, llllllllllllllllIllllIIllIIlIlII, this.alpha.getValue(), 63);
                    XuluTessellator.release();
                }
                else if (this.mode.getValue().equalsIgnoreCase("Outline")) {
                    XuluTessellator.drawBoundingBox(llllllllllllllllIllllIIllIIlIIlI.getSelectedBoundingBox((World)llllllllllllllllIllllIIllIIIllll.world, llllllllllllllllIllllIIllIIlIIll).grow(0.0020000000949949026).offset(-llllllllllllllllIllllIIllIIlIlll.x, -llllllllllllllllIllllIIllIIlIlll.y, -llllllllllllllllIllllIIllIIlIlll.z), this.width.getValue(), llllllllllllllllIllllIIllIIlIllI, llllllllllllllllIllllIIllIIlIlIl, llllllllllllllllIllllIIllIIlIlII, this.alpha.getValue());
                }
                else if (this.mode.getValue().equalsIgnoreCase("Full")) {
                    XuluTessellator.drawFullBox(llllllllllllllllIllllIIllIIlIIlI.getSelectedBoundingBox((World)llllllllllllllllIllllIIllIIIllll.world, llllllllllllllllIllllIIllIIlIIll).grow(0.0020000000949949026).offset(-llllllllllllllllIllllIIllIIlIlll.x, -llllllllllllllllIllllIIllIIlIlll.y, -llllllllllllllllIllllIIllIIlIlll.z), llllllllllllllllIllllIIllIIlIIll, this.width.getValue(), llllllllllllllllIllllIIllIIlIllI, llllllllllllllllIllllIIllIIlIlIl, llllllllllllllllIllllIIllIIlIlII, this.alpha.getValue(), this.alphaF.getValue());
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onDrawBlockHighlight(final DrawBlockHighlightEvent llllllllllllllllIllllIIllIIIIIIl) {
        if (BlockHighlight.mc.player == null || BlockHighlight.mc.world == null || (!BlockHighlight.mc.playerController.getCurrentGameType().equals((Object)GameType.SURVIVAL) && !BlockHighlight.mc.playerController.getCurrentGameType().equals((Object)GameType.CREATIVE))) {
            return;
        }
        llllllllllllllllIllllIIllIIIIIIl.setCanceled(true);
    }
    
    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        BlockHighlight.position = null;
    }
    
    public BlockHighlight() {
        super("BlockHighlight", "Highlights block you're looking at", 0, Category.RENDER, true);
        this.mode = this.register(new Value<String>("Mode", this, "Outline", new String[] { "Solid", "Outline", "Full" }));
        this.rainbow = this.register(new Value<Boolean>("Rainbow", this, false));
        this.red = this.register(new Value<Integer>("Red", this, 255, 0, 255));
        this.green = this.register(new Value<Integer>("Green", this, 255, 0, 255));
        this.blue = this.register(new Value<Integer>("Blue", this, 255, 0, 255));
        this.alpha = this.register(new Value<Integer>("Alpha", this, 255, 0, 255));
        this.alphaF = this.register(new Value<Integer>("Alpha Full", this, 255, 0, 255));
        this.width = this.register(new Value<Float>("Width", this, 1.0f, 1.0f, 10.0f));
    }
}
