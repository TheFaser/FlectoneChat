package flectone.main;

import java.util.List; 

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public final class utils {
	
	
	public static void sendToPlayers(FileConfiguration ignoreConfig, String senderName, String messageText){
		for (Player player : Bukkit.getOnlinePlayers()) {
			List<String> senderIgnoreList = ignoreConfig.getStringList(player.getName());
			if(!senderIgnoreList.contains(senderName)) {
				player.sendMessage(messageText);
			}
		}
	}
	public static void playCommandSound(String commandName, CommandSender sender, String soundType) {
		flectone plugin = flectone.getInstance();
		boolean isEnable = plugin.getConfig().getBoolean("sound." + commandName + ".enable");
		String soundString = plugin.getConfig().getString("sound." + commandName + "." + soundType);
		if(isEnable == true) {
			Player player = Bukkit.getPlayer(sender.getName());
			player.playSound(player.getLocation(), soundString, 1, 0);
		}
	}
	public static String formatMessage(String receiver, String messageText, String mainString) {
		flectone plugin = flectone.getInstance();
		String message = plugin.locale.getString(mainString)
				.replace("&", "\u00a7")
				.replace("<player>", receiver)
				.replace("<message>", messageText);
		return message;
	}
}
