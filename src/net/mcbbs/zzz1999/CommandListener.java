package net.mcbbs.zzz1999;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.util.ArrayList;
import java.util.List;

public class CommandListener extends Command {

    CommandListener() {
        super("wp", "世界保护主命令", "wp <world | admin> <add | remove>", new String[]{"worldprotection"});
        this.setPermission("WorldProtection.command.wp");
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
                new CommandParameter("world | admin", new String[]{"world", "admin"}),
                new CommandParameter("add | remove", new String[]{"add", "remove"}),
                new CommandParameter("target", CommandParamType.STRING, false),
        });
        this.commandParameters.put("list", new CommandParameter[]{
                new CommandParameter("list", new String[]{"list"}),
        });

        this.commandParameters.put("help", new CommandParameter[]{
                new CommandParameter("help", new String[]{"help"}),
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!this.testPermission(sender)) {
            return true;
        }
        if (args.length == 3) {
            switch (args[0]) {
                case "world":
                    switch (args[1]) {
                        case "add": {
                            Config config = WorldProtection.config;
                            if (((List<String>) config.get("World")).contains(args[2])) {
                                sender.sendMessage("[WorldProtection] 该世界已经在世界保护名单中");
                            } else {
                                List<String> list = config.getStringList("World");
                                list.add(args[2]);
                                config.set("World", list);
                                config.save();
                                sender.sendMessage("[WorldProtection] " + args[2] + "已经加入世界保护名单");
                            }
                            break;
                        }
                        case "remove": {
                            Config config = WorldProtection.config;
                            if (((List<String>) config.get("World")).contains(args[2])) {
                                List<String> list = config.getStringList("World");
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
                            Config config = WorldProtection.config;
                            if (config.getStringList("Admin").contains(args[2])) {
                                sender.sendMessage("[WorldProtection] 该玩家已经在世界保护管理员列表中");
                            } else {
                                ArrayList<String> list = ((ArrayList<String>) config.getStringList("Admin"));
                                list.add(args[2]);
                                config.set("Admin", list);
                                config.save();
                                sender.sendMessage("[WorldProtection] " + args[2] + "已经加入世界保护管理员列表");
                            }
                            break;
                        }
                        case "remove": {
                            Config config = WorldProtection.config;
                            if (config.getStringList("Admin").contains(args[2])) {
                                List<String> list = config.getStringList("Admin");
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

        } else if (args.length == 1) {
            sender.sendMessage(TextFormat.GOLD + "受保护的世界列表:");
            List<String> WorldList = WorldProtection.config.getStringList("World");
            for (String wl : WorldList) {
                sender.sendMessage(TextFormat.BLUE + " -" + wl);
            }
            sender.sendMessage(TextFormat.GOLD + "世界管理员列表:");
            List<String> AdminList = WorldProtection.config.getStringList("Admin");
            for (String al : AdminList) {
                sender.sendMessage(TextFormat.BLUE + " -" + al);
            }
            return true;
        } else {
            sender.sendMessage("用法: " + this.getUsage());
            return true;
        }
    }
}