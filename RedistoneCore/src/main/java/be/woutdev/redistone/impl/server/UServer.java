package be.woutdev.redistone.impl.server;

import be.woutdev.redistone.api.config.Config;
import be.woutdev.redistone.api.server.NativeFunctionality;
import be.woutdev.redistone.api.server.Server;
import be.woutdev.redistone.api.server.ServerEnvironment;
import be.woutdev.redistone.api.server.ServerType;
import be.woutdev.redistone.impl.core.UCore;

import java.nio.file.Path;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Wout on 14/04/2017.
 */
public class UServer implements Server
{
    private final String name;
    private final ServerType type;
    private final ServerEnvironment environment;
    private final Config config;
    private final Logger logger;
    private final Path pluginDirPath;
    private final NativeFunctionality plugin;

    public UServer(ServerType type, Config config, Logger logger, Path pluginDirPath, NativeFunctionality plugin)
    {
        this.name = config.getString("server-name");
        this.type = type;
        this.environment = ServerEnvironment.valueOf(config.getString("environment").toUpperCase());
        this.config = config;
        this.logger = logger;
        this.pluginDirPath = pluginDirPath;
        this.plugin = plugin;

        new UCore(this, plugin);
    }

    public ServerType getType()
    {
        return type;
    }

    public String getName()
    {
        return name;
    }

    public Logger getLogger()
    {
        return logger;
    }

    @Override
    public ServerEnvironment getEnvironment()
    {
        return environment;
    }

    @Override
    public Config getConfig()
    {
        return config;
    }

    @Override
    public NativeFunctionality getPlugin()
    {
        return plugin;
    }

    public Date getNextScheduledRestart()
    {
        return null;
    }

    public void setNextScheduledRestart(Date date)
    {
        // TODO scheduled restart
    }

    public Path getPluginDir()
    {
        return pluginDirPath;
    }
}
