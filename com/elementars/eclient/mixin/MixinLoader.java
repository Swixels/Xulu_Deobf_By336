package com.elementars.eclient.mixin;

import net.minecraftforge.fml.relauncher.*;
import org.spongepowered.asm.launch.*;
import org.spongepowered.asm.mixin.*;
import java.util.*;

public class MixinLoader implements IFMLLoadingPlugin
{
    private static boolean isObfuscatedEnvironment;
    
    public MixinLoader() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.eclient.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
    }
    
    public String[] getASMTransformerClass() {
        return new String[0];
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> data) {
        MixinLoader.isObfuscatedEnvironment = data.get("runtimeDeobfuscationEnabled");
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
    
    static {
        MixinLoader.isObfuscatedEnvironment = false;
    }
}
