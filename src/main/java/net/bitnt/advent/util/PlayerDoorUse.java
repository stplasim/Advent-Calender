package net.bitnt.advent.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.bitnt.advent.Advent;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class PlayerDoorUse {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Advent plugin;
    private final Player player;
    private final int day;
    private ArrayList<String> playerList;

    /**
     * This class is used to add users to the day log and to check if the user has already opened a specific door
     *
     * @param plugin - Main plugin instance
     * @param player - Player instance
     * @param day - Selected day
     */
    public PlayerDoorUse(Advent plugin, Player player, int day) {
        this.plugin = plugin;
        this.player = player;
        this.day = day;
    }

    /**
     * Save player to the log of the selected day
     */
    public void savePlayerAction() {
        try {

            // Get file of the day
            File playerConfigFile = new File(plugin.getDataFolder(), "day_" + day + ".json");

            // Check if the file exists
            // If not create black file otherwise load file content and use that
            if(!playerConfigFile.exists()) {
                playerList = new ArrayList<>();
            }
            else {
                // Load data from file and put it into the array list
                playerList = gson.fromJson(new FileReader(playerConfigFile), ArrayList.class);
            }

            // Add the UUID of the player to the array list
            playerList.add(player.getUniqueId().toString());

            // Encode array list to json
            String json = gson.toJson(playerList);

            // Write json list to the file
            Files.write(playerConfigFile.toPath(), json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            // In case the file doesnt exist create it.
            if(!playerConfigFile.exists()) {
                plugin.saveResource(playerConfigFile.getName(), false);
            }
        }
        catch (Exception e) {
            // Handle potential error
            e.printStackTrace();
        }
    }

    public boolean hasAlreadyOpenedDay() {
        // Create return result
        boolean result = false;

        try {
            // Get file of the selected day
            File playerConfigFile = new File(plugin.getDataFolder(), "day_" + day + ".json");

            // Check if the file exists
            // If not return false because if the file doesn't exist yet nobody has opened any door
            if(!playerConfigFile.exists()) {
                return false;
            }

            // Decode array list form json
            playerList = gson.fromJson(new FileReader(playerConfigFile), ArrayList.class);

            // File player with index of
            // IndexOf return -1 if nothing was found and the index of the element if something was found
            int index = playerList.indexOf(player.getUniqueId().toString());

            // Make result
            // True = User has opened this door
            // False = User has't opened this door
            result = index != -1;
        }
        catch (NullPointerException e) {
            // If this error is thrown the might be an error with the file not been found.
            // In this case false is returned
            return false;
        }
        catch (FileNotFoundException e) {
            // Handle general error
            e.printStackTrace();
        }

        return result;
    }
}
