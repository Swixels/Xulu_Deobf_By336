package com.elementars.eclient.enemy;

import io.netty.util.internal.*;

public class Enemies
{
    private static /* synthetic */ ConcurrentSet<Enemy> enemies;
    
    public static Enemy getEnemyByName(final String lllIIllIllIlIl) {
        for (final Enemy lllIIllIllIllI : Enemies.enemies) {
            if (lllIIllIllIllI.username.equalsIgnoreCase(lllIIllIllIlIl)) {
                return lllIIllIllIllI;
            }
        }
        return null;
    }
    
    public static boolean isEnemy(final String lllIIllIlIllll) {
        return Enemies.enemies.stream().anyMatch(lllIIllIlIlIIl -> lllIIllIlIlIIl.username.equalsIgnoreCase(lllIIllIlIllll));
    }
    
    public static void delEnemy(final String lllIIllIlllIlI) {
        Enemies.enemies.remove((Object)getEnemyByName(lllIIllIlllIlI));
    }
    
    public static void addEnemy(final String lllIIllIlllllI) {
        Enemies.enemies.add((Object)new Enemy(lllIIllIlllllI));
    }
    
    static {
        Enemies.enemies = (ConcurrentSet<Enemy>)new ConcurrentSet();
    }
    
    public static ConcurrentSet<Enemy> getEnemies() {
        return Enemies.enemies;
    }
}
