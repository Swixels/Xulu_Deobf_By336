package com.elementars.eclient.module.render;

import dev.xulu.settings.*;
import net.minecraft.world.chunk.*;
import com.elementars.eclient.event.*;
import com.elementars.eclient.command.*;
import org.lwjgl.opengl.*;
import org.apache.commons.lang3.*;
import net.minecraft.client.*;
import com.elementars.eclient.module.*;
import com.elementars.eclient.event.events.*;
import java.text.*;
import java.util.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.*;
import com.elementars.eclient.util.*;

public class ChunkFinder extends Module
{
    private /* synthetic */ LastSetting lastSetting;
    private final /* synthetic */ Value<Boolean> relative;
    private /* synthetic */ PrintWriter logWriter;
    private /* synthetic */ int list;
    private final /* synthetic */ Value<Boolean> saveNewChunks;
    private final /* synthetic */ Value<Boolean> alsoSaveNormalCoords;
    private final /* synthetic */ Value<Boolean> saveInRegionFolder;
    private final /* synthetic */ Value<String> saveOption;
    static /* synthetic */ ArrayList<Chunk> chunks;
    private static /* synthetic */ boolean dirty;
    private final /* synthetic */ Value<Integer> yOffset;
    
    @EventTarget
    public void onChunk(final ChunkEvent llllIlIlIIlIIlI) {
        if (!llllIlIlIIlIIlI.getPacket().isFullChunk()) {
            ChunkFinder.chunks.add(llllIlIlIIlIIlI.getChunk());
            ChunkFinder.dirty = true;
            if (this.saveNewChunks.getValue()) {
                this.saveNewChunk(llllIlIlIIlIIlI.getChunk());
            }
        }
    }
    
    private PrintWriter testAndGetLogWriter() {
        if (this.lastSetting.testChangeAndUpdate()) {
            this.logWriterClose();
            this.logWriterOpen();
        }
        return this.logWriter;
    }
    
    private boolean isInteger(final String llllIlIIIllllll) {
        try {
            Integer.parseInt(llllIlIIIllllll);
        }
        catch (NumberFormatException | NullPointerException ex2) {
            final RuntimeException ex;
            final RuntimeException llllIlIIlIIIIIl = ex;
            return false;
        }
        return true;
    }
    
    private void logWriterOpen() {
        final String llllIlIIlllIlll = this.getPath().toString();
        try {
            this.logWriter = new PrintWriter(new BufferedWriter(new FileWriter(llllIlIIlllIlll, true)), true);
            String llllIlIIllllIlI = "timestamp,ChunkX,ChunkZ";
            if (this.alsoSaveNormalCoords.getValue()) {
                llllIlIIllllIlI = String.valueOf(new StringBuilder().append(llllIlIIllllIlI).append(",x coordinate,z coordinate"));
            }
            this.logWriter.println(llllIlIIllllIlI);
        }
        catch (Exception llllIlIIllllIIl) {
            llllIlIIllllIIl.printStackTrace();
            System.err.print(String.valueOf(new StringBuilder().append("some exception happened when trying to start the logging -> ").append(llllIlIIllllIIl.getMessage())));
            Command.sendChatMessage(String.valueOf(new StringBuilder().append("onLogStart: ").append(llllIlIIllllIIl.getMessage())));
        }
    }
    
    @Override
    public void onDisable() {
        this.logWriterClose();
        ChunkFinder.chunks.clear();
    }
    
    @Override
    public void destroy() {
        GL11.glDeleteLists(1, 1);
    }
    
    private String getNHackInetName() {
        String llllIlIIlIlIIlI = ChunkFinder.mc.getCurrentServerData().serverIP;
        if (SystemUtils.IS_OS_WINDOWS) {
            llllIlIIlIlIIlI = llllIlIIlIlIIlI.replace(":", "_");
        }
        if (this.hasNoPort(llllIlIIlIlIIlI)) {
            llllIlIIlIlIIlI = String.valueOf(new StringBuilder().append(llllIlIIlIlIIlI).append("_25565"));
        }
        return llllIlIIlIlIIlI;
    }
    
