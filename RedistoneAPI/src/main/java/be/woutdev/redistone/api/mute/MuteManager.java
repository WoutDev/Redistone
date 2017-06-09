package be.woutdev.redistone.api.mute;

import be.woutdev.redistone.api.entity.EntityId;

import java.util.Date;

/**
 * The MuteManager-class which handles all of the mutes
 *
 * @author Wout Ceulemans
 * @see Mute
 */
public interface MuteManager
{
    /**
     * Insert a new Mute
     *
     * @param mute The Mute to insert
     */
    void addMute(Mute mute);

    /**
     * Removes the Mute by renaming the key, this doesn't really remove it from the database.
     *
     * @param mute The Mute to 'remove'
     */
    void removeMute(Mute mute);

    /**
     * Get a Mute object by EntityId. Could return null in case of no Mute active.
     *
     * @param id The id to check
     *
     * @return The Mute object, if applicable
     */
    Mute getMute(EntityId id);

    /**
     * Create a new Mute object by different parameters (permanent)
     *
     * @param target The target
     * @param reason The reason
     * @param type The type
     * @param mutedBy Who muted him
     *
     * @return A new Mute object created by the above parameters
     */
    Mute createMute(EntityId target, String reason, MuteType type, EntityId mutedBy);

    /**
     * Create a new Mute object by different parameters (temporarily)
     *
     * @param target The target
     * @param reason The reason
     * @param type The type
     * @param mutedBy Who muted him
     * @param expireDate When the mute should expire
     *
     * @return A new Mute object created by the above parameters
     */
    Mute createMute(EntityId target, String reason, MuteType type, EntityId mutedBy, Date expireDate);

    /**
     * Check if the EntityId is muted
     *
     * @param id The id to check
     *
     * @return If the above EntityId is muted
     */
    boolean isMuted(EntityId id);

    /**
     * Get the message for a certain type of mute. This is used for example when a user tries to talk.
     *
     * @param mute The mute to get the message from
     *
     * @return The message based of the specified Mute object
     */
    String getMuteMessage(Mute mute);
}