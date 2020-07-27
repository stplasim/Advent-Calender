package net.bitnt.advent.calender;

import org.bukkit.inventory.ItemStack;

public abstract class Calender {
    public static final String CALENDER_TITLE = "§6§lAdvent Calender";
    public static final String CALENDER_TITLE_ADMIN = CALENDER_TITLE + " - Admin";
    public static final String CALENDER_TITLE_EDIT = CALENDER_TITLE + " - Edit";

    private final int day;
    private DayStatus status = DayStatus.NONE;
    private final int position;
    private int year;

    /**
     * Constructor for day with year
     *
     * @param day - Day this day is active
     * @param position - Position of the day in the inventory
     * @param year - Year the day is active
     */
    public Calender(int day, int position, int year) {
        this.day = day;
        this.position = position;
        this.year = year;
    }

    /**
     * Constructor for day without year
     * This constructor is used to change days in the config
     *
     * @param day
     * @param position
     */
    public Calender(int day, int position) {
        this.day = day;
        this.position = position;
    }

    /**
     * Get the current status of the day
     *
     * @return DayStatus
     */
    public DayStatus getStatus() {
        return status;
    }

    /**
     * Get the position on the day in the inventory
     *
     * @return int
     */
    public int getPosition() {
        return position;
    }

    /**
     * Get the year in with the day is active
     *
     * @return int
     */
    public int getYear() {
        return year;
    }

    /**
     * Get day when day is active
     *
     * @return int
     */
    public int getDay() {
        return day;
    }

    /**
     * Change day status
     *
     * @param status - Day status
     */
    public void setStatus(DayStatus status) {
        this.status = status;
    }

    /**
     * Get item to display in the inventory
     *
     * @param lore - Add description to item
     * @return ItemStack
     */
    public abstract ItemStack getDisplayItem(String... lore);
}
