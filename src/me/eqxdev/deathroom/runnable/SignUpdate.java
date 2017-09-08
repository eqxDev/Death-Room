package me.eqxdev.deathroom.runnable;

import me.eqxdev.deathroom.Main;
import me.eqxdev.deathroom.utils.objects.DPlayer;
import me.eqxdev.deathroom.utils.objects.dSign;
import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

/**
 * Created by eqxDev.
 */
public class SignUpdate extends BukkitRunnable{

    @Override
    public void run() {
            Main.getInstance().player.keySet().stream().filter(uuid -> Bukkit.getPlayer(uuid) != null).forEach(uuid -> {
                Player p = Bukkit.getPlayer(uuid);
                if (p.isOnline() && !p.isDead()) {
                    for (UUID id : Main.getInstance().getSignsManager().getSigns().keySet()) {
                        dSign dsign = Main.getInstance().getSignsManager().getSigns().get(id);
                        if (dsign.getLoc().distance(p.getLocation()) <= 10) {
                            Main.getInstance().getSignsManager().proccess(p, dsign.getLoc(), dsign.getType());
                        }
                    }
                }
            });

    }
}
