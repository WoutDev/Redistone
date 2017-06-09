package be.woutdev.redistone.natives.bukkit.listener;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.impl.user.UUserManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Wout on 16/04/2017.
 */
public class PlayerJoinListener implements Listener
{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        ((UUserManager) API.getUserManager()).handleJoin(e.getPlayer().getUniqueId());
    }
}
