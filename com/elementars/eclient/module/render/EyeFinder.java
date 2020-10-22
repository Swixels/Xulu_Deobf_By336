package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import com.elementars.eclient.module.*;
import net.minecraft.entity.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.event.events.*;
import java.util.function.*;

public class EyeFinder extends Module
{
    private final /* synthetic */ Value<Boolean> players;
    private final /* synthetic */ Value<Boolean> mobs;
    private final /* synthetic */ Value<Boolean> animals;
    
    public EyeFinder() {
        super("EyeFinder", "Draws line at an entity's eyes", 0, Category.RENDER, true);
        this.players = this.register(new Value<Boolean>("Players", this, true));
        this.mobs = this.register(new Value<Boolean>("Mobs", this, false));
        this.animals = this.register(new Value<Boolean>("Animals", this, false));
    }
    
    private void drawLine(final EntityLivingBase llllllllllllllllllIIIIIIllIlIlll) {
        final RayTraceResult llllllllllllllllllIIIIIIllIlIllI = llllllllllllllllllIIIIIIllIlIlll.rayTrace(6.0, Minecraft.getMinecraft().getRenderPartialTicks());
        if (llllllllllllllllllIIIIIIllIlIllI == null) {
            return;
        }
        final Vec3d llllllllllllllllllIIIIIIllIlIlIl = llllllllllllllllllIIIIIIllIlIlll.getPositionEyes(Minecraft.getMinecraft().getRenderPartialTicks());
        GlStateManager.enableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        final double llllllllllllllllllIIIIIIllIlIlII = llllllllllllllllllIIIIIIllIlIlIl.x - EyeFinder.mc.getRenderManager().renderPosX;
        final double llllllllllllllllllIIIIIIllIlIIll = llllllllllllllllllIIIIIIllIlIlIl.y - EyeFinder.mc.getRenderManager().renderPosY;
        final double llllllllllllllllllIIIIIIllIlIIlI = llllllllllllllllllIIIIIIllIlIlIl.z - EyeFinder.mc.getRenderManager().renderPosZ;
        final double llllllllllllllllllIIIIIIllIlIIIl = llllllllllllllllllIIIIIIllIlIllI.hitVec.x - EyeFinder.mc.getRenderManager().renderPosX;
        final double llllllllllllllllllIIIIIIllIlIIII = llllllllllllllllllIIIIIIllIlIllI.hitVec.y - EyeFinder.mc.getRenderManager().renderPosY;
        final double llllllllllllllllllIIIIIIllIIllll = llllllllllllllllllIIIIIIllIlIllI.hitVec.z - EyeFinder.mc.getRenderManager().renderPosZ;
        GL11.glColor4f(0.2f, 0.1f, 0.3f, 0.8f);
        GlStateManager.glLineWidth(1.5f);
        GL11.glBegin(1);
        GL11.glVertex3d(llllllllllllllllllIIIIIIllIlIlII, llllllllllllllllllIIIIIIllIlIIll, llllllllllllllllllIIIIIIllIlIIlI);
        GL11.glVertex3d(llllllllllllllllllIIIIIIllIlIIIl, llllllllllllllllllIIIIIIllIlIIII, llllllllllllllllllIIIIIIllIIllll);
        GL11.glVertex3d(llllllllllllllllllIIIIIIllIlIIIl, llllllllllllllllllIIIIIIllIlIIII, llllllllllllllllllIIIIIIllIIllll);
        GL11.glVertex3d(llllllllllllllllllIIIIIIllIlIIIl, llllllllllllllllllIIIIIIllIlIIII, llllllllllllllllllIIIIIIllIIllll);
        GL11.glEnd();
        if (llllllllllllllllllIIIIIIllIlIllI.typeOfHit == RayTraceResult.Type.BLOCK) {
            XuluTessellator.prepare(7);
            GL11.glEnable(2929);
            final BlockPos llllllllllllllllllIIIIIIllIlllII = llllllllllllllllllIIIIIIllIlIllI.getBlockPos();
            final float llllllllllllllllllIIIIIIllIllIll = llllllllllllllllllIIIIIIllIlllII.x - 0.01f;
            final float llllllllllllllllllIIIIIIllIllIlI = llllllllllllllllllIIIIIIllIlllII.y - 0.01f;
            final float llllllllllllllllllIIIIIIllIllIIl = llllllllllllllllllIIIIIIllIlllII.z - 0.01f;
            XuluTessellator.drawBox(XuluTessellator.getBufferBuilder(), llllllllllllllllllIIIIIIllIllIll, llllllllllllllllllIIIIIIllIllIlI, llllllllllllllllllIIIIIIllIllIIl, 1.01f, 1.01f, 1.01f, 51, 25, 73, 200, 63);
            XuluTessellator.release();
        }
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
    }
    
    @Override
    public void onWorldRender(final RenderEvent llllllllllllllllllIIIIIIlllIlIll) {
        final boolean b;
        EyeFinder.mc.world.loadedEntityList.stream().filter(EntityUtil::isLiving).filter(llllllllllllllllllIIIIIIlIllIlII -> EyeFinder.mc.player != llllllllllllllllllIIIIIIlIllIlII).map(llllllllllllllllllIIIIIIlIllIllI -> llllllllllllllllllIIIIIIlIllIllI).filter(llllllllllllllllllIIIIIIlIlllIIl -> !llllllllllllllllllIIIIIIlIlllIIl.isDead).filter(llllllllllllllllllIIIIIIlIllllII -> {
            if (!this.players.getValue() || !(llllllllllllllllllIIIIIIlIllllII instanceof EntityPlayer)) {
                if (!(EntityUtil.isPassive(llllllllllllllllllIIIIIIlIllllII) ? this.animals.getValue() : this.mobs.getValue())) {
                    return b;
                }
            }
            return b;
        }).forEach(this::drawLine);
    }
}
