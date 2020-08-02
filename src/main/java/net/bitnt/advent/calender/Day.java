package net.bitnt.advent.calender;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Day extends Calendar {
    private ItemStack giftItem = null;
    private String giftCommand = null;

    /**
     * Constructor with year
     * For general purpose
     *
     * @param day - Active day
     * @param position - Position in the inventory
     */
    public Day(int day, int position) {
        super(day, position);
    }

    /**
     * Get item to display in the inventory
     *
     * @param lore - Add description to item
     * @return ItemStack
     */
    @Override
    public ItemStack getDisplayItem(String... lore) {
        Material material;

        // Check in with state the day is in
        switch (getStatus()) {
            case EMPTY:
            case OVER:
                // Displayed when day has no item or command
                material = Material.ENDER_CHEST;
                break;

            case UPCOMING:
                // Displayed when day is ready
                material = Material.CHEST;
                break;

            case READY:
                material = Material.DIAMOND;
                break;

            case NONE:
            default:
                // Displayed when day status is unknown
                material = Material.BARRIER;
                break;
        }

        // Setup day and item
        ItemStack day = new ItemStack(material);
        ItemMeta itemMeta = day.getItemMeta();

        // Set title and description
        assert itemMeta != null;
        itemMeta.setDisplayName("§6Day " + getDay());
        itemMeta.setLore(Arrays.asList(lore));

        // If day is active add glow effect
        if(getStatus() == DayStatus.READY) {
            itemMeta.addEnchant(Enchantment.DURABILITY, 1, false);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        // Assembly item and return
        day.setItemMeta(itemMeta);
        return day;
    }

    /**
     * Get set item for day
     *
     * @return ItemStack
     */
    public ItemStack getGiftItem() {
        return giftItem;
    }

    /**
     * Get set command for day
     *
     * @return String
     */
    public String getGiftCommand() {
        return giftCommand;
    }

    /**
     * Set gift command for day
     *
     * @param giftCommand - Command add set
     */
    public void setGiftCommand(String giftCommand) {
        this.giftCommand = giftCommand;
    }

    /**
     * Set gift item for day
     *
     * @param giftItem - Item add set
     */
    public void setGiftItem(ItemStack giftItem) {
        this.giftItem = giftItem;
    }

    /**
     * Check if the day has a item associated with it
     * True = yes
     * False = no
     *
     * @return boolean
     */
    public boolean hasItem() {
        return giftItem != null;
    }

    /**
     * Check if the day has a command associated with it
     * True = yes
     * False = no
     *
     * @return boolean
     */
    public boolean hasCommand() {
        return giftCommand != null;
    }
}
