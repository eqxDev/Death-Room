package me.eqxdev.deathroom;

import me.eqxdev.deathroom.listener.*;
import me.eqxdev.deathroom.runnable.Counter;
import me.eqxdev.deathroom.runnable.SignUpdate;
import me.eqxdev.deathroom.utils.*;
import me.eqxdev.deathroom.utils.cache.UUIDCache;
import me.eqxdev.deathroom.utils.enums.Lang;
import me.eqxdev.deathroom.utils.events.PlayerMove;
import me.eqxdev.deathroom.utils.managers.*;
import me.eqxdev.deathroom.utils.objects.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.util.*;

/**
 * Created by eqxDev.
 */
public class Main extends JavaPlugin {

    /*
        Do items (emerald paper)
        /lives
     */

    private QueManager queManager;
    private DPlayerManager dPlayerManager;
    private static Main main;
    private eCommandHelper commandHelper;
    private RankUtil rankUtil;
    private LivesManager livesManager;
    private RegionManager region;
    private dSignManager signsManager;
    private DInventory dInventory;
    private BukkitTask signUpdate;
    private BukkitTask counterUpdate;
    public Map<UUID, DPlayer> player = new HashMap<>();
    public List<String> commandsAllowed = new ArrayList<String>();

    public static Main getInstance() {
        return main;
    }
    public QueManager getQueManager() {return queManager;}
    public DPlayerManager getPlayerManager() {return dPlayerManager;}
    public eCommandHelper getCommandHelper() {
        return commandHelper;
    }
    public RankUtil getRankUtil() {return rankUtil;}
    public LivesManager getLivesManager() {
        return livesManager;
    }
    public RegionManager getRegionManager() {return region;}
    public dSignManager getSignsManager() {return signsManager;}
    public DInventory getInventory() {return dInventory;}

    @Override
    public void onEnable() {

        // Create instances
        main = this;
        queManager = new QueManager();
        dPlayerManager = new DPlayerManager();
        commandHelper = new eCommandHelper();
        rankUtil = new RankUtil();
        region = new RegionManager();
        livesManager = new LivesManager();
        signsManager = new dSignManager();
        ConfigManager.load(this,"kit.yml");
        dInventory = new DInventory(ConfigManager.get("kit.yml"));

        // Load config
        lang();
        ConfigManager.load(this,"data.yml");
        ConfigManager.load(this,"users.dat");

        // Int the api
        DeathRoom.get();

        // Load cache
       // dare();
        commandsAllowed = ConfigManager.get("data.yml").getStringList("DeathbannedCommands");
        dInventory.load();
        commandHelper.load(this);
        rankUtil.load();
        queManager.load();
        dPlayerManager.load();
        region.load();
        LocationManager.load();
        signsManager.load();
        Logger.load();
        UUIDCache.getInstance().setFile(new File(getDataFolder().getParent(), "uuid.cache"));

        // Load events
        events();

        // Init runnable
        counterUpdate = new Counter().runTaskTimerAsynchronously(this,20,20);
        signUpdate = new SignUpdate().runTaskTimerAsynchronously(this,20,20);
    }

    @Override
    public void onDisable() {

        // Ending runnable
        signUpdate.cancel();
        counterUpdate.cancel();

        // Save cache
        UUIDCache.getInstance().save();
        queManager.save();
        LocationManager.save();
        dPlayerManager.save();
        livesManager.save();
        region.save();
        signsManager.del(true);

    }

    // Utils
    private void events() {
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(queManager, this);
        getServer().getPluginManager().registerEvents(UUIDCache.getInstance(), this);
        getServer().getPluginManager().registerEvents(livesManager, this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(getRegionManager(), this);
        getServer().getPluginManager().registerEvents(new RegionEvents(), this);
        getServer().getPluginManager().registerEvents(signsManager, this);
        getServer().getPluginManager().registerEvents(new ServerCommand(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new EntityHitEvent(), this);
        getServer().getPluginManager().registerEvents(new DeathbanEvents(), this);
    //    getServer().getPluginManager().registerEvents(this, this);
    }

    private void lang() {
        ConfigManager.load(this,"messages.yml");
        Lang.setFile(ConfigManager.get("messages.yml"));
        for(Lang value : Lang.values()) {
            ConfigManager.get("messages.yml").addDefault(value.getPath(),value.getDefault());
        }
        ConfigManager.get("messages.yml").options().copyDefaults(true);
        ConfigManager.save(this,"messages.yml");
    }
}
