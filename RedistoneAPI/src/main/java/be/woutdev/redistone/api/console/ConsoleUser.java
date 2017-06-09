package be.woutdev.redistone.api.console;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.entity.EntityId;
import be.woutdev.redistone.api.module.command.CommandCaller;

/**
 * Represents a ConsoleUser. For example a Ban, Mute or Warning issued by the Console
 *
 * @author Wout Ceulemans
 * @see EntityId
 */
public class ConsoleUser implements CommandCaller, EntityId
{
    private final String name;

    public ConsoleUser(String name)
    {
        this.name = name;
    }

    public String getEntityIdentifier()
    {
        return String.format("CONSOLE|%s", name);
    }

    public void sendMessage(String s)
    {
        API.getLogger().info(s);
    }

    public void sendUnformattedMessage(String s)
    {
        API.getLogger().info(s);
    }

    public String getServerName()
    {
        return name;
    }
}
