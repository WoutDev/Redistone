package be.woutdev.redistone.api.ban;

import be.woutdev.redistone.api.entity.EntityId;

import java.util.Date;

/**
 * The BanManager-class which handles all of the bans
 *
 * @author Wout Ceulemans
 * @see Ban
 */
public interface BanManager
{
    /**
     * Insert a new Ban
     *
     * @param ban The Ban to insert
     */
    void addBan(Ban ban);

    /**
     * Removes the Ban by renaming the key, this doesn't really remove it from the database.
     *
     * @param ban The Ban to 'remove'
     */
    void removeBan(Ban ban);

    /**
     * Get a Ban object by EntityId. Could return null in case of no Ban active.
     *
     * @param id The id to check
     *
     * @return The Ban object, if applicable
     */
    Ban getBan(EntityId id);

    /**
     * Create a new Ban object by different parameters (permanent)
     *
     * @param target The target
     * @param reason The reason
     * @param type The type
     * @param bannedBy Who banned him
     *
     * @return A new Ban object created by the above parameters
     */
    Ban createBan(EntityId target, String reason, BanType type, EntityId bannedBy);

    /**
     * Create a new Ban object by different parameters (temporarily)
     *
     * @param target The target
     * @param reason The reason
     * @param type The type
     * @param bannedBy Who banned him
     * @param expireDate When the ban should expire
     *
     * @return A new Ban object created by the above paremeters
     */
    Ban createBan(EntityId target, String reason, BanType type, EntityId bannedBy, Date expireDate);

    /**
     * Check if the EntityId is banned
     *
     * @param id The id to check
     *
     * @return If the above EntityId is banned
     */
    boolean isBanned(EntityId id);

    /**
     * Get the message for a certain type of ban. This is used for kick messages, failed to login messages, etc.
     *
     * @param ban The ban to get the message from
     *
     * @return The message based of the specified Ban object
     */
    String getBanMessage(Ban ban);
}
