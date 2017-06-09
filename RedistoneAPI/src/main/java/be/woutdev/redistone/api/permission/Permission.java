package be.woutdev.redistone.api.permission;

/**
 * Represents a single Permission Node
 * <p>
 * '*' is the wildcard node giving all permissions
 *
 * @author Wout Ceulemans
 * @see Group
 */
public interface Permission
{
    /**
     * Get the String representation of the node
     *
     * @return The node as a String
     */
    String getNode();

    /**
     * If this permission node is negative
     *
     * @return If its negative
     */
    boolean isNegative();

    /**
     * If this permission node is the '*' wildcard node
     *
     * @return In case of wildcard node
     */
    boolean isWildcard();
}
