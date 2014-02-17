package net.year4000.translator;

import com.memetix.mst.language.Language;
import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Config;
import net.cubespace.Yamler.Config.InvalidConfigurationException;

import java.io.File;
import java.util.HashMap;

public class Languages extends Config {
    public Languages() {
        CONFIG_HEADER = new String[]{"Player's Language Preferences"};
        CONFIG_FILE = new File(Translator.inst().getDataFolder(), "languages.yml");

        try {
            init();
        }
        catch (InvalidConfigurationException e) {
            Translator.log("Could not read player's language preferences.");
        }
    }

    @Comment("This is the storage of all the player's language preferences.")
    private HashMap<String, String> languages = new HashMap<String, String>();

    /**
     * Add the player so we can track their language.
     * @param player The player to track.
     * @return This class.
     */
    public Languages addPlayer(String player) throws InvalidConfigurationException {
        languages.put(player, new net.year4000.translator.Config().getDefaultLang());

        // Save the config file
        save();

        return this;
    }

    /**
     * Get the language of the player.
     * @param player The player to get the language from.
     * @return The current language of the player.
     */
    public Language getLanguage(String player) throws InvalidConfigurationException {
        // When a player is not found add them.
        if (!languages.containsKey(player)) {
            addPlayer(player);
        }

        return Language.valueOf(languages.get(player));
    }

    /**
     * Set the language of the player.
     * @param player The player to set the language.
     * @param lang The lang to set the player with.
     * @return The new language of the player.
     */
    public Language setLanguage(String player, String lang) throws InvalidConfigurationException {
        // When a player is not found add them.
        if (!languages.containsKey(player)) {
            addPlayer(player);
        }

        // Set the language of the player.
        languages.remove(player);
        languages.put(player, lang);

        // Save the config file
        save();

        return Language.valueOf(languages.get(player));
    }
}
