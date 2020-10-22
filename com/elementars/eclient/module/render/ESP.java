package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.event.events.*;
import com.elementars.eclient.util.*;
import java.util.function.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.math.*;

public class ESP extends Module
{
    private final /* synthetic */ Value<Boolean> players;
    private final /* synthetic */ Value<Boolean> mobs;
    private final /* synthetic */ Value<Boolean> animals;
    
    public ESP() {
        super("ESP", "Highlights entities", 0, Category.RENDER, true);
        this.players = this.register(new Value<Boolean>("Players", this, true));
        this.animals = this.register(new Value<Boolean>("Animals", this, false));
        this.mobs = this.register(new Value<Boolean>("Mobs", this, false));
    }
    
    @Override
    public void onWorldRender(final RenderEvent lIIIIIIlIlIIlIl) {
        if (Wrapper.getMinecraft().getRenderManager().options == null) {
            return;
        }
        final boolean lIIIIIIlIlIIIll = Wrapper.getMinecraft().getRenderManager().options.thirdPersonView == 2;
        final float lIIIIIIlIlIIIlI = Wrapper.getMinecraft().getRenderManager().playerViewY;
        final boolean b;
        final Vec3d lIIIIIIlIIlIlII;
        final float n;
        final boolean b2;
        ESP.mc.world.loadedEntityList.stream().filter(EntityUtil::isLiving).filter(lIIIIIIlIIIIIII -> ESP.mc.player != lIIIIIIlIIIIIII).map(lIIIIIIlIIIIIll -> lIIIIIIlIIIIIll).filter(lIIIIIIlIIIIllI -> !lIIIIIIlIIIIllI.isDead).filter(lIIIIIIlIIIlIIl -> {
            if (!this.players.getValue() || !(lIIIIIIlIIIlIIl instanceof EntityPlayer)) {
                if (!(EntityUtil.isPassive(lIIIIIIlIIIlIIl) ? this.animals.getValue() : this.mobs.getValue())) {
                    return b;
                }
            }
            return b;
        }).forEach(lIIIIIIlIIlIlIl -> {
            GlStateManager.pushMatrix();
            lIIIIIIlIIlIlII = EntityUtil.getInterpolatedPos((Entity)lIIIIIIlIIlIlIl, lIIIIIIlIlIIlIl.getPartialTicks());
            GlStateManager.translate(lIIIIIIlIIlIlII.x - ESP.mc.getRenderManager().renderPosX, lIIIIIIlIIlIlII.y - ESP.mc.getRenderManager().renderPosY, lIIIIIIlIIlIlII.z - ESP.mc.getRenderManager().renderPosZ);
            GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(-n, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate((float)(b2 ? -1 : 1), 1.0f, 0.0f, 0.0f);
            GlStateManager.disableLighting();
            GlStateManager.depthMask(false);
            GlStateManager.disableDepth();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            if (lIIIIIIlIIlIlIl instanceof EntityPlayer) {
                GL11.glColor3f(1.0f, 1.0f, 1.0f);
            }
            else if (EntityUtil.isPassive((Entity)lIIIIIIlIIlIlIl)) {
                GL11.glColor3f(0.11f, 0.9f, 0.11f);
            }
            else {
                GL11.glColor3f(0.9f, 0.1f, 0.1f);
            }
            GlStateManager.disableTexture2D();
            GL11.glLineWidth(2.0f);
            GL11.glEnable(2848);
            GL11.glBegin(2);
            GL11.glVertex2d((double)(-lIIIIIIlIIlIlIl.width / 2.0f), 0.0);
            GL11.glVertex2d((double)(-lIIIIIIlIIlIlIl.width / 2.0f), (double)lIIIIIIlIIlIlIl.height);
            GL11.glVertex2d((double)(lIIIIIIlIIlIlIl.width / 2.0f), (double)lIIIIIIlIIlIlIl.height);
            GL11.glVertex2d((double)(lIIIIIIlIIlIlIl.width / 2.0f), 0.0);
            GL11.glEnd();
            GlStateManager.popMatrix();
            return;
        });
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
}
