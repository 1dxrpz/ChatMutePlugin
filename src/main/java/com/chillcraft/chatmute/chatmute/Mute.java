package com.chillcraft.chatmute.chatmute;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

public class Mute implements CommandExecutor {
    ChatMute plugin;
    ConfigParser parser;

    public Mute(ChatMute p) {
        plugin = p;
        parser = new ConfigParser();
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission(plugin.getConfig().getString("permission")) || sender.isOp())
        {
            if (args.length < 2)
                return false;
            Player executor = (Player) sender;
            Player player = Bukkit.getPlayer(args[0]);

            MuteDataModel _props = new MuteDataModel(executor.getName(), player.getName(), args[1], args[2]);
            ConfigParser _parser = parser.CreateParserProps(_props);

            if (plugin.getConfig().getBoolean("enablePublicAnnouncement"))
                Bukkit.getServer().getOnlinePlayers().forEach(p -> {
                    if (p != executor)
                        plugin.getConfig().getStringList("infostrings.playerMutedInfo")
                            .forEach(v -> {
                                String _playerInfo = _parser
                                        .Parse(v);
                                player.sendMessage(_playerInfo);
                            });
                });
            plugin.getConfig().getStringList("infostrings.playerMuteAnnounce")
                    .forEach(v -> {
                        String _playerInfo = _parser
                                .Parse(v);
                        player.sendMessage(_playerInfo);
                    });

            plugin.getConfig().getStringList("infostrings.playerMutedInfo")
                    .forEach(v -> {
                        String _executorInfo = _parser
                                .Parse(v);
                        executor.sendMessage(_executorInfo);
                    });

            _props.executionDate = System.currentTimeMillis() / 1000L;
            _props.expirationDate = _props.executionDate + _parser.AmountUnix();

            _props.IsMuted = true;
            FixedMetadataValue _data = new FixedMetadataValue(plugin, _props);
            player.setMetadata("mutedata", _data);

            return true;
        }
        return false;
    }
}
