package net.year4000.translator;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import net.md_5.bungee.api.plugin.Command;

public class LanguageCommand extends Command {
    public LanguageCommand() {
        super("language");

        ProxyServer.getInstance().getPluginManager().registerCommand(Translator.inst(), this);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        try {
            if (args.length > 1)
                throw new Exception("&c" + new TranslatableComponent("commands.generic.syntax").toPlainText());

            if (args.length == 1)
                setLang(sender, args[0]);
            else
                showLang(sender);
        }
        catch (Exception e) {
            String colors = Translator.formatColor(Translator.replaceColor(e.getMessage()));
            sender.sendMessage(TextComponent.fromLegacyText(colors));
        }
    }

    /**
     * Show the lang of the current player.
     * @param sender The sender of the command.
     */
    private void showLang(final CommandSender sender) {
        try {
            Language playerLang = new Languages().getLanguage(sender.getName());
            String message = String.format(
                    "&6Your language is set to &f%s",
                    playerLang.name().toLowerCase() + " (" + playerLang.toString() + ")"
            );
            message = new TranslateLanguage().translate(message, playerLang);
            sender.sendMessage(TextComponent.fromLegacyText(message));
        }
        catch (Exception e) {
            Translator.debug(e.getStackTrace().toString());
        }
    }

    private void setLang(final CommandSender sender, final String lang) {
        Language playerLang = null;
        try {
            playerLang = new Languages().getLanguage(sender.getName());
            Language newLang = Language.valueOf(lang.toUpperCase());
            new Languages().setLanguage(sender.getName(), newLang.name());
            showLang(sender);
        }
        catch (InvalidConfigurationException e) {
            String colors = Translator.formatColor(Translator.replaceColor("&cCould not get your language."));
            sender.sendMessage(TextComponent.fromLegacyText(colors));
        }
        catch (Exception e) {
            boolean first = true;
            String langs = "&6Language not valid &f";
            langs = new TranslateLanguage().translate(langs, playerLang);

            for (Language langTrue : Language.values()) {
                if (langTrue.name().equals("AUTO_DETECT"));
                else if (first) {
                    langs += langTrue.name();
                    first = false;
                }
                else
                    langs += "&6, &f" + langTrue.name();

            }

            langs = Translator.formatColor(Translator.replaceColor(langs));
            sender.sendMessage(TextComponent.fromLegacyText(langs));
        }
    }
}
