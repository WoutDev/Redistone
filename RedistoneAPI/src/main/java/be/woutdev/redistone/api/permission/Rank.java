package be.woutdev.redistone.api.permission;

/**
 * Rank object grouping the Group, applicable server together.
 *
 * @author Wout Ceulemans
 * @see Group
 */
public interface Rank
{
    /**
     * Get the Group of this rank
     *
     * @return The group
     */
    Group getGroup();

    /**
     * Get where this Rank is applicable
     *
     * @return The server name where this Rank is applicable
     */
    String getApplicableServer();

    /**
     * If this Rank is applicable globally
     *
     * @return If this Rank is global
     */
    boolean isGlobal();
}
