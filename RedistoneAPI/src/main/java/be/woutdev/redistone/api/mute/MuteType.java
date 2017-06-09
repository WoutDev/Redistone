package be.woutdev.redistone.api.mute;

/**
 * Get the specific type of Mute
 * <p>
 * DRY: Should probably be combined with BanType in the future
 *
 * @author Wout Ceulemans
 * @see Mute#getType
 */
public enum MuteType
{
    NORMAL_PERMANENT,
    NORMAL_TEMPORARILY,
    IP_PERMANENT,
    IP_TEMPORARILY
}
