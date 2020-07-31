package net.bitnt.advent.util;

import net.bitnt.advent.Advent;
import org.bukkit.configuration.file.FileConfiguration;

import java.time.Month;

public class ConfigLoader {
    private Advent plugin;
    private String root;

    /**
     * Load config data from the config
     *
     * @param plugin - Plugin instance
     * @param root - Path to data
     */
    public ConfigLoader(Advent plugin, String root) {
        this.plugin = plugin;
        this.root = root;
    }

    /**
     * Get the active month stored in the config
     *
     * @return Month
     */
    public Month getActiveMonth() {
        FileConfiguration config = plugin.getConfig();

        // Get string of active month
        String month = config.getString(root + ".Month");

        return Month.valueOf(month);
    }

    /**
     * Save the active month to the config
     *
     * @param month - Active month
     */
    public void saveActiveMonth(Month month) {
        FileConfiguration config = plugin.getConfig();

        config.set(root + ".Month", month.toString());

        plugin.saveConfig();
    }
}
