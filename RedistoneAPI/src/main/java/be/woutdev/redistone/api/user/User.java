package be.woutdev.redistone.api.user;

import be.woutdev.redistone.api.module.command.CommandCaller;

/**
 * Online User object that inherits mainly from OfflineUser and CommandCaller
 *
 * @author Wout Ceulemans
 * @see UserManager
 * @see OfflineUser
 */
public interface User extends OfflineUser, CommandCaller
{
    /**
     * Kick this online User with the specified message
     *
     * @param message The message to kick the user with
     */
    void kick(String message);
}
