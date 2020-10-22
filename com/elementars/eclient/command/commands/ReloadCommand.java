package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import com.elementars.eclient.*;

public class ReloadCommand extends Command
{
    public ReloadCommand() {
        super("reload", "Reloads the config", new String[0]);
    }
    
    @Override
    public void execute(final String[] lllllllllllllllllIllllIllIllIIII) {
        if (lllllllllllllllllIllllIllIllIIII.length > 1 && lllllllllllllllllIllllIllIllIIII[1].equalsIgnoreCase("help")) {
            this.showSyntax(lllllllllllllllllIllllIllIllIIII[0]);
            return;
        }
        Xulu.load();
        Command.sendChatMessage("Config reloaded!");
    }
}
