package invictus.earth.stafflogger;

import invictus.earth.stafflogger.commands.CommandManager;
import invictus.earth.stafflogger.data.StaffModeManager;
import invictus.earth.stafflogger.data.permissions.PermissionManager;
import org.bukkit.command.Command;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Objects;

/**
 * [Description]
 *
 * @author Garret Reichenbach
 */
public class EventManager implements Listener {

	private static StaffLogger plugin;

	public static void initialize(StaffLogger plugin) {
		EventManager.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(new EventManager(), plugin);
	}

	/**
	 * Command event handler. Intercepts any admin commands and sends them to the staff logger to verify if the sender is in staff mode.
	 * <br/>If the sender is not in staff mode, it cancels the event and notifies the user.
	 */
	@EventHandler (priority = EventPriority.LOWEST)
	public void onCommand(PlayerCommandPreprocessEvent event) {
		String commandName = event.getMessage().split(" ")[0].replace("/", "");
		Command command = CommandManager.getCommand(commandName);
		if(command != null) {
			String permission = command.getPermission();
			if(permission != null) {
				if(PermissionManager.isStaffGroup(PermissionManager.getGroup(event.getPlayer()))) {
					if(PermissionManager.requiresStaff(permission)) {
						if(!commandName.equals("staffmode")) {
							if(!StaffModeManager.isStaffModeEnabled(event.getPlayer())) {
								event.getPlayer().sendMessage(Objects.requireNonNull(ConfigManager.messagesConfig.getString("staff-mode-required")));
								event.setCancelled(true);
							} else WebHookManager.sendStaffModeCommandUsageWebHook(event.getPlayer().getName(), commandName, event.getMessage().replaceFirst("/" + commandName, "").split(" "));
						}
					}
				}
			} else {
				if(PermissionManager.isStaffGroup(PermissionManager.getGroup(event.getPlayer()))) {
					if(!StaffModeManager.isStaffModeEnabled(event.getPlayer())) {
						event.getPlayer().sendMessage(Objects.requireNonNull(ConfigManager.messagesConfig.getString("staff-mode-required")));
						event.setCancelled(true);
					} else WebHookManager.sendStaffModeCommandUsageWebHook(event.getPlayer().getName(), commandName, event.getMessage().replaceFirst("/" + commandName, "").split(" "));
				}
			}
		}
	}
}
