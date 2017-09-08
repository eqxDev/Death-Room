package me.eqxdev.deathroom.ecommands;

import me.eqxdev.deathroom.Main;
import me.eqxdev.deathroom.utils.Matcher;
import me.eqxdev.deathroom.utils.enums.Lang;
import me.eqxdev.deathroom.utils.objects.eCommand;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by eqxDev.
 */
public class LivesCmd extends eCommand {

    public LivesCmd() {
        super("lives");
    }

    @Override
    public void execute(Player sender, String[] args) {  // /lives <send/check> <player>
        if(args.length > 0) {
            if(args[0].equalsIgnoreCase("check") && args.length == 1) {
                // msg sender
                if(!Main.getInstance().getLivesManager().getLives().containsKey(sender.getUniqueId())) {
                    Main.getInstance().getLivesManager().getLives().put(sender.getUniqueId(),0);
                }
                sender.sendMessage(Lang.LIVES_CMD_CHECK.toString().replace("%lives%", Main.getInstance().getLivesManager().getLives().get(sender.getUniqueId()) +""));
            } else if(args[0].equalsIgnoreCase("send") && args.length == 3 && isNumber(args[2])) { // lives send player <amt// >
                int amt = Integer.parseInt(args[2]);
                UUID tid = Matcher.matchUUID(args[1]);
                if(Main.getInstance().getLivesManager().getLives().get(sender.getUniqueId()) >= amt) {
                    if(tid !=null) {
                        Main.getInstance().getLivesManager().getLives().put(sender.getUniqueId(), Main.getInstance().getLivesManager().getLives().get(sender.getUniqueId()) - amt);
                        Main.getInstance().getLivesManager().getLives().put(tid,Main.getInstance().getLivesManager().getLives().get(tid) + amt);

                        // msg sender
                        Player t = Matcher.matchPlayer(tid);

                        sender.sendMessage(Lang.LIVES_SEND_SENDER.toString().replace("%lives%", amt +"").replace("%target%",t!=null?t.getName():"ERROR"));

                        if(t !=null && t.isOnline()) {
                            // msg t
                            t.sendMessage(Lang.LIVES_SEND_TARGET.toString().replace("%sender%",sender.getName()).replace("%lives%",amt +""));
                        }

                    } else {
                        // Cannot find player
                        Lang.LIVES_SEND_ERROR_PLAYERNOTFOUND.send(sender);
                    }
                } else {
                    // not enough lives to send
                    Lang.LIVES_SEND_ERROR_NOLIVES.send(sender);
                }
            } else {
                // help
                Lang.PLAYER_HELP_LIVES.send(sender);
            }
        } else {
            // help
            Lang.PLAYER_HELP_LIVES.send(sender);
        }
    }

    private boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
