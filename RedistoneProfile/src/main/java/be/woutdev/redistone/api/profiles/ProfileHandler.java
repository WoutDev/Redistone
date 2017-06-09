package be.woutdev.redistone.api.profiles;

import be.woutdev.redistone.api.profiles.core.UStandardProfile;
import be.woutdev.redistone.api.user.profile.Profile;

import java.util.HashMap;

/**
 * Created by Wout on 15/05/2017.
 */
public class ProfileHandler
{
    private final HashMap<String, Class<? extends Profile>> profiles;

    public ProfileHandler()
    {
        profiles = new HashMap<>();

        profiles.put("standard", UStandardProfile.class);
    }

    public HashMap<String, Class<? extends Profile>> getProfiles()
    {
        return profiles;
    }
}
