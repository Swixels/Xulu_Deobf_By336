package org.newdawn.slick;

import org.newdawn.slick.util.*;
import org.lwjgl.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import java.util.*;
import java.io.*;

public class Input
{
    private /* synthetic */ float scaleX;
    protected /* synthetic */ ArrayList mouseListeners;
    private /* synthetic */ int pressedX;
    protected /* synthetic */ boolean[] pressed;
    private /* synthetic */ float yoffset;
    private /* synthetic */ int lastMouseX;
    protected /* synthetic */ ArrayList keyListenersToAdd;
    private /* synthetic */ float xoffset;
    private /* synthetic */ boolean paused;
    protected /* synthetic */ ArrayList keyListeners;
    private /* synthetic */ int clickY;
    private static /* synthetic */ boolean controllersInited;
    private static /* synthetic */ ArrayList controllers;
    private /* synthetic */ int keyRepeatInterval;
    private /* synthetic */ long doubleClickTimeout;
    protected /* synthetic */ HashSet allListeners;
    private /* synthetic */ int wheel;
    private /* synthetic */ int pressedY;
    protected /* synthetic */ char[] keys;
    private /* synthetic */ float scaleY;
    protected /* synthetic */ long[] nextRepeat;
    private /* synthetic */ int mouseClickTolerance;
    protected /* synthetic */ ArrayList controllerListeners;
    private /* synthetic */ int clickX;
    private /* synthetic */ boolean[][] controllerPressed;
    private /* synthetic */ boolean keyRepeat;
    private /* synthetic */ boolean displayActive;
    private /* synthetic */ int keyRepeatInitial;
    private /* synthetic */ int doubleClickDelay;
    private /* synthetic */ int lastMouseY;
    private /* synthetic */ boolean[][] controls;
    private /* synthetic */ int clickButton;
    protected /* synthetic */ boolean[] mousePressed;
    protected /* synthetic */ boolean consumed;
    protected /* synthetic */ ArrayList mouseListenersToAdd;
    private /* synthetic */ int height;
    
    public boolean isControllerUp(final int llllllllllllllllllIlIlllIllIIIll) {
        if (llllllllllllllllllIlIlllIllIIIll >= this.getControllerCount()) {
            return false;
        }
        if (llllllllllllllllllIlIlllIllIIIll == -1) {
            for (int llllllllllllllllllIlIlllIllIIlll = 0; llllllllllllllllllIlIlllIllIIlll < Input.controllers.size(); ++llllllllllllllllllIlIlllIllIIlll) {
                if (this.isControllerUp(llllllllllllllllllIlIlllIllIIlll)) {
                    return true;
                }
            }
            return false;
        }
        return Input.controllers.get(llllllllllllllllllIlIlllIllIIIll).getYAxisValue() < -0.5f || Input.controllers.get(llllllllllllllllllIlIlllIllIIIll).getPovY() < -0.5f;
    }
    
    private void fireControlRelease(final int llllllllllllllllllIlIllIllIIlIII, final int llllllllllllllllllIlIllIllIIlIlI) {
        this.consumed = false;
        for (int llllllllllllllllllIlIllIllIIllIl = 0; llllllllllllllllllIlIllIllIIllIl < this.controllerListeners.size(); ++llllllllllllllllllIlIllIllIIllIl) {
            final ControllerListener llllllllllllllllllIlIllIllIIlllI = this.controllerListeners.get(llllllllllllllllllIlIllIllIIllIl);
            if (llllllllllllllllllIlIllIllIIlllI.isAcceptingInput()) {
                switch (llllllllllllllllllIlIllIllIIlIII) {
                    case 0: {
                        llllllllllllllllllIlIllIllIIlllI.controllerLeftReleased(llllllllllllllllllIlIllIllIIlIlI);
                        break;
                    }
                    case 1: {
                        llllllllllllllllllIlIllIllIIlllI.controllerRightReleased(llllllllllllllllllIlIllIllIIlIlI);
                        break;
                    }
                    case 2: {
                        llllllllllllllllllIlIllIllIIlllI.controllerUpReleased(llllllllllllllllllIlIllIllIIlIlI);
                        break;
                    }
                    case 3: {
                        llllllllllllllllllIlIllIllIIlllI.controllerDownReleased(llllllllllllllllllIlIllIllIIlIlI);
                        break;
                    }
                    default: {
                        llllllllllllllllllIlIllIllIIlllI.controllerButtonReleased(llllllllllllllllllIlIllIllIIlIlI, llllllllllllllllllIlIllIllIIlIII - 4 + 1);
                        break;
                    }
                }
                if (this.consumed) {
                    break;
                }
            }
        }
    }
    
    public boolean isControllerLeft(final int llllllllllllllllllIlIlllIlllIlll) {
        if (llllllllllllllllllIlIlllIlllIlll >= this.getControllerCount()) {
            return false;
        }
        if (llllllllllllllllllIlIlllIlllIlll == -1) {
            for (int llllllllllllllllllIlIlllIllllIIl = 0; llllllllllllllllllIlIlllIllllIIl < Input.controllers.size(); ++llllllllllllllllllIlIlllIllllIIl) {
                if (this.isControllerLeft(llllllllllllllllllIlIlllIllllIIl)) {
                    return true;
                }
            }
            return false;
        }
        return Input.controllers.get(llllllllllllllllllIlIlllIlllIlll).getXAxisValue() < -0.5f || Input.controllers.get(llllllllllllllllllIlIlllIlllIlll).getPovX() < -0.5f;
    }
    
    public void disableKeyRepeat() {
        Keyboard.enableRepeatEvents(false);
    }
    
    public void removeAllListeners() {
        this.removeAllKeyListeners();
        this.removeAllMouseListeners();
        this.removeAllControllerListeners();
    }
    
    public void removeListener(final InputListener llllllllllllllllllIlIllllllIllIl) {
        this.removeKeyListener(llllllllllllllllllIlIllllllIllIl);
        this.removeMouseListener(llllllllllllllllllIlIllllllIllIl);
        this.removeControllerListener(llllllllllllllllllIlIllllllIllIl);
    }
    
