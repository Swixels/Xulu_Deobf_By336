package com.elementars.eclient;

import net.minecraftforge.fml.common.*;
import java.text.*;
import com.elementars.eclient.macro.*;
import com.elementars.eclient.font.rainbow.*;
import com.elementars.eclient.guirewrite.*;
import net.minecraft.client.*;
import dev.xulu.newgui.*;
import dev.xulu.settings.*;
import com.elementars.eclient.command.*;
import com.elementars.eclient.font.custom.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.event.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import dev.xulu.newgui.util.*;
import com.elementars.eclient.module.core.*;
import com.mojang.realmsclient.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.awt.*;
import net.minecraftforge.fml.common.event.*;
import org.lwjgl.opengl.*;
import com.elementars.eclient.cape.*;
import com.elementars.eclient.font.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.event.events.*;

@Mod(modid = "eclient", name = "Xulu", version = "v1.5.2")
public class Xulu implements Helper
{
    public static final /* synthetic */ EventManager EVENT_MANAGER;
    private /* synthetic */ int yCount;
    public static /* synthetic */ int rgb;
    public static /* synthetic */ boolean CustomFont;
    public static /* synthetic */ DecimalFormat df;
    public static final /* synthetic */ MacroManager MACRO_MANAGER;
    private /* synthetic */ int endY;
    private /* synthetic */ DiscordRP discordRP;
    public static /* synthetic */ RainbowCycle rainbowCycle;
    public static /* synthetic */ CFontManager cFontRenderer;
    public static /* synthetic */ HUD hud;
    private /* synthetic */ boolean shownLag;
    public static final /* synthetic */ ModuleManager MODULE_MANAGER;
    public /* synthetic */ Minecraft mc;
    private /* synthetic */ int beginY;
    public static final /* synthetic */ CommandManager COMMAND_MANAGER;
    public static /* synthetic */ NewGUI newGUI;
    public static final /* synthetic */ ValueManager VALUE_MANAGER;
    
    static {
        id = "eclient";
        creator = "Elementars";
        version = "v1.5.2";
        name = "Xulu";
        Xulu.df = new DecimalFormat("##,###,###,###,##0.00");
        Xulu.rainbowCycle = new RainbowCycle();
        TASK_SCHEDULER = new TaskScheduler();
        VALUE_MANAGER = new ValueManager();
        EVENT_MANAGER = new EventManager();
        MODULE_MANAGER = new ModuleManager();
        MACRO_MANAGER = new MacroManager();
        COMMAND_MANAGER = new CommandManager();
    }
    
    public static void setXdolfFontRendererDefault() {
        try {
            CFontManager.xFontRenderer = new XFontRenderer(new Font("Verdana", 0, 36), true, 8);
        }
        catch (Exception lIllllIllIlIIIl) {
            lIllllIllIlIIIl.printStackTrace();
        }
    }
    
    public static void load() {
        Xulu.fileManager.loadHacks();
        Xulu.fileManager.loadBinds();
        Xulu.fileManager.loadDrawn();
        Xulu.fileManager.loadSettingsList();
        Xulu.fileManager.loadMacros();
        Xulu.fileManager.loadPrefix();
        Xulu.fileManager.loadNewGui();
        Xulu.fileManager.loadFriends();
        Xulu.fileManager.loadEnemies();
        Xulu.fileManager.loadHUD();
        Xulu.fileManager.loadFont();
        Xulu.fileManager.loadStickyNote();
        Xulu.fileManager.loadWelcomeMessage();
        Xulu.fileManager.loadDummy();
        Xulu.fileManager.loadXray();
        Xulu.fileManager.loadSearch();
        Xulu.fileManager.loadWaypoints();
        Xulu.fileManager.loadNicks();
        System.out.println("Xulu Loaded!");
    }
    
    public static void setXdolfFontRenderer(final String lIllllIllIlIlIl, final int lIllllIllIlIlII) {
        try {
            if (getCorrectFont(lIllllIllIlIlIl) == null && !lIllllIllIlIlIl.equalsIgnoreCase("Xulu")) {
                Command.sendChatMessage("Invalid font!");
                return;
            }
            CFontManager.xFontRenderer = new XFontRenderer(new Font(getCorrectFont(lIllllIllIlIlIl), 0, lIllllIllIlIlII), true, 8);
        }
        catch (Exception lIllllIllIllIII) {
            lIllllIllIllIII.printStackTrace();
        }
    }
    
