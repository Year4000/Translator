package net.year4000.translator;

import com.memetix.mst.language.Language;
import net.cubespace.Yamler.Config.InvalidConfigurationException;

public class TranslateMessage {
    Language playerLang = null;

    public TranslateMessage(String player) {
        try {
            playerLang = new Languages().getLanguage(player);
        }
        catch (InvalidConfigurationException e) {
            Translator.log("Could not get player's language.");
            Translator.debug(e.getMessage());
        }
    }

    /**
     * Translate a message to the language.
     * @param message The message to be translated.
     * @return The translated message.
     */
    public String translate(String message) {
        return new TranslateLanguage().translate(message, playerLang);
    }
}
