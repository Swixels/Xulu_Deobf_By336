package com.elementars.eclient.util;

import java.util.*;
import java.net.*;
import com.google.gson.*;
import java.io.*;

public class PlayerIdentity implements Serializable
{
    private /* synthetic */ String stringUuid;
    private /* synthetic */ String displayName;
    private /* synthetic */ Long lastUpdated;
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public boolean shouldUpdate() {
        return System.currentTimeMillis() - this.lastUpdated >= 6.048E8;
    }
    
    private static String getName(final String lIIIIlIlIllIIII) {
        final LinkedHashMap<String, Long> lIIIIlIlIllIIIl = new LinkedHashMap<String, Long>();
        try {
            final URL lIIIIlIlIllllII = new URL(String.valueOf(new StringBuilder().append("https://api.mojang.com/user/profiles/").append(lIIIIlIlIllIIII.replace("-", "")).append("/names")));
            final URLConnection lIIIIlIlIlllIll = lIIIIlIlIllllII.openConnection();
            final BufferedReader lIIIIlIlIlllIlI = new BufferedReader(new InputStreamReader(lIIIIlIlIlllIll.getInputStream()));
            final StringBuilder lIIIIlIlIlllIIl = new StringBuilder();
            String lIIIIlIlIlllIII;
            while ((lIIIIlIlIlllIII = lIIIIlIlIlllIlI.readLine()) != null) {
                lIIIIlIlIlllIIl.append(String.valueOf(new StringBuilder().append(lIIIIlIlIlllIII).append("\n")));
            }
            final String lIIIIlIlIllIlll = String.valueOf(lIIIIlIlIlllIIl);
            lIIIIlIlIlllIlI.close();
            final JsonArray lIIIIlIlIllIllI = new JsonParser().parse(lIIIIlIlIllIlll).getAsJsonArray();
            final JsonObject lIIIIlIlIllIlIl = lIIIIlIlIllIllI.get(lIIIIlIlIllIllI.size() - 1).getAsJsonObject();
            final String lIIIIlIlIllIlII = lIIIIlIlIllIlIl.get("name").getAsString();
            try {
                lIIIIlIlIllIlIl.get("changedToAt");
                final Calendar lIIIIlIllIIIIlI = Calendar.getInstance();
                lIIIIlIllIIIIlI.setTimeInMillis(lIIIIlIlIllIlIl.get("changedToAt").getAsLong());
                final long lIIIIlIllIIIIIl = lIIIIlIlIllIlIl.get("changedToAt").getAsLong();
                final int lIIIIlIllIIIIII = lIIIIlIllIIIIlI.get(1);
                final int lIIIIlIlIllllll = lIIIIlIllIIIIlI.get(2);
                final int lIIIIlIlIlllllI = lIIIIlIllIIIIlI.get(5);
                lIIIIlIlIllIIIl.put(lIIIIlIlIllIlII, lIIIIlIllIIIIIl);
                return lIIIIlIlIllIlII;
            }
            catch (Exception lIIIIlIlIllllIl) {
                return lIIIIlIlIllIlII;
            }
        }
        catch (Exception lIIIIlIlIllIIll) {
            lIIIIlIlIllIIll.printStackTrace();
            System.out.print("fuck");
            return lIIIIlIlIllIIII;
        }
    }
    
    public PlayerIdentity(final String lIIIIlIllIlIlII) {
        final String lIIIIlIllIlIllI = lIIIIlIllIlIlII.replace("-", "");
        this.stringUuid = lIIIIlIllIlIlII;
        this.displayName = "Loading...";
        new Thread(() -> {
            this.displayName = getName(lIIIIlIllIlIllI);
            DataManager.identityCacheMap.put(this.getStringUuid(), this);
            try {
                DataManager.savePlayerIdentity(this, false);
            }
            catch (IOException lIIIIlIIlllIIII) {
                lIIIIlIIlllIIII.printStackTrace();
            }
            return;
        }).start();
        this.lastUpdated = System.currentTimeMillis();
    }
    
    public void updateDisplayName() {
        final PlayerIdentity lIIIIlIlIIIIIII;
        final PlayerIdentity lIIIIlIlIIIIIII2;
        new Thread(() -> {
            lIIIIlIlIIIIIII = new PlayerIdentity(this.stringUuid);
            this.displayName = lIIIIlIlIIIIIII.getDisplayName();
            this.lastUpdated = System.currentTimeMillis();
            lIIIIlIlIIIIIII2 = null;
        }).start();
    }
    
    public String getStringUuid() {
        return this.stringUuid;
    }
    
    private static String getDateFormat(final int lIIIIlIlIIlllIl, final int lIIIIlIlIIllIIl, final int lIIIIlIlIIllIII) {
        return String.valueOf(new StringBuilder().append(lIIIIlIlIIlllIl).append("/").append(lIIIIlIlIIllIIl).append("/").append(lIIIIlIlIIllIII));
    }
}
