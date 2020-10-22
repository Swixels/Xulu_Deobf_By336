package org.newdawn.slick.opengl;

import java.nio.*;

public interface ImageData
{
    ByteBuffer getImageBufferData();
    
    int getWidth();
    
    int getTexWidth();
    
    int getDepth();
    
    int getHeight();
    
    int getTexHeight();
}
