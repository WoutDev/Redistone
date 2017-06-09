package be.woutdev.redistone.api.profiles.core;

import be.woutdev.redistone.api.entity.IPEntity;
import be.woutdev.redistone.api.permission.Rank;
import be.woutdev.redistone.api.user.OfflineUser;
import be.woutdev.redistone.api.user.profile.Profile;
import be.woutdev.redistone.api.user.profile.StandardProfile;
import be.woutdev.redistone.api.warning.Warning;
import be.woutdev.redistone.entity.BasicProfile;
import be.woutdev.redistone.entity.UIPEntity;
import be.woutdev.redistone.entity.UWarning;
import be.woutdev.redistone.entity.converter.EntityIdConverter;
import be.woutdev.redistone.entity.permission.URank;
import com.google.common.collect.Sets;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Wout on 13/05/2017.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UStandardProfile extends BasicProfile implements StandardProfile
{
    @Column
    private long joinDate;
    @Column
    private long lastSeen;
    @Column
    private String username;
    @OneToMany(targetEntity = URank.class)
    private Set<Rank> ranks;
    @ManyToMany(targetEntity = UIPEntity.class)
    @Convert(converter = EntityIdConverter.class)
    private Set<IPEntity> ips;
    @ManyToOne(targetEntity = UIPEntity.class)
    @Convert(converter = EntityIdConverter.class)
    private IPEntity lastIP;
    @Column
    private String currentServer;
    @OneToMany(targetEntity = UWarning.class)
    private Set<Warning> warnings;

    protected UStandardProfile()
    {
    } // JPA requirement

    public UStandardProfile(OfflineUser entity)
    {
        super("standard", entity);

        joinDate = System.currentTimeMillis();
        lastSeen = System.currentTimeMillis();
        username = "";
        ranks = Sets.newHashSet();
        ips = Sets.newHashSet();
        lastIP = null;
        currentServer = "";
        warnings = Sets.newHashSet();
    }

    public UStandardProfile(OfflineUser entity, Profile profile)
    {
        super("standard", entity);

        UStandardProfile p = (UStandardProfile) profile;

        joinDate = p.getJoinDate();
        lastSeen = p.getLastSeenDate();
        username = p.getUsername();
        ranks = p.getRanks();
        ips = p.getIPs();
        lastIP = p.getMostRecentIP();
        currentServer = p.getCurrentServer();
        warnings = p.getWarnings();
    }

    @Override
    public void load(Profile profile)
    {
        UStandardProfile p = (UStandardProfile) profile;

        joinDate = p.getJoinDate();
        lastSeen = p.getLastSeenDate();
        username = p.getUsername();
        ranks = p.getRanks();
        ips = p.getIPs();
        lastIP = p.getMostRecentIP();
        currentServer = p.getCurrentServer();
        warnings = p.getWarnings();
    }

    public long getJoinDate()
    {
        return joinDate;
    }

    public long getLastSeenDate()
    {
        return lastSeen;
    }

    public void setLastSeenDate(long lastSeen)
    {
        this.lastSeen = lastSeen;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Set<Rank> getRanks()
    {
        return ranks;
    }

    public Set<IPEntity> getIPs()
    {
        return ips;
    }

    public IPEntity getMostRecentIP()
    {
        return lastIP;
    }

    public void setMostRecentIP(IPEntity entity)
    {
        this.lastIP = entity;
    }

    public String getCurrentServer()
    {
        return currentServer;
    }

    public void setCurrentServer(String server)
    {
        this.currentServer = server;
    }

    public Set<Warning> getWarnings()
    {
        return warnings;
    }
}
