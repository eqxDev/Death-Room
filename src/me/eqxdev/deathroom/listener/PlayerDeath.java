package me.eqxdev.deathroom.listener;

import me.eqxdev.deathroom.Main;
import me.eqxdev.deathroom.utils.BukkitUtils;
import me.eqxdev.deathroom.utils.enums.Lang;
import me.eqxdev.deathroom.utils.managers.LocationManager;
import me.eqxdev.deathroom.utils.TimeUtil;
import me.eqxdev.deathroom.utils.objects.DPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by eqxDev.
 */
public class PlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if(!Main.getInstance().player.containsKey(e.getEntity().getUniqueId()))
            if(!Main.getInstance().player.get(e.getEntity().getUniqueId()).isBanned())
        if(!e.getEntity().hasPermission("dbans.staff") || !e.getEntity().hasPermission("dbans.bypass")) {
            if(!Main.getInstance().getLivesManager().getLives().containsKey(e.getEntity().getUniqueId())) {
                Main.getInstance().getLivesManager().getLives().put(e.getEntity().getUniqueId(),0);
            }
            if(Main.getInstance().getRankUtil().hasPerm(e.getEntity())) {
                Main.getInstance().player.put(e.getEntity().getUniqueId(), new DPlayer(e.getEntity().getUniqueId(),true,Main.getInstance().getRankUtil().getTime(e.getEntity())));
               Main.getInstance().getPlayerManager().reset(e.getEntity(),true);
                e.getEntity().sendMessage(Lang.PLAYER_DEATHBANNED.toString().replace("%time%", TimeUtil.format(Main.getInstance().player.get(e.getEntity().getUniqueId()).getTime(), TimeUtil.FormatType.SHORT_TIME)));
            } else {
                BukkitUtils.respawn(e.getEntity(), LocationManager.SPAWN);
                Main.getInstance().getInventory().applyDeath(e.getEntity());
            }
        } else {
            BukkitUtils.respawn(e.getEntity(), LocationManager.SPAWN);
        }
    }

}
