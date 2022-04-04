package com.chillcraft.chatmute.chatmute;

import org.bukkit.entity.Player;

public class MuteDataModel {
    public Boolean IsMuted = false;
    public long executionDate = 0;
    public long expirationDate = 0;
    public long currentDate = 0;

    public String player = "";
    public String executor = "";
    public String amount = "";
    public String reason = "";
    public MuteDataModel(){
        this("", "", "", "");
    }
    public MuteDataModel(String player, String executor, String amount, String reason) {
        this.player = player;
        this.executor = executor;
        this.amount = amount;
        this.reason = reason;
    }
}
