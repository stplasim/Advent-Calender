package net.bitnt.advent.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AdventTabCompletion implements TabCompleter {
    @Override
    // Executes when user types
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        // Check if the command has the correct signature
        if(command.getName().equalsIgnoreCase("advent") && args.length > 0) {

            // Check if the command comes from the player
            if(sender instanceof Player) {
                // Cast player form sender
                Player player = (Player) sender;

                // Create list of commands
                List<String> commandList = new ArrayList<>();

                // Check if player can use this plugin and add basic commands
                if(player.hasPermission("advent.use")) {
                    commandList.add("help");
                    commandList.add("help");
                }

                // Check if player is admin and add admin commands
                if(player.hasPermission("advent.admin")) {
                    commandList.add("admin");
                    commandList.add("set");
                    commandList.add("load");
                }

                // Return list
                return commandList;
            }
        }
        return null;
    }
}
