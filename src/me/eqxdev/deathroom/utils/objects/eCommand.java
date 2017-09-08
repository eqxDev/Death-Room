package me.eqxdev.deathroom.utils.objects;

import me.eqxdev.deathroom.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eqxDev.
 */
public abstract class eCommand {

    private String cmd;
    private String perm;
    private boolean canceled;
    private List<String> help;

    public eCommand(String name) {
        this(name, null, null);
        Main.getInstance().getCommandHelper().cmds.put(name, this);
    }

    public eCommand(String name, String permission) {
        this(name, permission,null);
        Main.getInstance().getCommandHelper().cmds.put(name, this);
    }


    public eCommand(String name, String permission, List<String> help) {
        this.cmd = name;
        this.perm = permission;
        this.help = help;
        Main.getInstance().getCommandHelper().cmds.put(name, this);
    }

    public abstract void execute(Player sender, String[] args);


    public String getCmd() {
        return this.cmd;
    }

    public String getPermission() {
        return this.perm;
    }


    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public List<String> getHelp() {
        if(help !=null) return help;
        return null;
    }

    public void setHelp(List<String> help) {
        this.help = help;
    }
}