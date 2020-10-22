package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import com.elementars.eclient.module.player.*;

public class LogspotCommand extends Command
{
    @Override
    public void execute(final String[] lllIlIlIllIIlII) {
        if (lllIlIlIllIIlII.length == 1) {
            Command.sendChatMessage("Please specify a server IP");
            return;
        }
        if (SelfLogoutSpot.INSTANCE.logoutMap.isEmpty() || SelfLogoutSpot.INSTANCE.logoutMap.get(lllIlIlIllIIlII[1]) == null) {
            Command.sendChatMessage("Your logout spot is not saved for that server!");
        }
        else {
            Command.sendChatMessage(String.valueOf(new StringBuilder().append("Your logout spot is - ").append(SelfLogoutSpot.INSTANCE.logoutMap.get(lllIlIlIllIIlII[1]))));
        }
    }
    
    public LogspotCommand() {
        super("logspot", "Shows your logout spot from a given server", new String[0]);
    }
}
