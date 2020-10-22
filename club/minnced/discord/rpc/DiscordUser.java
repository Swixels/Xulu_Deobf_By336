package club.minnced.discord.rpc;

import com.sun.jna.*;
import java.util.*;

public class DiscordUser extends Structure
{
    public /* synthetic */ String discriminator;
    public /* synthetic */ String avatar;
    private static final /* synthetic */ List<String> FIELD_ORDER;
    public /* synthetic */ String userId;
    public /* synthetic */ String username;
    
    protected List<String> getFieldOrder() {
        return DiscordUser.FIELD_ORDER;
    }
    
    public DiscordUser() {
        this("UTF-8");
    }
    
    public int hashCode() {
        return Objects.hash(this.userId, this.username, this.discriminator, this.avatar);
    }
    
    public boolean equals(final Object lllllllllllllllllIIlIlIIlllIllII) {
        if (this == lllllllllllllllllIIlIlIIlllIllII) {
            return true;
        }
        if (!(lllllllllllllllllIIlIlIIlllIllII instanceof DiscordUser)) {
            return false;
        }
        final DiscordUser lllllllllllllllllIIlIlIIlllIlIll = (DiscordUser)lllllllllllllllllIIlIlIIlllIllII;
        return Objects.equals(this.userId, lllllllllllllllllIIlIlIIlllIlIll.userId) && Objects.equals(this.username, lllllllllllllllllIIlIlIIlllIlIll.username) && Objects.equals(this.discriminator, lllllllllllllllllIIlIlIIlllIlIll.discriminator) && Objects.equals(this.avatar, lllllllllllllllllIIlIlIIlllIlIll.avatar);
    }
    
    public DiscordUser(final String lllllllllllllllllIIlIlIIllllIllI) {
        this.setStringEncoding(lllllllllllllllllIIlIlIIllllIllI);
    }
    
    static {
        FIELD_ORDER = Collections.unmodifiableList((List<? extends String>)Arrays.asList("userId", "username", "discriminator", "avatar"));
    }
}
