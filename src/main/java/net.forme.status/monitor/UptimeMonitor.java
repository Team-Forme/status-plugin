package net.forme.status.monitor;

import java.lang.management.ManagementFactory;

public class UptimeMonitor {

    public String getUptime() {
        long ms = ManagementFactory.getRuntimeMXBean().getUptime();
        long sec = ms / 1000;
        long h = sec / 3600;
        long m = (sec % 3600) / 60;
        return h + "h " + m + "m";
    }
}
