package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;

public class PrefixCommand extends Command
{
    public PrefixCommand() {
        super("prefix", "Changes the prefix for commands", new String[0]);
    }
    
    @Override
    public void execute(final String[] llllllllllllllllIlIlIIllIlIllIll) {
        if (llllllllllllllllIlIlIIllIlIllIll.length < 2) {
            Command.sendChatMessage("Specify what prefix you would like to change to.");
            Command.sendChatMessage(String.valueOf(new StringBuilder().append("Current prefix is: ").append(Command.getPrefix())));
            return;
        }
        if (llllllllllllllllIlIlIIllIlIllIll[1].equalsIgnoreCase("help")) {
            this.showSyntax(llllllllllllllllIlIlIIllIlIllIll[0]);
            return;
        }
        Command.setPrefix(llllllllllllllllIlIlIIllIlIllIll[1]);
        Command.sendChatMessage(String.valueOf(new StringBuilder().append("Set the prefix to: ").append(Command.getPrefix())));
    }
}
