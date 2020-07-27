package net.bitnt.advent.commands;

import net.bitnt.advent.Advent;
import net.bitnt.advent.calender.Day;
import net.bitnt.advent.calender.DayStatus;
import net.bitnt.advent.util.ConfigCalender;
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
            player.sendMessage("§cYou dont have the permission to open the calender admin menu");
            return;
        }

        // Safety first
        try {
            // Get block player is looking at
            Location chestLocation = player.getTargetBlock(null, 15).getLocation();
            BlockState blockState =  chestLocation.getBlock().getState();

            // Check if block is a chest
            if(blockState.getType() != Material.CHEST) {
                player.sendMessage("§c§lThis is not a chest!");
                player.sendMessage("§cUse a chest to create or update calender");
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
                    d.setGiftCommand(bookMeta.getPage(1));
                }
                else {
                    // Get item and save it to config
                    d.setGiftItem(item);
                }

                // Update the state of the day
                d.setStatus(DayStatus.READY);

                // Save to config
                new ConfigCalender(plugin, "Advent.Calender").updateSingleDay(d);
            }

            player.sendMessage("§aCalender created successful");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