    public Xulu() {
        this.mc = Minecraft.getMinecraft();
        this.discordRP = new DiscordRP();
        this.shownLag = false;
    }
    
    private String getFacing(final String lIllllIlIlIIllI) {
        final String lIllllIlIlIlIII = getTitle(lIllllIlIlIIllI);
        String lIllllIlIlIIlll = null;
        if (lIllllIlIlIIllI.equalsIgnoreCase("North")) {
            final String lIllllIlIlIlllI = " §7[§r-Z§7]";
        }
        else if (lIllllIlIlIIllI.equalsIgnoreCase("East")) {
            final String lIllllIlIlIllIl = " §7[§r+X§7]";
        }
        else if (lIllllIlIlIIllI.equalsIgnoreCase("South")) {
            final String lIllllIlIlIllII = " §7[§r+Z§7]";
        }
        else if (lIllllIlIlIIllI.equalsIgnoreCase("West")) {
            final String lIllllIlIlIlIll = " §7[§r-X§7]";
        }
        else {
            lIllllIlIlIIlll = " ERROR";
        }
        return String.valueOf(new StringBuilder().append(lIllllIlIlIlIII).append(lIllllIlIlIIlll));
    }
    
    public static void setCFontRenderer(final String lIllllIlllIIIIl, final int lIllllIlllIIIlI) {
        try {
            if (getCorrectFont(lIllllIlllIIIIl) == null) {
                Command.sendChatMessage("Invalid font!");
                return;
            }
            if (lIllllIlllIIIIl.equalsIgnoreCase("Comfortaa Regular")) {
                CFontManager.customFont = new CustomFont(new Font("Comfortaa Regular", 0, lIllllIlllIIIlI), true, false);
                return;
            }
            CFontManager.customFont = new CustomFont(new Font(getCorrectFont(lIllllIlllIIIIl), 0, lIllllIlllIIIlI), true, false);
        }
        catch (Exception lIllllIlllIIlII) {
            lIllllIlllIIlII.printStackTrace();
        }
    }
    
    @EventTarget
    public void onKey(final EventKey lIllllIlIIllllI) {
        Xulu.MACRO_MANAGER.runMacros(lIllllIlIIllllI.getKey());
        Xulu.MODULE_MANAGER.getModules().stream().filter(lIllllIlIIlIlll -> lIllllIlIIlIlll.getKey() == lIllllIlIIllllI.getKey()).forEach(Module::toggle);
    }
    
    public static String getTitle(String lIllllIlIlIIIIl) {
        lIllllIlIlIIIIl = (Exception)String.valueOf(new StringBuilder().append(Character.toUpperCase(((String)lIllllIlIlIIIIl).toLowerCase().charAt(0))).append(((String)lIllllIlIlIIIIl).toLowerCase().substring(1)));
        return (String)lIllllIlIlIIIIl;
    }
    
