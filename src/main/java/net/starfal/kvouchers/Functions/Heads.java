package net.starfal.kvouchers.Functions;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import org.bukkit.inventory.ItemStack;

public class Heads {
    private static Heads instance;
    public static Heads getInstance(){
        if(instance == null){
            instance = new Heads();
        }
        return instance;
    }
    public ItemStack create(String texture){
        return ItemBuilder.skull().texture(texture).build();
    }
}
