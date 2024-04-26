package invictus.earth.stafflogger;

import net.luckperms.api.LuckPerms;

import java.util.Objects;

/**
 * [Description]
 *
 * @author Garret Reichenbach
 */
public class APIManager {

	private static LuckPerms luckPerms;

	public static void initialize(StaffLogger plugin) {
		luckPerms = Objects.requireNonNull(plugin.getServer().getServicesManager().getRegistration(LuckPerms.class)).getProvider();
	}

	public static LuckPerms getLuckPerms() {
		return luckPerms;
	}
}
