package com.elementars.eclient.module.render;

import io.netty.util.internal.*;
import dev.xulu.settings.*;
import net.minecraft.client.renderer.*;
import net.minecraft.init.*;
import com.elementars.eclient.util.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;
import net.minecraft.block.state.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.module.*;
import java.util.*;
import com.elementars.eclient.event.events.*;

public class BreakESP extends Module
{
    private /* synthetic */ ConcurrentSet<BlockPos> test;
    private final /* synthetic */ Value<Integer> alpha;
    private final /* synthetic */ Value<Boolean> ignoreSelf;
    private final /* synthetic */ Value<Integer> alphaF;
    public /* synthetic */ ConcurrentSet<BlockPos> breaking;
    private final /* synthetic */ Value<Integer> red;
    private final /* synthetic */ Value<Boolean> fade;
    private final /* synthetic */ Value<Integer> blue;
    private final /* synthetic */ Value<String> mode;
    private final /* synthetic */ Value<Integer> green;
    private final /* synthetic */ Value<Boolean> onlyObby;
    private /* synthetic */ Map<Integer, Integer> alphaMap;
    
    public BreakESP() {
        super("BreakESP", "Highlights blocks being broken", 0, Category.RENDER, true);
        this.breaking = (ConcurrentSet<BlockPos>)new ConcurrentSet();
        this.test = (ConcurrentSet<BlockPos>)new ConcurrentSet();
        this.alphaMap = new HashMap<Integer, Integer>();
        this.mode = this.register(new Value<String>("Mode", this, "Solid", new ArrayList<String>(Arrays.asList("Solid", "Outline", "Full"))));
        this.ignoreSelf = this.register(new Value<Boolean>("Ignore Self", this, true));
        this.onlyObby = this.register(new Value<Boolean>("Only Obsidian", this, true));
        this.fade = this.register(new Value<Boolean>("Fade Progress", this, true));
        this.red = this.register(new Value<Integer>("Red", this, 255, 0, 255));
        this.green = this.register(new Value<Integer>("Green", this, 0, 0, 255));
        this.blue = this.register(new Value<Integer>("Blue", this, 0, 0, 255));
        this.alpha = this.register(new Value<Integer>("Alpha", this, 70, 0, 255));
        this.alphaF = this.register(new Value<Integer>("Full Alpha", this, 100, 0, 255));
        this.alphaMap.put(0, 28);
        this.alphaMap.put(1, 56);
        this.alphaMap.put(2, 84);
        this.alphaMap.put(3, 112);
        this.alphaMap.put(4, 140);
        this.alphaMap.put(5, 168);
        this.alphaMap.put(6, 196);
        this.alphaMap.put(7, 224);
        this.alphaMap.put(8, 255);
        this.alphaMap.put(9, 255);
    }
    
    @Override
    public void onWorldRender(final RenderEvent llllllllllllllllllllIllIIlllIIll) {
        int llllllllllllllllllllIllIIllIIlII;
        IBlockState llllllllllllllllllllIllIIllIlIll;
        Vec3d llllllllllllllllllllIllIIllIlIlI;
        IBlockState llllllllllllllllllllIllIIllIlIIl;
        Vec3d llllllllllllllllllllIllIIllIlIII;
        BreakESP.mc.renderGlobal.damagedBlocks.forEach((llllllllllllllllllllIllIIllIIIlI, llllllllllllllllllllIllIIllIIIIl) -> {
            if (llllllllllllllllllllIllIIllIIIIl != null) {
                if (!this.ignoreSelf.getValue() || BreakESP.mc.world.getEntityByID((int)llllllllllllllllllllIllIIllIIIlI) != BreakESP.mc.player) {
                    if (!this.onlyObby.getValue() || BreakESP.mc.world.getBlockState(llllllllllllllllllllIllIIllIIIIl.getPosition()).getBlock() == Blocks.OBSIDIAN) {
                        llllllllllllllllllllIllIIllIIlII = (this.fade.getValue() ? this.alphaMap.get(llllllllllllllllllllIllIIllIIIIl.getPartialBlockDamage()) : ((int)this.alpha.getValue()));
                        if (this.mode.getValue().equalsIgnoreCase("Solid")) {
                            XuluTessellator.prepare(7);
                            XuluTessellator.drawBox(llllllllllllllllllllIllIIllIIIIl.getPosition(), this.red.getValue(), this.green.getValue(), this.blue.getValue(), llllllllllllllllllllIllIIllIIlII, 63);
                            XuluTessellator.release();
                        }
                        else if (this.mode.getValue().equalsIgnoreCase("Full")) {
                            llllllllllllllllllllIllIIllIlIll = BreakESP.mc.world.getBlockState(llllllllllllllllllllIllIIllIIIIl.getPosition());
                            llllllllllllllllllllIllIIllIlIlI = MathUtil.interpolateEntity((Entity)BreakESP.mc.player, BreakESP.mc.getRenderPartialTicks());
                            XuluTessellator.drawFullBox(llllllllllllllllllllIllIIllIlIll.getSelectedBoundingBox((World)BreakESP.mc.world, llllllllllllllllllllIllIIllIIIIl.getPosition()).grow(0.0020000000949949026).offset(-llllllllllllllllllllIllIIllIlIlI.x, -llllllllllllllllllllIllIIllIlIlI.y, -llllllllllllllllllllIllIIllIlIlI.z), llllllllllllllllllllIllIIllIIIIl.getPosition(), 1.5f, this.red.getValue(), this.green.getValue(), this.blue.getValue(), llllllllllllllllllllIllIIllIIlII, this.alphaF.getValue());
                        }
                        else if (this.mode.getValue().equalsIgnoreCase("Outline")) {
                            llllllllllllllllllllIllIIllIlIIl = BreakESP.mc.world.getBlockState(llllllllllllllllllllIllIIllIIIIl.getPosition());
                            llllllllllllllllllllIllIIllIlIII = MathUtil.interpolateEntity((Entity)BreakESP.mc.player, BreakESP.mc.getRenderPartialTicks());
                            XuluTessellator.drawBoundingBox(llllllllllllllllllllIllIIllIlIIl.getSelectedBoundingBox((World)BreakESP.mc.world, llllllllllllllllllllIllIIllIIIIl.getPosition()).grow(0.0020000000949949026).offset(-llllllllllllllllllllIllIIllIlIII.x, -llllllllllllllllllllIllIIllIlIII.y, -llllllllllllllllllllIllIIllIlIII.z), 1.5f, this.red.getValue(), this.green.getValue(), this.blue.getValue(), llllllllllllllllllllIllIIllIIlII);
                        }
                        else {
                            XuluTessellator.prepare(7);
                            XuluTessellator.drawBox(llllllllllllllllllllIllIIllIIIIl.getPosition(), this.red.getValue(), this.green.getValue(), this.blue.getValue(), llllllllllllllllllllIllIIllIIlII, 63);
                            XuluTessellator.release();
                        }
                    }
                }
            }
        });
    }
}
