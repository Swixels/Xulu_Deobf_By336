package com.elementars.eclient.guirewrite.elements;

import com.elementars.eclient.guirewrite.*;
import net.minecraft.util.*;
import java.awt.image.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;
import javax.imageio.*;
import net.minecraft.client.renderer.texture.*;
import java.io.*;
import com.elementars.eclient.module.render.*;

public class Logo extends Element
{
    private /* synthetic */ ResourceLocation logo;
    
    public Logo() {
        super("Logo");
        this.onLoad();
    }
    
    private <T> BufferedImage getImage(final T lllllllllllllllllIllIIlllIllIIll, final ThrowingFunction<T, BufferedImage> lllllllllllllllllIllIIlllIllIIlI) {
        try {
            return lllllllllllllllllIllIIlllIllIIlI.apply(lllllllllllllllllIllIIlllIllIIll);
        }
        catch (IOException lllllllllllllllllIllIIlllIllIlIl) {
            lllllllllllllllllIllIIlllIllIlIl.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void onRender() {
        Logo.mc.renderEngine.bindTexture(this.logo);
        GlStateManager.color(255.0f, 255.0f, 255.0f);
        Gui.drawScaledCustomSizeModalRect((int)this.x + 4, (int)this.y + 4, 7.0f, 7.0f, (int)this.width - 7, (int)this.height - 7, (int)this.width, (int)this.height, (float)this.width, (float)this.height);
    }
    
    @Override
    public void onEnable() {
        this.width = 32.0;
        this.height = 32.0;
    }
    
    private void onLoad() {
        BufferedImage lllllllllllllllllIllIIllllIIIIIl = null;
        try {
            if (this.getFile("/images/xulutransparent.png") != null) {
                lllllllllllllllllIllIIllllIIIIIl = this.getImage(this.getFile("/images/xulutransparent.png"), ImageIO::read);
            }
            if (lllllllllllllllllIllIIllllIIIIIl == null) {
                this.LOGGER.warn("Failed to load image");
            }
            else {
                final DynamicTexture lllllllllllllllllIllIIllllIIIlII = new DynamicTexture(lllllllllllllllllIllIIllllIIIIIl);
                lllllllllllllllllIllIIllllIIIlII.loadTexture(Logo.mc.getResourceManager());
                this.logo = Logo.mc.getTextureManager().getDynamicTextureLocation("XULU_LOGO_TRANSPARENT", lllllllllllllllllIllIIllllIIIlII);
            }
        }
        catch (Exception lllllllllllllllllIllIIllllIIIIll) {
            lllllllllllllllllIllIIllllIIIIll.printStackTrace();
        }
    }
    
    private InputStream getFile(final String lllllllllllllllllIllIIlllIlllIIl) {
        return ImageESP.class.getResourceAsStream(lllllllllllllllllIllIIlllIlllIIl);
    }
    
    @FunctionalInterface
    private interface ThrowingFunction<T, R>
    {
        R apply(final T p0) throws IOException;
    }
}