    static {
        LEFT = 0;
        BUTTON3 = 6;
        KEY_RSHIFT = 54;
        KEY_END = 207;
        BUTTON5 = 8;
        KEY_SLEEP = 223;
        KEY_TAB = 15;
        KEY_1 = 2;
        KEY_V = 47;
        KEY_POWER = 222;
        KEY_W = 17;
        KEY_NUMPAD0 = 82;
        BUTTON2 = 5;
        KEY_R = 19;
        KEY_NUMPAD4 = 75;
        KEY_RALT = 184;
        BUTTON4 = 7;
        KEY_M = 50;
        KEY_H = 35;
        KEY_F14 = 101;
        KEY_SEMICOLON = 39;
        KEY_2 = 3;
        KEY_K = 37;
        KEY_F12 = 88;
        KEY_COLON = 146;
        KEY_F11 = 87;
        KEY_F2 = 60;
        KEY_BACK = 14;
        KEY_LBRACKET = 26;
        KEY_BACKSLASH = 43;
        KEY_DELETE = 211;
        MOUSE_MIDDLE_BUTTON = 2;
        KEY_F7 = 65;
        KEY_NUMPAD5 = 76;
        KEY_7 = 8;
        KEY_NUMPAD3 = 81;
        KEY_X = 45;
        KEY_4 = 5;
        KEY_PAUSE = 197;
        KEY_T = 20;
        KEY_F3 = 61;
        KEY_AT = 145;
        BUTTON10 = 13;
        KEY_F15 = 102;
        KEY_HOME = 199;
        KEY_F5 = 63;
        KEY_SUBTRACT = 74;
        BUTTON8 = 11;
        UP = 2;
        KEY_CAPITAL = 58;
        MOUSE_LEFT_BUTTON = 0;
        KEY_APOSTROPHE = 40;
        KEY_0 = 11;
        KEY_C = 46;
        KEY_ESCAPE = 1;
        KEY_Q = 16;
        KEY_SCROLL = 70;
        KEY_N = 49;
        KEY_STOP = 149;
        KEY_INSERT = 210;
        KEY_NEXT = 209;
        KEY_O = 24;
        KEY_RBRACKET = 27;
        KEY_KANJI = 148;
        KEY_RCONTROL = 157;
        KEY_LALT = 56;
        KEY_NUMPADEQUALS = 141;
        RIGHT = 1;
        KEY_Y = 21;
        KEY_5 = 6;
        KEY_NUMPAD2 = 80;
        KEY_DIVIDE = 181;
        KEY_ADD = 78;
        KEY_SPACE = 57;
        KEY_8 = 9;
        KEY_EQUALS = 13;
        KEY_YEN = 125;
        KEY_G = 34;
        KEY_RMENU = 184;
        KEY_SLASH = 53;
        KEY_F1 = 59;
        KEY_NUMPAD7 = 71;
        KEY_COMMA = 51;
        KEY_3 = 4;
        BUTTON9 = 12;
        KEY_RETURN = 28;
        KEY_NUMPADENTER = 156;
        KEY_LWIN = 219;
        KEY_F13 = 100;
        KEY_6 = 7;
        KEY_RWIN = 220;
        KEY_A = 30;
        KEY_NUMPADCOMMA = 179;
        KEY_UP = 200;
        KEY_P = 25;
        KEY_D = 32;
        MAX_BUTTONS = 100;
        KEY_GRAVE = 41;
        KEY_U = 22;
        BUTTON1 = 4;
        KEY_F4 = 62;
        KEY_KANA = 112;
        KEY_NUMPAD9 = 73;
        KEY_UNLABELED = 151;
        KEY_MINUS = 12;
        KEY_F10 = 68;
        KEY_NUMPAD1 = 79;
        KEY_F8 = 66;
        KEY_ENTER = 28;
        KEY_CONVERT = 121;
        KEY_F = 33;
        DOWN = 3;
        KEY_CIRCUMFLEX = 144;
        KEY_S = 31;
        ANY_CONTROLLER = -1;
        KEY_E = 18;
        KEY_UNDERLINE = 147;
        KEY_LSHIFT = 42;
        KEY_NUMLOCK = 69;
        KEY_L = 38;
        BUTTON6 = 9;
        KEY_I = 23;
        KEY_PERIOD = 52;
        KEY_B = 48;
        KEY_LMENU = 56;
        KEY_F6 = 64;
        KEY_J = 36;
        KEY_RIGHT = 205;
        KEY_9 = 10;
        KEY_DOWN = 208;
        KEY_LCONTROL = 29;
        MOUSE_RIGHT_BUTTON = 1;
        KEY_NOCONVERT = 123;
        KEY_DECIMAL = 83;
        KEY_LEFT = 203;
        KEY_AX = 150;
        KEY_Z = 44;
        KEY_NUMPAD6 = 77;
        BUTTON7 = 10;
        KEY_SYSRQ = 183;
        KEY_PRIOR = 201;
        KEY_F9 = 67;
        KEY_APPS = 221;
        KEY_MULTIPLY = 55;
        KEY_NUMPAD8 = 72;
        Input.controllersInited = false;
        Input.controllers = new ArrayList();
    }
    
