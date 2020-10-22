package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;

public class CreditsCommand extends Command
{
    /* synthetic */ String[] credits;
    
    @Override
    public void execute(final String[] lllllllllllllllllllllllIlIlIllll) {
        if (lllllllllllllllllllllllIlIlIllll.length > 1 && lllllllllllllllllllllllIlIlIllll[1].equalsIgnoreCase("help")) {
            this.showSyntax(lllllllllllllllllllllllIlIlIllll[0]);
            return;
        }
        Command.sendChatMessage("Here's a list of people who helped brainstorm ideas for the client:");
        String lllllllllllllllllllllllIlIlIlllI = "";
        boolean lllllllllllllllllllllllIlIlIllIl = true;
        final byte lllllllllllllllllllllllIlIlIlIII = (Object)this.credits;
        for (float lllllllllllllllllllllllIlIlIIlll = lllllllllllllllllllllllIlIlIlIII.length, lllllllllllllllllllllllIlIlIIllI = 0; lllllllllllllllllllllllIlIlIIllI < lllllllllllllllllllllllIlIlIIlll; ++lllllllllllllllllllllllIlIlIIllI) {
            final String lllllllllllllllllllllllIlIllIIIl = lllllllllllllllllllllllIlIlIlIII[lllllllllllllllllllllllIlIlIIllI];
            if (lllllllllllllllllllllllIlIlIllIl) {
                lllllllllllllllllllllllIlIlIlllI = lllllllllllllllllllllllIlIllIIIl;
            }
            else {
                lllllllllllllllllllllllIlIlIlllI = String.valueOf(new StringBuilder().append(lllllllllllllllllllllllIlIlIlllI).append(", ").append(lllllllllllllllllllllllIlIllIIIl));
            }
            lllllllllllllllllllllllIlIlIllIl = false;
        }
        Command.sendChatMessage(lllllllllllllllllllllllIlIlIlllI);
    }
    
    public CreditsCommand() {
        super("credits", "Shows the people who helped come up with ideas for modules and ect.", new String[0]);
        this.credits = new String[] { "Sago", "WeWide", "Nemac", "Jumpy/Xdolf", "Naughty", "John", "Mtnl" };
    }
}
