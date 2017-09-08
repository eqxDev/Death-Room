package me.eqxdev.deathroom.utils.objects;

import me.eqxdev.deathroom.utils.BukkitUtils;
import me.eqxdev.deathroom.utils.enums.SignType;
import me.eqxdev.deathroom.utils.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import sun.security.krb5.Config;

import java.util.UUID;

/**
 * Created by eqxDev.
 */
public class dSign {

    private org.bukkit.Location loc;
    private SignType type;
    private UUID id;

    public dSign(org.bukkit.Location loc, SignType type, UUID id) {
        this.loc = loc;
        this.type = type;
        if (id != null) {
            this.id = id;
        } else {
           this.id = UUID.randomUUID();
        }
    }

    public org.bukkit.Location getLoc() {
        return loc;
    }

    public void setLoc(org.bukkit.Location loc) {
        this.loc = loc;
    }

    public SignType getType() {
        return type;
    }

    public void setType(SignType type) {
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void save(JavaPlugin instance, String config, String path) {
        path = path + "." + id.toString() + ".";
        ConfigManager.get(config).set(path + "Type" ,getType().toString());
        ConfigManager.get(config).set(path + "Location.world" , getLoc().getWorld().getName());
        ConfigManager.get(config).set(path + "Location.x", getLoc().getX());
        ConfigManager.get(config).set(path + "Location.y", getLoc().getY());
        ConfigManager.get(config).set(path + "Location.z", getLoc().getZ());
        ConfigManager.get(config).set(path + "Location.pitch", getLoc().getPitch());
        ConfigManager.get(config).set(path + "Location.yaw", getLoc().getYaw());
        ConfigManager.save(instance,config);
    }

    public void remove(JavaPlugin instance, String config, String path) {
        String p = path;
        path = path + "." + id.toString() + ".";
        ConfigManager.remove(config,path + "Type");
        ConfigManager.remove(config,path + "Location.world");
        ConfigManager.remove(config,path + "Location.x");
        ConfigManager.remove(config,path + "Location.y");
        ConfigManager.remove(config,path + "Location.z");
        ConfigManager.remove(config,path + "Location.pitch");
        ConfigManager.remove(config,path + "Location.yaw");
        ConfigManager.remove(config,path + "Location");
        ConfigManager.remove(config,p + "." + id.toString());
        ConfigManager.save(instance,config);
    }
}
