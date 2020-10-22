package com.elementars.eclient.util;

import java.util.concurrent.locks.*;
import java.io.*;
import java.util.*;

public class DataManager
{
    public static /* synthetic */ LinkedHashMap<String, PlayerIdentity> identityCacheMap;
    private static final /* synthetic */ Lock identityLock;
    private final /* synthetic */ Lock threadLock;
    private final /* synthetic */ Lock waypointLock;
    
    public DataManager() {
        this.threadLock = new ReentrantLock();
        this.waypointLock = new ReentrantLock();
    }
    
    public static PlayerIdentity getPlayerIdentity(final String llllllllllllllllllIIIIllllIIlllI) {
        if (DataManager.identityCacheMap.containsKey(llllllllllllllllllIIIIllllIIlllI)) {
            return DataManager.identityCacheMap.get(llllllllllllllllllIIIIllllIIlllI);
        }
        return new PlayerIdentity(llllllllllllllllllIIIIllllIIlllI);
    }
    
    public static synchronized void savePlayerIdentity(final PlayerIdentity llllllllllllllllllIIIIllllIlIllI, final boolean llllllllllllllllllIIIIllllIlIlIl) throws IOException {
        DataManager.identityLock.lock();
        try {
            final File llllllllllllllllllIIIIllllIlllII = new File("playeridentitycache");
            if (!llllllllllllllllllIIIIllllIlllII.exists()) {
                llllllllllllllllllIIIIllllIlllII.mkdir();
            }
            final File llllllllllllllllllIIIIllllIllIll = new File(String.valueOf(new StringBuilder().append("playeridentitycache/").append(llllllllllllllllllIIIIllllIlIllI.getStringUuid()).append(".mcid")));
            if (llllllllllllllllllIIIIllllIllIll.exists() || llllllllllllllllllIIIIllllIlIlIl) {
                llllllllllllllllllIIIIllllIllIll.delete();
                if (llllllllllllllllllIIIIllllIlIlIl) {
                    return;
                }
            }
            final FileOutputStream llllllllllllllllllIIIIllllIllIlI = new FileOutputStream(llllllllllllllllllIIIIllllIllIll);
            final ObjectOutputStream llllllllllllllllllIIIIllllIllIIl = new ObjectOutputStream(llllllllllllllllllIIIIllllIllIlI);
            llllllllllllllllllIIIIllllIllIIl.writeObject(llllllllllllllllllIIIIllllIlIllI);
            llllllllllllllllllIIIIllllIllIIl.close();
            llllllllllllllllllIIIIllllIllIlI.close();
        }
        finally {
            DataManager.identityLock.unlock();
        }
    }
    
    static {
        identityLock = new ReentrantLock();
        DataManager.identityCacheMap = new LinkedHashMap<String, PlayerIdentity>();
    }
    
    public synchronized void loadPlayerIdentities() throws IOException {
        DataManager.identityLock.lock();
        try {
            final File llllllllllllllllllIIIIllllIIlIIl = new File("playeridentitycache");
            if (!llllllllllllllllllIIIIllllIIlIIl.exists()) {
                return;
            }
            if (!llllllllllllllllllIIIIllllIIlIIl.isDirectory()) {
                llllllllllllllllllIIIIllllIIlIIl.delete();
                return;
            }
            final List<File> llllllllllllllllllIIIIllllIIlIII = Arrays.asList(llllllllllllllllllIIIIllllIIlIIl.listFiles());
            FileInputStream llllllllllllllllllIIIIlllIllllll;
            ObjectInputStream llllllllllllllllllIIIIlllIlllllI;
            Object llllllllllllllllllIIIIlllIllllIl;
            final Exception ex;
            Exception llllllllllllllllllIIIIlllIllllII;
            llllllllllllllllllIIIIllllIIlIII.stream().filter(llllllllllllllllllIIIIlllIllIlIl -> llllllllllllllllllIIIIlllIllIlIl.getName().endsWith(".mcid")).forEach(llllllllllllllllllIIIIlllIlllIll -> {
                try {
                    llllllllllllllllllIIIIlllIllllll = new FileInputStream(llllllllllllllllllIIIIlllIlllIll);
                    llllllllllllllllllIIIIlllIlllllI = new ObjectInputStream(llllllllllllllllllIIIIlllIllllll);
                    llllllllllllllllllIIIIlllIllllIl = llllllllllllllllllIIIIlllIlllllI.readObject();
                    if (llllllllllllllllllIIIIlllIllllIl instanceof PlayerIdentity) {
                        DataManager.identityCacheMap.put(((PlayerIdentity)llllllllllllllllllIIIIlllIllllIl).getStringUuid(), (PlayerIdentity)llllllllllllllllllIIIIlllIllllIl);
                        llllllllllllllllllIIIIlllIlllllI.close();
                        llllllllllllllllllIIIIlllIllllll.close();
                    }
                    else {
                        llllllllllllllllllIIIIlllIlllllI.close();
                        llllllllllllllllllIIIIlllIllllll.close();
                    }
                }
                catch (IOException | ClassNotFoundException ex2) {
                    llllllllllllllllllIIIIlllIllllII = ex;
                    llllllllllllllllllIIIIlllIllllII.printStackTrace();
                }
            });
        }
        finally {
            DataManager.identityLock.unlock();
        }
    }
}
