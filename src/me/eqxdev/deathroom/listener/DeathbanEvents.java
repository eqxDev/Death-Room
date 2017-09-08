package me.eqxdev.deathroom.listener;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import me.eqxdev.deathroom.Main;
import me.eqxdev.deathroom.utils.Matcher;
import me.eqxdev.deathroom.utils.enums.Lang;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.UUID;

/**
 * Created by eqxDev.
 */
public class DeathbanEvents implements Listener {

   @EventHandler
   public void onDrop(PlayerDropItemEvent e) {
       if(Main.getInstance().player.containsKey(e.getPlayer().getUniqueId()))
           if(Main.getInstance().player.get(e.getPlayer().getUniqueId()).isBanned())
               if(Main.getInstance().getRegionManager().getRegionList().contains(e.getPlayer())) {
                   e.getItemDrop().setItemStack(null);
               } else {
                   e.setCancelled(true);
               }
   }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (Main.getInstance().player.containsKey(e.getPlayer().getUniqueId())) {
            if (Main.getInstance().player.get(e.getPlayer().getUniqueId()).isBanned()) {
                String[] cmd = new String[]{e.getMessage()};
                if (e.getMessage().contains(" ")) {
                    cmd = e.getMessage().split(" ");
                }
                cmd[0] = cmd[0].replaceFirst("/", "");
                if (!Main.getInstance().commandsAllowed.contains(cmd[0])) {
                    Lang.PlAYER_DEATHABN_COMMAND.send(e.getPlayer());
                    e.setCancelled(true);
                }
            }
        }}

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (Main.getInstance().player.containsKey(e.getPlayer().getUniqueId()) && Main.getInstance().player.get(e.getPlayer().getUniqueId()).isBanned()) {
            e.setCancelled(true);
            for(UUID uuid : Main.getInstance().player.keySet()) {
                Player p = Matcher.matchPlayer(uuid);
                if(p!=null)
                    p.sendMessage(String.format(e.getFormat(),e.getPlayer().getDisplayName(),e.getMessage()));
            }
        } else {
            // sender isnt banned
            for (UUID uuid : Main.getInstance().player.keySet()) {
                Player p = Matcher.matchPlayer(uuid);
                if (p != null)
                    e.getRecipients().remove(p);
            }
        }
    }


}
