package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.*;

public class DrawnCommand extends Command
{
    public DrawnCommand() {
        super("drawn", "toggles if a module is drawn on array list", new String[0]);
    }
    
    @Override
    public void execute(final String[] llllllllllllllllIlIlIIllllIllIlI) {
        if (llllllllllllllllIlIlIIllllIllIlI.length < 2) {
            Command.sendChatMessage("Please specify which module you want drawn/undrawn");
            return;
        }
        if (llllllllllllllllIlIlIIllllIllIlI[1].equalsIgnoreCase("help")) {
            this.showSyntax(llllllllllllllllIlIlIIllllIllIlI[0]);
            return;
        }
        final Module llllllllllllllllIlIlIIllllIllIIl = Xulu.MODULE_MANAGER.getModuleByName(llllllllllllllllIlIlIIllllIllIlI[1]);
        if (llllllllllllllllIlIlIIllllIllIIl == null) {
            Command.sendChatMessage("Module not found.");
            return;
        }
        llllllllllllllllIlIlIIllllIllIIl.setDrawn(!llllllllllllllllIlIlIIllllIllIIl.isDrawn());
        Command.sendChatMessage(String.valueOf(new StringBuilder().append(llllllllllllllllIlIlIIllllIllIIl.getDisplayName()).append(llllllllllllllllIlIlIIllllIllIIl.isDrawn() ? " drawn" : " undrawn")));
    }
}
