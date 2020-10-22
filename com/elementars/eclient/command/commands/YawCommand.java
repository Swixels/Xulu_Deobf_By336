package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import com.elementars.eclient.util.*;

public class YawCommand extends Command
{
    public YawCommand() {
        super("setyaw", "Sets the yaw of the player", new String[0]);
    }
    
    @Override
    public void execute(final String[] lllllllllllllllllIlIIlIIIlIllIII) {
        if (lllllllllllllllllIlIIlIIIlIllIII.length < 2) {
            Command.sendChatMessage("Please specify the yaw!");
            return;
        }
        Wrapper.getMinecraft().player.rotationYaw = Integer.valueOf(lllllllllllllllllIlIIlIIIlIllIII[1]);
    }
}
