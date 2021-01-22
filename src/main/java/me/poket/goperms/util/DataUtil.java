package me.poket.goperms.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.poket.goperms.GoPerms;
import me.poket.goperms.PermissionGroup;
import me.poket.goperms.PermissionPlayer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DataUtil {

    public static void loadGroupsFromJson(String file) throws FileNotFoundException {
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, List<String>>>() {}.getType();
        Map<String, List<String>> groups = gson.fromJson(new FileReader(file), mapType);
        for (String group : groups.keySet()) {
            PermissionGroup newGroup = new PermissionGroup(group, groups.get(group));
            GoPerms.groups.put(group, newGroup);
        }
    }
    public static void loadUsersFromJson(String file) throws FileNotFoundException {
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, List<String>>>() {}.getType();
        Map<String, List<String>> users = gson.fromJson(new FileReader(file), mapType);
        for (String user : users.keySet()) {
            UUID uuid = UUID.fromString(user);
            PermissionPlayer permissionPlayer = new PermissionPlayer(uuid, users.get(user));
            GoPerms.players.put(uuid, permissionPlayer);
        }
    }

}
