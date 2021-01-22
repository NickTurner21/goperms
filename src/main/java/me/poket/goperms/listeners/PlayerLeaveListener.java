package me.poket.goperms.listeners;

import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventPriority;
import io.gomint.event.player.PlayerQuitEvent;
import me.poket.goperms.util.PlayerUtil;
import io.gomint.event.EventListener;


public class PlayerLeaveListener implements EventListener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        EntityPlayer player = event.player();
        PlayerUtil.unregisterPlayer(player);
    }

}
