package me.eqxdev.deathroom.listener;

import me.eqxdev.deathroom.Main;
import me.eqxdev.deathroom.utils.enums.Lang;
import me.eqxdev.deathroom.utils.events.custom.RegionEnterEvent;
import me.eqxdev.deathroom.utils.events.custom.RegionExitEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by eqxDev.
 */
public class RegionEvents implements Listener {

    @EventHandler
    public void onEnter(RegionEnterEvent e) {
        if(Main.getInstance().player.containsKey(e.getPlayer().getUniqueId())) {
            Lang.SYSTEM_ARENA_ENTER.send(e.getPlayer());
            Main.getInstance().getInventory().apply(e.getPlayer());
        }
    }

    @EventHandler
    public void onExit(RegionExitEvent e) {
        Main.getInstance().getPlayerManager().reset(e.getPlayer(), false);
    }

}
