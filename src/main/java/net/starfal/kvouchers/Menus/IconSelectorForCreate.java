package net.starfal.kvouchers.Menus;


import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import net.starfal.kvouchers.MenuSystems.ItemPaletteGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class IconSelectorForCreate {
    private static IconSelectorForCreate instance;
    public static IconSelectorForCreate getInstance() {
        if (instance == null) {
            instance = new IconSelectorForCreate();
        }
        return instance;
    }
    public void open(Player p){
        ItemPaletteGUI itemPalette = new ItemPaletteGUI.Builder("Choose an Item:")
                .show(Material::isItem)
                .as(this::getDisplayItem)
                .build();

        itemPalette.show(p);
    }
    private GuiItem getDisplayItem(Material material)
    {
        ItemStack item = new ItemStack(material);
        return new GuiItem(item, event ->
        {
            Player player = (Player) event.getWhoClicked();

            player.closeInventory();
        });
    }
}