package dev.bjarne.statisticsui.util;

import org.bukkit.ChatColor;

/** Translates legacy {@code &} colour codes. Here so future i18n could exist one day */
public final class Text {

    private Text() {
    }

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
