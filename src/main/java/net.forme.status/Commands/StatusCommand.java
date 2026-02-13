package net.forme.status.commands;

import net.forme.status.monitor.MemoryMonitor;
import net.forme.status.monitor.TPSMonitor;
import net.forme.status.monitor.UptimeMonitor;
import org.bukkit.ChatColor;
import org.bukkit.command.*;

public class StatusCommand implements CommandExecutor {

    private final TPSMonitor tps;
    private final MemoryMonitor mem;
    private final UptimeMonitor uptime;

    public StatusCommand(TPSMonitor tps,
                         MemoryMonitor mem,
                         UptimeMonitor uptime) {
        this.tps = tps;
        this.mem = mem;
        this.uptime = uptime;
    }

    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        sender.sendMessage(ChatColor.GRAY + "====================");
        sender.sendMessage(ChatColor.GREEN + "Forme Server Status");
        sender.sendMessage("TPS: " + String.format("%.2f", tps.getTPS()));
        sender.sendMessage("Online: " + tps.getOnline() + "/" + tps.getMax());
        sender.sendMessage("Memory: " + mem.getMemoryUsage());
        sender.sendMessage("Uptime: " + uptime.getUptime());
        sender.sendMessage(ChatColor.GRAY + "====================");

        return true;
    }
}
