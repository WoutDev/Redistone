package be.woutdev.redistone.natives.bukkit.command;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.console.ConsoleUser;
import be.woutdev.redistone.api.module.command.CommandCallerType;
import be.woutdev.redistone.api.module.command.CommandListener;
import be.woutdev.redistone.api.user.User;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

/**
 * Created by Wout on 17/04/2017.
 */
public class NativeBukkitCommand extends BukkitCommand
{
    private final CommandListener listener;

    public NativeBukkitCommand(CommandListener listener)
    {
        super(listener.getCommand(), listener.getDescription(), listener.getUsage(), listener.getAliases());

        this.listener = listener;
    }

    @Override
    public boolean execute(CommandSender sender, String cmd, String[] args)
    {
        if (sender instanceof ConsoleCommandSender)
        {
            if (listener.isPlayerOnly())
            {
                sender.sendMessage(ChatColor.RED + "This command is player only.");
                return false;
            }

            listener.execute(new ConsoleUser(API.getServer().getName()), CommandCallerType.CONSOLE,
                             args);
            return true;
        }

        User user = API.getUserManager().findByUniqueId(((Player) sender).getUniqueId());

        if (!API.getPermissionHelper().hasPermission(user, listener.getPermission()))
        {
            sender.sendMessage(ChatColor.RED + "You do not have enough permissions to execute that command!");
            return false;
        }

        listener.execute(user, CommandCallerType.USER, args);
        return true;
    }
}
