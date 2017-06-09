package be.woutdev.redistone.api.user;

import be.woutdev.redistone.api.entity.EntityId;
import be.woutdev.redistone.api.permission.Group;
import be.woutdev.redistone.api.permission.Rank;
import be.woutdev.redistone.api.user.profile.Profile;
import be.woutdev.redistone.api.user.profile.StandardProfile;

import java.util.Set;
import java.util.UUID;

/**
 * To show a User which might be online, or offline.
 *
 * @author Wout Ceulemans
 * @see UserManager
 * @see User
 */
public interface OfflineUser extends EntityId
{
    /**
     * Get the unique id of this User
     *
     * @return The UUID of this user
     */
    UUID getUniqueId();

    /**
     * Get the StandardProfile for this User
     *
     * @return The StandardProfile for this User
     */
    StandardProfile getStandardProfile();

    /**
     * Get a Profile for this User
     *
     * @param name The profile's name
     *
     * @return The Profile or null if it doesn't exist for this user
     */
    Profile getProfile(String name);

    /**
     * Get all Profile instances for this OfflineUser
     *
     * @return A Set with all Profile instances
     */
    Set<Profile> getProfiles();

    /**
     * Get the Rank applicable for this Server
     *
     * @return The rank applicable on this Server
     */
    Rank getRank();

    /**
     * Set a Rank for a certain Group and certain server name
     *
     * @param group The group
     * @param server The servername
     */
    void setRank(Group group, String server);

    /**
     * Check if this user is currently online.
     *
     * @return If this user is currently online.
     */
    boolean isOnline();

    /**
     * Update this user and sync him with all servers applicable through Redis pub/sub
     */
    void update();
}
