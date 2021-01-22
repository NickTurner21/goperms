package me.poket.goperms;

public class PermissionItem {

    private final String name;
    private final Boolean allowed;

    public static PermissionItem createPermission(String permission) {
        if (permission.startsWith("-")) return new PermissionItem(permission.substring(1), false);
        return new PermissionItem(permission, true);
    }

    public PermissionItem(String permission, Boolean allowed) {
        this.name = permission;
        this.allowed = allowed;
    }

    public String getName() {
        return this.name;
    }

    public boolean isAllowed() {
        return this.allowed;
    }

    @Override
    public String toString() {
        return this.allowed ? this.name : "-" + this.name;
    }
}
