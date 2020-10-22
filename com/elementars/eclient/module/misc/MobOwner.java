package com.elementars.eclient.module.misc;

import net.minecraft.entity.passive.*;
import java.net.*;
import java.io.*;
import net.minecraft.entity.*;
import com.elementars.eclient.command.*;
import com.google.gson.*;
import com.elementars.eclient.module.*;
import java.util.*;

public class MobOwner extends Module
{
    private /* synthetic */ Map<String, String> uuidToName;
    private /* synthetic */ List<AbstractHorse> mobs;
    
    private static String requestName(final String lIllIIllllIIIIl) {
        try {
            final String lIllIIllllIlIII = String.valueOf(new StringBuilder().append("https://api.mojang.com/user/profiles/").append(lIllIIllllIIIIl).append("/names"));
            final URL lIllIIllllIIlll = new URL(lIllIIllllIlIII);
            final HttpURLConnection lIllIIllllIIllI = (HttpURLConnection)lIllIIllllIIlll.openConnection();
            lIllIIllllIIllI.setConnectTimeout(5000);
            lIllIIllllIIllI.setRequestMethod("GET");
            final InputStream lIllIIllllIIlIl = new BufferedInputStream(lIllIIllllIIllI.getInputStream());
            final String lIllIIllllIIlII = convertStreamToString(lIllIIllllIIlIl);
            lIllIIllllIIlIl.close();
            lIllIIllllIIllI.disconnect();
            return lIllIIllllIIlII;
        }
        catch (Exception lIllIIllllIIIll) {
            lIllIIllllIIIll.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void onUpdate() {
        if (MobOwner.mc.world == null) {
            return;
        }
        for (final Entity lIllIIllllllIlI : MobOwner.mc.world.loadedEntityList) {
            if (!(lIllIIllllllIlI instanceof AbstractHorse)) {
                continue;
            }
            final AbstractHorse lIllIIlllllllII = (AbstractHorse)lIllIIllllllIlI;
            if (this.mobs.contains(lIllIIlllllllII)) {
                continue;
            }
            this.mobs.add(lIllIIlllllllII);
            final UUID lIllIIllllllIll = lIllIIlllllllII.getOwnerUniqueId();
            if (lIllIIllllllIll == null) {
                lIllIIlllllllII.setCustomNameTag("Not tamed");
            }
            else {
                final String lIllIIllllllllI = lIllIIllllllIll.toString().replace("-", "");
                String lIllIIlllllllIl = "";
                if (this.uuidToName.get(lIllIIlllllllIl) != null) {
                    lIllIIlllllllIl = this.uuidToName.get(lIllIIlllllllIl);
                }
                else {
                    try {
                        final String lIllIlIIIIIIIlI = requestName(lIllIIllllllllI);
                        final JsonElement lIllIlIIIIIIIIl = new JsonParser().parse(lIllIlIIIIIIIlI);
                        final JsonArray lIllIlIIIIIIIII = lIllIlIIIIIIIIl.getAsJsonArray();
                        if (lIllIlIIIIIIIII.size() == 0) {
                            Command.sendChatMessage("Couldn't find player name. (1)");
                            continue;
                        }
                        lIllIIlllllllIl = lIllIlIIIIIIIII.get(lIllIlIIIIIIIII.size() - 1).getAsJsonObject().get("name").getAsString();
                        this.uuidToName.put(lIllIIllllllllI, lIllIIlllllllIl);
                    }
                    catch (Exception lIllIIlllllllll) {
                        lIllIIlllllllll.printStackTrace();
                        Command.sendChatMessage("Couldn't find player name. (2)");
                        continue;
                    }
                }
                lIllIIlllllllII.setCustomNameTag(String.valueOf(new StringBuilder().append("Owner: ").append(lIllIIlllllllIl)));
            }
        }
    }
    
    public MobOwner() {
        super("MobOwner", "Displays the owners of tamed mobs", 0, Category.MISC, true);
        this.mobs = new ArrayList<AbstractHorse>();
        this.uuidToName = new HashMap<String, String>();
    }
    
    private static String convertStreamToString(final InputStream lIllIIlllIllIII) {
        final Scanner lIllIIlllIlIlll = new Scanner(lIllIIlllIllIII).useDelimiter("\\A");
        final String lIllIIlllIlIllI = lIllIIlllIlIlll.hasNext() ? lIllIIlllIlIlll.next() : "/";
        return lIllIIlllIlIllI;
    }
}
