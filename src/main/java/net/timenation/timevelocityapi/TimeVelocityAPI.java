package net.timenation.timevelocityapi;

import com.google.inject.Inject;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import eu.thesimplecloud.api.CloudAPI;
import lombok.Getter;
import net.timenation.timevelocityapi.data.Logger;
import net.timenation.timevelocityapi.data.UUIDFetcher;
import net.timenation.timevelocityapi.manager.backend.RequestManager;
import net.timenation.timevelocityapi.listener.TimePlayerRegister;
import net.timenation.timevelocityapi.manager.CloudManager;
import net.timenation.timevelocityapi.manager.NotificationManager;
import net.timenation.timevelocityapi.manager.RankManager;
import net.timenation.timevelocityapi.manager.language.I18n;
import net.timenation.timevelocityapi.manager.player.TimePlayerManager;
import net.timenation.timevelocityapi.mysql.MySQL;
import net.timenation.timevelocityapi.utils.RabbitMQ;

import java.io.IOException;

@Getter
public class TimeVelocityAPI {

    private static TimeVelocityAPI instance;
    private final ProxyServer proxyServer;
    private final Logger logger;
    private final MySQL mySQL;
    private final TimePlayerManager timePlayerManager;
    private final RequestManager requestManager;
    private final CloudManager cloudManager;
    private final RankManager rankManager;
    private final NotificationManager notificationManager;
    private final UUIDFetcher uuidFetcher;
    private final RabbitMQ rabbitMQ;

    @Inject
    public TimeVelocityAPI(ProxyServer proxyServer) {
        instance = this;
        this.proxyServer = proxyServer;
        this.logger = new Logger();
        this.mySQL = new MySQL("Storage");
        this.timePlayerManager = new TimePlayerManager();
        this.requestManager = new RequestManager();
        this.cloudManager = new CloudManager();
        this.rankManager = new RankManager();
        this.notificationManager = new NotificationManager();
        this.uuidFetcher = new UUIDFetcher();
        this.rabbitMQ = new RabbitMQ();
        try {
            this.rabbitMQ.sendMessageToRabbtiMQ(CloudAPI.getInstance().getCloudServiceGroupManager().getProxyGroupByName("Proxy").isInMaintenance() ? "minecraft_maintenance" : "minecraft_on");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Subscribe
    public void handleProxyInitialization(ProxyInitializeEvent event) {
        EventManager eventManager = proxyServer.getEventManager();

        eventManager.register(this, new TimePlayerRegister());
    }

    @Subscribe
    public void handleProxyShutdown(ProxyShutdownEvent event) {
        try {
            rabbitMQ.sendMessageToRabbtiMQ("minecraft_off");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static TimeVelocityAPI getInstance() {
        return instance;
    }

    public String getFriendPrefix(Player player) {
        return I18n.format(player, "api.velocity.prefix.friends");
    }
}
