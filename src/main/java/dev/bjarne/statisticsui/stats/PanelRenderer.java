package dev.bjarne.statisticsui.stats;

import static dev.bjarne.statisticsui.util.Format.date;
import static dev.bjarne.statisticsui.util.Format.hearts;
import static dev.bjarne.statisticsui.util.Format.hoursFromTicks;
import static dev.bjarne.statisticsui.util.Format.meters;
import static dev.bjarne.statisticsui.util.Format.minutesFromTicks;
import static dev.bjarne.statisticsui.util.Format.number;
import static dev.bjarne.statisticsui.util.Format.ratio;
import static dev.bjarne.statisticsui.util.Text.color;
import static org.bukkit.Statistic.ANIMALS_BRED;
import static org.bukkit.Statistic.AVIATE_ONE_CM;
import static org.bukkit.Statistic.BEACON_INTERACTION;
import static org.bukkit.Statistic.BELL_RING;
import static org.bukkit.Statistic.BOAT_ONE_CM;
import static org.bukkit.Statistic.BREWINGSTAND_INTERACTION;
import static org.bukkit.Statistic.CAKE_SLICES_EATEN;
import static org.bukkit.Statistic.CHEST_OPENED;
import static org.bukkit.Statistic.CLIMB_ONE_CM;
import static org.bukkit.Statistic.CRAFTING_TABLE_INTERACTION;
import static org.bukkit.Statistic.CRAFT_ITEM;
import static org.bukkit.Statistic.CROUCH_ONE_CM;
import static org.bukkit.Statistic.DAMAGE_ABSORBED;
import static org.bukkit.Statistic.DAMAGE_BLOCKED_BY_SHIELD;
import static org.bukkit.Statistic.DAMAGE_DEALT;
import static org.bukkit.Statistic.DAMAGE_RESISTED;
import static org.bukkit.Statistic.DAMAGE_TAKEN;
import static org.bukkit.Statistic.DEATHS;
import static org.bukkit.Statistic.DROP;
import static org.bukkit.Statistic.DROP_COUNT;
import static org.bukkit.Statistic.ENDERCHEST_OPENED;
import static org.bukkit.Statistic.ENTITY_KILLED_BY;
import static org.bukkit.Statistic.FALL_ONE_CM;
import static org.bukkit.Statistic.FISH_CAUGHT;
import static org.bukkit.Statistic.FLOWER_POTTED;
import static org.bukkit.Statistic.FURNACE_INTERACTION;
import static org.bukkit.Statistic.HOPPER_INSPECTED;
import static org.bukkit.Statistic.HORSE_ONE_CM;
import static org.bukkit.Statistic.INTERACT_WITH_ANVIL;
import static org.bukkit.Statistic.INTERACT_WITH_BLAST_FURNACE;
import static org.bukkit.Statistic.INTERACT_WITH_CAMPFIRE;
import static org.bukkit.Statistic.INTERACT_WITH_GRINDSTONE;
import static org.bukkit.Statistic.INTERACT_WITH_LECTERN;
import static org.bukkit.Statistic.ITEM_ENCHANTED;
import static org.bukkit.Statistic.JUMP;
import static org.bukkit.Statistic.KILL_ENTITY;
import static org.bukkit.Statistic.LEAVE_GAME;
import static org.bukkit.Statistic.MINECART_ONE_CM;
import static org.bukkit.Statistic.MINE_BLOCK;
import static org.bukkit.Statistic.MOB_KILLS;
import static org.bukkit.Statistic.NOTEBLOCK_PLAYED;
import static org.bukkit.Statistic.NOTEBLOCK_TUNED;
import static org.bukkit.Statistic.OPEN_BARREL;
import static org.bukkit.Statistic.PIG_ONE_CM;
import static org.bukkit.Statistic.PLAYER_KILLS;
import static org.bukkit.Statistic.PLAY_ONE_MINUTE;
import static org.bukkit.Statistic.RAID_TRIGGER;
import static org.bukkit.Statistic.RAID_WIN;
import static org.bukkit.Statistic.RECORD_PLAYED;
import static org.bukkit.Statistic.SHULKER_BOX_OPENED;
import static org.bukkit.Statistic.SLEEP_IN_BED;
import static org.bukkit.Statistic.SPRINT_ONE_CM;
import static org.bukkit.Statistic.STRIDER_ONE_CM;
import static org.bukkit.Statistic.SWIM_ONE_CM;
import static org.bukkit.Statistic.TALKED_TO_VILLAGER;
import static org.bukkit.Statistic.TARGET_HIT;
import static org.bukkit.Statistic.TIME_SINCE_DEATH;
import static org.bukkit.Statistic.TIME_SINCE_REST;
import static org.bukkit.Statistic.TRADED_WITH_VILLAGER;
import static org.bukkit.Statistic.WALK_ONE_CM;
import static org.bukkit.Statistic.WALK_ON_WATER_ONE_CM;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.Tag;
import org.bukkit.entity.EntityType;

