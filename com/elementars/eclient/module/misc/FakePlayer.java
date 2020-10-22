package com.elementars.eclient.module.misc;

import com.elementars.eclient.module.*;
import java.util.*;
import com.mojang.authlib.*;
import net.minecraft.client.entity.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;

public class FakePlayer extends Module
{
    @Override
    public void onDisable() {
        FakePlayer.mc.world.removeEntityFromWorld(-100);
    }
    
    public FakePlayer() {
        super("FakePlayer", "Spawns a fake player", 0, Category.MISC, true);
    }
    
    @Override
    public void onEnable() {
        if (FakePlayer.mc.world == null) {
            return;
        }
        final EntityOtherPlayerMP lllllllllllllllllllllllIIIlIllIl = new EntityOtherPlayerMP((World)FakePlayer.mc.world, new GameProfile(UUID.fromString("70ee432d-0a96-4137-a2c0-37cc9df67f03"), "jared2013"));
        lllllllllllllllllllllllIIIlIllIl.copyLocationAndAnglesFrom((Entity)FakePlayer.mc.player);
        lllllllllllllllllllllllIIIlIllIl.rotationYawHead = FakePlayer.mc.player.rotationYawHead;
        FakePlayer.mc.world.addEntityToWorld(-100, (Entity)lllllllllllllllllllllllIIIlIllIl);
    }
}
