package dev.xulu.settings;

import dev.xulu.newgui.*;
import com.elementars.eclient.*;
import com.elementars.eclient.font.*;
import com.elementars.eclient.command.*;
import net.minecraft.util.math.*;
import com.elementars.eclient.guirewrite.elements.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import com.elementars.eclient.guirewrite.*;
import com.elementars.eclient.module.render.*;
import com.elementars.eclient.macro.*;
import com.elementars.eclient.module.*;
import java.nio.charset.*;
import java.io.*;
import java.nio.file.*;
import com.elementars.eclient.enemy.*;
import java.text.*;
import java.util.*;
import com.elementars.eclient.friend.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.dummy.*;

public class FileManager
{
    public /* synthetic */ File EclientSettings;
    public /* synthetic */ File Eclient;
    public /* synthetic */ File EclientStorageESP;
    
    public void loadFriends() {
        try {
            final File llIlIIlIlIlllIl = new File(this.Eclient.getAbsolutePath(), "Friends.txt");
            final FileInputStream llIlIIlIlIlllII = new FileInputStream(llIlIIlIlIlllIl.getAbsolutePath());
            final DataInputStream llIlIIlIlIllIll = new DataInputStream(llIlIIlIlIlllII);
            final BufferedReader llIlIIlIlIllIlI = new BufferedReader(new InputStreamReader(llIlIIlIlIllIll));
            String llIlIIlIlIllIIl;
            while ((llIlIIlIlIllIIl = llIlIIlIlIllIlI.readLine()) != null) {
                try {
                    Friends.addFriend(llIlIIlIlIllIIl);
                }
                catch (Exception llIlIIlIlIlIIII) {}
            }
            llIlIIlIlIllIlI.close();
        }
        catch (Exception llIlIIlIlIllIII) {
            llIlIIlIlIllIII.printStackTrace();
            this.saveFriends();
        }
    }
    
    public void saveWelcomeMessage() {
        try {
            final File llIlIIllllIlIlI = new File(this.Eclient.getAbsolutePath(), "Welcome.txt");
            final BufferedWriter llIlIIllllIlIIl = new BufferedWriter(new FileWriter(llIlIIllllIlIlI));
            llIlIIllllIlIIl.write(String.valueOf(new StringBuilder().append(Welcome.text).append("\r\n")));
            llIlIIllllIlIIl.close();
        }
        catch (Exception ex) {}
    }
    
    public void saveStorageESP(final String llIlIlIlIlIllll, final String llIlIlIlIllIIlI, final String llIlIlIlIllIIIl) {
        try {
            final File llIlIlIlIllIlll = new File(this.EclientStorageESP.getAbsolutePath(), String.valueOf(new StringBuilder().append(llIlIlIlIlIllll).append(".txt")));
            final BufferedWriter llIlIlIlIllIllI = new BufferedWriter(new FileWriter(llIlIlIlIllIlll));
            llIlIlIlIllIllI.write(String.valueOf(new StringBuilder().append(llIlIlIlIllIIlI).append(" with ").append(llIlIlIlIllIIIl).append(" chests")));
            llIlIlIlIllIllI.close();
        }
        catch (Exception llIlIlIlIllIlIl) {
            llIlIlIlIllIlIl.printStackTrace();
        }
    }
    
    public void saveNewGui() {
        try {
            final File llIlIIIlllllllI = new File(this.Eclient.getAbsolutePath(), "NewGui.txt");
            final BufferedWriter llIlIIIllllllIl = new BufferedWriter(new FileWriter(llIlIIIlllllllI));
            for (final Panel llIlIIIllllllll : NewGUI.panels) {
                try {
                    llIlIIIllllllIl.write(String.valueOf(new StringBuilder().append(llIlIIIllllllll.title).append(":").append(llIlIIIllllllll.x).append(":").append(llIlIIIllllllll.y).append(":").append(llIlIIIllllllll.extended)));
                    llIlIIIllllllIl.write("\r\n");
                }
                catch (Exception ex) {}
            }
            llIlIIIllllllIl.close();
        }
        catch (Exception ex2) {}
    }
    
    public void loadHacks() {
        try {
            final File llIIllllllIIlll = new File(this.Eclient.getAbsolutePath(), "Modules.txt");
            final FileInputStream llIIllllllIIllI = new FileInputStream(llIIllllllIIlll.getAbsolutePath());
            final DataInputStream llIIllllllIIlIl = new DataInputStream(llIIllllllIIllI);
            final BufferedReader llIIllllllIIlII = new BufferedReader(new InputStreamReader(llIIllllllIIlIl));
            String llIIllllllIIIll;
            while ((llIIllllllIIIll = llIIllllllIIlII.readLine()) != null) {
                for (final Module llIIllllllIlIII : Xulu.MODULE_MANAGER.getModules()) {
                    try {
                        if (llIIllllllIlIII.getName().equals(llIIllllllIIIll)) {
                            llIIllllllIlIII.initToggle(true);
                        }
                        if (!(llIIllllllIlIII instanceof Element)) {
                            continue;
                        }
                        ((Element)llIIllllllIlIII).getFrame().pinned = true;
                    }
                    catch (Exception ex) {}
                }
            }
            llIIllllllIIlII.close();
        }
        catch (Exception llIIllllllIIIlI) {
            llIIllllllIIIlI.printStackTrace();
            this.saveHacks();
        }
    }
    
    public void saveFont() {
        try {
            final File llIlIlIIlIIlIIl = new File(this.Eclient.getAbsolutePath(), "Font.txt");
            final BufferedWriter llIlIlIIlIIlIII = new BufferedWriter(new FileWriter(llIlIlIIlIIlIIl));
            llIlIlIIlIIlIII.write(String.valueOf(new StringBuilder().append("Normal:").append(CFontManager.customFont.getFont().getFontName().equalsIgnoreCase("Comfortaa Regular") ? "Comfortaa" : CFontManager.customFont.getFont().getFontName()).append(":").append(CFontManager.customFont.getFont().getSize()).append(":").append(CFontManager.customFont.getFont().getStyle()).append(":").append(CFontManager.customFont.isAntiAlias()).append(":").append(CFontManager.customFont.isFractionalMetrics()).append("\r\n")));
            llIlIlIIlIIlIII.write(String.valueOf(new StringBuilder().append("Xdolf:").append(CFontManager.xFontRenderer.getFont().getFont().getFontName().equalsIgnoreCase("Comfortaa Regular") ? "Comfortaa" : CFontManager.xFontRenderer.getFont().getFont().getFontName()).append(":").append(CFontManager.xFontRenderer.getFont().getFont().getSize()).append(":").append(CFontManager.xFontRenderer.getFont().getFont().getStyle()).append(":").append(CFontManager.xFontRenderer.isAntiAliasing()).append(":NULL\r\n")));
            llIlIlIIlIIlIII.close();
        }
        catch (Exception ex) {}
    }
    
