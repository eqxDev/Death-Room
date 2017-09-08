package me.eqxdev.deathroom.listener;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import me.eqxdev.deathroom.Main;
import me.eqxdev.deathroom.utils.Logger;
import me.eqxdev.deathroom.utils.Matcher;
import me.eqxdev.deathroom.utils.enums.Lang;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.UUID;

/**
 * Created by eqxDev.
 */
public class ServerCommand implements Listener {

    @EventHandler
    public void onServerCommand(ServerCommandEvent e){
        if(e.getCommand().contains(" ")) {
            String[] command = e.getCommand().split(" ");
            if(command[0].equalsIgnoreCase("dbans")) {
                UUID uuid = Matcher.matchUUID(command[1]);
                if(!Main.getInstance().getLivesManager().getLives().containsKey(uuid)) {
                    System.out.println(Main.getInstance().getLivesManager().getLives().get(uuid) + " <<");
                    Main.getInstance().getLivesManager().getLives().put(uuid,Main.getInstance().getLivesManager().getLives().get(uuid) + Integer.parseInt(command[2]));
                }
                Main.getInstance().getLivesManager().getLives().put(uuid, Main.getInstance().getLivesManager().getLives().get(uuid) + Integer.parseInt(command[2]));

                Player p = Matcher.matchPlayer(uuid);
                if(p!=null && p.isOnline()) {
                    p.sendMessage(Lang.PLAYER_LIVES_BROUGHT.toString().replace("%lives%", command[2]));
                }
            }
        }
    }

}