    @SubscribeEvent
    public void onRenderGui(final RenderGameOverlayEvent.Post lIllllIlIlllIIl) {
        Xulu.CustomFont = Xulu.MODULE_MANAGER.getModule(com.elementars.eclient.module.core.CustomFont.class).isToggled();
        if (this.beginY != (Xulu.CustomFont ? (-Xulu.cFontRenderer.getHeight()) : ((float)(-Xulu.fontRenderer.FONT_HEIGHT)))) {
            this.beginY = (Xulu.CustomFont ? ((int)(-Xulu.cFontRenderer.getHeight())) : (-Xulu.fontRenderer.FONT_HEIGHT));
        }
        if (this.endY != 3.0f) {
            this.endY = 3;
        }
        final ScaledResolution lIllllIlIllllIl = new ScaledResolution(this.mc);
        Rainbow.updateRainbow();
        Xulu.rgb = Rainbow.rgb;
        if (lIllllIlIlllIIl.getType() != RenderGameOverlayEvent.ElementType.HOTBAR) {
            return;
        }
        if (!Xulu.VALUE_MANAGER.getValueByName("Rainbow Watermark").getValue()) {
            Xulu.rgb = ColorUtil.getClickGUIColor().getRGB();
        }
        final String lIllllIlIllllII = this.mc.player.getName();
        final int lIllllIlIlllIll = lIllllIlIllllIl.getScaledHeight() - 3;
        if (Global.coordinates.getValue()) {
            final String lIllllIllIIIIlI = String.valueOf(new StringBuilder().append(Xulu.df.format(this.mc.player.posX)).append(ChatFormatting.GRAY).append(", ").append(ChatFormatting.RESET).append(Xulu.df.format(this.mc.player.posY)).append(ChatFormatting.GRAY).append(", ").append(ChatFormatting.RESET).append(Xulu.df.format(this.mc.player.posZ)).append(ChatFormatting.GRAY).append(" [").append(ChatFormatting.RESET).append((this.mc.player.dimension == -1) ? String.valueOf(new StringBuilder().append(Xulu.df.format(this.mc.player.posX * 8.0)).append(ChatFormatting.GRAY).append(", ").append(ChatFormatting.RESET).append(Xulu.df.format(this.mc.player.posY)).append(ChatFormatting.GRAY).append(", ").append(ChatFormatting.RESET).append(Xulu.df.format(this.mc.player.posZ * 8.0))) : String.valueOf(new StringBuilder().append(Xulu.df.format(this.mc.player.posX / 8.0)).append(ChatFormatting.GRAY).append(", ").append(ChatFormatting.RESET).append(Xulu.df.format(this.mc.player.posY)).append(ChatFormatting.GRAY).append(", ").append(ChatFormatting.RESET).append(Xulu.df.format(this.mc.player.posZ / 8.0)))).append(ChatFormatting.GRAY).append("]"));
            if (Xulu.CustomFont) {
                Xulu.cFontRenderer.drawStringWithShadow(lIllllIllIIIIlI, 3.0, lIllllIlIlllIll - Xulu.cFontRenderer.getHeight() - 1.0f - (this.mc.ingameGUI.getChatGUI().getChatOpen() ? 11 : 0), ColorUtils.changeAlpha(Color.white.getRGB(), Global.hudAlpha.getValue()));
            }
            else {
                Xulu.fontRenderer.drawStringWithShadow(lIllllIllIIIIlI, 3.0f, (float)(lIllllIlIlllIll - Xulu.fontRenderer.FONT_HEIGHT - (this.mc.ingameGUI.getChatGUI().getChatOpen() ? 11 : 0)), ColorUtils.changeAlpha(Color.white.getRGB(), Global.hudAlpha.getValue()));
            }
        }
        if (Global.direction.getValue()) {
            if (Xulu.CustomFont) {
                Xulu.cFontRenderer.drawStringWithShadow(this.getFacing(this.mc.player.getHorizontalFacing().getName().toUpperCase()), 3.0, lIllllIlIlllIll - Xulu.cFontRenderer.getHeight() - (Global.coordinates.getValue() ? 11 : 1) - (this.mc.ingameGUI.getChatGUI().getChatOpen() ? 11 : 0), ColorUtils.changeAlpha(Color.white.getRGB(), Global.hudAlpha.getValue()));
            }
            else {
                Xulu.fontRenderer.drawStringWithShadow(this.getFacing(this.mc.player.getHorizontalFacing().getName().toUpperCase()), 3.0f, (float)(lIllllIlIlllIll - Xulu.fontRenderer.FONT_HEIGHT - (Global.coordinates.getValue() ? 10 : 0) - (this.mc.ingameGUI.getChatGUI().getChatOpen() ? 11 : 0)), ColorUtils.changeAlpha(Color.white.getRGB(), Global.hudAlpha.getValue()));
            }
        }
        if (Global.showLag.getValue() && !this.mc.isIntegratedServerRunning()) {
            if (LagUtil.INSTANCE.getLastTimeDiff() != 0L && LagUtil.INSTANCE.getLastTimeDiff() > 5000L) {
                final String lIllllIllIIIIIl = String.format("Server has been lagging for %01.1fs", (LagUtil.INSTANCE.getLastTimeDiff() - 1000L) / 1000.0f);
                if (!this.shownLag) {
                    this.yCount = this.beginY;
                }
                if (Xulu.CustomFont) {
                    Xulu.cFontRenderer.drawStringWithShadow(lIllllIllIIIIIl, lIllllIlIllllIl.getScaledWidth() / 2 - Xulu.cFontRenderer.getStringWidth(lIllllIllIIIIIl) / 2, this.yCount, Global.lagColor.getValue().equalsIgnoreCase("Default") ? Color.LIGHT_GRAY.getRGB() : ColorUtil.getClickGUIColor().getRGB());
                }
                else {
                    Xulu.fontRenderer.drawStringWithShadow(lIllllIllIIIIIl, (float)(lIllllIlIllllIl.getScaledWidth() / 2 - Xulu.fontRenderer.getStringWidth(lIllllIllIIIIIl) / 2), (float)this.yCount, Global.lagColor.getValue().equalsIgnoreCase("Default") ? Color.LIGHT_GRAY.getRGB() : ColorUtil.getClickGUIColor().getRGB());
                }
                this.shownLag = true;
                if (this.yCount != this.endY) {
                    ++this.yCount;
                }
            }
            else if (this.shownLag) {
                final String lIllllIllIIIIII = "Server has been lagging for 0.0s";
                if (Xulu.CustomFont) {
                    Xulu.cFontRenderer.drawStringWithShadow(lIllllIllIIIIII, lIllllIlIllllIl.getScaledWidth() / 2 - Xulu.cFontRenderer.getStringWidth(lIllllIllIIIIII) / 2, this.yCount, Global.lagColor.getValue().equalsIgnoreCase("Default") ? Color.LIGHT_GRAY.getRGB() : ColorUtil.getClickGUIColor().getRGB());
                }
                else {
                    Xulu.fontRenderer.drawStringWithShadow(lIllllIllIIIIII, (float)(lIllllIlIllllIl.getScaledWidth() / 2 - Xulu.fontRenderer.getStringWidth(lIllllIllIIIIII) / 2), (float)this.yCount, Global.lagColor.getValue().equalsIgnoreCase("Default") ? Color.LIGHT_GRAY.getRGB() : ColorUtil.getClickGUIColor().getRGB());
                }
                if (this.yCount != this.beginY) {
                    --this.yCount;
                }
                else {
                    this.shownLag = false;
                }
            }
        }
    }
    
