package net.starfal.kvouchers.Menus;

import com.github.stefvanschie.inventoryframework.font.util.Font;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.Orientable;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.component.Label;
import net.starfal.kvouchers.Functions.Color;
import net.starfal.kvouchers.Functions.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toCollection;

public class ItemPaletteGUI extends ChestGui
{
    private final Predicate<Material> itemFilter;
    private final Function<Material, GuiItem> itemTransformer;
    private final PaginatedPane itemsPane;

    private static final int ITEMS_PER_PAGE = 9 * 4;

    private ItemPaletteGUI(Builder builder)
    {
        super(6, builder.title);

        this.itemTransformer = builder.itemTransformer;
        this.itemFilter = builder.itemFilter;

        setOnTopClick(event -> event.setCancelled(true));
        addPane(this.itemsPane = createItemsPane());
        addPane(createControlPane());
        addPane(InventoryUtils.createRectangle(Pane.Priority.LOWEST, 1, 5, 7, 1, new GuiItem(InventoryUtils.createWall(Material.GRAY_STAINED_GLASS_PANE))));
        addPane(InventoryUtils.createRectangle(Pane.Priority.LOWEST, 0, 4, 9, 1, new GuiItem(InventoryUtils.createWall(Material.GRAY_STAINED_GLASS_PANE))));
        update();
    }



    /*
     * Panes
     */
    private Pane createControlPane()
    {
        OutlinePane pane = new OutlinePane(0, 5, 9, 1, Pane.Priority.LOW);
        pane.setOrientation(Orientable.Orientation.HORIZONTAL);
        pane.setGap(7);

        pane.addItem(PageController.PREVIOUS.toItemStack(this, Color.format("<red>Back"), this.itemsPane, Material.PLAYER_HEAD));
        pane.addItem(PageController.NEXT.toItemStack(this, Color.format("<green>Next"), this.itemsPane, Material.PLAYER_HEAD));

        return pane;
    }

    private PaginatedPane createItemsPane()
    {
        Deque<GuiItem> itemsToDisplay = Arrays.stream(Material.values())
                .filter(material -> !material.isAir())
                .filter(this.itemFilter)
                .map(this.itemTransformer)
                .collect(toCollection(LinkedList::new));

        PaginatedPane pane = new PaginatedPane(0, 0, 9, 6, Pane.Priority.LOWEST);

        for(int i = 0, pagesAmount = (itemsToDisplay.size() / ITEMS_PER_PAGE) +1; i < pagesAmount; i++)
            pane.addPane(i, createPage(itemsToDisplay));

        pane.setPage(0);

        return pane;
    }

    private Pane createPage(Deque<GuiItem> items)
    {
        OutlinePane page = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        page.setOrientation(Orientable.Orientation.HORIZONTAL);

        for(int i = 1; i <= ITEMS_PER_PAGE; i++)
        {
            if(!items.isEmpty())
                page.addItem(items.removeFirst());
        }

        return page;
    }



    private enum PageController
    {
        PREVIOUS("MHF_ArrowLeft", (page, itemsPane) -> page > 0, page -> --page),
        NEXT("MHF_ArrowRight", (page, itemsPane) -> page < (itemsPane.getPages()-1), page -> ++page);

        private final String skullName;
        private final BiPredicate<Integer, PaginatedPane> shouldContinue;
        private final IntUnaryOperator nextPageSupplier;

        private PageController(String skullName, BiPredicate<Integer, PaginatedPane> shouldContinue, IntUnaryOperator nextPageSupplier)
        {
            this.skullName = skullName;
            this.shouldContinue = shouldContinue;
            this.nextPageSupplier = nextPageSupplier;
        }

        @SuppressWarnings("deprecation")
        public GuiItem toItemStack(ChestGui gui, String itemName, PaginatedPane itemsPane, Material material)
        {
            Label label = new Label(0, 0, 9, 6, Font.WHITE);
            if (this == PREVIOUS) {
                label.setText("←");
            } else if (this == NEXT) {
                label.setText("→");
            }

            ItemStack item = new ItemStack(material); // use the actual material
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(itemName);
            item.setItemMeta(meta);

            return new GuiItem(item, event ->
            {
                int currentPage = itemsPane.getPage();

                if(!this.shouldContinue.test(currentPage, itemsPane))
                    return;

                itemsPane.setPage(this.nextPageSupplier.applyAsInt(currentPage));
                gui.update();
            });
        }
    }


    public static class Builder
    {
        String title;
        Function<Material, GuiItem> itemTransformer;
        Predicate<Material> itemFilter;

        public Builder(String title)
        {
            this.title = title;
        }

        public Builder as(Function<Material, GuiItem> itemTransformer)
        {
            this.itemTransformer = itemTransformer;
            return this;
        }

        public Builder show(Predicate<Material> itemFilter)
        {
            this.itemFilter = itemFilter;
            return this;
        }

        public ItemPaletteGUI build()
        {
            return new ItemPaletteGUI(this);
        }
    }
}
