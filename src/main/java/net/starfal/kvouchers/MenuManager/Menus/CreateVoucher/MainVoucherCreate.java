package net.starfal.kvouchers.MenuManager.Menus.CreateVoucher;

import net.kyori.adventure.text.Component;
import net.starfal.kvouchers.Functions.Color;
import net.starfal.kvouchers.MenuManager.IconSelector;
import net.starfal.kvouchers.MenuManager.Menu;
import net.starfal.kvouchers.MenuManager.Menus.VoucherMain;
import net.starfal.kvouchers.MenuManager.PlayerMenuUtility;
import net.starfal.kvouchers.Settings.Settings;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MainVoucherCreate extends Menu {
    public MainVoucherCreate(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return Color.format((String) Settings.getInstance().getLang("Voucher-Create.Menu.Menu-Title"));
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
                new VoucherMain(playerMenuUtility).open();
                break;
            case 30:
                playerMenuUtility.getOwner().closeInventory();
                break;
            case 12:
                playerMenuUtility.getOwner().closeInventory();
                IconSelector.iconSelectorGUI(playerMenuUtility.getOwner());
                break;
        }
    }

    @Override
    public void setMenuItems() {
        var config = Settings.getInstance();
        ItemStack fillerGlass = makeItem(Material.GRAY_STAINED_GLASS_PANE, " ");
        setFillerGlass(fillerGlass);

        String currentTitle = playerMenuUtility.getCreateTitle();
        String currentDesc = playerMenuUtility.getCreateDesc();
        ItemStack currentIcon = playerMenuUtility.getCreateIcon();
        String currentTime = playerMenuUtility.getCreateTime();
        int currentUses = playerMenuUtility.getCreateUses();
        String currentCode = playerMenuUtility.getCreateCode();
        String currentAction = playerMenuUtility.getCreateAction();
        String currentActionName = playerMenuUtility.getCreateActionName();
        if (currentTitle == null) {
            currentTitle = "Not set";
        }
        if (currentDesc == null) {
            currentDesc = "Not set";
        }
        if (currentIcon == null) {
            currentIcon = new ItemStack(Material.GLASS);
        }
        if (currentTime == null) {
            currentTime = "Permanent";
        }
        if (currentUses == 0) {
            currentUses = 1;
        }
        if (currentCode == null) {
            currentCode = "Not set";
        }
        if (currentAction == null) {
            currentAction = "Not set";
        }
        if (currentActionName == null) {
            currentActionName = "Not set";
        }
        ItemStack title = new ItemStack(Material.NAME_TAG);
        ItemMeta titleMeta = title.getItemMeta();
        Component titleDisplayName = Component.text(Color.format((String) config.getLang("Voucher-Create.Menu.Title")));
        titleMeta.displayName(titleDisplayName);
        @SuppressWarnings("unchecked")
        List<String> titleLoreStrings = (List<String>) Settings.getInstance().getLang("Voucher-Create.Menu.Title-Lore");
        List<Component> titleLoreComponents = new ArrayList<>();
        for (String loreString : titleLoreStrings) {
            titleLoreComponents.add(Component.text(Color.format(loreString).replace("%title%", currentTitle)));
        }
        titleMeta.lore(titleLoreComponents);
        title.setItemMeta(titleMeta);
        inventory.setItem(10, title);
        ItemStack desc = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta descMeta = desc.getItemMeta();
        Component descDisplayName = Component.text(Color.format((String) config.getLang("Voucher-Create.Menu.Description")));
        descMeta.displayName(descDisplayName);
        @SuppressWarnings("unchecked")
        List<String> descLoreStrings = (List<String>) Settings.getInstance().getLang("Voucher-Create.Menu.Description-Lore");
        List<Component> descLoreComponents = new ArrayList<>();
        for (String loreString : descLoreStrings) {
            descLoreComponents.add(Component.text(Color.format(loreString).replace("%description%", currentDesc)));
        }
        descMeta.lore(descLoreComponents);
        desc.setItemMeta(descMeta);
        inventory.setItem(11, desc);

        ItemStack icon = new ItemStack(currentIcon);
        ItemMeta iconMeta = icon.getItemMeta();
        Component iconDisplayName = Component.text(Color.format((String) config.getLang("Voucher-Create.Menu.Icon")));
        iconMeta.displayName(iconDisplayName);
        @SuppressWarnings("unchecked")
        List<String> iconLoreStrings = (List<String>) Settings.getInstance().getLang("Voucher-Create.Menu.Icon-Lore");
        List<Component> iconLoreComponents = new ArrayList<>();
        for (String loreString : iconLoreStrings) {
            loreString = loreString.replace("%icon%", currentIcon.getType().name());
            iconLoreComponents.add(Component.text(Color.format(loreString)));
        }
        iconMeta.lore(iconLoreComponents);
        icon.setItemMeta(iconMeta);
        inventory.setItem(12, icon);
        ItemStack time = new ItemStack(Material.CLOCK);
        ItemMeta timeMeta = time.getItemMeta();
        Component timeDisplayName = Component.text(Color.format((String) config.getLang("Voucher-Create.Menu.Time")));
        timeMeta.displayName(timeDisplayName);
        @SuppressWarnings("unchecked")
        List<String> timeLoreStrings = (List<String>) Settings.getInstance().getLang("Voucher-Create.Menu.Time-Lore");
        List<Component> timeLoreComponents = new ArrayList<>();
        for (String loreString : timeLoreStrings) {
            timeLoreComponents.add(Component.text(Color.format(loreString).replace("%time%", currentTime)));
        }
        timeMeta.lore(timeLoreComponents);
        time.setItemMeta(timeMeta);
        inventory.setItem(13, time);
        ItemStack action = new ItemStack(Material.COMMAND_BLOCK);
        ItemMeta actionMeta = action.getItemMeta();
        Component actionDisplayName = Component.text(Color.format((String) config.getLang("Voucher-Create.Menu.Action")));
        actionMeta.displayName(actionDisplayName);
        @SuppressWarnings("unchecked")
        List<String> actionLoreStrings = (List<String>) Settings.getInstance().getLang("Voucher-Create.Menu.Action-Lore");
        List<Component> actionLoreComponents = new ArrayList<>();
        for (String loreString : actionLoreStrings) {
            actionLoreComponents.add(Component.text(Color.format(loreString).replace("%action%", currentAction).replace("%action-name%", currentActionName)));
        }
        actionMeta.lore(actionLoreComponents);
        action.setItemMeta(actionMeta);
        inventory.setItem(14, action);
        ItemStack uses = new ItemStack(Material.DAMAGED_ANVIL);
        ItemMeta usesMeta = uses.getItemMeta();
        Component usesDisplayName = Component.text(Color.format((String) config.getLang("Voucher-Create.Menu.Uses")));
        usesMeta.displayName(usesDisplayName);
        @SuppressWarnings("unchecked")
        List<String> usesLoreStrings = (List<String>) Settings.getInstance().getLang("Voucher-Create.Menu.Uses-Lore");
        List<Component> usesLoreComponents = new ArrayList<>();
        for (String loreString : usesLoreStrings) {
            usesLoreComponents.add(Component.text(Color.format(loreString).replace("%uses%", String.valueOf(currentUses))));
        }
        usesMeta.lore(usesLoreComponents);
        uses.setItemMeta(usesMeta);
        inventory.setItem(15, uses);
        ItemStack code = new ItemStack(Material.PAPER);
        ItemMeta codeMeta = code.getItemMeta();
        Component codeDisplayName = Component.text(Color.format((String) config.getLang("Voucher-Create.Menu.Code")));
        codeMeta.displayName(codeDisplayName);
        @SuppressWarnings("unchecked")
        List<String> codeLoreStrings = (List<String>) Settings.getInstance().getLang("Voucher-Create.Menu.Code-Lore");
        List<Component> codeLoreComponents = new ArrayList<>();
        for (String loreString : codeLoreStrings) {
            codeLoreComponents.add(Component.text(Color.format(loreString).replace("%code%", currentCode)));
        }
        codeMeta.lore(codeLoreComponents);
        code.setItemMeta(codeMeta);
        inventory.setItem(16, code);
        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        Component backDisplayName = Component.text(Color.format((String) config.getLang("Voucher-Create.Menu.Back")));
        backMeta.displayName(backDisplayName);
        back.setItemMeta(backMeta);
        inventory.setItem(36, back);
        ItemStack create = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta createMeta = create.getItemMeta();
        Component createDisplayName = Component.text(Color.format((String) config.getLang("Voucher-Create.Menu.Create")));
        createMeta.displayName(createDisplayName);
        @SuppressWarnings("unchecked")
        List<String> createLoreStrings = (List<String>) Settings.getInstance().getLang("Voucher-Create.Menu.Create-Lore");
        List<Component> createLoreComponents = new ArrayList<>();
        for (String loreString : createLoreStrings) {
            loreString = loreString.replace("%title%", currentTitle);
            loreString = loreString.replace("%description%", currentDesc);
            loreString = loreString.replace("%icon%", currentIcon.getType().name());
            loreString = loreString.replace("%time%", currentTime);
            loreString = loreString.replace("%uses%", String.valueOf(currentUses));
            loreString = loreString.replace("%code%", currentCode);
            loreString = loreString.replace("%action%", currentAction);
            loreString = loreString.replace("%action-name%", currentActionName);
            Component createLore = Component.text(Color.format(loreString));
            createLoreComponents.add(createLore);
        }
        createMeta.lore(createLoreComponents);
        create.setItemMeta(createMeta);
        inventory.setItem(32, create);
        ItemStack cancel = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta cancelMeta = cancel.getItemMeta();
        Component cancelDisplayName = Component.text(Color.format((String) config.getLang("Voucher-Create.Menu.Cancel")));
        cancelMeta.displayName(cancelDisplayName);
        cancel.setItemMeta(cancelMeta);
        inventory.setItem(30, cancel);
    }
}
