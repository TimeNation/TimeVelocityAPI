package net.timenation.timevelocityapi.manager.player;

import com.google.gson.JsonObject;
import com.velocitypowered.api.proxy.Player;
import net.timenation.timevelocityapi.TimeVelocityAPI;
import net.timenation.timevelocityapi.timeplayer.TimePlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TimePlayerManager {

    private final Map<Player, TimePlayer> playerCache;

    public TimePlayerManager() {
        this.playerCache = new HashMap<>();
    }

    public TimePlayer getTimePlayer(Player player) {
        if (playerCache.containsKey(player)) {
            return playerCache.get(player);
        }

        ResultSet resultSet = TimeVelocityAPI.getInstance().getMySQL().getDatabaseResult("SELECT * FROM playerData WHERE playerUuid='" + player.getUniqueId() + "'");

        try {
            if (resultSet.next()) {
                TimePlayer timePlayer = new TimePlayer();

                JsonObject jsonObject = TimeVelocityAPI.getInstance().getRequestManager().getHttpResponse(player.getUniqueId());
                timePlayer.setPlayerUuid(player.getUniqueId().toString());
                timePlayer.setPlayerName(player.getUsername());
                timePlayer.setLanguage(jsonObject.get("playerData").getAsJsonObject().get("language").getAsString());
                timePlayer.setCrystals(jsonObject.get("playerData").getAsJsonObject().get("crystals").getAsInt());
                timePlayer.setLootboxes(jsonObject.get("playerData").getAsJsonObject().get("lootboxes").getAsInt());
                timePlayer.setNickTool(false);

                playerCache.put(player, timePlayer);
                return timePlayer;
            }

            TimePlayer timePlayer = new TimePlayer();
            timePlayer.setPlayerUuid(player.getUniqueId().toString());
            timePlayer.setPlayerName(player.getUsername());
            timePlayer.setLanguage("de");
            timePlayer.setCrystals(100);
            timePlayer.setLootboxes(0);
            timePlayer.setNickTool(false);

            playerCache.put(player, timePlayer);
            return timePlayer;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
