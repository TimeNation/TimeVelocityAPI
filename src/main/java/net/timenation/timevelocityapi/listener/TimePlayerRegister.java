package net.timenation.timevelocityapi.listener;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.PostLoginEvent;
import net.kyori.adventure.text.Component;
import net.timenation.timevelocityapi.TimeVelocityAPI;
import net.timenation.timevelocityapi.manager.language.I18n;
import net.timenation.timevelocityapi.utils.Components;

public class TimePlayerRegister {

    @Subscribe(order = PostOrder.FIRST)
    public void handlePlayerJoin(PostLoginEvent event) {
        TimeVelocityAPI.getInstance().getTimePlayerManager().getTimePlayer(event.getPlayer());

        if (event.getPlayer().getProtocolVersion().getProtocol() < 754) {
            event.getPlayer().disconnect(Components.parse(I18n.format(event.getPlayer(), "api.velocity.outdatedclientversion")));
        }
    }
}