/**
 * Turn one player's statistics into a list of {@link RenderedPanel}s. This can very likely run off the main thread :)
 */
public final class PanelRenderer {

    private final OfflinePlayer player;
    private final PlayerMeta meta;

    public PanelRenderer(OfflinePlayer player, PlayerMeta meta) {
        this.player = player;
        this.meta = meta;
    }

    public List<RenderedPanel> render() {
        List<RenderedPanel> panels = new ArrayList<>(26);
        panels.add(arrowsFired());
        panels.add(bedsEntered());
        panels.add(blocksDestroyed());
        panels.add(blocksPlaced());
        panels.add(bucketsUsed());
        panels.add(damageTaken());
        panels.add(deaths());
        panels.add(fishCaught());
        panels.add(itemsCrafted());
        panels.add(itemsDropped());
        panels.add(joins());
        panels.add(mobKills());
        panels.add(movements());
        panels.add(foodEaten());
        panels.add(playersKilled());
        panels.add(playtime());
        panels.add(itemInteractions());
        panels.add(toolsUsed());
        panels.add(trades());
        panels.add(experience());
        panels.add(blockInteractions());
        panels.add(music());
        panels.add(bell());
        panels.add(targets());
        panels.add(itemsEnchanted());
        panels.add(firstJoined());
        panels.add(animalsBred());
        return panels;
    }

    private RenderedPanel arrowsFired() {
        long bow = read(Statistic.USE_ITEM, Material.BOW);
        long crossbow = read(Statistic.USE_ITEM, Material.CROSSBOW);
        return panel(18, Material.SPECTRAL_ARROW, "&7&lArrows fired", List.of(
                line("Total", number(bow + crossbow)),
                line("Bow used", number(bow)),
                line("Crossbow used", number(crossbow))));
    }

    private RenderedPanel bedsEntered() {
        return panel(19, Material.GREEN_BED, "&7&lBeds entered", List.of(
                line("Total", number(read(SLEEP_IN_BED)))));
    }

    private RenderedPanel blocksDestroyed() {
        // Ore tags fold in the deepslate variants (and any future ores), which single-material reads missed.
        long coal = readTag(MINE_BLOCK, Tag.COAL_ORES);
        long copper = readTag(MINE_BLOCK, Tag.COPPER_ORES);
        long iron = readTag(MINE_BLOCK, Tag.IRON_ORES);
        long gold = readTag(MINE_BLOCK, Tag.GOLD_ORES);
        long redstone = readTag(MINE_BLOCK, Tag.REDSTONE_ORES);
        long lapis = readTag(MINE_BLOCK, Tag.LAPIS_ORES);
        long emerald = readTag(MINE_BLOCK, Tag.EMERALD_ORES);
        long diamond = readTag(MINE_BLOCK, Tag.DIAMOND_ORES);
        long quartz = read(MINE_BLOCK, Material.NETHER_QUARTZ_ORE);
        long netherGold = read(MINE_BLOCK, Material.NETHER_GOLD_ORE);
        long totalOres = coal + copper + iron + gold + redstone + lapis + emerald + diamond + quartz + netherGold;
        long pumpkin = read(MINE_BLOCK, Material.PUMPKIN) + read(MINE_BLOCK, Material.CARVED_PUMPKIN);
        return panel(20, Material.DIAMOND_PICKAXE, "&7&lBlocks destroyed", List.of(
                line("Stone", number(read(MINE_BLOCK, Material.STONE))),
                line("Dirt", number(read(MINE_BLOCK, Material.DIRT))),
                line("Grass Block", number(read(MINE_BLOCK, Material.GRASS_BLOCK))),
                line("Total ores", number(totalOres)),
                line("Coal Ore", number(coal)),
                line("Copper Ore", number(copper)),
                line("Iron Ore", number(iron)),
                line("Gold Ore", number(gold)),
                line("Redstone Ore", number(redstone)),
                line("Lapis Ore", number(lapis)),
                line("Emerald Ore", number(emerald)),
                line("Diamond Ore", number(diamond)),
                line("Quartz Ore", number(quartz)),
                line("Nether Gold Ore", number(netherGold)),
                line("Ancient Debris", number(read(MINE_BLOCK, Material.ANCIENT_DEBRIS))),
                line("Netherrack", number(read(MINE_BLOCK, Material.NETHERRACK))),
                line("Melon", number(read(MINE_BLOCK, Material.MELON))),
                line("Pumpkin", number(pumpkin)),
                line("Logs", number(readTag(MINE_BLOCK, Tag.LOGS)))));
    }

