package be.woutdev.redistone.api.server;

import be.woutdev.redistone.api.module.command.CommandListener;
import be.woutdev.redistone.api.user.User;

/**
 * Is implemented by our Bukkit and Bungee plugin implementations.
 * <p>
 * Can be casted to the Bukkit Plugin object, or the Bungee Plugin object, based of where this Core is being used.
 * <p>
 * It is used to share similar functionality.
 *
 * @author Wout Ceulemans
 */
public interface NativeFunctionality
{
    /**
     * Send a message to a user
     *
     * @param user The user to send the message to
     * @param message The message
     */
    void sendMessage(User user, String message);

    /**
     * Run a Runnable async
     *
     * @param r The Runnable to run
     */
    void runAsync(Runnable r);

    /**
     * Register a CommandListener
     *
     * @param listener The listener to register
     */
    void registerCommand(CommandListener listener);

    /**
     * Kick a User for a specified message
     *
     * @param user The user to kick
     * @param message The message
     */
    void kick(User user, String message);
}

