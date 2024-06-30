package net.starfal.kvouchers.Functions;

import com.github.stefvanschie.inventoryframework.font.util.Font;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.pane.component.Label;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.meta.ItemMeta;

public class Skull {
    public static GuiItem create(String displayName, String text, Font font, Integer lenght, Integer height){
        Label label = new Label(lenght, height, font);
        GuiItem[] guiItem = new GuiItem[1];
        label.setText(text, (character, item) -> {
            ItemMeta meta = item.getItemMeta();
            Component displayNameComponent = Component.text(displayName);
            meta.displayName(displayNameComponent);
            item.setItemMeta(meta);

            guiItem[0] = new GuiItem(item);
            return guiItem[0];
        });
        return guiItem[0] != null ? guiItem[0] : null;
    }
}
