package net.timenation.timevelocityapi.timeplayer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimePlayer {

    private String playerName;
    private String playerUuid;
    private String playerNickName;
    private String language;
    private int crystals;
    private int lootboxes;
    private boolean nickTool;
    private boolean isNicked;
}
