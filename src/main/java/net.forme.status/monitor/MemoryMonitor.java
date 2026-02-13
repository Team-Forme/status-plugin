package net.forme.status.monitor;

public class MemoryMonitor {

    public String getMemoryUsage() {
        long used = (Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory()) / 1024 / 1024;
        long max = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        return used + "MB / " + max + "MB";
    }
}
