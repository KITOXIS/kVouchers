package net.starfal.kvouchers.Menus;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import net.kyori.adventure.text.Component;
import net.starfal.kvouchers.Functions.Color;
import net.starfal.kvouchers.Settings.Settings;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class OpenMainMenu {
    private static OpenMainMenu instance;
    public static OpenMainMenu getInstance() {
        if (instance == null) {
            instance = new OpenMainMenu();
        }
        return instance;
    }
    public void open(Player p){
        var conf = Settings.getInstance();
        ChestGui gui = new ChestGui(4, Color.format((String) conf.getLang("Voucher-Main.Title")));

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        background.addItem(new GuiItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        gui.addPane(background);

        // Voucher Create Button

        OutlinePane voucherCreate = new OutlinePane(1, 1, 1, 1);

        ItemStack voucherCreateButton = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta createMeta = voucherCreateButton.getItemMeta();

        Component createDisplayName = Component.text(Color.format((String) conf.getLang("Voucher-Main.Buttons.Create.Title")));
        createMeta.displayName(createDisplayName);
        @SuppressWarnings("unchecked")
        List<String> createLoreStrings = (List<String>) Settings.getInstance().getLang("Voucher-Main.Buttons.Create.Lore");
        List<Component> createLoreComponents = new ArrayList<>();
        for (String loreString : createLoreStrings) {
            createLoreComponents.add(Component.text(Color.format(loreString)));
        }
        createMeta.lore(createLoreComponents);
        voucherCreateButton.setItemMeta(createMeta);

        voucherCreate.addItem(new GuiItem(voucherCreateButton, event -> {
            //navigate to the creation of vouchers
        }));

        gui.addPane(voucherCreate);

        // Voucher Delete Button

        OutlinePane voucherDelete = new OutlinePane(3, 1, 1, 1);

        ItemStack voucherDeleteButton = new ItemStack(Material.CACTUS);
        ItemMeta deleteMeta = voucherDeleteButton.getItemMeta();

        Component deleteDisplayName = Component.text(Color.format((String) conf.getLang("Voucher-Main.Buttons.Delete.Title")));
        deleteMeta.displayName(deleteDisplayName);
        @SuppressWarnings("unchecked")
        List<String> deleteLoreStrings = (List<String>) Settings.getInstance().getLang("Voucher-Main.Buttons.Delete.Lore");
        List<Component> deleteLoreComponents = new ArrayList<>();
        for (String loreString : deleteLoreStrings) {
            deleteLoreComponents.add(Component.text(Color.format(loreString)));
        }
        deleteMeta.lore(deleteLoreComponents);
        voucherDeleteButton.setItemMeta(deleteMeta);

        voucherDelete.addItem(new GuiItem(voucherDeleteButton, event -> {
            //navigate to the deletion of vouchers
        }));

        gui.addPane(voucherDelete);

        // Voucher List Button
        OutlinePane voucherList = new OutlinePane(5, 1, 1, 1);

        ItemStack voucherListButton = new ItemStack(Material.WRITTEN_BOOK);
        ItemMeta listMeta = voucherListButton.getItemMeta();

        Component listDisplayName = Component.text(Color.format((String) conf.getLang("Voucher-Main.Buttons.List.Title")));
        listMeta.displayName(listDisplayName);
        @SuppressWarnings("unchecked")
        List<String> listLoreStrings = (List<String>) Settings.getInstance().getLang("Voucher-Main.Buttons.List.Lore");
        List<Component> listLoreComponents = new ArrayList<>();
        for (String loreString : listLoreStrings) {
            listLoreComponents.add(Component.text(Color.format(loreString)));
        }
        listMeta.lore(listLoreComponents);
        voucherListButton.setItemMeta(listMeta);

        voucherList.addItem(new GuiItem(voucherListButton, event -> {
            //navigate to the list of vouchers
        }));

        gui.addPane(voucherList);

        // Close Button
        OutlinePane close = new OutlinePane(4, 3, 1, 1);

        ItemStack closeButton = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = closeButton.getItemMeta();

        Component closeDisplayName = Component.text(Color.format((String) conf.getLang("Voucher-Main.Buttons.Close.Title")));
        closeMeta.displayName(closeDisplayName);
        @SuppressWarnings("unchecked")
        List<String> closeLoreStrings = (List<String>) Settings.getInstance().getLang("Voucher-Main.Buttons.Close.Lore");
        List<Component> closeLoreComponents = new ArrayList<>();
        for (String loreString : closeLoreStrings) {
            closeLoreComponents.add(Component.text(Color.format(loreString)));
        }
        closeMeta.lore(closeLoreComponents);
        closeButton.setItemMeta(closeMeta);

        close.addItem(new GuiItem(closeButton, event -> {
            p.closeInventory();
        }));

        gui.addPane(close);

        // Show the menu to the player

        gui.show(p);
    }
}
