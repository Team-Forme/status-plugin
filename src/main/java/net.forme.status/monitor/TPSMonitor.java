package net.forme.status.monitor;

import org.bukkit.Bukkit;

public class TPSMonitor {

    public double getTPS() {
        return Bukkit.getServer().getTPS()[0];
    }

    public int getOnline() {
        return Bukkit.getOnlinePlayers().size();
    }

    public int getMax() {
        return Bukkit.getMaxPlayers();
    }
}
