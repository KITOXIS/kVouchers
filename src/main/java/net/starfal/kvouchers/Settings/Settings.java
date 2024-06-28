package net.starfal.kvouchers.Settings;

import net.starfal.kvouchers.KVouchers;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public class Settings {
    private final static Settings instance = new Settings();
    private Settings() {
    }
    public static Settings getInstance() {
        return instance;
    }

    private File file;
    private YamlConfiguration config;
    private File enmsgFile;
    private YamlConfiguration messages_en;
    private File listFile;
    private YamlConfiguration list;
    public void load(){
        file = new File(KVouchers.getInstance().getDataFolder(), "settings.yml");
        enmsgFile = new File(KVouchers.getInstance().getDataFolder(), "languages/en.yml");
        listFile = new File(KVouchers.getInstance().getDataFolder(), "vouchers/voucher_list.yml");
        if (!file.exists()) {
            KVouchers.getInstance().saveResource("settings.yml", false);
        }
        if (!enmsgFile.exists()) {
            KVouchers.getInstance().saveResource("languages/en.yml", false);
        }
        if (!listFile.exists()) {
            KVouchers.getInstance().saveResource("vouchers/voucher_list.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(file);
        config.options().parseComments(true);
        messages_en = YamlConfiguration.loadConfiguration(enmsgFile);
        messages_en.options().parseComments(true);
        list = YamlConfiguration.loadConfiguration(listFile);
        list.options().parseComments(true);

        YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(Objects.requireNonNull(KVouchers.getInstance().getResource("settings.yml"))));

        for (String key : defaultConfig.getKeys(true)) {
            if (!config.contains(key)) {
                config.set(key, defaultConfig.get(key));
            }
        }

        YamlConfiguration defaultMessages = YamlConfiguration.loadConfiguration(new InputStreamReader(Objects.requireNonNull(KVouchers.getInstance().getResource("languages/en.yml"))));

        for (String key : defaultMessages.getKeys(true)) {
            if (!messages_en.contains(key)) {
                messages_en.set(key, defaultConfig.get(key));
            }
        }

        try {
            config.save(file);
            messages_en.save(enmsgFile);
            list.save(listFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void save(){
        try {
            config.save(file);
            messages_en.save(enmsgFile);
            list.save(listFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void set(String path, Object value){
        config.set(path, value);
        try {
            save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setMSG(String path, Object value){
        messages_en.set(path, value);
        try {
            save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Object get(String path){
        Object value = config.get(path);
        if (value == null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(Objects.requireNonNull(KVouchers.getInstance().getResource("settings.yml"))));
            if (defaultConfig.contains(path)) {
                value = defaultConfig.get(path);
                config.set(path, value);
                save();
                reload();
            }
        }
        return value;
    }
    public Object getLang(String path){
        Object value = messages_en.get(path);
        if (value == null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(Objects.requireNonNull(KVouchers.getInstance().getResource("languages/en.yml"))));
            if (defaultConfig.contains(path)) {
                value = defaultConfig.get(path);
                messages_en.set(path, value);
                save();
                reload();
            }
        }
        return value;
    }
    public boolean getBoolean(String path){
        if (config.contains(path)) {
            return config.getBoolean(path);
        } else {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(Objects.requireNonNull(KVouchers.getInstance().getResource("settings.yml"))));
            if (defaultConfig.contains(path)) {
                boolean value = defaultConfig.getBoolean(path);
                config.set(path, value);
                save();
                reload();
                return value;
            }
        }
        return false;
    }
    public String getString(String path){
        if (config.contains(path)) {
            return config.getString(path);
        } else {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(Objects.requireNonNull(KVouchers.getInstance().getResource("settings.yml"))));
            if (defaultConfig.contains(path)) {
                String value = defaultConfig.getString(path);
                config.set(path, value);
                save();
                reload();
                return value;
            }
        }
        return null;
    }
    public int getInt(String path){
        if (config.contains(path)) {
            return config.getInt(path);
        } else {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(Objects.requireNonNull(KVouchers.getInstance().getResource("settings.yml"))));
            if (defaultConfig.contains(path)) {
                int value = defaultConfig.getInt(path);
                config.set(path, value);
                save();
                reload();
                return value;
            }
        }
        return 0;
    }
    public List getList(String path){
        if (config.contains(path)) {
            return config.getList(path);
        } else {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(Objects.requireNonNull(KVouchers.getInstance().getResource("settings.yml"))));
            if (defaultConfig.contains(path)) {
                List value = defaultConfig.getList(path);
                config.set(path, value);
                save();
                reload();
                return value;
            }
        }
        return null;
    }
    public void reload(){
        config = YamlConfiguration.loadConfiguration(file);
        messages_en = YamlConfiguration.loadConfiguration(enmsgFile);
    }
    public List getVoucherList(){
        list = YamlConfiguration.loadConfiguration(listFile);
        return list.getList("Vouchers");
    }
    public void addVoucher(String name){
        List<String> voucherlist = list.getStringList("Vouchers");
        voucherlist.add(name);
        list.set("Vouchers", voucherlist);
        try {
            list.save(listFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeVoucher(String name){
        List<String> voucherlist = list.getStringList("Vouchers");
        voucherlist.remove(name);
        list.set("Vouchers", voucherlist);
        try {
            list.save(listFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
