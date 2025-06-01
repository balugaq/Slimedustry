package me.diamondman121314.Slimedustry;

import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class Commands implements CommandExecutor {
    private final Plugin plugin;

    public Commands(Slimedustry plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage(ChatColors.color("&r\n&a粘液工厂 &2v" + plugin.getDescription().getVersion() + "\n&r\n&3/sd reload &b重载配置\n&3/sd charge {Player} &b为玩家主手物品充满电"));
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                if (!p.hasPermission("Slimedustry.reload")) {
                    p.sendMessage(ChatColor.DARK_RED + "你没有足够的权限!");
                    return true;
                }
                this.plugin.getConfig().options().copyDefaults(true);
                this.plugin.reloadConfig();
                p.sendMessage(ChatColor.DARK_GREEN + "配置重载成功!");
            }
            if (args[0].equalsIgnoreCase("charge")) {
                if (!p.hasPermission("Slimedustry.charge")) {
                    p.sendMessage(ChatColor.DARK_RED + "你没有足够的权限!");
                    return true;
                }
                if (args.length == 1) {
                    p.sendMessage(ChatColor.RED + "用法: /sd charge {Player}");
                    return true;
                }
                Player target = null;
                //byte b;
                //int i;
                //Player[] arrayOfPlayer;
                //for (i = (arrayOfPlayer = Bukkit.getServer().getOnlinePlayers()).length, b = 0; b < i; ) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    //Player online = arrayOfPlayer[b];
                    if (player.getName().equalsIgnoreCase(args[1])) {
                        target = Bukkit.getPlayer(args[1]);
                        //b++;
                    }
                }

                if (target == null) {
                    p.sendMessage(ChatColor.DARK_RED + "找不到指定玩家!");
                } else {
                    if (target.getInventory().getItemInMainHand() == null) {
                        return true;
                    }
                    if (target.getInventory().getItemInMainHand().getItemMeta().getLore() == null) {
                        return true;
                    }
                    List<String> lore = target.getInventory().getItemInMainHand().getItemMeta().getLore();
                    if (lore.size() < 3) {
                        return true;
                    }
                    if (((String) lore.get(1)).contains("Charge:") && ((String) lore.get(2)).contains("Capacity:")) {
                        double charge = Double.valueOf(((String) lore.get(1)).replace("Charge: ", "").replace(" J", "").replace("&7", "").replace("&b", "")).doubleValue();
                        double capacity = Double.valueOf(((String) lore.get(2)).replace("Capacity: ", "").replace(" J", "").replace("&7", "").replace("&b", "")).doubleValue();
                        charge = capacity;
                        lore.set(1, "&7Charge: &b" + String.valueOf(charge) + " J");
                        ItemMeta im = target.getInventory().getItemInMainHand().getItemMeta();
                        im.setLore(lore);
                        target.getInventory().getItemInMainHand().setItemMeta(im);
                    }
                }
            }
        } else {

            ConsoleCommandSender c = (ConsoleCommandSender) sender;
            if (args.length == 0) {
                c.sendMessage(ChatColors.color("&r\n&a粘液工厂 &2v" + plugin.getDescription().getVersion() + "\n&r\n&3/sd reload &b重载配置\n&3/sd charge {Player} &b为玩家主手物品充满电"));
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                this.plugin.getConfig().options().copyDefaults(true);
                this.plugin.reloadConfig();
                c.sendMessage(ChatColor.GREEN + "配置重载成功!");
            }
            if (args[0].equalsIgnoreCase("charge")) {
                if (args.length == 1) {
                    c.sendMessage(ChatColor.RED + "用法: /sd charge {PlayerName}");
                    return true;
                }
                Player target = null;
                //byte b;
                //int i;
                //Player[] arrayOfPlayer;
                //for (i = (arrayOfPlayer = Bukkit.getServer().getOnlinePlayers()).length, b = 0; b < i; ) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    //Player online = arrayOfPlayer[b];
                    if (player.getName().equalsIgnoreCase(args[1])) {
                        target = Bukkit.getPlayer(args[1]);
                        //b++;
                    }
                }

                if (target == null) {
                    c.sendMessage(ChatColor.RED + "找不到指定玩家!");
                } else {
                    if (target.getInventory().getItemInMainHand() == null) {
                        return true;
                    }
                    if (target.getInventory().getItemInMainHand().getItemMeta().getLore() == null) {
                        return true;
                    }
                    List<String> lore = target.getInventory().getItemInMainHand().getItemMeta().getLore();
                    if (lore.size() < 3) {
                        return true;
                    }
                    if (((String) lore.get(1)).contains("Charge:") && ((String) lore.get(2)).contains("Capacity:")) {
                        double charge = Double.valueOf(((String) lore.get(1)).replace("Charge: ", "").replace(" J", "").replace("&7", "").replace("&b", "")).doubleValue();
                        double capacity = Double.valueOf(((String) lore.get(2)).replace("Capacity: ", "").replace(" J", "").replace("&7", "").replace("&b", "")).doubleValue();
                        charge = capacity;
                        lore.set(1, "&7Charge: &b" + String.valueOf(charge) + " J");
                        ItemMeta im = target.getInventory().getItemInMainHand().getItemMeta();
                        im.setLore(lore);
                        target.getInventory().getItemInMainHand().setItemMeta(im);
                    }
                }
            }
        }
        return true;
    }
}
