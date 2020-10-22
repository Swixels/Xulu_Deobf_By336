package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import dev.xulu.settings.*;
import com.elementars.eclient.module.*;
import com.google.common.collect.*;
import java.awt.*;
import com.elementars.eclient.*;
import com.elementars.eclient.command.*;
import java.util.stream.*;
import java.util.function.*;
import dev.xulu.newgui.util.*;
import com.mojang.realmsclient.gui.*;
import com.elementars.eclient.module.core.*;
import com.elementars.eclient.util.*;
import com.elementars.eclient.font.*;
import java.util.*;
import net.minecraft.client.gui.*;

public class FeatureList extends Element
{
    private /* synthetic */ Value<Integer> rsaturation;
    private /* synthetic */ Rainbow rainbow;
    private /* synthetic */ Map<Module, Triplet<Double, Double, Pair<Double, Integer>>> animationMap;
    private /* synthetic */ Value<String> categoryProfile;
    private /* synthetic */ List<Module> removal;
    private /* synthetic */ Value<Integer> rainbowspeed;
    private /* synthetic */ Value<String> mode;
    public static /* synthetic */ Value<String> type;
    private /* synthetic */ Value<Boolean> box;
    private /* synthetic */ Value<Integer> rlightness;
    private /* synthetic */ Value<Boolean> corner;
    private /* synthetic */ Value<String> rlist;
    private /* synthetic */ Value<String> suffix;
    private /* synthetic */ Value<String> prefix;
    private /* synthetic */ Value<String> order;
    private /* synthetic */ Value<Integer> rspeed;
    private /* synthetic */ Value<String> boxMode;
    public static /* synthetic */ Value<Boolean> animation;
    /* synthetic */ String comp;
    private /* synthetic */ Value<Boolean> alphab;
    
    @Override
    public void onEnable() {
        this.width = 80.0;
        this.height = 80.0;
    }
    
    private int betterCompare(final Module lllllllllllllllllIlllIlIlllIllll, final String lllllllllllllllllIlllIlIlllIlllI) {
        int lllllllllllllllllIlllIlIlllIllIl = 0;
        lllllllllllllllllIlllIlIlllIllIl = lllllllllllllllllIlllIlIlllIllll.getName().compareTo(lllllllllllllllllIlllIlIlllIlllI);
        this.comp = lllllllllllllllllIlllIlIlllIlllI;
        return lllllllllllllllllIlllIlIlllIllIl;
    }
    
    public FeatureList() {
        super("FeatureList");
        this.rainbow = new Rainbow();
        this.animationMap = (Map<Module, Triplet<Double, Double, Pair<Double, Integer>>>)Maps.newHashMap();
        this.removal = new ArrayList<Module>();
        this.corner = this.register(new Value<Boolean>("List In Corner", this, false));
        FeatureList.animation = this.register(new Value<Boolean>("Animation", this, false));
        FeatureList.type = this.register(new Value<String>("Type", this, "Both", new String[] { "Both", "Enable", "Disable" }));
        this.alphab = this.register(new Value<Boolean>("Alphabetical", this, false));
        this.box = this.register(new Value<Boolean>("Boxes", this, false));
        this.boxMode = this.register(new Value<String>("Box Mode", this, "Tag", new String[] { "Black", "Tag", "Outline" }));
        this.prefix = this.register(new Value<String>("Prefix", this, "None", new String[] { "None", ">", ")", "]", "}", ">(space)", "->", "-", "=", "<", "(", "[", "{" }));
        this.suffix = this.register(new Value<String>("Suffix", this, "None", new String[] { "None", ">", ")", "]", "}", "(space)<", "<-", "-", "=", "<", "(", "[", "{" }));
        this.mode = this.register(new Value<String>("Aligned", this, "Left", new ArrayList<String>(Arrays.asList("Left", "Right"))));
        this.order = this.register(new Value<String>("Ordering", this, "Up", new ArrayList<String>(Arrays.asList("Up", "Down"))));
        this.rlist = this.register(new Value<String>("Color Mode", this, "ClickGui", new String[] { "ClickGui", "Rainbow", "Category" }));
        this.categoryProfile = this.register(new Value<String>("Category Mode", this, "Xulu", new String[] { "Xulu", "Impact", "DotGod" }));
        this.rainbowspeed = this.register(new Value<Integer>("Rainbow Speed", this, 5, 1, 100));
        this.rspeed = this.register(new Value<Integer>("Rainbow Size", this, 2, 0, 20));
        this.rsaturation = this.register(new Value<Integer>("Rainbow Sat.", this, 255, 0, 255));
        this.rlightness = this.register(new Value<Integer>("Rainbow Light.", this, 255, 0, 255));
    }
    
    private int getCategoryColor(final Module lllllllllllllllllIlllIlIIllIlIIl) {
        final long lllllllllllllllllIlllIlIIllIlIII = (long)this.categoryProfile.getValue();
        double lllllllllllllllllIlllIlIIllIIlll = -1;
        switch (((String)lllllllllllllllllIlllIlIIllIlIII).hashCode()) {
            case 2737510: {
                if (((String)lllllllllllllllllIlllIlIIllIlIII).equals("Xulu")) {
                    lllllllllllllllllIlllIlIIllIIlll = 0;
                    break;
                }
                break;
            }
            case -2100942490: {
                if (((String)lllllllllllllllllIlllIlIIllIlIII).equals("Impact")) {
                    lllllllllllllllllIlllIlIIllIIlll = 1;
                    break;
                }
                break;
            }
            case 2052820627: {
                if (((String)lllllllllllllllllIlllIlIIllIlIII).equals("DotGod")) {
                    lllllllllllllllllIlllIlIIllIIlll = 2;
                    break;
                }
                break;
            }
        }
        Label_0539: {
            switch (lllllllllllllllllIlllIlIIllIIlll) {
                case 0.0: {
                    switch (lllllllllllllllllIlllIlIIllIlIIl.getCategory()) {
                        case CORE: {
                            return new Color(0, 218, 242).getRGB();
                        }
                        case COMBAT: {
                            return new Color(222, 57, 11).getRGB();
                        }
                        case MOVEMENT: {
                            return new Color(189, 28, 173).getRGB();
                        }
                        case PLAYER: {
                            return new Color(83, 219, 41).getRGB();
                        }
                        case RENDER: {
                            return new Color(255, 242, 62).getRGB();
                        }
                        case MISC: {
                            return new Color(255, 143, 15).getRGB();
                        }
                        case DUMMY: {
                            return new Color(222, 57, 209).getRGB();
                        }
                        case HUD: {
                            return new Color(255, 0, 123).getRGB();
                        }
                        case HIDDEN: {
                            return -1;
                        }
                        default: {
                            break Label_0539;
                        }
                    }
                    break;
                }
                case 1.0: {
                    switch (lllllllllllllllllIlllIlIIllIlIIl.getCategory()) {
                        case CORE: {
                            return new Color(0, 218, 242).getRGB();
                        }
                        case COMBAT: {
                            return new Color(229, 30, 16).getRGB();
                        }
                        case MOVEMENT: {
                            return new Color(8, 116, 227).getRGB();
                        }
                        case PLAYER: {
                            return new Color(43, 203, 55).getRGB();
                        }
                        case RENDER: {
                            return new Color(227, 162, 50).getRGB();
                        }
                        case MISC: {
                            return new Color(97, 30, 212).getRGB();
                        }
                        case DUMMY: {
                            return new Color(241, 243, 90).getRGB();
                        }
                        case HUD: {
                            return new Color(255, 0, 123).getRGB();
                        }
                        case HIDDEN: {
                            return -1;
                        }
                        default: {
                            break Label_0539;
                        }
                    }
                    break;
                }
                case 2.0: {
                    switch (lllllllllllllllllIlllIlIIllIlIIl.getCategory()) {
                        case CORE: {
                            return new Color(0, 218, 242).getRGB();
                        }
                        case COMBAT: {
                            return new Color(39, 181, 171).getRGB();
                        }
                        case MOVEMENT: {
                            return new Color(26, 84, 219).getRGB();
                        }
                        case PLAYER: {
                            return new Color(219, 184, 190).getRGB();
                        }
                        case RENDER: {
                            return new Color(169, 204, 83).getRGB();
                        }
                        case MISC: {
                            return new Color(215, 214, 216).getRGB();
                        }
                        case DUMMY: {
                            return new Color(222, 57, 209).getRGB();
                        }
                        case HUD: {
                            return new Color(255, 0, 123).getRGB();
                        }
                        case HIDDEN: {
                            return -1;
                        }
                        default: {
                            break Label_0539;
                        }
                    }
                    break;
                }
            }
        }
        return -1;
    }
    
