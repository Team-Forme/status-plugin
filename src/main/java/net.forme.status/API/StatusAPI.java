package net.forme.status.api;

import com.sun.net.httpserver.HttpServer;
import net.forme.status.FormeStatus;
import net.forme.status.monitor.TPSMonitor;
import org.bukkit.Bukkit;

import java.io.OutputStream;
import java.net.InetSocketAddress;

public class StatusAPI {

    private final FormeStatus plugin;

    public StatusAPI(FormeStatus plugin) {
        this.plugin = plugin;
    }

    public void start() {
        try {
            int port = plugin.getConfig().getInt("api.port");
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

            server.createContext("/status", exchange -> {
                TPSMonitor monitor = plugin.getServer()
                        .getServicesManager()
                        .load(TPSMonitor.class);

                double tps = Bukkit.getServer().getTPS()[0];

                String json = "{ \"tps\": " + tps +
                        ", \"online\": " + Bukkit.getOnlinePlayers().size() +
                        " }";

                exchange.sendResponseHeaders(200, json.length());
                OutputStream os = exchange.getResponseBody();
                os.write(json.getBytes());
                os.close();
            });

            server.start();
            plugin.getLogger().info("Status API started on port " + port);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