    private RenderedPanel blocksPlaced() {
        long seeds = read(Statistic.USE_ITEM, Material.WHEAT_SEEDS) + read(Statistic.USE_ITEM, Material.MELON_SEEDS)
                + read(Statistic.USE_ITEM, Material.PUMPKIN_SEEDS) + read(Statistic.USE_ITEM, Material.BEETROOT_SEEDS)
                + read(Statistic.USE_ITEM, Material.COCOA_BEANS);
        return panel(21, Material.COBBLESTONE, "&7&lBlocks placed", List.of(
                line("Cobblestone", number(read(Statistic.USE_ITEM, Material.COBBLESTONE))),
                line("Dirt", number(read(Statistic.USE_ITEM, Material.DIRT))),
                line("Workbench", number(read(Statistic.USE_ITEM, Material.CRAFTING_TABLE))),
                line("Furnace", number(read(Statistic.USE_ITEM, Material.FURNACE))),
                line("Chest", number(read(Statistic.USE_ITEM, Material.CHEST))),
                line("Enchanting Table", number(read(Statistic.USE_ITEM, Material.ENCHANTING_TABLE))),
                line("TNT", number(read(Statistic.USE_ITEM, Material.TNT))),
                line("Beacon", number(read(Statistic.USE_ITEM, Material.BEACON))),
                line("Scaffolding", number(read(Statistic.USE_ITEM, Material.SCAFFOLDING))),
                line("Seeds", number(seeds)),
                line("Carrots", number(read(Statistic.USE_ITEM, Material.CARROT))),
                line("Potatoes", number(read(Statistic.USE_ITEM, Material.POTATO))),
                line("Respawn Anchor", number(read(Statistic.USE_ITEM, Material.RESPAWN_ANCHOR)))));
    }

    private RenderedPanel bucketsUsed() {
        long water = read(Statistic.USE_ITEM, Material.WATER_BUCKET);
        long lava = read(Statistic.USE_ITEM, Material.LAVA_BUCKET);
        long empty = read(Statistic.USE_ITEM, Material.BUCKET);
        return panel(22, Material.WATER_BUCKET, "&7&lBuckets used", List.of(
                line("Total", number(empty + water + lava)),
                line("Water", number(water)),
                line("Lava", number(lava))));
    }

    private RenderedPanel damageTaken() {
        return panel(23, Material.SHIELD, "&7&lTotal damage", List.of(
                line("Taken", hearts(read(DAMAGE_TAKEN))),
                line("Dealt", hearts(read(DAMAGE_DEALT))),
                line("Blocked", hearts(read(DAMAGE_BLOCKED_BY_SHIELD))),
                line("Absorbed", hearts(read(DAMAGE_ABSORBED), "&e")),
                line("Resisted", hearts(read(DAMAGE_RESISTED), "&7"))));
    }

    private RenderedPanel deaths() {
        return panel(24, Material.SKELETON_SKULL, "&7&lDeaths", List.of(
                line("Total", number(read(DEATHS))),
                line("By player", number(read(ENTITY_KILLED_BY, EntityType.PLAYER))),
                line("By zombie", number(read(ENTITY_KILLED_BY, EntityType.ZOMBIE))),
                line("By skeleton", number(read(ENTITY_KILLED_BY, EntityType.SKELETON))),
                line("By creeper", number(read(ENTITY_KILLED_BY, EntityType.CREEPER))),
                line("By enderman", number(read(ENTITY_KILLED_BY, EntityType.ENDERMAN))),
                line("By warden", number(read(ENTITY_KILLED_BY, EntityType.WARDEN)))));
                
    }

