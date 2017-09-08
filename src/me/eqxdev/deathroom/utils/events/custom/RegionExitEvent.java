package me.eqxdev.deathroom.utils.events.custom;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by eqxDev.
 */
public class RegionExitEvent extends Event {

    private Player p;
    private Location loc;

    public RegionExitEvent(Player p, Location loc) {
        this.p = p;
        this.loc = loc;
    }

    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return p;
    }

    public void getPlayer(Player p) {
        this.p = p;
    }

    public Location getLocation() {
        return loc;
    }

    public void setLocation(Location loc) {
        this.loc = loc;
    }

}
