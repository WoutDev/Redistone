package be.woutdev.redistone.api.user.profile;

import be.woutdev.redistone.api.entity.IPEntity;
import be.woutdev.redistone.api.permission.Rank;
import be.woutdev.redistone.api.warning.Warning;

import java.util.Set;

/**
 * Created by Wout on 6/06/2017.
 */
public interface StandardProfile extends Profile
{
    long getJoinDate();

    long getLastSeenDate();

    void setLastSeenDate(long lastSeen);

    String getUsername();

    void setUsername(String username);

    Set<Rank> getRanks();

    Set<IPEntity> getIPs();

    IPEntity getMostRecentIP();

    void setMostRecentIP(IPEntity entity);

    String getCurrentServer();

    void setCurrentServer(String server);

    Set<Warning> getWarnings();
}
