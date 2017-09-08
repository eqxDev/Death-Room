package me.eqxdev.deathroom.utils;

import me.eqxdev.deathroom.Main;
import net.minecraft.server.v1_7_R4.EnumClientCommand;
import net.minecraft.server.v1_7_R4.PacketPlayInClientCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;


/**
 * Created by eqxDev.
 */
public class BukkitUtils {

    public static void respawn(Player p, Location loc) {
        instaRespawn(p);
        new BukkitRunnable() {
            @Override
            public void run() {
                p.teleport(loc);
            }
        }.runTaskLater(Main.getInstance(),3);
    }

    public static void error(String str) {
        if(Bukkit.getOnlinePlayers().size() > 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', str));
            }
        }
    }

    public static void instaRespawn(Player p)
    {
       new BukkitRunnable() {
            public void run() {
                if (p.isDead()) {
                    try {
                        Object nmsPlayer = p.getClass().getMethod("getHandle").invoke(p);
                        Object packet = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".PacketPlayInClientCommand").newInstance();
                        Class<?> enumClass = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".EnumClientCommand");

                        for (Object ob : enumClass.getEnumConstants()) {
                            if (ob.toString().equals("PERFORM_RESPAWN")) {
                                packet = packet.getClass().getConstructor(enumClass).newInstance(ob);
                            }
                        }

                        Object con = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
                        con.getClass().getMethod("a", packet.getClass()).invoke(con, packet);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.runTaskLater(Main.getInstance(),2);

    }

}
