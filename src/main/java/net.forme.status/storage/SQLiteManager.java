package net.forme.status.storage;

import org.bukkit.plugin.Plugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLiteManager {

    private final Plugin plugin;

    public SQLiteManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void init() {
        try {
            File dbFile = new File(plugin.getDataFolder(), "stats.db");
            if (!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdirs();
            }

            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS stats (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP," +
                    "tps REAL," +
                    "online INTEGER" +
                    ");");

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
