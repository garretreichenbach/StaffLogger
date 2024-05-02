package invictus.earth.stafflogger;

import java.util.Date;

/**
 * [Description]
 *
 * @author Garret Reichenbach
 */
public class WebHookManager {

	public static void sendStaffModeWebHookEnable(String playerName, String reason) {
		DiscordWebhook webhook = new DiscordWebhook(getWebHookUrl());
		webhook.setUsername("Staff Logger");
		DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
		embed.setTitle("Staff Mode Enabled");
		embed.addField("Time Stamp", String.valueOf(new Date()), true);
		embed.addField("Player", playerName, true);
		embed.addField("Reason", reason, false);
		webhook.addEmbed(embed);
		try {
			webhook.execute();
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	public static void sendStaffModeWebHookDisable(String playerName) {
		DiscordWebhook webhook = new DiscordWebhook(getWebHookUrl());
		webhook.setUsername("Staff Logger");
		DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
		embed.setTitle("Staff Mode Disabled");
		embed.addField("Time Stamp", String.valueOf(new Date()), true);
		embed.addField("Player", playerName, true);
		webhook.addEmbed(embed);
		try {
			webhook.execute();
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	public static void sendStaffModeCommandUsageWebHook(String playerName, String command, String[] args) {
		DiscordWebhook webhook = new DiscordWebhook(getWebHookUrl());
		webhook.setUsername("Staff Logger");
		DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
		embed.setTitle("Staff Command Usage");
		embed.addField("Time Stamp", String.valueOf(new Date()), true);
		embed.addField("Player", playerName, true);
		embed.addField("Command", command, true);
		embed.addField("Arguments", String.join(" ", args), false);
		webhook.addEmbed(embed);
		try {
			webhook.execute();
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	private static String getWebHookUrl() {
		return ConfigManager.config.getString("webhook-url");
	}
}
