package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import com.elementars.eclient.module.player.*;

public class AntiVoidCommand extends Command
{
    @Override
    public void execute(final String[] lIllllIIlIIIllI) {
        if (lIllllIIlIIIllI.length == 1) {
            Command.sendChatMessage("Please specify a server IP");
            return;
        }
        if (AntiVoid.INSTANCE.ipList.isEmpty() || !AntiVoid.INSTANCE.ipList.contains(lIllllIIlIIIllI[1])) {
            Command.sendChatMessage("You did not trigger AntiVoid on this server!");
        }
        else {
            Command.sendChatMessage("You did fall below the Y level in AntiVoid! Be careful!");
        }
    }
    
    public AntiVoidCommand() {
        super("antivoid", "Shows if you have logged from antivoid on a server", new String[0]);
    }
}
