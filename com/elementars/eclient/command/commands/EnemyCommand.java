package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import com.elementars.eclient.enemy.*;

public class EnemyCommand extends Command
{
    public EnemyCommand() {
        super("enemy", "adds or deletes enemies", new String[] { "add", "del" });
    }
    
    @Override
    public void execute(final String[] lllllllllllllllllIIIIllllllIIlII) {
        if (lllllllllllllllllIIIIllllllIIlII.length < 2) {
            Command.sendChatMessage("Try .enemy add or .enemy del");
            return;
        }
        if (lllllllllllllllllIIIIllllllIIlII[1].equalsIgnoreCase("help")) {
            this.showSyntax(lllllllllllllllllIIIIllllllIIlII[0]);
        }
        if (lllllllllllllllllIIIIllllllIIlII.length < 3) {
            Command.sendChatMessage("Specify a username");
            return;
        }
        final Enemy lllllllllllllllllIIIIllllllIIIll = new Enemy(lllllllllllllllllIIIIllllllIIlII[2]);
        if (lllllllllllllllllIIIIllllllIIlII[1].equalsIgnoreCase("add")) {
            if (!Enemies.getEnemies().contains((Object)lllllllllllllllllIIIIllllllIIIll)) {
                Enemies.addEnemy(lllllllllllllllllIIIIllllllIIIll.getUsername());
                Command.sendChatMessage(String.valueOf(new StringBuilder().append(lllllllllllllllllIIIIllllllIIIll.getUsername()).append(" is now an enemy")));
            }
            else {
                Command.sendChatMessage(String.valueOf(new StringBuilder().append(lllllllllllllllllIIIIllllllIIIll.getUsername()).append(" is already an enemy!")));
            }
        }
        else if (lllllllllllllllllIIIIllllllIIlII[1].equalsIgnoreCase("del")) {
            if (Enemies.getEnemyByName(lllllllllllllllllIIIIllllllIIIll.getUsername()) != null) {
                Enemies.delEnemy(lllllllllllllllllIIIIllllllIIIll.getUsername());
                Command.sendChatMessage(String.valueOf(new StringBuilder().append(lllllllllllllllllIIIIllllllIIIll.getUsername()).append(" is no longer an enemy")));
            }
            else {
                Command.sendChatMessage(String.valueOf(new StringBuilder().append(lllllllllllllllllIIIIllllllIIIll.getUsername()).append(" isn't an enemy")));
            }
        }
        else {
            Command.sendChatMessage(String.valueOf(new StringBuilder().append("Unknown attribute '").append(lllllllllllllllllIIIIllllllIIlII[1]).append("'")));
        }
    }
}
