package me.eqxdev.deathroom.utils.objects;

import me.eqxdev.deathroom.Main;
import me.eqxdev.deathroom.utils.FileUtils;
import me.eqxdev.deathroom.utils.Lists;
import me.eqxdev.deathroom.utils.Matcher;
import net.minecraft.server.v1_7_R4.Item;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by eqxDev.
 */
public class DInventory {

    private static ItemStack info;
    private static ItemStack lives;
    private FileConfiguration config;

    public DInventory(FileConfiguration configuration) {
        config = configuration;
    }

    private ItemStack[] contents;
    public ItemStack[] getContents() {
        return contents;
    }

    public void setContents(ItemStack[] contents) {
        config.set("inventory", Lists.asList(contents));
        save();
        this.contents = contents;
    }

    private ItemStack[] armour;
    public ItemStack[] getArmorContents() {
        return armour;
    }

    public void setArmorContents(ItemStack[] contents) {
        config.set("armor", Lists.asList(contents));
        save();
        armour = contents;
    }

    public void apply(Player player) {
            player.getInventory().setArmorContents(getArmorContents());
            player.getInventory().setContents(getContents());
            // Apply extra shit
    }

    public void applyDeath(Player p) {
            if (lives != null && info != null) {
                p.getInventory().setHeldItemSlot(0);
                p.getInventory().setItem(0, info);
                p.getInventory().setItem(1, lives);
                p.updateInventory();
                // Apply extra shit crap cock #Bants
        }
    }

    public void load() {
        if(config.contains("inventory")) {
            List<ItemStack> c = (List) config.get("inventory");
            contents = c.toArray(new ItemStack[c.size()]);
        }
        if(config.contains("armour")) {
            List<ItemStack> a = (List) config.get("armor");
            armour = a.toArray(new ItemStack[a.size()]);
        }
        if(config.contains("info")) {
            info = (ItemStack) config.get("info");
        }
        if(config.contains("lives")) {
            lives = (ItemStack) config.get("lives");
        }
    }

    public void save() {
        try {
            config.save(FileUtils.getOrCreate(Main.getInstance().getDataFolder(),"kit.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInfo(ItemStack is) {
        config.set("info", is);
        save();
        info = is;
    }
    public void setLives(ItemStack is) {
        config.set("lives", is);
        save();
        lives = is;
    }

    public ItemStack getLives() {
        return lives;
    }

    public ItemStack getInfo() {
        return info;
    }

    public boolean equalInfo(ItemStack is) {
        if(is.getType() == info.getType()) {
            if(is.hasItemMeta() == info.hasItemMeta()) {
                if(is.getItemMeta() == info.getItemMeta()) {
                    if(is.getDurability() == info.getDurability()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean equalLives(ItemStack is) {
        if(is.getType() == lives.getType()) {
            if(is.hasItemMeta() == lives.hasItemMeta()) {
                if(is.getItemMeta() == lives.getItemMeta()) {
                    if(is.getDurability() == lives.getDurability()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}


