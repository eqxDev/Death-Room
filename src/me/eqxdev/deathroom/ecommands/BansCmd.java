package me.eqxdev.deathroom.ecommands;

import me.eqxdev.deathroom.Main;
import me.eqxdev.deathroom.utils.BukkitUtils;
import me.eqxdev.deathroom.utils.Matcher;
import me.eqxdev.deathroom.utils.enums.Lang;
import me.eqxdev.deathroom.utils.managers.LocationManager;
import me.eqxdev.deathroom.utils.TimeUtil;
import me.eqxdev.deathroom.utils.objects.DPlayer;
import me.eqxdev.deathroom.utils.objects.eCommand;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by eqxDev.
 */
public class BansCmd extends eCommand {

    public BansCmd() {
        super("dbans", "dbans.staff");
    }

    org.bukkit.Location l1;
    Location l2;

    @Override
    public void execute(Player sender, String[] args) {
        if(args.length > 0) {
            if (args[0].equalsIgnoreCase("revive") && args.length == 2) { // /dbans revive name
                UUID uuid = Matcher.matchUUID(args[1]);
                if (uuid != null)
                    if (Main.getInstance().player.containsKey(uuid)) {
                        Main.getInstance().player.get(uuid).setTime(1);

                        Player t = Matcher.matchPlayer(uuid);
                        sender.sendMessage(Lang.STAFF_CMD_SENDER_REVIVE.toString().replace("%target%", t!=null?t.getName():"ERROR"));
                        if (t != null) {
                            Lang.STAFF_CMD_TARGET_REVIVE.send(t);
                        }
                    } else {
                        // not banned
                        Lang.STAFF_CMD_ERROR_REVIVE_NOTBANNED.send(sender);
                    }
            } else if (args[0].equalsIgnoreCase("lives")) {
                if(args.length == 1) {
                    // help for lives
                    Lang.PLAYER_HELP_STAFF_LIVES.send(sender);
                } else if((args[1].equalsIgnoreCase("remove") || args[1].equalsIgnoreCase("add")) && args[3].equalsIgnoreCase("0")) {
                    // help for lives
                    Lang.PLAYER_HELP_STAFF_LIVES.send(sender);
                } else if (args[1].equalsIgnoreCase("add") && args.length == 4 && isNumber(args[3])) { // /dbans lives add name ammount
                    UUID uuid = Matcher.matchUUID(args[2]);
                    if (uuid != null) {
                        Main.getInstance().getLivesManager().getLives().put(uuid, Main.getInstance().getLivesManager().getLives().get(uuid) + Integer.parseInt(args[3]));
                        Player t = Matcher.matchPlayer(uuid);
                        if (t != null) {
                            t.sendMessage(Lang.STAFF_CMD_LIVES_ADD_TARGET.toString().replace("%lives%",args[3]));
                        }
                        sender.sendMessage(Lang.STAFF_CMD_LIVES_ADD_SENDER.toString().replace("%lives%",args[3]).replace("%target%",t!=null?t.getName():"ERROR"));
                    } else {
                        Lang.STAFF_CMD_LIVES_ADD_ERROR_PLAYERNOTFOUND.send(sender);
                    }
                } else if (args[1].equalsIgnoreCase("remove") && args.length == 4 && isNumber(args[3])) {
                    UUID uuid = Matcher.matchUUID(args[2]);
                    if (uuid != null) {
                        if ((Main.getInstance().getLivesManager().getLives().get(uuid) - Integer.parseInt(args[3]) < 0)) {
                            Lang.STAFF_CMD_LIVES_REMOVE_ERROR_NOLIVES.send(sender);
                        } else {
                            Main.getInstance().getLivesManager().getLives().put(uuid, Main.getInstance().getLivesManager().getLives().get(uuid) - Integer.parseInt(args[3]));
                            Player t = Matcher.matchPlayer(uuid);
                            if (t != null) {
                                t.sendMessage(Lang.STAFF_CMD_LIVES_ADD_TARGET.toString().replace("%lives%", args[3]));
                            }
                            sender.sendMessage(Lang.STAFF_CMD_LIVES_ADD_SENDER.toString().replace("%lives%", args[3]).replace("%target%",t!=null?t.getName():"ERROR"));
                        }
                    } else {
                        Lang.STAFF_CMD_LIVES_REMOVE_ERROR_PLAYERNOTFOUND.send(sender);
                    }
                } else if (args[1].equalsIgnoreCase("check") && args.length == 3) {
                    UUID uuid = Matcher.matchUUID(args[2]);
                    if (uuid != null) {
                        sender.sendMessage(Lang.STAFF_CMD_LIVES_CHECK_SENDER.toString().replace("%target%",args[2]).replace("%lives%",Main.getInstance().getLivesManager().getLives().get(uuid) +""));

                    } else {
                        Lang.STAFF_CMD_LIVES_CHECK_ERROR_PLAYERNOTFOUND.send(sender);
                    }
                } else {
                    // help for lives
                    Lang.PLAYER_HELP_STAFF_LIVES.send(sender);
                }
            } else if (args[0].equalsIgnoreCase("ban") && args.length == 2) {
                Player t = Matcher.matchPlayer(args[1]);
                if (t != null) {
                    Main.getInstance().player.put(t.getUniqueId(), new DPlayer(t.getUniqueId(), true, Main.getInstance().getRankUtil().getTime(t)));
                    BukkitUtils.respawn(t, LocationManager.ARENA);
                    Main.getInstance().getInventory().applyDeath(t);
                    t.sendMessage(Lang.PLAYER_DEATHBANNED.toString().replace("%time%", TimeUtil.format(Main.getInstance().player.get(t.getUniqueId()).getTime(), TimeUtil.FormatType.SHORT_TIME)));
                    sender.sendMessage(Lang.STAFF_CMD_SENDER_BAN.toString().replace("%time%",TimeUtil.format(Main.getInstance().player.get(t.getUniqueId()).getTime(), TimeUtil.FormatType.SHORT_TIME))
                    .replace("%target%", t.getName()));
                } else {
                    Lang.STAFF_CMD_ERROR_BAN_PLAYERNOTFOUNT.send(sender);
                }

            } else if (args[0].equalsIgnoreCase("check") && args.length == 2) {
                UUID uuid = Matcher.matchUUID(args[1]);
                if (uuid != null)
                    if (Main.getInstance().player.containsKey(uuid)) {
sender.sendMessage(Lang.STAFF_CMD_SENDER_CHECK.toString().replace("%time%", TimeUtil.format(Main.getInstance().player.get(uuid).getTime(), TimeUtil.FormatType.LONG_TIME))
.replace("%target%",args[1]));

                    } else {
                        // not banned
                        Lang.STAFF_CMD_ERROR_CHECK_NOTBANNED.send(sender);
                    }
            } else if(args[0].equalsIgnoreCase("admin") && sender.hasPermission("dbans.admin")) {
                if(args.length >= 2 && args[1].equalsIgnoreCase("arena")) {
                  if(args.length ==3 && args[2].equalsIgnoreCase("setregion")) { // /dbans admin arena setregion
                      if(l1 == null) {
                          l1 = sender.getLocation();
                          Lang.STAFF_CMD_ADMIN_ARENA_SETREGION_SENDER_1.send(sender);
                      } else {
                          l2 = sender.getLocation();
                          Lang.STAFF_CMD_ADMIN_ARENA_SETREGION_SENDER_2.send(sender);
                      }
                      if(l1 !=null && l2 !=null) {
                          Main.getInstance().getRegionManager().corner(l1,l2);
                          l1 = null;
                          l2 = null;
                         Lang.STAFF_CMD_ADMIN_ARENA_SETREGION_SENDER_SUCCESS.send(sender);
                      }
                  } else if(args.length ==3 && args[2].equalsIgnoreCase("setkit")) {
                      Main.getInstance().getInventory().setContents(sender.getInventory().getContents());
                      Main.getInstance().getInventory().setArmorContents(sender.getInventory().getArmorContents());
                     Lang.STAFF_CMD_ADMIN_ARENA_SETKIT_SENDER.send(sender);
                  } else {
                      // arena help
                      Lang.PLAYER_HELP_STAFF_ADMIN_ARENA.send(sender);
                  }
                } else if (args.length >= 2 && args[1].equalsIgnoreCase("setspawnpoint")) {
                    if (args.length ==3 && args[2].equalsIgnoreCase("deathban")) {
                        // set death ban
                        LocationManager.ARENA = sender.getLocation();
                        Lang.STAFF_CMD_ADMIN_SPAWNPOINT_SENDER_DEATHBAN.send(sender);
                    } else if (args.length ==3 && args[2].equalsIgnoreCase("spawn")) {
                        // set spawn
                        LocationManager.SPAWN = sender.getLocation();
                        Lang.STAFF_CMD_ADMIN_SPAWNPOINT_SENDER_SPAWN.send(sender);
                    } else {
                        // spawn point help
                        Lang.PLAYER_HELP_STAFF_ADMIN_SPAWNPOINT.send(sender);
                    }
                }else if(args.length >= 2 && args[1].equalsIgnoreCase("setitem")) {
                    if(args.length ==3 && args[2].equalsIgnoreCase("info")) {
                        if (sender.getItemInHand() != null) {
                            Lang.STAFF_CMD_ADMIN_SETITEM_INFO.send(sender);
                            Main.getInstance().getInventory().setInfo(sender.getItemInHand());
                    } else {
                            Lang.STAFF_CMD_ADMIN_SETITEM_ERROR_NOITEM.send(sender);
                        }
                    } else if(args.length ==3&&args[2].equalsIgnoreCase("lives")) {
                        if(sender.getItemInHand() !=null) {
                            Lang.STAFF_CMD_ADMIN_SETITEM_LIVES.send(sender);
                            Main.getInstance().getInventory().setLives(sender.getItemInHand());
                        } else {
                            Lang.STAFF_CMD_ADMIN_SETITEM_ERROR_NOITEM.send(sender);
                        }
                    } else {
                        Lang.PLAYER_HELP_STAFF_ADMIN_SETITEM.send(sender);
                    }

        } else {
                // help admin
                Lang.PLAYER_HELP_STAFF_ADMIN.send(sender);
            }
            } else {
                // help admin
                Lang.PLAYER_HELP_STAFF_ADMIN.send(sender);
            }
        } else {
            //help
          Lang.PLAYER_HELP_STAFF.send(sender);
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
