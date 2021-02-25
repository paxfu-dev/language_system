package at.paxfu.velo.inventories;

import at.paxfu.velo.LanguageSystem;
import at.paxfu.velo.api.LanguageAPI;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class inventoryClick implements Listener {

    public static final String PREFIX = LanguageSystem.config.getString("system_prefix").replace('&', '§');

    @EventHandler
    public void handleClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(event.getInventory().getName().equalsIgnoreCase(selectInventory.INVENTORY_TITLE)) {
            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aEnglish")) {
                player.sendMessage(PREFIX + "You chose §aEnglish §7as Language!");
                event.setCancelled(true);
                player.getOpenInventory().close();
                LanguageAPI.setLanguage(player.getUniqueId(), "en_EN");

            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aDeutsch")) {
                player.sendMessage(PREFIX + "Du hast §aDeutsch §7als Sprache ausgewählt!");
                event.setCancelled(true);
                player.getOpenInventory().close();
                LanguageAPI.setLanguage(player.getUniqueId(), "de_DE");

            }else {
                event.setCancelled(true);
            }
        }
    }
}
