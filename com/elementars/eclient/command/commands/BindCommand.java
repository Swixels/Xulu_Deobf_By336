package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import com.elementars.eclient.*;
import org.lwjgl.input.*;
import com.elementars.eclient.module.*;

public class BindCommand extends Command
{
    @Override
    public void execute(final String[] lllllllllllllllllllIIIlllIlllIII) {
        if (lllllllllllllllllllIIIlllIlllIII.length < 2) {
            Command.sendChatMessage("Please specify which module you want bound");
            return;
        }
        if (lllllllllllllllllllIIIlllIlllIII[1].equalsIgnoreCase("help")) {
            this.showSyntax(lllllllllllllllllllIIIlllIlllIII[0]);
            return;
        }
        if (lllllllllllllllllllIIIlllIlllIII.length < 3) {
            Command.sendChatMessage("Please specify the key you would like to bind");
            return;
        }
        final Module lllllllllllllllllllIIIlllIllIlll = Xulu.MODULE_MANAGER.getModuleByName(lllllllllllllllllllIIIlllIlllIII[1]);
        if (lllllllllllllllllllIIIlllIllIlll == null) {
            Command.sendChatMessage("Module not found.");
            return;
        }
        lllllllllllllllllllIIIlllIllIlll.setKey(Keyboard.getKeyIndex(lllllllllllllllllllIIIlllIlllIII[2].toUpperCase()));
        Command.sendChatMessage(String.valueOf(new StringBuilder().append(lllllllllllllllllllIIIlllIllIlll.getDisplayName()).append(" bound to ").append(lllllllllllllllllllIIIlllIlllIII[2].toUpperCase())));
    }
    
    public BindCommand() {
        super("bind", "binds a module to a key", new String[0]);
    }
}
