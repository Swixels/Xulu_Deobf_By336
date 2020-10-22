package org.newdawn.slick.openal;

import com.jcraft.jogg.*;
import com.jcraft.jorbis.*;
import java.nio.*;
import org.lwjgl.*;
import java.io.*;
import org.newdawn.slick.util.*;

public class OggInputStream extends InputStream implements AudioInputStream
{
    private /* synthetic */ Packet packet;
    private /* synthetic */ int total;
    private /* synthetic */ SyncState syncState;
    /* synthetic */ boolean inited;
    private /* synthetic */ ByteBuffer pcmBuffer;
    private /* synthetic */ Block vorbisBlock;
    /* synthetic */ int bytes;
    private /* synthetic */ Page page;
    private /* synthetic */ StreamState streamState;
    private /* synthetic */ int convsize;
    private /* synthetic */ boolean endOfStream;
    private /* synthetic */ byte[] convbuffer;
    /* synthetic */ boolean endOfBitStream;
    private /* synthetic */ Info oggInfo;
    private /* synthetic */ InputStream input;
    /* synthetic */ boolean bigEndian;
    private /* synthetic */ int readIndex;
    /* synthetic */ byte[] buffer;
    private /* synthetic */ DspState dspState;
    private /* synthetic */ Comment comment;
    
    public OggInputStream(final InputStream llIllllIllIlIIl) throws IOException {
        this.convsize = 16384;
        this.convbuffer = new byte[this.convsize];
        this.oggInfo = new Info();
        this.syncState = new SyncState();
        this.streamState = new StreamState();
        this.page = new Page();
        this.packet = new Packet();
        this.comment = new Comment();
        this.dspState = new DspState();
        this.vorbisBlock = new Block(this.dspState);
        this.bytes = 0;
        this.bigEndian = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
        this.endOfBitStream = true;
        this.inited = false;
        this.pcmBuffer = BufferUtils.createByteBuffer(2048000);
        this.input = llIllllIllIlIIl;
        this.total = llIllllIllIlIIl.available();
        this.init();
    }
    
    @Override
    public int available() {
        return this.endOfStream ? 0 : 1;
    }
    
