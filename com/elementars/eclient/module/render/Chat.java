package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.text.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.command.*;
import java.text.*;
import java.util.*;

public class Chat extends Module
{
    public final /* synthetic */ Value<Boolean> customFont;
    private final /* synthetic */ Value<String> namemode;
    private final /* synthetic */ Value<String> bracketmode;
    public static /* synthetic */ Chat INSTANCE;
    private final /* synthetic */ Value<Boolean> mode;
    public static /* synthetic */ Value<Boolean> nochatshadow;
    private final /* synthetic */ Value<String> color;
    private final /* synthetic */ Value<String> playername;
    private final /* synthetic */ Value<Boolean> timestamps;
    private final /* synthetic */ Value<String> playerColor;
    private final /* synthetic */ Value<Boolean> ncb;
    private final /* synthetic */ Value<Boolean> namehighlight;
    
    public Chat() {
        super("Chat", "Tampers with chat", 0, Category.RENDER, true);
        this.customFont = this.register(new Value<Boolean>("Custom Font", this, false));
        this.ncb = this.register(new Value<Boolean>("No Chat Background", this, false));
        this.namehighlight = this.register(new Value<Boolean>("Name Highlight", this, false));
        this.namemode = this.register(new Value<String>("Highlight Mode", this, "Highlight", new ArrayList<String>(Arrays.asList("Highlight", "Hide"))));
        this.playername = this.register(new Value<String>("Player Tag", this, "<Player>", new String[] { "<Player>", "[Player]:", "Player:", "Player ->" }));
        this.playerColor = this.register(new Value<String>("Player Color", this, "White", ColorTextUtils.colors));
        this.timestamps = this.register(new Value<Boolean>("Time Stamps", this, false));
        this.mode = this.register(new Value<Boolean>("24 Hour Time", this, false));
        this.bracketmode = this.register(new Value<String>("Bracket Type", this, "<>", new ArrayList<String>(Arrays.asList("()", "<>", "[]", "{}"))));
        this.color = this.register(new Value<String>("Color", this, "LightGray", ColorTextUtils.colors));
        Chat.nochatshadow = this.register(new Value<Boolean>("No Chat Shadow", this, false));
        Chat.INSTANCE = this;
    }
    
    @EventTarget
    public void onPacket(final EventReceivePacket lllIIlllllIll) {
        if (lllIIlllllIll.getPacket() instanceof SPacketChat) {
            final SPacketChat lllIIllllllll = (SPacketChat)lllIIlllllIll.getPacket();
            if (lllIIllllllll.getType() != ChatType.GAME_INFO && this.tryProcessChat(lllIIllllllll.getChatComponent().getFormattedText(), lllIIllllllll.getChatComponent().getUnformattedText())) {
                lllIIlllllIll.setCancelled(true);
            }
        }
    }
    
