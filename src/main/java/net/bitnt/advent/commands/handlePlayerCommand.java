package net.bitnt.advent.commands;

import net.bitnt.advent.Advent;
import net.bitnt.advent.calender.Calender;
import net.bitnt.advent.calender.Day;
import net.bitnt.advent.util.ConfigCalender;
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
            player.sendMessage("§cYou dont have the permission to open the calender");
            return;
        }

        // Create empty inventory
        Inventory calender = Bukkit.createInventory(null, 9*6, Calender.CALENDER_TITLE);

        // Create item for every day
        for(Day d : new ConfigCalender(plugin, "Advent.Calender").loadAllDays()) {
            calender.setItem(
                    d.getPosition(),
                    d.getDisplayItem()
            );
        }

        // Open gui
        player.openInventory(calender);
    }
}
