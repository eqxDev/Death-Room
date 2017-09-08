package me.eqxdev.deathroom.utils.managers;

import com.avaje.ebean.annotation.EnumValue;
import me.eqxdev.deathroom.utils.LocationUtil;
import me.eqxdev.deathroom.utils.events.custom.RegionEnterEvent;
import org.bukkit.*;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eqxDev.
 */
public class RegionManager implements Listener {

    private org.bukkit.Location l1;
    private org.bukkit.Location l2;
    public boolean enabled;
    public List<Player> region = new ArrayList<>();

    public void corner(org.bukkit.Location loc1, Location loc2) {
        int x1 = Math.min(loc1.getBlockX(), loc2.getBlockX());
        int y1 = Math.min(loc1.getBlockY(), loc2.getBlockY());
        int z1 = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
        int x2 = Math.max(loc1.getBlockX(), loc2.getBlockX());
        int y2 = Math.max(loc1.getBlockY(), loc2.getBlockY());
        int z2 = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
        l1 = new Location(loc1.getWorld(), x1, y1, z1);
        l2 = new Location(loc2.getWorld(), x2, y2, z2);
        save();
        enabled = true;
    }

    public boolean inside(org.bukkit.Location loc) {
        return loc.getBlockX() >= l1.getBlockX() && loc.getBlockX() <= l2.getBlockX()
                && loc.getBlockY() >= l1.getBlockY() && loc.getBlockY() <= l2.getBlockY()
                && loc.getBlockZ() >= l1.getBlockZ() && loc.getBlockZ() <= l2.getBlockZ();

    }

    public void save() {
        if(l1 !=null) {
            LocationUtil.saveLocation("data", "Locations.Regions.1", l1);
        }
        if(l2 !=null) {
            LocationUtil.saveLocation("data", "Locations.Regions.2", l2);
        }
    }

    public void load() {
        try {
            l1 = LocationUtil.getLocation("data", "Locations.Regions.1");
            l2 = LocationUtil.getLocation("data", "Locations.Regions.2");
            enabled = true;
        } catch (IllegalArgumentException iae) {
            System.out.println("Region has not been set yet.");
            enabled = false;
        }

        enabled = l1 !=null && l2 !=null;

    }

    public List<Player> getRegionList() {
        return region;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        if(region.contains(e.getPlayer())) {
            region.remove(e.getPlayer());
        }
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        if(region.contains(e.getPlayer())) {
            region.remove(e.getPlayer());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(enabled)
        if(inside(e.getPlayer().getLocation())) {
            region.add(e.getPlayer());
            // Call event
            Bukkit.getPluginManager().callEvent(new RegionEnterEvent(e.getPlayer(),e.getPlayer().getLocation()));
        }
    }

}
