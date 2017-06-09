package be.woutdev.redistone.api.user.profile;

import be.woutdev.redistone.api.user.OfflineUser;

/**
 * Created by Wout on 4/06/2017.
 */
public interface Profile
{
    /**
     * Get the name of this Profile
     *
     * @return The name of the profile
     */
    String getProfileName();

    /**
     * Get the OfflineUser who owns this Profile
     *
     * @return The OfflineUser who owns this Profile
     */
    OfflineUser getUser();

    /**
     * Load in a profile and override all fields.
     * <p>
     * Should be implemented by each Profile implementation.
     *
     * @param profile The profile to be loaded in
     */
    void load(Profile profile);
}
