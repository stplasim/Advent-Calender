package net.bitnt.advent.statics;

import org.bukkit.Bukkit;

/**
 * The command filter scans the commands entered by the user (admin) and check if none of the
 * contains a blocked one.
 * This prevents the server from harm. Since the commands will be executed by the server itself.
 *
 */
public class CommandFilter {
    // List of blocked commands
    // This array will grow over time
    private static final String[] blockedCommands = {
        "/op ",
        "/ban ",
        "/kick ",
        "/lp ",
        "/stop"
    };

    /**
     * Filter command. This functions tests if any of the blocked commands matches the submitted one
     * If the function returns true a blocked command was found.
     *
     * @param command - Command given by the user
     * @param playerName - Player display name (Used for logging on the server)
     * @return boolean
     */
    public static boolean filter(String command, String playerName) {
        // Loop over array of blocked commands
        for (String checkCommand : blockedCommands) {

            // Check if provided commands contains any blocked commands
            if(command.contains(checkCommand)) {

                // Log player who tried to use blocked command to the server console
                Bukkit.getConsoleSender().sendMessage(
                        StaticMessages.PREFIX
                                + "§c§lThe player §7"
                                + playerName
                                + "§c has tried to insert a blocked command in the advent calendar."
                );

                // Log blocked command used
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
