package net.starfal.kvouchers;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.starfal.kvouchers.Commands.VoucherCMD;
import net.starfal.kvouchers.Settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class KVouchers extends JavaPlugin {
    private static KVouchers instance;
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
    }
    public void log(String message){
        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize(message));
    }
}
