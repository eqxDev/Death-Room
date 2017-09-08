package me.eqxdev.deathroom.utils.cache;

import me.eqxdev.deathroom.Main;
import me.eqxdev.deathroom.utils.ExceptionHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by eqxDev.
 */
public class UUIDCache extends Chache<String, UUID> implements Listener {

    private static UUIDCache instance;

    private UUIDCache(){ instance = this; }

    public static UUIDCache getInstance(){
        return (instance == null ? new UUIDCache() : instance);
    }

    private Map<String, UUID> data = new HashMap<>();
    private File file;

    public Map<String, UUID> getCachedData() {
        return data;
    }

    public boolean contains(Player p){
        return contains(p.getName());
    }

    public void add(Player p){
        if(data.containsValue(p.getUniqueId()) && !data.containsKey(p.getName())){
            String key = "";
            for(Map.Entry<String, UUID> entry : data.entrySet()){
                if(entry.getValue().equals(p.getUniqueId())){
                    key = entry.getKey();
                    break;
                }
            }
            if(!key.equals("")){
                data.remove(key);
            }
        }
        add(p.getName(), p.getUniqueId());
    }

    public void remove(Player p){
        remove(p.getName());
    }

    public UUID get(final String name){
        UUID uuid = data.get(name);

        if(uuid == null) {
            new BukkitRunnable() {
                public void run() {
                    try {
                        UUID fetch = UUIDFetcher.getUUIDOf(name);

                        if(fetch != null) UUIDCache.getInstance().add(name, fetch);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskAsynchronously(Main.getInstance());

            uuid = data.get(name);
        }

        return uuid;
    }

    public String get(UUID uuid){
        for(Map.Entry<String, UUID> entry : data.entrySet()){
            if(entry.getValue().equals(uuid)) return entry.getKey();
        }
        return null;
    }

    public void setFile(File file){
        this.file = file;
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch(IOException ignored){}
        }
        load();
    }

    public File getFile(){
        return this.file;
    }

    public void save() {
        try{
            BufferedWriter writer = new BufferedWriter(new PrintWriter(file));

            for(Map.Entry<String, UUID> entry : data.entrySet()){
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }

            writer.close();
        }catch(Exception e){
            ExceptionHandler.handle(e);
        }
    }

    public void load() {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;

            while((line = reader.readLine()) != null){
                if(line.contains("=")){
                    String[] split = line.split("=");
                    data.put(split[0], UUID.fromString(split[1]));
                }
            }
        }catch(Exception e){
            ExceptionHandler.handle(e);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        add(e.getPlayer());
    }

}