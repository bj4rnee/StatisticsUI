package dev.bjarne.statisticsUI;

import java.util.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class guiHandler implements Listener {

	public static main Main;
	public Set<UUID> idSet;
	public long delay = 20L; // 15 ticks, 0.75s OR 20 ticks, 1s worked well

	public guiHandler() {
		idSet = new HashSet<UUID>();
	}

	public void setMain(main Main) {
		guiHandler.Main = Main;
		Main.gH = this;
	}

	/**
	 * This Method handles the GUI for Players on a UUID basis
	 * 
	 * @param p
	 * @param opener
	 */
	public void getGuiStats(UUID p, Player opener) {
		Inventory inv;
		ItemStack Filler = Main.iH.getFiller();
		if (Bukkit.getOfflinePlayer(p) != null) {
			inv = Bukkit.createInventory(null, 54,
					main.makeColors("&7Statistics of " + "&d" + Bukkit.getOfflinePlayer(p).getName()));
		} else {
			inv = Bukkit.createInventory(null, 54,
					main.makeColors("&7Statistics of " + "&d" + Bukkit.getPlayer(p).getName()));
		}
		//if the UUID hasn't been checked before, check with delay
		if (!idSet.contains(p)) {
			idSet.add(p);

			// initializing the loading screen
			ItemStack Loading = Main.iH.getLoading();
			for (int i = 0; i < 54; i++) {
				inv.setItem(i, Loading);
			}
			opener.openInventory(inv);

			// loading stats with delay to not cause a lag spike
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main, new Runnable() {

				@Override
				public void run() {
					ItemStack ArrowsFired = Main.iH.getArrowsFired(p);
					inv.setItem(18, ArrowsFired);
					ItemStack BedsEntered = Main.iH.getBedsEntered(p);
					inv.setItem(19, BedsEntered);
					ItemStack BlocksBroken = Main.iH.getBlocksBroken(p);
					inv.setItem(20, BlocksBroken);
					ItemStack BlocksPlaced = Main.iH.getBlocksPlaced(p);
					inv.setItem(21, BlocksPlaced);
					ItemStack Player = Main.iH.getPlayer(p); // sets the playerhead
					inv.setItem(4, Player);
					opener.updateInventory();
				}
			}, delay);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main, new Runnable() {

				@Override
				public void run() {
					ItemStack BucketsEmptied = Main.iH.getBucketsEmptied(p);
					inv.setItem(22, BucketsEmptied); // used bucket in general
					ItemStack DamageTaken = Main.iH.getDamageTaken(p);
					inv.setItem(23, DamageTaken);
					ItemStack Deaths = Main.iH.getDeaths(p);
					inv.setItem(24, Deaths);
					ItemStack FishCaught = Main.iH.getFishCaught(p);
					inv.setItem(25, FishCaught);
					ItemStack ItemsCrafted = Main.iH.getItemsCrafted(p);
					inv.setItem(26, ItemsCrafted);
					opener.updateInventory();
				}
			}, 2*delay);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main, new Runnable() {

				@Override
				public void run() {
					ItemStack ItemsDropped = Main.iH.getItemsDropped(p);
					inv.setItem(27, ItemsDropped); // e.g. eggs thrown, items picked up
					ItemStack Joins = Main.iH.getJoins(p);
					inv.setItem(28, Joins);
					ItemStack MobKills = Main.iH.getMobKills(p);
					inv.setItem(29, MobKills);
					ItemStack Moves = Main.iH.getMoves(p);
					inv.setItem(30, Moves);// every form of movement
					opener.updateInventory();
				}
			}, 3*delay);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main, new Runnable() {

				@Override
				public void run() {
					ItemStack FoodEaten = Main.iH.getFoodEaten(p);
					inv.setItem(31, FoodEaten);
					ItemStack PvpKills = Main.iH.getPvpKills(p);
					inv.setItem(32, PvpKills);
					ItemStack Playtime = Main.iH.getPlaytime(p);
					inv.setItem(33, Playtime);
					ItemStack ItemsUsed = Main.iH.getItemsUsed(p);
					inv.setItem(34, ItemsUsed);
					ItemStack ToolsBroken = Main.iH.getToolsBroken(p);
					inv.setItem(35, ToolsBroken);
					ItemStack AnimalsBred = Main.iH.getAnimalsBred(p);
					inv.setItem(44,AnimalsBred);
					opener.updateInventory();
				}
			}, 4*delay);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main, new Runnable() {

				@Override
				public void run() {
					ItemStack Trades = Main.iH.getTrades(p);
					inv.setItem(36, Trades);
					ItemStack XpGained = Main.iH.getXpGained(p);
					inv.setItem(37, XpGained);
					ItemStack ChestsOpened = Main.iH.getChestsOpened(p);
					inv.setItem(38, ChestsOpened); // used a block (ITEMS SEPERATELY)
					ItemStack Music = Main.iH.getMusic(p);
					inv.setItem(39, Music);
					ItemStack BellRang = Main.iH.getBellRang(p);
					inv.setItem(40, BellRang);
					ItemStack TargetsHit = Main.iH.getTargetsHit(p);
					inv.setItem(41, TargetsHit);
					ItemStack ItemsEnchanted = Main.iH.getItemsEnchanted(p);
					inv.setItem(42, ItemsEnchanted);
					ItemStack PlayStats = Main.iH.getPlayStats(p);
					inv.setItem(43, PlayStats);
					
					for (int i = 0; i < 54; i++) {
						if (inv.getItem(i).equals(Loading) == true) {
							inv.setItem(i, Filler);
						}
					}
					opener.updateInventory();
				}
			}, 5*delay);
			
			
		} else { // gets called, if player's statistics are checked a second time, which is
					// faster bc
					// they're cached

			for (int i = 0; i < 54; i++) {
				inv.setItem(i, Filler);
			}
			ItemStack ArrowsFired = Main.iH.getArrowsFired(p);
			inv.setItem(18, ArrowsFired);
			ItemStack BedsEntered = Main.iH.getBedsEntered(p);
			inv.setItem(19, BedsEntered);
			ItemStack BlocksBroken = Main.iH.getBlocksBroken(p);
			inv.setItem(20, BlocksBroken);
			ItemStack BlocksPlaced = Main.iH.getBlocksPlaced(p);
			inv.setItem(21, BlocksPlaced);
			ItemStack BucketsEmptied = Main.iH.getBucketsEmptied(p);
			inv.setItem(22, BucketsEmptied); // used bucket in general
			ItemStack DamageTaken = Main.iH.getDamageTaken(p);
			inv.setItem(23, DamageTaken);
			ItemStack Deaths = Main.iH.getDeaths(p);
			inv.setItem(24, Deaths);
			ItemStack FishCaught = Main.iH.getFishCaught(p);
			inv.setItem(25, FishCaught);
			ItemStack ItemsCrafted = Main.iH.getItemsCrafted(p);
			inv.setItem(26, ItemsCrafted);
			ItemStack ItemsDropped = Main.iH.getItemsDropped(p);
			inv.setItem(27, ItemsDropped); // e.g. eggs thrown, items picked up
			ItemStack Joins = Main.iH.getJoins(p);
			inv.setItem(28, Joins);
			ItemStack MobKills = Main.iH.getMobKills(p);
			inv.setItem(29, MobKills);
			ItemStack Moves = Main.iH.getMoves(p);
			inv.setItem(30, Moves);// every form of movement
			ItemStack FoodEaten = Main.iH.getFoodEaten(p);
			inv.setItem(31, FoodEaten);
			ItemStack PvpKills = Main.iH.getPvpKills(p);
			inv.setItem(32, PvpKills);
			ItemStack Playtime = Main.iH.getPlaytime(p);
			inv.setItem(33, Playtime);
			ItemStack ItemsUsed = Main.iH.getItemsUsed(p);
			inv.setItem(34, ItemsUsed);
			ItemStack ToolsBroken = Main.iH.getToolsBroken(p);
			inv.setItem(35, ToolsBroken);
			ItemStack Trades = Main.iH.getTrades(p);
			inv.setItem(36, Trades);
			ItemStack XpGained = Main.iH.getXpGained(p);
			inv.setItem(37, XpGained);
			ItemStack ChestsOpened = Main.iH.getChestsOpened(p);
			inv.setItem(38, ChestsOpened); // used a block (ITEMS SEPERATELY)
			ItemStack Music = Main.iH.getMusic(p);
			inv.setItem(39, Music);
			ItemStack BellRang = Main.iH.getBellRang(p);
			inv.setItem(40, BellRang);
			ItemStack TargetsHit = Main.iH.getTargetsHit(p);
			inv.setItem(41, TargetsHit);
			ItemStack ItemsEnchanted = Main.iH.getItemsEnchanted(p);
			inv.setItem(42, ItemsEnchanted);
			ItemStack PlayStats = Main.iH.getPlayStats(p);
			inv.setItem(43, PlayStats);
			ItemStack AnimalsBred = Main.iH.getAnimalsBred(p);
			inv.setItem(44,AnimalsBred);

			ItemStack Player = Main.iH.getPlayer(p); // sets the playerhead
			inv.setItem(4, Player);
			// open the inventory
			opener.openInventory(inv);
		}
	}
}
