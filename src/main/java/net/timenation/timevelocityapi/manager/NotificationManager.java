package net.timenation.timevelocityapi.manager;

import java.util.ArrayList;
import java.util.UUID;

public class NotificationManager {

    private final ArrayList<UUID> notificationList;

    public NotificationManager() {
        this.notificationList = new ArrayList<>();
    }

    public boolean hasNotificationEnabled(UUID uuid) {
        return notificationList.contains(uuid);
    }

    public void disableNotifications(UUID uuid) {
        notificationList.remove(uuid);
    }

    public void enableNotifications(UUID uuid) {
        notificationList.add(uuid);
    }
}
