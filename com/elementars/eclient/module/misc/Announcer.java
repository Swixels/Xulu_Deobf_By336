package com.elementars.eclient.module.misc;

import dev.xulu.settings.*;
import com.elementars.eclient.event.events.*;
import net.minecraftforge.common.*;
import com.elementars.eclient.command.*;
import com.elementars.eclient.module.*;
import java.util.*;

public class Announcer extends Module
{
    public static /* synthetic */ Value<Boolean> command;
    public static /* synthetic */ Value<Boolean> swapHand;
    public static /* synthetic */ Value<Boolean> blockPlaced;
    public static /* synthetic */ Value<Boolean> screenShot;
    private /* synthetic */ AnnouncerRegistry announcerRegistry;
    public static /* synthetic */ Value<Boolean> pickUpItem;
    public static /* synthetic */ Value<Boolean> itemDroped;
    public static /* synthetic */ Value<Boolean> pickBlock;
    public static /* synthetic */ Value<Boolean> craftedItem;
    public static /* synthetic */ Value<Boolean> fullScreen;
    public static /* synthetic */ Value<Boolean> jump;
    public static /* synthetic */ Value<Boolean> sneak;
    public static /* synthetic */ Value<Boolean> blockBroke;
    public static /* synthetic */ Value<Boolean> pauseGame;
    public static /* synthetic */ Value<Boolean> attack;
    public static /* synthetic */ Value<Boolean> respawn;
    public static /* synthetic */ Value<Boolean> smeltedItem;
    public static /* synthetic */ Value<Boolean> openChat;
    public static /* synthetic */ Value<Boolean> eatting;
    public static /* synthetic */ Value<Boolean> openInv;
    public static /* synthetic */ Value<Integer> delay;
    public static /* synthetic */ Value<String> mode;
    public static /* synthetic */ int delayy;
    public static /* synthetic */ Value<Boolean> playerList;
    public static /* synthetic */ Value<Boolean> walk;
    public static /* synthetic */ Value<Boolean> Perspective;
    
    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this.announcerRegistry);
        Command.sendChatMessage("Announcer ON");
    }
    
    public Announcer() {
        super("Announcer", "Announce EVERYTHING in chat", 0, Category.MISC, true);
        this.announcerRegistry = new AnnouncerRegistry();
        Announcer.mode = this.register(new Value<String>("Mode", this, "English", new ArrayList<String>(Arrays.asList("English", "Hebrew"))));
        Announcer.delay = this.register(new Value<Integer>("Delay", this, 10, 0, 60));
        Announcer.walk = this.register(new Value<Boolean>("Walk", this, true));
        Announcer.craftedItem = this.register(new Value<Boolean>("CraftedItem", this, true));
        Announcer.pickUpItem = this.register(new Value<Boolean>("PickUpItem", this, true));
        Announcer.smeltedItem = this.register(new Value<Boolean>("SmeltedItem", this, true));
        Announcer.respawn = this.register(new Value<Boolean>("Respawn", this, true));
        Announcer.blockPlaced = this.register(new Value<Boolean>("BlockPlaced", this, true));
        Announcer.blockBroke = this.register(new Value<Boolean>("BlockBroke", this, true));
        Announcer.itemDroped = this.register(new Value<Boolean>("ItemDropped", this, true));
        Announcer.openChat = this.register(new Value<Boolean>("OpenChat", this, true));
        Announcer.pickBlock = this.register(new Value<Boolean>("PickBlock", this, true));
        Announcer.command = this.register(new Value<Boolean>("Command", this, true));
        Announcer.fullScreen = this.register(new Value<Boolean>("FullScreen", this, true));
        Announcer.pauseGame = this.register(new Value<Boolean>("PauseGame", this, true));
        Announcer.openInv = this.register(new Value<Boolean>("OpenInv", this, true));
        Announcer.playerList = this.register(new Value<Boolean>("PlayerList", this, true));
        Announcer.screenShot = this.register(new Value<Boolean>("ScreenShot", this, true));
        Announcer.swapHand = this.register(new Value<Boolean>("SwapHand", this, true));
        Announcer.sneak = this.register(new Value<Boolean>("Sneak", this, true));
        Announcer.Perspective = this.register(new Value<Boolean>("Perspective", this, true));
        Announcer.jump = this.register(new Value<Boolean>("Jump", this, true));
        Announcer.attack = this.register(new Value<Boolean>("Attack", this, true));
        Announcer.eatting = this.register(new Value<Boolean>("Eating", this, true));
    }
    
    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this.announcerRegistry);
        Command.sendChatMessage("Announcer OFF");
    }
    
    @Override
    public void onUpdate() {
        if (Announcer.delayy > 0) {
            --Announcer.delayy;
        }
    }
}
