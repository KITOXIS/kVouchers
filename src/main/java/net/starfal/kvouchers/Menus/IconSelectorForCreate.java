package net.starfal.kvouchers.Menus;


import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import net.starfal.kvouchers.Functions.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Predicate;

public class IconSelectorForCreate {
    private static IconSelectorForCreate instance;
    public static IconSelectorForCreate getInstance() {
        if (instance == null) {
            instance = new IconSelectorForCreate();
        }
        return instance;
    }
    public void open(Player p){
        // Predicate<Material> allMaterialsPredicate = material -> true;
        ItemPaletteGUI itemPalette = new ItemPaletteGUI.Builder("Choose an Item:")
                .show(Material::isItem)
                .as(this::getDisplayItem)
                .build();

        itemPalette.show(p);
    }
    private GuiItem getDisplayItem(Material material)
    {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Color.format("<gold>" + material.name()));
        item.setItemMeta(meta);

        return new GuiItem(item, event ->
        {
            Player player = (Player) event.getWhoClicked();

            player.closeInventory();
            player.getInventory().addItem(item);
            player.sendMessage(String.format(Color.format("<gold>" + "Don't get close to %s!"), material));
        });
    }
}