    public void initControllers() throws SlickException {
        if (Input.controllersInited) {
            return;
        }
        Input.controllersInited = true;
        try {
            Controllers.create();
            for (int llllllllllllllllllIlIlllIIllIlII = Controllers.getControllerCount(), llllllllllllllllllIlIlllIIllIllI = 0; llllllllllllllllllIlIlllIIllIllI < llllllllllllllllllIlIlllIIllIlII; ++llllllllllllllllllIlIlllIIllIllI) {
                final Controller llllllllllllllllllIlIlllIIllIlll = Controllers.getController(llllllllllllllllllIlIlllIIllIllI);
                if (llllllllllllllllllIlIlllIIllIlll.getButtonCount() >= 3 && llllllllllllllllllIlIlllIIllIlll.getButtonCount() < 100) {
                    Input.controllers.add(llllllllllllllllllIlIlllIIllIlll);
                }
            }
            Log.info(String.valueOf(new StringBuilder().append("Found ").append(Input.controllers.size()).append(" controllers")));
            for (int llllllllllllllllllIlIlllIIllIlIl = 0; llllllllllllllllllIlIlllIIllIlIl < Input.controllers.size(); ++llllllllllllllllllIlIlllIIllIlIl) {
                Log.info(String.valueOf(new StringBuilder().append(llllllllllllllllllIlIlllIIllIlIl).append(" : ").append(Input.controllers.get(llllllllllllllllllIlIlllIIllIlIl).getName())));
            }
        }
        catch (LWJGLException llllllllllllllllllIlIlllIIllIIll) {
            if (llllllllllllllllllIlIlllIIllIIll.getCause() instanceof ClassNotFoundException) {
                throw new SlickException("Unable to create controller - no jinput found - add jinput.jar to your classpath");
            }
            throw new SlickException("Unable to create controllers");
        }
        catch (NoClassDefFoundError noClassDefFoundError) {}
    }
    
    public void considerDoubleClick(final int llllllllllllllllllIlIlllIIIllIll, final int llllllllllllllllllIlIlllIIIllllI, final int llllllllllllllllllIlIlllIIIllIIl) {
        if (this.doubleClickTimeout == 0L) {
            this.clickX = llllllllllllllllllIlIlllIIIllllI;
            this.clickY = llllllllllllllllllIlIlllIIIllIIl;
            this.clickButton = llllllllllllllllllIlIlllIIIllIll;
            this.doubleClickTimeout = System.currentTimeMillis() + this.doubleClickDelay;
            this.fireMouseClicked(llllllllllllllllllIlIlllIIIllIll, llllllllllllllllllIlIlllIIIllllI, llllllllllllllllllIlIlllIIIllIIl, 1);
        }
        else if (this.clickButton == llllllllllllllllllIlIlllIIIllIll && System.currentTimeMillis() < this.doubleClickTimeout) {
            this.fireMouseClicked(llllllllllllllllllIlIlllIIIllIll, llllllllllllllllllIlIlllIIIllllI, llllllllllllllllllIlIlllIIIllIIl, 2);
            this.doubleClickTimeout = 0L;
        }
    }
    
    public Input(final int llllllllllllllllllIllIIIIlIIllII) {
        this.mousePressed = new boolean[10];
        this.controllerPressed = new boolean[100][100];
        this.keys = new char[1024];
        this.pressed = new boolean[1024];
        this.nextRepeat = new long[1024];
        this.controls = new boolean[10][110];
        this.consumed = false;
        this.allListeners = new HashSet();
        this.keyListeners = new ArrayList();
        this.keyListenersToAdd = new ArrayList();
        this.mouseListeners = new ArrayList();
        this.mouseListenersToAdd = new ArrayList();
        this.controllerListeners = new ArrayList();
        this.displayActive = true;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.xoffset = 0.0f;
        this.yoffset = 0.0f;
        this.doubleClickDelay = 250;
        this.doubleClickTimeout = 0L;
        this.pressedX = -1;
        this.pressedY = -1;
        this.mouseClickTolerance = 5;
        this.init(llllllllllllllllllIllIIIIlIIllII);
    }
    
    public int getControllerCount() {
        try {
            this.initControllers();
        }
        catch (SlickException llllllllllllllllllIlIllllIIlIIlI) {
            throw new RuntimeException("Failed to initialise controllers");
        }
        return Input.controllers.size();
    }
    
    public void consumeEvent() {
        this.consumed = true;
    }
    
    public static void disableControllers() {
        Input.controllersInited = true;
    }
    
    public void removeKeyListener(final KeyListener llllllllllllllllllIlIllllllIIlll) {
        this.keyListeners.remove(llllllllllllllllllIlIllllllIIlll);
        if (!this.mouseListeners.contains(llllllllllllllllllIlIllllllIIlll) && !this.controllerListeners.contains(llllllllllllllllllIlIllllllIIlll)) {
            this.allListeners.remove(llllllllllllllllllIlIllllllIIlll);
        }
    }
    
    public void addMouseListener(final MouseListener llllllllllllllllllIllIIIIIIlIIll) {
        this.mouseListenersToAdd.add(llllllllllllllllllIllIIIIIIlIIll);
    }
    
    public int getMouseY() {
        return (int)((this.height - Mouse.getY()) * this.scaleY + this.yoffset);
    }
    
    public void setOffset(final float llllllllllllllllllIllIIIIIlIllIl, final float llllllllllllllllllIllIIIIIlIllll) {
        this.xoffset = llllllllllllllllllIllIIIIIlIllIl;
        this.yoffset = llllllllllllllllllIllIIIIIlIllll;
    }
    
    public void pause() {
        this.paused = true;
        this.clearKeyPressedRecord();
        this.clearMousePressedRecord();
        this.clearControlPressedRecord();
    }
    
    public void resetInputTransform() {
        this.setOffset(0.0f, 0.0f);
        this.setScale(1.0f, 1.0f);
    }
    
    public void addKeyListener(final KeyListener llllllllllllllllllIllIIIIIIlllll) {
        this.keyListenersToAdd.add(llllllllllllllllllIllIIIIIIlllll);
    }
    
    public void removeMouseListener(final MouseListener llllllllllllllllllIlIlllllIlllIl) {
        this.mouseListeners.remove(llllllllllllllllllIlIlllllIlllIl);
        if (!this.controllerListeners.contains(llllllllllllllllllIlIlllllIlllIl) && !this.keyListeners.contains(llllllllllllllllllIlIlllllIlllIl)) {
            this.allListeners.remove(llllllllllllllllllIlIlllllIlllIl);
        }
    }
    
