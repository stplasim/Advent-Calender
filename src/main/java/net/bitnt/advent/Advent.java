package net.bitnt.advent;

import net.bitnt.advent.commands.AdventCommand;
import net.bitnt.advent.commands.AdventTabCompletion;
import net.bitnt.advent.handler.AdminOverviewHandler;
import net.bitnt.advent.handler.FireworkHandler;
import net.bitnt.advent.handler.PlayerHandler;
import net.bitnt.advent.util.ConfigLoader;
import net.bitnt.advent.util.DayDataLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDate;
import java.time.Month;

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
        getCommand("advent").setTabCompleter(new AdventTabCompletion());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerHandler(this), this);
        pluginManager.registerEvents(new FireworkHandler(), this);
        pluginManager.registerEvents(new AdminOverviewHandler(this), this);
    }

    /**
     * Initialise the config if the config doesn't exist
     */
    private void initConfig() {
        if(this.getConfig().getConfigurationSection("Advent") == null) {
            new ConfigLoader(this, "Advent.Timing").saveActiveMonth(Month.DECEMBER);

            new DayDataLoader(this, "Advent.Calendar").cleanConfig();

            this.saveConfig();
        }
    }
}
