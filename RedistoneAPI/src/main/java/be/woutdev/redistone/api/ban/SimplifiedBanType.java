package be.woutdev.redistone.api.ban;

/**
 * Get the simplified type of Ban
 * <p>
 * DRY: Should probably be combined with SimplifiedMuteType in the future.
 *
 * @author Wout Ceulemans
 * @see Ban#getSimplifiedType
 */
public enum SimplifiedBanType
{
    TEMPORARILY,
    PERMANENT
}
