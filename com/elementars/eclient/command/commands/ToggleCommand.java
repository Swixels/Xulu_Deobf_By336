package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import com.elementars.eclient.*;
import com.elementars.eclient.module.*;

public class ToggleCommand extends Command
{
    @Override
    public void execute(final String[] lllllllllllllllllIIIlllllIIIIlIl) {
        if (lllllllllllllllllIIIlllllIIIIlIl.length > 1 && lllllllllllllllllIIIlllllIIIIlIl[1].equalsIgnoreCase("help")) {
            this.showSyntax(lllllllllllllllllIIIlllllIIIIlIl[0]);
            return;
        }
        if (lllllllllllllllllIIIlllllIIIIlIl.length <= 1) {
            Command.sendChatMessage("Not enough arguments!");
            return;
        }
        if (Xulu.MODULE_MANAGER.getModuleByName(lllllllllllllllllIIIlllllIIIIlIl[1]) != null) {
            final Module lllllllllllllllllIIIlllllIIIIlll = Xulu.MODULE_MANAGER.getModuleByName(lllllllllllllllllIIIlllllIIIIlIl[1]);
            lllllllllllllllllIIIlllllIIIIlll.toggle();
            Command.sendChatMessage(String.valueOf(new StringBuilder().append(lllllllllllllllllIIIlllllIIIIlll.getName()).append(" toggled ").append(lllllllllllllllllIIIlllllIIIIlll.isToggled() ? String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append("aON")) : String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append("cOFF")))));
        }
        else {
            Command.sendChatMessage("Module not found.");
        }
    }
    
    public ToggleCommand() {
        super("t", "Toggles modules", new String[0]);
    }
}
