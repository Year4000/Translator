package net.year4000.translator;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.logging.Level;

public class Translator extends Plugin {
    private final static boolean DEBUG = true;
    private static Translator translator;

    @Override
    public void onEnable() {
        this.translator = this;

        // Configs
        new Config();
        new Languages();
        // Commands
        new LanguageCommand();
    }

    /**
     * Get the instance of Translator
     */
    public static Translator inst() {
        return translator;
    }

    /**
     * Replace the chat with the right colors.
     * @param message The message to add colors.
     * @return The message with colors.
     */
    public static String replaceColor(String message) {
        final char COLOR_CHAR = '\u00A7';
        return message.replaceAll("&([0-9a-fA-Fk-rK-R])", "(" + COLOR_CHAR + "$1)");
    }

    /**
     * Fix the formating when translating the text.
     * @param message Bad color format.
     * @return Good color format.
     */
    public static String formatColor(String message) {
        final char COLOR_CHAR = '\u00A7';
        return message.replaceAll("\\(" + COLOR_CHAR + "([0-9a-fA-Fk-rK-R])\\)", COLOR_CHAR + "$1");
    }

    /**
     * Strip the colors from the message.
     * @param message The message to strip from.
     * @return The clean version of the message.
     */
    public static String stripColor(String message) {
        return ChatColor.stripColor(message);
    }

    /**
     * Create a log message
     * @param message The message to log.
     */
    public static void log(String message) {
        inst().getLogger().log(Level.INFO, message);
    }

    /**
     * A message that will be logged when debug is enabled.
     * @param message The message to log.
     */
    public static void debug(String message) {
        if (DEBUG) log("DEBUG: " + message);
    }
}