    private RenderedPanel fishCaught() {
        return panel(25, Material.TROPICAL_FISH, "&7&lFish caught", List.of(
                line("Total", number(read(FISH_CAUGHT)))));
    }

    private RenderedPanel itemsCrafted() {
        long planks = readTag(CRAFT_ITEM, Tag.PLANKS);
        return panel(26, Material.CRAFTING_TABLE, "&7&lItems crafted", List.of(
                line("Iron Ingot", number(read(CRAFT_ITEM, Material.IRON_INGOT))),
                line("Steak", number(read(CRAFT_ITEM, Material.COOKED_BEEF))),
                line("Stick", number(read(CRAFT_ITEM, Material.STICK))),
                line("Gold Ingot", number(read(CRAFT_ITEM, Material.GOLD_INGOT))),
                line("Torch", number(read(CRAFT_ITEM, Material.TORCH))),
                line("Bread", number(read(CRAFT_ITEM, Material.BREAD))),
                line("Paper", number(read(CRAFT_ITEM, Material.PAPER))),
                line("Planks", number(planks)),
                line("Furnace", number(read(CRAFT_ITEM, Material.FURNACE))),
                line("TNT", number(read(CRAFT_ITEM, Material.TNT))),
                line("Crafting Table", number(read(CRAFT_ITEM, Material.CRAFTING_TABLE))),
                line("Glass", number(read(CRAFT_ITEM, Material.GLASS))),
                line("Netherite Ingot", number(read(CRAFT_ITEM, Material.NETHERITE_INGOT)))));
    }

    private RenderedPanel itemsDropped() {
        return panel(27, Material.HOPPER, "&7&lItems dropped", List.of(
                line("Total", number(read(DROP_COUNT))),
                line("Cobblestone", number(read(DROP, Material.COBBLESTONE))),
                line("Dirt", number(read(DROP, Material.DIRT))),
                line("Diorite", number(read(DROP, Material.DIORITE))),
                line("Granite", number(read(DROP, Material.GRANITE))),
                line("Andesite", number(read(DROP, Material.ANDESITE))),
                line("Gravel", number(read(DROP, Material.GRAVEL))),
                line("Flint", number(read(DROP, Material.FLINT))),
                line("Netherrack", number(read(DROP, Material.NETHERRACK))),
                line("Rotten Flesh", number(read(DROP, Material.ROTTEN_FLESH))),
                line("Poisonous Potato", number(read(DROP, Material.POISONOUS_POTATO)))));
    }

    private RenderedPanel joins() {
        // There is no join statistic; leave-game sessions are the closest proxy.
        return panel(28, Material.GREEN_BANNER, "&7&lJoined game", List.of(
                line("Total", number(read(LEAVE_GAME)))));
    }

    private RenderedPanel mobKills() {
        return panel(29, Material.DIAMOND_SWORD, "&7&lMobs killed", List.of(
                line("Total", number(read(MOB_KILLS))),
                line("Creeper", number(read(KILL_ENTITY, EntityType.CREEPER))),
                line("Zombie", number(read(KILL_ENTITY, EntityType.ZOMBIE))),
                line("Skeleton", number(read(KILL_ENTITY, EntityType.SKELETON))),
                line("Spider", number(read(KILL_ENTITY, EntityType.SPIDER))),
                line("Cave Spider", number(read(KILL_ENTITY, EntityType.CAVE_SPIDER))),
                line("Blaze", number(read(KILL_ENTITY, EntityType.BLAZE))),
                line("Cat", number(read(KILL_ENTITY, EntityType.CAT))),
                line("Cow", number(read(KILL_ENTITY, EntityType.COW))),
                line("Dolphin", number(read(KILL_ENTITY, EntityType.DOLPHIN))),
                line("Drowned", number(read(KILL_ENTITY, EntityType.DROWNED))),
                line("Enderman", number(read(KILL_ENTITY, EntityType.ENDERMAN))),
                line("Fox", number(read(KILL_ENTITY, EntityType.FOX))),
                line("Hoglin", number(read(KILL_ENTITY, EntityType.HOGLIN))),
                line("Horse", number(read(KILL_ENTITY, EntityType.HORSE))),
                line("Pig", number(read(KILL_ENTITY, EntityType.PIG))),
                line("Villager", number(read(KILL_ENTITY, EntityType.VILLAGER))),
                line("Squid", number(read(KILL_ENTITY, EntityType.SQUID))),
                line("Witch", number(read(KILL_ENTITY, EntityType.WITCH))),
                line("Pillager", number(read(KILL_ENTITY, EntityType.PILLAGER))),
                line("Phantom", number(read(KILL_ENTITY, EntityType.PHANTOM))),
                line("Warden", number(read(KILL_ENTITY, EntityType.WARDEN))),
                line("Wither", number(read(KILL_ENTITY, EntityType.WITHER))),
                line("Ender Dragon", number(read(KILL_ENTITY, EntityType.ENDER_DRAGON)))));
    }

