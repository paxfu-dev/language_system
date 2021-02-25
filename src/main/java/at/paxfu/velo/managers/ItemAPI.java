package at.paxfu.velo.managers;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemAPI {

    ItemStack itemStack;
    ItemMeta itemMeta;

    public ItemAPI(String displayname, Material material, byte subid, int amount) {
        itemStack = new ItemStack(material, amount, subid);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayname);
    }
    public ItemAPI(String displayname, Material material, byte subid, int amount, Enchantment enchantment) {
        itemStack = new ItemStack(material, amount, subid);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayname);
        itemMeta.addEnchant(enchantment, 10, true);
    }



    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }


}
