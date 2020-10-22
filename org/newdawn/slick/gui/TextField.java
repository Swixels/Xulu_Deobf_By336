package org.newdawn.slick.gui;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.lwjgl.*;

public class TextField extends AbstractComponent
{
    private /* synthetic */ boolean visibleCursor;
    protected /* synthetic */ int y;
    private /* synthetic */ int lastKey;
    private /* synthetic */ String value;
    protected /* synthetic */ int x;
    private /* synthetic */ int maxCharacter;
    private /* synthetic */ Color border;
    private /* synthetic */ Color text;
    private /* synthetic */ char lastChar;
    private /* synthetic */ long repeatTimer;
    private /* synthetic */ Color background;
    private /* synthetic */ int height;
    private /* synthetic */ String oldText;
    private /* synthetic */ int width;
    private /* synthetic */ Font font;
    private /* synthetic */ int cursorPos;
    private /* synthetic */ boolean consume;
    private /* synthetic */ int oldCursorPos;
    
    @Override
    public int getWidth() {
        return this.width;
    }
    
    @Override
    public int getX() {
        return this.x;
    }
    
    public String getText() {
        return this.value;
    }
    
    public void setBorderColor(final Color llllIIIlIIIIIll) {
        this.border = llllIIIlIIIIIll;
    }
    
    @Override
    public int getHeight() {
        return this.height;
    }
    
    @Override
    public void setFocus(final boolean llllIIIIIlIIlIl) {
        this.lastKey = -1;
        super.setFocus(llllIIIIIlIIlIl);
    }
    
    @Override
    public int getY() {
        return this.y;
    }
    
    public TextField(final GUIContext llllIIIllIIlIII, final Font llllIIIllIIIlll, final int llllIIIllIIlllI, final int llllIIIllIIllIl, final int llllIIIllIIIlII, final int llllIIIllIIlIll, final ComponentListener llllIIIllIIlIlI) {
        this(llllIIIllIIlIII, llllIIIllIIIlll, llllIIIllIIlllI, llllIIIllIIllIl, llllIIIllIIIlII, llllIIIllIIlIll);
        this.addListener(llllIIIllIIlIlI);
    }
    
    @Override
    public void setLocation(final int llllIIIlIIlllII, final int llllIIIlIIllllI) {
        this.x = llllIIIlIIlllII;
        this.y = llllIIIlIIllllI;
    }
    
    public void setBackgroundColor(final Color llllIIIlIIIlIIl) {
        this.background = llllIIIlIIIlIIl;
    }
    
    static {
        KEY_REPEAT_INTERVAL = 50;
        INITIAL_KEY_REPEAT_INTERVAL = 400;
    }
    
    public void setCursorPos(final int llllIIIIlIllIlI) {
        this.cursorPos = llllIIIIlIllIlI;
        if (this.cursorPos > this.value.length()) {
            this.cursorPos = this.value.length();
        }
    }
    
    protected void doUndo(final int llllIIIIIlllIII, final String llllIIIIIlllIlI) {
        if (llllIIIIIlllIlI != null) {
            this.setText(llllIIIIIlllIlI);
            this.setCursorPos(llllIIIIIlllIII);
        }
    }
    
    public void setConsumeEvents(final boolean llllIIIlIlIIlll) {
        this.consume = llllIIIlIlIIlll;
    }
    
    public void setCursorVisible(final boolean llllIIIIlIlIlII) {
        this.visibleCursor = llllIIIIlIlIlII;
    }
    
    public void setText(final String llllIIIIllIIIII) {
        this.value = llllIIIIllIIIII;
        if (this.cursorPos > llllIIIIllIIIII.length()) {
            this.cursorPos = llllIIIIllIIIII.length();
        }
    }
    
