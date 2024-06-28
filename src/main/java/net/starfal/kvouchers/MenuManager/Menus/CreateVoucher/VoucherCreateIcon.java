package net.starfal.kvouchers.MenuManager.Menus.CreateVoucher;

import net.kyori.adventure.text.Component;
import net.starfal.kvouchers.Functions.Color;
import net.starfal.kvouchers.MenuManager.PaginatedMenu;
import net.starfal.kvouchers.MenuManager.PlayerMenuUtility;
import net.starfal.kvouchers.Settings.Settings;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoucherCreateIcon extends PaginatedMenu {
    public VoucherCreateIcon(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return Color.format((String) Settings.getInstance().getLang("Voucher-Create.Menu.Icon-Select-GUI"));
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        switch (e.getClickedInventory().getItem(e.getSlot()).getType()) {
            case PLAYER_HEAD:

        }
    }

    @Override
    public void setMenuItems() {
        // ItemStack nextButton = Heads.create("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM5OWU1ZGE4MmVmNzc2NWZkNWU0NzJmMzE0N2VkMTE4ZDk4MTg4NzczMGVhN2JiODBkN2ExYmVkOThkNWJhIn19fQ==");
        // ItemMeta headMeta = nextButton.getItemMeta();
        // headMeta.displayName(Component.text(Color.format("<yellow>Next")));
        // nextButton.setItemMeta(headMeta);
        // ItemStack backButton = Heads.create("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzZlYmFhNDFkMWQ0MDVlYjZiNjA4NDViYjlhYzcyNGFmNzBlODVlYWM4YTk2YTU1NDRiOWUyM2FkNmM5NmM2MiJ9fX0=");
       // ItemMeta backMeta = nextButton.getItemMeta();
       // backMeta.displayName(Component.text(Color.format("<yellow>Back")));
       // backButton.setItemMeta(backMeta);
       // ItemStack closeButton = makeItem(Material.BARRIER, Color.format("<red>Close"), "");
       // ItemStack fillerGlass = makeItem(Material.GRAY_STAINED_GLASS_PANE, " ", "");
       // addMenuBorder(fillerGlass, backButton, nextButton, closeButton);

        List<Material> items = new ArrayList<>(Arrays.asList(Material.values()));

        if(items != null && !items.isEmpty()) {
            for (int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if (index >= items.size()) break;
                if (items.get(index) != null) {
                    ItemStack itemStack = new ItemStack(items.get(index), 1);
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta != null) {
                        Component displayName = Component.text(Color.format("&r<yellow>" + items.get(index).name()));
                        itemMeta.displayName(displayName);
                        itemStack.setItemMeta(itemMeta);
                    }


                    inventory.addItem(itemStack);
                }
            }
        }
    }
}
