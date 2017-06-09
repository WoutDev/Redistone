package be.woutdev.redistone.entity;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.user.OfflineUser;
import be.woutdev.redistone.api.user.profile.Profile;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by Wout on 4/06/2017.
 */
@Entity
public abstract class BasicProfile implements Profile
{
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;
    @Column
    private UUID user;

    protected BasicProfile()
    {
    } // JPA requirement

    /**
     * Create a new empty Profile
     *
     * @param user The user to create the profile for
     */
    public BasicProfile(String name, OfflineUser user)
    {
        this.name = name;
        this.user = user.getUniqueId();
    }

    /**
     * Get the name of this Profile
     *
     * @return The name of the profile
     */
    public final String getProfileName()
    {
        return name;
    }

    /**
     * Get the OfflineUser owning the profile
     *
     * @return The OfflineUser who owns this profile
     */
    public final OfflineUser getUser()
    {
        return API.getUserManager().findByUniqueId(user) == null ? API.getUserManager()
                                                                      .findOfflineByUniqueId(
                                                                              user) : API.getUserManager()
                                                                                         .findByUniqueId(user);
    }

    /**
     * Load in a profile and override all fields.
     * <p>
     * Should be implemented by each Profile implementation.
     *
     * @param profile The profile to be loaded in
     */
    public abstract void load(Profile profile);
}
