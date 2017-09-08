package me.eqxdev.deathroom.utils.managers;

import me.eqxdev.deathroom.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by eqxDev.
 */
public class LivesManager implements Listener {

    private Map<UUID, Integer> lives = new HashMap<>();

    public Map<UUID, Integer> getLives() {
        return lives;
    }

    public void load() {
        try {
            for (String key : ConfigManager.get("users.dat").getConfigurationSection("Lives").getKeys(false)) {
                lives.put(UUID.fromString(key), ConfigManager.get("users.dat").getInt("Lives." + key));
            }
        } catch (NullPointerException ex) {}
    }

    public void save() {
        for(UUID uuid : lives.keySet()) {
            ConfigManager.get("users.dat").set("Lives." + uuid.toString(), lives.get(uuid));
            lives.remove(uuid);
        }
        ConfigManager.save(Main.getInstance(), "users.dat");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(ConfigManager.get("users.dat").contains("Lives." + e.getPlayer().getUniqueId().toString())) {
            try {
                lives.put(e.getPlayer().getUniqueId(), ConfigManager.get("users.dat").getInt("Lives." + e.getPlayer().getUniqueId().toString()));
            } catch (NullPointerException ex) {
                e.getPlayer().sendMessage(ChatColor.RED + "Error loading lives please contact an administrator.");
            }
        } else {
            lives.put(e.getPlayer().getUniqueId(), 0);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (lives.containsKey(e.getPlayer().getUniqueId())) {
            ConfigManager.get("users.dat").set("Lives." + e.getPlayer().getUniqueId().toString(), lives.get(e.getPlayer().getUniqueId()));
            lives.remove(e.getPlayer().getUniqueId());
            ConfigManager.save(Main.getInstance(), "users.dat");
        }
    }
    @EventHandler
    public void onKick(PlayerKickEvent e) {
        if (lives.containsKey(e.getPlayer().getUniqueId())) {
            ConfigManager.get("users.dat").set("Lives." + e.getPlayer().getUniqueId().toString(), lives.get(e.getPlayer().getUniqueId()));
            lives.remove(e.getPlayer().getUniqueId());
            ConfigManager.save(Main.getInstance(), "users.dat");
        }
    }
}