    public void savePrefix() {
        try {
            final File llIlIIIlIIIlIll = new File(this.Eclient.getAbsolutePath(), "Prefix.txt");
            final BufferedWriter llIlIIIlIIIlIlI = new BufferedWriter(new FileWriter(llIlIIIlIIIlIll));
            llIlIIIlIIIlIlI.write(Command.getPrefix());
            llIlIIIlIIIlIlI.write("\r\n");
            llIlIIIlIIIlIlI.close();
        }
        catch (Exception ex) {}
    }
    
    public void loadFont() {
        try {
            final File llIlIlIIIlIIlll = new File(this.Eclient.getAbsolutePath(), "Font.txt");
            final FileInputStream llIlIlIIIlIIllI = new FileInputStream(llIlIlIIIlIIlll.getAbsolutePath());
            final DataInputStream llIlIlIIIlIIlIl = new DataInputStream(llIlIlIIIlIIllI);
            final BufferedReader llIlIlIIIlIIlII = new BufferedReader(new InputStreamReader(llIlIlIIIlIIlIl));
            String llIlIlIIIlIIIll;
            while ((llIlIlIIIlIIIll = llIlIlIIIlIIlII.readLine()) != null) {
                try {
                    final String llIlIlIIIllIIlI = llIlIlIIIlIIIll.trim();
                    final String llIlIlIIIllIIIl = llIlIlIIIllIIlI.split(":")[0];
                    final String llIlIlIIIllIIII = llIlIlIIIllIIlI.split(":")[1];
                    final String llIlIlIIIlIllll = llIlIlIIIllIIlI.split(":")[2];
                    final String llIlIlIIIlIlllI = llIlIlIIIllIIlI.split(":")[3];
                    final String llIlIlIIIlIllIl = llIlIlIIIllIIlI.split(":")[4];
                    final String llIlIlIIIlIllII = llIlIlIIIllIIlI.split(":")[5];
                    final int llIlIlIIIlIlIll = Integer.parseInt(llIlIlIIIlIllll);
                    final int llIlIlIIIlIlIlI = Integer.parseInt(llIlIlIIIlIlllI);
                    final boolean llIlIlIIIlIlIIl = Boolean.parseBoolean(llIlIlIIIlIllIl);
                    final boolean llIlIlIIIlIlIII = !llIlIlIIIlIllII.equalsIgnoreCase("null") && Boolean.parseBoolean(llIlIlIIIlIllII);
                    if (llIlIlIIIllIIIl.equalsIgnoreCase("Normal")) {
                        FontHelper.setCFontRenderer(llIlIlIIIllIIII, llIlIlIIIlIlIlI, llIlIlIIIlIlIll, llIlIlIIIlIlIIl, llIlIlIIIlIlIII);
                    }
                    else if (llIlIlIIIllIIIl.equalsIgnoreCase("Xdolf")) {
                        FontHelper.setXdolfFontRenderer(llIlIlIIIllIIII, llIlIlIIIlIlIlI, llIlIlIIIlIlIll, llIlIlIIIlIlIIl);
                    }
                    else {
                        System.out.println("Invalid Font Type!");
                    }
                }
                catch (Exception ex) {}
            }
            llIlIlIIIlIIlII.close();
        }
        catch (Exception llIlIlIIIlIIIlI) {
            llIlIlIIIlIIIlI.printStackTrace();
            this.saveFont();
        }
    }
    
    public void loadWaypoints() {
        try {
            final File llIlIIIIlIIlIlI = new File(this.Eclient.getAbsolutePath(), "Waypoints.txt");
            final FileInputStream llIlIIIIlIIlIIl = new FileInputStream(llIlIIIIlIIlIlI.getAbsolutePath());
            final DataInputStream llIlIIIIlIIlIII = new DataInputStream(llIlIIIIlIIlIIl);
            final BufferedReader llIlIIIIlIIIlll = new BufferedReader(new InputStreamReader(llIlIIIIlIIlIII));
            String llIlIIIIlIIIllI;
            while ((llIlIIIIlIIIllI = llIlIIIIlIIIlll.readLine()) != null) {
                try {
                    final String llIlIIIIlIlIlII = llIlIIIIlIIIllI.trim();
                    final String llIlIIIIlIlIIll = llIlIIIIlIlIlII.split(":")[0];
                    final String llIlIIIIlIlIIlI = llIlIIIIlIlIlII.split(":")[1];
                    final String llIlIIIIlIlIIIl = llIlIIIIlIlIlII.split(":")[2];
                    final String llIlIIIIlIlIIII = llIlIIIIlIlIlII.split(":")[3];
                    final String llIlIIIIlIIllll = llIlIIIIlIlIlII.split(":")[4];
                    final int llIlIIIIlIIlllI = Integer.parseInt(llIlIIIIlIlIIlI);
                    final int llIlIIIIlIIllIl = Integer.parseInt(llIlIIIIlIlIIIl);
                    final int llIlIIIIlIIllII = Integer.parseInt(llIlIIIIlIlIIII);
                    final int llIlIIIIlIIlIll = Integer.parseInt(llIlIIIIlIIllll);
                    Waypoints.WAYPOINTS.add(new Waypoints.Waypoint(UUID.randomUUID(), llIlIIIIlIlIIll, new BlockPos(llIlIIIIlIIlllI, llIlIIIIlIIllIl, llIlIIIIlIIllII), null, llIlIIIIlIIlIll));
                }
                catch (Exception llIlIIIIIllllIl) {}
            }
            llIlIIIIlIIIlll.close();
        }
        catch (Exception llIlIIIIlIIIlIl) {
            llIlIIIIlIIIlIl.printStackTrace();
            this.saveDrawn();
        }
    }
    
    public FileManager() {
        this.Eclient = new File(String.valueOf(new StringBuilder().append(Wrapper.getMinecraft().gameDir).append(File.separator).append("Xulu")));
        if (!this.Eclient.exists()) {
            this.Eclient.mkdirs();
        }
        this.EclientSettings = new File(String.valueOf(new StringBuilder().append(Wrapper.getMinecraft().gameDir).append(File.separator).append("Xulu").append(File.separator).append("Xulu Settings")));
        if (!this.EclientSettings.exists()) {
            this.EclientSettings.mkdirs();
        }
        this.EclientStorageESP = new File(String.valueOf(new StringBuilder().append(Wrapper.getMinecraft().gameDir).append(File.separator).append("Xulu").append(File.separator).append("StorageESP Logs")));
        if (!this.EclientStorageESP.exists()) {
            this.EclientStorageESP.mkdirs();
        }
    }
    
    public void loadWelcomeMessage() {
        try {
            final File llIlIIlllIlllII = new File(this.Eclient.getAbsolutePath(), "Welcome.txt");
            final FileInputStream llIlIIlllIllIll = new FileInputStream(llIlIIlllIlllII.getAbsolutePath());
            final DataInputStream llIlIIlllIllIlI = new DataInputStream(llIlIIlllIllIll);
            final BufferedReader llIlIIlllIllIIl = new BufferedReader(new InputStreamReader(llIlIIlllIllIlI));
            String llIlIIlllIllIII;
            while ((llIlIIlllIllIII = llIlIIlllIllIIl.readLine()) != null) {
                try {
                    final String llIlIIlllIlllIl = llIlIIlllIllIII.trim();
                    Welcome.handleWelcome(llIlIIlllIlllIl);
                }
                catch (Exception ex) {}
            }
            llIlIIlllIllIIl.close();
        }
        catch (Exception llIlIIlllIlIlll) {
            llIlIIlllIlIlll.printStackTrace();
            this.saveWelcomeMessage();
        }
    }
    
