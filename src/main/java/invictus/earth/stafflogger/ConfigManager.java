package invictus.earth.stafflogger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * [Description]
 *
 * @author Garret Reichenbach
 */
public class ConfigManager {

	public static FileConfiguration config;
	public static FileConfiguration messagesConfig;

	public static void initialize(StaffLogger plugin) {
		try {
			File configFile = new File(plugin.getDataFolder(), "config.yml");
			if(!configFile.exists()) {
				configFile.getParentFile().mkdirs();
				plugin.saveResource("config.yml", false);
			}
			config = new YamlConfiguration();
			saveDefaultConfig();
			config.load(configFile);

			File messagesFile = new File(plugin.getDataFolder(), "messages.yml");
			if(!messagesFile.exists()) {
				messagesFile.getParentFile().mkdirs();
				plugin.saveResource("messages.yml", false);
			}
			messagesConfig = new YamlConfiguration();
			saveDefaultMessages();
			messagesConfig.load(messagesFile);
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	private static void saveDefaultConfig() {
		config.options().copyDefaults(true);
		config.addDefault("staff-roles", new String[] {"Admin", "Developer", "Moderator"});
		config.addDefault("webhook-url", "[Discord Webhook URL]");
	}

	private static void saveDefaultMessages() {
		messagesConfig.options().copyDefaults(true);
		messagesConfig.addDefault("staff-mode-required", "&cYou must be in staff mode to use this command.");
	}
}
