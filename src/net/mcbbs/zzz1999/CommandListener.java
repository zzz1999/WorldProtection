package net.mcbbs.zzz1999;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.Config;

import java.util.ArrayList;

public class CommandListener extends Command {

    private WorldProtection plugin;

    public CommandListener(WorldProtection plugin) {
        super("wp","世界保护主命令","wp <world | admin> <add | remove>",new String[]{"worldprotection"});
        this.setPermission("WorldProtection.command.wp");
        this.plugin=plugin;
        this.commandParameters.clear();
        this.commandParameters.put("default",new CommandParameter[]{
                new CommandParameter("world | admin",new String[]{"world","admin"}),
                new CommandParameter("add | remove",new String[]{"add","remove"}),
                new CommandParameter("target",CommandParameter.ARG_TYPE_STRING,false),
        });

        this.commandParameters.put("help",new CommandParameter[]{
                new CommandParameter("help",new String[]{"help"}),
        });


    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!this.testPermission(sender)) {
            return true;
        }
        if(args.length==3){
            switch(args[0]){
                case "world":
                    switch (args[1]) {
                        case "add": {
                            Config config = this.plugin.config;
                            if (((ArrayList<String>) config.get("World")).contains(args[2])) {
                                sender.sendMessage("[WorldProtection] 该世界已经在世界保护名单中");
                            } else {
                                ArrayList<String> list = ((ArrayList<String>) config.get("World"));
                                list.add(args[2]);
                                config.set("World", list);
                                config.save();
                                sender.sendMessage("[WorldProtection] "+args[2] + "已经加入世界保护名单");
                            }
                            break;
                        }
                        case "remove": {
                            Config config = this.plugin.config;
                            if (((ArrayList<String>) config.get("World")).contains(args[2])) {
                                ArrayList<String> list =((ArrayList<String>) config.get("World"));
                                list.remove(args[2]);
                                config.set("World", list);
                                config.save();
                                sender.sendMessage("[WorldProtection] 已经将" + args[2] + "移除出世界保护列表");
                            } else {
                                sender.sendMessage("[WorldProtection] 该世界不存在与世界保护列表中");
                            }
                            break;
                        }
                        default:
                            sender.sendMessage("[WorldProtection] 请输入/wp help 查看全部命令");

                            break;
                    }

                    break;
                case "admin":
                    switch (args[1]) {
                        case "add": {
                            Config config = this.plugin.config;
                            if (((ArrayList<String>) config.get("Admin")).contains(args[2])) {
                                sender.sendMessage("[WorldProtection] 该玩家已经在世界保护管理员列表中");
                            } else {
                                ArrayList<String> list = ((ArrayList<String>) config.get("Admin"));
                                list.add(args[2]);
                                config.set("Admin", list);
                                config.save();
                                sender.sendMessage("[WorldProtection] "+args[2] + "已经加入世界保护管理员列表");
                            }
                            break;
                        }
                        case "remove": {
                            Config config = this.plugin.config;
                            if (((ArrayList<String>) config.get("Admin")).contains(args[2])) {
                                ArrayList<String> list = ((ArrayList<String>) config.get("Admin"));
                                list.remove(args[2]);
                                config.set("Admin", list);
                                config.save();
                                sender.sendMessage("[WorldProtection] 已经将" + args[2] + "移除出世界保护管理员列表");
                            } else {
                                sender.sendMessage("[WorldProtection] 该世界不存在与世界保护管理员列表中");
                            }
                            break;
                        }
                        default:
                            sender.sendMessage("[WorldProtection] 请输入/wp help 查看全部命令");

                            break;
                    }
                    break;
            }
            return true;

        }else{
            sender.sendMessage("用法: "+this.getUsage());
            return true;
        }
    }

}