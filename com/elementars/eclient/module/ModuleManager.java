package com.elementars.eclient.module;

import java.util.*;
import com.elementars.eclient.*;
import com.elementars.eclient.event.events.*;
import net.minecraft.client.*;
import com.elementars.eclient.module.core.*;
import com.elementars.eclient.module.combat.*;
import com.elementars.eclient.module.misc.*;
import me.memeszz.aurora.module.modules.movement.*;
import com.elementars.eclient.module.movement.*;
import com.elementars.eclient.module.player.*;
import com.elementars.eclient.module.render.*;
import com.elementars.eclient.guirewrite.*;
import com.elementars.eclient.guirewrite.elements.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.*;
import com.elementars.eclient.util.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.math.*;

public class ModuleManager
{
    private static /* synthetic */ ArrayList<Module> modules;
    
    public static void onUpdate() {
        Xulu.MODULE_MANAGER.getModules().stream().filter(Module::isToggled).forEach(Module::onUpdate);
        NewGui.resetGui();
        Xulu.TASK_SCHEDULER.onUpdate();
    }
    
    static {
        ModuleManager.modules = new ArrayList<Module>();
    }
    
    public <T extends Module> T getModuleT(final Class<T> llllllllllllllllllllllIlIIlIIIII) {
        return ModuleManager.modules.stream().filter(llllllllllllllllllllllIlIIIIlIlI -> llllllllllllllllllllllIlIIIIlIlI.getClass() == llllllllllllllllllllllIlIIlIIIII).map(llllllllllllllllllllllIlIIIlIIII -> llllllllllllllllllllllIlIIIlIIII).findFirst().orElse(null);
    }
    
