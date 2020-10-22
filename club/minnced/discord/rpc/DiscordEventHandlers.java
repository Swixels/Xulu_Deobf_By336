package club.minnced.discord.rpc;

import java.util.*;
import com.sun.jna.*;

public class DiscordEventHandlers extends Structure
{
    public /* synthetic */ OnStatus errored;
    private static final /* synthetic */ List<String> FIELD_ORDER;
    public /* synthetic */ OnReady ready;
    public /* synthetic */ OnGameUpdate joinGame;
    public /* synthetic */ OnStatus disconnected;
    public /* synthetic */ OnJoinRequest joinRequest;
    public /* synthetic */ OnGameUpdate spectateGame;
    
    protected List<String> getFieldOrder() {
        return DiscordEventHandlers.FIELD_ORDER;
    }
    
    public boolean equals(final Object lllllllllllllllllIIlIllIIIlIlIll) {
        if (this == lllllllllllllllllIIlIllIIIlIlIll) {
            return true;
        }
        if (!(lllllllllllllllllIIlIllIIIlIlIll instanceof DiscordEventHandlers)) {
            return false;
        }
        final DiscordEventHandlers lllllllllllllllllIIlIllIIIlIllIl = (DiscordEventHandlers)lllllllllllllllllIIlIllIIIlIlIll;
        return Objects.equals(this.ready, lllllllllllllllllIIlIllIIIlIllIl.ready) && Objects.equals(this.disconnected, lllllllllllllllllIIlIllIIIlIllIl.disconnected) && Objects.equals(this.errored, lllllllllllllllllIIlIllIIIlIllIl.errored) && Objects.equals(this.joinGame, lllllllllllllllllIIlIllIIIlIllIl.joinGame) && Objects.equals(this.spectateGame, lllllllllllllllllIIlIllIIIlIllIl.spectateGame) && Objects.equals(this.joinRequest, lllllllllllllllllIIlIllIIIlIllIl.joinRequest);
    }
    
    public int hashCode() {
        return Objects.hash(this.ready, this.disconnected, this.errored, this.joinGame, this.spectateGame, this.joinRequest);
    }
    
    static {
        FIELD_ORDER = Collections.unmodifiableList((List<? extends String>)Arrays.asList("ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest"));
    }
    
    public interface OnGameUpdate extends Callback
    {
        void accept(final String p0);
    }
    
    public interface OnJoinRequest extends Callback
    {
        void accept(final DiscordUser p0);
    }
    
    public interface OnReady extends Callback
    {
        void accept(final DiscordUser p0);
    }
    
    public interface OnStatus extends Callback
    {
        void accept(final int p0, final String p1);
    }
}
