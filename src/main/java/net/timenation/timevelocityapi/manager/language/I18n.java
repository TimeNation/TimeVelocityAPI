package net.timenation.timevelocityapi.manager.language;

import com.velocitypowered.api.proxy.Player;
import net.timenation.timevelocityapi.TimeVelocityAPI;

import java.text.MessageFormat;

public class I18n {

    public static String format(Player player, String translateKey, Object... arguments) {
        try {
            translateKey = new ConfigManager(TimeVelocityAPI.getInstance().getTimePlayerManager().getTimePlayer(player).getLanguage()).getString(translateKey);
            MessageFormat messageFormat = new MessageFormat(translateKey);
            return messageFormat.format(arguments)
                    .replace("§7", "<gray>")
                    .replace("§8", "<dark_gray>")
                    .replace("§c" ,"<red>")
                    .replace("§4", "<dark_red>")
                    .replace("§e", "<yellow>")
                    .replace("§a", "<green>")
                    .replace("§6", "<gold>")
                    .replace("§9", "<blue>")
                    .replace("§2", "<dark_green>")
                    .replace("§6", "<purple>")
                    .replace("§b", "<aqua>")
                    .replace("§d", "<light_purple>")
                    .replace("§f", "<white>")
                    .replace("§l", "<bold>")
                    .replace("§p", "</bold>");
        } catch (NullPointerException exception) {
            // exception.printStackTrace();
        }
        return translateKey;
    }

    public static String format(Player player, String prefix, String translateKey, Object... arguments) {
        try {
            translateKey = prefix + new ConfigManager(TimeVelocityAPI.getInstance().getTimePlayerManager().getTimePlayer(player).getLanguage()).getString(translateKey);
            MessageFormat messageFormat = new MessageFormat(translateKey);
            return messageFormat.format(arguments)
                    .replace("§7", "<gray>")
                    .replace("§8", "<dark_gray>")
                    .replace("§c" ,"<red>")
                    .replace("§4", "<dark_red>")
                    .replace("§e", "<yellow>")
                    .replace("§a", "<green>")
                    .replace("§6", "<gold>")
                    .replace("§9", "<blue>")
                    .replace("§2", "<dark_green>")
                    .replace("§6", "<purple>")
                    .replace("§b", "<aqua>")
                    .replace("§d", "<light_purple>")
                    .replace("§f", "<white>")
                    .replace("§l", "<bold>")
                    .replace("§p", "</bold>");
        } catch (NullPointerException exception) {
            // exception.printStackTrace();
        }
        return translateKey;
    }

    public static String format(Player player, String prefix, String translateKey) {
        try {
            translateKey = prefix + new ConfigManager(TimeVelocityAPI.getInstance().getTimePlayerManager().getTimePlayer(player).getLanguage()).getString(translateKey)
                    .replace("§7", "<gray>")
                    .replace("§8", "<dark_gray>")
                    .replace("§c" ,"<red>")
                    .replace("§4", "<dark_red>")
                    .replace("§e", "<yellow>")
                    .replace("§a", "<green>")
                    .replace("§6", "<gold>")
                    .replace("§9", "<blue>")
                    .replace("§2", "<dark_green>")
                    .replace("§6", "<purple>")
                    .replace("§b", "<aqua>")
                    .replace("§d", "<light_purple>")
                    .replace("§f", "<white>")
                    .replace("§l", "<bold>")
                    .replace("§p", "</bold>");
        } catch (NullPointerException exception) {
            // exception.printStackTrace();
        }
        return translateKey;
    }

    public static String formatTablist(Player player, String translateKey, Object... arguments) {
        try {
            translateKey = new ConfigManager(TimeVelocityAPI.getInstance().getTimePlayerManager().getTimePlayer(player).getLanguage()).getString(translateKey);
            MessageFormat messageFormat = new MessageFormat(translateKey);
            return messageFormat.format(arguments)
                    .replace("§7", "<gray>")
                    .replace("§8", "<dark_gray>")
                    .replace("§c" ,"<red>")
                    .replace("§4", "<dark_red>")
                    .replace("§e", "<yellow>")
                    .replace("§a", "<green>")
                    .replace("§6", "<gold>")
                    .replace("§9", "<blue>")
                    .replace("§2", "<dark_green>")
                    .replace("§6", "<purple>")
                    .replace("§b", "<aqua>")
                    .replace("§d", "<light_purple>")
                    .replace("§f", "<white>")
                    .replace("§l", "<bold>")
                    .replace("§p", "</bold>");
        } catch (NullPointerException exception) {
            // exception.printStackTrace();
        }
        return translateKey;
    }
}