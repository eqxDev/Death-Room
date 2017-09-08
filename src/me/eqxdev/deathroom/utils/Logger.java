package me.eqxdev.deathroom.utils;

import me.eqxdev.deathroom.Main;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by eqxDev.
 */
public class Logger {
    public static void load() {
        File dataFolder = Main.getInstance().getDataFolder();
        if(!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        File saveTo = new File(Main.getInstance().getDataFolder(),"lives-error.txt");
        if(!saveTo.exists()) {
            try {
                saveTo.createNewFile();
    toFile("Error log for giving players lives.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public static void toFile(String str) {
        File saveTo = new File(Main.getInstance().getDataFolder(),"lives-error.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(saveTo,true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(str);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
