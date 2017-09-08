package me.eqxdev.deathroom.utils.objects;

import me.eqxdev.deathroom.Main;
import me.eqxdev.deathroom.utils.BukkitUtils;
import me.eqxdev.deathroom.utils.LocationUtil;
import me.eqxdev.deathroom.utils.managers.ConfigManager;
import me.eqxdev.deathroom.utils.managers.LocationManager;
import me.eqxdev.deathroom.utils.objects.DPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;


/**
 * Created by eqxDev.
 */
public class DPlayerManager {

    public void reset(Player player, Boolean teleport) {
        if(player.isDead()) {
            BukkitUtils.instaRespawn(player);
        }
            player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.getInventory().setHeldItemSlot(0);
        if(Main.getInstance().player.containsKey(player.getUniqueId())) {
            if (Main.getInstance().player.get(player.getUniqueId()).isBanned()) {
                if (teleport) {
                    player.teleport(LocationManager.ARENA);
                }
                Main.getInstance().getInventory().applyDeath(player);
            } else {
                if (teleport) {
                    player.teleport(LocationManager.SPAWN);
                }
            }
        } else {
            if (teleport) {
                player.teleport(LocationManager.SPAWN);
            }
        }
    }

    public void save(DPlayer player) {
        ConfigManager.get("users.dat").set("Bans." +player.getUUID().toString() + ".Time", player.getTime());
        ConfigManager.get("users.dat").set("Bans." + player.getUUID().toString() + ".UUID", player.getUUID().toString());
        Main.getInstance().player.remove(player.getUUID());
        ConfigManager.save(Main.getInstance(),"users.dat");
    }

    public void save(){
        for(DPlayer player : Main.getInstance().player.values()) {
            ConfigManager.get("users.dat").set("Bans." +player.getUUID().toString() + ".Time", player.getTime());
            ConfigManager.get("users.dat").set("Bans." + player.getUUID().toString() + ".UUID", player.getUUID().toString());
            Main.getInstance().player.remove(player.getUUID());
        }
        ConfigManager.save(Main.getInstance(),"users.dat");
    }

    public DPlayer load(Player player) {
        Main.getInstance().player.put(player.getUniqueId(),new DPlayer(player.getUniqueId(),true,ConfigManager.get("users.dat").getInt("Bans." + player.getUniqueId().toString())));
        ConfigManager.remove("users.dat","Bans." + player.getUniqueId().toString());
        ConfigManager.save(Main.getInstance(),"users.dat");
        return Main.getInstance().player.get(player.getUniqueId());
    }

    public void load() {
        try {
            for (String key : ConfigManager.get("users.dat").getConfigurationSection("Bans").getKeys(false)) {
                String uuid = ConfigManager.get("users.dat").getString("Bans." + key + ".UUID");
                Main.getInstance().player.put(UUID.fromString(uuid), new DPlayer(UUID.fromString(uuid), true, ConfigManager.get("users.dat").getInt("Bans." + uuid + ".Time")));
                ConfigManager.remove("users.dat", "Bans." + key);
            }
            ConfigManager.save(Main.getInstance(), "users.dat");
        }catch (NullPointerException ex) {}
    }

    public boolean contains(Player p) {
        return ConfigManager.get("users.dat").contains("Bans." + p.getUniqueId().toString());
    }


}
