package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;

public class HelpCommand extends Command
{
    public HelpCommand() {
        super("help", "Shows a list of commands", new String[0]);
    }
    
    @Override
    public void execute(final String[] llllllllllllllllIllIllIlIIIlIIII) {
        if (llllllllllllllllIllIllIlIIIlIIII.length > 1 && llllllllllllllllIllIllIlIIIlIIII[1].equalsIgnoreCase("help")) {
            this.showSyntax(llllllllllllllllIllIllIlIIIlIIII[0]);
            return;
        }
        Command.sendChatMessage("Here's a list of commands:");
        for (final Command llllllllllllllllIllIllIlIIIlIIlI : CommandManager.getCommands()) {
            Command.sendChatMessage(String.valueOf(new StringBuilder().append(llllllllllllllllIllIllIlIIIlIIlI.getName()).append(" : ").append(llllllllllllllllIllIllIlIIIlIIlI.getDescription())));
        }
        Command.sendChatMessage("Follow any command with help to see command options");
    }
}
