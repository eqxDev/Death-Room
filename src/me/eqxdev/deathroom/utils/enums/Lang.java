package me.eqxdev.deathroom.utils.enums;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * Created by eqxDev.
 */
public enum Lang { // Make help - Make L0litsalex nightcore remix - #Bantslolremix

    SYSTEM_SIGN_DEFAULT_TIMER("SYSTEM_SIGN_DEFAULT_TIMER", "&4&lTIMER"),
    SYSTEM_SIGN_DEFAULT_LIVES("SYSTEM_SIGN_DEFAULT_LIVES", "&4&lLIVES"),
    SYSTEM_SIGN_LIVES("SYSTEM_SIGN_LIVES", "www.google.com"),
    SYSTEM_ARENA_ENTER("SYSTEM_ARENA_ENTER", "PvP Enabled."),
    SYSTEM_ERROR_OUR_DEFINEDITEMS("SYSTEM_ERROR_OUR_DEFINEDITEMS", "Items have not been set yet."),

    PlAYER_DEATHABN_COMMAND("PlAYER_DEATHABN_COMMAND", "You cannot do this command while deathbanned."),

    PLAYER_HELP_STAFF_LIVES("PLAYER_HELP_STAFF_LIVES", "/dbans lives <add/remove> <player> <amount> | /dbans check <player>"),
    PLAYER_HELP_STAFF_ADMIN_ARENA("PLAYER_HELP_STAFF_ADMIN_ARENA","/dbans admin arena <setregion/setkit>"),
    PLAYER_HELP_STAFF_ADMIN_SPAWNPOINT("PLAYER_HELP_STAFF_ADMIN_SPAWNPOINT","/dbans admin setspawnpoint <spawn/deathban>"),
    PLAYER_HELP_STAFF_ADMIN_SETITEM("PLAYER_HELP_STAFF_ADMIN_SETITEM","/dbans admin setitem <info/lives>"),
    PLAYER_HELP_STAFF_ADMIN("PLAYER_HELP_STAFF_ADMIN", "/dbans admin <arena/setspawnpoint/setitem>"),
    PLAYER_HELP_STAFF("PLAYER_HELP_STAFF","Help | /dbans <lives/admin> | /dbans <ban/revive/check> <player>"),
    PLAYER_HELP_LIVES("PLAYER_HELP_LIVES","Help | /lives <check> | /lives send <player>"),
    PLAYER_NOPERMISSION("PLAYER_NOPERMISSION", "No permission!"),
    PLAYER_SIGN_CREATE_TIMER("PLAYER_SIGN_CREATE_TIMER", "You have created a timer sign."),
    PLAYER_SIGN_CREATE_LIVES("PLAYER_SIGN_CREATE_LIVES", "You have created a lives sign."),
    PLAYER_SIGN_DESTROY("PLAYER_SIGN_DESTROY", "You have broken a sign."),
    PLAYER_DEATHBANNED("PLAYER_DEATHBANNED", "&4You have banned death banned, %time% left!"),
    PLAYER_DEATHBANNED_CURRENTLY("PLAYER_DEATHBANNED_CURRENTLY", "You are currently death banned."),
    PLAYER_DEATHBAN_FINISHED("PLAYER_DEATHBAN_FINISHED", "Death ban has finished!"),
    PLAYER_LIVES_USED("PLAYER_LIVES_USED", "You have used a life %remaining%"),
    PLAYER_LIVES_NONE("PLAYER_LIVES_NONE", "You have no lives."),
    PLAYER_LIVES_BROUGHT("PLAYER_LIVES_BROUGHT", "You have brought %lives%."),
    STAFF_CMD_TARGET_REVIVE("STAFF_CMD_TARGET_REVIVE", "You have revived %target%."),
    STAFF_CMD_SENDER_REVIVE("STAFF_CMD_SENDER_REVIVE", "A staff member has revived you."),
    STAFF_CMD_ERROR_REVIVE_NOTBANNED("STAFF_CMD_ERROR_REVIVE_NOTBANNED", "%target% is not deathbanned!"),
    STAFF_CMD_SENDER_BAN("STAFF_CMD_SENDER_BAN", "You have death banned %target% for %time%."),
    STAFF_CMD_ERROR_BAN_PLAYERNOTFOUNT("STAFF_CMD_ERROR_BAN_PLAYERNOTFOUNT", "Can not find player."),
    STAFF_CMD_SENDER_CHECK("STAFF_CMD_SENDER_CHECK", "%target% deathbanned for %time%."),
    STAFF_CMD_ERROR_CHECK_NOTBANNED("STAFF_CMD_ERROR_CHECK_NOTBANNED", "Player not banned."),
    STAFF_CMD_LIVES_ADD_SENDER("STAFF_CMD_LIVES_ADD_SENDER", "You have given %target% %lives%."),
    STAFF_CMD_LIVES_ADD_TARGET("STAFF_CMD_LIVES_ADD_TARGET", "A staff member have given you %lives%."),
    STAFF_CMD_LIVES_ADD_ERROR_PLAYERNOTFOUND("STAFF_CMD_LIVES_ADD_ERROR_PLAYERNOTFOUND", "Can not find player."),
    STAFF_CMD_LIVES_REMOVE_SENDER("STAFF_CMD_LIVES_REMOVE_SENDER", "You have removed %lives% off %target%."),
    STAFF_CMD_LIVES_REMOVE_TARGET("STAFF_CMD_LIVES_REMOVE_TARGET", "A staff member have taken %lives% from you."),
    STAFF_CMD_LIVES_REMOVE_ERROR_NOLIVES("STAFF_CMD_LIVES_REMOVE_ERROR_NOLIVES", "Player does not have enough lives."),
    STAFF_CMD_LIVES_REMOVE_ERROR_PLAYERNOTFOUND("STAFF_CMD_LIVES_REMOVE_ERROR_PLAYERNOTFOUND", "Can not find player."),
    STAFF_CMD_LIVES_CHECK_SENDER("STAFF_CMD_LIVES_CHECK_SENDER", "%target% > %lives%"),
    STAFF_CMD_LIVES_CHECK_ERROR_PLAYERNOTFOUND("STAFF_CMD_LIVES_CHECK_ERROR_PLAYERNOTFOUND", "Can not find player."),
    STAFF_CMD_ADMIN_ARENA_SETREGION_SENDER_1("STAFF_CMD_ADMIN_ARENA_SETREGION_SENDER_1", "Selection 1."),
    STAFF_CMD_ADMIN_ARENA_SETREGION_SENDER_2("STAFF_CMD_ADMIN_ARENA_SETREGION_SENDER_2", "Selection 2."),
    STAFF_CMD_ADMIN_ARENA_SETREGION_SENDER_SUCCESS("STAFF_CMD_ADMIN_ARENA_SETREGION_SENDER_SUCCESS", "Saved region!"),
    STAFF_CMD_ADMIN_ARENA_SETKIT_SENDER("STAFF_CMD_ADMIN_ARENA_SETKIT_SENDER", "Saved kit!"),
    STAFF_CMD_ADMIN_SPAWNPOINT_SENDER_DEATHBAN("STAFF_CMD_ADMIN_SPAWNPOINT_SENDER_DEATHBAN", "Set the death room spawn."),
    STAFF_CMD_ADMIN_SPAWNPOINT_SENDER_SPAWN("STAFF_CMD_ADMIN_SPAWNPOINT_SENDER_SPAWN", "Set the spawn point."),
    STAFF_CMD_ADMIN_SETITEM_INFO("STAFF_CMD_ADMIN_SETITEM_INFO", "You have set the info item."),
    STAFF_CMD_ADMIN_SETITEM_LIVES("STAFF_CMD_ADMIN_SETITEM_LIVES", "You have set the lives item."),
    STAFF_CMD_ADMIN_SETITEM_ERROR_NOITEM("STAFF_CMD_ADMIN_SETITEM_ERROR_NOITEM", "You are not holding a item."),
    LIVES_CMD_CHECK("LIVES_CMD_CHECK", "You have %lives%."),
    LIVES_SEND_SENDER("LIVES_SEND_SENDER", "You have sent %lives% to %target%."),
    LIVES_SEND_TARGET("LIVES_SEND_TARGET", "You have received %lives% from %sender%."),
    LIVES_SEND_ERROR_PLAYERNOTFOUND("LIVES_SEND_ERROR_PLAYERNOTFOUND", "Can not find player."),
    LIVES_SEND_ERROR_NOLIVES("LIVES_SEND_ERROR_NOLIVES", "You do not have enough lives for this.");


    Lang(String path, String start) {
        this.path = path;
        this.def = start;
    }

    private final String path, def;
    private static FileConfiguration LANG;

    public static void setFile(FileConfiguration config) {
        LANG = config;
    }

    @Override
    public String toString() {
        return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def));
    }

    public void send(Player p) {
        String str = toString();
        p.sendMessage(str);
    }

    public String getDefault() {
        return this.def;
    }


    public String getPath() {
        return this.path;
    }

}
