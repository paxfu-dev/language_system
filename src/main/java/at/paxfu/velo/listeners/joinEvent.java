package at.paxfu.velo.listeners;

import at.paxfu.velo.api.LanguageAPI;
import at.paxfu.velo.inventories.inventoryClick;
import at.paxfu.velo.inventories.selectInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class joinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();


        if(!LanguageAPI.userPlayedBefore(player.getUniqueId())) {
            joinTickScheduler.start(player);
        }
        LanguageAPI.createUser(player.getUniqueId(), player.getName(), "en_EN");


    }
}
