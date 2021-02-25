package at.paxfu.velo.managers;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class createItem {

    public static ItemStack createItem(final Material material, final boolean addcolor, final short colorid, final String name, final boolean enchant, final String... lore){
        final ItemStack item;
        if (addcolor){
            item = new ItemStack(material, 1, colorid);
        }else {
            item = new ItemStack(material, 1);
        }
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));

        if (enchant){
            meta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }

        item.setItemMeta(meta);

        return item;
    }
}
