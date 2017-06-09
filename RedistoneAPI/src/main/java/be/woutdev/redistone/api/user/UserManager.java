package be.woutdev.redistone.api.user;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.UUID;

/**
 * Manager to manage Users
 *
 * @author Seth Mcallister
 * @see User
 * @see OfflineUser
 */
public interface UserManager
{
    /**
     * Find a User by uniqueId
     *
     * @param uuid The uniqueId to search for
     *
     * @return The User or null
     */
    User findByUniqueId(UUID uuid);

    /**
     * Find all online Users
     *
     * @return A ImmutableList of all online users
     */
    ImmutableList<User> findAllUsers();

    /**
     * Find a OfflineUser by unique id
     *
     * @param uuid The unique id of the user
     *
     * @return The OfflineUser object or null
     */
    OfflineUser findOfflineByUniqueId(UUID uuid);

    /**
     * Find all OfflineUsers who joined this server before.
     * Not advised to use. It gets all the users in the database.
     *
     * @return A List of all OfflineUsers
     */
    List<OfflineUser> findAllUsersAnyway();

    /**
     * Find a OfflineUser by name
     *
     * @param name The name to search for
     *
     * @return The OfflineUser or null
     */
    OfflineUser findOfflineByName(String name);

    /**
     * Update the provided player
     * <p>
     * This does not send a update request through Redis
     *
     * @param user The user to update
     */
    void update(OfflineUser user);
}
