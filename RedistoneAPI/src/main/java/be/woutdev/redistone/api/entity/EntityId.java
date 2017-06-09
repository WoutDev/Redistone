package be.woutdev.redistone.api.entity;

/**
 * The EntityId is an interface to identify and easily store different kinds of data. Example implementations of
 * EntityId are: OfflineUser, IPEntity, ConsoleUser
 *
 * @see be.woutdev.redistone.api.console.ConsoleUser
 * @see be.woutdev.redistone.api.user.OfflineUser
 * @see IPEntity
 */
public interface EntityId
{
    /**
     * Get a unique String to identify the kind of data. By convention, use type format:
     * TYPE|uuid
     * A OfflineUser has the following format:
     * USER|<36 length String of its UUID>
     *
     * @return The unique String to identify the kind of data.
     */
    String getEntityIdentifier();
}
