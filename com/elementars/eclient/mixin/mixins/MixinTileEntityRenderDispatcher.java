package com.elementars.eclient.mixin.mixins;

import java.util.*;
import net.minecraft.client.renderer.tileentity.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.*;
import com.google.common.collect.*;
import javax.annotation.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.elementars.eclient.module.render.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.*;
import net.minecraft.tileentity.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import com.elementars.eclient.util.*;
import net.minecraft.util.*;
import dev.xulu.settings.*;
import net.minecraft.crash.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ TileEntityRendererDispatcher.class })
public class MixinTileEntityRenderDispatcher
{
    @Shadow
    public final Map<Class<? extends TileEntity>, TileEntitySpecialRenderer<? extends TileEntity>> renderers;
    @Shadow
    private Tessellator batchBuffer;
    @Shadow
    private boolean drawingBatch;
    
    public MixinTileEntityRenderDispatcher() {
        this.renderers = (Map<Class<? extends TileEntity>, TileEntitySpecialRenderer<? extends TileEntity>>)Maps.newHashMap();
        this.batchBuffer = new Tessellator(2097152);
        this.drawingBatch = false;
    }
    
    @Shadow
    @Nullable
    public <T extends TileEntity> TileEntitySpecialRenderer<T> getRenderer(@Nullable final TileEntity tileEntityIn) {
        return (tileEntityIn == null || tileEntityIn.isInvalid()) ? null : this.getRenderer(tileEntityIn.getClass());
    }
    
    @Shadow
    public <T extends TileEntity> TileEntitySpecialRenderer<T> getRenderer(final Class<? extends TileEntity> teClass) {
        TileEntitySpecialRenderer<T> tileentityspecialrenderer = (TileEntitySpecialRenderer<T>)this.renderers.get(teClass);
        if (tileentityspecialrenderer == null && teClass != TileEntity.class) {
            tileentityspecialrenderer = (TileEntitySpecialRenderer<T>)this.getRenderer((Class<? extends TileEntity>)teClass.getSuperclass());
            this.renderers.put(teClass, tileentityspecialrenderer);
        }
        return tileentityspecialrenderer;
    }
    
    @Inject(method = { "render(Lnet/minecraft/tileentity/TileEntity;DDDFIF)V" }, at = { @At("HEAD") }, cancellable = true)
    public void Irender(final TileEntity tileEntityIn, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float p_192854_10, final CallbackInfo callback) {
        final TileEntitySpecialRenderer<TileEntity> tileentityspecialrenderer = this.getRenderer(tileEntityIn);
        if (tileentityspecialrenderer != null) {
            try {
                final StorageESP storageESP = (StorageESP)Xulu.MODULE_MANAGER.getModuleByName("StorageESP");
                final Value all = Xulu.VALUE_MANAGER.getValueByMod(storageESP, "All Tile Entities");
                if (all != null && !all.getValue() && !(tileEntityIn instanceof TileEntityChest) && !(tileEntityIn instanceof TileEntityEnderChest) && !(tileEntityIn instanceof TileEntityShulkerBox)) {
                    return;
                }
                if (storageESP != null && storageESP.isToggled() && Xulu.VALUE_MANAGER.getValueByMod(storageESP, "Mode").getValue().equalsIgnoreCase("Shader") && tileEntityIn.hasWorld()) {
                    Color n;
                    if (Xulu.VALUE_MANAGER.getValueByMod(storageESP, "Future Colors").getValue()) {
                        n = new Color(StorageESP.getTileEntityColorF(tileEntityIn));
                    }
                    else {
                        n = new Color(StorageESP.getTileEntityColor(tileEntityIn));
                    }
                    if (this.drawingBatch && tileEntityIn.hasFastRenderer()) {
                        GL11.glLineWidth(5.0f);
                        tileentityspecialrenderer.renderTileEntityFast(tileEntityIn, x, y, z, partialTicks, destroyStage, p_192854_10, this.batchBuffer.getBuffer());
                        OutlineUtils.renderOne(Xulu.VALUE_MANAGER.getValueByMod(storageESP, "Line Width").getValue());
                        tileentityspecialrenderer.renderTileEntityFast(tileEntityIn, x, y, z, partialTicks, destroyStage, p_192854_10, this.batchBuffer.getBuffer());
                        OutlineUtils.renderTwo();
                        tileentityspecialrenderer.renderTileEntityFast(tileEntityIn, x, y, z, partialTicks, destroyStage, p_192854_10, this.batchBuffer.getBuffer());
                        OutlineUtils.renderThree();
                        OutlineUtils.renderFour(n);
                        tileentityspecialrenderer.renderTileEntityFast(tileEntityIn, x, y, z, partialTicks, destroyStage, p_192854_10, this.batchBuffer.getBuffer());
                        OutlineUtils.renderFive();
                    }
                    else {
                        GL11.glLineWidth(5.0f);
                        tileentityspecialrenderer.render(tileEntityIn, x, y, z, partialTicks, destroyStage, p_192854_10);
                        OutlineUtils.renderOne(Xulu.VALUE_MANAGER.getValueByMod(storageESP, "Line Width").getValue());
                        tileentityspecialrenderer.render(tileEntityIn, x, y, z, partialTicks, destroyStage, p_192854_10);
                        OutlineUtils.renderTwo();
                        tileentityspecialrenderer.render(tileEntityIn, x, y, z, partialTicks, destroyStage, p_192854_10);
                        OutlineUtils.renderThree();
                        OutlineUtils.renderFour(n);
                        tileentityspecialrenderer.render(tileEntityIn, x, y, z, partialTicks, destroyStage, p_192854_10);
                        OutlineUtils.renderFive();
                    }
                }
                if (this.drawingBatch && tileEntityIn.hasFastRenderer()) {
                    tileentityspecialrenderer.renderTileEntityFast(tileEntityIn, x, y, z, partialTicks, destroyStage, p_192854_10, this.batchBuffer.getBuffer());
                }
                else {
                    tileentityspecialrenderer.render(tileEntityIn, x, y, z, partialTicks, destroyStage, p_192854_10);
                }
            }
            catch (Throwable throwable) {
                final CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Rendering Block Entity");
                final CrashReportCategory crashreportcategory = crashreport.makeCategory("Block Entity Details");
                tileEntityIn.addInfoToCrashReport(crashreportcategory);
                throw new ReportedException(crashreport);
            }
        }
    }
}
