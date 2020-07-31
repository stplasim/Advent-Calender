package net.bitnt.advent.util;

import net.bitnt.advent.Advent;
import net.bitnt.advent.calender.Day;
import net.bitnt.advent.calender.DayStatus;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class ConfigLoader {
    private Advent plugin;
    private String root;

    /**
     * Handle save and load days in config
     *
     * @param plugin - Plugin instance
     * @param root - Root path
     */
    public ConfigLoader(Advent plugin, String root) {
        this.plugin = plugin;
        this.root = root;
    }

    /**
     * Save a single day
     *
     * @param day - Day to save
     */
    public void saveDay(Day day) {
        FileConfiguration config = plugin.getConfig();

        config.set(root + ".Day." + day.getDay() + ".Position", day.getPosition());
        config.set(root + ".Day." + day.getDay() + ".Status", day.getStatus().getValue());

        plugin.saveConfig();
    }

    /**
     * Get array of all days
     *
     * @return Day
     */
    public Day[] loadAllDays() {
        Day[] day = new Day[24];
        FileConfiguration config = plugin.getConfig();

        // Get every day and create new Day instance
        for(int i = 0; i < 24; i++) {
            Day d = new Day(
                    i + 1,
                    config.getInt(root + ".Day." + (i + 1) + ".Position")
            );

            // Set day status
            d.setStatus(DayStatus.getStatus(config.getInt(root + ".Day." + (i + 1) + ".Status")));

            // Set item
            String material = config.getString(root + ".Day." + (i + 1) + ".Item");
            if(material != null) {
                ItemStack item = new ItemStack(Material.valueOf(material));
                item.setAmount(config.getInt(root + ".Day." + (i + 1) + ".Amount"));
                d.setGiftItem(item);
            }

            if(config.getString(root + ".Day." + (i + 1) + ".Command") != null) {
                d.setGiftCommand(config.getString(root + ".Day." + (i + 1) + ".Command"));
            }

            day[i] = d;
        }

        return day;
    }

    /**
     * Get day by id
     *
     * @param id - Day number
     * @return Day
     */
    public Day getSingleDay(int id) {
        FileConfiguration config = plugin.getConfig();

        // Make day
        Day day = new Day(
                id,
                config.getInt(root + ".Day." + id + ".Position")
        );

        // Set status
        day.setStatus(DayStatus.getStatus(config.getInt(root + ".Day." + id + ".Status")));

        // Set item
        String material = config.getString(root + ".Day." + id + ".Item");
        if(material != null) {
            ItemStack item = new ItemStack(Material.valueOf(material));
            item.setAmount(config.getInt(root + ".Day." + id + ".Amount"));
            day.setGiftItem(item);
        }

        if(config.getString(root + ".Day." + id + ".Command") != null) {
            day.setGiftCommand(config.getString(root + ".Day." + id + ".Command"));
        }

        return day;
    }

    /**
     * Update day
     *
     * @param day - Day to update
     */
    public void updateSingleDay(Day day) {
        FileConfiguration config = plugin.getConfig();

        // Check if day has item
        if(day.getGiftItem() != null) {
            config.set(root + ".Day." + day.getDay() + ".Item", day.getGiftItem().getType().toString());
            config.set(root + ".Day." + day.getDay() + ".Amount", day.getGiftItem().getAmount());
        }

        // Check if day has command
        if(day.getGiftCommand() != null) {
            config.set(root + ".Day." + day.getDay() + ".Command", day.getGiftCommand());
        }

        // Update status
        config.set(root + ".Day." + day.getDay() + ".Status", day.getStatus().getValue());

        plugin.saveConfig();
    }
}
