package at.paxfu.velo.listeners;

import at.paxfu.velo.LanguageSystem;
import at.paxfu.velo.api.LanguageAPI;
import at.paxfu.velo.inventories.selectInventory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class joinTickScheduler {

    public static void start(Player player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(LanguageSystem.getInstance(), new Runnable() {
            @Override
            public void run() {
                selectInventory.setInventory(player);
            }
        }, 1L);
    }
}
