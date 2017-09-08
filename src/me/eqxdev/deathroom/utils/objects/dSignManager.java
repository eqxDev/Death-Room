package me.eqxdev.deathroom.utils.objects;

import me.eqxdev.deathroom.Main;
import me.eqxdev.deathroom.utils.enums.Lang;
import me.eqxdev.deathroom.utils.LocationUtil;
import me.eqxdev.deathroom.utils.enums.SignType;
import me.eqxdev.deathroom.utils.managers.ConfigManager;
import me.eqxdev.deathroom.utils.TimeUtil;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Created by eqxDev.
 */
public class dSignManager implements Listener {

    private Map<UUID, dSign> signs = new HashMap<>();

    public Map<UUID,dSign> getSigns() {return signs;}

    public void create(Location loc, SignType signType, UUID id) {
        dSign sign = new dSign(loc, signType,id);
        signs.put(sign.getId(),sign);
    }

    public void del(dSign sign, Boolean save) {
        signs.remove(sign.getId());
        if(save) {
            sign.save(Main.getInstance(),"data.yml", "Signs");
        }
    }
    public void del(Boolean save) {
        Iterator<UUID> iterator = signs.keySet().iterator();
        while(iterator.hasNext()) {
            if (save) {
                signs.get(iterator.next()).save(Main.getInstance(), "data.yml", "Signs");
            }
            iterator.remove();
        }
    }

    private String colour(String str) { return org.bukkit.ChatColor.translateAlternateColorCodes('&', str); }
    public void load() {

        lg1 = colour(ConfigManager.get("data.yml").getString("Config.Signs.Lives.Got.1")); // Lives got 1-4
        lg2 = colour(ConfigManager.get("data.yml").getString("Config.Signs.Lives.Got.2"));
        lg3 = colour(ConfigManager.get("data.yml").getString("Config.Signs.Lives.Got.3"));
        lg4 = colour(ConfigManager.get("data.yml").getString("Config.Signs.Lives.Got.4"));

        ln1 = colour(ConfigManager.get("data.yml").getString("Config.Signs.Lives.Out.1")); // Lives none 1-4
        ln2 = colour(ConfigManager.get("data.yml").getString("Config.Signs.Lives.Out.2"));
        ln3 = colour(ConfigManager.get("data.yml").getString("Config.Signs.Lives.Out.3"));
        ln4 = colour(ConfigManager.get("data.yml").getString("Config.Signs.Lives.Out.4"));

        t1 = colour(ConfigManager.get("data.yml").getString("Config.Signs.Timer.1")); // Timer 1-4
        t2 = colour(ConfigManager.get("data.yml").getString("Config.Signs.Timer.2"));
        t3 = colour(ConfigManager.get("data.yml").getString("Config.Signs.Timer.3"));
        t4 = colour(ConfigManager.get("data.yml").getString("Config.Signs.Timer.4"));

            try {
                for (String key : ConfigManager.get("data.yml").getConfigurationSection("Signs").getKeys(false)) {
                    create(LocationUtil.getLocation("data", "Signs." + key + ".Location"), SignType.getFromString(ConfigManager.get("data.yml").getString("Signs." + key + ".Type")), UUID.fromString(key));
                }
            } catch (NullPointerException ex) {}

    }

    private String lg1; // Lives got 1-4
    private String lg2;
    private String lg3;
    private String lg4;

    private String ln1; // Lives none 1-4
    private String ln2;
    private String ln3;
    private String ln4;

    private String t1; // Timer 1-4
    private String t2;
    private String t3;
    private String t4;

    public void proccess(Player p, Location loc, SignType type) { // Add in signs - Add replace using string array
        if(type == SignType.LIVES) {
            // Lives
            if(!Main.getInstance().getLivesManager().getLives().containsKey(p.getUniqueId())) {
                Main.getInstance().getLivesManager().getLives().put(p.getUniqueId(),0);
            }
            if(Main.getInstance().getLivesManager().getLives().get(p.getUniqueId()) > 0) {
                // Got
                p.sendSignChange(loc, new String[] {
                        lg1.replace("%lives%",Main.getInstance().getLivesManager().getLives().get(p.getUniqueId()) + ""),
                        lg2.replace("%lives%",Main.getInstance().getLivesManager().getLives().get(p.getUniqueId()) + ""),
                        lg3.replace("%lives%",Main.getInstance().getLivesManager().getLives().get(p.getUniqueId()) + ""),
                        lg4.replace("%lives%",Main.getInstance().getLivesManager().getLives().get(p.getUniqueId()) + "")
                });
            } else {
                // None
                p.sendSignChange(loc, new String[] {
                        ln1.replace("%lives%",Main.getInstance().getLivesManager().getLives().get(p.getUniqueId()) + ""),
                        ln2.replace("%lives%",Main.getInstance().getLivesManager().getLives().get(p.getUniqueId()) + ""),
                        ln3.replace("%lives%",Main.getInstance().getLivesManager().getLives().get(p.getUniqueId()) + ""),
                        ln4.replace("%lives%",Main.getInstance().getLivesManager().getLives().get(p.getUniqueId()) + "")
                });
            }
        } else if(type == SignType.TIMER) {
            // Timer
            p.sendSignChange(loc, new String[] {
                    t1.replace("%time%", TimeUtil.format(Main.getInstance().player.get(p.getUniqueId()).getTime(), TimeUtil.FormatType.SHORT_TIME)),
                    t2.replace("%time%", TimeUtil.format(Main.getInstance().player.get(p.getUniqueId()).getTime(), TimeUtil.FormatType.SHORT_TIME)),
                    t3.replace("%time%", TimeUtil.format(Main.getInstance().player.get(p.getUniqueId()).getTime(), TimeUtil.FormatType.SHORT_TIME)),
                    t4.replace("%time%", TimeUtil.format(Main.getInstance().player.get(p.getUniqueId()).getTime(), TimeUtil.FormatType.SHORT_TIME))
            });
        }
    }


    @EventHandler
    public void onPlace(SignChangeEvent e) {
        if(e.getPlayer().isOp()) {
            if(e.getLines().length > 0) {
                if(ChatColor.stripColor(e.getLines()[0]).equalsIgnoreCase("[timer]")) {
                    create(e.getBlock().getLocation(),SignType.TIMER,null);
                    e.setLine(0, Lang.SYSTEM_SIGN_DEFAULT_TIMER.toString());
                    e.getPlayer().sendMessage(Lang.PLAYER_SIGN_CREATE_TIMER.toString());
                } else if(ChatColor.stripColor(e.getLines()[0]).equalsIgnoreCase("[lives]")) {
                    create(e.getBlock().getLocation(),SignType.LIVES,null);
                    e.setLine(0, Lang.SYSTEM_SIGN_DEFAULT_LIVES.toString());
                    e.getPlayer().sendMessage(Lang.PLAYER_SIGN_CREATE_LIVES.toString());
                }
            }
        }
    }

    private boolean equals(Location loc, Location loc2) {
        return loc.getX() == loc2.getX() && loc.getY() == loc2.getY() && loc.getZ() == loc2.getZ();
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if(e.getPlayer().isOp()) {

            Iterator<UUID> iterator = signs.keySet().iterator();
            while(iterator.hasNext()) {
                UUID key = iterator.next();
                if (equals(e.getBlock().getLocation(), signs.get(key).getLoc())) {
                    e.getPlayer().sendMessage(Lang.PLAYER_SIGN_DESTROY.toString());
                    signs.get(key).remove(Main.getInstance(),"data.yml", "Signs");
                    iterator.remove();
                    break;
                }
            }
        }
    }

}
