package be.woutdev.redistone.natives.bukkit;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.module.command.CommandListener;
import be.woutdev.redistone.api.server.NativeFunctionality;
import be.woutdev.redistone.api.server.Server;
import be.woutdev.redistone.api.server.ServerType;
import be.woutdev.redistone.api.user.User;
import be.woutdev.redistone.impl.config.UConfig;
import be.woutdev.redistone.impl.server.UServer;
import be.woutdev.redistone.natives.bukkit.command.NativeBukkitCommand;
import be.woutdev.redistone.natives.bukkit.listener.PlayerJoinListener;
import be.woutdev.redistone.natives.bukkit.listener.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;

/**
 * Created by Wout on 14/04/2017.
 */
public class NativeBukkitPlugin extends JavaPlugin implements NativeFunctionality
{
    @Override
    public void onDisable()
    {
        API.getDataManager().getAdapter().shutdown();
    }

    @Override
    public void onEnable()
    {
        Server server = new UServer(ServerType.BUKKIT,
                                    new UConfig(getDataFolder().toPath(), "config.yml", getResource("config.yml")),
                                    getLogger(), getDataFolder().toPath(), this);

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
    }

    @Override
    public void sendMessage(User user, String message)
    {
        Bukkit.getPlayer(user.getUniqueId()).sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    @Override
    public void runAsync(Runnable r)
    {
        getServer().getScheduler().runTaskAsynchronously(this, r);
    }

    @Override
    public void registerCommand(CommandListener listener)
    {
        try
        {
            Method commandMap = getServer().getClass().getMethod("getCommandMap", null);
            Object cmdmap = commandMap.invoke(getServer(), null);
            Method register = cmdmap.getClass().getMethod("register", String.class, Command.class);
            register.invoke(cmdmap, listener.getCommand(), new NativeBukkitCommand(listener));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void kick(User user, String s)
    {
        getServer().getPlayer(user.getUniqueId()).kickPlayer(ChatColor.translateAlternateColorCodes('&', s));
    }
}
