package me.eqxdev.deathroom.utils;

import me.eqxdev.deathroom.Main;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by eqxDev.
 */
public class FileUtils {


    public static File getOrCreate(File folder, String name){
        try{
            if(!folder.exists()) folder.mkdirs();
            File f = new File(folder, name);
            if(!f.exists()){
                f.createNewFile();
            }
            return f;
        }catch(IOException ignored){}
        return null;
    }

    public static void copy(File from, File to){
        FileUtil.copy(from, to);
    }

    public static File saveDefault(Plugin plugin, String name){
        InputStream stream = Main.getInstance().getResource(name);
        if(stream != null){
            plugin.saveResource(name, false);
        }

        return getOrCreate(plugin.getDataFolder(), name);
    }


}
