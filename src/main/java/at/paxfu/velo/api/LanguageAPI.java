package at.paxfu.velo.api;

import at.paxfu.velo.utils.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class LanguageAPI {

    public static void createUser(UUID uuid, String name, String language) {
        if(!userPlayedBefore(uuid)) {
            ResultSet set = MySQL.getInstance().executeQuery("INSERT INTO `user_language`(`username`, `uuid`, `language`) VALUES (?,?,?)", new HashMap<Integer, String>(){
                {
                    put(1, name);
                    put(2, String.valueOf(uuid));
                    put(3, language);
                }
            });
        }

    }

    public static boolean userPlayedBefore(UUID uuid) {
        ResultSet set = MySQL.getInstance().executeQuery("SELECT * FROM `user_language` WHERE `uuid` = ?", new HashMap<Integer, String>(){
            {
                put(1, String.valueOf(uuid));
            }
        });
        try {
            if(set.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void setLanguage(UUID uuid, String lang) {
        ResultSet set = MySQL.getInstance().executeQuery("UPDATE `user_language` SET `language`=? WHERE `uuid` = ?", new HashMap<Integer, String>(){
            {
                put(1, lang);
                put(2, String.valueOf(uuid));
            }
        });
    }

    public static String getLanguage(UUID uuid) {
        ResultSet set = MySQL.getInstance().executeQuery("SELECT * FROM `user_language` WHERE `uuid` = ?", new HashMap<Integer, String>(){
            {
                put(1, String.valueOf(uuid));
            }
        });
        try {
            if(set.next()) {
                return set.getString("language");
            }else {
                return "en_EN";
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return "en_EN";
        }
    }
}
