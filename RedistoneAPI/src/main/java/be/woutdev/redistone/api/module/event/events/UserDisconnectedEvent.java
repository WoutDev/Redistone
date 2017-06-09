package be.woutdev.redistone.api.module.event.events;

import be.woutdev.redistone.api.module.event.Event;
import be.woutdev.redistone.api.user.OfflineUser;

/**
 * Created by Wout on 15/04/2017.
 */
public class UserDisconnectedEvent extends Event
{
    private final OfflineUser user;

    public UserDisconnectedEvent(OfflineUser user)
    {
        this.user = user;
    }

    public String getEventName()
    {
        return "UserDisconnectedEvent";
    }

    public OfflineUser getOfflineUser()
    {
        return user;
    }
}