    private boolean tryProcessChat(String lllIIllIlllll, final String lllIIllIllllI) {
        String lllIIlllIIIll = (String)lllIIllIlllll;
        final String[] lllIIlllIIIlI = lllIIlllIIIll.split(" ");
        final String[] lllIIlllIIIIl = lllIIllIllllI.split(" ");
        lllIIlllIIIlI[0] = lllIIlllIIIIl[0];
        if (lllIIlllIIIlI[0].startsWith("<") && lllIIlllIIIlI[0].endsWith(">")) {
            lllIIlllIIIlI[0] = lllIIlllIIIlI[0].replaceAll("<", "");
            lllIIlllIIIlI[0] = lllIIlllIIIlI[0].replaceAll(">", "");
            lllIIlllIIIlI[0] = String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append(ColorTextUtils.getColor(this.playerColor.getValue()).substring(1)).append(lllIIlllIIIlI[0]).append(Command.SECTIONSIGN()).append("r"));
            if (this.playername.getValue().equalsIgnoreCase("<Player>")) {
                String lllIIllllIIII = String.valueOf(new StringBuilder().append("<").append(lllIIlllIIIlI[0]).append(">"));
                for (int lllIIllllIIIl = 1; lllIIllllIIIl < lllIIlllIIIlI.length; ++lllIIllllIIIl) {
                    lllIIllllIIII = String.valueOf(new StringBuilder().append(lllIIllllIIII).append(" ").append(lllIIlllIIIlI[lllIIllllIIIl]));
                }
                lllIIllIlllll = lllIIllllIIII;
            }
            else if (this.playername.getValue().equalsIgnoreCase("[Player]:")) {
                String lllIIlllIlllI = String.valueOf(new StringBuilder().append("[").append(lllIIlllIIIlI[0]).append("]:"));
                for (int lllIIlllIllll = 1; lllIIlllIllll < lllIIlllIIIlI.length; ++lllIIlllIllll) {
                    lllIIlllIlllI = String.valueOf(new StringBuilder().append(lllIIlllIlllI).append(" ").append(lllIIlllIIIlI[lllIIlllIllll]));
                }
                lllIIllIlllll = lllIIlllIlllI;
            }
            else if (this.playername.getValue().equalsIgnoreCase("Player:")) {
                String lllIIlllIllII = String.valueOf(new StringBuilder().append(lllIIlllIIIlI[0]).append(":"));
                for (int lllIIlllIllIl = 1; lllIIlllIllIl < lllIIlllIIIlI.length; ++lllIIlllIllIl) {
                    lllIIlllIllII = String.valueOf(new StringBuilder().append(lllIIlllIllII).append(" ").append(lllIIlllIIIlI[lllIIlllIllIl]));
                }
                lllIIllIlllll = lllIIlllIllII;
            }
            else if (this.playername.getValue().equalsIgnoreCase("Player ->")) {
                String lllIIlllIlIlI = String.valueOf(new StringBuilder().append(lllIIlllIIIlI[0]).append(" ->"));
                for (int lllIIlllIlIll = 1; lllIIlllIlIll < lllIIlllIIIlI.length; ++lllIIlllIlIll) {
                    lllIIlllIlIlI = String.valueOf(new StringBuilder().append(lllIIlllIlIlI).append(" ").append(lllIIlllIIIlI[lllIIlllIlIll]));
                }
                lllIIllIlllll = lllIIlllIlIlI;
            }
            else {
                String lllIIlllIlIII = String.valueOf(new StringBuilder().append("<").append(lllIIlllIIIlI[0]).append(">"));
                for (int lllIIlllIlIIl = 1; lllIIlllIlIIl < lllIIlllIIIlI.length; ++lllIIlllIlIIl) {
                    lllIIlllIlIII = String.valueOf(new StringBuilder().append(lllIIlllIlIII).append(" ").append(lllIIlllIIIlI[lllIIlllIlIIl]));
                }
                lllIIllIlllll = lllIIlllIlIII;
            }
        }
        lllIIlllIIIll = (String)lllIIllIlllll;
        if (this.timestamps.getValue()) {
            String lllIIlllIIlll = "";
            if (this.mode.getValue()) {
                lllIIlllIIlll = new SimpleDateFormat("k:mm").format(new Date());
            }
            else {
                lllIIlllIIlll = new SimpleDateFormat("h:mm a").format(new Date());
            }
            if (this.bracketmode.getValue().equalsIgnoreCase("<>")) {
                lllIIlllIIIll = String.valueOf(new StringBuilder().append("§").append(ColorTextUtils.getColor(this.color.getValue()).substring(1)).append("<").append(lllIIlllIIlll).append(">§r ").append((String)lllIIllIlllll));
            }
            else if (this.bracketmode.getValue().equalsIgnoreCase("()")) {
                lllIIlllIIIll = String.valueOf(new StringBuilder().append("§").append(ColorTextUtils.getColor(this.color.getValue()).substring(1)).append("(").append(lllIIlllIIlll).append(")§r ").append((String)lllIIllIlllll));
            }
            else if (this.bracketmode.getValue().equalsIgnoreCase("[]")) {
                lllIIlllIIIll = String.valueOf(new StringBuilder().append("§").append(ColorTextUtils.getColor(this.color.getValue()).substring(1)).append("[").append(lllIIlllIIlll).append("]§r ").append((String)lllIIllIlllll));
            }
            else if (this.bracketmode.getValue().equalsIgnoreCase("{}")) {
                lllIIlllIIIll = String.valueOf(new StringBuilder().append("§").append(ColorTextUtils.getColor(this.color.getValue()).substring(1)).append("{").append(lllIIlllIIlll).append("}§r ").append((String)lllIIllIlllll));
            }
        }
        if (this.namehighlight.getValue()) {
            if (Chat.mc.player == null) {
                return false;
            }
            if (this.namemode.getValue().equalsIgnoreCase("Hide")) {
                lllIIlllIIIll = lllIIlllIIIll.replace(Chat.mc.player.getName(), "HIDDEN");
            }
            else {
                lllIIlllIIIll = lllIIlllIIIll.replace(Chat.mc.player.getName(), String.valueOf(new StringBuilder().append("§b").append(Chat.mc.player.getName()).append("§r")));
            }
        }
        Command.sendRawChatMessage(lllIIlllIIIll);
        return true;
    }
}
