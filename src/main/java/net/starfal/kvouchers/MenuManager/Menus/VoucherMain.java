package net.starfal.kvouchers.MenuManager.Menus;

import net.kyori.adventure.text.Component;
import net.starfal.kvouchers.Functions.Color;
import net.starfal.kvouchers.MenuManager.Menu;
import net.starfal.kvouchers.MenuManager.Menus.CreateVoucher.MainVoucherCreate;
import net.starfal.kvouchers.MenuManager.PlayerMenuUtility;

import net.starfal.kvouchers.Settings.Settings;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class VoucherMain extends Menu {
    public VoucherMain(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return Color.format((String) Settings.getInstance().getLang("Voucher-Main.Title"));
    }

    @Override
    public int getSlots() {
        return 45;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        switch (e.getSlot()) {
            case 36:
                playerMenuUtility.getOwner().closeInventory();
                break;
            case 13:
                playerMenuUtility.getOwner().closeInventory();
                new MainVoucherCreate(playerMenuUtility).open();
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack fillerGlass = makeItem(Material.GRAY_STAINED_GLASS_PANE, " ", "");
        setFillerGlass(fillerGlass);

        ItemStack createItem = new ItemStack(Material.LIME_CONCRETE, 1);
        ItemMeta createMeta = createItem.getItemMeta();
        Component createDisplayName = Component.text(Color.format((String) Settings.getInstance().getLang("Voucher-Main.Buttons.Create.Title")));
        createMeta.displayName(createDisplayName);
        @SuppressWarnings("unchecked")
        List<String> createLoreStrings = (List<String>) Settings.getInstance().getLang("Voucher-Main.Buttons.Create.Lore");
        List<Component> createLoreComponents = new ArrayList<>();
        for (String loreString : createLoreStrings) {
            createLoreComponents.add(Component.text(Color.format(loreString)));
        }
        createMeta.lore(createLoreComponents);
        createItem.setItemMeta(createMeta);

        inventory.setItem(13, createItem);
        ItemStack deleteItem = new ItemStack(Material.RED_CONCRETE, 1);
        ItemMeta deleteMeta = deleteItem.getItemMeta();
        Component deleteDisplayName = Component.text(Color.format((String) Settings.getInstance().getLang("Voucher-Main.Buttons.Delete.Title")));
        deleteMeta.displayName(deleteDisplayName);
        @SuppressWarnings("unchecked")
        List<String> deleteLoreStrings = (List<String>) Settings.getInstance().getLang("Voucher-Main.Buttons.Delete.Lore");
        List<Component> deleteLoreComponents = new ArrayList<>();
        for (String loreString : deleteLoreStrings) {
            deleteLoreComponents.add(Component.text(Color.format(loreString)));
        }
        deleteMeta.lore(deleteLoreComponents);
        deleteItem.setItemMeta(deleteMeta);

        inventory.setItem(24, deleteItem);
        ItemStack listItem = new ItemStack(Material.PAPER, 1);
        ItemMeta listMeta = listItem.getItemMeta();
        Component listDisplayName = Component.text(Color.format((String) Settings.getInstance().getLang("Voucher-Main.Buttons.List.Title")));
        listMeta.displayName(listDisplayName);
        @SuppressWarnings("unchecked")
        List<String> listLoreStrings = (List<String>) Settings.getInstance().getLang("Voucher-Main.Buttons.List.Lore");
        List<Component> listLoreComponents = new ArrayList<>();
        for (String loreString : listLoreStrings) {
            listLoreComponents.add(Component.text(Color.format(loreString)));
        }
        listMeta.lore(listLoreComponents);
        listItem.setItemMeta(listMeta);
        inventory.setItem(31, listItem);
        ItemStack renameItem = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta renameMeta = renameItem.getItemMeta();
        Component renameDisplayName = Component.text(Color.format((String) Settings.getInstance().getLang("Voucher-Main.Buttons.Rename.Title")));
        renameMeta.displayName(renameDisplayName);
        @SuppressWarnings("unchecked")
        List<String> renameLoreStrings = (List<String>) Settings.getInstance().getLang("Voucher-Main.Buttons.Rename.Lore");
        List<Component> renameLoreComponents = new ArrayList<>();
        for (String loreString : renameLoreStrings) {
            renameLoreComponents.add(Component.text(Color.format(loreString)));
        }
        renameMeta.lore(renameLoreComponents);
        renameItem.setItemMeta(renameMeta);
        inventory.setItem(20, renameItem);
        ItemStack closeItem = new ItemStack(Material.BARRIER, 1);
        ItemMeta closeMeta = closeItem.getItemMeta();
        Component closeDisplayName = Component.text(Color.format((String) Settings.getInstance().getLang("Voucher-Main.Buttons.Close.Title")));
        closeMeta.displayName(closeDisplayName);
        @SuppressWarnings("unchecked")
        List<String> closeLoreStrings = (List<String>) Settings.getInstance().getLang("Voucher-Main.Buttons.Close.Lore");
        List<Component> closeLoreComponents = new ArrayList<>();
        for (String loreString : closeLoreStrings) {
            closeLoreComponents.add(Component.text(Color.format(loreString)));
        }
        closeMeta.lore(closeLoreComponents);
        closeItem.setItemMeta(closeMeta);
        inventory.setItem(36, closeItem);
    }
}