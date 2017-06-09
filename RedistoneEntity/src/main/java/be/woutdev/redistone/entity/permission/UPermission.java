package be.woutdev.redistone.entity.permission;

import be.woutdev.redistone.api.permission.Permission;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Wout on 6/06/2017.
 */
@Entity
public class UPermission implements Permission
{
    @Id
    private String node;
    @Column
    private boolean negative;

    protected UPermission()
    {
    } // JPA requirement

    public UPermission(String node, boolean neg)
    {
        this.node = node;
        this.negative = neg;
    }

    @Override
    public String getNode()
    {
        return node;
    }

    @Override
    public boolean isNegative()
    {
        return negative;
    }

    @Override
    public boolean isWildcard()
    {
        return getNode().equals("*");
    }
}
