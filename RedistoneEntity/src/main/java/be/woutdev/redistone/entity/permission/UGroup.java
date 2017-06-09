package be.woutdev.redistone.entity.permission;

import be.woutdev.redistone.api.permission.Group;
import be.woutdev.redistone.api.permission.Permission;
import com.google.common.collect.Sets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wout on 6/06/2017.
 */
@Entity
public class UGroup implements Group
{
    @Id
    private String name;
    @Column
    private String prefix;
    @Column
    private String suffix;
    @ManyToMany
    private Set<UPermission> nodes;

    protected UGroup()
    {
    } // JPA requirement

    public UGroup(String name)
    {
        this.name = name;
        this.prefix = "";
        this.suffix = "";
        this.nodes = Sets.newHashSet();
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getPrefix()
    {
        return prefix;
    }

    @Override
    public String getSuffix()
    {
        return suffix;
    }

    @Override
    public Set<Permission> getPermissions()
    {
        return new HashSet<>(nodes);
    }

    @Override
    public boolean hasWildcardNode()
    {
        return nodes.stream().anyMatch(Permission::isWildcard);
    }
}
