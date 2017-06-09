package be.woutdev.redistone.impl.user;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.module.command.CommandCaller;
import be.woutdev.redistone.api.permission.Group;
import be.woutdev.redistone.api.profiles.core.UStandardProfile;
import be.woutdev.redistone.api.user.OfflineUser;
import be.woutdev.redistone.api.user.User;
import be.woutdev.redistone.api.user.profile.Profile;
import be.woutdev.redistone.entity.UOfflineUser;
import be.woutdev.redistone.entity.permission.URank;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by Wout on 14/04/2017.
 */
public class UUser extends UOfflineUser implements User, OfflineUser, CommandCaller
{
    public UUser(UUID uniqueId)
    {
        super(uniqueId);

        getProfiles().add(new UStandardProfile(this));

        // getStandardProfile().getRanks().add(new URank(Group.MEMBER, "___GLOBAL___"));
        // TODO: Replacement for this
    }

    public UUser(UUID uniqueId, Collection<Profile> profiles)
    {
        super(uniqueId, profiles);
    }

    public void load(OfflineUser user)
    {
        user.getProfiles().forEach((profile) ->
                                   {
                                       if (getProfile(profile.getProfileName()) == null)
                                       {
                                           getProfiles().add(profile);
                                           return;
                                       }

                                       getProfile(profile.getProfileName()).load(profile);
                                   });
    }

    @Override
    public void kick(String s)
    {
        API.getPlugin().kick(this, s);
    }

    @Override
    public void sendMessage(String s)
    {
        API.getPlugin().sendMessage(this, s);
    }

    @Override
    public void sendUnformattedMessage(String s)
    {
        API.getPlugin().sendMessage(this, s);
    }
}
