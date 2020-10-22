package org.newdawn.slick.openal;

import java.io.*;

interface AudioInputStream
{
    int getRate();
    
    boolean atEnd();
    
    int read(final byte[] p0) throws IOException;
    
    int getChannels();
    
    int read() throws IOException;
    
    void close() throws IOException;
    
    int read(final byte[] p0, final int p1, final int p2) throws IOException;
}
