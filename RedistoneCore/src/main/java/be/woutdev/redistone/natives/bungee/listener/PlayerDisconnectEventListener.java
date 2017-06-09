package be.woutdev.redistone.natives.bungee.listener;

import be.woutdev.redistone.api.API;
import be.woutdev.redistone.impl.user.UUserManager;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Wout on 15/04/2017.
 */
public class PlayerDisconnectEventListener implements Listener
{
    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent e)
    {
        ((UUserManager) API.getUserManager()).handleQuit(e.getPlayer().getUniqueId());
    }
}