    private RenderedPanel movements() {
        return panel(30, Material.CHAINMAIL_BOOTS, "&7&lMovements", List.of(
                line("Jumps", number(read(JUMP))),
                line("Horse ridden", meters(read(HORSE_ONE_CM))),
                line("Strider ridden", meters(read(STRIDER_ONE_CM))),
                line("Pig ridden", meters(read(PIG_ONE_CM))),
                line("Walked on water", meters(read(WALK_ON_WATER_ONE_CM))),
                line("Fallen", meters(read(FALL_ONE_CM))),
                line("Elytra used", meters(read(AVIATE_ONE_CM))),
                line("Climbed", meters(read(CLIMB_ONE_CM))),
                line("Walked", meters(read(WALK_ONE_CM))),
                line("Crouched", meters(read(CROUCH_ONE_CM))),
                line("Swam", meters(read(SWIM_ONE_CM))),
                line("Sprinted", meters(read(SPRINT_ONE_CM))),
                line("By Boat", meters(read(BOAT_ONE_CM))),
                line("By Minecart", meters(read(MINECART_ONE_CM)))));
    }

    private RenderedPanel foodEaten() {
        long fish = read(Statistic.USE_ITEM, Material.COD) + read(Statistic.USE_ITEM, Material.COOKED_COD)
                + read(Statistic.USE_ITEM, Material.SALMON) + read(Statistic.USE_ITEM, Material.COOKED_SALMON);
        return panel(31, Material.CARROT, "&7&lFood eaten", List.of(
                line("Steak", number(read(Statistic.USE_ITEM, Material.COOKED_BEEF))),
                line("Cooked Porkchop", number(read(Statistic.USE_ITEM, Material.COOKED_PORKCHOP))),
                line("Cooked Mutton", number(read(Statistic.USE_ITEM, Material.COOKED_MUTTON))),
                line("Cooked Chicken", number(read(Statistic.USE_ITEM, Material.COOKED_CHICKEN))),
                line("Raw Chicken", number(read(Statistic.USE_ITEM, Material.CHICKEN))),
                line("Fish", number(fish)),
                line("Bread", number(read(Statistic.USE_ITEM, Material.BREAD))),
                line("Baked Potato", number(read(Statistic.USE_ITEM, Material.BAKED_POTATO))),
                line("Beetroot", number(read(Statistic.USE_ITEM, Material.BEETROOT))),
                line("Mushroom Stew", number(read(Statistic.USE_ITEM, Material.MUSHROOM_STEW))),
                line("Apple", number(read(Statistic.USE_ITEM, Material.APPLE))),
                line("Melon", number(read(Statistic.USE_ITEM, Material.MELON_SLICE))),
                line("Sweet Berries", number(read(Statistic.USE_ITEM, Material.SWEET_BERRIES))),
                line("Golden Carrot", number(read(Statistic.USE_ITEM, Material.GOLDEN_CARROT))),
                line("Golden Apple", number(read(Statistic.USE_ITEM, Material.GOLDEN_APPLE))),
                line("Kelp", number(read(Statistic.USE_ITEM, Material.DRIED_KELP))),
                line("Honey", number(read(Statistic.USE_ITEM, Material.HONEY_BOTTLE))),
                line("Milk", number(read(Statistic.USE_ITEM, Material.MILK_BUCKET)))));
    }

