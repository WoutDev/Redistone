package be.woutdev.redistone.api.ban;

import be.woutdev.redistone.api.entity.EntityId;

import java.util.Date;

/**
 * The Ban-object that holds Ban related data.
 *
 * @author Wout Ceulemans
 * @see BanManager#createBan
 */
public interface Ban
{
    /**
     * Get the BanType associated with this Ban
     *
     * @return The BanType associated with this Ban
     */
    BanType getType();

    /**
     * Get the SimplifiedBanType associated with this Ban
     *
     * @return The SimplifiedBanType associated with this Ban
     */
    SimplifiedBanType getSimplifiedType();

    /**
     * Get the reason for this Ban
     *
     * @return The reason specified with this Ban
     */
    String getReason();

    /**
     * Get the EntityId who issued this Ban
     *
     * @return The EntityId who issued this Ban
     */
    EntityId getBannedBy();

    /**
     * If this is a temporarily ban, get the expire date when this ban should expire
     *
     * @return The expire date if applicable
     */
    Date getExpireDate();

    /**
     * Get when this Ban was issued
     *
     * @return The date this Ban was issued
     */
    Date getBanDate();

    /**
     * The EntityId who was targeted with this Ban. Could be a OfflineUser or an IPEntity
     *
     * @return The EntityId of the target of this Ban.
     */
    EntityId getTarget();

    /**
     * Check if this Ban is still active, should only be used on temporarily Ban
     *
     * @return If this ban is still active, if applicable
     */
    boolean isActive();
}
