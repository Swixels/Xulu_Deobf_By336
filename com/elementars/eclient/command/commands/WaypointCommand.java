package com.elementars.eclient.command.commands;

import com.elementars.eclient.command.*;
import com.elementars.eclient.module.render.*;
import java.util.*;
import net.minecraft.util.math.*;

public class WaypointCommand extends Command
{
    public WaypointCommand() {
        super("waypoints", "Manages Waypoints", new String[] { "add", "remove" });
    }
    
    @Override
    public void execute(final String[] llllllllllllllllIllIIIIIlllIIIII) {
        if (llllllllllllllllIllIIIIIlllIIIII.length < 2) {
            Command.sendChatMessage("Use .waypoints help to see commands");
            return;
        }
        if (!llllllllllllllllIllIIIIIlllIIIII[1].equalsIgnoreCase("add")) {
            if (llllllllllllllllIllIIIIIlllIIIII[1].equalsIgnoreCase("remove")) {
                if (llllllllllllllllIllIIIIIlllIIIII.length < 3) {
                    Command.sendChatMessage("Please specify the name of the waypoint (.waypoints remove name)");
                    return;
                }
                Waypoints.WAYPOINTS.removeIf(llllllllllllllllIllIIIIIllIlIlll -> llllllllllllllllIllIIIIIllIlIlll.getName().equalsIgnoreCase(llllllllllllllllIllIIIIIlllIIIII[2]));
                Command.sendChatMessage(String.valueOf(new StringBuilder().append("Removed waypoint(s) with the name: ").append(llllllllllllllllIllIIIIIlllIIIII[2])));
            }
            return;
        }
        if (llllllllllllllllIllIIIIIlllIIIII.length < 3) {
            Command.sendChatMessage("Please specify the name of the waypoint (.waypoints add name (X) (Y) (Z))");
            return;
        }
        if (llllllllllllllllIllIIIIIlllIIIII.length < 6) {
            Command.sendChatMessage("Please specify coordinates (.waypoints add name (X) (Y) (Z))");
            return;
        }
        final int llllllllllllllllIllIIIIIlllIIlIl = (int)Double.parseDouble(llllllllllllllllIllIIIIIlllIIIII[3]);
        final int llllllllllllllllIllIIIIIlllIIlII = (int)Double.parseDouble(llllllllllllllllIllIIIIIlllIIIII[4]);
        final int llllllllllllllllIllIIIIIlllIIIll = (int)Double.parseDouble(llllllllllllllllIllIIIIIlllIIIII[5]);
        Waypoints.WAYPOINTS.add(new Waypoints.Waypoint(UUID.randomUUID(), llllllllllllllllIllIIIIIlllIIIII[2], new BlockPos(llllllllllllllllIllIIIIIlllIIlIl, llllllllllllllllIllIIIIIlllIIlII, llllllllllllllllIllIIIIIlllIIIll), WaypointCommand.mc.player.getEntityBoundingBox(), WaypointCommand.mc.player.dimension));
        Command.sendChatMessage(String.valueOf(new StringBuilder().append("Added waypoint with the name: ").append(llllllllllllllllIllIIIIIlllIIIII[2])));
    }
}
