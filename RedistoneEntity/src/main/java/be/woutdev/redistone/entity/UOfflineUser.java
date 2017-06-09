package be.woutdev.redistone.entity;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.permission.Group;
import be.woutdev.redistone.api.permission.Rank;
import be.woutdev.redistone.api.user.OfflineUser;
import be.woutdev.redistone.api.user.profile.Profile;
import be.woutdev.redistone.api.user.profile.StandardProfile;
import be.woutdev.redistone.entity.permission.URank;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Wout on 14/04/2017.
 */
@Entity(name = "user")
public class UOfflineUser implements OfflineUser, Cloneable
{
    @Id
    @GeneratedValue
    private UUID uniqueId;
    @OneToMany(targetEntity = BasicProfile.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Profile> profiles;

    protected UOfflineUser()
    {
    } // JPA requirement

    public UOfflineUser(UUID uniqueId)
    {
        this.uniqueId = uniqueId;
        this.profiles = new HashSet<>();
    }

    public UOfflineUser(UUID uniqueId, Collection<Profile> profiles)
    {
        this.uniqueId = uniqueId;
        this.profiles = new HashSet<>();

        this.profiles.addAll(profiles);
    }

    public UUID getUniqueId()
    {
        return uniqueId;
    }

    public StandardProfile getStandardProfile()
    {
        return (StandardProfile) profiles.stream()
                                         .filter(p -> p.getProfileName().equalsIgnoreCase("standard"))
                                         .findAny()
                                         .orElse(null);
    }

    public Profile getProfile(String s)
    {
        return profiles.stream().filter(p -> p.getProfileName().equalsIgnoreCase(s)).findAny().orElse(null);
    }

    public Set<Profile> getProfiles()
    {
        return profiles;
    }

    public Rank getRank()
    {
        return getStandardProfile().getRanks().stream()
                                   .map(r -> (URank) r)
                                   .filter(r -> API.getServer().getName().equalsIgnoreCase(r.getApplicableServer()))
                                   .findAny()
                                   .orElse(getStandardProfile().getRanks().stream()
                                                               .map(r -> (URank) r)
                                                               .filter(r -> r.getApplicableServer()
                                                                             .equals("___GLOBAL___"))
                                                               .findAny()
                                                               .orElse(null));
    }

    public void setRank(Group group, String s)
    {
        URank r = (URank) getStandardProfile().getRanks().stream()
                                              .filter(ra -> ra.getApplicableServer().equalsIgnoreCase(s))
                                              .findAny()
                                              .orElse(null);

        if (r != null)
        {
            r.setGroup(group);
        }
        else
        {
            getStandardProfile().getRanks().add(new URank(group, s));
        }
    }

    public boolean isOnline()
    {
        return API.getUserManager().findByUniqueId(uniqueId) != null;
    }

    public void update()
    {
        API.getUserManager().update(this);

        API.getDataManager().getRedisMessageManager().sendUpdate("user", getUniqueId());
    }

    public String getEntityIdentifier()
    {
        return String.format("USER|%s", getUniqueId().toString());
    }
}
