package me.poket.goperms;

import io.gomint.GoMint;
import io.gomint.permission.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionGroup {

    private String name;
    private Map<String, PermissionItem> permissions = new HashMap<>();
    private Group group;

    public PermissionGroup(String name, List<String> perms){
        this.name = name;
        Group group = GoMint.instance().groupManager().group(name);
        this.group = group;
        for (String perm : perms) {
            PermissionItem item = PermissionItem.createPermission(perm);
            group.permission(item.getName(), item.isAllowed());
            this.permissions.put(item.getName(), item);
        }
    }

    public List<PermissionItem> getPermissions() {
        return new ArrayList<>(this.permissions.values());
    }

    public Group getGroup() {
        return group;
    }

}
