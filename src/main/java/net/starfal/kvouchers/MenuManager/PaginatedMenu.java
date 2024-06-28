package net.starfal.kvouchers.MenuManager;

import net.starfal.kvouchers.Functions.Color;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/*

A class extending the functionality of the regular Menu, but making it Paginated

This pagination system was made from Jer's code sample. <3

 */

public abstract class PaginatedMenu extends Menu {

    protected int page = 0;
    protected int maxItemsPerPage = 28;
    protected int index = 0;

    public PaginatedMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    //Set the border and menu buttons for the menu
    public void addMenuBorder(ItemStack fillerGlass, ItemStack leftButton, ItemStack rightButton, ItemStack closeButton) {
        inventory.setItem(48, leftButton);

        inventory.setItem(49, closeButton);

        inventory.setItem(50, rightButton);

        for (int i = 0; i < 10; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, fillerGlass);
            }
        }

        inventory.setItem(17, fillerGlass);
        inventory.setItem(18, fillerGlass);
        inventory.setItem(26, fillerGlass);
        inventory.setItem(27, fillerGlass);
        inventory.setItem(35, fillerGlass);
        inventory.setItem(36, fillerGlass);

        for (int i = 44; i < 54; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, fillerGlass);
            }
        }
    }

    public int getMaxItemsPerPage() {
        return maxItemsPerPage;
    }
}
