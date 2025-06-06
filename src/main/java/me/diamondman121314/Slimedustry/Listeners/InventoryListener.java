package me.diamondman121314.Slimedustry.Listeners;

import me.diamondman121314.Slimedustry.Slimedustry;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class InventoryListener implements Listener {
    public InventoryListener(Slimedustry plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, (Plugin) plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryInteract(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory i = e.getInventory();
        if (i.getType() == InventoryType.DISPENSER) {
            Dispenser d = (Dispenser) i.getHolder();
            if (d.getBlock().getRelative(BlockFace.UP).getType() == Material.WHITE_STAINED_GLASS)
                e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryMove(InventoryMoveItemEvent e) {
        Inventory i = e.getSource();
        ItemStack moved = e.getItem();
        if (moved.getType() == Material.TERRACOTTA && moved.hasItemMeta() && (moved.getItemMeta().getDisplayName().equalsIgnoreCase("&b水") || moved.getItemMeta().getDisplayName().equalsIgnoreCase("&6岩浆")) && i.getType() == InventoryType.DISPENSER)
            e.setCancelled(true);
    }
}
