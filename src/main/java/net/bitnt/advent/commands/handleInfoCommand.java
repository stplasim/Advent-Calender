package net.bitnt.advent.commands;

import net.bitnt.advent.statics.StaticMessages;
import org.bukkit.entity.Player;

public class handleInfoCommand {
   public static void handleCommand(Player player) {
       // Check if the player has the permission
       if(!player.hasPermission("advent.use")) {
           player.sendMessage(StaticMessages.NO_COMMAND_PERMISSIONS);
           return;
       }

       // Print info message to user
       player.sendMessage(StaticMessages.INFO);
       player.sendMessage(StaticMessages.INFO_NOTE);
   }
}
