package be.woutdev.redistone.api;

import be.woutdev.redistone.api.ban.BanManager;
import be.woutdev.redistone.api.core.Core;
import be.woutdev.redistone.api.data.storage.DataManager;
import be.woutdev.redistone.api.module.ModuleManager;
import be.woutdev.redistone.api.mute.MuteManager;
import be.woutdev.redistone.api.permission.PermissionHelper;
import be.woutdev.redistone.api.server.NativeFunctionality;
import be.woutdev.redistone.api.server.Server;
import be.woutdev.redistone.api.time.TimeFormatter;
import be.woutdev.redistone.api.user.UserManager;
import be.woutdev.redistone.api.util.UUIDFetcher;

import java.util.logging.Logger;

/**
 * The single access point to access all parts of the Core
 *
 * @author Wout Ceulemans
 * @see Core
 */
public final class API
{
    private static Core core;

    /**
     * Private constructor
     */
    private API()
    {
    }

    /**
     * Get the UserManager to manage and access User objects.
     *
     * @return A UserManager implementation
     */
    public static UserManager getUserManager()
    {
        return core.getUserManager();
    }

    /**
     * Get the BanManager to manage and access Ban objects.
     *
     * @return A BanManager implementation
     */
    public static BanManager getBanManager()
    {
        return core.getBanManager();
    }

    /**
     * Get the MuteManager to manage and access Mute objects.
     *
     * @return A MuteManager implementation
     */
    public static MuteManager getMuteManager()
    {
        return core.getMuteManager();
    }

    /**
     * Get the ModuleManager to manage and access PluginModules.
     *
     * @return A ModuleManager implementation
     */
    public static ModuleManager getModuleManger()
    {
        return core.getModuleManager();
    }

    /**
     * Get the DataManager
     *
     * @return A DataManager implementation
     */
    public static DataManager getDataManager()
    {
        return core.getDataManager();
    }

    /**
     * Get the PermissionHelper
     *
     * @return A PermissionHelper implementation
     */
    public static PermissionHelper getPermissionHelper() { return core.getPermissionHelper(); }

    /**
     * Get the TimeFormatter to format your dates to the correct format or to convert formatted dates to epoch.
     *
     * @return A TimeFormatter implementation
     */
    public static TimeFormatter getTimeFormatter()
    {
        return core.getTimeFormatter();
    }

    /**
     * Get the UUIDFetcher to fetch UUIDs into usernames.
     *
     * @return A UUIDFetcher implementation
     */
    public static UUIDFetcher getUUIDFetcher()
    {
        return core.getUUIDFetcher();
    }

    /**
     * Get A logger which you could use to log through the core.
     *
     * @return A Logger used by the Core
     */
    public static Logger getLogger()
    {
        return core.getLogger();
    }

    /**
     * Get the Server which this module is running on
     *
     * @return A Server implementation
     */
    public static Server getServer()
    {
        return core.getServer();
    }

    /**
     * Based off on which kind of server your module will be used on, getPlugin() will return either
     * net.md_5.bungee.api.plugin.Plugin or org.bukkit.plugin.Plugin (the instance of the implementation of the API)
     *
     * @return an Object which can be cast either to net.md_5.bungee.api.plugin.Plugin or org.bukkit.plugin.Plugin
     */
    public static NativeFunctionality getPlugin()
    {
        return core.getPlugin();
    }

    /**
     * Get the Core implementation that is being used on this server.
     *
     * @return The Core implementation being used
     */
    public static Core getCore()
    {
        return core;
    }

    /**
     * Set the core for the API. The core is automatically set by the API implementation and therefor shouldn't be set
     * by any module using the API.
     *
     * @param core The API implementation
     */
    public static void setCore(Core core)
    {
        if (API.core != null)
        {
            throw new RuntimeException("Core shouldn't be initialized more than once!");
        }

        API.core = core;
    }
}
