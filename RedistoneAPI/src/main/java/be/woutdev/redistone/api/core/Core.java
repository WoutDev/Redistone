package be.woutdev.redistone.api.core;

import be.woutdev.redistone.api.ban.BanManager;
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
 * The Core interface that gives access to all parts of the Core.
 *
 * @author Wout Ceulemans
 * @see be.woutdev.redistone.api.API
 */
public interface Core
{
    /**
     * Get the UserManager to manage and access User objects.
     *
     * @return A UserManager implementation
     */
    UserManager getUserManager();

    /**
     * Get the BanManager to manage and access Ban objects.
     *
     * @return A BanManager implementation
     */
    BanManager getBanManager();

    /**
     * Get the MuteManager to manage and access Mute objects.
     *
     * @return A MuteManager implementation
     */
    MuteManager getMuteManager();

    /**
     * Get the ModuleManager to manage and access PluginModules.
     *
     * @return A ModuleManager implementation
     */
    ModuleManager getModuleManager();

    /**
     * Get the DataManager
     *
     * @return A DataManager implementation
     */
    DataManager getDataManager();

    /**
     * Get the PermissionHelper
     *
     * @return A PermissionHelper implementation
     */
    PermissionHelper getPermissionHelper();

    /**
     * Get the TimeFormatter to format your dates to the correct format or to convert formatted dates to epoch.
     *
     * @return A TimeFormatter implementation
     */
    TimeFormatter getTimeFormatter();

    /**
     * Get the UUIDFetcher to fetch UUIDs into usernames.
     *
     * @return A UUIDFetcher implementation
     */
    UUIDFetcher getUUIDFetcher();

    /**
     * Get A logger which you could use to log through the core.
     *
     * @return A Logger used by the Core
     */
    Logger getLogger();

    /**
     * Get the Server which this module is running on
     *
     * @return A Server implementation
     */
    Server getServer();

    /**
     * Based off on which kind of server your module will be used on, getPlugin() will return either
     * net.md_5.bungee.api.plugin.Plugin or org.bukkit.plugin.Plugin (the instance of the implementation of the API)
     *
     * @return an Object which can be cast either to net.md_5.bungee.api.plugin.Plugin or org.bukkit.plugin.Plugin
     */
    NativeFunctionality getPlugin();
}