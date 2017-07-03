package net.mcbbs.zzz1999;


import cn.nukkit.event.Listener;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class WorldProtection extends PluginBase implements Listener {
    Config config;

    @Override
    @SuppressWarnings({"unchecked","deprecation"})
    public void onEnable(){
        this.getServer().getPluginManager().registerEvents(this,this);

        this.config = new Config(new File(this.getDataFolder(), "Config.yml"), Config.YAML,new LinkedHashMap<String, Object>(){{
            put("World",new ArrayList<String>());
            put("Admin",new ArrayList<String>());
        }});
        this.getServer().getCommandMap().register("wp", new CommandListener(this));
        this.getServer().getPluginManager().registerEvents(new EventListener(this),this);
    }
    @SuppressWarnings({"unchecked","deprecation"})
    boolean isProtectWorld(String world){
        return ((ArrayList<String>) this.config.get("World")).contains(world);
    }
    @SuppressWarnings({"unchecked","deprecation"})
    boolean isAdmin(String name){
        return ((ArrayList<String>) this.config.get("Admin")).contains(name);
    }

}
