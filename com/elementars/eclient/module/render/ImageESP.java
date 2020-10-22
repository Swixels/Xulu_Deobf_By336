package com.elementars.eclient.module.render;

import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import dev.xulu.settings.*;
import java.awt.image.*;
import javax.imageio.*;
import net.minecraft.client.renderer.texture.*;
import java.io.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.util.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import com.elementars.eclient.module.*;
import net.minecraft.client.renderer.culling.*;

public class ImageESP extends Module
{
    private /* synthetic */ ResourceLocation waifu;
    private final /* synthetic */ Value<Boolean> noRenderPlayers;
    private /* synthetic */ ICamera camera;
    private final /* synthetic */ Value<CachedImage> imageUrl;
    
    @Override
    public void onDisable() {
        ImageESP.EVENT_BUS.unregister((Object)this);
    }
    
    private boolean shouldDraw(final EntityLivingBase llllllllllllllllllIIIIlIlIIIIIIl) {
        return !llllllllllllllllllIIIIlIlIIIIIIl.equals((Object)ImageESP.mc.player) && llllllllllllllllllIIIIlIlIIIIIIl.getHealth() > 0.0f && EntityUtil.isPlayer((Entity)llllllllllllllllllIIIIlIlIIIIIIl);
    }
    
    private <T> BufferedImage getImage(final T llllllllllllllllllIIIIlIlIIIIllI, final ThrowingFunction<T, BufferedImage> llllllllllllllllllIIIIlIlIIIIlll) {
        try {
            return llllllllllllllllllIIIIlIlIIIIlll.apply(llllllllllllllllllIIIIlIlIIIIllI);
        }
        catch (IOException llllllllllllllllllIIIIlIlIIIlIlI) {
            llllllllllllllllllIIIIlIlIIIlIlI.printStackTrace();
            return null;
        }
    }
    
    public void onLoad() {
        BufferedImage llllllllllllllllllIIIIlIIlIIIIII = null;
        try {
            if (this.getFile(this.imageUrl.getValue().getName()) != null) {
                llllllllllllllllllIIIIlIIlIIIIII = this.getImage(this.getFile(this.imageUrl.getValue().getName()), ImageIO::read);
            }
            if (llllllllllllllllllIIIIlIIlIIIIII == null) {
                this.LOGGER.warn("Failed to load image");
            }
            else {
                final DynamicTexture llllllllllllllllllIIIIlIIlIIIIll = new DynamicTexture(llllllllllllllllllIIIIlIIlIIIIII);
                llllllllllllllllllIIIIlIIlIIIIll.loadTexture(ImageESP.mc.getResourceManager());
                this.waifu = ImageESP.mc.getTextureManager().getDynamicTextureLocation(String.valueOf(new StringBuilder().append("XULU_").append(this.imageUrl.getValue().name())), llllllllllllllllllIIIIlIIlIIIIll);
            }
        }
        catch (Exception llllllllllllllllllIIIIlIIlIIIIlI) {
            llllllllllllllllllIIIIlIIlIIIIlI.printStackTrace();
        }
    }
    
    private InputStream getFile(final String llllllllllllllllllIIIIlIIIlllIIl) {
        return ImageESP.class.getResourceAsStream(llllllllllllllllllIIIIlIIIlllIIl);
    }
    
