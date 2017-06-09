package be.woutdev.redistone.entity;

import be.woutdev.redistone.api.entity.IPEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Wout on 6/06/2017.
 */
@Entity
public class UIPEntity implements IPEntity
{
    @Id
    private String ip;

    protected UIPEntity()
    {
    } // JPA requirement

    public UIPEntity(String ip)
    {
        this.ip = ip;
    }

    public String getEntityIdentifier()
    {
        return String.format("IP|%s", ip);
    }

    public String getIP()
    {
        return ip;
    }

    @Override
    public boolean equals(Object obj)
    {
        return getIP().equalsIgnoreCase(((IPEntity) obj).getIP());
    }
}
