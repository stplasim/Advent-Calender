package net.bitnt.advent;

import net.bitnt.advent.calender.Day;
import net.bitnt.advent.commands.AdventCommand;
import net.bitnt.advent.handler.AdminOverviewHandler;
import net.bitnt.advent.handler.PlayerHandler;
import net.bitnt.advent.util.ConfigCalender;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDate;

public final class Advent extends JavaPlugin {
    @Override
    public void onEnable() {
        initConfig();
        initHandlers();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Initialise command executors and listeners
     */
    private void initHandlers() {
        getCommand("advent").setExecutor(new AdventCommand(this));

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerHandler(this), this);
        pluginManager.registerEvents(new AdminOverviewHandler(this), this);
    }

    /**
     * Initialise the config if the config doesn't exist
     */
    private void initConfig() {
        if(this.getConfig().getConfigurationSection("Advent") == null) {
            int currentYear = LocalDate.now().getYear();

            for (int i = 0; i < 24; i++) {
                new ConfigCalender(this, "Advent.Calender")
                        .saveDay(new Day((i + 1), (i * 2), currentYear));
            }

            this.saveConfig();
        }
    }
}