    public void saveStickyNote() {
        try {
            final File llIlIlIIIIIllII = new File(this.Eclient.getAbsolutePath(), "Note.txt");
            final BufferedWriter llIlIlIIIIIlIll = new BufferedWriter(new FileWriter(llIlIlIIIIIllII));
            llIlIlIIIIIlIll.write(String.valueOf(new StringBuilder().append(StickyNotes.saveText).append("\r\n")));
            llIlIlIIIIIlIll.close();
        }
        catch (Exception ex) {}
    }
    
    public void saveHacks() {
        try {
            final File llIlIIlIlllllIl = new File(this.Eclient.getAbsolutePath(), "Modules.txt");
            final BufferedWriter llIlIIlIlllllII = new BufferedWriter(new FileWriter(llIlIIlIlllllIl));
            for (final Module llIlIIlIllllllI : Xulu.MODULE_MANAGER.getModules()) {
                try {
                    if (!llIlIIlIllllllI.isToggled() || llIlIIlIllllllI.getName().matches("null") || llIlIIlIllllllI.getName().equals("Log Out Spot") || llIlIIlIllllllI.getName().equals("Freecam") || llIlIIlIllllllI.getName().equals("Blink") || llIlIIlIllllllI.getName().equals("Join/Leave msgs") || llIlIIlIllllllI.getName().equals("Elytra +") || llIlIIlIllllllI.getName().equals("Sound")) {
                        continue;
                    }
                    llIlIIlIlllllII.write(llIlIIlIllllllI.getName());
                    llIlIIlIlllllII.write("\r\n");
                }
                catch (Exception ex) {}
            }
            llIlIIlIlllllII.close();
        }
        catch (Exception ex2) {}
    }
    
    public void loadEnemies() {
        try {
            final File llIlIIlIIIlIIll = new File(this.Eclient.getAbsolutePath(), "Enemies.txt");
            final FileInputStream llIlIIlIIIlIIlI = new FileInputStream(llIlIIlIIIlIIll.getAbsolutePath());
            final DataInputStream llIlIIlIIIlIIIl = new DataInputStream(llIlIIlIIIlIIlI);
            final BufferedReader llIlIIlIIIlIIII = new BufferedReader(new InputStreamReader(llIlIIlIIIlIIIl));
            String llIlIIlIIIIllll;
            while ((llIlIIlIIIIllll = llIlIIlIIIlIIII.readLine()) != null) {
                try {
                    Enemies.addEnemy(llIlIIlIIIIllll);
                }
                catch (Exception llIlIIlIIIIIllI) {}
            }
            llIlIIlIIIlIIII.close();
        }
        catch (Exception llIlIIlIIIIlllI) {
            llIlIIlIIIIlllI.printStackTrace();
            this.saveEnemies();
        }
    }
    
    public void saveXray() {
        try {
            final File llIlIIlllIIIlll = new File(this.Eclient.getAbsolutePath(), "Xray.txt");
            final BufferedWriter llIlIIlllIIIllI = new BufferedWriter(new FileWriter(llIlIIlllIIIlll));
            for (final Block llIlIIlllIIlIII : Xray.getBLOCKS()) {
                try {
                    llIlIIlllIIIllI.write(((ResourceLocation)Block.REGISTRY.getNameForObject((Object)llIlIIlllIIlIII)).getPath());
                    llIlIIlllIIIllI.write("\r\n");
                }
                catch (Exception ex) {}
            }
            llIlIIlllIIIllI.close();
        }
        catch (Exception ex2) {}
    }
    
    public void saveSettingsList() {
        try {
            final File llIIllllIlIIIII = new File(this.EclientSettings.getAbsolutePath(), "Slider.txt");
            final BufferedWriter llIIllllIIlllll = new BufferedWriter(new FileWriter(llIIllllIlIIIII));
            for (final Value<?> llIIllllIlIIIIl : Xulu.VALUE_MANAGER.getValues()) {
                if (llIIllllIlIIIIl.isNumber()) {
                    llIIllllIIlllll.write(String.valueOf(new StringBuilder().append(llIIllllIlIIIIl.getName()).append(":").append(llIIllllIlIIIIl.getValue().toString()).append(":").append(llIIllllIlIIIIl.getParentMod().getName()).append("\r\n")));
                }
            }
            llIIllllIIlllll.close();
        }
        catch (Exception ex) {}
        try {
            final File llIIllllIIlllIl = new File(this.EclientSettings.getAbsolutePath(), "Check.txt");
            final BufferedWriter llIIllllIIlllII = new BufferedWriter(new FileWriter(llIIllllIIlllIl));
            for (final Value llIIllllIIllllI : Xulu.VALUE_MANAGER.getValues()) {
                if (llIIllllIIllllI.isToggle()) {
                    llIIllllIIlllII.write(String.valueOf(new StringBuilder().append(llIIllllIIllllI.getName()).append(":").append(llIIllllIIllllI.getValue().toString()).append(":").append(llIIllllIIllllI.getParentMod().getName()).append("\r\n")));
                }
            }
            llIIllllIIlllII.close();
        }
        catch (Exception ex2) {}
        try {
            final File llIIllllIIllIlI = new File(this.EclientSettings.getAbsolutePath(), "Combo.txt");
            final BufferedWriter llIIllllIIllIIl = new BufferedWriter(new FileWriter(llIIllllIIllIlI));
            for (final Value llIIllllIIllIll : Xulu.VALUE_MANAGER.getValues()) {
                if (llIIllllIIllIll.isMode()) {
                    if (llIIllllIIllIll.getValue().contains(":")) {
                        llIIllllIIllIIl.write(String.valueOf(new StringBuilder().append(llIIllllIIllIll.getName()).append(";").append(llIIllllIIllIll.getValue().toString()).append(";").append(llIIllllIIllIll.getParentMod().getName()).append("\r\n")));
                    }
                    llIIllllIIllIIl.write(String.valueOf(new StringBuilder().append(llIIllllIIllIll.getName()).append(":").append(llIIllllIIllIll.getValue().toString()).append(":").append(llIIllllIIllIll.getParentMod().getName()).append("\r\n")));
                }
                if (llIIllllIIllIll.isEnum()) {
                    llIIllllIIllIIl.write(String.valueOf(new StringBuilder().append(llIIllllIIllIll.getName()).append(":").append(llIIllllIIllIll.getValue().toString()).append(":").append(llIIllllIIllIll.getParentMod().getName()).append("\r\n")));
                }
            }
            llIIllllIIllIIl.close();
        }
        catch (Exception ex3) {}
        try {
            final File llIIllllIIlIlll = new File(this.EclientSettings.getAbsolutePath(), "TextBox.txt");
            final BufferedWriter llIIllllIIlIllI = new BufferedWriter(new FileWriter(llIIllllIIlIlll));
            for (final Value llIIllllIIllIII : Xulu.VALUE_MANAGER.getValues()) {
                if (llIIllllIIllIII.isText()) {
                    if (llIIllllIIllIII.getValue().getText().contains(":")) {
                        llIIllllIIlIllI.write(String.valueOf(new StringBuilder().append(llIIllllIIllIII.getName()).append(";").append(llIIllllIIllIII.getValue().getText()).append(";").append(llIIllllIIllIII.getParentMod().getName()).append("\r\n")));
                    }
                    llIIllllIIlIllI.write(String.valueOf(new StringBuilder().append(llIIllllIIllIII.getName()).append(":").append(llIIllllIIllIII.getValue().getText()).append(":").append(llIIllllIIllIII.getParentMod().getName()).append("\r\n")));
                }
            }
            llIIllllIIlIllI.close();
        }
        catch (Exception ex4) {}
    }
    
