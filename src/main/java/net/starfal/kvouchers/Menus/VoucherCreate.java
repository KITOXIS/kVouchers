package net.starfal.kvouchers.Menus;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import net.kyori.adventure.text.Component;
import net.starfal.kvouchers.Functions.Color;
import net.starfal.kvouchers.Settings.Settings;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class VoucherCreate {
    private static VoucherCreate instance;
    public static VoucherCreate getInstance() {
        if (instance == null) {
            instance = new VoucherCreate();
        }
        return instance;
    }
    public void open(Player p){
        ChestGui gui = new ChestGui(5, Color.format((String) Settings.getInstance().getLang("Voucher-Create.Menu.Menu-Title")));

        StaticPane itemPane = new StaticPane(1, 1, 7, 3);

        addItemToPane(itemPane, Material.NAME_TAG, "Voucher-Create.Menu.Title", "Voucher-Create.Menu.Title-Lore", event -> {
            // Handle click event for Title
        }, 1, 1);

        addItemToPane(itemPane, Material.WRITABLE_BOOK, "Voucher-Create.Menu.Description", "Voucher-Create.Menu.Description-Lore", event -> {
            // Handle click event for Description
        }, 1, 3);

        addItemToPane(itemPane, Material.WRITABLE_BOOK, "Voucher-Create.Menu.Description", "Voucher-Create.Menu.Description-Lore", event -> {
            // Handle click event for Description
        }, 1, 3);


        gui.addPane(itemPane);

        gui.show(p);
    }

    private void addItemToPane(StaticPane pane, Material material, String displayNameKey, String loreKey, Consumer<InventoryClickEvent> clickHandler, int x, int y) {
        var conf = Settings.getInstance();
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();

        meta.displayName(Component.text(Color.format((String) conf.getLang(displayNameKey))));
        List<String> loreList = (List<String>) conf.getLang(loreKey);
        List<Component> lore = new ArrayList<>();
        for (String loreLine : loreList) {
            lore.add(Component.text(Color.format(loreLine)));
        }
        meta.lore(lore);

        itemStack.setItemMeta(meta);

        GuiItem guiItem = new GuiItem(itemStack, clickHandler);
        pane.addItem(guiItem, x, y);
    }
}
