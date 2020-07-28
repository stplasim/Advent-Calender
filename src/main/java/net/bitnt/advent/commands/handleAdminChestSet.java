package net.bitnt.advent.commands;

import net.bitnt.advent.Advent;
import net.bitnt.advent.calender.Day;
import net.bitnt.advent.calender.DayStatus;
import net.bitnt.advent.statics.CommandFilter;
import net.bitnt.advent.util.ConfigLoader;
import net.bitnt.advent.statics.StaticMessages;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class handleAdminChestSet {
    /**
     * Load all items form chest into config
     *
     * @param plugin - Plugin instance
     * @param player - Player sender
     */
    public static void handleCommand(Advent plugin, Player player) {
        // Check if player has permission to access this command
        if(!player.hasPermission("advent.admin")) {
            player.sendMessage(StaticMessages.NO_COMMAND_PERMISSIONS);
            return;
        }

        // Safety first
        try {
            // Get block player is looking at
            Location chestLocation = player.getTargetBlock(null, 15).getLocation();
            BlockState blockState =  chestLocation.getBlock().getState();

            // Check if block is a chest
            if(blockState.getType() != Material.CHEST) {
                player.sendMessage(StaticMessages.NOT_A_CHEST_ERROR);
                return;
            }

            // Cast block to chest
            Chest chest = (Chest) blockState;

            // Get chest items
            ItemStack[] items = chest.getBlockInventory().getContents();

            // Loop over every slot and save to config (if possible)
            for (int i = 0; i < items.length; i++) {
                // Get single items and check if the slot is not empty => null
                ItemStack item = items[i];
                if(item == null) continue;

                // Create new day
                Day d = new Day((i + 1), i);

                // Check if item is a book
                // If it is a book its used for commands
                if(item.getType() == Material.WRITABLE_BOOK || item.getType() == Material.WRITTEN_BOOK) {
                    // Get book content and save it to config
                    BookMeta bookMeta = (BookMeta) item.getItemMeta();
                    String command = bookMeta.getPage(1);

                    // Check if command is allowed
                    if(CommandFilter.filter(command, player.getDisplayName())) {
                        player.sendMessage(StaticMessages.USED_BLOCKED_COMMAND);
                        return;
                    }

                    d.setGiftCommand(command);
                }
                else {
                    // Get item and save it to config
                    d.setGiftItem(item);
                }

                // Update the state of the day
                d.setStatus(DayStatus.READY);

                // Save to config
                new ConfigLoader(plugin, "Advent.Calender").updateSingleDay(d);
            }

            player.sendMessage(StaticMessages.CALENDAR_CREATED);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
