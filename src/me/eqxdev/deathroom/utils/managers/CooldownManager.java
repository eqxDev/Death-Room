package me.eqxdev.deathroom.utils.managers;

import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by eqxDev.
 */
public class CooldownManager {

    public static HashMap<String, Integer> cooldown = new HashMap<String, Integer>();

    public static String name(Player player) {
        return player.getName();
    }

    public static void add(String id, Player player, int time) {
        int current = (int) System.currentTimeMillis() / 1000;
        int finish = current + time;
        cooldown.put(id + "." + name(player), finish);
    }

    public static void remove(String id, Player player) {
        if(cooldown.containsKey(id + "." + name(player)))  {
            cooldown.remove(id + "." + name(player));
        }
    }

    public static boolean isExpired(String id, Player player) {
        if(cooldown.containsKey(id + "." + name(player))) {
            if (cooldown.get(id + "." + name(player)) <= (int) System.currentTimeMillis() / 1000) {
                remove(id, player);
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public static int time(String id, Player player) {
        return cooldown.get(id + "." + name(player)) - (int) System.currentTimeMillis() / 1000;
    }

    public static boolean isIncluded(String id, Player player) {
        if(cooldown.containsKey(id + "." + name(player))) {
            return true;
        } else {
            return false;
        }
    }


}