    private String getNewChunkInfo(final Chunk llllIlIlIIIIIlI) {
        String llllIlIlIIIIlII = String.format("%d,%d,%d", System.currentTimeMillis(), llllIlIlIIIIIlI.x, llllIlIlIIIIIlI.z);
        if (this.alsoSaveNormalCoords.getValue()) {
            llllIlIlIIIIlII = String.valueOf(new StringBuilder().append(llllIlIlIIIIlII).append(String.format(",%d,%d", llllIlIlIIIIIlI.x * 16 + 8, llllIlIlIIIIIlI.z * 16 + 8)));
        }
        return llllIlIlIIIIlII;
    }
    
    public void saveNewChunk(final Chunk llllIlIlIIIlIlI) {
        this.saveNewChunk(this.testAndGetLogWriter(), this.getNewChunkInfo(llllIlIlIIIlIlI));
    }
    
    private boolean hasNoPort(final String llllIlIIlIIlIlI) {
        if (!llllIlIIlIIlIlI.contains("_")) {
            return true;
        }
        final String[] llllIlIIlIIlIIl = llllIlIIlIIlIlI.split("_");
        final String llllIlIIlIIlIII = llllIlIIlIIlIIl[llllIlIIlIIlIIl.length - 1];
        return !this.isInteger(llllIlIIlIIlIII);
    }
    
    private Path makeMultiplayerDirectory() {
        File llllIlIIlIllIlI = Minecraft.getMinecraft().gameDir;
        if (this.saveOption.getValue().equalsIgnoreCase("liteLoaderWdl")) {
            final String llllIlIIlIlllIl = ChunkFinder.mc.getCurrentServerData().serverName;
            llllIlIIlIllIlI = new File(llllIlIIlIllIlI, "saves");
            llllIlIIlIllIlI = new File(llllIlIIlIllIlI, llllIlIIlIlllIl);
        }
        else if (this.saveOption.getValue().equalsIgnoreCase("nhackWdl")) {
            final String llllIlIIlIlllII = this.getNHackInetName();
            llllIlIIlIllIlI = new File(llllIlIIlIllIlI, "config");
            llllIlIIlIllIlI = new File(llllIlIIlIllIlI, "wdl-saves");
            llllIlIIlIllIlI = new File(llllIlIIlIllIlI, llllIlIIlIlllII);
            if (!llllIlIIlIllIlI.exists()) {
                Command.sendChatMessage(String.valueOf(new StringBuilder().append("nhack wdl directory doesnt exist: ").append(llllIlIIlIlllII)));
                Command.sendChatMessage("creating the directory now. It is recommended to update the ip");
            }
        }
        else {
            String llllIlIIlIllIIl = String.valueOf(new StringBuilder().append(ChunkFinder.mc.getCurrentServerData().serverName).append("-").append(ChunkFinder.mc.getCurrentServerData().serverIP));
            if (SystemUtils.IS_OS_WINDOWS) {
                llllIlIIlIllIIl = llllIlIIlIllIIl.replace(":", "_");
            }
            llllIlIIlIllIlI = new File(llllIlIIlIllIlI, "KAMI_NewChunks");
            llllIlIIlIllIlI = new File(llllIlIIlIllIlI, llllIlIIlIllIIl);
        }
        return llllIlIIlIllIlI.toPath();
    }
    
