package invictus.earth.stafflogger.data.permissions;

import invictus.earth.stafflogger.APIManager;
import invictus.earth.stafflogger.StaffLogger;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;

import java.util.Objects;

/**
 * Manages permissions.
 *
 * @author Garret Reichenbach
 */
public class PermissionManager {

	private static StaffLogger plugin;

	public static void initialize(StaffLogger plugin) {
		PermissionManager.plugin = plugin;
	}

	public static boolean hasPermission(Player player, String permission) {
		User user = APIManager.getLuckPerms().getUserManager().getUser(player.getUniqueId());
		if(user != null) return user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
		else return player.hasPermission(permission);
	}

	public static boolean hasPermission(Group group, String permission) {
		return group.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
	}

	/**
	 * Checks if this group is a staff group.
	 * @param group The group to check.
	 * @return True if the group is a staff group.
	 */
	public static boolean isStaffGroup(Group group) {
		return Objects.requireNonNull(plugin.getConfig().getList("staff-roles")).contains(group.getName());
	}

	public static boolean requiresStaff(String permission) {
		Group defaultGroup = APIManager.getLuckPerms().getGroupManager().getGroup("default");
		assert defaultGroup != null;
		return !hasPermission(defaultGroup, permission);
	}

	public static boolean isInDefaultGroup(Player player) {
		User user = APIManager.getLuckPerms().getUserManager().getUser(player.getUniqueId());
		if(user != null) return user.getPrimaryGroup().equalsIgnoreCase("default");
		else return player.hasPermission("group.default");
	}

	public static Group getGroup(Player player) {
		User user = APIManager.getLuckPerms().getUserManager().getUser(player.getUniqueId());
		String groupName = "default";
		if(user != null) groupName = user.getPrimaryGroup();
		return APIManager.getLuckPerms().getGroupManager().getGroup(groupName);
	}
}
