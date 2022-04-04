package com.chillcraft.chatmute.chatmute;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

public class Unmute implements CommandExecutor {
    ChatMute plugin;
    ConfigParser parser;

    public Unmute(ChatMute p) {
        plugin = p;
        parser = new ConfigParser();
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission(plugin.getConfig().getString("permission")) || sender.isOp())
        {
            if (args.length != 1)
                return false;

            Player player = Bukkit.getPlayer(args[0]);

            if (player.hasMetadata("mutedata"))
            {
                MuteDataModel _props = (MuteDataModel)player.getMetadata("mutedata").get(0).value();
                _props.IsMuted = false;
                FixedMetadataValue _data = new FixedMetadataValue(plugin, _props);
                player.setMetadata("mutedata", _data);
            }
            return true;
        }
        return false;
    }
}
