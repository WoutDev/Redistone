package be.woutdev.redistone.api.entity;

/**
 * Represents a IP as an entity. This might be used as a target in a Ban
 *
 * @author Wout Ceulemans
 * @see EntityId
 */
public interface IPEntity extends EntityId
{
    String getEntityIdentifier();

    String getIP();
}
