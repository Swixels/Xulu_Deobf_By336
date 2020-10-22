package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import dev.xulu.settings.*;
import com.elementars.eclient.module.*;
import dev.xulu.newgui.util.*;
import com.elementars.eclient.*;
import net.minecraft.client.gui.*;
import com.elementars.eclient.module.core.*;
import com.elementars.eclient.util.*;
import java.text.*;
import java.util.*;
import java.time.temporal.*;
import java.time.*;

public class Welcome extends Element
{
    private final /* synthetic */ Value<Boolean> center;
    public static /* synthetic */ String text;
    private final /* synthetic */ Value<Boolean> dynamic;
    private final /* synthetic */ Value<Boolean> holiday;
    private final /* synthetic */ Value<Boolean> rainbow;
    
    @Override
    public void onMiddleClick() {
        WelcomeNotes.initTextBox();
    }
    
    public Welcome() {
        super("Welcome");
        this.rainbow = this.register(new Value<Boolean>("Rainbow", this, false));
        this.dynamic = this.register(new Value<Boolean>("Dynamic", this, false));
        this.holiday = this.register(new Value<Boolean>("Holiday", this, true));
        this.center = this.register(new Value<Boolean>("Center", this, false));
    }
    
    static {
        Welcome.text = "Welcome NAME";
    }
    
    @Override
    public void onRender() {
        if (Welcome.mc.player == null) {
            return;
        }
        int llllllllllllllllIlllllIIlIlllllI = ColorUtil.getClickGUIColor().getRGB();
        if (this.rainbow.getValue()) {
            llllllllllllllllIlllllIIlIlllllI = Xulu.rgb;
        }
        String llllllllllllllllIlllllIIlIllllIl = Welcome.text.replaceAll("NAME", Welcome.mc.player.getName());
        if (this.dynamic.getValue()) {
            llllllllllllllllIlllllIIlIllllIl = this.getTimeMessage().replaceAll("NAME", Welcome.mc.player.getName());
        }
        if (this.holiday.getValue() && this.getHoliday() != null) {
            llllllllllllllllIlllllIIlIllllIl = this.getHoliday().replaceAll("NAME", Welcome.mc.player.getName());
        }
        this.width = Welcome.fontRenderer.getStringWidth(llllllllllllllllIlllllIIlIllllIl) + 2;
        this.height = Welcome.fontRenderer.FONT_HEIGHT + 2;
        final String llllllllllllllllIlllllIIlIllllII = llllllllllllllllIlllllIIlIllllIl.replaceAll("&", String.valueOf('§'));
        if (this.center.getValue()) {
            final ScaledResolution llllllllllllllllIlllllIIllIIIIII = new ScaledResolution(Welcome.mc);
            if (Xulu.CustomFont) {
                Xulu.cFontRenderer.drawStringWithShadow(llllllllllllllllIlllllIIlIllllII, llllllllllllllllIlllllIIllIIIIII.getScaledWidth() / 2.0f - Xulu.cFontRenderer.getStringWidth(llllllllllllllllIlllllIIlIllllII) / 2.0f + 1.0f, this.y + 1.0, ColorUtils.changeAlpha(llllllllllllllllIlllllIIlIlllllI, Global.hudAlpha.getValue()));
            }
            else {
                Wrapper.getMinecraft().fontRenderer.drawStringWithShadow(llllllllllllllllIlllllIIlIllllII, llllllllllllllllIlllllIIllIIIIII.getScaledWidth() / 2.0f - Xulu.cFontRenderer.getStringWidth(llllllllllllllllIlllllIIlIllllII) / 2.0f + 1.0f, (float)this.y + 1.0f, ColorUtils.changeAlpha(llllllllllllllllIlllllIIlIlllllI, Global.hudAlpha.getValue()));
            }
        }
        else if (Xulu.CustomFont) {
            Xulu.cFontRenderer.drawStringWithShadow(llllllllllllllllIlllllIIlIllllII, this.x + 1.0, this.y + 1.0, ColorUtils.changeAlpha(llllllllllllllllIlllllIIlIlllllI, Global.hudAlpha.getValue()));
        }
        else {
            Wrapper.getMinecraft().fontRenderer.drawStringWithShadow(llllllllllllllllIlllllIIlIllllII, (float)this.x + 1.0f, (float)this.y + 1.0f, ColorUtils.changeAlpha(llllllllllllllllIlllllIIlIlllllI, Global.hudAlpha.getValue()));
        }
    }
    
    private String getHoliday() {
        final int llllllllllllllllIlllllIIlIlIIllI = Integer.valueOf(new SimpleDateFormat("MM").format(new Date()));
        final int llllllllllllllllIlllllIIlIlIIlIl = Integer.valueOf(new SimpleDateFormat("dd").format(new Date()));
        switch (llllllllllllllllIlllllIIlIlIIllI) {
            case 1: {
                if (llllllllllllllllIlllllIIlIlIIlIl == 1) {
                    return "Happy New Years NAME!";
                }
                break;
            }
            case 2: {
                if (llllllllllllllllIlllllIIlIlIIlIl == 14) {
                    return "Happy Valentines Day NAME!";
                }
                break;
            }
            case 10: {
                if (llllllllllllllllIlllllIIlIlIIlIl == 31) {
                    return "Happy Halloween NAME! (spooky)";
                }
                break;
            }
            case 11: {
                final LocalDate llllllllllllllllIlllllIIlIlIlIII = Year.of(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date()))).atMonth(Month.NOVEMBER).atDay(1).with(TemporalAdjusters.lastInMonth(DayOfWeek.WEDNESDAY));
                if (llllllllllllllllIlllllIIlIlIlIII.getDayOfMonth() == llllllllllllllllIlllllIIlIlIIlIl) {
                    return "Happy Thanksgiving NAME!";
                }
            }
            case 12: {
                if (llllllllllllllllIlllllIIlIlIIlIl == 25) {
                    return "Happy X-mas NAME!";
                }
                break;
            }
        }
        return null;
    }
    
    public static void handleWelcome(final String llllllllllllllllIlllllIIlIllIlIl) {
        Welcome.text = llllllllllllllllIlllllIIlIllIlIl;
    }
    
    private String getTimeMessage() {
        final String llllllllllllllllIlllllIIlIlIllll = new SimpleDateFormat("k").format(new Date());
        final int llllllllllllllllIlllllIIlIlIlllI = Integer.valueOf(llllllllllllllllIlllllIIlIlIllll);
        if (llllllllllllllllIlllllIIlIlIlllI < 6) {
            return "Good Night NAME";
        }
        if (llllllllllllllllIlllllIIlIlIlllI < 12) {
            return "Good Morning NAME";
        }
        if (llllllllllllllllIlllllIIlIlIlllI < 17) {
            return "Good Afternoon NAME";
        }
        if (llllllllllllllllIlllllIIlIlIlllI < 20) {
            return "Good Evening NAME";
        }
        return "Good Night NAME";
    }
}