    @Override
    public void onEnable() {
        ImageESP.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onRenderGameOverlayEvent(final RenderGameOverlayEvent.Text llllllllllllllllllIIIIlIIlIlllIl) {
        if (this.waifu == null) {
            return;
        }
        final double llllllllllllllllllIIIIlIIllIIIlI = ImageESP.mc.player.lastTickPosX + (ImageESP.mc.player.posX - ImageESP.mc.player.lastTickPosX) * llllllllllllllllllIIIIlIIlIlllIl.getPartialTicks();
        final double llllllllllllllllllIIIIlIIllIIIIl = ImageESP.mc.player.lastTickPosY + (ImageESP.mc.player.posY - ImageESP.mc.player.lastTickPosY) * llllllllllllllllllIIIIlIIlIlllIl.getPartialTicks();
        final double llllllllllllllllllIIIIlIIllIIIII = ImageESP.mc.player.lastTickPosZ + (ImageESP.mc.player.posZ - ImageESP.mc.player.lastTickPosZ) * llllllllllllllllllIIIIlIIlIlllIl.getPartialTicks();
        this.camera.setPosition(llllllllllllllllllIIIIlIIllIIIlI, llllllllllllllllllIIIIlIIllIIIIl, llllllllllllllllllIIIIlIIllIIIII);
        final List<EntityPlayer> llllllllllllllllllIIIIlIIlIlllll = new ArrayList<EntityPlayer>(ImageESP.mc.world.playerEntities);
        llllllllllllllllllIIIIlIIlIlllll.sort(Comparator.comparing(llllllllllllllllllIIIIlIIIllIllI -> ImageESP.mc.player.getDistance((Entity)llllllllllllllllllIIIIlIIIllIllI)).reversed());
        for (final EntityPlayer llllllllllllllllllIIIIlIIllIIlIl : llllllllllllllllllIIIIlIIlIlllll) {
            if (llllllllllllllllllIIIIlIIllIIlIl != ImageESP.mc.getRenderViewEntity() && llllllllllllllllllIIIIlIIllIIlIl.isEntityAlive() && this.camera.isBoundingBoxInFrustum(llllllllllllllllllIIIIlIIllIIlIl.getEntityBoundingBox())) {
                final EntityLivingBase llllllllllllllllllIIIIlIIllIlllI = (EntityLivingBase)llllllllllllllllllIIIIlIIllIIlIl;
                final Vec3d llllllllllllllllllIIIIlIIllIllIl = EntityUtil.getInterpolatedPos((Entity)llllllllllllllllllIIIIlIIllIlllI, llllllllllllllllllIIIIlIIlIlllIl.getPartialTicks());
                final Vec3d llllllllllllllllllIIIIlIIllIllII = llllllllllllllllllIIIIlIIllIllIl.add(new Vec3d(0.0, llllllllllllllllllIIIIlIIllIIlIl.getRenderBoundingBox().maxY - llllllllllllllllllIIIIlIIllIIlIl.posY, 0.0));
                final VectorUtils.ScreenPos llllllllllllllllllIIIIlIIllIlIll = VectorUtils._toScreen(llllllllllllllllllIIIIlIIllIllII.x, llllllllllllllllllIIIIlIIllIllII.y, llllllllllllllllllIIIIlIIllIllII.z);
                final VectorUtils.ScreenPos llllllllllllllllllIIIIlIIllIlIlI = VectorUtils._toScreen(llllllllllllllllllIIIIlIIllIllIl.x, llllllllllllllllllIIIIlIIllIllIl.y, llllllllllllllllllIIIIlIIllIllIl.z);
                if (!llllllllllllllllllIIIIlIIllIlIll.isVisible && !llllllllllllllllllIIIIlIIllIlIlI.isVisible) {
                    continue;
                }
                final int llllllllllllllllllIIIIlIIllIlIII;
                final int llllllllllllllllllIIIIlIIllIlIIl = llllllllllllllllllIIIIlIIllIlIII = llllllllllllllllllIIIIlIIllIlIlI.y - llllllllllllllllllIIIIlIIllIlIll.y;
                final int llllllllllllllllllIIIIlIIllIIlll = (int)(llllllllllllllllllIIIIlIIllIlIll.x - llllllllllllllllllIIIIlIIllIlIIl / 1.8);
                final int llllllllllllllllllIIIIlIIllIIllI = llllllllllllllllllIIIIlIIllIlIll.y;
                ImageESP.mc.renderEngine.bindTexture(this.waifu);
                GlStateManager.color(255.0f, 255.0f, 255.0f);
                Gui.drawScaledCustomSizeModalRect(llllllllllllllllllIIIIlIIllIIlll, llllllllllllllllllIIIIlIIllIIllI, 0.0f, 0.0f, llllllllllllllllllIIIIlIIllIlIIl, llllllllllllllllllIIIIlIIllIlIII, llllllllllllllllllIIIIlIIllIlIIl, llllllllllllllllllIIIIlIIllIlIII, (float)llllllllllllllllllIIIIlIIllIlIIl, (float)llllllllllllllllllIIIIlIIllIlIII);
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderPlayer(final RenderPlayerEvent.Pre llllllllllllllllllIIIIlIIlIIlIlI) {
        if (this.noRenderPlayers.getValue() && !llllllllllllllllllIIIIlIIlIIlIlI.getEntity().equals((Object)ImageESP.mc.player)) {
            llllllllllllllllllIIIIlIIlIIlIlI.setCanceled(true);
        }
    }
    
    public ImageESP() {
        super("ImageESP", "overlay cute images over players", 0, Category.RENDER, true);
        this.noRenderPlayers = this.register(new Value<Boolean>("No Render Players", this, false));
        this.imageUrl = this.register(new Value<CachedImage>("Image", this, CachedImage.LAUREN, CachedImage.values())).onChanged(llllllllllllllllllIIIIlIIIllIIlI -> {
            this.waifu = null;
            this.onLoad();
            return;
        });
        this.camera = (ICamera)new Frustum();
        this.onLoad();
    }
    
    @FunctionalInterface
    private interface ThrowingFunction<T, R>
    {
        R apply(final T p0) throws IOException;
    }
    
    private enum CachedImage
    {
        LAUREN("/images/lauren.png");
        
        /* synthetic */ String name;
        
        WAIFU("/images/waifu.png"), 
        LOTUS("/images/lotus.png"), 
        DELTA("/images/delta.png"), 
        WAIFU2("/images/waifu2.png"), 
        ZHN("/images/zhn.png"), 
        LOGAN("/images/logan.png"), 
        XULU("/images/xulutransparent.png"), 
        PETER("/images/peter.png"), 
        BIGOUNCE("/images/bigounce.png"), 
        TRIANGLE("/images/triangle.png"), 
        JOINT("/images/joint.png"), 
        OMEGA("/images/omega.png");
        
        public String getName() {
            return this.name;
        }
        
        private CachedImage(final String llllllllllllllllIlIlIlIIlllIllIl) {
            this.name = llllllllllllllllIlIlIlIIlllIllIl;
        }
    }
}
