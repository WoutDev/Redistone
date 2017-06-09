package be.woutdev.redistone.impl.core;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.ban.BanManager;
import be.woutdev.redistone.api.core.Core;
import be.woutdev.redistone.api.data.storage.DataManager;
import be.woutdev.redistone.api.module.ModuleManager;
import be.woutdev.redistone.api.mute.MuteManager;
import be.woutdev.redistone.api.permission.PermissionHelper;
import be.woutdev.redistone.api.profiles.ProfileHandler;
import be.woutdev.redistone.api.server.NativeFunctionality;
import be.woutdev.redistone.api.server.Server;
import be.woutdev.redistone.api.time.TimeFormatter;
import be.woutdev.redistone.api.user.UserManager;
import be.woutdev.redistone.api.util.UUIDFetcher;
import be.woutdev.redistone.impl.ban.UBanManager;
import be.woutdev.redistone.impl.data.storage.UDataManager;
import be.woutdev.redistone.impl.module.UModuleManager;
import be.woutdev.redistone.impl.mute.UMuteManager;
import be.woutdev.redistone.impl.permission.UPermissionHelper;
import be.woutdev.redistone.impl.time.UTimeFormatter;
import be.woutdev.redistone.impl.user.UUserManager;
import be.woutdev.redistone.impl.util.UUUIDFetcher;

import java.util.logging.Logger;

/**
 * Created by Wout on 14/04/2017.
 */
public class UCore implements Core
{
    private final UDataManager dataManager;
    private final ProfileHandler profileHandler;
    private final UserManager userManager;
    private final BanManager banManager;
    private final MuteManager muteManager;
    private final PermissionHelper permissionHelper;
    private final NativeFunctionality plugin;
    private final ModuleManager moduleManager;
    private final TimeFormatter timeFormatter;
    private final UUIDFetcher uuidFetcher;
    private final Logger logger;
    private final Server server;

    public UCore(Server server, NativeFunctionality plugin)
    {
        API.setCore(this);

        this.profileHandler = new ProfileHandler();

        this.dataManager = new UDataManager(server.getConfig(), profileHandler);

        this.userManager = new UUserManager(dataManager);
        this.banManager = new UBanManager(dataManager);
        this.muteManager = new UMuteManager(dataManager);
        this.permissionHelper = new UPermissionHelper();
        this.plugin = plugin;
        this.timeFormatter = new UTimeFormatter();
        this.uuidFetcher = new UUUIDFetcher();
        this.server = server;
        this.logger = getServer().getLogger();
        this.moduleManager = new UModuleManager(server);
    }

    public UserManager getUserManager()
    {
        return userManager;
    }

    public BanManager getBanManager()
    {
        return banManager;
    }

    public MuteManager getMuteManager()
    {
        return muteManager;
    }

    public ModuleManager getModuleManager()
    {
        return moduleManager;
    }

    @Override
    public DataManager getDataManager()
    {
        return dataManager;
    }

    @Override
    public PermissionHelper getPermissionHelper()
    {
        return permissionHelper;
    }

    public TimeFormatter getTimeFormatter()
    {
        return timeFormatter;
    }

    public UUIDFetcher getUUIDFetcher()
    {
        return uuidFetcher;
    }

    public Logger getLogger()
    {
        return logger;
    }

    public Server getServer()
    {
        return server;
    }

    public NativeFunctionality getPlugin()
    {
        return plugin;
    }

    public ProfileHandler getProfileHandler()
    {
        return profileHandler;
    }
}
