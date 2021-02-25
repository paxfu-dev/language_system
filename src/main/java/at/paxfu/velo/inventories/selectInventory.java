package at.paxfu.velo.inventories;

import at.paxfu.velo.LanguageSystem;
import at.paxfu.velo.managers.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static at.paxfu.velo.managers.createItem.createItem;

public class selectInventory {

    public static final int INVENTORY_SIZE = LanguageSystem.config.getInt("inventory_size");
    public static final String INVENTORY_TITLE = LanguageSystem.config.getString("inventory_title").replace('&', '§');

    public static void setInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, INVENTORY_SIZE, INVENTORY_TITLE);

        ItemStack langEN = new ItemBuilder(Material.SKULL_ITEM).setCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTllZGNkZDdiMDYxNzNkN2QyMjFjNzI3NGM4NmNiYTM1NzMwMTcwNzg4YmI2YTFkYjA5Y2M2ODEwNDM1YjkyYyJ9fX0=").setName("§aEnglish").build();
        ItemStack langDE = new ItemBuilder(Material.SKULL_ITEM).setCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWU3ODk5YjQ4MDY4NTg2OTdlMjgzZjA4NGQ5MTczZmU0ODc4ODY0NTM3NzQ2MjZiMjRiZDhjZmVjYzc3YjNmIn19fQ==").setName("§aDeutsch").build();

        inventory.setItem(0, langEN);
        inventory.setItem(1, langDE);

        player.openInventory(inventory);
    }



    public static void fillInv(Inventory inv){
        ItemStack glasspane = createItem(Material.STAINED_GLASS_PANE, true, (short) 7, " ", false, "");
        ItemStack lightglasspane = createItem(Material.STAINED_GLASS_PANE, true, (short) 3, " ", false, "");
        for(int slot = 0; slot < inv.getSize(); slot++){
            inv.setItem(slot, glasspane);
        }
    }
}
