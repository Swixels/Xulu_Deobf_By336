package com.elementars.eclient.util;

import com.elementars.eclient.module.misc.Announcer;

public class AnnouncerUtil {
   public static String getPickBlock() {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return ">I just used pick block!";
      } else {
         return ((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew") ? "!Xulu תוכזב קולב קיפב יתשמתשה עגרכ" : "null";
      }
   }

   public static String getDropped(String var0) {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return String.valueOf((new StringBuilder()).append(">I just dropped a ").append(var0).append("!"));
      } else if (((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew")) {
         String var1 = "!Xulu תוכזב <> יתלפה עגרכ";
         return var1.replaceAll("<>", var0);
      } else {
         return "null";
      }
   }

   public static String getPlaced(String var0) {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return String.valueOf((new StringBuilder()).append(">I just placed a ").append(var0).append(" block!"));
      } else if (((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew")) {
         String var1 = "!Xulu תוכזב <> יתחנה עגרכ";
         return var1.replaceAll("<>", var0);
      } else {
         return "null";
      }
   }

   public static String getRespawn() {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return ">I just respawned!";
      } else {
         return ((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew") ? "!Xulu תוכזב הייחתל יתמק עגרכ" : "null";
      }
   }

   public static String getCraft(String var0) {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return String.valueOf((new StringBuilder()).append(>"I just crafted ").append(var0).append("!"));
      } else if (((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew")) {
         String var1 = "!Xulu תוכזב <> יתרצי עגרכ";
         return var1.replaceAll("<>", var0);
      } else {
         return "null";
      }
   }

   public static String getAttacked(String var0, String var1) {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return String.valueOf((new StringBuilder()).append(">I just attacked ").append(var0).append(" with a ").append(var1).append("!"));
      } else if (((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew")) {
         String var2 = "!Xulu תוכזב :: םע <> תא יתפקת עגרכ";
         var2 = var2.replaceAll("<>", var0);
         return var2.replaceAll("::", var1);
      } else {
         return "null";
      }
   }

   public static String getAte(String var0) {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return String.valueOf((new StringBuilder()).append(">I just ate a ").append(var0).append("!"));
      } else if (((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew")) {
         String var1 = "!Xulu תוכזב <> יתלכא עגרכ";
         return var1.replaceAll("<>", var0);
      } else {
         return "null";
      }
   }

   public static String getPause() {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return ">I just paused my game!";
      } else {
         return ((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew") ? "!Xulu תוכזב קחשמל זואפ יתישע עגרכ" : "null";
      }
   }

   public static String getPerspectives() {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return ">I just changed perspectives!";
      } else {
         return ((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew") ? "!Xulu תוכזב טבמ תדוקנ יתפלחה עגרכ" : "null";
      }
   }

   public static String getConsole() {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return ">I just opened my console!";
      } else {
         return ((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew") ? "!Xulu תוכזב לוסנוקת יתחתפ עגרכ" : "null";
      }
   }

   public static String getBlockBreak(String var0) {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return String.valueOf((new StringBuilder()).append(">I just broke a ").append(var0).append("!"));
      } else if (((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew")) {
         String var1 = "!Xulu תוכזב <> יתרבש עגרכ";
         return var1.replaceAll("<>", var0);
      } else {
         return "null";
      }
   }

   public static String getSmelted(String var0) {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return String.valueOf((new StringBuilder()).append(">I just smelted ").append(var0).append("!"));
      } else if (((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew")) {
         String var1 = "!Xulu תוכזב <> יתכתה עגרכ";
         return var1.replaceAll("<>", var0);
      } else {
         return "null";
      }
   }

   public static String getChat() {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return ">I just opened chat!";
      } else {
         return ((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew") ? "!Xulu תוכזב ט'צה תא יתחתפ עגרכ" : "null";
      }
   }

   public static String getCrouched() {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return ">I just crouched!";
      } else {
         return ((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew") ? "!Xulu תוכזב יתפפוכתה עגרכ" : "null";
      }
   }

   public static String getJumped() {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return ">I just jumped!";
      } else {
         return ((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew") ? "!Xulu תוכזב יתצפק עגרכ" : "null";
      }
   }

   public static String getPlayerList() {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return ">I just looked at the player list!";
      } else {
         return ((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew") ? "!Xulu תוכזב םינקחשה תמישר לע יתלכתסה עגרכ" : "null";
      }
   }

   public static String getScreenShot() {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return ">I just took a screen shot!";
      } else {
         return ((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew") ? "!Xulu תוכזב ךסמ םוליצ יתישע עגרכ" : "null";
      }
   }

   public static String getMove(String var0) {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return String.valueOf((new StringBuilder()).append(">I just moved ").append(var0).append(" feet!"));
      } else if (((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew")) {
         String var1 = "!Xulu תוכזב טיפ <> יתזז עגרכ";
         return var1.replaceAll("<>", var0);
      } else {
         return "null";
      }
   }

   public static String getInventory() {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return ">I just opened my inventory!";
      } else {
         return ((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew") ? "!Xulu תוכזב ילש ירוטנבניאה תא יתחתפ עגרכ" : "null";
      }
   }

   public static String getFullScreen() {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return ">I just turned full screen mode!";
      } else {
         return ((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew") ? "!Xulu תוכזב ןירקס לופ יתישע עגרכ" : "null";
      }
   }

   public static String getPickedUp(String var0) {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return String.valueOf((new StringBuilder()).append(">I just picked up a ").append(var0).append("!"));
      } else if (((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew")) {
         String var1 = "!Xulu תוכזב <> יתמרה עגרכ";
         return var1.replaceAll("<>", var0);
      } else {
         return "null";
      }
   }

   public static String getSwappedHands() {
      if (((String)Announcer.mode.getValue()).equalsIgnoreCase("English")) {
         return ">I just swapped hands!";
      } else {
         return ((String)Announcer.mode.getValue()).equalsIgnoreCase("Hebrew") ? "!Xulu תוכזב םיידי יתפלחה עגרכ" : "null";
      }
   }
}
