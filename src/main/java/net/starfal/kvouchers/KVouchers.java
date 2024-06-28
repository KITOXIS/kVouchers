package net.starfal.kvouchers;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.starfal.kvouchers.Commands.VoucherCMD;
import net.starfal.kvouchers.Listeners.MenuHandler;
import net.starfal.kvouchers.MenuManager.PlayerMenuUtility;
import net.starfal.kvouchers.Settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class KVouchers extends JavaPlugin {
    private static KVouchers instance;
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    public static KVouchers getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        load();
        log("<gradient:blue:green>kVouchers <green>| enabled!");
    }

    @Override
    public void onDisable() {
        log("<gradient:blue:green>kVouchers <red>| disabled!");
    }
    private void load(){
        Settings.getInstance().load();
        this.getCommand("vouchers").setExecutor(new VoucherCMD());
        this.getServer().getPluginManager().registerEvents(new MenuHandler(), this);
    }
    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(p))) { //See if the player has a playermenuutility "saved" for them

            //This player doesn't. Make one for them add add it to the hashmap
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(p); //Return the object by using the provided player
        }
    }
    public void log(String message){
        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize(message));
    }
}
