package net.starfal.kvouchers.Functions;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;

import java.util.List;

public class Color {
    public static String format(String miniMessage) {
        if (miniMessage == null) {
            return null;
        }
        MiniMessage mm = MiniMessage.miniMessage();
        Component message = mm.deserialize(miniMessage);
        String legacyMessage = LegacyComponentSerializer.legacyAmpersand().serialize(message);
        return ChatColor.translateAlternateColorCodes('&', legacyMessage);
    }
    public static List<String> formatList(List<String> miniMessage) {
        for (int i = 0; i < miniMessage.size(); i++) {
            miniMessage.set(i, format(miniMessage.get(i)));
        }
        return miniMessage;
    }
}
