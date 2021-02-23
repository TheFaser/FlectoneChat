package flectone.main;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class chat implements Listener {

	public chat(flectone plugin) {
	}
	flectone plugin = flectone.getInstance();
	@EventHandler
	public void local(AsyncPlayerChatEvent event) {
		String eventMessage = event.getMessage();
		Player eventPlayer = event.getPlayer();
		Location eventLocation = eventPlayer.getLocation();
		String format;
		if(eventMessage.startsWith(plugin.getConfig().getString("chat.global.condition"))) {
			eventMessage = eventMessage.substring(1);
			if(!eventMessage.isEmpty()) {
				for (Player player : new HashSet<Player>(event.getRecipients())) {
					List<String> senderIgnoreList = plugin.ignoreConfig.getStringList(player.getName());
					if(senderIgnoreList.contains(eventPlayer.getName())) {
						event.getRecipients().remove(player);
					}
				}
				format = this.setFormat(plugin.getConfig().getString("chat.global.prefix").replace("&", "§"));
				event.setFormat(format);
				event.setMessage(eventMessage);
				return;
			}
		} 
		for (Player player : new HashSet<Player>(event.getRecipients())) {
			if(!this.playerInRange(player.getLocation(), eventLocation, plugin.getConfig().getInt("chat.local.range"))) {
				event.getRecipients().remove(player);
			} 
		}
		
		if(event.getRecipients().size() == 1) {
			String msg = plugin.locale
					.getString("chat.no_recipients")
					.replace("&", "§");
			eventPlayer.sendMessage(msg);
		
		}
		for (Player player : new HashSet<Player>(event.getRecipients())) {
			List<String> senderIgnoreList = plugin.ignoreConfig.getStringList(player.getName());
			if(senderIgnoreList.contains(eventPlayer.getName())) {
				event.getRecipients().remove(player);
			}
		}
		format = this.setFormat(plugin.getConfig().getString("chat.local.prefix").replace("&", "§"));
		event.setFormat(format);
	}
	private boolean playerInRange(Location receiverLocation, Location senderLocation, int range) {
		if(receiverLocation.getWorld() != senderLocation.getWorld()){
			return false;
		}
		if(receiverLocation.distance(senderLocation) > range) {
			return false;
		}
		return true;
	}
	private String setFormat(String prefix) {
		String chatFormat = plugin.getConfig().getString("chat.format")
				.replace("&", "\u00a7")
				.replace("<player>", "%1$s")
				.replace("<message>", "%2$s")
				.replace("<prefix>", prefix);
		return chatFormat;
	}
}