    @Override
    public void render(final GUIContext llllIIIIlllIlII, final Graphics llllIIIIlllIIll) {
        if (this.lastKey != -1) {
            if (this.input.isKeyDown(this.lastKey)) {
                if (this.repeatTimer < System.currentTimeMillis()) {
                    this.repeatTimer = System.currentTimeMillis() + 50L;
                    this.keyPressed(this.lastKey, this.lastChar);
                }
            }
            else {
                this.lastKey = -1;
            }
        }
        final Rectangle llllIIIIlllIIlI = llllIIIIlllIIll.getClip();
        llllIIIIlllIIll.setWorldClip((float)this.x, (float)this.y, (float)this.width, (float)this.height);
        final Color llllIIIIlllIIIl = llllIIIIlllIIll.getColor();
        if (this.background != null) {
            llllIIIIlllIIll.setColor(this.background.multiply(llllIIIIlllIIIl));
            llllIIIIlllIIll.fillRect((float)this.x, (float)this.y, (float)this.width, (float)this.height);
        }
        llllIIIIlllIIll.setColor(this.text.multiply(llllIIIIlllIIIl));
        final Font llllIIIIlllIIII = llllIIIIlllIIll.getFont();
        final int llllIIIIllIllll = this.font.getWidth(this.value.substring(0, this.cursorPos));
        int llllIIIIllIlllI = 0;
        if (llllIIIIllIllll > this.width) {
            llllIIIIllIlllI = this.width - llllIIIIllIllll - this.font.getWidth("_");
        }
        llllIIIIlllIIll.translate((float)(llllIIIIllIlllI + 2), 0.0f);
        llllIIIIlllIIll.setFont(this.font);
        llllIIIIlllIIll.drawString(this.value, (float)(this.x + 1), (float)(this.y + 1));
        if (this.hasFocus() && this.visibleCursor) {
            llllIIIIlllIIll.drawString("_", (float)(this.x + 1 + llllIIIIllIllll + 2), (float)(this.y + 1));
        }
        llllIIIIlllIIll.translate((float)(-llllIIIIllIlllI - 2), 0.0f);
        if (this.border != null) {
            llllIIIIlllIIll.setColor(this.border.multiply(llllIIIIlllIIIl));
            llllIIIIlllIIll.drawRect((float)this.x, (float)this.y, (float)this.width, (float)this.height);
        }
        llllIIIIlllIIll.setColor(llllIIIIlllIIIl);
        llllIIIIlllIIll.setFont(llllIIIIlllIIII);
        llllIIIIlllIIll.clearWorldClip();
        llllIIIIlllIIll.setClip(llllIIIIlllIIlI);
    }
    
    public void deactivate() {
        this.setFocus(false);
    }
    
    public void setTextColor(final Color llllIIIIlllllll) {
        this.text = llllIIIIlllllll;
    }
    
    protected void recordOldPosition() {
        this.oldText = this.getText();
        this.oldCursorPos = this.cursorPos;
    }
    
    public TextField(final GUIContext llllIIIlIlllIIl, final Font llllIIIlIlllIII, final int llllIIIlIllIIII, final int llllIIIlIlIllll, final int llllIIIlIllIlIl, final int llllIIIlIlIllIl) {
        super(llllIIIlIlllIIl);
        this.maxCharacter = 10000;
        this.value = "";
        this.border = Color.white;
        this.text = Color.white;
        this.background = new Color(0.0f, 0.0f, 0.0f, 0.5f);
        this.visibleCursor = true;
        this.lastKey = -1;
        this.lastChar = '\0';
        this.consume = true;
        this.font = llllIIIlIlllIII;
        this.setLocation(llllIIIlIllIIII, llllIIIlIlIllll);
        this.width = llllIIIlIllIlIl;
        this.height = llllIIIlIlIllIl;
    }
    
