package me.eqxdev.deathroom.utils;

import me.eqxdev.deathroom.Main;
import me.eqxdev.deathroom.utils.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by eqxDev.
 */
public class LocationUtil {
    public static void saveLocation(String config, String path, Location l) {
        config = config + ".yml";
        if(l !=null)
        ConfigManager.get(config).set(path + ".world" , l.getWorld().getName());
        ConfigManager.get(config).set(path + ".x", l.getX());
        ConfigManager.get(config).set(path + ".y", l.getY());
        ConfigManager.get(config).set(path + ".z", l.getZ());
        ConfigManager.get(config).set(path + ".pitch", l.getPitch());
        ConfigManager.get(config).set(path + ".yaw", l.getYaw());
        ConfigManager.save(JavaPlugin.getPlugin(Main.class), config);
    }

    public static Location getLocation(String config, String path) {
        config = config + ".yml";
        try {
            String world = (String) ConfigManager.get(config).get(path + ".world");
            double x = ConfigManager.get(config).getDouble(path + ".x");
            double y = ConfigManager.get(config).getDouble(path + ".y");
            double z = ConfigManager.get(config).getDouble(path + ".z");
            float pitch = (float) ConfigManager.get(config).getDouble(path + ".pitch");
            float yaw = (float) ConfigManager.get(config).getDouble(path + ".yaw");
            Location loc = new Location(Bukkit.getWorld(world), x, y, z);
            loc.setPitch(Float.valueOf(pitch).floatValue());
            loc.setYaw(Float.valueOf(yaw).floatValue());
            return loc;
        } catch (NullPointerException ex) {
            System.out.println("ERROR: While loading location, if this is first time ignore, else make sure all locations have been set at the path: " + path);
            return null;
        }
    }

}
