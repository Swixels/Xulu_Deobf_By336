package com.elementars.eclient.font.rainbow;

public class RainbowCycler
{
    public static RainbowCycle rainbowCycle(final int llllllllllllllllIllIlIllIIlIllll, final RainbowCycle llllllllllllllllIllIlIllIIlIlllI) {
        for (int llllllllllllllllIllIlIllIIllIIII = 0; llllllllllllllllIllIlIllIIllIIII < llllllllllllllllIllIlIllIIlIllll; ++llllllllllllllllIllIlIllIIllIIII) {
            if (llllllllllllllllIllIlIllIIlIlllI.red == ColorChangeType.INCREASE) {
                llllllllllllllllIllIlIllIIlIlllI.r += 4;
                if (llllllllllllllllIllIlIllIIlIlllI.r > 255) {
                    llllllllllllllllIllIlIllIIlIlllI.red = ColorChangeType.DECRASE;
                    llllllllllllllllIllIlIllIIlIlllI.green = ColorChangeType.INCREASE;
                }
            }
            else if (llllllllllllllllIllIlIllIIlIlllI.red == ColorChangeType.DECRASE) {
                llllllllllllllllIllIlIllIIlIlllI.r -= 4;
                if (llllllllllllllllIllIlIllIIlIlllI.r == 0) {
                    llllllllllllllllIllIlIllIIlIlllI.red = ColorChangeType.NONE;
                }
            }
            if (llllllllllllllllIllIlIllIIlIlllI.green == ColorChangeType.INCREASE) {
                llllllllllllllllIllIlIllIIlIlllI.g += 4;
                if (llllllllllllllllIllIlIllIIlIlllI.g > 255) {
                    llllllllllllllllIllIlIllIIlIlllI.green = ColorChangeType.DECRASE;
                    llllllllllllllllIllIlIllIIlIlllI.blue = ColorChangeType.INCREASE;
                }
            }
            else if (llllllllllllllllIllIlIllIIlIlllI.green == ColorChangeType.DECRASE) {
                llllllllllllllllIllIlIllIIlIlllI.g -= 4;
                if (llllllllllllllllIllIlIllIIlIlllI.g == 0) {
                    llllllllllllllllIllIlIllIIlIlllI.green = ColorChangeType.NONE;
                }
            }
            if (llllllllllllllllIllIlIllIIlIlllI.blue == ColorChangeType.INCREASE) {
                llllllllllllllllIllIlIllIIlIlllI.b += 4;
                if (llllllllllllllllIllIlIllIIlIlllI.b > 255) {
                    llllllllllllllllIllIlIllIIlIlllI.blue = ColorChangeType.DECRASE;
                    llllllllllllllllIllIlIllIIlIlllI.red = ColorChangeType.INCREASE;
                }
            }
            else if (llllllllllllllllIllIlIllIIlIlllI.blue == ColorChangeType.DECRASE) {
                llllllllllllllllIllIlIllIIlIlllI.b -= 4;
                if (llllllllllllllllIllIlIllIIlIlllI.b == 0) {
                    llllllllllllllllIllIlIllIIlIlllI.blue = ColorChangeType.NONE;
                }
            }
        }
        return llllllllllllllllIllIlIllIIlIlllI;
    }
}
