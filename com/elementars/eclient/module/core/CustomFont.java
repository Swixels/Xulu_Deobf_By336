package com.elementars.eclient.module.core;

import com.elementars.eclient.*;
import dev.xulu.settings.*;
import java.awt.*;
import com.elementars.eclient.font.*;
import com.elementars.eclient.module.*;
import java.util.*;

public class CustomFont extends Module
{
    public static /* synthetic */ Value<String> customFontMode;
    public static /* synthetic */ Value<Boolean> metrics;
    public static /* synthetic */ Value<Integer> FONT_STYLE;
    public static /* synthetic */ Value<Integer> fontOffset;
    public static /* synthetic */ Value<Boolean> shadow;
    public static /* synthetic */ Value<TextBox> FONT;
    public static /* synthetic */ Value<Boolean> antiAlias;
    public static /* synthetic */ Value<Integer> FONT_SIZE;
    
    public static void updateFont(final String lllllllllllllllllIIIlIlIlllIIIIl, final int lllllllllllllllllIIIlIlIllIllIll, final int lllllllllllllllllIIIlIlIllIllIlI, final boolean lllllllllllllllllIIIlIlIllIllIIl, final boolean lllllllllllllllllIIIlIlIllIlllIl) {
        final short lllllllllllllllllIIIlIlIllIlIlll = (short)CustomFont.customFontMode.getValue();
        Exception lllllllllllllllllIIIlIlIllIlIllI = (Exception)(-1);
        switch (((String)lllllllllllllllllIIIlIlIllIlIlll).hashCode()) {
            case -1955878649: {
                if (((String)lllllllllllllllllIIIlIlIllIlIlll).equals("Normal")) {
                    lllllllllllllllllIIIlIlIllIlIllI = (Exception)0;
                    break;
                }
                break;
            }
            case 84359069: {
                if (((String)lllllllllllllllllIIIlIlIllIlIlll).equals("Xdolf")) {
                    lllllllllllllllllIIIlIlIllIlIllI = (Exception)1;
                    break;
                }
                break;
            }
        }
        switch (lllllllllllllllllIIIlIlIllIlIllI) {
            case 0L: {
                try {
                    if (lllllllllllllllllIIIlIlIlllIIIIl.equalsIgnoreCase("Comfortaa Regular")) {
                        CFontManager.customFont = new com.elementars.eclient.font.custom.CustomFont(new Font("Comfortaa Regular", lllllllllllllllllIIIlIlIllIllIll, lllllllllllllllllIIIlIlIllIllIlI), lllllllllllllllllIIIlIlIllIllIIl, lllllllllllllllllIIIlIlIllIlllIl);
                        return;
                    }
                    CFontManager.customFont = new com.elementars.eclient.font.custom.CustomFont(new Font(Xulu.getCorrectFont(lllllllllllllllllIIIlIlIlllIIIIl), lllllllllllllllllIIIlIlIllIllIll, lllllllllllllllllIIIlIlIllIllIlI), lllllllllllllllllIIIlIlIllIllIIl, lllllllllllllllllIIIlIlIllIlllIl);
                }
                catch (Exception lllllllllllllllllIIIlIlIlllIIIll) {
                    lllllllllllllllllIIIlIlIlllIIIll.printStackTrace();
                }
                break;
            }
            case 1L: {
                try {
                    if (lllllllllllllllllIIIlIlIlllIIIIl.equalsIgnoreCase("Comfortaa Regular")) {
                        CFontManager.xFontRenderer = new XFontRenderer(new Font("Comfortaa Regular", lllllllllllllllllIIIlIlIllIllIll, lllllllllllllllllIIIlIlIllIllIlI * 2), lllllllllllllllllIIIlIlIllIllIIl, 8);
                        return;
                    }
                    CFontManager.xFontRenderer = new XFontRenderer(new Font(Xulu.getCorrectFont(lllllllllllllllllIIIlIlIlllIIIIl), lllllllllllllllllIIIlIlIllIllIll, lllllllllllllllllIIIlIlIllIllIlI * 2), lllllllllllllllllIIIlIlIllIllIIl, 8);
                }
                catch (Exception lllllllllllllllllIIIlIlIlllIIIlI) {
                    lllllllllllllllllIIIlIlIlllIIIlI.printStackTrace();
                }
                break;
            }
        }
    }
    
