package me.diamondman121314.Slimedustry;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MassFabricator extends MultiBlockMachine {
    public MassFabricator(ItemGroup category, SlimefunItemStack item, ItemStack[] recipe, ItemStack[] machineRecipes, BlockFace trigger) {
        super(category, item, recipe, machineRecipes, trigger);
    }

    @Override
    public void onInteract(Player player, Block block) {

    }

    @Override
    public String getLabelLocalPath() {
        return null;
    }

    @Override
    public String getRecipeSectionLabel(Player p) {
        return null;
    }
}
