package com.elementars.eclient;

import net.minecraft.client.*;
import club.minnced.discord.rpc.*;

public class DiscordRP
{
    private /* synthetic */ boolean running;
    private /* synthetic */ long created;
    
    public void shutdown() {
        final DiscordRPC lIlIIIIIIllllll = DiscordRPC.INSTANCE;
        lIlIIIIIIllllll.Discord_Shutdown();
    }
    
    public DiscordRP() {
        this.running = true;
        this.created = 0L;
    }
    
    public void start() {
        final Minecraft lIlIIIIIlIIllIl = Minecraft.getMinecraft();
        final DiscordRPC lIlIIIIIlIIllII = DiscordRPC.INSTANCE;
        final String lIlIIIIIlIIlIll = "671154973274275850";
        final String lIlIIIIIlIIlIlI = "";
        final DiscordEventHandlers lIlIIIIIlIIlIIl = new DiscordEventHandlers();
        lIlIIIIIlIIlIIl.ready = (lIlIIIIIIllIIIl -> System.out.println("Ready!"));
        lIlIIIIIlIIllII.Discord_Initialize(lIlIIIIIlIIlIll, lIlIIIIIlIIlIIl, true, lIlIIIIIlIIlIlI);
        final DiscordRichPresence lIlIIIIIlIIlIII = new DiscordRichPresence();
        lIlIIIIIlIIlIII.startTimestamp = System.currentTimeMillis() / 1000L;
        lIlIIIIIlIIlIII.details = "Playing epicly";
        lIlIIIIIlIIlIII.state = "lol";
        lIlIIIIIlIIlIII.largeImageKey = "xulu2";
        lIlIIIIIlIIllII.Discord_UpdatePresence(lIlIIIIIlIIlIII);
        final DiscordRPC discordRPC;
        final DiscordRichPresence discordRichPresence;
        final Minecraft minecraft;
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                discordRPC.Discord_RunCallbacks();
                try {
                    discordRichPresence.largeImageKey = "xulurevamp3";
                    discordRichPresence.largeImageText = "Xulu v1.5.2";
                    if (minecraft.isIntegratedServerRunning()) {
                        discordRichPresence.details = "Singleplayer";
                        discordRichPresence.state = "In Game";
                    }
                    else if (minecraft.getCurrentServerData() != null) {
                        if (!minecraft.getCurrentServerData().serverIP.equals(discordRichPresence.state)) {
                            discordRichPresence.details = "Playing a server";
                            discordRichPresence.state = minecraft.getCurrentServerData().serverIP;
                        }
                    }
                    else {
                        discordRichPresence.details = "Menu";
                        discordRichPresence.state = "Idle";
                    }
                    discordRPC.Discord_UpdatePresence(discordRichPresence);
                }
                catch (Exception lIlIIIIIIlllIIl) {
                    lIlIIIIIIlllIIl.printStackTrace();
                }
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException ex) {}
            }
        }, "RPC-Callback-Handler").start();
    }
}
