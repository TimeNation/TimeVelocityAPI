package net.timenation.timevelocityapi.manager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import eu.thesimplecloud.clientserverapi.lib.promise.ICommunicationPromise;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import lombok.SneakyThrows;
import net.timenation.timevelocityapi.TimeVelocityAPI;

import java.io.File;
import java.io.FileReader;
import java.util.UUID;

public class RankManager {

    private final File[] files;
    private final Gson gson;

    public RankManager() {
        this.files = new File("ranks").listFiles();
        this.gson = new Gson();
    }

    @SneakyThrows
    public Rank getPlayersRank(UUID uuid) {
        ICommunicationPromise<IPermissionPlayer> iPermissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getPermissionPlayer(uuid);

        for (File file : this.files) {
            if (file.isDirectory()) continue;
            if (!file.getName().endsWith(".json")) continue;

            if (file.getName().equalsIgnoreCase(iPermissionPlayer.getBlockingOrNull().getHighestPermissionGroup().getName() + ".json")) {
                JsonObject jsonObject = new JsonParser().parse(new FileReader(file)).getAsJsonObject();
                return gson.fromJson(jsonObject, Rank.class);
            }
        }

        return null;
    }

    public static class Rank {
        private String rankName;
        private String rankPrefix;
        private String rankColor;

        public Rank() {
        }

        public String getRankName() {
            return rankColor + rankName;
        }

        public String getRankPrefix() {
            return rankPrefix;
        }

        public String getRankColor() {
            return rankColor;
        }

        public String getPlayersRankAndName(UUID uuid) {
            return TimeVelocityAPI.getInstance().getTimePlayerManager().getTimePlayer(TimeVelocityAPI.getInstance().getProxyServer().getPlayer(uuid).get()).isNicked() ? rankPrefix + TimeVelocityAPI.getInstance().getTimePlayerManager().getTimePlayer(TimeVelocityAPI.getInstance().getProxyServer().getPlayer(uuid).get()).getPlayerNickName() : rankPrefix + TimeVelocityAPI.getInstance().getUuidFetcher().getName(uuid);
        }

        public String getPlayersNameWithRankColor(UUID uuid) {
            if (TimeVelocityAPI.getInstance().getProxyServer().getPlayer(uuid).get() != null)
                return TimeVelocityAPI.getInstance().getTimePlayerManager().getTimePlayer(TimeVelocityAPI.getInstance().getProxyServer().getPlayer(uuid).get()).isNicked() ? rankColor + TimeVelocityAPI.getInstance().getTimePlayerManager().getTimePlayer(TimeVelocityAPI.getInstance().getProxyServer().getPlayer(uuid).get()).getPlayerNickName() : rankColor + TimeVelocityAPI.getInstance().getUuidFetcher().getName(uuid);
            return "§cERROR";
        }
    }
}