    public static String getCorrectFont(final String lIllllIlllIllII) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_1        /* lIllllIlllIlIll */
        //     4: aload_1         /* lIllllIlllIlIll */
        //     5: arraylength    
        //     6: istore_2        /* lIllllIlllIlIlI */
        //     7: iconst_0       
        //     8: istore_3        /* lIllllIlllIlIIl */
        //     9: iload_3         /* lIllllIlllIlIIl */
        //    10: iload_2         /* lIllllIlllIlIlI */
        //    11: if_icmpge       37
        //    14: aload_1         /* lIllllIlllIlIll */
        //    15: iload_3         /* lIllllIlllIlIIl */
        //    16: aaload         
        //    17: astore          lIllllIlllIlllI
        //    19: aload           lIllllIlllIlllI
        //    21: aload_0         /* lIllllIlllIllIl */
        //    22: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    25: ifeq            31
        //    28: aload           lIllllIlllIlllI
        //    30: areturn        
        //    31: iinc            lIllllIlllIlIIl, 1
        //    34: goto            9
        //    37: aconst_null    
        //    38: areturn        
        //    StackMapTable: 00 03 FE 00 09 07 02 C8 01 01 FC 00 15 07 00 FB FA 00 05
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.lang.Thread.run(Thread.java:748)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void stopClient() {
        save();
        Xulu.MODULE_MANAGER.getModules().forEach(Module::destroy);
        Xulu.EVENT_MANAGER.unregister(this);
    }
    
    public static void setcFontRendererDefault() {
        try {
            CFontManager.customFont = new CustomFont(new Font("Verdana", 0, 18), true, false);
        }
        catch (Exception lIllllIllIlllIl) {
            lIllllIllIlllIl.printStackTrace();
        }
    }
    
