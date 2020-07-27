package net.bitnt.advent.handler;

import net.bitnt.advent.Advent;
import net.bitnt.advent.calender.Calender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AdminOverviewHandler implements Listener {
    private Advent plugin;

    /**
     * Handle clicks in admin panel
     *
     * @param plugin - Plugin instance
     */
    public AdminOverviewHandler(Advent plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handleAdminGUIInteract(InventoryClickEvent event) {
        // Check if click comes from player
        if(!((event.getWhoClicked()) instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();

        // Check if player has permission
        if(!player.hasPermission("advent.admin")) {
            player.sendMessage("§cYou dont have the permission to open the calender admin menu");
            return;
        }

        // Handle clicks
        if(event.getView().getTitle().equals(Calender.CALENDER_TITLE_ADMIN)) {
            try {
                player.closeInventory();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
