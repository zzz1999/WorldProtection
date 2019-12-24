package net.mcbbs.zzz1999;


import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class WorldProtection extends PluginBase implements Listener {
    static Config config;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);

        config = new Config(new File(this.getDataFolder(), "Config.yml"), Config.YAML, new ConfigSection(new LinkedHashMap<String, Object>() {{
            put("World", new ArrayList<String>());
            put("Admin", new ArrayList<String>());
        }}));
        this.getServer().getCommandMap().register("worldprotection", new CommandListener());
        this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
    }

    boolean isProtectWorld(String world) {
        return config.getStringList("World").contains(world);
    }

    boolean isAdmin(String name) {
        return config.getStringList("Admin").contains(name);
    }

}
