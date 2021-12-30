package dev.bjarne.statisticsUI;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.*;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Main Plugin class. Current version: 1.2
 * 
 * @author bj4rne
 *
 */
public class main extends JavaPlugin implements Listener {

	public String prefix;
	public String help;
	public itemHandler iH;
	public guiHandler gH;
	public eventHandler eH;
	private int commandsExecuted;
	private int serviceId = 10389;

	static Map<String, String> prefixMap = new HashMap<String, String>();
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm");

	public boolean usePermissions;

	// Fired when plugin is first enabled
	@Override
	public void onEnable() {
		getLogger().info(makeColors("&dStatisticsUI v1.2 has been enabled"));

		commandsExecuted = 0; // number of StatisticsUI commands executed

		iH = new itemHandler();
		gH = new guiHandler();
		eH = new eventHandler();
		Metrics metrics = new Metrics(this, serviceId);

		// the metrics custom line chart
		metrics.addCustomChart(new Metrics.SingleLineChart("command_executed", new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return commandsExecuted;
			}
		}));

		help = ChatColor.RESET + "" + ChatColor.GRAY;
		Bukkit.getPluginManager().registerEvents(eH, this);

		// creating of Directory
		File Dir1 = new File("plugins" + File.separator + "StatisticsUI");
		if (!Dir1.exists())
			Dir1.mkdir();

		// create the config.yml
		File configFile = new File("plugins" + File.separator + "StatisticsUI" + File.separator + "config.yml");
		if (!configFile.exists()) {
			copy(getResource("config.yml"), configFile);
		}

		gH.setMain(this);
		iH.setMain(this);
		eH.setMain(this);

		// check, if permissions should be used
		usePermissions = getConfig().getBoolean("permission-system.enabled");
	}

	// Fired when plugin is disabled
	@Override
	public void onDisable() {
		getLogger().info(makeColors("&dStatisticsUI v1.2 has been disabled"));
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
			boolean perms = false;
			if (label.equalsIgnoreCase("stats") || label.equalsIgnoreCase("statistics")) {

				if (args.length == 0) {
					if ((p.hasPermission("statsui.opengui.self") || p.hasPermission("statsui.admin"))
							|| !usePermissions) {
						try {
							gH.getGuiStats(p.getUniqueId(), p);
							commandsExecuted++;
						} catch (NullPointerException e) {
							e.printStackTrace();
						}
					} else {
						perms = true;
					}
				}
				if (args.length == 1) {
					if ((p.hasPermission("statsui.opengui.others") || p.hasPermission("statsui.admin"))
							|| !usePermissions) {
						// if (Bukkit.getOfflinePlayer(args[0]).getPlayer() == null) {
						OfflinePlayer pOther = Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(args[0]).getUniqueId());
						if (pOther.hasPlayedBefore() == false) {
							p.sendMessage(
									makeColors("&cPlayer '" + args[0] + "' not found :( ") + pOther.getUniqueId());
						} else {
							if (pOther.isOnline() || (pOther.getPlayer() != null)) {
								try {
									gH.getGuiStats(pOther.getUniqueId(), p);
									commandsExecuted++;
								} catch (NullPointerException e) {
									e.printStackTrace();
								}
							} else {
								try {
									// this else section is kinda unnecessary but lol
									gH.getGuiStats(pOther.getUniqueId(), p);
									commandsExecuted++;
								} catch (NullPointerException e) {
									e.printStackTrace();
								}
							}
						}
					} else {
						perms = true;
					}
				}
			}
			if (args.length > 1) {
				p.sendMessage(makeColors("&l&aUsage:"));
				p.sendMessage(makeColors("&a/stats" + " - check your stats."));
				p.sendMessage(makeColors("&a/stats [Playername]" + " - check the stats of someone else."));
			}
			if (perms) {
				p.sendMessage(makeColors("&cYou do not have one of the required permissions:"));
				p.sendMessage(makeColors("&c&lstatsui.opengui.* &r&cOR &c&lstatsui.admin"));
				getLogger().info(makeColors("&c" + p.getName() + " &4was denied access to command"));
			}
		} else {
			getLogger().info(makeColors("&4This command is only executable as a Player."));
		}
		return false;
	}

	/**
	 * Method to transform Minecraft color codes
	 * 
	 * @param s
	 * @return
	 */
	public static String makeColors(String s) {
		String replaced = s.replaceAll("&0", "" + ChatColor.BLACK).replaceAll("&1", "" + ChatColor.DARK_BLUE)
				.replaceAll("&2", "" + ChatColor.DARK_GREEN).replaceAll("&3", "" + ChatColor.DARK_AQUA)
				.replaceAll("&4", "" + ChatColor.DARK_RED).replaceAll("&5", "" + ChatColor.DARK_PURPLE)
				.replaceAll("&6", "" + ChatColor.GOLD).replaceAll("&7", "" + ChatColor.GRAY)
				.replaceAll("&8", "" + ChatColor.DARK_GRAY).replaceAll("&9", "" + ChatColor.BLUE)
				.replaceAll("&a", "" + ChatColor.GREEN).replaceAll("&b", "" + ChatColor.AQUA)
				.replaceAll("&c", "" + ChatColor.RED).replaceAll("&d", "" + ChatColor.LIGHT_PURPLE)
				.replaceAll("&e", "" + ChatColor.YELLOW).replaceAll("&f", "" + ChatColor.WHITE)
				.replaceAll("&r", "" + ChatColor.RESET).replaceAll("&l", "" + ChatColor.BOLD)
				.replaceAll("&o", "" + ChatColor.ITALIC).replaceAll("&k", "" + ChatColor.MAGIC)
				.replaceAll("&m", "" + ChatColor.STRIKETHROUGH).replaceAll("&n", "" + ChatColor.UNDERLINE)
				.replaceAll("\\\\", " ");
		return replaced;
	}

	/**
	 * Time delta String formatter
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	private String formatTimeDelta(long before, long after) {
		long delta = after - before;
		int weeks = (int) (delta / 604800000);
		int weeksRemain = (int) (delta % 604800000);
		int days = (int) (weeksRemain / 86400000);
		int daysRemain = weeksRemain % 86400000;
		int hours = (int) (daysRemain / 3600000);
		int hoursRemain = daysRemain % 3600000;
		int minutes = (int) (hoursRemain / 60000);
		int minutesRemain = hoursRemain % 60000;
		int seconds = (int) (minutesRemain / 1000);
		String time = weeks + " Weeks " + days + " Days " + hours + " Hours " + minutes + " Minutes " + seconds
				+ " Seconds";
		if (weeks == 0) {
			time = time.replace(weeks + " Weeks ", "");
		}
		if (weeks == 0 && days == 0) {
			time = time.replace(days + " Days ", "");
		}
		if (weeks == 0 && days == 0 && hours == 0) {
			time = time.replace(hours + " Hours ", "");
		}
		if (weeks == 0 && days == 0 && hours == 0 && minutes == 0) {
			time = time.replace(minutes + " Minutes ", "");
		}
		return time;
	}

	/**
	 * Simple number formatter
	 * 
	 * @param number
	 * @return
	 */
	private String formatNumber(int number) {
		NumberFormat myFormat = NumberFormat.getInstance();
		myFormat.setGroupingUsed(true);
		return myFormat.format(number);
	}

	/**
	 * copies a File from an inputStream
	 * 
	 * @param in
	 * @param file
	 */
	private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * returns formatted time
	 * 
	 * @param milliSeconds (unix-timestamp)
	 * @return date format as set in simpleDateFormat
	 */
	public String formatTime(long milliSeconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return simpleDateFormat.format(calendar.getTime());
	}
}
