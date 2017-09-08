package me.eqxdev.deathroom.listener;

import me.eqxdev.deathroom.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Created by eqxDev.
 */
public class EntityHitEvent implements Listener {

    @EventHandler
    public void OnPlayerDeath(EntityDamageByEntityEvent e) {

        if (e.getEntity() instanceof Player) {
            Player damaged = (Player) e.getEntity();
            if (Main.getInstance().player.containsKey(damaged.getUniqueId())) {
                if (Main.getInstance().player.get(damaged.getUniqueId()).isBanned()) {
                    if ((damaged.getHealth() - e.getDamage()) <= 0) {
                        e.setDamage(0);
                        Main.getInstance().getPlayerManager().reset(damaged,true);
                    }
                }
            }
        }
    }

}
