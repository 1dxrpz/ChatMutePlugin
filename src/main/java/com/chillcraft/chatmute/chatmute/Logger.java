package com.chillcraft.chatmute.chatmute;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class Logger implements Listener {
    ChatMute plugin;
    ConfigParser parser;

    public Logger(ChatMute p) {
        plugin = p;
        parser = new ConfigParser();
    }

    @EventHandler
    public void Join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("mutedata")){
            MuteDataModel _model = new MuteDataModel();
            FixedMetadataValue _data = new FixedMetadataValue(plugin, _model);
            player.setMetadata("mutedata", _data);
        }
    }
    @EventHandler
    public void Message(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        MuteDataModel _props = (MuteDataModel)player.getMetadata("mutedata").get(0).value();
        ConfigParser _parser = parser
                .CreateParserProps(_props);
        if (_props.IsMuted){
            _parser.model.currentDate = System.currentTimeMillis() / 1000L;

            if (_parser.model.expirationDate - _parser.model.currentDate <= 0)
            {
                _props.IsMuted = false;
                FixedMetadataValue _data = new FixedMetadataValue(plugin, _props);
                player.setMetadata("mutedata", _data);
            } else
            if (plugin.getConfig().getBoolean("announcement.enableMuteAnnounce"))
                plugin.getConfig().getStringList("announcement.muteAnnounce")
                        .forEach(v -> {
                            String _muteAnnounce = _parser
                                    .Parse(v);
                            player.sendMessage(_muteAnnounce);
                        });
        }
        event.setCancelled(_props.IsMuted);
    }

}
