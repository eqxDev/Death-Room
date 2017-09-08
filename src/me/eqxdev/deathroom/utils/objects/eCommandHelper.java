package me.eqxdev.deathroom.utils.objects;

import me.eqxdev.deathroom.ecommands.BansCmd;
import me.eqxdev.deathroom.ecommands.LivesCmd;
import me.eqxdev.deathroom.utils.enums.Lang;
import me.eqxdev.deathroom.utils.objects.eCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by eqxDev.
 */
public class eCommandHelper implements Listener {

        public HashMap<String, eCommand> cmds = new HashMap<>();


        public void add(eCommand eCommand) {
            cmds.put(eCommand.getCmd(), eCommand);
        }

        public void load(JavaPlugin pl) {
            pl.getServer().getPluginManager().registerEvents(this, pl);
            new BansCmd();
            new LivesCmd();
        }

        @EventHandler
        public void onCommand(PlayerCommandPreprocessEvent e) {
            Player p = e.getPlayer();
            String[] args = e.getMessage().substring(1).split(" ");
            eCommand cmd = cmds.get(args[0]);
            if (cmd == null) {
                e.setCancelled(false);
                return;
            }
            String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
            System.arraycopy(args, 1, newArgs, 0, newArgs.length);
            if(cmd.getPermission() != null) {
                if(e.getPlayer().isOp() || e.getPlayer().hasPermission(cmd.getPermission())) {
                    cmd.execute(p,newArgs);
                } else {
                    Lang.PLAYER_NOPERMISSION.send(p);
                }
            } else {
                cmd.execute(p, newArgs);
            }
                if(cmd.isCanceled()) {
                    e.setCancelled(false);
                } else {
                    e.setCancelled(true);
                }

        }
    }