    @Override
    public void onWorldRender(final RenderEvent llllIlIlIlIIIlI) {
        if (ChunkFinder.dirty) {
            GL11.glNewList(this.list, 4864);
            GL11.glPushMatrix();
            GL11.glEnable(2848);
            GL11.glDisable(2929);
            GL11.glDisable(3553);
            GL11.glDepthMask(false);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(3042);
            GL11.glLineWidth(1.0f);
            for (final Chunk llllIlIlIlIIlII : ChunkFinder.chunks) {
                final double llllIlIlIlIIlll = llllIlIlIlIIlII.x * 16;
                final double llllIlIlIlIIllI = 0.0;
                final double llllIlIlIlIIlIl = llllIlIlIlIIlII.z * 16;
                GL11.glColor3f(0.6f, 0.1f, 0.2f);
                GL11.glBegin(2);
                GL11.glVertex3d(llllIlIlIlIIlll, llllIlIlIlIIllI, llllIlIlIlIIlIl);
                GL11.glVertex3d(llllIlIlIlIIlll + 16.0, llllIlIlIlIIllI, llllIlIlIlIIlIl);
                GL11.glVertex3d(llllIlIlIlIIlll + 16.0, llllIlIlIlIIllI, llllIlIlIlIIlIl + 16.0);
                GL11.glVertex3d(llllIlIlIlIIlll, llllIlIlIlIIllI, llllIlIlIlIIlIl + 16.0);
                GL11.glVertex3d(llllIlIlIlIIlll, llllIlIlIlIIllI, llllIlIlIlIIlIl);
                GL11.glEnd();
            }
            GL11.glDisable(3042);
            GL11.glDepthMask(true);
            GL11.glEnable(3553);
            GL11.glEnable(2929);
            GL11.glDisable(2848);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glEndList();
            ChunkFinder.dirty = false;
        }
        final double llllIlIlIlIIIIl = ChunkFinder.mc.getRenderManager().renderPosX;
        final double llllIlIlIlIIIII = this.relative.getValue() ? 0.0 : (-ChunkFinder.mc.getRenderManager().renderPosY);
        final double llllIlIlIIlllll = ChunkFinder.mc.getRenderManager().renderPosZ;
        GL11.glTranslated(-llllIlIlIlIIIIl, llllIlIlIlIIIII + this.yOffset.getValue(), -llllIlIlIIlllll);
        GL11.glCallList(this.list);
        GL11.glTranslated(llllIlIlIlIIIIl, -(llllIlIlIlIIIII + this.yOffset.getValue()), llllIlIlIIlllll);
    }
    
    private void saveNewChunk(final PrintWriter llllIlIIIllIllI, final String llllIlIIIllIlIl) {
        llllIlIIIllIllI.println(llllIlIIIllIlIl);
    }
    
    private void logWriterClose() {
        if (this.logWriter != null) {
            this.logWriter.close();
            this.logWriter = null;
        }
    }
    
    public ChunkFinder() {
        super("ChunkFinder", "Finds new chunks", 0, Category.RENDER, true);
        this.yOffset = this.register(new Value<Integer>("Y Offset", this, 0, 0, 256));
        this.relative = this.register(new Value<Boolean>("Relative", this, true));
        this.saveNewChunks = this.register(new Value<Boolean>("Save New Chunks", this, false));
        this.saveOption = this.register(new Value<String>("Save Option", this, "extraFolder", new ArrayList<String>(Arrays.asList("extraFolder", "liteLoaderWdl", "nhackWdl"))));
        this.saveInRegionFolder = this.register(new Value<Boolean>("In Region", this, false));
        this.alsoSaveNormalCoords = this.register(new Value<Boolean>("Save Normal Coords", this, false));
        this.lastSetting = new LastSetting();
        this.list = GL11.glGenLists(1);
    }
    
    @EventTarget
    private void onUnload(final UnloadChunkEvent llllIlIIIllIIII) {
        ChunkFinder.dirty = ChunkFinder.chunks.remove(llllIlIIIllIIII.getChunk());
    }
    