    private RenderedPanel playersKilled() {
        long kills = read(PLAYER_KILLS);
        long deathsByPlayer = read(ENTITY_KILLED_BY, EntityType.PLAYER);
        return panel(32, Material.GOLDEN_SWORD, "&7&lPlayers killed", List.of(
                line("Total", number(kills)),
                line("K/D", ratio(kills, deathsByPlayer))));
    }

    private RenderedPanel playtime() {
        return panel(33, Material.CLOCK, "&7&lPlaytime", List.of(
                line("Total", hoursFromTicks(read(PLAY_ONE_MINUTE))),
                line("Since rest", minutesFromTicks(read(TIME_SINCE_REST))),
                line("Since death", hoursFromTicks(read(TIME_SINCE_DEATH)))));
    }

    private RenderedPanel itemInteractions() {
        return panel(34, Material.ENDER_EYE, "&7&lItem Interactions", List.of(
                line("Eggs thrown", number(read(Statistic.USE_ITEM, Material.EGG))),
                line("Snowballs thrown", number(read(Statistic.USE_ITEM, Material.SNOWBALL))),
                line("Eye of Ender", number(read(Statistic.USE_ITEM, Material.ENDER_EYE))),
                line("Ender Pearl", number(read(Statistic.USE_ITEM, Material.ENDER_PEARL))),
                line("Bone Meal", number(read(Statistic.USE_ITEM, Material.BONE_MEAL))),
                line("Book and Quill", number(read(Statistic.USE_ITEM, Material.WRITABLE_BOOK))),
                line("Maps created", number(read(Statistic.USE_ITEM, Material.MAP))),
                line("Painting placed", number(read(Statistic.USE_ITEM, Material.PAINTING))),
                line("Item Frame placed", number(read(Statistic.USE_ITEM, Material.ITEM_FRAME))),
                line("Firework Rocket", number(read(Statistic.USE_ITEM, Material.FIREWORK_ROCKET))),
                line("Potions consumed", number(read(Statistic.USE_ITEM, Material.POTION)))));
    }

    private RenderedPanel toolsUsed() {
        return panel(35, Material.IRON_PICKAXE, "&7&lTools used", List.of(
                line("Iron Sword", number(read(Statistic.USE_ITEM, Material.IRON_SWORD))),
                line("Diamond Sword", number(read(Statistic.USE_ITEM, Material.DIAMOND_SWORD))),
                line("Netherite Sword", number(read(Statistic.USE_ITEM, Material.NETHERITE_SWORD))),
                line("Iron Pickaxe", number(read(Statistic.USE_ITEM, Material.IRON_PICKAXE))),
                line("Diamond Pickaxe", number(read(Statistic.USE_ITEM, Material.DIAMOND_PICKAXE))),
                line("Netherite Pickaxe", number(read(Statistic.USE_ITEM, Material.NETHERITE_PICKAXE))),
                line("Diamond Axe", number(read(Statistic.USE_ITEM, Material.DIAMOND_AXE))),
                line("Netherite Axe", number(read(Statistic.USE_ITEM, Material.NETHERITE_AXE))),
                line("Trident", number(read(Statistic.USE_ITEM, Material.TRIDENT))),
                line("Flint and Steel", number(read(Statistic.USE_ITEM, Material.FLINT_AND_STEEL))),
                line("Shears", number(read(Statistic.USE_ITEM, Material.SHEARS))),
                line("Fishing Rod", number(read(Statistic.USE_ITEM, Material.FISHING_ROD))),
                line("Name Tag", number(read(Statistic.USE_ITEM, Material.NAME_TAG)))));
    }

    private RenderedPanel trades() {
        return panel(36, Material.EMERALD, "&7&lTraded with villagers", List.of(
                line("Total", number(read(TRADED_WITH_VILLAGER))),
                line("Interacted", number(read(TALKED_TO_VILLAGER))),
                line("Raids triggered", number(read(RAID_TRIGGER))),
                line("Raids won", number(read(RAID_WIN)))));
    }

