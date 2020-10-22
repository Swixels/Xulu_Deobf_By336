package com.elementars.eclient.friend;

import io.netty.util.internal.*;

public class Friends
{
    private static /* synthetic */ ConcurrentSet<Friend> friends;
    
    public static Friend getFriendByName(final String lllIIIlIlIlIIlI) {
        for (final Friend lllIIIlIlIlIllI : Friends.friends) {
            if (lllIIIlIlIlIllI.username.equalsIgnoreCase(lllIIIlIlIlIIlI)) {
                return lllIIIlIlIlIllI;
            }
        }
        return null;
    }
    
    public static void addFriend(final String lllIIIlIllIIlll) {
        Friends.friends.add((Object)new Friend(lllIIIlIllIIlll));
    }
    
    public static ConcurrentSet<Friend> getFriends() {
        return Friends.friends;
    }
    
    static {
        Friends.friends = (ConcurrentSet<Friend>)new ConcurrentSet();
    }
    
    public static void delFriend(final String lllIIIlIlIlllIl) {
        Friends.friends.remove((Object)getFriendByName(lllIIIlIlIlllIl));
    }
    
    public static boolean isFriend(final String lllIIIlIlIIllII) {
        return Friends.friends.stream().anyMatch(lllIIIlIlIIIlll -> lllIIIlIlIIIlll.username.equalsIgnoreCase(lllIIIlIlIIllII));
    }
}
