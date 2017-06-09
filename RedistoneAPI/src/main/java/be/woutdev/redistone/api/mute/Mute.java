package be.woutdev.redistone.api.mute;

import be.woutdev.redistone.api.entity.EntityId;

import java.util.Date;

/**
 * The Mute-object that holds Mute related data.
 *
 * @author Wout Ceulemans
 * @see MuteManager#createMute
 */
public interface Mute
{
    /**
     * Get the MuteType associated with this Mute
     *
     * @return The MuteType associated with this Mute
     */
    MuteType getType();

    /**
     * Get the SimplifiedMuteType associated with this Mute
     *
     * @return The SimplifiedMuteType associated with this Mute
     */
    SimplifiedMuteType getSimplifiedType();

    /**
     * Get the reason for this Mute
     *
     * @return The reason specified with this Mute
     */
    String getReason();

    /**
     * Get the EntityId who issued this Mute
     *
     * @return The EntityId who issued this Mute
     */
    EntityId getMutedBy();

    /**
     * If this is a temporarily mute, get the expire date when this mute should expire
     *
     * @return The expire date if applicable
     */
    Date getExpireDate();

    /**
     * Get when this Mute was issued
     *
     * @return The date this Mute was issued
     */
    Date getMuteDate();

    /**
     * The EntityId who was targeted with this Mute. Should only be of type OfflineUser
     *
     * @return The EntityId of the target of this Mute.
     */
    EntityId getTarget();

    /**
     * Check if this Mute is still active, should only be used on temporarily Mute
     *
     * @return If this mute is still active, if applicable
     */
    boolean isActive();
}