    private void readPCM() throws IOException {
        boolean llIllllIIlIlIlI = false;
        while (true) {
            if (this.endOfBitStream) {
                if (!this.getPageAndPacket()) {
                    this.syncState.clear();
                    this.endOfStream = true;
                    return;
                }
                this.endOfBitStream = false;
            }
            if (!this.inited) {
                this.inited = true;
                return;
            }
            final float[][][] llIllllIIlIllIl = { null };
            final int[] llIllllIIlIllII = new int[this.oggInfo.channels];
            while (!this.endOfBitStream) {
                while (!this.endOfBitStream) {
                    int llIllllIIllIIII = this.syncState.pageout(this.page);
                    if (llIllllIIllIIII == 0) {
                        break;
                    }
                    if (llIllllIIllIIII == -1) {
                        Log.error("Corrupt or missing data in bitstream; continuing...");
                    }
                    else {
                        this.streamState.pagein(this.page);
                        while (true) {
                            llIllllIIllIIII = this.streamState.packetout(this.packet);
                            if (llIllllIIllIIII == 0) {
                                break;
                            }
                            if (llIllllIIllIIII == -1) {
                                continue;
                            }
                            if (this.vorbisBlock.synthesis(this.packet) == 0) {
                                this.dspState.synthesis_blockin(this.vorbisBlock);
                            }
                            int llIllllIIllIIIl;
                            while ((llIllllIIllIIIl = this.dspState.synthesis_pcmout(llIllllIIlIllIl, llIllllIIlIllII)) > 0) {
                                final float[][] llIllllIIllIlII = llIllllIIlIllIl[0];
                                final int llIllllIIllIIll = (llIllllIIllIIIl < this.convsize) ? llIllllIIllIIIl : this.convsize;
                                for (int llIllllIIllIlIl = 0; llIllllIIllIlIl < this.oggInfo.channels; ++llIllllIIllIlIl) {
                                    int llIllllIIllIlll = llIllllIIllIlIl * 2;
                                    final int llIllllIIllIllI = llIllllIIlIllII[llIllllIIllIlIl];
                                    for (int llIllllIIlllIII = 0; llIllllIIlllIII < llIllllIIllIIll; ++llIllllIIlllIII) {
                                        int llIllllIIlllIIl = (int)(llIllllIIllIlII[llIllllIIllIlIl][llIllllIIllIllI + llIllllIIlllIII] * 32767.0);
                                        if (llIllllIIlllIIl > 32767) {
                                            llIllllIIlllIIl = 32767;
                                        }
                                        if (llIllllIIlllIIl < -32768) {
                                            llIllllIIlllIIl = -32768;
                                        }
                                        if (llIllllIIlllIIl < 0) {
                                            llIllllIIlllIIl |= 0x8000;
                                        }
                                        if (this.bigEndian) {
                                            this.convbuffer[llIllllIIllIlll] = (byte)(llIllllIIlllIIl >>> 8);
                                            this.convbuffer[llIllllIIllIlll + 1] = (byte)llIllllIIlllIIl;
                                        }
                                        else {
                                            this.convbuffer[llIllllIIllIlll] = (byte)llIllllIIlllIIl;
                                            this.convbuffer[llIllllIIllIlll + 1] = (byte)(llIllllIIlllIIl >>> 8);
                                        }
                                        llIllllIIllIlll += 2 * this.oggInfo.channels;
                                    }
                                }
                                final int llIllllIIllIIlI = 2 * this.oggInfo.channels * llIllllIIllIIll;
                                if (llIllllIIllIIlI >= this.pcmBuffer.remaining()) {
                                    Log.warn(String.valueOf(new StringBuilder().append("Read block from OGG that was too big to be buffered: ").append(llIllllIIllIIlI)));
                                }
                                else {
                                    this.pcmBuffer.put(this.convbuffer, 0, llIllllIIllIIlI);
                                }
                                llIllllIIlIlIlI = true;
                                this.dspState.synthesis_read(llIllllIIllIIll);
                            }
                        }
                        if (this.page.eos() != 0) {
                            this.endOfBitStream = true;
                        }
                        if (!this.endOfBitStream && llIllllIIlIlIlI) {
                            return;
                        }
                        continue;
                    }
                }
                if (!this.endOfBitStream) {
                    this.bytes = 0;
                    final int llIllllIIlIlllI = this.syncState.buffer(4096);
                    Label_0590: {
                        if (llIllllIIlIlllI >= 0) {
                            this.buffer = this.syncState.data;
                            try {
                                this.bytes = this.input.read(this.buffer, llIllllIIlIlllI, 4096);
                                break Label_0590;
                            }
                            catch (Exception llIllllIIlIllll) {
                                Log.error("Failure during vorbis decoding");
                                Log.error(llIllllIIlIllll);
                                this.endOfStream = true;
                                return;
                            }
                        }
                        this.bytes = 0;
                    }
                    this.syncState.wrote(this.bytes);
                    if (this.bytes != 0) {
                        continue;
                    }
                    this.endOfBitStream = true;
                }
            }
            this.streamState.clear();
            this.vorbisBlock.clear();
            this.dspState.clear();
            this.oggInfo.clear();
        }
    }
    
    @Override
    public int read(final byte[] llIlllIllllllll) throws IOException {
        return this.read(llIlllIllllllll, 0, llIlllIllllllll.length);
    }
    
    public int getLength() {
        return this.total;
    }
    
    private void init() throws IOException {
        this.initVorbis();
        this.readPCM();
    }
    
    private void initVorbis() {
        this.syncState.init();
    }
    
    @Override
    public void close() throws IOException {
    }
    
    @Override
    public boolean atEnd() {
        return this.endOfStream && this.readIndex >= this.pcmBuffer.position();
    }
    
    @Override
    public int read() throws IOException {
        if (this.readIndex >= this.pcmBuffer.position()) {
            this.pcmBuffer.clear();
            this.readPCM();
            this.readIndex = 0;
        }
        if (this.readIndex >= this.pcmBuffer.position()) {
            return -1;
        }
        int llIllllIIIllIIl = this.pcmBuffer.get(this.readIndex);
        if (llIllllIIIllIIl < 0) {
            llIllllIIIllIIl += 256;
        }
        ++this.readIndex;
        return llIllllIIIllIIl;
    }
    