    public CustomFont() {
        super("CustomFont", "Custom in game text rendering", 0, Category.CORE, false);
        CustomFont.customFontMode = this.register(new Value<String>("Mode", this, "Normal", new ArrayList<String>(Arrays.asList("Normal", "Xdolf", "Rainbow"))));
        CustomFont.FONT = this.register(new Value<TextBox>("Font", this, new TextBox("Verdana"))).newValueFilter(lllllllllllllllllIIIlIlIlIllllII -> Xulu.getCorrectFont(lllllllllllllllllIIIlIlIlIllllII.getText()) != null).withFilterError("Not a font! (Case-sensitive)").onChanged(lllllllllllllllllIIIlIlIlIllllll -> updateFont(lllllllllllllllllIIIlIlIlIllllll.getNew().getText(), CustomFont.FONT_STYLE.getValue(), CustomFont.FONT_SIZE.getValue(), CustomFont.antiAlias.getValue(), CustomFont.metrics.getValue())).visibleWhen(lllllllllllllllllIIIlIlIllIIIIlI -> !CustomFont.customFontMode.getValue().equalsIgnoreCase("Rainbow"));
        CustomFont.FONT_STYLE = this.register(new Value<Integer>("Font Style", this, 0, 0, 2)).onChanged(lllllllllllllllllIIIlIlIllIIIlII -> updateFont(CustomFont.FONT.getValue().getText(), lllllllllllllllllIIIlIlIllIIIlII.getNew(), CustomFont.FONT_SIZE.getValue(), CustomFont.antiAlias.getValue(), CustomFont.metrics.getValue())).visibleWhen(lllllllllllllllllIIIlIlIllIIIllI -> !CustomFont.customFontMode.getValue().equalsIgnoreCase("Rainbow"));
        CustomFont.FONT_SIZE = this.register(new Value<Integer>("Font Size", this, 18, 5, 50)).onChanged(lllllllllllllllllIIIlIlIllIIIlll -> updateFont(CustomFont.FONT.getValue().getText(), CustomFont.FONT_STYLE.getValue(), lllllllllllllllllIIIlIlIllIIIlll.getNew(), CustomFont.antiAlias.getValue(), CustomFont.metrics.getValue())).visibleWhen(lllllllllllllllllIIIlIlIllIIlIlI -> !CustomFont.customFontMode.getValue().equalsIgnoreCase("Rainbow"));
        CustomFont.antiAlias = this.register(new Value<Boolean>("Anti Alias", this, true)).visibleWhen(lllllllllllllllllIIIlIlIllIIlIll -> CustomFont.customFontMode.getValue().equalsIgnoreCase("Normal") || CustomFont.customFontMode.getValue().equalsIgnoreCase("Xdolf")).onChanged(lllllllllllllllllIIIlIlIllIIllII -> updateFont(CustomFont.FONT.getValue().getText(), CustomFont.FONT_STYLE.getValue(), CustomFont.FONT_SIZE.getValue(), lllllllllllllllllIIIlIlIllIIllII.getNew(), CustomFont.metrics.getValue()));
        CustomFont.metrics = this.register(new Value<Boolean>("Metrics", this, true)).visibleWhen(lllllllllllllllllIIIlIlIllIIllll -> CustomFont.customFontMode.getValue().equalsIgnoreCase("Normal")).onChanged(lllllllllllllllllIIIlIlIllIlIIII -> updateFont(CustomFont.FONT.getValue().getText(), CustomFont.FONT_STYLE.getValue(), CustomFont.FONT_SIZE.getValue(), CustomFont.antiAlias.getValue(), lllllllllllllllllIIIlIlIllIlIIII.getNew()));
        CustomFont.shadow = this.register(new Value<Boolean>("Shadow", this, true)).visibleWhen(lllllllllllllllllIIIlIlIllIlIIll -> CustomFont.customFontMode.getValue().equalsIgnoreCase("Normal"));
        CustomFont.fontOffset = this.register(new Value<Integer>("Font Offset", this, 0, -5, 5)).visibleWhen(lllllllllllllllllIIIlIlIllIlIlII -> !CustomFont.customFontMode.getValue().equalsIgnoreCase("Rainbow"));
    }
}
