package me.eqxdev.deathroom.utils.managers;

import me.eqxdev.deathroom.Main;
import me.eqxdev.deathroom.utils.BukkitUtils;
import me.eqxdev.deathroom.utils.LocationUtil;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Bukkit;
import sun.security.krb5.Config;

/**
 * Created by eqxDev.
 */
public class LocationManager {
    public static org.bukkit.Location SPAWN;
    public static org.bukkit.Location ARENA;

    public static void load() {
        try {
            try {
                SPAWN = LocationUtil.getLocation("data", "Locations.Spawn");
            } catch (NullPointerException ex) {
                BukkitUtils.error("&4ERROR: Please set the Spawn spawn, Errors could arise.");
                System.out.println("ERROR: Please set the Spawn spawn, Errors could arise.");
            }
        } catch (IllegalArgumentException ex) {
            BukkitUtils.error("&4ERROR: Please set the Spawn spawn, Errors could arise.");
            System.out.println("ERROR: Please set the Spawn spawn, Errors could arise.");
        }
        try {
            try {
                ARENA = LocationUtil.getLocation("data", "Locations.Arena");
            } catch (NullPointerException ex) {
                BukkitUtils.error("&4ERROR: Please set the Arena spawn, Errors could arise.");
                System.out.println("ERROR: Please set the Arena spawn, Errors could arise.");
            }
        } catch (IllegalArgumentException ex) {
            BukkitUtils.error("&4ERROR: Please set the Spawn spawn, Errors could arise.");
            System.out.println("ERROR: Please set the Spawn spawn, Errors could arise.");
        }
    }



    public static void save() {
        try {
            LocationUtil.saveLocation("data", "Locations.Spawn", SPAWN);
        } catch (NullPointerException ex) {}
        try {
            LocationUtil.saveLocation("data", "Locations.Arena", ARENA);
        } catch (NullPointerException ex) {}
    }
}
