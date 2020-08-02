package net.bitnt.advent.commands;

import net.bitnt.advent.Advent;
import net.bitnt.advent.statics.StaticMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdventCommand implements CommandExecutor {
    private Advent plugin;

    public AdventCommand(Advent plugin) {
        this.plugin = plugin;
    }

    // Handler for all advent commands
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if command comes form player
        if(!(sender instanceof Player)) return false;

        // Create player form sender
        Player player = (Player) sender;

        // Handle default advent command
        if(args.length == 0) {
            handlePlayerCommand.handleCommand(plugin, player);
        }

        // Handle admin commands
        else if(args.length == 1) {

            // Display admin overview
            switch (args[0]) {
                case "admin":
                    handleAdminOverviewCommand.handleCommand(plugin, player);
                    break;

                // Load items for chest into config
                case "set":
                    handleAdminChestSet.handleCommand(plugin, player);
                    break;

                // Load items for chest into config
                case "load":
                    handleAdminChestLoad.handleCommand(plugin, player);
                    break;

                // Display help page to user
                case "help":
                    handleHelpCommand.handleCommand(player);
                    break;

                case "info":
                    handleInfoCommand.handleCommand(player);
                    break;

                default:
                    player.sendMessage(StaticMessages.COMMAND_NOT_FOUND_ERROR);
                    break;

            }
        }
        else {
            // Display error if needed
            player.sendMessage(StaticMessages.COMMAND_NOT_FOUND_ERROR);
            return false;
        }

        return false;
    }
}