    @Override
    public void onRender() {
        final ScaledResolution lllllllllllllllllIlllIlIlIIIlIll = new ScaledResolution(FeatureList.mc);
        double lllllllllllllllllIlllIlIlIIIlIlI = 3.0;
        double lllllllllllllllllIlllIlIlIIIlIIl = lllllllllllllllllIlllIlIlIIIlIll.getScaledWidth() - 3 - this.getFrame().width;
        if (!this.corner.getValue()) {
            lllllllllllllllllIlllIlIlIIIlIlI = this.y + 1.0;
            lllllllllllllllllIlllIlIlIIIlIIl = this.x + 1.0;
        }
        this.rainbow.updateRainbow();
        if (Xulu.CustomFont) {
            final CFontManager cFontRenderer;
            String value;
            final StringBuilder sb;
            final List<Module> lllllllllllllllllIlllIlIlIllIIll = Xulu.MODULE_MANAGER.getModules().stream().filter(Module::isToggledAnim).filter(Module::isDrawn).sorted(Comparator.comparing(lllllllllllllllllIlllIlIIlIlIIll -> {
                cFontRenderer = Xulu.cFontRenderer;
                new StringBuilder().append(lllllllllllllllllIlllIlIIlIlIIll.getName());
                if (lllllllllllllllllIlllIlIIlIlIIll.getHudInfo() == null) {
                    value = "";
                }
                else {
                    value = String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append("7 [").append(lllllllllllllllllIlllIlIIlIlIIll.getHudInfo()).append("]"));
                }
                return Integer.valueOf(cFontRenderer.getStringWidth(String.valueOf(sb.append(value))) * -1);
            })).collect((Collector<? super Object, ?, List<Module>>)Collectors.toList());
            if (this.alphab.getValue()) {
                final String[] lllllllllllllllllIlllIlIllIlIIII = lllllllllllllllllIlllIlIlIllIIll.stream().map((Function<? super Object, ?>)Module::getName).toArray(String[]::new);
                for (int lllllllllllllllllIlllIlIllIIllll = lllllllllllllllllIlllIlIlIllIIll.size(), lllllllllllllllllIlllIlIllIlIIlI = 0; lllllllllllllllllIlllIlIllIlIIlI < lllllllllllllllllIlllIlIllIIllll; ++lllllllllllllllllIlllIlIllIlIIlI) {
                    for (int lllllllllllllllllIlllIlIllIlIIll = lllllllllllllllllIlllIlIllIlIIlI + 1; lllllllllllllllllIlllIlIllIlIIll < lllllllllllllllllIlllIlIllIIllll; ++lllllllllllllllllIlllIlIllIlIIll) {
                        if (lllllllllllllllllIlllIlIllIlIIII[lllllllllllllllllIlllIlIllIlIIlI].compareTo(lllllllllllllllllIlllIlIllIlIIII[lllllllllllllllllIlllIlIllIlIIll]) > 0) {
                            final String lllllllllllllllllIlllIlIllIlIlII = lllllllllllllllllIlllIlIllIlIIII[lllllllllllllllllIlllIlIllIlIIlI];
                            lllllllllllllllllIlllIlIllIlIIII[lllllllllllllllllIlllIlIllIlIIlI] = lllllllllllllllllIlllIlIllIlIIII[lllllllllllllllllIlllIlIllIlIIll];
                            lllllllllllllllllIlllIlIllIlIIII[lllllllllllllllllIlllIlIllIlIIll] = lllllllllllllllllIlllIlIllIlIlII;
                        }
                    }
                }
                lllllllllllllllllIlllIlIlIllIIll.clear();
                for (final String lllllllllllllllllIlllIlIllIlIIIl : lllllllllllllllllIlllIlIllIlIIII) {
                    try {
                        lllllllllllllllllIlllIlIlIllIIll.add(Xulu.MODULE_MANAGER.getModuleByName(lllllllllllllllllIlllIlIllIlIIIl));
                    }
                    catch (Exception ex) {}
                }
            }
            boolean lllllllllllllllllIlllIlIlIllIIlI = true;
            if (this.order.getValue().equalsIgnoreCase("Down")) {
                lllllllllllllllllIlllIlIlIIIlIlI += 69.0;
            }
            float lllllllllllllllllIlllIlIlIllIIIl = this.rainbow.hue;
            for (final Module lllllllllllllllllIlllIlIlIllllIl : lllllllllllllllllIlllIlIlIllIIll) {
                int lllllllllllllllllIlllIlIllIIIlII = Color.HSBtoRGB(lllllllllllllllllIlllIlIlIllIIIl, this.rsaturation.getValue() / 255.0f, this.rlightness.getValue() / 255.0f);
                final String s = this.rlist.getValue();
                switch (s) {
                    case "ClickGui": {
                        lllllllllllllllllIlllIlIllIIIlII = ColorUtil.getClickGUIColor().getRGB();
                        break;
                    }
                    case "Category": {
                        lllllllllllllllllIlllIlIllIIIlII = this.getCategoryColor(lllllllllllllllllIlllIlIlIllllIl);
                        break;
                    }
                }
                final String s2 = this.prefix.getValue();
                String lllllllllllllllllIlllIlIIllllIll = (String)(-1);
                switch (s2.hashCode()) {
                    case 2433880: {
                        if (s2.equals("None")) {
                            lllllllllllllllllIlllIlIIllllIll = (String)0;
                            break;
                        }
                        break;
                    }
                    case 924274701: {
                        if (s2.equals(">(space)")) {
                            lllllllllllllllllIlllIlIIllllIll = (String)1;
                            break;
                        }
                        break;
                    }
                }
                final String lllllllllllllllllIlllIlIllIIIIll;
                switch (lllllllllllllllllIlllIlIIllllIll) {
                    case 0L: {
                        final String lllllllllllllllllIlllIlIllIIlllI = "";
                        break;
                    }
                    case 1L: {
                        final String lllllllllllllllllIlllIlIllIIllIl = "> ";
                        break;
                    }
                    default: {
                        lllllllllllllllllIlllIlIllIIIIll = this.prefix.getValue();
                        break;
                    }
                }
                final String s3 = this.suffix.getValue();
                lllllllllllllllllIlllIlIIllllIll = (String)(-1);
                switch (s3.hashCode()) {
                    case 2433880: {
                        if (s3.equals("None")) {
                            lllllllllllllllllIlllIlIIllllIll = (String)0;
                            break;
                        }
                        break;
                    }
                    case -1019228271: {
                        if (s3.equals("(space)<")) {
                            lllllllllllllllllIlllIlIIllllIll = (String)1;
                            break;
                        }
                        break;
                    }
                }
                final String lllllllllllllllllIlllIlIllIIIIlI;
                switch (lllllllllllllllllIlllIlIIllllIll) {
                    case 0L: {
                        final String lllllllllllllllllIlllIlIllIIllII = "";
                        break;
                    }
                    case 1L: {
                        final String lllllllllllllllllIlllIlIllIIlIll = " <";
                        break;
                    }
                    default: {
                        lllllllllllllllllIlllIlIllIIIIlI = this.suffix.getValue();
                        break;
                    }
                }
                final String lllllllllllllllllIlllIlIllIIIIIl = String.valueOf(new StringBuilder().append(lllllllllllllllllIlllIlIllIIIIll).append(lllllllllllllllllIlllIlIlIllllIl.getName()).append((lllllllllllllllllIlllIlIlIllllIl.getHudInfo() == null) ? "" : String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append("7 [").append(Command.SECTIONSIGN()).append("f").append(lllllllllllllllllIlllIlIlIllllIl.getHudInfo()).append(Command.SECTIONSIGN()).append("7]").append(ChatFormatting.RESET))).append(lllllllllllllllllIlllIlIllIIIIlI));
                final double lllllllllllllllllIlllIlIllIIIIII = this.mode.getValue().equalsIgnoreCase("Right") ? (lllllllllllllllllIlllIlIlIIIlIIl - Xulu.cFontRenderer.getStringWidth(lllllllllllllllllIlllIlIllIIIIIl) + this.getFrame().width - 3.0) : lllllllllllllllllIlllIlIlIIIlIIl;
                final double lllllllllllllllllIlllIlIlIllllll = Xulu.cFontRenderer.getStringWidth(lllllllllllllllllIlllIlIllIIIIIl);
                if (this.box.getValue()) {
                    final byte lllllllllllllllllIlllIlIIllllIIl = (byte)this.boxMode.getValue();
                    byte lllllllllllllllllIlllIlIIllllIII = -1;
                    switch (((String)lllllllllllllllllIlllIlIIllllIIl).hashCode()) {
                        case 64266207: {
                            if (((String)lllllllllllllllllIlllIlIIllllIIl).equals("Black")) {
                                lllllllllllllllllIlllIlIIllllIII = 0;
                                break;
                            }
                            break;
                        }
                        case 83834: {
                            if (((String)lllllllllllllllllIlllIlIIllllIIl).equals("Tag")) {
                                lllllllllllllllllIlllIlIIllllIII = 1;
                                break;
                            }
                            break;
                        }
                        case 558407714: {
                            if (((String)lllllllllllllllllIlllIlIIllllIIl).equals("Outline")) {
                                lllllllllllllllllIlllIlIIllllIII = 2;
                                break;
                            }
                            break;
                        }
                    }
                    switch (lllllllllllllllllIlllIlIIllllIII) {
                        case 0: {
                            Gui.drawRect((int)lllllllllllllllllIlllIlIllIIIIII - 1, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIllIIIIII + (int)lllllllllllllllllIlllIlIlIllllll + 3, (int)lllllllllllllllllIlllIlIlIIIlIlI + (int)Xulu.cFontRenderer.getHeight(), 1427181841);
                            break;
                        }
                        case 1: {
                            Gui.drawRect((int)lllllllllllllllllIlllIlIllIIIIII - 1, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIllIIIIII + (int)lllllllllllllllllIlllIlIlIllllll + 3, (int)lllllllllllllllllIlllIlIlIIIlIlI + (int)Xulu.cFontRenderer.getHeight(), 1427181841);
                            Gui.drawRect((int)lllllllllllllllllIlllIlIllIIIIII - 1, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIllIIIIII + 1, (int)lllllllllllllllllIlllIlIlIIIlIlI + (int)Xulu.cFontRenderer.getHeight(), lllllllllllllllllIlllIlIllIIIlII);
                            break;
                        }
                        case 2: {
                            Gui.drawRect((int)lllllllllllllllllIlllIlIllIIIIII - 1, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIllIIIIII + (int)lllllllllllllllllIlllIlIlIllllll + 3, (int)lllllllllllllllllIlllIlIlIIIlIlI + (int)Xulu.cFontRenderer.getHeight(), 1427181841);
                            XuluTessellator.drawRectOutline((int)lllllllllllllllllIlllIlIllIIIIII - 2, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIllIIIIII + (int)lllllllllllllllllIlllIlIlIllllll + 4, (int)lllllllllllllllllIlllIlIlIIIlIlI + (int)Xulu.cFontRenderer.getHeight(), (int)lllllllllllllllllIlllIlIllIIIIII - 1, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIllIIIIII + (int)lllllllllllllllllIlllIlIlIllllll + 3, (int)lllllllllllllllllIlllIlIlIIIlIlI + (int)Xulu.cFontRenderer.getHeight(), lllllllllllllllllIlllIlIllIIIlII);
                            if (lllllllllllllllllIlllIlIlIllIIll.indexOf(lllllllllllllllllIlllIlIlIllllIl) == 0) {
                                XuluTessellator.drawRectOutline((int)lllllllllllllllllIlllIlIllIIIIII - 2, (int)lllllllllllllllllIlllIlIlIIIlIlI - 2, (int)lllllllllllllllllIlllIlIllIIIIII + (int)lllllllllllllllllIlllIlIlIllllll + 4, (int)lllllllllllllllllIlllIlIlIIIlIlI + (int)Xulu.cFontRenderer.getHeight(), (int)lllllllllllllllllIlllIlIllIIIIII - 2, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIllIIIIII + (int)lllllllllllllllllIlllIlIlIllllll + 4, (int)lllllllllllllllllIlllIlIlIIIlIlI + (int)Xulu.cFontRenderer.getHeight(), lllllllllllllllllIlllIlIllIIIlII);
                                if (lllllllllllllllllIlllIlIlIllIIll.indexOf(lllllllllllllllllIlllIlIlIllllIl) + 1 < lllllllllllllllllIlllIlIlIllIIll.size()) {
                                    final Module lllllllllllllllllIlllIlIllIIlIlI = lllllllllllllllllIlllIlIlIllIIll.get(lllllllllllllllllIlllIlIlIllIIll.indexOf(lllllllllllllllllIlllIlIlIllllIl) + 1);
                                    final String lllllllllllllllllIlllIlIllIIlIIl = String.valueOf(new StringBuilder().append(lllllllllllllllllIlllIlIllIIIIll).append(lllllllllllllllllIlllIlIllIIlIlI.getName()).append((lllllllllllllllllIlllIlIllIIlIlI.getHudInfo() == null) ? "" : String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append("7 [").append(Command.SECTIONSIGN()).append("f").append(lllllllllllllllllIlllIlIllIIlIlI.getHudInfo()).append(Command.SECTIONSIGN()).append("7]").append(ChatFormatting.RESET))).append(lllllllllllllllllIlllIlIllIIIIlI));
                                    final double lllllllllllllllllIlllIlIllIIlIII = Xulu.cFontRenderer.getStringWidth(lllllllllllllllllIlllIlIllIIlIIl);
                                    XuluTessellator.drawRectOutline((int)lllllllllllllllllIlllIlIllIIIIII - 2, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIllIIIIII + (int)lllllllllllllllllIlllIlIlIllllll - lllllllllllllllllIlllIlIllIIlIII - 1.0, (int)lllllllllllllllllIlllIlIlIIIlIlI + (int)Xulu.cFontRenderer.getHeight() + 1, (int)lllllllllllllllllIlllIlIllIIIIII - 2, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIllIIIIII + (int)lllllllllllllllllIlllIlIlIllllll - lllllllllllllllllIlllIlIllIIlIII - 1.0, (int)lllllllllllllllllIlllIlIlIIIlIlI + (int)Xulu.cFontRenderer.getHeight(), lllllllllllllllllIlllIlIllIIIlII);
                                    break;
                                }
                                break;
                            }
                            else {
                                if (lllllllllllllllllIlllIlIlIllIIll.indexOf(lllllllllllllllllIlllIlIlIllllIl) + 1 == lllllllllllllllllIlllIlIlIllIIll.size()) {
                                    XuluTessellator.drawRectOutline((int)lllllllllllllllllIlllIlIllIIIIII - 2, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIllIIIIII + (int)lllllllllllllllllIlllIlIlIllllll + 4, (int)lllllllllllllllllIlllIlIlIIIlIlI + (int)Xulu.cFontRenderer.getHeight() + 1, (int)lllllllllllllllllIlllIlIllIIIIII - 2, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIllIIIIII + (int)lllllllllllllllllIlllIlIlIllllll + 4, (int)lllllllllllllllllIlllIlIlIIIlIlI + (int)Xulu.cFontRenderer.getHeight(), lllllllllllllllllIlllIlIllIIIlII);
                                    break;
                                }
                                final Module lllllllllllllllllIlllIlIllIIIlll = lllllllllllllllllIlllIlIlIllIIll.get(lllllllllllllllllIlllIlIlIllIIll.indexOf(lllllllllllllllllIlllIlIlIllllIl) + 1);
                                final String lllllllllllllllllIlllIlIllIIIllI = String.valueOf(new StringBuilder().append(lllllllllllllllllIlllIlIllIIIIll).append(lllllllllllllllllIlllIlIllIIIlll.getName()).append((lllllllllllllllllIlllIlIllIIIlll.getHudInfo() == null) ? "" : String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append("7 [").append(Command.SECTIONSIGN()).append("f").append(lllllllllllllllllIlllIlIllIIIlll.getHudInfo()).append(Command.SECTIONSIGN()).append("7]").append(ChatFormatting.RESET))).append(lllllllllllllllllIlllIlIllIIIIlI));
                                final double lllllllllllllllllIlllIlIllIIIlIl = Xulu.cFontRenderer.getStringWidth(lllllllllllllllllIlllIlIllIIIllI);
                                XuluTessellator.drawRectOutline((int)lllllllllllllllllIlllIlIllIIIIII - 2, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIllIIIIII + (int)lllllllllllllllllIlllIlIlIllllll - lllllllllllllllllIlllIlIllIIIlIl - 1.0, (int)lllllllllllllllllIlllIlIlIIIlIlI + (int)Xulu.cFontRenderer.getHeight() + 1, (int)lllllllllllllllllIlllIlIllIIIIII - 2, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIllIIIIII + (int)lllllllllllllllllIlllIlIlIllllll - lllllllllllllllllIlllIlIllIIIlIl - 1.0, (int)lllllllllllllllllIlllIlIlIIIlIlI + (int)Xulu.cFontRenderer.getHeight(), lllllllllllllllllIlllIlIllIIIlII);
                                break;
                            }
                            break;
                        }
                    }
                    lllllllllllllllllIlllIlIlIllIIlI = false;
                }
                Xulu.cFontRenderer.drawStringWithShadow((lllllllllllllllllIlllIlIlIllllIl.inAnimation.getValue() != Animation.NONE) ? "" : lllllllllllllllllIlllIlIllIIIIIl, lllllllllllllllllIlllIlIllIIIIII + 1.0, lllllllllllllllllIlllIlIlIIIlIlI, ColorUtils.changeAlpha(lllllllllllllllllIlllIlIllIIIlII, Global.hudAlpha.getValue()), true);
                if (!this.animationMap.containsKey(lllllllllllllllllIlllIlIlIllllIl)) {
                    if (lllllllllllllllllIlllIlIlIllllIl.inAnimation.getValue() != Animation.NONE) {
                        if (this.mode.getValue().equalsIgnoreCase("Right")) {
                            if (lllllllllllllllllIlllIlIlIllllIl.inAnimation.getValue() == Animation.ENABLE) {
                                this.animationMap.put(lllllllllllllllllIlllIlIlIllllIl, new Triplet<Double, Double, Pair<Double, Integer>>(lllllllllllllllllIlllIlIllIIIIII + lllllllllllllllllIlllIlIlIllllll, lllllllllllllllllIlllIlIlIIIlIlI, new Pair<Double, Integer>(lllllllllllllllllIlllIlIllIIIIII, lllllllllllllllllIlllIlIllIIIlII)));
                            }
                            else {
                                this.animationMap.put(lllllllllllllllllIlllIlIlIllllIl, new Triplet<Double, Double, Pair<Double, Integer>>(lllllllllllllllllIlllIlIllIIIIII, lllllllllllllllllIlllIlIlIIIlIlI, new Pair<Double, Integer>(lllllllllllllllllIlllIlIllIIIIII + lllllllllllllllllIlllIlIlIllllll, lllllllllllllllllIlllIlIllIIIlII)));
                            }
                        }
                        else if (this.mode.getValue().equalsIgnoreCase("Left")) {
                            if (lllllllllllllllllIlllIlIlIllllIl.inAnimation.getValue() == Animation.ENABLE) {
                                this.animationMap.put(lllllllllllllllllIlllIlIlIllllIl, new Triplet<Double, Double, Pair<Double, Integer>>(lllllllllllllllllIlllIlIllIIIIII - lllllllllllllllllIlllIlIlIllllll, lllllllllllllllllIlllIlIlIIIlIlI, new Pair<Double, Integer>(lllllllllllllllllIlllIlIllIIIIII, lllllllllllllllllIlllIlIllIIIlII)));
                            }
                            else {
                                this.animationMap.put(lllllllllllllllllIlllIlIlIllllIl, new Triplet<Double, Double, Pair<Double, Integer>>(lllllllllllllllllIlllIlIllIIIIII, lllllllllllllllllIlllIlIlIIIlIlI, new Pair<Double, Integer>(lllllllllllllllllIlllIlIllIIIIII - lllllllllllllllllIlllIlIlIllllll, lllllllllllllllllIlllIlIllIIIlII)));
                            }
                        }
                    }
                }
                else {
                    this.animationMap.get(lllllllllllllllllIlllIlIlIllllIl).getThird().setValue(lllllllllllllllllIlllIlIllIIIlII);
                }
                lllllllllllllllllIlllIlIlIIIlIlI += (this.order.getValue().equalsIgnoreCase("Up") ? 10 : -10);
                final double lllllllllllllllllIlllIlIlIlllllI = this.rspeed.getValue();
                lllllllllllllllllIlllIlIlIllIIIl += (float)(lllllllllllllllllIlllIlIlIlllllI / 100.0);
            }
            for (final Module lllllllllllllllllIlllIlIlIllIlII : this.animationMap.keySet()) {
                final Triplet<Double, Double, Pair<Double, Integer>> lllllllllllllllllIlllIlIlIlllIII = this.animationMap.get(lllllllllllllllllIlllIlIlIllIlII);
                final String s4 = this.prefix.getValue();
                String lllllllllllllllllIlllIlIIllllIll = (String)(-1);
                switch (s4.hashCode()) {
                    case 2433880: {
                        if (s4.equals("None")) {
                            lllllllllllllllllIlllIlIIllllIll = (String)0;
                            break;
                        }
                        break;
                    }
                    case 924274701: {
                        if (s4.equals(">(space)")) {
                            lllllllllllllllllIlllIlIIllllIll = (String)1;
                            break;
                        }
                        break;
                    }
                }
                final String lllllllllllllllllIlllIlIlIllIlll;
                switch (lllllllllllllllllIlllIlIIllllIll) {
                    case 0L: {
                        final String lllllllllllllllllIlllIlIlIllllII = "";
                        break;
                    }
                    case 1L: {
                        final String lllllllllllllllllIlllIlIlIlllIll = "> ";
                        break;
                    }
                    default: {
                        lllllllllllllllllIlllIlIlIllIlll = this.prefix.getValue();
                        break;
                    }
                }
                final String s5 = this.suffix.getValue();
                lllllllllllllllllIlllIlIIllllIll = (String)(-1);
                switch (s5.hashCode()) {
                    case 2433880: {
                        if (s5.equals("None")) {
                            lllllllllllllllllIlllIlIIllllIll = (String)0;
                            break;
                        }
                        break;
                    }
                    case -1019228271: {
                        if (s5.equals("(space)<")) {
                            lllllllllllllllllIlllIlIIllllIll = (String)1;
                            break;
                        }
                        break;
                    }
                }
                final String lllllllllllllllllIlllIlIlIllIllI;
                switch (lllllllllllllllllIlllIlIIllllIll) {
                    case 0L: {
                        final String lllllllllllllllllIlllIlIlIlllIlI = "";
                        break;
                    }
                    case 1L: {
                        final String lllllllllllllllllIlllIlIlIlllIIl = " <";
                        break;
                    }
                    default: {
                        lllllllllllllllllIlllIlIlIllIllI = this.suffix.getValue();
                        break;
                    }
                }
                final String lllllllllllllllllIlllIlIlIllIlIl = String.valueOf(new StringBuilder().append(lllllllllllllllllIlllIlIlIllIlll).append(lllllllllllllllllIlllIlIlIllIlII.getName()).append((lllllllllllllllllIlllIlIlIllIlII.getHudInfo() == null) ? "" : String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append("7 [").append(Command.SECTIONSIGN()).append("f").append(lllllllllllllllllIlllIlIlIllIlII.getHudInfo()).append(Command.SECTIONSIGN()).append("7]").append(ChatFormatting.RESET))).append(lllllllllllllllllIlllIlIlIllIllI));
                Xulu.cFontRenderer.drawStringWithShadow(lllllllllllllllllIlllIlIlIllIlIl, lllllllllllllllllIlllIlIlIlllIII.getFirst(), lllllllllllllllllIlllIlIlIlllIII.getSecond(), ColorUtils.changeAlpha(lllllllllllllllllIlllIlIlIlllIII.getThird().getValue(), Global.hudAlpha.getValue()), true);
                if (!lllllllllllllllllIlllIlIlIlllIII.getFirst().equals(lllllllllllllllllIlllIlIlIlllIII.getThird().getKey())) {
                    if (lllllllllllllllllIlllIlIlIlllIII.getFirst() > lllllllllllllllllIlllIlIlIlllIII.getThird().getKey()) {
                        if (this.mode.getValue().equalsIgnoreCase("Left")) {
                            lllllllllllllllllIlllIlIlIlllIII.setFirst(lllllllllllllllllIlllIlIlIlllIII.getThird().getKey());
                        }
                        lllllllllllllllllIlllIlIlIlllIII.setFirst(lllllllllllllllllIlllIlIlIlllIII.getFirst() - 1.0);
                    }
                    if (lllllllllllllllllIlllIlIlIlllIII.getFirst() >= lllllllllllllllllIlllIlIlIlllIII.getThird().getKey()) {
                        continue;
                    }
                    if (this.mode.getValue().equalsIgnoreCase("Right")) {
                        lllllllllllllllllIlllIlIlIlllIII.setFirst(lllllllllllllllllIlllIlIlIlllIII.getThird().getKey());
                    }
                    lllllllllllllllllIlllIlIlIlllIII.setFirst(lllllllllllllllllIlllIlIlIlllIII.getFirst() + 1.0);
                }
                else {
                    lllllllllllllllllIlllIlIlIllIlII.inAnimation.setEnumValue("NONE");
                    this.removal.add(lllllllllllllllllIlllIlIlIllIlII);
                }
            }
            this.removal.forEach(lllllllllllllllllIlllIlIIlIllIII -> {
                if (lllllllllllllllllIlllIlIIlIllIII.inAnimation.getValue() == Animation.NONE) {
                    this.animationMap.remove(lllllllllllllllllIlllIlIIlIllIII);
                }
                return;
            });
            this.removal.clear();
        }
        else {
            final FontRenderer fontRenderer;
            String value2;
            final StringBuilder sb2;
            final List<Module> lllllllllllllllllIlllIlIlIIIlllI = Xulu.MODULE_MANAGER.getModules().stream().filter(Module::isToggledAnim).filter(Module::isDrawn).sorted(Comparator.comparing(lllllllllllllllllIlllIlIIlIllllI -> {
                fontRenderer = Wrapper.getMinecraft().fontRenderer;
                new StringBuilder().append(lllllllllllllllllIlllIlIIlIllllI.getName());
                if (lllllllllllllllllIlllIlIIlIllllI.getHudInfo() == null) {
                    value2 = "";
                }
                else {
                    value2 = String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append("7 [").append(lllllllllllllllllIlllIlIIlIllllI.getHudInfo()).append("]"));
                }
                return Integer.valueOf(fontRenderer.getStringWidth(String.valueOf(sb2.append(value2))) * -1);
            })).collect((Collector<? super Object, ?, List<Module>>)Collectors.toList());
            if (this.alphab.getValue()) {
                final String[] lllllllllllllllllIlllIlIlIlIllII = lllllllllllllllllIlllIlIlIIIlllI.stream().map((Function<? super Object, ?>)Module::getName).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()).toArray(new String[0]);
                for (int lllllllllllllllllIlllIlIlIlIlIll = lllllllllllllllllIlllIlIlIIIlllI.size(), lllllllllllllllllIlllIlIlIlIlllI = 0; lllllllllllllllllIlllIlIlIlIlllI < lllllllllllllllllIlllIlIlIlIlIll; ++lllllllllllllllllIlllIlIlIlIlllI) {
                    for (int lllllllllllllllllIlllIlIlIlIllll = lllllllllllllllllIlllIlIlIlIlllI + 1; lllllllllllllllllIlllIlIlIlIllll < lllllllllllllllllIlllIlIlIlIlIll; ++lllllllllllllllllIlllIlIlIlIllll) {
                        if (lllllllllllllllllIlllIlIlIlIllII[lllllllllllllllllIlllIlIlIlIlllI].compareTo(lllllllllllllllllIlllIlIlIlIllII[lllllllllllllllllIlllIlIlIlIllll]) > 0) {
                            final String lllllllllllllllllIlllIlIlIllIIII = lllllllllllllllllIlllIlIlIlIllII[lllllllllllllllllIlllIlIlIlIlllI];
                            lllllllllllllllllIlllIlIlIlIllII[lllllllllllllllllIlllIlIlIlIlllI] = lllllllllllllllllIlllIlIlIlIllII[lllllllllllllllllIlllIlIlIlIllll];
                            lllllllllllllllllIlllIlIlIlIllII[lllllllllllllllllIlllIlIlIlIllll] = lllllllllllllllllIlllIlIlIllIIII;
                        }
                    }
                }
                lllllllllllllllllIlllIlIlIIIlllI.clear();
                for (final String lllllllllllllllllIlllIlIlIlIllIl : lllllllllllllllllIlllIlIlIlIllII) {
                    try {
                        lllllllllllllllllIlllIlIlIIIlllI.add(Xulu.MODULE_MANAGER.getModuleByName(lllllllllllllllllIlllIlIlIlIllIl));
                    }
                    catch (Exception ex2) {}
                }
            }
            float lllllllllllllllllIlllIlIlIIIllIl = this.rainbow.hue;
            if (this.order.getValue().equalsIgnoreCase("Down")) {
                lllllllllllllllllIlllIlIlIIIlIlI += 69.0;
            }
            for (final Module lllllllllllllllllIlllIlIlIIllIII : lllllllllllllllllIlllIlIlIIIlllI) {
                int lllllllllllllllllIlllIlIlIlIIIII = Color.HSBtoRGB(lllllllllllllllllIlllIlIlIIIllIl, this.rsaturation.getValue() / 255.0f, this.rlightness.getValue() / 255.0f);
                final String s6 = this.rlist.getValue();
                switch (s6) {
                    case "ClickGui": {
                        lllllllllllllllllIlllIlIlIlIIIII = ColorUtil.getClickGUIColor().getRGB();
                        break;
                    }
                    case "Category": {
                        lllllllllllllllllIlllIlIlIlIIIII = this.getCategoryColor(lllllllllllllllllIlllIlIlIIllIII);
                        break;
                    }
                }
                final String s7 = this.prefix.getValue();
                final String lllllllllllllllllIlllIlIlIIlllll;
                switch (s7) {
                    case "None": {
                        final String lllllllllllllllllIlllIlIlIlIlIlI = "";
                        break;
                    }
                    case ">(space)": {
                        final String lllllllllllllllllIlllIlIlIlIlIIl = "> ";
                        break;
                    }
                    default: {
                        lllllllllllllllllIlllIlIlIIlllll = this.prefix.getValue();
                        break;
                    }
                }
                final String s8 = this.suffix.getValue();
                final String lllllllllllllllllIlllIlIlIIllllI;
                switch (s8) {
                    case "None": {
                        final String lllllllllllllllllIlllIlIlIlIlIII = "";
                        break;
                    }
                    case "(space)<": {
                        final String lllllllllllllllllIlllIlIlIlIIlll = " <";
                        break;
                    }
                    default: {
                        lllllllllllllllllIlllIlIlIIllllI = this.suffix.getValue();
                        break;
                    }
                }
                boolean lllllllllllllllllIlllIlIlIIlllIl = true;
                final String lllllllllllllllllIlllIlIlIIlllII = String.valueOf(new StringBuilder().append(lllllllllllllllllIlllIlIlIIlllll).append(lllllllllllllllllIlllIlIlIIllIII.getName()).append((lllllllllllllllllIlllIlIlIIllIII.getHudInfo() == null) ? "" : String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append("7 [").append(Command.SECTIONSIGN()).append("f").append(lllllllllllllllllIlllIlIlIIllIII.getHudInfo()).append(Command.SECTIONSIGN()).append("7]").append(ChatFormatting.RESET))).append(lllllllllllllllllIlllIlIlIIllllI));
                final double lllllllllllllllllIlllIlIlIIllIll = (int)(this.mode.getValue().equalsIgnoreCase("Right") ? (lllllllllllllllllIlllIlIlIIIlIIl - Wrapper.getMinecraft().fontRenderer.getStringWidth(lllllllllllllllllIlllIlIlIIlllII) + this.getFrame().width - 3.0) : lllllllllllllllllIlllIlIlIIIlIIl);
                final double lllllllllllllllllIlllIlIlIIllIlI = Wrapper.getMinecraft().fontRenderer.getStringWidth(lllllllllllllllllIlllIlIlIIlllII);
                if (this.box.getValue()) {
                    final byte lllllllllllllllllIlllIlIIllllIIl = (byte)this.boxMode.getValue();
                    byte lllllllllllllllllIlllIlIIllllIII = -1;
                    switch (((String)lllllllllllllllllIlllIlIIllllIIl).hashCode()) {
                        case 64266207: {
                            if (((String)lllllllllllllllllIlllIlIIllllIIl).equals("Black")) {
                                lllllllllllllllllIlllIlIIllllIII = 0;
                                break;
                            }
                            break;
                        }
                        case 83834: {
                            if (((String)lllllllllllllllllIlllIlIIllllIIl).equals("Tag")) {
                                lllllllllllllllllIlllIlIIllllIII = 1;
                                break;
                            }
                            break;
                        }
                        case 558407714: {
                            if (((String)lllllllllllllllllIlllIlIIllllIIl).equals("Outline")) {
                                lllllllllllllllllIlllIlIIllllIII = 2;
                                break;
                            }
                            break;
                        }
                    }
                    switch (lllllllllllllllllIlllIlIIllllIII) {
                        case 0: {
                            Gui.drawRect((int)lllllllllllllllllIlllIlIlIIllIll + 2, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIlIIllIll + (int)lllllllllllllllllIlllIlIlIIllIlI + 3, (int)lllllllllllllllllIlllIlIlIIIlIlI + Wrapper.getMinecraft().fontRenderer.FONT_HEIGHT, 1427181841);
                            break;
                        }
                        case 1: {
                            Gui.drawRect((int)lllllllllllllllllIlllIlIlIIllIll - 2, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIlIIllIll + (int)lllllllllllllllllIlllIlIlIIllIlI + 3, (int)lllllllllllllllllIlllIlIlIIIlIlI + Wrapper.getMinecraft().fontRenderer.FONT_HEIGHT, 1427181841);
                            Gui.drawRect((int)lllllllllllllllllIlllIlIlIIllIll - 2, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIlIIllIll + 1, (int)lllllllllllllllllIlllIlIlIIIlIlI + Wrapper.getMinecraft().fontRenderer.FONT_HEIGHT, lllllllllllllllllIlllIlIlIlIIIII);
                            break;
                        }
                        case 2: {
                            Gui.drawRect((int)lllllllllllllllllIlllIlIlIIllIll + 2, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIlIIllIll + (int)lllllllllllllllllIlllIlIlIIllIlI + 3, (int)lllllllllllllllllIlllIlIlIIIlIlI + Wrapper.getMinecraft().fontRenderer.FONT_HEIGHT, 1427181841);
                            XuluTessellator.drawRectOutline((int)lllllllllllllllllIlllIlIlIIllIll + 1, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIlIIllIll + (int)lllllllllllllllllIlllIlIlIIllIlI + 4, (int)lllllllllllllllllIlllIlIlIIIlIlI + Wrapper.getMinecraft().fontRenderer.FONT_HEIGHT, (int)lllllllllllllllllIlllIlIlIIllIll + 2, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIlIIllIll + (int)lllllllllllllllllIlllIlIlIIllIlI + 3, (int)lllllllllllllllllIlllIlIlIIIlIlI + Wrapper.getMinecraft().fontRenderer.FONT_HEIGHT, lllllllllllllllllIlllIlIlIlIIIII);
                            if (lllllllllllllllllIlllIlIlIIIlllI.indexOf(lllllllllllllllllIlllIlIlIIllIII) == 0) {
                                XuluTessellator.drawRectOutline((int)lllllllllllllllllIlllIlIlIIllIll + 1, (int)lllllllllllllllllIlllIlIlIIIlIlI - 2, (int)lllllllllllllllllIlllIlIlIIllIll + (int)lllllllllllllllllIlllIlIlIIllIlI + 4, (int)lllllllllllllllllIlllIlIlIIIlIlI + Wrapper.getMinecraft().fontRenderer.FONT_HEIGHT, (int)lllllllllllllllllIlllIlIlIIllIll + 1, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIlIIllIll + (int)lllllllllllllllllIlllIlIlIIllIlI + 4, (int)lllllllllllllllllIlllIlIlIIIlIlI + Wrapper.getMinecraft().fontRenderer.FONT_HEIGHT, lllllllllllllllllIlllIlIlIlIIIII);
                                if (lllllllllllllllllIlllIlIlIIIlllI.indexOf(lllllllllllllllllIlllIlIlIIllIII) + 1 < lllllllllllllllllIlllIlIlIIIlllI.size()) {
                                    final Module lllllllllllllllllIlllIlIlIlIIllI = lllllllllllllllllIlllIlIlIIIlllI.get(lllllllllllllllllIlllIlIlIIIlllI.indexOf(lllllllllllllllllIlllIlIlIIllIII) + 1);
                                    final String lllllllllllllllllIlllIlIlIlIIlIl = String.valueOf(new StringBuilder().append(lllllllllllllllllIlllIlIlIIlllll).append(lllllllllllllllllIlllIlIlIlIIllI.getName()).append((lllllllllllllllllIlllIlIlIlIIllI.getHudInfo() == null) ? "" : String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append("7 [").append(Command.SECTIONSIGN()).append("f").append(lllllllllllllllllIlllIlIlIlIIllI.getHudInfo()).append(Command.SECTIONSIGN()).append("7]").append(ChatFormatting.RESET))).append(lllllllllllllllllIlllIlIlIIllllI));
                                    final double lllllllllllllllllIlllIlIlIlIIlII = Wrapper.getMinecraft().fontRenderer.getStringWidth(lllllllllllllllllIlllIlIlIlIIlIl);
                                    XuluTessellator.drawRectOutline((int)lllllllllllllllllIlllIlIlIIllIll + 1, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIlIIllIll + (int)lllllllllllllllllIlllIlIlIIllIlI - lllllllllllllllllIlllIlIlIlIIlII + 2.0, (int)lllllllllllllllllIlllIlIlIIIlIlI + Wrapper.getMinecraft().fontRenderer.FONT_HEIGHT + 1, (int)lllllllllllllllllIlllIlIlIIllIll + 1, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIlIIllIll + (int)lllllllllllllllllIlllIlIlIIllIlI - lllllllllllllllllIlllIlIlIlIIlII + 2.0, (int)lllllllllllllllllIlllIlIlIIIlIlI + Wrapper.getMinecraft().fontRenderer.FONT_HEIGHT, lllllllllllllllllIlllIlIlIlIIIII);
                                    break;
                                }
                                break;
                            }
                            else {
                                if (lllllllllllllllllIlllIlIlIIIlllI.indexOf(lllllllllllllllllIlllIlIlIIllIII) + 1 == lllllllllllllllllIlllIlIlIIIlllI.size()) {
                                    XuluTessellator.drawRectOutline((int)lllllllllllllllllIlllIlIlIIllIll + 1, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIlIIllIll + (int)lllllllllllllllllIlllIlIlIIllIlI + 4, (int)lllllllllllllllllIlllIlIlIIIlIlI + Wrapper.getMinecraft().fontRenderer.FONT_HEIGHT + 1, (int)lllllllllllllllllIlllIlIlIIllIll + 1, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIlIIllIll + (int)lllllllllllllllllIlllIlIlIIllIlI + 4, (int)lllllllllllllllllIlllIlIlIIIlIlI + Wrapper.getMinecraft().fontRenderer.FONT_HEIGHT, lllllllllllllllllIlllIlIlIlIIIII);
                                    break;
                                }
                                final Module lllllllllllllllllIlllIlIlIlIIIll = lllllllllllllllllIlllIlIlIIIlllI.get(lllllllllllllllllIlllIlIlIIIlllI.indexOf(lllllllllllllllllIlllIlIlIIllIII) + 1);
                                final String lllllllllllllllllIlllIlIlIlIIIlI = String.valueOf(new StringBuilder().append(lllllllllllllllllIlllIlIlIIlllll).append(lllllllllllllllllIlllIlIlIlIIIll.getName()).append((lllllllllllllllllIlllIlIlIlIIIll.getHudInfo() == null) ? "" : String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append("7 [").append(Command.SECTIONSIGN()).append("f").append(lllllllllllllllllIlllIlIlIlIIIll.getHudInfo()).append(Command.SECTIONSIGN()).append("7]").append(ChatFormatting.RESET))).append(lllllllllllllllllIlllIlIlIIllllI));
                                final double lllllllllllllllllIlllIlIlIlIIIIl = Wrapper.getMinecraft().fontRenderer.getStringWidth(lllllllllllllllllIlllIlIlIlIIIlI);
                                XuluTessellator.drawRectOutline((int)lllllllllllllllllIlllIlIlIIllIll + 1, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIlIIllIll + (int)lllllllllllllllllIlllIlIlIIllIlI - lllllllllllllllllIlllIlIlIlIIIIl + 2.0, (int)lllllllllllllllllIlllIlIlIIIlIlI + Wrapper.getMinecraft().fontRenderer.FONT_HEIGHT + 1, (int)lllllllllllllllllIlllIlIlIIllIll + 1, (int)lllllllllllllllllIlllIlIlIIIlIlI - 1, (int)lllllllllllllllllIlllIlIlIIllIll + (int)lllllllllllllllllIlllIlIlIIllIlI - lllllllllllllllllIlllIlIlIlIIIIl + 2.0, (int)lllllllllllllllllIlllIlIlIIIlIlI + Wrapper.getMinecraft().fontRenderer.FONT_HEIGHT, lllllllllllllllllIlllIlIlIlIIIII);
                                break;
                            }
                            break;
                        }
                    }
                    lllllllllllllllllIlllIlIlIIlllIl = false;
                }
                Wrapper.getMinecraft().fontRenderer.drawStringWithShadow((lllllllllllllllllIlllIlIlIIllIII.inAnimation.getValue() != Animation.NONE) ? "" : lllllllllllllllllIlllIlIlIIlllII, (float)(int)(this.mode.getValue().equalsIgnoreCase("Right") ? (lllllllllllllllllIlllIlIlIIIlIIl - Wrapper.getMinecraft().fontRenderer.getStringWidth(lllllllllllllllllIlllIlIlIIlllII) + this.getFrame().width) : lllllllllllllllllIlllIlIlIIIlIIl), (float)(int)lllllllllllllllllIlllIlIlIIIlIlI, ColorUtils.changeAlpha(lllllllllllllllllIlllIlIlIlIIIII, Global.hudAlpha.getValue()));
                if (!this.animationMap.containsKey(lllllllllllllllllIlllIlIlIIllIII)) {
                    if (lllllllllllllllllIlllIlIlIIllIII.inAnimation.getValue() != Animation.NONE) {
                        if (this.mode.getValue().equalsIgnoreCase("Right")) {
                            if (lllllllllllllllllIlllIlIlIIllIII.inAnimation.getValue() == Animation.ENABLE) {
                                this.animationMap.put(lllllllllllllllllIlllIlIlIIllIII, new Triplet<Double, Double, Pair<Double, Integer>>(lllllllllllllllllIlllIlIlIIllIll + lllllllllllllllllIlllIlIlIIllIlI, lllllllllllllllllIlllIlIlIIIlIlI, new Pair<Double, Integer>(lllllllllllllllllIlllIlIlIIllIll, lllllllllllllllllIlllIlIlIlIIIII)));
                            }
                            else {
                                this.animationMap.put(lllllllllllllllllIlllIlIlIIllIII, new Triplet<Double, Double, Pair<Double, Integer>>(lllllllllllllllllIlllIlIlIIllIll, lllllllllllllllllIlllIlIlIIIlIlI, new Pair<Double, Integer>(lllllllllllllllllIlllIlIlIIllIll + lllllllllllllllllIlllIlIlIIllIlI, lllllllllllllllllIlllIlIlIlIIIII)));
                            }
                        }
                        else if (this.mode.getValue().equalsIgnoreCase("Left")) {
                            if (lllllllllllllllllIlllIlIlIIllIII.inAnimation.getValue() == Animation.ENABLE) {
                                this.animationMap.put(lllllllllllllllllIlllIlIlIIllIII, new Triplet<Double, Double, Pair<Double, Integer>>(lllllllllllllllllIlllIlIlIIllIll - lllllllllllllllllIlllIlIlIIllIlI, lllllllllllllllllIlllIlIlIIIlIlI, new Pair<Double, Integer>(lllllllllllllllllIlllIlIlIIllIll, lllllllllllllllllIlllIlIlIlIIIII)));
                            }
                            else {
                                this.animationMap.put(lllllllllllllllllIlllIlIlIIllIII, new Triplet<Double, Double, Pair<Double, Integer>>(lllllllllllllllllIlllIlIlIIllIll, lllllllllllllllllIlllIlIlIIIlIlI, new Pair<Double, Integer>(lllllllllllllllllIlllIlIlIIllIll - lllllllllllllllllIlllIlIlIIllIlI, lllllllllllllllllIlllIlIlIlIIIII)));
                            }
                        }
                    }
                }
                else {
                    this.animationMap.get(lllllllllllllllllIlllIlIlIIllIII).getThird().setValue(lllllllllllllllllIlllIlIlIlIIIII);
                }
                lllllllllllllllllIlllIlIlIIIlIlI += (this.order.getValue().equalsIgnoreCase("Up") ? 10 : -10);
                final double lllllllllllllllllIlllIlIlIIllIIl = this.rspeed.getValue();
                lllllllllllllllllIlllIlIlIIIllIl += (float)(lllllllllllllllllIlllIlIlIIllIIl / 100.0);
            }
            for (final Module lllllllllllllllllIlllIlIlIIIllll : this.animationMap.keySet()) {
                final Triplet<Double, Double, Pair<Double, Integer>> lllllllllllllllllIlllIlIlIIlIIll = this.animationMap.get(lllllllllllllllllIlllIlIlIIIllll);
                final String s9 = this.prefix.getValue();
                final String lllllllllllllllllIlllIlIlIIlIIlI;
                switch (s9) {
                    case "None": {
                        final String lllllllllllllllllIlllIlIlIIlIlll = "";
                        break;
                    }
                    case ">(space)": {
                        final String lllllllllllllllllIlllIlIlIIlIllI = "> ";
                        break;
                    }
                    default: {
                        lllllllllllllllllIlllIlIlIIlIIlI = this.prefix.getValue();
                        break;
                    }
                }
                final String s10 = this.suffix.getValue();
                final String lllllllllllllllllIlllIlIlIIlIIIl;
                switch (s10) {
                    case "None": {
                        final String lllllllllllllllllIlllIlIlIIlIlIl = "";
                        break;
                    }
                    case "(space)<": {
                        final String lllllllllllllllllIlllIlIlIIlIlII = " <";
                        break;
                    }
                    default: {
                        lllllllllllllllllIlllIlIlIIlIIIl = this.suffix.getValue();
                        break;
                    }
                }
                final String lllllllllllllllllIlllIlIlIIlIIII = String.valueOf(new StringBuilder().append(lllllllllllllllllIlllIlIlIIlIIlI).append(lllllllllllllllllIlllIlIlIIIllll.getName()).append((lllllllllllllllllIlllIlIlIIIllll.getHudInfo() == null) ? "" : String.valueOf(new StringBuilder().append(Command.SECTIONSIGN()).append("7 [").append(Command.SECTIONSIGN()).append("f").append(lllllllllllllllllIlllIlIlIIIllll.getHudInfo()).append(Command.SECTIONSIGN()).append("7]").append(ChatFormatting.RESET))).append(lllllllllllllllllIlllIlIlIIlIIIl));
                Wrapper.getMinecraft().fontRenderer.drawStringWithShadow(lllllllllllllllllIlllIlIlIIlIIII, lllllllllllllllllIlllIlIlIIlIIll.getFirst().floatValue(), lllllllllllllllllIlllIlIlIIlIIll.getSecond().floatValue(), ColorUtils.changeAlpha(lllllllllllllllllIlllIlIlIIlIIll.getThird().getValue(), Global.hudAlpha.getValue()));
                if (!lllllllllllllllllIlllIlIlIIlIIll.getFirst().equals(lllllllllllllllllIlllIlIlIIlIIll.getThird().getKey())) {
                    if (lllllllllllllllllIlllIlIlIIlIIll.getFirst() > lllllllllllllllllIlllIlIlIIlIIll.getThird().getKey()) {
                        if (this.mode.getValue().equalsIgnoreCase("Left")) {
                            lllllllllllllllllIlllIlIlIIlIIll.setFirst(lllllllllllllllllIlllIlIlIIlIIll.getThird().getKey());
                        }
                        lllllllllllllllllIlllIlIlIIlIIll.setFirst(lllllllllllllllllIlllIlIlIIlIIll.getFirst() - 1.0);
                    }
                    if (lllllllllllllllllIlllIlIlIIlIIll.getFirst() >= lllllllllllllllllIlllIlIlIIlIIll.getThird().getKey()) {
                        continue;
                    }
                    if (this.mode.getValue().equalsIgnoreCase("Right")) {
                        lllllllllllllllllIlllIlIlIIlIIll.setFirst(lllllllllllllllllIlllIlIlIIlIIll.getThird().getKey());
                    }
                    lllllllllllllllllIlllIlIlIIlIIll.setFirst(lllllllllllllllllIlllIlIlIIlIIll.getFirst() + 1.0);
                }
                else {
                    lllllllllllllllllIlllIlIlIIIllll.inAnimation.setEnumValue("NONE");
                    this.removal.add(lllllllllllllllllIlllIlIlIIIllll);
                }
            }
            this.removal.forEach(lllllllllllllllllIlllIlIIllIIIll -> {
                if (lllllllllllllllllIlllIlIIllIIIll.inAnimation.getValue() == Animation.NONE) {
                    this.animationMap.remove(lllllllllllllllllIlllIlIIllIIIll);
                }
                return;
            });
            this.removal.clear();
        }
    }
    
    public String getTitle(String lllllllllllllllllIlllIlIIlllIIIl) {
        lllllllllllllllllIlllIlIIlllIIIl = String.valueOf(new StringBuilder().append(Character.toUpperCase(lllllllllllllllllIlllIlIIlllIIIl.toLowerCase().charAt(0))).append(lllllllllllllllllIlllIlIIlllIIIl.toLowerCase().substring(1)));
        return lllllllllllllllllIlllIlIIlllIIIl;
    }
    
    public class Rainbow
    {
        public /* synthetic */ int rgb;
        public /* synthetic */ int g;
        public /* synthetic */ int a;
        public /* synthetic */ int b;
        /* synthetic */ float hue;
        public /* synthetic */ int r;
        
        public Rainbow() {
            this.hue = 0.01f;
        }
        
        public void updateRainbow() {
            this.rgb = Color.HSBtoRGB(this.hue, Xulu.MODULE_MANAGER.getModuleT(FeatureList.class).rsaturation.getValue() / 255.0f, Xulu.MODULE_MANAGER.getModuleT(FeatureList.class).rlightness.getValue() / 255.0f);
            this.a = (this.rgb >>> 24 & 0xFF);
            this.r = (this.rgb >>> 16 & 0xFF);
            this.g = (this.rgb >>> 8 & 0xFF);
            this.b = (this.rgb & 0xFF);
            this.hue += Xulu.MODULE_MANAGER.getModuleT(FeatureList.class).rainbowspeed.getValue() / 10000.0f;
            if (this.hue > 1.0f) {
                --this.hue;
            }
        }
    }
}
