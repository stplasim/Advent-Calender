package net.bitnt.advent.commands;

import net.bitnt.advent.Advent;
import net.bitnt.advent.calender.Calendar;
import net.bitnt.advent.calender.Day;
import net.bitnt.advent.calender.DayStatus;
import net.bitnt.advent.util.DayDataLoader;
import net.bitnt.advent.statics.StaticMessages;
import net.bitnt.advent.util.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.time.LocalDate;
import java.time.Month;

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

        // Get the active month from the config
        Month activeMonth = new ConfigLoader(plugin, "Advent.Timing").getActiveMonth();

        // Check if the current month is the active month
        // If that is not the case return an error to the user
        if(LocalDate.now().getMonth() != activeMonth) {
            player.sendMessage(StaticMessages.CALENDAR_NOT_ACTIVE(activeMonth));
            return;
        }

        // Create empty inventory
        Inventory calender = Bukkit.createInventory(null, 9*6, Calendar.CALENDER_TITLE);

        int currantDay = LocalDate.now().getDayOfMonth();

        // Create item for every day
        for(Day d : new DayDataLoader(plugin, "Advent.Calendar").loadAllDays()) {
            if(d.getStatus() != DayStatus.NONE) {
                if(currantDay == d.getDay()) {
                    d.setStatus(DayStatus.READY);
                }
                else if(currantDay > d.getDay()) {
                    d.setStatus(DayStatus.OVER);
                }
                else if(currantDay < d.getDay()) {
                    d.setStatus(DayStatus.UPCOMING);
                }
            }

            calender.setItem(
                    d.getPosition(),
                    d.getDisplayItem()
            );
        }

        // Open gui
        player.openInventory(calender);
    }
}