    private RenderedPanel experience() {
        String level = meta.online() ? number(meta.level()) : "&c&oPlayer is not online";
        String total = meta.online() ? number(meta.totalExperience()) : "&c&oPlayer is not online";
        return panel(37, Material.EXPERIENCE_BOTTLE, "&7&lExperience gained", List.of(
                line("Current level", level),
                line("Total", total)));
    }

    private RenderedPanel blockInteractions() {
        long furnaces = read(FURNACE_INTERACTION) + read(INTERACT_WITH_BLAST_FURNACE);
        return panel(38, Material.CHEST, "&7&lBlock Interactions", List.of(
                line("Crafting Table", number(read(CRAFTING_TABLE_INTERACTION))),
                line("Chest", number(read(CHEST_OPENED))),
                line("Enderchest", number(read(ENDERCHEST_OPENED))),
                line("Anvil", number(read(INTERACT_WITH_ANVIL))),
                line("Barrel", number(read(OPEN_BARREL))),
                line("Brewing Stand", number(read(BREWINGSTAND_INTERACTION))),
                line("Shulker Box", number(read(SHULKER_BOX_OPENED))),
                line("Cake", number(read(CAKE_SLICES_EATEN))),
                line("Campfire", number(read(INTERACT_WITH_CAMPFIRE))),
                line("Lectern", number(read(INTERACT_WITH_LECTERN))),
                line("Furnace", number(furnaces)),
                line("Plants potted", number(read(FLOWER_POTTED))),
                line("Grindstone", number(read(INTERACT_WITH_GRINDSTONE))),
                line("Hopper", number(read(HOPPER_INSPECTED))),
                line("Beacon", number(read(BEACON_INTERACTION)))));
    }

    private RenderedPanel music() {
        return panel(39, Material.JUKEBOX, "&7&lMusic Played", List.of(
                line("Noteblock played", number(read(NOTEBLOCK_PLAYED))),
                line("Noteblock tuned", number(read(NOTEBLOCK_TUNED))),
                line("Records played", number(read(RECORD_PLAYED))),
                line("Jukeboxes crafted", number(read(CRAFT_ITEM, Material.JUKEBOX)))));
    }

    private RenderedPanel bell() {
        return panel(40, Material.BELL, "&7&lBell Rung", List.of(
                line("Total", number(read(BELL_RING)))));
    }

    private RenderedPanel targets() {
        return panel(41, Material.TARGET, "&7&lTargets Hit", List.of(
                line("Total", number(read(TARGET_HIT)))));
    }

    private RenderedPanel itemsEnchanted() {
        return panel(42, Material.ENCHANTING_TABLE, "&7&lItems Enchanted", List.of(
                line("Total", number(read(ITEM_ENCHANTED)))));
    }

    private RenderedPanel firstJoined() {
        String lastPlayed = meta.online() ? "&a&oCurrently Online" : date(meta.lastPlayed());
        return panel(43, Material.WRITABLE_BOOK, "&7&lFirst Joined", List.of(
                line("First joined", date(meta.firstPlayed())),
                line("Last played", lastPlayed)));
    }

    private RenderedPanel animalsBred() {
        return panel(44, Material.RABBIT_HIDE, "&7&lAnimals Bred", List.of(
                line("Total", number(read(ANIMALS_BRED)))));
    }

    private long read(Statistic statistic) {
        try {
            return player.getStatistic(statistic);
        } catch (RuntimeException ignored) {
            return 0L;
        }
    }

    private long read(Statistic statistic, Material material) {
        try {
            return player.getStatistic(statistic, material);
        } catch (RuntimeException ignored) {
            return 0L;
        }
    }

    private long read(Statistic statistic, EntityType entity) {
        try {
            return player.getStatistic(statistic, entity);
        } catch (RuntimeException ignored) {
            return 0L;
        }
    }

    // Sums a statistic over a material tag, so new block/item variants are picked up automatically.
    private long readTag(Statistic statistic, Tag<Material> tag) {
        long total = 0L;
        for (Material material : tag.getValues()) {
            total += read(statistic, material);
        }
        return total;
    }

    private static String line(String label, String value) {
        return color("&9" + label + ": " + value);
    }

    private static RenderedPanel panel(int slot, Material icon, String title, List<String> lore) {
        return new RenderedPanel(slot, icon, color(title), lore);
    }
}
