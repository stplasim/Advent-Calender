package net.bitnt.advent.statics;

/**
 * This static class provides all Messages for the plugin
 * If you want to change them you can do so in the file:
 * net > bitnt > advent > statics > StaticMessages.java.
 *
 * To save the changes just compile again with Maven.
 */
public class StaticMessages {
    // Global plugin prefix
    public static final String PREFIX = "§7[§bAdvent Calendar§7]§r ";

    // Error messages
    public static final String COMMAND_NOT_FOUND_ERROR = PREFIX + "§cCommand not found. Pleas use §7/advent help";
    public static final String NO_COMMAND_PERMISSIONS = PREFIX + "§cYou dont have the permission to use this command";
    public static final String NOT_A_CHEST_ERROR = PREFIX + "§cThis is not a chest! Use a chest to create or update calendar";
    public static final String DAY_NOT_READY_ERROR = PREFIX + "§cOops. It looks like this day has not yet been configured.";
    public static final String DAY_USED_ERROR = PREFIX + "§cYou already got your gift for today";
    public static final String USED_BLOCKED_COMMAND = PREFIX + "§c§lA command in this calendar is not allowed. The case will be reported.";

    // Success messages
    public static final String CALENDAR_CREATED =  PREFIX + "§aCalendar created successful";
    public static final String GIFT_GIVEN = PREFIX + "§a§lYay! Happy advent!!";

    // Help command text
    public static final String HELP_TITLE = "§6----------|§b§l Advent Calendar §r§6|----------";
    public static final String HELP_HELP_CMD = "§7/advent help §6- Display the help page.";
    public static final String HELP_ADMIN_CMD = "§7/advent admin §6- Get a overview over all day in the calendar.";
    public static final String HELP_SET_CMD = "§7/advent set §6- Create the calendar with the items and command by looking at a chest that contains these items.";
    public static final String HELP_LOAD_CMD = "§7/advent load §6- Get the chest with the items back. So you can change the calendar anytime. The chest appears at your current position.";
}
