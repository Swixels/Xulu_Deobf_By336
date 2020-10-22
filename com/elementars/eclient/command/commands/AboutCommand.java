package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;

public class AboutCommand extends Command
{
    @Override
    public void execute(final String[] lllllllllllllllllIIlIlllIllllIlI) {
        if (lllllllllllllllllIIlIlllIllllIlI.length > 1 && lllllllllllllllllIIlIlllIllllIlI[1].equalsIgnoreCase("help")) {
            this.showSyntax(lllllllllllllllllIIlIlllIllllIlI[0]);
            return;
        }
        Command.sendChatMessage("Xulu v1.5.2 by Elementars and John200410");
        Command.sendChatMessage("Do .help to see a list of commands");
    }
    
    public AboutCommand() {
        super("about", "Shows general information", new String[0]);
    }
}
