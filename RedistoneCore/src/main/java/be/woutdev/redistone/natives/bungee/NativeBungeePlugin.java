package be.woutdev.redistone.natives.bungee;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.module.command.CommandListener;
import be.woutdev.redistone.api.server.NativeFunctionality;
import be.woutdev.redistone.api.server.Server;
import be.woutdev.redistone.api.server.ServerType;
import be.woutdev.redistone.api.user.User;
import be.woutdev.redistone.impl.config.UConfig;
import be.woutdev.redistone.impl.server.UServer;
import be.woutdev.redistone.natives.bungee.command.NativeBungeeCommand;
import be.woutdev.redistone.natives.bungee.listener.PlayerDisconnectEventListener;
import be.woutdev.redistone.natives.bungee.listener.PlayerSwitchServerEventListener;
import be.woutdev.redistone.natives.bungee.listener.ServerConnectEventListener;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * Created by Wout on 14/04/2017.
 */
public class NativeBungeePlugin extends Plugin implements NativeFunctionality
{
    @Override
    public void onEnable()
    {
        Server server = new UServer(ServerType.PROXY, new UConfig(getDataFolder().toPath(), "config.yml",
                                                                  getResourceAsStream("config.yml")), getLogger(),
                                    getDataFolder().toPath(), this);

        getProxy().getPluginManager().registerListener(this, new ServerConnectEventListener());
        getProxy().getPluginManager().registerListener(this, new PlayerDisconnectEventListener());
        getProxy().getPluginManager().registerListener(this, new PlayerSwitchServerEventListener());
    }

    @Override
    public void onDisable()
    {
        API.getDataManager().getAdapter().shutdown();
    }

    @Override
    public void sendMessage(User user, String message)
    {
        getProxy().getPlayer(user.getUniqueId()).sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    @Override
    public void runAsync(Runnable r)
    {
        getProxy().getScheduler().runAsync(this, r);
    }

    @Override
    public void registerCommand(CommandListener listener)
    {
        getProxy().getPluginManager().registerCommand(this, new NativeBungeeCommand(listener));
    }

    @Override
    public void kick(User user, String s)
    {
        getProxy().getPlayer(user.getUniqueId()).disconnect(ChatColor.translateAlternateColorCodes('&', s));
    }
}
