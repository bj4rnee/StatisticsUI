package dev.bjarne.statisticsUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class itemHandler {

	public static main Main;

	public void setMain(main Main) {
		itemHandler.Main = Main;
		Main.iH = this;
	}

	public ItemStack getFiller() {
		ItemStack item = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&7"));

		meta.setDisplayName(main.makeColors("&c&l"));
		meta.setLore(metalist);
		item.setItemMeta(meta);

		return item;
	}

	public ItemStack getLoading() {
		ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&7This may take up to 6 seconds..."));

		meta.setDisplayName(main.makeColors("&c&lLoading Statistics"));
		meta.setLore(metalist);
		item.setItemMeta(meta);

		return item;
	}

	public ItemStack getArrowsFired(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.getMaterial("SPECTRAL_ARROW"));
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&9Total: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.BOW)
				+ p.getStatistic(Statistic.USE_ITEM, Material.CROSSBOW))));
		metalist.add(
				main.makeColors("&9Bow used: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.BOW))));
		metalist.add(main.makeColors(
				"&9Crossbow used: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.CROSSBOW))));

		meta.setDisplayName(main.makeColors("&7&lArrows fired"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getBedsEntered(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.GREEN_BED);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&9Total: " + String.valueOf(p.getStatistic(Statistic.SLEEP_IN_BED))));

		meta.setDisplayName(main.makeColors("&7&lBeds entered"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getBlocksBroken(UUID u) {// blocks mined
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		// metalist.add(main.makeColors("&9Total:
		// "+String.valueOf(p.getStatistic(Statistic.MINE_BLOCK)))); // not possible,
		// invalid args
		metalist.add(
				main.makeColors("&9Stone: " + String.valueOf(p.getStatistic(Statistic.MINE_BLOCK, Material.STONE))));
		metalist.add(main.makeColors("&9Dirt: " + String.valueOf(p.getStatistic(Statistic.MINE_BLOCK, Material.DIRT))));
		metalist.add(main.makeColors(
				"&9Grass Block: " + String.valueOf(p.getStatistic(Statistic.MINE_BLOCK, Material.GRASS_BLOCK))));
		metalist.add(main
				.makeColors("&9Gold Ore: " + String.valueOf(p.getStatistic(Statistic.MINE_BLOCK, Material.GOLD_ORE))));
		metalist.add(main
				.makeColors("&9Iron Ore: " + String.valueOf(p.getStatistic(Statistic.MINE_BLOCK, Material.IRON_ORE))));
		metalist.add(main.makeColors(
				"&9Lapis Ore: " + String.valueOf(p.getStatistic(Statistic.MINE_BLOCK, Material.LAPIS_ORE))));
		metalist.add(main
				.makeColors("&9Coal Ore: " + String.valueOf(p.getStatistic(Statistic.MINE_BLOCK, Material.COAL_ORE))));
		metalist.add(main.makeColors(
				"&9Emerald Ore: " + String.valueOf(p.getStatistic(Statistic.MINE_BLOCK, Material.EMERALD_ORE))));
		metalist.add(main.makeColors(
				"&9Diamond Ore: " + String.valueOf(p.getStatistic(Statistic.MINE_BLOCK, Material.DIAMOND_ORE))));
		metalist.add(main.makeColors(
				"&9Ancient Debris: " + String.valueOf(p.getStatistic(Statistic.MINE_BLOCK, Material.ANCIENT_DEBRIS))));
		metalist.add(main.makeColors(
				"&9Quarz Ore: " + String.valueOf(p.getStatistic(Statistic.MINE_BLOCK, Material.NETHER_QUARTZ_ORE))));
		metalist.add(main.makeColors("&9Nether Gold Ore: "
				+ String.valueOf(p.getStatistic(Statistic.MINE_BLOCK, Material.NETHER_GOLD_ORE))));
		metalist.add(main.makeColors(
				"&9Netherrack: " + String.valueOf(p.getStatistic(Statistic.MINE_BLOCK, Material.NETHERRACK))));
		metalist.add(main
				.makeColors("&9Melon Block: " + String.valueOf(p.getStatistic(Statistic.MINE_BLOCK, Material.MELON))));
		metalist.add(
				main.makeColors("&9Pumpkin: " + String.valueOf(p.getStatistic(Statistic.MINE_BLOCK, Material.PUMPKIN)
						+ p.getStatistic(Statistic.MINE_BLOCK, Material.CARVED_PUMPKIN))));
		metalist.add(main
				.makeColors("&9Wooden Logs: " + String.valueOf(p.getStatistic(Statistic.MINE_BLOCK, Material.ACACIA_LOG)
						+ p.getStatistic(Statistic.MINE_BLOCK, Material.BIRCH_LOG)
						+ p.getStatistic(Statistic.MINE_BLOCK, Material.DARK_OAK_LOG)
						+ p.getStatistic(Statistic.MINE_BLOCK, Material.JUNGLE_LOG)
						+ p.getStatistic(Statistic.MINE_BLOCK, Material.OAK_LOG)
						+ p.getStatistic(Statistic.MINE_BLOCK, Material.SPRUCE_LOG))));
		// metalist.add(main.makeColors(
		// "&9Crafting Table: " + String.valueOf(p.getStatistic(Statistic.MINE_BLOCK,
		// Material.CRAFTING_TABLE))));

		meta.setDisplayName(main.makeColors("&7&lBlocks destroyed"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getBlocksPlaced(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.COBBLESTONE);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors(
				"&9Cobblestone: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.COBBLESTONE))));
		metalist.add(main.makeColors("&9Dirt: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.DIRT))));
		metalist.add(main.makeColors(
				"&9Workbench: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.CRAFTING_TABLE))));
		metalist.add(
				main.makeColors("&9Furnace: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.FURNACE))));
		metalist.add(main.makeColors("&9Chest: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.CHEST))));
		metalist.add(main.makeColors("&9Enchanting Table: "
				+ String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.ENCHANTING_TABLE))));
		metalist.add(main.makeColors("&9Tnt: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.TNT))));
		metalist.add(
				main.makeColors("&9Beacon: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.BEACON))));
		metalist.add(main.makeColors(
				"&9Scaffolding: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.SCAFFOLDING))));
		metalist.add(
				main.makeColors("&9Seeds: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.WHEAT_SEEDS)
						+ p.getStatistic(Statistic.USE_ITEM, Material.MELON_SEEDS)
						+ p.getStatistic(Statistic.USE_ITEM, Material.PUMPKIN_SEEDS)
						+ p.getStatistic(Statistic.USE_ITEM, Material.BEETROOT_SEEDS)
						+ p.getStatistic(Statistic.USE_ITEM, Material.COCOA_BEANS))));
		metalist.add(
				main.makeColors("&9Carrots: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.CARROT))));
		metalist.add(
				main.makeColors("&9Potatoes: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.POTATO))));
		metalist.add(main.makeColors(
				"&9Respawn Anchor: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.RESPAWN_ANCHOR))));

		meta.setDisplayName(main.makeColors("&7&lBlocks placed"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getDamageTaken(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.SHIELD);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors(
				"&9Total: " + String.valueOf((p.getStatistic(Statistic.DAMAGE_TAKEN) / 10) / 2) + "&l&4\u2665")); // \u2754
		metalist.add(main.makeColors("&9Blocked: "
				+ String.valueOf((p.getStatistic(Statistic.DAMAGE_BLOCKED_BY_SHIELD) / 10) / 2) + "&l&4\u2665"));

		meta.setDisplayName(main.makeColors("&7&lDamage taken"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getDeaths(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.SKELETON_SKULL);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&9Total: " + String.valueOf(p.getStatistic(Statistic.DEATHS))));
		metalist.add(main.makeColors("&9Killed by player: "
				+ String.valueOf(p.getStatistic(Statistic.ENTITY_KILLED_BY, EntityType.PLAYER))));

		meta.setDisplayName(main.makeColors("&7&lDeaths"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getFishCaught(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.TROPICAL_FISH);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&9Gesamt: " + String.valueOf(p.getStatistic(Statistic.FISH_CAUGHT))));

		meta.setDisplayName(main.makeColors("&7&lFisch caught"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getItemsCrafted(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.CRAFTING_TABLE);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		// total items crafted is yet not possible
		metalist.add(main.makeColors(
				"&9Iron Ingot: " + String.valueOf(p.getStatistic(Statistic.CRAFT_ITEM, Material.IRON_INGOT))));
		metalist.add(main
				.makeColors("&9Steak: " + String.valueOf(p.getStatistic(Statistic.CRAFT_ITEM, Material.COOKED_BEEF))));
		metalist.add(
				main.makeColors("&9Stick: " + String.valueOf(p.getStatistic(Statistic.CRAFT_ITEM, Material.STICK))));
		metalist.add(main.makeColors(
				"&9Gold Ingot: " + String.valueOf(p.getStatistic(Statistic.CRAFT_ITEM, Material.GOLD_INGOT))));
		metalist.add(
				main.makeColors("&9Torch: " + String.valueOf(p.getStatistic(Statistic.CRAFT_ITEM, Material.TORCH))));
		metalist.add(
				main.makeColors("&9Bread: " + String.valueOf(p.getStatistic(Statistic.CRAFT_ITEM, Material.BREAD))));
		metalist.add(
				main.makeColors("&9Paper: " + String.valueOf(p.getStatistic(Statistic.CRAFT_ITEM, Material.PAPER))));
		metalist.add(main
				.makeColors("&9Planks: " + String.valueOf(p.getStatistic(Statistic.CRAFT_ITEM, Material.ACACIA_PLANKS)
						+ p.getStatistic(Statistic.CRAFT_ITEM, Material.BIRCH_PLANKS)
						+ p.getStatistic(Statistic.CRAFT_ITEM, Material.CRIMSON_PLANKS)
						+ p.getStatistic(Statistic.CRAFT_ITEM, Material.DARK_OAK_PLANKS)
						+ p.getStatistic(Statistic.CRAFT_ITEM, Material.JUNGLE_PLANKS)
						+ p.getStatistic(Statistic.CRAFT_ITEM, Material.OAK_PLANKS)
						+ p.getStatistic(Statistic.CRAFT_ITEM, Material.SPRUCE_PLANKS)
						+ p.getStatistic(Statistic.CRAFT_ITEM, Material.WARPED_PLANKS))));
		metalist.add(main
				.makeColors("&9Furnace: " + String.valueOf(p.getStatistic(Statistic.CRAFT_ITEM, Material.FURNACE))));
		metalist.add(main.makeColors("&9Tnt: " + String.valueOf(p.getStatistic(Statistic.CRAFT_ITEM, Material.TNT))));
		metalist.add(main.makeColors(
				"&9Crafting Table: " + String.valueOf(p.getStatistic(Statistic.CRAFT_ITEM, Material.CRAFTING_TABLE))));
		metalist.add(
				main.makeColors("&9Glass: " + String.valueOf(p.getStatistic(Statistic.CRAFT_ITEM, Material.GLASS))));
		metalist.add(main.makeColors("&9Netherite Ingot: "
				+ String.valueOf(p.getStatistic(Statistic.CRAFT_ITEM, Material.NETHERITE_INGOT))));

		meta.setDisplayName(main.makeColors("&7&lItems crafted"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getItemsDropped(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.HOPPER);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&9Total: " + String.valueOf(p.getStatistic(Statistic.DROP_COUNT))));
		metalist.add(main
				.makeColors("&9Cobblestone: " + String.valueOf(p.getStatistic(Statistic.DROP, Material.COBBLESTONE))));
		metalist.add(main.makeColors("&9Dirt: " + String.valueOf(p.getStatistic(Statistic.DROP, Material.DIRT))));
		metalist.add(main.makeColors(
				"&9Rotten Flesh: " + String.valueOf(p.getStatistic(Statistic.DROP, Material.ROTTEN_FLESH))));
		metalist.add(main.makeColors("&9Diorite: " + String.valueOf(p.getStatistic(Statistic.DROP, Material.DIORITE))));
		metalist.add(main.makeColors("&9Granite: " + String.valueOf(p.getStatistic(Statistic.DROP, Material.GRANITE))));
		// metalist.add(main.makeColors("&9Eier geworfen: "));
		// metalist.add(main.makeColors("&9Schneebälle geworfen: "));
		// metalist.add(main.makeColors("&9Items picked up:
		// "+String.valueOf(p.getStatistic(Statistic.PICKUP,Material.)))); // cannot
		// count total pickups :(

		meta.setDisplayName(main.makeColors("&7&lItems dropped"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getBucketsEmptied(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.WATER_BUCKET);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&9Total: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.BUCKET)
				+ p.getStatistic(Statistic.USE_ITEM, Material.LAVA_BUCKET)
				+ p.getStatistic(Statistic.USE_ITEM, Material.WATER_BUCKET))));
		metalist.add(main
				.makeColors("&9Water: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.WATER_BUCKET))));
		metalist.add(
				main.makeColors("&9Lava: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.LAVA_BUCKET))));

		meta.setDisplayName(main.makeColors("&7&lBuckets used"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getJoins(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.GREEN_BANNER);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&9Total: " + String.valueOf(p.getStatistic(Statistic.LEAVE_GAME)))); // leave_game
																											// should be
																											// similar
																											// to
																											// join_game

		meta.setDisplayName(main.makeColors("&7&lJoined game"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getMobKills(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&9Total: " + String.valueOf(p.getStatistic(Statistic.MOB_KILLS))));
		metalist.add(main
				.makeColors("&9Creeper: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.CREEPER))));
		metalist.add(main
				.makeColors("&9Zombie: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.ZOMBIE))));
		metalist.add(main.makeColors(
				"&9Skeleton: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.SKELETON))));
		metalist.add(main
				.makeColors("&9Spider: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.SPIDER))));
		metalist.add(main.makeColors(
				"&9Cavespider: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.CAVE_SPIDER))));
		metalist.add(
				main.makeColors("&9Blaze: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.BLAZE))));
		metalist.add(
				main.makeColors("&9Cat: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.CAT))));
		metalist.add(
				main.makeColors("&9Cow: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.COW))));
		metalist.add(main
				.makeColors("&9Dolphin: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.DOLPHIN))));
		metalist.add(main.makeColors(
				"&9Drowned zombie: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.DROWNED))));
		metalist.add(main.makeColors(
				"&9Enderman: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.ENDERMAN))));
		metalist.add(
				main.makeColors("&9Fox: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.FOX))));
		metalist.add(main
				.makeColors("&9Hoglin: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.HOGLIN))));
		metalist.add(
				main.makeColors("&9Horse: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.HORSE))));
		metalist.add(
				main.makeColors("&9Pig: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.PIG))));
		metalist.add(main.makeColors(
				"&9Villager: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.VILLAGER))));
		metalist.add(
				main.makeColors("&9Squid: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.SQUID))));
		metalist.add(main.makeColors(
				"&9Enderdragon: " + String.valueOf(p.getStatistic(Statistic.KILL_ENTITY, EntityType.ENDER_DRAGON))));

		meta.setDisplayName(main.makeColors("&7&lMobs killed"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getMoves(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.CHAINMAIL_BOOTS);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&9Jumps: " + String.valueOf(p.getStatistic(Statistic.JUMP))));
		metalist.add(main
				.makeColors("&9Horse ridden: " + String.valueOf(p.getStatistic(Statistic.HORSE_ONE_CM) / 100) + "m"));
		metalist.add(main.makeColors(
				"&9Strider ridden: " + String.valueOf(p.getStatistic(Statistic.STRIDER_ONE_CM) / 100) + "m"));
		metalist.add(
				main.makeColors("&9Pig ridden: " + String.valueOf(p.getStatistic(Statistic.PIG_ONE_CM) / 100) + "m"));
		metalist.add(main.makeColors(
				"&9Walked on water: " + String.valueOf(p.getStatistic(Statistic.WALK_ON_WATER_ONE_CM) / 100) + "m"));
		metalist.add(main.makeColors("&9Fallen: " + String.valueOf(p.getStatistic(Statistic.FALL_ONE_CM) / 100) + "m"));
		metalist.add(main
				.makeColors("&9Elytra used: " + String.valueOf(p.getStatistic(Statistic.AVIATE_ONE_CM) / 100) + "m"));
		metalist.add(
				main.makeColors("&9Climbed: " + String.valueOf(p.getStatistic(Statistic.CLIMB_ONE_CM) / 100) + "m"));
		metalist.add(main.makeColors("&9Walked: " + String.valueOf(p.getStatistic(Statistic.WALK_ONE_CM) / 100) + "m"));
		metalist.add(
				main.makeColors("&9Crouched: " + String.valueOf(p.getStatistic(Statistic.CROUCH_ONE_CM) / 100) + "m"));
		metalist.add(main.makeColors("&9Swam: " + String.valueOf(p.getStatistic(Statistic.SWIM_ONE_CM) / 100) + "m"));
		metalist.add(
				main.makeColors("&9Sprinted: " + String.valueOf(p.getStatistic(Statistic.SPRINT_ONE_CM) / 100) + "m"));
		metalist.add(
				main.makeColors("&9By Boat: " + String.valueOf(p.getStatistic(Statistic.BOAT_ONE_CM) / 100) + "m"));
		metalist.add(main
				.makeColors("&9By Minecart: " + String.valueOf(p.getStatistic(Statistic.MINECART_ONE_CM) / 100) + "m"));

		meta.setDisplayName(main.makeColors("&7&lMovements"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getFoodEaten(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.CARROT);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main
				.makeColors("&9Steak: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.COOKED_BEEF))));
		metalist.add(main.makeColors(
				"&9Cooked Porkchop: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.COOKED_PORKCHOP))));
		// metalist.add(main.makeColors(
		// "&9Cooked Mutton: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM,
		// Material.COOKED_MUTTON))));
		metalist.add(main.makeColors(
				"&9Coocked Chicken: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.COOKED_CHICKEN))));
		metalist.add(main
				.makeColors("&9Raw Chicken: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.CHICKEN))));
		metalist.add(main.makeColors("&9Apple: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.APPLE))));
		// Normal Carrots are currently bugged due to USE_ITEM counting placed carrots
		// too :( [THIS MIGHT NOT BE FIXED]
		metalist.add(main.makeColors("&9Golden Carrot: " + String.valueOf(// p.getStatistic(Statistic.USE_ITEM,
																			// Material.CARROT)+
				p.getStatistic(Statistic.USE_ITEM, Material.GOLDEN_CARROT))));
		metalist.add(main.makeColors("&9Bread: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.BREAD))));
		metalist.add(main.makeColors(
				"&9Baked Potato: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.BAKED_POTATO))));
		metalist.add(main
				.makeColors("&9Beetroot: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.BEETROOT))));
		metalist.add(main.makeColors(
				"&9Mushroom Stew: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.MUSHROOM_STEW))));
		/*
		 * metalist.add(main.makeColors( "&9Cooked Salmon: " +
		 * String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.COOKED_SALMON))));
		 */
		metalist.add(main
				.makeColors("&9Melon: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.MELON_SLICE))));
		metalist.add(main.makeColors(
				"&9Golden Apple: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.GOLDEN_APPLE))));
		metalist.add(main.makeColors(
				"&9Sweet Berries: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.SWEET_BERRIES))));
		metalist.add(
				main.makeColors("&9Kelp: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.DRIED_KELP))));
		metalist.add(main
				.makeColors("&9Honey: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.HONEY_BOTTLE))));
		metalist.add(
				main.makeColors("&9Milk: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.MILK_BUCKET))));

		meta.setDisplayName(main.makeColors("&7&lFood eaten"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getPvpKills(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&9Total: " + String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS))));
		metalist.add(main.makeColors("&9K/D: " + String.valueOf((double) p.getStatistic(Statistic.PLAYER_KILLS)
				/ (double) p.getStatistic(Statistic.ENTITY_KILLED_BY, EntityType.PLAYER))));
		meta.setDisplayName(main.makeColors("&7&lPlayers Killed"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getPlaytime(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.CLOCK);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors(
				"&9Total: " + String.valueOf(((p.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20) / 60) / 60) + "h"));
		metalist.add(main.makeColors(
				"&9Since rest: " + String.valueOf(((p.getStatistic(Statistic.TIME_SINCE_REST) / 20) / 60)) + "m"));
		metalist.add(main.makeColors("&9Since death: "
				+ String.valueOf(((p.getStatistic(Statistic.TIME_SINCE_DEATH) / 20) / 60) / 60) + "h"));

		meta.setDisplayName(main.makeColors("&7&lPlaytime"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getToolsBroken(UUID u) {// changed to tools (and weapons) used in v1.2
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.IRON_PICKAXE);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors(
				"&9Iron Sword: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.IRON_SWORD))));
		metalist.add(main.makeColors(
				"&9Diamond Sword: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.DIAMOND_SWORD))));
		metalist.add(main.makeColors(
				"&9Netherite Sword: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.NETHERITE_SWORD))));
		metalist.add(main.makeColors(
				"&9Iron Pickaxe: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.IRON_PICKAXE))));
		metalist.add(main.makeColors(
				"&9Diamond Pickaxe: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.DIAMOND_PICKAXE))));
		metalist.add(main.makeColors("&9Netherite Pickaxe: "
				+ String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.NETHERITE_PICKAXE))));
		metalist.add(main.makeColors(
				"&9Diamond Axe: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.DIAMOND_AXE))));
		metalist.add(main.makeColors(
				"&9Netherite Axe: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.NETHERITE_AXE))));
		metalist.add(
				main.makeColors("&9Trident: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.TRIDENT))));
		metalist.add(main.makeColors(
				"&9Flint and Steel: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.FLINT_AND_STEEL))));
		metalist.add(
				main.makeColors("&9Shears: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.SHEARS))));
		metalist.add(main.makeColors(
				"&9Fishing Rod: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.FISHING_ROD))));
		metalist.add(main
				.makeColors("&9Name Tag: " + String.valueOf(p.getStatistic(Statistic.USE_ITEM, Material.NAME_TAG))));

		meta.setDisplayName(main.makeColors("&7&lTools used"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getTrades(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.EMERALD);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&9Total: " + String.valueOf(p.getStatistic(Statistic.TRADED_WITH_VILLAGER))));
		metalist.add(main.makeColors("&9Interacted: " + String.valueOf(p.getStatistic(Statistic.TALKED_TO_VILLAGER))));
		metalist.add(main.makeColors("&9Raids triggered: " + String.valueOf(p.getStatistic(Statistic.RAID_TRIGGER))));
		metalist.add(main.makeColors("&9Raids won: " + String.valueOf(p.getStatistic(Statistic.RAID_WIN))));

		meta.setDisplayName(main.makeColors("&7&lTraded with villagers"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getXpGained(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.EXPERIENCE_BOTTLE);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		if (p.isOnline() == true) {
			Player o = p.getPlayer();
			metalist.add(main.makeColors("&9Current level: " + String.valueOf(o.getLevel())));
			metalist.add(main.makeColors("&9Total: " + String.valueOf(o.getTotalExperience())));
		} else {
			metalist.add(main.makeColors("&9Current level: " + "&c&oPlayer is not online"));
			metalist.add(main.makeColors("&9Total: " + "&c&oPlayer is not online"));
		}

		meta.setDisplayName(main.makeColors("&7&lExperience gained"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getChestsOpened(UUID u) { // interacted with block / etc
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.CHEST);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors(
				"&9Crafting Table: " + String.valueOf(p.getStatistic(Statistic.CRAFTING_TABLE_INTERACTION))));
		metalist.add(main.makeColors("&9Chest: " + String.valueOf(p.getStatistic(Statistic.CHEST_OPENED))));
		metalist.add(main.makeColors("&9Enderchest: " + String.valueOf(p.getStatistic(Statistic.ENDERCHEST_OPENED))));
		metalist.add(main.makeColors("&9Anvil: " + String.valueOf(p.getStatistic(Statistic.INTERACT_WITH_ANVIL))));
		metalist.add(main.makeColors("&9Barrel: " + String.valueOf(p.getStatistic(Statistic.OPEN_BARREL))));
		metalist.add(main
				.makeColors("&9Brewing Stand: " + String.valueOf(p.getStatistic(Statistic.BREWINGSTAND_INTERACTION))));
		metalist.add(main.makeColors("&9Shulker Box: " + String.valueOf(p.getStatistic(Statistic.SHULKER_BOX_OPENED))));
		// metalist.add(main.makeColors("&9Cartography Table: "+
		// String.valueOf(p.getStatistic(Statistic.INTERACT_WITH_CARTOGRAPHY_TABLE))));
		// metalist.add(main.makeColors("&9Smithing Table: "+
		// String.valueOf(p.getStatistic(Statistic.INTERACT_WITH_SMITHING_TABLE))));
		metalist.add(main.makeColors("&9Cake: " + String.valueOf(p.getStatistic(Statistic.CAKE_SLICES_EATEN))));
		metalist.add(
				main.makeColors("&9Campfire: " + String.valueOf(p.getStatistic(Statistic.INTERACT_WITH_CAMPFIRE))));
		metalist.add(main.makeColors("&9Lectern: " + String.valueOf(p.getStatistic(Statistic.INTERACT_WITH_LECTERN))));
		metalist.add(main.makeColors("&9Furnace: " + String.valueOf(p.getStatistic(Statistic.FURNACE_INTERACTION)
				+ p.getStatistic(Statistic.INTERACT_WITH_BLAST_FURNACE))));
		metalist.add(main.makeColors("&9Plants potted: " + String.valueOf(p.getStatistic(Statistic.FLOWER_POTTED))));
		metalist.add(
				main.makeColors("&9Grindstone: " + String.valueOf(p.getStatistic(Statistic.INTERACT_WITH_GRINDSTONE))));
		metalist.add(main.makeColors("&9Hopper: " + String.valueOf(p.getStatistic(Statistic.HOPPER_INSPECTED))));
		metalist.add(main.makeColors("&9Beacon: " + String.valueOf(p.getStatistic(Statistic.BEACON_INTERACTION))));

		meta.setDisplayName(main.makeColors("&7&lBlock Interactions"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getItemsUsed(UUID u) { // item interactions
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.ENDER_EYE);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&9Eggs thrown: "+ String.valueOf(p.getStatistic(Statistic.USE_ITEM,Material.EGG))));
		metalist.add(main.makeColors("&9Snowballs thrown: "+ String.valueOf(p.getStatistic(Statistic.USE_ITEM,Material.SNOWBALL))));
		metalist.add(main.makeColors("&9Eye of Ender: "+ String.valueOf(p.getStatistic(Statistic.USE_ITEM,Material.ENDER_EYE))));
		metalist.add(main.makeColors("&9Ender Pearl: "+ String.valueOf(p.getStatistic(Statistic.USE_ITEM,Material.ENDER_PEARL))));
		metalist.add(main.makeColors("&9Bone Meal: "+ String.valueOf(p.getStatistic(Statistic.USE_ITEM,Material.BONE_MEAL))));
		metalist.add(main.makeColors("&9Book and Quill: "+ String.valueOf(p.getStatistic(Statistic.USE_ITEM,Material.WRITABLE_BOOK))));
		metalist.add(main.makeColors("&9Maps created: "+ String.valueOf(p.getStatistic(Statistic.USE_ITEM,Material.MAP))));
		metalist.add(main.makeColors("&9Painting placed: "+ String.valueOf(p.getStatistic(Statistic.USE_ITEM,Material.PAINTING))));
		metalist.add(main.makeColors("&9Item Frame placed: "+ String.valueOf(p.getStatistic(Statistic.USE_ITEM,Material.ITEM_FRAME))));
		metalist.add(main.makeColors("&9Firework Rocket: "+ String.valueOf(p.getStatistic(Statistic.USE_ITEM,Material.FIREWORK_ROCKET))));
		metalist.add(main.makeColors("&9Potions Consumed: "+ String.valueOf(p.getStatistic(Statistic.USE_ITEM,Material.POTION))));

		meta.setDisplayName(main.makeColors("&7&lItem Interactions"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getPlayer(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
		meta.setOwningPlayer(p);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&7" + p.getUniqueId().toString()));

		meta.setDisplayName(main.makeColors("&d&l" + p.getName().toString()));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getMusic(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.JUKEBOX);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(
				main.makeColors("&9Noteblock played: " + String.valueOf(p.getStatistic(Statistic.NOTEBLOCK_PLAYED))));
		metalist.add(
				main.makeColors("&9Noteblock tuned: " + String.valueOf(p.getStatistic(Statistic.NOTEBLOCK_TUNED))));
		metalist.add(main.makeColors("&9Records played: " + String.valueOf(p.getStatistic(Statistic.RECORD_PLAYED))));
		metalist.add(main.makeColors(
				"&9Jukeboxes crafted: " + String.valueOf(p.getStatistic(Statistic.CRAFT_ITEM, Material.JUKEBOX))));

		meta.setDisplayName(main.makeColors("&7&lMusic Played"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getBellRang(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.BELL);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&9Total: " + String.valueOf(p.getStatistic(Statistic.BELL_RING))));

		meta.setDisplayName(main.makeColors("&7&lBell Rung"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getTargetsHit(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.TARGET);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&9Total: " + String.valueOf(p.getStatistic(Statistic.TARGET_HIT))));

		meta.setDisplayName(main.makeColors("&7&lTargets Hit"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getItemsEnchanted(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.ENCHANTING_TABLE);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&9Total: " + String.valueOf(p.getStatistic(Statistic.ITEM_ENCHANTED))));

		meta.setDisplayName(main.makeColors("&7&lItems Enchanted"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getPlayStats(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.WRITABLE_BOOK);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&9First joined: " + Main.formatTime(p.getFirstPlayed())));
		if (p.isOnline() == true) {
			metalist.add(main.makeColors("&9Last played: " + "&a&oCurrently Online"));
		} else {
			metalist.add(main.makeColors("&9Last played: " + Main.formatTime(p.getLastPlayed())));
		}

		meta.setDisplayName(main.makeColors("&7&lFirst Joined"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack getAnimalsBred(UUID u) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(u);
		ItemStack item = new ItemStack(Material.RABBIT_HIDE);
		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(Material.STICK);
		List<String> metalist = new ArrayList<String>();
		metalist.add(main.makeColors("&9Total: " + String.valueOf(p.getStatistic(Statistic.ANIMALS_BRED))));

		meta.setDisplayName(main.makeColors("&7Animals Bred"));
		meta.setLore(metalist);
		item.setItemMeta(meta);
		return item;
	}

}
