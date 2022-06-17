package net.timenation.timevelocityapi.manager;

import com.velocitypowered.api.proxy.Player;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.ICloudPlayer;
import eu.thesimplecloud.api.service.ICloudService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CloudManager {

    public int getPlayerCount(String group) {
        return CloudAPI.getInstance().getCloudServiceGroupManager().getServiceGroupByName(group).getOnlinePlayerCount();

    }

    public int getPlayerCountFromServer(String server) {
        int players = CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName(server).getOnlineCount();
        return players;
    }

    public void sendPlayerToCloudService(String service, Player player) {
        ICloudService iCloudService = CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName(service);
        ICloudPlayer iCloudPlayer = CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(player.getUsername());

        iCloudPlayer.connect(iCloudService);
    }

    public void startCloudServer(String cloudServerGroup) {
        CloudAPI.getInstance().getCloudServiceGroupManager().getServiceGroupByName(cloudServerGroup).startNewService();
    }

    public void stopCloudServer(String cloudServer) {
        CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName(cloudServer).shutdown();
    }

    public String getOnlineTimeinDays(ICloudPlayer iCloudPlayer) {
        long ms = iCloudPlayer.getOnlineTime();
        return String.format("%d", TimeUnit.MILLISECONDS.toDays(ms));
    }

    public String getOnlineTimeinHour(ICloudPlayer iCloudPlayer) {
        long ms = iCloudPlayer.getOnlineTime();
        return String.format("%d", TimeUnit.MILLISECONDS.toHours(ms));
    }

    public String getOnlineTimeinMinutes(ICloudPlayer iCloudPlayer) {
        long ms = iCloudPlayer.getOnlineTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm");
        Date date = new Date(ms);
        return simpleDateFormat.format(date);
    }
}