    public static String[] getFonts() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    }
    
    public static void save() {
        Xulu.fileManager.saveHacks();
        Xulu.fileManager.saveBinds();
        Xulu.fileManager.saveDrawn();
        Xulu.fileManager.saveSettingsList();
        Xulu.fileManager.saveMacros();
        Xulu.fileManager.savePrefix();
        Xulu.fileManager.saveNewGui();
        Xulu.fileManager.saveFriends();
        Xulu.fileManager.saveEnemies();
        Xulu.fileManager.saveHUD();
        Xulu.fileManager.saveFont();
        Xulu.fileManager.saveStickyNote();
        Xulu.fileManager.saveWelcomeMessage();
        Xulu.fileManager.saveDummy();
        Xulu.fileManager.saveXray();
        Xulu.fileManager.saveSearch();
        Xulu.fileManager.saveWaypoints();
        Xulu.fileManager.saveNicks();
        System.out.println("Xulu Saved!");
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent lIllllIllIIllII) {
        System.out.println("[Xulu] Starting client, v1.5.2, created by Elementars");
        Display.setTitle("Xulu v1.5.2");
        this.discordRP.start();
        ColorTextUtils.initColors();
        Capes.getUsersCape();
        final FontInit lIllllIllIIlIll = new FontInit();
        lIllllIllIIlIll.initFonts();
        Xulu.MODULE_MANAGER.init();
        Xulu.COMMAND_MANAGER.init();
        Xulu.cFontRenderer = new CFontManager();
        Xulu.fileManager.loadDummy();
        Xulu.newGUI = new NewGUI();
        Xulu.hud = new HUD();
        HUD.registerElements();
        Xulu.hud.refreshPanel();
        LagCompensator.INSTANCE = new LagCompensator();
        LagUtil.INSTANCE = new LagUtil();
        ConnectionUtil.INSTANCE = new ConnectionUtil();
        Xulu.fileManager.loadHacks();
        Xulu.fileManager.loadDrawn();
        Xulu.fileManager.loadSettingsList();
        Xulu.fileManager.loadBinds();
        Xulu.fileManager.loadMacros();
        Xulu.fileManager.loadPrefix();
        Xulu.fileManager.loadNewGui();
        Xulu.fileManager.loadFriends();
        Xulu.fileManager.loadEnemies();
        Xulu.fileManager.loadHUD();
        Xulu.fileManager.loadFont();
        Xulu.fileManager.loadStickyNote();
        Xulu.fileManager.loadWelcomeMessage();
        Xulu.fileManager.loadXray();
        Xulu.fileManager.loadSearch();
        Xulu.fileManager.loadWaypoints();
        Xulu.fileManager.loadNicks();
        Xulu.EVENT_BUS.register((Object)new KeyRegistry());
        Xulu.EVENT_BUS.register((Object)this);
        AnnouncerRegistry.initAnnouncer();
        Xulu.EVENT_MANAGER.register(this);
        Xulu.rgb = Color.HSBtoRGB(0.01f, Global.rainbowSaturation.getValue() / 255.0f, Global.rainbowLightness.getValue() / 255.0f);
    }
    
    public static class Rainbow
    {
        public static /* synthetic */ int g;
        public static /* synthetic */ int b;
        public static /* synthetic */ int r;
        private static /* synthetic */ int rgb;
        public static /* synthetic */ int a;
        static /* synthetic */ float hue;
        
        static {
            Rainbow.hue = 0.01f;
        }
        
        public static void updateRainbow() {
            Rainbow.rgb = Color.HSBtoRGB(Rainbow.hue, Global.rainbowSaturation.getValue() / 255.0f, Global.rainbowLightness.getValue() / 255.0f);
            Rainbow.a = (Rainbow.rgb >>> 24 & 0xFF);
            Rainbow.r = (Rainbow.rgb >>> 16 & 0xFF);
            Rainbow.g = (Rainbow.rgb >>> 8 & 0xFF);
            Rainbow.b = (Rainbow.rgb & 0xFF);
            Rainbow.hue += Global.rainbowspeed2.getValue() / 100000.0f;
            if (Rainbow.hue > 1.0f) {
                --Rainbow.hue;
            }
        }
    }
}
