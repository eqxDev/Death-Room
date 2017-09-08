package me.eqxdev.deathroom.listener;

import me.eqxdev.deathroom.Main;
import me.eqxdev.deathroom.utils.enums.Lang;
import me.eqxdev.deathroom.utils.managers.CooldownManager;
import me.eqxdev.deathroom.utils.TimeUtil;
import me.eqxdev.deathroom.utils.messages.Message;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

/**
 * Created by eqxDev.
 */
public class PlayerInteract implements Listener {

    private boolean equals(Location loc, Location loc2) {
        return loc.getX() == loc2.getX() && loc.getY() == loc2.getY() && loc.getZ() == loc2.getZ();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
        if(Main.getInstance().player.containsKey(e.getPlayer().getUniqueId())&& e.getItem() !=null) {
            if (e.getItem().equals(Main.getInstance().getInventory().getInfo())) {
                Message.sendCenteredMessage(e.getPlayer(), Lang.PLAYER_DEATHBANNED_CURRENTLY.toString());
                Message.sendCenteredMessage(e.getPlayer(), ChatColor.GRAY + TimeUtil.format(Main.getInstance().player.get(e.getPlayer().getUniqueId()).getTime(), TimeUtil.FormatType.LONG_TIME) + " time remaining.");
            } else if (e.getItem().equals(Main.getInstance().getInventory().getLives())) {
                if (!CooldownManager.isExpired("lives", e.getPlayer())) {
                    CooldownManager.add("lives", e.getPlayer(), 3);
                    if(!Main.getInstance().getLivesManager().getLives().containsKey(e.getPlayer().getUniqueId())) {
                        Main.getInstance().getLivesManager().getLives().put(e.getPlayer().getUniqueId(),0);
                    }
                    if (Main.getInstance().getLivesManager().getLives().get(e.getPlayer().getUniqueId()) > 0) {
                        Main.getInstance().getLivesManager().getLives().put(e.getPlayer().getUniqueId(), Main.getInstance().getLivesManager().getLives().get(e.getPlayer().getUniqueId()) - 1);
                        Main.getInstance().player.get(e.getPlayer().getUniqueId()).setTime(1);
                        e.getPlayer().sendMessage(Lang.PLAYER_LIVES_USED.toString().replace("%remaining%",Main.getInstance().getLivesManager().getLives().get(e.getPlayer().getUniqueId()) +""));
                    } else {
                        Lang.PLAYER_LIVES_NONE.send(e.getPlayer());
                    }
                }
            }
        }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN) {
                for(UUID id : Main.getInstance().getSignsManager().getSigns().keySet()) {
                    if(equals(Main.getInstance().getSignsManager().getSigns().get(id).getLoc(),e.getClickedBlock().getLocation())) {
                        Lang.SYSTEM_SIGN_LIVES.send(e.getPlayer());
                        break;
                    }
                }
            }
        }
    }

}
