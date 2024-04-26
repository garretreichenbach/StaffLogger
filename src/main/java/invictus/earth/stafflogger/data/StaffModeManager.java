package invictus.earth.stafflogger.data;

import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * [Description]
 *
 * @author Garret Reichenbach
 */
public class StaffModeManager {

	private static final HashMap<Player, Boolean> staffMode = new HashMap<>();

	public static boolean isStaffModeEnabled(Player player) {
		return staffMode.getOrDefault(player, false);
	}

	public static void setStaffMode(Player player, boolean enabled) {
		staffMode.put(player, enabled);
	}
}
