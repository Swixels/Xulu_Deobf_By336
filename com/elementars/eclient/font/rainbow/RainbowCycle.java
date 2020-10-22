package com.elementars.eclient.font.rainbow;

public class RainbowCycle implements Cloneable
{
    public /* synthetic */ int b;
    public /* synthetic */ ColorChangeType blue;
    public /* synthetic */ ColorChangeType red;
    public /* synthetic */ int g;
    public /* synthetic */ ColorChangeType green;
    public /* synthetic */ int r;
    
    public RainbowCycle() {
        this.red = ColorChangeType.INCREASE;
        this.green = ColorChangeType.NONE;
        this.blue = ColorChangeType.NONE;
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }
    
    public RainbowCycle clone() {
        try {
            return (RainbowCycle)super.clone();
        }
        catch (CloneNotSupportedException lIlllll) {
            lIlllll.printStackTrace();
            return this;
        }
    }
}
