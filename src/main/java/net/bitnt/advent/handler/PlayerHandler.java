package net.bitnt.advent.handler;

import net.bitnt.advent.Advent;
import net.bitnt.advent.calender.Calendar;
import net.bitnt.advent.calender.Day;
import net.bitnt.advent.util.PlayerDayLoader;
import net.bitnt.advent.util.DayDataLoader;
import net.bitnt.advent.statics.StaticMessages;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class PlayerHandler implements Listener {
    private Advent plugin;

    public PlayerHandler(Advent plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handlePlayerCalenderInteract(InventoryClickEvent event) {
        // Check if click comes from player
        if(!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();

        // Handle item click
        if(event.getView().getTitle().equals(Calendar.CALENDER_TITLE)) {
            // Handle potential errors
            try {
                // Check if day is configured
                if(event.getCurrentItem().getType() == Material.BARRIER) {
                    player.sendMessage(StaticMessages.DAY_NOT_READY_ERROR);
                    player.closeInventory();
                    return;
                }
                // Check if day is still in the future
                else if(event.getCurrentItem().getType() == Material.CHEST) {
                    player.sendMessage(StaticMessages.CALENDER_UPCOMING);
                    player.closeInventory();
                    return;
                }
                // Check if day is already over
                else if(event.getCurrentItem().getType() == Material.ENDER_CHEST) {
                    player.sendMessage(StaticMessages.CALENDER_OVER);
                    player.closeInventory();
                    return;
                }

                // Check if player has some empty slots in the inventory
                // This is still WIP. This might not work on other versions
                if(!hasAvailableSlot(player)) {
                    player.sendMessage(StaticMessages.INVENTORY_FULL);
                    player.closeInventory();
                    return;
                }

                // Get id of selected door
                int dayId = Integer.parseInt( // Parse string to int
                        event.getCurrentItem()
                                .getItemMeta()
                                .getDisplayName()
                                .replace("Day ", "") // Remove text
                                .replace("§6", "") // Remove color code
                );

                // Get Player door usage helper class
                PlayerDayLoader dayLoader = new PlayerDayLoader(plugin, player, dayId);

                // Check if user hasn't already onend this door
                if(dayLoader.hasAlreadyOpenedDay()) {
                    player.sendMessage(StaticMessages.DAY_USED_ERROR);
                    player.closeInventory();
                    return;
                }

                // Get selected day
                Day day = new DayDataLoader(plugin, "Advent.Calendar").getSingleDay(dayId);

                // Check if gif item is not null
                // If item is present give it to user
                if(day.getGiftItem() != null) {
                    player.getInventory().addItem(day.getGiftItem());
                }

                // Check if git command is not null
                // If command is preset execute on the server
                // NOTE! Only commands that the server can execute live give can be executed
                if(day.getGiftCommand() != null) {
                    // Get command
                    String command = day.getGiftCommand().replace("<PLAYER>", player.getDisplayName());
                    // Create command sender and execute command on the server
                    ConsoleCommandSender commandSender = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(commandSender, command);
                }

                // Save player in the log file of the day
                // This prevents the user form opening the door more the once
                dayLoader.savePlayerAction();

                // Close inventory
                player.closeInventory();

                // Spawn firework effect
                spawnFirework(plugin, player).detonate();

                // Print message to user
                player.sendMessage(StaticMessages.GIFT_GIVEN);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get firework effect
     *
     * @param player - Player instance
     * @return Firework
     */
    private Firework spawnFirework(Advent plugin, Player player) {
        // Get user position
        Location playerLocation = player.getLocation();

        // Create firework entity
        Firework firework = (Firework) playerLocation
                .getWorld()
                .spawnEntity(playerLocation, EntityType.FIREWORK);

        // Set custom no damage meta
        firework.setMetadata("noDamage", new FixedMetadataValue(plugin, true));

        // Create firework meta
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.setPower(2);
        fireworkMeta.addEffect(
                FireworkEffect
                        .builder()
                        .withColor(Color.RED)
                        .withColor(Color.WHITE)
                        .withTrail()
                        .flicker(true)
                        .build()
        );
        // Add meta back to firework entity and return it
        firework.setFireworkMeta(fireworkMeta);
        return firework;
    }

    /**
     * Check if player has empty slots in inventory
     * True = yes
     * False = no
     *
     * WARNING! This function is not fully working yet
     * NOTE! This might not work on other versions
     *
     * @param player - Player instance
     * @return boolean
     */
    private boolean hasAvailableSlot(Player player){
        // Slots form 0 to 35 are the player inventory
        // The other ones are for armor and the crafting table
        for (int i = 0; i < 35; i++) {
            // Get current item
            ItemStack item = player.getInventory().getItem(i);

            // Check if item is not null
            if(item == null) {
                return true;
            }
        }
        return false;
    }
}
