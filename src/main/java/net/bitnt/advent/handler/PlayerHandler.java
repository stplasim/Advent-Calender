package net.bitnt.advent.handler;

import net.bitnt.advent.Advent;
import net.bitnt.advent.calender.Calender;
import net.bitnt.advent.calender.Day;
import net.bitnt.advent.util.PlayerDoorUse;
import net.bitnt.advent.util.ConfigCalender;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.FireworkMeta;

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
        if(event.getView().getTitle().equals(Calender.CALENDER_TITLE)) {
            // Handle potential errors
            try {
                // Check if day is configured
                if(event.getCurrentItem().getType() == Material.BARRIER) {
                    player.sendMessage("§cOops. It looks like this day has not yet been configured.");
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
                PlayerDoorUse pu = new PlayerDoorUse(plugin, player, dayId);

                // Check if user hasn't already onend this door
                // TODO: Check if day isn't over
                if(pu.hasAlreadyOpenedDay()) {
                    player.sendMessage("§c§lYou already have got your gift for today");
                    player.closeInventory();
                    return;
                }

                // Get selected day
                Day day = new ConfigCalender(plugin, "Advent.Calender").getSingleDay(dayId);

                // Check if gif item is not null
                // If item is present give it to user
                // TODO: Check if user inventory is full
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
                pu.savePlayerAction();

                // Close inventory
                player.closeInventory();

                // Spawn firework effect
                spawnFirework(player).detonate();

                // Print message to user
                player.sendMessage("§a§lYay happy advent!!");
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
    private Firework spawnFirework(Player player) {
        // Get user position
        Location playerLocation = player.getLocation();

        // Create firework entity
        Firework firework = (Firework) playerLocation
                .getWorld()
                .spawnEntity(playerLocation, EntityType.FIREWORK);

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
}
