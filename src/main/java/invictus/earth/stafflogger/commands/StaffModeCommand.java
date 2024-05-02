package invictus.earth.stafflogger.commands;

import invictus.earth.stafflogger.ConfigManager;
import invictus.earth.stafflogger.WebHookManager;
import invictus.earth.stafflogger.data.StaffModeManager;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

/**
 * [Description]
 *
 * @author Garret Reichenbach
 */
public class StaffModeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player player) {
			if(StaffModeManager.isStaffModeEnabled(player)) {
				StaffModeManager.setStaffMode(player, false);
				player.setGameMode(GameMode.SURVIVAL);
				WebHookManager.sendStaffModeWebHookDisable(player.getName());
				player.sendMessage(Objects.requireNonNull(ConfigManager.messagesConfig.getString("exiting-staff-mode")));
			} else {
				if(args == null || args.length == 0 || args[0] == null || args[0].isEmpty()) player.sendMessage("§cUsage: /staffmode <reason>");
				else {
					String reason = String.join(" ", args).replace("\"", "");
					StaffModeManager.setStaffMode(player, true);
					WebHookManager.sendStaffModeWebHookEnable(player.getName(), reason);
					player.sendMessage(Objects.requireNonNull(ConfigManager.messagesConfig.getString("entering-staff-mode")));
				}
			}
			return true;
		} else return false;
	}
}