    public void loadHUD() {
        try {
            final File llIlIIIlIlIIIll = new File(this.Eclient.getAbsolutePath(), "HUD.txt");
            final FileInputStream llIlIIIlIlIIIlI = new FileInputStream(llIlIIIlIlIIIll.getAbsolutePath());
            final DataInputStream llIlIIIlIlIIIIl = new DataInputStream(llIlIIIlIlIIIlI);
            final BufferedReader llIlIIIlIlIIIII = new BufferedReader(new InputStreamReader(llIlIIIlIlIIIIl));
            String llIlIIIlIIlllll;
            while ((llIlIIIlIIlllll = llIlIIIlIlIIIII.readLine()) != null) {
                try {
                    final String llIlIIIlIlIlIll = llIlIIIlIIlllll.trim();
                    final String llIlIIIlIlIlIlI = llIlIIIlIlIlIll.split(":")[0];
                    final String llIlIIIlIlIlIIl = llIlIIIlIlIlIll.split(":")[1];
                    final String llIlIIIlIlIlIII = llIlIIIlIlIlIll.split(":")[2];
                    final double llIlIIIlIlIIlll = Double.parseDouble(llIlIIIlIlIlIIl);
                    final double llIlIIIlIlIIllI = Double.parseDouble(llIlIIIlIlIlIII);
                    if (llIlIIIlIlIlIlI.equalsIgnoreCase(Xulu.hud.hudPanel.title)) {
                        Xulu.hud.hudPanel.x = llIlIIIlIlIIlll;
                        Xulu.hud.hudPanel.y = llIlIIIlIlIIllI;
                    }
                    final Frame llIlIIIlIlIIlIl = HUD.getframeByName(llIlIIIlIlIlIlI);
                    if (llIlIIIlIlIIlIl != null) {
                        llIlIIIlIlIIlIl.x = llIlIIIlIlIIlll;
                        llIlIIIlIlIIlIl.y = llIlIIIlIlIIllI;
                    }
                    final Element llIlIIIlIlIIlII = (Element)Xulu.MODULE_MANAGER.getModuleByName(llIlIIIlIlIlIlI);
                    if (llIlIIIlIlIIlII == null) {
                        continue;
                    }
                    llIlIIIlIlIIlII.setX(llIlIIIlIlIIlll);
                    llIlIIIlIlIIlII.setY(llIlIIIlIlIIllI);
                }
                catch (Exception llIlIIIlIIlIllI) {}
            }
            llIlIIIlIlIIIII.close();
        }
        catch (Exception llIlIIIlIIllllI) {
            llIlIIIlIIllllI.printStackTrace();
            this.saveHUD();
        }
    }
    
    public void saveSearch() {
        try {
            final File llIlIIllIlIIIlI = new File(this.Eclient.getAbsolutePath(), "Search.txt");
            final BufferedWriter llIlIIllIlIIIIl = new BufferedWriter(new FileWriter(llIlIIllIlIIIlI));
            for (final Block llIlIIllIlIIIll : Search.getBLOCKS()) {
                try {
                    llIlIIllIlIIIIl.write(((ResourceLocation)Block.REGISTRY.getNameForObject((Object)llIlIIllIlIIIll)).getPath());
                    llIlIIllIlIIIIl.write("\r\n");
                }
                catch (Exception ex) {}
            }
            llIlIIllIlIIIIl.close();
        }
        catch (Exception ex2) {}
    }
    
    public void saveHUD() {
        try {
            final File llIlIIIllIIIIIl = new File(this.Eclient.getAbsolutePath(), "HUD.txt");
            final BufferedWriter llIlIIIllIIIIII = new BufferedWriter(new FileWriter(llIlIIIllIIIIIl));
            for (final Frame llIlIIIllIIIIlI : HUD.frames) {
                llIlIIIllIIIIII.write(String.valueOf(new StringBuilder().append(llIlIIIllIIIIlI.title).append(":").append(llIlIIIllIIIIlI.x).append(":").append(llIlIIIllIIIIlI.y)));
                llIlIIIllIIIIII.write("\r\n");
            }
            llIlIIIllIIIIII.write(String.valueOf(new StringBuilder().append(Xulu.hud.hudPanel.title).append(":").append(Xulu.hud.hudPanel.x).append(":").append(Xulu.hud.hudPanel.y)));
            llIlIIIllIIIIII.write("\r\n");
            llIlIIIllIIIIII.close();
        }
        catch (Exception ex) {}
    }
    
    public void loadNicks() {
        try {
            final File llIlIIlIIlllIlI = new File(this.Eclient.getAbsolutePath(), "Nicknames.txt");
            final FileInputStream llIlIIlIIlllIIl = new FileInputStream(llIlIIlIIlllIlI.getAbsolutePath());
            final DataInputStream llIlIIlIIlllIII = new DataInputStream(llIlIIlIIlllIIl);
            final BufferedReader llIlIIlIIllIlll = new BufferedReader(new InputStreamReader(llIlIIlIIlllIII));
            String llIlIIlIIllIllI;
            while ((llIlIIlIIllIllI = llIlIIlIIllIlll.readLine()) != null) {
                try {
                    final String llIlIIlIIllllIl = llIlIIlIIllIllI.trim();
                    final String llIlIIlIIllllII = llIlIIlIIllllIl.split(":")[0];
                    final String llIlIIlIIlllIll = llIlIIlIIllllIl.split(":")[1];
                    Nicknames.addNickname(llIlIIlIIllllII, llIlIIlIIlllIll);
                }
                catch (Exception llIlIIlIIlIllIl) {}
            }
            llIlIIlIIllIlll.close();
        }
        catch (Exception llIlIIlIIllIlIl) {
            llIlIIlIIllIlIl.printStackTrace();
            this.saveFriends();
        }
    }
    
