package net.bitnt.advent.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {
    private final ItemStack item;
    private final ItemMeta itemMeta;

    /**
     * Constructor for item with sub id
     *
     * @param material - Item material
     * @param subId - Item Sub id
     */
    public ItemBuilder(Material material, short subId) {
        item = new ItemStack(material, 1, subId);
        itemMeta = item.getItemMeta();
    }

    /**
     * Constructor for item without sub id
     *
     * @param material - Item material
     */
    public ItemBuilder(Material material) {
        this(material, (short)0);
    }

    /**
     * Set display name
     *
     * @param name - Display name of item
     * @return ItemBuilder
     */
    public ItemBuilder setName(String name) {
        itemMeta.setDisplayName(name);
        return this;
    }

    /**
     * Set lore
     *
     * @param lore - Item lore
     * @return ItemBuilder
     */
    public ItemBuilder setLore(String... lore) {
        itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    /**
     * Set item amount
     *
     * @param amount - Item amount
     * @return ItemBuilder
     */
    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    /**
     * Build item stack
     *
     * @return ItemStack
     */
    public ItemStack build() {
        item.setItemMeta(itemMeta);
        return item;
    }
}