    private Path getPath() {
        File llllIlIIllIlIlI = null;
        final int llllIlIIllIlIIl = ChunkFinder.mc.player.dimension;
        if (ChunkFinder.mc.isSingleplayer()) {
            try {
                llllIlIIllIlIlI = ChunkFinder.mc.getIntegratedServer().getWorld(llllIlIIllIlIIl).getChunkSaveLocation();
            }
            catch (Exception llllIlIIllIllIl) {
                llllIlIIllIllIl.printStackTrace();
                System.err.print(String.valueOf(new StringBuilder().append("some exception happened when getting canonicalFile -> ").append(llllIlIIllIllIl.getMessage())));
                Command.sendChatMessage(String.valueOf(new StringBuilder().append("onGetPath: ").append(llllIlIIllIllIl.getMessage())));
            }
            if (llllIlIIllIlIlI.toPath().relativize(ChunkFinder.mc.gameDir.toPath()).getNameCount() != 2) {
                llllIlIIllIlIlI = llllIlIIllIlIlI.getParentFile();
            }
        }
        else {
            llllIlIIllIlIlI = this.makeMultiplayerDirectory().toFile();
        }
        if (llllIlIIllIlIIl != 0) {
            llllIlIIllIlIlI = new File(llllIlIIllIlIlI, String.valueOf(new StringBuilder().append("DIM").append(llllIlIIllIlIIl)));
        }
        if (this.saveInRegionFolder.getValue()) {
            llllIlIIllIlIlI = new File(llllIlIIllIlIlI, "region");
        }
        llllIlIIllIlIlI = new File(llllIlIIllIlIlI, "newChunkLogs");
        final String llllIlIIllIlIII = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        llllIlIIllIlIlI = new File(llllIlIIllIlIlI, String.valueOf(new StringBuilder().append(ChunkFinder.mc.getSession().getUsername()).append("_").append(llllIlIIllIlIII).append(".csv")));
        final Path llllIlIIllIIlll = llllIlIIllIlIlI.toPath();
        try {
            if (!Files.exists(llllIlIIllIIlll, new LinkOption[0])) {
                Files.createDirectories(llllIlIIllIIlll.getParent(), (FileAttribute<?>[])new FileAttribute[0]);
                Files.createFile(llllIlIIllIIlll, (FileAttribute<?>[])new FileAttribute[0]);
            }
        }
        catch (IOException llllIlIIllIllII) {
            llllIlIIllIllII.printStackTrace();
            System.err.print(String.valueOf(new StringBuilder().append("some exception happened when trying to make the file -> ").append(llllIlIIllIllII.getMessage())));
            Command.sendChatMessage(String.valueOf(new StringBuilder().append("onCreateFile: ").append(llllIlIIllIllII.getMessage())));
        }
        return llllIlIIllIIlll;
    }
    
    static {
        ChunkFinder.chunks = new ArrayList<Chunk>();
        ChunkFinder.dirty = true;
    }
    
    private enum SaveOption
    {
        nhackWdl, 
        extraFolder, 
        liteLoaderWdl;
    }
    
    private class LastSetting
    {
        /* synthetic */ boolean lastSaveNormal;
        /* synthetic */ String lastSaveOption;
        /* synthetic */ String ip;
        /* synthetic */ boolean lastInRegion;
        /* synthetic */ int dimension;
        
        private void update() {
            this.lastSaveOption = ChunkFinder.this.saveOption.getValue();
            this.lastInRegion = ChunkFinder.this.saveInRegionFolder.getValue();
            this.lastSaveNormal = ChunkFinder.this.alsoSaveNormalCoords.getValue();
            this.dimension = Helper.mc.player.dimension;
            this.ip = Helper.mc.getCurrentServerData().serverIP;
        }
        
        public boolean testChange() {
            return !ChunkFinder.this.saveOption.getValue().equals(this.lastSaveOption) || ChunkFinder.this.saveInRegionFolder.getValue() != this.lastInRegion || ChunkFinder.this.alsoSaveNormalCoords.getValue() != this.lastSaveNormal || this.dimension != Helper.mc.player.dimension || !Helper.mc.getCurrentServerData().serverIP.equals(this.ip);
        }
        
        public boolean testChangeAndUpdate() {
            if (this.testChange()) {
                this.update();
                return true;
            }
            return false;
        }
    }
}
