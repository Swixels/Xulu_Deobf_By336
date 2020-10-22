package com.elementars.eclient.module.render;

import net.minecraft.util.*;
import dev.xulu.settings.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.world.*;
import com.elementars.eclient.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.elementars.eclient.module.*;
import java.util.*;

public class BossStack extends Module
{
    private static final /* synthetic */ ResourceLocation GUI_BARS_TEXTURES;
    public static /* synthetic */ Value<String> mode;
    private static /* synthetic */ Value<Float> scale;
    
    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @SubscribeEvent
    public void render(final RenderGameOverlayEvent.Pre llllllllllllllllIlIlIIlIIllIIIIl) {
        if (BossStack.mode.getValue().equalsIgnoreCase("Minimize")) {
            final Map<UUID, BossInfoClient> llllllllllllllllIlIlIIlIIlllIlII = (Map<UUID, BossInfoClient>)Minecraft.getMinecraft().ingameGUI.getBossOverlay().mapBossInfos;
            if (llllllllllllllllIlIlIIlIIlllIlII == null) {
                return;
            }
            final ScaledResolution llllllllllllllllIlIlIIlIIlllIIll = new ScaledResolution(Minecraft.getMinecraft());
            final int llllllllllllllllIlIlIIlIIlllIIlI = llllllllllllllllIlIlIIlIIlllIIll.getScaledWidth();
            int llllllllllllllllIlIlIIlIIlllIIIl = 12;
            for (final Map.Entry<UUID, BossInfoClient> llllllllllllllllIlIlIIlIIlllIlIl : llllllllllllllllIlIlIIlIIlllIlII.entrySet()) {
                final BossInfoClient llllllllllllllllIlIlIIlIIllllIII = llllllllllllllllIlIlIIlIIlllIlIl.getValue();
                final String llllllllllllllllIlIlIIlIIlllIlll = llllllllllllllllIlIlIIlIIllllIII.getName().getFormattedText();
                final int llllllllllllllllIlIlIIlIIlllIllI = (int)(llllllllllllllllIlIlIIlIIlllIIlI / BossStack.scale.getValue() / 2.0f - 91.0f);
                GL11.glScaled((double)BossStack.scale.getValue(), (double)BossStack.scale.getValue(), 1.0);
                if (!llllllllllllllllIlIlIIlIIllIIIIl.isCanceled()) {
                    GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                    Minecraft.getMinecraft().getTextureManager().bindTexture(BossStack.GUI_BARS_TEXTURES);
                    Minecraft.getMinecraft().ingameGUI.getBossOverlay().render(llllllllllllllllIlIlIIlIIlllIllI, llllllllllllllllIlIlIIlIIlllIIIl, (BossInfo)llllllllllllllllIlIlIIlIIllllIII);
                    Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(llllllllllllllllIlIlIIlIIlllIlll, llllllllllllllllIlIlIIlIIlllIIlI / BossStack.scale.getValue() / 2.0f - Minecraft.getMinecraft().fontRenderer.getStringWidth(llllllllllllllllIlIlIIlIIlllIlll) / 2, (float)(llllllllllllllllIlIlIIlIIlllIIIl - 9), 16777215);
                }
                GL11.glScaled(1.0 / BossStack.scale.getValue(), 1.0 / BossStack.scale.getValue(), 1.0);
                llllllllllllllllIlIlIIlIIlllIIIl += 10 + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
            }
        }
        else if (BossStack.mode.getValue().equalsIgnoreCase("Stack")) {
            final Map<UUID, BossInfoClient> llllllllllllllllIlIlIIlIIllIIlll = (Map<UUID, BossInfoClient>)Minecraft.getMinecraft().ingameGUI.getBossOverlay().mapBossInfos;
            final HashMap<String, Pair<BossInfoClient, Integer>> llllllllllllllllIlIlIIlIIllIIllI = new HashMap<String, Pair<BossInfoClient, Integer>>();
            for (final Map.Entry<UUID, BossInfoClient> llllllllllllllllIlIlIIlIIllIllIl : llllllllllllllllIlIlIIlIIllIIlll.entrySet()) {
                final String llllllllllllllllIlIlIIlIIllIlllI = llllllllllllllllIlIlIIlIIllIllIl.getValue().getName().getFormattedText();
                if (llllllllllllllllIlIlIIlIIllIIllI.containsKey(llllllllllllllllIlIlIIlIIllIlllI)) {
                    Pair<BossInfoClient, Integer> llllllllllllllllIlIlIIlIIlllIIII = llllllllllllllllIlIlIIlIIllIIllI.get(llllllllllllllllIlIlIIlIIllIlllI);
                    llllllllllllllllIlIlIIlIIlllIIII = new Pair<BossInfoClient, Integer>(llllllllllllllllIlIlIIlIIlllIIII.getKey(), llllllllllllllllIlIlIIlIIlllIIII.getValue() + 1);
                    llllllllllllllllIlIlIIlIIllIIllI.put(llllllllllllllllIlIlIIlIIllIlllI, llllllllllllllllIlIlIIlIIlllIIII);
                }
                else {
                    final Pair<BossInfoClient, Integer> llllllllllllllllIlIlIIlIIllIllll = new Pair<BossInfoClient, Integer>(llllllllllllllllIlIlIIlIIllIllIl.getValue(), 1);
                    llllllllllllllllIlIlIIlIIllIIllI.put(llllllllllllllllIlIlIIlIIllIlllI, llllllllllllllllIlIlIIlIIllIllll);
                }
            }
            final ScaledResolution llllllllllllllllIlIlIIlIIllIIlIl = new ScaledResolution(Minecraft.getMinecraft());
            final int llllllllllllllllIlIlIIlIIllIIlII = llllllllllllllllIlIlIIlIIllIIlIl.getScaledWidth();
            int llllllllllllllllIlIlIIlIIllIIIll = 12;
            for (final Map.Entry<String, Pair<BossInfoClient, Integer>> llllllllllllllllIlIlIIlIIllIlIII : llllllllllllllllIlIlIIlIIllIIllI.entrySet()) {
                String llllllllllllllllIlIlIIlIIllIllII = llllllllllllllllIlIlIIlIIllIlIII.getKey();
                final BossInfoClient llllllllllllllllIlIlIIlIIllIlIll = llllllllllllllllIlIlIIlIIllIlIII.getValue().getKey();
                final int llllllllllllllllIlIlIIlIIllIlIlI = llllllllllllllllIlIlIIlIIllIlIII.getValue().getValue();
                llllllllllllllllIlIlIIlIIllIllII = String.valueOf(new StringBuilder().append(llllllllllllllllIlIlIIlIIllIllII).append(" x").append(llllllllllllllllIlIlIIlIIllIlIlI));
                final int llllllllllllllllIlIlIIlIIllIlIIl = (int)(llllllllllllllllIlIlIIlIIllIIlII / BossStack.scale.getValue() / 2.0f - 91.0f);
                GL11.glScaled((double)BossStack.scale.getValue(), (double)BossStack.scale.getValue(), 1.0);
                if (!llllllllllllllllIlIlIIlIIllIIIIl.isCanceled()) {
                    GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                    Minecraft.getMinecraft().getTextureManager().bindTexture(BossStack.GUI_BARS_TEXTURES);
                    Minecraft.getMinecraft().ingameGUI.getBossOverlay().render(llllllllllllllllIlIlIIlIIllIlIIl, llllllllllllllllIlIlIIlIIllIIIll, (BossInfo)llllllllllllllllIlIlIIlIIllIlIll);
                    Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(llllllllllllllllIlIlIIlIIllIllII, llllllllllllllllIlIlIIlIIllIIlII / BossStack.scale.getValue() / 2.0f - Minecraft.getMinecraft().fontRenderer.getStringWidth(llllllllllllllllIlIlIIlIIllIllII) / 2, (float)(llllllllllllllllIlIlIIlIIllIIIll - 9), 16777215);
                }
                GL11.glScaled(1.0 / BossStack.scale.getValue(), 1.0 / BossStack.scale.getValue(), 1.0);
                llllllllllllllllIlIlIIlIIllIIIll += 10 + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
            }
        }
    }
    
    static {
        GUI_BARS_TEXTURES = new ResourceLocation("textures/gui/bars.png");
    }
    
    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public BossStack() {
        super("BossStack", "Stacks boss bars", 0, Category.RENDER, true);
        BossStack.mode = this.register(new Value<String>("Mode", this, "Stack", new ArrayList<String>(Arrays.asList("Remove", "Stack", "Minimize"))));
        BossStack.scale = this.register(new Value<Float>("Scale", this, 0.5f, 0.0f, 1.0f));
    }
}
