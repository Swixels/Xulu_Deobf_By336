package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import com.elementars.eclient.module.render.*;
import net.minecraft.block.*;

public class XrayCommand extends Command
{
    @Override
    public void execute(final String[] lllIlIllIlIlIll) {
        if (lllIlIllIlIlIll.length > 1 && lllIlIllIlIlIll[1].equalsIgnoreCase("help")) {
            this.showSyntax(lllIlIllIlIlIll[0]);
            return;
        }
        if (lllIlIllIlIlIll.length < 2) {
            Command.sendChatMessage("Specify an option. Try doing .xray help to see command options");
            return;
        }
        if (lllIlIllIlIlIll[1].equalsIgnoreCase("add")) {
            if (lllIlIllIlIlIll.length < 3) {
                Command.sendChatMessage("Please specify a block.");
                return;
            }
            if (Xray.addBlock(lllIlIllIlIlIll[2])) {
                Command.sendChatMessage(String.valueOf(new StringBuilder().append("Added ").append(lllIlIllIlIlIll[2]).append(" to XRAY!")));
                XrayCommand.mc.renderGlobal.loadRenderers();
            }
            else {
                Command.sendChatMessage("Unknown block!");
            }
        }
        else if (lllIlIllIlIlIll[1].equalsIgnoreCase("remove")) {
            if (lllIlIllIlIlIll.length < 3) {
                Command.sendChatMessage("Please specify a block.");
                return;
            }
            if (Xray.delBlock(lllIlIllIlIlIll[2])) {
                Command.sendChatMessage(String.valueOf(new StringBuilder().append("Removed ").append(lllIlIllIlIlIll[2]).append(" from XRAY!")));
                XrayCommand.mc.renderGlobal.loadRenderers();
            }
            else {
                Command.sendChatMessage("Unknown block!");
            }
        }
        else if (lllIlIllIlIlIll[1].equalsIgnoreCase("list")) {
            Command.sendChatMessage(String.valueOf(new StringBuilder().append("Xray blocks &7(").append(Xray.getBLOCKS().size()).append(")&r: ")));
            String lllIlIllIllIIII = "";
            boolean lllIlIllIlIllll = true;
            for (final Block lllIlIllIllIIIl : Xray.getBLOCKS()) {
                if (lllIlIllIlIllll) {
                    lllIlIllIllIIII = lllIlIllIllIIIl.getLocalizedName();
                }
                else {
                    lllIlIllIllIIII = String.valueOf(new StringBuilder().append(lllIlIllIllIIII).append(", ").append(lllIlIllIllIIIl.getLocalizedName()));
                }
                lllIlIllIlIllll = false;
            }
            Command.sendChatMessage(lllIlIllIllIIII);
        }
        else {
            Command.sendChatMessage("Unknown arguments!");
        }
    }
    
    public XrayCommand() {
        super("xray", "Manages Xray", new String[] { "add", "remove", "list" });
    }
}
