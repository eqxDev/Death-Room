package me.eqxdev.deathroom.utils;

import me.eqxdev.deathroom.utils.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by eqxDev.
 */
public class RankUtil {

    private HashMap<String,Integer> ranks = new HashMap<>();
    private List<String> perms = new ArrayList<>();

    public void load() {
        ranks.clear();
        for (String key : ConfigManager.get("data.yml").getConfigurationSection("Ranks").getKeys(false)) {
            ranks.put(ConfigManager.get("data.yml").getString("Ranks." + key + ".permission"), ConfigManager.get("data.yml").getInt("Ranks." + key +".time"));
            perms.add(ConfigManager.get("data.yml").getString("Ranks." + key + ".permission"));
        }
    }

    public boolean hasPerm(Player p) {
        for(String str : perms) {
            if(p.hasPermission(str)) return true;
        }
        return false;
    }

    public int getTime(Player p) {
        for(String str : perms) {
            if(p.hasPermission(str)) {
                return ranks.get(str);
            }
        }
        return 0;
    }

}
