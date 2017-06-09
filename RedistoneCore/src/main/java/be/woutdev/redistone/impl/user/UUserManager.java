package be.woutdev.redistone.impl.user;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.ban.Ban;
import be.woutdev.redistone.api.module.event.events.UserConnectedEvent;
import be.woutdev.redistone.api.module.event.events.UserDisconnectedEvent;
import be.woutdev.redistone.api.user.OfflineUser;
import be.woutdev.redistone.api.user.User;
import be.woutdev.redistone.api.user.UserManager;
import be.woutdev.redistone.entity.UOfflineUser;
import be.woutdev.redistone.impl.data.storage.UDataManager;
import be.woutdev.redistone.impl.data.storage.adapter.model.generic.GenericUserDao;
import be.woutdev.redistone.impl.util.UUIDFetcher;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by Wout on 14/04/2017.
 */
public class UUserManager implements UserManager
{
    private final GenericUserDao dao;
    private final List<User> users;

    public UUserManager(UDataManager manager)
    {
        this.dao = manager.getUserDao();
        this.users = Lists.newArrayList();
    }

    public User findByUniqueId(UUID uuid)
    {
        return users.stream().filter(u -> u.getUniqueId().equals(uuid)).findFirst().orElse(null);
    }

    public ImmutableList<User> findAllUsers()
    {
        return ImmutableList.copyOf(users);
    }

    public OfflineUser findOfflineByUniqueId(UUID uuid)
    {
        return dao.find(uuid);
    }

    public List<OfflineUser> findAllUsersAnyway()
    {
        return new ArrayList<>(dao.findAll());
    }

    public OfflineUser findOfflineByName(String s)
    {
        try
        {
            return findOfflineByUniqueId(new UUIDFetcher(Collections.singletonList(s)).call().get(s));
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public void update(OfflineUser user)
    {
        getDao().update((UOfflineUser) user);
    }

    public GenericUserDao getDao()
    {
        return dao;
    }

    public List<User> getUsers()
    {
        return users;
    }

    public boolean handleJoin(UUID uniqueId)
    {
        UOfflineUser user = getDao().find(uniqueId);
        Ban ban = null;

        if (user == null)
        {
            user = new UUser(uniqueId);

            getDao().insert(user);
        }
        else
        {
            user = new UUser(uniqueId, user.getProfiles());

            ban = API.getBanManager().getBan(user);

            if (ban == null || !ban.isActive())
            {
                ban = API.getBanManager().getBan(user.getStandardProfile().getMostRecentIP());
            }
        }

        if (ban != null && ban.isActive())
        {
            ((User) user).kick(API.getBanManager().getBanMessage(ban));

            return false;
        }

        getUsers().add((User) user);

        API.getModuleManger().triggerEvent(new UserConnectedEvent((User) user));

        return true;
    }

    public void handleQuit(UUID uniqueId)
    {
        UUser user = (UUser) findByUniqueId(uniqueId);

        getUsers().remove(user);

        API.getModuleManger().triggerEvent(new UserDisconnectedEvent(user));
    }
}
