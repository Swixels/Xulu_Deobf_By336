package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import com.elementars.eclient.*;

public class SaveCommand extends Command
{
    @Override
    public void execute(final String[] llllIIlIlIllIl) {
        if (llllIIlIlIllIl.length > 1 && llllIIlIlIllIl[1].equalsIgnoreCase("help")) {
            this.showSyntax(llllIIlIlIllIl[0]);
            return;
        }
        Xulu.save();
        Command.sendChatMessage("Config saved!");
    }
    
    public SaveCommand() {
        super("save", "Saves the config", new String[0]);
    }
}
