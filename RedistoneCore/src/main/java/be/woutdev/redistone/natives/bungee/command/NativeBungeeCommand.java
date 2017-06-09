package be.woutdev.redistone.natives.bungee.command;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.console.ConsoleUser;
import be.woutdev.redistone.api.module.command.CommandCallerType;
import be.woutdev.redistone.api.module.command.CommandListener;
import be.woutdev.redistone.api.user.User;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Wout on 17/04/2017.
 */
public class NativeBungeeCommand extends Command
{
    private final CommandListener listener;

    public NativeBungeeCommand(CommandListener listener)
    {
        super(listener.getCommand(), "hcf.command",
              listener.getAliases().toArray(new String[listener.getAliases().size()]));

        this.listener = listener;
    }

    @Override
    public void execute(CommandSender sender, String[] args)
    {
        if (!(sender instanceof ProxiedPlayer))
        {
            if (listener.isPlayerOnly())
            {
                sender.sendMessage(ChatColor.RED + "This command is player only.");
                return;
            }

            listener.execute(new ConsoleUser(API.getServer().getName()), CommandCallerType.CONSOLE,
                             args);
            return;
        }

        User user = API.getUserManager().findByUniqueId(((ProxiedPlayer) sender).getUniqueId());

        if (!API.getPermissionHelper().hasPermission(user, listener.getPermission()))
        {
            sender.sendMessage(ChatColor.RED + "You do not have enough permissions to execute that command!");
            return;
        }

        listener.execute(user, CommandCallerType.USER, args);
    }
}
