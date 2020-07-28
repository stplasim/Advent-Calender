package net.bitnt.advent.statics;

import org.bukkit.Bukkit;

public class CommandFilter {
    private static final String[] blockedCommands = {
        "/op ",
        "/ban ",
        "/kick ",
        "/lp ",
        "/stop"
    };

    public static boolean filter(String command, String playerName) {
        for (String checkCommand : blockedCommands) {
            if(command.contains(checkCommand)) {
                Bukkit.getConsoleSender().sendMessage(
                        StaticMessages.PREFIX
                                + "§c§lThe player §7"
                                + playerName
                                + "§c has tried to insert a blocked command in the advent calendar."
                );
                Bukkit.getConsoleSender().sendMessage(
                        StaticMessages.PREFIX
                                + "§c Blocked command: §7"
                                + command
                );
                return true;
            }
        }
        return false;
    }
}
