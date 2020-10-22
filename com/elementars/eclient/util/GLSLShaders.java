package com.elementars.eclient.util;

public enum GLSLShaders
{
    SUNSETWAVES("/shaders/sunsetwaves.fsh"), 
    REDGLOW("/shaders/redglow.fsh"), 
    SNAKE("/shaders/snake.fsh"), 
    WAIFU("/shaders/waifu.fsh");
    
    /* synthetic */ String s;
    
    JUPITER("/shaders/jupiter.fsh"), 
    PINWHEEL("/shaders/pinwheel.fsh"), 
    DJ("/shaders/dj.fsh"), 
    TRIANGLE("/shaders/triangle.fsh"), 
    STORM("/shaders/storm.fsh"), 
    SPACE2("/shaders/space2.fsh"), 
    BLUEGRID("/shaders/bluegrid.fsh"), 
    SKY("/shaders/sky.fsh"), 
    BLUENEBULA("/shaders/bluenebula.fsh"), 
    PURPLEGRID("/shaders/purplegrid.fsh"), 
    CLOUDS("/shaders/clouds.fsh"), 
    SPACE("/shaders/space.fsh"), 
    XULU("/shaders/xulu.fsh"), 
    BLUEVORTEX("/shaders/bluevortex.fsh"), 
    MATRIX("/shaders/matrix.fsh"), 
    ICYFIRE("/shaders/icyfire.fsh"), 
    CITY("/shaders/city.fsh"), 
    MATRIXRED("/shaders/matrixred.fsh"), 
    AWESOME("/shaders/awesome.fsh"), 
    MINECRAFT("/shaders/minecraft.fsh"), 
    CAVE("/shaders/cave.fsh"), 
    PURPLEMIST("/shaders/purplemist.fsh");
    
    public String get() {
        return this.s;
    }
    
    private GLSLShaders(final String lllllllllllllllllIlIlllIllllllIl) {
        this.s = lllllllllllllllllIlIlllIllllllIl;
    }
}
