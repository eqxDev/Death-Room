package me.eqxdev.deathroom.utils.managers;

import me.eqxdev.deathroom.Main;
import me.eqxdev.deathroom.utils.BukkitUtils;
import me.eqxdev.deathroom.utils.enums.Lang;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eqxDev.
 */
public class QueManager implements Listener {

    private List<String> que = new ArrayList<>();
    public List<String> getQue() {return que;}

    public void load() {
        try {
            for (String key : ConfigManager.get("users.dat").getConfigurationSection("Que").getKeys(false)) {
                que.add(key);
                ConfigManager.remove("users.dat", "Que." + key);
            }
            ConfigManager.save(Main.getInstance(), "users.dat");
        } catch (NullPointerException ex) {}
    }

    public void save() {
        for(String str : que) {
            ConfigManager.get("users.dat").set("Que." + str, 0);
        }
        ConfigManager.save(Main.getInstance(), "users.dat");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(que.contains(e.getPlayer().getUniqueId().toString())) {
            Lang.PLAYER_DEATHBAN_FINISHED.send(e.getPlayer());
            que.remove(e.getPlayer().getUniqueId().toString());
           Main.getInstance().getPlayerManager().reset(e.getPlayer(),true);
        }
    }

}
