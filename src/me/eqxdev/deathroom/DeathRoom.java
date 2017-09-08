package me.eqxdev.deathroom;

import me.eqxdev.deathroom.utils.TimeUtil;
import me.eqxdev.deathroom.utils.enums.Lang;
import me.eqxdev.deathroom.utils.managers.LivesManager;
import me.eqxdev.deathroom.utils.managers.QueManager;
import me.eqxdev.deathroom.utils.managers.RegionManager;
import me.eqxdev.deathroom.utils.objects.DInventory;
import me.eqxdev.deathroom.utils.objects.DPlayer;
import me.eqxdev.deathroom.utils.objects.DPlayerManager;
import me.eqxdev.deathroom.utils.objects.dSignManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by eqxDev.
 */
public class DeathRoom {

    public DeathRoom() {
        instance = new DeathRoom();
    }

    private static DeathRoom instance;

    public static DeathRoom get(){
        return instance == null?new DeathRoom():instance;
    }

    public boolean isBanned(Player p) {
        UUID uuid = p.getUniqueId();
        return (Main.getInstance().player.containsKey(uuid) && !Main.getInstance().player.get(uuid).isBanned());
    }

    public boolean isBanned(UUID uuid) {
        return (Main.getInstance().player.containsKey(uuid) && !Main.getInstance().player.get(uuid).isBanned());
    }

    public void ban(Player p) {
        Main.getInstance().player.put(p.getUniqueId(), new DPlayer(p.getUniqueId(),true,Main.getInstance().getRankUtil().getTime(p)));
        Main.getInstance().getPlayerManager().reset(p,true);
        p.sendMessage(Lang.PLAYER_DEATHBANNED.toString().replace("%time%", TimeUtil.format(Main.getInstance().player.get(p.getUniqueId()).getTime(), TimeUtil.FormatType.SHORT_TIME)));
    }

    public void ban(Player p, int time) {
        Main.getInstance().player.put(p.getUniqueId(), new DPlayer(p.getUniqueId(),true,time));
        Main.getInstance().getPlayerManager().reset(p,true);
        p.sendMessage(Lang.PLAYER_DEATHBANNED.toString().replace("%time%", TimeUtil.format(time, TimeUtil.FormatType.SHORT_TIME)));
    }
    public void unban(Player p) {
        Main.getInstance().player.get(p.getUniqueId()).setTime(1);
    }

    public void unban(UUID uuid) {
        Main.getInstance().player.get(uuid).setTime(1);
    }

    public int getTime(Player p) {
        return Main.getInstance().player.get(p.getUniqueId()).getTime();
    }
    public int getTime(UUID uuid) {
        return Main.getInstance().player.get(uuid).getTime();
    }

    public void reset(Player p, Boolean teleport) {
        Main.getInstance().getPlayerManager().reset(p,teleport);
    }

    public DPlayer getDPlayer(Player p) {
        return Main.getInstance().player.get(p.getUniqueId());
    }

    public DPlayer getDplayer(UUID uuid) {
        return Main.getInstance().player.get(uuid);
    }

    public Map<UUID, DPlayer> getDeathBannedPlayers() {
        return Main.getInstance().player;
    }

    public DPlayerManager getDPlayerManager() {
        return Main.getInstance().getPlayerManager();
    }

    public LivesManager getLivesManager() {
        return Main.getInstance().getLivesManager();
    }

    public RegionManager getRegionManager() {
        return Main.getInstance().getRegionManager();
    }

    public dSignManager getSignManager() {
        return Main.getInstance().getSignsManager();
    }

    public DInventory getInventoryManager() {
        return Main.getInstance().getInventory();
    }

    public QueManager getQueManager() {
       return Main.getInstance().getQueManager();
    }

}
