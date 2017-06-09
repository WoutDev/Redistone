package be.woutdev.redistone.natives.bungee.listener;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.profiles.core.UStandardProfile;
import be.woutdev.redistone.entity.UIPEntity;
import be.woutdev.redistone.impl.user.UUser;
import be.woutdev.redistone.impl.user.UUserManager;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Wout on 15/04/2017.
 */
public class ServerConnectEventListener implements Listener
{
    @EventHandler
    public void onServerConnect(ServerConnectEvent e)
    {
        boolean allow = ((UUserManager) API.getUserManager()).handleJoin(e.getPlayer().getUniqueId());

        if (!allow)
        {
            e.setCancelled(true);
            return;
        }

        UUser u = (UUser) API.getUserManager().findByUniqueId(e.getPlayer().getUniqueId());
        UStandardProfile profile = (UStandardProfile) u.getStandardProfile();

        profile.setUsername(e.getPlayer().getName());
        profile.setMostRecentIP(new UIPEntity(e.getPlayer().getAddress().getAddress().getHostAddress()));
        profile.getIPs().add(new UIPEntity(e.getPlayer().getAddress().getAddress().getHostAddress()));
        profile.setLastSeenDate(System.currentTimeMillis());

        u.update();
    }
}
