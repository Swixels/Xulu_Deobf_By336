package com.elementars.eclient.module.misc;

import net.minecraft.client.renderer.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import com.elementars.eclient.module.*;

public class Australia extends Module
{
    @Override
    public void onUpdate() {
        if (OpenGlHelper.shadersSupported && Australia.mc.getRenderViewEntity() instanceof EntityPlayer) {
            if (Australia.mc.entityRenderer.getShaderGroup() != null) {
                Australia.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
            }
            try {
                Australia.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/flip.json"));
            }
            catch (Exception llllllllllllllllIllllIllIllIlllI) {
                llllllllllllllllIllllIllIllIlllI.printStackTrace();
            }
        }
        else if (Australia.mc.entityRenderer.getShaderGroup() != null && Australia.mc.currentScreen == null) {
            Australia.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
        Australia.mc.player.setFire(1);
    }
    
    public Australia() {
        super("Australia", "Best Module", 0, Category.MISC, true);
    }
}
