package net.bitnt.advent.handler;

import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class FireworkHandler implements Listener {
    /**
     * Handle damage form advent firework
     */
    @EventHandler
    public void handleFireworkDamage(EntityDamageByEntityEvent event) {
        // Check if entity is instance of Fireworl
        if(event.getDamager() instanceof Firework) {

            // Cast firework
            Firework firework = (Firework) event.getDamager();

            // Check if firework has custom no damage meta data
            if(firework.hasMetadata("noDamage")) {
                event.setCancelled(true);
            }
        }
    }
}
