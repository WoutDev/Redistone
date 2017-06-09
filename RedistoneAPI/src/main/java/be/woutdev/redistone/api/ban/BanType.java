package be.woutdev.redistone.api.ban;

/**
 * Get the specific type of Ban
 * <p>
 * DRY: Should probably be combined with MuteType in the future
 *
 * @author Wout Ceulemans
 * @see Ban#getType
 */
public enum BanType
{
    NORMAL_PERMANENT,
    NORMAL_TEMPORARILY,
    IP_PERMANENT,
    IP_TEMPORARILY,
}
