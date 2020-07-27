package net.bitnt.advent.commands;

import net.bitnt.advent.Advent;
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
            if(args[0].equals("admin")) {
                handleAdminOverviewCommand.handleCommand(plugin, player);
            }

            // Load items for chest into config
            else if(args[0].equals("set")) {
                handleAdminChestSet.handleCommand(plugin, player);
            }

            // Load items for chest into config
            else if(args[0].equals("load")) {
                handleAdminChestLoad.handleCommand(plugin, player);
            }
        }
        else {
            // Display error if needed
            player.sendMessage("§cPlease use §7/advent");
            return false;
        }

        return false;
    }
}
