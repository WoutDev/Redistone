package be.woutdev.redistone.api.permission;

import java.util.Set;

/**
 * Group object defining each Groups permissions and properties
 *
 * @author Wout Ceulemans
 * @see Rank
 */
public interface Group
{
    /**
     * Get the name of the group
     *
     * @return The name of the group
     */
    String getName();

    /**
     * Get the prefix of the group
     *
     * @return The prefix of the group
     */
    String getPrefix();

    /**
     * Get the suffix of the group
     *
     * @return The suffix of the group
     */
    String getSuffix();

    /**
     * Get all the Permission nodes of the groups
     *
     * @return A set of Permission nodes
     */
    Set<Permission> getPermissions();

    /**
     * If there is a wildcard Permission Node present giving all permissions to this group.
     * <p>
     * Still be careful for negative Permission Nodes though.
     *
     * @return If a wildcard node is present
     */
    boolean hasWildcardNode();
}
