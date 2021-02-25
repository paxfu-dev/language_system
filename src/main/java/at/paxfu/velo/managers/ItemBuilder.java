package at.paxfu.velo.managers;

import com.google.gson.internal.LinkedTreeMap;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.lang.reflect.Field;
import java.util.*;

public class ItemBuilder {

    private ItemStack item;

    private byte metadata = -1;
    private Potion potion = null;

    public ItemBuilder(Material material) {
        item = new ItemStack(material, 1);
    }

    public ItemBuilder(Material material, int amount) {
        item = new ItemStack(material, amount);
    }

    public ItemBuilder(int id) {
        item = new ItemStack(id);
    }

    public ItemBuilder(int id, int amount) {
        item = new ItemStack(id, amount);
    }

    public ItemBuilder(int id, int amount, int data) {
        item = new ItemStack(id, amount, (short) data);
    }

    public ItemBuilder(Material material, int amount, int data) {
        item = new ItemStack(material, amount, (short) data);
    }

    public ItemBuilder(ItemStack item) {
        this.item = item;
    }

    public ItemBuilder setData(int data) {
        item.setDurability((short) data);
        return this;
    }

    public ItemBuilder setMaterial(Material m) {
        item.setType(m);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder setName(String name) {
        ItemMeta m = item.getItemMeta();
        m.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(m);
        return this;
    }

    public ItemBuilder setName() {
        ItemMeta m = item.getItemMeta();
        m.setDisplayName(" ");
        item.setItemMeta(m);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta m = item.getItemMeta();
        m.setLore(lore);
        item.setItemMeta(m);
        return this;
    }

    public ItemBuilder setMetadata(byte metadata) {
        this.metadata = metadata;
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        ItemMeta m = item.getItemMeta();
        m.setLore(Arrays.asList(lore));
        item.setItemMeta(m);
        return this;
    }


    public ItemBuilder setGlowing() {
        enchant(Enchantment.ARROW_INFINITE, 1);
        addFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemBuilder setGlowing(boolean b) {
        if (b) {
            enchant(Enchantment.ARROW_INFINITE, 1);
            addFlags(ItemFlag.HIDE_ENCHANTS);
            return this;
        } else{
            item.removeEnchantment(Enchantment.ARROW_INFINITE);
            return this;
        }
    }

    public ItemBuilder enchant(Enchantment ench, int lvl) {
        item.addUnsafeEnchantment(ench, lvl);
        return this;
    }

    public ItemBuilder addFlags(ItemFlag... flag) {
        ItemMeta m = item.getItemMeta();
        m.addItemFlags(flag);
        item.setItemMeta(m);
        return this;
    }

    /* DECO ITEM */
    public ItemStack setDeco(int data) {
        setData(data);
        setName();
        return item;
    }

    public ItemStack setDeco(long data) {
        setData((int) data);
        setName();
        return item;
    }

    public ItemBuilder setLeatherColor(Color color) {
        LeatherArmorMeta m = (LeatherArmorMeta) item.getItemMeta();
        m.setColor(color);
        item.setItemMeta(m);
        return this;
    }

    public ItemBuilder setSkullOwner(String owner) {
        setData(3);
        SkullMeta m = (SkullMeta) item.getItemMeta();
        m.setOwner(owner);
        item.setItemMeta(m);
        return this;
    }

    public ItemBuilder setCustomSkull(String value) {
        setData(3);
        if (value.isEmpty()) return this;
        SkullMeta m = (SkullMeta) item.getItemMeta();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
        gameProfile.getProperties().put("textures", new Property("textures", value));
        try {
            Field profileField = m.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(m, gameProfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        item.setItemMeta(m);
        return this;
    }

    public ItemBuilder setPotionType(PotionEffectType type) {
        PotionMeta m = (PotionMeta) item.getItemMeta();
        m.setMainEffect(type);
        item.setItemMeta(m);
        return this;
    }

    public ItemBuilder setSplashPotion(PotionType type, int level, boolean extend) {
        Potion pot = new Potion(1);
        pot.setSplash(true);
        pot.setType(type);
        pot.setLevel(level);
        pot.setHasExtendedDuration(extend);
        this.potion = pot;
        return this;
    }

    public ItemBuilder setBookAuthor(String author) {
        BookMeta m = (BookMeta) item.getItemMeta();
        m.setAuthor(author);
        item.setItemMeta(m);
        return this;
    }

    public ItemBuilder setBookContent(String... pages) {
        BookMeta m = (BookMeta) item.getItemMeta();
        m.setPages(pages);
        item.setItemMeta(m);
        return this;
    }

    public ItemBuilder setBookTitle(String title) {
        BookMeta m = (BookMeta) item.getItemMeta();
        m.setTitle(title);
        item.setItemMeta(m);
        return this;
    }

    public ItemBuilder setBookMeta(String title, String author, String... pages) {
        BookMeta m = (BookMeta) item.getItemMeta();
        m.setTitle(title);
        m.setAuthor(author);
        m.setPages(pages);
        item.setItemMeta(m);
        return this;
    }

    public ItemBuilder setEggType(EntityType type) {
        if ((item != null) && (item.getType() == Material.MONSTER_EGG) && (type != null) && (type.getName() != null)) {
            try {
                String version = Bukkit.getServer().getClass().toString().split("\\.")[3];
                Class<?> craftItemStack = Class.forName("org.bukkit.craftbukkit." + version + ".inventory.CraftItemStack");
                Object nmsItemStack = craftItemStack.getDeclaredMethod("asNMSCopy", ItemStack.class).invoke(null, item);
                Object nbtTagCompound = Class.forName("net.minecraft.server." + version + ".NBTTagCompound").newInstance();
                Field nbtTagCompoundField = nmsItemStack.getClass().getDeclaredField("tag");
                nbtTagCompoundField.setAccessible(true);
                nbtTagCompound.getClass().getMethod("setString", String.class, String.class).invoke(nbtTagCompound, "id", type.getName());
                nbtTagCompound.getClass().getMethod("set", String.class, Class.forName("net.minecraft.server." + version + ".NBTBase")).invoke(nbtTagCompoundField.get(nmsItemStack), "EntityTag", nbtTagCompound);
                item = (ItemStack) craftItemStack.getDeclaredMethod("asCraftMirror", nmsItemStack.getClass()).invoke(null, nmsItemStack);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return this;
    }

    public ItemStack build() {
        if (potion != null) potion.apply(item);
        return item;
    }

    public static LinkedTreeMap<String, Object> serialize(ItemStack item) {
        LinkedTreeMap<String, Object> result = new LinkedTreeMap<>();
        result.put("type", item.getType().getId());
        if (item.getDurability() != 0) {
            result.put("durability", item.getDurability());
        }
        result.put("amount", item.getAmount());
        ItemMeta meta = item.getItemMeta();
        if (!Bukkit.getItemFactory().equals(meta, (ItemMeta) null)) {
            if (meta.getEnchants().size() != 0) {
                HashMap<String, Object> map1 = new HashMap<>();
                meta.getEnchants().forEach((enchantment, level) -> {
                    map1.put(enchantment.getId() + "", level);
                });
                //Enchantment.getById()
                result.put("enchantments", map1);
                meta.getEnchants().forEach((enchantment, integer) -> {
                    meta.removeEnchant(enchantment);
                });
            }
            result.put("meta", meta);
        }
        return result;
    }

    public static ItemStack deserialize(Map<String, Object> args) {
        Material type = Material.getMaterial(Integer.parseInt(args.get("type").toString().split("\\.")[0]));
        short durability = 0;
        int amount = 1;
        if (args.containsKey("durability")) {
            durability = ((Number) args.get("durability")).shortValue();
        }

        if (args.containsKey("amount")) {
            amount = ((Number) args.get("amount")).intValue();
        }

        ItemStack result = new ItemStack(type, amount, durability);
        Object raw;
        if (args.containsKey("enchantments")) {
            LinkedTreeMap<String, Object> map = (LinkedTreeMap<String, Object>) args.get("enchantments");
            map.forEach((str, level) -> {
                int id;
                if (str.contains(".")) {
                    id = Integer.parseInt(str.split("\\.")[0]);
                } else {
                    id = Integer.parseInt(str);
                }
                int lvl;
                if (level.toString().contains(".")) {
                    lvl = Integer.parseInt(level.toString().split("\\.")[0]);
                } else {
                    lvl = Integer.parseInt(level.toString());
                }
                Enchantment enchantment = Enchantment.getById(id);
                if (enchantment != null) {
                    result.addUnsafeEnchantment(enchantment, lvl);
                }
            });
        }
        if (args.containsKey("meta")) {
            LinkedTreeMap<String, Object> map = (LinkedTreeMap<String, Object>) args.get("meta");
            String color = null;
            if (map.containsKey("color")) {
                color = map.get("color").toString();
                map.remove("color");
            }
            args.put("meta", map);
            raw = args.get("meta");
            if (raw instanceof ItemMeta) {
                result.setItemMeta((ItemMeta) raw);
            }else{
                if (raw!=null){
                    LinkedTreeMap<String, Object> metaMap = (LinkedTreeMap<String, Object>) raw;
                    ItemMeta itemMeta = result.getItemMeta();
                    if (metaMap.get("displayName")!=null){
                        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',metaMap.get("displayName").toString()));
                    }
                    result.setItemMeta(itemMeta);
                }
            }
            if (color != null) {
                if (color.startsWith("#")) {
                    color = "0x" + color.substring(1, Math.min(color.length(), 7));
                }
                LeatherArmorMeta m = (LeatherArmorMeta) result.getItemMeta();
                m.setColor(hex2Rgb(color));
                result.setItemMeta(m);
            }
        }
        return result;
    }


    public static Color hex2Rgb(String colorStr) {
        java.awt.Color color = java.awt.Color.decode(colorStr);
        return Color.fromRGB(color.getRed(), color.getGreen(), color.getBlue());
    }
}