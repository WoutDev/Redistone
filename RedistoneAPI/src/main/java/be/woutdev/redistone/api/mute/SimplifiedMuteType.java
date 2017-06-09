package be.woutdev.redistone.api.mute;

/**
 * Get the simplified type of Mute
 * <p>
 * DRY: Should probably be combined with SimplifiedBanType in the future.
 *
 * @author Wout Ceulemans
 * @see Mute#getSimplifiedType
 */
public enum SimplifiedMuteType
{
    TEMPORARILY,
    PERMANENT
}
