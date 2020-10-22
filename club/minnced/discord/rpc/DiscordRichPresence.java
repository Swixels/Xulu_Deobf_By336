package club.minnced.discord.rpc;

import com.sun.jna.*;
import java.util.*;

public class DiscordRichPresence extends Structure
{
    public /* synthetic */ String partyId;
    public /* synthetic */ String joinSecret;
    public /* synthetic */ int partyMax;
    public /* synthetic */ String spectateSecret;
    public /* synthetic */ String details;
    public /* synthetic */ int partySize;
    public /* synthetic */ String largeImageKey;
    public /* synthetic */ String smallImageText;
    public /* synthetic */ long endTimestamp;
    public /* synthetic */ String state;
    public /* synthetic */ String matchSecret;
    public /* synthetic */ String smallImageKey;
    private static final /* synthetic */ List<String> FIELD_ORDER;
    public /* synthetic */ byte instance;
    public /* synthetic */ String largeImageText;
    public /* synthetic */ long startTimestamp;
    
    public boolean equals(final Object llllllllllllllllIlllllIllIllIlII) {
        if (this == llllllllllllllllIlllllIllIllIlII) {
            return true;
        }
        if (!(llllllllllllllllIlllllIllIllIlII instanceof DiscordRichPresence)) {
            return false;
        }
        final DiscordRichPresence llllllllllllllllIlllllIllIllIllI = (DiscordRichPresence)llllllllllllllllIlllllIllIllIlII;
        return this.startTimestamp == llllllllllllllllIlllllIllIllIllI.startTimestamp && this.endTimestamp == llllllllllllllllIlllllIllIllIllI.endTimestamp && this.partySize == llllllllllllllllIlllllIllIllIllI.partySize && this.partyMax == llllllllllllllllIlllllIllIllIllI.partyMax && this.instance == llllllllllllllllIlllllIllIllIllI.instance && Objects.equals(this.state, llllllllllllllllIlllllIllIllIllI.state) && Objects.equals(this.details, llllllllllllllllIlllllIllIllIllI.details) && Objects.equals(this.largeImageKey, llllllllllllllllIlllllIllIllIllI.largeImageKey) && Objects.equals(this.largeImageText, llllllllllllllllIlllllIllIllIllI.largeImageText) && Objects.equals(this.smallImageKey, llllllllllllllllIlllllIllIllIllI.smallImageKey) && Objects.equals(this.smallImageText, llllllllllllllllIlllllIllIllIllI.smallImageText) && Objects.equals(this.partyId, llllllllllllllllIlllllIllIllIllI.partyId) && Objects.equals(this.matchSecret, llllllllllllllllIlllllIllIllIllI.matchSecret) && Objects.equals(this.joinSecret, llllllllllllllllIlllllIllIllIllI.joinSecret) && Objects.equals(this.spectateSecret, llllllllllllllllIlllllIllIllIllI.spectateSecret);
    }
    
    static {
        FIELD_ORDER = Collections.unmodifiableList((List<? extends String>)Arrays.asList("state", "details", "startTimestamp", "endTimestamp", "largeImageKey", "largeImageText", "smallImageKey", "smallImageText", "partyId", "partySize", "partyMax", "matchSecret", "joinSecret", "spectateSecret", "instance"));
    }
    
    public DiscordRichPresence(final String llllllllllllllllIlllllIllIllllll) {
        this.setStringEncoding(llllllllllllllllIlllllIllIllllll);
    }
    
    protected List<String> getFieldOrder() {
        return DiscordRichPresence.FIELD_ORDER;
    }
    
    public int hashCode() {
        return Objects.hash(this.state, this.details, this.startTimestamp, this.endTimestamp, this.largeImageKey, this.largeImageText, this.smallImageKey, this.smallImageText, this.partyId, this.partySize, this.partyMax, this.matchSecret, this.joinSecret, this.spectateSecret, this.instance);
    }
    
    public DiscordRichPresence() {
        this("UTF-8");
    }
}
