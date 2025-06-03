package me.diamondman121314.Slimedustry.Listeners;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.libraries.dough.inventory.InvUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.diamondman121314.Slimedustry.Setup;
import me.diamondman121314.Slimedustry.Slimedustry;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Furnace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class InteractListener implements Listener {
    Plugin plugin;

    public InteractListener(Slimedustry plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, (Plugin) plugin);
        this.plugin = (Plugin) plugin;
    }

    public static boolean isDispenserEmpty(Dispenser d) {
        byte b;
        int i;
        ItemStack[] arrayOfItemStack;
        for (i = (arrayOfItemStack = d.getInventory().getContents()).length, b = 0; b < i; ) {
            ItemStack item = arrayOfItemStack[b];
            if (item != null)
                return false;
            b++;
        }

        return true;
    }

    public static boolean hasUnlocked(Player player, ItemStack itemStack) {
        SlimefunItem sfitem = SlimefunItem.getByItem(itemStack);
        if (sfitem != null) {
            Research research = sfitem.getResearch();
            if (research != null) {
                PlayerProfile profile = PlayerProfile.find(player).get();
                if (profile != null) {
                    if (profile.hasUnlocked(research)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteract(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (b.getType() == Material.CRAFTING_TABLE && b.getRelative(BlockFace.UP).getType() == Material.DISPENSER && b.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE && hasUnlocked(p, new CustomItemStack(Material.CRAFTING_TABLE, "&b工厂合成台", "", "&a&o用于工厂合成"))) {
                e.setCancelled(true);
                Dispenser d = (Dispenser) b.getRelative(BlockFace.UP).getState();
                final Inventory inv = d.getInventory();
                List<ItemStack[]> recipes = Setup.iwMachine.getRecipes();


                List<ItemStack[]> convertable = new ArrayList();

                int i;
                for (i = 0; i < recipes.size(); i++) {
                    if (i % 2 == 0) {
                        convertable.add(recipes.get(i));
                    }
                }

                for (i = 0; i < convertable.size(); i++) {
                    boolean craftable = true;
                    for (int j = 0; j < (inv.getContents()).length; j++) {
                        if (!SlimefunUtils.isItemSimilar(inv.getContents()[j], ((ItemStack[]) convertable.get(i))[j], true)) {
                            craftable = false;
                            break;
                        }
                    }
                    if (craftable) {
                        ItemStack adding = ((ItemStack[]) recipes.get(recipes.indexOf(convertable.get(i)) + 1))[0];
                        if (hasUnlocked(p, adding)) {
                            Inventory inv2 = Bukkit.createInventory(null, 9, "test");
                            int k;
                            for (k = 0; k < (inv.getContents()).length; k++) {
                                inv2.setItem(k, inv.getContents()[k]);
                            }
                            for (k = 0; k < 9; k++) {
                                if (inv2.getContents()[k] != null && inv2.getContents()[k].getType() != Material.AIR) {
                                    if (inv2.getContents()[k].getAmount() > 1) {
                                        inv2.setItem(k, (ItemStack) new CustomItemStack(inv2.getContents()[k], inv2.getContents()[k].getAmount() - 1));
                                    } else {
                                        inv2.setItem(k, null);
                                    }

                                }
                            }
                            if (InvUtils.fits(inv2, adding)) {
                                for (k = 0; k < 9; k++) {
                                    if (inv.getContents()[k] != null && inv.getContents()[k].getType() != Material.AIR) {
                                        if (inv.getContents()[k].getAmount() > 1) {
                                            inv.setItem(k, (ItemStack) new CustomItemStack(inv.getContents()[k], inv.getContents()[k].getAmount() - 1));
                                        } else {
                                            inv.setItem(k, null);
                                        }

                                    }
                                }
                                p.getWorld().playSound(b.getLocation(), Sound.BLOCK_LAVA_POP, 1.0F, 1.0F); // used to be WOOD_CLICK
                                inv.addItem(new ItemStack[]{adding});
                            } else {
                                //Messages.local.sendTranslation(p, Slimefun.getPrefix(true), "machines.full-inventory");
                                io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getLocalization().sendMessage(p, "machines.full-inventory", true);
                            }
                        }
                        return;
                    }
                }
                //Messages.local.sendTranslation(p, Slimefun.getPrefix(true), "machines.pattern-not-found");
                io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getLocalization().sendMessage(p, "machines.pattern-not-found", true);

            }


            if (b.getType() == Material.PISTON && b.getRelative(BlockFace.DOWN).getType() == Material.DISPENSER && hasUnlocked(p, new CustomItemStack(Material.PISTON, "&b压板机", "", "&a&o可以将锭压成板"))) {
                e.setCancelled(true);
                Dispenser d = (Dispenser) b.getRelative(BlockFace.DOWN).getState();
                final Inventory inv = d.getInventory();
                List<ItemStack[]> recipes = Setup.pbMachine.getRecipes();


                List<ItemStack> convertible = new ArrayList();
                for (int i = 0; i < recipes.size(); i++) {
                    if (i % 2 == 0)
                        convertible.add(((ItemStack[]) recipes.get(i))[0]);
                }
                byte b1;
                int j;
                ItemStack[] arrayOfItemStack;
                for (j = (arrayOfItemStack = inv.getContents()).length, b1 = 0; b1 < j; ) {
                    ItemStack current = arrayOfItemStack[b1];
                    for (Iterator<ItemStack> localIterator1 = convertible.iterator(); localIterator1.hasNext(); ) {
                        ItemStack convert = localIterator1.next();
                        if (current != null &&
                                SlimefunUtils.isItemSimilar(current, convert, true)) {
                            List<ItemStack> newRecipes = new ArrayList();
                            for (ItemStack[] recipe : recipes) {
                                newRecipes.add(recipe[0]);
                            }
                            ItemStack adding = newRecipes.get(newRecipes.indexOf(convert) + 1);
                            if (InvUtils.fits(inv, adding)) {
                                Object removing = current.clone();
                                ((ItemStack) removing).setAmount(1);
                                inv.removeItem(new ItemStack[]{(ItemStack) removing});
                                inv.addItem(new ItemStack[]{adding});
                                p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0F, 1.0F);
                            } else {
                                //Messages.local.sendTranslation(p, Slimefun.getPrefix(true), "machines.full-inventory");
                                io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getLocalization().sendMessage(p, "machines.full-inventory", true);
                            }
                            return;
                        }
                    }

                    b1++;
                }

                //Messages.local.sendTranslation(p, Slimefun.getPrefix(true), "machines.unknown-material");
                io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getLocalization().sendMessage(p, "machines.unknown-material", true);
            }


            if (b.getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE && b.getRelative(BlockFace.DOWN).getType() == Material.DISPENSER && b.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType() == Material.BEACON) {
                if (b.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST).getType() == Material.IRON_BLOCK && b.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST).getType() == Material.IRON_BLOCK) {
                    if (hasUnlocked(p, new CustomItemStack(Material.BEACON, "&9&l零件工厂", "&a&o可以制造工厂零件"))) {
                        e.setCancelled(true);
                        Dispenser d = (Dispenser) b.getRelative(BlockFace.DOWN).getState();
                        final Inventory inv = d.getInventory();
                        List<ItemStack[]> recipes = Setup.mfMachine.getRecipes();


                        List<ItemStack> convertable = new ArrayList();
                        for (int i = 0; i < recipes.size(); i++) {
                            if (i % 2 == 0)
                                convertable.add(((ItemStack[]) recipes.get(i))[0]);
                        }
                        byte b1;
                        int j;
                        ItemStack[] arrayOfItemStack;
                        for (j = (arrayOfItemStack = inv.getContents()).length, b1 = 0; b1 < j; ) {
                            ItemStack current = arrayOfItemStack[b1];
                            for (Iterator<ItemStack> localIterator1 = convertable.iterator(); localIterator1.hasNext(); ) {
                                ItemStack convert = localIterator1.next();
                                if (current != null &&
                                        SlimefunUtils.isItemSimilar(current, convert, true)) {
                                    List<ItemStack> newRecipes = new ArrayList();
                                    for (ItemStack[] recipe : recipes) {
                                        newRecipes.add(recipe[0]);
                                    }
                                    ItemStack adding = newRecipes.get(newRecipes.indexOf(convert) + 1);
                                    if (InvUtils.fits(inv, adding)) {
                                        Object removing = current.clone();
                                        ((ItemStack) removing).setAmount(1);
                                        inv.removeItem(new ItemStack[]{(ItemStack) removing});
                                        adding.setAmount(3);
                                        inv.addItem(new ItemStack[]{adding});
                                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0F, 1.0F);
                                    } else {
                                        //Messages.local.sendTranslation(p, Slimefun.getPrefix(true), "machines.full-inventory");
                                        io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getLocalization().sendMessage(p, "machines.full-inventory", true);
                                    }
                                    return;
                                }
                            }
                            b1++;
                        }

                        //Messages.local.sendTranslation(p, Slimefun.getPrefix(true), "machines.unknown-material");
                        io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getLocalization().sendMessage(p, "machines.unknown-material", true);
                    }
                } else if (b.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH).getType() == Material.IRON_BLOCK && b.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH).getType() == Material.IRON_BLOCK && hasUnlocked(p, new CustomItemStack(Material.BEACON, "&9&l零件工厂", "&a&o可以制造工厂零件"))) {
                    Furnace f = (Furnace) b.getRelative(BlockFace.DOWN).getState();
                    final FurnaceInventory inv = f.getInventory();
                    if (inv.getFuel().getType() == Material.DIAMOND && inv.getFuel().getItemMeta().getDisplayName().equalsIgnoreCase("&c&l能量水晶")) {
                        if (inv.getSmelting() == null || inv.getSmelting().getItemMeta().getDisplayName().equalsIgnoreCase("&d工厂零件")) {
                            if (inv.getFuel().getAmount() > 1) {
                                inv.getFuel().setAmount(inv.getFuel().getAmount() - 1);
                            } else {
                                inv.setFuel(null);
                            }
                            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0F, 1.0F);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
                                public void run() {
                                    if (inv.getSmelting() == null) {
                                        CustomItemStack customItem = new CustomItemStack(Material.INK_SAC, "&d工厂零件"); // TODO: RECEIVE 3
                                        inv.setSmelting((ItemStack) customItem);
                                    } else {
                                        inv.getSmelting().setAmount(inv.getSmelting().getAmount() + 3);
                                    }
                                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
                                }
                                //}100L);
                            });
                        } else {
                            //Messages.local.sendTranslation(p, Slimefun.getPrefix(true), "machines.full-inventory");
                            io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getLocalization().sendMessage(p, "machines.full-inventory", true);
                        }
                    } else {
                        //Messages.local.sendTranslation(p, Slimefun.getPrefix(true), "machines.unknown-material");
                        io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getLocalization().sendMessage(p, "machines.unknown-material", true);
                    }
                }
            }


            if (b.getType() == Material.WHITE_STAINED_GLASS) {
                Block down = b.getRelative(BlockFace.DOWN);
                if (down.getType() == Material.DISPENSER && down.getData() == 1 && hasUnlocked(p, new CustomItemStack(Material.WHITE_STAINED_GLASS, "&6储罐", "", "&a&o用于储存液体&7(水或岩浆)"))) {
                    e.setCancelled(true);
                    ItemStack HandItem = p.getInventory().getItemInMainHand();
                    Dispenser dispenser = (Dispenser) down.getState();
                    if (HandItem.getType() == Material.LAVA_BUCKET || HandItem.getType() == Material.WATER_BUCKET) {
                        if (HandItem.getType() == Material.LAVA_BUCKET) {
                            if (b.getData() == 1 || b.getData() == 0) {
                                dispenser.getInventory().addItem(new CustomItemStack(Material.TERRACOTTA, "&6岩浆"));
                                dispenser.update();
                                //b.setData((byte)1);
                                //b.setMetadata().setData((byte)0);
                                b.getLocation().getWorld().playSound(b.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 1.0F, 0.0F);
                            } else {
                                p.sendMessage("&a&l粘液科技 &7> &c这个储罐里面有岩浆，不能混入水!");
                                return;
                            }
                        }
                        if (HandItem.getType() == Material.WATER_BUCKET) {
                            if (b.getData() == 3 || b.getData() == 0) {
                                dispenser.getInventory().addItem(new CustomItemStack(Material.TERRACOTTA, "&b水"));
                                dispenser.update();
                                //b.setData((byte)3);
                                //b.setMetadata().setData((byte)3);
                                b.getLocation().getWorld().playSound(b.getLocation(), Sound.BLOCK_WATER_AMBIENT, 1.0F, 0.0F);
                            } else {
                                p.sendMessage("&a&l粘液科技 &7> &c这个储罐里面有水，不能混入岩浆!");
                                io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getLocalization().sendMessage(p, "machines.unknown-material", true);
                                return;
                            }
                        }
                        HandItem.setType(Material.BUCKET);
                        return;
                    }
                    if (HandItem.getType() == Material.BUCKET) {
                        if (b.getData() == 1 && down.getData() == 1) {
                            p.getInventory().getItemInMainHand().setType(Material.LAVA_BUCKET);
                            dispenser.getInventory().removeItem(new CustomItemStack(Material.TERRACOTTA, "&6岩浆"));
                            dispenser.update();
                            if (isDispenserEmpty(dispenser)) {
                                //b.setData((byte)0);
                                //b.setMetadata().setData((byte)0);
                            }
                        }
                        if (b.getData() == 3 && down.getData() == 1) {
                            p.getInventory().getItemInMainHand().setType(Material.WATER_BUCKET);
                            dispenser.getInventory().removeItem(new CustomItemStack(Material.TERRACOTTA, "&b水"));
                            dispenser.update();
                            if (isDispenserEmpty(dispenser)) {
                                //b.setData((byte)0);
                                //b.setMetadata().setData((byte)0);
                            }
                        }
                    }
                    int i = 0;
                    byte b1;
                    int j;
                    ItemStack[] arrayOfItemStack;
                    for (j = (arrayOfItemStack = dispenser.getInventory().getContents()).length, b1 = 0; b1 < j; ) {
                        ItemStack is = arrayOfItemStack[b1];
                        if (is != null && is.getType() != Material.AIR) {
                            i += is.getAmount();
                        }
                        b1++;
                    }

                    p.sendMessage("&a&l粘液科技 &7> &e储罐容量: &6" + String.valueOf(i) + "&e/&6576 &e桶");
                }
            }
        }

        e.setCancelled(true);
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) && p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_HORSE_ARMOR && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("&b激光采矿器") && hasUnlocked(p, new CustomItemStack(Material.DIAMOND_HORSE_ARMOR, "&b激光采矿器", "&6模式: &1挖矿", "&7电量: &b0.0 J", "&7电容: &b40.0 J", "&a&oRight click to shoot, left click to change mode"))) {
            List<String> lore = p.getInventory().getItemInMainHand().getItemMeta().getLore();
            double charge = Double.valueOf(((String) lore.get(1)).replace("电量: ", "").replace(" J", "").replace("&7", "").replace("&b", "")).doubleValue();
            if (charge <= 0.0) {
                return;
            }
            if (((String) lore.get(0)).equalsIgnoreCase("&6模式: &1挖矿") && charge - this.plugin.getConfig().getInt("MiningLaser.MiningCharge") < 0.0) {
                return;
            }
            if (((String) lore.get(0)).equalsIgnoreCase("&6模式: &1爆炸") && charge - this.plugin.getConfig().getInt("MiningLaser.ExplosiveCharge") < 0.0) {
                return;
            }
            if (((String) lore.get(0)).equalsIgnoreCase("&6模式: &1高温") && charge - this.plugin.getConfig().getInt("MiningLaser.SuperHeatCharge") < 0.0) {
                return;
            }
            Snowball snowball = (Snowball) p.launchProjectile(Snowball.class);
            snowball.setShooter((LivingEntity) p);
            snowball.setVelocity(snowball.getVelocity().multiply(3));
            if (((String) lore.get(0)).equalsIgnoreCase("&6模式: &1挖矿")) {
                charge = Double.valueOf((new DecimalFormat("##.##")).format(charge - this.plugin.getConfig().getInt("MiningLaser.MiningCharge")).replace(",", ".")).doubleValue();
            }
            if (((String) lore.get(0)).equalsIgnoreCase("&6模式: &1爆炸")) {
                charge = Double.valueOf((new DecimalFormat("##.##")).format(charge - this.plugin.getConfig().getInt("MiningLaser.ExplosiveCharge")).replace(",", ".")).doubleValue();
            }
            if (((String) lore.get(0)).equalsIgnoreCase("&6模式: &1高温")) {
                charge = Double.valueOf((new DecimalFormat("##.##")).format(charge - this.plugin.getConfig().getInt("MiningLaser.SuperHeatCharge")).replace(",", ".")).doubleValue();
            }
            lore.set(1, "&7电量: &b" + String.valueOf(charge) + " J");
            ItemMeta im = p.getInventory().getItemInMainHand().getItemMeta();
            im.setLore(lore);
            p.getInventory().getItemInMainHand().setItemMeta(im);
        }

        e.setCancelled(true);
        if ((e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) && p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_HORSE_ARMOR && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("&b激光采矿器") && hasUnlocked(p, new CustomItemStack(Material.DIAMOND_HORSE_ARMOR, "&b激光采矿器", "&6模式: &1挖矿", "&7电量: &b0.0 J", "&7电容: &b40.0 J", "&a&o右键发射使用, 左键切换模式"))) {
            ItemMeta im = p.getInventory().getItemInMainHand().getItemMeta();
            List<String> lore = im.getLore();
            if (((String) lore.get(0)).equalsIgnoreCase("&6模式: &1挖矿")) {
                lore.set(0, "&6模式: &1爆炸");
                im.setLore(lore);
                p.getInventory().getItemInMainHand().setItemMeta(im);
                return;
            }
            if (((String) lore.get(0)).equalsIgnoreCase("&6模式: &1爆炸")) {
                lore.set(0, "&6模式: &1高温");
                im.setLore(lore);
                p.getInventory().getItemInMainHand().setItemMeta(im);
                return;
            }
            if (((String) lore.get(0)).equalsIgnoreCase("&6模式: &1高温")) {
                lore.set(0, "&6模式: &1挖矿");
                im.setLore(lore);
                p.getInventory().getItemInMainHand().setItemMeta(im);
                return;
            }
        }
    }
}