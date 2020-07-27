package net.bitnt.advent.commands;

import net.bitnt.advent.Advent;
import net.bitnt.advent.calender.Calender;
import net.bitnt.advent.calender.Day;
import net.bitnt.advent.util.ConfigCalender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class handleAdminOverviewCommand {
    /**
     * Open admin overview of every day
     *
     * @param plugin - Plugin instance
     * @param player - Player sender
     */
    public static void handleCommand(Advent plugin, Player player) {
        // Check if player has the permission
        if(!player.hasPermission("advent.admin")) {
            player.sendMessage("§cYou dont have the permission to open the calender admin menu");
            return;
        }

        // Create new empty inventory
        Inventory panel = Bukkit.createInventory(
                null,
                9*4,
                Calender.CALENDER_TITLE_ADMIN);


        // Get days form config
        Day[] days = new ConfigCalender(plugin, "Advent.Calender").loadAllDays();

        // Create items for day
        for (int i = 0; i < days.length; i++) {
            panel.setItem(i, days[i].getDisplayItem("§7Status: §l" + days[i].getStatus())
            );
        }

        // Open gui
        player.openInventory(panel);
    }
}
