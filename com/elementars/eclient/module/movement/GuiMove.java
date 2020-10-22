package com.elementars.eclient.module.movement;

import com.elementars.eclient.module.*;
import net.minecraft.client.gui.*;
import org.lwjgl.input.*;
import net.minecraft.client.settings.*;
import net.minecraft.client.gui.inventory.*;
import com.elementars.eclient.util.*;
import net.minecraft.client.entity.*;

public class GuiMove extends Module
{
    public GuiMove() {
        super("GuiMove", "Move in gui menus", 0, Category.MOVEMENT, true);
    }
    
    @Override
    public void onUpdate() {
        if (GuiMove.mc.currentScreen instanceof GuiChat || GuiMove.mc.currentScreen == null) {
            return;
        }
        final int lllllllIIIllI;
        final int[] lllllllIIlIII = (Object)(lllllllIIIllI = (int)(Object)new int[] { GuiMove.mc.gameSettings.keyBindForward.getKeyCode(), GuiMove.mc.gameSettings.keyBindLeft.getKeyCode(), GuiMove.mc.gameSettings.keyBindRight.getKeyCode(), GuiMove.mc.gameSettings.keyBindBack.getKeyCode(), GuiMove.mc.gameSettings.keyBindJump.getKeyCode() });
        final float lllllllIIIlIl = lllllllIIIllI.length;
        for (byte lllllllIIIlII = 0; lllllllIIIlII < lllllllIIIlIl; ++lllllllIIIlII) {
            final int lllllllIIlIlI = lllllllIIIllI[lllllllIIIlII];
            if (Keyboard.isKeyDown(lllllllIIlIlI)) {
                KeyBinding.setKeyBindState(lllllllIIlIlI, true);
            }
            else {
                KeyBinding.setKeyBindState(lllllllIIlIlI, false);
            }
        }
        if (Wrapper.getMinecraft().currentScreen instanceof GuiContainer) {
            if (Keyboard.isKeyDown((int)200)) {
                final EntityPlayerSP player = Wrapper.getMinecraft().player;
                player.rotationPitch -= 7.0f;
            }
            if (Keyboard.isKeyDown((int)208)) {
                final EntityPlayerSP player2 = Wrapper.getMinecraft().player;
                player2.rotationPitch += 7.0f;
            }
            if (Keyboard.isKeyDown((int)205)) {
                final EntityPlayerSP player3 = Wrapper.getMinecraft().player;
                player3.rotationYaw += 7.0f;
            }
            if (Keyboard.isKeyDown((int)203)) {
                final EntityPlayerSP player4 = Wrapper.getMinecraft().player;
                player4.rotationYaw -= 7.0f;
            }
            if (Keyboard.isKeyDown(Wrapper.getMinecraft().gameSettings.keyBindSprint.getKeyCode())) {
                Wrapper.getMinecraft().player.setSprinting(true);
            }
        }
    }
}