    public void init() {
        ModuleManager.modules.add(new Global());
        ModuleManager.modules.add(new CustomFont());
        ModuleManager.modules.add(new TitleScreenShader());
        ModuleManager.modules.add(new AntiChainPop());
        ModuleManager.modules.add(new Aura());
        ModuleManager.modules.add(new AutoArmor());
        ModuleManager.modules.add(new AutoCrystal());
        ModuleManager.modules.add(new AutoEz());
        ModuleManager.modules.add(new AutoFeetBlock());
        ModuleManager.modules.add(new AutoRepair());
        ModuleManager.modules.add(new AutoTotem());
        ModuleManager.modules.add(new AutoTrap());
        ModuleManager.modules.add(new AutoWeb());
        ModuleManager.modules.add(new BreakAlert());
        ModuleManager.modules.add(new CityBlocker());
        ModuleManager.modules.add(new Criticals());
        ModuleManager.modules.add(new DurabilityAlert());
        ModuleManager.modules.add(new EXPFast());
        ModuleManager.modules.add(new FastBow());
        ModuleManager.modules.add(new FuckedDetect());
        ModuleManager.modules.add(new HoleBlocker());
        ModuleManager.modules.add(new HoleFill());
        ModuleManager.modules.add(new MiddleClickPearl());
        ModuleManager.modules.add(new Offhand());
        ModuleManager.modules.add(new PearlAlert());
        ModuleManager.modules.add(new PopCounter());
        ModuleManager.modules.add(new SelfWeb());
        ModuleManager.modules.add(new Sharp32kDetect());
        ModuleManager.modules.add(new StrengthDetect());
        ModuleManager.modules.add(new Surround());
        ModuleManager.modules.add(new Announcer());
        ModuleManager.modules.add(new AntiDeathScreen());
        ModuleManager.modules.add(new AntiSound());
        ModuleManager.modules.add(new AntiSpam());
        ModuleManager.modules.add(new Australia());
        ModuleManager.modules.add(new AutoQMain());
        ModuleManager.modules.add(new AutoWither());
        ModuleManager.modules.add(new Avoid());
        ModuleManager.modules.add(new CameraClip());
        ModuleManager.modules.add(new ColorSign());
        ModuleManager.modules.add(new CoordLogger());
        ModuleManager.modules.add(new CrashExploit());
        ModuleManager.modules.add(new CustomChat());
        ModuleManager.modules.add(new DonkeyAlert());
        ModuleManager.modules.add(new FakePlayer());
        ModuleManager.modules.add(new FakeVanilla());
        ModuleManager.modules.add(new FovSlider());
        ModuleManager.modules.add(new HopperNuker());
        ModuleManager.modules.add(new LiquidInteract());
        ModuleManager.modules.add(new MCF());
        ModuleManager.modules.add(new MobOwner());
        ModuleManager.modules.add(new NoEntityTrace());
        ModuleManager.modules.add(new NoPacketKick());
        ModuleManager.modules.add(new PortalChat());
        ModuleManager.modules.add(new SkinFlicker());
        ModuleManager.modules.add(new Time());
        ModuleManager.modules.add(new Timer());
        ModuleManager.modules.add(new VisualRange());
        ModuleManager.modules.add(new ElytraFly());
        ModuleManager.modules.add(new FastSwim());
        ModuleManager.modules.add(new Flight());
        ModuleManager.modules.add(new ForgeFly());
        ModuleManager.modules.add(new GuiMove());
        ModuleManager.modules.add(new HoleTP());
        ModuleManager.modules.add(new Jesus());
        ModuleManager.modules.add(new LongJump());
        ModuleManager.modules.add(new NoSlowDown());
        ModuleManager.modules.add(new Sprint());
        ModuleManager.modules.add(new Step());
        ModuleManager.modules.add(new Strafe());
        ModuleManager.modules.add(new Velocity());
        ModuleManager.modules.add(new AntiVoid());
        ModuleManager.modules.add(new AutoReplenish());
        ModuleManager.modules.add(new AutoWalk());
        ModuleManager.modules.add(new Blink());
        ModuleManager.modules.add(new FastFall());
        ModuleManager.modules.add(new Freecam());
        ModuleManager.modules.add(new ItemSpoof());
        ModuleManager.modules.add(new Multitask());
        ModuleManager.modules.add(new NoBreakAnimation());
        ModuleManager.modules.add(new PacketSwing());
        ModuleManager.modules.add(new SelfLogoutSpot());
        ModuleManager.modules.add(new Speedmine());
        ModuleManager.modules.add(new TpsSync());
        ModuleManager.modules.add(new XCarry());
        ModuleManager.modules.add(new AntiFog());
        ModuleManager.modules.add(new Arrows());
        ModuleManager.modules.add(new BlockHighlight());
        ModuleManager.modules.add(new BossStack());
        ModuleManager.modules.add(new BreakESP());
        ModuleManager.modules.add(new Cape());
        ModuleManager.modules.add(new Chams());
        ModuleManager.modules.add(new Chat());
        ModuleManager.modules.add(new ChunkFinder());
        ModuleManager.modules.add(new Compass());
        ModuleManager.modules.add(new EnchantColor());
        ModuleManager.modules.add(new NewGui());
        ModuleManager.modules.add(new ESP());
        ModuleManager.modules.add(new ExeterGui());
        ModuleManager.modules.add(new ExtraTab());
        ModuleManager.modules.add(new FullBright());
        ModuleManager.modules.add(new HellenKeller());
        ModuleManager.modules.add(new HoleESP());
        ModuleManager.modules.add(new ImageESP());
        ModuleManager.modules.add(new ItemESP());
        ModuleManager.modules.add(new LogoutSpots());
        ModuleManager.modules.add(new Nametags());
        ModuleManager.modules.add(new NoHurtCam());
        ModuleManager.modules.add(new NoRender());
        ModuleManager.modules.add(new OffhandSwing());
        ModuleManager.modules.add(new OutlineESP());
        ModuleManager.modules.add(new Pathfind());
        ModuleManager.modules.add(new Search());
        ModuleManager.modules.add(new SecretShaders());
        ModuleManager.modules.add(new PvPInfo());
        ModuleManager.modules.add(new ShulkerPreview());
        ModuleManager.modules.add(new Skeleton());
        ModuleManager.modules.add(new StorageESP());
        ModuleManager.modules.add(new ToolTips());
        ModuleManager.modules.add(new Tracers());
        ModuleManager.modules.add(new Trajectories());
        ModuleManager.modules.add(new ViewmodelChanger());
        ModuleManager.modules.add(new VoidESP());
        ModuleManager.modules.add(new Waypoints());
        ModuleManager.modules.add(new Xray());
        ModuleManager.modules.add(new HudEditor());
        ModuleManager.modules.add(new Totems());
        ModuleManager.modules.add(new Obsidian());
        ModuleManager.modules.add(new Crystals());
        ModuleManager.modules.add(new Gapples());
        ModuleManager.modules.add(new Exp());
        ModuleManager.modules.add(new InvPreview());
        ModuleManager.modules.add(new CraftingPreview());
        ModuleManager.modules.add(new TextRadar());
        ModuleManager.modules.add(new FeatureList());
        ModuleManager.modules.add(new Player());
        ModuleManager.modules.add(new Welcome());
        ModuleManager.modules.add(new OldName());
        ModuleManager.modules.add(new TheGoons());
        ModuleManager.modules.add(new Potions());
        ModuleManager.modules.add(new StickyNotes());
        ModuleManager.modules.add(new HoleHud());
        ModuleManager.modules.add(new Info());
        ModuleManager.modules.add(new Armor());
        ModuleManager.modules.add(new GodInfo());
        ModuleManager.modules.add(new Watermark());
        ModuleManager.modules.add(new Logo());
        ModuleManager.modules.add(new Target());
    }
    
