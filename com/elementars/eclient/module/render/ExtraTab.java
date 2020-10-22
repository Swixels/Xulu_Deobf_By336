package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import net.minecraft.client.network.*;
import net.minecraft.scoreboard.*;
import com.elementars.eclient.friend.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.command.*;
import com.elementars.eclient.enemy.*;
import com.elementars.eclient.module.*;

public class ExtraTab extends Module
{
    public final /* synthetic */ Value<String> color;
    public static /* synthetic */ ExtraTab INSTANCE;
    public final /* synthetic */ Value<String> ecolor;
    public final /* synthetic */ Value<Integer> tabSize;
    
    public static String getPlayerName(final NetworkPlayerInfo lIlllIlIIllIIIl) {
        final String lIlllIlIIllIIll = (lIlllIlIIllIIIl.getDisplayName() != null) ? lIlllIlIIllIIIl.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName((Team)lIlllIlIIllIIIl.getPlayerTeam(), lIlllIlIIllIIIl.getGameProfile().getName());
        if (Friends.isFriend(lIlllIlIIllIIll)) {
            return String.format(String.valueOf(new StringBuilder().append("%s").append(ColorTextUtils.getColor(ExtraTab.INSTANCE.color.getValue()).substring(1)).append("%s")), Command.SECTIONSIGN(), lIlllIlIIllIIll);
        }
        if (Enemies.isEnemy(lIlllIlIIllIIll)) {
            return String.format(String.valueOf(new StringBuilder().append("%s").append(ColorTextUtils.getColor(ExtraTab.INSTANCE.ecolor.getValue()).substring(1)).append("%s")), Command.SECTIONSIGN(), lIlllIlIIllIIll);
        }
        return lIlllIlIIllIIll;
    }
    
    public ExtraTab() {
        super("ExtraTab", "Expands tab menu", 0, Category.RENDER, true);
        this.tabSize = this.register(new Value<Integer>("Players", this, 80, 1, 1000));
        this.color = this.register(new Value<String>("Friend Color", this, "LightGreen", ColorTextUtils.colors));
        this.ecolor = this.register(new Value<String>("Enemy Color", this, "LightRed", ColorTextUtils.colors));
        ExtraTab.INSTANCE = this;
    }
}
