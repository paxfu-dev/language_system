package at.paxfu.velo;

import at.paxfu.velo.commands.languageCMD;
import at.paxfu.velo.inventories.inventoryClick;
import at.paxfu.velo.listeners.joinEvent;
import at.paxfu.velo.utils.MySQLCreate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class LanguageSystem extends JavaPlugin {

    public static File configf, de_DEf, en_ENf, mysqlf;
    public static FileConfiguration config, de_DE, en_EN, mysql;

    @Override
    public void onEnable() {
        instance = this;
        createFiles();
        registerEvents();
        registerCommands();
        MySQLCreate.connect();
        MySQLCreate.createTable();

        Bukkit.getConsoleSender().sendMessage("§8-----------------------------------");
        Bukkit.getConsoleSender().sendMessage("§6VELO-CODE LanguageSystem v.1.0");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§8Creator VELO-CODE");
        Bukkit.getConsoleSender().sendMessage("§8All Rights Reserved");
        Bukkit.getConsoleSender().sendMessage("§8Creator: paxfu");
        Bukkit.getConsoleSender().sendMessage("§8-----------------------------------");
    }



    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new joinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new inventoryClick(), this);
    }

    private void registerCommands() {
        getCommand("language").setExecutor(new languageCMD());
    }


    private void createFiles() {
        configf = new File(getDataFolder(), "config.yml");
        de_DEf = new File(getDataFolder(), "de_DE.yml");
        en_ENf = new File(getDataFolder(), "en_EN.yml");
        mysqlf = new File(getDataFolder(), "mysql.yml");

        if(!configf.exists()) {
            configf.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        if(!de_DEf.exists()) {
            de_DEf.getParentFile().mkdirs();
            saveResource("de_DE.yml", false);
        }

        if(!en_ENf.exists()) {
            en_ENf.getParentFile().mkdirs();
            saveResource("en_EN.yml", false);
        }

        if(!mysqlf.exists()) {
            mysqlf.getParentFile().mkdirs();
            saveResource("mysql.yml", false);
        }

        config = new YamlConfiguration();
        de_DE = new YamlConfiguration();
        en_EN = new YamlConfiguration();
        mysql = new YamlConfiguration();

        try {
            config.load(configf);
            de_DE.load(de_DEf);
            en_EN.load(en_ENf);
            mysql.load(mysqlf);
        }catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
    }



    public static LanguageSystem instance;

    public static LanguageSystem getInstance() {
        return instance;
    }
}
