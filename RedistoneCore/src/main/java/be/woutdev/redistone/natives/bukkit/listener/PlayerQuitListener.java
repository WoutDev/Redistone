package be.woutdev.redistone.natives.bukkit.listener;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.api.user.User;
import be.woutdev.redistone.impl.user.UUserManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Wout on 16/04/2017.
 */
public class PlayerQuitListener implements Listener
{
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        User user = API.getUserManager().findByUniqueId(e.getPlayer().getUniqueId());

        ((UUserManager) API.getUserManager()).handleQuit(user.getUniqueId());

        user.update();
    }
}
