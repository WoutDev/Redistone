package be.woutdev.redistone.api.module.command;

import java.util.List;

/**
 * Created by Wout on 13/04/2017.
 */
public abstract class CommandListener
{
    private final String command;
    private final String description;
    private final String usage;
    private final List<String> aliases;
    private final String permission;
    private final Boolean playerOnly;

    public CommandListener(String command, String description, String usage, List<String> aliases, String permission, Boolean playerOnly)
    {
        this.command = command;
        this.description = description;
        this.usage = usage;
        this.aliases = aliases;
        this.permission = permission;
        this.playerOnly = playerOnly;
    }

    public String getCommand()
    {
        return command;
    }

    public List<String> getAliases()
    {
        return aliases;
    }

    public String getPermission()
    {
        return permission;
    }

    public Boolean isPlayerOnly()
    {
        return playerOnly;
    }

    public String getDescription()
    {
        return description;
    }

    public String getUsage()
    {
        return usage;
    }

    public abstract void execute(CommandCaller caller, CommandCallerType callerType, String[] args);
}
