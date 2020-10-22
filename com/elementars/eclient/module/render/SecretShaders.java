package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import com.elementars.eclient.module.*;
import java.util.*;

public class SecretShaders extends Module
{
    private final /* synthetic */ Value<String> shader;
    
    @Override
    public void onDisable() {
        if (SecretShaders.mc.entityRenderer.getShaderGroup() != null) {
            SecretShaders.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
    }
    
    @Override
    public void onEnable() {
        if (OpenGlHelper.shadersSupported && SecretShaders.mc.getRenderViewEntity() instanceof EntityPlayer) {
            if (SecretShaders.mc.entityRenderer.getShaderGroup() != null) {
                SecretShaders.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
            }
            try {
                SecretShaders.mc.entityRenderer.loadShader(new ResourceLocation(String.valueOf(new StringBuilder().append("shaders/post/").append(this.shader.getValue()).append(".json"))));
            }
            catch (Exception llllIllIIIIIllI) {
                llllIllIIIIIllI.printStackTrace();
            }
        }
        else if (SecretShaders.mc.entityRenderer.getShaderGroup() != null && SecretShaders.mc.currentScreen == null) {
            SecretShaders.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
    }
    
    public SecretShaders() {
        super("SecretShaders", "Brings back super secret settings shaders", 0, Category.RENDER, true);
        this.shader = this.register(new Value<String>("Shader", this, "notch", new ArrayList<String>(Arrays.asList("antialias", "art", "bits", "blobs", "blobs2", "blur", "bumpy", "color_convolve", "creeper", "deconverge", "desaturate", "entity_outline", "flip", "fxaa", "green", "invert", "notch", "ntsc", "outline", "pencil", "phosphor", "scan_pincusion", "sobel", "spider", "wobble"))));
    }
}
