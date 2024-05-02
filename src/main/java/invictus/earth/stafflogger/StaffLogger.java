package invictus.earth.stafflogger;

import invictus.earth.stafflogger.commands.CommandManager;
import invictus.earth.stafflogger.data.permissions.PermissionManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class StaffLogger extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "Enabling StaffLogger...");
        APIManager.initialize(this);
        ConfigManager.initialize(this);
        EventManager.initialize(this);
        PermissionManager.initialize(this);
        CommandManager.initialize(this);
        getLogger().log(Level.INFO, "StaffLogger has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Disabling StaffLogger...");
    }
}
