package net.bitnt.advent.commands;

import net.bitnt.advent.Advent;
import net.bitnt.advent.calender.Day;
import net.bitnt.advent.util.ConfigLoader;
import net.bitnt.advent.util.ItemBuilder;
import net.bitnt.advent.statics.StaticMessages;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class handleAdminChestLoad {
    public static void handleCommand(Advent plugin, Player player) {
        // Check if player has permission to access this command
        if(!player.hasPermission("advent.admin")) {
            player.sendMessage(StaticMessages.NO_COMMAND_PERMISSIONS);
            return;
        }

        // Safety first
        try {
            // Create chest at player position
            Block chestBlock = player.getLocation().getBlock();
            chestBlock.setType(Material.CHEST);

            // Cast block to chest
            Chest chest = (Chest) chestBlock.getState();

            // Get days form config
            Day[] days = new ConfigLoader(plugin, "Advent.Calender").loadAllDays();

            // Create items for day
            for(Day day : days) {
                // If item is not null, set item
                if(day.getGiftItem() != null) {
                    chest.getBlockInventory().setItem((day.getDay() - 1), day.getGiftItem());
                }

                // If commend is not null, set book with command
                else if (day.getGiftCommand() != null) {
                    // Create book
                    ItemStack book = new ItemBuilder(Material.WRITABLE_BOOK)
                            .setName("§bCommand")
                            .build();

                    // Create book meta
                    BookMeta bookMeta = (BookMeta) book.getItemMeta();

                    // Add Command to book
                    assert bookMeta != null;
                    bookMeta.setPages(day.getGiftCommand());

                    // Set meta to book
                    book.setItemMeta(bookMeta);

                    // Add to book in chest
                    chest.getBlockInventory().setItem((day.getDay() - 1), book);
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
 }
