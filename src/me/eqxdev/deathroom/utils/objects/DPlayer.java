package me.eqxdev.deathroom.utils.objects;

import me.eqxdev.deathroom.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Created by eqxDev.
 */
public class DPlayer {

    private Boolean banned;
    private int time;
    private UUID uuid;

    public DPlayer(UUID player, Boolean banned, int time) {
        this.uuid = player;
        this.banned = banned;
        this.time = time;
    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Boolean isBanned() {
        return banned;
    }
    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID player) {
        this.uuid = player;
    }

}
