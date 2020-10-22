package org.newdawn.slick.opengl;

public interface Texture
{
    int getTextureID();
    
    boolean hasAlpha();
    
    void setTextureFilter(final int p0);
    
    float getHeight();
    
    String getTextureRef();
    
    void bind();
    
    byte[] getTextureData();
    
    float getWidth();
    
    int getTextureHeight();
    
    int getImageWidth();
    
    int getImageHeight();
    
    int getTextureWidth();
    
    void release();
}
