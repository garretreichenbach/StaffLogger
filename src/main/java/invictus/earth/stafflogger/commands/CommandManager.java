package invictus.earth.stafflogger.commands;

import invictus.earth.stafflogger.StaffLogger;
import org.bukkit.command.Command;

import java.util.Objects;

/**
 * [Description]
 *
 * @author Garret Reichenbach
 */
public class CommandManager {

	private static StaffLogger plugin;

	public static void initialize(StaffLogger plugin) {
		CommandManager.plugin = plugin;
		Objects.requireNonNull(plugin.getCommand("staffmode")).setExecutor(new StaffModeCommand());
	}

	public static Command getCommand(String commandName) {
		return plugin.getServer().getCommandMap().getCommand(commandName);
	}
}
