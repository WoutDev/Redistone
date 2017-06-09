package be.woutdev.redistone.natives.bungee.listener;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.profiles.core.UStandardProfile;
import be.woutdev.redistone.api.user.User;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Wout on 17/05/2017.
 */
public class PlayerSwitchServerEventListener implements Listener
{
    @EventHandler
    public void onPlayerSwitchServerEvent(ServerSwitchEvent e)
    {
        User user = API.getUserManager().findByUniqueId(e.getPlayer().getUniqueId());

        UStandardProfile profile = (UStandardProfile) user.getStandardProfile();
        profile.setCurrentServer(e.getPlayer().getServer().getInfo().getName().toLowerCase());

        user.update();
    }
}