    public void loadXray() {
        try {
            final File llIlIIllIllIlll = new File(this.Eclient.getAbsolutePath(), "Xray.txt");
            final FileInputStream llIlIIllIllIllI = new FileInputStream(llIlIIllIllIlll.getAbsolutePath());
            final DataInputStream llIlIIllIllIlIl = new DataInputStream(llIlIIllIllIllI);
            final BufferedReader llIlIIllIllIlII = new BufferedReader(new InputStreamReader(llIlIIllIllIlIl));
            Xray.getBLOCKS().clear();
            String llIlIIllIllIIll;
            while ((llIlIIllIllIIll = llIlIIllIllIlII.readLine()) != null) {
                try {
                    Xray.getBLOCKS().add(Objects.requireNonNull(Block.getBlockFromName(llIlIIllIllIIll)));
                }
                catch (NullPointerException llIlIIllIlIlIlI) {}
            }
            llIlIIllIllIlII.close();
        }
        catch (Exception llIlIIllIllIIlI) {
            llIlIIllIllIIlI.printStackTrace();
        }
    }
    
    public void saveMacros() {
        try {
            final File llIIlllllIlIIIl = new File(this.Eclient.getAbsolutePath(), "Macros.txt");
            final BufferedWriter llIIlllllIlIIII = new BufferedWriter(new FileWriter(llIIlllllIlIIIl));
            for (final Macro llIIlllllIlIIlI : Xulu.MACRO_MANAGER.getMacros()) {
                llIIlllllIlIIII.write(String.valueOf(new StringBuilder().append(llIIlllllIlIIlI.getMacro()).append(":").append(llIIlllllIlIIlI.getKey()).append("\r\n")));
            }
            llIIlllllIlIIII.close();
        }
        catch (Exception ex) {}
    }
    
    public void loadStickyNote() {
        try {
            final File llIlIIlllllllII = new File(this.Eclient.getAbsolutePath(), "Note.txt");
            final FileInputStream llIlIIllllllIll = new FileInputStream(llIlIIlllllllII.getAbsolutePath());
            final DataInputStream llIlIIllllllIlI = new DataInputStream(llIlIIllllllIll);
            final BufferedReader llIlIIllllllIIl = new BufferedReader(new InputStreamReader(llIlIIllllllIlI));
            String llIlIIllllllIII;
            while ((llIlIIllllllIII = llIlIIllllllIIl.readLine()) != null) {
                try {
                    final String llIlIIllllllllI = llIlIIllllllIII.trim();
                    final String llIlIIlllllllIl = llIlIIllllllllI.split(":")[0];
                    StickyNotes.processText(llIlIIlllllllIl);
                }
                catch (Exception ex) {}
            }
            llIlIIllllllIIl.close();
        }
        catch (Exception llIlIIlllllIlll) {
            llIlIIlllllIlll.printStackTrace();
            this.saveStickyNote();
        }
    }
    
    public void saveDummy() {
        try {
            final File llIlIlIIlllIIIl = new File(this.Eclient.getAbsolutePath(), "Dummy.txt");
            final BufferedWriter llIlIlIIlllIIII = new BufferedWriter(new FileWriter(llIlIlIIlllIIIl));
            for (final Module llIlIlIIlllIIlI : Xulu.MODULE_MANAGER.getModules()) {
                if (llIlIlIIlllIIlI.getCategory() == Category.DUMMY) {
                    try {
                        llIlIlIIlllIIII.write(String.valueOf(new StringBuilder().append(llIlIlIIlllIIlI.getName()).append(":").append((llIlIlIIlllIIlI.getHudInfo() == null) ? "null" : llIlIlIIlllIIlI.getHudInfo())));
                        llIlIlIIlllIIII.write("\r\n");
                    }
                    catch (Exception ex) {}
                }
            }
            llIlIlIIlllIIII.close();
        }
        catch (Exception ex2) {}
    }
    
    public void loadBinds() {
        try {
            final File llIlIlIlIIIlIlI = new File(this.Eclient.getAbsolutePath(), "Binds.txt");
            final FileInputStream llIlIlIlIIIlIIl = new FileInputStream(llIlIlIlIIIlIlI.getAbsolutePath());
            final DataInputStream llIlIlIlIIIlIII = new DataInputStream(llIlIlIlIIIlIIl);
            final BufferedReader llIlIlIlIIIIlll = new BufferedReader(new InputStreamReader(llIlIlIlIIIlIII));
            String llIlIlIlIIIIllI;
            while ((llIlIlIlIIIIllI = llIlIlIlIIIIlll.readLine()) != null) {
                try {
                    final String llIlIlIlIIIllll = llIlIlIlIIIIllI.trim();
                    final String llIlIlIlIIIlllI = llIlIlIlIIIllll.split(":")[0];
                    final String llIlIlIlIIIllIl = llIlIlIlIIIllll.split(":")[1];
                    final int llIlIlIlIIIllII = Integer.parseInt(llIlIlIlIIIllIl);
                    final Module llIlIlIlIIIlIll = Xulu.MODULE_MANAGER.getModuleByName(llIlIlIlIIIlllI);
                    if (llIlIlIlIIIlIll == null) {
                        continue;
                    }
                    llIlIlIlIIIlIll.setKey(llIlIlIlIIIllII);
                }
                catch (Exception llIlIlIIlllllIl) {}
            }
            llIlIlIlIIIIlll.close();
        }
        catch (Exception llIlIlIlIIIIlIl) {
            llIlIlIlIIIIlIl.printStackTrace();
            this.saveBinds();
        }
    }
    