    @Override
    public int read(final byte[] llIllllIIIIIllI, final int llIllllIIIIlIIl, final int llIllllIIIIlIII) throws IOException {
        for (int llIllllIIIIllII = 0; llIllllIIIIllII < llIllllIIIIlIII; ++llIllllIIIIllII) {
            try {
                final int llIllllIIIIlllI = this.read();
                if (llIllllIIIIlllI >= 0) {
                    llIllllIIIIIllI[llIllllIIIIllII] = (byte)llIllllIIIIlllI;
                }
                else {
                    if (llIllllIIIIllII == 0) {
                        return -1;
                    }
                    return llIllllIIIIllII;
                }
            }
            catch (IOException llIllllIIIIllIl) {
                Log.error(llIllllIIIIllIl);
                return llIllllIIIIllII;
            }
        }
        return llIllllIIIIlIII;
    }
    
    private boolean getPageAndPacket() {
        int llIllllIlIIllII = this.syncState.buffer(4096);
        this.buffer = this.syncState.data;
        if (this.buffer == null) {
            this.endOfStream = true;
            return false;
        }
        try {
            this.bytes = this.input.read(this.buffer, llIllllIlIIllII, 4096);
        }
        catch (Exception llIllllIlIlIIII) {
            Log.error("Failure reading in vorbis");
            Log.error(llIllllIlIlIIII);
            this.endOfStream = true;
            return false;
        }
        this.syncState.wrote(this.bytes);
        if (this.syncState.pageout(this.page) != 1) {
            if (this.bytes < 4096) {
                return false;
            }
            Log.error("Input does not appear to be an Ogg bitstream.");
            this.endOfStream = true;
            return false;
        }
        else {
            this.streamState.init(this.page.serialno());
            this.oggInfo.init();
            this.comment.init();
            if (this.streamState.pagein(this.page) < 0) {
                Log.error("Error reading first page of Ogg bitstream data.");
                this.endOfStream = true;
                return false;
            }
            if (this.streamState.packetout(this.packet) != 1) {
                Log.error("Error reading initial header packet.");
                this.endOfStream = true;
                return false;
            }
            if (this.oggInfo.synthesis_headerin(this.comment, this.packet) < 0) {
                Log.error("This Ogg bitstream does not contain Vorbis audio data.");
                this.endOfStream = true;
                return false;
            }
            int llIllllIlIIlIll = 0;
            while (llIllllIlIIlIll < 2) {
                while (llIllllIlIIlIll < 2) {
                    int llIllllIlIIllll = this.syncState.pageout(this.page);
                    if (llIllllIlIIllll == 0) {
                        break;
                    }
                    if (llIllllIlIIllll != 1) {
                        continue;
                    }
                    this.streamState.pagein(this.page);
                    while (llIllllIlIIlIll < 2) {
                        llIllllIlIIllll = this.streamState.packetout(this.packet);
                        if (llIllllIlIIllll == 0) {
                            break;
                        }
                        if (llIllllIlIIllll == -1) {
                            Log.error("Corrupt secondary header.  Exiting.");
                            this.endOfStream = true;
                            return false;
                        }
                        this.oggInfo.synthesis_headerin(this.comment, this.packet);
                        ++llIllllIlIIlIll;
                    }
                }
                llIllllIlIIllII = this.syncState.buffer(4096);
                this.buffer = this.syncState.data;
                try {
                    this.bytes = this.input.read(this.buffer, llIllllIlIIllII, 4096);
                }
                catch (Exception llIllllIlIIlllI) {
                    Log.error("Failed to read Vorbis: ");
                    Log.error(llIllllIlIIlllI);
                    this.endOfStream = true;
                    return false;
                }
                if (this.bytes == 0 && llIllllIlIIlIll < 2) {
                    Log.error("End of file before finding all Vorbis headers!");
                    this.endOfStream = true;
                    return false;
                }
                this.syncState.wrote(this.bytes);
            }
            this.convsize = 4096 / this.oggInfo.channels;
            this.dspState.synthesis_init(this.oggInfo);
            this.vorbisBlock.init(this.dspState);
            return true;
        }
    }
    
    @Override
    public int getRate() {
        return this.oggInfo.rate;
    }
    
    @Override
    public int getChannels() {
        return this.oggInfo.channels;
    }
}
