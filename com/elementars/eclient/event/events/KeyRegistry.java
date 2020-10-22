package com.elementars.eclient.event.events;

import com.elementars.eclient.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.*;
import com.elementars.eclient.module.*;
import net.minecraft.entity.passive.*;
import org.lwjgl.opengl.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.command.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.entity.living.*;

public class KeyRegistry implements Helper
{
    @SubscribeEvent
    public void onKeyPress(final InputEvent.KeyInputEvent lllllllllllllllllIlIlIlIlIIlllll) {
        for (final Module lllllllllllllllllIlIlIlIlIlIIIIl : Xulu.MODULE_MANAGER.getModules()) {
            if (lllllllllllllllllIlIlIlIlIlIIIIl.keybind != null && lllllllllllllllllIlIlIlIlIlIIIIl.keybind.isPressed()) {
                lllllllllllllllllIlIlIlIlIlIIIIl.toggle();
            }
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent lllllllllllllllllIlIlIlIlIIIllIl) {
        if (KeyRegistry.mc.player == null) {
            return;
        }
        final ModuleManager module_MANAGER = Xulu.MODULE_MANAGER;
        ModuleManager.onUpdate();
        TargetPlayers.onUpdate();
        RainbowUtils.updateRainbow();
    }
    
    @SubscribeEvent
    public void onRenderPre(final RenderGameOverlayEvent.Pre lllllllllllllllllIlIlIlIIllllIlI) {
        if (lllllllllllllllllIlIlIlIIllllIlI.getType() == RenderGameOverlayEvent.ElementType.BOSSINFO && Xulu.MODULE_MANAGER.getModuleByName("BossStack").isToggled()) {
            lllllllllllllllllIlIlIlIIllllIlI.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Post lllllllllllllllllIlIlIlIlIIlIIII) {
        if (lllllllllllllllllIlIlIlIlIIlIIII.isCanceled()) {
            return;
        }
        RenderGameOverlayEvent.ElementType lllllllllllllllllIlIlIlIlIIlIIIl = RenderGameOverlayEvent.ElementType.EXPERIENCE;
        if (!Wrapper.getPlayer().isCreative() && Wrapper.getPlayer().getRidingEntity() instanceof AbstractHorse) {
            lllllllllllllllllIlIlIlIlIIlIIIl = RenderGameOverlayEvent.ElementType.HEALTHMOUNT;
        }
        if (lllllllllllllllllIlIlIlIlIIlIIII.getType() == lllllllllllllllllIlIlIlIlIIlIIIl) {
            final ModuleManager module_MANAGER = Xulu.MODULE_MANAGER;
            ModuleManager.onRender();
            GL11.glPushMatrix();
            GL11.glPopMatrix();
            XuluTessellator.releaseGL();
        }
    }
    
    @SubscribeEvent
    public void onChatMessage(final ClientChatEvent lllllllllllllllllIlIlIlIIlllllll) {
        if (lllllllllllllllllIlIlIlIIlllllll.getMessage().startsWith(Command.getPrefix())) {
            final String lllllllllllllllllIlIlIlIlIIIIIlI = lllllllllllllllllIlIlIlIIlllllll.getMessage();
            lllllllllllllllllIlIlIlIIlllllll.setCanceled(true);
            CommandManager.runCommand(lllllllllllllllllIlIlIlIlIIIIIlI.substring(Command.getPrefix().length()));
        }
    }
    
    @SubscribeEvent
    public void onAttack(final AttackEntityEvent lllllllllllllllllIlIlIlIlIIIlIIl) {
        TargetPlayers.onAttack(lllllllllllllllllIlIlIlIlIIIlIIl);
    }
    
    @SubscribeEvent
    public void onWorldRender(final RenderWorldLastEvent lllllllllllllllllIlIlIlIlIIIIllI) {
        if (lllllllllllllllllIlIlIlIlIIIIllI.isCanceled()) {
            return;
        }
        final ModuleManager module_MANAGER = Xulu.MODULE_MANAGER;
        ModuleManager.onWorldRender(lllllllllllllllllIlIlIlIlIIIIllI);
    }
    
    @SubscribeEvent
    public void onPlayerUpdate(final LivingEvent.LivingUpdateEvent lllllllllllllllllIlIlIlIlIIlIlll) {
        if (Wrapper.getMinecraft().world != null && lllllllllllllllllIlIlIlIlIIlIlll.getEntity().getEntityWorld().isRemote && lllllllllllllllllIlIlIlIlIIlIlll.getEntityLiving().equals((Object)Wrapper.getPlayer())) {
            final LocalPlayerUpdateEvent lllllllllllllllllIlIlIlIlIIllIlI = new LocalPlayerUpdateEvent(lllllllllllllllllIlIlIlIlIIlIlll.getEntityLiving());
            lllllllllllllllllIlIlIlIlIIllIlI.call();
        }
    }
}