    public boolean appendTextFile(final String llIlIlIllIIIIII, final String llIlIlIlIllllll) {
        try {
            final Path llIlIlIllIIIlIl = Paths.get(llIlIlIlIllllll, new String[0]);
            Files.write(llIlIlIllIIIlIl, Collections.singletonList(llIlIlIllIIIIII), StandardCharsets.UTF_8, Files.exists(llIlIlIllIIIlIl, new LinkOption[0]) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
        }
        catch (IOException llIlIlIllIIIlII) {
            System.out.println(String.valueOf(new StringBuilder().append("WARNING: Unable to write file: ").append(llIlIlIlIllllll)));
            return false;
        }
        return true;
    }
    
    public void loadSearch() {
        try {
            final File llIlIIllIIlIIlI = new File(this.Eclient.getAbsolutePath(), "Search.txt");
            final FileInputStream llIlIIllIIlIIIl = new FileInputStream(llIlIIllIIlIIlI.getAbsolutePath());
            final DataInputStream llIlIIllIIlIIII = new DataInputStream(llIlIIllIIlIIIl);
            final BufferedReader llIlIIllIIIllll = new BufferedReader(new InputStreamReader(llIlIIllIIlIIII));
            Search.getBLOCKS().clear();
            String llIlIIllIIIlllI;
            while ((llIlIIllIIIlllI = llIlIIllIIIllll.readLine()) != null) {
                try {
                    Search.getBLOCKS().add(Objects.requireNonNull(Block.getBlockFromName(llIlIIllIIIlllI)));
                }
                catch (NullPointerException llIlIIllIIIIlIl) {}
            }
            llIlIIllIIIllll.close();
        }
        catch (Exception llIlIIllIIIllIl) {
            llIlIIllIIIllIl.printStackTrace();
        }
    }
    
    public void saveDrawn() {
        try {
            final File llIlIIIIIlIllIl = new File(this.Eclient.getAbsolutePath(), "Drawn.txt");
            final BufferedWriter llIlIIIIIlIllII = new BufferedWriter(new FileWriter(llIlIIIIIlIllIl));
            for (final Module llIlIIIIIlIlllI : Xulu.MODULE_MANAGER.getModules()) {
                llIlIIIIIlIllII.write(String.valueOf(new StringBuilder().append(llIlIIIIIlIlllI.getName()).append(":").append(llIlIIIIIlIlllI.isDrawn())));
                llIlIIIIIlIllII.write("\r\n");
            }
            llIlIIIIIlIllII.close();
        }
        catch (Exception ex) {}
    }
    
    public void saveEnemies() {
        try {
            final File llIlIIlIIlIIIll = new File(this.Eclient.getAbsolutePath(), "Enemies.txt");
            final BufferedWriter llIlIIlIIlIIIlI = new BufferedWriter(new FileWriter(llIlIIlIIlIIIll));
            for (final Enemy llIlIIlIIlIIlII : Enemies.getEnemies()) {
                try {
                    llIlIIlIIlIIIlI.write(llIlIIlIIlIIlII.getUsername());
                    llIlIIlIIlIIIlI.write("\r\n");
                }
                catch (Exception ex) {}
            }
            llIlIIlIIlIIIlI.close();
        }
        catch (Exception ex2) {}
    }
    
    public void saveWaypoints() {
        try {
            final File llIlIIIIllIllII = new File(this.Eclient.getAbsolutePath(), "Waypoints.txt");
            final BufferedWriter llIlIIIIllIlIll = new BufferedWriter(new FileWriter(llIlIIIIllIllII));
            for (final Waypoints.Waypoint llIlIIIIllIllIl : Waypoints.WAYPOINTS) {
                llIlIIIIllIlIll.write(String.valueOf(new StringBuilder().append(llIlIIIIllIllIl.getName()).append(":").append(llIlIIIIllIllIl.getPos().x).append(":").append(llIlIIIIllIllIl.getPos().y).append(":").append(llIlIIIIllIllIl.getPos().z).append(":").append(llIlIIIIllIllIl.getDimension())));
                llIlIIIIllIlIll.write("\r\n");
            }
            llIlIIIIllIlIll.close();
        }
        catch (Exception ex) {}
    }
    
    public void writeCrash(final String llIIllllllllIII) {
        try {
            final SimpleDateFormat llIIlllllllllIl = new SimpleDateFormat("MM_dd_yyyy-HH_mm_ss");
            final Date llIIlllllllllII = new Date();
            final File llIIllllllllIll = new File(this.Eclient.getAbsolutePath(), "crashlog-".concat(llIIlllllllllIl.format(llIIlllllllllII)).concat(".xen"));
            final BufferedWriter llIIllllllllIlI = new BufferedWriter(new FileWriter(llIIllllllllIll));
            llIIllllllllIlI.write(llIIllllllllIII);
            llIIllllllllIlI.close();
        }
        catch (Exception ex) {}
    }
    
    public void saveBinds() {
        try {
            final File llIlIlIlIlIIIll = new File(this.Eclient.getAbsolutePath(), "Binds.txt");
            final BufferedWriter llIlIlIlIlIIIlI = new BufferedWriter(new FileWriter(llIlIlIlIlIIIll));
            for (final Module llIlIlIlIlIIlII : Xulu.MODULE_MANAGER.getModules()) {
                try {
                    llIlIlIlIlIIIlI.write(String.valueOf(new StringBuilder().append(llIlIlIlIlIIlII.getName()).append(":").append(llIlIlIlIlIIlII.getKey())));
                    llIlIlIlIlIIIlI.write("\r\n");
                }
                catch (Exception ex) {}
            }
            llIlIlIlIlIIIlI.close();
        }
        catch (Exception ex2) {}
    }
    
    public void saveNicks() {
        try {
            final File llIlIIlIlIIllII = new File(this.Eclient.getAbsolutePath(), "Nicknames.txt");
            final BufferedWriter llIlIIlIlIIlIll = new BufferedWriter(new FileWriter(llIlIIlIlIIllII));
            final Writer writer;
            Nicknames.getAliases().forEach((llIIlllIIlIlIII, llIIlllIIlIlIlI) -> {
                try {
                    writer.write(String.valueOf(new StringBuilder().append(llIIlllIIlIlIII).append(":").append(llIIlllIIlIlIlI)));
                    writer.write("\r\n");
                }
                catch (Exception ex) {}
                return;
            });
            llIlIIlIlIIlIll.close();
        }
        catch (Exception ex2) {}
    }
    
    public void saveFriends() {
        try {
            final File llIlIIlIllIllIl = new File(this.Eclient.getAbsolutePath(), "Friends.txt");
            final BufferedWriter llIlIIlIllIllII = new BufferedWriter(new FileWriter(llIlIIlIllIllIl));
            for (final Friend llIlIIlIllIlllI : Friends.getFriends()) {
                try {
                    llIlIIlIllIllII.write(llIlIIlIllIlllI.getUsername());
                    llIlIIlIllIllII.write("\r\n");
                }
                catch (Exception ex) {}
            }
            llIlIIlIllIllII.close();
        }
        catch (Exception ex2) {}
    }
    
    public void loadPrefix() {
        try {
            final File llIlIIIIlllllll = new File(this.Eclient.getAbsolutePath(), "Prefix.txt");
            final FileInputStream llIlIIIIllllllI = new FileInputStream(llIlIIIIlllllll.getAbsolutePath());
            final DataInputStream llIlIIIIlllllIl = new DataInputStream(llIlIIIIllllllI);
            final BufferedReader llIlIIIIlllllII = new BufferedReader(new InputStreamReader(llIlIIIIlllllIl));
            String llIlIIIIllllIll;
            while ((llIlIIIIllllIll = llIlIIIIlllllII.readLine()) != null) {
                Command.setPrefix(llIlIIIIllllIll);
            }
            llIlIIIIlllllII.close();
        }
        catch (Exception llIlIIIIllllIlI) {
            llIlIIIIllllIlI.printStackTrace();
            this.savePrefix();
        }
    }
    
    public void loadSettingsList() {
        try {
            final File llIIlllIlllllIl = new File(this.EclientSettings.getAbsolutePath(), "Slider.txt");
            final FileInputStream llIIlllIlllllII = new FileInputStream(llIIlllIlllllIl.getAbsolutePath());
            final DataInputStream llIIlllIllllIll = new DataInputStream(llIIlllIlllllII);
            final BufferedReader llIIlllIllllIlI = new BufferedReader(new InputStreamReader(llIIlllIllllIll));
            String llIIlllIllllIIl;
            while ((llIIlllIllllIIl = llIIlllIllllIlI.readLine()) != null) {
                try {
                    final String llIIllllIIIIIll = llIIlllIllllIIl.trim();
                    final String llIIllllIIIIIlI = llIIllllIIIIIll.split(":")[0];
                    final String llIIllllIIIIIIl = llIIllllIIIIIll.split(":")[1];
                    final String llIIllllIIIIIII = llIIllllIIIIIll.split(":")[2];
                    final Value llIIlllIlllllll = Xulu.VALUE_MANAGER.getValueByMod(Xulu.MODULE_MANAGER.getModuleByName(llIIllllIIIIIII), llIIllllIIIIIlI);
                    Number llIIlllIllllllI = 0;
                    if (llIIlllIlllllll.getValue() instanceof Double) {
                        llIIlllIllllllI = NumberUtils.createDouble(llIIllllIIIIIIl);
                    }
                    else if (llIIlllIlllllll.getValue() instanceof Integer) {
                        llIIlllIllllllI = NumberUtils.createInteger(llIIllllIIIIIIl);
                    }
                    else if (llIIlllIlllllll.getValue() instanceof Float) {
                        llIIlllIllllllI = NumberUtils.createFloat(llIIllllIIIIIIl);
                    }
                    else if (llIIlllIlllllll.getValue() instanceof Long) {
                        llIIlllIllllllI = NumberUtils.createLong(llIIllllIIIIIIl);
                    }
                    else if (llIIlllIlllllll.getValue() instanceof Short) {
                        llIIlllIllllllI = NumberUtils.createShort(llIIllllIIIIIIl);
                    }
                    llIIlllIlllllll.setValue(llIIlllIllllllI);
                }
                catch (Exception ex) {}
            }
            llIIlllIllllIlI.close();
        }
        catch (Exception llIIlllIllllIII) {
            llIIlllIllllIII.printStackTrace();
        }
        try {
            final File llIIlllIlllIIlI = new File(this.EclientSettings.getAbsolutePath(), "Check.txt");
            final FileInputStream llIIlllIlllIIIl = new FileInputStream(llIIlllIlllIIlI.getAbsolutePath());
            final DataInputStream llIIlllIlllIIII = new DataInputStream(llIIlllIlllIIIl);
            final BufferedReader llIIlllIllIllll = new BufferedReader(new InputStreamReader(llIIlllIlllIIII));
            String llIIlllIllIlllI;
            while ((llIIlllIllIlllI = llIIlllIllIllll.readLine()) != null) {
                try {
                    final String llIIlllIlllIlll = llIIlllIllIlllI.trim();
                    final String llIIlllIlllIllI = llIIlllIlllIlll.split(":")[0];
                    final String llIIlllIlllIlIl = llIIlllIlllIlll.split(":")[1];
                    final String llIIlllIlllIlII = llIIlllIlllIlll.split(":")[2];
                    final Value llIIlllIlllIIll = Xulu.VALUE_MANAGER.getValueByMod(Xulu.MODULE_MANAGER.getModuleByName(llIIlllIlllIlII), llIIlllIlllIllI);
                    llIIlllIlllIIll.setValue(Boolean.parseBoolean(llIIlllIlllIlIl));
                }
                catch (Exception ex2) {}
            }
            llIIlllIllIllll.close();
        }
        catch (Exception llIIlllIllIllIl) {
            llIIlllIllIllIl.printStackTrace();
        }
        try {
            final File llIIlllIllIIIll = new File(this.EclientSettings.getAbsolutePath(), "Combo.txt");
            final FileInputStream llIIlllIllIIIlI = new FileInputStream(llIIlllIllIIIll.getAbsolutePath());
            final DataInputStream llIIlllIllIIIIl = new DataInputStream(llIIlllIllIIIlI);
            final BufferedReader llIIlllIlIlllll = new BufferedReader(new InputStreamReader(llIIlllIllIIIIl));
            String llIIlllIlIllllI;
            while ((llIIlllIlIllllI = llIIlllIlIlllll.readLine()) != null) {
                try {
                    final String llIIlllIllIlIIl = llIIlllIlIllllI.trim();
                    String llIIlllIllIlIII = null;
                    String llIIlllIllIIlll = null;
                    String llIIlllIllIIllI = null;
                    if (llIIlllIllIlIIl.contains(";")) {
                        final String llIIlllIllIllII = llIIlllIllIlIIl.split(";")[0];
                        final String llIIlllIllIlIll = llIIlllIllIlIIl.split(";")[1];
                        final String llIIlllIllIlIlI = llIIlllIllIlIIl.split(";")[2];
                    }
                    else {
                        llIIlllIllIlIII = llIIlllIllIlIIl.split(":")[0];
                        llIIlllIllIIlll = llIIlllIllIlIIl.split(":")[1];
                        llIIlllIllIIllI = llIIlllIllIlIIl.split(":")[2];
                    }
                    final Value llIIlllIllIIlII = Xulu.VALUE_MANAGER.getValueByMod(Xulu.MODULE_MANAGER.getModuleByName(llIIlllIllIIllI), llIIlllIllIlIII);
                    if (llIIlllIllIIlII.isEnum()) {
                        llIIlllIllIIlII.setEnumValue(llIIlllIllIIlll);
                    }
                    else {
                        llIIlllIllIIlII.setValue(llIIlllIllIIlll);
                    }
                }
                catch (Exception ex3) {}
            }
            llIIlllIlIlllll.close();
        }
        catch (Exception llIIlllIlIlllIl) {
            llIIlllIlIlllIl.printStackTrace();
        }
        try {
            final File llIIlllIlIlIIIl = new File(this.EclientSettings.getAbsolutePath(), "TextBox.txt");
            final FileInputStream llIIlllIlIlIIII = new FileInputStream(llIIlllIlIlIIIl.getAbsolutePath());
            final DataInputStream llIIlllIlIIllll = new DataInputStream(llIIlllIlIlIIII);
            final BufferedReader llIIlllIlIIllIl = new BufferedReader(new InputStreamReader(llIIlllIlIIllll));
            String llIIlllIlIIlIll;
            while ((llIIlllIlIIlIll = llIIlllIlIIllIl.readLine()) != null) {
                try {
                    final String llIIlllIlIlIllI = llIIlllIlIIlIll.trim();
                    String llIIlllIlIlIlIl = null;
                    String llIIlllIlIlIlII = null;
                    String llIIlllIlIlIIll = null;
                    if (llIIlllIlIlIllI.contains(";")) {
                        final String llIIlllIlIlllII = llIIlllIlIlIllI.split(";")[0];
                        final String llIIlllIlIllIll = llIIlllIlIlIllI.split(";")[1];
                        final String llIIlllIlIllIIl = llIIlllIlIlIllI.split(";")[2];
                    }
                    else {
                        llIIlllIlIlIlIl = llIIlllIlIlIllI.split(":")[0];
                        llIIlllIlIlIlII = llIIlllIlIlIllI.split(":")[1];
                        llIIlllIlIlIIll = llIIlllIlIlIllI.split(":")[2];
                    }
                    final Value llIIlllIlIlIIlI = Xulu.VALUE_MANAGER.getValueByMod(Xulu.MODULE_MANAGER.getModuleByName(llIIlllIlIlIIll), llIIlllIlIlIlIl);
                    llIIlllIlIlIIlI.setValue(new TextBox(llIIlllIlIlIlII));
                }
                catch (Exception ex4) {}
            }
            llIIlllIlIIllIl.close();
        }
        catch (Exception llIIlllIlIIlIIl) {
            llIIlllIlIIlIIl.printStackTrace();
        }
    }
    
    public void loadMacros() {
        try {
            final File llIIllllIlllIll = new File(this.Eclient.getAbsolutePath(), "Macros.txt");
            final FileInputStream llIIllllIlllIlI = new FileInputStream(llIIllllIlllIll.getAbsolutePath());
            final DataInputStream llIIllllIlllIIl = new DataInputStream(llIIllllIlllIlI);
            final BufferedReader llIIllllIlllIII = new BufferedReader(new InputStreamReader(llIIllllIlllIIl));
            String llIIllllIllIlll;
            while ((llIIllllIllIlll = llIIllllIlllIII.readLine()) != null) {
                try {
                    final String llIIllllIllllll = llIIllllIllIlll.trim();
                    final String llIIllllIlllllI = llIIllllIllllll.split(":")[0];
                    final String llIIllllIllllIl = llIIllllIllllll.split(":")[1];
                    final int llIIllllIllllII = Integer.valueOf(llIIllllIllllIl);
                    if (Xulu.MACRO_MANAGER.getMacros().contains(new Macro(llIIllllIlllllI, llIIllllIllllII))) {
                        continue;
                    }
                    Xulu.MACRO_MANAGER.addMacro(llIIllllIlllllI, llIIllllIllllII);
                }
                catch (Exception ex) {}
            }
            llIIllllIlllIII.close();
        }
        catch (Exception llIIllllIllIllI) {
            llIIllllIllIllI.printStackTrace();
            this.saveMacros();
        }
    }
    
    public void loadDummy() {
        try {
            final File llIlIlIIlIlllII = new File(this.Eclient.getAbsolutePath(), "Dummy.txt");
            final FileInputStream llIlIlIIlIllIll = new FileInputStream(llIlIlIIlIlllII.getAbsolutePath());
            final DataInputStream llIlIlIIlIllIlI = new DataInputStream(llIlIlIIlIllIll);
            final BufferedReader llIlIlIIlIllIIl = new BufferedReader(new InputStreamReader(llIlIlIIlIllIlI));
            String llIlIlIIlIllIII;
            while ((llIlIlIIlIllIII = llIlIlIIlIllIIl.readLine()) != null) {
                try {
                    final String llIlIlIIlIlllll = llIlIlIIlIllIII.trim();
                    final String llIlIlIIlIllllI = llIlIlIIlIlllll.split(":")[0];
                    final String llIlIlIIlIlllIl = llIlIlIIlIlllll.split(":")[1];
                    if (llIlIlIIlIlllIl.equalsIgnoreCase("null")) {
                        Xulu.MODULE_MANAGER.getModules().add(new DummyMod(llIlIlIIlIllllI));
                    }
                    else {
                        Xulu.MODULE_MANAGER.getModules().add(new DummyMod(llIlIlIIlIllllI, llIlIlIIlIlllIl));
                    }
                }
                catch (Exception ex) {}
            }
            llIlIlIIlIllIIl.close();
        }
        catch (Exception llIlIlIIlIlIlll) {
            llIlIlIIlIlIlll.printStackTrace();
            this.saveDummy();
        }
    }
    
    public void loadDrawn() {
        try {
            final File llIlIIIIIIlIlIl = new File(this.Eclient.getAbsolutePath(), "Drawn.txt");
            final FileInputStream llIlIIIIIIlIlII = new FileInputStream(llIlIIIIIIlIlIl.getAbsolutePath());
            final DataInputStream llIlIIIIIIlIIll = new DataInputStream(llIlIIIIIIlIlII);
            final BufferedReader llIlIIIIIIlIIlI = new BufferedReader(new InputStreamReader(llIlIIIIIIlIIll));
            String llIlIIIIIIlIIIl;
            while ((llIlIIIIIIlIIIl = llIlIIIIIIlIIlI.readLine()) != null) {
                try {
                    final String llIlIIIIIIllIlI = llIlIIIIIIlIIIl.trim();
                    final String llIlIIIIIIllIIl = llIlIIIIIIllIlI.split(":")[0];
                    final String llIlIIIIIIllIII = llIlIIIIIIllIlI.split(":")[1];
                    final boolean llIlIIIIIIlIlll = Boolean.parseBoolean(llIlIIIIIIllIII);
                    final Module llIlIIIIIIlIllI = Xulu.MODULE_MANAGER.getModuleByName(llIlIIIIIIllIIl);
                    llIlIIIIIIlIllI.setDrawn(llIlIIIIIIlIlll);
                }
                catch (Exception ex) {}
            }
            llIlIIIIIIlIIlI.close();
        }
        catch (Exception llIlIIIIIIlIIII) {
            llIlIIIIIIlIIII.printStackTrace();
            this.saveDrawn();
        }
    }
    
    public void loadNewGui() {
        try {
            final File llIlIIIllIlllIl = new File(this.Eclient.getAbsolutePath(), "NewGui.txt");
            final FileInputStream llIlIIIllIlllII = new FileInputStream(llIlIIIllIlllIl.getAbsolutePath());
            final DataInputStream llIlIIIllIllIll = new DataInputStream(llIlIIIllIlllII);
            final BufferedReader llIlIIIllIllIlI = new BufferedReader(new InputStreamReader(llIlIIIllIllIll));
            String llIlIIIllIllIIl;
            while ((llIlIIIllIllIIl = llIlIIIllIllIlI.readLine()) != null) {
                try {
                    final String llIlIIIlllIIllI = llIlIIIllIllIIl.trim();
                    final String llIlIIIlllIIlIl = llIlIIIlllIIllI.split(":")[0];
                    final String llIlIIIlllIIlII = llIlIIIlllIIllI.split(":")[1];
                    final String llIlIIIlllIIIll = llIlIIIlllIIllI.split(":")[2];
                    final String llIlIIIlllIIIlI = llIlIIIlllIIllI.split(":")[3];
                    final double llIlIIIlllIIIIl = Double.parseDouble(llIlIIIlllIIlII);
                    final double llIlIIIlllIIIII = Double.parseDouble(llIlIIIlllIIIll);
                    final boolean llIlIIIllIlllll = Boolean.parseBoolean(llIlIIIlllIIIlI);
                    final Panel llIlIIIllIllllI = NewGUI.getPanelByName(llIlIIIlllIIlIl);
                    if (llIlIIIllIllllI == null) {
                        continue;
                    }
                    llIlIIIllIllllI.x = llIlIIIlllIIIIl;
                    llIlIIIllIllllI.y = llIlIIIlllIIIII;
                    llIlIIIllIllllI.extended = llIlIIIllIlllll;
                }
                catch (Exception ex) {}
            }
            llIlIIIllIllIlI.close();
        }
        catch (Exception llIlIIIllIllIII) {
            llIlIIIllIllIII.printStackTrace();
            this.saveNewGui();
        }
    }
    
    public String determineNumber(final Object llIIllllIlIIlll) {
        if (llIIllllIlIIlll instanceof Integer) {
            return "INTEGER";
        }
        if (llIIllllIlIIlll instanceof Float) {
            return "FLOAT";
        }
        if (llIIllllIlIIlll instanceof Double) {
            return "DOUBLE";
        }
        return "INVALID";
    }
}
