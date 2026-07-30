package dev.bjarne.statisticsui.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/** formatting and stuff. Dates use the day-first layout i like more. what are you going to do about it? */
public final class Format {

    private static final DateTimeFormatter DATE =
            DateTimeFormatter.ofPattern("dd.MM.yyyy 'at' HH:mm").withZone(ZoneId.systemDefault());

    private Format() {
    }

    public static String number(long value) {
        return String.format(Locale.US, "%,d", value);
    }

    public static String meters(long centimeters) {
        return number(centimeters / 100) + "m";
    }

    public static String hoursFromTicks(long ticks) {
        return number(ticks / 20 / 60 / 60) + "h";
    }

    public static String minutesFromTicks(long ticks) {
        return number(ticks / 20 / 60) + "m";
    }

    /** Damage statistics are stored in tenths of a health point; two points make one heart. */
    public static String hearts(long damage) {
        return hearts(damage, "&4");
    }

    public static String hearts(long damage, String heartColor) {
        return number(damage / 10 / 2) + " " + heartColor + "♥";
    }

    public static String ratio(long numerator, long denominator) {
        if (denominator <= 0) {
            return "-";
        }
        return String.format(Locale.US, "%.2f", (double) numerator / denominator);
    }

    public static String date(long epochMillis) {
        return DATE.format(Instant.ofEpochMilli(epochMillis));
    }
}
