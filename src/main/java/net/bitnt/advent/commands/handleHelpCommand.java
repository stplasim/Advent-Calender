package net.bitnt.advent.commands;

import net.bitnt.advent.statics.StaticMessages;
import org.bukkit.entity.Player;

public class handleHelpCommand {
    /**
     * Display help page to user
     *
     * @param player - Player instance
     */
    public static void handleCommand(Player player) {
        // Check if the player has the permission
        if(!player.hasPermission("advent.use")) {
            player.sendMessage(StaticMessages.NO_COMMAND_PERMISSIONS);
            return;
        }

        // Return help menu
        player.sendMessage(StaticMessages.HELP_TITLE);
        player.sendMessage(StaticMessages.HELP_HELP_CMD);
        player.sendMessage(StaticMessages.HELP_ADMIN_CMD);
        player.sendMessage(StaticMessages.HELP_SET_CMD);
        player.sendMessage(StaticMessages.HELP_LOAD_CMD);
    }
}
