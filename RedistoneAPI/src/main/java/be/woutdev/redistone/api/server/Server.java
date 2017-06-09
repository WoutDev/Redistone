package be.woutdev.redistone.api.server;

import be.woutdev.redistone.api.config.Config;

import java.util.logging.Logger;

/**
 * Every running core plugin has its own Server object which holds data related to that specific Plugin instance.
 *
 * @author Wout Ceulemans
 */
public interface Server
{
    /**
     * The type of the Server
     *
     * @return The ServerType
     *
     * @see ServerType
     */
    ServerType getType();

    /**
     * The name of the Server, specified in the plugin core's configuration file.
     *
     * @return The name of the Server
     */
    String getName();

    /**
     * The logger which was given by us by either Bukkit or Bungeecord
     *
     * @return The Logger
     */
    Logger getLogger();

    /**
     * The ServerEnvironment which this core is currently running on, specified in the plugin core's configuration file.
     *
     * @return The current ServerEnvironment
     */
    ServerEnvironment getEnvironment();

    /**
     * The Config that holds the core's configuration file data.
     *
     * @return The Config for this core
     */
    Config getConfig();

    /**
     * The NativeFunctionality implementation
     *
     * @return The NativeFunctionality implementation
     */
    NativeFunctionality getPlugin();
}
