package net.starfal.kvouchers.Commands;

import net.starfal.kvouchers.Functions.Color;
import net.starfal.kvouchers.KVouchers;
import net.starfal.kvouchers.MenuManager.Menus.CreateVoucher.MainVoucherCreate;
import net.starfal.kvouchers.MenuManager.Menus.VoucherMain;
import net.starfal.kvouchers.Settings.Settings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VoucherCMD implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("vouchers")) {
            if (sender instanceof Player p) {
                if (args.length == 0) {
                    String perm = Settings.getInstance().getString("Permissions.MainMenu");
                    if (perm == null) {
                        p.sendMessage(Color.format("&cPermission not set in config. Please contact an admin to fix this issue."));
                    }
                    if (p.hasPermission(perm)) {
                        new VoucherMain(KVouchers.getPlayerMenuUtility(p)).open();
                        return true;
                    } else {
                        String msg = (String) Settings.getInstance().getLang("General.No-Permission");
                        String prefix = (String) Settings.getInstance().getLang("General.Prefix");
                        msg = msg.replace("%permission%", perm);
                        msg = msg.replace("%prefix%", prefix);
                        p.sendMessage(Color.format(msg));
                    }
                } else {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("create")) {
                            String perm = Settings.getInstance().getString("Permissions.CreateVoucher");
                            if (perm == null) {
                                p.sendMessage(Color.format("&cPermission not set in config. Please contact an admin to fix this issue."));
                            }
                            if (p.hasPermission(perm)) {
                                new MainVoucherCreate(KVouchers.getPlayerMenuUtility(p)).open();
                                return true;
                            } else {
                                String msg = (String) Settings.getInstance().getLang("General.No-Permission");
                                String prefix = (String) Settings.getInstance().getLang("General.Prefix");
                                msg = msg.replace("%permission%", perm);
                                msg = msg.replace("%prefix%", prefix);
                                p.sendMessage(Color.format(msg));
                            }
                        } else if (args[0].equalsIgnoreCase("delete")) {
                            String perm = Settings.getInstance().getString("Permissions.DeleteVoucher");
                            if (perm == null) {
                                p.sendMessage(Color.format("&cPermission not set in config. Please contact an admin to fix this issue."));
                            }
                            if (p.hasPermission(perm)) {
                                new VoucherMain(KVouchers.getPlayerMenuUtility(p)).open();
                                return true;
                            } else {
                                String msg = (String) Settings.getInstance().getLang("General.No-Permission");
                                String prefix = (String) Settings.getInstance().getLang("General.Prefix");
                                msg = msg.replace("%permission%", perm);
                                msg = msg.replace("%prefix%", prefix);
                                p.sendMessage(Color.format(msg));
                            }
                        } else if (args[0].equalsIgnoreCase("list")) {
                            String perm = Settings.getInstance().getString("Permissions.ListVouchers");
                            if (perm == null) {
                                p.sendMessage(Color.format("&cPermission not set in config. Please contact an admin to fix this issue."));
                            }
                            if (p.hasPermission(perm)) {
                                new VoucherMain(KVouchers.getPlayerMenuUtility(p)).open();
                                return true;
                            } else {
                                String msg = (String) Settings.getInstance().getLang("General.No-Permission");
                                String prefix = (String) Settings.getInstance().getLang("General.Prefix");
                                msg = msg.replace("%permission%", perm);
                                msg = msg.replace("%prefix%", prefix);
                                p.sendMessage(Color.format(msg));
                            }
                        } else if (args[0].equalsIgnoreCase("reload")) {
                            String perm = Settings.getInstance().getString("Permissions.Admin");
                            if (perm == null) {
                                p.sendMessage(Color.format("&cPermission not set in config. Please contact an admin to fix this issue."));
                            }
                            if (p.hasPermission(perm)) {
                                Settings.getInstance().reload();
                                String msg = (String) Settings.getInstance().getLang("General.Reloaded");
                                String prefix = (String) Settings.getInstance().getLang("General.Prefix");
                                msg = msg.replace("%prefix%", prefix);
                                p.sendMessage(Color.format(msg));
                                return true;
                            } else {
                                String msg = (String) Settings.getInstance().getLang("General.No-Permission");
                                String prefix = (String) Settings.getInstance().getLang("General.Prefix");
                                msg = msg.replace("%permission%", perm);
                                msg = msg.replace("%prefix%", prefix);
                                p.sendMessage(Color.format(msg));
                            }
                        } else if (args[0].equalsIgnoreCase("rename")) {
                            String perm = Settings.getInstance().getString("Permissions.RenameVoucher");
                            if (perm == null) {
                                p.sendMessage(Color.format("&cPermission not set in config. Please contact an admin to fix this issue."));
                            }
                            if (p.hasPermission(perm)) {
                                new VoucherMain(KVouchers.getPlayerMenuUtility(p)).open();
                                return true;
                            } else {
                                String msg = (String) Settings.getInstance().getLang("General.No-Permission");
                                String prefix = (String) Settings.getInstance().getLang("General.Prefix");
                                msg = msg.replace("%permission%", perm);
                                msg = msg.replace("%prefix%", prefix);
                                p.sendMessage(Color.format(msg));
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of("create", "delete", "list", "reload");
    }
}