    public void poll(final int llllllllllllllllllIlIllIllllIIll, final int llllllllllllllllllIlIllIllllIIlI) {
        if (this.paused) {
            this.clearControlPressedRecord();
            this.clearKeyPressedRecord();
            this.clearMousePressedRecord();
            while (Keyboard.next()) {}
            while (Mouse.next()) {}
            return;
        }
        if (!Display.isActive()) {
            this.clearControlPressedRecord();
            this.clearKeyPressedRecord();
            this.clearMousePressedRecord();
        }
        for (int llllllllllllllllllIlIlllIIIlIIIl = 0; llllllllllllllllllIlIlllIIIlIIIl < this.keyListenersToAdd.size(); ++llllllllllllllllllIlIlllIIIlIIIl) {
            this.addKeyListenerImpl(this.keyListenersToAdd.get(llllllllllllllllllIlIlllIIIlIIIl));
        }
        this.keyListenersToAdd.clear();
        for (int llllllllllllllllllIlIlllIIIlIIII = 0; llllllllllllllllllIlIlllIIIlIIII < this.mouseListenersToAdd.size(); ++llllllllllllllllllIlIlllIIIlIIII) {
            this.addMouseListenerImpl(this.mouseListenersToAdd.get(llllllllllllllllllIlIlllIIIlIIII));
        }
        this.mouseListenersToAdd.clear();
        if (this.doubleClickTimeout != 0L && System.currentTimeMillis() > this.doubleClickTimeout) {
            this.doubleClickTimeout = 0L;
        }
        this.height = llllllllllllllllllIlIllIllllIIlI;
        for (final ControlledInputReciever llllllllllllllllllIlIlllIIIIllll : this.allListeners) {
            llllllllllllllllllIlIlllIIIIllll.inputStarted();
        }
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                final int llllllllllllllllllIlIlllIIIIllII = this.resolveEventKey(Keyboard.getEventKey(), Keyboard.getEventCharacter());
                this.keys[llllllllllllllllllIlIlllIIIIllII] = Keyboard.getEventCharacter();
                this.pressed[llllllllllllllllllIlIlllIIIIllII] = true;
                this.nextRepeat[llllllllllllllllllIlIlllIIIIllII] = System.currentTimeMillis() + this.keyRepeatInitial;
                this.consumed = false;
                for (int llllllllllllllllllIlIlllIIIIllIl = 0; llllllllllllllllllIlIlllIIIIllIl < this.keyListeners.size(); ++llllllllllllllllllIlIlllIIIIllIl) {
                    final KeyListener llllllllllllllllllIlIlllIIIIlllI = this.keyListeners.get(llllllllllllllllllIlIlllIIIIllIl);
                    if (llllllllllllllllllIlIlllIIIIlllI.isAcceptingInput()) {
                        llllllllllllllllllIlIlllIIIIlllI.keyPressed(llllllllllllllllllIlIlllIIIIllII, Keyboard.getEventCharacter());
                        if (this.consumed) {
                            break;
                        }
                    }
                }
            }
            else {
                final int llllllllllllllllllIlIlllIIIIlIIl = this.resolveEventKey(Keyboard.getEventKey(), Keyboard.getEventCharacter());
                this.nextRepeat[llllllllllllllllllIlIlllIIIIlIIl] = 0L;
                this.consumed = false;
                for (int llllllllllllllllllIlIlllIIIIlIlI = 0; llllllllllllllllllIlIlllIIIIlIlI < this.keyListeners.size(); ++llllllllllllllllllIlIlllIIIIlIlI) {
                    final KeyListener llllllllllllllllllIlIlllIIIIlIll = this.keyListeners.get(llllllllllllllllllIlIlllIIIIlIlI);
                    if (llllllllllllllllllIlIlllIIIIlIll.isAcceptingInput()) {
                        llllllllllllllllllIlIlllIIIIlIll.keyReleased(llllllllllllllllllIlIlllIIIIlIIl, this.keys[llllllllllllllllllIlIlllIIIIlIIl]);
                        if (this.consumed) {
                            break;
                        }
                    }
                }
            }
        }
        while (Mouse.next()) {
            if (Mouse.getEventButton() >= 0) {
                if (Mouse.getEventButtonState()) {
                    this.consumed = false;
                    this.mousePressed[Mouse.getEventButton()] = true;
                    this.pressedX = (int)(this.xoffset + Mouse.getEventX() * this.scaleX);
                    this.pressedY = (int)(this.yoffset + (llllllllllllllllllIlIllIllllIIlI - Mouse.getEventY()) * this.scaleY);
                    for (int llllllllllllllllllIlIlllIIIIIlll = 0; llllllllllllllllllIlIlllIIIIIlll < this.mouseListeners.size(); ++llllllllllllllllllIlIlllIIIIIlll) {
                        final MouseListener llllllllllllllllllIlIlllIIIIlIII = this.mouseListeners.get(llllllllllllllllllIlIlllIIIIIlll);
                        if (llllllllllllllllllIlIlllIIIIlIII.isAcceptingInput()) {
                            llllllllllllllllllIlIlllIIIIlIII.mousePressed(Mouse.getEventButton(), this.pressedX, this.pressedY);
                            if (this.consumed) {
                                break;
                            }
                        }
                    }
                }
                else {
                    this.consumed = false;
                    this.mousePressed[Mouse.getEventButton()] = false;
                    final int llllllllllllllllllIlIlllIIIIIlII = (int)(this.xoffset + Mouse.getEventX() * this.scaleX);
                    final int llllllllllllllllllIlIlllIIIIIIll = (int)(this.yoffset + (llllllllllllllllllIlIllIllllIIlI - Mouse.getEventY()) * this.scaleY);
                    if (this.pressedX != -1 && this.pressedY != -1 && Math.abs(this.pressedX - llllllllllllllllllIlIlllIIIIIlII) < this.mouseClickTolerance && Math.abs(this.pressedY - llllllllllllllllllIlIlllIIIIIIll) < this.mouseClickTolerance) {
                        this.considerDoubleClick(Mouse.getEventButton(), llllllllllllllllllIlIlllIIIIIlII, llllllllllllllllllIlIlllIIIIIIll);
                        final int n = -1;
                        this.pressedY = n;
                        this.pressedX = n;
                    }
                    for (int llllllllllllllllllIlIlllIIIIIlIl = 0; llllllllllllllllllIlIlllIIIIIlIl < this.mouseListeners.size(); ++llllllllllllllllllIlIlllIIIIIlIl) {
                        final MouseListener llllllllllllllllllIlIlllIIIIIllI = this.mouseListeners.get(llllllllllllllllllIlIlllIIIIIlIl);
                        if (llllllllllllllllllIlIlllIIIIIllI.isAcceptingInput()) {
                            llllllllllllllllllIlIlllIIIIIllI.mouseReleased(Mouse.getEventButton(), llllllllllllllllllIlIlllIIIIIlII, llllllllllllllllllIlIlllIIIIIIll);
                            if (this.consumed) {
                                break;
                            }
                        }
                    }
                }
            }
            else {
                if (Mouse.isGrabbed() && this.displayActive && (Mouse.getEventDX() != 0 || Mouse.getEventDY() != 0)) {
                    this.consumed = false;
                    for (int llllllllllllllllllIlIlllIIIIIIIl = 0; llllllllllllllllllIlIlllIIIIIIIl < this.mouseListeners.size(); ++llllllllllllllllllIlIlllIIIIIIIl) {
                        final MouseListener llllllllllllllllllIlIlllIIIIIIlI = this.mouseListeners.get(llllllllllllllllllIlIlllIIIIIIIl);
                        if (llllllllllllllllllIlIlllIIIIIIlI.isAcceptingInput()) {
                            if (this.anyMouseDown()) {
                                llllllllllllllllllIlIlllIIIIIIlI.mouseDragged(0, 0, Mouse.getEventDX(), -Mouse.getEventDY());
                            }
                            else {
                                llllllllllllllllllIlIlllIIIIIIlI.mouseMoved(0, 0, Mouse.getEventDX(), -Mouse.getEventDY());
                            }
                            if (this.consumed) {
                                break;
                            }
                        }
                    }
                }
                final int llllllllllllllllllIlIllIlllllllI = Mouse.getEventDWheel();
                this.wheel += llllllllllllllllllIlIllIlllllllI;
                if (llllllllllllllllllIlIllIlllllllI == 0) {
                    continue;
                }
                this.consumed = false;
                for (int llllllllllllllllllIlIllIllllllll = 0; llllllllllllllllllIlIllIllllllll < this.mouseListeners.size(); ++llllllllllllllllllIlIllIllllllll) {
                    final MouseListener llllllllllllllllllIlIlllIIIIIIII = this.mouseListeners.get(llllllllllllllllllIlIllIllllllll);
                    if (llllllllllllllllllIlIlllIIIIIIII.isAcceptingInput()) {
                        llllllllllllllllllIlIlllIIIIIIII.mouseWheelMoved(llllllllllllllllllIlIllIlllllllI);
                        if (this.consumed) {
                            break;
                        }
                    }
                }
            }
        }
        if (!this.displayActive || Mouse.isGrabbed()) {
            this.lastMouseX = this.getMouseX();
            this.lastMouseY = this.getMouseY();
        }
        else if (this.lastMouseX != this.getMouseX() || this.lastMouseY != this.getMouseY()) {
            this.consumed = false;
            for (int llllllllllllllllllIlIllIllllllII = 0; llllllllllllllllllIlIllIllllllII < this.mouseListeners.size(); ++llllllllllllllllllIlIllIllllllII) {
                final MouseListener llllllllllllllllllIlIllIllllllIl = this.mouseListeners.get(llllllllllllllllllIlIllIllllllII);
                if (llllllllllllllllllIlIllIllllllIl.isAcceptingInput()) {
                    if (this.anyMouseDown()) {
                        llllllllllllllllllIlIllIllllllIl.mouseDragged(this.lastMouseX, this.lastMouseY, this.getMouseX(), this.getMouseY());
                    }
                    else {
                        llllllllllllllllllIlIllIllllllIl.mouseMoved(this.lastMouseX, this.lastMouseY, this.getMouseX(), this.getMouseY());
                    }
                    if (this.consumed) {
                        break;
                    }
                }
            }
            this.lastMouseX = this.getMouseX();
            this.lastMouseY = this.getMouseY();
        }
        if (Input.controllersInited) {
            for (int llllllllllllllllllIlIllIlllllIIl = 0; llllllllllllllllllIlIllIlllllIIl < this.getControllerCount(); ++llllllllllllllllllIlIllIlllllIIl) {
                int llllllllllllllllllIlIllIlllllIlI = Input.controllers.get(llllllllllllllllllIlIllIlllllIIl).getButtonCount() + 3;
                llllllllllllllllllIlIllIlllllIlI = Math.min(llllllllllllllllllIlIllIlllllIlI, 24);
                for (int llllllllllllllllllIlIllIlllllIll = 0; llllllllllllllllllIlIllIlllllIll <= llllllllllllllllllIlIllIlllllIlI; ++llllllllllllllllllIlIllIlllllIll) {
                    if (this.controls[llllllllllllllllllIlIllIlllllIIl][llllllllllllllllllIlIllIlllllIll] && !this.isControlDwn(llllllllllllllllllIlIllIlllllIll, llllllllllllllllllIlIllIlllllIIl)) {
                        this.controls[llllllllllllllllllIlIllIlllllIIl][llllllllllllllllllIlIllIlllllIll] = false;
                        this.fireControlRelease(llllllllllllllllllIlIllIlllllIll, llllllllllllllllllIlIllIlllllIIl);
                    }
                    else if (!this.controls[llllllllllllllllllIlIllIlllllIIl][llllllllllllllllllIlIllIlllllIll] && this.isControlDwn(llllllllllllllllllIlIllIlllllIll, llllllllllllllllllIlIllIlllllIIl)) {
                        this.controllerPressed[llllllllllllllllllIlIllIlllllIIl][llllllllllllllllllIlIllIlllllIll] = true;
                        this.controls[llllllllllllllllllIlIllIlllllIIl][llllllllllllllllllIlIllIlllllIll] = true;
                        this.fireControlPress(llllllllllllllllllIlIllIlllllIll, llllllllllllllllllIlIllIlllllIIl);
                    }
                }
            }
        }
        if (this.keyRepeat) {
            for (int llllllllllllllllllIlIllIllllIllI = 0; llllllllllllllllllIlIllIllllIllI < 1024; ++llllllllllllllllllIlIllIllllIllI) {
                if (this.pressed[llllllllllllllllllIlIllIllllIllI] && this.nextRepeat[llllllllllllllllllIlIllIllllIllI] != 0L && System.currentTimeMillis() > this.nextRepeat[llllllllllllllllllIlIllIllllIllI]) {
                    this.nextRepeat[llllllllllllllllllIlIllIllllIllI] = System.currentTimeMillis() + this.keyRepeatInterval;
                    this.consumed = false;
                    for (int llllllllllllllllllIlIllIllllIlll = 0; llllllllllllllllllIlIllIllllIlll < this.keyListeners.size(); ++llllllllllllllllllIlIllIllllIlll) {
                        final KeyListener llllllllllllllllllIlIllIlllllIII = this.keyListeners.get(llllllllllllllllllIlIllIllllIlll);
                        if (llllllllllllllllllIlIllIlllllIII.isAcceptingInput()) {
                            llllllllllllllllllIlIllIlllllIII.keyPressed(llllllllllllllllllIlIllIllllIllI, this.keys[llllllllllllllllllIlIllIllllIllI]);
                            if (this.consumed) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (final ControlledInputReciever llllllllllllllllllIlIllIllllIlIl : this.allListeners) {
            llllllllllllllllllIlIllIllllIlIl.inputEnded();
        }
        if (Display.isCreated()) {
            this.displayActive = Display.isActive();
        }
    }
    
    public boolean isButtonPressed(final int llllllllllllllllllIlIlllIlIlIIlI, final int llllllllllllllllllIlIlllIlIIlllI) {
        if (llllllllllllllllllIlIlllIlIIlllI >= this.getControllerCount()) {
            return false;
        }
        if (llllllllllllllllllIlIlllIlIIlllI == -1) {
            for (int llllllllllllllllllIlIlllIlIlIlII = 0; llllllllllllllllllIlIlllIlIlIlII < Input.controllers.size(); ++llllllllllllllllllIlIlllIlIlIlII) {
                if (this.isButtonPressed(llllllllllllllllllIlIlllIlIlIIlI, llllllllllllllllllIlIlllIlIlIlII)) {
                    return true;
                }
            }
            return false;
        }
        return Input.controllers.get(llllllllllllllllllIlIlllIlIIlllI).isButtonPressed(llllllllllllllllllIlIlllIlIlIIlI);
    }
    
    public boolean isControlPressed(final int llllllllllllllllllIlIllllIlllIII, final int llllllllllllllllllIlIllllIllIlll) {
        if (this.controllerPressed[llllllllllllllllllIlIllllIllIlll][llllllllllllllllllIlIllllIlllIII]) {
            this.controllerPressed[llllllllllllllllllIlIllllIllIlll][llllllllllllllllllIlIllllIlllIII] = false;
            return true;
        }
        return false;
    }
    
    public boolean isControllerRight(final int llllllllllllllllllIlIlllIllIlllI) {
        if (llllllllllllllllllIlIlllIllIlllI >= this.getControllerCount()) {
            return false;
        }
        if (llllllllllllllllllIlIlllIllIlllI == -1) {
            for (int llllllllllllllllllIlIlllIlllIIII = 0; llllllllllllllllllIlIlllIlllIIII < Input.controllers.size(); ++llllllllllllllllllIlIlllIlllIIII) {
                if (this.isControllerRight(llllllllllllllllllIlIlllIlllIIII)) {
                    return true;
                }
            }
            return false;
        }
        return Input.controllers.get(llllllllllllllllllIlIlllIllIlllI).getXAxisValue() > 0.5f || Input.controllers.get(llllllllllllllllllIlIlllIllIlllI).getPovX() > 0.5f;
    }
    
    private void fireControlPress(final int llllllllllllllllllIlIllIllIllIlI, final int llllllllllllllllllIlIllIllIllIIl) {
        this.consumed = false;
        for (int llllllllllllllllllIlIllIllIlllII = 0; llllllllllllllllllIlIllIllIlllII < this.controllerListeners.size(); ++llllllllllllllllllIlIllIllIlllII) {
            final ControllerListener llllllllllllllllllIlIllIllIlllIl = this.controllerListeners.get(llllllllllllllllllIlIllIllIlllII);
            if (llllllllllllllllllIlIllIllIlllIl.isAcceptingInput()) {
                switch (llllllllllllllllllIlIllIllIllIlI) {
                    case 0: {
                        llllllllllllllllllIlIllIllIlllIl.controllerLeftPressed(llllllllllllllllllIlIllIllIllIIl);
                        break;
                    }
                    case 1: {
                        llllllllllllllllllIlIllIllIlllIl.controllerRightPressed(llllllllllllllllllIlIllIllIllIIl);
                        break;
                    }
                    case 2: {
                        llllllllllllllllllIlIllIllIlllIl.controllerUpPressed(llllllllllllllllllIlIllIllIllIIl);
                        break;
                    }
                    case 3: {
                        llllllllllllllllllIlIllIllIlllIl.controllerDownPressed(llllllllllllllllllIlIllIllIllIIl);
                        break;
                    }
                    default: {
                        llllllllllllllllllIlIllIllIlllIl.controllerButtonPressed(llllllllllllllllllIlIllIllIllIIl, llllllllllllllllllIlIllIllIllIlI - 4 + 1);
                        break;
                    }
                }
                if (this.consumed) {
                    break;
                }
            }
        }
    }
    
    public void removeAllControllerListeners() {
        this.allListeners.removeAll(this.controllerListeners);
        this.controllerListeners.clear();
    }
    
    public void clearMousePressedRecord() {
        Arrays.fill(this.mousePressed, false);
    }
    
    private void addKeyListenerImpl(final KeyListener llllllllllllllllllIllIIIIIIllIIl) {
        if (this.keyListeners.contains(llllllllllllllllllIllIIIIIIllIIl)) {
            return;
        }
        this.keyListeners.add(llllllllllllllllllIllIIIIIIllIIl);
        this.allListeners.add(llllllllllllllllllIllIIIIIIllIIl);
    }
    
    public boolean isMousePressed(final int llllllllllllllllllIlIlllllIIlIII) {
        if (this.mousePressed[llllllllllllllllllIlIlllllIIlIII]) {
            this.mousePressed[llllllllllllllllllIlIlllllIIlIII] = false;
            return true;
        }
        return false;
    }
    
    public boolean isKeyRepeatEnabled() {
        return Keyboard.areRepeatEventsEnabled();
    }
    
    public boolean isMouseButtonDown(final int llllllllllllllllllIlIllllIIllIIl) {
        return Mouse.isButtonDown(llllllllllllllllllIlIllllIIllIIl);
    }
    
    public void clearKeyPressedRecord() {
        Arrays.fill(this.pressed, false);
    }
    
    private boolean isControlDwn(final int llllllllllllllllllIlIllIlIllllIl, final int llllllllllllllllllIlIllIlIllllII) {
        switch (llllllllllllllllllIlIllIlIllllIl) {
            case 0: {
                return this.isControllerLeft(llllllllllllllllllIlIllIlIllllII);
            }
            case 1: {
                return this.isControllerRight(llllllllllllllllllIlIllIlIllllII);
            }
            case 2: {
                return this.isControllerUp(llllllllllllllllllIlIllIlIllllII);
            }
            case 3: {
                return this.isControllerDown(llllllllllllllllllIlIllIlIllllII);
            }
            default: {
                if (llllllllllllllllllIlIllIlIllllIl >= 4) {
                    return this.isButtonPressed(llllllllllllllllllIlIllIlIllllIl - 4, llllllllllllllllllIlIllIlIllllII);
                }
                throw new RuntimeException("Unknown control index");
            }
        }
    }
    
    public boolean isButton3Pressed(final int llllllllllllllllllIlIlllIIllllIl) {
        return this.isButtonPressed(2, llllllllllllllllllIlIlllIIllllIl);
    }
    
    void init(final int llllllllllllllllllIlIlllllIlIlll) {
        this.height = llllllllllllllllllIlIlllllIlIlll;
        this.lastMouseX = this.getMouseX();
        this.lastMouseY = this.getMouseY();
    }
    
    public void clearControlPressedRecord() {
        for (int llllllllllllllllllIlIllllIllIlII = 0; llllllllllllllllllIlIllllIllIlII < Input.controllers.size(); ++llllllllllllllllllIlIllllIllIlII) {
            Arrays.fill(this.controllerPressed[llllllllllllllllllIlIllllIllIlII], false);
        }
    }
    
    public void removeAllKeyListeners() {
        this.allListeners.removeAll(this.keyListeners);
        this.keyListeners.clear();
    }
    
    public void removeControllerListener(final ControllerListener llllllllllllllllllIlIllllllIIIll) {
        this.controllerListeners.remove(llllllllllllllllllIlIllllllIIIll);
        if (!this.mouseListeners.contains(llllllllllllllllllIlIllllllIIIll) && !this.keyListeners.contains(llllllllllllllllllIlIllllllIIIll)) {
            this.allListeners.remove(llllllllllllllllllIlIllllllIIIll);
        }
    }
    
    public int getAxisCount(final int llllllllllllllllllIlIllllIIIlIll) {
        return Input.controllers.get(llllllllllllllllllIlIllllIIIlIll).getAxisCount();
    }
    
    public void enableKeyRepeat() {
        Keyboard.enableRepeatEvents(true);
    }
    
    public boolean isKeyDown(final int llllllllllllllllllIlIllllIlIIlll) {
        return Keyboard.isKeyDown(llllllllllllllllllIlIllllIlIIlll);
    }
    
    public void setMouseClickTolerance(final int llllllllllllllllllIllIIIIIlllllI) {
        this.mouseClickTolerance = llllllllllllllllllIllIIIIIlllllI;
    }
    
    public void addControllerListener(final ControllerListener llllllllllllllllllIllIIIIIIIIlIl) {
        if (this.controllerListeners.contains(llllllllllllllllllIllIIIIIIIIlIl)) {
            return;
        }
        this.controllerListeners.add(llllllllllllllllllIllIIIIIIIIlIl);
        this.allListeners.add(llllllllllllllllllIllIIIIIIIIlIl);
    }
    
    public float getAxisValue(final int llllllllllllllllllIlIllllIIIIlll, final int llllllllllllllllllIlIllllIIIIlII) {
        return Input.controllers.get(llllllllllllllllllIlIllllIIIIlll).getAxisValue(llllllllllllllllllIlIllllIIIIlII);
    }
    
    public boolean isControlPressed(final int llllllllllllllllllIlIlllllIIIIlI) {
        return this.isControlPressed(llllllllllllllllllIlIlllllIIIIlI, 0);
    }
    
    private void fireMouseClicked(final int llllllllllllllllllIlIllIlIIllIlI, final int llllllllllllllllllIlIllIlIlIIIlI, final int llllllllllllllllllIlIllIlIlIIIII, final int llllllllllllllllllIlIllIlIIllllI) {
        this.consumed = false;
        for (int llllllllllllllllllIlIllIlIlIIlIl = 0; llllllllllllllllllIlIllIlIlIIlIl < this.mouseListeners.size(); ++llllllllllllllllllIlIllIlIlIIlIl) {
            final MouseListener llllllllllllllllllIlIllIlIlIIllI = this.mouseListeners.get(llllllllllllllllllIlIllIlIlIIlIl);
            if (llllllllllllllllllIlIllIlIlIIllI.isAcceptingInput()) {
                llllllllllllllllllIlIllIlIlIIllI.mouseClicked(llllllllllllllllllIlIllIlIIllIlI, llllllllllllllllllIlIllIlIlIIIlI, llllllllllllllllllIlIllIlIlIIIII, llllllllllllllllllIlIllIlIIllllI);
                if (this.consumed) {
                    break;
                }
            }
        }
    }
    
    public void setScale(final float llllllllllllllllllIllIIIIIlllIIl, final float llllllllllllllllllIllIIIIIlllIII) {
        this.scaleX = llllllllllllllllllIllIIIIIlllIIl;
        this.scaleY = llllllllllllllllllIllIIIIIlllIII;
    }
    
    private boolean anyMouseDown() {
        for (int llllllllllllllllllIlIllllIIlIlll = 0; llllllllllllllllllIlIllllIIlIlll < 3; ++llllllllllllllllllIlIllllIIlIlll) {
            if (Mouse.isButtonDown(llllllllllllllllllIlIllllIIlIlll)) {
                return true;
            }
        }
        return false;
    }
    
    private int resolveEventKey(final int llllllllllllllllllIlIlllIIlIlIII, final char llllllllllllllllllIlIlllIIlIIlIl) {
        if (llllllllllllllllllIlIlllIIlIIlIl == '=' || llllllllllllllllllIlIlllIIlIlIII == 0) {
            return 13;
        }
        return llllllllllllllllllIlIlllIIlIlIII;
    }
    
    public boolean isKeyPressed(final int llllllllllllllllllIlIlllllIIlllI) {
        if (this.pressed[llllllllllllllllllIlIlllllIIlllI]) {
            this.pressed[llllllllllllllllllIlIlllllIIlllI] = false;
            return true;
        }
        return false;
    }
    
    public void addListener(final InputListener llllllllllllllllllIllIIIIIlIIIll) {
        this.addKeyListener(llllllllllllllllllIllIIIIIlIIIll);
        this.addMouseListener(llllllllllllllllllIllIIIIIlIIIll);
        this.addControllerListener(llllllllllllllllllIllIIIIIlIIIll);
    }
    
    public boolean isButton2Pressed(final int llllllllllllllllllIlIlllIlIIIIll) {
        return this.isButtonPressed(1, llllllllllllllllllIlIlllIlIIIIll);
    }
    
    public void resume() {
        this.paused = false;
    }
    
    public void setDoubleClickInterval(final int llllllllllllllllllIllIIIIlIIIllI) {
        this.doubleClickDelay = llllllllllllllllllIllIIIIlIIIllI;
    }
    
    public static String getKeyName(final int llllllllllllllllllIlIlllllIlIIll) {
        return Keyboard.getKeyName(llllllllllllllllllIlIlllllIlIIll);
    }
    
    public boolean isButton1Pressed(final int llllllllllllllllllIlIlllIlIIIlll) {
        return this.isButtonPressed(0, llllllllllllllllllIlIlllIlIIIlll);
    }
    
    public int getAbsoluteMouseX() {
        return Mouse.getX();
    }
    
    public void addPrimaryListener(final InputListener llllllllllllllllllIlIlllllllIlIl) {
        this.removeListener(llllllllllllllllllIlIlllllllIlIl);
        this.keyListeners.add(0, llllllllllllllllllIlIlllllllIlIl);
        this.mouseListeners.add(0, llllllllllllllllllIlIlllllllIlIl);
        this.controllerListeners.add(0, llllllllllllllllllIlIlllllllIlIl);
        this.allListeners.add(llllllllllllllllllIlIlllllllIlIl);
    }
    
    public boolean isControllerDown(final int llllllllllllllllllIlIlllIlIlllII) {
        if (llllllllllllllllllIlIlllIlIlllII >= this.getControllerCount()) {
            return false;
        }
        if (llllllllllllllllllIlIlllIlIlllII == -1) {
            for (int llllllllllllllllllIlIlllIlIllllI = 0; llllllllllllllllllIlIlllIlIllllI < Input.controllers.size(); ++llllllllllllllllllIlIlllIlIllllI) {
                if (this.isControllerDown(llllllllllllllllllIlIlllIlIllllI)) {
                    return true;
                }
            }
            return false;
        }
        return Input.controllers.get(llllllllllllllllllIlIlllIlIlllII).getYAxisValue() > 0.5f || Input.controllers.get(llllllllllllllllllIlIlllIlIlllII).getPovY() > 0.5f;
    }
    
    public void removeAllMouseListeners() {
        this.allListeners.removeAll(this.mouseListeners);
        this.mouseListeners.clear();
    }
    
    public int getAbsoluteMouseY() {
        return this.height - Mouse.getY();
    }
    
    private void addMouseListenerImpl(final MouseListener llllllllllllllllllIllIIIIIIIllIl) {
        if (this.mouseListeners.contains(llllllllllllllllllIllIIIIIIIllIl)) {
            return;
        }
        this.mouseListeners.add(llllllllllllllllllIllIIIIIIIllIl);
        this.allListeners.add(llllllllllllllllllIllIIIIIIIllIl);
    }
    
    public int getMouseX() {
        return (int)(Mouse.getX() * this.scaleX + this.xoffset);
    }
    
    @Deprecated
    public void enableKeyRepeat(final int llllllllllllllllllIlIllIlllIIlll, final int llllllllllllllllllIlIllIlllIIllI) {
        Keyboard.enableRepeatEvents(true);
    }
    
    public String getAxisName(final int llllllllllllllllllIlIlllIllllllI, final int llllllllllllllllllIlIlllIlllllll) {
        return Input.controllers.get(llllllllllllllllllIlIlllIllllllI).getAxisName(llllllllllllllllllIlIlllIlllllll);
    }
    
    private class NullOutputStream extends OutputStream
    {
        @Override
        public void write(final int lllllllllllllllllIlIlIlllIlllIIl) throws IOException {
        }
    }
}
