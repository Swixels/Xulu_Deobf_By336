package com.elementars.eclient.util;

import com.elementars.eclient.module.misc.*;

public class AnnouncerUtil
{
    public static String getPickBlock() {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return "I just used pick block thanks to Xulu!";
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            return "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 \u05e7\u05d5\u05dc\u05d1 \u05e7\u05d9\u05e4\u05d1 \u05d9\u05ea\u05e9\u05de\u05ea\u05e9\u05d4 \u05e2\u05d2\u05e8\u05db";
        }
        return "null";
    }
    
    public static String getDropped(final String llllllllllllllllllllIlIlllllIIII) {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return String.valueOf(new StringBuilder().append("I just dropped a ").append(llllllllllllllllllllIlIlllllIIII).append(" thanks to Xulu!"));
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            final String llllllllllllllllllllIlIlllllIIlI = "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 <> \u05d9\u05ea\u05dc\u05e4\u05d4 \u05e2\u05d2\u05e8\u05db";
            return llllllllllllllllllllIlIlllllIIlI.replaceAll("<>", llllllllllllllllllllIlIlllllIIII);
        }
        return "null";
    }
    
    public static String getPlaced(final String llllllllllllllllllllIlIlllllIllI) {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return String.valueOf(new StringBuilder().append("I just placed a ").append(llllllllllllllllllllIlIlllllIllI).append(" block thanks to Xulu!"));
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            final String llllllllllllllllllllIlIllllllIII = "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 <> \u05d9\u05ea\u05d7\u05e0\u05d4 \u05e2\u05d2\u05e8\u05db";
            return llllllllllllllllllllIlIllllllIII.replaceAll("<>", llllllllllllllllllllIlIlllllIllI);
        }
        return "null";
    }
    
    public static String getRespawn() {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return "I just respawned thanks to Xulu!";
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            return "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 \u05d4\u05d9\u05d9\u05d7\u05ea\u05dc \u05d9\u05ea\u05de\u05e7 \u05e2\u05d2\u05e8\u05db";
        }
        return "null";
    }
    
    public static String getCraft(final String llllllllllllllllllllIllIIIIIlIII) {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return String.valueOf(new StringBuilder().append("I just crafted ").append(llllllllllllllllllllIllIIIIIlIII).append(" thanks to Xulu!"));
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            final String llllllllllllllllllllIllIIIIIlIlI = "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 <> \u05d9\u05ea\u05e8\u05e6\u05d9 \u05e2\u05d2\u05e8\u05db";
            return llllllllllllllllllllIllIIIIIlIlI.replaceAll("<>", llllllllllllllllllllIllIIIIIlIII);
        }
        return "null";
    }
    
    public static String getAttacked(final String llllllllllllllllllllIlIllllIIlII, final String llllllllllllllllllllIlIllllIIIIl) {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return String.valueOf(new StringBuilder().append("I just attacked ").append(llllllllllllllllllllIlIllllIIlII).append(" with a ").append(llllllllllllllllllllIlIllllIIIIl).append(" thanks to Xulu!"));
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            String llllllllllllllllllllIlIllllIIlIl = "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 :: \u05dd\u05e2 <> \u05ea\u05d0 \u05d9\u05ea\u05e4\u05e7\u05ea \u05e2\u05d2\u05e8\u05db";
            llllllllllllllllllllIlIllllIIlIl = llllllllllllllllllllIlIllllIIlIl.replaceAll("<>", llllllllllllllllllllIlIllllIIlII);
            return llllllllllllllllllllIlIllllIIlIl.replaceAll("::", llllllllllllllllllllIlIllllIIIIl);
        }
        return "null";
    }
    
    public static String getAte(final String llllllllllllllllllllIlIllllIlIll) {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return String.valueOf(new StringBuilder().append("I just ate a ").append(llllllllllllllllllllIlIllllIlIll).append(" thanks to Xulu!"));
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            final String llllllllllllllllllllIlIllllIllII = "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 <> \u05d9\u05ea\u05dc\u05db\u05d0 \u05e2\u05d2\u05e8\u05db";
            return llllllllllllllllllllIlIllllIllII.replaceAll("<>", llllllllllllllllllllIlIllllIlIll);
        }
        return "null";
    }
    
    public static String getPause() {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return "I just paused my game thanks to Xulu!";
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            return "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 \u05e7\u05d7\u05e9\u05de\u05dc \u05d6\u05d5\u05d0\u05e4 \u05d9\u05ea\u05d9\u05e9\u05e2 \u05e2\u05d2\u05e8\u05db";
        }
        return "null";
    }
    
    public static String getPerspectives() {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return "I just changed perspectives thanks to Xulu!";
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            return "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 \u05d8\u05d1\u05de \u05ea\u05d3\u05d5\u05e7\u05e0 \u05d9\u05ea\u05e4\u05dc\u05d7\u05d4 \u05e2\u05d2\u05e8\u05db";
        }
        return "null";
    }
    
    public static String getConsole() {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return "I just opened my console thanks to Xulu!";
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            return "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 \u05dc\u05d5\u05e1\u05e0\u05d5\u05e7\u05ea \u05d9\u05ea\u05d7\u05ea\u05e4 \u05e2\u05d2\u05e8\u05db";
        }
        return "null";
    }
    
    public static String getBlockBreak(final String llllllllllllllllllllIllIIIIlIlII) {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return String.valueOf(new StringBuilder().append("I just broke a ").append(llllllllllllllllllllIllIIIIlIlII).append(" thanks to Xulu!"));
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            final String llllllllllllllllllllIllIIIIlIllI = "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 <> \u05d9\u05ea\u05e8\u05d1\u05e9 \u05e2\u05d2\u05e8\u05db";
            return llllllllllllllllllllIllIIIIlIllI.replaceAll("<>", llllllllllllllllllllIllIIIIlIlII);
        }
        return "null";
    }
    
    public static String getSmelted(final String llllllllllllllllllllIlIlllllllII) {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return String.valueOf(new StringBuilder().append("I just smelted ").append(llllllllllllllllllllIlIlllllllII).append(" thanks to Xulu!"));
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            final String llllllllllllllllllllIlIllllllllI = "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 <> \u05d9\u05ea\u05db\u05ea\u05d4 \u05e2\u05d2\u05e8\u05db";
            return llllllllllllllllllllIlIllllllllI.replaceAll("<>", llllllllllllllllllllIlIlllllllII);
        }
        return "null";
    }
    
    public static String getChat() {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return "I just opened chat thanks to Xulu!";
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            return "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 \u05d8'\u05e6\u05d4 \u05ea\u05d0 \u05d9\u05ea\u05d7\u05ea\u05e4 \u05e2\u05d2\u05e8\u05db";
        }
        return "null";
    }
    
    public static String getCrouched() {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return "I just crouched thanks to Xulu!";
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            return "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 \u05d9\u05ea\u05e4\u05e4\u05d5\u05db\u05ea\u05d4 \u05e2\u05d2\u05e8\u05db";
        }
        return "null";
    }
    
    public static String getJumped() {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return "I just jumped thanks to Xulu!";
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            return "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 \u05d9\u05ea\u05e6\u05e4\u05e7 \u05e2\u05d2\u05e8\u05db";
        }
        return "null";
    }
    
    public static String getPlayerList() {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return "I just looked at the player list thanks to Xulu!";
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            return "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 \u05dd\u05d9\u05e0\u05e7\u05d7\u05e9\u05d4 \u05ea\u05de\u05d9\u05e9\u05e8 \u05dc\u05e2 \u05d9\u05ea\u05dc\u05db\u05ea\u05e1\u05d4 \u05e2\u05d2\u05e8\u05db";
        }
        return "null";
    }
    
    public static String getScreenShot() {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return "I just took a screen shot thanks to Xulu!";
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            return "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 \u05da\u05e1\u05de \u05dd\u05d5\u05dc\u05d9\u05e6 \u05d9\u05ea\u05d9\u05e9\u05e2 \u05e2\u05d2\u05e8\u05db";
        }
        return "null";
    }
    
    public static String getMove(final String llllllllllllllllllllIllIIIIIllll) {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return String.valueOf(new StringBuilder().append("I just moved ").append(llllllllllllllllllllIllIIIIIllll).append(" feet thanks to Xulu!"));
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            final String llllllllllllllllllllIllIIIIlIIII = "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 \u05d8\u05d9\u05e4 <> \u05d9\u05ea\u05d6\u05d6 \u05e2\u05d2\u05e8\u05db";
            return llllllllllllllllllllIllIIIIlIIII.replaceAll("<>", llllllllllllllllllllIllIIIIIllll);
        }
        return "null";
    }
    
    public static String getInventory() {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return "I just opened my inventory thanks to Xulu!";
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            return "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 \u05d9\u05dc\u05e9 \u05d9\u05e8\u05d5\u05d8\u05e0\u05d1\u05e0\u05d9\u05d0\u05d4 \u05ea\u05d0 \u05d9\u05ea\u05d7\u05ea\u05e4 \u05e2\u05d2\u05e8\u05db";
        }
        return "null";
    }
    
    public static String getFullScreen() {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return "I just turned full screen mode thanks to Xulu!";
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            return "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 \u05df\u05d9\u05e8\u05e7\u05e1 \u05dc\u05d5\u05e4 \u05d9\u05ea\u05d9\u05e9\u05e2 \u05e2\u05d2\u05e8\u05db";
        }
        return "null";
    }
    
    public static String getPickedUp(final String llllllllllllllllllllIllIIIIIIIlI) {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return String.valueOf(new StringBuilder().append("I just picked up a ").append(llllllllllllllllllllIllIIIIIIIlI).append(" thanks to Xulu!"));
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            final String llllllllllllllllllllIllIIIIIIlII = "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 <> \u05d9\u05ea\u05de\u05e8\u05d4 \u05e2\u05d2\u05e8\u05db";
            return llllllllllllllllllllIllIIIIIIlII.replaceAll("<>", llllllllllllllllllllIllIIIIIIIlI);
        }
        return "null";
    }
    
    public static String getSwappedHands() {
        if (Announcer.mode.getValue().equalsIgnoreCase("English")) {
            return "I just swapped hands thanks to Xulu!";
        }
        if (Announcer.mode.getValue().equalsIgnoreCase("Hebrew")) {
            return "!Xulu \u05ea\u05d5\u05db\u05d6\u05d1 \u05dd\u05d9\u05d9\u05d3\u05d9 \u05d9\u05ea\u05e4\u05dc\u05d7\u05d4 \u05e2\u05d2\u05e8\u05db";
        }
        return "null";
    }
}
