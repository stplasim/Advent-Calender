package net.bitnt.advent.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AdventTabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(command.getName().equalsIgnoreCase("advent") && args.length > 0) {
            if(sender instanceof Player) {
                Player player = (Player) sender;

                List<String> commandList = new ArrayList<>();

                commandList.add("help");

                if(player.hasPermission("advent.admin")) {
                    commandList.add("admin");
                    commandList.add("set");
                    commandList.add("load");
                }

                return commandList;
            }
        }
        return null;
    }
}
