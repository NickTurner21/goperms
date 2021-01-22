package me.poket.goperms;


import io.gomint.plugin.*;
import me.poket.goperms.listeners.PlayerJoinListener;
import me.poket.goperms.listeners.PlayerLeaveListener;
import me.poket.goperms.util.DataUtil;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@PluginName("GoPerms")
@Version(major = 1, minor = 0)
@Startup(StartupPriority.STARTUP)
public class GoPerms extends Plugin {

    private static Plugin plugin;
    private static Logger logger;
    public static Map<String, PermissionGroup> groups = new HashMap<>();
    public static Map<UUID, PermissionPlayer> players = new HashMap<>();

    public static Plugin getPlugin() {
        return plugin;
    }


    @Override
    public void onInstall() {
        plugin = this;
        logger = logger();
        logger.info("GoPerms starting");
        if (!dataFolder().exists()) dataFolder().mkdir();
        registerListener( new PlayerJoinListener() );
        registerListener( new PlayerLeaveListener() );
        try {
            DataUtil.loadGroupsFromJson(this.dataFolder().getAbsolutePath() + "/groups.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            DataUtil.loadUsersFromJson(this.dataFolder().getAbsolutePath() + "/users.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
