package com.elementars.eclient.util;

import dev.xulu.settings.*;
import net.minecraft.client.entity.*;
import net.minecraft.world.*;
import net.minecraft.client.*;

public class Wrapper
{
    public static /* synthetic */ FileManager fileManager;
    
    public static EntityPlayerSP getPlayer() {
        return getMinecraft().player;
    }
    
    public static World getWorld() {
        return (World)getMinecraft().world;
    }
    
    public static FileManager getFileManager() {
        if (Wrapper.fileManager == null) {
            Wrapper.fileManager = new FileManager();
        }
        return Wrapper.fileManager;
    }
    
    public static Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }
}
