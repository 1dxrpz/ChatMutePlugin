package com.chillcraft.chatmute.chatmute;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class ChatMute extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("[ChatMute] Enabling plugin v1.0");
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists())
        {

            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }

        Bukkit.getPluginManager().registerEvents(new Logger(this), this);
        Objects.requireNonNull(getCommand("mute")).setExecutor(new Mute(this));
        Objects.requireNonNull(getCommand("unmute")).setExecutor(new Unmute(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
