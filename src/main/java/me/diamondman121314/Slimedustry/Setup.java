package me.diamondman121314.Slimedustry;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.ChargableItem;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class Setup {
    public static MultiBlockMachine iwMachine;
    public static MultiBlockMachine pbMachine;
    public static MultiBlockMachine mfMachine;
    static RecipeType iwType;
    static RecipeType pbType;
    static RecipeType mfType;
    static RecipeType ldType;
    static ItemGroup SLIMEDUSTRY;
    static ItemGroup DUSTS;
    static ItemGroup PLATES;
    static ItemGroup INGOTS;
    public static final SlimefunItemStack INDUSTRIAL_WORKBENCH = new SlimefunItemStack("INDUSTRIAL_WORKBENCH", Material.CRAFTING_TABLE, "&bIndustrial Crafting Table", "", "&a&oUsed for Industrial Crafting");
    public static final SlimefunItemStack PLATE_BENDER = new SlimefunItemStack("PLATE_BENDER", Material.PISTON, "&bPlate Bender", "", "&a&oCan bend Ingots into Plates");
    public static final SlimefunItemStack MASS_FABRICATOR = new SlimefunItemStack("MASS_FABRICATOR", Material.BEACON, "&9&lMass Fabricator", "", "&a&oGenerates UU-Matter");
    public static final SlimefunItemStack TANK = new SlimefunItemStack("TANK", Material.WHITE_STAINED_GLASS, "&6Tank", "", "&a&oStores Liquids");
    public static final SlimefunItemStack REINFORCED_GLASS = new SlimefunItemStack("REINFORCED_GLASS", Material.GLASS, "&bReinforced Glass");
    public static final SlimefunItemStack NUKE = new SlimefunItemStack("NUKE", Material.TNT, "&4Nuke", "&cAre you sure this is a good idea?", "&3Automatically detonates on placement");
    public static final SlimefunItemStack RUBBER = new SlimefunItemStack("RUBBER", Material.LEATHER, "&bRubber");
    public static final SlimefunItemStack STICKY_RESIN = new SlimefunItemStack("STICKY_RESIN", Material.CLAY_BALL, "&bSticky Resin");
    public static final SlimefunItemStack REINFORCED_ALLOY_PLATE = new SlimefunItemStack("REINFORCED_ALLOY_PLATE", Material.PAPER, "&bReinforced Alloy Plate");
    public static final SlimefunItemStack TITANIUM_INGOT = new SlimefunItemStack("TITANIUM_INGOT", Material.IRON_INGOT, "&bTitanium Ingot");
    public static final SlimefunItemStack TITANIUM_DUST = new SlimefunItemStack("TITANIUM_DUST", Material.SUGAR, "&6Titanium Dust");
    public static final SlimefunItemStack IMPURE_TITANIUM_DUST = new SlimefunItemStack("IMPURE_TITANIUM", Material.GUNPOWDER, "&bImpure Titanium Dust");
    public static final SlimefunItemStack IRIDIUM_ORE = new SlimefunItemStack("IRIDIUM_ORE", Material.GHAST_TEAR, "&f&lIridium Ore");
    public static final SlimefunItemStack IRIDIUM_PLATE = new SlimefunItemStack("IRIDIUM_PLATE", Material.PAPER, "&f&lIridium Plate");
    public static final SlimefunItemStack UU_MATTER = new SlimefunItemStack("UU_MATTER", Material.INK_SAC, "&dUU-Matter");
    public static final SlimefunItemStack SD_RUBBER_BOOTS = new SlimefunItemStack("SD_RUBBER_BOOTS", Material.LEATHER_BOOTS, Color.BLACK, "&6Rubber Boots", "&a&oPrevents Fall Damage");
    public static final SlimefunItemStack STATIC_BOOTS = new SlimefunItemStack("STATIC_BOOTS", Material.LEATHER_BOOTS, Color.BLACK, "&6Static Boots", "", "&a&oCharges the item in your hand while you walk");
    public static final SlimefunItemStack NANO_HELMET = new SlimefunItemStack("NANO_HELMET", Material.LEATHER_HELMET, Color.BLACK, "&8&lNanoSuit Helmet", "&a&oNightVision included!", "&7Charge: &b0.0 J", "&7Capacity: &b1000.0 J");
    public static final SlimefunItemStack NANO_CHESTPLATE = new SlimefunItemStack("NANO_CHESTPLATE", Material.LEATHER_CHESTPLATE, Color.BLACK, "&8&lNanoSuit Chestplate", "", "&7Charge: &b0.0 J", "&7Capacity: &b1000.0 J");
    public static final SlimefunItemStack NANO_LEGGINGS = new SlimefunItemStack("NANO_LEGGINGS", Material.LEATHER_LEGGINGS, Color.BLACK, "&8&lNanoSuit Leggings", "", "&7Charge: &b0.0 J", "&7Capacity: &b1000.0 J");
    public static final SlimefunItemStack NANO_BOOTS = new SlimefunItemStack("NANO_BOOTS", Material.LEATHER_BOOTS, Color.BLACK, "&8&lNanoSuit Boots", "", "&7Charge: &b0.0 J", "&7Capacity: &b1000.0 J");
    public static final SlimefunItemStack MINING_LASER = new SlimefunItemStack("MINING_LASER", Material.DIAMOND_HORSE_ARMOR, "&bMining Laser", "&6Mode: &1Mining", "&7Charge: &b0.0 J", "&7Capacity: &b40.0 J", "&a&oRight click to shoot, left click to change mode");
    public static final SlimefunItemStack ROCK_CUTTER = new SlimefunItemStack("ROCK_CUTTER", Material.DIAMOND_PICKAXE, "&bRock Cutter", "", "&7Charge: &b0.0 J", "&7Capacity: &b100.0 J");
    public static final SlimefunItemStack COMPOSITE_VEST = new SlimefunItemStack("COMPOSITE_VEST", Material.IRON_CHESTPLATE, "&7Composite Vest");

    public static void setupMisc(Slimedustry main) {
        SLIMEDUSTRY = new ItemGroup(new NamespacedKey(main, "slimedustry"), new CustomItemStack(Material.BEACON, "&7Slimedustry"));
        DUSTS = new ItemGroup(new NamespacedKey(main, "dusts"), new CustomItemStack(Material.SUGAR, "&7Dusts"));
        PLATES = new ItemGroup(new NamespacedKey(main, "plates"), new CustomItemStack(Material.MAP, "&7Plates"));
        INGOTS = new ItemGroup(new NamespacedKey(main, "ingots"), new CustomItemStack(Material.IRON_INGOT, "&7Ingots"));

        iwType = new RecipeType(new NamespacedKey(main, "industrial_crafting_table_material"), new CustomItemStack(Material.CRAFTING_TABLE, "&bIndustrial Crafting Table", "", "&a&oCraft it in an Industrial Crafting Table"));
        pbType = new RecipeType(new NamespacedKey(main, "plate_bender_material"), new CustomItemStack(Material.PISTON, "&bPlate Bender", "", "&a&oBend it in a Plate Bender"));
        mfType = new RecipeType(new NamespacedKey(main, "mass_fabricator_material"), new CustomItemStack(Material.BEACON, "&9&lMass Fabricator", "", "&a&oFabricate it using a Mass Fabricator"));
        ldType = new RecipeType(new NamespacedKey(main, "leaves_decay_material"), new CustomItemStack(Material.OAK_LEAVES, "&bLeaves Decay", "", "&aObtain this item when Leaves decay (can be any leaves)"));
    }

    public static void setupItems(Slimedustry main) {
        ROCK_CUTTER.addEnchantment(Enchantment.SILK_TOUCH, 1);
        COMPOSITE_VEST.addUnsafeEnchantment(Enchantment.DURABILITY, 5);

        iwMachine = new MultiBlockMachine(SLIMEDUSTRY, INDUSTRIAL_WORKBENCH, new ItemStack[]{null, new ItemStack(Material.HEAVY_WEIGHTED_PRESSURE_PLATE), null, null, new ItemStack(Material.DISPENSER), null, null, new ItemStack(Material.CRAFTING_TABLE), null}, BlockFace.SELF) {
            @Override
            public void onInteract(Player player, Block block) {
            }
        };
        pbMachine = new MultiBlockMachine(SLIMEDUSTRY, PLATE_BENDER, new ItemStack[] {null, null, null, null, new ItemStack(Material.PISTON), null, null, new CustomItemStack(Material.DISPENSER, "&r&oDispenser (Facing Up)"), null}, /*new ItemStack[] {SlimefunItems.STEEL_INGOT, SlimefunItems.STEEL_PLATE, SlimefunItems.REINFORCED_ALLOY_INGOT, REINFORCED_ALLOY_PLATE}, */new ItemStack[0], BlockFace.SELF)  {
            @Override
            public void onInteract(Player player, Block block) {
            }
        };
        mfMachine = new MultiBlockMachine(SLIMEDUSTRY, MASS_FABRICATOR, new ItemStack[] {null, new ItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE), null, new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.DISPENSER), new ItemStack(Material.IRON_BLOCK), null, new ItemStack(Material.BEACON), null}, /*new ItemStack[] {SlimefunItems.POWER_CRYSTAL, UU_MATTER}, */new ItemStack[0], BlockFace.SELF)  {
            @Override
            public void onInteract(Player player, Block block) {
            }
        };
        SlimefunItem tankItem = new SlimefunItem(SLIMEDUSTRY, TANK, RecipeType.MULTIBLOCK, new ItemStack[] {null, null, null, null, new ItemStack(Material.WHITE_STAINED_GLASS), null, null, new CustomItemStack(Material.DISPENSER, "&r&oDispenser (Facing Up)"), null});
        SlimefunItem rgItem = new SlimefunItem(SLIMEDUSTRY, REINFORCED_GLASS, iwType, new ItemStack[] {new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), REINFORCED_ALLOY_PLATE, new ItemStack(Material.GLASS), REINFORCED_ALLOY_PLATE, new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS)});
        SlimefunItem nukeItem = new SlimefunItem(SLIMEDUSTRY, NUKE, iwType, new ItemStack[] {new ItemStack(Material.GUNPOWDER), SlimefunItems.URANIUM, new ItemStack(Material.GUNPOWDER), SlimefunItems.URANIUM, new ItemStack(Material.GUNPOWDER), SlimefunItems.URANIUM, new ItemStack(Material.GUNPOWDER), SlimefunItems.URANIUM, new ItemStack(Material.GUNPOWDER)});
        SlimefunItem rubberItem = new SlimefunItem(Categories.RESOURCES, RUBBER, RecipeType.SMELTERY, new ItemStack[] {STICKY_RESIN, null, null, null, null, null, null, null, null});
        SlimefunItem srItem = new SlimefunItem(Categories.MISC, STICKY_RESIN, ldType, new ItemStack[] {null, null, null, null, new ItemStack(Material.OAK_LEAVES), null, null, null, null});
        SlimefunItem rapItem = new SlimefunItem(PLATES, REINFORCED_ALLOY_PLATE, pbType, new ItemStack[] {null, null, null, null, SlimefunItems.REINFORCED_ALLOY_INGOT, null, null, null, null});
        SlimefunItem tiItem = new SlimefunItem(INGOTS, TITANIUM_INGOT, RecipeType.SMELTERY, new ItemStack[] {TITANIUM_DUST, null, null, null, null, null, null, null, null});
        SlimefunItem tdItem = new SlimefunItem(DUSTS, TITANIUM_DUST, iwType, new ItemStack[] {IMPURE_TITANIUM_DUST, IMPURE_TITANIUM_DUST, IMPURE_TITANIUM_DUST, IMPURE_TITANIUM_DUST, new ItemStack(Material.WATER_BUCKET), IMPURE_TITANIUM_DUST, IMPURE_TITANIUM_DUST, IMPURE_TITANIUM_DUST, IMPURE_TITANIUM_DUST});
        SlimefunItem itItem = new SlimefunItem(DUSTS, IMPURE_TITANIUM_DUST, RecipeType.ORE_WASHER, new ItemStack[] {SlimefunItems.SIFTED_ORE, null, null, null, null, null, null, null, null});
        SlimefunItem ioItem = new SlimefunItem(Categories.RESOURCES, IRIDIUM_ORE, iwType, new ItemStack[] {UU_MATTER, UU_MATTER, UU_MATTER, null, UU_MATTER, null, UU_MATTER, UU_MATTER, UU_MATTER});
        SlimefunItem ipItem = new SlimefunItem(PLATES, IRIDIUM_PLATE, iwType, new ItemStack[] {IRIDIUM_ORE, REINFORCED_ALLOY_PLATE, IRIDIUM_ORE, REINFORCED_ALLOY_PLATE, new ItemStack(Material.DIAMOND), REINFORCED_ALLOY_PLATE, IRIDIUM_ORE, REINFORCED_ALLOY_PLATE, IRIDIUM_ORE});
        SlimefunItem uuItem = new SlimefunItem(Categories.RESOURCES, UU_MATTER, mfType, new ItemStack[] {null, null, null, null, SlimefunItems.POWER_CRYSTAL, null, null, null, null});
        SlimefunItem rbItem = new SlimefunItem(Categories.TECH, SD_RUBBER_BOOTS, iwType, new ItemStack[] {null, null, null, RUBBER, null, RUBBER, RUBBER, new ItemStack(Material.SLIME_BLOCK), RUBBER});
        SlimefunItem sbItem = new SlimefunItem(Categories.TECH, STATIC_BOOTS, iwType, new ItemStack[] {null, new ItemStack(Material.IRON_BOOTS), null, null, new ItemStack(Material.WHITE_WOOL), null, RUBBER, RUBBER, RUBBER});
        ChargableItem nhItem = new ChargableItem(Categories.ARMOR, NANO_HELMET, RecipeType.ARMOR_FORGE, new ItemStack[] {SlimefunItems.CARBON, SlimefunItems.POWER_CRYSTAL, SlimefunItems.CARBON, SlimefunItems.CARBON, SlimefunItems.NIGHT_VISION_GOGGLES, SlimefunItems.CARBON, null, null, null});
        ChargableItem ncItem = new ChargableItem(Categories.ARMOR, NANO_CHESTPLATE, RecipeType.ARMOR_FORGE, new ItemStack[] {SlimefunItems.CARBON, null, SlimefunItems.CARBON, SlimefunItems.CARBON, SlimefunItems.POWER_CRYSTAL, SlimefunItems.CARBON, SlimefunItems.CARBON, SlimefunItems.CARBON, SlimefunItems.CARBON});
        ChargableItem nlItem = new ChargableItem(Categories.ARMOR, NANO_LEGGINGS, RecipeType.ARMOR_FORGE, new ItemStack[] {SlimefunItems.CARBON, SlimefunItems.POWER_CRYSTAL, SlimefunItems.CARBON, SlimefunItems.CARBON, null, SlimefunItems.CARBON, SlimefunItems.CARBON, null, SlimefunItems.CARBON});
        ChargableItem nbItem = new ChargableItem(Categories.ARMOR, NANO_BOOTS, RecipeType.ARMOR_FORGE, new ItemStack[] {null, null, null, SlimefunItems.CARBON, null, SlimefunItems.CARBON, SlimefunItems.CARBON, SlimefunItems.POWER_CRYSTAL, SlimefunItems.CARBON});
        ChargableItem mlItem = new ChargableItem(Categories.TECH, MINING_LASER, iwType, new ItemStack[] {new ItemStack(Material.REDSTONE), SlimefunItems.POWER_CRYSTAL, SlimefunItems.POWER_CRYSTAL, REINFORCED_ALLOY_PLATE, REINFORCED_ALLOY_PLATE, SlimefunItems.ADVANCED_CIRCUIT_BOARD, null, REINFORCED_ALLOY_PLATE, REINFORCED_ALLOY_PLATE});
        ChargableItem rcItem = new ChargableItem(Categories.TECH, ROCK_CUTTER, iwType, new ItemStack[] {new ItemStack(Material.DIAMOND), TITANIUM_INGOT, null, new ItemStack(Material.DIAMOND), TITANIUM_INGOT, null, new ItemStack(Material.DIAMOND), SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.BATTERY});
        SlimefunItem cvItem = new SlimefunItem(Categories.ARMOR, COMPOSITE_VEST, RecipeType.ARMOR_FORGE, new ItemStack[] {REINFORCED_ALLOY_PLATE, null, REINFORCED_ALLOY_PLATE, REINFORCED_ALLOY_PLATE, new ItemStack(Material.IRON_CHESTPLATE), REINFORCED_ALLOY_PLATE, REINFORCED_ALLOY_PLATE, new ItemStack(Material.LEATHER_CHESTPLATE), REINFORCED_ALLOY_PLATE});

        mfMachine.addRecipe(uuItem.getRecipe(), UU_MATTER);
        iwMachine.addRecipe(rgItem.getRecipe(), REINFORCED_GLASS);
        iwMachine.addRecipe(mlItem.getRecipe(), MINING_LASER);
        iwMachine.addRecipe(rcItem.getRecipe(), ROCK_CUTTER);
        iwMachine.addRecipe(sbItem.getRecipe(), STATIC_BOOTS);
        iwMachine.addRecipe(ioItem.getRecipe(), IRIDIUM_ORE);
        iwMachine.addRecipe(ipItem.getRecipe(), IRIDIUM_PLATE);
        iwMachine.addRecipe(tdItem.getRecipe(), TITANIUM_DUST);
        iwMachine.addRecipe(rbItem.getRecipe(), SD_RUBBER_BOOTS);

        //mfMachine.addRecipe(new ItemStack[] {null, null, null, null, null, null, null, SlimefunItems.POWER_CRYSTAL, null}, SlimefunItem.getByItem(UU_MATTER));
        iwMachine.addRecipe(new ItemStack[] {RUBBER, RUBBER, RUBBER, new ItemStack(Material.REDSTONE), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.REDSTONE), RUBBER, RUBBER, RUBBER}, SlimefunItems.BASIC_CIRCUIT_BOARD);
        iwMachine.addRecipe(new ItemStack[] {new ItemStack(Material.REDSTONE), new ItemStack(Material.GLOWSTONE_DUST), new ItemStack(Material.REDSTONE), new ItemStack(Material.INK_SAC), SlimefunItems.BASIC_CIRCUIT_BOARD, new ItemStack(Material.INK_SAC), new ItemStack(Material.REDSTONE), new ItemStack(Material.GLOWSTONE_DUST), new ItemStack(Material.REDSTONE)}, SlimefunItems.ADVANCED_CIRCUIT_BOARD);
        iwMachine.addRecipe(new ItemStack[] {new ItemStack(Material.ORANGE_WOOL), new ItemStack(Material.ORANGE_WOOL), new ItemStack(Material.ORANGE_WOOL), new ItemStack(Material.BLACK_WOOL), REINFORCED_GLASS, new ItemStack(Material.BLACK_WOOL), null, null, null}, SlimefunItems.SCUBA_HELMET);
        iwMachine.addRecipe(new ItemStack[] {new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.COAL_BLOCK), REINFORCED_GLASS, new ItemStack(Material.COAL_BLOCK), REINFORCED_GLASS, new ItemStack(Material.COAL_BLOCK), null, new ItemStack(Material.COAL_BLOCK)}, SlimefunItems.NIGHT_VISION_GOGGLES);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.BATTERY).getRecipe(), SlimefunItems.BATTERY);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.STEEL_THRUSTER).getRecipe(), SlimefunItems.STEEL_THRUSTER);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.POWER_CRYSTAL).getRecipe(), SlimefunItems.POWER_CRYSTAL);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.DURALUMIN_JETPACK).getRecipe(), SlimefunItems.DURALUMIN_JETPACK);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.DURALUMIN_MULTI_TOOL).getRecipe(), SlimefunItems.DURALUMIN_MULTI_TOOL);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.DURALUMIN_JETBOOTS).getRecipe(), SlimefunItems.DURALUMIN_JETBOOTS);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.SOLDER_JETPACK).getRecipe(), SlimefunItems.SOLDER_JETPACK);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.SOLDER_MULTI_TOOL).getRecipe(), SlimefunItems.SOLDER_MULTI_TOOL);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.SOLDER_JETBOOTS).getRecipe(), SlimefunItems.SOLDER_JETBOOTS);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.BILLON_JETPACK).getRecipe(), SlimefunItems.BILLON_JETPACK);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.BILLON_MULTI_TOOL).getRecipe(), SlimefunItems.BILLON_MULTI_TOOL);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.BILLON_JETBOOTS).getRecipe(), SlimefunItems.BILLON_JETBOOTS);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.STEEL_JETPACK).getRecipe(), SlimefunItems.STEEL_JETPACK);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.STEEL_MULTI_TOOL).getRecipe(), SlimefunItems.STEEL_MULTI_TOOL);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.STEEL_JETBOOTS).getRecipe(), SlimefunItems.STEEL_JETBOOTS);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.DAMASCUS_STEEL_JETPACK).getRecipe(), SlimefunItems.DAMASCUS_STEEL_JETPACK);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.DAMASCUS_STEEL_MULTI_TOOL).getRecipe(), SlimefunItems.DAMASCUS_STEEL_MULTI_TOOL);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.DAMASCUS_STEEL_JETBOOTS).getRecipe(), SlimefunItems.DAMASCUS_STEEL_JETBOOTS);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.REINFORCED_ALLOY_JETPACK).getRecipe(), SlimefunItems.REINFORCED_ALLOY_JETPACK);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.REINFORCED_ALLOY_MULTI_TOOL).getRecipe(), SlimefunItems.REINFORCED_ALLOY_MULTI_TOOL);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.REINFORCED_ALLOY_JETBOOTS).getRecipe(), SlimefunItems.REINFORCED_ALLOY_JETBOOTS);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.CARBONADO_JETPACK).getRecipe(), SlimefunItems.CARBONADO_JETPACK);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.CARBONADO_MULTI_TOOL).getRecipe(), SlimefunItems.CARBONADO_MULTI_TOOL);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.CARBONADO_JETBOOTS).getRecipe(), SlimefunItems.CARBONADO_JETBOOTS);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.PARACHUTE).getRecipe(), SlimefunItems.PARACHUTE);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.SOLAR_HELMET).getRecipe(), SlimefunItems.SOLAR_HELMET);
        iwMachine.addRecipe(SlimefunItem.getByItem(SlimefunItems.ARMORED_JETBOOTS).getRecipe(), SlimefunItems.ARMORED_JETBOOTS);
        ((MultiBlockMachine)SlimefunItem.getByItem(SlimefunItems.ORE_WASHER)).addRecipe(new ItemStack[] {SlimefunItems.SIFTED_ORE}, IMPURE_TITANIUM_DUST);

        iwMachine.register(main);
        tankItem.register(main);
        rgItem.register(main);
        pbMachine.register(main);
        mlItem.register(main);
        rubberItem.register(main);
        nukeItem.register(main);
        srItem.register(main);
        rapItem.register(main);
        sbItem.register(main);
        mfMachine.register(main);
        uuItem.register(main);
        tiItem.register(main);
        tdItem.register(main);
        rcItem.register(main);
        ioItem.register(main);
        ipItem.register(main);
        nhItem.register(main);
        ncItem.register(main);
        nlItem.register(main);
        nbItem.register(main);
        itItem.register(main);
        rbItem.register(main);
        cvItem.register(main);
    }

    public static void setupResearches(Slimedustry main) {
        new Research(new NamespacedKey(main, "industrial_workbench"), 85851001, "Industrial Crafting Table", 8).addItems(INDUSTRIAL_WORKBENCH).register();
        new Research(new NamespacedKey(main, "tank"), 85851002, "Liquid Storage", 7).addItems(TANK).register();
        new Research(new NamespacedKey(main, "reinforced_glass"), 85851003, "Reinforced Glass", 10).addItems(REINFORCED_GLASS).register();
        new Research(new NamespacedKey(main, "plate_bender"), 85851004, "Plate Bender", 12).addItems(PLATE_BENDER).register();
        new Research(new NamespacedKey(main, "mining_laser"), 85851005,"Mining Laser", 13).addItems(MINING_LASER).register();
        new Research(new NamespacedKey(main, "nuke"), 85851006, "Nuke", 15).addItems(NUKE).register();
        new Research(new NamespacedKey(main, "sticky_situation"), 85851007, "Sticky Situation", 5).addItems(STICKY_RESIN).register();
        new Research(new NamespacedKey(main, "plates"), 85851008, "Plates", 6).addItems(REINFORCED_ALLOY_PLATE, IRIDIUM_PLATE).register();
        new Research(new NamespacedKey(main, "tech_boots"), 85851009, "Tech Boots", 7).addItems(STATIC_BOOTS, SD_RUBBER_BOOTS).register();
        new Research(new NamespacedKey(main, "totally_worth_it"), 85851010, "Totally Worth It", 18).addItems(MASS_FABRICATOR).register();
        new Research(new NamespacedKey(main, "high-grade"), 85851011, "High-Grade", 18).addItems(TITANIUM_INGOT, TITANIUM_DUST, IMPURE_TITANIUM_DUST).register();
        new Research(new NamespacedKey(main, "cutting_the_rocks"), 85851012, "Cutting The Rocks", 14).addItems(ROCK_CUTTER).register();
        new Research(new NamespacedKey(main, "iridium_ore"), 85851013, "Iridium Ore", 15).addItems(IRIDIUM_ORE).register();
        new Research(new NamespacedKey(main, "nanosuit"), 85851014, "NanoSuit", 13).addItems(NANO_HELMET, NANO_CHESTPLATE, NANO_LEGGINGS, NANO_BOOTS).register();
        new Research(new NamespacedKey(main, "tankin"), 85851014, "Tankin'", 11).addItems(COMPOSITE_VEST).register();
    }

    public static void changeRecipes() {
        SlimefunItem.getByItem(SlimefunItems.BASIC_CIRCUIT_BOARD).setRecipe(new ItemStack[] {RUBBER, RUBBER, RUBBER, new ItemStack(Material.REDSTONE), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.REDSTONE), RUBBER, RUBBER, RUBBER});
        SlimefunItem.getByItem(SlimefunItems.BASIC_CIRCUIT_BOARD).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.ADVANCED_CIRCUIT_BOARD).setRecipe(new ItemStack[] {new ItemStack(Material.REDSTONE), new ItemStack(Material.GLOWSTONE_DUST), new ItemStack(Material.REDSTONE), new ItemStack(Material.INK_SAC), SlimefunItems.BASIC_CIRCUIT_BOARD, new ItemStack(Material.INK_SAC), new ItemStack(Material.REDSTONE), new ItemStack(Material.GLOWSTONE_DUST), new ItemStack(Material.REDSTONE)});
        SlimefunItem.getByItem(SlimefunItems.ADVANCED_CIRCUIT_BOARD).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.STEEL_THRUSTER).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.POWER_CRYSTAL).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.BATTERY).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.DURALUMIN_JETPACK).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.DURALUMIN_MULTI_TOOL).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.DURALUMIN_JETBOOTS).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.SOLDER_JETPACK).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.SOLDER_MULTI_TOOL).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.SOLDER_JETBOOTS).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.BILLON_JETPACK).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.BILLON_MULTI_TOOL).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.BILLON_JETBOOTS).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.STEEL_JETPACK).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.STEEL_MULTI_TOOL).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.STEEL_JETBOOTS).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.DAMASCUS_STEEL_JETPACK).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.DAMASCUS_STEEL_MULTI_TOOL).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.DAMASCUS_STEEL_JETBOOTS).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.REINFORCED_ALLOY_JETPACK).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.REINFORCED_ALLOY_MULTI_TOOL).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.REINFORCED_ALLOY_JETBOOTS).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.CARBONADO_JETPACK).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.CARBONADO_MULTI_TOOL).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.CARBONADO_JETBOOTS).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.PARACHUTE).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.SOLAR_HELMET).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.ARMORED_JETBOOTS).setRecipeType(iwType);
        SlimefunItem.getByItem(SlimefunItems.SCUBA_HELMET).setRecipe(new ItemStack[] {new ItemStack(Material.ORANGE_WOOL), new ItemStack(Material.ORANGE_WOOL), new ItemStack(Material.ORANGE_WOOL), new ItemStack(Material.BLACK_WOOL), new CustomItemStack(Material.GLASS, "&bReinforced Glass"), new ItemStack(Material.BLACK_WOOL), null, null, null});
        SlimefunItem.getByItem(SlimefunItems.RUBBER_BOOTS).setRecipe(new ItemStack[] {null, null, null, RUBBER, null, RUBBER, RUBBER, null, RUBBER});
        SlimefunItem.getByItem(SlimefunItems.NIGHT_VISION_GOGGLES).setRecipe(new ItemStack[] {new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.COAL_BLOCK), new CustomItemStack(Material.GLASS, "&bReinforced Glass"), new ItemStack(Material.COAL_BLOCK), new CustomItemStack(Material.GLASS, "&bReinforced Glass"), new ItemStack(Material.COAL_BLOCK), null, new ItemStack(Material.COAL_BLOCK)});
        SlimefunItem.getByItem(SlimefunItems.STEEL_PLATE).setRecipe(new ItemStack[] {null, null, null, null, SlimefunItem.getByItem(SlimefunItems.STEEL_INGOT).getItem(), null, null, null, null});
        SlimefunItem.getByItem(SlimefunItems.STEEL_PLATE).setRecipeType(pbType);
    }

    public static void changeCategories() {
        SlimefunItem.getByItem(SlimefunItems.COPPER_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.TIN_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.ALUMINUM_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.LEAD_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.ZINC_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.MAGNESIUM_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.SILVER_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.REINFORCED_ALLOY_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.HARDENED_METAL_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.DAMASCUS_STEEL_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.STEEL_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.BRONZE_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.DURALUMIN_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.BILLON_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.BRASS_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.ALUMINUM_BRASS_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.ALUMINUM_BRONZE_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.CORINTHIAN_BRONZE_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.SOLDER_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.NICKEL_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.COBALT_INGOT).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.FERROSILICON).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.GILDED_IRON).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.REDSTONE_ALLOY).setItemGroup(INGOTS);
        SlimefunItem.getByItem(SlimefunItems.COPPER_DUST).setItemGroup(DUSTS);
        SlimefunItem.getByItem(SlimefunItems.TIN_DUST).setItemGroup(DUSTS);
        SlimefunItem.getByItem(SlimefunItems.ALUMINUM_DUST).setItemGroup(DUSTS);
        SlimefunItem.getByItem(SlimefunItems.LEAD_DUST).setItemGroup(DUSTS);
        SlimefunItem.getByItem(SlimefunItems.ZINC_DUST).setItemGroup(DUSTS);
        SlimefunItem.getByItem(SlimefunItems.MAGNESIUM_DUST).setItemGroup(DUSTS);
        SlimefunItem.getByItem(SlimefunItems.SILVER_DUST).setItemGroup(DUSTS);
        SlimefunItem.getByItem(SlimefunItems.IRON_DUST).setItemGroup(DUSTS);
        SlimefunItem.getByItem(SlimefunItems.GOLD_DUST).setItemGroup(DUSTS);
        SlimefunItem.getByItem(SlimefunItems.SULFATE).setItemGroup(DUSTS);
        SlimefunItem.getByItem(SlimefunItems.STEEL_PLATE).setItemGroup(PLATES);
    }
}