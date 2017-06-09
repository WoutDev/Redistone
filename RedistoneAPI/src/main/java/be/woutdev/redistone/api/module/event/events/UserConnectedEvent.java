package be.woutdev.redistone.api.module.event.events;

import be.woutdev.redistone.api.module.event.Event;
import be.woutdev.redistone.api.user.User;

/**
 * Created by Wout on 15/04/2017.
 */
public class UserConnectedEvent extends Event
{
    private final User user;

    public UserConnectedEvent(User user)
    {
        this.user = user;
    }

    @Override
    public String getEventName()
    {
        return "UserConnectedEvent";
    }

    public User getUser()
    {
        return user;
    }
}
