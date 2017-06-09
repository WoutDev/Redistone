package be.woutdev.redistone.api.warning;

import be.woutdev.redistone.api.entity.EntityId;

import java.util.Date;

/**
 * Simple Warning object to store Warning-details
 */
public interface Warning
{
    /**
     * Get the reason specified with this warning.
     *
     * @return The reason
     */
    String getReason();

    /**
     * Get who issued this Warning
     *
     * @return The EntityId who issued this Warning
     */
    EntityId getWarnedBy();

    /**
     * Get when this Warning was issued.
     *
     * @return The data when this Warning was issued.
     */
    Date getWarnDate();
}
