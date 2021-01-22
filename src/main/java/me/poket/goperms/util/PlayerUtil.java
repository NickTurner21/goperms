package me.poket.goperms.util;

import io.gomint.entity.EntityPlayer;
import me.poket.goperms.GoPerms;
import me.poket.goperms.PermissionGroup;
import me.poket.goperms.PermissionPlayer;

import java.util.UUID;

public class PlayerUtil {

    public static void registerPlayer(EntityPlayer player) {
        if (GoPerms.players.containsKey(player.uuid())) {
            PermissionPlayer permissionPlayer = GoPerms.players.get(player.uuid());
            permissionPlayer.setOnline();
            GoPerms.players.put(player.uuid(), permissionPlayer);
        } else {
            PermissionPlayer permissionPlayer = GoPerms.players.get(player.uuid());
            GoPerms.players.put(player.uuid(), permissionPlayer);
        }
    }

    public static void setPlayerGroup(EntityPlayer player, String groupName) {
        if (!GoPerms.groups.containsKey(groupName)) return;
        PermissionGroup group = GoPerms.groups.get(groupName);
        PermissionPlayer permissionPlayer = GoPerms.players.get(player.uuid());
        permissionPlayer.addPermissions(group.getPermissions());
        GoPerms.players.put(player.uuid(), permissionPlayer);
    }

    public static void unregisterPlayer(EntityPlayer player) {
        if (!GoPerms.players.containsKey(player.uuid())) return;
        PermissionPlayer permissionPlayer = GoPerms.players.get(player.uuid());
        permissionPlayer.setOffline();
        GoPerms.players.put(player.uuid(), permissionPlayer);
    }
}