package me.poket.goperms.listeners;

import io.gomint.entity.EntityPlayer;
import io.gomint.event.EventHandler;
import io.gomint.event.EventListener;
import io.gomint.event.EventPriority;
import io.gomint.event.player.PlayerJoinEvent;

import me.poket.goperms.util.PlayerUtil;

public class PlayerJoinListener implements EventListener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        EntityPlayer player = event.player();
        PlayerUtil.registerPlayer(player);
        PlayerUtil.setPlayerGroup(player, "default");
    }
}
