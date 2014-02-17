package net.year4000.translator;

import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.InvalidConfigurationException;

import java.io.File;

public class Config extends net.cubespace.Yamler.Config.Config {
    public Config() {
        CONFIG_HEADER = new String[]{"Translator Config"};
        CONFIG_FILE = new File(Translator.inst().getDataFolder(), "config.yml");
        try {
            init();
        }
        catch (InvalidConfigurationException e) {
            Translator.log("Could not read config.");
            Translator.debug(e.getMessage());
        }
    }

    @Comment("The client ID from Microsoft")
    private String ClientId = "<ENTER ID>";
    @Comment("The client Secert from Microsoft")
    private String ClientSecret = "<ENTER SECERT>";
    @Comment("The default language for the players.")
    private String DefaultLang = "ENGLISH";

    /**
     * Get the client id to use the translator.
     * @return The client id.
     */
    public String getClientId() {
        return ClientId;
    }

    /**
     * Get the client secert to use the translator.
     * @return The client secret.
     */
    public String getClientSecret() {
        return ClientSecret;
    }

    /**
     * Get the default language for all players.
     * @return
     */
    public String getDefaultLang() {
        return DefaultLang;
    }
}
