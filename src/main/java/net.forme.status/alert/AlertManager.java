package net.forme.status.alert;

import net.forme.status.FormeStatus;
import net.forme.status.monitor.TPSMonitor;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class AlertManager {

    private final FormeStatus plugin;
    private final TPSMonitor monitor;

    public AlertManager(FormeStatus plugin, TPSMonitor monitor) {
        this.plugin = plugin;
        this.monitor = monitor;
    }

    public void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!plugin.getConfig().getBoolean("alert.enabled")) return;

                double tps = monitor.getTPS();
                double danger = plugin.getConfig().getDouble("tps.danger");

                if (tps <= danger) {
                    Bukkit.broadcastMessage("§c[Alert] TPS Low: " + String.format("%.2f", tps));
                }
            }
        }.runTaskTimer(plugin, 0L, 200L);
    }
}
