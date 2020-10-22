package com.elementars.eclient.module.combat;

import dev.xulu.settings.*;
import io.netty.util.internal.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import com.elementars.eclient.command.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.module.*;
import java.util.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import com.elementars.eclient.util.*;

public class BreakAlert extends Module
{
    private final /* synthetic */ Value<Boolean> watermark;
    private /* synthetic */ ConcurrentSet<BlockPos> test;
    private final /* synthetic */ Value<Boolean> ignoreSelf;
    public /* synthetic */ BlockPos[] xd;
    /* synthetic */ boolean testy;
    private final /* synthetic */ Value<String> mode;
    /* synthetic */ int delay;
    private /* synthetic */ ConcurrentSet<BlockPos> breaking;
    private final /* synthetic */ Value<String> color;
    
    @Override
    public void onUpdate() {
        if (this.delay > 0) {
            --this.delay;
        }
        this.test.clear();
        if (BreakAlert.mc.world == null) {
            return;
        }
        RayTraceResult llllllllllllllllIlIIllIlIIlIlIII;
        RayTraceResult llllllllllllllllIlIIllIlIIlIIlll;
        BreakAlert.mc.world.playerEntities.forEach(llllllllllllllllIlIIllIlIIlIIlIl -> {
            if (this.ignoreSelf.getValue()) {
                if (!llllllllllllllllIlIIllIlIIlIIlIl.getName().equalsIgnoreCase(BreakAlert.mc.player.getName()) && llllllllllllllllIlIIllIlIIlIIlIl.isSwingInProgress && llllllllllllllllIlIIllIlIIlIIlIl.getHeldItemMainhand().getItem() == Items.DIAMOND_PICKAXE) {
                    llllllllllllllllIlIIllIlIIlIlIII = llllllllllllllllIlIIllIlIIlIIlIl.rayTrace(5.0, BreakAlert.mc.getRenderPartialTicks());
                    if (llllllllllllllllIlIIllIlIIlIlIII != null && llllllllllllllllIlIIllIlIIlIlIII.typeOfHit == RayTraceResult.Type.BLOCK && BreakAlert.mc.world.getBlockState(llllllllllllllllIlIIllIlIIlIlIII.getBlockPos()).getBlock() != Blocks.BEDROCK) {
                        this.test.add((Object)llllllllllllllllIlIIllIlIIlIlIII.getBlockPos());
                    }
                }
            }
            else if (llllllllllllllllIlIIllIlIIlIIlIl.isSwingInProgress && llllllllllllllllIlIIllIlIIlIIlIl.getHeldItemMainhand().getItem() == Items.DIAMOND_PICKAXE) {
                llllllllllllllllIlIIllIlIIlIIlll = llllllllllllllllIlIIllIlIIlIIlIl.rayTrace(5.0, BreakAlert.mc.getRenderPartialTicks());
                if (llllllllllllllllIlIIllIlIIlIIlll != null && llllllllllllllllIlIIllIlIIlIIlll.typeOfHit == RayTraceResult.Type.BLOCK && BreakAlert.mc.world.getBlockState(llllllllllllllllIlIIllIlIIlIIlll.getBlockPos()).getBlock() != Blocks.BEDROCK) {
                    this.test.add((Object)llllllllllllllllIlIIllIlIIlIIlll.getBlockPos());
                }
            }
            return;
        });
        this.breaking.removeIf(llllllllllllllllIlIIllIlIIlIlllI -> !this.test.contains((Object)llllllllllllllllIlIIllIlIIlIlllI));
        this.breaking.addAll((Collection)this.test);
        this.testy = false;
        final Exception llllllllllllllllIlIIllIlIIlllIIl = (Object)this.xd;
        final int llllllllllllllllIlIIllIlIIlllIII = llllllllllllllllIlIIllIlIIlllIIl.length;
        for (float llllllllllllllllIlIIllIlIIllIlll = 0; llllllllllllllllIlIIllIlIIllIlll < llllllllllllllllIlIIllIlIIlllIII; ++llllllllllllllllIlIIllIlIIllIlll) {
            final BlockPos llllllllllllllllIlIIllIlIIllllII = llllllllllllllllIlIIllIlIIlllIIl[llllllllllllllllIlIIllIlIIllIlll];
            final BlockPos llllllllllllllllIlIIllIlIIllllIl = new BlockPos(BreakAlert.mc.player.posX, BreakAlert.mc.player.posY, BreakAlert.mc.player.posZ).add(llllllllllllllllIlIIllIlIIllllII.x, llllllllllllllllIlIIllIlIIllllII.y, llllllllllllllllIlIIllIlIIllllII.z);
            if (!this.breaking.isEmpty() && this.breaking.contains((Object)llllllllllllllllIlIIllIlIIllllIl)) {
                this.testy = true;
            }
        }
        if (this.testy && this.mode.getValue().equalsIgnoreCase("Chat")) {
            if (this.delay == 0) {
                if (this.watermark.getValue()) {
                    Command.sendChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.color.getValue())).append("Your feet are being mined!")));
                }
                else {
                    Command.sendRawChatMessage(String.valueOf(new StringBuilder().append(ColorTextUtils.getColor(this.color.getValue())).append("Your feet are being mined!")));
                }
            }
            this.delay = 100;
        }
    }
    
    public BreakAlert() {
        super("BreakAlert", "Alerts you when your feet blocks are being broken", 0, Category.COMBAT, true);
        this.mode = this.register(new Value<String>("Mode", this, "Chat", new ArrayList<String>(Arrays.asList("Chat", "Text", "Dot"))));
        this.ignoreSelf = this.register(new Value<Boolean>("Ignore Self", this, true));
        this.watermark = this.register(new Value<Boolean>("Watermark", this, true));
        this.color = this.register(new Value<String>("Color", this, "White", ColorTextUtils.colors));
        this.breaking = (ConcurrentSet<BlockPos>)new ConcurrentSet();
        this.test = (ConcurrentSet<BlockPos>)new ConcurrentSet();
        this.xd = new BlockPos[] { new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1) };
    }
    
    @Override
    public void onRender() {
        if (this.testy && this.mode.getValue().equalsIgnoreCase("dot")) {
            GlStateManager.pushMatrix();
            Gui.drawRect(BreakAlert.mc.displayWidth / 4 - 3, BreakAlert.mc.displayHeight / 4 - 3, BreakAlert.mc.displayWidth / 4 + 4, BreakAlert.mc.displayHeight / 4 + 4, new Color(255, 0, 0, 255).getRGB());
            GlStateManager.popMatrix();
        }
        else if (this.testy && this.mode.getValue().equalsIgnoreCase("Text")) {
            GlStateManager.pushMatrix();
            BreakAlert.mc.fontRenderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append(ColorTextUtils.getColor(this.color.getValue()).substring(1)).append("Your feet are being mined!")), (float)(BreakAlert.mc.displayWidth / 4 - BreakAlert.mc.fontRenderer.getStringWidth("Your feet are being mined!") / 2), (float)(BreakAlert.mc.displayHeight / 4 - BreakAlert.mc.fontRenderer.FONT_HEIGHT / 2), ColorUtils.Colors.RED);
            GlStateManager.popMatrix();
        }
    }
}
