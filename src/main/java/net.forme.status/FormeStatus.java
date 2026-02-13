package net.forme.status;

import net.forme.status.alert.AlertManager;
import net.forme.status.api.StatusAPI;
import net.forme.status.commands.StatusCommand;
import net.forme.status.listeners.ServerPingListener;
import net.forme.status.monitor.*;
import net.forme.status.storage.SQLiteManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FormeStatus extends JavaPlugin {

    private TPSMonitor tpsMonitor;
    private MemoryMonitor memoryMonitor;
    private UptimeMonitor uptimeMonitor;
    private AlertManager alertManager;
    private SQLiteManager sqliteManager;
    private StatusAPI api;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        tpsMonitor = new TPSMonitor();
        memoryMonitor = new MemoryMonitor();
        uptimeMonitor = new UptimeMonitor();
        sqliteManager = new SQLiteManager(this);
        alertManager = new AlertManager(this, tpsMonitor);
        api = new StatusAPI(this);

        getCommand("status").setExecutor(
                new StatusCommand(tpsMonitor, memoryMonitor, uptimeMonitor));

        getServer().getPluginManager()
                .registerEvents(new ServerPingListener(this, tpsMonitor), this);

        alertManager.start();
        sqliteManager.init();

        if (getConfig().getBoolean("api.enabled")) {
            api.start();
        }

        getLogger().info("FormeStatus Enabled");
    }
}
