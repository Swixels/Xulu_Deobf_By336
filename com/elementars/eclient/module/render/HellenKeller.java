package com.elementars.eclient.module.render;

import net.minecraft.client.renderer.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.*;
import com.elementars.eclient.module.*;

public class HellenKeller extends Module
{
    /* synthetic */ float masterLevel;
    
    @Override
    public void onRender() {
        GlStateManager.pushMatrix();
        Gui.drawRect(0, 0, HellenKeller.mc.displayWidth, HellenKeller.mc.displayHeight, new Color(0, 0, 0, 255).getRGB());
        GlStateManager.popMatrix();
    }
    
    @Override
    public void onEnable() {
        this.masterLevel = HellenKeller.mc.gameSettings.getSoundLevel(SoundCategory.MASTER);
        HellenKeller.mc.gameSettings.setSoundLevel(SoundCategory.MASTER, 0.0f);
    }
    
    @Override
    public void onDisable() {
        HellenKeller.mc.gameSettings.setSoundLevel(SoundCategory.MASTER, this.masterLevel);
    }
    
    public HellenKeller() {
        super("HellenKeller", "Play like Hellen Keller", 0, Category.RENDER, true);
    }
}
