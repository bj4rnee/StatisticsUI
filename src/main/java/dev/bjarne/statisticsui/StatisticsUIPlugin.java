package dev.bjarne.statisticsui;

import dev.bjarne.statisticsui.command.StatsCommand;
import dev.bjarne.statisticsui.config.Settings;
import dev.bjarne.statisticsui.menu.MenuListener;
import dev.bjarne.statisticsui.stats.StatsService;
import java.util.concurrent.atomic.AtomicInteger;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SingleLineChart;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main Plugin class
 * 
 * @author bj4rnee
 *
 */
public final class StatisticsUIPlugin extends JavaPlugin {

    private static final int BSTATS_SERVICE_ID = 10389;

    private final AtomicInteger commandsExecuted = new AtomicInteger();
    private Settings settings;
    private StatsService statsService;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        settings = new Settings(getConfig());
        statsService = new StatsService(this, settings.cacheMillis());

        getServer().getPluginManager().registerEvents(new MenuListener(), this);

        StatsCommand command = new StatsCommand(this, statsService, settings);
        PluginCommand stats = getCommand("stats");
        if (stats != null) {
            stats.setExecutor(command);
            stats.setTabCompleter(command);
        }

        Metrics metrics = new Metrics(this, BSTATS_SERVICE_ID);
        metrics.addCustomChart(new SingleLineChart("commands_executed", () -> commandsExecuted.getAndSet(0)));

        announce("enabled");
    }

    @Override
    public void onDisable() {
        announce("disabled");
    }

    private void announce(String state) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Statistics" + ChatColor.YELLOW + "UI"
                + ChatColor.RESET + " v" + getDescription().getVersion() + " has been " + state);
    }

    public void reload() {
        reloadConfig();
        settings.load(getConfig());
        statsService.cacheMillis(settings.cacheMillis());
        statsService.clearCache();
    }

    public void countCommand() {
        commandsExecuted.incrementAndGet();
    }
}
