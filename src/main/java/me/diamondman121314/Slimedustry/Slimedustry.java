package me.diamondman121314.Slimedustry;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public class Slimedustry extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        if (!getServer().getPluginManager().isPluginEnabled("Slimefun")) {
            getLogger().severe("Please install Slimefun, as without it, the plugin will not work!\nYou can download Slimefun from https://thebusybiscuit.github.io/builds/TheBusyBiscuit/Slimefun4/master/!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getCommand("Slimedustry").setExecutor(new Commands(this));
        if (!new File(getDataFolder(), "config.yml").exists()) saveDefaultConfig();
        Setup.setupMisc(this);
        Setup.setupItems(this);
        Setup.setupResearches(this);
        //Setup.changeRecipes();
        //Setup.changeCategories();
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/balugaq/Slimedustry/issues/";
    }
}
