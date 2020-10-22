package com.elementars.eclient.command.commands;

import com.elementars.eclient.*;
import com.elementars.eclient.command.*;
import dev.xulu.settings.*;
import com.elementars.eclient.module.*;

public class SetStringCommand extends Command
{
    @Override
    public void execute(final String[] llllllllllllllllIlIlIIIllllIIlll) {
        if (llllllllllllllllIlIlIIIllllIIlll.length < 2) {
            Command.sendChatMessage("Please specify a module");
            return;
        }
        if (llllllllllllllllIlIlIIIllllIIlll[1].equalsIgnoreCase("help")) {
            this.showSyntax(llllllllllllllllIlIlIIIllllIIlll[0]);
            return;
        }
        if (llllllllllllllllIlIlIIIllllIIlll.length < 3) {
            Command.sendChatMessage("Please specify which setting you would like to change");
            return;
        }
        final Module llllllllllllllllIlIlIIIllllIlIlI = Xulu.MODULE_MANAGER.getModuleByName(llllllllllllllllIlIlIIIllllIIlll[1]);
        if (llllllllllllllllIlIlIIIllllIlIlI == null) {
            Command.sendChatMessage("Module not found!");
            return;
        }
        final Value llllllllllllllllIlIlIIIllllIlIIl = Xulu.VALUE_MANAGER.getValueByMod(llllllllllllllllIlIlIIIllllIlIlI, llllllllllllllllIlIlIIIllllIIlll[2]);
        if (llllllllllllllllIlIlIIIllllIlIIl == null) {
            Command.sendChatMessage("Setting not found!");
            return;
        }
        if (llllllllllllllllIlIlIIIllllIlIIl.getParentMod() != llllllllllllllllIlIlIIIllllIlIlI) {
            Command.sendChatMessage(String.valueOf(new StringBuilder().append(llllllllllllllllIlIlIIIllllIlIlI.getDisplayName()).append(" has no setting ").append(llllllllllllllllIlIlIIIllllIlIIl.getName())));
            return;
        }
        if (llllllllllllllllIlIlIIIllllIlIIl.isMode()) {
            SetBox.initTextBox(llllllllllllllllIlIlIIIllllIlIIl);
        }
        else {
            Command.sendChatMessage(String.valueOf(new StringBuilder().append(llllllllllllllllIlIlIIIllllIlIIl.getName()).append(" is not a text setting!")));
        }
    }
    
    public SetStringCommand() {
        super("setstring", "Sets a string easier", new String[0]);
    }
}
