package me.poket.goperms;

import io.gomint.GoMint;
import io.gomint.entity.EntityPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PermissionPlayer {

    private Boolean isOnline = false;
    private UUID uuid;
    private EntityPlayer player;
    private List<PermissionItem> permissionsToInit = new ArrayList<>();
    private List<PermissionItem> permissions = new ArrayList<>();


    public PermissionPlayer(UUID uuid) {
        this.uuid = uuid;
        this.player = GoMint.instance().findPlayerByUUID(uuid);
        if (this.player != null) isOnline = true;
    }
    public PermissionPlayer(UUID uuid, List<String> perms) {
        this.uuid = uuid;
        this.player = GoMint.instance().findPlayerByUUID(uuid);
        if (this.player != null) {
            isOnline = true;
        }
        registerPermissions(perms);
    }

    public void setOnline() {
        this.isOnline = true;
        this.player = GoMint.instance().findPlayerByUUID(this.uuid);
        for (PermissionItem perm : this.permissionsToInit) {
            this.player.permissionManager().permission(perm.getName(), perm.isAllowed());
        }
        this.permissions.addAll(this.permissionsToInit);
        this.permissionsToInit.clear();
    }

    public void setOffline() {
        this.isOnline = false;
        this.player = null;
        this.permissionsToInit.addAll(this.permissions);
        this.permissions.clear();
    }

    public void addPermissions(List<PermissionItem> perms) {
        for (PermissionItem item : perms) {
            addPermission(item);
        }
    }

    private void addPermission(PermissionItem item) {
        if (!this.isOnline) {
            permissionsToInit.add(item);
        } else {
            permissions.add(item);
            this.player.permissionManager().permission(item.getName(), item.isAllowed());
        }
    }

    private void registerPermissions(List<String> perms) {
        for (String perm : perms) {
            if (!this.isOnline) {
                permissionsToInit.add(PermissionItem.createPermission(perm));
            } else {
                permissions.add(PermissionItem.createPermission(perm));
            }
        }
    }




}
