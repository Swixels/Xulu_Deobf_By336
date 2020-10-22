package dev.xulu.clickgui;

import net.minecraft.client.gui.*;
import java.util.*;
import org.lwjgl.input.*;
import java.io.*;
import dev.xulu.clickgui.item.*;

public final class ClickGui extends GuiScreen
{
    private static /* synthetic */ ClickGui clickGui;
    private final /* synthetic */ ArrayList<Panel> panels;
    
    public void mouseClicked(final int lllllIIlIIlIlll, final int lllllIIlIIlIllI, final int lllllIIlIIllIIl) {
        this.panels.forEach(lllllIIIlIIlllI -> lllllIIIlIIlllI.mouseClicked(lllllIIlIIlIlll, lllllIIlIIlIllI, lllllIIlIIllIIl));
    }
    
    private void load() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: istore_1        /* lllllIIlIllIlIl */
        //     3: invokestatic    com/elementars/eclient/module/Category.values:()[Lcom/elementars/eclient/module/Category;
        //     6: astore_2        /* lllllIIlIllIIlI */
        //     7: aload_2         /* lllllIIlIllIIlI */
        //     8: arraylength    
        //     9: istore_3        /* lllllIIlIllIIIl */
        //    10: iconst_0       
        //    11: istore          lllllIIlIllIIII
        //    13: iload           lllllIIlIllIIII
        //    15: iload_3         /* lllllIIlIllIIIl */
        //    16: if_icmpge       125
        //    19: aload_2         /* lllllIIlIllIIlI */
        //    20: iload           lllllIIlIllIIII
        //    22: aaload         
        //    23: astore          lllllIIlIllIlll
        //    25: aload           lllllIIlIllIlll
        //    27: getstatic       com/elementars/eclient/module/Category.HIDDEN:Lcom/elementars/eclient/module/Category;
        //    30: if_acmpeq       119
        //    33: aload           lllllIIlIllIlll
        //    35: getstatic       com/elementars/eclient/module/Category.HUD:Lcom/elementars/eclient/module/Category;
        //    38: if_acmpne       44
        //    41: goto            119
        //    44: aload_0         /* lllllIIlIllIllI */
        //    45: getfield        dev/xulu/clickgui/ClickGui.panels:Ljava/util/ArrayList;
        //    48: astore          lllllIIlIlllIIl
        //    50: new             Ljava/lang/StringBuilder;
        //    53: dup            
        //    54: invokespecial   java/lang/StringBuilder.<init>:()V
        //    57: aload           lllllIIlIllIlll
        //    59: invokevirtual   com/elementars/eclient/module/Category.name:()Ljava/lang/String;
        //    62: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
        //    65: iconst_0       
        //    66: invokevirtual   java/lang/String.charAt:(I)C
        //    69: invokestatic    java/lang/Character.toUpperCase:(C)C
        //    72: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //    75: aload           lllllIIlIllIlll
        //    77: invokevirtual   com/elementars/eclient/module/Category.name:()Ljava/lang/String;
        //    80: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
        //    83: iconst_1       
        //    84: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //    87: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    90: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    93: astore          lllllIIlIlllIII
        //    95: iinc            lllllIIlIllIlIl, 90
        //    98: aload           lllllIIlIlllIIl
        //   100: new             Ldev/xulu/clickgui/ClickGui$1;
        //   103: dup            
        //   104: aload_0         /* lllllIIlIllIllI */
        //   105: aload           lllllIIlIlllIII
        //   107: iload_1         /* lllllIIlIllIlIl */
        //   108: iconst_4       
        //   109: iconst_1       
        //   110: aload           lllllIIlIllIlll
        //   112: invokespecial   dev/xulu/clickgui/ClickGui$1.<init>:(Ldev/xulu/clickgui/ClickGui;Ljava/lang/String;IIZLcom/elementars/eclient/module/Category;)V
        //   115: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   118: pop            
        //   119: iinc            lllllIIlIllIIII, 1
        //   122: goto            13
        //   125: aload_0         /* lllllIIlIllIllI */
        //   126: getfield        dev/xulu/clickgui/ClickGui.panels:Ljava/util/ArrayList;
        //   129: invokedynamic   BootstrapMethod #1, accept:()Ljava/util/function/Consumer;
        //   134: invokevirtual   java/util/ArrayList.forEach:(Ljava/util/function/Consumer;)V
        //   137: return         
        //    StackMapTable: 00 04 FF 00 0D 00 05 07 00 02 01 07 00 44 01 01 00 00 FC 00 1E 07 00 3E FB 00 4A FA 00 05
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
    
    public void handleMouseInput() throws IOException {
        final int lllllIIIllIlIlI = 5;
        if (Mouse.getEventDWheel() > 0) {
            for (final Panel lllllIIIllIllIl : this.panels) {
                lllllIIIllIllIl.setY(lllllIIIllIllIl.getY() + lllllIIIllIlIlI);
            }
        }
        if (Mouse.getEventDWheel() < 0) {
            for (final Panel lllllIIIllIllII : this.panels) {
                lllllIIIllIllII.setY(lllllIIIllIllII.getY() - lllllIIIllIlIlI);
            }
        }
        super.handleMouseInput();
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public ClickGui() {
        this.panels = new ArrayList<Panel>();
        if (this.getPanels().isEmpty()) {
            this.load();
        }
    }
    
    public void mouseReleased(final int lllllIIlIIIlIll, final int lllllIIlIIIlIlI, final int lllllIIlIIIllIl) {
        this.panels.forEach(lllllIIIlIlIllI -> lllllIIIlIlIllI.mouseReleased(lllllIIlIIIlIll, lllllIIlIIIlIlI, lllllIIlIIIllIl));
    }
    
    public static ClickGui getClickGui() {
        return (ClickGui.clickGui == null) ? (ClickGui.clickGui = new ClickGui()) : ClickGui.clickGui;
    }
    
    public void drawScreen(final int lllllIIlIlIIIll, final int lllllIIlIlIIllI, final float lllllIIlIlIIlIl) {
        this.drawDefaultBackground();
        this.panels.forEach(lllllIIIlIIIIlI -> lllllIIIlIIIIlI.drawScreen(lllllIIlIlIIIll, lllllIIlIlIIllI, lllllIIlIlIIlIl));
    }
    
    public final ArrayList<Panel> getPanels() {
        return this.panels;
    }
    
    protected void keyTyped(final char lllllIIIllllIll, final int lllllIIIlllIlll) {
        for (final Panel lllllIIIllllllI : this.panels) {
            if (lllllIIIllllllI != null && lllllIIIllllllI.getOpen()) {
                for (final Item lllllIIIlllllll : lllllIIIllllllI.getItems()) {
                    try {
                        if (lllllIIIlllllll.keyTyped(lllllIIIllllIll, lllllIIIlllIlll)) {
                            return;
                        }
                        continue;
                    }
                    catch (IOException lllllIIlIIIIIII) {
                        lllllIIlIIIIIII.printStackTrace();
                    }
                }
            }
        }
        try {
            super.keyTyped(lllllIIIllllIll, lllllIIIlllIlll);
        }
        catch (IOException lllllIIIlllllIl) {
            lllllIIIlllllIl.printStackTrace();
        }
    }
}
