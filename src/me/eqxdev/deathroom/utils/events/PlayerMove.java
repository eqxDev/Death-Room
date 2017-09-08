package me.eqxdev.deathroom.utils.events;

import me.eqxdev.deathroom.Main;
import me.eqxdev.deathroom.utils.events.custom.RegionEnterEvent;
import me.eqxdev.deathroom.utils.events.custom.RegionExitEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by eqxDev.
 */
public class PlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if(!(e.getFrom().getX() == e.getTo().getX() && e.getFrom().getY() == e.getTo().getY() && e.getFrom().getZ() == e.getTo().getZ())) {
            if(Main.getInstance().getRegionManager().enabled) {
                if(Main.getInstance().getRegionManager().getRegionList().contains(e.getPlayer()) && !Main.getInstance().getRegionManager().inside(e.getPlayer().getLocation())) {
                    Main.getInstance().getRegionManager().getRegionList().remove(e.getPlayer());
                    // exit
                    Bukkit.getPluginManager().callEvent(new RegionExitEvent(e.getPlayer(),e.getPlayer().getLocation()));
                }
                if(!Main.getInstance().getRegionManager().getRegionList().contains(e.getPlayer()) && Main.getInstance().getRegionManager().inside(e.getPlayer().getLocation())) {
                    Main.getInstance().getRegionManager().getRegionList().add(e.getPlayer());
                    // enter
                    Bukkit.getPluginManager().callEvent(new RegionEnterEvent(e.getPlayer(),e.getPlayer().getLocation()));
                }
            }
        }
    }

}
