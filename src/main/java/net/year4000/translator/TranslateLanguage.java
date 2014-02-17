package net.year4000.translator;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class TranslateLanguage {
    public TranslateLanguage() {
        Config config = new Config();
        Translate.setClientId(config.getClientId());
        Translate.setClientSecret(config.getClientSecret());
    }

    public String translate(String message, Language language) {
        try {
            String translated =  Translate.execute(Translator.replaceColor(message), language);
            return Translator.formatColor(translated);
        }
        catch (Exception e) {
            Translator.debug(e.getMessage());
            return Translator.replaceColor(message);
        }
    }

    public String translate(String message, String language) {
        return translate(message, Language.fromString(language));
    }
}
