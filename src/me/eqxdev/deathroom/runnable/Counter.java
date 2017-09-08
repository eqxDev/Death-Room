package me.eqxdev.deathroom.runnable;

import me.eqxdev.deathroom.Main;
import me.eqxdev.deathroom.utils.enums.Lang;
import me.eqxdev.deathroom.utils.Matcher;
import me.eqxdev.deathroom.utils.objects.DPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

/**
 * Created by eqxDev.
 */
public class Counter extends BukkitRunnable {
    @Override
    public void run() {
        if (Main.getInstance().player.size() > 0) {
            for (UUID uuid : Main.getInstance().player.keySet()) {
                DPlayer dPlayer = Main.getInstance().player.get(uuid);

                if (dPlayer.getTime() > 0 || dPlayer.isBanned()) {
                    dPlayer.setTime(dPlayer.getTime() - 1);
                    Main.getInstance().player.put(uuid, dPlayer);
                    if (dPlayer.getTime() < 1) {
                        dPlayer.setBanned(false);
                    }
                } else {
                    Player t = Matcher.matchPlayer(uuid);
                    if (t != null) {
                        t.sendMessage(Lang.PLAYER_DEATHBAN_FINISHED.toString());
                        Main.getInstance().getPlayerManager().reset(t, true);
                    } else {
                        Main.getInstance().getQueManager().getQue().add(uuid.toString());
                        System.out.println(Matcher.matchName(uuid) + "[" + uuid + "] Is offline adding to que for unban.");
                    }
                    Main.getInstance().player.remove(uuid);
                }
            }
        }
    }
}