    public ArrayList<Module> getModules() {
        return ModuleManager.modules;
    }
    
    @Deprecated
    public Module getModuleByName(final String llllllllllllllllllllllIlIIlIIlll) {
        return ModuleManager.modules.stream().filter(llllllllllllllllllllllIlIIIIIIII -> llllllllllllllllllllllIlIIIIIIII.getName().equalsIgnoreCase(llllllllllllllllllllllIlIIlIIlll)).findFirst().orElse(null);
    }
    
    public static boolean isModuleEnabled(final String llllllllllllllllllllllIlIIIlllII) {
        final Module llllllllllllllllllllllIlIIIllIll = ModuleManager.modules.stream().filter(llllllllllllllllllllllIlIIIlIIll -> llllllllllllllllllllllIlIIIlIIll.getName().equalsIgnoreCase(llllllllllllllllllllllIlIIIlllII)).findFirst().orElse(null);
        return llllllllllllllllllllllIlIIIllIll != null && llllllllllllllllllllllIlIIIllIll.isToggled();
    }
    
    public static void onWorldRender(final RenderWorldLastEvent llllllllllllllllllllllIlIIlIlllI) {
        Minecraft.getMinecraft().profiler.startSection("eclient");
        Minecraft.getMinecraft().profiler.startSection("setup");
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GlStateManager.disableDepth();
        GlStateManager.glLineWidth(1.0f);
        final Vec3d llllllllllllllllllllllIlIIllIIII = EntityUtil.getInterpolatedPos((Entity)Wrapper.getPlayer(), llllllllllllllllllllllIlIIlIlllI.getPartialTicks());
        final RenderEvent llllllllllllllllllllllIlIIlIllll = new RenderEvent(XuluTessellator.INSTANCE, llllllllllllllllllllllIlIIllIIII);
        llllllllllllllllllllllIlIIlIllll.resetTranslation();
        Minecraft.getMinecraft().profiler.endSection();
        final RenderEvent lllllllllllllllllIIllIlIIIIlIlII;
        Xulu.MODULE_MANAGER.getModules().stream().filter(Module::isToggled).forEach(llllllllllllllllllllllIIlllllIlI -> {
            Minecraft.getMinecraft().profiler.startSection(llllllllllllllllllllllIIlllllIlI.getName());
            llllllllllllllllllllllIIlllllIlI.onWorldRender(lllllllllllllllllIIllIlIIIIlIlII);
            Minecraft.getMinecraft().profiler.endSection();
            return;
        });
        Minecraft.getMinecraft().profiler.startSection("release");
        GlStateManager.glLineWidth(1.0f);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        XuluTessellator.releaseGL();
        Minecraft.getMinecraft().profiler.endSection();
        Minecraft.getMinecraft().profiler.endSection();
    }
    
    public static void onRender() {
        Xulu.MODULE_MANAGER.getModules().stream().filter(Module::isToggled).forEach(Module::onRender);
    }
    
    public Module getModule(final Class<? extends Module> llllllllllllllllllllllIlIIlIIIll) {
        return ModuleManager.modules.stream().filter(llllllllllllllllllllllIlIIIIIllI -> llllllllllllllllllllllIlIIIIIllI.getClass() == llllllllllllllllllllllIlIIlIIIll).findFirst().orElse(null);
    }
}