    @Override
    public void keyPressed(final int llllIIIIIllIIII, final char llllIIIIIlIllII) {
        if (this.hasFocus()) {
            if (llllIIIIIllIIII != -1) {
                if (llllIIIIIllIIII == 47 && (this.input.isKeyDown(29) || this.input.isKeyDown(157))) {
                    final String llllIIIIIllIIlI = Sys.getClipboard();
                    if (llllIIIIIllIIlI != null) {
                        this.doPaste(llllIIIIIllIIlI);
                    }
                    return;
                }
                if (llllIIIIIllIIII == 44 && (this.input.isKeyDown(29) || this.input.isKeyDown(157))) {
                    if (this.oldText != null) {
                        this.doUndo(this.oldCursorPos, this.oldText);
                    }
                    return;
                }
                if (this.input.isKeyDown(29) || this.input.isKeyDown(157)) {
                    return;
                }
                if (this.input.isKeyDown(56) || this.input.isKeyDown(184)) {
                    return;
                }
            }
            if (this.lastKey != llllIIIIIllIIII) {
                this.lastKey = llllIIIIIllIIII;
                this.repeatTimer = System.currentTimeMillis() + 400L;
            }
            else {
                this.repeatTimer = System.currentTimeMillis() + 50L;
            }
            this.lastChar = llllIIIIIlIllII;
            if (llllIIIIIllIIII == 203) {
                if (this.cursorPos > 0) {
                    --this.cursorPos;
                }
                if (this.consume) {
                    this.container.getInput().consumeEvent();
                }
            }
            else if (llllIIIIIllIIII == 205) {
                if (this.cursorPos < this.value.length()) {
                    ++this.cursorPos;
                }
                if (this.consume) {
                    this.container.getInput().consumeEvent();
                }
            }
            else if (llllIIIIIllIIII == 14) {
                if (this.cursorPos > 0 && this.value.length() > 0) {
                    if (this.cursorPos < this.value.length()) {
                        this.value = String.valueOf(new StringBuilder().append(this.value.substring(0, this.cursorPos - 1)).append(this.value.substring(this.cursorPos)));
                    }
                    else {
                        this.value = this.value.substring(0, this.cursorPos - 1);
                    }
                    --this.cursorPos;
                }
                if (this.consume) {
                    this.container.getInput().consumeEvent();
                }
            }
            else if (llllIIIIIllIIII == 211) {
                if (this.value.length() > this.cursorPos) {
                    this.value = String.valueOf(new StringBuilder().append(this.value.substring(0, this.cursorPos)).append(this.value.substring(this.cursorPos + 1)));
                }
                if (this.consume) {
                    this.container.getInput().consumeEvent();
                }
            }
            else if (llllIIIIIlIllII < '\u007f' && llllIIIIIlIllII > '\u001f' && this.value.length() < this.maxCharacter) {
                if (this.cursorPos < this.value.length()) {
                    this.value = String.valueOf(new StringBuilder().append(this.value.substring(0, this.cursorPos)).append(llllIIIIIlIllII).append(this.value.substring(this.cursorPos)));
                }
                else {
                    this.value = String.valueOf(new StringBuilder().append(this.value.substring(0, this.cursorPos)).append(llllIIIIIlIllII));
                }
                ++this.cursorPos;
                if (this.consume) {
                    this.container.getInput().consumeEvent();
                }
            }
            else if (llllIIIIIllIIII == 28) {
                this.notifyListeners();
                if (this.consume) {
                    this.container.getInput().consumeEvent();
                }
            }
        }
    }
    
    protected void doPaste(final String llllIIIIlIIIllI) {
        this.recordOldPosition();
        for (int llllIIIIlIIlIII = 0; llllIIIIlIIlIII < llllIIIIlIIIllI.length(); ++llllIIIIlIIlIII) {
            this.keyPressed(-1, llllIIIIlIIIllI.charAt(llllIIIIlIIlIII));
        }
    }
    
    public void setMaxLength(final int llllIIIIlIIlllI) {
        this.maxCharacter = llllIIIIlIIlllI;
        if (this.value.length() > this.maxCharacter) {
            this.value = this.value.substring(0, this.maxCharacter);
        }
    }
}
