package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import com.elementars.eclient.module.render.*;
import com.elementars.eclient.*;
import net.minecraft.block.*;

public class SearchCommand extends Command
{
    @Override
    public void execute(final String[] lIIlIIIIIIIlIIl) {
        if (lIIlIIIIIIIlIIl.length > 1 && lIIlIIIIIIIlIIl[1].equalsIgnoreCase("help")) {
            this.showSyntax(lIIlIIIIIIIlIIl[0]);
            return;
        }
        if (lIIlIIIIIIIlIIl.length < 2) {
            Command.sendChatMessage("Specify an option. Try doing .search help to see command options");
            return;
        }
        if (lIIlIIIIIIIlIIl[1].equalsIgnoreCase("add")) {
            if (lIIlIIIIIIIlIIl.length < 3) {
                Command.sendChatMessage("Please specify a block.");
                return;
            }
            if (Search.addBlock(lIIlIIIIIIIlIIl[2])) {
                Command.sendChatMessage(String.valueOf(new StringBuilder().append("Added ").append(lIIlIIIIIIIlIIl[2]).append(" to Search!")));
                SearchCommand.mc.renderGlobal.loadRenderers();
            }
            else {
                Command.sendChatMessage("Unknown block!");
            }
        }
        else if (lIIlIIIIIIIlIIl[1].equalsIgnoreCase("remove")) {
            if (lIIlIIIIIIIlIIl.length < 3) {
                Command.sendChatMessage("Please specify a block.");
                return;
            }
            if (Search.delBlock(lIIlIIIIIIIlIIl[2])) {
                Command.sendChatMessage(String.valueOf(new StringBuilder().append("Removed ").append(lIIlIIIIIIIlIIl[2]).append(" from Search!")));
                Xulu.MODULE_MANAGER.getModuleT(Search.class).posList.clear();
                SearchCommand.mc.renderGlobal.loadRenderers();
            }
            else {
                Command.sendChatMessage("Unknown block!");
            }
        }
        else if (lIIlIIIIIIIlIIl[1].equalsIgnoreCase("list")) {
            Command.sendChatMessage(String.valueOf(new StringBuilder().append("Search blocks &7(").append(Search.getBLOCKS().size()).append(")&r: ")));
            String lIIlIIIIIIIlllI = "";
            boolean lIIlIIIIIIIllIl = true;
            for (final Block lIIlIIIIIIIllll : Search.getBLOCKS()) {
                if (lIIlIIIIIIIllIl) {
                    lIIlIIIIIIIlllI = lIIlIIIIIIIllll.getLocalizedName();
                }
                else {
                    lIIlIIIIIIIlllI = String.valueOf(new StringBuilder().append(lIIlIIIIIIIlllI).append(", ").append(lIIlIIIIIIIllll.getLocalizedName()));
                }
                lIIlIIIIIIIllIl = false;
            }
            Command.sendChatMessage(lIIlIIIIIIIlllI);
        }
        else {
            Command.sendChatMessage("Unknown arguments!");
        }
    }
    
    public SearchCommand() {
        super("search", "Manages Search", new String[] { "add", "remove", "list" });
    }
}
