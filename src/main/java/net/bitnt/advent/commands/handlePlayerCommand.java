package net.bitnt.advent.commands;

import net.bitnt.advent.Advent;
import net.bitnt.advent.calender.Calender;
import net.bitnt.advent.calender.Day;
import net.bitnt.advent.util.ConfigLoader;
import net.bitnt.advent.statics.StaticMessages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class handlePlayerCommand {
    /**
     * Handle default advent command
     *
     * @param plugin - Plugin instance
     * @param player - Player sender
     */
    public static void handleCommand(Advent plugin, Player player) {
        // Check if the player has the permission
        if(!player.hasPermission("advent.use")) {
            player.sendMessage(StaticMessages.NO_COMMAND_PERMISSIONS);
            return;
        }

        // Create empty inventory
        Inventory calender = Bukkit.createInventory(null, 9*6, Calender.CALENDER_TITLE);

        // Create item for every day
        for(Day d : new ConfigLoader(plugin, "Advent.Calender").loadAllDays()) {
            calender.setItem(
                    d.getPosition(),
                    d.getDisplayItem()
            );
        }

        // Open gui
        player.openInventory(calender);
    }
}
