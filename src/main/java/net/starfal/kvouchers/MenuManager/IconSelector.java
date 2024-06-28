package net.starfal.kvouchers.MenuManager;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IconSelector {

    public static void iconSelectorGUI(Player p){
        PaginatedGui gui = Gui.paginated()
                .title(Component.text("GUI Title!"))
                .rows(6)
                .pageSize(36)
                .create();
        gui.setItem(6, 3, ItemBuilder.skull().texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzZlYmFhNDFkMWQ0MDVlYjZiNjA4NDViYjlhYzcyNGFmNzBlODVlYWM4YTk2YTU1NDRiOWUyM2FkNmM5NmM2MiJ9fX0=").asGuiItem(event -> gui.previous()));
        gui.setItem(6, 5, ItemBuilder.skull().texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM5OWU1ZGE4MmVmNzc2NWZkNWU0NzJmMzE0N2VkMTE4ZDk4MTg4NzczMGVhN2JiODBkN2ExYmVkOThkNWJhIn19fQ==").asGuiItem(event -> gui.next()));
        List<Material> items = new ArrayList<>(Arrays.asList(Material.values()));

        if(items != null && !items.isEmpty()) {
            for (Material material : items) {
                if (material.isItem()) { // Check if the material is an item
                    ItemStack itemStack = new ItemStack(material, 1);
                    GuiItem guiItem = ItemBuilder.from(itemStack).asGuiItem();
                    gui.addItem(guiItem);
                }
            }
        }
        gui.open(p);
    }
}
